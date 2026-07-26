import { NextRequest, NextResponse } from 'next/server';
import { adminAuth, adminDb } from '@/lib/firebaseAdmin';
import { verifyAuthToken } from '@/lib/auth';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';

const UpdateUserSchema = z.object({
  uid: z.string().min(1).max(100),
  role: z.enum(['admin', 'reseller', 'user']).optional(),
  status: z.enum(['active', 'suspended', 'deactivated']).optional()
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

    const body = await req.json();
    const parsed = UpdateUserSchema.safeParse(body);
    if (!parsed.success) {
      return NextResponse.json({ error: 'Invalid payload format or extra fields present.' }, { status: 400 });
    }

    const { uid, role, status } = parsed.data;

    // We do NOT have a distributed transaction between Firestore and Firebase Auth.
    // Compensation strategy: Update Firestore first, then Auth. If Auth fails, rollback Firestore.
    const userRef = adminDb.collection('users').doc(uid);
    const userSnap = await userRef.get();
    
    if (!userSnap.exists) {
      return NextResponse.json({ error: 'User not found' }, { status: 404 });
    }
    
    const previousData = userSnap.data();
    
    // Protect the last active administrator
    if (previousData?.role === 'admin' && previousData?.status === 'active') {
      if ((role && role !== 'admin') || (status && status !== 'active')) {
        const adminsSnapshot = await adminDb.collection('users')
          .where('role', '==', 'admin')
          .where('status', '==', 'active')
          .get();
          
        if (adminsSnapshot.size <= 1) {
          return NextResponse.json({ error: 'Cannot modify or deactivate the last active administrator.' }, { status: 400 });
        }
      }
    }

    const updates: any = {};
    if (role) updates.role = role;
    if (status) updates.status = status;
    updates.updatedAt = FieldValue.serverTimestamp();

    // 1. Update Firestore
    await userRef.update(updates);

    try {
      // 2. Update Firebase Auth
      if (role) {
        await adminAuth.setCustomUserClaims(uid, { role });
      }
      if (status) {
        const disabled = (status === 'suspended' || status === 'deactivated');
        await adminAuth.updateUser(uid, { disabled });
      }

      // Log success
      const logRef = adminDb.collection('activity_logs').doc();
      await logRef.set({
        userId: authContext.uid,
        userEmail: authContext.email || '',
        role: 'admin',
        action: 'UPDATE_USER',
        details: `Updated user ${uid}. Role: ${role || 'unchanged'}, Status: ${status || 'unchanged'}`,
        timestamp: FieldValue.serverTimestamp()
      });

    } catch (authError: any) {
      console.error('Firebase Auth update failed. Rolling back Firestore...', authError);
      // 3. Rollback Firestore if Auth fails
      const rollbackData: any = {};
      if (role) rollbackData.role = previousData?.role || 'user';
      if (status) rollbackData.status = previousData?.status || 'active';
      
      await userRef.update(rollbackData).catch((e: any) => {
        console.error('CRITICAL: Rollback failed! State might be inconsistent.', e);
      });

      return NextResponse.json({ error: 'Auth update failed. Changes rolled back.' }, { status: 500 });
    }

    return NextResponse.json({ success: true }, { status: 200 });
  } catch (error: any) {
    console.error('API /admin/users error:', error);
    return NextResponse.json({ error: error.message || 'Internal Server Error' }, { status: 500 });
  }
}
