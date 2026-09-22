import { NextRequest, NextResponse } from 'next/server';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';
import { verifyAuthToken } from '@/lib/auth';
import { adminDb } from '@/lib/firebaseAdmin';

const BulkExtendSchema = z.object({
  requestId: z.string().uuid(),
  licenseIds: z.array(z.string().trim().min(1).max(50)).min(1).max(100)
    .refine(ids => new Set(ids).size === ids.length, 'Duplicate license IDs are not allowed.')
}).strict();

function expiryDate(value: any): Date | null {
  if (value?.toDate) return value.toDate();
  if (value instanceof Date) return value;
  return null;
}

export async function POST(req: NextRequest) {
  try {
    const auth = await verifyAuthToken(req);
    if (auth.status !== 'authenticated') {
      const status = auth.status === 'error' ? 500 : ['invalid', 'unauthenticated'].includes(auth.status) ? 401 : 403;
      return NextResponse.json({ error: auth.error || 'Unauthorized' }, { status });
    }
    if (!['reseller', 'subseller'].includes(auth.context.role)) {
      return NextResponse.json({ error: 'Unauthorized' }, { status: 403 });
    }

    const parsed = BulkExtendSchema.safeParse(await req.json());
    if (!parsed.success) {
      return NextResponse.json({ error: 'Odaberite između 1 i 100 jedinstvenih licenci.' }, { status: 400 });
    }

    const { requestId, licenseIds } = parsed.data;
    const resellerUid = auth.context.uid;
    const now = new Date();

    const result = await adminDb.runTransaction(async (tx: any) => {
      const resellerRef = adminDb.collection('users').doc(resellerUid);
      const operationRef = adminDb.collection('transactions').doc(`bulk-${resellerUid}-${requestId}`);
      const licenseRefs = licenseIds.map(id => adminDb.collection('licenses').doc(id));

      const resellerSnap = await tx.get(resellerRef);
      const operationSnap = await tx.get(operationRef);
      const licenseSnaps = [];
      for (const ref of licenseRefs) licenseSnaps.push(await tx.get(ref));

      if (!resellerSnap.exists) return { error: 'Reseller nije pronađen.', status: 404 };
      if (operationSnap.exists) {
        const previousOperation = operationSnap.data() || {};
        const previousIds = previousOperation.licenseIds || [];
        if (previousIds.length !== licenseIds.length || previousIds.some((id: string, index: number) => id !== licenseIds[index])) {
          return { error: 'Request ID je već iskorišten za drugi zahtjev.', status: 409 };
        }
        return { ...previousOperation.response, idempotent: true };
      }

      const resellerData = resellerSnap.data() || {};
      const currentCredits = Number(resellerData.credits || 0);
      if (currentCredits < licenseIds.length) {
        return { error: 'Nemate dovoljno kredita.', status: 400 };
      }

      const extensions: Array<{ licenseId: string; expiresAt: string }> = [];
      for (let index = 0; index < licenseSnaps.length; index += 1) {
        const snap = licenseSnaps[index];
        const licenseId = licenseIds[index];
        if (!snap.exists) return { error: `Licenca ${licenseId} nije pronađena.`, status: 404 };
        const data = snap.data() || {};
        if (data.resellerId !== resellerUid) {
          return { error: `Nemate pristup licenci ${licenseId}.`, status: 403 };
        }
        if (data.isLifetime === true) {
          return { error: `Lifetime licenca ${licenseId} ne može se produžiti.`, status: 400 };
        }

        const currentExpiry = expiryDate(data.expiresAt);
        const nextExpiry = currentExpiry && currentExpiry.getTime() > now.getTime()
          ? new Date(currentExpiry)
          : new Date(now);
        nextExpiry.setFullYear(nextExpiry.getFullYear() + 1);
        extensions.push({ licenseId, expiresAt: nextExpiry.toISOString() });
      }

      const creditsRemaining = currentCredits - licenseIds.length;
      tx.update(resellerRef, { credits: creditsRemaining, updatedAt: FieldValue.serverTimestamp() });
      extensions.forEach((extension, index) => {
        tx.update(licenseRefs[index], {
          status: 'Active',
          isLifetime: false,
          expiresAt: new Date(extension.expiresAt),
          updatedAt: FieldValue.serverTimestamp()
        });
      });

      const response = { success: true, creditsRemaining, extensions };
      tx.set(operationRef, {
        resellerId: resellerUid,
        type: 'bulk_extension',
        licenseIds,
        creditsDeducted: licenseIds.length,
        response,
        timestamp: FieldValue.serverTimestamp()
      });
      tx.set(adminDb.collection('activity_logs').doc(), {
        userId: resellerUid,
        userEmail: auth.context.email || '',
        role: auth.context.role,
        action: 'BULK_EXTEND',
        details: `Extended ${licenseIds.length} licenses for one year`,
        timestamp: FieldValue.serverTimestamp()
      });

      return response;
    });

    if (result.error) return NextResponse.json({ error: result.error }, { status: result.status });
    return NextResponse.json(result, { status: 200 });
  } catch (error) {
    console.error('API /reseller/bulk-extend error:', error);
    return NextResponse.json({ error: 'Masovno produženje nije uspjelo.' }, { status: 500 });
  }
}
