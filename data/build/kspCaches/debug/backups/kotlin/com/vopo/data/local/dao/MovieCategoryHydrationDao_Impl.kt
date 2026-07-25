package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.MovieCategoryHydrationEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class MovieCategoryHydrationDao_Impl(
  __db: RoomDatabase,
) : MovieCategoryHydrationDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfMovieCategoryHydrationEntity:
      EntityInsertAdapter<MovieCategoryHydrationEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfMovieCategoryHydrationEntity = object : EntityInsertAdapter<MovieCategoryHydrationEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `movie_category_hydration` (`provider_id`,`category_id`,`last_hydrated_at`,`item_count`,`last_status`,`last_error`,`last_loaded_page`,`last_attempted_page`,`last_successful_page`,`total_pages`,`is_complete`,`page_size`,`retry_after_ms`,`failure_count`,`retry_budget_remaining`,`last_page_fingerprint`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: MovieCategoryHydrationEntity) {
        statement.bindLong(1, entity.providerId)
        statement.bindLong(2, entity.categoryId)
        statement.bindLong(3, entity.lastHydratedAt)
        statement.bindLong(4, entity.itemCount.toLong())
        statement.bindText(5, entity.lastStatus)
        val _tmpLastError: String? = entity.lastError
        if (_tmpLastError == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpLastError)
        }
        statement.bindLong(7, entity.lastLoadedPage.toLong())
        statement.bindLong(8, entity.lastAttemptedPage.toLong())
        statement.bindLong(9, entity.lastSuccessfulPage.toLong())
        statement.bindLong(10, entity.totalPages.toLong())
        val _tmp: Int = if (entity.isComplete) 1 else 0
        statement.bindLong(11, _tmp.toLong())
        statement.bindLong(12, entity.pageSize.toLong())
        statement.bindLong(13, entity.retryAfterMs)
        statement.bindLong(14, entity.failureCount.toLong())
        statement.bindLong(15, entity.retryBudgetRemaining.toLong())
        val _tmpLastPageFingerprint: String? = entity.lastPageFingerprint
        if (_tmpLastPageFingerprint == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpLastPageFingerprint)
        }
      }
    }
  }

  public override suspend fun upsert(metadata: MovieCategoryHydrationEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfMovieCategoryHydrationEntity.insert(_connection, metadata)
  }

  public override suspend fun `get`(providerId: Long, categoryId: Long): MovieCategoryHydrationEntity? {
    val _sql: String = "SELECT * FROM movie_category_hydration WHERE provider_id = ? AND category_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfLastHydratedAt: Int = getColumnIndexOrThrow(_stmt, "last_hydrated_at")
        val _columnIndexOfItemCount: Int = getColumnIndexOrThrow(_stmt, "item_count")
        val _columnIndexOfLastStatus: Int = getColumnIndexOrThrow(_stmt, "last_status")
        val _columnIndexOfLastError: Int = getColumnIndexOrThrow(_stmt, "last_error")
        val _columnIndexOfLastLoadedPage: Int = getColumnIndexOrThrow(_stmt, "last_loaded_page")
        val _columnIndexOfLastAttemptedPage: Int = getColumnIndexOrThrow(_stmt, "last_attempted_page")
        val _columnIndexOfLastSuccessfulPage: Int = getColumnIndexOrThrow(_stmt, "last_successful_page")
        val _columnIndexOfTotalPages: Int = getColumnIndexOrThrow(_stmt, "total_pages")
        val _columnIndexOfIsComplete: Int = getColumnIndexOrThrow(_stmt, "is_complete")
        val _columnIndexOfPageSize: Int = getColumnIndexOrThrow(_stmt, "page_size")
        val _columnIndexOfRetryAfterMs: Int = getColumnIndexOrThrow(_stmt, "retry_after_ms")
        val _columnIndexOfFailureCount: Int = getColumnIndexOrThrow(_stmt, "failure_count")
        val _columnIndexOfRetryBudgetRemaining: Int = getColumnIndexOrThrow(_stmt, "retry_budget_remaining")
        val _columnIndexOfLastPageFingerprint: Int = getColumnIndexOrThrow(_stmt, "last_page_fingerprint")
        val _result: MovieCategoryHydrationEntity?
        if (_stmt.step()) {
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpCategoryId: Long
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          val _tmpLastHydratedAt: Long
          _tmpLastHydratedAt = _stmt.getLong(_columnIndexOfLastHydratedAt)
          val _tmpItemCount: Int
          _tmpItemCount = _stmt.getLong(_columnIndexOfItemCount).toInt()
          val _tmpLastStatus: String
          _tmpLastStatus = _stmt.getText(_columnIndexOfLastStatus)
          val _tmpLastError: String?
          if (_stmt.isNull(_columnIndexOfLastError)) {
            _tmpLastError = null
          } else {
            _tmpLastError = _stmt.getText(_columnIndexOfLastError)
          }
          val _tmpLastLoadedPage: Int
          _tmpLastLoadedPage = _stmt.getLong(_columnIndexOfLastLoadedPage).toInt()
          val _tmpLastAttemptedPage: Int
          _tmpLastAttemptedPage = _stmt.getLong(_columnIndexOfLastAttemptedPage).toInt()
          val _tmpLastSuccessfulPage: Int
          _tmpLastSuccessfulPage = _stmt.getLong(_columnIndexOfLastSuccessfulPage).toInt()
          val _tmpTotalPages: Int
          _tmpTotalPages = _stmt.getLong(_columnIndexOfTotalPages).toInt()
          val _tmpIsComplete: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsComplete).toInt()
          _tmpIsComplete = _tmp != 0
          val _tmpPageSize: Int
          _tmpPageSize = _stmt.getLong(_columnIndexOfPageSize).toInt()
          val _tmpRetryAfterMs: Long
          _tmpRetryAfterMs = _stmt.getLong(_columnIndexOfRetryAfterMs)
          val _tmpFailureCount: Int
          _tmpFailureCount = _stmt.getLong(_columnIndexOfFailureCount).toInt()
          val _tmpRetryBudgetRemaining: Int
          _tmpRetryBudgetRemaining = _stmt.getLong(_columnIndexOfRetryBudgetRemaining).toInt()
          val _tmpLastPageFingerprint: String?
          if (_stmt.isNull(_columnIndexOfLastPageFingerprint)) {
            _tmpLastPageFingerprint = null
          } else {
            _tmpLastPageFingerprint = _stmt.getText(_columnIndexOfLastPageFingerprint)
          }
          _result = MovieCategoryHydrationEntity(_tmpProviderId,_tmpCategoryId,_tmpLastHydratedAt,_tmpItemCount,_tmpLastStatus,_tmpLastError,_tmpLastLoadedPage,_tmpLastAttemptedPage,_tmpLastSuccessfulPage,_tmpTotalPages,_tmpIsComplete,_tmpPageSize,_tmpRetryAfterMs,_tmpFailureCount,_tmpRetryBudgetRemaining,_tmpLastPageFingerprint)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(providerId: Long, categoryId: Long) {
    val _sql: String = "DELETE FROM movie_category_hydration WHERE provider_id = ? AND category_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByProvider(providerId: Long) {
    val _sql: String = "DELETE FROM movie_category_hydration WHERE provider_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
