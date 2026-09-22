"use client";
import { useCallback, useEffect, useState } from 'react';
import { History, Monitor, Pencil, Search, X } from 'lucide-react';
import { useAuth } from '@/context/AuthContext';

type Line = { id: string; deviceId?: string; resellerId?: string; customerName?: string; customerContact?: string; selectedDomain?: string; xtreamConfig?: { username?: string }; hasLinePassword?: boolean; hasLinePin?: boolean; status?: string; isLifetime?: boolean; expiresAt?: string };
type Detail = { line: Line; history: Array<Record<string, any>> };
type EditForm = { customerName: string; customerContact: string; selectedDomain: string; username: string; password: string; currentPin: string; newPin: string };

export default function LineDirectoryPanel({ initialEditId, onLineUpdated }: { initialEditId?: string | null; onLineUpdated?: (line: Line) => void }) {
  const { user, userData } = useAuth();
  const [lines, setLines] = useState<Line[]>([]);
  const [search, setSearch] = useState('');
  const [detail, setDetail] = useState<Detail | null>(null);
  const [form, setForm] = useState<EditForm | null>(null);
  const [editing, setEditing] = useState(false);
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState('');
  const [notice, setNotice] = useState('');
  const domains = [...new Set([...(userData?.assignedDomains || []), ...(userData?.customDomains || [])])];
  const fieldClass = 'mt-1 w-full rounded-xl border border-gray-700 bg-gray-950 px-3 py-2.5 text-sm text-white outline-none focus:border-blue-500';

  const authFetch = useCallback(async (url: string, options: RequestInit = {}) => fetch(url, {
    ...options, headers: { Authorization: `Bearer ${await user?.getIdToken()}`, ...options.headers }, cache: 'no-store'
  }), [user]);

  const loadLines = useCallback(async () => {
    if (!user) return;
    try {
      const response = await authFetch('/api/lines');
      const body = await response.json();
      if (!response.ok) throw new Error(body.error || 'Linije se ne mogu učitati.');
      setLines(body.lines || []);
    } catch (cause) { setError(cause instanceof Error ? cause.message : 'Linije se ne mogu učitati.'); }
    finally { setLoading(false); }
  }, [authFetch, user]);
  useEffect(() => { void loadLines(); }, [loadLines]);

  const open = useCallback(async (id: string, edit = false) => {
    setError(''); setNotice('');
    try {
      const response = await authFetch(`/api/lines?id=${encodeURIComponent(id)}`);
      const body = await response.json();
      if (!response.ok) throw new Error(body.error || 'Linija se ne može učitati.');
      setDetail(body);
      setForm({ customerName: body.line.customerName || '', customerContact: body.line.customerContact || '', selectedDomain: body.line.selectedDomain || '', username: body.line.xtreamConfig?.username || '', password: '', currentPin: '', newPin: '' });
      setEditing(edit);
    } catch (cause) { setError(cause instanceof Error ? cause.message : 'Linija se ne može učitati.'); }
  }, [authFetch]);
  useEffect(() => { if (initialEditId && user) void open(initialEditId, true); }, [initialEditId, open, user]);

  const save = async (event: React.FormEvent) => {
    event.preventDefault();
    if (!detail || !form || saving) return;
    setSaving(true); setError(''); setNotice('');
    try {
      const response = await authFetch('/api/lines', { method: 'PATCH', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify({ id: detail.line.id, ...form, newPin: form.newPin || undefined }) });
      const body = await response.json();
      if (!response.ok) throw new Error(body.error || 'Promjene nisu spremljene.');
      await loadLines();
      await open(detail.line.id);
      onLineUpdated?.(body.line);
      setNotice(body.changedFields?.length ? 'Promjene linije su spremljene.' : 'Nema novih promjena.');
    } catch (cause) { setError(cause instanceof Error ? cause.message : 'Promjene nisu spremljene.'); }
    finally { setSaving(false); }
  };
  const visible = lines.filter(line => [line.deviceId, line.customerName, line.customerContact, line.selectedDomain].some(value => String(value || '').toLowerCase().includes(search.toLowerCase())));

  return <div className="space-y-5">
    <div className="rounded-3xl border border-gray-700/60 bg-gray-900/60 p-6">
      <div className="flex flex-col gap-4 sm:flex-row sm:items-center sm:justify-between"><div><h2 className="text-2xl font-bold text-white">Pretplatničke linije</h2><p className="mt-1 text-sm text-gray-400">Pregled, uređivanje i povijest promjena.</p></div><label className="flex items-center rounded-xl border border-gray-700 bg-gray-950 px-3"><Search className="h-4 w-4 text-gray-500"/><input value={search} onChange={event => setSearch(event.target.value)} placeholder="Device ID, kupac ili domena" className="w-full bg-transparent p-3 text-sm text-white outline-none sm:w-64"/></label></div>
      {error && !detail && <p role="alert" className="mt-4 text-sm text-red-300">{error}</p>}
      <div className="mt-5 overflow-x-auto"><table className="w-full text-left text-sm"><thead className="text-gray-500"><tr><th className="p-3">Device ID</th><th className="p-3">Kupac</th><th className="p-3">Status</th><th className="p-3">Ističe</th><th className="p-3"></th></tr></thead><tbody>
        {visible.map(line => <tr key={line.id} className="border-t border-gray-800 text-gray-300"><td className="p-3 font-mono text-blue-300">{line.deviceId || line.id}</td><td className="p-3">{line.customerName || '—'}</td><td className="p-3">{line.isLifetime ? 'Trajna' : line.status}</td><td className="p-3">{line.isLifetime ? 'Nikada' : line.expiresAt ? new Date(line.expiresAt).toLocaleDateString('hr-HR') : '—'}</td><td className="space-x-2 whitespace-nowrap p-3 text-right"><button onClick={() => open(line.id)} className="rounded-lg border border-gray-600 px-3 py-2 font-semibold text-white hover:bg-gray-800">Detalji</button><button onClick={() => open(line.id, true)} className="inline-flex items-center gap-1 rounded-lg bg-blue-600 px-3 py-2 font-semibold text-white hover:bg-blue-500"><Pencil className="h-3.5 w-3.5"/> Uredi</button></td></tr>)}
        {!loading && !visible.length && <tr><td colSpan={5} className="p-10 text-center text-gray-500">Nema pronađenih linija.</td></tr>}
      </tbody></table></div>
    </div>
    {detail && <div className="fixed inset-0 z-[70] flex items-center justify-center bg-black/80 p-4 backdrop-blur-sm"><div className="max-h-[90vh] w-full max-w-3xl overflow-y-auto rounded-3xl border border-gray-700 bg-gray-900 p-6 shadow-2xl">
      <div className="flex items-start justify-between gap-3"><div><h3 className="flex items-center gap-2 text-xl font-bold text-white"><Monitor className="text-blue-400"/> {detail.line.deviceId || detail.line.id}</h3><p className="text-sm text-gray-400">{detail.line.customerName || 'Bez imena kupca'} · {detail.line.selectedDomain || 'Bez domene'}</p></div><button aria-label="Zatvori" onClick={() => { setDetail(null); setEditing(false); setError(''); }}><X className="text-gray-400"/></button></div>
      {notice && <p role="status" className="mt-4 rounded-lg bg-green-900/30 p-3 text-sm text-green-300">{notice}</p>}
      {error && <p role="alert" className="mt-4 rounded-lg bg-red-900/30 p-3 text-sm text-red-300">{error}</p>}
      <div className="mt-5 grid grid-cols-2 gap-3 text-sm sm:grid-cols-4">{[['Status', detail.line.status], ['Licenca', detail.line.isLifetime ? 'Trajna' : 'Vremenska'], ['Ističe', detail.line.isLifetime ? 'Nikada' : detail.line.expiresAt ? new Date(detail.line.expiresAt).toLocaleString('hr-HR') : '—'], ['Vlasnik', detail.line.resellerId]].map(([label, value]) => <div key={label} className="rounded-xl bg-gray-800 p-3"><p className="text-gray-500">{label}</p><p className="mt-1 break-all font-semibold text-white">{value || '—'}</p></div>)}</div>
      {!editing && <button onClick={() => setEditing(true)} className="mt-5 inline-flex items-center gap-2 rounded-xl bg-blue-600 px-4 py-2.5 font-semibold text-white hover:bg-blue-500"><Pencil className="h-4 w-4"/> Uredi liniju</button>}
      {editing && form && <form onSubmit={save} className="mt-5 space-y-4 rounded-2xl border border-gray-700 bg-gray-800/50 p-4"><h4 className="font-semibold text-white">Uredi liniju</h4><div className="grid gap-4 sm:grid-cols-2">
        <label className="text-sm text-gray-300">Ime kupca<input value={form.customerName} maxLength={100} onChange={event => setForm({ ...form, customerName: event.target.value })} className={fieldClass}/></label>
        <label className="text-sm text-gray-300">Kontakt<input value={form.customerContact} maxLength={100} onChange={event => setForm({ ...form, customerContact: event.target.value })} className={fieldClass}/></label>
        <label className="text-sm text-gray-300">Domena{userData?.role === 'admin' ? <input required type="url" value={form.selectedDomain} onChange={event => setForm({ ...form, selectedDomain: event.target.value })} className={fieldClass}/> : <select required value={form.selectedDomain} onChange={event => setForm({ ...form, selectedDomain: event.target.value })} className={fieldClass}><option value="" disabled>Odaberite domenu</option>{domains.map(domain => <option key={domain} value={domain}>{domain}</option>)}</select>}</label>
        <label className="text-sm text-gray-300">Korisničko ime linije<input required value={form.username} maxLength={100} onChange={event => setForm({ ...form, username: event.target.value })} className={fieldClass}/></label>
        <label className="text-sm text-gray-300">Lozinka linije<input type="password" autoComplete="new-password" value={form.password} maxLength={100} placeholder={detail.line.hasLinePassword ? 'Prazno = zadrži postojeću' : 'Unesite lozinku'} required={!detail.line.hasLinePassword} onChange={event => setForm({ ...form, password: event.target.value })} className={fieldClass}/></label>
        {userData?.role !== 'admin' && (detail.line.hasLinePin ? <label className="text-sm text-gray-300">Trenutni PIN linije<input required type="password" autoComplete="off" value={form.currentPin} maxLength={32} onChange={event => setForm({ ...form, currentPin: event.target.value })} className={fieldClass}/></label> : <label className="text-sm text-gray-300">Novi PIN za staru liniju<input required type="password" autoComplete="new-password" value={form.newPin} minLength={6} maxLength={32} onChange={event => setForm({ ...form, newPin: event.target.value })} className={fieldClass}/></label>)}
      </div><p className="text-xs text-gray-400">Spremanje podataka linije ne mijenja trajanje licence ni stanje kredita. Prazna lozinka zadržava postojeću.</p><div className="flex flex-wrap gap-2"><button disabled={saving} type="submit" className="rounded-xl bg-blue-600 px-4 py-2.5 font-semibold text-white disabled:opacity-50">{saving ? 'Spremanje…' : 'Spremi promjene'}</button><button type="button" onClick={() => { setEditing(false); setError(''); }} className="rounded-xl border border-gray-600 px-4 py-2.5 text-gray-200">Odustani</button></div></form>}
      <h4 className="mt-6 flex items-center gap-2 font-bold text-white"><History className="h-4 w-4"/> Povijest promjena</h4><div className="mt-3 space-y-2">{detail.history.map(history => <div key={`${history.source}-${history.id}`} className="rounded-xl border border-gray-800 bg-gray-950/60 p-3"><div className="flex justify-between gap-3"><b className="text-sm text-blue-300">{history.action || history.type || history.source}</b><span className="text-xs text-gray-500">{history.timestamp ? new Date(history.timestamp).toLocaleString('hr-HR') : '—'}</span></div><p className="mt-1 text-sm text-gray-300">{typeof history.details === 'string' ? history.details : JSON.stringify(history.details || {})}</p></div>)}{!detail.history.length && <p className="text-sm text-gray-500">Nema ranijih promjena.</p>}</div>
    </div></div>}
  </div>;
}
