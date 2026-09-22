"use client";

import { FormEvent, useState } from 'react';
import { ArrowRightLeft, KeyRound, Radio, X } from 'lucide-react';
import { useAuth } from '@/context/AuthContext';

type TransferMode = 'license_only' | 'license_and_line';

type Props = {
  onTransferred?: (oldDeviceId: string, license: Record<string, unknown>) => void;
};

export default function LicenseTransferPanel({ onTransferred }: Props) {
  const { user } = useAuth();
  const [open, setOpen] = useState(false);
  const [oldDeviceId, setOldDeviceId] = useState('');
  const [newDeviceId, setNewDeviceId] = useState('');
  const [mode, setMode] = useState<TransferMode>('license_only');
  const [submitting, setSubmitting] = useState(false);
  const [message, setMessage] = useState<{ type: 'success' | 'error'; text: string } | null>(null);

  const submit = async (event: FormEvent) => {
    event.preventDefault();
    if (!user) return;
    setSubmitting(true);
    setMessage(null);
    try {
      const token = await user.getIdToken();
      const response = await fetch('/api/licenses/transfer', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${token}` },
        body: JSON.stringify({ requestId: crypto.randomUUID(), oldDeviceId: oldDeviceId.trim(), newDeviceId: newDeviceId.trim(), mode }),
      });
      const payload = await response.json();
      if (!response.ok) throw new Error(payload.error || 'Prijenos nije uspio.');
      setMessage({ type: 'success', text: `Licenca je prenesena na ${payload.newDeviceId}. Stari uređaj je deaktiviran.` });
      onTransferred?.(payload.oldDeviceId, payload.license);
      setOldDeviceId('');
      setNewDeviceId('');
      setOpen(false);
    } catch (error) {
      setMessage({ type: 'error', text: error instanceof Error ? error.message : 'Prijenos nije uspio.' });
    } finally {
      setSubmitting(false);
    }
  };

  return <div className="bg-gray-800/70 border border-gray-700 rounded-xl p-6 shadow-lg">
    <div className="flex flex-wrap items-center justify-between gap-4">
      <div>
        <h2 className="text-xl font-bold text-white flex items-center"><ArrowRightLeft className="w-5 h-5 mr-2 text-blue-400" />Migracija uređaja</h2>
        <p className="text-sm text-gray-400 mt-1">Prenesite postojeće trajanje licence na novi MAC / App ID bez nove naplate.</p>
      </div>
      <button onClick={() => { setOpen(true); setMessage(null); }} className="bg-blue-600 hover:bg-blue-500 text-white font-semibold px-4 py-2.5 rounded-lg flex items-center">
        <ArrowRightLeft className="w-4 h-4 mr-2" />Pokreni prijenos
      </button>
    </div>

    {message && <div className={`mt-4 rounded-lg border p-3 text-sm ${message.type === 'success' ? 'border-green-700 bg-green-950/40 text-green-300' : 'border-red-700 bg-red-950/40 text-red-300'}`}>{message.text}</div>}

    {open && <div className="fixed inset-0 z-50 bg-black/80 backdrop-blur-sm flex items-center justify-center p-4">
      <div className="w-full max-w-xl bg-gray-900 border border-gray-700 rounded-2xl shadow-2xl p-6 relative">
        <button type="button" onClick={() => setOpen(false)} className="absolute right-4 top-4 text-gray-400 hover:text-white" aria-label="Zatvori"><X className="w-5 h-5" /></button>
        <h3 className="text-2xl font-bold text-white">Prijenos licence</h3>
        <p className="text-gray-400 text-sm mt-1 mb-6">Datum isteka ostaje potpuno isti. Nakon prijenosa stari uređaj više neće imati aktivnu licencu.</p>
        <form onSubmit={submit} className="space-y-5">
          <div className="grid sm:grid-cols-2 gap-4">
            <div><label className="block text-sm text-gray-300 mb-1.5">Stari Device ID / MAC</label><input required maxLength={50} value={oldDeviceId} onChange={e => setOldDeviceId(e.target.value)} className="w-full bg-gray-950 border border-gray-600 rounded-lg p-3 text-white font-mono" /></div>
            <div><label className="block text-sm text-gray-300 mb-1.5">Novi Device ID / MAC</label><input required maxLength={50} value={newDeviceId} onChange={e => setNewDeviceId(e.target.value)} className="w-full bg-gray-950 border border-gray-600 rounded-lg p-3 text-white font-mono" /></div>
          </div>
          <fieldset className="space-y-3">
            <legend className="text-sm font-semibold text-gray-300 mb-2">Odaberite sadržaj prijenosa</legend>
            <label className={`block rounded-xl border p-4 cursor-pointer ${mode === 'license_only' ? 'border-blue-500 bg-blue-950/30' : 'border-gray-700 bg-gray-800/50'}`}>
              <input type="radio" name="transferMode" value="license_only" checked={mode === 'license_only'} onChange={() => setMode('license_only')} className="sr-only" />
              <span className="flex items-center text-white font-semibold"><KeyRound className="w-5 h-5 mr-2 text-blue-400" />Samo licenca</span>
              <span className="block text-sm text-gray-400 mt-1">Prenosi status i preostalo trajanje. Postavke linije novog uređaja ostaju nepromijenjene.</span>
            </label>
            <label className={`block rounded-xl border p-4 cursor-pointer ${mode === 'license_and_line' ? 'border-blue-500 bg-blue-950/30' : 'border-gray-700 bg-gray-800/50'}`}>
              <input type="radio" name="transferMode" value="license_and_line" checked={mode === 'license_and_line'} onChange={() => setMode('license_and_line')} className="sr-only" />
              <span className="flex items-center text-white font-semibold"><Radio className="w-5 h-5 mr-2 text-purple-400" />Licenca i pretplatnička linija</span>
              <span className="block text-sm text-gray-400 mt-1">Prenosi i portal, korisničke podatke, domenu te podatke pretplatnika.</span>
            </label>
          </fieldset>
          {message?.type === 'error' && <div className="rounded-lg border border-red-700 bg-red-950/40 p-3 text-sm text-red-300">{message.text}</div>}
          <div className="flex justify-end gap-3 pt-2">
            <button type="button" onClick={() => setOpen(false)} className="px-4 py-2.5 rounded-lg bg-gray-700 text-gray-200">Odustani</button>
            <button disabled={submitting || oldDeviceId.trim() === newDeviceId.trim()} className="px-4 py-2.5 rounded-lg bg-blue-600 hover:bg-blue-500 disabled:opacity-40 text-white font-bold">{submitting ? 'Prijenos u tijeku...' : 'Potvrdi prijenos'}</button>
          </div>
        </form>
      </div>
    </div>}
  </div>;
}
