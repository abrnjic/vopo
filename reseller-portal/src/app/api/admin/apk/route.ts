import { NextRequest, NextResponse } from 'next/server';
import { adminDb } from '@/lib/firebaseAdmin';
import { verifyAuthToken } from '@/lib/auth';
import { handleUpload, type HandleUploadBody } from '@vercel/blob/client';
import crypto from 'crypto';
import { del } from '@vercel/blob';
import { validateBlobUrl } from '@/utils/blobValidator';

export const onBeforeGenerateToken = async (pathname: string, clientPayload: string | null, request: NextRequest) => {
  // 3. Provjeri Firebase ID token i potvrdi ulogu i status
  const authResult = await verifyAuthToken(request);
  if (authResult.status !== 'authenticated') {
    throw new Error('Unauthorized: Not authenticated');
  }
  if (authResult.context.role !== 'admin') {
    throw new Error('Unauthorized: Not an admin');
  }

  // Provjeri status korisnika (aktivan)
  const userDoc = await adminDb.collection('users').doc(authResult.context.uid).get();
  if (!userDoc.exists || userDoc.data()?.status !== 'active') {
    throw new Error('Unauthorized: User is not active');
  }

  // Validiraj ekstenziju, versionName, versionCode
  const payload = JSON.parse(clientPayload || '{}');
  const { versionName, versionCode, checksum } = payload;

  if (!versionName || !versionCode || !checksum) {
    throw new Error('Missing versionName, versionCode, or checksum in clientPayload');
  }

  // Format sigurnog versionName
  const safeVersionName = versionName.replace(/[^a-zA-Z0-9.-]/g, '');
  if (safeVersionName !== versionName || safeVersionName.length === 0) {
    throw new Error('Invalid versionName format.');
  }

  if (!/^[1-9]\d*$/.test(versionCode)) {
    throw new Error('Invalid versionCode format. Must be a positive integer without leading zeros.');
  }

  const vCodeNum = parseInt(versionCode, 10);
  if (vCodeNum > Number.MAX_SAFE_INTEGER) {
    throw new Error('versionCode exceeds maximum safe integer.');
  }

  if (!/^[a-fA-F0-9]{64}$/.test(checksum)) {
    throw new Error('Invalid checksum format. Must be a 64-character hexadecimal string.');
  }
  const safeChecksum = checksum.toLowerCase();

  const expectedPathname = `apk/releases/vopoapp-${safeVersionName}-${versionCode}.apk`;
  if (pathname !== expectedPathname) {
    throw new Error('Invalid pathname. Must match exactly: apk/releases/vopoapp-{versionName}-{versionCode}.apk');
  }



  // Provjeri da novi versionCode mora biti veci od trenutačnog
  const metadataDoc = await adminDb.collection('system').doc('apk_metadata').get();
  if (metadataDoc.exists) {
    const currentData = metadataDoc.data();
    const currentCode = parseInt(currentData?.versionCode, 10);
    if (!isNaN(currentCode) && vCodeNum <= currentCode) {
      throw new Error('versionCode must be greater than current versionCode.');
    }
  }

  return {
    allowedContentTypes: ['application/vnd.android.package-archive', 'application/octet-stream'],
    maximumSizeInBytes: 100 * 1024 * 1024, // 100MB
    tokenPayload: JSON.stringify({
      versionName: safeVersionName,
      versionCode: vCodeNum.toString(),
      checksum: safeChecksum,
      uid: authResult.context.uid,
      email: authResult.context.email
    }),
  };
};

export const onUploadCompleted = async ({ blob, tokenPayload }: any) => {
  try {
    if (!tokenPayload) throw new Error('Missing tokenPayload');
    const { versionName, versionCode, checksum, uid, email } = JSON.parse(tokenPayload);

    if (!validateBlobUrl(blob.url, versionName, versionCode)) {
      await del(blob.url).catch(e => console.error("Failed to delete blob:", e.message));
      throw new Error('Invalid Blob URL characteristics.');
    }

    // Idempotency: Brzi read prije skidanja cijelog APK-a
    const metadataRef = adminDb.collection('system').doc('apk_metadata');
    const initialDoc = await metadataRef.get();
    if (initialDoc.exists) {
      const data = initialDoc.data();
      if (data?.latestUrl === blob.url && data?.versionCode === versionCode) {
        console.log('Idempotency hit: Blob is already latest.');
        return; // Već procesirano
      }
    }

    // Verify SHA-256 via Stream
    const response = await fetch(blob.url);
    if (!response.ok) throw new Error('Failed to fetch blob for verification');

    // We check content length but also gracefully handle missing header or mock environment limitations
    const contentLength = parseInt(response.headers.get('content-length') || '0', 10);
    if (contentLength > 100 * 1024 * 1024) {
      await del(blob.url).catch(e => console.error("Failed to delete blob:", e.message));
      throw new Error('Blob headers exceed maximum allowed size.');
    }

    const hash = crypto.createHash('sha256');
    const readable = response.body;
    if (!readable) throw new Error('No body in response');

    let byteCount = 0;
    const reader = readable.getReader();
    while (true) {
      const { done, value } = await reader.read();
      if (done) break;
      byteCount += value.length;
      if (byteCount > 100 * 1024 * 1024) {
        await del(blob.url).catch(e => console.error("Failed to delete blob:", e.message));
        throw new Error('Blob stream exceeds maximum allowed size.');
      }
      hash.update(value);
    }

    const serverHash = hash.digest('hex');
    let isValidHash = false;
    try {
      const clientBuffer = Buffer.from(checksum, 'hex');
      const serverBuffer = Buffer.from(serverHash, 'hex');
      if (clientBuffer.length === 32 && serverBuffer.length === 32) {
        isValidHash = crypto.timingSafeEqual(clientBuffer, serverBuffer);
      }
    } catch {
      isValidHash = false;
    }

    if (!isValidHash) {
      await del(blob.url).catch(e => console.error("Failed to delete blob:", e.message));
      throw new Error(`SHA-256 mismatch. Client: ${checksum}, Server: ${serverHash}`);
    }

    let wasPublished = false;

    await adminDb.runTransaction(async (transaction: any) => {
       const doc = await transaction.get(metadataRef);
       if (doc.exists) {
         const currentData = doc.data();

         // Idempotentnost unutar transakcije
         if (currentData?.latestUrl === blob.url && currentData?.versionCode === versionCode) {
           return; // Već procesirano
         }

         const currentCode = parseInt(currentData?.versionCode, 10);
         const newCode = parseInt(versionCode, 10);
         if (!isNaN(currentCode) && newCode <= currentCode) {
           throw new Error('versionCode conflict: newer version already exists');
         }
       }

       const metadata = {
         versionName,
         versionCode,
         checksum: serverHash,
         size: byteCount,
         latestUrl: blob.url,
         updatedAt: new Date().toISOString(),
       };

       transaction.set(metadataRef, metadata);

       // Deterministic audit log ID
       const auditId = `apk_dist_${versionCode}_${serverHash.substring(0, 8)}`;
       const auditRef = adminDb.collection('activity_logs').doc(auditId);

       transaction.set(auditRef, {
         actorUid: uid,
         actorEmail: email || '',
         actorRole: 'admin',
         action: 'UPDATE_PROFILE',
         details: `Objavljena nova verzija aplikacije: ${versionName} (${versionCode})`,
         timestamp: new Date().toISOString(),
         ipAddress: 'server'
       });

       wasPublished = true;
    });

  } catch (error) {
    console.error('Error in onUploadCompleted:', error instanceof Error ? error.message : 'Unknown error');
    throw error;
  }
};

export async function POST(request: NextRequest) {
  try {
    const body = (await request.json()) as HandleUploadBody;

    const jsonResponse = await handleUpload({
      body,
      request,
      onBeforeGenerateToken: async (pathname, clientPayload) => {
        return onBeforeGenerateToken(pathname, clientPayload, request);
      },
      onUploadCompleted: async ({ blob, tokenPayload }) => {
        return onUploadCompleted({ blob, tokenPayload });
      }
    });

    return NextResponse.json(jsonResponse);
  } catch (error: any) {
    console.error('APK Upload API Error:', error instanceof Error ? error.message : 'Unknown error');
    return NextResponse.json({ error: 'Upload request failed or was rejected.' }, { status: 400 });
  }
}
