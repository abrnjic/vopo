import { NextResponse } from 'next/server';

export async function GET() {
  // In a real application, this might be fetched from Firestore or environment variables
  const versionInfo = {
    latestVersionCode: 105,
    latestVersionName: "1.0.5",
    // This will point to the actual APK download route
    downloadUrl: "https://vopoapp.com/api/download/latest.apk", 
    releaseNotes: "Dodana podrška za VOD i serije. Ispravci grešaka u playeru.",
    forceUpdate: false
  };

  return NextResponse.json(versionInfo);
}
