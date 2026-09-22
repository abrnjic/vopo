"use client";

import Link from 'next/link';
import { useRouter, usePathname } from 'next/navigation';
import { LogOut, LayoutDashboard, User, Bell } from 'lucide-react';
import { useAuth } from '../context/AuthContext';
import { motion } from 'framer-motion';
import { useEffect, useState } from 'react';

export default function AdminLayout({ children }: { children: React.ReactNode }) {
  const router = useRouter();
  const pathname = usePathname();
  const { logout, userData, user } = useAuth();
  const [notifications, setNotifications] = useState<any[]>([]);
  const [showNotifications, setShowNotifications] = useState(false);

  const loadNotifications = async () => {
    if (!user) return;
    const response = await fetch('/api/notifications', { headers: { Authorization: `Bearer ${await user.getIdToken()}` }, cache: 'no-store' });
    if (response.ok) setNotifications((await response.json()).notifications || []);
  };
  useEffect(() => { void loadNotifications(); }, [user]);
  const markRead = async (id: string) => {
    await fetch('/api/notifications', { method: 'PATCH', headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${await user?.getIdToken()}` }, body: JSON.stringify({ id }) });
    setNotifications(items => items.map(item => item.id === id ? { ...item, read: true } : item));
  };

  const handleLogout = async () => {
    await logout();
    router.push('/login');
  };

  return (
    <div className="min-h-screen bg-[#0B0F19] text-gray-200 font-sans selection:bg-blue-500/30">
      
      {/* Background Glows */}
      <div className="fixed top-[-20%] left-[-10%] w-[50%] h-[50%] bg-blue-900/20 rounded-full blur-[150px] pointer-events-none" />
      <div className="fixed bottom-[-20%] right-[-10%] w-[50%] h-[50%] bg-indigo-900/10 rounded-full blur-[150px] pointer-events-none" />

      {/* Modern Header */}
      <header className="sticky top-0 z-50 bg-gray-900/60 backdrop-blur-xl border-b border-gray-800 shadow-sm">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 h-16 flex items-center justify-between">
          
          {/* Logo */}
          <Link href="/" className="flex items-center space-x-2 group">
            <div className="w-8 h-8 bg-gradient-to-br from-blue-500 to-indigo-600 rounded-lg flex items-center justify-center shadow-lg shadow-blue-500/20 group-hover:shadow-blue-500/40 transition-shadow">
              <span className="text-white font-bold text-sm">V</span>
            </div>
            <span className="text-xl font-bold text-white tracking-tight">Vopo</span>
          </Link>

          {/* Navigation */}
          <nav className="hidden md:flex items-center space-x-1">
            <Link 
              href={userData?.role === 'admin' ? '/admin' : '/reseller'} 
              className={`flex items-center px-4 py-2 rounded-lg text-sm font-medium transition-all ${
                pathname.includes('/admin') || pathname.includes('/reseller')
                  ? 'bg-blue-500/10 text-blue-400' 
                  : 'text-gray-400 hover:text-white hover:bg-gray-800'
              }`}
            >
              <LayoutDashboard className="w-4 h-4 mr-2" />
              Dashboard
            </Link>
          </nav>

          {/* User & Actions */}
          <div className="flex items-center space-x-4">
            <div className="relative">
              <button onClick={() => setShowNotifications(v => !v)} className="relative flex h-9 w-9 items-center justify-center rounded-full border border-gray-700 bg-gray-800 text-gray-400 hover:text-white" title="Obavijesti"><Bell className="h-4 w-4"/>{notifications.some(n=>!n.read)&&<span className="absolute right-0 top-0 h-2.5 w-2.5 rounded-full bg-red-500 ring-2 ring-gray-900"/>}</button>
              {showNotifications&&<div className="absolute right-0 top-12 z-[80] w-80 overflow-hidden rounded-2xl border border-gray-700 bg-gray-900 shadow-2xl"><div className="border-b border-gray-800 p-4 font-bold text-white">Obavijesti o licencama</div><div className="max-h-80 overflow-y-auto">{notifications.map(n=><button key={n.id} onClick={()=>markRead(n.id)} className={`block w-full border-b border-gray-800 p-4 text-left hover:bg-gray-800 ${n.read?'opacity-60':''}`}><p className="text-sm font-bold text-white">{n.title}</p><p className="mt-1 text-xs text-gray-400">{n.message}</p></button>)}{!notifications.length&&<p className="p-6 text-center text-sm text-gray-500">Nema obavijesti.</p>}</div></div>}
            </div>
            <div className="hidden sm:flex items-center space-x-3 bg-gray-800/50 px-3 py-1.5 rounded-full border border-gray-700/50">
              <div className="w-6 h-6 bg-blue-900 rounded-full flex items-center justify-center">
                <User className="w-3 h-3 text-blue-400" />
              </div>
              <span className="text-xs font-medium text-gray-300">{user?.email}</span>
            </div>
            
            <button 
              onClick={handleLogout} 
              className="flex items-center justify-center w-9 h-9 rounded-full bg-gray-800 border border-gray-700 hover:bg-red-900/30 hover:border-red-500/50 hover:text-red-400 transition-all text-gray-400"
              title="Odjava"
            >
              <LogOut className="w-4 h-4" />
            </button>
          </div>
        </div>
      </header>

      {/* Main Content Area */}
      <main className="relative max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8 w-full z-10">
        <motion.div
          initial={{ opacity: 0, y: 10 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.4 }}
        >
          {children}
        </motion.div>
      </main>
    </div>
  );
}
