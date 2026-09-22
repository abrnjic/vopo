import { NextRequest, NextResponse } from 'next/server';
import { adminDb } from '@/lib/firebaseAdmin';
import { verifyAuthToken } from '@/lib/auth';
import { diagnosticsForPortal } from '@/lib/deviceDiagnostics';

export async function GET(req: NextRequest) {
  try {
    const auth = await verifyAuthToken(req);
    if (auth.status !== 'authenticated') {
      const status = auth.status === 'unauthenticated' || auth.status === 'invalid' ? 401 : auth.status === 'error' ? 500 : 403;
      return NextResponse.json({ error: auth.error || 'Unauthorized' }, { status });
    }
    if (!['admin', 'reseller'].includes(auth.context.role)) {
      return NextResponse.json({ error: 'Pristup odbijen.' }, { status: 403 });
    }

    let query: any = adminDb.collection('device_diagnostics');
    if (auth.context.role === 'reseller') query = query.where('resellerId', '==', auth.context.uid);
    const snapshot = await query.get();
    const diagnostics = snapshot.docs
      .map((doc: any) => diagnosticsForPortal(doc.id, doc.data() || {}))
      .sort((a: any, b: any) => Date.parse(b.lastSeenAt || '1970-01-01') - Date.parse(a.lastSeenAt || '1970-01-01'))
      .slice(0, 500);

    return NextResponse.json({ diagnostics }, { headers: { 'Cache-Control': 'no-store, private' } });
  } catch (error) {
    console.error('API /diagnostics error:', error);
    return NextResponse.json({ error: 'Internal Server Error' }, { status: 500 });
  }
}
