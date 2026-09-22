"use client";
import { useEffect, useState } from 'react';
import { Search, X, History, Monitor } from 'lucide-react';
import { useAuth } from '@/context/AuthContext';

export default function LineDirectoryPanel() {
  const { user } = useAuth();
  const [lines, setLines] = useState<any[]>([]);
  const [search, setSearch] = useState('');
  const [detail, setDetail] = useState<any>(null);
  const [loading, setLoading] = useState(true);
  const authFetch = async (url: string) => fetch(url, { headers: { Authorization: `Bearer ${await user?.getIdToken()}` }, cache: 'no-store' });
  useEffect(() => { if (user) authFetch('/api/lines').then(r => r.json()).then(d => setLines(d.lines || [])).finally(() => setLoading(false)); }, [user]);
  const open = async (id: string) => { const r = await authFetch(`/api/lines?id=${encodeURIComponent(id)}`); if (r.ok) setDetail(await r.json()); };
  const visible = lines.filter(l => [l.deviceId, l.customerName, l.customerContact, l.selectedDomain].some(v => String(v || '').toLowerCase().includes(search.toLowerCase())));
  return <div className="space-y-5">
    <div className="rounded-3xl border border-gray-700/60 bg-gray-900/60 p-6">
      <div className="flex flex-col gap-4 sm:flex-row sm:items-center sm:justify-between"><div><h2 className="text-2xl font-bold text-white">Pretplatničke linije</h2><p className="mt-1 text-sm text-gray-400">Detalji licence i potpuna povijest promjena.</p></div>
      <label className="flex items-center rounded-xl border border-gray-700 bg-gray-950 px-3"><Search className="h-4 w-4 text-gray-500"/><input value={search} onChange={e=>setSearch(e.target.value)} placeholder="Device ID, kupac ili domena" className="w-64 bg-transparent p-3 text-sm text-white outline-none"/></label></div>
      <div className="mt-5 overflow-x-auto"><table className="w-full text-left text-sm"><thead className="text-gray-500"><tr><th className="p-3">Device ID</th><th className="p-3">Kupac</th><th className="p-3">Status</th><th className="p-3">Ističe</th><th className="p-3"></th></tr></thead><tbody>
      {visible.map(l=><tr key={l.id} className="border-t border-gray-800 text-gray-300"><td className="p-3 font-mono text-blue-300">{l.deviceId||l.id}</td><td className="p-3">{l.customerName||'—'}</td><td className="p-3">{l.isLifetime?'Trajna':l.status}</td><td className="p-3">{l.isLifetime?'Nikada':l.expiresAt?new Date(l.expiresAt).toLocaleDateString('hr-HR'):'—'}</td><td className="p-3 text-right"><button onClick={()=>open(l.id)} className="rounded-lg bg-blue-600 px-3 py-2 font-semibold text-white hover:bg-blue-500">Detalji</button></td></tr>)}
      {!loading&&!visible.length&&<tr><td colSpan={5} className="p-10 text-center text-gray-500">Nema pronađenih linija.</td></tr>}</tbody></table></div>
    </div>
    {detail&&<div className="fixed inset-0 z-[70] flex items-center justify-center bg-black/80 p-4 backdrop-blur-sm"><div className="max-h-[90vh] w-full max-w-3xl overflow-y-auto rounded-3xl border border-gray-700 bg-gray-900 p-6 shadow-2xl"><div className="flex justify-between"><div><h3 className="flex items-center gap-2 text-xl font-bold text-white"><Monitor className="text-blue-400"/> {detail.line.deviceId||detail.line.id}</h3><p className="text-sm text-gray-400">{detail.line.customerName||'Bez imena kupca'} · {detail.line.selectedDomain||'Bez domene'}</p></div><button onClick={()=>setDetail(null)}><X className="text-gray-400"/></button></div>
    <div className="mt-5 grid grid-cols-2 gap-3 text-sm sm:grid-cols-4">{[['Status',detail.line.status],['Licenca',detail.line.isLifetime?'Trajna':'Vremenska'],['Ističe',detail.line.isLifetime?'Nikada':detail.line.expiresAt?new Date(detail.line.expiresAt).toLocaleString('hr-HR'):'—'],['Vlasnik',detail.line.resellerId]].map(([a,b])=><div key={a} className="rounded-xl bg-gray-800 p-3"><p className="text-gray-500">{a}</p><p className="mt-1 break-all font-semibold text-white">{b||'—'}</p></div>)}</div>
    <h4 className="mt-6 flex items-center gap-2 font-bold text-white"><History className="h-4 w-4"/> Povijest promjena</h4><div className="mt-3 space-y-2">{detail.history.map((h:any)=><div key={`${h.source}-${h.id}`} className="rounded-xl border border-gray-800 bg-gray-950/60 p-3"><div className="flex justify-between gap-3"><b className="text-sm text-blue-300">{h.action||h.type||h.source}</b><span className="text-xs text-gray-500">{h.timestamp?new Date(h.timestamp).toLocaleString('hr-HR'):'—'}</span></div><p className="mt-1 text-sm text-gray-300">{typeof h.details==='string'?h.details:JSON.stringify(h.details||{})}</p></div>)}{!detail.history.length&&<p className="text-sm text-gray-500">Nema ranijih promjena.</p>}</div></div></div>}
  </div>;
}
