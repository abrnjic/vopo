import { NextResponse } from 'next/server';
import { adminDb } from '@/lib/firebaseAdmin';

export const dynamic = 'force-dynamic';

export function validateBlobUrl(latestUrl: string, expectedVersionName: string, expectedVersionCode: string): boolean {
  try {
    const url = new URL(latestUrl);
    
    // Stroga provjera domene protiv okolišne varijable
    const expectedHostname = process.env.BLOB_HOSTNAME;
    if (!expectedHostname || url.hostname !== expectedHostname) {
      console.error('Invalid blob URL hostname. Expected:', expectedHostname);
      return false;
    }
    
    const isHttps = url.protocol === 'https:';
    const hasNoPort = url.port === '';
    const hasNoAuth = url.username === '' && url.password === '';
    
    // Stroga provjera pathname strukture s versionName i versionCode metapodacima
    const expectedPathname = `/apk/releases/vopoapp-${expectedVersionName}-${expectedVersionCode}.apk`;
    const isValidPath = url.pathname === expectedPathname;

    if (!isHttps || !hasNoPort || !hasNoAuth || !isValidPath) {
      console.error('Invalid blob URL characteristics:', {
        protocol: url.protocol, port: url.port, auth: !!url.username, pathname: url.pathname
      });
      return false;
    }
    return true;
  } catch {
    return false;
  }
}

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
