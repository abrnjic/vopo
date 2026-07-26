import { initializeApp } from "firebase/app";
import { getFirestore, doc, setDoc } from "firebase/firestore";
import { getAuth, createUserWithEmailAndPassword } from "firebase/auth";

const firebaseConfig = {
  apiKey: "YOUR_API_KEY",
  authDomain: "vopoapp-86a75.firebaseapp.com",
  projectId: "vopoapp-86a75",
  storageBucket: "vopoapp-86a75.firebasestorage.app",
  messagingSenderId: "1000759434828",
  appId: "1:1000759434828:web:472be03a1c7fcd73cd1ee9"
};

const app = initializeApp(firebaseConfig);
const db = getFirestore(app);
const auth = getAuth(app);

const email = 'admin@vopo.com';
const password = 'adminpassword123';

async function createAdmin() {
  try {
    const userCredential = await createUserWithEmailAndPassword(auth, email, password);
    const uid = userCredential.user.uid;
    
    await setDoc(doc(db, 'users', uid), {
      email: email,
      role: 'admin',
      credits: 999999, // Admin has infinite credits practically
      assignedDomains: ['http://vopo.tv:8080'],
      customDomains: []
    });

    console.log(`✅ Admin created successfully!`);
    console.log(`Email: ${email}`);
    console.log(`Password: ${password}`);
  } catch(e) {
    if (e.code === 'auth/email-already-in-use') {
        console.log(`Admin user already exists! You can log in with ${email}`);
    } else {
        console.error('❌ Error creating admin:', e);
    }
  }
  process.exit(0);
}
createAdmin();
