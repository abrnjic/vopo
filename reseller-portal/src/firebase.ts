import { initializeApp, getApps, getApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";

const getFirebaseConfig = () => {
  const stripQuotes = (val?: string) => val?.replace(/^["']|["']$/g, '');
  return {
    apiKey: stripQuotes(process.env.NEXT_PUBLIC_FIREBASE_API_KEY) || 'dummy-api-key',
    authDomain: stripQuotes(process.env.NEXT_PUBLIC_FIREBASE_AUTH_DOMAIN) || 'dummy.firebaseapp.com',
    projectId: stripQuotes(process.env.NEXT_PUBLIC_FIREBASE_PROJECT_ID) || 'dummy-project',
    storageBucket: stripQuotes(process.env.NEXT_PUBLIC_FIREBASE_STORAGE_BUCKET) || 'dummy.appspot.com',
    messagingSenderId: stripQuotes(process.env.NEXT_PUBLIC_FIREBASE_MESSAGING_SENDER_ID) || '123456',
    appId: stripQuotes(process.env.NEXT_PUBLIC_FIREBASE_APP_ID) || '1:123456:web:dummy'
  };
};

const firebaseConfig = getFirebaseConfig();

// Initialize Firebase only if it hasn't been initialized yet
const app = !getApps().length ? initializeApp(firebaseConfig) : getApp();

export const auth = getAuth(app);
export const db = getFirestore(app);
