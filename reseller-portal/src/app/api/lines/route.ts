import { NextRequest, NextResponse } from 'next/server';
import { verifyAuthToken } from '@/lib/auth';
import { adminDb } from '@/lib/firebaseAdmin';
import { ownsRecord, permittedOwnerIds, serializeValue } from '@/lib/accessScope';

export const dynamic = 'force-dynamic';

export async function GET(request: NextRequest) {
  const auth = await verifyAuthToken(request);
  if (auth.status !== 'authenticated' || !['admin', 'reseller', 'subseller'].includes(auth.context.role)) {
    return NextResponse.json({ error: 'Pristup odbijen.' }, { status: auth.status === 'unauthenticated' ? 401 : 403 });
  }
  const ownerIds = await permittedOwnerIds(auth.context);
  const requestedId = request.nextUrl.searchParams.get('id')?.trim();
  const [licenses, activities, transactions] = await Promise.all([
    adminDb.collection('licenses').get(), adminDb.collection('activity_logs').get(), adminDb.collection('transactions').get()
  ]);
  const visible = licenses.docs.filter((d: any) => ownsRecord(d.data(), ownerIds));
  if (!requestedId) {
    return NextResponse.json({ lines: visible.map((d: any) => ({ id: d.id, ...serializeValue(d.data()) })) }, { headers: { 'Cache-Control': 'no-store, private' } });
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
  return NextResponse.json({ line: { id: license.id, ...serializeValue(license.data()) }, history }, { headers: { 'Cache-Control': 'no-store, private' } });
}
