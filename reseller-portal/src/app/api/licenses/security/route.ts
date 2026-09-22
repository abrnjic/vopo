import { NextRequest, NextResponse } from 'next/server';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';
import { verifyAuthToken } from '@/lib/auth';
import { adminDb } from '@/lib/firebaseAdmin';
import { hashLinePin, LINE_PIN_PATTERN, linePinMatches } from '@/lib/lineSecurity';
import { requestIp, writeSecurityLog } from '@/lib/securityLog';

const SecuritySchema = z.object({
  deviceId: z.string().trim().min(1).max(50),
  currentPin: z.string().max(32).optional(),
  newPin: z.string().regex(LINE_PIN_PATTERN).optional(),
  resetDeviceBinding: z.boolean().optional()
}).strict().refine(value => Boolean(value.newPin || value.resetDeviceBinding), 'No change requested');

export async function PATCH(req: NextRequest) {
  const auth = await verifyAuthToken(req);
  if (auth.status !== 'authenticated') {
    return NextResponse.json({ error: auth.error || 'Unauthorized' }, { status: auth.status === 'error' ? 500 : 401 });
  }
  if (!['admin', 'reseller', 'subseller'].includes(auth.context.role)) {
    return NextResponse.json({ error: 'Forbidden' }, { status: 403 });
  }
  const parsed = SecuritySchema.safeParse(await req.json());
  if (!parsed.success) return NextResponse.json({ error: 'PIN mora imati 6-32 dopuštena znaka.' }, { status: 400 });

  const { deviceId, currentPin, newPin, resetDeviceBinding } = parsed.data;
  const ref = adminDb.collection('licenses').doc(deviceId);
  const snapshot = await ref.get();
  if (!snapshot.exists) return NextResponse.json({ error: 'Linija ne postoji.' }, { status: 404 });
  const license = snapshot.data() || {};
  if (auth.context.role !== 'admin' && license.resellerId !== auth.context.uid) {
    return NextResponse.json({ error: 'Nemate pravo upravljati ovom linijom.' }, { status: 403 });
  }
  const hasExistingPin = typeof license.linePinHash === 'string' && license.linePinHash.length > 0;
  if (auth.context.role !== 'admin' && hasExistingPin && !linePinMatches(currentPin || '', license.linePinHash)) {
    await writeSecurityLog({ eventType: 'INVALID_LINE_PIN', deviceId, resellerId: license.resellerId, actorId: auth.context.uid, actorRole: auth.context.role, ip: requestIp(req.headers), details: 'Neuspjela potvrda postojećeg PIN-a.' });
    return NextResponse.json({ error: 'Postojeći PIN nije ispravan.' }, { status: 403 });
  }
  if (auth.context.role !== 'admin' && !hasExistingPin && !newPin) {
    return NextResponse.json({ error: 'Prvo postavite PIN za postojeću liniju.' }, { status: 400 });
  }

  const update: Record<string, unknown> = { updatedAt: FieldValue.serverTimestamp() };
  if (newPin) update.linePinHash = hashLinePin(newPin);
  if (resetDeviceBinding) {
    update.accessTokenHash = null;
    update.deviceBindingResetAt = FieldValue.serverTimestamp();
  }
  await ref.update(update);
  if (newPin) await writeSecurityLog({ eventType: 'LINE_PIN_CHANGED', deviceId, resellerId: license.resellerId, actorId: auth.context.uid, actorRole: auth.context.role, ip: requestIp(req.headers), details: 'PIN linije je promijenjen.' });
  if (resetDeviceBinding) await writeSecurityLog({ eventType: 'DEVICE_BINDING_RESET', deviceId, resellerId: license.resellerId, actorId: auth.context.uid, actorRole: auth.context.role, ip: requestIp(req.headers), details: 'Vezanje instalacije je resetirano kroz portal.' });
  return NextResponse.json({ success: true });
}
