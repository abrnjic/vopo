package com.vopo.domain.repository

import com.vopo.domain.model.LicenseStatus
import kotlinx.coroutines.flow.Flow

interface LicenseRepository {
    fun getDeviceId(): Flow<String>
    fun getLicenseStatus(): Flow<LicenseStatus>
    suspend fun checkLicenseOnce(): LicenseStatus
    suspend fun generateAndRegisterDeviceIdIfNeeded(): String
}
