"use client";

import { useEffect } from 'react';
import { useRouter } from 'next/navigation';
import { useAuth } from '../context/AuthContext';
import { Tv } from 'lucide-react';

export default function Home() {
  const { user, userData, loading } = useAuth();
  const router = useRouter();

  useEffect(() => {
    if (!loading) {
      if (user && userData) {
        if (userData.role === 'admin') {
          router.replace('/admin');
        } else if (userData.role === 'reseller') {
          router.replace('/reseller');
        } else {
          // Fallback
          router.replace('/login');
        }
      } else {
        router.replace('/login');
      }
    }
  }, [user, userData, loading, router]);

  return (
    <div className="min-h-screen bg-gray-900 flex flex-col justify-center items-center">
      <div className="w-16 h-16 bg-gradient-to-br from-blue-600 to-indigo-500 rounded-full flex items-center justify-center shadow-lg shadow-blue-500/30 animate-pulse">
        <Tv className="w-8 h-8 text-white" />
      </div>
    </div>
  );
}
