import { NextRequest, NextResponse } from 'next/server';
import { verifyAuthToken } from '@/lib/auth';
import { adminDb } from '@/lib/firebaseAdmin';
import { permittedOwnerIds, serializeValue } from '@/lib/accessScope';

export const dynamic = 'force-dynamic';

export async function GET(request: NextRequest) {
  const auth = await verifyAuthToken(request);
  if (auth.status !== 'authenticated') return NextResponse.json({ error: 'Unauthorized' }, { status: 401 });
  const owners = await permittedOwnerIds(auth.context);
  const snap = await adminDb.collection('notifications').get();
  const notifications = snap.docs.filter((d: any) => owners === null || owners.includes(d.data()?.userId) || owners.includes(d.data()?.resellerId))
    .map((d: any) => ({ id: d.id, ...serializeValue(d.data()) })).sort((a: any, b: any) => String(b.createdAt).localeCompare(String(a.createdAt))).slice(0, 50);
  return NextResponse.json({ notifications });
}

export async function PATCH(request: NextRequest) {
  const auth = await verifyAuthToken(request);
  if (auth.status !== 'authenticated') return NextResponse.json({ error: 'Unauthorized' }, { status: 401 });
  const { id } = await request.json();
  const ref = adminDb.collection('notifications').doc(String(id || ''));
  const snap = await ref.get();
  const owners = await permittedOwnerIds(auth.context);
  if (!snap.exists || !(owners === null || owners.includes(snap.data()?.userId) || owners.includes(snap.data()?.resellerId))) return NextResponse.json({ error: 'Not found' }, { status: 404 });
  await ref.update({ read: true, readAt: new Date().toISOString() });
  return NextResponse.json({ success: true });
}
