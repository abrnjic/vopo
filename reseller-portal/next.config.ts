import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  turbopack: { root: process.cwd() },
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
