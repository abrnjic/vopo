"use client";

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import { useAuth } from '../context/AuthContext';
import { Tv, ArrowRight, ShieldCheck, Zap, Globe } from 'lucide-react';
import { motion } from 'framer-motion';

export default function Home() {
  const { user, userData, loading } = useAuth();
  const router = useRouter();
  const [mounted, setMounted] = useState(false);

  useEffect(() => {
    setMounted(true);
  }, []);

  const handleActionClick = () => {
    if (loading) return;
    if (user && userData) {
      if (userData.role === 'admin') router.push('/admin');
      else router.push('/reseller');
    } else {
      router.push('/login');
    }
  };

  if (!mounted) return null;

  return (
    <div className="min-h-screen bg-[#0B0F19] overflow-hidden relative flex flex-col justify-center items-center font-sans">
      {/* Dynamic Background Elements */}
      <div className="absolute top-[-10%] left-[-10%] w-[40%] h-[40%] bg-blue-600/20 rounded-full blur-[120px] mix-blend-screen pointer-events-none" />
      <div className="absolute bottom-[-10%] right-[-10%] w-[40%] h-[40%] bg-purple-600/20 rounded-full blur-[120px] mix-blend-screen pointer-events-none" />
      <div className="absolute top-[40%] left-[50%] transform -translate-x-1/2 -translate-y-1/2 w-[60%] h-[60%] bg-indigo-600/10 rounded-full blur-[150px] mix-blend-screen pointer-events-none" />

      {/* Main Content */}
      <div className="relative z-10 max-w-5xl w-full px-6 flex flex-col items-center text-center">
        
        {/* Animated Icon */}
        <motion.div
          initial={{ scale: 0.8, opacity: 0 }}
          animate={{ scale: 1, opacity: 1 }}
          transition={{ duration: 0.5, ease: "easeOut" }}
          className="mb-8 relative"
        >
          <div className="absolute inset-0 bg-gradient-to-br from-blue-500 to-purple-600 blur-xl opacity-50 rounded-full animate-pulse" />
          <div className="w-24 h-24 bg-gray-900/80 backdrop-blur-xl border border-gray-700/50 rounded-3xl flex items-center justify-center relative shadow-2xl">
            <Tv className="w-12 h-12 text-transparent bg-clip-text bg-gradient-to-br from-blue-400 to-purple-400" />
            <svg width="0" height="0">
              <linearGradient id="icon-gradient" x1="100%" y1="100%" x2="0%" y2="0%">
                <stop stopColor="#60A5FA" offset="0%" />
                <stop stopColor="#A78BFA" offset="100%" />
              </linearGradient>
            </svg>
            <Tv className="w-12 h-12" style={{ stroke: "url(#icon-gradient)" }} />
          </div>
        </motion.div>

        {/* Hero Typography */}
        <motion.h1 
          initial={{ y: 20, opacity: 0 }}
          animate={{ y: 0, opacity: 1 }}
          transition={{ duration: 0.5, delay: 0.1, ease: "easeOut" }}
          className="text-5xl md:text-7xl font-extrabold text-white tracking-tight mb-6 leading-tight"
        >
          Vopo <span className="text-transparent bg-clip-text bg-gradient-to-r from-blue-400 via-indigo-400 to-purple-400">Reseller</span>
          <br className="hidden md:block" /> Portal
        </motion.h1>

        <motion.p 
          initial={{ y: 20, opacity: 0 }}
          animate={{ y: 0, opacity: 1 }}
          transition={{ duration: 0.5, delay: 0.2, ease: "easeOut" }}
          className="text-lg md:text-xl text-gray-400 max-w-2xl mb-12"
        >
          Napredna platforma za upravljanje korisnicima, kreditima i uslugama. 
          Sve na jednom mjestu uz najviše sigurnosne standarde.
        </motion.p>

        {/* Action Buttons */}
        <motion.div
          initial={{ y: 20, opacity: 0 }}
          animate={{ y: 0, opacity: 1 }}
          transition={{ duration: 0.5, delay: 0.3, ease: "easeOut" }}
          className="flex flex-col sm:flex-row gap-4 items-center justify-center"
        >
          <button 
            onClick={handleActionClick}
            disabled={loading}
            className="group relative inline-flex items-center justify-center px-8 py-4 text-base font-bold text-white transition-all duration-200 bg-gradient-to-r from-blue-600 to-indigo-600 border border-transparent rounded-full overflow-hidden hover:scale-105 hover:shadow-[0_0_40px_rgba(79,70,229,0.4)] disabled:opacity-70 disabled:hover:scale-100"
          >
            <span className="absolute inset-0 w-full h-full -mt-1 rounded-lg opacity-30 bg-gradient-to-b from-transparent via-transparent to-black" />
            <span className="relative flex items-center">
              {loading ? (
                <div className="w-5 h-5 border-2 border-white/30 border-t-white rounded-full animate-spin" />
              ) : (
                <>
                  {user ? "Otvori Dashboard" : "Pristup Portalu"}
                  <ArrowRight className="w-5 h-5 ml-2 group-hover:translate-x-1 transition-transform" />
                </>
              )}
            </span>
          </button>

          <button 
            onClick={() => router.push('/connect')}
            className="group relative inline-flex items-center justify-center px-8 py-4 text-base font-bold text-gray-300 transition-all duration-200 bg-gray-800/50 hover:bg-gray-700/50 hover:text-white border border-gray-700 rounded-full hover:scale-105"
          >
            <span className="relative flex items-center">
              Aktivacija Uređaja
              <Tv className="w-5 h-5 ml-2 group-hover:scale-110 transition-transform" />
            </span>
          </button>
        </motion.div>

        {/* Feature Highlights */}
        <motion.div 
          initial={{ y: 40, opacity: 0 }}
          animate={{ y: 0, opacity: 1 }}
          transition={{ duration: 0.6, delay: 0.5, ease: "easeOut" }}
          className="grid grid-cols-1 md:grid-cols-3 gap-6 mt-24 w-full max-w-4xl"
        >
          {[
            { icon: ShieldCheck, title: "Sigurno i Pouzdano", desc: "Najviša razina zaštite Vaših podataka i klijenata." },
            { icon: Zap, title: "Brzo Upravljanje", desc: "Dodajte kredite i aktivirajte usluge u samo nekoliko sekundi." },
            { icon: Globe, title: "Globalna Dostupnost", desc: "Pristupite portalu bilo kada, s bilo kojeg uređaja na svijetu." }
          ].map((feature, idx) => (
            <div key={idx} className="bg-gray-800/30 backdrop-blur-md border border-gray-700/50 p-6 rounded-2xl flex flex-col items-center text-center hover:bg-gray-800/50 transition-colors">
              <div className="w-12 h-12 bg-blue-900/30 rounded-full flex items-center justify-center mb-4">
                <feature.icon className="w-6 h-6 text-blue-400" />
              </div>
              <h3 className="text-white font-semibold mb-2">{feature.title}</h3>
              <p className="text-gray-400 text-sm leading-relaxed">{feature.desc}</p>
            </div>
          ))}
        </motion.div>

      </div>
    </div>
  );
}
