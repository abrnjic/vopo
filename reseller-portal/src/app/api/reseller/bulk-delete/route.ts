import { NextRequest, NextResponse } from 'next/server';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';
import { verifyAuthToken } from '@/lib/auth';
import { adminDb } from '@/lib/firebaseAdmin';

const BulkDeleteSchema = z.object({
  requestId: z.string().uuid(),
  licenseIds: z.array(z.string().trim().min(1).max(50)).min(1).max(100)
    .refine(ids => new Set(ids).size === ids.length, 'Duplicate license IDs are not allowed.')
}).strict();

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

    const parsed = BulkDeleteSchema.safeParse(await req.json());
    if (!parsed.success) {
      return NextResponse.json({ error: 'Odaberite između 1 i 100 jedinstvenih licenci.' }, { status: 400 });
    }

    const { requestId } = parsed.data;
    const licenseIds = [...parsed.data.licenseIds].sort();
    const resellerUid = auth.context.uid;

    const result = await adminDb.runTransaction(async (tx: any) => {
      const operationRef = adminDb.collection('transactions').doc(`bulk-delete-${resellerUid}-${requestId}`);
      const licenseRefs = licenseIds.map(id => adminDb.collection('licenses').doc(id));

      const operationSnap = await tx.get(operationRef);
      const licenseSnaps = [];
      for (const ref of licenseRefs) licenseSnaps.push(await tx.get(ref));

      if (operationSnap.exists) {
        const previousOperation = operationSnap.data() || {};
        const previousIds = previousOperation.licenseIds || [];
        if (previousIds.length !== licenseIds.length || previousIds.some((id: string, index: number) => id !== licenseIds[index])) {
          return { error: 'Request ID je već iskorišten za drugi zahtjev.', status: 409 };
        }
        return { ...previousOperation.response, idempotent: true };
      }

      for (let index = 0; index < licenseSnaps.length; index += 1) {
        const snap = licenseSnaps[index];
        const licenseId = licenseIds[index];
        if (!snap.exists) return { error: `Licenca ${licenseId} nije pronađena.`, status: 404 };
        if (snap.data()?.resellerId !== resellerUid) {
          return { error: `Nemate pristup licenci ${licenseId}.`, status: 403 };
        }
      }

      licenseRefs.forEach(ref => tx.delete(ref));

      const response = { success: true, deletedIds: licenseIds };
      tx.set(operationRef, {
        resellerId: resellerUid,
        type: 'bulk_delete',
        licenseIds,
        response,
        timestamp: FieldValue.serverTimestamp()
      });
      tx.set(adminDb.collection('activity_logs').doc(), {
        userId: resellerUid,
        userEmail: auth.context.email || '',
        role: auth.context.role,
        action: 'BULK_DELETE',
        details: `Deleted ${licenseIds.length} lines permanently`,
        timestamp: FieldValue.serverTimestamp()
      });

      return response;
    });

    if (result.error) return NextResponse.json({ error: result.error }, { status: result.status });
    return NextResponse.json(result, { status: 200 });
  } catch (error) {
    console.error('API /reseller/bulk-delete error:', error);
    return NextResponse.json({ error: 'Masovno brisanje nije uspjelo.' }, { status: 500 });
  }
}
