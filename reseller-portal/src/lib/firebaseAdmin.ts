import { initializeApp, getApps, cert } from 'firebase-admin/app';
import { getFirestore } from 'firebase-admin/firestore';
import { getAuth } from 'firebase-admin/auth';
import { mockAdminAuth, mockAdminDb } from './mockFirebaseAdmin';

let db: any;
let auth: any;

if (process.env.MOCK_FIREBASE === 'true') {
  console.log('Using MOCK Firebase Admin');
  db = mockAdminDb;
  auth = mockAdminAuth;
} else {
  if (!getApps().length) {
    try {
      if (process.env.FIREBASE_PROJECT_ID || process.env.NEXT_PUBLIC_FIREBASE_PROJECT_ID) {
        const stripQuotes = (val?: string) => val?.replace(/^["']|["']$/g, '');
        const privateKey = stripQuotes(process.env.FIREBASE_PRIVATE_KEY)?.replace(/\\n/g, '\n');

        if (privateKey && privateKey.includes('BEGIN PRIVATE KEY')) {
          const serviceAccount = {
            projectId: stripQuotes(process.env.FIREBASE_PROJECT_ID || process.env.NEXT_PUBLIC_FIREBASE_PROJECT_ID),
            clientEmail: stripQuotes(process.env.FIREBASE_CLIENT_EMAIL),
            privateKey: privateKey,
          };
          initializeApp({
            credential: cert(serviceAccount),
          });
        } else {
          // If no valid private key, initialize without credential (might fail later, but won't crash on import)
          initializeApp();
        }
      } else {
        initializeApp();
      }
      db = getFirestore();
      auth = getAuth();
    } catch (error) {
      console.error('Firebase admin initialization failed.', error);
      throw new Error('Production Firebase initialization failed. Mock fallback is disabled.');
    }
  } else {
    db = getFirestore();
    auth = getAuth();
  }
}

export const adminDb = db;
export const adminAuth = auth;

