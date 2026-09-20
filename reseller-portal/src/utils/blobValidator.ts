import { apkPathname, type ApkChannel } from '@/lib/apkChannel';

export function validateBlobUrl(latestUrl: string, expectedVersionName: string, expectedVersionCode: string, channel: ApkChannel = 'stable'): boolean {
  try {
    const url = new URL(latestUrl);

    const expectedHostname = process.env.BLOB_HOSTNAME;
    if (!expectedHostname || url.hostname !== expectedHostname) {
      console.error('Invalid blob URL hostname. Expected:', expectedHostname);
      return false;
    }

    const isHttps = url.protocol === 'https:';
    const hasNoPort = url.port === '';
    const hasNoAuth = url.username === '' && url.password === '';

    const expectedPathname = `/${apkPathname(channel, expectedVersionName, expectedVersionCode)}`;
    const isValidPath = url.pathname === expectedPathname;

    if (!isHttps || !hasNoPort || !hasNoAuth || !isValidPath) {
      console.error('Invalid blob URL characteristics:', {
        protocol: url.protocol, port: url.port, auth: !!url.username, pathname: url.pathname
      });
      return false;
    }
    return true;
  } catch {
    return false;
  }
}
