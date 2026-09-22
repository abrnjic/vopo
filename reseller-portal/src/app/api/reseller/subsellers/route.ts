import { NextRequest, NextResponse } from 'next/server';
import { FieldValue } from 'firebase-admin/firestore';
import { z } from 'zod';
import { verifyAuthToken } from '@/lib/auth';
import { adminAuth, adminDb } from '@/lib/firebaseAdmin';
import { DomainsSchema, domainsForUser } from '@/lib/domains';

const CreateSubsellerSchema = z.object({
  email: z.string().trim().email(),
  password: z.string().min(6).max(128),
  credits: z.number().int().min(0).max(1_000_000),
  assignedDomains: DomainsSchema,
}).strict();

const UpdateSubsellerSchema = z.discriminatedUnion('action', [
  z.object({
    action: z.literal('adjust_credits'),
    targetUserId: z.string().min(1).max(128),
    newCredits: z.number().int().min(0).max(1_000_000),
    reason: z.string().trim().min(3).max(300),
  }).strict(),
  z.object({
    action: z.literal('set_domains'),
    targetUserId: z.string().min(1).max(128),
    assignedDomains: DomainsSchema,
  }).strict(),
  z.object({
    action: z.literal('set_status'),
    targetUserId: z.string().min(1).max(128),
    status: z.enum(['active', 'suspended']),
  }).strict(),
]);

function authError(auth: Awaited<ReturnType<typeof verifyAuthToken>>) {
  if (auth.status === 'authenticated') return null;
  const status = auth.status === 'unauthenticated' || auth.status === 'invalid'
    ? 401
    : auth.status === 'error'
      ? 500
      : 403;
  return NextResponse.json({ error: auth.error || 'Pristup odbijen.' }, { status });
}

function toIso(value: any): string | null {
  if (!value) return null;
  const date = typeof value.toDate === 'function' ? value.toDate() : value instanceof Date ? value : new Date(value);
  return Number.isNaN(date.getTime()) ? null : date.toISOString();
}

function serializeSubseller(id: string, data: any) {
  return {
    uid: id,
    email: typeof data.email === 'string' ? data.email : '',
    credits: Number.isFinite(data.credits) ? data.credits : 0,
    status: data.status === 'suspended' ? 'suspended' : 'active',
    assignedDomains: Array.isArray(data.assignedDomains) ? data.assignedDomains : [],
    createdAt: toIso(data.createdAt),
    updatedAt: toIso(data.updatedAt),
  };
}

async function requireReseller(req: NextRequest) {
  const auth = await verifyAuthToken(req);
  const error = authError(auth);
  if (error) return { error };
  if (auth.status !== 'authenticated' || auth.context.role !== 'reseller') {
    return { error: NextResponse.json({ error: 'Samo glavni reseller može upravljati subsellerima.' }, { status: 403 }) };
  }
  return { auth };
}

export async function GET(req: NextRequest) {
  try {
    const access = await requireReseller(req);
    if (access.error) return access.error;
    const context = access.auth!.context;
    const [parentSnap, childrenSnap] = await Promise.all([
      adminDb.collection('users').doc(context.uid).get(),
      adminDb.collection('users').where('parentResellerId', '==', context.uid).get(),
    ]);
    const parent = parentSnap.data() || {};
    const subsellers = childrenSnap.docs
      .filter((doc: any) => doc.data()?.role === 'subseller' && doc.data()?.status !== 'deleted')
      .map((doc: any) => serializeSubseller(doc.id, doc.data()))
      .sort((a: any, b: any) => a.email.localeCompare(b.email));
    return NextResponse.json({
      parentCredits: Number.isFinite(parent.credits) ? parent.credits : 0,
      availableDomains: domainsForUser(parent),
      subsellers,
    }, { headers: { 'Cache-Control': 'no-store, private' } });
  } catch (error) {
    console.error('GET /api/reseller/subsellers failed:', error);
    return NextResponse.json({ error: 'Dohvat subsellera nije uspio.' }, { status: 500 });
  }
}

export async function POST(req: NextRequest) {
  let createdUid: string | null = null;
  try {
    const access = await requireReseller(req);
    if (access.error) return access.error;
    const context = access.auth!.context;
    const parsed = CreateSubsellerSchema.safeParse(await req.json());
    if (!parsed.success) {
      return NextResponse.json({ error: 'Provjerite email, lozinku, početne kredite i domene.' }, { status: 400 });
    }
    const { email, password, credits, assignedDomains } = parsed.data;
    const authUser = await adminAuth.createUser({ email, password, disabled: false });
    createdUid = authUser.uid;

    const result = await adminDb.runTransaction(async (tx: any) => {
      const parentRef = adminDb.collection('users').doc(context.uid);
      const parentSnap = await tx.get(parentRef);
      if (!parentSnap.exists || parentSnap.data()?.role !== 'reseller') {
        return { error: 'Glavni reseller ne postoji.', status: 404 };
      }
      const parent = parentSnap.data() || {};
      const parentCredits = Number.isFinite(parent.credits) ? parent.credits : 0;
      const approvedDomains = new Set(domainsForUser(parent));
      if (assignedDomains.some(domain => !approvedDomains.has(domain))) {
        return { error: 'Odabrana domena nije odobrena glavnom reselleru.', status: 403 };
      }
      if (parentCredits < credits) {
        return { error: 'Glavni reseller nema dovoljno kredita.', status: 409 };
      }

      const childRef = adminDb.collection('users').doc(authUser.uid);
      const parentAfter = parentCredits - credits;
      tx.set(childRef, {
        email,
        role: 'subseller',
        parentResellerId: context.uid,
        status: 'active',
        credits,
        assignedDomains,
        customDomains: [],
        createdAt: FieldValue.serverTimestamp(),
        updatedAt: FieldValue.serverTimestamp(),
      });
      tx.update(parentRef, { credits: parentAfter, updatedAt: FieldValue.serverTimestamp() });
      tx.set(adminDb.collection('transactions').doc(), {
        type: 'subseller_initial_credit_transfer',
        resellerId: context.uid,
        subsellerId: authUser.uid,
        creditsTransferred: credits,
        parentBalanceBefore: parentCredits,
        parentBalanceAfter: parentAfter,
        subsellerBalanceBefore: 0,
        subsellerBalanceAfter: credits,
        timestamp: FieldValue.serverTimestamp(),
      });
      tx.set(adminDb.collection('activity_logs').doc(), {
        userId: context.uid,
        userEmail: context.email || '',
        role: 'reseller',
        action: 'CREATE_SUBSELLER',
        details: `Kreiran subseller ${email}; krediti 0 → ${credits}; reseller ${parentCredits} → ${parentAfter}; domene: ${assignedDomains.join(', ') || 'bez domena'}`,
        targetUserId: authUser.uid,
        parentResellerId: context.uid,
        creditDelta: credits,
        domains: assignedDomains,
        timestamp: FieldValue.serverTimestamp(),
      });
      return { parentCredits: parentAfter };
    });

    if ('error' in result) {
      await adminAuth.deleteUser(authUser.uid).catch((error: unknown) => console.error('Subseller auth rollback failed:', error));
      createdUid = null;
      return NextResponse.json({ error: result.error }, { status: result.status });
    }
    createdUid = null;
    return NextResponse.json({ uid: authUser.uid, parentCredits: result.parentCredits }, { status: 201 });
  } catch (error: any) {
    if (createdUid) await adminAuth.deleteUser(createdUid).catch((rollbackError: unknown) => console.error('Subseller auth rollback failed:', rollbackError));
    if (error?.code === 'auth/email-already-exists') {
      return NextResponse.json({ error: 'Račun s tim emailom već postoji.' }, { status: 409 });
    }
    console.error('POST /api/reseller/subsellers failed:', error);
    return NextResponse.json({ error: 'Kreiranje subsellera nije uspjelo.' }, { status: 500 });
  }
}

export async function PATCH(req: NextRequest) {
  try {
    const access = await requireReseller(req);
    if (access.error) return access.error;
    const context = access.auth!.context;
    const parsed = UpdateSubsellerSchema.safeParse(await req.json());
    if (!parsed.success) return NextResponse.json({ error: 'Neispravni podaci za izmjenu subsellera.' }, { status: 400 });
    const command = parsed.data;

    const result = await adminDb.runTransaction(async (tx: any) => {
      const parentRef = adminDb.collection('users').doc(context.uid);
      const childRef = adminDb.collection('users').doc(command.targetUserId);
      const [parentSnap, childSnap] = await Promise.all([tx.get(parentRef), tx.get(childRef)]);
      if (!parentSnap.exists || parentSnap.data()?.role !== 'reseller') return { error: 'Glavni reseller ne postoji.', status: 404 };
      if (!childSnap.exists || childSnap.data()?.role !== 'subseller' || childSnap.data()?.parentResellerId !== context.uid) {
        return { error: 'Subseller ne postoji ili nije u vašem vlasništvu.', status: 404 };
      }
      const parent = parentSnap.data() || {};
      const child = childSnap.data() || {};

      if (command.action === 'adjust_credits') {
        const parentBefore = Number.isFinite(parent.credits) ? parent.credits : 0;
        const childBefore = Number.isFinite(child.credits) ? child.credits : 0;
        const delta = command.newCredits - childBefore;
        if (delta > parentBefore) return { error: 'Glavni reseller nema dovoljno kredita za ovu korekciju.', status: 409 };
        const parentAfter = parentBefore - delta;
        tx.update(parentRef, { credits: parentAfter, updatedAt: FieldValue.serverTimestamp() });
        tx.update(childRef, { credits: command.newCredits, updatedAt: FieldValue.serverTimestamp() });
        tx.set(adminDb.collection('transactions').doc(), {
          type: delta >= 0 ? 'subseller_credit_transfer' : 'subseller_credit_refund',
          resellerId: context.uid,
          subsellerId: command.targetUserId,
          creditDelta: delta,
          parentBalanceBefore: parentBefore,
          parentBalanceAfter: parentAfter,
          subsellerBalanceBefore: childBefore,
          subsellerBalanceAfter: command.newCredits,
          reason: command.reason,
          timestamp: FieldValue.serverTimestamp(),
        });
        tx.set(adminDb.collection('activity_logs').doc(), {
          userId: context.uid,
          userEmail: context.email || '',
          role: 'reseller',
          action: delta >= 0 ? 'SUBSELLER_CREDIT_TRANSFER' : 'SUBSELLER_CREDIT_REFUND',
          details: `Korekcija kredita za ${child.email || command.targetUserId}: ${childBefore} → ${command.newCredits}; reseller ${parentBefore} → ${parentAfter}; razlog: ${command.reason}`,
          targetUserId: command.targetUserId,
          parentResellerId: context.uid,
          creditDelta: delta,
          timestamp: FieldValue.serverTimestamp(),
        });
        return { parentCredits: parentAfter };
      }

      if (command.action === 'set_domains') {
        const approved = new Set(domainsForUser(parent));
        if (command.assignedDomains.some(domain => !approved.has(domain))) {
          return { error: 'Subselleru možete dodijeliti samo vlastite odobrene domene.', status: 403 };
        }
        const before = domainsForUser(child);
        tx.update(childRef, { assignedDomains: command.assignedDomains, customDomains: [], updatedAt: FieldValue.serverTimestamp() });
        tx.set(adminDb.collection('activity_logs').doc(), {
          userId: context.uid,
          userEmail: context.email || '',
          role: 'reseller',
          action: 'SUBSELLER_DOMAINS_UPDATED',
          details: `Domene za ${child.email || command.targetUserId}: [${before.join(', ')}] → [${command.assignedDomains.join(', ')}]`,
          targetUserId: command.targetUserId,
          parentResellerId: context.uid,
          domainsBefore: before,
          domainsAfter: command.assignedDomains,
          timestamp: FieldValue.serverTimestamp(),
        });
        return { parentCredits: Number.isFinite(parent.credits) ? parent.credits : 0 };
      }

      const previousStatus = child.status === 'suspended' ? 'suspended' : 'active';
      tx.update(childRef, { status: command.status, updatedAt: FieldValue.serverTimestamp() });
      tx.set(adminDb.collection('activity_logs').doc(), {
        userId: context.uid,
        userEmail: context.email || '',
        role: 'reseller',
        action: command.status === 'suspended' ? 'SUSPEND_SUBSELLER' : 'ACTIVATE_SUBSELLER',
        details: `Status subsellera ${child.email || command.targetUserId}: ${previousStatus} → ${command.status}`,
        targetUserId: command.targetUserId,
        parentResellerId: context.uid,
        statusBefore: previousStatus,
        statusAfter: command.status,
        timestamp: FieldValue.serverTimestamp(),
      });
      return { parentCredits: Number.isFinite(parent.credits) ? parent.credits : 0, authStatus: command.status };
    });

    if ('error' in result) return NextResponse.json({ error: result.error }, { status: result.status });
    if ('authStatus' in result) {
      try {
        await adminAuth.updateUser(command.targetUserId, { disabled: result.authStatus === 'suspended' });
      } catch (error) {
        console.error('Subseller Firebase Auth status update failed:', error);
      }
    }
    return NextResponse.json({ success: true, parentCredits: result.parentCredits });
  } catch (error) {
    console.error('PATCH /api/reseller/subsellers failed:', error);
    return NextResponse.json({ error: 'Izmjena subsellera nije uspjela.' }, { status: 500 });
  }
}
