import Link from 'next/link';
import { ArrowLeft } from 'lucide-react';

export default function PrivacyPolicy() {
  return (
    <div className="min-h-screen bg-gray-900 text-white py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-3xl mx-auto">
        <Link href="/" className="inline-flex items-center text-blue-400 hover:text-blue-300 mb-8 transition-colors">
          <ArrowLeft className="w-4 h-4 mr-2" />
          Natrag na naslovnicu
        </Link>
        <div className="bg-gray-800 rounded-2xl p-8 shadow-xl border border-gray-700">
          <h1 className="text-3xl font-bold mb-6">Politika Privatnosti (Privacy Policy)</h1>
          <div className="prose prose-invert max-w-none text-gray-300 space-y-4">
            <p>Ova Politika Privatnosti opisuje kako VOPO prikuplja, koristi i dijeli vaše podatke kada koristite našu aplikaciju i usluge.</p>
            <h2 className="text-xl font-semibold text-white mt-6">1. Prikupljanje Podataka</h2>
            <p>Prikupljamo samo nužne podatke za pružanje naših usluga, uključujući jedinstveni Device ID vašeg televizora i podatke o Xtream listi koje sami unesete.</p>
            <h2 className="text-xl font-semibold text-white mt-6">2. Korištenje Podataka</h2>
            <p>Vaši podaci koriste se isključivo za autentifikaciju i pružanje funkcionalnosti IPTV reprodukcije na vašem uređaju.</p>
            <h2 className="text-xl font-semibold text-white mt-6">3. Zaštita Podataka</h2>
            <p>Poduzimamo tehničke i organizacijske mjere kako bismo osigurali sigurnost vaših podataka u našoj bazi.</p>
            <p className="mt-8 text-sm text-gray-500">Zadnja izmjena: Srpanj 2026.</p>
          </div>
        </div>
      </div>
    </div>
  );
}
