import { NextRequest, NextResponse } from 'next/server';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';
import { adminDb } from '@/lib/firebaseAdmin';
import { DEVICE_TOKEN_PATTERN, hashDeviceToken } from '@/lib/deviceLicense';
import { checkRateLimit } from '@/lib/rateLimit';
import { isOfficialVopoUserAgent, requestIp, writeSecurityLog } from '@/lib/securityLog';

const RegisterSchema = z.object({
  deviceId: z.string().trim().min(1).max(50),
  deviceToken: z.string().regex(DEVICE_TOKEN_PATTERN)
}).strict();

export async function POST(req: NextRequest) {
  try {
    const parsed = RegisterSchema.safeParse(await req.json());
    if (!parsed.success) return NextResponse.json({ error: 'Invalid device registration.' }, { status: 400 });

    const { deviceId, deviceToken } = parsed.data;
    const ip = requestIp(req.headers);
    const userAgent = req.headers.get('user-agent');
    if (!isOfficialVopoUserAgent(userAgent)) {
      await writeSecurityLog({ eventType: 'INVALID_USER_AGENT', deviceId, ip, userAgent, details: 'Odbijena registracija iz neslužbenog klijenta.' });
      return NextResponse.json({ error: 'Official VOPO app required.' }, { status: 403 });
    }
    const limit = await checkRateLimit(`device_register_${ip}`, 20, 60_000);
    if (!limit.success) {
      await writeSecurityLog({ eventType: 'RATE_LIMIT_EXCEEDED', deviceId, ip, userAgent, details: 'Previše registracijskih zahtjeva.' });
      return NextResponse.json({ error: 'Too many requests' }, { status: 429, headers: limit.headers });
    }

    const ref = adminDb.collection('licenses').doc(deviceId);
    const accessTokenHash = hashDeviceToken(deviceToken);
    const trialExpiresAt = new Date(Date.now() + 3 * 24 * 60 * 60 * 1000);
    const result = await adminDb.runTransaction(async (transaction: any) => {
      const snapshot = await transaction.get(ref);
      const existing = snapshot.exists ? snapshot.data() : null;
      if (existing?.accessTokenHash && existing.accessTokenHash !== accessTokenHash) {
        return { conflict: true };
      }
      const shouldStartTrial = !existing?.status || String(existing.status).toLowerCase() === 'unregistered';
      const registration: Record<string, unknown> = {
        deviceId,
        accessTokenHash,
        createdAt: existing?.createdAt || FieldValue.serverTimestamp(),
        updatedAt: FieldValue.serverTimestamp()
      };
      if (shouldStartTrial) {
        registration.status = 'Trial';
        registration.trialStartedAt = FieldValue.serverTimestamp();
        registration.expiresAt = trialExpiresAt;
        registration.isLifetime = false;
      }
      transaction.set(ref, registration, { merge: true });
      return { conflict: false, status: shouldStartTrial ? 'trial' : String(existing.status).toLowerCase() };
    });

    if (result.conflict) {
      const owner = await ref.get();
      await writeSecurityLog({ eventType: 'DEVICE_BINDING_MISMATCH', deviceId, resellerId: owner.data()?.resellerId, ip, userAgent, details: 'Druga instalacija pokušala je preuzeti postojeći Device ID.' });
      return NextResponse.json({ error: 'Device is already registered.' }, { status: 409 });
    }
    return NextResponse.json({ success: true, status: result.status });
  } catch (error: any) {
    if (error?.message === '503') return NextResponse.json({ error: 'Service Unavailable' }, { status: 503, headers: { 'Retry-After': '30' } });
    console.error('API /device/register error:', error);
    return NextResponse.json({ error: 'Internal Server Error' }, { status: 500 });
  }
}
