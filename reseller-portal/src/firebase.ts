// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

const getFirebaseConfig = () => {
  return {
    apiKey: process.env.NEXT_PUBLIC_FIREBASE_API_KEY || "AIzaSyDI0928okWbvWQ0SgjRqv436jteZIMmT78",
    authDomain: process.env.NEXT_PUBLIC_FIREBASE_AUTH_DOMAIN || "vopoapp-86a75.firebaseapp.com",
    projectId: process.env.NEXT_PUBLIC_FIREBASE_PROJECT_ID || "vopoapp-86a75",
    storageBucket: process.env.NEXT_PUBLIC_FIREBASE_STORAGE_BUCKET || "vopoapp-86a75.firebasestorage.app",
    messagingSenderId: process.env.NEXT_PUBLIC_FIREBASE_MESSAGING_SENDER_ID || "1000759434828",
    appId: process.env.NEXT_PUBLIC_FIREBASE_APP_ID || "1:1000759434828:web:472be03a1c7fcd73cd1ee9"
  };
};

const firebaseConfig = getFirebaseConfig();

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
export const db = getFirestore(app);
