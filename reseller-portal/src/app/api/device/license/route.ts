import { NextRequest, NextResponse } from 'next/server';
import { z } from 'zod';
import { adminDb } from '@/lib/firebaseAdmin';
import { DEVICE_TOKEN_PATTERN, deviceTokenMatches, publicLicenseState } from '@/lib/deviceLicense';

const QuerySchema = z.object({ deviceId: z.string().trim().min(1).max(50) });

export async function GET(req: NextRequest) {
  try {
    const parsed = QuerySchema.safeParse({ deviceId: req.nextUrl.searchParams.get('deviceId') || '' });
    const authorization = req.headers.get('authorization') || '';
    const deviceToken = authorization.startsWith('Device ') ? authorization.slice(7) : '';
    if (!parsed.success || !DEVICE_TOKEN_PATTERN.test(deviceToken)) {
      return NextResponse.json({ error: 'Unauthorized' }, { status: 401 });
    }

    const snapshot = await adminDb.collection('licenses').doc(parsed.data.deviceId).get();
    if (!snapshot.exists) return NextResponse.json({ error: 'Unauthorized' }, { status: 401 });
    const data = snapshot.data() || {};
    if (!deviceTokenMatches(deviceToken, data.accessTokenHash)) {
      return NextResponse.json({ error: 'Unauthorized' }, { status: 401 });
    }

    return NextResponse.json(publicLicenseState(data), {
      headers: { 'Cache-Control': 'no-store, private' }
    });
  } catch (error: any) {
    if (error?.message === '503') return NextResponse.json({ error: 'Service Unavailable' }, { status: 503, headers: { 'Retry-After': '30' } });
    console.error('API /device/license error:', error);
    return NextResponse.json({ error: 'Internal Server Error' }, { status: 500 });
  }
}
