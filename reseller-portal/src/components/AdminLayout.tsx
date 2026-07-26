"use client";

import Link from 'next/link';
import { useRouter, usePathname } from 'next/navigation';
import { LogOut, LayoutDashboard, Settings, User } from 'lucide-react';
import { useAuth } from '../context/AuthContext';
import { motion } from 'framer-motion';

export default function AdminLayout({ children }: { children: React.ReactNode }) {
  const router = useRouter();
  const pathname = usePathname();
  const { logout, userData, user } = useAuth();

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
