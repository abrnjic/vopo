import crypto from 'crypto';

export const DEVICE_TOKEN_PATTERN = /^[A-Za-z0-9_-]{43}$/;

export function hashDeviceToken(token: string): string {
  return crypto.createHash('sha256').update(token, 'utf8').digest('hex');
}

export function deviceTokenMatches(token: string, expectedHash: unknown): boolean {
  if (typeof expectedHash !== 'string' || !/^[a-f0-9]{64}$/i.test(expectedHash)) return false;
  const actual = Buffer.from(hashDeviceToken(token), 'hex');
  const expected = Buffer.from(expectedHash, 'hex');
  return actual.length === expected.length && crypto.timingSafeEqual(actual, expected);
}

function toMillis(value: unknown): number | null {
  if (value instanceof Date) return value.getTime();
  if (typeof value === 'number' && Number.isFinite(value)) return value;
  if (value && typeof (value as { toMillis?: unknown }).toMillis === 'function') {
    return (value as { toMillis: () => number }).toMillis();
  }
  return null;
}

export function publicLicenseState(data: Record<string, unknown>, now = Date.now()) {
  const rawStatus = typeof data.status === 'string' ? data.status.toLowerCase() : 'unregistered';
  const isLifetime = data.isLifetime === true;
  const expiresAt = toMillis(data.expiresAt);
  const config = data.xtreamConfig && typeof data.xtreamConfig === 'object'
    ? data.xtreamConfig as Record<string, unknown>
    : null;
  const validConfig = config
    && typeof config.url === 'string'
    && typeof config.username === 'string'
    && typeof config.password === 'string'
    ? { url: config.url, username: config.username, password: config.password }
    : null;

  if ((rawStatus === 'active' || rawStatus === 'lifetime') && isLifetime) {
    return { status: 'active', isLifetime: true, expiresAt: null, config: validConfig };
  }
  if (rawStatus === 'active' || rawStatus === 'lifetime') {
    if (expiresAt !== null && expiresAt > now) {
      return { status: 'active', isLifetime: false, expiresAt, config: validConfig };
    }
    return { status: 'expired', isLifetime: false, expiresAt, config: null };
  }
  if (rawStatus === 'trial') {
    if (expiresAt !== null && expiresAt > now) {
      const daysRemaining = Math.max(1, Math.ceil((expiresAt - now) / 86_400_000));
      return { status: 'trial', isLifetime: false, expiresAt, daysRemaining, config: validConfig };
    }
    return { status: 'expired', isLifetime: false, expiresAt, config: null };
  }
  if (rawStatus === 'expired') {
    return { status: 'expired', isLifetime: false, expiresAt, config: null };
  }
  return { status: 'unregistered', isLifetime: false, expiresAt: null, config: null };
}
