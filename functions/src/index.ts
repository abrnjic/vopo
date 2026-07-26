import * as functions from "firebase-functions";
import * as admin from "firebase-admin";
import { EmailService } from "./services/EmailService";
import { defineSecret } from "firebase-functions/params";

const resendApiKey = defineSecret("RESEND_API_KEY");

admin.initializeApp();

export const sendSupportEmail = functions
  .runWith({ secrets: [resendApiKey] })
  .https.onCall(async (data, context) => {
  // Validate request
  if (!context.auth) {
    throw new functions.https.HttpsError(
      "unauthenticated",
      "The function must be called while authenticated."
    );
  }

  // App Check validation (recommended if enforced)
  // if (context.app == undefined) {
  //   throw new functions.https.HttpsError(
  //       'failed-precondition',
  //       'The function must be called from an App Check verified app.')
  // }

  const { subject, message, replyTo } = data;

  if (!subject || !message) {
    throw new functions.https.HttpsError(
      "invalid-argument",
      "The function must be called with a subject and message."
    );
  }

  if (subject.length > 200 || message.length > 5000) {
    throw new functions.https.HttpsError(
      "invalid-argument",
      "Subject or message exceeds maximum length."
    );
  }

  const apiKey = resendApiKey.value();
  const emailService = new EmailService(apiKey);

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
