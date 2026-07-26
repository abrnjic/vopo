import { redirect } from "next/navigation"
import { auth } from "@/auth"
import Link from "next/link"
import { LogOut, LayoutDashboard, Users, CreditCard, Settings, Link2 } from "lucide-react"

export default async function DashboardLayout({
  children,
}: {
  children: React.ReactNode
}) {
  const session = await auth()

  if (!session?.user) {
    redirect("/login")
  }

  const role = session.user.role

  return (
    <div className="flex h-screen bg-zinc-950 text-white overflow-hidden font-sans">
      {/* Sidebar */}
      <aside className="w-64 flex flex-col bg-zinc-900/50 border-r border-white/5 backdrop-blur-xl">
        <div className="h-16 flex items-center px-6 border-b border-white/5">
          <div className="flex items-center gap-2">
            <div className="w-8 h-8 rounded-lg bg-blue-600/20 border border-blue-500/30 flex items-center justify-center">
              <span className="text-blue-500 font-bold">V</span>
            </div>
            <span className="font-semibold tracking-wide">Portal</span>
          </div>
        </div>

        <div className="flex-1 overflow-y-auto py-4 px-3 space-y-1">
          <Link
            href="/dashboard"
            className="flex items-center gap-3 px-3 py-2 text-sm font-medium text-zinc-300 rounded-lg hover:text-white hover:bg-white/5 transition-colors"
          >
            <LayoutDashboard className="w-4 h-4" />
            Dashboard
          </Link>
          
          {role === 'admin' && (
            <>
              <div className="pt-4 pb-2 px-3 text-xs font-semibold text-zinc-500 uppercase tracking-wider">
                Administration
              </div>
              <Link
                href="/dashboard/users"
                className="flex items-center gap-3 px-3 py-2 text-sm font-medium text-zinc-300 rounded-lg hover:text-white hover:bg-white/5 transition-colors"
              >
                <Users className="w-4 h-4" />
                Users
              </Link>
              <Link
                href="/dashboard/finances"
                className="flex items-center gap-3 px-3 py-2 text-sm font-medium text-zinc-300 rounded-lg hover:text-white hover:bg-white/5 transition-colors"
              >
                <CreditCard className="w-4 h-4" />
                Finances
              </Link>
            </>
          )}

          {(role === 'admin' || role === 'reseller') && (
            <>
              <div className="pt-4 pb-2 px-3 text-xs font-semibold text-zinc-500 uppercase tracking-wider">
                Reseller Tools
              </div>
              <Link
                href="/dashboard/domains"
                className="flex items-center gap-3 px-3 py-2 text-sm font-medium text-zinc-300 rounded-lg hover:text-white hover:bg-white/5 transition-colors"
              >
                <Link2 className="w-4 h-4" />
                Domains
              </Link>
              {role === 'reseller' && (
                <Link
                  href="/dashboard/sub-resellers"
                  className="flex items-center gap-3 px-3 py-2 text-sm font-medium text-zinc-300 rounded-lg hover:text-white hover:bg-white/5 transition-colors"
                >
                  <Users className="w-4 h-4" />
                  Sub-Resellers
                </Link>
              )}
            </>
          )}

          <div className="pt-4 pb-2 px-3 text-xs font-semibold text-zinc-500 uppercase tracking-wider">
            App Management
          </div>
          <Link
            href="/dashboard/activations"
            className="flex items-center gap-3 px-3 py-2 text-sm font-medium text-zinc-300 rounded-lg hover:text-white hover:bg-white/5 transition-colors"
          >
            <LayoutDashboard className="w-4 h-4" />
            Activations
          </Link>
        </div>

        <div className="p-4 border-t border-white/5">
          <div className="flex items-center gap-3 mb-4">
            <div className="w-8 h-8 rounded-full bg-gradient-to-tr from-blue-600 to-indigo-600 flex items-center justify-center text-xs font-medium">
              {session.user.email?.[0].toUpperCase()}
            </div>
            <div className="flex-1 min-w-0">
              <p className="text-sm font-medium text-white truncate">
                {session.user.email}
              </p>
              <p className="text-xs text-zinc-500 truncate capitalize">
                {role}
              </p>
            </div>
          </div>
          <Link
            href="/api/auth/signout"
            className="flex items-center gap-2 w-full px-3 py-2 text-sm font-medium text-zinc-400 rounded-lg hover:text-white hover:bg-white/5 transition-colors"
          >
            <LogOut className="w-4 h-4" />
            Sign out
          </Link>
        </div>
      </aside>

      {/* Main Content */}
      <main className="flex-1 flex flex-col min-w-0 overflow-hidden relative">
        <div className="absolute top-0 right-0 w-[800px] h-[500px] bg-blue-900/10 rounded-full blur-[120px] mix-blend-screen pointer-events-none" />
        <div className="flex-1 overflow-y-auto p-8 relative z-10">
          {children}
        </div>
      </main>
    </div>
  )
}
