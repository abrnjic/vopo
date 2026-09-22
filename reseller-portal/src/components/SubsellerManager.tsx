"use client";

import { FormEvent, useCallback, useEffect, useState } from 'react';
import { Ban, CheckCircle2, KeyRound, Plus, RefreshCw, Save, Users } from 'lucide-react';
import { useAuth } from '@/context/AuthContext';

type Subseller = {
  uid: string;
  email: string;
  credits: number;
  status: 'active' | 'suspended';
  assignedDomains: string[];
  createdAt: string | null;
};

type Props = {
  availableDomains: string[];
  parentCredits: number;
  onParentCreditsChange: (credits: number) => void;
};

export default function SubsellerManager({ availableDomains, parentCredits, onParentCreditsChange }: Props) {
  const { user } = useAuth();
  const [subsellers, setSubsellers] = useState<Subseller[]>([]);
  const [loading, setLoading] = useState(true);
  const [busyId, setBusyId] = useState('');
  const [message, setMessage] = useState<{ kind: 'success' | 'error'; text: string } | null>(null);
  const [showCreate, setShowCreate] = useState(false);
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [initialCredits, setInitialCredits] = useState(0);
  const [newDomains, setNewDomains] = useState<string[]>([]);
  const [creditDrafts, setCreditDrafts] = useState<Record<string, number>>({});
  const [reasons, setReasons] = useState<Record<string, string>>({});
  const [domainDrafts, setDomainDrafts] = useState<Record<string, string[]>>({});

  const request = useCallback(async (method: string, body?: unknown) => {
    if (!user) throw new Error('Niste prijavljeni.');
    const token = await user.getIdToken();
    const response = await fetch('/api/reseller/subsellers', {
      method,
      headers: { Authorization: `Bearer ${token}`, ...(body ? { 'Content-Type': 'application/json' } : {}) },
      body: body ? JSON.stringify(body) : undefined,
      cache: 'no-store',
    });
    const payload = await response.json();
    if (!response.ok) throw new Error(payload.error || 'Zahtjev nije uspio.');
    return payload;
  }, [user]);

  const load = useCallback(async () => {
    setLoading(true);
    try {
      const data = await request('GET');
      setSubsellers(data.subsellers || []);
      onParentCreditsChange(data.parentCredits ?? 0);
      setCreditDrafts(Object.fromEntries((data.subsellers || []).map((item: Subseller) => [item.uid, item.credits])));
      setDomainDrafts(Object.fromEntries((data.subsellers || []).map((item: Subseller) => [item.uid, item.assignedDomains])));
    } catch (error) {
      setMessage({ kind: 'error', text: error instanceof Error ? error.message : 'Dohvat subsellera nije uspio.' });
    } finally {
      setLoading(false);
    }
  }, [request, onParentCreditsChange]);

  useEffect(() => { void load(); }, [load]);

  const createSubseller = async (event: FormEvent) => {
    event.preventDefault();
    setBusyId('create');
    setMessage(null);
    try {
      const data = await request('POST', { email, password, credits: initialCredits, assignedDomains: newDomains });
      onParentCreditsChange(data.parentCredits);
      setEmail(''); setPassword(''); setInitialCredits(0); setNewDomains([]); setShowCreate(false);
      setMessage({ kind: 'success', text: 'Subseller račun je kreiran, a početni krediti su preneseni.' });
      await load();
    } catch (error) {
      setMessage({ kind: 'error', text: error instanceof Error ? error.message : 'Kreiranje nije uspjelo.' });
    } finally { setBusyId(''); }
  };

  const update = async (uid: string, body: unknown, success: string) => {
    setBusyId(uid);
    setMessage(null);
    try {
      const data = await request('PATCH', body);
      onParentCreditsChange(data.parentCredits);
      setMessage({ kind: 'success', text: success });
      await load();
    } catch (error) {
      setMessage({ kind: 'error', text: error instanceof Error ? error.message : 'Izmjena nije uspjela.' });
    } finally { setBusyId(''); }
  };

  const toggleDomain = (uid: string, domain: string) => {
    setDomainDrafts(current => {
      const list = current[uid] || [];
      return { ...current, [uid]: list.includes(domain) ? list.filter(item => item !== domain) : [...list, domain] };
    });
  };

  return <div className="space-y-6">
    <div className="flex flex-wrap items-center justify-between gap-3">
      <div>
        <h2 className="text-xl font-bold text-white flex items-center"><Users className="w-5 h-5 mr-2 text-blue-400" />Subseller računi</h2>
        <p className="text-sm text-gray-400 mt-1">Dodijeljeni krediti skidaju se s vašeg balansa. Smanjenje stanja vraća razliku vama.</p>
      </div>
      <div className="flex gap-2">
        <button onClick={() => void load()} className="p-2.5 rounded-lg bg-gray-800 border border-gray-700 text-gray-300" title="Osvježi"><RefreshCw className="w-4 h-4" /></button>
        <button onClick={() => setShowCreate(value => !value)} className="px-4 py-2.5 rounded-lg bg-blue-600 hover:bg-blue-500 text-white font-semibold flex items-center"><Plus className="w-4 h-4 mr-2" />Novi subseller</button>
      </div>
    </div>

    {message && <div className={`rounded-lg border p-3 text-sm ${message.kind === 'success' ? 'border-green-700 bg-green-950/40 text-green-300' : 'border-red-700 bg-red-950/40 text-red-300'}`}>{message.text}</div>}

    {showCreate && <form onSubmit={createSubseller} className="bg-gray-800 rounded-xl border border-gray-700 p-5 space-y-4">
      <h3 className="font-bold text-white">Kreiraj novi račun</h3>
      <div className="grid md:grid-cols-3 gap-3">
        <input type="email" required value={email} onChange={e => setEmail(e.target.value)} placeholder="Email adresa" className="bg-gray-900 border border-gray-600 rounded-lg p-2.5 text-white" />
        <input type="password" required minLength={6} value={password} onChange={e => setPassword(e.target.value)} placeholder="Početna lozinka" className="bg-gray-900 border border-gray-600 rounded-lg p-2.5 text-white" />
        <input type="number" required min={0} max={parentCredits} value={initialCredits} onChange={e => setInitialCredits(Number(e.target.value))} placeholder="Početni krediti" className="bg-gray-900 border border-gray-600 rounded-lg p-2.5 text-white" />
      </div>
      <div>
        <p className="text-sm text-gray-300 mb-2">Domene koje subseller smije koristiti</p>
        <div className="flex flex-wrap gap-2">{availableDomains.map(domain => <label key={domain} className="flex items-center gap-2 px-3 py-2 bg-gray-900 border border-gray-700 rounded-lg text-sm text-gray-200"><input type="checkbox" checked={newDomains.includes(domain)} onChange={() => setNewDomains(list => list.includes(domain) ? list.filter(item => item !== domain) : [...list, domain])} />{domain}</label>)}</div>
      </div>
      <button disabled={busyId === 'create'} className="px-4 py-2.5 rounded-lg bg-green-600 hover:bg-green-500 disabled:opacity-50 text-white font-semibold flex items-center"><KeyRound className="w-4 h-4 mr-2" />{busyId === 'create' ? 'Kreiranje...' : 'Kreiraj i prenesi kredite'}</button>
    </form>}

    {loading ? <div className="text-gray-400 py-10 text-center">Učitavanje subsellera...</div> : subsellers.length === 0 ? <div className="bg-gray-800 rounded-xl border border-gray-700 p-10 text-center text-gray-400">Još nema kreiranih subseller računa.</div> : <div className="grid xl:grid-cols-2 gap-4">
      {subsellers.map(item => <div key={item.uid} className="bg-gray-800 rounded-xl border border-gray-700 p-5 space-y-5">
        <div className="flex items-start justify-between gap-3">
          <div><div className="font-semibold text-white">{item.email}</div><div className="text-xs text-gray-500 font-mono mt-1">{item.uid}</div></div>
          <span className={`px-2.5 py-1 rounded-full text-xs font-bold ${item.status === 'active' ? 'bg-green-950 text-green-300' : 'bg-red-950 text-red-300'}`}>{item.status === 'active' ? 'AKTIVAN' : 'SUSPENDIRAN'}</span>
        </div>
        <div className="space-y-2">
          <label className="text-sm text-gray-300">Stanje kredita i razlog korekcije</label>
          <div className="grid grid-cols-[100px_1fr_auto] gap-2">
            <input type="number" min={0} value={creditDrafts[item.uid] ?? item.credits} onChange={e => setCreditDrafts(v => ({ ...v, [item.uid]: Number(e.target.value) }))} className="bg-gray-900 border border-gray-600 rounded-lg p-2 text-white" />
            <input value={reasons[item.uid] || ''} onChange={e => setReasons(v => ({ ...v, [item.uid]: e.target.value }))} placeholder="Razlog promjene (obavezno)" className="bg-gray-900 border border-gray-600 rounded-lg p-2 text-white" />
            <button disabled={busyId === item.uid || (reasons[item.uid] || '').trim().length < 3} onClick={() => void update(item.uid, { action: 'adjust_credits', targetUserId: item.uid, newCredits: creditDrafts[item.uid] ?? item.credits, reason: reasons[item.uid] }, 'Stanje kredita je korigirano i zabilježeno.')} className="p-2.5 bg-blue-600 rounded-lg text-white disabled:opacity-40" title="Spremi kredite"><Save className="w-4 h-4" /></button>
          </div>
        </div>
        <div className="space-y-2">
          <label className="text-sm text-gray-300">Dodijeljene domene</label>
          <div className="flex flex-wrap gap-2">{availableDomains.map(domain => <label key={domain} className="flex items-center gap-2 text-xs px-2.5 py-2 bg-gray-900 border border-gray-700 rounded-lg text-gray-200"><input type="checkbox" checked={(domainDrafts[item.uid] || []).includes(domain)} onChange={() => toggleDomain(item.uid, domain)} />{domain}</label>)}</div>
          <button disabled={busyId === item.uid} onClick={() => void update(item.uid, { action: 'set_domains', targetUserId: item.uid, assignedDomains: domainDrafts[item.uid] || [] }, 'Domene subsellera su spremljene i zabilježene.')} className="px-3 py-2 bg-gray-700 hover:bg-gray-600 rounded-lg text-sm text-white flex items-center"><Save className="w-4 h-4 mr-2" />Spremi domene</button>
        </div>
        <button disabled={busyId === item.uid} onClick={() => void update(item.uid, { action: 'set_status', targetUserId: item.uid, status: item.status === 'active' ? 'suspended' : 'active' }, item.status === 'active' ? 'Subseller je suspendiran.' : 'Subseller je ponovno aktiviran.')} className={`px-3 py-2 rounded-lg text-sm font-semibold flex items-center ${item.status === 'active' ? 'bg-red-900/60 text-red-200 hover:bg-red-800' : 'bg-green-900/60 text-green-200 hover:bg-green-800'}`}>{item.status === 'active' ? <><Ban className="w-4 h-4 mr-2" />Suspendiraj račun</> : <><CheckCircle2 className="w-4 h-4 mr-2" />Aktiviraj račun</>}</button>
      </div>)}
    </div>}
  </div>;
}
