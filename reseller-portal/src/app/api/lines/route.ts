import { NextRequest, NextResponse } from 'next/server';
import { verifyAuthToken } from '@/lib/auth';
import { adminDb } from '@/lib/firebaseAdmin';
import { ownsRecord, permittedOwnerIds, serializeValue } from '@/lib/accessScope';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';
import { DomainSchema, domainsForUser } from '@/lib/domains';
import { hashLinePin, LINE_PIN_PATTERN, linePinMatches } from '@/lib/lineSecurity';
import { requestIp, writeSecurityLog } from '@/lib/securityLog';

export const dynamic = 'force-dynamic';

const EditLineSchema = z.object({
  id: z.string().trim().min(1).max(50),
  customerName: z.string().trim().max(100),
  customerContact: z.string().trim().max(100),
  selectedDomain: DomainSchema,
  username: z.string().trim().min(1).max(100),
  password: z.string().max(100).optional(),
  currentPin: z.string().max(32).optional(),
  newPin: z.string().regex(LINE_PIN_PATTERN).optional()
}).strict();

function safeLine(id: string, data: Record<string, any>) {
  const visible = Object.fromEntries(Object.entries(data).filter(([key]) => !['linePinHash', 'accessTokenHash', 'xtreamConfig', 'createdByUid', 'createdByRole'].includes(key)));
  const xtreamConfig = data.xtreamConfig;
  return {
    id, ...serializeValue(visible),
    xtreamConfig: { url: xtreamConfig?.url || '', username: xtreamConfig?.username || '' },
    hasLinePassword: Boolean(xtreamConfig?.password),
    hasLinePin: Boolean(data.linePinHash)
  };
}

type Account = { uid: string; label: string; role: string; parentLabel?: string };

function accountFor(uid: unknown, users: Map<string, Record<string, any>>): Account | null {
  if (typeof uid !== 'string' || !uid || uid === 'self_registered') return null;
  const data = users.get(uid) || {};
  const parent = data.parentResellerId ? users.get(data.parentResellerId) : null;
  return {
    uid,
    label: data.username || data.email || uid,
    role: data.role || 'unknown',
    ...(parent ? { parentLabel: parent.username || parent.email || data.parentResellerId } : {})
  };
}

function creatorIds(activities: Array<{ data: () => Record<string, any> }>) {
  const candidates = new Map<string, { uid: string; timestamp: string }>();
  for (const entry of activities) {
    const log = entry.data();
    if (!['CREATE_LICENSE', 'UPDATE_LINE', 'CONNECT_DEVICE'].includes(log.action) || !log.userId) continue;
    const deviceId = log.deviceId || (typeof log.details === 'string'
      ? log.details.match(/(?:device |uređaj )([A-Za-z0-9-]+)/i)?.[1]
      : null);
    if (!deviceId) continue;
    const timestamp = String(serializeValue(log.timestamp) || '');
    const previous = candidates.get(deviceId);
    if (!previous || (timestamp && (!previous.timestamp || timestamp < previous.timestamp))) {
      candidates.set(deviceId, { uid: log.userId, timestamp });
    }
  }
  return candidates;
}

export async function GET(request: NextRequest) {
  const auth = await verifyAuthToken(request);
  if (auth.status !== 'authenticated' || !['admin', 'reseller', 'subseller'].includes(auth.context.role)) {
    return NextResponse.json({ error: 'Pristup odbijen.' }, { status: auth.status === 'unauthenticated' ? 401 : 403 });
  }
  const ownerIds = await permittedOwnerIds(auth.context);
  const requestedId = request.nextUrl.searchParams.get('id')?.trim();
  const [licenses, activities, transactions, users] = await Promise.all([
    adminDb.collection('licenses').get(), adminDb.collection('activity_logs').get(), adminDb.collection('transactions').get(),
    auth.context.role === 'admin' ? adminDb.collection('users').get() : Promise.resolve({ docs: [] })
  ]);
  const userMap = new Map<string, Record<string, any>>(users.docs.map((entry: any) => [entry.id, entry.data()]));
  const creatorMap = creatorIds(activities.docs);
  const displayLine = (entry: any) => {
    const data = entry.data();
    const deviceId = data.deviceId || entry.id;
    return {
      ...safeLine(entry.id, data),
      ...(auth.context.role === 'admin' ? {
        ownerAccount: accountFor(data.resellerId, userMap),
        creatorAccount: accountFor(data.createdByUid || creatorMap.get(deviceId)?.uid, userMap)
      } : {})
    };
  };
  const visible = licenses.docs.filter((d: any) => ownsRecord(d.data(), ownerIds));
  if (!requestedId) {
    return NextResponse.json({ lines: visible.map(displayLine) }, { headers: { 'Cache-Control': 'no-store, private' } });
  }
  const license = visible.find((d: any) => d.id === requestedId || d.data()?.deviceId === requestedId);
  if (!license) return NextResponse.json({ error: 'Linija nije pronađena.' }, { status: 404 });
  const deviceId = license.data()?.deviceId || license.id;
  const relates = (data: any) => {
    const text = typeof data.details === 'string' ? data.details : JSON.stringify(data.details || '');
    return [data.deviceId, data.oldDeviceId, data.newDeviceId, data.licenseId].includes(deviceId) || text.includes(deviceId);
  };
  const history = [
    ...activities.docs.filter((d: any) => relates(d.data())).map((d: any) => ({ id: d.id, source: 'activity', ...serializeValue(d.data()) })),
    ...transactions.docs.filter((d: any) => relates(d.data())).map((d: any) => ({ id: d.id, source: 'transaction', ...serializeValue(d.data()) }))
  ].sort((a: any, b: any) => String(b.timestamp || b.updatedAt || '').localeCompare(String(a.timestamp || a.updatedAt || '')));
  return NextResponse.json({ line: displayLine(license), history }, { headers: { 'Cache-Control': 'no-store, private' } });
}

export async function PATCH(request: NextRequest) {
  const auth = await verifyAuthToken(request);
  if (auth.status !== 'authenticated' || !['admin', 'reseller', 'subseller'].includes(auth.context.role)) {
    return NextResponse.json({ error: 'Pristup odbijen.' }, { status: auth.status === 'unauthenticated' ? 401 : 403 });
  }
  const parsed = EditLineSchema.safeParse(await request.json().catch(() => null));
  if (!parsed.success) return NextResponse.json({ error: 'Provjerite podatke linije i format PIN-a.' }, { status: 400 });

  const input = parsed.data;
  const ownerIds = await permittedOwnerIds(auth.context);
  const ref = adminDb.collection('licenses').doc(input.id);
  const result = await adminDb.runTransaction(async (tx: any) => {
    const snapshot = await tx.get(ref);
    if (!snapshot.exists) return { error: 'Linija nije pronađena.', status: 404 };
    const before = snapshot.data() || {};
    if (before.status === 'Transferred') return { error: 'Prenesena linija ne može se uređivati.', status: 409 };
    if (ownerIds !== null && !ownerIds.includes(before.resellerId)) {
      return { error: 'Nemate pravo uređivati ovu liniju.', status: 403 };
    }
    if (typeof before.resellerId !== 'string' || !before.resellerId) return { error: 'Vlasnik linije nije pronađen.', status: 409 };
    const ownerRef = adminDb.collection('users').doc(before.resellerId);
    const owner = await tx.get(ownerRef);
    if (auth.context.role !== 'admin' && !domainsForUser(owner.data() || {}).includes(input.selectedDomain)) {
      return { error: 'Domena nije dodijeljena vlasniku linije.', status: 403 };
    }
    const hasPin = Boolean(before.linePinHash);
    if (auth.context.role !== 'admin' && hasPin && !linePinMatches(input.currentPin || '', before.linePinHash)) {
      return { error: 'Postojeći PIN nije ispravan.', status: 403, invalidPin: true, resellerId: before.resellerId };
    }
    if (auth.context.role !== 'admin' && !hasPin && !input.newPin) {
      return { error: 'Za staru liniju prvo postavite novi PIN.', status: 400 };
    }
    const password = input.password?.trim() ? input.password : before.xtreamConfig?.password;
    if (!password) return { error: 'Unesite lozinku linije.', status: 400 };

    const next = {
      customerName: input.customerName,
      customerContact: input.customerContact,
      selectedDomain: input.selectedDomain,
      xtreamConfig: { ...before.xtreamConfig, url: input.selectedDomain, username: input.username, password },
      updatedAt: FieldValue.serverTimestamp(),
      ...(input.newPin ? { linePinHash: hashLinePin(input.newPin) } : {})
    };
    const changedFields = [
      ...(before.customerName !== next.customerName ? ['customerName'] : []),
      ...(before.customerContact !== next.customerContact ? ['customerContact'] : []),
      ...(before.selectedDomain !== next.selectedDomain || before.xtreamConfig?.url !== input.selectedDomain ? ['selectedDomain'] : []),
      ...(before.xtreamConfig?.username !== input.username ? ['username'] : []),
      ...(before.xtreamConfig?.password !== password ? ['password'] : []),
      ...(input.newPin ? ['linePin'] : [])
    ];
    if (changedFields.length) {
      tx.update(ref, next);
      tx.set(adminDb.collection('activity_logs').doc(), {
        userId: auth.context.uid,
        userEmail: auth.context.email || '',
        role: auth.context.role,
        action: 'EDIT_LINE',
        deviceId: before.deviceId || input.id,
        resellerId: before.resellerId,
        details: { changedFields, domain: input.selectedDomain },
        timestamp: FieldValue.serverTimestamp()
      });
    }
    return { success: true, changedFields, line: safeLine(input.id, { ...before, ...next }) };
  });
  if ('error' in result) {
    if (result.invalidPin) await writeSecurityLog({ eventType: 'INVALID_LINE_PIN', deviceId: input.id, resellerId: result.resellerId, actorId: auth.context.uid, actorRole: auth.context.role, ip: requestIp(request.headers), details: 'Neuspjela potvrda PIN-a pri uređivanju linije.' });
    return NextResponse.json({ error: result.error }, { status: result.status });
  }
  return NextResponse.json(result, { headers: { 'Cache-Control': 'no-store, private' } });
}
