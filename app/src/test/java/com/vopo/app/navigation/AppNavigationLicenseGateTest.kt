package com.vopo.app.navigation

import com.google.common.truth.Truth.assertThat
import com.vopo.domain.model.LicenseStatus
import org.junit.Test

class AppNavigationLicenseGateTest {
    @Test
    fun expiredLicenseReturnsEveryActiveScreenToActivation() {
        assertThat(shouldReturnToLicenseScreen(LicenseStatus.Expired, Routes.HOME)).isTrue()
        assertThat(shouldReturnToLicenseScreen(LicenseStatus.Expired, Routes.PLAYER)).isTrue()
    }

    @Test
    fun activeAndTrialLicensesRemainInApplication() {
        assertThat(shouldReturnToLicenseScreen(LicenseStatus.Active(), Routes.HOME)).isFalse()
        assertThat(shouldReturnToLicenseScreen(LicenseStatus.Trial(1), Routes.HOME)).isFalse()
    }

    @Test
    fun activationScreenDoesNotNavigateToItself() {
        assertThat(shouldReturnToLicenseScreen(LicenseStatus.Expired, Routes.WELCOME)).isFalse()
    }
}
