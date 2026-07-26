import * as admin from "firebase-admin";

// Initialize Firebase Admin SDK
admin.initializeApp();

// Export triggers
export * from "./triggers/onUserCreate";

// Export API routes
export * from "./api/processActivation";
