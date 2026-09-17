import { NextRequest, NextResponse } from 'next/server';
import { z } from 'zod';
import { FieldValue } from 'firebase-admin/firestore';
import { adminAuth, adminDb } from '@/lib/firebaseAdmin';
import { verifyAuthToken } from '@/lib/auth';
import { DomainsSchema } from '@/lib/domains';

const CreateSchema = z.object({ email: z.string().trim().email(), password: z.string().min(6).max(128), credits: z.number().int().min(0).max(1000000), assignedDomains: DomainsSchema }).strict();

export async function POST(req: NextRequest) {
  try {
    const auth = await verifyAuthToken(req);
    if (auth.status !== 'authenticated') return NextResponse.json({ error: auth.error }, { status: auth.status === 'error' ? 500 : ['invalid', 'unauthenticated'].includes(auth.status) ? 401 : 403 });
    if (auth.context.role !== 'admin') return NextResponse.json({ error: 'Pristup odbijen.' }, { status: 403 });
    const parsed = CreateSchema.safeParse(await req.json());
    if (!parsed.success) return NextResponse.json({ error: 'Provjerite email, lozinku (najmanje 6 znakova), kredite i domene.' }, { status: 400 });
    const { email, password, credits, assignedDomains } = parsed.data;
    const user = await adminAuth.createUser({ email, password, disabled: false });
    try {
      await adminDb.runTransaction(async (tx: any) => {
        tx.set(adminDb.collection('users').doc(user.uid), { email, role: 'reseller', status: 'active', credits, assignedDomains, customDomains: [], createdAt: FieldValue.serverTimestamp() });
        tx.set(adminDb.collection('activity_logs').doc(), { userId: auth.context.uid, userEmail: auth.context.email || '', role: 'admin', action: 'CREATE_RESELLER', details: `Reseller ${email}, krediti: ${credits}, domene: ${assignedDomains.length}`, timestamp: FieldValue.serverTimestamp() });
      });
    } catch (error) {
      try { await adminAuth.deleteUser(user.uid); }
      catch { console.error('Reseller creation compensation failed', user.uid); }
      throw error;
    }
    return NextResponse.json({ uid: user.uid }, { status: 201 });
  } catch (error: any) {
    if (error.code === 'auth/email-already-exists') return NextResponse.json({ error: 'Račun s tim emailom već postoji.' }, { status: 409 });
    return NextResponse.json({ error: 'Kreiranje reseller računa nije uspjelo.' }, { status: 500 });
  }
}
