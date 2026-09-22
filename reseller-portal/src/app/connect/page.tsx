"use client";

import { useState } from 'react';
import { Tv, Upload, Shield, CheckCircle, AlertCircle } from 'lucide-react';
import { useAuth } from '../../context/AuthContext';
import ProtectedRoute from '../../components/ProtectedRoute';
import AdminLayout from '../../components/AdminLayout';

export default function ConnectPage() {
  return (
    <ProtectedRoute allowedRoles={['admin', 'reseller', 'subseller']}>
      <AdminLayout>
        <ConnectForm />
      </AdminLayout>
    </ProtectedRoute>
  );
}

function ConnectForm() {
  const { user, userData } = useAuth();
  const [deviceId, setDeviceId] = useState('');
  const [portalUrl, setPortalUrl] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [linePin, setLinePin] = useState('');
  const [submitted, setSubmitted] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const availableDomains = Array.from(new Set([
    ...(userData?.assignedDomains || []),
    ...(userData?.customDomains || []),
  ]));

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!user) return;
    setLoading(true);
    setError('');

    try {
      const token = await user.getIdToken();
      const response = await fetch('/api/connect', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          deviceId,
          portalUrl,
          username,
          password,
          linePin
        }),
      });

      const data = await response.json();

      if (!response.ok) {
        setError(data.error || 'Došlo je do pogreške. Molimo pokušajte ponovno.');
        setLoading(false);
        return;
      }

      setSubmitted(true);
    } catch (err) {
      console.error("Greška pri unosu linije:", err);
      setError('Došlo je do pogreške. Molimo pokušajte ponovno.');
    } finally {
      setLoading(false);
    }
  };

  if (submitted) {
    return (
      <div className="min-h-[calc(100vh-8rem)] bg-gray-900 flex flex-col justify-center items-center p-4 text-white rounded-2xl border border-gray-800">
        <CheckCircle className="w-16 h-16 text-green-500 mb-4" />
        <h1 className="text-3xl font-bold mb-2">Linija uspješno poslana!</h1>
        <p className="text-gray-400 text-center max-w-md">
          Vaša linija je poslana na uređaj <span className="font-bold text-white">{deviceId}</span>. Probno razdoblje traje 3 dana od prve registracije aplikacije. Molimo ponovno pokrenite VOPO aplikaciju na vašem televizoru.
        </p>
        <button
          onClick={() => {
            setSubmitted(false);
            setDeviceId('');
            setPortalUrl('');
            setUsername('');
            setPassword('');
            setLinePin('');
          }}
          className="mt-8 bg-blue-600 hover:bg-blue-700 px-6 py-2 rounded-lg font-medium transition-colors"
        >
          Dodaj novu liniju
        </button>
      </div>
    );
  }

  return (
    <div className="min-h-[calc(100vh-8rem)] bg-gray-900 flex flex-col justify-center py-12 sm:px-6 lg:px-8 text-white rounded-2xl border border-gray-800">
      <div className="sm:mx-auto sm:w-full sm:max-w-md">
        <div className="flex justify-center">
          <div className="w-16 h-16 bg-gradient-to-br from-blue-600 to-indigo-500 rounded-full flex items-center justify-center shadow-lg shadow-blue-500/30">
            <Tv className="w-8 h-8 text-white" />
          </div>
        </div>
        <h2 className="mt-6 text-center text-3xl font-extrabold text-white tracking-tight">
          Aktivacija Uređaja
        </h2>
        <p className="mt-2 text-center text-sm text-gray-400">
          Unesite Device ID s televizora kako biste povezali listu s probnim razdobljem
        </p>
      </div>

      <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
        <div className="bg-gray-800 py-8 px-4 shadow-xl sm:rounded-xl sm:px-10 border border-gray-700">
          <form className="space-y-5" onSubmit={handleSubmit}>

            {error && (
              <div className="bg-red-900/50 border border-red-500 text-red-200 p-3 rounded flex items-center text-sm">
                <AlertCircle className="w-5 h-5 mr-2 flex-shrink-0" />
                {error}
              </div>
            )}

            <div>
              <label htmlFor="deviceId" className="block text-sm font-medium text-gray-300">
                Device ID (Prikazan na TV-u)
              </label>
              <div className="mt-1">
                <input
                  id="deviceId"
                  name="deviceId"
                  type="text"
                  required
                  placeholder="npr. 1a2b-3c4d-5e6f"
                  className="appearance-none block w-full px-3 py-2 border border-gray-600 bg-gray-700/50 rounded-md shadow-sm placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 sm:text-sm text-white transition-all"
                  value={deviceId}
                  onChange={(e) => setDeviceId(e.target.value)}
                  disabled={loading}
                />
              </div>
            </div>

            <div>
              <label htmlFor="portalUrl" className="block text-sm font-medium text-gray-300">
                Xtream Portal URL
              </label>
              <div className="mt-1">
                {userData?.role === 'admin' ? (
                  <input
                    id="portalUrl"
                    name="portalUrl"
                    type="url"
                    required
                    placeholder="https://portal.example"
                    className="appearance-none block w-full px-3 py-2 border border-gray-600 bg-gray-700/50 rounded-md shadow-sm placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 sm:text-sm text-white transition-all"
                    value={portalUrl}
                    onChange={(e) => setPortalUrl(e.target.value)}
                    disabled={loading}
                  />
                ) : (
                  <select
                    id="portalUrl"
                    name="portalUrl"
                    required
                    className="appearance-none block w-full px-3 py-2 border border-gray-600 bg-gray-700/50 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 sm:text-sm text-white transition-all"
                    value={portalUrl}
                    onChange={(e) => setPortalUrl(e.target.value)}
                    disabled={loading}
                  >
                    <option value="" disabled>Odaberite dodijeljenu domenu</option>
                    {availableDomains.map(domain => <option key={domain} value={domain}>{domain}</option>)}
                  </select>
                )}
              </div>
              {userData?.role !== 'admin' && availableDomains.length === 0 && (
                <p className="mt-2 text-xs text-amber-300">Vašem računu još nije dodijeljena domena. Obratite se nadređenom reselleru ili administratoru.</p>
              )}
            </div>

            <div className="grid grid-cols-2 gap-4">
              <div>
                <label htmlFor="username" className="block text-sm font-medium text-gray-300">
                  Username
                </label>
                <div className="mt-1">
                  <input
                    id="username"
                    name="username"
                    type="text"
                    required
                    className="appearance-none block w-full px-3 py-2 border border-gray-600 bg-gray-700/50 rounded-md shadow-sm placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 sm:text-sm text-white transition-all"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    disabled={loading}
                  />
                </div>
              </div>

              <div>
                <label htmlFor="password" className="block text-sm font-medium text-gray-300">
                  Password
                </label>
                <div className="mt-1">
                  <input
                    id="password"
                    name="password"
                    type="password"
                    required
                    className="appearance-none block w-full px-3 py-2 border border-gray-600 bg-gray-700/50 rounded-md shadow-sm placeholder-gray-500 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-blue-500 sm:text-sm text-white transition-all"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    disabled={loading}
                  />
                </div>
              </div>
            </div>

            <div className="flex items-center text-xs text-gray-400 bg-gray-900/50 p-3 rounded-lg border border-gray-700">
              <Shield className="w-4 h-4 mr-2 text-green-500 flex-shrink-0" />
              Slanjem forme povezujete listu s postojećim trodnevnim probnim razdobljem uređaja.
            </div>

            <div>
              <label htmlFor="linePin" className="block text-sm font-medium text-gray-300">Dodatni PIN linije</label>
              <div className="mt-1 flex gap-2">
                <input id="linePin" type="text" required minLength={6} maxLength={32} value={linePin} onChange={e => setLinePin(e.target.value)} className="min-w-0 flex-1 rounded-md border border-gray-600 bg-gray-700/50 px-3 py-2 text-white" placeholder="6-32 znaka" />
                <button type="button" onClick={() => { const values = new Uint32Array(2); crypto.getRandomValues(values); setLinePin(`${String(values[0] % 10000).padStart(4, '0')}${String(values[1] % 10000).padStart(4, '0')}`); }} className="rounded-md border border-blue-500 px-3 text-sm text-blue-300">Generiraj</button>
              </div>
            </div>

            <div>
              <button
                type="submit"
                disabled={loading}
                className="w-full flex justify-center items-center py-2.5 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 focus:ring-offset-gray-900 transition-all disabled:opacity-50"
              >
                {loading ? (
                  <div className="w-5 h-5 border-2 border-white border-t-transparent rounded-full animate-spin"></div>
                ) : (
                  <>
                    <Upload className="w-5 h-5 mr-2" />
                    Poveži Liniju s Uređajem
                  </>
                )}
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
