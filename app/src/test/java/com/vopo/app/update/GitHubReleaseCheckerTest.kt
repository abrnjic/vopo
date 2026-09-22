package com.vopo.app.update

import com.google.common.truth.Truth.assertThat
import com.vopo.domain.model.Result
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GitHubReleaseCheckerTest {

    @Test
    fun stableChannelUsesVercelMetadataAndPermanentDownloadUrl() = runTest {
        var requestedUrl: String? = null
        val checker = GitHubReleaseChecker(
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    requestedUrl = chain.request().url.toString()
                    Response.Builder()
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_1)
                        .code(200)
                        .message("OK")
                        .body(
                            """{
                                "versionName":"1.0.16",
                                "versionCode":"17",
                                "checksum":"${"a".repeat(64)}",
                                "latestUrl":"https://blob.example/apk.apk",
                                "minimumVersionCode":15,
                                "forceUpdate":true,
                                "updatedAt":"2026-09-21T10:00:00Z"
                            }""".trimIndent().toResponseBody("application/json".toMediaType())
                        )
                        .build()
                }
                .build()
        )

        val result = checker.fetchLatestRelease(AppUpdateChannel.Stable)

        assertThat(requestedUrl).isEqualTo("https://www.vopoapp.com/api/apk/latest")
        assertThat(result).isInstanceOf(Result.Success::class.java)
        val release = (result as Result.Success).data
        assertThat(release.versionName).isEqualTo("1.0.16")
        assertThat(release.versionCode).isEqualTo(17)
        assertThat(release.downloadUrl).isEqualTo("https://www.vopoapp.com/download")
        assertThat(release.sha256).isEqualTo("a".repeat(64))
        assertThat(release.publishedAt).isEqualTo("2026-09-21T10:00:00Z")
        assertThat(release.minimumVersionCode).isEqualTo(15)
        assertThat(release.forceUpdate).isTrue()
    }

    @Test
    fun minimumVersionAndForcedLatestBlockOnlyOlderBuilds() {
        val base = GitHubReleaseInfo("2.0", 20, "https://www.vopoapp.com/download", "https://www.vopoapp.com/download", "a".repeat(64), "", null, minimumVersionCode = 15)
        assertThat(requiresForcedUpdate(14, base)).isTrue()
        assertThat(requiresForcedUpdate(15, base)).isFalse()
        assertThat(requiresForcedUpdate(19, base.copy(forceUpdate = true))).isTrue()
        assertThat(requiresForcedUpdate(20, base.copy(forceUpdate = true))).isFalse()
    }

    @Test
    fun betaChannelUsesSeparateTestMetadataAndDownloadUrl() = runTest {
        var requestedUrl: String? = null
        val checker = GitHubReleaseChecker(
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    requestedUrl = chain.request().url.toString()
                    Response.Builder()
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_1)
                        .code(200)
                        .message("OK")
                        .body(
                            """{
                                "versionName":"1.0.16-beta",
                                "versionCode":17,
                                "checksum":"${"b".repeat(64)}"
                            }""".trimIndent().toResponseBody("application/json".toMediaType())
                        )
                        .build()
                }
                .build()
        )

        val result = checker.fetchLatestRelease(AppUpdateChannel.Beta)

        assertThat(requestedUrl).isEqualTo("https://www.vopoapp.com/api/apk/test")
        assertThat(result).isInstanceOf(Result.Success::class.java)
        assertThat((result as Result.Success).data.downloadUrl)
            .isEqualTo("https://www.vopoapp.com/download/test")
    }

    @Test
    fun invalidChecksumRejectsMetadata() = runTest {
        val checker = GitHubReleaseChecker(
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    Response.Builder()
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_1)
                        .code(200)
                        .message("OK")
                        .body(
                            """{"versionName":"1.0.16","versionCode":17,"checksum":"invalid"}"""
                                .toResponseBody("application/json".toMediaType())
                        )
                        .build()
                }
                .build()
        )

        val result = checker.fetchLatestRelease(AppUpdateChannel.Stable)

        assertThat(result).isInstanceOf(Result.Error::class.java)
        assertThat((result as Result.Error).message).contains("checksum")
    }
}
