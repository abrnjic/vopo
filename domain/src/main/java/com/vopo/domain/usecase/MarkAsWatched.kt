package com.vopo.domain.usecase

import com.vopo.domain.model.ContentType
import com.vopo.domain.model.PlaybackHistory
import com.vopo.domain.model.Result
import com.vopo.domain.repository.PlaybackHistoryRepository
import com.vopo.domain.util.isPlaybackComplete
import javax.inject.Inject

class MarkAsWatched @Inject constructor(
    private val playbackHistoryRepository: PlaybackHistoryRepository
) {
    suspend operator fun invoke(history: PlaybackHistory): Result<Unit> {
        val normalizedHistory = if (
            history.contentType != ContentType.LIVE &&
            isPlaybackComplete(history.resumePositionMs, history.totalDurationMs)
        ) {
            history.copy(
                resumePositionMs = history.totalDurationMs.coerceAtLeast(history.resumePositionMs),
                lastWatchedAt = System.currentTimeMillis()
            )
        } else {
            history
        }
        return playbackHistoryRepository.markAsWatched(normalizedHistory)
    }
}