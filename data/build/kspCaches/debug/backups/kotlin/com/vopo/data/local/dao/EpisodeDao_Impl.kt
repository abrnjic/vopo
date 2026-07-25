package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.EpisodeBrowseEntity
import com.vopo.`data`.local.entity.EpisodeEntity
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
public class EpisodeDao_Impl(
  __db: RoomDatabase,
) : EpisodeDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfEpisodeEntity: EntityInsertAdapter<EpisodeEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfEpisodeEntity = object : EntityInsertAdapter<EpisodeEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `episodes` (`id`,`episode_id`,`title`,`episode_number`,`season_number`,`stream_url`,`container_extension`,`cover_url`,`plot`,`duration`,`duration_seconds`,`rating`,`release_date`,`series_id`,`provider_id`,`watch_progress`,`last_watched_at`,`is_adult`,`is_user_protected`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: EpisodeEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.episodeId)
        statement.bindText(3, entity.title)
        statement.bindLong(4, entity.episodeNumber.toLong())
        statement.bindLong(5, entity.seasonNumber.toLong())
        statement.bindText(6, entity.streamUrl)
        val _tmpContainerExtension: String? = entity.containerExtension
        if (_tmpContainerExtension == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpContainerExtension)
        }
        val _tmpCoverUrl: String? = entity.coverUrl
        if (_tmpCoverUrl == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpCoverUrl)
        }
        val _tmpPlot: String? = entity.plot
        if (_tmpPlot == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpPlot)
        }
        val _tmpDuration: String? = entity.duration
        if (_tmpDuration == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpDuration)
        }
        statement.bindLong(11, entity.durationSeconds.toLong())
        statement.bindDouble(12, entity.rating.toDouble())
        val _tmpReleaseDate: String? = entity.releaseDate
        if (_tmpReleaseDate == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpReleaseDate)
        }
        statement.bindLong(14, entity.seriesId)
        statement.bindLong(15, entity.providerId)
        statement.bindLong(16, entity.watchProgress)
        statement.bindLong(17, entity.lastWatchedAt)
        val _tmp: Int = if (entity.isAdult) 1 else 0
        statement.bindLong(18, _tmp.toLong())
        val _tmp_1: Int = if (entity.isUserProtected) 1 else 0
        statement.bindLong(19, _tmp_1.toLong())
      }
    }
  }

  public override suspend fun insertAll(episodes: List<EpisodeEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfEpisodeEntity.insert(_connection, episodes)
  }

  public override suspend fun replaceAll(
    seriesId: Long,
    providerId: Long,
    episodes: List<EpisodeEntity>,
  ): Unit = performInTransactionSuspending(__db) {
    super@EpisodeDao_Impl.replaceAll(seriesId, providerId, episodes)
  }

  public override fun getBySeries(seriesId: Long): Flow<List<EpisodeBrowseEntity>> {
    val _sql: String = "SELECT * FROM episodes WHERE series_id = ? ORDER BY season_number ASC, episode_number ASC"
    return createFlow(__db, false, arrayOf("episodes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, seriesId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEpisodeId: Int = getColumnIndexOrThrow(_stmt, "episode_id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "cover_url")
        val _columnIndexOfPlot: Int = getColumnIndexOrThrow(_stmt, "plot")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<EpisodeBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: EpisodeBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpEpisodeId: Long
          _tmpEpisodeId = _stmt.getLong(_columnIndexOfEpisodeId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpEpisodeNumber: Int
          _tmpEpisodeNumber = _stmt.getLong(_columnIndexOfEpisodeNumber).toInt()
          val _tmpSeasonNumber: Int
          _tmpSeasonNumber = _stmt.getLong(_columnIndexOfSeasonNumber).toInt()
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpContainerExtension: String?
          if (_stmt.isNull(_columnIndexOfContainerExtension)) {
            _tmpContainerExtension = null
          } else {
            _tmpContainerExtension = _stmt.getText(_columnIndexOfContainerExtension)
          }
          val _tmpCoverUrl: String?
          if (_stmt.isNull(_columnIndexOfCoverUrl)) {
            _tmpCoverUrl = null
          } else {
            _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          }
          val _tmpPlot: String?
          if (_stmt.isNull(_columnIndexOfPlot)) {
            _tmpPlot = null
          } else {
            _tmpPlot = _stmt.getText(_columnIndexOfPlot)
          }
          val _tmpDuration: String?
          if (_stmt.isNull(_columnIndexOfDuration)) {
            _tmpDuration = null
          } else {
            _tmpDuration = _stmt.getText(_columnIndexOfDuration)
          }
          val _tmpDurationSeconds: Int
          _tmpDurationSeconds = _stmt.getLong(_columnIndexOfDurationSeconds).toInt()
          val _tmpRating: Float
          _tmpRating = _stmt.getDouble(_columnIndexOfRating).toFloat()
          val _tmpReleaseDate: String?
          if (_stmt.isNull(_columnIndexOfReleaseDate)) {
            _tmpReleaseDate = null
          } else {
            _tmpReleaseDate = _stmt.getText(_columnIndexOfReleaseDate)
          }
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpWatchProgress: Long
          _tmpWatchProgress = _stmt.getLong(_columnIndexOfWatchProgress)
          val _tmpLastWatchedAt: Long
          _tmpLastWatchedAt = _stmt.getLong(_columnIndexOfLastWatchedAt)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = EpisodeBrowseEntity(_tmpId,_tmpEpisodeId,_tmpTitle,_tmpEpisodeNumber,_tmpSeasonNumber,_tmpStreamUrl,_tmpContainerExtension,_tmpCoverUrl,_tmpPlot,_tmpDuration,_tmpDurationSeconds,_tmpRating,_tmpReleaseDate,_tmpSeriesId,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getBySeriesSync(seriesId: Long): List<EpisodeBrowseEntity> {
    val _sql: String = "SELECT * FROM episodes WHERE series_id = ? ORDER BY season_number ASC, episode_number ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, seriesId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEpisodeId: Int = getColumnIndexOrThrow(_stmt, "episode_id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "cover_url")
        val _columnIndexOfPlot: Int = getColumnIndexOrThrow(_stmt, "plot")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<EpisodeBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: EpisodeBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpEpisodeId: Long
          _tmpEpisodeId = _stmt.getLong(_columnIndexOfEpisodeId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpEpisodeNumber: Int
          _tmpEpisodeNumber = _stmt.getLong(_columnIndexOfEpisodeNumber).toInt()
          val _tmpSeasonNumber: Int
          _tmpSeasonNumber = _stmt.getLong(_columnIndexOfSeasonNumber).toInt()
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpContainerExtension: String?
          if (_stmt.isNull(_columnIndexOfContainerExtension)) {
            _tmpContainerExtension = null
          } else {
            _tmpContainerExtension = _stmt.getText(_columnIndexOfContainerExtension)
          }
          val _tmpCoverUrl: String?
          if (_stmt.isNull(_columnIndexOfCoverUrl)) {
            _tmpCoverUrl = null
          } else {
            _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          }
          val _tmpPlot: String?
          if (_stmt.isNull(_columnIndexOfPlot)) {
            _tmpPlot = null
          } else {
            _tmpPlot = _stmt.getText(_columnIndexOfPlot)
          }
          val _tmpDuration: String?
          if (_stmt.isNull(_columnIndexOfDuration)) {
            _tmpDuration = null
          } else {
            _tmpDuration = _stmt.getText(_columnIndexOfDuration)
          }
          val _tmpDurationSeconds: Int
          _tmpDurationSeconds = _stmt.getLong(_columnIndexOfDurationSeconds).toInt()
          val _tmpRating: Float
          _tmpRating = _stmt.getDouble(_columnIndexOfRating).toFloat()
          val _tmpReleaseDate: String?
          if (_stmt.isNull(_columnIndexOfReleaseDate)) {
            _tmpReleaseDate = null
          } else {
            _tmpReleaseDate = _stmt.getText(_columnIndexOfReleaseDate)
          }
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpWatchProgress: Long
          _tmpWatchProgress = _stmt.getLong(_columnIndexOfWatchProgress)
          val _tmpLastWatchedAt: Long
          _tmpLastWatchedAt = _stmt.getLong(_columnIndexOfLastWatchedAt)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = EpisodeBrowseEntity(_tmpId,_tmpEpisodeId,_tmpTitle,_tmpEpisodeNumber,_tmpSeasonNumber,_tmpStreamUrl,_tmpContainerExtension,_tmpCoverUrl,_tmpPlot,_tmpDuration,_tmpDurationSeconds,_tmpRating,_tmpReleaseDate,_tmpSeriesId,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long): EpisodeEntity? {
    val _sql: String = "SELECT * FROM episodes WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEpisodeId: Int = getColumnIndexOrThrow(_stmt, "episode_id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfEpisodeNumber: Int = getColumnIndexOrThrow(_stmt, "episode_number")
        val _columnIndexOfSeasonNumber: Int = getColumnIndexOrThrow(_stmt, "season_number")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "cover_url")
        val _columnIndexOfPlot: Int = getColumnIndexOrThrow(_stmt, "plot")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: EpisodeEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpEpisodeId: Long
          _tmpEpisodeId = _stmt.getLong(_columnIndexOfEpisodeId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpEpisodeNumber: Int
          _tmpEpisodeNumber = _stmt.getLong(_columnIndexOfEpisodeNumber).toInt()
          val _tmpSeasonNumber: Int
          _tmpSeasonNumber = _stmt.getLong(_columnIndexOfSeasonNumber).toInt()
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpContainerExtension: String?
          if (_stmt.isNull(_columnIndexOfContainerExtension)) {
            _tmpContainerExtension = null
          } else {
            _tmpContainerExtension = _stmt.getText(_columnIndexOfContainerExtension)
          }
          val _tmpCoverUrl: String?
          if (_stmt.isNull(_columnIndexOfCoverUrl)) {
            _tmpCoverUrl = null
          } else {
            _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          }
          val _tmpPlot: String?
          if (_stmt.isNull(_columnIndexOfPlot)) {
            _tmpPlot = null
          } else {
            _tmpPlot = _stmt.getText(_columnIndexOfPlot)
          }
          val _tmpDuration: String?
          if (_stmt.isNull(_columnIndexOfDuration)) {
            _tmpDuration = null
          } else {
            _tmpDuration = _stmt.getText(_columnIndexOfDuration)
          }
          val _tmpDurationSeconds: Int
          _tmpDurationSeconds = _stmt.getLong(_columnIndexOfDurationSeconds).toInt()
          val _tmpRating: Float
          _tmpRating = _stmt.getDouble(_columnIndexOfRating).toFloat()
          val _tmpReleaseDate: String?
          if (_stmt.isNull(_columnIndexOfReleaseDate)) {
            _tmpReleaseDate = null
          } else {
            _tmpReleaseDate = _stmt.getText(_columnIndexOfReleaseDate)
          }
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpWatchProgress: Long
          _tmpWatchProgress = _stmt.getLong(_columnIndexOfWatchProgress)
          val _tmpLastWatchedAt: Long
          _tmpLastWatchedAt = _stmt.getLong(_columnIndexOfLastWatchedAt)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _result = EpisodeEntity(_tmpId,_tmpEpisodeId,_tmpTitle,_tmpEpisodeNumber,_tmpSeasonNumber,_tmpStreamUrl,_tmpContainerExtension,_tmpCoverUrl,_tmpPlot,_tmpDuration,_tmpDurationSeconds,_tmpRating,_tmpReleaseDate,_tmpSeriesId,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getUnwatchedCount(
    providerId: Long,
    seriesId: Long,
    completionThreshold: Float,
  ): Flow<Int> {
    val _sql: String = """
        |
        |        SELECT COUNT(*)
        |        FROM episodes
        |        LEFT JOIN playback_history
        |            ON playback_history.content_id = episodes.id
        |           AND playback_history.content_type = 'SERIES_EPISODE'
        |           AND playback_history.provider_id = episodes.provider_id
        |        WHERE episodes.provider_id = ?
        |          AND episodes.series_id = ?
        |          AND (
        |              COALESCE(playback_history.total_duration_ms, episodes.duration_seconds * 1000) <= 0
        |              OR COALESCE(playback_history.resume_position_ms, episodes.watch_progress) < CAST(
        |                  COALESCE(playback_history.total_duration_ms, episodes.duration_seconds * 1000) * ?
        |                  AS INTEGER
        |              )
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("episodes", "playback_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, seriesId)
        _argIndex = 3
        _stmt.bindDouble(_argIndex, completionThreshold.toDouble())
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getIdMappings(providerId: Long, seriesId: Long): List<RemoteIdMapping> {
    val _sql: String = "SELECT id, episode_id AS remote_id FROM episodes WHERE provider_id = ? AND series_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, seriesId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfRemoteId: Int = 1
        val _result: MutableList<RemoteIdMapping> = mutableListOf()
        while (_stmt.step()) {
          val _item: RemoteIdMapping
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpRemoteId: Long
          _tmpRemoteId = _stmt.getLong(_columnIndexOfRemoteId)
          _item = RemoteIdMapping(_tmpId,_tmpRemoteId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun syncWatchProgressFromHistory(id: Long, providerId: Long) {
    val _sql: String = """
        |
        |        UPDATE episodes
        |        SET watch_progress = COALESCE((
        |            SELECT resume_position_ms FROM playback_history
        |            WHERE playback_history.content_id = episodes.id
        |              AND playback_history.content_type = 'SERIES_EPISODE'
        |              AND playback_history.provider_id = episodes.provider_id
        |        ), 0),
        |            last_watched_at = COALESCE((
        |                SELECT last_watched_at FROM playback_history
        |                WHERE playback_history.content_id = episodes.id
        |                  AND playback_history.content_type = 'SERIES_EPISODE'
        |                  AND playback_history.provider_id = episodes.provider_id
        |            ), 0)
        |        WHERE id = ? AND provider_id = ?
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun syncWatchProgressFromHistoryByProvider(providerId: Long) {
    val _sql: String = """
        |
        |        UPDATE episodes
        |        SET watch_progress = COALESCE((
        |            SELECT resume_position_ms FROM playback_history
        |            WHERE playback_history.content_id = episodes.id
        |              AND playback_history.content_type = 'SERIES_EPISODE'
        |              AND playback_history.provider_id = episodes.provider_id
        |        ), 0),
        |            last_watched_at = COALESCE((
        |                SELECT last_watched_at FROM playback_history
        |                WHERE playback_history.content_id = episodes.id
        |                  AND playback_history.content_type = 'SERIES_EPISODE'
        |                  AND playback_history.provider_id = episodes.provider_id
        |            ), 0)
        |        WHERE provider_id = ?
        |        
        """.trimMargin()
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

  public override suspend fun syncAllWatchProgressFromHistory() {
    val _sql: String = """
        |
        |        UPDATE episodes
        |        SET watch_progress = COALESCE((
        |            SELECT resume_position_ms FROM playback_history
        |            WHERE playback_history.content_id = episodes.id
        |              AND playback_history.content_type = 'SERIES_EPISODE'
        |              AND playback_history.provider_id = episodes.provider_id
        |        ), 0),
        |            last_watched_at = COALESCE((
        |                SELECT last_watched_at FROM playback_history
        |                WHERE playback_history.content_id = episodes.id
        |                  AND playback_history.content_type = 'SERIES_EPISODE'
        |                  AND playback_history.provider_id = episodes.provider_id
        |            ), 0)
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun resetAllWatchProgress() {
    val _sql: String = "UPDATE episodes SET watch_progress = 0, last_watched_at = 0"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteBySeries(seriesId: Long) {
    val _sql: String = "DELETE FROM episodes WHERE series_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, seriesId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteOrphans(): Int {
    val _sql: String = "DELETE FROM episodes WHERE series_id NOT IN (SELECT id FROM series)"
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

  public override suspend fun restoreWatchProgress(seriesId: Long) {
    val _sql: String = """
        |
        |        UPDATE episodes 
        |        SET watch_progress = (
        |            SELECT resume_position_ms FROM playback_history 
        |            WHERE playback_history.content_id = episodes.id 
        |            AND playback_history.content_type = 'SERIES_EPISODE'
        |            AND playback_history.provider_id = episodes.provider_id
        |        )
        |        WHERE series_id = ? AND EXISTS (
        |            SELECT 1 FROM playback_history 
        |            WHERE playback_history.content_id = episodes.id
        |            AND playback_history.content_type = 'SERIES_EPISODE' 
        |            AND playback_history.provider_id = episodes.provider_id
        |        )
        |    
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, seriesId)
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
