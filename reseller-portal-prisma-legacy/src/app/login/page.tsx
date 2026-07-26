import { redirect } from "next/navigation"
import { auth } from "@/auth"
import LoginForm from "@/components/LoginForm"

export default async function LoginPage() {
  const session = await auth()

  if (session) {
    redirect("/dashboard")
  }

  return (
    <div className="flex min-h-screen items-center justify-center bg-zinc-950 p-4 relative overflow-hidden">
      {/* Background gradients */}
      <div className="absolute top-0 -left-1/4 w-full h-[500px] bg-blue-900/20 rounded-full blur-[120px] mix-blend-screen pointer-events-none" />
      <div className="absolute bottom-0 -right-1/4 w-full h-[500px] bg-indigo-900/20 rounded-full blur-[120px] mix-blend-screen pointer-events-none" />
      
      <div className="relative z-10 w-full max-w-md flex flex-col items-center">
        <div className="mb-10 text-center">
          <div className="inline-flex items-center justify-center w-16 h-16 rounded-2xl bg-blue-600/10 border border-blue-500/20 mb-4">
            <svg
              className="w-8 h-8 text-blue-500"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth={2}
                d="M13 10V3L4 14h7v7l9-11h-7z"
              />
            </svg>
          </div>
          <h1 className="text-4xl font-bold tracking-tight text-white">Vopo</h1>
          <p className="text-blue-400 font-medium tracking-wide mt-1">RESELLER PORTAL</p>
        </div>
        
        <LoginForm />
      </div>
    </div>
  )
}
