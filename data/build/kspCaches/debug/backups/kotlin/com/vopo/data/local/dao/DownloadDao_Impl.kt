package com.vopo.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.DownloadEntity
import com.vopo.domain.model.DownloadContentType
import com.vopo.domain.model.DownloadStatus
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.IllegalArgumentException
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
public class DownloadDao_Impl(
  __db: RoomDatabase,
) : DownloadDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfDownloadEntity: EntityInsertAdapter<DownloadEntity>

  private val __updateAdapterOfDownloadEntity: EntityDeleteOrUpdateAdapter<DownloadEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfDownloadEntity = object : EntityInsertAdapter<DownloadEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `downloads` (`id`,`provider_id`,`content_type`,`content_id`,`content_name`,`stream_url`,`source_stream_url`,`source_stream_id`,`container_extension`,`poster_url`,`output_uri`,`output_display_path`,`status`,`bytes_written`,`total_bytes`,`supports_resume`,`retry_count`,`created_at`,`completed_at`,`failure_reason`,`series_id`,`season_number`,`episode_number`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DownloadEntity) {
        statement.bindText(1, entity.id)
        statement.bindLong(2, entity.providerId)
        statement.bindText(3, __DownloadContentType_enumToString(entity.contentType))
        statement.bindLong(4, entity.contentId)
        statement.bindText(5, entity.contentName)
        statement.bindText(6, entity.streamUrl)
        val _tmpSourceStreamUrl: String? = entity.sourceStreamUrl
        if (_tmpSourceStreamUrl == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpSourceStreamUrl)
        }
        val _tmpSourceStreamId: Long? = entity.sourceStreamId
        if (_tmpSourceStreamId == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpSourceStreamId)
        }
        val _tmpContainerExtension: String? = entity.containerExtension
        if (_tmpContainerExtension == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpContainerExtension)
        }
        val _tmpPosterUrl: String? = entity.posterUrl
        if (_tmpPosterUrl == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpPosterUrl)
        }
        val _tmpOutputUri: String? = entity.outputUri
        if (_tmpOutputUri == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpOutputUri)
        }
        val _tmpOutputDisplayPath: String? = entity.outputDisplayPath
        if (_tmpOutputDisplayPath == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpOutputDisplayPath)
        }
        statement.bindText(13, __DownloadStatus_enumToString(entity.status))
        statement.bindLong(14, entity.bytesWritten)
        val _tmpTotalBytes: Long? = entity.totalBytes
        if (_tmpTotalBytes == null) {
          statement.bindNull(15)
        } else {
          statement.bindLong(15, _tmpTotalBytes)
        }
        val _tmp: Int = if (entity.supportsResume) 1 else 0
        statement.bindLong(16, _tmp.toLong())
        statement.bindLong(17, entity.retryCount.toLong())
        statement.bindLong(18, entity.createdAt)
        val _tmpCompletedAt: Long? = entity.completedAt
        if (_tmpCompletedAt == null) {
          statement.bindNull(19)
        } else {
          statement.bindLong(19, _tmpCompletedAt)
        }
        val _tmpFailureReason: String? = entity.failureReason
        if (_tmpFailureReason == null) {
          statement.bindNull(20)
        } else {
          statement.bindText(20, _tmpFailureReason)
        }
        val _tmpSeriesId: Long? = entity.seriesId
        if (_tmpSeriesId == null) {
          statement.bindNull(21)
        } else {
          statement.bindLong(21, _tmpSeriesId)
        }
        val _tmpSeasonNumber: Int? = entity.seasonNumber
        if (_tmpSeasonNumber == null) {
          statement.bindNull(22)
        } else {
          statement.bindLong(22, _tmpSeasonNumber.toLong())
        }
        val _tmpEpisodeNumber: Int? = entity.episodeNumber
        if (_tmpEpisodeNumber == null) {
          statement.bindNull(23)
        } else {
          statement.bindLong(23, _tmpEpisodeNumber.toLong())
        }
      }
    }
    this.__updateAdapterOfDownloadEntity = object : EntityDeleteOrUpdateAdapter<DownloadEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `downloads` SET `id` = ?,`provider_id` = ?,`content_type` = ?,`content_id` = ?,`content_name` = ?,`stream_url` = ?,`source_stream_url` = ?,`source_stream_id` = ?,`container_extension` = ?,`poster_url` = ?,`output_uri` = ?,`output_display_path` = ?,`status` = ?,`bytes_written` = ?,`total_bytes` = ?,`supports_resume` = ?,`retry_count` = ?,`created_at` = ?,`completed_at` = ?,`failure_reason` = ?,`series_id` = ?,`season_number` = ?,`episode_number` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: DownloadEntity) {
        statement.bindText(1, entity.id)
        statement.bindLong(2, entity.providerId)
        statement.bindText(3, __DownloadContentType_enumToString(entity.contentType))
        statement.bindLong(4, entity.contentId)
        statement.bindText(5, entity.contentName)
        statement.bindText(6, entity.streamUrl)
        val _tmpSourceStreamUrl: String? = entity.sourceStreamUrl
        if (_tmpSourceStreamUrl == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpSourceStreamUrl)
        }
        val _tmpSourceStreamId: Long? = entity.sourceStreamId
        if (_tmpSourceStreamId == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpSourceStreamId)
        }
        val _tmpContainerExtension: String? = entity.containerExtension
        if (_tmpContainerExtension == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpContainerExtension)
        }
        val _tmpPosterUrl: String? = entity.posterUrl
        if (_tmpPosterUrl == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpPosterUrl)
        }
        val _tmpOutputUri: String? = entity.outputUri
        if (_tmpOutputUri == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpOutputUri)
        }
        val _tmpOutputDisplayPath: String? = entity.outputDisplayPath
        if (_tmpOutputDisplayPath == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpOutputDisplayPath)
        }
        statement.bindText(13, __DownloadStatus_enumToString(entity.status))
        statement.bindLong(14, entity.bytesWritten)
        val _tmpTotalBytes: Long? = entity.totalBytes
        if (_tmpTotalBytes == null) {
          statement.bindNull(15)
        } else {
          statement.bindLong(15, _tmpTotalBytes)
        }
        val _tmp: Int = if (entity.supportsResume) 1 else 0
        statement.bindLong(16, _tmp.toLong())
        statement.bindLong(17, entity.retryCount.toLong())
        statement.bindLong(18, entity.createdAt)
        val _tmpCompletedAt: Long? = entity.completedAt
        if (_tmpCompletedAt == null) {
          statement.bindNull(19)
        } else {
          statement.bindLong(19, _tmpCompletedAt)
        }
        val _tmpFailureReason: String? = entity.failureReason
        if (_tmpFailureReason == null) {
          statement.bindNull(20)
        } else {
          statement.bindText(20, _tmpFailureReason)
        }
        val _tmpSeriesId: Long? = entity.seriesId
        if (_tmpSeriesId == null) {
          statement.bindNull(21)
        } else {
          statement.bindLong(21, _tmpSeriesId)
        }
        val _tmpSeasonNumber: Int? = entity.seasonNumber
        if (_tmpSeasonNumber == null) {
          statement.bindNull(22)
        } else {
          statement.bindLong(22, _tmpSeasonNumber.toLong())
        }
        val _tmpEpisodeNumber: Int? = entity.episodeNumber
        if (_tmpEpisodeNumber == null) {
          statement.bindNull(23)
        } else {
          statement.bindLong(23, _tmpEpisodeNumber.toLong())
        }
        statement.bindText(24, entity.id)
      }
    }
  }

  public override suspend fun insert(entity: DownloadEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfDownloadEntity.insert(_connection, entity)
  }

  public override suspend fun update(entity: DownloadEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfDownloadEntity.handle(_connection, entity)
  }

  public override fun getAll(): Flow<List<DownloadEntity>> {
    val _sql: String = "SELECT * FROM downloads ORDER BY created_at DESC"
    return createFlow(__db, false, arrayOf("downloads")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentName: Int = getColumnIndexOrThrow(_stmt, "content_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfSourceStreamUrl: Int = getColumnIndexOrThrow(_stmt, "source_stream_url")
        val _columnIndexOfSourceStreamId: Int = getColumnIndexOrThrow(_stmt, "source_stream_id")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfOutputUri: Int = getColumnIndexOrThrow(_stmt, "output_uri")
        val _columnIndexOfOutputDisplayPath: Int = getColumnIndexOrThrow(_stmt, "output_display_path")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfBytesWritten: Int = getColumnIndexOrThrow(_stmt, "bytes_written")
        val _columnIndexOfTotalBytes: Int = getColumnIndexOrThrow(_stmt, "total_bytes")
        val _columnIndexOfSupportsResume: Int = getColumnIndexOrThrow(_stmt, "supports_resume")
        val _columnIndexOfRetryCount: Int = getColumnIndexOrThrow(_stmt, "retry_count")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfCompletedAt: Int = getColumnIndexOrThrow(_stmt, "completed_at")
        val _columnIndexOfFailureReason: Int = getColumnIndexOrThrow(_stmt, "failure_reason")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: MutableList<DownloadEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DownloadEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentType: DownloadContentType
          _tmpContentType = __DownloadContentType_stringToEnum(_stmt.getText(_columnIndexOfContentType))
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
          val _tmpContentName: String
          _tmpContentName = _stmt.getText(_columnIndexOfContentName)
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpSourceStreamUrl: String?
          if (_stmt.isNull(_columnIndexOfSourceStreamUrl)) {
            _tmpSourceStreamUrl = null
          } else {
            _tmpSourceStreamUrl = _stmt.getText(_columnIndexOfSourceStreamUrl)
          }
          val _tmpSourceStreamId: Long?
          if (_stmt.isNull(_columnIndexOfSourceStreamId)) {
            _tmpSourceStreamId = null
          } else {
            _tmpSourceStreamId = _stmt.getLong(_columnIndexOfSourceStreamId)
          }
          val _tmpContainerExtension: String?
          if (_stmt.isNull(_columnIndexOfContainerExtension)) {
            _tmpContainerExtension = null
          } else {
            _tmpContainerExtension = _stmt.getText(_columnIndexOfContainerExtension)
          }
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpOutputUri: String?
          if (_stmt.isNull(_columnIndexOfOutputUri)) {
            _tmpOutputUri = null
          } else {
            _tmpOutputUri = _stmt.getText(_columnIndexOfOutputUri)
          }
          val _tmpOutputDisplayPath: String?
          if (_stmt.isNull(_columnIndexOfOutputDisplayPath)) {
            _tmpOutputDisplayPath = null
          } else {
            _tmpOutputDisplayPath = _stmt.getText(_columnIndexOfOutputDisplayPath)
          }
          val _tmpStatus: DownloadStatus
          _tmpStatus = __DownloadStatus_stringToEnum(_stmt.getText(_columnIndexOfStatus))
          val _tmpBytesWritten: Long
          _tmpBytesWritten = _stmt.getLong(_columnIndexOfBytesWritten)
          val _tmpTotalBytes: Long?
          if (_stmt.isNull(_columnIndexOfTotalBytes)) {
            _tmpTotalBytes = null
          } else {
            _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
          }
          val _tmpSupportsResume: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSupportsResume).toInt()
          _tmpSupportsResume = _tmp != 0
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpCompletedAt: Long?
          if (_stmt.isNull(_columnIndexOfCompletedAt)) {
            _tmpCompletedAt = null
          } else {
            _tmpCompletedAt = _stmt.getLong(_columnIndexOfCompletedAt)
          }
          val _tmpFailureReason: String?
          if (_stmt.isNull(_columnIndexOfFailureReason)) {
            _tmpFailureReason = null
          } else {
            _tmpFailureReason = _stmt.getText(_columnIndexOfFailureReason)
          }
          val _tmpSeriesId: Long?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          }
          val _tmpSeasonNumber: Int?
          if (_stmt.isNull(_columnIndexOfSeasonNumber)) {
            _tmpSeasonNumber = null
          } else {
            _tmpSeasonNumber = _stmt.getLong(_columnIndexOfSeasonNumber).toInt()
          }
          val _tmpEpisodeNumber: Int?
          if (_stmt.isNull(_columnIndexOfEpisodeNumber)) {
            _tmpEpisodeNumber = null
          } else {
            _tmpEpisodeNumber = _stmt.getLong(_columnIndexOfEpisodeNumber).toInt()
          }
          _item = DownloadEntity(_tmpId,_tmpProviderId,_tmpContentType,_tmpContentId,_tmpContentName,_tmpStreamUrl,_tmpSourceStreamUrl,_tmpSourceStreamId,_tmpContainerExtension,_tmpPosterUrl,_tmpOutputUri,_tmpOutputDisplayPath,_tmpStatus,_tmpBytesWritten,_tmpTotalBytes,_tmpSupportsResume,_tmpRetryCount,_tmpCreatedAt,_tmpCompletedAt,_tmpFailureReason,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getById(id: String): Flow<DownloadEntity?> {
    val _sql: String = "SELECT * FROM downloads WHERE id = ?"
    return createFlow(__db, false, arrayOf("downloads")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentName: Int = getColumnIndexOrThrow(_stmt, "content_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfSourceStreamUrl: Int = getColumnIndexOrThrow(_stmt, "source_stream_url")
        val _columnIndexOfSourceStreamId: Int = getColumnIndexOrThrow(_stmt, "source_stream_id")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfOutputUri: Int = getColumnIndexOrThrow(_stmt, "output_uri")
        val _columnIndexOfOutputDisplayPath: Int = getColumnIndexOrThrow(_stmt, "output_display_path")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfBytesWritten: Int = getColumnIndexOrThrow(_stmt, "bytes_written")
        val _columnIndexOfTotalBytes: Int = getColumnIndexOrThrow(_stmt, "total_bytes")
        val _columnIndexOfSupportsResume: Int = getColumnIndexOrThrow(_stmt, "supports_resume")
        val _columnIndexOfRetryCount: Int = getColumnIndexOrThrow(_stmt, "retry_count")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfCompletedAt: Int = getColumnIndexOrThrow(_stmt, "completed_at")
        val _columnIndexOfFailureReason: Int = getColumnIndexOrThrow(_stmt, "failure_reason")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: DownloadEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentType: DownloadContentType
          _tmpContentType = __DownloadContentType_stringToEnum(_stmt.getText(_columnIndexOfContentType))
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
          val _tmpContentName: String
          _tmpContentName = _stmt.getText(_columnIndexOfContentName)
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpSourceStreamUrl: String?
          if (_stmt.isNull(_columnIndexOfSourceStreamUrl)) {
            _tmpSourceStreamUrl = null
          } else {
            _tmpSourceStreamUrl = _stmt.getText(_columnIndexOfSourceStreamUrl)
          }
          val _tmpSourceStreamId: Long?
          if (_stmt.isNull(_columnIndexOfSourceStreamId)) {
            _tmpSourceStreamId = null
          } else {
            _tmpSourceStreamId = _stmt.getLong(_columnIndexOfSourceStreamId)
          }
          val _tmpContainerExtension: String?
          if (_stmt.isNull(_columnIndexOfContainerExtension)) {
            _tmpContainerExtension = null
          } else {
            _tmpContainerExtension = _stmt.getText(_columnIndexOfContainerExtension)
          }
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpOutputUri: String?
          if (_stmt.isNull(_columnIndexOfOutputUri)) {
            _tmpOutputUri = null
          } else {
            _tmpOutputUri = _stmt.getText(_columnIndexOfOutputUri)
          }
          val _tmpOutputDisplayPath: String?
          if (_stmt.isNull(_columnIndexOfOutputDisplayPath)) {
            _tmpOutputDisplayPath = null
          } else {
            _tmpOutputDisplayPath = _stmt.getText(_columnIndexOfOutputDisplayPath)
          }
          val _tmpStatus: DownloadStatus
          _tmpStatus = __DownloadStatus_stringToEnum(_stmt.getText(_columnIndexOfStatus))
          val _tmpBytesWritten: Long
          _tmpBytesWritten = _stmt.getLong(_columnIndexOfBytesWritten)
          val _tmpTotalBytes: Long?
          if (_stmt.isNull(_columnIndexOfTotalBytes)) {
            _tmpTotalBytes = null
          } else {
            _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
          }
          val _tmpSupportsResume: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSupportsResume).toInt()
          _tmpSupportsResume = _tmp != 0
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpCompletedAt: Long?
          if (_stmt.isNull(_columnIndexOfCompletedAt)) {
            _tmpCompletedAt = null
          } else {
            _tmpCompletedAt = _stmt.getLong(_columnIndexOfCompletedAt)
          }
          val _tmpFailureReason: String?
          if (_stmt.isNull(_columnIndexOfFailureReason)) {
            _tmpFailureReason = null
          } else {
            _tmpFailureReason = _stmt.getText(_columnIndexOfFailureReason)
          }
          val _tmpSeriesId: Long?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          }
          val _tmpSeasonNumber: Int?
          if (_stmt.isNull(_columnIndexOfSeasonNumber)) {
            _tmpSeasonNumber = null
          } else {
            _tmpSeasonNumber = _stmt.getLong(_columnIndexOfSeasonNumber).toInt()
          }
          val _tmpEpisodeNumber: Int?
          if (_stmt.isNull(_columnIndexOfEpisodeNumber)) {
            _tmpEpisodeNumber = null
          } else {
            _tmpEpisodeNumber = _stmt.getLong(_columnIndexOfEpisodeNumber).toInt()
          }
          _result = DownloadEntity(_tmpId,_tmpProviderId,_tmpContentType,_tmpContentId,_tmpContentName,_tmpStreamUrl,_tmpSourceStreamUrl,_tmpSourceStreamId,_tmpContainerExtension,_tmpPosterUrl,_tmpOutputUri,_tmpOutputDisplayPath,_tmpStatus,_tmpBytesWritten,_tmpTotalBytes,_tmpSupportsResume,_tmpRetryCount,_tmpCreatedAt,_tmpCompletedAt,_tmpFailureReason,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByIdOnce(id: String): DownloadEntity? {
    val _sql: String = "SELECT * FROM downloads WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentName: Int = getColumnIndexOrThrow(_stmt, "content_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfSourceStreamUrl: Int = getColumnIndexOrThrow(_stmt, "source_stream_url")
        val _columnIndexOfSourceStreamId: Int = getColumnIndexOrThrow(_stmt, "source_stream_id")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfOutputUri: Int = getColumnIndexOrThrow(_stmt, "output_uri")
        val _columnIndexOfOutputDisplayPath: Int = getColumnIndexOrThrow(_stmt, "output_display_path")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfBytesWritten: Int = getColumnIndexOrThrow(_stmt, "bytes_written")
        val _columnIndexOfTotalBytes: Int = getColumnIndexOrThrow(_stmt, "total_bytes")
        val _columnIndexOfSupportsResume: Int = getColumnIndexOrThrow(_stmt, "supports_resume")
        val _columnIndexOfRetryCount: Int = getColumnIndexOrThrow(_stmt, "retry_count")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfCompletedAt: Int = getColumnIndexOrThrow(_stmt, "completed_at")
        val _columnIndexOfFailureReason: Int = getColumnIndexOrThrow(_stmt, "failure_reason")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: DownloadEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentType: DownloadContentType
          _tmpContentType = __DownloadContentType_stringToEnum(_stmt.getText(_columnIndexOfContentType))
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
          val _tmpContentName: String
          _tmpContentName = _stmt.getText(_columnIndexOfContentName)
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpSourceStreamUrl: String?
          if (_stmt.isNull(_columnIndexOfSourceStreamUrl)) {
            _tmpSourceStreamUrl = null
          } else {
            _tmpSourceStreamUrl = _stmt.getText(_columnIndexOfSourceStreamUrl)
          }
          val _tmpSourceStreamId: Long?
          if (_stmt.isNull(_columnIndexOfSourceStreamId)) {
            _tmpSourceStreamId = null
          } else {
            _tmpSourceStreamId = _stmt.getLong(_columnIndexOfSourceStreamId)
          }
          val _tmpContainerExtension: String?
          if (_stmt.isNull(_columnIndexOfContainerExtension)) {
            _tmpContainerExtension = null
          } else {
            _tmpContainerExtension = _stmt.getText(_columnIndexOfContainerExtension)
          }
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpOutputUri: String?
          if (_stmt.isNull(_columnIndexOfOutputUri)) {
            _tmpOutputUri = null
          } else {
            _tmpOutputUri = _stmt.getText(_columnIndexOfOutputUri)
          }
          val _tmpOutputDisplayPath: String?
          if (_stmt.isNull(_columnIndexOfOutputDisplayPath)) {
            _tmpOutputDisplayPath = null
          } else {
            _tmpOutputDisplayPath = _stmt.getText(_columnIndexOfOutputDisplayPath)
          }
          val _tmpStatus: DownloadStatus
          _tmpStatus = __DownloadStatus_stringToEnum(_stmt.getText(_columnIndexOfStatus))
          val _tmpBytesWritten: Long
          _tmpBytesWritten = _stmt.getLong(_columnIndexOfBytesWritten)
          val _tmpTotalBytes: Long?
          if (_stmt.isNull(_columnIndexOfTotalBytes)) {
            _tmpTotalBytes = null
          } else {
            _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
          }
          val _tmpSupportsResume: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSupportsResume).toInt()
          _tmpSupportsResume = _tmp != 0
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpCompletedAt: Long?
          if (_stmt.isNull(_columnIndexOfCompletedAt)) {
            _tmpCompletedAt = null
          } else {
            _tmpCompletedAt = _stmt.getLong(_columnIndexOfCompletedAt)
          }
          val _tmpFailureReason: String?
          if (_stmt.isNull(_columnIndexOfFailureReason)) {
            _tmpFailureReason = null
          } else {
            _tmpFailureReason = _stmt.getText(_columnIndexOfFailureReason)
          }
          val _tmpSeriesId: Long?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          }
          val _tmpSeasonNumber: Int?
          if (_stmt.isNull(_columnIndexOfSeasonNumber)) {
            _tmpSeasonNumber = null
          } else {
            _tmpSeasonNumber = _stmt.getLong(_columnIndexOfSeasonNumber).toInt()
          }
          val _tmpEpisodeNumber: Int?
          if (_stmt.isNull(_columnIndexOfEpisodeNumber)) {
            _tmpEpisodeNumber = null
          } else {
            _tmpEpisodeNumber = _stmt.getLong(_columnIndexOfEpisodeNumber).toInt()
          }
          _result = DownloadEntity(_tmpId,_tmpProviderId,_tmpContentType,_tmpContentId,_tmpContentName,_tmpStreamUrl,_tmpSourceStreamUrl,_tmpSourceStreamId,_tmpContainerExtension,_tmpPosterUrl,_tmpOutputUri,_tmpOutputDisplayPath,_tmpStatus,_tmpBytesWritten,_tmpTotalBytes,_tmpSupportsResume,_tmpRetryCount,_tmpCreatedAt,_tmpCompletedAt,_tmpFailureReason,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getActive(): Flow<List<DownloadEntity>> {
    val _sql: String = "SELECT * FROM downloads WHERE status IN ('PENDING', 'DOWNLOADING')"
    return createFlow(__db, false, arrayOf("downloads")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentName: Int = getColumnIndexOrThrow(_stmt, "content_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfSourceStreamUrl: Int = getColumnIndexOrThrow(_stmt, "source_stream_url")
        val _columnIndexOfSourceStreamId: Int = getColumnIndexOrThrow(_stmt, "source_stream_id")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfOutputUri: Int = getColumnIndexOrThrow(_stmt, "output_uri")
        val _columnIndexOfOutputDisplayPath: Int = getColumnIndexOrThrow(_stmt, "output_display_path")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfBytesWritten: Int = getColumnIndexOrThrow(_stmt, "bytes_written")
        val _columnIndexOfTotalBytes: Int = getColumnIndexOrThrow(_stmt, "total_bytes")
        val _columnIndexOfSupportsResume: Int = getColumnIndexOrThrow(_stmt, "supports_resume")
        val _columnIndexOfRetryCount: Int = getColumnIndexOrThrow(_stmt, "retry_count")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfCompletedAt: Int = getColumnIndexOrThrow(_stmt, "completed_at")
        val _columnIndexOfFailureReason: Int = getColumnIndexOrThrow(_stmt, "failure_reason")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: MutableList<DownloadEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DownloadEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentType: DownloadContentType
          _tmpContentType = __DownloadContentType_stringToEnum(_stmt.getText(_columnIndexOfContentType))
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
          val _tmpContentName: String
          _tmpContentName = _stmt.getText(_columnIndexOfContentName)
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpSourceStreamUrl: String?
          if (_stmt.isNull(_columnIndexOfSourceStreamUrl)) {
            _tmpSourceStreamUrl = null
          } else {
            _tmpSourceStreamUrl = _stmt.getText(_columnIndexOfSourceStreamUrl)
          }
          val _tmpSourceStreamId: Long?
          if (_stmt.isNull(_columnIndexOfSourceStreamId)) {
            _tmpSourceStreamId = null
          } else {
            _tmpSourceStreamId = _stmt.getLong(_columnIndexOfSourceStreamId)
          }
          val _tmpContainerExtension: String?
          if (_stmt.isNull(_columnIndexOfContainerExtension)) {
            _tmpContainerExtension = null
          } else {
            _tmpContainerExtension = _stmt.getText(_columnIndexOfContainerExtension)
          }
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpOutputUri: String?
          if (_stmt.isNull(_columnIndexOfOutputUri)) {
            _tmpOutputUri = null
          } else {
            _tmpOutputUri = _stmt.getText(_columnIndexOfOutputUri)
          }
          val _tmpOutputDisplayPath: String?
          if (_stmt.isNull(_columnIndexOfOutputDisplayPath)) {
            _tmpOutputDisplayPath = null
          } else {
            _tmpOutputDisplayPath = _stmt.getText(_columnIndexOfOutputDisplayPath)
          }
          val _tmpStatus: DownloadStatus
          _tmpStatus = __DownloadStatus_stringToEnum(_stmt.getText(_columnIndexOfStatus))
          val _tmpBytesWritten: Long
          _tmpBytesWritten = _stmt.getLong(_columnIndexOfBytesWritten)
          val _tmpTotalBytes: Long?
          if (_stmt.isNull(_columnIndexOfTotalBytes)) {
            _tmpTotalBytes = null
          } else {
            _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
          }
          val _tmpSupportsResume: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSupportsResume).toInt()
          _tmpSupportsResume = _tmp != 0
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpCompletedAt: Long?
          if (_stmt.isNull(_columnIndexOfCompletedAt)) {
            _tmpCompletedAt = null
          } else {
            _tmpCompletedAt = _stmt.getLong(_columnIndexOfCompletedAt)
          }
          val _tmpFailureReason: String?
          if (_stmt.isNull(_columnIndexOfFailureReason)) {
            _tmpFailureReason = null
          } else {
            _tmpFailureReason = _stmt.getText(_columnIndexOfFailureReason)
          }
          val _tmpSeriesId: Long?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          }
          val _tmpSeasonNumber: Int?
          if (_stmt.isNull(_columnIndexOfSeasonNumber)) {
            _tmpSeasonNumber = null
          } else {
            _tmpSeasonNumber = _stmt.getLong(_columnIndexOfSeasonNumber).toInt()
          }
          val _tmpEpisodeNumber: Int?
          if (_stmt.isNull(_columnIndexOfEpisodeNumber)) {
            _tmpEpisodeNumber = null
          } else {
            _tmpEpisodeNumber = _stmt.getLong(_columnIndexOfEpisodeNumber).toInt()
          }
          _item = DownloadEntity(_tmpId,_tmpProviderId,_tmpContentType,_tmpContentId,_tmpContentName,_tmpStreamUrl,_tmpSourceStreamUrl,_tmpSourceStreamId,_tmpContainerExtension,_tmpPosterUrl,_tmpOutputUri,_tmpOutputDisplayPath,_tmpStatus,_tmpBytesWritten,_tmpTotalBytes,_tmpSupportsResume,_tmpRetryCount,_tmpCreatedAt,_tmpCompletedAt,_tmpFailureReason,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getQueuedOnce(): List<DownloadEntity> {
    val _sql: String = "SELECT * FROM downloads WHERE status IN ('PENDING', 'PAUSED') ORDER BY created_at ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentName: Int = getColumnIndexOrThrow(_stmt, "content_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfSourceStreamUrl: Int = getColumnIndexOrThrow(_stmt, "source_stream_url")
        val _columnIndexOfSourceStreamId: Int = getColumnIndexOrThrow(_stmt, "source_stream_id")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfOutputUri: Int = getColumnIndexOrThrow(_stmt, "output_uri")
        val _columnIndexOfOutputDisplayPath: Int = getColumnIndexOrThrow(_stmt, "output_display_path")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfBytesWritten: Int = getColumnIndexOrThrow(_stmt, "bytes_written")
        val _columnIndexOfTotalBytes: Int = getColumnIndexOrThrow(_stmt, "total_bytes")
        val _columnIndexOfSupportsResume: Int = getColumnIndexOrThrow(_stmt, "supports_resume")
        val _columnIndexOfRetryCount: Int = getColumnIndexOrThrow(_stmt, "retry_count")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfCompletedAt: Int = getColumnIndexOrThrow(_stmt, "completed_at")
        val _columnIndexOfFailureReason: Int = getColumnIndexOrThrow(_stmt, "failure_reason")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: MutableList<DownloadEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DownloadEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentType: DownloadContentType
          _tmpContentType = __DownloadContentType_stringToEnum(_stmt.getText(_columnIndexOfContentType))
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
          val _tmpContentName: String
          _tmpContentName = _stmt.getText(_columnIndexOfContentName)
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpSourceStreamUrl: String?
          if (_stmt.isNull(_columnIndexOfSourceStreamUrl)) {
            _tmpSourceStreamUrl = null
          } else {
            _tmpSourceStreamUrl = _stmt.getText(_columnIndexOfSourceStreamUrl)
          }
          val _tmpSourceStreamId: Long?
          if (_stmt.isNull(_columnIndexOfSourceStreamId)) {
            _tmpSourceStreamId = null
          } else {
            _tmpSourceStreamId = _stmt.getLong(_columnIndexOfSourceStreamId)
          }
          val _tmpContainerExtension: String?
          if (_stmt.isNull(_columnIndexOfContainerExtension)) {
            _tmpContainerExtension = null
          } else {
            _tmpContainerExtension = _stmt.getText(_columnIndexOfContainerExtension)
          }
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpOutputUri: String?
          if (_stmt.isNull(_columnIndexOfOutputUri)) {
            _tmpOutputUri = null
          } else {
            _tmpOutputUri = _stmt.getText(_columnIndexOfOutputUri)
          }
          val _tmpOutputDisplayPath: String?
          if (_stmt.isNull(_columnIndexOfOutputDisplayPath)) {
            _tmpOutputDisplayPath = null
          } else {
            _tmpOutputDisplayPath = _stmt.getText(_columnIndexOfOutputDisplayPath)
          }
          val _tmpStatus: DownloadStatus
          _tmpStatus = __DownloadStatus_stringToEnum(_stmt.getText(_columnIndexOfStatus))
          val _tmpBytesWritten: Long
          _tmpBytesWritten = _stmt.getLong(_columnIndexOfBytesWritten)
          val _tmpTotalBytes: Long?
          if (_stmt.isNull(_columnIndexOfTotalBytes)) {
            _tmpTotalBytes = null
          } else {
            _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
          }
          val _tmpSupportsResume: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSupportsResume).toInt()
          _tmpSupportsResume = _tmp != 0
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpCompletedAt: Long?
          if (_stmt.isNull(_columnIndexOfCompletedAt)) {
            _tmpCompletedAt = null
          } else {
            _tmpCompletedAt = _stmt.getLong(_columnIndexOfCompletedAt)
          }
          val _tmpFailureReason: String?
          if (_stmt.isNull(_columnIndexOfFailureReason)) {
            _tmpFailureReason = null
          } else {
            _tmpFailureReason = _stmt.getText(_columnIndexOfFailureReason)
          }
          val _tmpSeriesId: Long?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          }
          val _tmpSeasonNumber: Int?
          if (_stmt.isNull(_columnIndexOfSeasonNumber)) {
            _tmpSeasonNumber = null
          } else {
            _tmpSeasonNumber = _stmt.getLong(_columnIndexOfSeasonNumber).toInt()
          }
          val _tmpEpisodeNumber: Int?
          if (_stmt.isNull(_columnIndexOfEpisodeNumber)) {
            _tmpEpisodeNumber = null
          } else {
            _tmpEpisodeNumber = _stmt.getLong(_columnIndexOfEpisodeNumber).toInt()
          }
          _item = DownloadEntity(_tmpId,_tmpProviderId,_tmpContentType,_tmpContentId,_tmpContentName,_tmpStreamUrl,_tmpSourceStreamUrl,_tmpSourceStreamId,_tmpContainerExtension,_tmpPosterUrl,_tmpOutputUri,_tmpOutputDisplayPath,_tmpStatus,_tmpBytesWritten,_tmpTotalBytes,_tmpSupportsResume,_tmpRetryCount,_tmpCreatedAt,_tmpCompletedAt,_tmpFailureReason,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getRetryablePausedOnce(maxRetries: Int): List<DownloadEntity> {
    val _sql: String = "SELECT * FROM downloads WHERE status = 'PAUSED' AND retry_count < ? ORDER BY created_at ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, maxRetries.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentName: Int = getColumnIndexOrThrow(_stmt, "content_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfSourceStreamUrl: Int = getColumnIndexOrThrow(_stmt, "source_stream_url")
        val _columnIndexOfSourceStreamId: Int = getColumnIndexOrThrow(_stmt, "source_stream_id")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfOutputUri: Int = getColumnIndexOrThrow(_stmt, "output_uri")
        val _columnIndexOfOutputDisplayPath: Int = getColumnIndexOrThrow(_stmt, "output_display_path")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfBytesWritten: Int = getColumnIndexOrThrow(_stmt, "bytes_written")
        val _columnIndexOfTotalBytes: Int = getColumnIndexOrThrow(_stmt, "total_bytes")
        val _columnIndexOfSupportsResume: Int = getColumnIndexOrThrow(_stmt, "supports_resume")
        val _columnIndexOfRetryCount: Int = getColumnIndexOrThrow(_stmt, "retry_count")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfCompletedAt: Int = getColumnIndexOrThrow(_stmt, "completed_at")
        val _columnIndexOfFailureReason: Int = getColumnIndexOrThrow(_stmt, "failure_reason")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: MutableList<DownloadEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DownloadEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentType: DownloadContentType
          _tmpContentType = __DownloadContentType_stringToEnum(_stmt.getText(_columnIndexOfContentType))
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
          val _tmpContentName: String
          _tmpContentName = _stmt.getText(_columnIndexOfContentName)
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpSourceStreamUrl: String?
          if (_stmt.isNull(_columnIndexOfSourceStreamUrl)) {
            _tmpSourceStreamUrl = null
          } else {
            _tmpSourceStreamUrl = _stmt.getText(_columnIndexOfSourceStreamUrl)
          }
          val _tmpSourceStreamId: Long?
          if (_stmt.isNull(_columnIndexOfSourceStreamId)) {
            _tmpSourceStreamId = null
          } else {
            _tmpSourceStreamId = _stmt.getLong(_columnIndexOfSourceStreamId)
          }
          val _tmpContainerExtension: String?
          if (_stmt.isNull(_columnIndexOfContainerExtension)) {
            _tmpContainerExtension = null
          } else {
            _tmpContainerExtension = _stmt.getText(_columnIndexOfContainerExtension)
          }
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpOutputUri: String?
          if (_stmt.isNull(_columnIndexOfOutputUri)) {
            _tmpOutputUri = null
          } else {
            _tmpOutputUri = _stmt.getText(_columnIndexOfOutputUri)
          }
          val _tmpOutputDisplayPath: String?
          if (_stmt.isNull(_columnIndexOfOutputDisplayPath)) {
            _tmpOutputDisplayPath = null
          } else {
            _tmpOutputDisplayPath = _stmt.getText(_columnIndexOfOutputDisplayPath)
          }
          val _tmpStatus: DownloadStatus
          _tmpStatus = __DownloadStatus_stringToEnum(_stmt.getText(_columnIndexOfStatus))
          val _tmpBytesWritten: Long
          _tmpBytesWritten = _stmt.getLong(_columnIndexOfBytesWritten)
          val _tmpTotalBytes: Long?
          if (_stmt.isNull(_columnIndexOfTotalBytes)) {
            _tmpTotalBytes = null
          } else {
            _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
          }
          val _tmpSupportsResume: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfSupportsResume).toInt()
          _tmpSupportsResume = _tmp != 0
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpCompletedAt: Long?
          if (_stmt.isNull(_columnIndexOfCompletedAt)) {
            _tmpCompletedAt = null
          } else {
            _tmpCompletedAt = _stmt.getLong(_columnIndexOfCompletedAt)
          }
          val _tmpFailureReason: String?
          if (_stmt.isNull(_columnIndexOfFailureReason)) {
            _tmpFailureReason = null
          } else {
            _tmpFailureReason = _stmt.getText(_columnIndexOfFailureReason)
          }
          val _tmpSeriesId: Long?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          }
          val _tmpSeasonNumber: Int?
          if (_stmt.isNull(_columnIndexOfSeasonNumber)) {
            _tmpSeasonNumber = null
          } else {
            _tmpSeasonNumber = _stmt.getLong(_columnIndexOfSeasonNumber).toInt()
          }
          val _tmpEpisodeNumber: Int?
          if (_stmt.isNull(_columnIndexOfEpisodeNumber)) {
            _tmpEpisodeNumber = null
          } else {
            _tmpEpisodeNumber = _stmt.getLong(_columnIndexOfEpisodeNumber).toInt()
          }
          _item = DownloadEntity(_tmpId,_tmpProviderId,_tmpContentType,_tmpContentId,_tmpContentName,_tmpStreamUrl,_tmpSourceStreamUrl,_tmpSourceStreamId,_tmpContainerExtension,_tmpPosterUrl,_tmpOutputUri,_tmpOutputDisplayPath,_tmpStatus,_tmpBytesWritten,_tmpTotalBytes,_tmpSupportsResume,_tmpRetryCount,_tmpCreatedAt,_tmpCompletedAt,_tmpFailureReason,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: String) {
    val _sql: String = "DELETE FROM downloads WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  private fun __DownloadContentType_enumToString(_value: DownloadContentType): String = when (_value) {
    DownloadContentType.MOVIE -> "MOVIE"
    DownloadContentType.SERIES_EPISODE -> "SERIES_EPISODE"
  }

  private fun __DownloadStatus_enumToString(_value: DownloadStatus): String = when (_value) {
    DownloadStatus.PENDING -> "PENDING"
    DownloadStatus.DOWNLOADING -> "DOWNLOADING"
    DownloadStatus.PAUSED -> "PAUSED"
    DownloadStatus.COMPLETED -> "COMPLETED"
    DownloadStatus.FAILED -> "FAILED"
    DownloadStatus.CANCELLED -> "CANCELLED"
  }

  private fun __DownloadContentType_stringToEnum(_value: String): DownloadContentType = when (_value) {
    "MOVIE" -> DownloadContentType.MOVIE
    "SERIES_EPISODE" -> DownloadContentType.SERIES_EPISODE
    else -> throw IllegalArgumentException("Can't convert value to enum, unknown value: " + _value)
  }

  private fun __DownloadStatus_stringToEnum(_value: String): DownloadStatus = when (_value) {
    "PENDING" -> DownloadStatus.PENDING
    "DOWNLOADING" -> DownloadStatus.DOWNLOADING
    "PAUSED" -> DownloadStatus.PAUSED
    "COMPLETED" -> DownloadStatus.COMPLETED
    "FAILED" -> DownloadStatus.FAILED
    "CANCELLED" -> DownloadStatus.CANCELLED
    else -> throw IllegalArgumentException("Can't convert value to enum, unknown value: " + _value)
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
