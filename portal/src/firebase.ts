// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyD1lfC94ca1J-BHE3wFxaoDpF9ILEKixrM",
  authDomain: "vopoapp-86a75.firebaseapp.com",
  projectId: "vopoapp-86a75",
  storageBucket: "vopoapp-86a75.firebasestorage.app",
  messagingSenderId: "1000759434828",
  appId: "1:1000759434828:web:472be03a1c7fcd73cd1ee9"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
export const db = getFirestore(app);
