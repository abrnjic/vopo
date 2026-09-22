package com.vopo.data.repository

import android.content.Context
import android.app.ActivityManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.StatFs
import android.os.SystemClock
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.vopo.domain.model.LicenseStatus
import com.vopo.domain.model.DeviceDiagnosticsReport
import com.vopo.domain.repository.LicenseRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import java.util.UUID
import java.security.SecureRandom
import android.util.Base64
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.launch

private val Context.licenseDataStore: DataStore<Preferences> by preferencesDataStore(name = "license_prefs")

@Singleton
class LicenseRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val apiClient: DeviceLicenseApiClient,
) : LicenseRepository {

    private val DEVICE_ID_KEY = stringPreferencesKey("device_id")
    private val DEVICE_TOKEN_KEY = stringPreferencesKey("device_token")
    @Volatile private var registered = false
    @Volatile private var lastDiagnosticsAtElapsedMs = 0L

    override fun getDeviceId(): Flow<String> {
        return context.licenseDataStore.data.map { preferences ->
            preferences[DEVICE_ID_KEY] ?: ""
        }
    }

    override fun getLicenseStatus(): Flow<LicenseStatus> = callbackFlow {
        val deviceId = getDeviceId().first()
        if (deviceId.isEmpty()) {
            trySend(LicenseStatus.Unregistered)
            close()
            return@callbackFlow
        }
        
        val job = launch(Dispatchers.IO) {
            val token = getOrCreateDeviceToken()
            while (isActive) {
                val status = runCatching {
                    ensureRegistered(deviceId, token)
                    apiClient.getLicense(deviceId, token).also { current ->
                        reportHeartbeatIfDue(deviceId, token, current)
                    }
                }.getOrElse {
                    registered = false
                    LicenseStatus.Unregistered
                }
                trySend(status)
                delay(LICENSE_POLL_INTERVAL_MS)
            }
        }
        awaitClose { job.cancel() }
    }

    override suspend fun checkLicenseOnce(): LicenseStatus {
        val deviceId = getDeviceId().first()
        if (deviceId.isEmpty()) return LicenseStatus.Unregistered
        
        val token = getOrCreateDeviceToken()
        return runCatching {
            ensureRegistered(deviceId, token)
            apiClient.getLicense(deviceId, token)
        }.getOrElse { LicenseStatus.Unregistered }
    }

    override suspend fun generateAndRegisterDeviceIdIfNeeded(): String {
        val currentId = getDeviceId().first()
        if (currentId.isNotEmpty()) {
            // Device identity is local state and must be available to the UI even
            // when the registration endpoint is slow or temporarily unreachable.
            // getLicenseStatus/checkLicenseOnce perform registration on Dispatchers.IO.
            getOrCreateDeviceToken()
            return currentId
        }

        // Generate 9 digit alphanumeric code: XXX-XXX-XXX
        val randomString = UUID.randomUUID().toString().replace("-", "").take(9).uppercase()
        val newDeviceId = "${randomString.substring(0, 3)}-${randomString.substring(3, 6)}-${randomString.substring(6, 9)}"
        
        val token = createDeviceToken()
        context.licenseDataStore.edit { prefs ->
            prefs[DEVICE_ID_KEY] = newDeviceId
            prefs[DEVICE_TOKEN_KEY] = token
        }

        return newDeviceId
    }

    override suspend fun reportDiagnostics(report: DeviceDiagnosticsReport): Boolean = withContext(Dispatchers.IO) {
        val deviceId = getDeviceId().first()
        if (deviceId.isBlank()) return@withContext false
        val token = getOrCreateDeviceToken()
        runCatching {
            ensureRegistered(deviceId, token)
            apiClient.reportDiagnostics(deviceId, token, report, collectDeviceInfo())
            lastDiagnosticsAtElapsedMs = SystemClock.elapsedRealtime()
        }.isSuccess
    }

    private fun reportHeartbeatIfDue(deviceId: String, token: String, status: LicenseStatus) {
        val now = SystemClock.elapsedRealtime()
        if (lastDiagnosticsAtElapsedMs != 0L && now - lastDiagnosticsAtElapsedMs < DIAGNOSTICS_INTERVAL_MS) return
        runCatching {
            apiClient.reportDiagnostics(
                deviceId = deviceId,
                deviceToken = token,
                report = DeviceDiagnosticsReport(
                    licenseStatus = status.diagnosticsName(),
                    connectionType = activeConnectionType(),
                ),
                deviceInfo = collectDeviceInfo(),
            )
            lastDiagnosticsAtElapsedMs = now
        }
    }

    private fun collectDeviceInfo(): Map<String, Any> {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        val memoryInfo = ActivityManager.MemoryInfo().also { info ->
            (context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).getMemoryInfo(info)
        }
        val storage = StatFs(context.filesDir.absolutePath)
        return mapOf(
            "appVersion" to (packageInfo.versionName ?: "unknown"),
            "appVersionCode" to packageInfo.longVersionCode,
            "androidVersion" to Build.VERSION.RELEASE,
            "deviceModel" to "${Build.MANUFACTURER} ${Build.MODEL}".trim().take(120),
            "connectionType" to activeConnectionType(),
            "availableStorageBytes" to storage.availableBytes,
            "availableMemoryBytes" to memoryInfo.availMem,
        )
    }

    private fun activeConnectionType(): String {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager ?: return "UNKNOWN"
        val capabilities = manager.getNetworkCapabilities(manager.activeNetwork) ?: return "UNKNOWN"
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "WIFI"
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> "ETHERNET"
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "CELLULAR"
            else -> "OTHER"
        }
    }

    private fun LicenseStatus.diagnosticsName(): String = when (this) {
        is LicenseStatus.Active -> "active"
        is LicenseStatus.Trial -> "trial"
        LicenseStatus.Expired -> "expired"
        LicenseStatus.Unregistered -> "unregistered"
        LicenseStatus.Loading -> "unknown"
    }

    private suspend fun getOrCreateDeviceToken(): String {
        val existing = context.licenseDataStore.data.first()[DEVICE_TOKEN_KEY]
        if (!existing.isNullOrBlank()) return existing
        val token = createDeviceToken()
        context.licenseDataStore.edit { it[DEVICE_TOKEN_KEY] = token }
        return token
    }

    private fun createDeviceToken(): String {
        val bytes = ByteArray(32)
        SecureRandom().nextBytes(bytes)
        return Base64.encodeToString(bytes, Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING)
    }

    private fun ensureRegistered(deviceId: String, token: String) {
        if (registered) return
        apiClient.register(deviceId, token)
        registered = true
    }

    private companion object {
        const val LICENSE_POLL_INTERVAL_MS = 5_000L
        const val DIAGNOSTICS_INTERVAL_MS = 15 * 60_000L
    }
}
