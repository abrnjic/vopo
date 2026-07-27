"use client";

import { useState, useEffect } from 'react';
import { Users, Plus, ShieldCheck, Coins, RefreshCw, Activity, Settings, Edit, Trash2, Key, Ban, CheckCircle, Home, Server, TrendingUp, User, Globe, Upload } from 'lucide-react';
import { collection, query, where, getDocs, orderBy, limit } from 'firebase/firestore';
import { db } from '../../firebase';
import { initializeApp } from 'firebase/app';
import { getAuth, createUserWithEmailAndPassword, signOut, sendPasswordResetEmail } from 'firebase/auth';
import { logActivity, ActivityAction } from '../../utils/activityLogger';
import { useAuth } from '../../context/AuthContext';
import AdminLayout from '../../components/AdminLayout';
import ProtectedRoute from '../../components/ProtectedRoute';
import { format } from 'date-fns';
import { upload } from '@vercel/blob/client';
import { hr } from 'date-fns/locale';

const secondaryApp = initializeApp({
  apiKey: process.env.NEXT_PUBLIC_FIREBASE_API_KEY,
  authDomain: process.env.NEXT_PUBLIC_FIREBASE_AUTH_DOMAIN,
  projectId: process.env.NEXT_PUBLIC_FIREBASE_PROJECT_ID,
  storageBucket: process.env.NEXT_PUBLIC_FIREBASE_STORAGE_BUCKET,
  messagingSenderId: process.env.NEXT_PUBLIC_FIREBASE_MESSAGING_SENDER_ID,
  appId: process.env.NEXT_PUBLIC_FIREBASE_APP_ID
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
  const [activeTab, setActiveTab] = useState<'home' | 'resellers' | 'logs' | 'settings'>('home');
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

  // APK Upload States
  const [apkFile, setApkFile] = useState<File | null>(null);
  const [apkVersionName, setApkVersionName] = useState('');
  const [apkVersionCode, setApkVersionCode] = useState('');
  const [isUploadingApk, setIsUploadingApk] = useState(false);
  const [apkUploadMessage, setApkUploadMessage] = useState<{type: 'success'|'error', text: string} | null>(null);

  // New states for actions
  const [actionMenuOpen, setActionMenuOpen] = useState<string | null>(null);
  const [editingUser, setEditingUser] = useState<ResellerData | null>(null);
  const [editCredits, setEditCredits] = useState(0);
  const [isSavingEdit, setIsSavingEdit] = useState(false);

  const handleApkUpload = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!apkFile || !apkVersionName || !apkVersionCode) {
      setApkUploadMessage({ type: 'error', text: 'Molimo popunite sva polja i odaberite APK datoteku.' });
      return;
    }

    if (!apkFile.name.endsWith('.apk')) {
      setApkUploadMessage({ type: 'error', text: 'Samo .apk datoteke su dozvoljene.' });
      return;
    }

    setIsUploadingApk(true);
    setApkUploadMessage(null);

    try {
      const idToken = await user?.getIdToken();

      // Calculate SHA-256 client-side
      const arrayBuffer = await apkFile.arrayBuffer();
      const hashBuffer = await crypto.subtle.digest('SHA-256', arrayBuffer);
      const hashArray = Array.from(new Uint8Array(hashBuffer));
      const checksum = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');

      const safeVersionName = apkVersionName.replace(/[^a-zA-Z0-9.-]/g, '');

      // Mock Local testing
      if (process.env.NEXT_PUBLIC_MOCK_FIREBASE === 'true') {
         const mockRes = await fetch('/api/admin/apk', {
           method: 'POST',
           headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${idToken}` },
           body: JSON.stringify({
             type: "blob.generate-client-token",
             payload: {
                pathname: `apk/releases/vopoapp-${safeVersionName}-${apkVersionCode}.apk`,
                clientPayload: JSON.stringify({ versionName: safeVersionName, versionCode: apkVersionCode, checksum })
             }
           })
         });
         const mockData = await mockRes.json();
         if (!mockRes.ok) throw new Error(mockData.error || 'Mock error');

         // Mock upload completed webhook
         const mockWebhookRes = await fetch('/api/admin/apk', {
           method: 'POST',
           headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${idToken}` },
           body: JSON.stringify({
             type: "blob.upload-completed",
             payload: {
               blob: { url: "https://mock-blob.com/test.apk", size: apkFile.size, pathname: `apk/releases/vopoapp-${safeVersionName}-${apkVersionCode}.apk` },
               tokenPayload: mockData.clientToken ? JSON.parse(atob(mockData.clientToken.split('.')[1] || 'e30=')).tokenPayload : JSON.stringify({ versionName: safeVersionName, versionCode: apkVersionCode, checksum, uid: user?.uid, email: user?.email })
             }
           })
         });

         const mockWebhookData = await mockWebhookRes.json();
         if (!mockWebhookRes.ok) throw new Error(mockWebhookData.error || 'Mock webhook error');

         setApkUploadMessage({ type: 'success', text: 'APK uspješno prenesen i objavljen. (MOCK)' });
         setApkFile(null);
         setApkVersionName('');
         setApkVersionCode('');
         return;
      }

      await upload(`apk/releases/vopoapp-${safeVersionName}-${apkVersionCode}.apk`, apkFile, {
        access: 'public',
        handleUploadUrl: '/api/admin/apk',
        clientPayload: JSON.stringify({ versionName: safeVersionName, versionCode: apkVersionCode, checksum }),
        headers: {
           Authorization: `Bearer ${idToken}`
        }
      });

      setApkUploadMessage({ type: 'success', text: 'Upload završen. Provjera i objava u tijeku...' });

      let attempts = 0;
      let isPublished = false;
      while (attempts < 15) {
        await new Promise(r => setTimeout(r, 2000));
        const checkRes = await fetch('/api/apk/latest', { cache: 'no-store' });
        if (checkRes.ok) {
           const data = await checkRes.json();
           if (data.versionCode === parseInt(apkVersionCode, 10) && data.checksum === checksum) {
              isPublished = true;
              break;
           }
        }
        attempts++;
      }

      if (isPublished) {
         setApkUploadMessage({ type: 'success', text: `APK (v${safeVersionName}) uspješno provjeren i objavljen.` });
         setApkFile(null);
         setApkVersionName('');
         setApkVersionCode('');
      } else {
         setApkUploadMessage({ type: 'error', text: 'Objava nije potvrđena unutar očekivanog vremena. Provjerite stanje (moguće odbijeno na provjeri integriteta).' });
      }

    } catch (error: any) {
      setApkUploadMessage({ type: 'error', text: error.message || 'Greška prilikom prijenosa' });
    } finally {
      setIsUploadingApk(false);
    }
  };

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
      const q = query(collection(db, 'users'), where('role', '==', 'reseller'));
      const snap = await getDocs(q);
      let totalCredits = 0;
      snap.forEach(doc => {
          totalCredits += (doc.data().credits || 0);
      });
      setAnalyticsData({
          totalResellers: snap.size,
          totalCreditsAllocated: totalCredits,
          activeNodes: 3, // Mock data for premium feel
          uptime: "99.9%"
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

      const idToken = await user?.getIdToken();
      const res = await fetch('/api/admin/users', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${idToken}`
        },
        body: JSON.stringify({
          action: 'create',
          targetUserId: newUid,
          data: {
            email: newEmail,
            role: 'reseller',
            credits: newCredits,
            assignedDomains: [],
            customDomains: [],
            status: 'active'
          }
        })
      });

      if (!res.ok) {
        throw new Error('Neuspješno spremanje korisnika u bazu');
      }

      setShowAddModal(false);
      setNewEmail('');
      setNewPassword('');
      setNewCredits(0);
      fetchResellers();
      fetchAnalytics();
    } catch (error: any) {
      console.error("Greška pri kreiranju", error);
      alert(`Došlo je do greške: ${error.message}`);
    } finally {
      setIsCreating(false);
    }
  };

  const handleSuspend = async (uid: string, currentStatus: string | undefined) => {
    const newStatus = currentStatus === 'suspended' ? 'active' : 'suspended';
    if (confirm(`Želite li promijeniti status u ${newStatus}?`)) {
      try {
        const idToken = await user?.getIdToken();
        const res = await fetch('/api/admin/users', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${idToken}`
          },
          body: JSON.stringify({
            action: 'update_status',
            targetUserId: uid,
            data: { status: newStatus }
          })
        });

        if (!res.ok) throw new Error('Failed to update status');

        fetchResellers();
      } catch (error) {
        console.error('Error toggling user status:', error);
      }
    }
    setActionMenuOpen(null);
  };

  const handleDelete = async (uid: string) => {
    if (confirm('Jeste li sigurni da želite izbrisati ovog korisnika? (Ovo će samo označiti korisnika kao obrisanog)')) {
      try {
        const idToken = await user?.getIdToken();
        const res = await fetch('/api/admin/users', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${idToken}`
          },
          body: JSON.stringify({
            action: 'update_status',
            targetUserId: uid,
            data: { status: 'deleted' }
          })
        });

        if (!res.ok) throw new Error('Failed to delete user');

        fetchResellers();
        fetchAnalytics();
      } catch (error) {
        console.error('Error deleting user:', error);
      }
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

      // Call API
      const idToken = await user?.getIdToken();
      const res = await fetch('/api/admin/credits', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${idToken}`
        },
        body: JSON.stringify({
          targetUserId: editingUser.uid,
          newCredits: editCredits,
          actionType,
          logMessage
        })
      });

      if (!res.ok) {
        const errorData = await res.json();
        throw new Error(errorData.error || 'Neuspješno ažuriranje kredita');
      }
      setEditingUser(null);
      fetchResellers();
      fetchAnalytics();
      fetchLogs();
    } catch (error: any) {
      alert(`Greška: ${error.message}`);
    } finally {
      setIsSavingEdit(false);
    }
  };

  return (
    <ProtectedRoute allowedRoles={['admin']}>
      <AdminLayout>
        <div className="space-y-6 pb-20">

          {/* Header & Tabs */}
          <div className="flex flex-col md:flex-row md:items-center justify-between bg-gray-900/40 backdrop-blur-xl p-6 rounded-3xl border border-gray-700/50 shadow-2xl relative overflow-hidden">
            <div className="absolute top-0 right-0 w-64 h-64 bg-blue-500/10 rounded-full blur-[80px] -mr-20 -mt-20 pointer-events-none" />
            <div className="relative z-10">
              <h1 className="text-3xl font-extrabold flex items-center text-transparent bg-clip-text bg-gradient-to-r from-white to-gray-400">
                <div className="w-12 h-12 rounded-xl bg-gradient-to-br from-purple-500/20 to-blue-600/20 flex items-center justify-center mr-4 border border-purple-500/30 shadow-[0_0_25px_rgba(168,85,247,0.2)]">
                  <ShieldCheck className="w-7 h-7 text-purple-400" />
                </div>
                Admin Centar
              </h1>
              <p className="text-gray-400 mt-2 text-sm ml-16 font-medium tracking-wide">Premium nadzorna ploča sustava</p>
            </div>

            <div className="relative z-10 flex space-x-2 mt-6 md:mt-0 overflow-x-auto pb-2 md:pb-0 bg-gray-950/60 p-1.5 rounded-2xl border border-gray-800/80 shadow-inner">
              <button
                onClick={() => setActiveTab('home')}
                className={`px-5 py-2.5 rounded-xl font-semibold flex items-center whitespace-nowrap transition-all duration-300 text-sm ${activeTab === 'home' ? 'bg-gradient-to-r from-blue-600 to-indigo-600 text-white shadow-lg shadow-blue-500/30 scale-105' : 'text-gray-400 hover:text-white hover:bg-gray-800/80'}`}
              >
                <Home className="w-4 h-4 mr-2" /> Početna
              </button>
              <button
                onClick={() => setActiveTab('resellers')}
                className={`px-5 py-2.5 rounded-xl font-semibold flex items-center whitespace-nowrap transition-all duration-300 text-sm ${activeTab === 'resellers' ? 'bg-gradient-to-r from-blue-600 to-indigo-600 text-white shadow-lg shadow-blue-500/30 scale-105' : 'text-gray-400 hover:text-white hover:bg-gray-800/80'}`}
              >
                <Users className="w-4 h-4 mr-2" /> Reselleri
              </button>
              <button
                onClick={() => setActiveTab('logs')}
                className={`px-5 py-2.5 rounded-xl font-semibold flex items-center whitespace-nowrap transition-all duration-300 text-sm ${activeTab === 'logs' ? 'bg-gradient-to-r from-blue-600 to-indigo-600 text-white shadow-lg shadow-blue-500/30 scale-105' : 'text-gray-400 hover:text-white hover:bg-gray-800/80'}`}
              >
                <Activity className="w-4 h-4 mr-2" /> Logovi
              </button>
              <button
                onClick={() => setActiveTab('settings')}
                className={`px-5 py-2.5 rounded-xl font-semibold flex items-center whitespace-nowrap transition-all duration-300 text-sm ${activeTab === 'settings' ? 'bg-gradient-to-r from-blue-600 to-indigo-600 text-white shadow-lg shadow-blue-500/30 scale-105' : 'text-gray-400 hover:text-white hover:bg-gray-800/80'}`}
              >
                <Settings className="w-4 h-4 mr-2" /> Postavke
              </button>
            </div>
          </div>

          {/* Home Tab */}
          {activeTab === 'home' && (
            <div className="space-y-6 animate-in fade-in slide-in-from-bottom-4 duration-500">
              {isLoadingAnalytics ? (
                <div className="flex justify-center py-20">
                  <div className="w-10 h-10 border-4 border-blue-500/30 border-t-blue-500 rounded-full animate-spin" />
                </div>
              ) : (
                <>
                  <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
                    <div className="bg-gray-900/40 backdrop-blur-xl p-6 rounded-3xl border border-gray-700/50 shadow-xl relative overflow-hidden group">
                      <div className="absolute -right-6 -top-6 w-24 h-24 bg-blue-500/20 rounded-full blur-xl group-hover:bg-blue-500/30 transition-all duration-500" />
                      <div className="w-12 h-12 bg-blue-500/20 rounded-2xl flex items-center justify-center mb-4 border border-blue-500/30">
                        <Users className="w-6 h-6 text-blue-400" />
                      </div>
                      <p className="text-gray-400 text-sm font-medium mb-1">Aktivni Reselleri</p>
                      <h3 className="text-3xl font-bold text-white">{analyticsData?.totalResellers || 0}</h3>
                    </div>

                    <div className="bg-gray-900/40 backdrop-blur-xl p-6 rounded-3xl border border-gray-700/50 shadow-xl relative overflow-hidden group">
                      <div className="absolute -right-6 -top-6 w-24 h-24 bg-orange-500/20 rounded-full blur-xl group-hover:bg-orange-500/30 transition-all duration-500" />
                      <div className="w-12 h-12 bg-orange-500/20 rounded-2xl flex items-center justify-center mb-4 border border-orange-500/30">
                        <Coins className="w-6 h-6 text-orange-400" />
                      </div>
                      <p className="text-gray-400 text-sm font-medium mb-1">Krediti u Optjecaju</p>
                      <h3 className="text-3xl font-bold text-white">{analyticsData?.totalCreditsAllocated || 0}</h3>
                    </div>

                    <div className="bg-gray-900/40 backdrop-blur-xl p-6 rounded-3xl border border-gray-700/50 shadow-xl relative overflow-hidden group">
                      <div className="absolute -right-6 -top-6 w-24 h-24 bg-purple-500/20 rounded-full blur-xl group-hover:bg-purple-500/30 transition-all duration-500" />
                      <div className="w-12 h-12 bg-purple-500/20 rounded-2xl flex items-center justify-center mb-4 border border-purple-500/30">
                        <TrendingUp className="w-6 h-6 text-purple-400" />
                      </div>
                      <p className="text-gray-400 text-sm font-medium mb-1">Mjesečni Rast</p>
                      <h3 className="text-3xl font-bold text-white">+12.4%</h3>
                    </div>

                    <div className="bg-gray-900/40 backdrop-blur-xl p-6 rounded-3xl border border-gray-700/50 shadow-xl relative overflow-hidden group">
                      <div className="absolute -right-6 -top-6 w-24 h-24 bg-emerald-500/20 rounded-full blur-xl group-hover:bg-emerald-500/30 transition-all duration-500" />
                      <div className="w-12 h-12 bg-emerald-500/20 rounded-2xl flex items-center justify-center mb-4 border border-emerald-500/30">
                        <Server className="w-6 h-6 text-emerald-400" />
                      </div>
                      <p className="text-gray-400 text-sm font-medium mb-1">Status Sustava</p>
                      <h3 className="text-3xl font-bold text-white">Online</h3>
                    </div>
                  </div>

                  <div className="bg-gray-900/40 backdrop-blur-xl p-8 rounded-3xl border border-gray-700/50 shadow-xl">
                    <h3 className="text-xl font-bold text-white mb-6 flex items-center">
                      <Activity className="w-5 h-5 mr-3 text-blue-400" />
                      Zadnje Aktivnosti
                    </h3>
                    <div className="space-y-4">
                      {logs.slice(0, 5).map(log => (
                        <div key={log.id} className="flex justify-between items-center p-4 bg-gray-800/40 rounded-2xl border border-gray-700/30 hover:bg-gray-800/60 transition-colors">
                          <div className="flex items-center">
                            <div className={`w-2 h-2 rounded-full mr-4 ${log.role === 'admin' ? 'bg-purple-500 shadow-[0_0_8px_rgba(168,85,247,0.8)]' : 'bg-blue-500 shadow-[0_0_8px_rgba(59,130,246,0.8)]'}`} />
                            <div>
                              <p className="text-gray-200 font-medium">{log.action}</p>
                              <p className="text-sm text-gray-500">{log.details}</p>
                            </div>
                          </div>
                          <span className="text-xs text-gray-500 bg-gray-900/50 px-3 py-1.5 rounded-full border border-gray-800">
                             {log.timestamp ? (
                                typeof log.timestamp?.toDate === 'function'
                                  ? format(log.timestamp.toDate(), "d. MMM HH:mm", { locale: hr })
                                  : format(new Date(log.timestamp), "d. MMM HH:mm", { locale: hr })
                              ) : 'Nedavno'}
                          </span>
                        </div>
                      ))}
                    </div>
                  </div>
                </>
              )}
            </div>
          )}

          {/* Resellers Tab */}
          {activeTab === 'resellers' && (
            <div className="space-y-6 animate-in fade-in slide-in-from-bottom-4 duration-500">
              <div className="flex justify-end mb-6">
                <button
                  onClick={fetchResellers}
                  className="bg-gray-800/80 hover:bg-gray-700/80 border border-gray-700 text-gray-300 px-4 py-2.5 rounded-xl font-medium flex items-center transition-all mr-3 backdrop-blur-sm"
                >
                  <RefreshCw className={`w-4 h-4 ${loading ? 'animate-spin' : ''}`} />
                </button>
                <button
                  onClick={() => setShowAddModal(true)}
                  className="bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-500 hover:to-indigo-500 text-white px-5 py-2.5 rounded-xl font-medium flex items-center shadow-lg shadow-blue-500/30 transition-all transform hover:-translate-y-0.5"
                >
                  <Plus className="w-5 h-5 mr-1.5" />
                  Novi Reseller
                </button>
              </div>

              <div className="bg-gray-900/40 backdrop-blur-xl rounded-3xl border border-gray-700/50 overflow-hidden shadow-2xl">
                <div className="overflow-x-auto">
                  <table className="w-full text-left text-white">
                    <thead className="bg-gray-900/80 text-gray-400 text-xs uppercase tracking-wider">
                      <tr>
                        <th className="px-8 py-5 font-semibold">Ime / Email</th>
                        <th className="px-8 py-5 font-semibold">Krediti</th>
                        <th className="px-8 py-5 font-semibold">Domene / Status</th>
                        <th className="px-8 py-5 font-semibold text-right">Akcije</th>
                      </tr>
                    </thead>
                    <tbody className="divide-y divide-gray-800/50">
                      {resellers.map((r) => (
                        <tr key={r.uid} className="hover:bg-gray-800/40 transition-colors group">
                          <td className="px-8 py-5">
                            <div className="flex items-center">
                              <div className="w-12 h-12 rounded-2xl bg-gradient-to-br from-gray-800 to-gray-700 flex items-center justify-center mr-4 border border-gray-600/50 shadow-md">
                                <span className="text-gray-200 font-bold text-lg">{r.email.charAt(0).toUpperCase()}</span>
                              </div>
                              <div>
                                <div className="font-bold text-gray-100 text-base">{r.email}</div>
                                <div className="text-xs text-gray-500 mt-1 font-mono tracking-wide">ID: {r.uid.slice(0,8)}...</div>
                              </div>
                            </div>
                          </td>
                          <td className="px-8 py-5">
                            <div className="flex items-center">
                              <div className="w-8 h-8 rounded-full bg-orange-500/10 flex items-center justify-center mr-3 border border-orange-500/20">
                                <Coins className="w-4 h-4 text-orange-400" />
                              </div>
                              <span className="font-bold text-xl text-white">{r.credits}</span>
                            </div>
                          </td>
                            <td className="px-8 py-5">
                            <div className="flex items-center space-x-3">
                              <span className="bg-gray-800/80 text-gray-300 text-xs font-semibold px-3 py-1.5 rounded-lg border border-gray-700/50">
                                {r.assignedDomains.length} domena
                              </span>
                              {r.status === 'suspended' && (
                                <span className="bg-red-500/10 text-red-400 text-xs font-semibold px-3 py-1.5 rounded-lg border border-red-500/20 flex items-center">
                                  <Ban className="w-3 h-3 mr-1" /> Suspendiran
                                </span>
                              )}
                            </div>
                          </td>
                          <td className="px-8 py-5 text-right">
                            <button
                              onClick={() => setActionMenuOpen(r.uid)}
                              className="px-4 py-2 text-sm font-medium text-gray-300 hover:text-white bg-gray-800/50 hover:bg-blue-600 rounded-xl transition-all border border-gray-700/50 hover:border-blue-500 hover:shadow-lg hover:shadow-blue-500/20"
                            >
                              Upravljaj
                            </button>
                          </td>
                        </tr>
                      ))}
                      {resellers.length === 0 && !loading && (
                        <tr>
                          <td colSpan={4} className="px-8 py-16 text-center text-gray-500">
                            <div className="flex flex-col items-center justify-center">
                              <Users className="w-16 h-16 text-gray-700 mb-4 opacity-50" />
                              <p className="text-lg">Nema kreiranih resellera.</p>
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
                <div className="fixed inset-0 bg-black/80 backdrop-blur-md flex items-center justify-center p-4 z-50">
                  <div className="bg-gray-900 border border-gray-700 rounded-3xl max-w-md w-full shadow-2xl overflow-hidden relative animate-in zoom-in-95 duration-200">
                    <div className="absolute top-0 left-0 w-full h-1.5 bg-gradient-to-r from-blue-500 to-indigo-500" />
                    <div className="p-8">
                      <h2 className="text-2xl font-bold text-white mb-2">Kreiraj Resellera</h2>
                      <p className="text-gray-400 text-sm mb-8">Unesite osnovne podatke za novog korisnika.</p>

                      <form onSubmit={handleCreateReseller} className="space-y-5">
                        <div>
                          <label className="block text-sm font-semibold text-gray-400 mb-2">Email adresa</label>
                          <input
                            type="email" required
                            placeholder="admin@tvrtka.com"
                            className="w-full bg-gray-950/60 border border-gray-700 rounded-2xl p-3.5 text-white focus:ring-2 focus:ring-blue-500/50 focus:border-blue-500 outline-none transition-all placeholder:text-gray-600"
                            value={newEmail} onChange={e => setNewEmail(e.target.value)}
                          />
                        </div>
                        <div>
                          <label className="block text-sm font-semibold text-gray-400 mb-2">Lozinka</label>
                          <input
                            type="password" required minLength={6}
                            placeholder="••••••••"
                            className="w-full bg-gray-950/60 border border-gray-700 rounded-2xl p-3.5 text-white focus:ring-2 focus:ring-blue-500/50 focus:border-blue-500 outline-none transition-all placeholder:text-gray-600"
                            value={newPassword} onChange={e => setNewPassword(e.target.value)}
                          />
                        </div>
                        <div>
                          <label className="block text-sm font-semibold text-gray-400 mb-2">Početni Krediti</label>
                          <input
                            type="number" required min={0}
                            className="w-full bg-gray-950/60 border border-gray-700 rounded-2xl p-3.5 text-white focus:ring-2 focus:ring-blue-500/50 focus:border-blue-500 outline-none transition-all"
                            value={newCredits} onChange={e => setNewCredits(Number(e.target.value))}
                          />
                        </div>
                        <div className="flex space-x-3 pt-6 mt-4 border-t border-gray-800">
                          <button
                            type="button" onClick={() => setShowAddModal(false)}
                            className="flex-1 bg-gray-800 hover:bg-gray-700 text-gray-300 py-3.5 rounded-2xl font-bold transition-colors border border-gray-700"
                          >
                            Odustani
                          </button>
                          <button
                            type="submit" disabled={isCreating}
                            className="flex-1 bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-500 hover:to-indigo-500 text-white py-3.5 rounded-2xl font-bold transition-all shadow-lg shadow-blue-500/30 disabled:opacity-50"
                          >
                            {isCreating ? 'Kreiranje...' : 'Kreiraj Korisnika'}
                          </button>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              )}

              {/* Advanced Action Menu Modal */}
              {actionMenuOpen && (
                (() => {
                  const r = resellers.find(user => user.uid === actionMenuOpen);
                  if (!r) return null;
                  return (
                    <div className="fixed inset-0 bg-black/80 backdrop-blur-md flex items-end sm:items-center justify-center p-4 z-[60]">
                      <div className="bg-gray-900 border border-gray-700 sm:rounded-3xl rounded-t-3xl max-w-sm w-full shadow-2xl overflow-hidden animate-in slide-in-from-bottom-10 sm:zoom-in-95 duration-200 pb-safe relative">
                        <div className="absolute top-0 left-0 w-full h-1.5 bg-gradient-to-r from-gray-600 to-gray-500" />
                        <div className="p-6 border-b border-gray-800 flex justify-between items-center bg-gray-800/40">
                          <div>
                            <h3 className="text-xl font-bold text-white">Upravljanje</h3>
                            <p className="text-sm text-gray-400 mt-1 truncate max-w-[200px]">{r.email}</p>
                          </div>
                          <button
                            onClick={() => setActionMenuOpen(null)}
                            className="p-2 bg-gray-800 hover:bg-gray-700 text-gray-400 hover:text-white rounded-full transition-colors border border-gray-700/50"
                          >
                            ✕
                          </button>
                        </div>
                        <div className="p-4 space-y-2">
                          <button
                            onClick={() => { setEditingUser(r); setEditCredits(r.credits); setActionMenuOpen(null); }}
                            className="w-full text-left px-5 py-4 rounded-2xl text-sm font-semibold text-gray-200 hover:bg-gray-800 flex items-center transition-colors group"
                          >
                            <div className="w-8 h-8 rounded-full bg-blue-500/10 flex items-center justify-center mr-4 group-hover:bg-blue-500/20 transition-colors">
                              <Edit className="w-4 h-4 text-blue-400" />
                            </div>
                            Uredi Kredite i Podatke
                          </button>

                          <button
                            onClick={() => { handlePasswordReset(r.email); setActionMenuOpen(null); }}
                            className="w-full text-left px-5 py-4 rounded-2xl text-sm font-semibold text-gray-200 hover:bg-gray-800 flex items-center transition-colors group"
                          >
                            <div className="w-8 h-8 rounded-full bg-purple-500/10 flex items-center justify-center mr-4 group-hover:bg-purple-500/20 transition-colors">
                              <Key className="w-4 h-4 text-purple-400" />
                            </div>
                            Pošalji Reset Lozinke
                          </button>

                          <button
                            onClick={() => { handleSuspend(r.uid, r.status); setActionMenuOpen(null); }}
                            className={`w-full text-left px-5 py-4 rounded-2xl text-sm font-semibold flex items-center transition-colors hover:bg-gray-800 group ${r.status === 'suspended' ? 'text-green-400' : 'text-orange-400'}`}
                          >
                            <div className={`w-8 h-8 rounded-full flex items-center justify-center mr-4 transition-colors ${r.status === 'suspended' ? 'bg-green-500/10 group-hover:bg-green-500/20' : 'bg-orange-500/10 group-hover:bg-orange-500/20'}`}>
                              {r.status === 'suspended' ? <CheckCircle className="w-4 h-4" /> : <Ban className="w-4 h-4" />}
                            </div>
                            {r.status === 'suspended' ? 'Aktiviraj Račun' : 'Suspendiraj Račun'}
                          </button>

                          <div className="h-px bg-gray-800 my-4 mx-2" />

                          <button
                            onClick={() => { handleDelete(r.uid); setActionMenuOpen(null); }}
                            className="w-full text-left px-5 py-4 rounded-2xl text-sm font-semibold text-red-400 hover:bg-red-500/10 flex items-center transition-colors group"
                          >
                            <div className="w-8 h-8 rounded-full bg-red-500/10 flex items-center justify-center mr-4 group-hover:bg-red-500/20 transition-colors">
                              <Trash2 className="w-4 h-4" />
                            </div>
                            Obriši Račun Trajno
                          </button>
                        </div>
                      </div>
                    </div>
                  );
                })()
              )}

              {/* Advanced Edit Reseller Modal */}
              {editingUser && (
                <div className="fixed inset-0 bg-black/80 backdrop-blur-md flex items-center justify-center p-4 z-50">
                  <div className="bg-gray-900 border border-gray-700 rounded-3xl max-w-md w-full shadow-2xl overflow-hidden relative animate-in zoom-in-95 duration-200">
                    <div className="absolute top-0 left-0 w-full h-1.5 bg-gradient-to-r from-emerald-500 to-teal-500" />
                    <div className="p-8">
                      <div className="flex justify-between items-center mb-6 border-b border-gray-800 pb-4">
                        <div>
                          <h2 className="text-2xl font-bold text-white">Uredi Resellera</h2>
                          <p className="text-gray-400 text-sm mt-1">{editingUser.email}</p>
                        </div>
                        <span className="text-xs bg-gray-800 border border-gray-700 text-gray-400 px-3 py-1.5 rounded-lg font-mono">{editingUser.uid.slice(0, 8)}...</span>
                      </div>

                      <form onSubmit={handleSaveEdit} className="space-y-6">
                        <div>
                          <label className="block text-sm font-semibold text-gray-400 mb-2">Email adresa (Zaključano)</label>
                          <input
                            type="email" readOnly disabled
                            className="w-full bg-gray-800/50 border border-gray-700/50 rounded-2xl p-3.5 text-gray-500 outline-none cursor-not-allowed"
                            value={editingUser.email}
                          />
                        </div>
                        <div>
                          <label className="block text-sm font-semibold text-gray-400 mb-2 flex justify-between">
                            <span>Dostupni Krediti</span>
                            <span className="text-emerald-500">Trenutno: {editingUser.credits}</span>
                          </label>
                          <input
                            type="number" required min={0}
                            className="w-full bg-gray-950/60 border border-gray-700 rounded-2xl p-3.5 text-white focus:ring-2 focus:ring-emerald-500/50 focus:border-emerald-500 outline-none transition-all text-lg font-bold"
                            value={editCredits} onChange={e => setEditCredits(Number(e.target.value))}
                          />
                          <p className="text-xs text-gray-500 mt-2">
                            * Sve promjene kredita bit će trajno zabilježene u Activity logu s imenom admina koji je izvršio promjenu.
                          </p>
                        </div>

                        <div className="flex space-x-3 pt-6 border-t border-gray-800 mt-6">
                          <button
                            type="button" onClick={() => setEditingUser(null)}
                            className="flex-1 bg-gray-800 hover:bg-gray-700 text-gray-300 py-3.5 rounded-2xl font-bold transition-colors border border-gray-700"
                          >
                            Odustani
                          </button>
                          <button
                            type="submit" disabled={isSavingEdit || editCredits === editingUser.credits}
                            className="flex-1 bg-gradient-to-r from-emerald-600 to-teal-600 hover:from-emerald-500 hover:to-teal-500 text-white py-3.5 rounded-2xl font-bold transition-all shadow-lg shadow-emerald-500/30 disabled:opacity-50"
                          >
                            {isSavingEdit ? 'Spremanje...' : 'Spremi Promjene'}
                          </button>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              )}
            </div>
          )}

          {/* Logs Tab */}
          {activeTab === 'logs' && (
            <div className="bg-gray-900/40 backdrop-blur-xl rounded-3xl p-6 md:p-8 border border-gray-700/50 shadow-2xl flex flex-col h-[750px] animate-in fade-in duration-500">
              <div className="flex flex-col sm:flex-row sm:items-center justify-between mb-8 flex-shrink-0 border-b border-gray-800/80 pb-6">
                <div>
                  <h2 className="text-2xl font-bold text-white flex items-center">
                    <div className="w-12 h-12 rounded-xl bg-green-500/10 flex items-center justify-center mr-4 border border-green-500/20 shadow-inner">
                      <Activity className="w-6 h-6 text-green-400" />
                    </div>
                    Globalni Revizorski Zapis
                  </h2>
                  <p className="text-gray-400 text-sm mt-2 ml-16">Pregled svih financijskih i sigurnosnih akcija u sustavu</p>
                </div>
                <button onClick={fetchLogs} className="mt-4 sm:mt-0 px-4 py-2.5 bg-gray-800 hover:bg-gray-700 border border-gray-700 rounded-xl text-gray-300 transition-colors flex items-center font-medium shadow-sm">
                  <RefreshCw className={`w-4 h-4 mr-2 ${isLoadingLogs ? 'animate-spin' : ''}`} />
                  Osvježi zapise
                </button>
              </div>

              {isLoadingLogs ? (
                <div className="text-center text-gray-500 py-12 flex-1 flex flex-col justify-center items-center">
                  <div className="w-10 h-10 border-4 border-green-500/20 border-t-green-500 rounded-full animate-spin mb-4"></div>
                  Dohvaćanje kriptiranih zapisa...
                </div>
              ) : logs.length > 0 ? (
                <div className="space-y-4 overflow-y-auto pr-2 custom-scrollbar flex-1 pb-4">
                  {logs.map((log) => (
                    <div key={log.id} className="flex items-start p-5 bg-gray-900/60 hover:bg-gray-800/80 rounded-2xl border border-gray-700/40 transition-all duration-300 hover:border-gray-600/50 shadow-sm">
                      <div className="flex-shrink-0 mt-1">
                        <div className={`w-12 h-12 rounded-xl flex items-center justify-center border ${
                          log.action.includes('CREDIT')
                            ? 'bg-orange-500/10 border-orange-500/20 text-orange-400'
                            : log.action.includes('USER') || log.action.includes('RESELLER')
                            ? 'bg-blue-500/10 border-blue-500/20 text-blue-400'
                            : 'bg-gray-700/50 border-gray-600 text-gray-300'
                        }`}>
                          {log.action.includes('CREDIT') ? <Coins className="w-5 h-5" /> : <ShieldCheck className="w-5 h-5" />}
                        </div>
                      </div>
                      <div className="ml-5 flex-1">
                        <div className="flex flex-col sm:flex-row sm:justify-between sm:items-start mb-1">
                          <h4 className="text-gray-100 font-bold text-lg tracking-wide">{log.action}</h4>
                          <span className={`mt-2 sm:mt-0 text-[10px] px-3 py-1.5 rounded-lg font-bold uppercase tracking-widest border shadow-sm ${
                            log.role === 'admin'
                              ? 'bg-purple-500/10 text-purple-400 border-purple-500/20'
                              : 'bg-blue-500/10 text-blue-400 border-blue-500/20'
                          }`}>
                            {log.role}
                          </span>
                        </div>
                        <p className="text-gray-300 text-base mt-2 leading-relaxed bg-gray-950/50 p-3 rounded-xl border border-gray-800/50">{log.details}</p>
                        <div className="flex flex-col sm:flex-row sm:justify-between sm:items-center mt-4 pt-4 border-t border-gray-800/60">
                          <p className="text-gray-500 text-sm font-medium flex items-center">
                            <User className="w-4 h-4 mr-2 text-gray-600" />
                            Izvršio: <span className="text-gray-300 ml-1">{log.userEmail}</span>
                          </p>
                          <p className="text-gray-400 text-sm font-mono mt-2 sm:mt-0 bg-gray-900 px-3 py-1.5 rounded-lg border border-gray-800">
                             {log.timestamp ? (
                                typeof log.timestamp?.toDate === 'function'
                                  ? format(log.timestamp.toDate(), "d. MMMM yyyy. 'u' HH:mm:ss", { locale: hr })
                                  : format(new Date(log.timestamp), "d. MMMM yyyy. 'u' HH:mm:ss", { locale: hr })
                              ) : 'Upravo sada'}
                          </p>
                        </div>
                      </div>
                    </div>
                  ))}
                </div>
              ) : (
                <div className="text-center text-gray-500 py-12 flex-1 flex flex-col justify-center items-center border-2 border-dashed border-gray-800/60 rounded-3xl bg-gray-900/20">
                  <Activity className="w-16 h-16 text-gray-700 mb-4 opacity-50" />
                  <p className="text-lg font-medium text-gray-400">Nema zabilježenih aktivnosti u revizorskom zapisu.</p>
                </div>
              )}
            </div>
          )}

          {/* Settings Tab */}
          {activeTab === 'settings' && (
            <div className="bg-gray-900/40 backdrop-blur-xl rounded-3xl border border-gray-700/50 shadow-2xl overflow-hidden animate-in fade-in slide-in-from-bottom-4 duration-500">
              <div className="p-8 border-b border-gray-800/80 bg-gray-900/60 flex items-center relative overflow-hidden">
                <div className="absolute top-0 right-0 w-64 h-64 bg-gray-500/5 rounded-full blur-[80px] pointer-events-none" />
                <div className="w-14 h-14 rounded-2xl bg-gradient-to-br from-gray-700 to-gray-800 flex items-center justify-center mr-5 border border-gray-600 shadow-lg">
                  <Settings className="w-7 h-7 text-gray-300" />
                </div>
                <div>
                  <h2 className="font-extrabold text-2xl text-white tracking-tight">Postavke Sustava</h2>
                  <p className="text-base text-gray-400 mt-1">Globalna konfiguracija sigurnosti i ponašanja platforme</p>
                </div>
              </div>

              <div className="p-8 md:p-10">
                <div className="grid grid-cols-1 lg:grid-cols-2 gap-10">
                  {/* General Settings */}
                  <div className="space-y-6">
                    <h3 className="text-xl font-bold text-white border-b border-gray-800 pb-4 flex items-center">
                      <ShieldCheck className="w-5 h-5 mr-3 text-blue-400" />
                      Sigurnost i Pristup
                    </h3>

                    <div className="group flex items-center justify-between p-5 bg-gray-800/40 hover:bg-gray-800/60 rounded-2xl border border-gray-700/50 transition-all shadow-sm">
                      <div className="pr-4">
                        <p className="text-white font-bold text-base">Način Održavanja (Maintenance Mode)</p>
                        <p className="text-gray-400 text-sm mt-1.5 leading-relaxed">Privremeno onemogućite prijave svim resellerima. Samo administratori zadržavaju pristup portalu.</p>
                      </div>
                      <label className="relative inline-flex items-center cursor-pointer flex-shrink-0">
                        <input type="checkbox" className="sr-only peer" />
                        <div className="w-14 h-7 bg-gray-700 peer-focus:outline-none rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-6 after:w-6 after:transition-all peer-checked:bg-gradient-to-r peer-checked:from-blue-600 peer-checked:to-indigo-600 shadow-inner"></div>
                      </label>
                    </div>

                    <div className="group flex items-center justify-between p-5 bg-gray-800/40 hover:bg-gray-800/60 rounded-2xl border border-gray-700/50 transition-all shadow-sm">
                      <div className="pr-4">
                        <p className="text-white font-bold text-base">Javna Registracija Trial Korisnika</p>
                        <p className="text-gray-400 text-sm mt-1.5 leading-relaxed">Dopustite anonimnim korisnicima da putem `/connect` rute zatraže besplatan trial na temelju Device ID-a.</p>
                      </div>
                      <label className="relative inline-flex items-center cursor-pointer flex-shrink-0">
                        <input type="checkbox" className="sr-only peer" defaultChecked />
                        <div className="w-14 h-7 bg-gray-700 peer-focus:outline-none rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-6 after:w-6 after:transition-all peer-checked:bg-gradient-to-r peer-checked:from-emerald-500 peer-checked:to-teal-500 shadow-inner"></div>
                      </label>
                    </div>
                    </div>
                  </div>

                  {/* APK Management */}
                  <div className="space-y-6">
                    <h3 className="text-xl font-bold text-white border-b border-gray-800 pb-4 flex items-center">
                      <Upload className="w-5 h-5 mr-3 text-emerald-400" />
                      Upravljanje APK Datotekom
                    </h3>

                    <div className="p-6 bg-gray-800/40 rounded-2xl border border-gray-700/50 shadow-sm">
                      <p className="text-gray-400 text-sm mb-6 leading-relaxed">
                        Prenesite novu verziju Vopo Android aplikacije. Nova datoteka će biti dostupna na /download.
                      </p>

                      <form onSubmit={handleApkUpload} className="space-y-4">
                        <div className="grid grid-cols-2 gap-4">
                          <div>
                            <label className="block text-sm font-bold text-gray-300 mb-2">Verzija (Name)</label>
                            <input
                              type="text"
                              placeholder="npr. 1.0.5"
                              value={apkVersionName}
                              onChange={(e) => setApkVersionName(e.target.value)}
                              className="w-full bg-gray-950/50 border border-gray-700 text-white rounded-xl p-3 outline-none focus:ring-2 focus:ring-emerald-500"
                              required
                            />
                          </div>
                          <div>
                            <label className="block text-sm font-bold text-gray-300 mb-2">Verzija (Code)</label>
                            <input
                              type="number"
                              placeholder="npr. 105"
                              value={apkVersionCode}
                              onChange={(e) => setApkVersionCode(e.target.value)}
                              className="w-full bg-gray-950/50 border border-gray-700 text-white rounded-xl p-3 outline-none focus:ring-2 focus:ring-emerald-500"
                              required
                            />
                          </div>
                        </div>

                        <div>
                          <label className="block text-sm font-bold text-gray-300 mb-2">APK Datoteka</label>
                          <input
                            type="file"
                            accept=".apk"
                            onChange={(e) => setApkFile(e.target.files ? e.target.files[0] : null)}
                            className="w-full bg-gray-950/50 border border-gray-700 text-white rounded-xl p-3 outline-none focus:ring-2 focus:ring-emerald-500 file:mr-4 file:py-2 file:px-4 file:rounded-full file:border-0 file:text-sm file:font-semibold file:bg-emerald-500/10 file:text-emerald-500 hover:file:bg-emerald-500/20"
                            required
                          />
                        </div>

                        {apkUploadMessage && (
                          <div className={`p-4 rounded-xl text-sm font-medium ${apkUploadMessage.type === 'success' ? 'bg-emerald-500/10 text-emerald-400 border border-emerald-500/20' : 'bg-red-500/10 text-red-400 border border-red-500/20'}`}>
                            {apkUploadMessage.text}
                          </div>
                        )}

                        <div className="flex justify-end pt-2">
                          <button
                            type="submit"
                            disabled={isUploadingApk}
                            className="bg-emerald-600 hover:bg-emerald-500 text-white px-6 py-2.5 rounded-xl font-bold shadow-lg shadow-emerald-500/20 transition-all flex items-center disabled:opacity-50 disabled:cursor-not-allowed"
                          >
                            {isUploadingApk ? (
                              <>
                                <RefreshCw className="w-4 h-4 mr-2 animate-spin" />
                                Prijenos u tijeku...
                              </>
                            ) : (
                              <>
                                <Upload className="w-4 h-4 mr-2" />
                                Objavi APK
                              </>
                            )}
                          </button>
                        </div>
                      </form>
                    </div>
                  </div>

                  {/* Appearance & Localization */}
                  <div className="space-y-6">
                    <h3 className="text-xl font-bold text-white border-b border-gray-800 pb-4 flex items-center">
                      <Globe className="w-5 h-5 mr-3 text-purple-400" />
                      Lokalizacija i Izgled
                    </h3>

                    <div className="space-y-4">
                      <label className="block text-sm font-bold text-gray-300">Glavna Tema Sučelja</label>
                      <div className="grid grid-cols-2 gap-4">
                        <button className="flex flex-col items-center justify-center p-4 bg-gray-900 border-2 border-blue-500 text-white rounded-2xl font-medium shadow-[0_0_15px_rgba(59,130,246,0.15)] relative overflow-hidden">
                          <div className="w-full h-8 bg-gray-800 rounded-md mb-3" />
                          Premium Dark
                          <CheckCircle className="w-4 h-4 absolute top-3 right-3 text-blue-500" />
                        </button>
                        <button className="flex flex-col items-center justify-center p-4 bg-gray-100 border-2 border-transparent text-gray-500 rounded-2xl font-medium opacity-50 cursor-not-allowed">
                          <div className="w-full h-8 bg-white rounded-md mb-3 border border-gray-200" />
                          Light Mode
                          <span className="text-[10px] mt-1 bg-gray-200 px-2 py-0.5 rounded">Uskoro</span>
                        </button>
                      </div>
                    </div>

                    <div className="space-y-4 mt-8">
                      <label className="block text-sm font-bold text-gray-300">Zadani Jezik Portala</label>
                      <div className="relative">
                        <select className="w-full bg-gray-950/50 border border-gray-700 text-white rounded-2xl p-4 outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent appearance-none font-medium shadow-inner">
                          <option value="hr">🇭🇷 Hrvatski (Zadano)</option>
                          <option value="en">🇬🇧 English</option>
                          <option value="de">🇩🇪 Deutsch</option>
                        </select>
                        <div className="absolute right-4 top-1/2 -translate-y-1/2 pointer-events-none text-gray-400">
                          ▼
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div className="mt-10 pt-8 border-t border-gray-800 flex justify-end">
                  <button className="bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-500 hover:to-indigo-500 text-white px-8 py-3.5 rounded-2xl font-bold shadow-lg shadow-blue-500/30 transition-all hover:scale-105">
                    Spremi Konfiguraciju
                  </button>
                </div>
              </div>
          )}
        </div>
      </AdminLayout>
    </ProtectedRoute>
  );
}
