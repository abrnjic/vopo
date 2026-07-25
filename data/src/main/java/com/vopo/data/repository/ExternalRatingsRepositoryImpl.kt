package com.vopo.data.repository

import com.vopo.domain.model.ExternalRatings
import com.vopo.domain.model.ExternalRatingsLookup
import com.vopo.domain.model.Result
import com.vopo.domain.repository.ExternalRatingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExternalRatingsRepositoryImpl @Inject constructor() : ExternalRatingsRepository {

    override suspend fun getRatings(lookup: ExternalRatingsLookup): Result<ExternalRatings> {
        return Result.success(ExternalRatings.unavailable())
    }
}