"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.EmailService = void 0;
const resend_1 = require("resend");
class EmailService {
    constructor(apiKey) {
        if (!apiKey) {
            console.warn("RESEND_API_KEY is not set. Emails will not be sent.");
        }
        this.resend = new resend_1.Resend(apiKey || "dummy_key");
    }
    async sendSupportEmail(options) {
        if (!this.resend || this.resend.key === "dummy_key") {
            console.log("Mock sending support email:", options);
            return;
        }
        try {
            await this.resend.emails.send({
                from: "VOPO Support <noreply@vopoapp.com>",
                to: ["support@vopoapp.com"],
                reply_to: "support@vopoapp.com",
                subject: `[Support Request] ${options.subject}`,
                text: `Message from User ID: ${options.userId}\n\n${options.message}`,
            });
        }
        catch (error) {
            console.error("Error in EmailService.sendSupportEmail:", error);
            throw error;
        }
    }
}
exports.EmailService = EmailService;
//# sourceMappingURL=EmailService.js.map