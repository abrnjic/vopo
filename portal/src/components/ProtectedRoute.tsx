import { Navigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import Layout from './Layout';

interface ProtectedRouteProps {
  allowedRoles?: ('admin' | 'reseller')[];
}

export default function ProtectedRoute({ allowedRoles }: ProtectedRouteProps) {
  const { user, userData, loading } = useAuth();

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-900 flex items-center justify-center text-white">
        <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-500"></div>
      </div>
    );
  }

  if (!user) {
    return <Navigate to="/login" replace />;
  }

  if (allowedRoles && userData && !allowedRoles.includes(userData.role)) {
    // If user doesn't have required role, redirect to appropriate dashboard
    if (userData.role === 'admin') {
      return <Navigate to="/admin" replace />;
    } else {
      return <Navigate to="/reseller" replace />;
    }
  }

  return (
    <Layout />
  );
}
