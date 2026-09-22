import { NextRequest, NextResponse } from 'next/server';
import { verifyAuthToken } from '@/lib/auth';
import { adminDb } from '@/lib/firebaseAdmin';
import { ownsRecord, permittedOwnerIds, serializeValue } from '@/lib/accessScope';
import { daysUntil } from '@/lib/operations';

export const dynamic = 'force-dynamic';

export async function GET(request: NextRequest) {
  const auth = await verifyAuthToken(request);
  if (auth.status !== 'authenticated' || !['admin', 'reseller', 'subseller'].includes(auth.context.role)) return NextResponse.json({ error: 'Pristup odbijen.' }, { status: 403 });
  const ownerIds = await permittedOwnerIds(auth.context);
  const [licenseSnap, txSnap] = await Promise.all([adminDb.collection('licenses').get(), adminDb.collection('transactions').get()]);
  const licenses = licenseSnap.docs.filter((d: any) => ownsRecord(d.data(), ownerIds)).map((d: any) => ({ id: d.id, ...serializeValue(d.data()) }));
  const transactions = txSnap.docs.filter((d: any) => ownsRecord(d.data(), ownerIds)).map((d: any) => serializeValue(d.data()));
  const creditsUsed = transactions.reduce((sum: number, tx: any) => sum + Number(tx.creditsDeducted || 0), 0);
  const creditsTransferred = transactions.reduce((sum: number, tx: any) => sum + Math.max(0, Number(tx.creditsTransferred ?? tx.creditDelta ?? 0)), 0);
  const expiry30 = licenses.filter((l: any) => { const d = daysUntil(l.expiresAt); return d !== null && d >= 0 && d <= 30; }).length;
  return NextResponse.json({
    generatedAt: new Date().toISOString(),
    summary: {
      totalActivations: licenses.length,
      active: licenses.filter((l: any) => String(l.status).toLowerCase() === 'active').length,
      trials: licenses.filter((l: any) => String(l.status).toLowerCase() === 'trial').length,
      lifetime: licenses.filter((l: any) => l.isLifetime === true).length,
      expired: licenses.filter((l: any) => String(l.status).toLowerCase() === 'expired' || (daysUntil(l.expiresAt) ?? 1) < 0).length,
      expiringIn30Days: expiry30, creditsUsed, creditsTransferred
    },
    expirations: licenses.filter((l: any) => !l.isLifetime && l.expiresAt).map((l: any) => ({ deviceId: l.deviceId || l.id, customerName: l.customerName || '', status: l.status, expiresAt: l.expiresAt, daysRemaining: daysUntil(l.expiresAt) })).sort((a: any, b: any) => (a.daysRemaining ?? 99999) - (b.daysRemaining ?? 99999))
  }, { headers: { 'Cache-Control': 'no-store, private' } });
}
