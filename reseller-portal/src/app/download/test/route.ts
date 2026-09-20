import { NextResponse } from 'next/server';
import { adminDb } from '@/lib/firebaseAdmin';
import { validateBlobUrl } from '@/utils/blobValidator';

export const dynamic = 'force-dynamic';

export async function GET() {
  try {
    const doc = await adminDb.collection('system').doc('apk_test_metadata').get();
    const data = doc.exists ? doc.data() : null;
    if (!data?.latestUrl) return new NextResponse('Test APK not found', { status: 404 });
    if (!validateBlobUrl(data.latestUrl, data.versionName, data.versionCode, 'test')) {
      return new NextResponse('Invalid Test APK URL', { status: 500 });
    }
    return NextResponse.redirect(data.latestUrl, {
      status: 307,
      headers: { 'Cache-Control': 'no-store, max-age=0' }
    });
  } catch (error) {
    console.error('Error redirecting to test APK:', error);
    return new NextResponse('Internal Server Error', { status: 500 });
  }
}
