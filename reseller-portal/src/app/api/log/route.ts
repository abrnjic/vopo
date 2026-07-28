import { NextRequest, NextResponse } from 'next/server';
import { adminDb } from '@/lib/firebaseAdmin';
import { verifyAuthToken } from '@/lib/auth';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';
import { checkRateLimit } from '@/lib/rateLimit';
import crypto from 'crypto';

const LogSchema = z.object({
  action: z.enum(['LOGIN', 'LOGOUT', 'VIEW_LICENSES', 'VIEW_USERS', 'EXPORT_DATA', 'OTHER']),
  details: z.string().max(500).optional()
}).strict();

export async function POST(req: NextRequest) {
  try {
    const auth = await verifyAuthToken(req);
    if (auth.status !== 'authenticated') {
      const statusCode = (auth.status === 'unauthenticated' || auth.status === 'invalid') ? 401 : (auth.status === 'error' ? 500 : 403);
      return NextResponse.json({ error: auth.error || 'Unauthorized' }, { status: statusCode });
    }
    
    const authContext = auth.context;

    // Rate limit check
    const ip = req.headers.get('x-real-ip') || req.headers.get('x-vercel-forwarded-for') || req.headers.get('x-forwarded-for') || 'unknown';
    const hashedUid = crypto.createHash('sha256').update(authContext.uid).digest('hex');

    try {
      const ipLimit = await checkRateLimit(`ip_${ip}`, 20, 60000); // 20 req per minute per IP for logs
      if (!ipLimit.success) {
        return NextResponse.json({ error: 'Too many requests from this IP' }, { status: 429, headers: ipLimit.headers });
      }

      const userLimit = await checkRateLimit(`user_${hashedUid}`, 10, 60000); // 10 req per minute per user for logs
      if (!userLimit.success) {
        return NextResponse.json({ error: 'Too many requests for this user' }, { status: 429, headers: userLimit.headers });
      }
    } catch (limitError: any) {
      if (limitError.message === '503') {
        return NextResponse.json({ error: 'Service Unavailable' }, { status: 503, headers: { 'Retry-After': '30' } });
      }
      throw limitError;
    }

    const body = await req.json();
    const parsed = LogSchema.safeParse(body);
    if (!parsed.success) {
      return NextResponse.json({ error: 'Invalid payload format or extra fields present.' }, { status: 400 });
    }

    const { action, details } = parsed.data;

    const logRef = adminDb.collection('activity_logs').doc();
    await logRef.set({
      userId: authContext.uid,
      userEmail: authContext.email || '',
      role: authContext.role || 'user',
      action: action,
      details: details || '',
      timestamp: FieldValue.serverTimestamp()
    });

    return NextResponse.json({ success: true }, { status: 201 });
  } catch (error: any) {
    console.error('API /log error:', error);
    return NextResponse.json({ error: 'Internal Server Error' }, { status: 500 });
  }
}
