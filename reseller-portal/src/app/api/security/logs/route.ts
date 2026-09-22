import { NextRequest, NextResponse } from 'next/server';
import { verifyAuthToken } from '@/lib/auth';
import { adminDb } from '@/lib/firebaseAdmin';

function toIso(value: any): string | null {
  const date = value?.toDate?.() || (value instanceof Date ? value : null);
  return date ? date.toISOString() : null;
}

export async function GET(req: NextRequest) {
  const auth = await verifyAuthToken(req);
  if (auth.status !== 'authenticated') {
    return NextResponse.json({ error: auth.error || 'Unauthorized' }, { status: auth.status === 'error' ? 500 : 401 });
  }
  if (!['admin', 'reseller', 'subseller'].includes(auth.context.role)) {
    return NextResponse.json({ error: 'Forbidden' }, { status: 403 });
  }

  const collection = adminDb.collection('security_logs');
  const snapshot = auth.context.role === 'admin'
    ? await collection.get()
    : await collection.where('resellerId', '==', auth.context.uid).get();
  const logs = snapshot.docs
    .map((doc: any) => ({ id: doc.id, ...doc.data() }))
    .sort((a: any, b: any) => (b.timestamp?.toMillis?.() || 0) - (a.timestamp?.toMillis?.() || 0))
    .slice(0, 200)
    .map((item: any) => ({ ...item, timestamp: toIso(item.timestamp) }));
  return NextResponse.json({ logs });
}
