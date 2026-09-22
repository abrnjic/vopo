package com.vopo.data.repository

import com.google.gson.Gson
import com.vopo.domain.model.LicenseStatus
import com.vopo.domain.model.RemoteProviderConfig
import com.vopo.domain.model.DeviceDiagnosticsReport
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceLicenseApiClient @Inject constructor(
    private val client: OkHttpClient,
    private val gson: Gson,
    @LicenseApiBaseUrl baseUrl: String,
) {
    private val apiBaseUrl = baseUrl.trimEnd('/')

    init {
        require(apiBaseUrl.startsWith("https://") || apiBaseUrl.startsWith("http://localhost")) {
            "License API must use HTTPS"
        }
    }

    fun register(deviceId: String, deviceToken: String) {
        val body = gson.toJson(mapOf("deviceId" to deviceId, "deviceToken" to deviceToken))
            .toRequestBody(JSON_MEDIA_TYPE)
        val request = Request.Builder()
            .url("$apiBaseUrl/api/device/register")
            .post(body)
            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful && response.code != 409) {
                throw IOException("Device registration failed (${response.code})")
            }
            if (response.code == 409) throw DeviceRegistrationConflictException()
        }
    }

    fun getLicense(deviceId: String, deviceToken: String): LicenseStatus {
        val url = "$apiBaseUrl/api/device/license".toHttpUrlWithDeviceId(deviceId)
        val request = Request.Builder()
            .url(url)
            .header("Authorization", "Device $deviceToken")
            .header("Cache-Control", "no-cache")
            .get()
            .build()
        client.newCall(request).execute().use { response ->
            if (response.code == 401 || response.code == 404) return LicenseStatus.Unregistered
            if (!response.isSuccessful) throw IOException("License check failed (${response.code})")
            val responseBody = response.body?.string() ?: throw IOException("Empty license response")
            val payload = gson.fromJson(responseBody, DeviceLicenseResponse::class.java)
            val config = payload.config?.takeIf {
                it.url.isNotBlank() && it.username.isNotBlank() && it.password.isNotBlank()
            }?.let { RemoteProviderConfig(it.url, it.username, it.password) }
            return when (payload.status.lowercase()) {
                "active" -> LicenseStatus.Active(config)
                "trial" -> LicenseStatus.Trial(payload.daysRemaining?.coerceAtLeast(1) ?: 1, config)
                "expired" -> LicenseStatus.Expired
                else -> LicenseStatus.Unregistered
            }
        }
    }

    fun reportDiagnostics(
        deviceId: String,
        deviceToken: String,
        report: DeviceDiagnosticsReport,
        deviceInfo: Map<String, Any>,
    ) {
        val payload = deviceInfo.toMutableMap().apply {
            put("deviceId", deviceId)
            put("licenseStatus", report.licenseStatus)
            put("connectionType", report.connectionType ?: deviceInfo["connectionType"] ?: "UNKNOWN")
            report.downloadMbps?.let { put("downloadMbps", it) }
            report.speedMeasuredAtMs?.let { put("speedMeasuredAtMs", it) }
        }
        val body = gson.toJson(payload).toRequestBody(JSON_MEDIA_TYPE)
        val request = Request.Builder()
            .url("$apiBaseUrl/api/device/diagnostics")
            .header("Authorization", "Device $deviceToken")
            .post(body)
            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Diagnostics upload failed (${response.code})")
        }
    }

    private fun String.toHttpUrlWithDeviceId(deviceId: String) =
        toHttpUrl().newBuilder().addQueryParameter("deviceId", deviceId).build()

    private data class DeviceLicenseResponse(
        val status: String = "unregistered",
        val daysRemaining: Int? = null,
        val config: DeviceConfig? = null,
    )

    private data class DeviceConfig(
        val url: String = "",
        val username: String = "",
        val password: String = "",
    )

    private companion object {
        val JSON_MEDIA_TYPE = "application/json; charset=utf-8".toMediaType()
    }
}

class DeviceRegistrationConflictException : IOException("Device is already registered")
