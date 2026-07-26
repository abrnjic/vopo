import { NextRequest, NextResponse } from 'next/server';
import { adminDb } from '@/lib/firebaseAdmin';
import { verifyAuthToken } from '@/lib/auth';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';
import { checkRateLimit } from '@/lib/rateLimit';

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
    const rateLimit = checkRateLimit(`log_${authContext.uid}`, 10, 60000); // 10 req per minute per user
    if (!rateLimit.success) {
      return NextResponse.json({ error: 'Too many requests' }, { 
        status: 429,
        headers: rateLimit.headers 
      });
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
