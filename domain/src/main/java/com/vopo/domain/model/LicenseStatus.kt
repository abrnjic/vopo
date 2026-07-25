package com.vopo.domain.model

data class RemoteProviderConfig(
    val url: String,
    val username: String,
    val password: String
)

sealed class LicenseStatus {
    data class Active(val config: RemoteProviderConfig? = null) : LicenseStatus()
    data class Trial(val daysRemaining: Int, val config: RemoteProviderConfig? = null) : LicenseStatus()
    object Expired : LicenseStatus()
    object Unregistered : LicenseStatus()
    object Loading : LicenseStatus()
}
