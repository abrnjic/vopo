import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  transpilePackages: ['firebase-admin', 'jwks-rsa', 'jose'],
  async redirects() {
    return [
      {
        source: '/upload',
        destination: '/connect',
        permanent: true,
      },
    ]
  },
};

export default nextConfig;
