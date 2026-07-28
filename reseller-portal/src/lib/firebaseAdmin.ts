import { initializeApp, getApps, cert } from 'firebase-admin/app';
import { getFirestore } from 'firebase-admin/firestore';
import { getAuth } from 'firebase-admin/auth';
import { mockAdminAuth, mockAdminDb } from './mockFirebaseAdmin';

let _db: any;
let _auth: any;

function initFirebaseAdmin() {
  if (_db && _auth) return;

  if (process.env.MOCK_FIREBASE === 'true' && process.env.NODE_ENV === 'test') {
    console.log('Using MOCK Firebase Admin');
    _db = mockAdminDb;
    _auth = mockAdminAuth;
    return;
  }

  if (process.env.MOCK_FIREBASE === 'true') {
    console.warn('MOCK_FIREBASE=true ignored because NODE_ENV is not "test"');
  }

  if (!getApps().length) {
    const stripQuotes = (val?: string) => val?.replace(/^["']|["']$/g, '');
    const projectId = stripQuotes(process.env.FIREBASE_PROJECT_ID || process.env.NEXT_PUBLIC_FIREBASE_PROJECT_ID);
    const clientEmail = stripQuotes(process.env.FIREBASE_CLIENT_EMAIL);
    const privateKey = stripQuotes(process.env.FIREBASE_PRIVATE_KEY)?.replace(/\\n/g, '\n');

    if (!projectId || !clientEmail || !privateKey || !privateKey.includes('BEGIN PRIVATE KEY')) {
      throw new Error('Production Firebase initialization failed: Missing or invalid FIREBASE_PROJECT_ID, FIREBASE_CLIENT_EMAIL, or FIREBASE_PRIVATE_KEY.');
    }

    try {
      initializeApp({
        credential: cert({
          projectId,
          clientEmail,
          privateKey,
        }),
      });
      _db = getFirestore();
      _auth = getAuth();
    } catch (error) {
      console.error('Firebase admin initialization failed.', error);
      throw new Error('Production Firebase initialization failed.');
    }
  } else {
    _db = getFirestore();
    _auth = getAuth();
  }
}

export const adminDb: any = new Proxy({}, {
  get: (_, prop) => {
    initFirebaseAdmin();
    const value = _db[prop];
    return typeof value === 'function' ? value.bind(_db) : value;
  }
});

export const adminAuth: any = new Proxy({}, {
  get: (_, prop) => {
    initFirebaseAdmin();
    const value = _auth[prop];
    return typeof value === 'function' ? value.bind(_auth) : value;
  }
});

