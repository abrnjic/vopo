import { NextRequest, NextResponse } from 'next/server';
import { verifyAuthToken } from '@/lib/auth';
import { adminDb } from '@/lib/firebaseAdmin';
import { permittedOwnerIds, serializeValue } from '@/lib/accessScope';
import { z } from 'zod';

const CreateTicket = z.object({
  deviceId: z.string().trim().min(1).max(80), subject: z.string().trim().min(3).max(120), message: z.string().trim().min(3).max(3000)
}).strict();
const UpdateTicket = z.object({
  id: z.string().min(1), message: z.string().trim().min(1).max(3000).optional(), status: z.enum(['open', 'in_progress', 'resolved']).optional()
}).strict().refine(v => v.message || v.status, 'Nema izmjene.');

export const dynamic = 'force-dynamic';

async function requireUser(request: NextRequest) {
  const auth = await verifyAuthToken(request);
  if (auth.status !== 'authenticated' || !['admin', 'reseller', 'subseller'].includes(auth.context.role)) return null;
  return auth.context;
}

export async function GET(request: NextRequest) {
  const context = await requireUser(request);
  if (!context) return NextResponse.json({ error: 'Pristup odbijen.' }, { status: 403 });
  const owners = await permittedOwnerIds(context);
  const snap = await adminDb.collection('support_tickets').get();
  const tickets = snap.docs.filter((d: any) => owners === null || owners.includes(d.data()?.ownerId) || owners.includes(d.data()?.resellerId))
    .map((d: any) => ({ id: d.id, ...serializeValue(d.data()) })).sort((a: any, b: any) => String(b.updatedAt).localeCompare(String(a.updatedAt)));
  return NextResponse.json({ tickets }, { headers: { 'Cache-Control': 'no-store, private' } });
}

export async function POST(request: NextRequest) {
  const context = await requireUser(request);
  if (!context) return NextResponse.json({ error: 'Pristup odbijen.' }, { status: 403 });
  const parsed = CreateTicket.safeParse(await request.json());
  if (!parsed.success) return NextResponse.json({ error: 'Provjerite Device ID, naslov i opis.' }, { status: 400 });
  const license = await adminDb.collection('licenses').doc(parsed.data.deviceId).get();
  const owners = await permittedOwnerIds(context);
  if (!license.exists || !(owners === null || owners.includes(license.data()?.resellerId))) return NextResponse.json({ error: 'Uređaj nije pronađen u vašem panelu.' }, { status: 404 });
  const now = new Date().toISOString();
  const ref = adminDb.collection('support_tickets').doc();
  await ref.set({
    ownerId: license.data()?.resellerId || context.uid,
    resellerId: license.data()?.resellerId || context.uid,
    createdBy: context.uid, createdByEmail: context.email || '', deviceId: parsed.data.deviceId,
    subject: parsed.data.subject, status: 'open', createdAt: now, updatedAt: now,
    messages: [{ authorId: context.uid, authorEmail: context.email || '', role: context.role, message: parsed.data.message, createdAt: now }]
  });
  await adminDb.collection('activity_logs').doc().set({ userId: context.uid, userEmail: context.email || '', role: context.role, action: 'SUPPORT_TICKET_CREATED', deviceId: parsed.data.deviceId, details: parsed.data.subject, timestamp: now });
  return NextResponse.json({ id: ref.id, success: true }, { status: 201 });
}

export async function PATCH(request: NextRequest) {
  const context = await requireUser(request);
  if (!context) return NextResponse.json({ error: 'Pristup odbijen.' }, { status: 403 });
  const parsed = UpdateTicket.safeParse(await request.json());
  if (!parsed.success) return NextResponse.json({ error: 'Neispravna izmjena.' }, { status: 400 });
  const ref = adminDb.collection('support_tickets').doc(parsed.data.id);
  const snap = await ref.get();
  const owners = await permittedOwnerIds(context);
  if (!snap.exists || !(owners === null || owners.includes(snap.data()?.ownerId) || owners.includes(snap.data()?.resellerId))) return NextResponse.json({ error: 'Ticket nije pronađen.' }, { status: 404 });
  if (context.role === 'subseller' && parsed.data.status) return NextResponse.json({ error: 'Status mijenjaju reseller ili administrator.' }, { status: 403 });
  const current = snap.data() || {};
  const now = new Date().toISOString();
  const messages = Array.isArray(current.messages) ? [...current.messages] : [];
  if (parsed.data.message) messages.push({ authorId: context.uid, authorEmail: context.email || '', role: context.role, message: parsed.data.message, createdAt: now });
  await ref.update({ messages, status: parsed.data.status || current.status || 'open', updatedAt: now });
  await adminDb.collection('activity_logs').doc().set({ userId: context.uid, userEmail: context.email || '', role: context.role, action: 'SUPPORT_TICKET_UPDATED', deviceId: current.deviceId || '', details: `Ticket ${parsed.data.id}: ${parsed.data.status || 'nova poruka'}`, timestamp: now });
  return NextResponse.json({ success: true });
}
