package com.vopo.domain.model

data class DeviceDiagnosticsReport(
    val licenseStatus: String,
    val downloadMbps: Double? = null,
    val speedMeasuredAtMs: Long? = null,
    val connectionType: String? = null,
)
