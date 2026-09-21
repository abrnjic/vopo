package com.vopo.app.update

import com.vopo.app.BuildConfig
import com.vopo.domain.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val STABLE_RELEASE_API_URL = "https://www.vopoapp.com/api/apk/latest"
private const val TEST_RELEASE_API_URL = "https://www.vopoapp.com/api/apk/test"
private const val STABLE_DOWNLOAD_URL = "https://www.vopoapp.com/download"
private const val TEST_DOWNLOAD_URL = "https://www.vopoapp.com/download/test"

data class GitHubReleaseInfo(
    val versionName: String,
    val versionCode: Int?,
    val releaseUrl: String,
    val downloadUrl: String?,
    val sha256: String,
    val releaseNotes: String,
    val publishedAt: String?
)

@Singleton
class GitHubReleaseChecker @Inject constructor(
    private val okHttpClient: OkHttpClient
) {
    private companion object {
        private const val MAX_RESPONSE_BYTES = 512 * 1024L
    }

    suspend fun fetchLatestRelease(
        updateChannel: AppUpdateChannel = AppUpdateChannel.fromCurrentBuild()
    ): Result<GitHubReleaseInfo> = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url(updateChannel.releaseApiUrl)
                .header("Accept", "application/json")
                .header("User-Agent", "Vopo-Update-Checker")
                .build()

            okHttpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    return@withContext Result.error("Update check failed: HTTP ${response.code}")
                }

                val body = when (val bodyResult = response.body?.let(::readResponseBodyCapped)) {
                    is Result.Success -> bodyResult.data
                    is Result.Error -> return@withContext Result.error(bodyResult.message, bodyResult.exception)
                    null,
                    Result.Loading -> ""
                }
                if (body.isBlank()) {
                    return@withContext Result.error("Update check failed: empty release response")
                }

                val json = JSONObject(body)
                val versionName = json.optString("versionName").trim()
                val versionCode = json.opt("versionCode")?.toString()?.toIntOrNull()
                val checksum = json.optString("checksum").trim()
                if (versionName.isBlank() || versionCode == null || versionCode <= 0) {
                    return@withContext Result.error("Update check failed: release metadata is invalid")
                }
                if (!checksum.matches(Regex("^[a-fA-F0-9]{64}$"))) {
                    return@withContext Result.error("Update check failed: release checksum is invalid")
                }

                return@withContext Result.success(
                    GitHubReleaseInfo(
                        versionName = versionName,
                        versionCode = versionCode,
                        releaseUrl = updateChannel.downloadUrl,
                        downloadUrl = updateChannel.downloadUrl,
                        sha256 = checksum.lowercase(),
                        releaseNotes = json.optString("releaseNotes").trim(),
                        publishedAt = json.optString("updatedAt").takeIf { it.isNotBlank() }
                    )
                )
            }
        } catch (error: IOException) {
            Result.error("Update check failed: network error", error)
        } catch (error: Exception) {
            Result.error("Update check failed: ${error.message}", error)
        }
    }

    private fun readResponseBodyCapped(body: ResponseBody): Result<String> {
        val contentLength = body.contentLength()
        if (contentLength > MAX_RESPONSE_BYTES) {
            return Result.error("Update check failed: release response exceeded 512 KB")
        }

        val charset = body.contentType()?.charset(Charsets.UTF_8) ?: Charsets.UTF_8
        val output = ByteArrayOutputStream()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        var totalBytesRead = 0L

        body.byteStream().use { input ->
            while (true) {
                val bytesRead = input.read(buffer)
                if (bytesRead == -1) break

                totalBytesRead += bytesRead
                if (totalBytesRead > MAX_RESPONSE_BYTES) {
                    return Result.error("Update check failed: release response exceeded 512 KB")
                }

                output.write(buffer, 0, bytesRead)
            }
        }

        return Result.success(output.toString(charset.name()))
    }
}

enum class AppUpdateChannel(
    val id: String,
    val releaseApiUrl: String,
    val downloadUrl: String
) {
    Stable(id = "stable", releaseApiUrl = STABLE_RELEASE_API_URL, downloadUrl = STABLE_DOWNLOAD_URL),
    Beta(id = "beta", releaseApiUrl = TEST_RELEASE_API_URL, downloadUrl = TEST_DOWNLOAD_URL);

    companion object {
        fun fromCurrentBuild(): AppUpdateChannel {
            return fromBuildConfig(BuildConfig.APP_UPDATE_CHANNEL, BuildConfig.VERSION_NAME)
        }

        fun fromBuildConfig(channelId: String?, versionName: String): AppUpdateChannel {
            return when {
                channelId.equals(Beta.id, ignoreCase = true) -> Beta
                versionName.contains("-beta", ignoreCase = true) -> Beta
                else -> Stable
            }
        }
    }
}
