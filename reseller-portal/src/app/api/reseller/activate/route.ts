import { NextRequest, NextResponse } from 'next/server';
import { adminDb } from '@/lib/firebaseAdmin';
import { verifyAuthToken } from '@/lib/auth';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';
import { DomainSchema, domainsForUser } from '@/lib/domains';

const ActivateSchema = z.object({
  deviceId: z.string().min(1).max(50),
  licenseType: z.enum(['1_year', 'lifetime', 'trial']),
  customerName: z.string().max(100).optional(),
  customerContact: z.string().max(100).optional(),
  username: z.string().max(100).optional(),
  password: z.string().max(100).optional(),
  selectedDomain: DomainSchema.optional()
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
      const availableDomains = domainsForUser(resellerData || {});
      if (selectedDomain && !availableDomains.includes(selectedDomain)) {
        return { error: 'Domena nije dodijeljena ovom reselleru.', status: 403 };
      }
      if (!selectedDomain && (availableDomains.length > 0 || username || password)) {
        return { error: 'Odaberite domenu servera.', status: 400 };
      }

      if (currentCredits < creditsToDeduct) {
        return { error: 'Not enough credits', status: 400 };
      }

      const safeDeviceId = deviceId.trim();
      const licenseRef = adminDb.collection('licenses').doc(safeDeviceId);
      const licenseSnap = await transaction.get(licenseRef);
      const existingLicenseData = licenseSnap.exists ? licenseSnap.data() : null;

      if (licenseSnap.exists) {
        const licenseData = existingLicenseData;
        if (licenseData?.status === 'Active' || licenseData?.status === 'Expired') {
          if (licenseData.resellerId !== resellerUid) {
            return { error: 'This device is already licensed by another reseller.', status: 403 };
          }

          if (licenseData.status === 'Active' && licenseData.isLifetime === true) {
            if (licenseType === 'lifetime') {
              return { success: true, message: 'Lifetime license is already active.' };
            }
            return { error: 'Trajna licenca ne može se zamijeniti kraćom licencom.', status: 409 };
          }

          const samePaidType = licenseData.isLifetime === true
            ? licenseType === 'lifetime'
            : licenseType === '1_year';
          if (licenseData.status === 'Active' && samePaidType && licenseData.updatedAt) {
            const updatedMs = licenseData.updatedAt.toMillis ? licenseData.updatedAt.toMillis() : Date.now();
            if (Date.now() - updatedMs < 5 * 60 * 1000) {
              return { success: true, message: 'Already activated recently.' };
            }
          }
        }
        if (licenseData?.status === 'Trial' && licenseType === 'trial') {
          return { success: true, message: 'Trial already exists.' };
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
          const previousExpiry = existingLicenseData?.expiresAt?.toDate
            ? existingLicenseData.expiresAt.toDate()
            : existingLicenseData?.expiresAt instanceof Date
              ? existingLicenseData.expiresAt
              : null;
          const expirationDate = previousExpiry && previousExpiry.getTime() > Date.now()
            ? new Date(previousExpiry)
            : new Date();
          expirationDate.setFullYear(expirationDate.getFullYear() + 1);
          licenseData.expiresAt = expirationDate;
        } else {
          licenseData.expiresAt = null;
        }
      } else {
        licenseData.status = 'Trial';
        licenseData.isLifetime = false;
        licenseData.trialStartedAt = FieldValue.serverTimestamp();
        const expirationDate = new Date();
        expirationDate.setDate(expirationDate.getDate() + 3);
        licenseData.expiresAt = expirationDate;
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
