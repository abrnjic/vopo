package com.vopo.data.repository

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.vopo.domain.model.LicenseStatus
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test

class DeviceLicenseApiClientTest {
    private val token = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"

    @Test
    fun registerThenReadActiveLicense_usesDeviceAuthorizationWithoutTokenInUrl() {
        val requests = mutableListOf<Request>()
        val responses = ArrayDeque(listOf(
            StubResponse(200, "{\"success\":true}"),
            StubResponse(200, """{"status":"active","config":{"url":"https://tv.example:8080/base","username":"alice","password":"secret"}}"""),
        ))
        val api = client(requests, responses)

        api.register("ABC-123-XYZ", token)
        val result = api.getLicense("ABC-123-XYZ", token)

        assertThat(result).isEqualTo(
            LicenseStatus.Active(com.vopo.domain.model.RemoteProviderConfig("https://tv.example:8080/base", "alice", "secret"))
        )
        assertThat(requests[1].header("Authorization")).isEqualTo("Device $token")
        assertThat(requests[1].url.toString()).doesNotContain(token)
        assertThat(requests[1].url.queryParameter("deviceId")).isEqualTo("ABC-123-XYZ")
    }

    @Test(expected = DeviceRegistrationConflictException::class)
    fun registerConflict_isRejected() {
        val api = client(mutableListOf(), ArrayDeque(listOf(StubResponse(409, "{}"))))
        api.register("ABC-123-XYZ", token)
    }

    @Test
    fun expiredResponse_neverReturnsProviderCredentials() {
        val api = client(
            mutableListOf(),
            ArrayDeque(listOf(StubResponse(200, """{"status":"expired","config":{"url":"https://secret","username":"u","password":"p"}}""")))
        )

        assertThat(api.getLicense("ABC-123-XYZ", token)).isEqualTo(LicenseStatus.Expired)
    }

    @Test
    fun trialResponse_preservesServerDaysRemainingAndConfiguration() {
        val api = client(
            mutableListOf(),
            ArrayDeque(listOf(StubResponse(
                200,
                """{"status":"trial","daysRemaining":3,"config":{"url":"https://tv.example","username":"u","password":"p"}}"""
            )))
        )

        assertThat(api.getLicense("ABC-123-XYZ", token)).isEqualTo(
            LicenseStatus.Trial(3, com.vopo.domain.model.RemoteProviderConfig("https://tv.example", "u", "p"))
        )
    }

    private fun client(requests: MutableList<Request>, responses: ArrayDeque<StubResponse>): DeviceLicenseApiClient {
        val interceptor = Interceptor { chain ->
            requests += chain.request()
            val stub = responses.removeFirst()
            Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .code(stub.code)
                .message("stub")
                .body(stub.body.toResponseBody("application/json".toMediaType()))
                .build()
        }
        return DeviceLicenseApiClient(
            OkHttpClient.Builder().addInterceptor(interceptor).build(),
            Gson(),
            "https://www.vopoapp.com",
        )
    }

    private data class StubResponse(val code: Int, val body: String)
}
