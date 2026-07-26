// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

const getFirebaseConfig = () => {
  const config = {
    apiKey: process.env.NEXT_PUBLIC_FIREBASE_API_KEY,
    authDomain: process.env.NEXT_PUBLIC_FIREBASE_AUTH_DOMAIN,
    projectId: process.env.NEXT_PUBLIC_FIREBASE_PROJECT_ID,
    storageBucket: process.env.NEXT_PUBLIC_FIREBASE_STORAGE_BUCKET,
    messagingSenderId: process.env.NEXT_PUBLIC_FIREBASE_MESSAGING_SENDER_ID,
    appId: process.env.NEXT_PUBLIC_FIREBASE_APP_ID
  };
  
  for (const [key, value] of Object.entries(config)) {
    if (!value) {
      console.warn(`Missing Firebase configuration key: ${key}`);
    }
  }
  
  // Use fallbacks to prevent initializeApp from crashing during Next.js build (SSR prerendering)
  return {
    apiKey: config.apiKey || "fallback",
    authDomain: config.authDomain || "fallback",
    projectId: config.projectId || "fallback",
    storageBucket: config.storageBucket || "fallback",
    messagingSenderId: config.messagingSenderId || "fallback",
    appId: config.appId || "fallback"
  };
};

const firebaseConfig = getFirebaseConfig();

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
export const db = getFirestore(app);
