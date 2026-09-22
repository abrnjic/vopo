import { adminDb } from '@/lib/firebaseAdmin';
import { FieldValue } from 'firebase-admin/firestore';

export const LICENSE_NOTICE_DAYS = [30, 7, 1, 0] as const;

const PROSERVERS_HTTPS = 'https://proservers.club';
const PROSERVERS_HTTP = 'http://proservers.club';

export function migrateProserversUrl(value: unknown): unknown {
  if (typeof value !== 'string') return value;
  return value === PROSERVERS_HTTPS || value.startsWith(`${PROSERVERS_HTTPS}/`)
    ? `${PROSERVERS_HTTP}${value.slice(PROSERVERS_HTTPS.length)}`
    : value;
}

function migrateDomainList(value: unknown): { value: unknown; changed: boolean } {
  if (!Array.isArray(value)) return { value, changed: false };
  const migrated = value.map(migrateProserversUrl);
  const deduplicated = [...new Set(migrated)];
  return { value: deduplicated, changed: JSON.stringify(value) !== JSON.stringify(deduplicated) };
}

/**
 * Idempotent production data repair for the Proservers endpoint protocol.
 * It updates the global catalog, every portal account and existing line
 * configuration so devices receive the correct URL on their next license poll.
 */
export async function migrateProserversDomainToHttp() {
  const result = { settings: 0, users: 0, licenses: 0 };
  const catalogRef = adminDb.collection('settings').doc('domainCatalog');
  const catalog = await catalogRef.get();
  if (catalog.exists) {
    const assigned = migrateDomainList(catalog.data()?.assignedDomains);
    if (assigned.changed) {
      await catalogRef.set({ assignedDomains: assigned.value, updatedAt: FieldValue.serverTimestamp() }, { merge: true });
      result.settings++;
    }
  }

  const users = await adminDb.collection('users').get();
  for (const user of users.docs) {
    const data = user.data() || {};
    const assigned = migrateDomainList(data.assignedDomains);
    const custom = migrateDomainList(data.customDomains);
    if (!assigned.changed && !custom.changed) continue;
    await adminDb.collection('users').doc(user.id).set({
      assignedDomains: assigned.value,
      customDomains: custom.value,
      updatedAt: FieldValue.serverTimestamp()
    }, { merge: true });
    result.users++;
  }

  const licenses = await adminDb.collection('licenses').get();
  for (const license of licenses.docs) {
    const data = license.data() || {};
    const selectedDomain = migrateProserversUrl(data.selectedDomain);
    const currentConfig = data.xtreamConfig && typeof data.xtreamConfig === 'object'
      ? data.xtreamConfig as Record<string, unknown>
      : null;
    const configUrl = currentConfig ? migrateProserversUrl(currentConfig.url) : undefined;
    const selectedDomainChanged = selectedDomain !== data.selectedDomain;
    const configChanged = Boolean(currentConfig && configUrl !== currentConfig.url);
    if (!selectedDomainChanged && !configChanged) continue;
    const update: Record<string, unknown> = { updatedAt: FieldValue.serverTimestamp() };
    if (selectedDomainChanged) update.selectedDomain = selectedDomain;
    if (currentConfig && configChanged) update.xtreamConfig = { ...currentConfig, url: configUrl };
    await adminDb.collection('licenses').doc(license.id).set(update, { merge: true });
    result.licenses++;
  }

  if (result.settings || result.users || result.licenses) {
    await adminDb.collection('activity_logs').doc().set({
      userId: 'system', role: 'system', action: 'MIGRATE_PROSERVERS_HTTP',
      details: `Katalog: ${result.settings}, korisnici: ${result.users}, linije: ${result.licenses}`,
      timestamp: FieldValue.serverTimestamp()
    });
  }
  return result;
}

export function asDate(value: any): Date | null {
  if (!value) return null;
  if (typeof value.toDate === 'function') return value.toDate();
  const parsed = new Date(value);
  return Number.isNaN(parsed.getTime()) ? null : parsed;
}

export function daysUntil(value: any, now = new Date()): number | null {
  const expiry = asDate(value);
  if (!expiry) return null;
  return Math.ceil((expiry.getTime() - now.getTime()) / 86_400_000);
}

export async function createExpiryNotifications(now = new Date()) {
  const snapshot = await adminDb.collection('licenses').get();
  let created = 0;
  for (const license of snapshot.docs) {
    const data = license.data();
    if (data.isLifetime) continue;
    const remaining = daysUntil(data.expiresAt, now);
    if (remaining === null || !LICENSE_NOTICE_DAYS.includes(remaining as any)) continue;
    const ownerId = data.subsellerId || data.resellerId;
    if (!ownerId) continue;
    const expiry = asDate(data.expiresAt)!;
    const id = `${ownerId}_${license.id}_${remaining}_${expiry.toISOString().slice(0, 10)}`.replace(/[^a-zA-Z0-9_-]/g, '_');
    const ref = adminDb.collection('notifications').doc(id);
    const existing = await ref.get();
    if (existing.exists) continue;
    await ref.set({
      userId: ownerId,
      resellerId: data.resellerId || ownerId,
      deviceId: data.deviceId || license.id,
      licenseId: license.id,
      type: remaining === 0 ? 'LICENSE_EXPIRED' : 'LICENSE_EXPIRING',
      title: remaining === 0 ? 'Licenca istječe danas' : `Licenca istječe za ${remaining} dana`,
      message: `Uređaj ${data.deviceId || license.id} · ${expiry.toLocaleDateString('hr-HR')}`,
      daysRemaining: remaining,
      read: false,
      createdAt: now.toISOString()
    });
    created++;
  }
  return { scanned: snapshot.size, created };
}

export async function startFirestoreExport() {
  const projectId = process.env.FIREBASE_PROJECT_ID || process.env.NEXT_PUBLIC_FIREBASE_PROJECT_ID;
  const bucket = process.env.FIRESTORE_BACKUP_BUCKET || process.env.NEXT_PUBLIC_FIREBASE_STORAGE_BUCKET;
  if (!projectId || !bucket) {
    return { configured: false, started: false, message: 'Postavite FIRESTORE_BACKUP_BUCKET u Vercel postavkama.' };
  }
  // Initializing adminDb also initializes the Firebase Admin application.
  await adminDb.collection('system').doc('backup_config').get();
  const { getApps } = await import('firebase-admin/app');
  const credential = getApps()[0]?.options.credential;
  if (!credential) throw new Error('Firebase Admin credential nije dostupan.');
  const token = await credential.getAccessToken();
  const stamp = new Date().toISOString().replace(/[:.]/g, '-');
  const outputUriPrefix = `gs://${bucket.replace(/^gs:\/\//, '').replace(/\/$/, '')}/firestore/${stamp}`;
  const response = await fetch(`https://firestore.googleapis.com/v1/projects/${projectId}/databases/(default):exportDocuments`, {
    method: 'POST',
    headers: { Authorization: `Bearer ${token.access_token}`, 'Content-Type': 'application/json' },
    body: JSON.stringify({ outputUriPrefix })
  });
  const result = await response.json();
  const runId = `backup_${stamp}`;
  await adminDb.collection('backup_runs').doc(runId).set({
    startedAt: new Date().toISOString(), outputUriPrefix, operationName: result.name || null,
    status: response.ok ? 'started' : 'failed', error: response.ok ? null : (result.error?.message || 'Firestore export nije pokrenut')
  });
  if (!response.ok) throw new Error(result.error?.message || 'Firestore export nije pokrenut');
  return { configured: true, started: true, operationName: result.name, outputUriPrefix };
}
