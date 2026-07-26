import * as functions from "firebase-functions";
import * as admin from "firebase-admin";

export const onUserCreate = functions.auth.user().onCreate(async (user) => {
  // Setup default user document in Firestore when Auth user is created
  await admin.firestore().collection("users").doc(user.uid).set({
    email: user.email,
    role: "subreseller",
    status: "pending",
    walletBalance: 0,
    createdAt: admin.firestore.FieldValue.serverTimestamp(),
  });
});
