package com.vopo.domain.repository

import com.vopo.domain.model.Category
import com.vopo.domain.model.ContentType
import com.vopo.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(providerId: Long): Flow<List<Category>>
    suspend fun setCategoryProtection(
        providerId: Long,
        categoryId: Long,
        type: ContentType,
        isProtected: Boolean
    ): Result<Unit>
}
