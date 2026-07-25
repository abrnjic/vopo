package com.vopo.domain.repository

import com.vopo.domain.model.ContentType
import com.vopo.domain.model.ExternalSubtitle
import java.io.File

interface ExternalSubtitleRepository {

    suspend fun search(
        contentType: ContentType,
        title: String,
        year: Int?,
        tmdbId: Long?,
        parentTmdbId: Long?,
        seasonNumber: Int?,
        episodeNumber: Int?,
        language: String
    ): Result<List<ExternalSubtitle>>

    suspend fun downloadToCache(subtitle: ExternalSubtitle): Result<File>
}
