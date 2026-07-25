import { Resend } from "resend";

export interface SendSupportEmailOptions {
  subject: string;
  message: string;
  replyTo?: string;
  userId: string;
}

export class EmailService {
  private resend: Resend;

  constructor() {
    const apiKey = process.env.RESEND_API_KEY;
    if (!apiKey) {
      console.warn("RESEND_API_KEY is not set. Emails will not be sent.");
    }
    this.resend = new Resend(apiKey || "dummy_key");
  }

  async sendSupportEmail(options: SendSupportEmailOptions): Promise<void> {
    if (!process.env.RESEND_API_KEY) {
      console.log("Mock sending support email:", options);
      return;
    }

    try {
      await this.resend.emails.send({
        from: "VOPO Support <support@vopoapp.com>",
        to: ["support@vopoapp.com"],
        reply_to: options.replyTo,
        subject: `[Support Request] ${options.subject}`,
        text: `Message from User ID: ${options.userId}\n\n${options.message}`,
      });
    } catch (error) {
      console.error("Error in EmailService.sendSupportEmail:", error);
      throw error;
    }
  }
}
