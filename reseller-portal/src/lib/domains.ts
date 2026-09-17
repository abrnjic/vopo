import { z } from 'zod';

export function normalizeDomain(input: string): string {
  const url = new URL(input.includes('://') ? input.trim() : `https://${input.trim()}`);
  if (!['http:', 'https:'].includes(url.protocol) || !url.hostname || url.username || url.password || url.search || url.hash) {
    throw new Error('Unesite HTTP/HTTPS adresu servera bez lozinke, upita ili fragmenta.');
  }
  return url.toString().replace(/\/+$/, '');
}

export const DomainSchema = z.string().trim().min(1).max(200).transform((value, ctx) => {
  try { return normalizeDomain(value); }
  catch { ctx.addIssue({ code: 'custom', message: 'Neispravna HTTP/HTTPS domena.' }); return z.NEVER; }
});
export const DomainsSchema = z.array(DomainSchema).max(50).transform(values => [...new Set(values)]);
export function domainsForUser(data: { assignedDomains?: string[]; customDomains?: string[] }): string[] {
  return [...new Set([...(data.assignedDomains || []), ...(data.customDomains || [])].map(normalizeDomain))];
}
