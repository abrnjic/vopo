import { auth } from "@/auth"

export default async function DashboardPage() {
  const session = await auth()
  
  if (!session) return null

  const role = session.user.role

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-3xl font-bold tracking-tight text-white">Overview</h1>
        <p className="text-zinc-400 mt-1">Welcome back, {session.user.email}</p>
      </div>

      <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-4">
        {/* Stat Cards placeholder */}
        <div className="p-6 bg-white/5 border border-white/10 rounded-2xl backdrop-blur-xl">
          <h3 className="text-sm font-medium text-zinc-400">Total Credits</h3>
          <p className="text-3xl font-bold text-white mt-2">0.00</p>
        </div>
        
        {role === 'admin' && (
          <div className="p-6 bg-white/5 border border-white/10 rounded-2xl backdrop-blur-xl">
            <h3 className="text-sm font-medium text-zinc-400">Active Resellers</h3>
            <p className="text-3xl font-bold text-white mt-2">0</p>
          </div>
        )}
        
        <div className="p-6 bg-white/5 border border-white/10 rounded-2xl backdrop-blur-xl">
          <h3 className="text-sm font-medium text-zinc-400">Total Domains</h3>
          <p className="text-3xl font-bold text-white mt-2">0</p>
        </div>

        <div className="p-6 bg-white/5 border border-white/10 rounded-2xl backdrop-blur-xl">
          <h3 className="text-sm font-medium text-zinc-400">Active Licenses</h3>
          <p className="text-3xl font-bold text-white mt-2">0</p>
        </div>
      </div>

      {/* Activity / Tables placeholder */}
      <div className="grid gap-6 lg:grid-cols-2">
        <div className="p-6 bg-white/5 border border-white/10 rounded-2xl backdrop-blur-xl min-h-[300px]">
          <h3 className="text-lg font-semibold text-white mb-4">Recent Activity</h3>
          <div className="text-sm text-zinc-500">No recent activity found.</div>
        </div>
        <div className="p-6 bg-white/5 border border-white/10 rounded-2xl backdrop-blur-xl min-h-[300px]">
          <h3 className="text-lg font-semibold text-white mb-4">Quick Actions</h3>
          <div className="text-sm text-zinc-500">Available actions will appear here based on your role.</div>
        </div>
      </div>
    </div>
  )
}
