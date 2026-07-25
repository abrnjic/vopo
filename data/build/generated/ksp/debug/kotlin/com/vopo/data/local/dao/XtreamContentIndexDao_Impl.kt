package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.RoomEnumConverters
import com.vopo.`data`.local.entity.XtreamContentIndexEntity
import com.vopo.domain.model.ContentType
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Float
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
public class XtreamContentIndexDao_Impl(
  __db: RoomDatabase,
) : XtreamContentIndexDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfXtreamContentIndexEntity:
      EntityInsertAdapter<XtreamContentIndexEntity>

  private val __roomEnumConverters: RoomEnumConverters = RoomEnumConverters()
  init {
    this.__db = __db
    this.__insertAdapterOfXtreamContentIndexEntity = object : EntityInsertAdapter<XtreamContentIndexEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `xtream_content_index` (`provider_id`,`content_type`,`remote_id`,`local_content_id`,`name`,`category_id`,`category_name`,`image_url`,`container_extension`,`rating`,`added_at`,`remote_updated_at`,`is_adult`,`indexed_at`,`detail_hydrated_at`,`stale_state`,`error_state`,`sync_fingerprint`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: XtreamContentIndexEntity) {
        statement.bindLong(1, entity.providerId)
        val _tmp: String? = __roomEnumConverters.fromContentType(entity.contentType)
        if (_tmp == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmp)
        }
        statement.bindText(3, entity.remoteId)
        val _tmpLocalContentId: Long? = entity.localContentId
        if (_tmpLocalContentId == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpLocalContentId)
        }
        statement.bindText(5, entity.name)
        val _tmpCategoryId: Long? = entity.categoryId
        if (_tmpCategoryId == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpCategoryId)
        }
        val _tmpCategoryName: String? = entity.categoryName
        if (_tmpCategoryName == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpCategoryName)
        }
        val _tmpImageUrl: String? = entity.imageUrl
        if (_tmpImageUrl == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpImageUrl)
        }
        val _tmpContainerExtension: String? = entity.containerExtension
        if (_tmpContainerExtension == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpContainerExtension)
        }
        statement.bindDouble(10, entity.rating.toDouble())
        statement.bindLong(11, entity.addedAt)
        statement.bindLong(12, entity.remoteUpdatedAt)
        val _tmp_1: Int = if (entity.isAdult) 1 else 0
        statement.bindLong(13, _tmp_1.toLong())
        statement.bindLong(14, entity.indexedAt)
        statement.bindLong(15, entity.detailHydratedAt)
        statement.bindText(16, entity.staleState)
        val _tmpErrorState: String? = entity.errorState
        if (_tmpErrorState == null) {
          statement.bindNull(17)
        } else {
          statement.bindText(17, _tmpErrorState)
        }
        statement.bindText(18, entity.syncFingerprint)
      }
    }
  }

  public override suspend fun upsert(entity: XtreamContentIndexEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfXtreamContentIndexEntity.insert(_connection, entity)
  }

  public override suspend fun upsertAll(entities: List<XtreamContentIndexEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfXtreamContentIndexEntity.insert(_connection, entities)
  }

  public override suspend fun pruneOrphanLocalContentRows(): Int = performInTransactionSuspending(__db) {
    super@XtreamContentIndexDao_Impl.pruneOrphanLocalContentRows()
  }

  public override suspend fun pruneStaleLocalContentRows(providerId: Long, contentType: String): Int = performInTransactionSuspending(__db) {
    super@XtreamContentIndexDao_Impl.pruneStaleLocalContentRows(providerId, contentType)
  }

  public override fun observeByProviderAndType(providerId: Long, contentType: String): Flow<List<XtreamContentIndexEntity>> {
    val _sql: String = """
        |
        |        SELECT * FROM xtream_content_index
        |        WHERE provider_id = ? AND content_type = ?
        |        ORDER BY name COLLATE NOCASE ASC
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("xtream_content_index")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, contentType)
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfRemoteId: Int = getColumnIndexOrThrow(_stmt, "remote_id")
        val _columnIndexOfLocalContentId: Int = getColumnIndexOrThrow(_stmt, "local_content_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "image_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _columnIndexOfRemoteUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "remote_updated_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIndexedAt: Int = getColumnIndexOrThrow(_stmt, "indexed_at")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfStaleState: Int = getColumnIndexOrThrow(_stmt, "stale_state")
        val _columnIndexOfErrorState: Int = getColumnIndexOrThrow(_stmt, "error_state")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _result: MutableList<XtreamContentIndexEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: XtreamContentIndexEntity
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentType: ContentType
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfContentType)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfContentType)
          }
          val _tmp_1: ContentType? = __roomEnumConverters.toContentType(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ContentType', but it was NULL.")
          } else {
            _tmpContentType = _tmp_1
          }
          val _tmpRemoteId: String
          _tmpRemoteId = _stmt.getText(_columnIndexOfRemoteId)
          val _tmpLocalContentId: Long?
          if (_stmt.isNull(_columnIndexOfLocalContentId)) {
            _tmpLocalContentId = null
          } else {
            _tmpLocalContentId = _stmt.getLong(_columnIndexOfLocalContentId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpContainerExtension: String?
          if (_stmt.isNull(_columnIndexOfContainerExtension)) {
            _tmpContainerExtension = null
          } else {
            _tmpContainerExtension = _stmt.getText(_columnIndexOfContainerExtension)
          }
          val _tmpRating: Float
          _tmpRating = _stmt.getDouble(_columnIndexOfRating).toFloat()
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          val _tmpRemoteUpdatedAt: Long
          _tmpRemoteUpdatedAt = _stmt.getLong(_columnIndexOfRemoteUpdatedAt)
          val _tmpIsAdult: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_2 != 0
          val _tmpIndexedAt: Long
          _tmpIndexedAt = _stmt.getLong(_columnIndexOfIndexedAt)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpStaleState: String
          _tmpStaleState = _stmt.getText(_columnIndexOfStaleState)
          val _tmpErrorState: String?
          if (_stmt.isNull(_columnIndexOfErrorState)) {
            _tmpErrorState = null
          } else {
            _tmpErrorState = _stmt.getText(_columnIndexOfErrorState)
          }
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          _item = XtreamContentIndexEntity(_tmpProviderId,_tmpContentType,_tmpRemoteId,_tmpLocalContentId,_tmpName,_tmpCategoryId,_tmpCategoryName,_tmpImageUrl,_tmpContainerExtension,_tmpRating,_tmpAddedAt,_tmpRemoteUpdatedAt,_tmpIsAdult,_tmpIndexedAt,_tmpDetailHydratedAt,_tmpStaleState,_tmpErrorState,_tmpSyncFingerprint)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByRemoteId(
    providerId: Long,
    contentType: String,
    remoteId: String,
  ): XtreamContentIndexEntity? {
    val _sql: String = """
        |
        |        SELECT * FROM xtream_content_index
        |        WHERE provider_id = ?
        |          AND content_type = ?
        |          AND remote_id = ?
        |        LIMIT 1
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, contentType)
        _argIndex = 3
        _stmt.bindText(_argIndex, remoteId)
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfRemoteId: Int = getColumnIndexOrThrow(_stmt, "remote_id")
        val _columnIndexOfLocalContentId: Int = getColumnIndexOrThrow(_stmt, "local_content_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "image_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _columnIndexOfRemoteUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "remote_updated_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIndexedAt: Int = getColumnIndexOrThrow(_stmt, "indexed_at")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfStaleState: Int = getColumnIndexOrThrow(_stmt, "stale_state")
        val _columnIndexOfErrorState: Int = getColumnIndexOrThrow(_stmt, "error_state")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _result: XtreamContentIndexEntity?
        if (_stmt.step()) {
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentType: ContentType
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfContentType)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfContentType)
          }
          val _tmp_1: ContentType? = __roomEnumConverters.toContentType(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ContentType', but it was NULL.")
          } else {
            _tmpContentType = _tmp_1
          }
          val _tmpRemoteId: String
          _tmpRemoteId = _stmt.getText(_columnIndexOfRemoteId)
          val _tmpLocalContentId: Long?
          if (_stmt.isNull(_columnIndexOfLocalContentId)) {
            _tmpLocalContentId = null
          } else {
            _tmpLocalContentId = _stmt.getLong(_columnIndexOfLocalContentId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpContainerExtension: String?
          if (_stmt.isNull(_columnIndexOfContainerExtension)) {
            _tmpContainerExtension = null
          } else {
            _tmpContainerExtension = _stmt.getText(_columnIndexOfContainerExtension)
          }
          val _tmpRating: Float
          _tmpRating = _stmt.getDouble(_columnIndexOfRating).toFloat()
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          val _tmpRemoteUpdatedAt: Long
          _tmpRemoteUpdatedAt = _stmt.getLong(_columnIndexOfRemoteUpdatedAt)
          val _tmpIsAdult: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_2 != 0
          val _tmpIndexedAt: Long
          _tmpIndexedAt = _stmt.getLong(_columnIndexOfIndexedAt)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpStaleState: String
          _tmpStaleState = _stmt.getText(_columnIndexOfStaleState)
          val _tmpErrorState: String?
          if (_stmt.isNull(_columnIndexOfErrorState)) {
            _tmpErrorState = null
          } else {
            _tmpErrorState = _stmt.getText(_columnIndexOfErrorState)
          }
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          _result = XtreamContentIndexEntity(_tmpProviderId,_tmpContentType,_tmpRemoteId,_tmpLocalContentId,_tmpName,_tmpCategoryId,_tmpCategoryName,_tmpImageUrl,_tmpContainerExtension,_tmpRating,_tmpAddedAt,_tmpRemoteUpdatedAt,_tmpIsAdult,_tmpIndexedAt,_tmpDetailHydratedAt,_tmpStaleState,_tmpErrorState,_tmpSyncFingerprint)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markDetailHydrated(
    providerId: Long,
    contentType: String,
    remoteId: String,
    localContentId: Long,
    imageUrl: String?,
    detailHydratedAt: Long,
  ): Int {
    val _sql: String = """
        |
        |        UPDATE xtream_content_index
        |        SET local_content_id = ?,
        |            image_url = COALESCE(?, image_url),
        |            detail_hydrated_at = ?,
        |            error_state = NULL
        |        WHERE provider_id = ?
        |          AND content_type = ?
        |          AND remote_id = ?
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, localContentId)
        _argIndex = 2
        if (imageUrl == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindText(_argIndex, imageUrl)
        }
        _argIndex = 3
        _stmt.bindLong(_argIndex, detailHydratedAt)
        _argIndex = 4
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 5
        _stmt.bindText(_argIndex, contentType)
        _argIndex = 6
        _stmt.bindText(_argIndex, remoteId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markDetailHydrationError(
    providerId: Long,
    contentType: String,
    remoteId: String,
    errorState: String,
  ): Int {
    val _sql: String = """
        |
        |        UPDATE xtream_content_index
        |        SET error_state = ?
        |        WHERE provider_id = ?
        |          AND content_type = ?
        |          AND remote_id = ?
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, errorState)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 3
        _stmt.bindText(_argIndex, contentType)
        _argIndex = 4
        _stmt.bindText(_argIndex, remoteId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markVodAndSeriesRowsStaleForRebuild(providerId: Long): Int {
    val _sql: String = """
        |
        |        UPDATE xtream_content_index
        |        SET stale_state = 'STALE_REMOTE',
        |            error_state = NULL
        |        WHERE provider_id = ?
        |          AND content_type IN ('MOVIE', 'SERIES')
        |        
        """.trimMargin()
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

  public override suspend fun markRowsStaleForProviderAndType(providerId: Long, contentType: String): Int {
    val _sql: String = """
        |
        |        UPDATE xtream_content_index
        |        SET stale_state = 'STALE_REMOTE',
        |            error_state = NULL
        |        WHERE provider_id = ?
        |          AND content_type = ?
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, contentType)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByProvider(providerId: Long): Int {
    val _sql: String = "DELETE FROM xtream_content_index WHERE provider_id = ?"
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

  public override suspend fun deleteByProviderAndType(providerId: Long, contentType: String): Int {
    val _sql: String = "DELETE FROM xtream_content_index WHERE provider_id = ? AND content_type = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, contentType)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  protected override suspend fun pruneStaleMovieRows(providerId: Long): Int {
    val _sql: String = """
        |
        |        DELETE FROM movies
        |        WHERE provider_id = ?
        |          AND EXISTS (
        |              SELECT 1
        |              FROM xtream_content_index
        |              WHERE xtream_content_index.provider_id = movies.provider_id
        |                AND xtream_content_index.content_type = 'MOVIE'
        |                AND xtream_content_index.stale_state = 'STALE_REMOTE'
        |                AND xtream_content_index.remote_id = CAST(movies.stream_id AS TEXT)
        |          )
        |        
        """.trimMargin()
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

  protected override suspend fun pruneStaleSeriesRows(providerId: Long): Int {
    val _sql: String = """
        |
        |        DELETE FROM series
        |        WHERE provider_id = ?
        |          AND EXISTS (
        |              SELECT 1
        |              FROM xtream_content_index
        |              WHERE xtream_content_index.provider_id = series.provider_id
        |                AND xtream_content_index.content_type = 'SERIES'
        |                AND xtream_content_index.stale_state = 'STALE_REMOTE'
        |                AND xtream_content_index.remote_id = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |          )
        |        
        """.trimMargin()
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

  protected override suspend fun pruneOrphanLiveRows(): Int {
    val _sql: String = """
        |
        |        DELETE FROM xtream_content_index
        |        WHERE content_type = 'LIVE'
        |          AND local_content_id IS NOT NULL
        |          AND NOT EXISTS (
        |              SELECT 1
        |              FROM channels
        |              WHERE channels.id = xtream_content_index.local_content_id
        |                AND channels.provider_id = xtream_content_index.provider_id
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  protected override suspend fun pruneOrphanMovieRows(): Int {
    val _sql: String = """
        |
        |        DELETE FROM xtream_content_index
        |        WHERE content_type = 'MOVIE'
        |          AND local_content_id IS NOT NULL
        |          AND NOT EXISTS (
        |              SELECT 1
        |              FROM movies
        |              WHERE movies.id = xtream_content_index.local_content_id
        |                AND movies.provider_id = xtream_content_index.provider_id
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  protected override suspend fun pruneOrphanSeriesRows(): Int {
    val _sql: String = """
        |
        |        DELETE FROM xtream_content_index
        |        WHERE content_type = 'SERIES'
        |          AND local_content_id IS NOT NULL
        |          AND NOT EXISTS (
        |              SELECT 1
        |              FROM series
        |              WHERE series.id = xtream_content_index.local_content_id
        |                AND series.provider_id = xtream_content_index.provider_id
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
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
