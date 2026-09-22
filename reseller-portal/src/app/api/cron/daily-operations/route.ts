import { NextRequest, NextResponse } from 'next/server';
import { createExpiryNotifications, startFirestoreExport } from '@/lib/operations';

export const dynamic = 'force-dynamic';
export const maxDuration = 60;

export async function GET(request: NextRequest) {
  const secret = process.env.CRON_SECRET;
  if (!secret || request.headers.get('authorization') !== `Bearer ${secret}`) {
    return NextResponse.json({ error: 'Unauthorized' }, { status: 401 });
  }
  const notifications = await createExpiryNotifications();
  let backup: any;
  try { backup = await startFirestoreExport(); }
  catch (error) { backup = { started: false, error: error instanceof Error ? error.message : 'Backup error' }; }
  return NextResponse.json({ ok: true, notifications, backup, ranAt: new Date().toISOString() });
}
