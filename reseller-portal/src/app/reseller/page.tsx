"use client";

import DomainManager from '../../components/DomainManager';
import { useState, useEffect, useMemo } from 'react';
import { Plus, List, CreditCard, Check, Settings, Send, Trash2, Activity, BarChart2, Network, Users, ArrowRightLeft, ShieldAlert, Headphones } from 'lucide-react';
import { useAuth } from '../../context/AuthContext';
import { doc, getDoc, collection, query, where, getDocs, limit } from 'firebase/firestore';
import { db } from '../../firebase';
import { format } from 'date-fns';
import { hr } from 'date-fns/locale';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, PieChart, Pie, Cell } from 'recharts';
import AdminLayout from '../../components/AdminLayout';
import ProtectedRoute from '../../components/ProtectedRoute';
import DiagnosticsPanel from '../../components/DiagnosticsPanel';
import CreditPricing from '../../components/CreditPricing';
import SubsellerManager from '../../components/SubsellerManager';
import LicenseTransferPanel from '../../components/LicenseTransferPanel';
import SecurityPanel from '../../components/SecurityPanel';
import LineDirectoryPanel from '../../components/LineDirectoryPanel';
import ReportsPanel from '../../components/ReportsPanel';
import SupportPanel from '../../components/SupportPanel';

export default function ResellerDashboard() {
  const { user, userData } = useAuth();
  const [activeTab, setActiveTab] = useState<'activate' | 'lines' | 'subsellers' | 'migration' | 'analytics' | 'reports' | 'support' | 'diagnostics' | 'security' | 'logs' | 'settings'>('activate');
  const [credits, setCredits] = useState<number>(userData?.credits || 0);
  const [assignedDomains, setAssignedDomains] = useState<string[]>(userData?.assignedDomains || []);
  const [customDomains, setCustomDomains] = useState<string[]>(userData?.customDomains || []);
  
  const [recentLines, setRecentLines] = useState<any[]>([]);
  const [lineToEdit, setLineToEdit] = useState<string | null>(null);

  // Activate Form State
  const [deviceId, setDeviceId] = useState('');
  const [selectedDomain, setSelectedDomain] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [linePin, setLinePin] = useState('');
  const [customerName, setCustomerName] = useState('');
  const [customerContact, setCustomerContact] = useState('');
  const [licenseType, setLicenseType] = useState<'1_year' | 'lifetime' | 'trial'>('1_year');
  const [isActivating, setIsActivating] = useState(false);
  const [activationNotice, setActivationNotice] = useState<{ type: 'success' | 'error'; text: string } | null>(null);

  // Analytics & Logs State
  const [analyticsData, setAnalyticsData] = useState<any>(null);
  const [logs, setLogs] = useState<any[]>([]);
  const [isLoadingAnalytics, setIsLoadingAnalytics] = useState(false);
  const [isLoadingLogs, setIsLoadingLogs] = useState(false);

  // Bulk Actions State
  const [selectedLines, setSelectedLines] = useState<string[]>([]);
  const [isProcessingBulk, setIsProcessingBulk] = useState(false);

  // Fetch data on load
  useEffect(() => {
    if (user) {
      const fetchUserData = async () => {
        const uDoc = await getDoc(doc(db, 'users', user.uid));
        if (uDoc.exists()) {
          setCredits(uDoc.data().credits || 0);
          setCustomDomains(uDoc.data().customDomains || []);
          setAssignedDomains(uDoc.data().assignedDomains || []);
        }
      };
      
      const fetchLinesAndAnalytics = async () => {
        setIsLoadingAnalytics(true);
        const q = query(collection(db, 'licenses'), where('resellerId', '==', user.uid));
        const snap = await getDocs(q);
        const lines: any[] = [];
        
        let activeCount = 0;
        let trialCount = 0;
        let expiredCount = 0;
        let lifetimeCount = 0;
        
        snap.forEach(doc => {
          const data = doc.data();
          lines.push({ id: doc.id, ...data });
          
          if (data.status === 'Active') activeCount++;
          if (data.status === 'Trial') trialCount++;
          if (data.status === 'Expired') expiredCount++;
          if (data.isLifetime) lifetimeCount++;
        });
        
        setRecentLines(lines.sort((a,b) => b.updatedAt?.toMillis() - a.updatedAt?.toMillis()));
        
        setAnalyticsData({
            total: lines.length,
            active: activeCount,
            trial: trialCount,
            expired: expiredCount,
            lifetime: lifetimeCount
        });
        setIsLoadingAnalytics(false);
      };

      const fetchLogs = async () => {
        setIsLoadingLogs(true);
        try {
          const logsRef = collection(db, 'activity_logs');
          const q = query(logsRef, where('userId', '==', user.uid), limit(100));
          const snap = await getDocs(q);
          const logsData = snap.docs
            .map(doc => ({ id: doc.id, ...doc.data() }))
            .sort((a: any, b: any) => {
              const aTime = a.timestamp?.toMillis?.() ?? 0;
              const bTime = b.timestamp?.toMillis?.() ?? 0;
              return bTime - aTime;
            })
            .slice(0, 50);
          setLogs(logsData);
        } catch (error) {
          console.error('Failed to load reseller activity logs:', error);
          setLogs([]);
        } finally {
          setIsLoadingLogs(false);
        }
      };

      fetchUserData();
      fetchLinesAndAnalytics();
      fetchLogs();
    }
  }, [user]);

  const allDomains = useMemo(() => [...new Set([...assignedDomains, ...customDomains])], [assignedDomains, customDomains]);
  
  // Set default domain if available
  useEffect(() => {
    if (!allDomains.includes(selectedDomain)) {
      setSelectedDomain(allDomains[0] || '');
    }
  }, [allDomains, selectedDomain]);

  const handleActivate = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!user) return;
    
    setActivationNotice(null);
    setIsActivating(true);
    try {
      const idToken = await user?.getIdToken();
      const res = await fetch('/api/reseller/activate', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${idToken}`
        },
        body: JSON.stringify({
          deviceId: deviceId.trim(),
          licenseType,
          customerName: customerName.trim(),
          customerContact: customerContact.trim(),
          username: username.trim(),
          password: password.trim(),
          linePin,
          selectedDomain
        })
      });

      const result = await res.json();
      if (!res.ok) throw new Error(result.error || 'Neuspješna aktivacija');

      setCredits(result.creditsRemaining);
      setActivationNotice({ type: 'success', text: result.message || 'Linija je uspješno spremljena.' });

      setDeviceId('');
      setUsername('');
      setPassword('');
      setLinePin('');
      setCustomerName('');
      setCustomerContact('');
      
      // Osvježi listu nedavnih
      setRecentLines(prev => [{ 
        id: deviceId.trim(), 
        deviceId: deviceId.trim(),
        resellerId: user.uid,
        status: result.status,
        isLifetime: result.isLifetime,
        selectedDomain,
        customerName: customerName,
        customerContact: customerContact
      }, ...prev.filter(l => l.id !== deviceId.trim())]);
    } catch (error) {
      console.error(error);
      setActivationNotice({ type: 'error', text: error instanceof Error ? error.message : 'Došlo je do greške.' });
    } finally {
      setIsActivating(false);
    }
  };

  const handleBulkExtend = async () => {
    if (!user || selectedLines.length === 0) return;
    
    // Provjera kredita (1 godina = 1 kredit po liniji)
    const totalCreditsNeeded = selectedLines.length;
    if (credits < totalCreditsNeeded) {
      alert(`Nemate dovoljno kredita! Potrebno: ${totalCreditsNeeded}, Imate: ${credits}`);
      return;
    }

    if (!window.confirm(`Jeste li sigurni da želite produžiti ${selectedLines.length} linija za 1 godinu? (Trošak: ${totalCreditsNeeded} kredita)`)) {
      return;
    }

    setIsProcessingBulk(true);
    try {
      const idToken = await user.getIdToken();
      const res = await fetch('/api/reseller/bulk-extend', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${idToken}` },
        body: JSON.stringify({ requestId: crypto.randomUUID(), licenseIds: selectedLines })
      });
      const result = await res.json();
      if (!res.ok) throw new Error(result.error || 'Masovno produženje nije uspjelo.');

      // Local state update
      setCredits(result.creditsRemaining);
      const expiryById = new Map<string, string>(result.extensions.map((item: { licenseId: string; expiresAt: string }) => [item.licenseId, item.expiresAt]));
      setRecentLines(prev => prev.map(l => {
          const nextExpiry = expiryById.get(l.id);
          if (nextExpiry) {
              const exp = new Date(nextExpiry);
              return { ...l, status: 'Active', isLifetime: false, expiresAt: { toDate: () => exp } };
          }
          return l;
      }));
      setSelectedLines([]);
      alert(`Uspješno produženo ${selectedLines.length} linija.`);
    } catch (error) {
      console.error(error);
      alert(error instanceof Error ? error.message : 'Došlo je do greške prilikom masovnog produženja.');
    } finally {
      setIsProcessingBulk(false);
    }
  };

  const handleBulkDelete = async () => {
    if (!user || selectedLines.length === 0) return;
    
    if (!window.confirm(`Jeste li sigurni da želite TRAJNO obrisati ${selectedLines.length} linija? Ova akcija se NE MOŽE poništiti!`)) {
      return;
    }

    setIsProcessingBulk(true);
    try {
      const idToken = await user.getIdToken();
      const res = await fetch('/api/reseller/bulk-delete', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${idToken}` },
        body: JSON.stringify({ requestId: crypto.randomUUID(), licenseIds: selectedLines })
      });
      const result = await res.json();
      if (!res.ok) throw new Error(result.error || 'Masovno brisanje nije uspjelo.');

      // Local state update
      const deletedIds = new Set<string>(result.deletedIds);
      setRecentLines(prev => prev.filter(l => !deletedIds.has(l.id)));
      setSelectedLines([]);
      alert(`Uspješno obrisano ${result.deletedIds.length} linija.`);
    } catch (error) {
      console.error(error);
      alert(error instanceof Error ? error.message : 'Došlo je do greške prilikom masovnog brisanja.');
    } finally {
      setIsProcessingBulk(false);
    }
  };

  const toggleLineSelection = (lineId: string) => {
    setSelectedLines(prev => 
      prev.includes(lineId) 
        ? prev.filter(id => id !== lineId)
        : [...prev, lineId]
    );
  };

  const toggleAllSelection = () => {
    if (selectedLines.length === recentLines.length) {
      setSelectedLines([]);
    } else {
      setSelectedLines(recentLines.map(l => l.id));
    }
  };

  return (
    <ProtectedRoute allowedRoles={['reseller', 'subseller']}>
      <AdminLayout>
        <div className="space-y-6">
          <div className="flex justify-between items-center bg-gray-800 p-6 rounded-xl border border-gray-700 shadow-lg">
        <div>
          <h1 className="text-2xl font-bold text-white">Reseller Dashboard</h1>
          <p className="text-gray-400 mt-1">Dobrodošli, {userData?.email}</p>
        </div>
        <div className="text-right bg-gray-900/50 px-6 py-3 rounded-lg border border-gray-700">
          <div className="text-3xl font-black text-orange-500">{credits}</div>
          <div className="text-xs uppercase tracking-wider text-gray-400 font-bold mt-1">Dostupnih Kredita</div>
        </div>
      </div>

      <CreditPricing />

      <div className="flex space-x-2 border-b border-gray-700 pb-px overflow-x-auto">
        <button 
          onClick={() => setActiveTab('activate')}
          className={`px-6 py-3 font-medium transition-all flex items-center border-b-2 whitespace-nowrap ${activeTab === 'activate' ? 'border-blue-500 text-blue-400' : 'border-transparent text-gray-400 hover:text-white'}`}
        >
          <CreditCard className="w-4 h-4 mr-2" />
          Upravljanje Linijama
        </button>
        {userData?.role === 'reseller' && <button
          onClick={() => setActiveTab('subsellers')}
          className={`px-6 py-3 font-medium transition-all flex items-center border-b-2 whitespace-nowrap ${activeTab === 'subsellers' ? 'border-blue-500 text-blue-400' : 'border-transparent text-gray-400 hover:text-white'}`}
        >
          <Users className="w-4 h-4 mr-2" />Subselleri
        </button>}
        <button onClick={() => { setLineToEdit(null); setActiveTab('lines'); }} className={`px-6 py-3 font-medium transition-all flex items-center border-b-2 whitespace-nowrap ${activeTab === 'lines' ? 'border-blue-500 text-blue-400' : 'border-transparent text-gray-400 hover:text-white'}`}><List className="w-4 h-4 mr-2" />Linije</button>
        <button
          onClick={() => setActiveTab('migration')}
          className={`px-6 py-3 font-medium transition-all flex items-center border-b-2 whitespace-nowrap ${activeTab === 'migration' ? 'border-blue-500 text-blue-400' : 'border-transparent text-gray-400 hover:text-white'}`}
        >
          <ArrowRightLeft className="w-4 h-4 mr-2" />Migracija
        </button>
        <button 
          onClick={() => setActiveTab('analytics')}
          className={`px-6 py-3 font-medium transition-all flex items-center border-b-2 whitespace-nowrap ${activeTab === 'analytics' ? 'border-blue-500 text-blue-400' : 'border-transparent text-gray-400 hover:text-white'}`}
        >
          <BarChart2 className="w-4 h-4 mr-2" />
          Moja Analitika
        </button>
          <button
            onClick={() => setActiveTab('diagnostics')}
            className={`px-6 py-3 font-medium transition-all flex items-center border-b-2 whitespace-nowrap ${activeTab === 'diagnostics' ? 'border-blue-500 text-blue-400' : 'border-transparent text-gray-400 hover:text-white'}`}
          >
            <Network className="w-4 h-4 mr-2" /> Dijagnostika
          </button>
          <button
            onClick={() => setActiveTab('logs')}
          className={`px-6 py-3 font-medium transition-all flex items-center border-b-2 whitespace-nowrap ${activeTab === 'logs' ? 'border-blue-500 text-blue-400' : 'border-transparent text-gray-400 hover:text-white'}`}
        >
          <Activity className="w-4 h-4 mr-2" />
          Aktivnosti
        </button>
        <button onClick={() => setActiveTab('security')} className={`px-6 py-3 font-medium transition-all flex items-center border-b-2 whitespace-nowrap ${activeTab === 'security' ? 'border-red-500 text-red-300' : 'border-transparent text-gray-400 hover:text-white'}`}>
          <ShieldAlert className="w-4 h-4 mr-2" /> Sigurnost
        </button>
        <button onClick={() => setActiveTab('reports')} className={`px-6 py-3 font-medium transition-all flex items-center border-b-2 whitespace-nowrap ${activeTab === 'reports' ? 'border-blue-500 text-blue-400' : 'border-transparent text-gray-400 hover:text-white'}`}><BarChart2 className="w-4 h-4 mr-2" />Izvještaji</button>
        <button onClick={() => setActiveTab('support')} className={`px-6 py-3 font-medium transition-all flex items-center border-b-2 whitespace-nowrap ${activeTab === 'support' ? 'border-blue-500 text-blue-400' : 'border-transparent text-gray-400 hover:text-white'}`}><Headphones className="w-4 h-4 mr-2" />Podrška</button>
        <button 
          onClick={() => setActiveTab('settings')}
          className={`px-6 py-3 font-medium transition-all flex items-center border-b-2 whitespace-nowrap ${activeTab === 'settings' ? 'border-blue-500 text-blue-400' : 'border-transparent text-gray-400 hover:text-white'}`}
        >
          <Settings className="w-4 h-4 mr-2" />
          Postavke Profila
        </button>
      </div>

      {activeTab === 'activate' && (
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
          {/* Forma za aktivaciju */}
          <div className="bg-gray-800 p-6 rounded-xl border border-gray-700 shadow-lg h-fit">
            <h2 className="text-xl font-bold mb-6 flex items-center text-white">
              <Plus className="w-5 h-5 mr-2 text-blue-500" />
              Nova Aktivacija / Linija
            </h2>
            {activationNotice && <div role={activationNotice.type === 'error' ? 'alert' : 'status'} className={`mb-5 rounded-lg border p-3 text-sm ${activationNotice.type === 'error' ? 'border-red-500/50 bg-red-900/20 text-red-200' : 'border-green-500/50 bg-green-900/20 text-green-200'}`}>{activationNotice.text}</div>}
            <form className="space-y-4" onSubmit={handleActivate}>
              <div>
                <label className="block text-sm font-medium text-gray-300 mb-1">Device ID</label>
                <input 
                  type="text" required
                  className="w-full bg-gray-900 border border-gray-600 rounded-lg p-2.5 text-white focus:ring-2 focus:ring-blue-500 outline-none transition-all" 
                  placeholder="Unesite ID korisnika s TV-a" 
                  value={deviceId} onChange={e => setDeviceId(e.target.value)}
                />
              </div>
              
              <div>
                <label className="block text-sm font-medium text-gray-300 mb-1">Odaberi Domenu</label>
                <select 
                  required
                  className="w-full bg-gray-900 border border-gray-600 rounded-lg p-2.5 text-white focus:ring-2 focus:ring-blue-500 outline-none transition-all"
                  value={selectedDomain} onChange={e => setSelectedDomain(e.target.value)}
                >
                  <option value="" disabled>-- Odaberite URL Portala --</option>
                  {allDomains.map((d, i) => <option key={i} value={d}>{d}</option>)}
                </select>
                {allDomains.length === 0 && (
                  <p className="text-xs text-red-400 mt-1">Nemate niti jednu domenu. Dodajte domenu u kartici "Upravljanje Domenama".</p>
                )}
              </div>

              <div className="grid grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-medium text-gray-300 mb-1">Ime i Prezime Kupca</label>
                  <input 
                    type="text"
                    className="w-full bg-gray-900 border border-gray-600 rounded-lg p-2.5 text-white focus:ring-2 focus:ring-blue-500 outline-none transition-all" 
                    placeholder="Opcionalno"
                    value={customerName} onChange={e => setCustomerName(e.target.value)}
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-300 mb-1">Kontakt (Mobitel/Email)</label>
                  <input 
                    type="text"
                    className="w-full bg-gray-900 border border-gray-600 rounded-lg p-2.5 text-white focus:ring-2 focus:ring-blue-500 outline-none transition-all" 
                    placeholder="Opcionalno"
                    value={customerContact} onChange={e => setCustomerContact(e.target.value)}
                  />
                </div>
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-300 mb-1">Dodatni PIN linije</label>
                <div className="flex gap-2">
                  <input type="text" required minLength={6} maxLength={32} pattern="[A-Za-z0-9!@#$%&amp;*_.-]{6,32}" value={linePin} onChange={e => setLinePin(e.target.value)} className="min-w-0 flex-1 bg-gray-900 border border-gray-600 rounded-lg p-2.5 text-white" placeholder="6-32 znaka" />
                  <button type="button" onClick={() => { const values = new Uint32Array(2); crypto.getRandomValues(values); setLinePin(`${String(values[0] % 10000).padStart(4, '0')}${String(values[1] % 10000).padStart(4, '0')}`); }} className="rounded-lg border border-blue-500 px-3 text-sm text-blue-300">Generiraj</button>
                </div>
                <p className="mt-1 text-xs text-gray-500">PIN se sprema kao sigurni hash i potreban je za kasnije promjene linije.</p>
              </div>

              <div className="grid grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-medium text-gray-300 mb-1">Username</label>
                  <input 
                    type="text" required
                    className="w-full bg-gray-900 border border-gray-600 rounded-lg p-2.5 text-white focus:ring-2 focus:ring-blue-500 outline-none transition-all" 
                    value={username} onChange={e => setUsername(e.target.value)}
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-300 mb-1">Password</label>
                  <input 
                    type="text" required
                    className="w-full bg-gray-900 border border-gray-600 rounded-lg p-2.5 text-white focus:ring-2 focus:ring-blue-500 outline-none transition-all" 
                    value={password} onChange={e => setPassword(e.target.value)}
                  />
                </div>
              </div>

              <div className="pt-4 mt-2 border-t border-gray-700">
                <label className="block text-sm font-medium text-gray-300 mb-3">Vrsta Aktivacije</label>
                <div className="space-y-3">
                  <label className={`flex items-center space-x-3 p-4 rounded-lg cursor-pointer border transition-all ${licenseType === 'trial' ? 'bg-blue-900/20 border-blue-500' : 'bg-gray-900 border-gray-700 hover:border-gray-500'}`}>
                    <input type="radio" name="license" checked={licenseType === 'trial'} onChange={() => setLicenseType('trial')} className="hidden" />
                    <div className={`w-5 h-5 rounded-full border-2 flex items-center justify-center ${licenseType === 'trial' ? 'border-blue-500' : 'border-gray-500'}`}>
                      {licenseType === 'trial' && <div className="w-2.5 h-2.5 bg-blue-500 rounded-full" />}
                    </div>
                    <div className="flex-1">
                      <div className="font-medium text-white">Probna Linija (Samo postavi)</div>
                      <div className="text-sm text-gray-400">Postavlja liniju u postojeće probno razdoblje od 3 dana; ne aktivira godišnju licencu. Troši <span className="font-bold text-green-400">0 kredita</span>.</div>
                    </div>
                  </label>

                  <label className={`flex items-center space-x-3 p-4 rounded-lg cursor-pointer border transition-all ${licenseType === '1_year' ? 'bg-blue-900/20 border-blue-500' : 'bg-gray-900 border-gray-700 hover:border-gray-500'}`}>
                    <input type="radio" name="license" checked={licenseType === '1_year'} onChange={() => setLicenseType('1_year')} className="hidden" />
                    <div className={`w-5 h-5 rounded-full border-2 flex items-center justify-center ${licenseType === '1_year' ? 'border-blue-500' : 'border-gray-500'}`}>
                      {licenseType === '1_year' && <div className="w-2.5 h-2.5 bg-blue-500 rounded-full" />}
                    </div>
                    <div className="flex-1">
                      <div className="font-medium text-white">1 Godina</div>
                      <div className="text-sm text-gray-400">Troši <span className="font-bold text-orange-400">1 kredit</span></div>
                    </div>
                  </label>

                  <label className={`flex items-center space-x-3 p-4 rounded-lg cursor-pointer border transition-all ${licenseType === 'lifetime' ? 'bg-blue-900/20 border-blue-500' : 'bg-gray-900 border-gray-700 hover:border-gray-500'}`}>
                    <input type="radio" name="license" checked={licenseType === 'lifetime'} onChange={() => setLicenseType('lifetime')} className="hidden" />
                    <div className={`w-5 h-5 rounded-full border-2 flex items-center justify-center ${licenseType === 'lifetime' ? 'border-blue-500' : 'border-gray-500'}`}>
                      {licenseType === 'lifetime' && <div className="w-2.5 h-2.5 bg-blue-500 rounded-full" />}
                    </div>
                    <div className="flex-1">
                      <div className="font-medium text-white">Trajno (Lifetime)</div>
                      <div className="text-sm text-gray-400">Troši <span className="font-bold text-orange-400">2 kredita</span></div>
                    </div>
                  </label>
                </div>
              </div>
              
              <button 
                type="submit" 
                disabled={isActivating || allDomains.length === 0}
                className="w-full bg-blue-600 hover:bg-blue-700 text-white font-bold py-3.5 px-4 rounded-lg mt-6 flex items-center justify-center shadow-lg shadow-blue-500/25 transition-all disabled:opacity-50"
              >
                <Send className="w-5 h-5 mr-2" />
                {isActivating ? 'Spremanje u tijeku...' : licenseType === 'trial' ? 'Pošalji probnu liniju (0 kredita)' : licenseType === 'lifetime' ? 'Aktiviraj trajno (2 kredita)' : 'Aktiviraj na 1 godinu (1 kredit)'}
              </button>
            </form>
          </div>
          
          {/* Nedavne aktivacije */}
          <div className="bg-gray-800 p-6 rounded-xl border border-gray-700 shadow-lg flex flex-col">
            <div className="flex justify-between items-center mb-6">
              <h2 className="text-xl font-bold flex items-center text-white">
                <List className="w-5 h-5 mr-2 text-blue-500" />
                Vaše Linije / Uređaji
              </h2>
              {recentLines.length > 0 && (
                <button 
                  onClick={toggleAllSelection}
                  className="text-sm text-blue-400 hover:text-blue-300 font-medium"
                >
                  {selectedLines.length === recentLines.length ? 'Odznači sve' : 'Označi sve'}
                </button>
              )}
            </div>

            {selectedLines.length > 0 && (
              <div className="bg-blue-900/20 border border-blue-500/50 rounded-lg p-3 mb-4 flex justify-between items-center animate-in fade-in slide-in-from-top-2">
                <span className="text-blue-400 font-medium text-sm">
                  Odabrano: {selectedLines.length}
                </span>
                <div className="flex space-x-2">
                  <button 
                    onClick={handleBulkExtend}
                    disabled={isProcessingBulk}
                    className="bg-blue-600 hover:bg-blue-500 text-white text-xs font-bold py-1.5 px-3 rounded flex items-center transition-colors disabled:opacity-50"
                  >
                    +1 Godina
                  </button>
                  <button 
                    onClick={handleBulkDelete}
                    disabled={isProcessingBulk}
                    className="bg-red-600/80 hover:bg-red-500 text-white text-xs font-bold py-1.5 px-3 rounded flex items-center transition-colors disabled:opacity-50"
                  >
                    <Trash2 className="w-3 h-3 mr-1" />
                    Obriši
                  </button>
                </div>
              </div>
            )}

            <div className="space-y-3 flex-1 overflow-y-auto pr-2 custom-scrollbar min-h-[300px]">
              {recentLines.map((line) => (
                <div 
                  key={line.id} 
                  onClick={() => toggleLineSelection(line.id)}
                  className={`flex flex-col gap-3 p-4 rounded-lg border transition-all cursor-pointer sm:flex-row sm:items-center sm:justify-between ${
                    selectedLines.includes(line.id) 
                      ? 'bg-blue-900/20 border-blue-500 shadow-[0_0_10px_rgba(59,130,246,0.2)]' 
                      : 'bg-gray-900/50 border-gray-700/50 hover:border-gray-600'
                  }`}
                >
                  <div className="flex w-full min-w-0 items-center sm:w-auto">
                    <div className={`w-5 h-5 rounded border mr-4 flex items-center justify-center transition-colors ${
                      selectedLines.includes(line.id) ? 'bg-blue-500 border-blue-500' : 'border-gray-500'
                    }`}>
                      {selectedLines.includes(line.id) && <Check className="w-3.5 h-3.5 text-white" />}
                    </div>
                    <div className="min-w-0">
                      <div className="break-all font-mono text-lg text-white">{line.id} {line.customerName && <span className="ml-2 text-sm text-gray-400">({line.customerName})</span>}</div>
                      <div className="mt-1 flex min-w-0 flex-wrap items-center gap-2 text-sm text-gray-400">
                        <span className={`px-2 py-0.5 rounded text-xs mr-2 font-medium ${line.status === 'Trial' ? 'bg-amber-900/50 text-amber-300' : line.isLifetime ? 'bg-purple-900/50 text-purple-400' : 'bg-blue-900/50 text-blue-400'}`}>
                          {line.status === 'Trial' ? '3 dana probno' : line.isLifetime ? 'Lifetime' : '1 Godina'}
                        </span>
                        <span className="break-all">{line.selectedDomain}</span>
                      </div>
                    </div>
                  </div>
                  <div className="flex w-full items-center justify-between gap-2 pl-9 sm:w-auto sm:justify-end sm:pl-0"><button type="button" onClick={event => { event.stopPropagation(); setLineToEdit(line.id); setActiveTab('lines'); }} className="shrink-0 rounded-lg border border-blue-500/50 px-3 py-1.5 text-sm font-semibold text-blue-300 hover:bg-blue-900/30">Uredi</button><div className={`flex shrink-0 items-center rounded-full border px-3 py-1 text-sm font-medium ${
                    line.status === 'Active' ? 'text-green-500 bg-green-900/20 border-green-900/50' : 
                    line.status === 'Trial' ? 'text-yellow-500 bg-yellow-900/20 border-yellow-900/50' : 
                    'text-red-500 bg-red-900/20 border-red-900/50'
                  }`}>
                    <Check className="w-4 h-4 mr-1" />
                    {line.status}
                  </div></div>
                </div>
              ))}
              {recentLines.length === 0 && (
                <div className="text-center text-gray-500 py-12">Nema aktiviranih linija.</div>
              )}
            </div>
          </div>
        </div>
      )}

      {activeTab === 'diagnostics' && <DiagnosticsPanel />}

      {activeTab === 'security' && <SecurityPanel />}

      {activeTab === 'lines' && <LineDirectoryPanel initialEditId={lineToEdit} onLineUpdated={line => setRecentLines(previous => previous.map(item => item.id === line.id ? { ...item, customerName: line.customerName, customerContact: line.customerContact, selectedDomain: line.selectedDomain } : item))} />}

      {activeTab === 'reports' && <ReportsPanel />}

      {activeTab === 'support' && <SupportPanel />}

      {activeTab === 'migration' && <LicenseTransferPanel onTransferred={(oldId, license) => {
        const migrated = license as any;
        const expiresAt = migrated.expiresAt ? new Date(migrated.expiresAt) : null;
        setRecentLines(previous => [
          { ...migrated, expiresAt: expiresAt ? { toDate: () => expiresAt } : null },
          ...previous.filter(line => line.id !== oldId && line.id !== migrated.id),
        ]);
        setSelectedLines(previous => previous.filter(id => id !== oldId));
      }} />}

      {activeTab === 'subsellers' && userData?.role === 'reseller' && (
        <SubsellerManager availableDomains={allDomains} parentCredits={credits} onParentCreditsChange={setCredits} />
      )}

      {activeTab === 'settings' && (
        <div className="space-y-6">
          <div className="bg-gray-800 p-6 rounded-xl border border-gray-700 max-w-3xl shadow-lg">
            <h2 className="text-xl font-bold mb-6 flex items-center text-white">
              <Settings className="w-5 h-5 mr-2 text-blue-500" />
              {userData?.role === 'subseller' ? 'Dodijeljene domene' : 'Moje Domene (Portal URL-ovi)'}
            </h2>
            {userData?.role === 'subseller' ? (
              <div className="space-y-2">
                {allDomains.map(domain => <div key={domain} className="bg-gray-900 border border-gray-700 rounded-lg p-3 text-gray-200">{domain}</div>)}
                {allDomains.length === 0 && <p className="text-gray-400">Glavni reseller još nije dodijelio domene.</p>}
              </div>
            ) : <DomainManager onChange={data => { setAssignedDomains(data.assignedDomains); setCustomDomains(data.customDomains); }} />}
          </div>
        </div>
      )}

      {activeTab === 'analytics' && (
        <div className="space-y-6">
          <div className="bg-gray-800 p-6 rounded-xl border border-gray-700 shadow-lg">
            <h2 className="text-xl font-bold mb-6 flex items-center text-white">
              <BarChart2 className="w-5 h-5 mr-2 text-blue-500" />
              Pregled Linija
            </h2>
            
            {isLoadingAnalytics ? (
              <div className="text-center text-gray-500 py-8">Učitavanje analitike...</div>
            ) : analyticsData ? (
              <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
                <div>
                  <h3 className="text-lg font-medium text-gray-300 mb-4 text-center">Status Linija</h3>
                  <div className="h-64">
                    <ResponsiveContainer width="100%" height="100%">
                      <PieChart>
                        <Pie
                          data={[
                            { name: 'Aktivne', value: analyticsData.active },
                            { name: 'Probne', value: analyticsData.trial },
                            { name: 'Istekle', value: analyticsData.expired }
                          ].filter(d => d.value > 0)}
                          cx="50%"
                          cy="50%"
                          innerRadius={60}
                          outerRadius={80}
                          paddingAngle={5}
                          dataKey="value"
                        >
                          <Cell fill="#3b82f6" /> {/* Blue for Active */}
                          <Cell fill="#eab308" /> {/* Yellow for Trial */}
                          <Cell fill="#ef4444" /> {/* Red for Expired */}
                        </Pie>
                        <Tooltip 
                          contentStyle={{ backgroundColor: '#1f2937', borderColor: '#374151', color: '#fff' }}
                          itemStyle={{ color: '#fff' }}
                        />
                      </PieChart>
                    </ResponsiveContainer>
                  </div>
                  <div className="flex justify-center space-x-4 mt-4">
                    <div className="flex items-center"><div className="w-3 h-3 bg-blue-500 rounded-full mr-2"></div><span className="text-gray-400 text-sm">Aktivne ({analyticsData.active})</span></div>
                    <div className="flex items-center"><div className="w-3 h-3 bg-yellow-500 rounded-full mr-2"></div><span className="text-gray-400 text-sm">Probne ({analyticsData.trial})</span></div>
                    <div className="flex items-center"><div className="w-3 h-3 bg-red-500 rounded-full mr-2"></div><span className="text-gray-400 text-sm">Istekle ({analyticsData.expired})</span></div>
                  </div>
                </div>
                
                <div>
                  <h3 className="text-lg font-medium text-gray-300 mb-4 text-center">Tip Pretplata</h3>
                  <div className="h-64">
                    <ResponsiveContainer width="100%" height="100%">
                      <BarChart
                        data={[
                          { name: '1 Godina', count: analyticsData.active - analyticsData.lifetime },
                          { name: 'Lifetime', count: analyticsData.lifetime },
                          { name: 'Probna', count: analyticsData.trial }
                        ]}
                        margin={{ top: 20, right: 30, left: 20, bottom: 5 }}
                      >
                        <CartesianGrid strokeDasharray="3 3" stroke="#374151" vertical={false} />
                        <XAxis dataKey="name" stroke="#9ca3af" />
                        <YAxis stroke="#9ca3af" allowDecimals={false} />
                        <Tooltip 
                          contentStyle={{ backgroundColor: '#1f2937', borderColor: '#374151', color: '#fff' }}
                          cursor={{ fill: '#374151', opacity: 0.4 }}
                        />
                        <Bar dataKey="count" fill="#8b5cf6" radius={[4, 4, 0, 0]} />
                      </BarChart>
                    </ResponsiveContainer>
                  </div>
                </div>
              </div>
            ) : (
              <div className="text-center text-gray-500 py-8">Nema podataka za analitiku.</div>
            )}
          </div>
        </div>
      )}

      {activeTab === 'logs' && (
        <div className="bg-gray-800 p-6 rounded-xl border border-gray-700 shadow-lg">
          <h2 className="text-xl font-bold mb-6 flex items-center text-white">
            <Activity className="w-5 h-5 mr-2 text-blue-500" />
            Nedavne Aktivnosti
          </h2>
          
          {isLoadingLogs ? (
            <div className="text-center text-gray-500 py-8">Učitavanje zapisa...</div>
          ) : logs.length > 0 ? (
            <div className="space-y-4 max-h-[600px] overflow-y-auto pr-2 custom-scrollbar">
              {logs.map((log) => (
                <div key={log.id} className="flex items-start p-4 bg-gray-900/50 rounded-lg border border-gray-700/50">
                  <div className="flex-shrink-0 mt-1">
                    <div className="w-2 h-2 rounded-full bg-blue-500"></div>
                  </div>
                  <div className="ml-4 flex-1">
                    <p className="text-white font-medium">{log.action}</p>
                    <p className="text-gray-400 text-sm mt-1">{log.details}</p>
                    <p className="text-gray-500 text-xs mt-2">
                      {log.timestamp ? format(log.timestamp.toDate(), "d. MMMM yyyy. 'u' HH:mm", { locale: hr }) : 'Upravo sada'}
                    </p>
                  </div>
                </div>
              ))}
            </div>
          ) : (
            <div className="text-center text-gray-500 py-8 border-2 border-dashed border-gray-700 rounded-lg">
              Nema zabilježenih aktivnosti.
            </div>
          )}
        </div>
        )}
      </div>
    </AdminLayout>
  </ProtectedRoute>
  );
}
