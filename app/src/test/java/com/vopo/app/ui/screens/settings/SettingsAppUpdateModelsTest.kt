package com.vopo.app.ui.screens.settings

import com.google.common.truth.Truth.assertThat
import com.vopo.app.update.AppUpdateChannel
import org.junit.Test

class SettingsAppUpdateModelsTest {

    @Test
    fun releaseInfoRequiresChecksumBeforeDownload() {
        val withoutChecksum = AppUpdateUiModel(
            latestVersionName = "1.0.16",
            latestVersionCode = 17,
            releaseUrl = "https://www.vopoapp.com/download",
            downloadUrl = "https://www.vopoapp.com/download"
        )
        val withChecksum = withoutChecksum.copy(sha256 = "a".repeat(64))

        assertThat(withoutChecksum.toReleaseInfoOrNull()).isNull()
        assertThat(withChecksum.toReleaseInfoOrNull()?.sha256).isEqualTo("a".repeat(64))
    }

    @Test
    fun stableBuildIgnoresBetaRelease() {
        val result = isRemoteVersionNewerForBuild(
            remoteVersionCode = 12,
            remoteVersionName = "1.0.11-beta-deadbee",
            remotePublishedAt = "2026-05-14T10:00:00Z",
            currentVersionCode = 12,
            currentVersionName = "1.0.11",
            currentBuildTimestampUtc = 1_747_216_000_000L,
            currentChannel = AppUpdateChannel.Stable
        )

        assertThat(result).isFalse()
    }

    @Test
    fun betaBuildIgnoresStableRelease() {
        val result = isRemoteVersionNewerForBuild(
            remoteVersionCode = 12,
            remoteVersionName = "1.0.11",
            remotePublishedAt = "2026-05-14T10:00:00Z",
            currentVersionCode = 12,
            currentVersionName = "1.0.11-beta",
            currentBuildTimestampUtc = 1_747_216_000_000L,
            currentChannel = AppUpdateChannel.Beta
        )

        assertThat(result).isFalse()
    }

    @Test
    fun betaBuildAcceptsNewerBaseVersionBetaRelease() {
        val result = isRemoteVersionNewerForBuild(
            remoteVersionCode = 13,
            remoteVersionName = "1.0.12-beta-cafebad",
            remotePublishedAt = "2026-05-14T10:00:00Z",
            currentVersionCode = 12,
            currentVersionName = "1.0.11-beta",
            currentBuildTimestampUtc = 1_747_216_000_000L,
            currentChannel = AppUpdateChannel.Beta
        )

        assertThat(result).isTrue()
    }

    @Test
    fun betaBuildAcceptsSameVersionBetaReleaseWhenPublishedLater() {
        val result = isRemoteVersionNewerForBuild(
            remoteVersionCode = 12,
            remoteVersionName = "1.0.11-beta-deadbee",
            remotePublishedAt = "2026-05-14T12:30:00Z",
            currentVersionCode = 12,
            currentVersionName = "1.0.11-beta",
            currentBuildTimestampUtc = 0L,
            currentChannel = AppUpdateChannel.Beta
        )

        assertThat(result).isTrue()
    }

    @Test
    fun betaBuildRejectsSameVersionBetaReleaseWhenNotPublishedLater() {
        val result = isRemoteVersionNewerForBuild(
            remoteVersionCode = 12,
            remoteVersionName = "1.0.11-beta-deadbee",
            remotePublishedAt = "2026-05-14T08:00:00Z",
            currentVersionCode = 12,
            currentVersionName = "1.0.11-beta",
            currentBuildTimestampUtc = Long.MAX_VALUE,
            currentChannel = AppUpdateChannel.Beta
        )

        assertThat(result).isFalse()
    }
}
