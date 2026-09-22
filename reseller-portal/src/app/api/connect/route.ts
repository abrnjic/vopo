import { NextResponse, NextRequest } from 'next/server';
import { adminDb } from '@/lib/firebaseAdmin';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';
import { checkRateLimit } from '@/lib/rateLimit';
import { verifyAuthToken } from '@/lib/auth';
import { DomainSchema, domainsForUser } from '@/lib/domains';
import crypto from 'crypto';

const ConnectSchema = z.object({
  deviceId: z.string().min(1).max(50),
  portalUrl: DomainSchema.optional(),
  username: z.string().optional(),
  password: z.string().optional()
}).strict();

export async function POST(req: NextRequest) {
  try {
    const auth = await verifyAuthToken(req);
    if (auth.status !== 'authenticated') {
      const status = auth.status === 'error' ? 500 : ['invalid', 'unauthenticated'].includes(auth.status) ? 401 : 403;
      return NextResponse.json({ error: auth.error || 'Pristup odbijen.' }, { status });
    }
    if (!['admin', 'reseller', 'subseller'].includes(auth.context.role)) {
      return NextResponse.json({ error: 'Pristup odbijen.' }, { status: 403 });
    }

    const body = await req.json();
    const parsed = ConnectSchema.safeParse(body);
    if (!parsed.success) {
      return NextResponse.json({ error: 'Invalid payload format or extra fields present.' }, { status: 400 });
    }

    const { deviceId, portalUrl, username, password } = parsed.data;
    const safeDeviceId = deviceId.trim();
    const configValues = [portalUrl, username, password];
    const hasAnyConfig = configValues.some(value => Boolean(value?.trim()));
    const hasCompleteConfig = configValues.every(value => Boolean(value?.trim()));
    if (hasAnyConfig && !hasCompleteConfig) {
      return NextResponse.json({ error: 'Portal URL, username and password must be provided together.' }, { status: 400 });
    }
    if (
      hasCompleteConfig
      && auth.context.role !== 'admin'
      && !domainsForUser(auth.context.dbUser || {}).includes(portalUrl!.trim())
    ) {
      return NextResponse.json({ error: 'Domena nije dodijeljena vašem računu.' }, { status: 403 });
    }

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
        if (data?.status === 'Active' || data?.status === 'Expired' || data?.status === 'Transferred') {
          return { error: 'Ovaj uređaj već ima aktivnu, isteklu ili prenesenu licencu.', status: 409 };
        }
        if (data?.status === 'Trial') {
          if (
            auth.context.role !== 'admin'
            && data.resellerId
            && data.resellerId !== 'self_registered'
            && data.resellerId !== auth.context.uid
          ) {
            return { error: 'Ovaj uređaj pripada drugom korisničkom računu.', status: 403 };
          }
          const ownerId = data.resellerId && data.resellerId !== 'self_registered'
            ? data.resellerId
            : auth.context.uid;
          const trialUpdate: Record<string, unknown> = {
            resellerId: ownerId,
            updatedAt: FieldValue.serverTimestamp()
          };
          if (hasCompleteConfig) {
            trialUpdate.xtreamConfig = {
                url: portalUrl!.trim(),
                username: username!.trim(),
                password: password!.trim(),
            };
            trialUpdate.selectedDomain = portalUrl!.trim();
          }
          transaction.set(licenseRef, trialUpdate, { merge: true });
          const logRef = adminDb.collection('activity_logs').doc();
          transaction.set(logRef, {
            userId: auth.context.uid,
            userEmail: auth.context.email || '',
            role: auth.context.role,
            action: 'CONNECT_DEVICE',
            details: `Povezan postojeći probni uređaj ${safeDeviceId}`,
            deviceId: safeDeviceId,
            targetResellerId: ownerId,
            timestamp: FieldValue.serverTimestamp()
          });
          return {
            success: true,
            message: hasCompleteConfig ? 'Trial already exists; configuration updated' : 'Trial already exists'
          };
        }
      }

      const expirationDate = new Date();
      expirationDate.setDate(expirationDate.getDate() + 3);

      transaction.set(licenseRef, {
        deviceId: safeDeviceId,
        resellerId: auth.context.uid,
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
      }, { merge: true });

      const logRef = adminDb.collection('activity_logs').doc();
      transaction.set(logRef, {
        userId: auth.context.uid,
        userEmail: auth.context.email || '',
        role: auth.context.role,
        action: 'CONNECT_DEVICE',
        details: `Kreiran probni uređaj ${safeDeviceId}`,
        deviceId: safeDeviceId,
        targetResellerId: auth.context.uid,
        timestamp: FieldValue.serverTimestamp()
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
