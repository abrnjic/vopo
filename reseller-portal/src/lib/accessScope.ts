import { adminDb } from '@/lib/firebaseAdmin';

export async function permittedOwnerIds(context: { uid: string; role: string }): Promise<string[] | null> {
  if (context.role === 'admin') return null;
  if (context.role === 'subseller') return [context.uid];
  if (context.role !== 'reseller') return [];
  const children = await adminDb.collection('users').where('parentResellerId', '==', context.uid).get();
  return [context.uid, ...children.docs.filter((d: any) => d.data()?.role === 'subseller').map((d: any) => d.id)];
}

export function ownsRecord(data: any, ownerIds: string[] | null) {
  if (ownerIds === null) return true;
  return ownerIds.includes(data.resellerId) || ownerIds.includes(data.userId) || ownerIds.includes(data.subsellerId);
}

export function serializeValue(value: any): any {
  if (value == null) return value;
  if (typeof value.toDate === 'function') return value.toDate().toISOString();
  if (value instanceof Date) return value.toISOString();
  if (Array.isArray(value)) return value.map(serializeValue);
  if (typeof value === 'object') return Object.fromEntries(Object.entries(value).map(([k, v]) => [k, serializeValue(v)]));
  return value;
}
