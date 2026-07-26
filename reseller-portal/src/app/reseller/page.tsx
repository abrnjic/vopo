"use client";

import { useState, useEffect } from 'react';
import { Plus, List, CreditCard, Check, Settings, Send, Trash2, Activity, BarChart2 } from 'lucide-react';
import { useAuth } from '../../context/AuthContext';
import { doc, getDoc, updateDoc, setDoc, serverTimestamp, collection, query, where, getDocs, increment, addDoc, orderBy, limit, writeBatch } from 'firebase/firestore';
import { db } from '../../firebase';
import { logActivity } from '../../utils/activityLogger';
import { format } from 'date-fns';
import { hr } from 'date-fns/locale';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, PieChart, Pie, Cell } from 'recharts';
import AdminLayout from '../../components/AdminLayout';
import ProtectedRoute from '../../components/ProtectedRoute';

export default function ResellerDashboard() {
  const { user, userData } = useAuth();
  const [activeTab, setActiveTab] = useState<'activate' | 'analytics' | 'logs' | 'settings'>('activate');
  const [credits, setCredits] = useState<number>(userData?.credits || 0);
  const [customDomains, setCustomDomains] = useState<string[]>(userData?.customDomains || []);
  
  const [recentLines, setRecentLines] = useState<any[]>([]);

  // Activate Form State
  const [deviceId, setDeviceId] = useState('');
  const [selectedDomain, setSelectedDomain] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [customerName, setCustomerName] = useState('');
  const [customerContact, setCustomerContact] = useState('');
  const [licenseType, setLicenseType] = useState<'1_year' | 'lifetime' | 'trial'>('1_year');
  const [isActivating, setIsActivating] = useState(false);

  // Domain Form State
  const [newDomain, setNewDomain] = useState('');

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
        const logsRef = collection(db, 'activity_logs');
        const q = query(logsRef, where('userId', '==', user.uid), orderBy('timestamp', 'desc'), limit(50));
        const snap = await getDocs(q);
        const logsData = snap.docs.map(doc => ({ id: doc.id, ...doc.data() }));
        setLogs(logsData);
        setIsLoadingLogs(false);
      };

      fetchUserData();
      fetchLinesAndAnalytics();
      fetchLogs();
    }
  }, [user]);

  const allDomains = [...(userData?.assignedDomains || []), ...customDomains];
  
  // Set default domain if available
  useEffect(() => {
    if (allDomains.length > 0 && !selectedDomain) {
      setSelectedDomain(allDomains[0]);
    }
  }, [allDomains]);

  const handleActivate = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!user) return;
    
    let creditsToDeduct = 0;
    if (licenseType === '1_year') creditsToDeduct = 1;
    if (licenseType === 'lifetime') creditsToDeduct = 2;
    
    if (credits < creditsToDeduct) {
      alert('Nemate dovoljno kredita!');
      return;
    }

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
          selectedDomain
        })
      });

      if (!res.ok) {
        const errData = await res.json();
        throw new Error(errData.error || 'Neuspješna aktivacija');
      }

      alert(creditsToDeduct > 0 ? 'Linija uspješno aktivirana!' : 'Probna linija uspješno postavljena!');

      setDeviceId('');
      setUsername('');
      setPassword('');
      setCustomerName('');
      setCustomerContact('');
      
      // Osvježi listu nedavnih
      setRecentLines(prev => [{ 
        id: deviceId.trim(), 
        deviceId: deviceId.trim(),
        resellerId: user.uid,
        status: licenseType === 'trial' ? 'Trial' : 'Active',
        isTrial: licenseType === 'trial',
        customerName: customerName,
        customerContact: customerContact
      }, ...prev.filter(l => l.id !== deviceId.trim())]);
    } catch (error) {
      console.error(error);
      alert('Došlo je do greške.');
    } finally {
      setIsActivating(false);
    }
  };

  const handleAddDomain = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!user || !newDomain.trim()) return;

    try {
      const updatedDomains = [...customDomains, newDomain.trim()];
      await updateDoc(doc(db, 'users', user.uid), { customDomains: updatedDomains });
      setCustomDomains(updatedDomains);
      
      await logActivity(
        user.uid, 
        user.email || '', 
        'reseller', 
        'ADD_DOMAIN', 
        `Added custom domain: ${newDomain.trim()}`
      );
      
      setNewDomain('');
    } catch (error) {
      console.error(error);
    }
  };

  const handleDeleteDomain = async (domainToRemove: string) => {
    if (!user) return;
    try {
      const updatedDomains = customDomains.filter(d => d !== domainToRemove);
      await updateDoc(doc(db, 'users', user.uid), { customDomains: updatedDomains });
      setCustomDomains(updatedDomains);
      
      await logActivity(
        user.uid, 
        user.email || '', 
        'reseller', 
        'DELETE_DOMAIN', 
        `Deleted custom domain: ${domainToRemove}`
      );

      if (selectedDomain === domainToRemove) {
        setSelectedDomain(updatedDomains[0] || '');
      }
    } catch (error) {
      console.error(error);
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
      const batch = writeBatch(db);
      
      selectedLines.forEach(lineId => {
        const lineRef = doc(db, 'licenses', lineId);
        
        // Find current line to get expiration
        const currentLine = recentLines.find(l => l.id === lineId);
        let newExpiration = new Date();
        
        if (currentLine && currentLine.expiresAt && currentLine.status !== 'Expired') {
            // Add 1 year to existing expiration
            newExpiration = currentLine.expiresAt.toDate();
            newExpiration.setFullYear(newExpiration.getFullYear() + 1);
        } else {
            // Start from today
            newExpiration.setFullYear(newExpiration.getFullYear() + 1);
        }

        batch.update(lineRef, {
          status: 'Active',
          expiresAt: newExpiration,
          updatedAt: serverTimestamp()
        });
      });

      // Deduct credits
      batch.update(doc(db, 'users', user.uid), {
        credits: increment(-totalCreditsNeeded)
      });

      await batch.commit();

      // Log transaction and activity
      await addDoc(collection(db, 'transactions'), {
        resellerId: user.uid,
        type: 'bulk_extension',
        linesCount: selectedLines.length,
        creditsDeducted: totalCreditsNeeded,
        timestamp: serverTimestamp()
      });

      await logActivity(
        user.uid, 
        user.email || '', 
        'reseller', 
        'BULK_EXTEND', 
        `Extended ${selectedLines.length} lines for 1 year`
      );

      // Local state update
      setCredits(prev => prev - totalCreditsNeeded);
      setRecentLines(prev => prev.map(l => {
          if (selectedLines.includes(l.id)) {
              let exp = l.expiresAt ? l.expiresAt.toDate() : new Date();
              if(l.status === 'Expired' || !l.expiresAt) exp = new Date();
              exp.setFullYear(exp.getFullYear() + 1);
              return { ...l, status: 'Active', expiresAt: { toDate: () => exp } };
          }
          return l;
      }));
      setSelectedLines([]);
      alert(`Uspješno produženo ${selectedLines.length} linija.`);
    } catch (error) {
      console.error(error);
      alert('Došlo je do greške prilikom masovnog produženja.');
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
      const batch = writeBatch(db);
      
      selectedLines.forEach(lineId => {
        batch.delete(doc(db, 'licenses', lineId));
      });

      await batch.commit();

      await logActivity(
        user.uid, 
        user.email || '', 
        'reseller', 
        'BULK_DELETE', 
        `Deleted ${selectedLines.length} lines permanently`
      );

      // Local state update
      setRecentLines(prev => prev.filter(l => !selectedLines.includes(l.id)));
      setSelectedLines([]);
      alert(`Uspješno obrisano ${selectedLines.length} linija.`);
    } catch (error) {
      console.error(error);
      alert('Došlo je do greške prilikom masovnog brisanja.');
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
    <ProtectedRoute allowedRoles={['reseller']}>
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

      <div className="flex space-x-2 border-b border-gray-700 pb-px overflow-x-auto">
        <button 
          onClick={() => setActiveTab('activate')}
          className={`px-6 py-3 font-medium transition-all flex items-center border-b-2 whitespace-nowrap ${activeTab === 'activate' ? 'border-blue-500 text-blue-400' : 'border-transparent text-gray-400 hover:text-white'}`}
        >
          <CreditCard className="w-4 h-4 mr-2" />
          Upravljanje Linijama
        </button>
        <button 
          onClick={() => setActiveTab('analytics')}
          className={`px-6 py-3 font-medium transition-all flex items-center border-b-2 whitespace-nowrap ${activeTab === 'analytics' ? 'border-blue-500 text-blue-400' : 'border-transparent text-gray-400 hover:text-white'}`}
        >
          <BarChart2 className="w-4 h-4 mr-2" />
          Moja Analitika
        </button>
        <button 
          onClick={() => setActiveTab('logs')}
          className={`px-6 py-3 font-medium transition-all flex items-center border-b-2 whitespace-nowrap ${activeTab === 'logs' ? 'border-blue-500 text-blue-400' : 'border-transparent text-gray-400 hover:text-white'}`}
        >
          <Activity className="w-4 h-4 mr-2" />
          Aktivnosti
        </button>
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
                      <div className="text-sm text-gray-400">Korisniku teče probni period od 3 dana. Troši <span className="font-bold text-green-400">0 kredita</span></div>
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
                {isActivating ? 'Aktivacija u tijeku...' : 'Pošalji liniju i Aktiviraj'}
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
                  className={`flex justify-between items-center p-4 rounded-lg border transition-all cursor-pointer ${
                    selectedLines.includes(line.id) 
                      ? 'bg-blue-900/20 border-blue-500 shadow-[0_0_10px_rgba(59,130,246,0.2)]' 
                      : 'bg-gray-900/50 border-gray-700/50 hover:border-gray-600'
                  }`}
                >
                  <div className="flex items-center">
                    <div className={`w-5 h-5 rounded border mr-4 flex items-center justify-center transition-colors ${
                      selectedLines.includes(line.id) ? 'bg-blue-500 border-blue-500' : 'border-gray-500'
                    }`}>
                      {selectedLines.includes(line.id) && <Check className="w-3.5 h-3.5 text-white" />}
                    </div>
                    <div>
                      <div className="font-mono text-white text-lg">{line.id} {line.customerName && <span className="text-gray-400 text-sm ml-2">({line.customerName})</span>}</div>
                      <div className="text-sm text-gray-400 flex items-center mt-1">
                        <span className={`px-2 py-0.5 rounded text-xs mr-2 font-medium ${line.isLifetime ? 'bg-purple-900/50 text-purple-400' : 'bg-blue-900/50 text-blue-400'}`}>
                          {line.isLifetime ? 'Lifetime' : '1 Godina'}
                        </span>
                        {line.selectedDomain}
                      </div>
                    </div>
                  </div>
                  <div className={`flex items-center px-3 py-1 rounded-full text-sm font-medium border ${
                    line.status === 'Active' ? 'text-green-500 bg-green-900/20 border-green-900/50' : 
                    line.status === 'Trial' ? 'text-yellow-500 bg-yellow-900/20 border-yellow-900/50' : 
                    'text-red-500 bg-red-900/20 border-red-900/50'
                  }`}>
                    <Check className="w-4 h-4 mr-1" />
                    {line.status}
                  </div>
                </div>
              ))}
              {recentLines.length === 0 && (
                <div className="text-center text-gray-500 py-12">Nema aktiviranih linija.</div>
              )}
            </div>
          </div>
        </div>
      )}

      {activeTab === 'settings' && (
        <div className="space-y-6">
          <div className="bg-gray-800 p-6 rounded-xl border border-gray-700 max-w-3xl shadow-lg">
            <h2 className="text-xl font-bold mb-6 flex items-center text-white">
              <Settings className="w-5 h-5 mr-2 text-blue-500" />
              Moje Domene (Portal URL-ovi)
            </h2>
            <form className="flex space-x-3 mb-8" onSubmit={handleAddDomain}>
            <input 
              type="url" required
              className="flex-1 bg-gray-900 border border-gray-600 rounded-lg p-3 text-white focus:ring-2 focus:ring-blue-500 outline-none transition-all" 
              placeholder="npr. http://moj-novi-portal.com:8080" 
              value={newDomain} onChange={e => setNewDomain(e.target.value)}
            />
            <button type="submit" className="bg-blue-600 hover:bg-blue-700 px-6 py-3 rounded-lg font-medium flex items-center text-white transition-all shadow-lg shadow-blue-500/20">
              <Plus className="w-5 h-5 mr-2" />
              Dodaj Domenu
            </button>
          </form>

          <div className="space-y-3">
            {userData?.assignedDomains?.map((d, i) => (
              <div key={`assigned-${i}`} className="flex justify-between items-center p-4 bg-gray-900 rounded-lg border border-gray-700">
                <div className="font-medium text-white flex items-center">
                  <span className="bg-blue-900/50 text-blue-400 text-xs px-2 py-1 rounded mr-3 uppercase font-bold tracking-wider">Dodijeljeno</span>
                  {d}
                </div>
              </div>
            ))}
            
            {customDomains.map((d, i) => (
              <div key={`custom-${i}`} className="flex justify-between items-center p-4 bg-gray-900 rounded-lg border border-gray-700 group hover:border-gray-500 transition-colors">
                <div className="font-medium text-white flex items-center">
                  <span className="bg-gray-700 text-gray-300 text-xs px-2 py-1 rounded mr-3 uppercase font-bold tracking-wider">Vlastito</span>
                  {d}
                </div>
                <button 
                  onClick={() => handleDeleteDomain(d)}
                  className="text-red-400 hover:text-red-300 p-2 hover:bg-red-900/20 rounded transition-colors opacity-0 group-hover:opacity-100"
                  title="Obriši domenu"
                >
                  <Trash2 className="w-5 h-5" />
                </button>
              </div>
            ))}

            {allDomains.length === 0 && (
              <div className="text-center text-gray-500 py-8 border-2 border-dashed border-gray-700 rounded-lg">
                Trenutno nemate niti jednu domenu. Dodajte prvu domenu iznad.
              </div>
            )}
          </div>
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
