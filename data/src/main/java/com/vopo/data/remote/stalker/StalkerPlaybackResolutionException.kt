package com.vopo.data.remote.stalker

import com.vopo.domain.model.StalkerBootstrapRecipe
import com.vopo.domain.model.StalkerCookieMode
import com.vopo.domain.model.StalkerEndpointPreference
import com.vopo.domain.model.StalkerMagPreset
import com.vopo.domain.model.StalkerPlaybackBackendHint
import com.vopo.domain.model.StalkerPortalFingerprint
import java.io.IOException

class StalkerPlaybackResolutionException(
    message: String,
    cause: Throwable? = null,
    val streamKind: StalkerStreamKind = StalkerStreamKind.LIVE,
    val portalFingerprint: StalkerPortalFingerprint? = null,
    val magPreset: StalkerMagPreset? = null,
    val bootstrapRecipe: StalkerBootstrapRecipe? = null,
    val endpointPreference: StalkerEndpointPreference = StalkerEndpointPreference.AUTO,
    val cookieMode: StalkerCookieMode = StalkerCookieMode.NONE,
    val playbackBackendHint: StalkerPlaybackBackendHint = StalkerPlaybackBackendHint.AUTO,
    val fallbackRecipeUsed: Boolean = false,
    val rediscoveryAttempted: Boolean = false
) : IOException(message, cause)
