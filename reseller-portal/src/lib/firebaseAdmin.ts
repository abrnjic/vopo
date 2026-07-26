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
      if (process.env.FIREBASE_PROJECT_ID) {
        const stripQuotes = (val?: string) => val?.replace(/^["']|["']$/g, '');
        const serviceAccount = {
          projectId: stripQuotes(process.env.FIREBASE_PROJECT_ID),
          clientEmail: stripQuotes(process.env.FIREBASE_CLIENT_EMAIL),
          privateKey: stripQuotes(process.env.FIREBASE_PRIVATE_KEY)?.replace(/\\n/g, '\n'),
        };
        initializeApp({
          credential: cert(serviceAccount),
        });
      } else {
        initializeApp();
      }
    } catch (error) {
      console.error('Firebase admin initialization error', error);
    }
  }
  db = getFirestore();
  auth = getAuth();
}

export const adminDb = db;
export const adminAuth = auth;

