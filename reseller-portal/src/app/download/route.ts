import { NextResponse } from 'next/server';
import { adminDb } from '@/lib/firebaseAdmin';

export const dynamic = 'force-dynamic';

import { validateBlobUrl } from '@/utils/blobValidator';

export async function GET() {
  try {
    const doc = await adminDb.collection('system').doc('apk_metadata').get();

    if (!doc.exists || !doc.data()?.latestUrl) {
      return new NextResponse('APK not found', { status: 404 });
    }

    const { latestUrl, versionName, versionCode } = doc.data() as any;

    if (!validateBlobUrl(latestUrl, versionName, versionCode)) {
      return new NextResponse('Invalid Blob URL', { status: 500 });
    }

    return NextResponse.redirect(latestUrl, {
      status: 307,
      headers: {
        'Cache-Control': 'no-store, max-age=0'
      }
    });
  } catch (error: any) {
    console.error('Error redirecting to APK:', error);
    return new NextResponse('Internal Server Error', { status: 500 });
  }
}
