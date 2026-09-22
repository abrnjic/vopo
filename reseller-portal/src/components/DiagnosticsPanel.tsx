'use client';

import { useCallback, useEffect, useMemo, useState } from 'react';
import { Activity, Gauge, HardDrive, Network, RefreshCw, Search, Smartphone, Wifi } from 'lucide-react';
import { useAuth } from '@/context/AuthContext';

type Diagnostic = {
  deviceId: string;
  resellerId: string;
  customerName: string;
  publicIp: string;
  appVersion: string;
  appVersionCode: number | null;
  androidVersion: string;
  deviceModel: string;
  connectionType: string;
  downloadMbps: number | null;
  speedMeasuredAt: string | null;
  availableStorageBytes: number | null;
  availableMemoryBytes: number | null;
  licenseStatus: string;
  lastSeenAt: string | null;
};

function dateLabel(value: string | null) {
  if (!value) return 'Nema podataka';
  return new Intl.DateTimeFormat('hr-HR', { dateStyle: 'medium', timeStyle: 'medium' }).format(new Date(value));
}

function bytesLabel(value: number | null) {
  if (value === null) return '—';
  return `${(value / 1_073_741_824).toFixed(1)} GB`;
}

export default function DiagnosticsPanel({ showReseller = false }: { showReseller?: boolean }) {
  const { user } = useAuth();
  const [items, setItems] = useState<Diagnostic[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [search, setSearch] = useState('');
  const [referenceNow, setReferenceNow] = useState(0);

  const load = useCallback(async () => {
    if (!user) return;
    setLoading(true);
    setError('');
    try {
      const token = await user.getIdToken();
      const response = await fetch('/api/diagnostics', {
        headers: { Authorization: `Bearer ${token}` },
        cache: 'no-store',
      });
      const payload = await response.json();
      if (!response.ok) throw new Error(payload.error || 'Dijagnostika nije dostupna.');
      setItems(payload.diagnostics || []);
      setReferenceNow(Date.now());
    } catch (cause) {
      setError(cause instanceof Error ? cause.message : 'Dijagnostika nije dostupna.');
    } finally {
      setLoading(false);
    }
  }, [user]);

  useEffect(() => { void load(); }, [load]);

  const filtered = useMemo(() => {
    const needle = search.trim().toLowerCase();
    if (!needle) return items;
    return items.filter(item => [item.deviceId, item.customerName, item.publicIp, item.deviceModel, item.resellerId]
      .some(value => value.toLowerCase().includes(needle)));
  }, [items, search]);

  const isOnline = (item: Diagnostic) => Boolean(item.lastSeenAt && referenceNow - Date.parse(item.lastSeenAt) < 30 * 60_000);
  const online = items.filter(isOnline).length;
  const tested = items.filter(item => item.downloadMbps !== null).length;

  return (
    <div className="space-y-6 animate-in fade-in duration-500">
      <div className="grid grid-cols-1 gap-4 md:grid-cols-3">
        <Summary icon={<Smartphone className="h-5 w-5" />} label="Uređaji s dijagnostikom" value={items.length} />
        <Summary icon={<Activity className="h-5 w-5" />} label="Aktivni zadnjih 30 min" value={online} />
        <Summary icon={<Gauge className="h-5 w-5" />} label="Izmjerena brzina" value={tested} />
      </div>

      <div className="rounded-3xl border border-gray-700/50 bg-gray-900/50 p-6 shadow-2xl">
        <div className="mb-6 flex flex-col gap-4 lg:flex-row lg:items-center lg:justify-between">
          <div>
            <h2 className="flex items-center text-2xl font-bold text-white"><Network className="mr-3 h-6 w-6 text-blue-400" />Dijagnostika uređaja</h2>
            <p className="mt-2 text-sm text-gray-400">IP adresu utvrđuje server. Brzina se prikazuje nakon testa u APK postavkama.</p>
          </div>
          <div className="flex gap-2">
            <label className="flex items-center rounded-xl border border-gray-700 bg-gray-950 px-3 text-gray-400">
              <Search className="h-4 w-4" />
              <input value={search} onChange={event => setSearch(event.target.value)} placeholder="Uređaj, korisnik ili IP" className="w-56 bg-transparent px-3 py-2 text-sm text-white outline-none" />
            </label>
            <button onClick={() => void load()} className="flex items-center rounded-xl border border-gray-700 bg-gray-800 px-4 py-2 text-sm font-semibold text-white hover:bg-gray-700">
              <RefreshCw className={`mr-2 h-4 w-4 ${loading ? 'animate-spin' : ''}`} />Osvježi
            </button>
          </div>
        </div>

        {error && <div className="rounded-xl border border-red-500/30 bg-red-500/10 p-4 text-red-300">{error}</div>}
        {!error && loading && <div className="py-16 text-center text-gray-400">Dohvaćanje dijagnostike...</div>}
        {!error && !loading && filtered.length === 0 && <div className="rounded-2xl border border-dashed border-gray-700 py-16 text-center text-gray-500">Još nema dijagnostičkih podataka. Pojavit će se nakon pokretanja nove APK verzije.</div>}
        {!error && !loading && filtered.length > 0 && (
          <div className="overflow-x-auto">
            <table className="w-full min-w-[1050px] text-left text-sm">
              <thead className="border-b border-gray-700 text-xs uppercase tracking-wide text-gray-500">
                <tr><th className="p-3">Uređaj / pretplatnik</th>{showReseller && <th className="p-3">Reseller</th>}<th className="p-3">Javna IP adresa</th><th className="p-3">Veza i brzina</th><th className="p-3">APK / Android</th><th className="p-3">Resursi</th><th className="p-3">Zadnja aktivnost</th></tr>
              </thead>
              <tbody className="divide-y divide-gray-800">
                {filtered.map(item => (
                  <tr key={item.deviceId} className="text-gray-300 hover:bg-gray-800/50">
                    <td className="p-3"><div className="font-mono font-bold text-white">{item.deviceId}</div><div className="mt-1 text-xs text-gray-500">{item.customerName || 'Bez imena'} · {item.licenseStatus}</div></td>
                    {showReseller && <td className="p-3 font-mono text-xs">{item.resellerId || 'Nije dodijeljen'}</td>}
                    <td className="p-3 font-mono font-semibold text-blue-300">{item.publicIp}</td>
                    <td className="p-3"><div className="flex items-center"><Wifi className="mr-2 h-4 w-4 text-cyan-400" />{item.connectionType}</div><div className="mt-1 font-semibold text-white">{item.downloadMbps === null ? 'Test nije pokrenut' : `${item.downloadMbps.toFixed(1)} Mbps`}</div><div className="text-xs text-gray-500">{item.speedMeasuredAt ? dateLabel(item.speedMeasuredAt) : ''}</div></td>
                    <td className="p-3"><div className="text-white">{item.appVersion || '—'}{item.appVersionCode !== null ? ` (${item.appVersionCode})` : ''}</div><div className="mt-1 text-xs text-gray-500">Android {item.androidVersion || '—'} · {item.deviceModel || '—'}</div></td>
                    <td className="p-3"><div className="flex items-center"><HardDrive className="mr-2 h-4 w-4" />{bytesLabel(item.availableStorageBytes)}</div><div className="mt-1 text-xs text-gray-500">RAM dostupno: {bytesLabel(item.availableMemoryBytes)}</div></td>
                    <td className="p-3"><div className={isOnline(item) ? 'font-semibold text-emerald-400' : 'text-gray-300'}>{dateLabel(item.lastSeenAt)}</div></td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
}

function Summary({ icon, label, value }: { icon: React.ReactNode; label: string; value: number }) {
  return <div className="rounded-2xl border border-gray-700/50 bg-gray-900/50 p-5"><div className="mb-3 flex items-center text-blue-400">{icon}<span className="ml-2 text-sm text-gray-400">{label}</span></div><div className="text-3xl font-bold text-white">{value}</div></div>;
}
