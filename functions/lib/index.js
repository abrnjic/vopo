"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.sendSupportEmail = void 0;
const functions = require("firebase-functions");
const admin = require("firebase-admin");
const EmailService_1 = require("./services/EmailService");
const params_1 = require("firebase-functions/params");
const resendApiKey = (0, params_1.defineSecret)("RESEND_API_KEY");
admin.initializeApp();
exports.sendSupportEmail = functions
    .runWith({ secrets: [resendApiKey] })
    .https.onCall(async (data, context) => {
    // Validate request
    if (!context.auth) {
        throw new functions.https.HttpsError("unauthenticated", "The function must be called while authenticated.");
    }
    // App Check validation (recommended if enforced)
    // if (context.app == undefined) {
    //   throw new functions.https.HttpsError(
    //       'failed-precondition',
    //       'The function must be called from an App Check verified app.')
    // }
    const { subject, message, replyTo } = data;
    if (!subject || !message) {
        throw new functions.https.HttpsError("invalid-argument", "The function must be called with a subject and message.");
    }
    if (subject.length > 200 || message.length > 5000) {
        throw new functions.https.HttpsError("invalid-argument", "Subject or message exceeds maximum length.");
    }
    const apiKey = resendApiKey.value();
    const emailService = new EmailService_1.EmailService(apiKey);
    try {
        await emailService.sendSupportEmail({
            subject,
            message,
            replyTo,
            userId: context.auth.uid,
        });
        return { success: true };
    }
    catch (error) {
        console.error("Error sending email:", error);
        throw new functions.https.HttpsError("internal", "An error occurred while sending the email.");
    }
});
//# sourceMappingURL=index.js.map