"use client";

import Link from 'next/link';
import { useRouter } from 'next/navigation';
import { LogOut, Home } from 'lucide-react';
import { useAuth } from '../context/AuthContext';

export default function AdminLayout({ children }: { children: React.ReactNode }) {
  const router = useRouter();
  const { logout, userData } = useAuth();

  const handleLogout = async () => {
    await logout();
    router.push('/login');
  };

  return (
    <div className="min-h-screen bg-gray-900 text-white flex flex-col">
      <header className="bg-gray-800 border-b border-gray-700">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 h-16 flex items-center justify-between">
          <div className="flex items-center space-x-2 text-xl font-bold">
            <span className="text-orange-500">VOPO</span> 
            <span>Portal</span>
          </div>
          <nav className="flex space-x-4">
            <Link 
              href={userData?.role === 'admin' ? '/admin' : '/reseller'} 
              className="hover:text-blue-400 flex items-center px-3 py-2 rounded-md text-sm font-medium"
            >
              <Home className="w-4 h-4 mr-1" />
              Početna
            </Link>
            <button onClick={handleLogout} className="hover:text-red-400 flex items-center px-3 py-2 rounded-md text-sm font-medium">
              <LogOut className="w-4 h-4 mr-1" />
              Odjava
            </button>
          </nav>
        </div>
      </header>
      <main className="flex-1 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8 w-full">
        {children}
      </main>
    </div>
  );
}
