import { NextRequest, NextResponse } from 'next/server';
import { adminDb } from '@/lib/firebaseAdmin';
import { verifyAuthToken } from '@/lib/auth';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';

const UpdateCreditsSchema = z.object({
  targetUserId: z.string().min(1).max(100),
  newCredits: z.number().min(0),
  actionType: z.string().max(100).optional(),
  logMessage: z.string().max(500).optional()
}).strict();

export async function POST(req: NextRequest) {
  try {
    const auth = await verifyAuthToken(req);
    if (auth.status !== 'authenticated') {
      const statusCode = (auth.status === 'unauthenticated' || auth.status === 'invalid') ? 401 : (auth.status === 'error' ? 500 : 403);
      return NextResponse.json({ error: auth.error || 'Unauthorized' }, { status: statusCode });
    }
    
    const authContext = auth.context;
    if (authContext.role !== 'admin') {
      return NextResponse.json({ error: 'Unauthorized' }, { status: 403 });
    }

    const adminUid = authContext.uid;
    const adminEmail = authContext.email || 'admin';
    const body = await req.json();
    const parsed = UpdateCreditsSchema.safeParse(body);
    if (!parsed.success) {
      return NextResponse.json({ error: 'Invalid payload format or extra fields present.' }, { status: 400 });
    }

    const { targetUserId, newCredits, actionType, logMessage } = parsed.data;

    // Run atomic transaction
    const result = await adminDb.runTransaction(async (transaction: any) => {
      const targetUserRef = adminDb.collection('users').doc(targetUserId);
      const targetUserSnap = await transaction.get(targetUserRef);

      if (!targetUserSnap.exists) {
        return { error: 'User not found', status: 404 };
      }

      transaction.update(targetUserRef, { credits: newCredits });

      const logRef = adminDb.collection('activity_logs').doc();
      transaction.set(logRef, {
        userId: adminUid,
        userEmail: adminEmail,
        role: 'admin',
        action: actionType || 'UPDATE_CREDITS',
        details: logMessage || `Admin updated credits to ${newCredits}`,
        timestamp: FieldValue.serverTimestamp(),
        targetUserId: targetUserId
      });

      return { success: true };
    });

    if (result.error) {
      return NextResponse.json({ error: result.error }, { status: result.status });
    }

    return NextResponse.json({ success: true }, { status: 200 });
  } catch (error: any) {
    console.error('API /admin/credits error:', error);
    return NextResponse.json({ error: error.message || 'Internal Server Error' }, { status: 500 });
  }
}
