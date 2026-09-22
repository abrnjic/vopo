"use client";

import { useCallback, useEffect, useState } from 'react';
import { KeyRound, RefreshCw, ShieldAlert } from 'lucide-react';
import { useAuth } from '../context/AuthContext';

type SecurityLog = {
  id: string;
  eventType: string;
  deviceId?: string;
  ip?: string;
  details?: string;
  timestamp?: string | null;
};

export default function SecurityPanel() {
  const { user, userData } = useAuth();
  const [logs, setLogs] = useState<SecurityLog[]>([]);
  const [loading, setLoading] = useState(false);
  const [deviceId, setDeviceId] = useState('');
  const [currentPin, setCurrentPin] = useState('');
  const [newPin, setNewPin] = useState('');
  const [saving, setSaving] = useState(false);
  const [message, setMessage] = useState('');

  const loadLogs = useCallback(async () => {
    if (!user) return;
    setLoading(true);
    try {
      const token = await user.getIdToken();
      const response = await fetch('/api/security/logs', { headers: { Authorization: `Bearer ${token}` } });
      const payload = await response.json();
      if (!response.ok) throw new Error(payload.error || 'Dohvat nije uspio.');
      setLogs(payload.logs || []);
    } catch (error) {
      setMessage(error instanceof Error ? error.message : 'Dohvat nije uspio.');
    } finally {
      setLoading(false);
    }
  }, [user]);

  useEffect(() => { void loadLogs(); }, [loadLogs]);

  function generatePin() {
    const bytes = new Uint32Array(2);
    crypto.getRandomValues(bytes);
    setNewPin(`${String(bytes[0] % 10000).padStart(4, '0')}${String(bytes[1] % 10000).padStart(4, '0')}`);
  }

  async function saveSecurity(resetDeviceBinding: boolean) {
    if (!user) return;
    setSaving(true);
    setMessage('');
    try {
      const token = await user.getIdToken();
      const body: Record<string, unknown> = { deviceId: deviceId.trim(), resetDeviceBinding };
      if (currentPin) body.currentPin = currentPin;
      if (newPin) body.newPin = newPin;
      const response = await fetch('/api/licenses/security', {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${token}` },
        body: JSON.stringify(body)
      });
      const payload = await response.json();
      if (!response.ok) throw new Error(payload.error || 'Promjena nije uspjela.');
      setMessage(resetDeviceBinding ? 'Vezanje uređaja je resetirano.' : 'PIN linije je promijenjen.');
      setCurrentPin('');
      setNewPin('');
      await loadLogs();
    } catch (error) {
      setMessage(error instanceof Error ? error.message : 'Promjena nije uspjela.');
    } finally {
      setSaving(false);
    }
  }

  return (
    <div className="space-y-6">
      <section className="rounded-2xl border border-gray-700 bg-gray-900/60 p-6">
        <h2 className="flex items-center text-xl font-bold text-white"><KeyRound className="mr-2 h-5 w-5 text-blue-400" />Zaštita linije</h2>
        <p className="mt-2 text-sm text-gray-400">Promijenite dodatni PIN ili službeno resetirajte vezanje instalacije. Nakon reseta prva sljedeća VOPO instalacija koja se registrira za taj Device ID postaje ovlaštena.</p>
        <div className="mt-5 grid gap-3 md:grid-cols-3">
          <input value={deviceId} onChange={e => setDeviceId(e.target.value)} placeholder="Device ID" className="rounded-lg border border-gray-600 bg-gray-950 p-3 text-white" />
          {userData?.role !== 'admin' && <input type="password" value={currentPin} onChange={e => setCurrentPin(e.target.value)} placeholder="Postojeći PIN" className="rounded-lg border border-gray-600 bg-gray-950 p-3 text-white" />}
          <div className="flex gap-2">
            <input type="text" value={newPin} onChange={e => setNewPin(e.target.value)} placeholder="Novi PIN (6-32)" className="min-w-0 flex-1 rounded-lg border border-gray-600 bg-gray-950 p-3 text-white" />
            <button type="button" onClick={generatePin} className="rounded-lg border border-blue-500 px-3 text-sm text-blue-300">Generiraj</button>
          </div>
        </div>
        <div className="mt-4 flex flex-wrap gap-3">
          <button disabled={saving || !deviceId || !newPin} onClick={() => void saveSecurity(false)} className="rounded-lg bg-blue-600 px-4 py-2 font-semibold text-white disabled:opacity-50">Spremi novi PIN</button>
          <button disabled={saving || !deviceId || (userData?.role !== 'admin' && !currentPin && !newPin)} onClick={() => window.confirm('Resetirati vezanje uređaja?') && void saveSecurity(true)} className="rounded-lg bg-amber-600 px-4 py-2 font-semibold text-white disabled:opacity-50">Resetiraj uređaj</button>
        </div>
        {message && <p className="mt-3 text-sm text-amber-200">{message}</p>}
      </section>

      <section className="rounded-2xl border border-gray-700 bg-gray-900/60 p-6">
        <div className="flex items-center justify-between">
          <h2 className="flex items-center text-xl font-bold text-white"><ShieldAlert className="mr-2 h-5 w-5 text-red-400" />Security Logs</h2>
          <button onClick={() => void loadLogs()} className="flex items-center rounded-lg border border-gray-600 px-3 py-2 text-sm text-gray-300"><RefreshCw className={`mr-2 h-4 w-4 ${loading ? 'animate-spin' : ''}`} />Osvježi</button>
        </div>
        <div className="mt-5 space-y-3">
          {logs.map(log => (
            <div key={log.id} className="grid gap-2 rounded-xl border border-gray-800 bg-gray-950/70 p-4 md:grid-cols-[190px_1fr_160px]">
              <div><div className="font-semibold text-red-300">{log.eventType}</div><div className="font-mono text-xs text-gray-400">{log.deviceId || '—'}</div></div>
              <div className="text-sm text-gray-300">{log.details || 'Sigurnosni događaj'}<div className="mt-1 font-mono text-xs text-gray-500">IP: {log.ip || 'unknown'}</div></div>
              <time className="text-xs text-gray-500">{log.timestamp ? new Date(log.timestamp).toLocaleString('hr-HR') : 'Upravo sada'}</time>
            </div>
          ))}
          {!loading && logs.length === 0 && <p className="py-8 text-center text-gray-500">Nema zabilježenih sigurnosnih incidenata.</p>}
        </div>
      </section>
    </div>
  );
}
