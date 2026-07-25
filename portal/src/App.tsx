import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import PublicUpload from './pages/PublicUpload';
import Login from './pages/Login';
import ResellerDashboard from './pages/ResellerDashboard';
import AdminDashboard from './pages/AdminDashboard';
import ProtectedRoute from './components/ProtectedRoute';
import { AuthProvider } from './context/AuthContext';

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Navigate to="/upload" replace />} />
          
          {/* Public route for end-users to add their IPTV line */}
          <Route path="/upload" element={<PublicUpload />} />
          
          {/* Auth Route */}
          <Route path="/login" element={<Login />} />
          
          {/* Protected Routes (Require Auth) */}
          <Route element={<ProtectedRoute allowedRoles={['reseller']} />}>
            <Route path="/reseller" element={<ResellerDashboard />} />
          </Route>

          <Route element={<ProtectedRoute allowedRoles={['admin']} />}>
            <Route path="/admin" element={<AdminDashboard />} />
          </Route>
          
          <Route path="*" element={<Navigate to="/upload" replace />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
