import { NextResponse } from 'next/server';
import { Config } from '@/config/urls';
import { adminDb } from '@/lib/firebaseAdmin';

export const dynamic = 'force-dynamic';

export async function GET() {
  try {
    const doc = await adminDb.collection('system').doc('apk_metadata').get();
    const data = doc.exists ? doc.data() : null;
    if (!data?.versionName || !data?.versionCode || !data?.checksum) {
      return NextResponse.json({ error: 'Metadata not found' }, { status: 404 });
    }

    return NextResponse.json({
      latestVersionCode: Number(data.versionCode),
      latestVersionName: data.versionName,
      downloadUrl: Config.LATEST_APK_URL,
      checksum: data.checksum,
      releaseNotes: data.releaseNotes || '',
      updatedAt: data.updatedAt || null,
      minimumVersionCode: Number(data.minimumVersionCode || data.versionCode),
      forceUpdate: data.forceUpdate === true
    }, {
      headers: { 'Cache-Control': 'no-store, max-age=0' }
    });
  } catch (error) {
    console.error('Error fetching version metadata:', error);
    return NextResponse.json({ error: 'Internal Server Error' }, { status: 500 });
  }
}
