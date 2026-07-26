"use client";

import { useState } from 'react';
import { Lock, LogIn, AlertCircle, ShieldCheck } from 'lucide-react';
import { useRouter } from 'next/navigation';
import { signInWithEmailAndPassword } from 'firebase/auth';
import { doc, getDoc } from 'firebase/firestore';
import { auth, db } from '../../firebase';
import { motion } from 'framer-motion';

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const router = useRouter();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      const userCredential = await signInWithEmailAndPassword(auth, email, password);
      const user = userCredential.user;

      // Fetch user role to determine routing
      const userDoc = await getDoc(doc(db, 'users', user.uid));
      if (userDoc.exists()) {
        const userData = userDoc.data();
        if (userData.role === 'admin') {
          router.push('/admin');
        } else {
          router.push('/reseller');
        }
      } else {
        setError('Korisnički račun ne postoji u bazi.');
        await auth.signOut();
      }
    } catch (err: any) {
      console.error(err);
      setError(`Greška: ${err.message || 'Nepoznata greška'}`);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-[#0B0F19] flex relative overflow-hidden font-sans text-white">
      
      {/* LEFT SIDE - Branding / Graphics (Hidden on Mobile) */}
      <div className="hidden lg:flex lg:w-1/2 relative flex-col justify-center items-center bg-gray-900 border-r border-gray-800 z-10 p-12 overflow-hidden">
        {/* Abstract Backgrounds */}
        <div className="absolute top-0 left-0 w-full h-full bg-gradient-to-br from-[#0B0F19] via-[#0f172a] to-blue-900/20" />
        <div className="absolute top-[-10%] left-[-10%] w-[50%] h-[50%] bg-blue-600/30 rounded-full blur-[120px] mix-blend-screen pointer-events-none" />
        <div className="absolute bottom-[-10%] right-[-10%] w-[50%] h-[50%] bg-purple-600/20 rounded-full blur-[120px] mix-blend-screen pointer-events-none" />
        
        <motion.div 
          initial={{ opacity: 0, x: -50 }}
          animate={{ opacity: 1, x: 0 }}
          transition={{ duration: 0.8, ease: "easeOut" }}
          className="relative z-20 text-center max-w-md"
        >
          <div className="w-20 h-20 bg-gradient-to-br from-blue-500 to-purple-600 rounded-2xl flex items-center justify-center mx-auto mb-8 shadow-2xl shadow-blue-500/20">
            <ShieldCheck className="w-10 h-10 text-white" />
          </div>
          <h1 className="text-4xl font-extrabold text-transparent bg-clip-text bg-gradient-to-r from-blue-400 to-purple-400 mb-6">
            Vopo Portal
          </h1>
          <p className="text-gray-400 text-lg leading-relaxed">
            Centralizirani sustav za upravljanje korisnicima i uslugama. 
            Prijavite se kako biste pristupili svom nadzornom centru.
          </p>
        </motion.div>
      </div>

      {/* RIGHT SIDE - Login Form */}
      <div className="w-full lg:w-1/2 flex items-center justify-center p-6 relative z-10">
        
        {/* Mobile Background Elements */}
        <div className="absolute top-0 right-0 w-[50%] h-[50%] bg-blue-600/10 rounded-full blur-[100px] lg:hidden mix-blend-screen pointer-events-none" />
        
        <motion.div 
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.6, delay: 0.2, ease: "easeOut" }}
          className="max-w-md w-full"
        >
          {/* Form Card (Glassmorphism) */}
          <div className="bg-gray-800/40 backdrop-blur-xl border border-gray-700/50 p-8 sm:p-10 rounded-3xl shadow-2xl">
            <div className="text-center mb-10">
              <div className="w-16 h-16 bg-gray-900/80 rounded-full flex items-center justify-center border border-gray-700 mx-auto mb-6 lg:hidden shadow-lg shadow-blue-500/10">
                <Lock className="w-8 h-8 text-blue-400" />
              </div>
              <h2 className="text-3xl font-bold text-white mb-2">Dobrodošli natrag</h2>
              <p className="text-gray-400">Unesite svoje podatke za prijavu</p>
            </div>

            <form className="space-y-6" onSubmit={handleSubmit}>
              
              {error && (
                <motion.div 
                  initial={{ opacity: 0, scale: 0.95 }}
                  animate={{ opacity: 1, scale: 1 }}
                  className="bg-red-900/30 border border-red-500/50 text-red-200 p-4 rounded-xl flex items-start text-sm backdrop-blur-sm"
                >
                  <AlertCircle className="w-5 h-5 mr-3 flex-shrink-0 mt-0.5 text-red-400" />
                  <span>{error}</span>
                </motion.div>
              )}

              <div className="space-y-1">
                <label htmlFor="email" className="block text-sm font-medium text-gray-400 ml-1">
                  Email adresa
                </label>
                <input
                  id="email"
                  name="email"
                  type="email"
                  required
                  placeholder="admin@vopoapp.com"
                  className="w-full px-4 py-3 bg-gray-900/50 border border-gray-700 rounded-xl shadow-inner placeholder-gray-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent text-white transition-all"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  disabled={loading}
                />
              </div>

              <div className="space-y-1">
                <label htmlFor="password" className="block text-sm font-medium text-gray-400 ml-1">
                  Lozinka
                </label>
                <input
                  id="password"
                  name="password"
                  type="password"
                  required
                  placeholder="••••••••"
                  className="w-full px-4 py-3 bg-gray-900/50 border border-gray-700 rounded-xl shadow-inner placeholder-gray-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent text-white transition-all"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  disabled={loading}
                />
              </div>

              <div className="pt-2">
                <button
                  type="submit"
                  disabled={loading}
                  className="group relative w-full flex justify-center items-center py-3.5 px-4 rounded-xl text-sm font-bold text-white bg-gradient-to-r from-blue-600 to-indigo-600 hover:from-blue-500 hover:to-indigo-500 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 focus:ring-offset-gray-900 transition-all shadow-lg hover:shadow-blue-500/25 disabled:opacity-70 disabled:hover:shadow-none"
                >
                  {loading ? (
                    <div className="w-5 h-5 border-2 border-white/30 border-t-white rounded-full animate-spin" />
                  ) : (
                    <>
                      <LogIn className="w-5 h-5 mr-2 opacity-80 group-hover:opacity-100 transition-opacity" />
                      Prijavi se
                    </>
                  )}
                </button>
              </div>
            </form>
          </div>
        </motion.div>
      </div>
    </div>
  );
}
