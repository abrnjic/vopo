import { NextRequest, NextResponse } from 'next/server';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';
import { adminDb } from '@/lib/firebaseAdmin';
import { authenticateDevice, clientIp } from '@/lib/deviceDiagnostics';
import { publicLicenseState } from '@/lib/deviceLicense';
import { checkRateLimit } from '@/lib/rateLimit';

const DiagnosticsSchema = z.object({
  deviceId: z.string().trim().min(1).max(50),
  appVersion: z.string().trim().min(1).max(32),
  appVersionCode: z.number().int().nonnegative().max(1_000_000),
  androidVersion: z.string().trim().min(1).max(32),
  deviceModel: z.string().trim().min(1).max(120),
  connectionType: z.enum(['WIFI', 'ETHERNET', 'CELLULAR', 'OTHER', 'UNKNOWN']),
  availableStorageBytes: z.number().int().nonnegative().optional(),
  availableMemoryBytes: z.number().int().nonnegative().optional(),
  licenseStatus: z.enum(['active', 'trial', 'expired', 'unregistered', 'unknown']),
  downloadMbps: z.number().finite().min(0).max(100_000).optional(),
  speedMeasuredAtMs: z.number().int().positive().optional(),
}).strict().refine(
  value => (value.downloadMbps === undefined) === (value.speedMeasuredAtMs === undefined),
  { message: 'Speed value and measurement time must be provided together.' }
);

export async function POST(req: NextRequest) {
  try {
    const parsed = DiagnosticsSchema.safeParse(await req.json());
    if (!parsed.success) return NextResponse.json({ error: 'Invalid diagnostics payload.' }, { status: 400 });

    const authenticated = await authenticateDevice(req, parsed.data.deviceId);
    if (!authenticated) return NextResponse.json({ error: 'Unauthorized' }, { status: 401 });

    const ip = clientIp(req);
    const limit = await checkRateLimit(`device_diagnostics_${parsed.data.deviceId}`, 12, 15 * 60_000);
    if (!limit.success) {
      return NextResponse.json({ error: 'Too many requests' }, { status: 429, headers: limit.headers });
    }

    const { license } = authenticated;
    const data = parsed.data;
    const update: Record<string, unknown> = {
      deviceId: data.deviceId,
      resellerId: typeof license.resellerId === 'string' ? license.resellerId : '',
      customerName: typeof license.customerName === 'string' ? license.customerName : '',
      publicIp: ip,
      appVersion: data.appVersion,
      appVersionCode: data.appVersionCode,
      androidVersion: data.androidVersion,
      deviceModel: data.deviceModel,
      connectionType: data.connectionType,
      availableStorageBytes: data.availableStorageBytes ?? null,
      availableMemoryBytes: data.availableMemoryBytes ?? null,
      licenseStatus: publicLicenseState(license).status,
      lastSeenAt: FieldValue.serverTimestamp(),
      updatedAt: FieldValue.serverTimestamp(),
      schemaVersion: 1,
    };
    if (data.downloadMbps !== undefined && data.speedMeasuredAtMs !== undefined) {
      update.downloadMbps = Math.round(data.downloadMbps * 10) / 10;
      update.speedMeasuredAt = new Date(data.speedMeasuredAtMs);
    }
    await adminDb.collection('device_diagnostics').doc(data.deviceId).set(update, { merge: true });

    return NextResponse.json({ success: true, publicIp: ip }, {
      headers: { ...limit.headers, 'Cache-Control': 'no-store, private' }
    });
  } catch (error: any) {
    if (error?.message === '503') {
      return NextResponse.json({ error: 'Service Unavailable' }, { status: 503, headers: { 'Retry-After': '30' } });
    }
    console.error('API /device/diagnostics error:', error);
    return NextResponse.json({ error: 'Internal Server Error' }, { status: 500 });
  }
}
