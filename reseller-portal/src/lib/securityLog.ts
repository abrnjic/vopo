import { FieldValue } from 'firebase-admin/firestore';
import { adminDb } from './firebaseAdmin';

export type SecurityEventType =
  | 'INVALID_USER_AGENT'
  | 'DEVICE_BINDING_MISMATCH'
  | 'INVALID_LINE_PIN'
  | 'RATE_LIMIT_EXCEEDED'
  | 'DEVICE_BINDING_RESET'
  | 'LINE_PIN_CHANGED';

export function requestIp(headers: Headers | { get(name: string): string | null }): string {
  const raw = headers.get('x-real-ip') || headers.get('x-vercel-forwarded-for') || headers.get('x-forwarded-for') || 'unknown';
  return raw.split(',')[0].trim().slice(0, 80);
}

export function isOfficialVopoUserAgent(value: string | null): boolean {
  return /^Vopo\/[A-Za-z0-9._+-]+ \(Android; Media3; OkHttp\)$/.test(value || '');
}

export async function writeSecurityLog(input: {
  eventType: SecurityEventType;
  deviceId?: string;
  resellerId?: string;
  actorId?: string;
  actorRole?: string;
  ip?: string;
  userAgent?: string | null;
  details?: string;
}) {
  try {
    await adminDb.collection('security_logs').doc().set({
      ...input,
      deviceId: input.deviceId || '',
      resellerId: input.resellerId || '',
      actorId: input.actorId || '',
      actorRole: input.actorRole || '',
      ip: input.ip || 'unknown',
      userAgent: (input.userAgent || '').slice(0, 300),
      details: (input.details || '').slice(0, 500),
      timestamp: FieldValue.serverTimestamp()
    });
  } catch (error) {
    console.error('Security log write failed:', error);
  }
}
