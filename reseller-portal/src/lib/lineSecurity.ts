import crypto from 'crypto';

export const LINE_PIN_PATTERN = /^[A-Za-z0-9!@#$%&*_.-]{6,32}$/;

export function hashLinePin(pin: string): string {
  const salt = crypto.randomBytes(16);
  const hash = crypto.scryptSync(pin, salt, 32);
  return `scrypt$${salt.toString('hex')}$${hash.toString('hex')}`;
}

export function linePinMatches(pin: string, stored: unknown): boolean {
  if (typeof stored !== 'string') return false;
  const [algorithm, saltHex, expectedHex] = stored.split('$');
  if (algorithm !== 'scrypt' || !/^[a-f0-9]{32}$/i.test(saltHex || '') || !/^[a-f0-9]{64}$/i.test(expectedHex || '')) return false;
  const actual = crypto.scryptSync(pin, Buffer.from(saltHex, 'hex'), 32);
  const expected = Buffer.from(expectedHex, 'hex');
  return actual.length === expected.length && crypto.timingSafeEqual(actual, expected);
}
