import { NextRequest, NextResponse } from 'next/server';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';
import { verifyAuthToken } from '@/lib/auth';
import { adminDb } from '@/lib/firebaseAdmin';

const TransferSchema = z.object({
  requestId: z.string().uuid(),
  oldDeviceId: z.string().trim().min(1).max(50),
  newDeviceId: z.string().trim().min(1).max(50),
  mode: z.enum(['license_only', 'license_and_line']),
}).strict().refine(value => value.oldDeviceId !== value.newDeviceId, {
  message: 'Stari i novi uređaj moraju biti različiti.',
});

const LINE_FIELDS = ['xtreamConfig', 'selectedDomain', 'customerName', 'customerContact'] as const;

function toMillis(value: any): number | null {
  if (value instanceof Date) return value.getTime();
  if (typeof value === 'number' && Number.isFinite(value)) return value;
  if (value?.toMillis) return value.toMillis();
  if (value?.toDate) return value.toDate().getTime();
  return null;
}

function responseLicense(deviceId: string, data: any) {
  const expiresAt = toMillis(data.expiresAt);
  return {
    id: deviceId,
    deviceId,
    resellerId: data.resellerId || '',
    status: data.status || 'Active',
    isLifetime: data.isLifetime === true,
    expiresAt: expiresAt ? new Date(expiresAt).toISOString() : null,
    selectedDomain: data.selectedDomain || '',
    customerName: data.customerName || '',
    customerContact: data.customerContact || '',
  };
}

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

    const parsed = TransferSchema.safeParse(await req.json());
    if (!parsed.success) {
      return NextResponse.json({ error: parsed.error.issues[0]?.message || 'Provjerite Device ID podatke i vrstu prijenosa.' }, { status: 400 });
    }
    const { requestId, oldDeviceId, newDeviceId, mode } = parsed.data;
    const actor = auth.context;

    const result = await adminDb.runTransaction(async (tx: any) => {
      const sourceRef = adminDb.collection('licenses').doc(oldDeviceId);
      const destinationRef = adminDb.collection('licenses').doc(newDeviceId);
      const operationRef = adminDb.collection('transactions').doc(`license-transfer-${actor.uid}-${requestId}`);
      const [operationSnap, sourceSnap, destinationSnap] = await Promise.all([
        tx.get(operationRef), tx.get(sourceRef), tx.get(destinationRef),
      ]);

      if (operationSnap.exists) {
        const previous = operationSnap.data() || {};
        if (previous.oldDeviceId !== oldDeviceId || previous.newDeviceId !== newDeviceId || previous.mode !== mode) {
          return { error: 'Request ID je već iskorišten za drugi prijenos.', status: 409 };
        }
        return { ...previous.response, idempotent: true };
      }
      if (!sourceSnap.exists) return { error: 'Licenca starog uređaja nije pronađena.', status: 404 };

      const source = sourceSnap.data() || {};
      const sourceStatus = String(source.status || '').toLowerCase();
      if (sourceStatus === 'transferred') return { error: 'Licenca je već prenesena na drugi uređaj.', status: 409 };
      if (!['active', 'lifetime', 'trial'].includes(sourceStatus)) {
        return { error: 'Prenijeti se može samo aktivna, probna ili trajna licenca.', status: 409 };
      }
      if (actor.role !== 'admin' && source.resellerId !== actor.uid) {
        return { error: 'Možete prenijeti samo licence iz vlastitog panela.', status: 403 };
      }

      const expiryMs = toMillis(source.expiresAt);
      const isLifetime = source.isLifetime === true;
      if (!isLifetime && (!expiryMs || expiryMs <= Date.now())) {
        return { error: 'Istekla licenca ne može se prenijeti.', status: 409 };
      }

      const destination = destinationSnap.exists ? destinationSnap.data() || {} : {};
      const destinationStatus = String(destination.status || '').toLowerCase();
      if (destinationSnap.exists && ['active', 'lifetime', 'transferred'].includes(destinationStatus)) {
        return { error: 'Novi Device ID već ima aktivnu ili ranije prenesenu licencu.', status: 409 };
      }
      if (
        actor.role !== 'admin'
        && destination.resellerId
        && destination.resellerId !== 'self_registered'
        && destination.resellerId !== actor.uid
      ) {
        return { error: 'Novi uređaj pripada drugom reselleru.', status: 403 };
      }

      const migrated: Record<string, any> = {
        ...source,
        deviceId: newDeviceId,
        status: source.status,
        isLifetime,
        expiresAt: isLifetime ? null : source.expiresAt,
        resellerId: source.resellerId,
        migratedFrom: oldDeviceId,
        migratedAt: FieldValue.serverTimestamp(),
        migrationMode: mode,
        createdAt: destination.createdAt || FieldValue.serverTimestamp(),
        updatedAt: FieldValue.serverTimestamp(),
      };
      delete migrated.accessTokenHash;
      delete migrated.transferredTo;
      delete migrated.transferredAt;
      delete migrated.transferredBy;
      delete migrated.transferredOriginalStatus;

      if (typeof destination.accessTokenHash === 'string') migrated.accessTokenHash = destination.accessTokenHash;
      if (mode === 'license_only') {
        for (const field of LINE_FIELDS) {
          if (Object.prototype.hasOwnProperty.call(destination, field)) migrated[field] = destination[field];
          else delete migrated[field];
        }
      }

      const now = FieldValue.serverTimestamp();
      tx.set(destinationRef, migrated);
      tx.update(sourceRef, {
        status: 'Transferred',
        isLifetime: false,
        transferredTo: newDeviceId,
        transferredAt: now,
        transferredBy: actor.uid,
        transferredOriginalStatus: source.status || '',
        updatedAt: now,
      });

      const response = {
        success: true,
        oldDeviceId,
        newDeviceId,
        mode,
        license: responseLicense(newDeviceId, migrated),
      };
      tx.set(operationRef, {
        type: 'license_transfer',
        actorId: actor.uid,
        actorRole: actor.role,
        resellerId: source.resellerId || '',
        oldDeviceId,
        newDeviceId,
        mode,
        isLifetime,
        expiresAt: isLifetime ? null : source.expiresAt,
        response,
        timestamp: now,
      });
      tx.set(adminDb.collection('activity_logs').doc(), {
        userId: actor.uid,
        userEmail: actor.email || '',
        role: actor.role,
        action: 'TRANSFER_LICENSE',
        details: `Prijenos ${oldDeviceId} → ${newDeviceId}; opcija: ${mode === 'license_and_line' ? 'licenca i linija' : 'samo licenca'}`,
        oldDeviceId,
        newDeviceId,
        transferMode: mode,
        targetResellerId: source.resellerId || '',
        timestamp: now,
      });
      return response;
    });

    if (result.error) return NextResponse.json({ error: result.error }, { status: result.status });
    return NextResponse.json(result);
  } catch (error) {
    console.error('POST /api/licenses/transfer failed:', error);
    return NextResponse.json({ error: 'Prijenos licence nije uspio.' }, { status: 500 });
  }
}
