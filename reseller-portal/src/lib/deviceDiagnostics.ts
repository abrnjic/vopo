import { isIP } from 'node:net';
import { NextRequest } from 'next/server';
import { adminDb } from './firebaseAdmin';
import { DEVICE_TOKEN_PATTERN, deviceTokenMatches } from './deviceLicense';

export function clientIp(req: NextRequest): string {
  const raw = req.headers.get('x-real-ip')
    || req.headers.get('x-vercel-forwarded-for')
    || req.headers.get('x-forwarded-for')
    || '';
  const candidate = raw.split(',')[0].trim().replace(/^\[|\]$/g, '');
  return isIP(candidate) ? candidate : 'unknown';
}

export async function authenticateDevice(req: NextRequest, deviceId: string) {
  const authorization = req.headers.get('authorization') || '';
  const token = authorization.startsWith('Device ') ? authorization.slice(7) : '';
  if (!DEVICE_TOKEN_PATTERN.test(token)) return null;

  const snapshot = await adminDb.collection('licenses').doc(deviceId).get();
  if (!snapshot.exists) return null;
  const license = snapshot.data() || {};
  if (!deviceTokenMatches(token, license.accessTokenHash)) return null;
  return { license };
}

export function diagnosticsForPortal(id: string, data: Record<string, any>) {
  const timestamp = (value: unknown) => {
    if (value instanceof Date) return value.toISOString();
    if (value && typeof (value as { toDate?: unknown }).toDate === 'function') {
      return (value as { toDate: () => Date }).toDate().toISOString();
    }
    if (typeof value === 'number') return new Date(value).toISOString();
    return null;
  };
  return {
    deviceId: id,
    resellerId: typeof data.resellerId === 'string' ? data.resellerId : '',
    customerName: typeof data.customerName === 'string' ? data.customerName : '',
    publicIp: typeof data.publicIp === 'string' ? data.publicIp : 'unknown',
    appVersion: typeof data.appVersion === 'string' ? data.appVersion : '',
    appVersionCode: typeof data.appVersionCode === 'number' ? data.appVersionCode : null,
    androidVersion: typeof data.androidVersion === 'string' ? data.androidVersion : '',
    deviceModel: typeof data.deviceModel === 'string' ? data.deviceModel : '',
    connectionType: typeof data.connectionType === 'string' ? data.connectionType : 'UNKNOWN',
    downloadMbps: typeof data.downloadMbps === 'number' ? data.downloadMbps : null,
    speedMeasuredAt: timestamp(data.speedMeasuredAt),
    availableStorageBytes: typeof data.availableStorageBytes === 'number' ? data.availableStorageBytes : null,
    availableMemoryBytes: typeof data.availableMemoryBytes === 'number' ? data.availableMemoryBytes : null,
    licenseStatus: typeof data.licenseStatus === 'string' ? data.licenseStatus : 'unknown',
    lastSeenAt: timestamp(data.lastSeenAt),
    updatedAt: timestamp(data.updatedAt),
  };
}
