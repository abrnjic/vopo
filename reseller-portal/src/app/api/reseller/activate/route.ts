import { NextRequest, NextResponse } from 'next/server';
import { adminDb } from '@/lib/firebaseAdmin';
import { verifyAuthToken } from '@/lib/auth';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';
import { DomainSchema, domainsForUser } from '@/lib/domains';
import { hashLinePin, LINE_PIN_PATTERN } from '@/lib/lineSecurity';

const ActivateSchema = z.object({
  deviceId: z.string().min(1).max(50),
  licenseType: z.enum(['1_year', 'lifetime', 'trial']),
  customerName: z.string().max(100).optional(),
  customerContact: z.string().max(100).optional(),
  username: z.string().max(100).optional(),
  password: z.string().max(100).optional(),
  linePin: z.string().regex(LINE_PIN_PATTERN),
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
    if (!['reseller', 'subseller'].includes(authContext.role)) {
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
      username, password, linePin, selectedDomain
    } = parsed.data;
    if (!selectedDomain || !username?.trim() || !password?.trim()) {
      return NextResponse.json({ error: 'Domena, korisničko ime i lozinka linije su obvezni.' }, { status: 400 });
    }
    
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

      const safeDeviceId = deviceId.trim();
      const licenseRef = adminDb.collection('licenses').doc(safeDeviceId);
      const licenseSnap = await transaction.get(licenseRef);
      const existingLicenseData = licenseSnap.exists ? licenseSnap.data() : null;

      if (existingLicenseData?.status === 'Transferred') {
        return { error: 'Licenca je prenesena na drugi uređaj.', status: 409 };
      }
      if (existingLicenseData?.resellerId && existingLicenseData.resellerId !== 'self_registered'
        && existingLicenseData.resellerId !== resellerUid) {
        return { error: 'Ovaj uređaj pripada drugom reselleru.', status: 403 };
      }
      if (existingLicenseData?.status === 'Active' && existingLicenseData.isLifetime === true
        && licenseType !== 'lifetime') {
        return { error: 'Trajna licenca ne može se zamijeniti kraćom licencom.', status: 409 };
      }

      // The app starts its own three-day trial at registration. Sending a line
      // for that trial must save its provider credentials without restarting it.
      const existingTrial = existingLicenseData?.status === 'Trial' && licenseType === 'trial';
      if (existingTrial) {
        const expiresAtMs = existingLicenseData.expiresAt?.toMillis?.()
          ?? (existingLicenseData.expiresAt instanceof Date ? existingLicenseData.expiresAt.getTime() : 0);
        if (expiresAtMs <= Date.now()) {
          return { error: 'Probni period je istekao. Odaberite godišnju ili trajnu aktivaciju.', status: 409 };
        }
      }
      const existingLifetime = existingLicenseData?.status === 'Active'
        && existingLicenseData.isLifetime === true && licenseType === 'lifetime';
      const samePaidType = existingLicenseData?.status === 'Active'
        && existingLicenseData.isLifetime !== true && licenseType === '1_year';
      const updatedMs = existingLicenseData?.updatedAt?.toMillis?.()
        ?? (existingLicenseData?.updatedAt instanceof Date
          ? existingLicenseData.updatedAt.getTime()
          : existingLicenseData?.updatedAt ? Date.now() : 0);
      const recentlyActivated = samePaidType && updatedMs > 0
        && Date.now() - updatedMs < 5 * 60 * 1000;
      const updateLineOnly = existingTrial || existingLifetime || recentlyActivated;

      if (!updateLineOnly && currentCredits < creditsToDeduct) {
        return { error: 'Not enough credits', status: 400 };
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
        ...(!existingLicenseData?.xtreamConfig?.username && !existingLicenseData?.createdByUid
          ? { createdByUid: resellerUid, createdByRole: authContext.role }
          : {}),
        linePinHash: hashLinePin(linePin),
        maxConcurrentStreams: 1,
        requireOfficialClient: true,
        updatedAt: FieldValue.serverTimestamp()
      };

      if (updateLineOnly) {
        transaction.set(licenseRef, licenseData, { merge: true });
        const logRef = adminDb.collection('activity_logs').doc();
        transaction.set(logRef, {
          userId: resellerUid,
          userEmail: authContext.email || '',
          role: authContext.role,
          action: 'UPDATE_LINE',
          details: `Updated line for device ${safeDeviceId} without changing license duration`,
          deviceId: safeDeviceId,
          timestamp: FieldValue.serverTimestamp()
        });
        return {
          success: true,
          status: existingLicenseData.status,
          isLifetime: existingLicenseData.isLifetime === true,
          creditsRemaining: currentCredits,
          message: existingTrial
            ? 'Probna linija je spremljena. Uređaj ostaje u postojećem probnom razdoblju; za godišnju ili trajnu aktivaciju odaberite odgovarajuću opciju.'
            : existingLifetime
              ? 'Podaci trajne linije su ažurirani. Licenca ostaje trajna; krediti nisu potrošeni.'
              : 'Podaci godišnje linije su ažurirani. Postojeći datum isteka ostaje isti; krediti nisu ponovno potrošeni.'
        };
      }

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
        role: authContext.role,
        action: 'CREATE_LICENSE',
        details: `Created ${licenseType} license for device ${safeDeviceId} (${customerName ? customerName.trim() : ''})`,
        timestamp: FieldValue.serverTimestamp()
      });

      return {
        success: true,
        status: licenseData.status,
        isLifetime: licenseData.isLifetime,
        creditsRemaining: currentCredits - creditsToDeduct,
        message: licenseType === 'trial'
          ? 'Probna linija je spremljena. Probno razdoblje traje 3 dana od registracije uređaja; krediti nisu potrošeni.'
          : licenseType === 'lifetime'
            ? 'Linija je trajno aktivirana. Potrošena su 2 kredita.'
            : 'Linija je aktivirana na 1 godinu. Potrošen je 1 kredit.'
      };
    });

    if (result.error) {
      return NextResponse.json({ error: result.error }, { status: result.status });
    }

    return NextResponse.json(result, { status: 200 });
  } catch (error: any) {
    console.error('API /reseller/activate error:', error);
    return NextResponse.json({ error: error.message || 'Internal Server Error' }, { status: 500 });
  }
}
