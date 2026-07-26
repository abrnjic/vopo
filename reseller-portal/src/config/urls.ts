export const Config = {
  DOMAIN: process.env.NEXT_PUBLIC_DOMAIN || 'https://vopoapp.com',
  PORTAL_DOMAIN: process.env.NEXT_PUBLIC_PORTAL_DOMAIN || 'https://portal.vopoapp.com',
  API_DOMAIN: process.env.NEXT_PUBLIC_API_DOMAIN || 'https://api.vopoapp.com',
  CONNECT_URL: process.env.NEXT_PUBLIC_CONNECT_URL || 'https://vopoapp.com/connect',
  PRIVACY_URL: process.env.NEXT_PUBLIC_PRIVACY_URL || 'https://vopoapp.com/legal/privacy',
  TERMS_URL: process.env.NEXT_PUBLIC_TERMS_URL || 'https://vopoapp.com/legal/terms',
  SUPPORT_EMAIL: process.env.NEXT_PUBLIC_SUPPORT_EMAIL || 'support@vopoapp.com',
  LATEST_APK_URL: process.env.NEXT_PUBLIC_LATEST_APK_URL || 'https://vopoapp.com/api/download/latest.apk',
};
