import { NextRequest, NextResponse } from 'next/server';
import { adminDb } from '@/lib/firebaseAdmin';
import { verifyAuthToken } from '@/lib/auth';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';

const ActivateSchema = z.object({
  deviceId: z.string().min(1).max(50),
  licenseType: z.enum(['1_year', 'lifetime', 'trial']),
  customerName: z.string().max(100).optional(),
  customerContact: z.string().max(100).optional(),
  username: z.string().max(100).optional(),
  password: z.string().max(100).optional(),
  selectedDomain: z.string().max(200).optional()
}).strict();

export async function POST(req: NextRequest) {
  try {
    const auth = await verifyAuthToken(req);
    if (auth.status !== 'authenticated') {
      const statusCode = (auth.status === 'unauthenticated' || auth.status === 'invalid') ? 401 : (auth.status === 'error' ? 500 : 403);
      return NextResponse.json({ error: auth.error || 'Unauthorized' }, { status: statusCode });
    }
    
    const authContext = auth.context;
    if (authContext.role !== 'reseller') {
      return NextResponse.json({ error: 'Unauthorized' }, { status: 403 });
    }

    const resellerUid = authContext.uid;
    const body = await req.json();
    const parsed = ActivateSchema.safeParse(body);
    if (!parsed.success) {
      return NextResponse.json({ error: 'Invalid payload format or extra fields present.' }, { status: 400 });
    }

    const { 
      deviceId, licenseType, customerName, customerContact, 
      username, password, selectedDomain 
    } = parsed.data;
    
    let creditsToDeduct = 0;
    if (licenseType === '1_year') creditsToDeduct = 1;
    if (licenseType === 'lifetime') creditsToDeduct = 2;

    const result = await adminDb.runTransaction(async (transaction: any) => {
      const resellerRef = adminDb.collection('users').doc(resellerUid);
      const resellerSnap = await transaction.get(resellerRef);

      if (!resellerSnap.exists) {
        return { error: 'Reseller not found', status: 404 };
      }

      const resellerData = resellerSnap.data();
      const currentCredits = resellerData?.credits || 0;

      if (currentCredits < creditsToDeduct) {
        return { error: 'Not enough credits', status: 400 };
      }

      const safeDeviceId = deviceId.trim();
      const licenseRef = adminDb.collection('licenses').doc(safeDeviceId);
      const licenseSnap = await transaction.get(licenseRef);

      // Ownership check: If license exists and is not Trial, make sure it's owned by this reseller
      if (licenseSnap.exists) {
        const licenseData = licenseSnap.data();
        if (licenseData?.status === 'Active' || licenseData?.status === 'Expired') {
          if (licenseData.resellerId !== resellerUid) {
            return { error: 'This device is already licensed by another reseller.', status: 403 };
          }
          
          // Idempotency check: if it was activated very recently (e.g. within last 5 minutes)
          // we treat this as a duplicate request and don't charge again or extend.
          if (licenseData.status === 'Active' && licenseData.updatedAt) {
            const updatedMs = licenseData.updatedAt.toMillis ? licenseData.updatedAt.toMillis() : Date.now();
            if (Date.now() - updatedMs < 5 * 60 * 1000) {
              return { success: true, message: 'Already activated recently.' };
            }
          }
        }
      }
      
      const licenseData: any = {
        deviceId: safeDeviceId,
        resellerId: resellerUid,
        customerName: customerName ? customerName.trim() : '',
        customerContact: customerContact ? customerContact.trim() : '',
        xtreamConfig: {
          url: selectedDomain || '',
          username: username ? username.trim() : '',
          password: password ? password.trim() : ''
        },
        selectedDomain: selectedDomain || '',
        updatedAt: FieldValue.serverTimestamp()
      };

      if (licenseType === '1_year' || licenseType === 'lifetime') {
        licenseData.status = 'Active';
        licenseData.isLifetime = licenseType === 'lifetime';
        if (licenseType === '1_year') {
          const expirationDate = new Date();
          expirationDate.setFullYear(expirationDate.getFullYear() + 1);
          licenseData.expiresAt = expirationDate;
        } else {
          licenseData.expiresAt = null;
        }
      } else {
        licenseData.status = 'Trial';
      }

      if (creditsToDeduct > 0) {
        transaction.update(resellerRef, { credits: currentCredits - creditsToDeduct });
      }

      transaction.set(licenseRef, licenseData, { merge: true });

      const transactionRef = adminDb.collection('transactions').doc();
      transaction.set(transactionRef, {
        resellerId: resellerUid,
        deviceId: safeDeviceId,
        type: creditsToDeduct > 0 ? 'activation' : 'trial_setup',
        creditsDeducted: creditsToDeduct,
        licenseType: licenseType || 'trial',
        customerName: customerName ? customerName.trim() : '',
        customerContact: customerContact ? customerContact.trim() : '',
        timestamp: FieldValue.serverTimestamp()
      });
      
      const logRef = adminDb.collection('activity_logs').doc();
      transaction.set(logRef, {
        userId: resellerUid,
        userEmail: authContext.email || '',
        role: 'reseller',
        action: 'CREATE_LICENSE',
        details: `Created ${licenseType} license for device ${safeDeviceId} (${customerName ? customerName.trim() : ''})`,
        timestamp: FieldValue.serverTimestamp()
      });

      return { success: true };
    });

    if (result.error) {
      return NextResponse.json({ error: result.error }, { status: result.status });
    }

    return NextResponse.json({ success: true }, { status: 200 });
  } catch (error: any) {
    console.error('API /reseller/activate error:', error);
    return NextResponse.json({ error: error.message || 'Internal Server Error' }, { status: 500 });
  }
}
