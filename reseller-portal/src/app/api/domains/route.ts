import { NextRequest, NextResponse } from 'next/server';
import { z } from 'zod';
import { FieldValue } from 'firebase-admin/firestore';
import { verifyAuthToken } from '@/lib/auth';
import { adminDb } from '@/lib/firebaseAdmin';
import { DomainSchema, DomainsSchema } from '@/lib/domains';

const TargetSchema = z.object({ targetUserId: z.string().min(1).max(100).optional(), catalog: z.boolean().optional() });
const ChangeSchema = TargetSchema.extend({
  action: z.enum(['add', 'edit', 'delete']),
  source: z.enum(['assignedDomains', 'customDomains']),
  domain: DomainSchema,
  replacement: DomainSchema.optional()
}).strict().refine(v => v.action !== 'edit' || !!v.replacement, { message: 'Nova domena je obavezna.' });

async function authorize(req: NextRequest) {
  const auth = await verifyAuthToken(req);
  if (auth.status !== 'authenticated') return { response: NextResponse.json({ error: auth.error }, { status: auth.status === 'error' ? 500 : ['invalid', 'unauthenticated'].includes(auth.status) ? 401 : 403 }) };
  if (!['admin', 'reseller', 'subseller'].includes(auth.context.role)) return { response: NextResponse.json({ error: 'Pristup odbijen.' }, { status: 403 }) };
  return { context: auth.context };
}

export async function GET(req: NextRequest) {
  try {
    const auth = await authorize(req);
    if (auth.response) return auth.response;
    const parsed = TargetSchema.safeParse({ targetUserId: req.nextUrl.searchParams.get('targetUserId') || undefined, catalog: req.nextUrl.searchParams.get('catalog') === '1' });
    if (!parsed.success) return NextResponse.json({ error: 'Neispravan korisnik.' }, { status: 400 });
    const catalog = parsed.data.catalog === true;
    if (catalog && auth.context!.role !== 'admin') return NextResponse.json({ error: 'Pristup odbijen.' }, { status: 403 });
    const uid = parsed.data.targetUserId || auth.context!.uid;
    if (auth.context!.role !== 'admin' && uid !== auth.context!.uid) return NextResponse.json({ error: 'Pristup odbijen.' }, { status: 403 });
    const snap = await (catalog ? adminDb.collection('settings').doc('domainCatalog') : adminDb.collection('users').doc(uid)).get();
    if (catalog) return NextResponse.json({ assignedDomains: snap.data()?.assignedDomains || [], customDomains: [] });
    if (!snap.exists || !['reseller', 'subseller'].includes(snap.data().role)) return NextResponse.json({ error: 'Prodajni račun ne postoji.' }, { status: 404 });
    return NextResponse.json({ assignedDomains: snap.data().assignedDomains || [], customDomains: snap.data().customDomains || [] });
  } catch { return NextResponse.json({ error: 'Dohvat domena nije uspio.' }, { status: 500 }); }
}

export async function POST(req: NextRequest) {
  try {
    const auth = await authorize(req);
    if (auth.response) return auth.response;
    if (auth.context!.role === 'subseller') return NextResponse.json({ error: 'Domene subselleru dodjeljuje glavni reseller.' }, { status: 403 });
    const parsed = ChangeSchema.safeParse(await req.json());
    if (!parsed.success) return NextResponse.json({ error: 'Provjerite HTTP/HTTPS adresu domene i novu adresu pri uređivanju.' }, { status: 400 });
    const { action, source, domain, replacement } = parsed.data;
    if (parsed.data.catalog && source !== 'assignedDomains') return NextResponse.json({ error: 'Neispravan popis domena.' }, { status: 400 });
    const catalog = parsed.data.catalog === true;
    if (catalog && auth.context!.role !== 'admin') return NextResponse.json({ error: 'Pristup odbijen.' }, { status: 403 });
    const uid = parsed.data.targetUserId || auth.context!.uid;
    if (auth.context!.role !== 'admin' && uid !== auth.context!.uid) return NextResponse.json({ error: 'Pristup odbijen.' }, { status: 403 });
    const result = await adminDb.runTransaction(async (tx: any) => {
      const ref = catalog ? adminDb.collection('settings').doc('domainCatalog') : adminDb.collection('users').doc(uid);
      const snap = await tx.get(ref);
      if (!catalog && (!snap.exists || !['reseller', 'subseller'].includes(snap.data().role))) return { error: 'Prodajni račun ne postoji.', status: 404 };
      const data = snap.data() || {};
      const assignedDomains = DomainsSchema.parse(data.assignedDomains || []);
      const customDomains = DomainsSchema.parse(data.customDomains || []);
      const lists = { assignedDomains, customDomains };
      const list = lists[source];
      const other = lists[source === 'assignedDomains' ? 'customDomains' : 'assignedDomains'];
      if (action !== 'add' && !list.includes(domain)) return { error: 'Domena više ne postoji. Osvježite popis.', status: 409 };
      const next = action === 'delete' ? list.filter(d => d !== domain) : action === 'edit' ? list.map(d => d === domain ? replacement! : d) : [...list, domain];
      if (new Set([...next, ...other]).size !== next.length + other.length) return { error: 'Domena je već dodana.', status: 409 };
      if (next.length + other.length > 50) return { error: 'Najviše 50 domena po reselleru.', status: 400 };
      lists[source] = next;
      tx.set(ref, { ...lists, updatedAt: FieldValue.serverTimestamp() }, { merge: true });
      tx.set(adminDb.collection('activity_logs').doc(), { userId: auth.context!.uid, userEmail: auth.context!.email || '', role: auth.context!.role, action: `${action.toUpperCase()}_DOMAIN`, details: `${catalog ? 'catalog' : uid}: ${domain}${replacement ? ` → ${replacement}` : ''}`, timestamp: FieldValue.serverTimestamp() });
      return lists;
    });
    if ('error' in result) return NextResponse.json({ error: result.error }, { status: result.status });
    return NextResponse.json(result);
  } catch { return NextResponse.json({ error: 'Spremanje domena nije uspjelo.' }, { status: 500 }); }
}
