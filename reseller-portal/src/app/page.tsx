import Link from 'next/link';
import { Tv, Play } from 'lucide-react';

export default function Home() {
  return (
    <div className="min-h-screen bg-gray-900 text-white flex flex-col items-center justify-center p-8">
      <div className="max-w-2xl text-center">
        <div className="flex justify-center mb-8">
          <div className="w-24 h-24 bg-gradient-to-br from-blue-600 to-indigo-500 rounded-2xl flex items-center justify-center shadow-lg shadow-blue-500/30">
            <Tv className="w-12 h-12 text-white" />
          </div>
        </div>
        <h1 className="text-5xl font-extrabold mb-6 tracking-tight bg-gradient-to-r from-blue-400 to-indigo-400 bg-clip-text text-transparent">
          Dobrodošli u VOPO
        </h1>
        <p className="text-xl text-gray-400 mb-10">
          Vrhunsko IPTV iskustvo za vaš Android TV. Povežite svoj uređaj i uživajte u najboljem sadržaju uživo i na zahtjev.
        </p>
        <div className="flex flex-col sm:flex-row gap-4 justify-center">
          <Link href="/connect" className="bg-blue-600 hover:bg-blue-700 px-8 py-4 rounded-xl font-bold transition-all flex items-center justify-center shadow-lg shadow-blue-600/20">
            <Play className="w-5 h-5 mr-2" />
            Poveži Uređaj
          </Link>
          <Link href="/legal/terms" className="bg-gray-800 hover:bg-gray-700 border border-gray-700 px-8 py-4 rounded-xl font-medium transition-all flex items-center justify-center">
            Saznajte Više
          </Link>
        </div>
      </div>
    </div>
  );
}
