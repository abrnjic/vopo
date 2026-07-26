import { Resend } from "resend";

export interface SendSupportEmailOptions {
  subject: string;
  message: string;
  replyTo?: string;
  userId: string;
}

export class EmailService {
  private resend: Resend;

  constructor(apiKey: string) {
    if (!apiKey) {
      console.warn("RESEND_API_KEY is not set. Emails will not be sent.");
    }
    this.resend = new Resend(apiKey || "dummy_key");
  }

  async sendSupportEmail(options: SendSupportEmailOptions): Promise<void> {
    if (!this.resend || (this.resend as any).key === "dummy_key") {
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
    } catch (error) {
      console.error("Error in EmailService.sendSupportEmail:", error);
      throw error;
    }
  }
}
