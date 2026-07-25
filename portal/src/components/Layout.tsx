import { Outlet, Link, useNavigate } from 'react-router-dom';
import { LogOut, Home } from 'lucide-react';

export default function Layout() {
  const navigate = useNavigate();

  const handleLogout = () => {
    // TODO: Firebase logout
    navigate('/login');
  };

  return (
    <div className="min-h-screen bg-gray-900 text-white flex flex-col">
      <header className="bg-gray-800 border-b border-gray-700">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 h-16 flex items-center justify-between">
          <div className="flex items-center space-x-2 text-xl font-bold">
            <span className="text-orange-500">VOPO</span> 
            <span>Portal</span>
          </div>
          <nav className="flex space-x-4">
            <Link to="/reseller" className="hover:text-blue-400 flex items-center px-3 py-2 rounded-md text-sm font-medium">
              <Home className="w-4 h-4 mr-1" />
              Početna
            </Link>
            <button onClick={handleLogout} className="hover:text-red-400 flex items-center px-3 py-2 rounded-md text-sm font-medium">
              <LogOut className="w-4 h-4 mr-1" />
              Odjava
            </button>
          </nav>
        </div>
      </header>
      <main className="flex-1 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8 w-full">
        <Outlet />
      </main>
    </div>
  );
}
