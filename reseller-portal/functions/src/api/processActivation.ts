import * as functions from "firebase-functions";
import * as admin from "firebase-admin";

export const processActivation = functions.https.onCall(async (data, context) => {
  // Ensure user is authenticated
  if (!context.auth) {
    throw new functions.https.HttpsError("unauthenticated", "User must be logged in.");
  }

  const { macAddress, appName, licenseType } = data;
  const uid = context.auth.uid;

  // Transaction to deduct balance and record license activation safely
  return await admin.firestore().runTransaction(async (transaction) => {
    const userRef = admin.firestore().collection("users").doc(uid);
    const userSnap = await transaction.get(userRef);

    if (!userSnap.exists) {
      throw new functions.https.HttpsError("not-found", "User not found.");
    }

    const userData = userSnap.data();
    // Assuming a cost, for example 1 credit per year
    const cost = licenseType === "lifetime" ? 2.0 : 1.0; 

    if ((userData?.walletBalance || 0) < cost) {
      throw new functions.https.HttpsError("resource-exhausted", "Insufficient balance.");
    }

    // Deduct balance
    const newBalance = userData!.walletBalance - cost;
    transaction.update(userRef, { walletBalance: newBalance });

    // Create license record
    const licenseRef = admin.firestore().collection("licenses").doc();
    transaction.set(licenseRef, {
      macAddress,
      appName,
      licenseType,
      status: "active",
      activatedBy: uid,
      createdAt: admin.firestore.FieldValue.serverTimestamp(),
    });

    // Create audit log
    const auditRef = admin.firestore().collection("audit_logs").doc();
    transaction.set(auditRef, {
      action: "LICENSE_ACTIVATED",
      userId: uid,
      details: { macAddress, appName, cost },
      timestamp: admin.firestore.FieldValue.serverTimestamp(),
    });

    // Note: External API calls (IBO, SmartOne) should ideally be done AFTER successful transaction
    // using a pub/sub trigger or similar to ensure atomicity, or very carefully within the process.

    return { success: true, newBalance };
  });
});
