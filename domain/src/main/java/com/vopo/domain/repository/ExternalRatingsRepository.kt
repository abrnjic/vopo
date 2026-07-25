package com.vopo.domain.repository

import com.vopo.domain.model.ExternalRatings
import com.vopo.domain.model.ExternalRatingsLookup
import com.vopo.domain.model.Result

interface ExternalRatingsRepository {
    suspend fun getRatings(lookup: ExternalRatingsLookup): Result<ExternalRatings>
}