import { NextResponse, NextRequest } from 'next/server';
import { adminDb } from '@/lib/firebaseAdmin';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';
import { checkRateLimit } from '@/lib/rateLimit';
import crypto from 'crypto';

const ConnectSchema = z.object({
  deviceId: z.string().min(1).max(50),
  portalUrl: z.string().optional(),
  username: z.string().optional(),
  password: z.string().optional()
}).strict();

export async function POST(req: NextRequest) {
  try {
    const body = await req.json();
    const parsed = ConnectSchema.safeParse(body);
    if (!parsed.success) {
      return NextResponse.json({ error: 'Invalid payload format or extra fields present.' }, { status: 400 });
    }

    const { deviceId, portalUrl, username, password } = parsed.data;
    const safeDeviceId = deviceId.trim();

    // 1. IP Rate Limiting
    const ip = req.headers.get('x-real-ip') || req.headers.get('x-vercel-forwarded-for') || req.headers.get('x-forwarded-for') || 'unknown';

    // 2. Hash device ID for rate limiting
    const hashedDevice = crypto.createHash('sha256').update(safeDeviceId.toLowerCase()).digest('hex');

    try {
      const ipLimit = await checkRateLimit(`ip_${ip}`, 10, 60000); // 10 req per min per IP
      if (!ipLimit.success) {
        return NextResponse.json({ error: 'Too many requests from this IP' }, { status: 429, headers: ipLimit.headers });
      }

      const deviceLimit = await checkRateLimit(`device_${hashedDevice}`, 5, 60000); // 5 req per min per device
      if (!deviceLimit.success) {
        return NextResponse.json({ error: 'Too many requests for this device' }, { status: 429, headers: deviceLimit.headers });
      }
    } catch (limitError: any) {
      if (limitError.message === '503') {
        return NextResponse.json({ error: 'Service Unavailable' }, { status: 503, headers: { 'Retry-After': '30' } });
      }
      throw limitError;
    }

    const licenseRef = adminDb.collection('licenses').doc(safeDeviceId);

    // We use a transaction to ensure idempotency and prevent overwrites
    const result = await adminDb.runTransaction(async (transaction: any) => {
      const licenseSnap = await transaction.get(licenseRef);

      if (licenseSnap.exists) {
        const data = licenseSnap.data();
        if (data?.status === 'Active' || data?.status === 'Expired') {
          return { error: 'Ovaj uređaj već ima aktivnu ili isteklu licencu.', status: 409 };
        }
        if (data?.status === 'Trial') {
          return { success: true, message: 'Trial already exists' };
        }
      }

      const expirationDate = new Date();
      expirationDate.setDate(expirationDate.getDate() + 3);

      transaction.set(licenseRef, {
        deviceId: safeDeviceId,
        resellerId: 'self_registered',
        status: 'Trial',
        trialStartedAt: FieldValue.serverTimestamp(),
        expiresAt: expirationDate,
        isLifetime: false,
        xtreamConfig: {
          url: portalUrl ? portalUrl.trim() : '',
          username: username ? username.trim() : '',
          password: password ? password.trim() : '',
        },
        selectedDomain: portalUrl ? portalUrl.trim() : '',
        updatedAt: FieldValue.serverTimestamp()
      });

      return { success: true };
    });

    if (result.error) {
      return NextResponse.json({ error: result.error }, { status: result.status });
    }

    return NextResponse.json({ success: true, message: result.message }, { status: 201 });
  } catch (error) {
    console.error('API /connect error:', error);
    return NextResponse.json({ error: 'Internal Server Error' }, { status: 500 });
  }
}
