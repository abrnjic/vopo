import { adminAuth, adminDb } from './firebaseAdmin';
import { NextRequest } from 'next/server';

export type AuthResult = 
  | { status: 'authenticated'; context: { uid: string; email?: string; role: string; userRecord: any; dbUser: any } }
  | { status: 'unauthenticated' | 'invalid' | 'inactive' | 'forbidden' | 'error'; error?: string };

export async function verifyAuthToken(req: NextRequest): Promise<AuthResult> {
  try {
    const authHeader = req.headers.get('Authorization');
    if (!authHeader || !authHeader.startsWith('Bearer ')) {
      return { status: 'unauthenticated', error: 'No token provided' };
    }
    const idToken = authHeader.split('Bearer ')[1];
    
    let decodedToken;
    try {
      decodedToken = await adminAuth.verifyIdToken(idToken, true);
    } catch (e: any) {
      console.error('Invalid or revoked token:', e);
      return { status: 'invalid', error: 'Invalid or revoked token' };
    }
    
    // 2. Check if user is disabled in Firebase Auth
    const userRecord = await adminAuth.getUser(decodedToken.uid);
    if (userRecord.disabled) {
      console.warn(`User ${decodedToken.uid} is disabled in Auth`);
      return { status: 'inactive', error: 'User is disabled' };
    }

    // 3. Check application-level status in Firestore (SUSPENDED / DEACTIVATED)
    const userDoc = await adminDb.collection('users').doc(decodedToken.uid).get();
    if (!userDoc.exists) {
       console.warn(`User ${decodedToken.uid} not found in Firestore`);
       return { status: 'forbidden', error: 'User not found in DB' };
    }
    
    const userData = userDoc.data();
    if (userData?.status === 'suspended' || userData?.status === 'deactivated' || userData?.status === 'SUSPENDED' || userData?.status === 'DEACTIVATED') {
       console.warn(`User ${decodedToken.uid} is suspended/deactivated in Firestore`);
       return { status: 'inactive', error: 'User is suspended or deactivated' };
    }

    // Overwrite the token role with the fresh DB role to prevent stale claims
    decodedToken.role = userData?.role || 'user';
    
    return {
      status: 'authenticated',
      context: {
        uid: decodedToken.uid,
        email: decodedToken.email,
        role: decodedToken.role,
        userRecord,
        dbUser: userData
      }
    };
  } catch (error) {
    console.error('Error verifying token:', error);
    return { status: 'error', error: 'Internal auth error' };
  }
}
