package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.XtreamLiveOnboardingStateEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
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
public class XtreamLiveOnboardingDao_Impl(
  __db: RoomDatabase,
) : XtreamLiveOnboardingDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfXtreamLiveOnboardingStateEntity:
      EntityInsertAdapter<XtreamLiveOnboardingStateEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfXtreamLiveOnboardingStateEntity = object : EntityInsertAdapter<XtreamLiveOnboardingStateEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `xtream_live_onboarding_state` (`provider_id`,`provider_type`,`content_type`,`phase`,`staged_session_id`,`import_strategy`,`next_category_index`,`accepted_row_count`,`staged_flush_count`,`sync_profile_tier`,`sync_profile_batch_size`,`sync_profile_strategy`,`sync_profile_low_memory`,`sync_profile_memory_class_mb`,`sync_profile_available_mem_mb`,`last_error`,`created_at`,`updated_at`,`completed_at`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: XtreamLiveOnboardingStateEntity) {
        statement.bindLong(1, entity.providerId)
        statement.bindText(2, entity.providerType)
        statement.bindText(3, entity.contentType)
        statement.bindText(4, entity.phase)
        val _tmpStagedSessionId: Long? = entity.stagedSessionId
        if (_tmpStagedSessionId == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpStagedSessionId)
        }
        val _tmpImportStrategy: String? = entity.importStrategy
        if (_tmpImportStrategy == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpImportStrategy)
        }
        statement.bindLong(7, entity.nextCategoryIndex.toLong())
        statement.bindLong(8, entity.acceptedRowCount.toLong())
        statement.bindLong(9, entity.stagedFlushCount.toLong())
        val _tmpSyncProfileTier: String? = entity.syncProfileTier
        if (_tmpSyncProfileTier == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpSyncProfileTier)
        }
        statement.bindLong(11, entity.syncProfileBatchSize.toLong())
        val _tmpSyncProfileStrategy: String? = entity.syncProfileStrategy
        if (_tmpSyncProfileStrategy == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpSyncProfileStrategy)
        }
        val _tmp: Int = if (entity.syncProfileLowMemory) 1 else 0
        statement.bindLong(13, _tmp.toLong())
        statement.bindLong(14, entity.syncProfileMemoryClassMb.toLong())
        statement.bindLong(15, entity.syncProfileAvailableMemMb)
        val _tmpLastError: String? = entity.lastError
        if (_tmpLastError == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpLastError)
        }
        statement.bindLong(17, entity.createdAt)
        statement.bindLong(18, entity.updatedAt)
        val _tmpCompletedAt: Long? = entity.completedAt
        if (_tmpCompletedAt == null) {
          statement.bindNull(19)
        } else {
          statement.bindLong(19, _tmpCompletedAt)
        }
      }
    }
  }

  public override suspend fun upsert(state: XtreamLiveOnboardingStateEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfXtreamLiveOnboardingStateEntity.insert(_connection, state)
  }

  public override suspend fun getByProvider(providerId: Long): XtreamLiveOnboardingStateEntity? {
    val _sql: String = "SELECT * FROM xtream_live_onboarding_state WHERE provider_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfProviderType: Int = getColumnIndexOrThrow(_stmt, "provider_type")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfPhase: Int = getColumnIndexOrThrow(_stmt, "phase")
        val _columnIndexOfStagedSessionId: Int = getColumnIndexOrThrow(_stmt, "staged_session_id")
        val _columnIndexOfImportStrategy: Int = getColumnIndexOrThrow(_stmt, "import_strategy")
        val _columnIndexOfNextCategoryIndex: Int = getColumnIndexOrThrow(_stmt, "next_category_index")
        val _columnIndexOfAcceptedRowCount: Int = getColumnIndexOrThrow(_stmt, "accepted_row_count")
        val _columnIndexOfStagedFlushCount: Int = getColumnIndexOrThrow(_stmt, "staged_flush_count")
        val _columnIndexOfSyncProfileTier: Int = getColumnIndexOrThrow(_stmt, "sync_profile_tier")
        val _columnIndexOfSyncProfileBatchSize: Int = getColumnIndexOrThrow(_stmt, "sync_profile_batch_size")
        val _columnIndexOfSyncProfileStrategy: Int = getColumnIndexOrThrow(_stmt, "sync_profile_strategy")
        val _columnIndexOfSyncProfileLowMemory: Int = getColumnIndexOrThrow(_stmt, "sync_profile_low_memory")
        val _columnIndexOfSyncProfileMemoryClassMb: Int = getColumnIndexOrThrow(_stmt, "sync_profile_memory_class_mb")
        val _columnIndexOfSyncProfileAvailableMemMb: Int = getColumnIndexOrThrow(_stmt, "sync_profile_available_mem_mb")
        val _columnIndexOfLastError: Int = getColumnIndexOrThrow(_stmt, "last_error")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _columnIndexOfCompletedAt: Int = getColumnIndexOrThrow(_stmt, "completed_at")
        val _result: XtreamLiveOnboardingStateEntity?
        if (_stmt.step()) {
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpProviderType: String
          _tmpProviderType = _stmt.getText(_columnIndexOfProviderType)
          val _tmpContentType: String
          _tmpContentType = _stmt.getText(_columnIndexOfContentType)
          val _tmpPhase: String
          _tmpPhase = _stmt.getText(_columnIndexOfPhase)
          val _tmpStagedSessionId: Long?
          if (_stmt.isNull(_columnIndexOfStagedSessionId)) {
            _tmpStagedSessionId = null
          } else {
            _tmpStagedSessionId = _stmt.getLong(_columnIndexOfStagedSessionId)
          }
          val _tmpImportStrategy: String?
          if (_stmt.isNull(_columnIndexOfImportStrategy)) {
            _tmpImportStrategy = null
          } else {
            _tmpImportStrategy = _stmt.getText(_columnIndexOfImportStrategy)
          }
          val _tmpNextCategoryIndex: Int
          _tmpNextCategoryIndex = _stmt.getLong(_columnIndexOfNextCategoryIndex).toInt()
          val _tmpAcceptedRowCount: Int
          _tmpAcceptedRowCount = _stmt.getLong(_columnIndexOfAcceptedRowCount).toInt()
          val _tmpStagedFlushCount: Int
          _tmpStagedFlushCount = _stmt.getLong(_columnIndexOfStagedFlushCount).toInt()
          val _tmpSyncProfileTier: String?
          if (_stmt.isNull(_columnIndexOfSyncProfileTier)) {
            _tmpSyncProfileTier = null
          } else {
            _tmpSyncProfileTier = _stmt.getText(_columnIndexOfSyncProfileTier)
          }
          val _tmpSyncProfileBatchSize: Int
          _tmpSyncProfileBatchSize = _stmt.getLong(_columnIndexOfSyncProfileBatchSize).toInt()
          val _tmpSyncProfileStrategy: String?
          if (_stmt.isNull(_columnIndexOfSyncProfileStrategy)) {
            _tmpSyncProfileStrategy = null
          } else {
            _tmpSyncProfileStrategy = _stmt.getText(_columnIndexOfSyncProfileStrategy)
          }
          val _tmpSyncProfileLowMemory: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSyncProfileLowMemory).toInt()
          _tmpSyncProfileLowMemory = _tmp != 0
          val _tmpSyncProfileMemoryClassMb: Int
          _tmpSyncProfileMemoryClassMb = _stmt.getLong(_columnIndexOfSyncProfileMemoryClassMb).toInt()
          val _tmpSyncProfileAvailableMemMb: Long
          _tmpSyncProfileAvailableMemMb = _stmt.getLong(_columnIndexOfSyncProfileAvailableMemMb)
          val _tmpLastError: String?
          if (_stmt.isNull(_columnIndexOfLastError)) {
            _tmpLastError = null
          } else {
            _tmpLastError = _stmt.getText(_columnIndexOfLastError)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          val _tmpCompletedAt: Long?
          if (_stmt.isNull(_columnIndexOfCompletedAt)) {
            _tmpCompletedAt = null
          } else {
            _tmpCompletedAt = _stmt.getLong(_columnIndexOfCompletedAt)
          }
          _result = XtreamLiveOnboardingStateEntity(_tmpProviderId,_tmpProviderType,_tmpContentType,_tmpPhase,_tmpStagedSessionId,_tmpImportStrategy,_tmpNextCategoryIndex,_tmpAcceptedRowCount,_tmpStagedFlushCount,_tmpSyncProfileTier,_tmpSyncProfileBatchSize,_tmpSyncProfileStrategy,_tmpSyncProfileLowMemory,_tmpSyncProfileMemoryClassMb,_tmpSyncProfileAvailableMemMb,_tmpLastError,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getIncompleteByProvider(providerId: Long): XtreamLiveOnboardingStateEntity? {
    val _sql: String = "SELECT * FROM xtream_live_onboarding_state WHERE provider_id = ? AND completed_at IS NULL"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfProviderType: Int = getColumnIndexOrThrow(_stmt, "provider_type")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfPhase: Int = getColumnIndexOrThrow(_stmt, "phase")
        val _columnIndexOfStagedSessionId: Int = getColumnIndexOrThrow(_stmt, "staged_session_id")
        val _columnIndexOfImportStrategy: Int = getColumnIndexOrThrow(_stmt, "import_strategy")
        val _columnIndexOfNextCategoryIndex: Int = getColumnIndexOrThrow(_stmt, "next_category_index")
        val _columnIndexOfAcceptedRowCount: Int = getColumnIndexOrThrow(_stmt, "accepted_row_count")
        val _columnIndexOfStagedFlushCount: Int = getColumnIndexOrThrow(_stmt, "staged_flush_count")
        val _columnIndexOfSyncProfileTier: Int = getColumnIndexOrThrow(_stmt, "sync_profile_tier")
        val _columnIndexOfSyncProfileBatchSize: Int = getColumnIndexOrThrow(_stmt, "sync_profile_batch_size")
        val _columnIndexOfSyncProfileStrategy: Int = getColumnIndexOrThrow(_stmt, "sync_profile_strategy")
        val _columnIndexOfSyncProfileLowMemory: Int = getColumnIndexOrThrow(_stmt, "sync_profile_low_memory")
        val _columnIndexOfSyncProfileMemoryClassMb: Int = getColumnIndexOrThrow(_stmt, "sync_profile_memory_class_mb")
        val _columnIndexOfSyncProfileAvailableMemMb: Int = getColumnIndexOrThrow(_stmt, "sync_profile_available_mem_mb")
        val _columnIndexOfLastError: Int = getColumnIndexOrThrow(_stmt, "last_error")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _columnIndexOfCompletedAt: Int = getColumnIndexOrThrow(_stmt, "completed_at")
        val _result: XtreamLiveOnboardingStateEntity?
        if (_stmt.step()) {
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpProviderType: String
          _tmpProviderType = _stmt.getText(_columnIndexOfProviderType)
          val _tmpContentType: String
          _tmpContentType = _stmt.getText(_columnIndexOfContentType)
          val _tmpPhase: String
          _tmpPhase = _stmt.getText(_columnIndexOfPhase)
          val _tmpStagedSessionId: Long?
          if (_stmt.isNull(_columnIndexOfStagedSessionId)) {
            _tmpStagedSessionId = null
          } else {
            _tmpStagedSessionId = _stmt.getLong(_columnIndexOfStagedSessionId)
          }
          val _tmpImportStrategy: String?
          if (_stmt.isNull(_columnIndexOfImportStrategy)) {
            _tmpImportStrategy = null
          } else {
            _tmpImportStrategy = _stmt.getText(_columnIndexOfImportStrategy)
          }
          val _tmpNextCategoryIndex: Int
          _tmpNextCategoryIndex = _stmt.getLong(_columnIndexOfNextCategoryIndex).toInt()
          val _tmpAcceptedRowCount: Int
          _tmpAcceptedRowCount = _stmt.getLong(_columnIndexOfAcceptedRowCount).toInt()
          val _tmpStagedFlushCount: Int
          _tmpStagedFlushCount = _stmt.getLong(_columnIndexOfStagedFlushCount).toInt()
          val _tmpSyncProfileTier: String?
          if (_stmt.isNull(_columnIndexOfSyncProfileTier)) {
            _tmpSyncProfileTier = null
          } else {
            _tmpSyncProfileTier = _stmt.getText(_columnIndexOfSyncProfileTier)
          }
          val _tmpSyncProfileBatchSize: Int
          _tmpSyncProfileBatchSize = _stmt.getLong(_columnIndexOfSyncProfileBatchSize).toInt()
          val _tmpSyncProfileStrategy: String?
          if (_stmt.isNull(_columnIndexOfSyncProfileStrategy)) {
            _tmpSyncProfileStrategy = null
          } else {
            _tmpSyncProfileStrategy = _stmt.getText(_columnIndexOfSyncProfileStrategy)
          }
          val _tmpSyncProfileLowMemory: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSyncProfileLowMemory).toInt()
          _tmpSyncProfileLowMemory = _tmp != 0
          val _tmpSyncProfileMemoryClassMb: Int
          _tmpSyncProfileMemoryClassMb = _stmt.getLong(_columnIndexOfSyncProfileMemoryClassMb).toInt()
          val _tmpSyncProfileAvailableMemMb: Long
          _tmpSyncProfileAvailableMemMb = _stmt.getLong(_columnIndexOfSyncProfileAvailableMemMb)
          val _tmpLastError: String?
          if (_stmt.isNull(_columnIndexOfLastError)) {
            _tmpLastError = null
          } else {
            _tmpLastError = _stmt.getText(_columnIndexOfLastError)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          val _tmpCompletedAt: Long?
          if (_stmt.isNull(_columnIndexOfCompletedAt)) {
            _tmpCompletedAt = null
          } else {
            _tmpCompletedAt = _stmt.getLong(_columnIndexOfCompletedAt)
          }
          _result = XtreamLiveOnboardingStateEntity(_tmpProviderId,_tmpProviderType,_tmpContentType,_tmpPhase,_tmpStagedSessionId,_tmpImportStrategy,_tmpNextCategoryIndex,_tmpAcceptedRowCount,_tmpStagedFlushCount,_tmpSyncProfileTier,_tmpSyncProfileBatchSize,_tmpSyncProfileStrategy,_tmpSyncProfileLowMemory,_tmpSyncProfileMemoryClassMb,_tmpSyncProfileAvailableMemMb,_tmpLastError,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getIncomplete(): List<XtreamLiveOnboardingStateEntity> {
    val _sql: String = "SELECT * FROM xtream_live_onboarding_state WHERE completed_at IS NULL ORDER BY updated_at ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfProviderType: Int = getColumnIndexOrThrow(_stmt, "provider_type")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfPhase: Int = getColumnIndexOrThrow(_stmt, "phase")
        val _columnIndexOfStagedSessionId: Int = getColumnIndexOrThrow(_stmt, "staged_session_id")
        val _columnIndexOfImportStrategy: Int = getColumnIndexOrThrow(_stmt, "import_strategy")
        val _columnIndexOfNextCategoryIndex: Int = getColumnIndexOrThrow(_stmt, "next_category_index")
        val _columnIndexOfAcceptedRowCount: Int = getColumnIndexOrThrow(_stmt, "accepted_row_count")
        val _columnIndexOfStagedFlushCount: Int = getColumnIndexOrThrow(_stmt, "staged_flush_count")
        val _columnIndexOfSyncProfileTier: Int = getColumnIndexOrThrow(_stmt, "sync_profile_tier")
        val _columnIndexOfSyncProfileBatchSize: Int = getColumnIndexOrThrow(_stmt, "sync_profile_batch_size")
        val _columnIndexOfSyncProfileStrategy: Int = getColumnIndexOrThrow(_stmt, "sync_profile_strategy")
        val _columnIndexOfSyncProfileLowMemory: Int = getColumnIndexOrThrow(_stmt, "sync_profile_low_memory")
        val _columnIndexOfSyncProfileMemoryClassMb: Int = getColumnIndexOrThrow(_stmt, "sync_profile_memory_class_mb")
        val _columnIndexOfSyncProfileAvailableMemMb: Int = getColumnIndexOrThrow(_stmt, "sync_profile_available_mem_mb")
        val _columnIndexOfLastError: Int = getColumnIndexOrThrow(_stmt, "last_error")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _columnIndexOfCompletedAt: Int = getColumnIndexOrThrow(_stmt, "completed_at")
        val _result: MutableList<XtreamLiveOnboardingStateEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: XtreamLiveOnboardingStateEntity
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpProviderType: String
          _tmpProviderType = _stmt.getText(_columnIndexOfProviderType)
          val _tmpContentType: String
          _tmpContentType = _stmt.getText(_columnIndexOfContentType)
          val _tmpPhase: String
          _tmpPhase = _stmt.getText(_columnIndexOfPhase)
          val _tmpStagedSessionId: Long?
          if (_stmt.isNull(_columnIndexOfStagedSessionId)) {
            _tmpStagedSessionId = null
          } else {
            _tmpStagedSessionId = _stmt.getLong(_columnIndexOfStagedSessionId)
          }
          val _tmpImportStrategy: String?
          if (_stmt.isNull(_columnIndexOfImportStrategy)) {
            _tmpImportStrategy = null
          } else {
            _tmpImportStrategy = _stmt.getText(_columnIndexOfImportStrategy)
          }
          val _tmpNextCategoryIndex: Int
          _tmpNextCategoryIndex = _stmt.getLong(_columnIndexOfNextCategoryIndex).toInt()
          val _tmpAcceptedRowCount: Int
          _tmpAcceptedRowCount = _stmt.getLong(_columnIndexOfAcceptedRowCount).toInt()
          val _tmpStagedFlushCount: Int
          _tmpStagedFlushCount = _stmt.getLong(_columnIndexOfStagedFlushCount).toInt()
          val _tmpSyncProfileTier: String?
          if (_stmt.isNull(_columnIndexOfSyncProfileTier)) {
            _tmpSyncProfileTier = null
          } else {
            _tmpSyncProfileTier = _stmt.getText(_columnIndexOfSyncProfileTier)
          }
          val _tmpSyncProfileBatchSize: Int
          _tmpSyncProfileBatchSize = _stmt.getLong(_columnIndexOfSyncProfileBatchSize).toInt()
          val _tmpSyncProfileStrategy: String?
          if (_stmt.isNull(_columnIndexOfSyncProfileStrategy)) {
            _tmpSyncProfileStrategy = null
          } else {
            _tmpSyncProfileStrategy = _stmt.getText(_columnIndexOfSyncProfileStrategy)
          }
          val _tmpSyncProfileLowMemory: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSyncProfileLowMemory).toInt()
          _tmpSyncProfileLowMemory = _tmp != 0
          val _tmpSyncProfileMemoryClassMb: Int
          _tmpSyncProfileMemoryClassMb = _stmt.getLong(_columnIndexOfSyncProfileMemoryClassMb).toInt()
          val _tmpSyncProfileAvailableMemMb: Long
          _tmpSyncProfileAvailableMemMb = _stmt.getLong(_columnIndexOfSyncProfileAvailableMemMb)
          val _tmpLastError: String?
          if (_stmt.isNull(_columnIndexOfLastError)) {
            _tmpLastError = null
          } else {
            _tmpLastError = _stmt.getText(_columnIndexOfLastError)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          val _tmpCompletedAt: Long?
          if (_stmt.isNull(_columnIndexOfCompletedAt)) {
            _tmpCompletedAt = null
          } else {
            _tmpCompletedAt = _stmt.getLong(_columnIndexOfCompletedAt)
          }
          _item = XtreamLiveOnboardingStateEntity(_tmpProviderId,_tmpProviderType,_tmpContentType,_tmpPhase,_tmpStagedSessionId,_tmpImportStrategy,_tmpNextCategoryIndex,_tmpAcceptedRowCount,_tmpStagedFlushCount,_tmpSyncProfileTier,_tmpSyncProfileBatchSize,_tmpSyncProfileStrategy,_tmpSyncProfileLowMemory,_tmpSyncProfileMemoryClassMb,_tmpSyncProfileAvailableMemMb,_tmpLastError,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeIncomplete(): Flow<List<XtreamLiveOnboardingStateEntity>> {
    val _sql: String = "SELECT * FROM xtream_live_onboarding_state WHERE completed_at IS NULL ORDER BY updated_at ASC"
    return createFlow(__db, false, arrayOf("xtream_live_onboarding_state")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfProviderType: Int = getColumnIndexOrThrow(_stmt, "provider_type")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfPhase: Int = getColumnIndexOrThrow(_stmt, "phase")
        val _columnIndexOfStagedSessionId: Int = getColumnIndexOrThrow(_stmt, "staged_session_id")
        val _columnIndexOfImportStrategy: Int = getColumnIndexOrThrow(_stmt, "import_strategy")
        val _columnIndexOfNextCategoryIndex: Int = getColumnIndexOrThrow(_stmt, "next_category_index")
        val _columnIndexOfAcceptedRowCount: Int = getColumnIndexOrThrow(_stmt, "accepted_row_count")
        val _columnIndexOfStagedFlushCount: Int = getColumnIndexOrThrow(_stmt, "staged_flush_count")
        val _columnIndexOfSyncProfileTier: Int = getColumnIndexOrThrow(_stmt, "sync_profile_tier")
        val _columnIndexOfSyncProfileBatchSize: Int = getColumnIndexOrThrow(_stmt, "sync_profile_batch_size")
        val _columnIndexOfSyncProfileStrategy: Int = getColumnIndexOrThrow(_stmt, "sync_profile_strategy")
        val _columnIndexOfSyncProfileLowMemory: Int = getColumnIndexOrThrow(_stmt, "sync_profile_low_memory")
        val _columnIndexOfSyncProfileMemoryClassMb: Int = getColumnIndexOrThrow(_stmt, "sync_profile_memory_class_mb")
        val _columnIndexOfSyncProfileAvailableMemMb: Int = getColumnIndexOrThrow(_stmt, "sync_profile_available_mem_mb")
        val _columnIndexOfLastError: Int = getColumnIndexOrThrow(_stmt, "last_error")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _columnIndexOfCompletedAt: Int = getColumnIndexOrThrow(_stmt, "completed_at")
        val _result: MutableList<XtreamLiveOnboardingStateEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: XtreamLiveOnboardingStateEntity
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpProviderType: String
          _tmpProviderType = _stmt.getText(_columnIndexOfProviderType)
          val _tmpContentType: String
          _tmpContentType = _stmt.getText(_columnIndexOfContentType)
          val _tmpPhase: String
          _tmpPhase = _stmt.getText(_columnIndexOfPhase)
          val _tmpStagedSessionId: Long?
          if (_stmt.isNull(_columnIndexOfStagedSessionId)) {
            _tmpStagedSessionId = null
          } else {
            _tmpStagedSessionId = _stmt.getLong(_columnIndexOfStagedSessionId)
          }
          val _tmpImportStrategy: String?
          if (_stmt.isNull(_columnIndexOfImportStrategy)) {
            _tmpImportStrategy = null
          } else {
            _tmpImportStrategy = _stmt.getText(_columnIndexOfImportStrategy)
          }
          val _tmpNextCategoryIndex: Int
          _tmpNextCategoryIndex = _stmt.getLong(_columnIndexOfNextCategoryIndex).toInt()
          val _tmpAcceptedRowCount: Int
          _tmpAcceptedRowCount = _stmt.getLong(_columnIndexOfAcceptedRowCount).toInt()
          val _tmpStagedFlushCount: Int
          _tmpStagedFlushCount = _stmt.getLong(_columnIndexOfStagedFlushCount).toInt()
          val _tmpSyncProfileTier: String?
          if (_stmt.isNull(_columnIndexOfSyncProfileTier)) {
            _tmpSyncProfileTier = null
          } else {
            _tmpSyncProfileTier = _stmt.getText(_columnIndexOfSyncProfileTier)
          }
          val _tmpSyncProfileBatchSize: Int
          _tmpSyncProfileBatchSize = _stmt.getLong(_columnIndexOfSyncProfileBatchSize).toInt()
          val _tmpSyncProfileStrategy: String?
          if (_stmt.isNull(_columnIndexOfSyncProfileStrategy)) {
            _tmpSyncProfileStrategy = null
          } else {
            _tmpSyncProfileStrategy = _stmt.getText(_columnIndexOfSyncProfileStrategy)
          }
          val _tmpSyncProfileLowMemory: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSyncProfileLowMemory).toInt()
          _tmpSyncProfileLowMemory = _tmp != 0
          val _tmpSyncProfileMemoryClassMb: Int
          _tmpSyncProfileMemoryClassMb = _stmt.getLong(_columnIndexOfSyncProfileMemoryClassMb).toInt()
          val _tmpSyncProfileAvailableMemMb: Long
          _tmpSyncProfileAvailableMemMb = _stmt.getLong(_columnIndexOfSyncProfileAvailableMemMb)
          val _tmpLastError: String?
          if (_stmt.isNull(_columnIndexOfLastError)) {
            _tmpLastError = null
          } else {
            _tmpLastError = _stmt.getText(_columnIndexOfLastError)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          val _tmpCompletedAt: Long?
          if (_stmt.isNull(_columnIndexOfCompletedAt)) {
            _tmpCompletedAt = null
          } else {
            _tmpCompletedAt = _stmt.getLong(_columnIndexOfCompletedAt)
          }
          _item = XtreamLiveOnboardingStateEntity(_tmpProviderId,_tmpProviderType,_tmpContentType,_tmpPhase,_tmpStagedSessionId,_tmpImportStrategy,_tmpNextCategoryIndex,_tmpAcceptedRowCount,_tmpStagedFlushCount,_tmpSyncProfileTier,_tmpSyncProfileBatchSize,_tmpSyncProfileStrategy,_tmpSyncProfileLowMemory,_tmpSyncProfileMemoryClassMb,_tmpSyncProfileAvailableMemMb,_tmpLastError,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(providerId: Long) {
    val _sql: String = "DELETE FROM xtream_live_onboarding_state WHERE provider_id = ?"
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
