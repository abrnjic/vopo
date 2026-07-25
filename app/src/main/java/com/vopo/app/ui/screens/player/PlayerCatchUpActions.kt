package com.vopo.app.ui.screens.player

import androidx.lifecycle.viewModelScope
import com.vopo.app.ui.model.isArchivePlayable
import com.vopo.data.security.CredentialDecryptionException
import com.vopo.domain.model.ContentType
import com.vopo.domain.model.Program
import com.vopo.domain.model.StreamInfo
import kotlinx.coroutines.launch
import android.util.Log

internal suspend fun resolveCatchUpStreamInfo(
    candidateUrl: String,
    title: String,
    currentContentId: Long,
    currentProviderId: Long,
    resolveStreamInfo: suspend (String, Long, Long, ContentType) -> StreamInfo?
): StreamInfo? = resolveStreamInfo(candidateUrl, currentContentId, currentProviderId, ContentType.LIVE)
    ?.copy(title = title)

internal suspend fun PlayerViewModel.startCatchUpPlayback(
    urls: List<String>,
    title: String,
    recoveryAction: String,
    requestVersionOverride: Long? = null
) {
    val requestVersion = requestVersionOverride ?: beginPlaybackSession()
    val candidates = urls
        .asSequence()
        .map { it.trim() }
        .filter { it.isNotBlank() }
        .distinct()
        .toList()
    val primaryUrl = candidates.firstOrNull() ?: return

    currentTitle = title
    pendingCatchUpUrls = candidates
    triedAlternativeStreams.clear()
    triedAlternativeStreams.add(primaryUrl)
    currentStreamUrl = primaryUrl
    updateStreamClass("Catch-up")
    appendRecoveryAction(recoveryAction)

    val catchupStream = resolveCatchUpStreamInfo(
        candidateUrl = primaryUrl,
        title = currentTitle,
        currentContentId = currentContentId,
        currentProviderId = currentProviderId,
        resolveStreamInfo = ::resolvePlaybackStreamInfo
    ) ?: return
    
    val redactedUrl = catchupStream.url
        .replace(Regex("password=[^&]*", RegexOption.IGNORE_CASE), "password=***")
        .replace(Regex("username=[^&]*", RegexOption.IGNORE_CASE), "username=***")
        .replace(Regex("/([^/]+)/([^/]+)/(\\d+)\\.ts"), "/username/***/$3.ts")

    Log.i("CatchUpDebug", "Generated CatchUp URL (redacted): $redactedUrl")
    
    if (!preparePlayer(catchupStream, requestVersion)) return
    playerEngine.play()
}

fun PlayerViewModel.playCatchUp(program: Program) {
    viewModelScope.launch {
        val requestVersion = prepareRequestVersion
        val channel = currentChannelFlow.value
        if (channel == null || !channel.isArchivePlayable(program)) {
            return@launch
        }
        val start = program.startTime / 1000L
        val end = program.endTime / 1000L
        val streamId = channel.id
        val providerId = currentProviderId

        if (providerId == -1L || streamId == 0L) {
            setLastFailureReason("Catch-up playback needs a valid live channel context.")
            showPlayerNotice(
                message = "Catch-up playback needs a valid live channel context.",
                recoveryType = PlayerRecoveryType.CATCH_UP,
                actions = buildRecoveryActions(PlayerRecoveryType.CATCH_UP)
            )
            return@launch
        }

        if (com.vopo.app.BuildConfig.DEBUG) {
            try {
                val durationSeconds = end - start
                val durationMinutes = durationSeconds / 60
                val utcStart = java.time.Instant.ofEpochSecond(start).atZone(java.time.ZoneId.of("UTC"))
                val localStart = java.time.Instant.ofEpochSecond(start).atZone(java.time.ZoneId.systemDefault())
                Log.i("CatchUpDebug", "--- CATCH-UP DIAGNOSTICS START ---")
                Log.i("CatchUpDebug", "Stream ID: $streamId")
                Log.i("CatchUpDebug", "Channel Name: ${channel.name}")
                Log.i("CatchUpDebug", "CatchUpSupported (channel): ${channel.catchUpSupported}")
                Log.i("CatchUpDebug", "CatchUpDays: ${channel.catchUpDays}")
                Log.i("CatchUpDebug", "hasArchive (program): ${program.hasArchive}")
                Log.i("CatchUpDebug", "Program Start (ms): ${program.startTime}")
                Log.i("CatchUpDebug", "Program End (ms): ${program.endTime}")
                Log.i("CatchUpDebug", "Duration (sec): $durationSeconds")
                Log.i("CatchUpDebug", "Duration (min): $durationMinutes")
                Log.i("CatchUpDebug", "Start UTC: ${utcStart.format(java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME)}")
                Log.i("CatchUpDebug", "Start Local: ${localStart.format(java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME)}")
                Log.i("CatchUpDebug", "M3U Source Template (if any): ${channel.catchUpSource}")
            } catch (e: Exception) {
                Log.e("CatchUpDebug", "Failed to print diagnostics", e)
            }
        }

        val catchUpUrls = try {
            providerRepository.buildCatchUpUrls(providerId, streamId, start, end)
        } catch (e: CredentialDecryptionException) {
            if (!isActivePlaybackSession(requestVersion)) return@launch
            setLastFailureReason(e.message ?: CredentialDecryptionException.MESSAGE)
            showPlayerNotice(
                message = e.message ?: CredentialDecryptionException.MESSAGE,
                recoveryType = PlayerRecoveryType.SOURCE,
                actions = buildRecoveryActions(PlayerRecoveryType.SOURCE)
            )
            return@launch
        }
        if (!isActivePlaybackSession(requestVersion)) return@launch
        if (catchUpUrls.isNotEmpty()) {
            startCatchUpPlayback(
                urls = catchUpUrls,
                title = "${channel.name}: ${program.title}",
                recoveryAction = "Started program replay"
            )
        } else {
            val reason = resolveCatchUpFailureMessage(
                channel,
                archiveRequested = true,
                programHasArchive = program.hasArchive
            )
            setLastFailureReason(reason)
            showPlayerNotice(
                message = reason,
                recoveryType = PlayerRecoveryType.CATCH_UP,
                actions = buildRecoveryActions(PlayerRecoveryType.CATCH_UP)
            )
        }
    }
}