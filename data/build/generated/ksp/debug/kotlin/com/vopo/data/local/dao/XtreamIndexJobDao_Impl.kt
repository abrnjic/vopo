package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.XtreamIndexJobEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class XtreamIndexJobDao_Impl(
  __db: RoomDatabase,
) : XtreamIndexJobDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfXtreamIndexJobEntity: EntityInsertAdapter<XtreamIndexJobEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfXtreamIndexJobEntity = object : EntityInsertAdapter<XtreamIndexJobEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `xtream_index_jobs` (`provider_id`,`section`,`state`,`total_categories`,`completed_categories`,`next_category_index`,`failed_categories`,`indexed_rows`,`skipped_malformed_rows`,`deleted_pruned_rows`,`priority_category_id`,`priority_requested_at`,`last_error`,`last_attempt_at`,`last_success_at`,`updated_at`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: XtreamIndexJobEntity) {
        statement.bindLong(1, entity.providerId)
        statement.bindText(2, entity.section)
        statement.bindText(3, entity.state)
        statement.bindLong(4, entity.totalCategories.toLong())
        statement.bindLong(5, entity.completedCategories.toLong())
        statement.bindLong(6, entity.nextCategoryIndex.toLong())
        statement.bindLong(7, entity.failedCategories.toLong())
        statement.bindLong(8, entity.indexedRows.toLong())
        statement.bindLong(9, entity.skippedMalformedRows.toLong())
        statement.bindLong(10, entity.deletedPrunedRows.toLong())
        val _tmpPriorityCategoryId: Long? = entity.priorityCategoryId
        if (_tmpPriorityCategoryId == null) {
          statement.bindNull(11)
        } else {
          statement.bindLong(11, _tmpPriorityCategoryId)
        }
        statement.bindLong(12, entity.priorityRequestedAt)
        val _tmpLastError: String? = entity.lastError
        if (_tmpLastError == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpLastError)
        }
        statement.bindLong(14, entity.lastAttemptAt)
        statement.bindLong(15, entity.lastSuccessAt)
        statement.bindLong(16, entity.updatedAt)
      }
    }
  }

  public override suspend fun upsert(entity: XtreamIndexJobEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfXtreamIndexJobEntity.insert(_connection, entity)
  }

  public override suspend fun upsertAll(entities: List<XtreamIndexJobEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfXtreamIndexJobEntity.insert(_connection, entities)
  }

  public override fun observeAll(): Flow<List<XtreamIndexJobEntity>> {
    val _sql: String = "SELECT * FROM xtream_index_jobs ORDER BY provider_id ASC, section ASC"
    return createFlow(__db, false, arrayOf("xtream_index_jobs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfSection: Int = getColumnIndexOrThrow(_stmt, "section")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfTotalCategories: Int = getColumnIndexOrThrow(_stmt, "total_categories")
        val _columnIndexOfCompletedCategories: Int = getColumnIndexOrThrow(_stmt, "completed_categories")
        val _columnIndexOfNextCategoryIndex: Int = getColumnIndexOrThrow(_stmt, "next_category_index")
        val _columnIndexOfFailedCategories: Int = getColumnIndexOrThrow(_stmt, "failed_categories")
        val _columnIndexOfIndexedRows: Int = getColumnIndexOrThrow(_stmt, "indexed_rows")
        val _columnIndexOfSkippedMalformedRows: Int = getColumnIndexOrThrow(_stmt, "skipped_malformed_rows")
        val _columnIndexOfDeletedPrunedRows: Int = getColumnIndexOrThrow(_stmt, "deleted_pruned_rows")
        val _columnIndexOfPriorityCategoryId: Int = getColumnIndexOrThrow(_stmt, "priority_category_id")
        val _columnIndexOfPriorityRequestedAt: Int = getColumnIndexOrThrow(_stmt, "priority_requested_at")
        val _columnIndexOfLastError: Int = getColumnIndexOrThrow(_stmt, "last_error")
        val _columnIndexOfLastAttemptAt: Int = getColumnIndexOrThrow(_stmt, "last_attempt_at")
        val _columnIndexOfLastSuccessAt: Int = getColumnIndexOrThrow(_stmt, "last_success_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: MutableList<XtreamIndexJobEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: XtreamIndexJobEntity
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpSection: String
          _tmpSection = _stmt.getText(_columnIndexOfSection)
          val _tmpState: String
          _tmpState = _stmt.getText(_columnIndexOfState)
          val _tmpTotalCategories: Int
          _tmpTotalCategories = _stmt.getLong(_columnIndexOfTotalCategories).toInt()
          val _tmpCompletedCategories: Int
          _tmpCompletedCategories = _stmt.getLong(_columnIndexOfCompletedCategories).toInt()
          val _tmpNextCategoryIndex: Int
          _tmpNextCategoryIndex = _stmt.getLong(_columnIndexOfNextCategoryIndex).toInt()
          val _tmpFailedCategories: Int
          _tmpFailedCategories = _stmt.getLong(_columnIndexOfFailedCategories).toInt()
          val _tmpIndexedRows: Int
          _tmpIndexedRows = _stmt.getLong(_columnIndexOfIndexedRows).toInt()
          val _tmpSkippedMalformedRows: Int
          _tmpSkippedMalformedRows = _stmt.getLong(_columnIndexOfSkippedMalformedRows).toInt()
          val _tmpDeletedPrunedRows: Int
          _tmpDeletedPrunedRows = _stmt.getLong(_columnIndexOfDeletedPrunedRows).toInt()
          val _tmpPriorityCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfPriorityCategoryId)) {
            _tmpPriorityCategoryId = null
          } else {
            _tmpPriorityCategoryId = _stmt.getLong(_columnIndexOfPriorityCategoryId)
          }
          val _tmpPriorityRequestedAt: Long
          _tmpPriorityRequestedAt = _stmt.getLong(_columnIndexOfPriorityRequestedAt)
          val _tmpLastError: String?
          if (_stmt.isNull(_columnIndexOfLastError)) {
            _tmpLastError = null
          } else {
            _tmpLastError = _stmt.getText(_columnIndexOfLastError)
          }
          val _tmpLastAttemptAt: Long
          _tmpLastAttemptAt = _stmt.getLong(_columnIndexOfLastAttemptAt)
          val _tmpLastSuccessAt: Long
          _tmpLastSuccessAt = _stmt.getLong(_columnIndexOfLastSuccessAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item = XtreamIndexJobEntity(_tmpProviderId,_tmpSection,_tmpState,_tmpTotalCategories,_tmpCompletedCategories,_tmpNextCategoryIndex,_tmpFailedCategories,_tmpIndexedRows,_tmpSkippedMalformedRows,_tmpDeletedPrunedRows,_tmpPriorityCategoryId,_tmpPriorityRequestedAt,_tmpLastError,_tmpLastAttemptAt,_tmpLastSuccessAt,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeForProvider(providerId: Long): Flow<List<XtreamIndexJobEntity>> {
    val _sql: String = "SELECT * FROM xtream_index_jobs WHERE provider_id = ? ORDER BY section ASC"
    return createFlow(__db, false, arrayOf("xtream_index_jobs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfSection: Int = getColumnIndexOrThrow(_stmt, "section")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfTotalCategories: Int = getColumnIndexOrThrow(_stmt, "total_categories")
        val _columnIndexOfCompletedCategories: Int = getColumnIndexOrThrow(_stmt, "completed_categories")
        val _columnIndexOfNextCategoryIndex: Int = getColumnIndexOrThrow(_stmt, "next_category_index")
        val _columnIndexOfFailedCategories: Int = getColumnIndexOrThrow(_stmt, "failed_categories")
        val _columnIndexOfIndexedRows: Int = getColumnIndexOrThrow(_stmt, "indexed_rows")
        val _columnIndexOfSkippedMalformedRows: Int = getColumnIndexOrThrow(_stmt, "skipped_malformed_rows")
        val _columnIndexOfDeletedPrunedRows: Int = getColumnIndexOrThrow(_stmt, "deleted_pruned_rows")
        val _columnIndexOfPriorityCategoryId: Int = getColumnIndexOrThrow(_stmt, "priority_category_id")
        val _columnIndexOfPriorityRequestedAt: Int = getColumnIndexOrThrow(_stmt, "priority_requested_at")
        val _columnIndexOfLastError: Int = getColumnIndexOrThrow(_stmt, "last_error")
        val _columnIndexOfLastAttemptAt: Int = getColumnIndexOrThrow(_stmt, "last_attempt_at")
        val _columnIndexOfLastSuccessAt: Int = getColumnIndexOrThrow(_stmt, "last_success_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: MutableList<XtreamIndexJobEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: XtreamIndexJobEntity
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpSection: String
          _tmpSection = _stmt.getText(_columnIndexOfSection)
          val _tmpState: String
          _tmpState = _stmt.getText(_columnIndexOfState)
          val _tmpTotalCategories: Int
          _tmpTotalCategories = _stmt.getLong(_columnIndexOfTotalCategories).toInt()
          val _tmpCompletedCategories: Int
          _tmpCompletedCategories = _stmt.getLong(_columnIndexOfCompletedCategories).toInt()
          val _tmpNextCategoryIndex: Int
          _tmpNextCategoryIndex = _stmt.getLong(_columnIndexOfNextCategoryIndex).toInt()
          val _tmpFailedCategories: Int
          _tmpFailedCategories = _stmt.getLong(_columnIndexOfFailedCategories).toInt()
          val _tmpIndexedRows: Int
          _tmpIndexedRows = _stmt.getLong(_columnIndexOfIndexedRows).toInt()
          val _tmpSkippedMalformedRows: Int
          _tmpSkippedMalformedRows = _stmt.getLong(_columnIndexOfSkippedMalformedRows).toInt()
          val _tmpDeletedPrunedRows: Int
          _tmpDeletedPrunedRows = _stmt.getLong(_columnIndexOfDeletedPrunedRows).toInt()
          val _tmpPriorityCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfPriorityCategoryId)) {
            _tmpPriorityCategoryId = null
          } else {
            _tmpPriorityCategoryId = _stmt.getLong(_columnIndexOfPriorityCategoryId)
          }
          val _tmpPriorityRequestedAt: Long
          _tmpPriorityRequestedAt = _stmt.getLong(_columnIndexOfPriorityRequestedAt)
          val _tmpLastError: String?
          if (_stmt.isNull(_columnIndexOfLastError)) {
            _tmpLastError = null
          } else {
            _tmpLastError = _stmt.getText(_columnIndexOfLastError)
          }
          val _tmpLastAttemptAt: Long
          _tmpLastAttemptAt = _stmt.getLong(_columnIndexOfLastAttemptAt)
          val _tmpLastSuccessAt: Long
          _tmpLastSuccessAt = _stmt.getLong(_columnIndexOfLastSuccessAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item = XtreamIndexJobEntity(_tmpProviderId,_tmpSection,_tmpState,_tmpTotalCategories,_tmpCompletedCategories,_tmpNextCategoryIndex,_tmpFailedCategories,_tmpIndexedRows,_tmpSkippedMalformedRows,_tmpDeletedPrunedRows,_tmpPriorityCategoryId,_tmpPriorityRequestedAt,_tmpLastError,_tmpLastAttemptAt,_tmpLastSuccessAt,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun `get`(providerId: Long, section: String): XtreamIndexJobEntity? {
    val _sql: String = "SELECT * FROM xtream_index_jobs WHERE provider_id = ? AND section = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, section)
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfSection: Int = getColumnIndexOrThrow(_stmt, "section")
        val _columnIndexOfState: Int = getColumnIndexOrThrow(_stmt, "state")
        val _columnIndexOfTotalCategories: Int = getColumnIndexOrThrow(_stmt, "total_categories")
        val _columnIndexOfCompletedCategories: Int = getColumnIndexOrThrow(_stmt, "completed_categories")
        val _columnIndexOfNextCategoryIndex: Int = getColumnIndexOrThrow(_stmt, "next_category_index")
        val _columnIndexOfFailedCategories: Int = getColumnIndexOrThrow(_stmt, "failed_categories")
        val _columnIndexOfIndexedRows: Int = getColumnIndexOrThrow(_stmt, "indexed_rows")
        val _columnIndexOfSkippedMalformedRows: Int = getColumnIndexOrThrow(_stmt, "skipped_malformed_rows")
        val _columnIndexOfDeletedPrunedRows: Int = getColumnIndexOrThrow(_stmt, "deleted_pruned_rows")
        val _columnIndexOfPriorityCategoryId: Int = getColumnIndexOrThrow(_stmt, "priority_category_id")
        val _columnIndexOfPriorityRequestedAt: Int = getColumnIndexOrThrow(_stmt, "priority_requested_at")
        val _columnIndexOfLastError: Int = getColumnIndexOrThrow(_stmt, "last_error")
        val _columnIndexOfLastAttemptAt: Int = getColumnIndexOrThrow(_stmt, "last_attempt_at")
        val _columnIndexOfLastSuccessAt: Int = getColumnIndexOrThrow(_stmt, "last_success_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: XtreamIndexJobEntity?
        if (_stmt.step()) {
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpSection: String
          _tmpSection = _stmt.getText(_columnIndexOfSection)
          val _tmpState: String
          _tmpState = _stmt.getText(_columnIndexOfState)
          val _tmpTotalCategories: Int
          _tmpTotalCategories = _stmt.getLong(_columnIndexOfTotalCategories).toInt()
          val _tmpCompletedCategories: Int
          _tmpCompletedCategories = _stmt.getLong(_columnIndexOfCompletedCategories).toInt()
          val _tmpNextCategoryIndex: Int
          _tmpNextCategoryIndex = _stmt.getLong(_columnIndexOfNextCategoryIndex).toInt()
          val _tmpFailedCategories: Int
          _tmpFailedCategories = _stmt.getLong(_columnIndexOfFailedCategories).toInt()
          val _tmpIndexedRows: Int
          _tmpIndexedRows = _stmt.getLong(_columnIndexOfIndexedRows).toInt()
          val _tmpSkippedMalformedRows: Int
          _tmpSkippedMalformedRows = _stmt.getLong(_columnIndexOfSkippedMalformedRows).toInt()
          val _tmpDeletedPrunedRows: Int
          _tmpDeletedPrunedRows = _stmt.getLong(_columnIndexOfDeletedPrunedRows).toInt()
          val _tmpPriorityCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfPriorityCategoryId)) {
            _tmpPriorityCategoryId = null
          } else {
            _tmpPriorityCategoryId = _stmt.getLong(_columnIndexOfPriorityCategoryId)
          }
          val _tmpPriorityRequestedAt: Long
          _tmpPriorityRequestedAt = _stmt.getLong(_columnIndexOfPriorityRequestedAt)
          val _tmpLastError: String?
          if (_stmt.isNull(_columnIndexOfLastError)) {
            _tmpLastError = null
          } else {
            _tmpLastError = _stmt.getText(_columnIndexOfLastError)
          }
          val _tmpLastAttemptAt: Long
          _tmpLastAttemptAt = _stmt.getLong(_columnIndexOfLastAttemptAt)
          val _tmpLastSuccessAt: Long
          _tmpLastSuccessAt = _stmt.getLong(_columnIndexOfLastSuccessAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _result = XtreamIndexJobEntity(_tmpProviderId,_tmpSection,_tmpState,_tmpTotalCategories,_tmpCompletedCategories,_tmpNextCategoryIndex,_tmpFailedCategories,_tmpIndexedRows,_tmpSkippedMalformedRows,_tmpDeletedPrunedRows,_tmpPriorityCategoryId,_tmpPriorityRequestedAt,_tmpLastError,_tmpLastAttemptAt,_tmpLastSuccessAt,_tmpUpdatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun requestCategoryPriority(
    providerId: Long,
    section: String,
    categoryId: Long,
    requestedAt: Long,
  ): Int {
    val _sql: String = """
        |
        |        UPDATE xtream_index_jobs
        |        SET priority_category_id = ?,
        |            priority_requested_at = ?,
        |            state = CASE
        |                WHEN state IN ('IDLE', 'SUCCESS', 'STALE', 'FAILED_RETRYABLE', 'PARTIAL') THEN 'QUEUED'
        |                ELSE state
        |            END,
        |            updated_at = ?
        |        WHERE provider_id = ? AND section = ?
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, requestedAt)
        _argIndex = 3
        _stmt.bindLong(_argIndex, requestedAt)
        _argIndex = 4
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 5
        _stmt.bindText(_argIndex, section)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByProvider(providerId: Long): Int {
    val _sql: String = "DELETE FROM xtream_index_jobs WHERE provider_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
