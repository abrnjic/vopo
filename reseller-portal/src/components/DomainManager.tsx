'use client';

import { useEffect, useState } from 'react';
import { useAuth } from '../context/AuthContext';

type Domains = { assignedDomains: string[]; customDomains: string[] };
export default function DomainManager({ targetUserId, catalog = false, onChange }: { targetUserId?: string; catalog?: boolean; onChange?: (data: Domains) => void }) {
  const { user, userData } = useAuth();
  const [domains, setDomains] = useState<Domains>({ assignedDomains: [], customDomains: [] });
  const [value, setValue] = useState('');
  const [editing, setEditing] = useState<{ domain: string; source: keyof Domains } | null>(null);
  const [busy, setBusy] = useState(false);
  const [error, setError] = useState('');
  const [loaded, setLoaded] = useState(false);
  useEffect(() => {
    let current = true;
    setLoaded(false);
    setEditing(null);
    setValue('');
    async function load() {
      try {
        const token = await user?.getIdToken();
        const res = await fetch(`/api/domains${catalog ? '?catalog=1' : targetUserId ? `?targetUserId=${encodeURIComponent(targetUserId)}` : ''}`, { headers: { Authorization: `Bearer ${token}` } });
        const data = await res.json().catch(() => ({ error: 'Server nije mogao obraditi zahtjev. Pokušajte ponovno.' }));
        if (!res.ok) throw new Error(data.error);
        if (current) { setDomains(data); setError(''); setLoaded(true); }
      } catch (e: any) { if (current) setError(e.message || 'Dohvat domena nije uspio.'); }
    }
    if (user) void load();
    return () => { current = false; };
  }, [user, targetUserId, catalog]);

  async function change(action: 'add' | 'edit' | 'delete', domain: string, source: keyof Domains, replacement?: string) {
    setBusy(true);
    setError('');
    try {
      const token = await user?.getIdToken();
      const res = await fetch('/api/domains', { method: 'POST', headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${token}` }, body: JSON.stringify({ targetUserId, catalog, action, source, domain, replacement }) });
      const data = await res.json().catch(() => ({ error: 'Server nije mogao obraditi zahtjev. Pokušajte ponovno.' }));
      if (!res.ok) throw new Error(data.error);
      setDomains(data);
      onChange?.(data);
      setValue('');
      setEditing(null);
    } catch (e: any) { setError(e.message || 'Spremanje nije uspjelo.'); }
    finally { setBusy(false); }
  }
  return (
    <section className="space-y-4">
      <h3 className="text-lg font-bold text-white">Domene servera ({domains.assignedDomains.length + domains.customDomains.length})</h3>
      <p className="text-sm text-gray-400">Dodajte jednu ili više HTTP/HTTPS adresa, uključujući port ako ga server koristi. Promjene vrijede za nove aktivacije; postojeće licence zadržavaju svoj URL.</p>
      {error && <p role="alert" className="text-red-400">{error}</p>}
      {!loaded && !error && <p className="text-gray-400">Učitavanje domena...</p>}
      <div className="flex gap-2">
        <input aria-label={editing ? 'Nova adresa domene' : 'Adresa domene'} placeholder="https://server.example:8080" value={value} onChange={e => setValue(e.target.value)} disabled={busy || !loaded} className="min-w-0 flex-1 rounded-lg bg-gray-950 border border-gray-700 p-3 text-white" />
        <button type="button" disabled={busy || !loaded || !value.trim()} onClick={() => void change(editing ? 'edit' : 'add', editing?.domain || value, editing?.source || (userData?.role === 'admin' ? 'assignedDomains' : 'customDomains'), editing ? value : undefined)} className="rounded-lg bg-blue-600 px-3 text-white disabled:opacity-50">{busy ? 'Spremanje…' : editing ? 'Spremi' : 'Dodaj'}</button>
        {editing && <button type="button" disabled={busy} onClick={() => { setEditing(null); setValue(''); }} className="text-gray-300">Odustani</button>}
      </div>
      {(['assignedDomains', 'customDomains'] as const).map(source => domains[source].map(domain => (
        <div key={`${source}:${domain}`} className="flex flex-wrap items-center gap-3 rounded-lg bg-gray-950 border border-gray-700 p-3">
          <span className="flex-1 min-w-0 break-all text-white">{domain}</span>
          <button type="button" disabled={busy} onClick={() => { setEditing({ domain, source }); setValue(domain); }} className="text-blue-400">Uredi</button>
          <button type="button" disabled={busy} onClick={() => { if (confirm(`Ukloniti domenu ${domain}?`)) void change('delete', domain, source); }} className="text-red-400">Ukloni</button>
        </div>
      )))}
      {loaded && !domains.assignedDomains.length && !domains.customDomains.length && <p className="text-gray-400">Nema dodanih domena.</p>}
    </section>
  );
}
