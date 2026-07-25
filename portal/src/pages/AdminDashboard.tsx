import { useState, useEffect } from 'react';
import { Users, Plus, ShieldCheck, Coins, RefreshCw, BarChart2, Activity, Settings } from 'lucide-react';
import { collection, query, where, getDocs, doc, updateDoc, setDoc, orderBy, limit } from 'firebase/firestore';
import { db } from '../firebase';
import { initializeApp } from 'firebase/app';
import { getAuth, createUserWithEmailAndPassword, signOut } from 'firebase/auth';
import { logActivity } from '../utils/activityLogger';
import { useAuth } from '../context/AuthContext';
import { format } from 'date-fns';
import { hr } from 'date-fns/locale';


const secondaryApp = initializeApp({
  apiKey: "AIzaSyD1lfC94ca1J-BHE3wFxaoDpF9ILEKixrM",
  authDomain: "vopoapp-86a75.firebaseapp.com",
  projectId: "vopoapp-86a75",
  storageBucket: "vopoapp-86a75.firebasestorage.app",
  messagingSenderId: "1000759434828",
  appId: "1:1000759434828:web:472be03a1c7fcd73cd1ee9"
}, 'SecondaryApp');
const secondaryAuth = getAuth(secondaryApp);

interface ResellerData {
  uid: string;
  email: string;
  credits: number;
  assignedDomains: string[];
}

export default function AdminDashboard() {
  const { user } = useAuth();
  const [activeTab, setActiveTab] = useState<'resellers' | 'analytics' | 'logs' | 'settings'>('resellers');
  const [resellers, setResellers] = useState<ResellerData[]>([]);
  const [loading, setLoading] = useState(true);
  
  const [showAddModal, setShowAddModal] = useState(false);
  const [newEmail, setNewEmail] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [newCredits, setNewCredits] = useState(0);
  const [isCreating, setIsCreating] = useState(false);

  const [analyticsData, setAnalyticsData] = useState<any>(null);
  const [logs, setLogs] = useState<any[]>([]);
  const [isLoadingAnalytics, setIsLoadingAnalytics] = useState(false);
  const [isLoadingLogs, setIsLoadingLogs] = useState(false);

  const fetchResellers = async () => {
    setLoading(true);
    try {
      const q = query(collection(db, 'users'), where('role', '==', 'reseller'));
      const querySnapshot = await getDocs(q);
      const data: ResellerData[] = [];
      querySnapshot.forEach((doc) => {
        const d = doc.data();
        data.push({ uid: doc.id, email: d.email, credits: d.credits || 0, assignedDomains: d.assignedDomains || [] });
      });
      setResellers(data);
    } catch (error) {
      console.error("Error fetching resellers", error);
    } finally {
      setLoading(false);
    }
  };

  const fetchAnalytics = async () => {
    setIsLoadingAnalytics(true);
    try {
      // Basic analytics for Admin: sum of all reseller credits and total number of resellers
      const q = query(collection(db, 'users'), where('role', '==', 'reseller'));
      const snap = await getDocs(q);
      let totalCredits = 0;
      snap.forEach(doc => {
          totalCredits += (doc.data().credits || 0);
      });
      setAnalyticsData({
          totalResellers: snap.size,
          totalCreditsAllocated: totalCredits
      });
    } catch (error) {
        console.error("Error fetching analytics", error);
    } finally {
        setIsLoadingAnalytics(false);
    }
  };

  const fetchLogs = async () => {
    setIsLoadingLogs(true);
    try {
      // Fetch all logs for admin
      const logsRef = collection(db, 'activity_logs');
      const q = query(logsRef, orderBy('timestamp', 'desc'), limit(100));
      const snap = await getDocs(q);
      const logsData = snap.docs.map(doc => ({ id: doc.id, ...doc.data() }));
      setLogs(logsData);
    } catch (error) {
        console.error("Error fetching logs", error);
    } finally {
        setIsLoadingLogs(false);
    }
  };

  useEffect(() => {
    fetchResellers();
    fetchAnalytics();
    fetchLogs();
  }, []);

  const handleCreateReseller = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsCreating(true);
    try {
      const userCredential = await createUserWithEmailAndPassword(secondaryAuth, newEmail, newPassword);
      await signOut(secondaryAuth);
      const newUid = userCredential.user.uid;
      
      await setDoc(doc(db, 'users', newUid), {
        email: newEmail,
        role: 'reseller',
        credits: newCredits,
        assignedDomains: [],
        customDomains: []
      });

      if (user) {
        await logActivity(user.uid, user.email || '', 'admin', 'CREATE_RESELLER', `Created reseller: ${newEmail}`);
      }

      setShowAddModal(false);
      setNewEmail('');
      setNewPassword('');
      setNewCredits(0);
      fetchResellers();
    } catch (error: any) {
      console.error("Greška pri kreiranju", error);
      alert(`Došlo je do greške: ${error.message}`);
    } finally {
      setIsCreating(false);
    }
  };

  const addCredits = async (uid: string, currentCredits: number, resellerEmail: string) => {
    const amount = parseInt(prompt("Unesite količinu kredita za dodavanje:", "10") || "0", 10);
    if (amount > 0) {
      try {
        await updateDoc(doc(db, 'users', uid), {
          credits: currentCredits + amount
        });
        if (user) {
          await logActivity(user.uid, user.email || '', 'admin', 'ADD_CREDITS', `Added ${amount} credits to ${resellerEmail}`);
        }
        fetchResellers();
      } catch (error) {
        console.error("Greška", error);
      }
    }
  };

  return (
    <div className="space-y-6">
      <div className="flex flex-col md:flex-row md:items-center justify-between bg-gray-800 p-6 rounded-xl border border-gray-700 shadow-lg">
        <div>
          <h1 className="text-2xl font-bold flex items-center text-white">
            <ShieldCheck className="w-7 h-7 mr-2 text-red-500" />
            Admin Dashboard
          </h1>
          <p className="text-gray-400 mt-1">Upravljanje platformom i resellerima</p>
        </div>
        
        <div className="flex space-x-2 mt-4 md:mt-0 overflow-x-auto pb-2 md:pb-0">
          <button 
            onClick={() => setActiveTab('resellers')}
            className={`px-4 py-2 rounded-lg font-medium flex items-center whitespace-nowrap transition-colors ${activeTab === 'resellers' ? 'bg-blue-600 text-white' : 'bg-gray-700 text-gray-300 hover:bg-gray-600'}`}
          >
            <Users className="w-4 h-4 mr-2" /> Reselleri
          </button>
          <button 
            onClick={() => setActiveTab('analytics')}
            className={`px-4 py-2 rounded-lg font-medium flex items-center whitespace-nowrap transition-colors ${activeTab === 'analytics' ? 'bg-blue-600 text-white' : 'bg-gray-700 text-gray-300 hover:bg-gray-600'}`}
          >
            <BarChart2 className="w-4 h-4 mr-2" /> Analitika
          </button>
          <button 
            onClick={() => setActiveTab('logs')}
            className={`px-4 py-2 rounded-lg font-medium flex items-center whitespace-nowrap transition-colors ${activeTab === 'logs' ? 'bg-blue-600 text-white' : 'bg-gray-700 text-gray-300 hover:bg-gray-600'}`}
          >
            <Activity className="w-4 h-4 mr-2" /> Logovi
          </button>
          <button 
            onClick={() => setActiveTab('settings')}
            className={`px-4 py-2 rounded-lg font-medium flex items-center whitespace-nowrap transition-colors ${activeTab === 'settings' ? 'bg-blue-600 text-white' : 'bg-gray-700 text-gray-300 hover:bg-gray-600'}`}
          >
            <Settings className="w-4 h-4 mr-2" /> Postavke
          </button>
        </div>
      </div>

      {activeTab === 'resellers' && (
        <>
          <div className="flex justify-end mb-4">
            <button 
              onClick={fetchResellers}
              className="bg-gray-700 hover:bg-gray-600 text-white px-4 py-2 rounded-lg font-medium flex items-center transition-colors mr-2"
            >
              <RefreshCw className={`w-5 h-5 ${loading ? 'animate-spin' : ''}`} />
            </button>
            <button 
              onClick={() => setShowAddModal(true)}
              className="bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded-lg font-medium flex items-center shadow-lg shadow-blue-500/20 transition-all"
            >
              <Plus className="w-5 h-5 mr-1" />
              Novi Reseller
            </button>
          </div>


          <div className="bg-gray-800 rounded-xl border border-gray-700 overflow-hidden shadow-lg">
            <div className="p-5 border-b border-gray-700 bg-gray-900/50 flex items-center">
              <Users className="w-5 h-5 mr-2 text-blue-400" />
              <h2 className="font-bold text-lg text-white">Aktivni Reselleri</h2>
            </div>
            
            <div className="overflow-x-auto">
              <table className="w-full text-left text-white">
                <thead className="bg-gray-900/80 text-gray-400 text-sm">
                  <tr>
                    <th className="p-4 font-medium uppercase tracking-wider text-xs">Ime / Email</th>
                    <th className="p-4 font-medium uppercase tracking-wider text-xs">Dostupni Krediti</th>
                    <th className="p-4 font-medium uppercase tracking-wider text-xs">Domene</th>
                    <th className="p-4 font-medium uppercase tracking-wider text-xs text-right">Akcije</th>
                  </tr>
                </thead>
                <tbody className="divide-y divide-gray-700">
                  {resellers.map((r) => (
                    <tr key={r.uid} className="hover:bg-gray-750/50 transition-colors">
                      <td className="p-4">
                        <div className="font-medium text-white">{r.email}</div>
                        <div className="text-xs text-gray-400">UID: {r.uid.slice(0,8)}...</div>
                      </td>
                      <td className="p-4">
                        <div className="flex items-center">
                          <Coins className="w-4 h-4 mr-2 text-orange-500" />
                          <span className="font-bold text-lg">{r.credits}</span>
                        </div>
                      </td>
                      <td className="p-4 text-gray-300 text-sm">
                        {r.assignedDomains.length} domena
                      </td>
                      <td className="p-4 text-right">
                        <button 
                          onClick={() => addCredits(r.uid, r.credits, r.email)}
                          className="text-blue-400 hover:text-blue-300 bg-blue-900/20 px-3 py-1.5 rounded-lg text-sm font-medium mr-2 transition-colors"
                        >
                          + Krediti
                        </button>
                      </td>
                    </tr>
                  ))}
                  {resellers.length === 0 && !loading && (
                    <tr>
                      <td colSpan={4} className="p-8 text-center text-gray-400">
                        Nema kreiranih resellera.
                      </td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
          </div>

          {showAddModal && (
            <div className="fixed inset-0 bg-black/60 backdrop-blur-sm flex items-center justify-center p-4 z-50">
              <div className="bg-gray-800 rounded-xl max-w-md w-full border border-gray-700 shadow-2xl">
                <div className="p-6">
                  <h2 className="text-2xl font-bold text-white mb-6">Kreiraj Resellera</h2>
                  <form onSubmit={handleCreateReseller} className="space-y-4">
                    <div>
                      <label className="block text-sm font-medium text-gray-300 mb-1">Email</label>
                      <input 
                        type="email" required 
                        className="w-full bg-gray-900 border border-gray-600 rounded-lg p-2.5 text-white focus:ring-2 focus:ring-blue-500 focus:border-transparent outline-none transition-all" 
                        value={newEmail} onChange={e => setNewEmail(e.target.value)} 
                      />
                    </div>
                    <div>
                      <label className="block text-sm font-medium text-gray-300 mb-1">Lozinka</label>
                      <input 
                        type="password" required minLength={6}
                        className="w-full bg-gray-900 border border-gray-600 rounded-lg p-2.5 text-white focus:ring-2 focus:ring-blue-500 focus:border-transparent outline-none transition-all" 
                        value={newPassword} onChange={e => setNewPassword(e.target.value)} 
                      />
                    </div>
                    <div>
                      <label className="block text-sm font-medium text-gray-300 mb-1">Početni Krediti</label>
                      <input 
                        type="number" required min={0}
                        className="w-full bg-gray-900 border border-gray-600 rounded-lg p-2.5 text-white focus:ring-2 focus:ring-blue-500 focus:border-transparent outline-none transition-all" 
                        value={newCredits} onChange={e => setNewCredits(Number(e.target.value))} 
                      />
                    </div>
                    <div className="flex space-x-3 pt-4">
                      <button 
                        type="button" onClick={() => setShowAddModal(false)}
                        className="flex-1 bg-gray-700 hover:bg-gray-600 text-white py-2.5 rounded-lg font-medium transition-colors"
                      >
                        Odustani
                      </button>
                      <button 
                        type="submit" disabled={isCreating}
                        className="flex-1 bg-blue-600 hover:bg-blue-700 text-white py-2.5 rounded-lg font-medium transition-colors disabled:opacity-50"
                      >
                        {isCreating ? 'Kreiranje...' : 'Kreiraj'}
                      </button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          )}
        </>
      )}

      {activeTab === 'analytics' && (
        <div className="bg-gray-800 rounded-xl p-8 border border-gray-700 shadow-lg">
          <h2 className="text-xl font-bold text-white flex items-center mb-6">
            <BarChart2 className="w-5 h-5 mr-2 text-blue-500" />
            Analitika Sustava
          </h2>
          {isLoadingAnalytics ? (
            <div className="text-center text-gray-500 py-8">Učitavanje analitike...</div>
          ) : analyticsData ? (
            <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
              <div className="bg-gray-900/50 p-6 rounded-lg border border-gray-700/50 flex flex-col items-center justify-center text-center">
                <Users className="w-12 h-12 text-blue-500 mb-4" />
                <h3 className="text-gray-400 font-medium uppercase tracking-wider text-sm mb-1">Ukupno Resellera</h3>
                <p className="text-4xl font-bold text-white">{analyticsData.totalResellers}</p>
              </div>
              <div className="bg-gray-900/50 p-6 rounded-lg border border-gray-700/50 flex flex-col items-center justify-center text-center">
                <Coins className="w-12 h-12 text-orange-500 mb-4" />
                <h3 className="text-gray-400 font-medium uppercase tracking-wider text-sm mb-1">Ukupno Kredita u Optjecaju</h3>
                <p className="text-4xl font-bold text-white">{analyticsData.totalCreditsAllocated}</p>
              </div>
            </div>
          ) : (
            <div className="text-center text-gray-500 py-8">Nema podataka za analitiku.</div>
          )}
        </div>
      )}

      {activeTab === 'logs' && (
        <div className="bg-gray-800 rounded-xl p-8 border border-gray-700 shadow-lg">
          <h2 className="text-xl font-bold text-white flex items-center mb-6">
            <Activity className="w-5 h-5 mr-2 text-blue-500" />
            Globalni Logovi
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
                    <div className="flex justify-between items-start">
                        <p className="text-white font-medium">{log.action}</p>
                        <span className={`text-xs px-2 py-0.5 rounded font-medium uppercase tracking-wider ${log.role === 'admin' ? 'bg-purple-900/50 text-purple-400' : 'bg-blue-900/50 text-blue-400'}`}>
                            {log.role}
                        </span>
                    </div>
                    <p className="text-gray-400 text-sm mt-1">{log.details}</p>
                    <div className="flex justify-between items-center mt-2">
                        <p className="text-gray-500 text-xs">{log.userEmail}</p>
                        <p className="text-gray-500 text-xs">
                        {log.timestamp ? format(log.timestamp.toDate(), "d. MMMM yyyy. 'u' HH:mm", { locale: hr }) : 'Upravo sada'}
                        </p>
                    </div>
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

      {activeTab === 'settings' && (
        <div className="bg-gray-800 rounded-xl p-8 text-center border border-gray-700 shadow-lg">
          <Settings className="w-16 h-16 text-gray-600 mx-auto mb-4" />
          <h2 className="text-xl font-bold text-white">Postavke Sustava</h2>
          <p className="text-gray-400 mt-2">Uskoro: Konfiguracija platforme</p>
        </div>
      )}
    </div>
  );
}
