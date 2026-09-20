export type ApkChannel = 'stable' | 'test';

export function parseApkChannel(value: unknown): ApkChannel {
  return value === 'test' ? 'test' : 'stable';
}

export function apkMetadataDocument(channel: ApkChannel): string {
  return channel === 'test' ? 'apk_test_metadata' : 'apk_metadata';
}

export function apkPathname(channel: ApkChannel, versionName: string, versionCode: string): string {
  return channel === 'test'
    ? `apk/test/vopoapp-test-${versionName}-${versionCode}.apk`
    : `apk/releases/vopoapp-${versionName}-${versionCode}.apk`;
}
