import { CreditCard, Sparkles } from 'lucide-react';

const creditPackages = [
  {
    name: 'STARTER',
    credits: 10,
    pricePerCredit: '3,00 €',
    total: '30,00 €',
    saving: null,
    featured: false,
    accent: 'from-blue-500/20 to-cyan-500/5 border-blue-500/30',
    label: 'text-blue-300',
  },
  {
    name: 'PRO',
    credits: 50,
    pricePerCredit: '2,75 €',
    total: '137,50 €',
    saving: 'Ušteda 8%',
    featured: true,
    accent: 'from-violet-500/25 to-fuchsia-500/5 border-violet-400/40',
    label: 'text-violet-300',
  },
  {
    name: 'ENTERPRISE',
    credits: 100,
    pricePerCredit: '2,50 €',
    total: '250,00 €',
    saving: 'Ušteda 17%',
    featured: false,
    accent: 'from-amber-500/25 to-orange-500/5 border-amber-400/40',
    label: 'text-amber-300',
  },
] as const;

export default function CreditPricing() {
  return (
    <section
      aria-labelledby="credit-pricing-title"
      className="relative overflow-hidden rounded-3xl border border-gray-700/60 bg-gray-900/70 p-5 shadow-2xl backdrop-blur-xl sm:p-7"
    >
      <div className="pointer-events-none absolute -right-20 -top-24 h-56 w-56 rounded-full bg-blue-500/10 blur-3xl" />
      <div className="relative mb-5 flex flex-col gap-2 sm:flex-row sm:items-end sm:justify-between">
        <div>
          <div className="mb-2 flex items-center gap-2 text-xs font-bold uppercase tracking-[0.22em] text-blue-300">
            <CreditCard className="h-4 w-4" aria-hidden="true" />
            Službeni cjenik
          </div>
          <h2 id="credit-pricing-title" className="text-2xl font-black tracking-tight text-white">
            Cjenik kredita <span className="font-semibold text-gray-500">/ Credit Pricing</span>
          </h2>
        </div>
        <p className="max-w-md text-sm leading-6 text-gray-400">
          Odaberite paket prema potrebnom broju aktivacija. Veći paketi imaju nižu cijenu po kreditu.
        </p>
      </div>

      <div className="relative grid grid-cols-1 gap-4 lg:grid-cols-3">
        {creditPackages.map((pkg) => (
          <article
            key={pkg.name}
            className={`relative rounded-2xl border bg-gradient-to-br p-5 ${pkg.accent} ${pkg.featured ? 'shadow-lg shadow-violet-950/30' : ''}`}
          >
            {pkg.featured && (
              <span className="absolute right-4 top-4 inline-flex items-center gap-1 rounded-full border border-violet-400/30 bg-violet-500/15 px-2.5 py-1 text-[10px] font-bold uppercase tracking-wider text-violet-200">
                <Sparkles className="h-3 w-3" aria-hidden="true" /> Preporučeno
              </span>
            )}

            <p className={`text-xs font-black uppercase tracking-[0.2em] ${pkg.label}`}>Paket {pkg.name}</p>
            <div className="mt-4 flex items-baseline gap-2">
              <span className="text-4xl font-black text-white">{pkg.credits}</span>
              <span className="text-sm font-semibold uppercase tracking-wide text-gray-400">kredita</span>
            </div>

            <dl className="mt-5 space-y-3 border-t border-white/10 pt-4 text-sm">
              <div className="flex items-center justify-between gap-4">
                <dt className="text-gray-400">Cijena / kredit</dt>
                <dd className="font-bold text-gray-100">{pkg.pricePerCredit}</dd>
              </div>
              <div className="flex items-end justify-between gap-4">
                <dt className="text-gray-400">Ukupno za uplatu</dt>
                <dd className="text-xl font-black text-white">{pkg.total}</dd>
              </div>
            </dl>

            {pkg.saving ? (
              <div className="mt-4 inline-flex rounded-lg border border-emerald-400/25 bg-emerald-500/10 px-3 py-1.5 text-xs font-bold text-emerald-300">
                {pkg.saving}
              </div>
            ) : (
              <div className="mt-4 text-xs font-medium text-gray-500">Početni paket</div>
            )}
          </article>
        ))}
      </div>
    </section>
  );
}
