const admin = require("firebase-admin");

admin.initializeApp({
  projectId: "vopoapp-86a75"
});

async function run() {
  try {
    const email = "admin@vopoapp.com";
    const password = "vopoAdmin2026";
    let userRecord;

    try {
      // Check if user already exists
      userRecord = await admin.auth().getUserByEmail(email);
      console.log("User already exists in Auth. Updating password...");
      await admin.auth().updateUser(userRecord.uid, { password });
    } catch (error) {
      if (error.code === 'auth/user-not-found') {
        console.log("User not found. Creating in Auth...");
        userRecord = await admin.auth().createUser({
          email: email,
          password: password,
          emailVerified: true,
        });
      } else {
        throw error;
      }
    }

    console.log(`User UID: ${userRecord.uid}`);

    // Wait a brief moment to ensure the onUserCreate trigger (if any) doesn't overwrite our admin role
    // Alternatively, we just set the role now and if it's overwritten we set it again.
    // Actually, setting it directly here:
    await admin.firestore().collection("users").doc(userRecord.uid).set({
      email: email,
      role: "admin",
      status: "active",
      walletBalance: 1000,
      createdAt: admin.firestore.FieldValue.serverTimestamp(),
    }, { merge: true });

    // Since onUserCreate might run concurrently and set role to 'subreseller', let's wait 3 seconds and force it again to be safe.
    await new Promise(resolve => setTimeout(resolve, 3000));
    
    await admin.firestore().collection("users").doc(userRecord.uid).update({
      role: "admin",
      status: "active"
    });

    console.log(`\n=== USPJEH ===`);
    console.log(`Admin račun uspješno kreiran!`);
    console.log(`Email: ${email}`);
    console.log(`Lozinka: ${password}`);
    
  } catch (e) {
    console.error("Failed:", e);
  }
}

run();
