"use client";

import { useState, useEffect, useRef } from 'react';
import { Users, Plus, ShieldCheck, Coins, RefreshCw, BarChart2, Activity, Settings, MoreVertical, Edit, Trash2, Key, Ban, CheckCircle } from 'lucide-react';
import { collection, query, where, getDocs, doc, updateDoc, setDoc, orderBy, limit } from 'firebase/firestore';
import { db } from '../../firebase';
import { initializeApp } from 'firebase/app';
import { getAuth, createUserWithEmailAndPassword, signOut, sendPasswordResetEmail } from 'firebase/auth';
import { logActivity, ActivityAction } from '../../utils/activityLogger';
import { useAuth } from '../../context/AuthContext';
import AdminLayout from '../../components/AdminLayout';
import ProtectedRoute from '../../components/ProtectedRoute';
import { format } from 'date-fns';
import { hr } from 'date-fns/locale';


const secondaryApp = initializeApp({
  apiKey: process.env.NEXT_PUBLIC_FIREBASE_API_KEY || "AIzaSyDI0928okWbvWQ0SgjRqv436jteZIMmT78",
  authDomain: process.env.NEXT_PUBLIC_FIREBASE_AUTH_DOMAIN || "vopoapp-86a75.firebaseapp.com",
  projectId: process.env.NEXT_PUBLIC_FIREBASE_PROJECT_ID || "vopoapp-86a75",
  storageBucket: process.env.NEXT_PUBLIC_FIREBASE_STORAGE_BUCKET || "vopoapp-86a75.firebasestorage.app",
  messagingSenderId: process.env.NEXT_PUBLIC_FIREBASE_MESSAGING_SENDER_ID || "1000759434828",
  appId: process.env.NEXT_PUBLIC_FIREBASE_APP_ID || "1:1000759434828:web:472be03a1c7fcd73cd1ee9"
}, 'SecondaryApp');
const secondaryAuth = getAuth(secondaryApp);

interface ResellerData {
  uid: string;
  email: string;
  credits: number;
  assignedDomains: string[];
  status?: 'active' | 'suspended' | 'deleted';
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

  // New states for actions
  const [actionMenuOpen, setActionMenuOpen] = useState<string | null>(null);
  const [editingUser, setEditingUser] = useState<ResellerData | null>(null);
  const [editCredits, setEditCredits] = useState(0);
  const [isSavingEdit, setIsSavingEdit] = useState(false);

  // Close dropdown on click outside
  const menuRef = useRef<HTMLDivElement>(null);
  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (menuRef.current && !menuRef.current.contains(event.target as Node)) {
        setActionMenuOpen(null);
      }
    };
    document.addEventListener('mousedown', handleClickOutside);
    return () => document.removeEventListener('mousedown', handleClickOutside);
  }, []);

  const fetchResellers = async () => {
    setLoading(true);
    try {
      const q = query(collection(db, 'users'), where('role', '==', 'reseller'));
      const querySnapshot = await getDocs(q);
      const data: ResellerData[] = [];
      querySnapshot.forEach((doc) => {
        const d = doc.data();
        if (d.status !== 'deleted') {
          data.push({ 
            uid: doc.id, 
            email: d.email, 
            credits: d.credits || 0, 
            assignedDomains: d.assignedDomains || [],
            status: d.status || 'active'
          });
        }
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
        customDomains: [],
        status: 'active'
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

  const handleSuspend = async (uid: string, currentStatus: string | undefined, email: string) => {
    const newStatus = currentStatus === 'suspended' ? 'active' : 'suspended';
    if (confirm(`Jeste li sigurni da želite ${newStatus === 'suspended' ? 'suspendirati' : 'aktivirati'} račun ${email}?`)) {
      await updateDoc(doc(db, 'users', uid), { status: newStatus });
      if (user) {
        await logActivity(user.uid, user.email || '', 'admin', 'UPDATE_STATUS', `Postavljen status '${newStatus}' za ${email}`);
      }
      fetchResellers();
    }
    setActionMenuOpen(null);
  };

  const handleDelete = async (uid: string, email: string) => {
    if (confirm(`UPOZORENJE! Jeste li sigurni da želite obrisati račun ${email}? Ovo će mu onemogućiti pristup aplikaciji.`)) {
      await updateDoc(doc(db, 'users', uid), { status: 'deleted' });
      if (user) {
        await logActivity(user.uid, user.email || '', 'admin', 'DELETE_USER', `Obrisan korisnik ${email}`);
      }
      fetchResellers();
    }
    setActionMenuOpen(null);
  };

  const handlePasswordReset = async (email: string) => {
    if (confirm(`Poslati link za resetiranje lozinke na ${email}?`)) {
      try {
        await sendPasswordResetEmail(secondaryAuth, email);
        alert(`Link za resetiranje lozinke uspješno poslan na ${email}`);
        if (user) {
          await logActivity(user.uid, user.email || '', 'admin', 'PASSWORD_RESET', `Poslan link za reset lozinke za ${email}`);
        }
      } catch (e: any) {
        alert(`Greška: ${e.message}`);
      }
    }
    setActionMenuOpen(null);
  };

  const handleSaveEdit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!editingUser) return;
    setIsSavingEdit(true);
    try {
      await updateDoc(doc(db, 'users', editingUser.uid), {
        credits: editCredits
      });
      if (user) {
        const creditDiff = editCredits - editingUser.credits;
        let actionType: ActivityAction = 'EDIT_RESELLER';
        let logMessage = `Ažurirani podaci za ${editingUser.email}`;

        if (creditDiff !== 0) {
          if (creditDiff > 0) {
            actionType = 'ADD_CREDITS';
            logMessage = `Dodano ${creditDiff} kredita korisniku ${editingUser.email} (Novo stanje: ${editCredits})`;
          } else {
            actionType = 'REMOVE_CREDITS';
            logMessage = `Skinuto ${Math.abs(creditDiff)} kredita korisniku ${editingUser.email} (Novo stanje: ${editCredits})`;
          }
        }

        await logActivity(user.uid, user.email || '', 'admin', actionType, logMessage);
      }
      setEditingUser(null);
      fetchResellers();
    } catch (error: any) {
      alert(`Greška: ${error.message}`);
    } finally {
      setIsSavingEdit(false);
    }
  };

  return (
    <ProtectedRoute allowedRoles={['admin']}>
      <AdminLayout>
        <div className="space-y-6">
          {/* Header & Tabs */}
          <div className="flex flex-col md:flex-row md:items-center justify-between bg-gray-900/40 backdrop-blur-md p-6 rounded-2xl border border-gray-800/60 shadow-xl">
            <div>
              <h1 className="text-2xl font-bold flex items-center text-white">
                <div className="w-10 h-10 rounded-lg bg-gradient-to-br from-red-500/20 to-red-600/10 flex items-center justify-center mr-3 border border-red-500/20 shadow-[0_0_15px_rgba(239,68,68,0.2)]">
                  <ShieldCheck className="w-6 h-6 text-red-500" />
                </div>
                Admin Centar
              </h1>
              <p className="text-gray-400 mt-2 text-sm ml-13">Upravljanje platformom, analitikom i resellerima</p>
            </div>
            
            <div className="flex space-x-2 mt-6 md:mt-0 overflow-x-auto pb-2 md:pb-0 bg-gray-950/50 p-1.5 rounded-xl border border-gray-800/50">
              <button 
                onClick={() => setActiveTab('resellers')}
                className={`px-4 py-2.5 rounded-lg font-medium flex items-center whitespace-nowrap transition-all duration-300 text-sm ${activeTab === 'resellers' ? 'bg-blue-600 text-white shadow-lg shadow-blue-500/20' : 'text-gray-400 hover:text-white hover:bg-gray-800/50'}`}
              >
                <Users className="w-4 h-4 mr-2" /> Reselleri
              </button>
              <button 
                onClick={() => setActiveTab('analytics')}
                className={`px-4 py-2.5 rounded-lg font-medium flex items-center whitespace-nowrap transition-all duration-300 text-sm ${activeTab === 'analytics' ? 'bg-blue-600 text-white shadow-lg shadow-blue-500/20' : 'text-gray-400 hover:text-white hover:bg-gray-800/50'}`}
              >
                <BarChart2 className="w-4 h-4 mr-2" /> Analitika
              </button>
              <button 
                onClick={() => setActiveTab('logs')}
                className={`px-4 py-2.5 rounded-lg font-medium flex items-center whitespace-nowrap transition-all duration-300 text-sm ${activeTab === 'logs' ? 'bg-blue-600 text-white shadow-lg shadow-blue-500/20' : 'text-gray-400 hover:text-white hover:bg-gray-800/50'}`}
              >
                <Activity className="w-4 h-4 mr-2" /> Logovi
              </button>
              <button 
                onClick={() => setActiveTab('settings')}
                className={`px-4 py-2.5 rounded-lg font-medium flex items-center whitespace-nowrap transition-all duration-300 text-sm ${activeTab === 'settings' ? 'bg-blue-600 text-white shadow-lg shadow-blue-500/20' : 'text-gray-400 hover:text-white hover:bg-gray-800/50'}`}
              >
                <Settings className="w-4 h-4 mr-2" /> Postavke
              </button>
            </div>
          </div>

          {/* Resellers Tab */}
          {activeTab === 'resellers' && (
            <div className="space-y-6">
              <div className="flex justify-end">
                <button 
                  onClick={fetchResellers}
                  className="bg-gray-800/80 hover:bg-gray-700/80 border border-gray-700 text-gray-300 px-4 py-2.5 rounded-xl font-medium flex items-center transition-all mr-3 backdrop-blur-sm"
                >
                  <RefreshCw className={`w-4 h-4 ${loading ? 'animate-spin' : ''}`} />
                </button>
                <button 
                  onClick={() => setShowAddModal(true)}
                  className="bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-500 hover:to-indigo-500 text-white px-5 py-2.5 rounded-xl font-medium flex items-center shadow-lg shadow-blue-500/25 transition-all transform hover:-translate-y-0.5"
                >
                  <Plus className="w-5 h-5 mr-1.5" />
                  Novi Reseller
                </button>
              </div>

              <div className="bg-gray-900/40 backdrop-blur-md rounded-2xl border border-gray-800/60 overflow-hidden shadow-xl">
                <div className="p-6 border-b border-gray-800/60 bg-gray-900/40 flex items-center justify-between">
                  <div className="flex items-center">
                    <div className="w-8 h-8 rounded-lg bg-blue-500/10 flex items-center justify-center mr-3 border border-blue-500/20">
                      <Users className="w-4 h-4 text-blue-400" />
                    </div>
                    <h2 className="font-semibold text-lg text-white">Aktivni Reselleri</h2>
                  </div>
                  <span className="text-xs font-medium bg-blue-500/10 text-blue-400 px-2.5 py-1 rounded-full border border-blue-500/20">Ukupno: {resellers.length}</span>
                </div>
                
                <div className="overflow-x-auto">
                  <table className="w-full text-left text-white">
                    <thead className="bg-gray-900/80 text-gray-400 text-xs uppercase tracking-wider">
                      <tr>
                        <th className="px-6 py-4 font-medium">Ime / Email</th>
                        <th className="px-6 py-4 font-medium">Dostupni Krediti</th>
                        <th className="px-6 py-4 font-medium">Domene</th>
                        <th className="px-6 py-4 font-medium text-right">Akcije</th>
                      </tr>
                    </thead>
                    <tbody className="divide-y divide-gray-800/60">
                      {resellers.map((r) => (
                        <tr key={r.uid} className="hover:bg-gray-800/30 transition-colors">
                          <td className="px-6 py-4">
                            <div className="flex items-center">
                              <div className="w-10 h-10 rounded-full bg-gradient-to-br from-gray-800 to-gray-700 flex items-center justify-center mr-3 border border-gray-600/30">
                                <span className="text-gray-300 font-medium text-sm">{r.email.charAt(0).toUpperCase()}</span>
                              </div>
                              <div>
                                <div className="font-medium text-gray-200">{r.email}</div>
                                <div className="text-xs text-gray-500 mt-0.5 font-mono">UID: {r.uid.slice(0,8)}...</div>
                              </div>
                            </div>
                          </td>
                          <td className="px-6 py-4">
                            <div className="flex items-center">
                              <div className="w-8 h-8 rounded-full bg-orange-500/10 flex items-center justify-center mr-2 border border-orange-500/20">
                                <Coins className="w-4 h-4 text-orange-400" />
                              </div>
                              <span className="font-semibold text-lg text-gray-200">{r.credits}</span>
                            </div>
                          </td>
                            <td className="px-6 py-4">
                            <div className="flex items-center space-x-2">
                              <span className="bg-gray-800/80 text-gray-300 text-xs font-medium px-2.5 py-1 rounded-md border border-gray-700/50">
                                {r.assignedDomains.length} domena
                              </span>
                              {r.status === 'suspended' && (
                                <span className="bg-red-500/10 text-red-400 text-xs font-medium px-2.5 py-1 rounded-md border border-red-500/20">
                                  Suspendiran
                                </span>
                              )}
                            </div>
                          </td>
                          <td className="px-6 py-4 text-right">
                            <button 
                              onClick={() => setActionMenuOpen(r.uid)}
                              className="p-2 text-gray-400 hover:text-white bg-gray-800/30 hover:bg-gray-700/50 rounded-lg transition-colors border border-gray-700/50"
                            >
                              Upravljaj
                            </button>
                          </td>
                        </tr>
                      ))}
                      {resellers.length === 0 && !loading && (
                        <tr>
                          <td colSpan={4} className="px-6 py-12 text-center text-gray-500">
                            <div className="flex flex-col items-center justify-center">
                              <Users className="w-12 h-12 text-gray-700 mb-3" />
                              <p>Nema kreiranih resellera.</p>
                            </div>
                          </td>
                        </tr>
                      )}
                    </tbody>
                  </table>
                </div>
              </div>

              {/* Add Reseller Modal */}
              {showAddModal && (
                <div className="fixed inset-0 bg-black/80 backdrop-blur-sm flex items-center justify-center p-4 z-50">
                  <div className="bg-gray-900 border border-gray-800 rounded-2xl max-w-md w-full shadow-2xl overflow-hidden relative">
                    <div className="absolute top-0 left-0 w-full h-1 bg-gradient-to-r from-blue-500 to-indigo-500" />
                    <div className="p-8">
                      <h2 className="text-2xl font-bold text-white mb-2">Kreiraj Resellera</h2>
                      <p className="text-gray-400 text-sm mb-6">Unesite podatke za novog korisnika sustava.</p>
                      
                      <form onSubmit={handleCreateReseller} className="space-y-5">
                        <div>
                          <label className="block text-sm font-medium text-gray-400 mb-1.5">Email adresa</label>
                          <input 
                            type="email" required 
                            placeholder="admin@tvrtka.com"
                            className="w-full bg-gray-950/50 border border-gray-700 rounded-xl p-3 text-white focus:ring-2 focus:ring-blue-500/50 focus:border-blue-500 outline-none transition-all placeholder:text-gray-600" 
                            value={newEmail} onChange={e => setNewEmail(e.target.value)} 
                          />
                        </div>
                        <div>
                          <label className="block text-sm font-medium text-gray-400 mb-1.5">Lozinka</label>
                          <input 
                            type="password" required minLength={6}
                            placeholder="••••••••"
                            className="w-full bg-gray-950/50 border border-gray-700 rounded-xl p-3 text-white focus:ring-2 focus:ring-blue-500/50 focus:border-blue-500 outline-none transition-all placeholder:text-gray-600" 
                            value={newPassword} onChange={e => setNewPassword(e.target.value)} 
                          />
                        </div>
                        <div>
                          <label className="block text-sm font-medium text-gray-400 mb-1.5">Početni Krediti</label>
                          <input 
                            type="number" required min={0}
                            className="w-full bg-gray-950/50 border border-gray-700 rounded-xl p-3 text-white focus:ring-2 focus:ring-blue-500/50 focus:border-blue-500 outline-none transition-all" 
                            value={newCredits} onChange={e => setNewCredits(Number(e.target.value))} 
                          />
                        </div>
                        <div className="flex space-x-3 pt-6">
                          <button 
                            type="button" onClick={() => setShowAddModal(false)}
                            className="flex-1 bg-gray-800 hover:bg-gray-700 text-gray-300 py-3 rounded-xl font-medium transition-colors border border-gray-700"
                          >
                            Odustani
                          </button>
                          <button 
                            type="submit" disabled={isCreating}
                            className="flex-1 bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-500 hover:to-indigo-500 text-white py-3 rounded-xl font-medium transition-all shadow-lg shadow-blue-500/25 disabled:opacity-50"
                          >
                            {isCreating ? 'Spremanje...' : 'Kreiraj Korisnika'}
                          </button>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              )}

              {/* Action Menu Modal */}
              {actionMenuOpen && (
                (() => {
                  const r = resellers.find(user => user.uid === actionMenuOpen);
                  if (!r) return null;
                  return (
                    <div className="fixed inset-0 bg-black/80 backdrop-blur-sm flex items-end sm:items-center justify-center p-4 z-[60]">
                      <div className="bg-gray-900 border border-gray-700 sm:rounded-2xl rounded-t-2xl max-w-sm w-full shadow-2xl overflow-hidden animate-in slide-in-from-bottom-10 sm:zoom-in-95 duration-200 pb-safe">
                        <div className="p-6 border-b border-gray-800 flex justify-between items-center bg-gray-800/30">
                          <div>
                            <h3 className="text-lg font-bold text-white">Upravljanje</h3>
                            <p className="text-xs text-gray-400 mt-1 truncate max-w-[200px]">{r.email}</p>
                          </div>
                          <button 
                            onClick={() => setActionMenuOpen(null)}
                            className="p-2 bg-gray-800 hover:bg-gray-700 text-gray-400 hover:text-white rounded-full transition-colors"
                          >
                            ✕
                          </button>
                        </div>
                        <div className="p-3 space-y-1">
                          <button 
                            onClick={() => { setEditingUser(r); setEditCredits(r.credits); setActionMenuOpen(null); }}
                            className="w-full text-left px-4 py-3.5 rounded-xl text-sm font-medium text-gray-200 hover:bg-gray-800 flex items-center transition-colors"
                          >
                            <Edit className="w-5 h-5 mr-3 text-blue-400" /> Uredi Podatke
                          </button>
                          <button 
                            onClick={() => { addCredits(r.uid, r.credits, r.email); setActionMenuOpen(null); }}
                            className="w-full text-left px-4 py-3.5 rounded-xl text-sm font-medium text-gray-200 hover:bg-gray-800 flex items-center transition-colors"
                          >
                            <Coins className="w-5 h-5 mr-3 text-emerald-400" /> Brzo dodaj kredite
                          </button>
                          <button 
                            onClick={() => { handlePasswordReset(r.email); setActionMenuOpen(null); }}
                            className="w-full text-left px-4 py-3.5 rounded-xl text-sm font-medium text-gray-200 hover:bg-gray-800 flex items-center transition-colors"
                          >
                            <Key className="w-5 h-5 mr-3 text-purple-400" /> Reset Lozinke
                          </button>
                          <button 
                            onClick={() => { handleSuspend(r.uid, r.status, r.email); setActionMenuOpen(null); }}
                            className={`w-full text-left px-4 py-3.5 rounded-xl text-sm font-medium flex items-center transition-colors hover:bg-gray-800 ${r.status === 'suspended' ? 'text-green-400' : 'text-orange-400'}`}
                          >
                            {r.status === 'suspended' ? <><CheckCircle className="w-5 h-5 mr-3" /> Aktiviraj Račun</> : <><Ban className="w-5 h-5 mr-3" /> Suspendiraj Račun</>}
                          </button>
                          <div className="h-px bg-gray-800 my-2 mx-2" />
                          <button 
                            onClick={() => { handleDelete(r.uid, r.email); setActionMenuOpen(null); }}
                            className="w-full text-left px-4 py-3.5 rounded-xl text-sm font-medium text-red-400 hover:bg-red-500/10 flex items-center transition-colors"
                          >
                            <Trash2 className="w-5 h-5 mr-3" /> Obriši Račun
                          </button>
                        </div>
                      </div>
                    </div>
                  );
                })()
              )}

              {/* Edit Reseller Modal */}
              {editingUser && (
                <div className="fixed inset-0 bg-black/80 backdrop-blur-sm flex items-center justify-center p-4 z-50">
                  <div className="bg-gray-900 border border-gray-800 rounded-2xl max-w-md w-full shadow-2xl overflow-hidden relative">
                    <div className="absolute top-0 left-0 w-full h-1 bg-gradient-to-r from-emerald-500 to-teal-500" />
                    <div className="p-8">
                      <div className="flex justify-between items-center mb-6">
                        <h2 className="text-2xl font-bold text-white">Uredi Resellera</h2>
                        <span className="text-xs bg-gray-800 text-gray-400 px-2 py-1 rounded-md font-mono">{editingUser.uid.slice(0, 8)}...</span>
                      </div>
                      
                      <form onSubmit={handleSaveEdit} className="space-y-5">
                        <div>
                          <label className="block text-sm font-medium text-gray-400 mb-1.5">Email adresa (Read-only)</label>
                          <input 
                            type="email" readOnly disabled
                            className="w-full bg-gray-800/50 border border-gray-700/50 rounded-xl p-3 text-gray-500 outline-none cursor-not-allowed" 
                            value={editingUser.email}
                          />
                        </div>
                        <div>
                          <label className="block text-sm font-medium text-gray-400 mb-1.5">Dostupni Krediti</label>
                          <input 
                            type="number" required min={0}
                            className="w-full bg-gray-950/50 border border-gray-700 rounded-xl p-3 text-white focus:ring-2 focus:ring-emerald-500/50 focus:border-emerald-500 outline-none transition-all" 
                            value={editCredits} onChange={e => setEditCredits(Number(e.target.value))} 
                          />
                        </div>
                        
                        <div className="flex space-x-3 pt-6 border-t border-gray-800">
                          <button 
                            type="button" onClick={() => setEditingUser(null)}
                            className="flex-1 bg-gray-800 hover:bg-gray-700 text-gray-300 py-3 rounded-xl font-medium transition-colors border border-gray-700"
                          >
                            Odustani
                          </button>
                          <button 
                            type="submit" disabled={isSavingEdit}
                            className="flex-1 bg-gradient-to-r from-emerald-600 to-teal-600 hover:from-emerald-500 hover:to-teal-500 text-white py-3 rounded-xl font-medium transition-all shadow-lg shadow-emerald-500/25 disabled:opacity-50"
                          >
                            {isSavingEdit ? 'Spremanje...' : 'Spremi Izmjene'}
                          </button>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              )}
            </div>
          )}


          {/* Analytics Tab */}
          {activeTab === 'analytics' && (
            <div className="bg-gray-900/40 backdrop-blur-md rounded-2xl p-8 border border-gray-800/60 shadow-xl">
              <div className="flex items-center justify-between mb-8">
                <h2 className="text-xl font-bold text-white flex items-center">
                  <div className="w-10 h-10 rounded-lg bg-blue-500/10 flex items-center justify-center mr-3 border border-blue-500/20">
                    <BarChart2 className="w-5 h-5 text-blue-400" />
                  </div>
                  Analitika Sustava
                </h2>
                <button onClick={fetchAnalytics} className="p-2 bg-gray-800/50 rounded-lg hover:bg-gray-700 text-gray-400 transition-colors">
                  <RefreshCw className={`w-4 h-4 ${isLoadingAnalytics ? 'animate-spin' : ''}`} />
                </button>
              </div>

              {isLoadingAnalytics ? (
                <div className="text-center text-gray-500 py-12">
                  <div className="w-8 h-8 border-4 border-blue-500/20 border-t-blue-500 rounded-full animate-spin mx-auto mb-4"></div>
                  Učitavanje analitike...
                </div>
              ) : analyticsData ? (
                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                  <div className="bg-gradient-to-br from-gray-800/40 to-gray-900/40 p-8 rounded-2xl border border-gray-700/50 flex flex-col relative overflow-hidden group">
                    <div className="absolute top-0 right-0 w-32 h-32 bg-blue-500/5 rounded-full blur-2xl -mr-10 -mt-10 group-hover:bg-blue-500/10 transition-colors" />
                    <Users className="w-8 h-8 text-blue-400 mb-6" />
                    <h3 className="text-gray-400 font-medium text-sm mb-1">Ukupno Resellera</h3>
                    <p className="text-5xl font-bold text-white tracking-tight">{analyticsData.totalResellers}</p>
                  </div>
                  <div className="bg-gradient-to-br from-gray-800/40 to-gray-900/40 p-8 rounded-2xl border border-gray-700/50 flex flex-col relative overflow-hidden group">
                    <div className="absolute top-0 right-0 w-32 h-32 bg-orange-500/5 rounded-full blur-2xl -mr-10 -mt-10 group-hover:bg-orange-500/10 transition-colors" />
                    <Coins className="w-8 h-8 text-orange-400 mb-6" />
                    <h3 className="text-gray-400 font-medium text-sm mb-1">Ukupno Kredita u Optjecaju</h3>
                    <p className="text-5xl font-bold text-white tracking-tight">{analyticsData.totalCreditsAllocated}</p>
                  </div>
                </div>
              ) : (
                <div className="text-center text-gray-500 py-8">Nema podataka za analitiku.</div>
              )}
            </div>
          )}

          {/* Logs Tab */}
          {activeTab === 'logs' && (
            <div className="bg-gray-900/40 backdrop-blur-md rounded-2xl p-6 border border-gray-800/60 shadow-xl flex flex-col h-[700px]">
              <div className="flex items-center justify-between mb-6 flex-shrink-0">
                <h2 className="text-xl font-bold text-white flex items-center">
                  <div className="w-10 h-10 rounded-lg bg-green-500/10 flex items-center justify-center mr-3 border border-green-500/20">
                    <Activity className="w-5 h-5 text-green-400" />
                  </div>
                  Globalni Logovi
                </h2>
                <button onClick={fetchLogs} className="p-2 bg-gray-800/50 rounded-lg hover:bg-gray-700 text-gray-400 transition-colors">
                  <RefreshCw className={`w-4 h-4 ${isLoadingLogs ? 'animate-spin' : ''}`} />
                </button>
              </div>

              {isLoadingLogs ? (
                <div className="text-center text-gray-500 py-12 flex-1 flex flex-col justify-center items-center">
                  <div className="w-8 h-8 border-4 border-green-500/20 border-t-green-500 rounded-full animate-spin mb-4"></div>
                  Učitavanje zapisa...
                </div>
              ) : logs.length > 0 ? (
                <div className="space-y-3 overflow-y-auto pr-2 custom-scrollbar flex-1">
                  {logs.map((log) => (
                    <div key={log.id} className="flex items-start p-5 bg-gray-800/30 hover:bg-gray-800/50 rounded-xl border border-gray-700/30 transition-colors group">
                      <div className="flex-shrink-0 mt-1">
                        <div className={`w-2.5 h-2.5 rounded-full ${log.role === 'admin' ? 'bg-purple-500 shadow-[0_0_8px_rgba(168,85,247,0.5)]' : 'bg-blue-500 shadow-[0_0_8px_rgba(59,130,246,0.5)]'}`}></div>
                      </div>
                      <div className="ml-4 flex-1">
                        <div className="flex justify-between items-start">
                          <p className="text-gray-200 font-semibold">{log.action}</p>
                          <span className={`text-[10px] px-2 py-1 rounded-md font-bold uppercase tracking-wider border ${
                            log.role === 'admin' 
                              ? 'bg-purple-500/10 text-purple-400 border-purple-500/20' 
                              : 'bg-blue-500/10 text-blue-400 border-blue-500/20'
                          }`}>
                            {log.role}
                          </span>
                        </div>
                        <p className="text-gray-400 text-sm mt-1.5">{log.details}</p>
                        <div className="flex justify-between items-center mt-4 pt-3 border-t border-gray-800">
                          <p className="text-gray-500 text-xs font-medium">{log.userEmail}</p>
                          <p className="text-gray-500 text-xs bg-gray-900/50 px-2 py-1 rounded-md">
                            {log.timestamp ? (
                              typeof log.timestamp?.toDate === 'function' 
                                ? format(log.timestamp.toDate(), "d. MMMM yyyy. 'u' HH:mm", { locale: hr }) 
                                : format(new Date(log.timestamp), "d. MMMM yyyy. 'u' HH:mm", { locale: hr })
                            ) : 'Upravo sada'}
                          </p>
                        </div>
                      </div>
                    </div>
                  ))}
                </div>
              ) : (
                <div className="text-center text-gray-500 py-12 flex-1 flex flex-col justify-center items-center border-2 border-dashed border-gray-800/60 rounded-xl">
                  <Activity className="w-12 h-12 text-gray-700 mb-3" />
                  <p>Nema zabilježenih aktivnosti.</p>
                </div>
              )}
            </div>
          )}

          {/* Settings Tab */}
          {activeTab === 'settings' && (
            <div className="bg-gray-900/40 backdrop-blur-md rounded-2xl border border-gray-800/60 shadow-xl overflow-hidden">
              <div className="p-6 border-b border-gray-800/60 bg-gray-900/40 flex items-center">
                <div className="w-10 h-10 rounded-lg bg-gray-800 flex items-center justify-center mr-3 border border-gray-700">
                  <Settings className="w-5 h-5 text-gray-400" />
                </div>
                <div>
                  <h2 className="font-bold text-xl text-white">Postavke Sustava</h2>
                  <p className="text-sm text-gray-400 mt-1">Konfiguracija i prilagodba Vopo platforme</p>
                </div>
              </div>
              
              <div className="p-8">
                <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
                  {/* General Settings */}
                  <div className="space-y-6">
                    <h3 className="text-lg font-semibold text-white border-b border-gray-800 pb-2">Opće Postavke</h3>
                    
                    <div className="flex items-center justify-between p-4 bg-gray-800/30 rounded-xl border border-gray-700/50">
                      <div>
                        <p className="text-white font-medium">Održavanje Sustava</p>
                        <p className="text-gray-400 text-sm mt-1">Uključi mod održavanja (onemogućava prijave resellerima)</p>
                      </div>
                      <label className="relative inline-flex items-center cursor-pointer">
                        <input type="checkbox" className="sr-only peer" />
                        <div className="w-11 h-6 bg-gray-700 peer-focus:outline-none rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-500"></div>
                      </label>
                    </div>

                    <div className="flex items-center justify-between p-4 bg-gray-800/30 rounded-xl border border-gray-700/50">
                      <div>
                        <p className="text-white font-medium">Javna Registracija</p>
                        <p className="text-gray-400 text-sm mt-1">Dozvoli korisnicima da se sami registriraju na portal</p>
                      </div>
                      <label className="relative inline-flex items-center cursor-pointer">
                        <input type="checkbox" className="sr-only peer" defaultChecked />
                        <div className="w-11 h-6 bg-gray-700 peer-focus:outline-none rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-blue-500"></div>
                      </label>
                    </div>
                  </div>

                  {/* Appearance */}
                  <div className="space-y-6">
                    <h3 className="text-lg font-semibold text-white border-b border-gray-800 pb-2">Izgled</h3>
                    
                    <div className="space-y-3">
                      <label className="block text-sm font-medium text-gray-400">Glavna Tema</label>
                      <div className="grid grid-cols-2 gap-3">
                        <button className="flex items-center justify-center py-3 bg-gray-800 border-2 border-blue-500 text-white rounded-xl font-medium">
                          Dark Mode
                        </button>
                        <button className="flex items-center justify-center py-3 bg-gray-800/50 border-2 border-transparent text-gray-400 hover:text-white rounded-xl font-medium transition-colors opacity-50 cursor-not-allowed" title="Light mode is coming soon">
                          Light Mode
                        </button>
                      </div>
                    </div>

                    <div className="space-y-3">
                      <label className="block text-sm font-medium text-gray-400">Jezik Sustava</label>
                      <select className="w-full bg-gray-900 border border-gray-700 text-white rounded-xl p-3 outline-none focus:border-blue-500">
                        <option value="hr">Hrvatski</option>
                        <option value="en">English</option>
                        <option value="de">Deutsch</option>
                      </select>
                    </div>
                  </div>
                </div>

                <div className="mt-8 pt-6 border-t border-gray-800 flex justify-end">
                  <button className="bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-500 hover:to-indigo-500 text-white px-6 py-2.5 rounded-xl font-medium shadow-lg shadow-blue-500/25 transition-all">
                    Spremi Promjene
                  </button>
                </div>
              </div>
            </div>
          )}
        </div>
      </AdminLayout>
    </ProtectedRoute>
  );
}
