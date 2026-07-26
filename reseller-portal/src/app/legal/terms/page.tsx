import Link from 'next/link';
import { ArrowLeft } from 'lucide-react';

export default function TermsOfService() {
  return (
    <div className="min-h-screen bg-gray-900 text-white py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-3xl mx-auto">
        <Link href="/" className="inline-flex items-center text-blue-400 hover:text-blue-300 mb-8 transition-colors">
          <ArrowLeft className="w-4 h-4 mr-2" />
          Natrag na naslovnicu
        </Link>
        <div className="bg-gray-800 rounded-2xl p-8 shadow-xl border border-gray-700">
          <h1 className="text-3xl font-bold mb-6">Uvjeti Korištenja (Terms of Service)</h1>
          <div className="prose prose-invert max-w-none text-gray-300 space-y-4">
            <p>Dobrodošli u VOPO! Korištenjem naše aplikacije prihvaćate ove Uvjete Korištenja.</p>
            <h2 className="text-xl font-semibold text-white mt-6">1. Korištenje Aplikacije</h2>
            <p>VOPO aplikacija služi isključivo kao media player i ne sadrži, ne pruža, niti prodaje ikakav video sadržaj ili IPTV liste (m3u, Xtream, i sl.). Korisnik je isključivo odgovoran za sav sadržaj koji reproducira putem naše aplikacije.</p>
            <h2 className="text-xl font-semibold text-white mt-6">2. Probni Period i Licence</h2>
            <p>Pri unosu Device ID-a automatski se odobrava 3 dana besplatnog probnog (trial) perioda. Nakon isteka, daljnje korištenje zahtijeva aktivaciju pune licence.</p>
            <h2 className="text-xl font-semibold text-white mt-6">3. Odricanje od Odgovornosti</h2>
            <p>VOPO se odriče svake odgovornosti vezane uz autorska prava i kvalitetu streaminga jer ne upravlja niti pruža ikakav sadržaj na poslužiteljima.</p>
            <p className="mt-8 text-sm text-gray-500">Zadnja izmjena: Srpanj 2026.</p>
          </div>
        </div>
      </div>
    </div>
  );
}
