package com.vopo.domain.repository

import com.vopo.domain.model.LicenseStatus
import com.vopo.domain.model.DeviceDiagnosticsReport
import kotlinx.coroutines.flow.Flow

interface LicenseRepository {
    fun getDeviceId(): Flow<String>
    fun getLicenseStatus(): Flow<LicenseStatus>
    suspend fun checkLicenseOnce(): LicenseStatus
    suspend fun generateAndRegisterDeviceIdIfNeeded(): String
    suspend fun reportDiagnostics(report: DeviceDiagnosticsReport): Boolean
}
