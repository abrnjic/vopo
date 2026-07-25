package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.RoomEnumConverters
import com.vopo.`data`.local.entity.PlaybackHistoryEntity
import com.vopo.`data`.local.entity.PlaybackHistoryLiteEntity
import com.vopo.domain.model.ContentType
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PlaybackHistoryDao_Impl(
  __db: RoomDatabase,
) : PlaybackHistoryDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPlaybackHistoryEntity: EntityInsertAdapter<PlaybackHistoryEntity>

  private val __roomEnumConverters: RoomEnumConverters = RoomEnumConverters()
  init {
    this.__db = __db
    this.__insertAdapterOfPlaybackHistoryEntity = object : EntityInsertAdapter<PlaybackHistoryEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `playback_history` (`id`,`content_id`,`content_type`,`provider_id`,`title`,`poster_url`,`stream_url`,`resume_position_ms`,`total_duration_ms`,`last_watched_at`,`watch_count`,`watched_status`,`series_id`,`season_number`,`episode_number`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PlaybackHistoryEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.contentId)
        val _tmp: String? = __roomEnumConverters.fromContentType(entity.contentType)
        if (_tmp == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmp)
        }
        statement.bindLong(4, entity.providerId)
        statement.bindText(5, entity.title)
        val _tmpPosterUrl: String? = entity.posterUrl
        if (_tmpPosterUrl == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpPosterUrl)
        }
        statement.bindText(7, entity.streamUrl)
        statement.bindLong(8, entity.resumePositionMs)
        statement.bindLong(9, entity.totalDurationMs)
        statement.bindLong(10, entity.lastWatchedAt)
        statement.bindLong(11, entity.watchCount.toLong())
        statement.bindText(12, entity.watchedStatus)
        val _tmpSeriesId: Long? = entity.seriesId
        if (_tmpSeriesId == null) {
          statement.bindNull(13)
        } else {
          statement.bindLong(13, _tmpSeriesId)
        }
        val _tmpSeasonNumber: Int? = entity.seasonNumber
        if (_tmpSeasonNumber == null) {
          statement.bindNull(14)
        } else {
          statement.bindLong(14, _tmpSeasonNumber.toLong())
        }
        val _tmpEpisodeNumber: Int? = entity.episodeNumber
        if (_tmpEpisodeNumber == null) {
          statement.bindNull(15)
        } else {
          statement.bindLong(15, _tmpEpisodeNumber.toLong())
        }
      }
    }
  }

  public override suspend fun insertOrUpdate(history: PlaybackHistoryEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfPlaybackHistoryEntity.insert(_connection, history)
  }

  public override fun getRecentlyWatched(limit: Int): Flow<List<PlaybackHistoryLiteEntity>> {
    val _sql: String = "SELECT * FROM playback_history ORDER BY last_watched_at DESC LIMIT ?"
    return createFlow(__db, false, arrayOf("playback_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfResumePositionMs: Int = getColumnIndexOrThrow(_stmt, "resume_position_ms")
        val _columnIndexOfTotalDurationMs: Int = getColumnIndexOrThrow(_stmt, "total_duration_ms")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfWatchedStatus: Int = getColumnIndexOrThrow(_stmt, "watched_status")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: MutableList<PlaybackHistoryLiteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaybackHistoryLiteEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpResumePositionMs: Long
          _tmpResumePositionMs = _stmt.getLong(_columnIndexOfResumePositionMs)
          val _tmpTotalDurationMs: Long
          _tmpTotalDurationMs = _stmt.getLong(_columnIndexOfTotalDurationMs)
          val _tmpLastWatchedAt: Long
          _tmpLastWatchedAt = _stmt.getLong(_columnIndexOfLastWatchedAt)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
          val _tmpWatchedStatus: String
          _tmpWatchedStatus = _stmt.getText(_columnIndexOfWatchedStatus)
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
          _item = PlaybackHistoryLiteEntity(_tmpId,_tmpContentId,_tmpContentType,_tmpProviderId,_tmpTitle,_tmpPosterUrl,_tmpStreamUrl,_tmpResumePositionMs,_tmpTotalDurationMs,_tmpLastWatchedAt,_tmpWatchCount,_tmpWatchedStatus,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getRecentlyWatchedByProvider(providerId: Long, limit: Int): Flow<List<PlaybackHistoryLiteEntity>> {
    val _sql: String = "SELECT * FROM playback_history WHERE provider_id = ? ORDER BY last_watched_at DESC LIMIT ?"
    return createFlow(__db, false, arrayOf("playback_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfResumePositionMs: Int = getColumnIndexOrThrow(_stmt, "resume_position_ms")
        val _columnIndexOfTotalDurationMs: Int = getColumnIndexOrThrow(_stmt, "total_duration_ms")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfWatchedStatus: Int = getColumnIndexOrThrow(_stmt, "watched_status")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: MutableList<PlaybackHistoryLiteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaybackHistoryLiteEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpResumePositionMs: Long
          _tmpResumePositionMs = _stmt.getLong(_columnIndexOfResumePositionMs)
          val _tmpTotalDurationMs: Long
          _tmpTotalDurationMs = _stmt.getLong(_columnIndexOfTotalDurationMs)
          val _tmpLastWatchedAt: Long
          _tmpLastWatchedAt = _stmt.getLong(_columnIndexOfLastWatchedAt)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
          val _tmpWatchedStatus: String
          _tmpWatchedStatus = _stmt.getText(_columnIndexOfWatchedStatus)
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
          _item = PlaybackHistoryLiteEntity(_tmpId,_tmpContentId,_tmpContentType,_tmpProviderId,_tmpTitle,_tmpPosterUrl,_tmpStreamUrl,_tmpResumePositionMs,_tmpTotalDurationMs,_tmpLastWatchedAt,_tmpWatchCount,_tmpWatchedStatus,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getRecentlyWatchedByProviders(providerIds: Set<Long>, limit: Int): Flow<List<PlaybackHistoryLiteEntity>> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM playback_history WHERE provider_id IN (")
    val _inputSize: Int = providerIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") ORDER BY last_watched_at DESC LIMIT ")
    _stringBuilder.append("?")
    val _sql: String = _stringBuilder.toString()
    return createFlow(__db, false, arrayOf("playback_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in providerIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        _argIndex = 1 + _inputSize
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfResumePositionMs: Int = getColumnIndexOrThrow(_stmt, "resume_position_ms")
        val _columnIndexOfTotalDurationMs: Int = getColumnIndexOrThrow(_stmt, "total_duration_ms")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfWatchedStatus: Int = getColumnIndexOrThrow(_stmt, "watched_status")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: MutableList<PlaybackHistoryLiteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: PlaybackHistoryLiteEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpResumePositionMs: Long
          _tmpResumePositionMs = _stmt.getLong(_columnIndexOfResumePositionMs)
          val _tmpTotalDurationMs: Long
          _tmpTotalDurationMs = _stmt.getLong(_columnIndexOfTotalDurationMs)
          val _tmpLastWatchedAt: Long
          _tmpLastWatchedAt = _stmt.getLong(_columnIndexOfLastWatchedAt)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
          val _tmpWatchedStatus: String
          _tmpWatchedStatus = _stmt.getText(_columnIndexOfWatchedStatus)
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
          _item_1 = PlaybackHistoryLiteEntity(_tmpId,_tmpContentId,_tmpContentType,_tmpProviderId,_tmpTitle,_tmpPosterUrl,_tmpStreamUrl,_tmpResumePositionMs,_tmpTotalDurationMs,_tmpLastWatchedAt,_tmpWatchCount,_tmpWatchedStatus,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByProvider(providerId: Long): Flow<List<PlaybackHistoryLiteEntity>> {
    val _sql: String = "SELECT * FROM playback_history WHERE provider_id = ? ORDER BY last_watched_at DESC"
    return createFlow(__db, false, arrayOf("playback_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfResumePositionMs: Int = getColumnIndexOrThrow(_stmt, "resume_position_ms")
        val _columnIndexOfTotalDurationMs: Int = getColumnIndexOrThrow(_stmt, "total_duration_ms")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfWatchedStatus: Int = getColumnIndexOrThrow(_stmt, "watched_status")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: MutableList<PlaybackHistoryLiteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaybackHistoryLiteEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpResumePositionMs: Long
          _tmpResumePositionMs = _stmt.getLong(_columnIndexOfResumePositionMs)
          val _tmpTotalDurationMs: Long
          _tmpTotalDurationMs = _stmt.getLong(_columnIndexOfTotalDurationMs)
          val _tmpLastWatchedAt: Long
          _tmpLastWatchedAt = _stmt.getLong(_columnIndexOfLastWatchedAt)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
          val _tmpWatchedStatus: String
          _tmpWatchedStatus = _stmt.getText(_columnIndexOfWatchedStatus)
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
          _item = PlaybackHistoryLiteEntity(_tmpId,_tmpContentId,_tmpContentType,_tmpProviderId,_tmpTitle,_tmpPosterUrl,_tmpStreamUrl,_tmpResumePositionMs,_tmpTotalDurationMs,_tmpLastWatchedAt,_tmpWatchCount,_tmpWatchedStatus,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSync(): List<PlaybackHistoryEntity> {
    val _sql: String = "SELECT * FROM playback_history ORDER BY last_watched_at DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfResumePositionMs: Int = getColumnIndexOrThrow(_stmt, "resume_position_ms")
        val _columnIndexOfTotalDurationMs: Int = getColumnIndexOrThrow(_stmt, "total_duration_ms")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfWatchedStatus: Int = getColumnIndexOrThrow(_stmt, "watched_status")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: MutableList<PlaybackHistoryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaybackHistoryEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpResumePositionMs: Long
          _tmpResumePositionMs = _stmt.getLong(_columnIndexOfResumePositionMs)
          val _tmpTotalDurationMs: Long
          _tmpTotalDurationMs = _stmt.getLong(_columnIndexOfTotalDurationMs)
          val _tmpLastWatchedAt: Long
          _tmpLastWatchedAt = _stmt.getLong(_columnIndexOfLastWatchedAt)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
          val _tmpWatchedStatus: String
          _tmpWatchedStatus = _stmt.getText(_columnIndexOfWatchedStatus)
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
          _item = PlaybackHistoryEntity(_tmpId,_tmpContentId,_tmpContentType,_tmpProviderId,_tmpTitle,_tmpPosterUrl,_tmpStreamUrl,_tmpResumePositionMs,_tmpTotalDurationMs,_tmpLastWatchedAt,_tmpWatchCount,_tmpWatchedStatus,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun `get`(
    contentId: Long,
    contentType: String,
    providerId: Long,
  ): PlaybackHistoryEntity? {
    val _sql: String = "SELECT * FROM playback_history WHERE content_id = ? AND content_type = ? AND provider_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, contentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, contentType)
        _argIndex = 3
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfResumePositionMs: Int = getColumnIndexOrThrow(_stmt, "resume_position_ms")
        val _columnIndexOfTotalDurationMs: Int = getColumnIndexOrThrow(_stmt, "total_duration_ms")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfWatchedStatus: Int = getColumnIndexOrThrow(_stmt, "watched_status")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: PlaybackHistoryEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpResumePositionMs: Long
          _tmpResumePositionMs = _stmt.getLong(_columnIndexOfResumePositionMs)
          val _tmpTotalDurationMs: Long
          _tmpTotalDurationMs = _stmt.getLong(_columnIndexOfTotalDurationMs)
          val _tmpLastWatchedAt: Long
          _tmpLastWatchedAt = _stmt.getLong(_columnIndexOfLastWatchedAt)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
          val _tmpWatchedStatus: String
          _tmpWatchedStatus = _stmt.getText(_columnIndexOfWatchedStatus)
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
          _result = PlaybackHistoryEntity(_tmpId,_tmpContentId,_tmpContentType,_tmpProviderId,_tmpTitle,_tmpPosterUrl,_tmpStreamUrl,_tmpResumePositionMs,_tmpTotalDurationMs,_tmpLastWatchedAt,_tmpWatchCount,_tmpWatchedStatus,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLatestMovieHistoryBySharedTmdb(contentId: Long, providerId: Long): PlaybackHistoryEntity? {
    val _sql: String = """
        |
        |        SELECT ph.* FROM playback_history ph
        |        JOIN movies current_movie
        |          ON current_movie.id = ?
        |         AND current_movie.provider_id = ?
        |        JOIN tmdb_identity identity
        |          ON identity.tmdb_id = current_movie.tmdb_id
        |         AND identity.content_type = 'MOVIE'
        |        JOIN movies candidate_movie
        |          ON candidate_movie.tmdb_id = identity.tmdb_id
        |        WHERE current_movie.tmdb_id IS NOT NULL
        |          AND ph.content_type = 'MOVIE'
        |          AND ph.provider_id = candidate_movie.provider_id
        |          AND ph.content_id = candidate_movie.id
        |        ORDER BY ph.last_watched_at DESC
        |        LIMIT 1
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, contentId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfResumePositionMs: Int = getColumnIndexOrThrow(_stmt, "resume_position_ms")
        val _columnIndexOfTotalDurationMs: Int = getColumnIndexOrThrow(_stmt, "total_duration_ms")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfWatchedStatus: Int = getColumnIndexOrThrow(_stmt, "watched_status")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: PlaybackHistoryEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpResumePositionMs: Long
          _tmpResumePositionMs = _stmt.getLong(_columnIndexOfResumePositionMs)
          val _tmpTotalDurationMs: Long
          _tmpTotalDurationMs = _stmt.getLong(_columnIndexOfTotalDurationMs)
          val _tmpLastWatchedAt: Long
          _tmpLastWatchedAt = _stmt.getLong(_columnIndexOfLastWatchedAt)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
          val _tmpWatchedStatus: String
          _tmpWatchedStatus = _stmt.getText(_columnIndexOfWatchedStatus)
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
          _result = PlaybackHistoryEntity(_tmpId,_tmpContentId,_tmpContentType,_tmpProviderId,_tmpTitle,_tmpPosterUrl,_tmpStreamUrl,_tmpResumePositionMs,_tmpTotalDurationMs,_tmpLastWatchedAt,_tmpWatchCount,_tmpWatchedStatus,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLatestSeriesHistoryBySharedTmdb(seriesId: Long, providerId: Long): PlaybackHistoryEntity? {
    val _sql: String = """
        |
        |        SELECT ph.* FROM playback_history ph
        |        JOIN series current_series
        |          ON current_series.id = ?
        |         AND current_series.provider_id = ?
        |        JOIN tmdb_identity identity
        |          ON identity.tmdb_id = current_series.tmdb_id
        |         AND identity.content_type = 'SERIES'
        |        JOIN series candidate_series
        |          ON candidate_series.tmdb_id = identity.tmdb_id
        |        WHERE current_series.tmdb_id IS NOT NULL
        |          AND (
        |              (ph.content_type = 'SERIES' AND ph.content_id = candidate_series.id)
        |              OR (
        |                  ph.content_type = 'SERIES_EPISODE'
        |                  AND ph.series_id = candidate_series.id
        |              )
        |          )
        |          AND ph.provider_id = candidate_series.provider_id
        |        ORDER BY ph.last_watched_at DESC
        |        LIMIT 1
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, seriesId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfResumePositionMs: Int = getColumnIndexOrThrow(_stmt, "resume_position_ms")
        val _columnIndexOfTotalDurationMs: Int = getColumnIndexOrThrow(_stmt, "total_duration_ms")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfWatchedStatus: Int = getColumnIndexOrThrow(_stmt, "watched_status")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: PlaybackHistoryEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpResumePositionMs: Long
          _tmpResumePositionMs = _stmt.getLong(_columnIndexOfResumePositionMs)
          val _tmpTotalDurationMs: Long
          _tmpTotalDurationMs = _stmt.getLong(_columnIndexOfTotalDurationMs)
          val _tmpLastWatchedAt: Long
          _tmpLastWatchedAt = _stmt.getLong(_columnIndexOfLastWatchedAt)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
          val _tmpWatchedStatus: String
          _tmpWatchedStatus = _stmt.getText(_columnIndexOfWatchedStatus)
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
          _result = PlaybackHistoryEntity(_tmpId,_tmpContentId,_tmpContentType,_tmpProviderId,_tmpTitle,_tmpPosterUrl,_tmpStreamUrl,_tmpResumePositionMs,_tmpTotalDurationMs,_tmpLastWatchedAt,_tmpWatchCount,_tmpWatchedStatus,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getLatestEpisodeHistoryByCoordinates(
    providerId: Long,
    seriesId: Long,
    seasonNumber: Int,
    episodeNumber: Int,
  ): PlaybackHistoryEntity? {
    val _sql: String = """
        |
        |        SELECT * FROM playback_history
        |        WHERE content_type = 'SERIES_EPISODE'
        |          AND provider_id = ?
        |          AND series_id = ?
        |          AND season_number = ?
        |          AND episode_number = ?
        |        ORDER BY last_watched_at DESC
        |        LIMIT 1
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, seriesId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, seasonNumber.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, episodeNumber.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfResumePositionMs: Int = getColumnIndexOrThrow(_stmt, "resume_position_ms")
        val _columnIndexOfTotalDurationMs: Int = getColumnIndexOrThrow(_stmt, "total_duration_ms")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfWatchedStatus: Int = getColumnIndexOrThrow(_stmt, "watched_status")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _result: PlaybackHistoryEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpResumePositionMs: Long
          _tmpResumePositionMs = _stmt.getLong(_columnIndexOfResumePositionMs)
          val _tmpTotalDurationMs: Long
          _tmpTotalDurationMs = _stmt.getLong(_columnIndexOfTotalDurationMs)
          val _tmpLastWatchedAt: Long
          _tmpLastWatchedAt = _stmt.getLong(_columnIndexOfLastWatchedAt)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
          val _tmpWatchedStatus: String
          _tmpWatchedStatus = _stmt.getText(_columnIndexOfWatchedStatus)
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
          _result = PlaybackHistoryEntity(_tmpId,_tmpContentId,_tmpContentType,_tmpProviderId,_tmpTitle,_tmpPosterUrl,_tmpStreamUrl,_tmpResumePositionMs,_tmpTotalDurationMs,_tmpLastWatchedAt,_tmpWatchCount,_tmpWatchedStatus,_tmpSeriesId,_tmpSeasonNumber,_tmpEpisodeNumber)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(
    contentId: Long,
    contentType: String,
    providerId: Long,
  ) {
    val _sql: String = "DELETE FROM playback_history WHERE content_id = ? AND content_type = ? AND provider_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, contentId)
        _argIndex = 2
        _stmt.bindText(_argIndex, contentType)
        _argIndex = 3
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAll() {
    val _sql: String = "DELETE FROM playback_history"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByProvider(providerId: Long) {
    val _sql: String = "DELETE FROM playback_history WHERE provider_id = ?"
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

  public override suspend fun deleteByProviderAndType(providerId: Long, contentType: String) {
    val _sql: String = "DELETE FROM playback_history WHERE provider_id = ? AND content_type = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, contentType)
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
