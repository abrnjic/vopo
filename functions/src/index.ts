import * as functions from "firebase-functions";
import * as admin from "firebase-admin";
import { EmailService } from "./services/EmailService";
import * as dotenv from "dotenv";

// Load environment variables
dotenv.config();

admin.initializeApp();

const emailService = new EmailService();

export const sendSupportEmail = functions.https.onCall(async (data, context) => {
  // Validate request
  if (!context.auth) {
    throw new functions.https.HttpsError(
      "unauthenticated",
      "The function must be called while authenticated."
    );
  }

  const { subject, message, replyTo } = data;

  if (!subject || !message) {
    throw new functions.https.HttpsError(
      "invalid-argument",
      "The function must be called with a subject and message."
    );
  }

  try {
    await emailService.sendSupportEmail({
      subject,
      message,
      replyTo,
      userId: context.auth.uid,
    });
    return { success: true };
  } catch (error) {
    console.error("Error sending email:", error);
    throw new functions.https.HttpsError(
      "internal",
      "An error occurred while sending the email."
    );
  }
});
