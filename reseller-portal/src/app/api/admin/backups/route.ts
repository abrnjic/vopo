import { NextRequest, NextResponse } from 'next/server';
import { verifyAuthToken } from '@/lib/auth';
import { adminDb } from '@/lib/firebaseAdmin';
import { startFirestoreExport } from '@/lib/operations';

export const dynamic = 'force-dynamic';

async function adminOnly(request: NextRequest) {
  const auth = await verifyAuthToken(request);
  return auth.status === 'authenticated' && auth.context.role === 'admin' ? auth : null;
}

export async function GET(request: NextRequest) {
  if (!await adminOnly(request)) return NextResponse.json({ error: 'Forbidden' }, { status: 403 });
  const snap = await adminDb.collection('backup_runs').get();
  const runs = snap.docs.map((d: any) => ({ id: d.id, ...d.data() }))
    .sort((a: any, b: any) => String(b.startedAt).localeCompare(String(a.startedAt))).slice(0, 10);
  return NextResponse.json({ configured: Boolean(process.env.FIRESTORE_BACKUP_BUCKET || process.env.NEXT_PUBLIC_FIREBASE_STORAGE_BUCKET), runs });
}

export async function POST(request: NextRequest) {
  const auth = await adminOnly(request);
  if (!auth) return NextResponse.json({ error: 'Forbidden' }, { status: 403 });
  try {
    const result = await startFirestoreExport();
    await adminDb.collection('activity_logs').doc(`backup_${Date.now()}`).set({
      userId: auth.context.uid, userEmail: auth.context.email || '', role: 'admin', action: 'FIRESTORE_BACKUP_STARTED',
      details: { message: result.message || 'Firestore backup pokrenut', outputUriPrefix: result.outputUriPrefix || null }, timestamp: new Date().toISOString()
    });
    return NextResponse.json(result, { status: result.configured ? 200 : 409 });
  } catch (error) {
    return NextResponse.json({ error: error instanceof Error ? error.message : 'Backup nije pokrenut' }, { status: 500 });
  }
}
