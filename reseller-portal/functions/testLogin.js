const { initializeApp } = require("firebase/app");
const { getAuth, signInWithEmailAndPassword } = require("firebase/auth");

const firebaseConfig = {
  apiKey: "AIzaSyDI0928okWbvWQ0SgjRqv436jteZIMmT78",
  authDomain: "vopoapp-86a75.firebaseapp.com",
  projectId: "vopoapp-86a75",
  storageBucket: "vopoapp-86a75.firebasestorage.app",
  messagingSenderId: "1000759434828",
  appId: "1:1000759434828:web:472be03a1c7fcd73cd1ee9"
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);

signInWithEmailAndPassword(auth, "admin@vopoapp.com", "vopoAdmin2026")
  .then((userCredential) => {
    console.log("Logged in successfully! UID:", userCredential.user.uid);
    process.exit(0);
  })
  .catch((error) => {
    console.error("Login failed:", error.code, error.message);
    process.exit(1);
  });
