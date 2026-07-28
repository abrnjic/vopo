import { NextResponse } from 'next/server';
import { adminDb } from '@/lib/firebaseAdmin';

export const dynamic = 'force-dynamic';

export async function GET() {
  try {
    const doc = await adminDb.collection('system').doc('apk_metadata').get();

    if (!doc.exists) {
      return NextResponse.json({ error: 'Metadata not found' }, { status: 404 });
    }

    return NextResponse.json(doc.data(), {
      headers: {
        'Cache-Control': 'no-store, max-age=0'
      }
    });
  } catch (error: any) {
    console.error('Error fetching APK metadata:', error);
    return NextResponse.json({ error: 'Internal Server Error' }, { status: 500 });
  }
}
