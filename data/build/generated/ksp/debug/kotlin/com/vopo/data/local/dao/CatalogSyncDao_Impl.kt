package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.RoomEnumConverters
import com.vopo.`data`.local.entity.CategoryImportStageEntity
import com.vopo.`data`.local.entity.ChannelImportStageEntity
import com.vopo.`data`.local.entity.MovieImportStageEntity
import com.vopo.`data`.local.entity.SeriesImportStageEntity
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

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class CatalogSyncDao_Impl(
  __db: RoomDatabase,
) : CatalogSyncDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfChannelImportStageEntity:
      EntityInsertAdapter<ChannelImportStageEntity>

  private val __insertAdapterOfMovieImportStageEntity: EntityInsertAdapter<MovieImportStageEntity>

  private val __insertAdapterOfSeriesImportStageEntity: EntityInsertAdapter<SeriesImportStageEntity>

  private val __insertAdapterOfCategoryImportStageEntity:
      EntityInsertAdapter<CategoryImportStageEntity>

  private val __roomEnumConverters: RoomEnumConverters = RoomEnumConverters()
  init {
    this.__db = __db
    this.__insertAdapterOfChannelImportStageEntity = object : EntityInsertAdapter<ChannelImportStageEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `channel_import_stage` (`session_id`,`provider_id`,`stream_id`,`name`,`logo_url`,`group_title`,`category_id`,`category_name`,`stream_url`,`epg_channel_id`,`number`,`catch_up_supported`,`catch_up_days`,`catchUpSource`,`is_adult`,`logical_group_id`,`error_count`,`sync_fingerprint`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ChannelImportStageEntity) {
        statement.bindLong(1, entity.sessionId)
        statement.bindLong(2, entity.providerId)
        statement.bindLong(3, entity.streamId)
        statement.bindText(4, entity.name)
        val _tmpLogoUrl: String? = entity.logoUrl
        if (_tmpLogoUrl == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpLogoUrl)
        }
        val _tmpGroupTitle: String? = entity.groupTitle
        if (_tmpGroupTitle == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpGroupTitle)
        }
        val _tmpCategoryId: Long? = entity.categoryId
        if (_tmpCategoryId == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpCategoryId)
        }
        val _tmpCategoryName: String? = entity.categoryName
        if (_tmpCategoryName == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpCategoryName)
        }
        statement.bindText(9, entity.streamUrl)
        val _tmpEpgChannelId: String? = entity.epgChannelId
        if (_tmpEpgChannelId == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpEpgChannelId)
        }
        statement.bindLong(11, entity.number.toLong())
        val _tmp: Int = if (entity.catchUpSupported) 1 else 0
        statement.bindLong(12, _tmp.toLong())
        statement.bindLong(13, entity.catchUpDays.toLong())
        val _tmpCatchUpSource: String? = entity.catchUpSource
        if (_tmpCatchUpSource == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpCatchUpSource)
        }
        val _tmp_1: Int = if (entity.isAdult) 1 else 0
        statement.bindLong(15, _tmp_1.toLong())
        statement.bindText(16, entity.logicalGroupId)
        statement.bindLong(17, entity.errorCount.toLong())
        statement.bindText(18, entity.syncFingerprint)
      }
    }
    this.__insertAdapterOfMovieImportStageEntity = object : EntityInsertAdapter<MovieImportStageEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `movie_import_stage` (`session_id`,`provider_id`,`stream_id`,`name`,`poster_url`,`backdrop_url`,`category_id`,`category_name`,`stream_url`,`container_extension`,`plot`,`cast`,`director`,`genre`,`release_date`,`duration`,`duration_seconds`,`rating`,`year`,`tmdb_id`,`youtube_trailer`,`is_adult`,`sync_fingerprint`,`added_at`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: MovieImportStageEntity) {
        statement.bindLong(1, entity.sessionId)
        statement.bindLong(2, entity.providerId)
        statement.bindLong(3, entity.streamId)
        statement.bindText(4, entity.name)
        val _tmpPosterUrl: String? = entity.posterUrl
        if (_tmpPosterUrl == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpPosterUrl)
        }
        val _tmpBackdropUrl: String? = entity.backdropUrl
        if (_tmpBackdropUrl == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpBackdropUrl)
        }
        val _tmpCategoryId: Long? = entity.categoryId
        if (_tmpCategoryId == null) {
          statement.bindNull(7)
        } else {
          statement.bindLong(7, _tmpCategoryId)
        }
        val _tmpCategoryName: String? = entity.categoryName
        if (_tmpCategoryName == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpCategoryName)
        }
        statement.bindText(9, entity.streamUrl)
        val _tmpContainerExtension: String? = entity.containerExtension
        if (_tmpContainerExtension == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpContainerExtension)
        }
        val _tmpPlot: String? = entity.plot
        if (_tmpPlot == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpPlot)
        }
        val _tmpCast: String? = entity.cast
        if (_tmpCast == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpCast)
        }
        val _tmpDirector: String? = entity.director
        if (_tmpDirector == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpDirector)
        }
        val _tmpGenre: String? = entity.genre
        if (_tmpGenre == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpGenre)
        }
        val _tmpReleaseDate: String? = entity.releaseDate
        if (_tmpReleaseDate == null) {
          statement.bindNull(15)
        } else {
          statement.bindText(15, _tmpReleaseDate)
        }
        val _tmpDuration: String? = entity.duration
        if (_tmpDuration == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpDuration)
        }
        statement.bindLong(17, entity.durationSeconds.toLong())
        statement.bindDouble(18, entity.rating.toDouble())
        val _tmpYear: String? = entity.year
        if (_tmpYear == null) {
          statement.bindNull(19)
        } else {
          statement.bindText(19, _tmpYear)
        }
        val _tmpTmdbId: Long? = entity.tmdbId
        if (_tmpTmdbId == null) {
          statement.bindNull(20)
        } else {
          statement.bindLong(20, _tmpTmdbId)
        }
        val _tmpYoutubeTrailer: String? = entity.youtubeTrailer
        if (_tmpYoutubeTrailer == null) {
          statement.bindNull(21)
        } else {
          statement.bindText(21, _tmpYoutubeTrailer)
        }
        val _tmp: Int = if (entity.isAdult) 1 else 0
        statement.bindLong(22, _tmp.toLong())
        statement.bindText(23, entity.syncFingerprint)
        statement.bindLong(24, entity.addedAt)
      }
    }
    this.__insertAdapterOfSeriesImportStageEntity = object : EntityInsertAdapter<SeriesImportStageEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `series_import_stage` (`session_id`,`provider_id`,`series_id`,`provider_series_id`,`provider_series_key`,`name`,`poster_url`,`backdrop_url`,`category_id`,`category_name`,`plot`,`cast`,`director`,`genre`,`release_date`,`rating`,`tmdb_id`,`youtube_trailer`,`episode_run_time`,`last_modified`,`is_adult`,`sync_fingerprint`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SeriesImportStageEntity) {
        statement.bindLong(1, entity.sessionId)
        statement.bindLong(2, entity.providerId)
        statement.bindLong(3, entity.seriesId)
        val _tmpProviderSeriesId: String? = entity.providerSeriesId
        if (_tmpProviderSeriesId == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpProviderSeriesId)
        }
        statement.bindText(5, entity.providerSeriesKey)
        statement.bindText(6, entity.name)
        val _tmpPosterUrl: String? = entity.posterUrl
        if (_tmpPosterUrl == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpPosterUrl)
        }
        val _tmpBackdropUrl: String? = entity.backdropUrl
        if (_tmpBackdropUrl == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpBackdropUrl)
        }
        val _tmpCategoryId: Long? = entity.categoryId
        if (_tmpCategoryId == null) {
          statement.bindNull(9)
        } else {
          statement.bindLong(9, _tmpCategoryId)
        }
        val _tmpCategoryName: String? = entity.categoryName
        if (_tmpCategoryName == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpCategoryName)
        }
        val _tmpPlot: String? = entity.plot
        if (_tmpPlot == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpPlot)
        }
        val _tmpCast: String? = entity.cast
        if (_tmpCast == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpCast)
        }
        val _tmpDirector: String? = entity.director
        if (_tmpDirector == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpDirector)
        }
        val _tmpGenre: String? = entity.genre
        if (_tmpGenre == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpGenre)
        }
        val _tmpReleaseDate: String? = entity.releaseDate
        if (_tmpReleaseDate == null) {
          statement.bindNull(15)
        } else {
          statement.bindText(15, _tmpReleaseDate)
        }
        statement.bindDouble(16, entity.rating.toDouble())
        val _tmpTmdbId: Long? = entity.tmdbId
        if (_tmpTmdbId == null) {
          statement.bindNull(17)
        } else {
          statement.bindLong(17, _tmpTmdbId)
        }
        val _tmpYoutubeTrailer: String? = entity.youtubeTrailer
        if (_tmpYoutubeTrailer == null) {
          statement.bindNull(18)
        } else {
          statement.bindText(18, _tmpYoutubeTrailer)
        }
        val _tmpEpisodeRunTime: String? = entity.episodeRunTime
        if (_tmpEpisodeRunTime == null) {
          statement.bindNull(19)
        } else {
          statement.bindText(19, _tmpEpisodeRunTime)
        }
        statement.bindLong(20, entity.lastModified)
        val _tmp: Int = if (entity.isAdult) 1 else 0
        statement.bindLong(21, _tmp.toLong())
        statement.bindText(22, entity.syncFingerprint)
      }
    }
    this.__insertAdapterOfCategoryImportStageEntity = object : EntityInsertAdapter<CategoryImportStageEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `category_import_stage` (`session_id`,`provider_id`,`category_id`,`name`,`parent_id`,`type`,`is_adult`,`sync_fingerprint`) VALUES (?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CategoryImportStageEntity) {
        statement.bindLong(1, entity.sessionId)
        statement.bindLong(2, entity.providerId)
        statement.bindLong(3, entity.categoryId)
        statement.bindText(4, entity.name)
        val _tmpParentId: Long? = entity.parentId
        if (_tmpParentId == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpParentId)
        }
        val _tmp: String? = __roomEnumConverters.fromContentType(entity.type)
        if (_tmp == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmp)
        }
        val _tmp_1: Int = if (entity.isAdult) 1 else 0
        statement.bindLong(7, _tmp_1.toLong())
        statement.bindText(8, entity.syncFingerprint)
      }
    }
  }

  public override suspend fun insertChannelStages(rows: List<ChannelImportStageEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfChannelImportStageEntity.insert(_connection, rows)
  }

  public override suspend fun insertMovieStages(rows: List<MovieImportStageEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfMovieImportStageEntity.insert(_connection, rows)
  }

  public override suspend fun insertSeriesStages(rows: List<SeriesImportStageEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfSeriesImportStageEntity.insert(_connection, rows)
  }

  public override suspend fun insertCategoryStages(rows: List<CategoryImportStageEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfCategoryImportStageEntity.insert(_connection, rows)
  }

  public override suspend fun getCategoryStages(
    providerId: Long,
    sessionId: Long,
    type: String,
  ): List<CategoryImportStageEntity> {
    val _sql: String = "SELECT * FROM category_import_stage WHERE provider_id = ? AND session_id = ? AND type = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 3
        _stmt.bindText(_argIndex, type)
        val _columnIndexOfSessionId: Int = getColumnIndexOrThrow(_stmt, "session_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfParentId: Int = getColumnIndexOrThrow(_stmt, "parent_id")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _result: MutableList<CategoryImportStageEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CategoryImportStageEntity
          val _tmpSessionId: Long
          _tmpSessionId = _stmt.getLong(_columnIndexOfSessionId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpCategoryId: Long
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpParentId: Long?
          if (_stmt.isNull(_columnIndexOfParentId)) {
            _tmpParentId = null
          } else {
            _tmpParentId = _stmt.getLong(_columnIndexOfParentId)
          }
          val _tmpType: ContentType
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfType)
          }
          val _tmp_1: ContentType? = __roomEnumConverters.toContentType(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ContentType', but it was NULL.")
          } else {
            _tmpType = _tmp_1
          }
          val _tmpIsAdult: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_2 != 0
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          _item = CategoryImportStageEntity(_tmpSessionId,_tmpProviderId,_tmpCategoryId,_tmpName,_tmpParentId,_tmpType,_tmpIsAdult,_tmpSyncFingerprint)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getChannelStages(providerId: Long, sessionId: Long): List<ChannelImportStageEntity> {
    val _sql: String = "SELECT * FROM channel_import_stage WHERE provider_id = ? AND session_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
        val _columnIndexOfSessionId: Int = getColumnIndexOrThrow(_stmt, "session_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfLogoUrl: Int = getColumnIndexOrThrow(_stmt, "logo_url")
        val _columnIndexOfGroupTitle: Int = getColumnIndexOrThrow(_stmt, "group_title")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfEpgChannelId: Int = getColumnIndexOrThrow(_stmt, "epg_channel_id")
        val _columnIndexOfNumber: Int = getColumnIndexOrThrow(_stmt, "number")
        val _columnIndexOfCatchUpSupported: Int = getColumnIndexOrThrow(_stmt, "catch_up_supported")
        val _columnIndexOfCatchUpDays: Int = getColumnIndexOrThrow(_stmt, "catch_up_days")
        val _columnIndexOfCatchUpSource: Int = getColumnIndexOrThrow(_stmt, "catchUpSource")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfLogicalGroupId: Int = getColumnIndexOrThrow(_stmt, "logical_group_id")
        val _columnIndexOfErrorCount: Int = getColumnIndexOrThrow(_stmt, "error_count")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _result: MutableList<ChannelImportStageEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelImportStageEntity
          val _tmpSessionId: Long
          _tmpSessionId = _stmt.getLong(_columnIndexOfSessionId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
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
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          _item = ChannelImportStageEntity(_tmpSessionId,_tmpProviderId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpIsAdult,_tmpLogicalGroupId,_tmpErrorCount,_tmpSyncFingerprint)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getMovieStages(providerId: Long, sessionId: Long): List<MovieImportStageEntity> {
    val _sql: String = "SELECT * FROM movie_import_stage WHERE provider_id = ? AND session_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
        val _columnIndexOfSessionId: Int = getColumnIndexOrThrow(_stmt, "session_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfBackdropUrl: Int = getColumnIndexOrThrow(_stmt, "backdrop_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfPlot: Int = getColumnIndexOrThrow(_stmt, "plot")
        val _columnIndexOfCast: Int = getColumnIndexOrThrow(_stmt, "cast")
        val _columnIndexOfDirector: Int = getColumnIndexOrThrow(_stmt, "director")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDuration: Int = getColumnIndexOrThrow(_stmt, "duration")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfYoutubeTrailer: Int = getColumnIndexOrThrow(_stmt, "youtube_trailer")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieImportStageEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieImportStageEntity
          val _tmpSessionId: Long
          _tmpSessionId = _stmt.getLong(_columnIndexOfSessionId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpBackdropUrl: String?
          if (_stmt.isNull(_columnIndexOfBackdropUrl)) {
            _tmpBackdropUrl = null
          } else {
            _tmpBackdropUrl = _stmt.getText(_columnIndexOfBackdropUrl)
          }
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
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpContainerExtension: String?
          if (_stmt.isNull(_columnIndexOfContainerExtension)) {
            _tmpContainerExtension = null
          } else {
            _tmpContainerExtension = _stmt.getText(_columnIndexOfContainerExtension)
          }
          val _tmpPlot: String?
          if (_stmt.isNull(_columnIndexOfPlot)) {
            _tmpPlot = null
          } else {
            _tmpPlot = _stmt.getText(_columnIndexOfPlot)
          }
          val _tmpCast: String?
          if (_stmt.isNull(_columnIndexOfCast)) {
            _tmpCast = null
          } else {
            _tmpCast = _stmt.getText(_columnIndexOfCast)
          }
          val _tmpDirector: String?
          if (_stmt.isNull(_columnIndexOfDirector)) {
            _tmpDirector = null
          } else {
            _tmpDirector = _stmt.getText(_columnIndexOfDirector)
          }
          val _tmpGenre: String?
          if (_stmt.isNull(_columnIndexOfGenre)) {
            _tmpGenre = null
          } else {
            _tmpGenre = _stmt.getText(_columnIndexOfGenre)
          }
          val _tmpReleaseDate: String?
          if (_stmt.isNull(_columnIndexOfReleaseDate)) {
            _tmpReleaseDate = null
          } else {
            _tmpReleaseDate = _stmt.getText(_columnIndexOfReleaseDate)
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
          val _tmpYear: String?
          if (_stmt.isNull(_columnIndexOfYear)) {
            _tmpYear = null
          } else {
            _tmpYear = _stmt.getText(_columnIndexOfYear)
          }
          val _tmpTmdbId: Long?
          if (_stmt.isNull(_columnIndexOfTmdbId)) {
            _tmpTmdbId = null
          } else {
            _tmpTmdbId = _stmt.getLong(_columnIndexOfTmdbId)
          }
          val _tmpYoutubeTrailer: String?
          if (_stmt.isNull(_columnIndexOfYoutubeTrailer)) {
            _tmpYoutubeTrailer = null
          } else {
            _tmpYoutubeTrailer = _stmt.getText(_columnIndexOfYoutubeTrailer)
          }
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieImportStageEntity(_tmpSessionId,_tmpProviderId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpDuration,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpTmdbId,_tmpYoutubeTrailer,_tmpIsAdult,_tmpSyncFingerprint,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSeriesStages(providerId: Long, sessionId: Long): List<SeriesImportStageEntity> {
    val _sql: String = "SELECT * FROM series_import_stage WHERE provider_id = ? AND session_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
        val _columnIndexOfSessionId: Int = getColumnIndexOrThrow(_stmt, "session_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfProviderSeriesKey: Int = getColumnIndexOrThrow(_stmt, "provider_series_key")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfBackdropUrl: Int = getColumnIndexOrThrow(_stmt, "backdrop_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfPlot: Int = getColumnIndexOrThrow(_stmt, "plot")
        val _columnIndexOfCast: Int = getColumnIndexOrThrow(_stmt, "cast")
        val _columnIndexOfDirector: Int = getColumnIndexOrThrow(_stmt, "director")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfYoutubeTrailer: Int = getColumnIndexOrThrow(_stmt, "youtube_trailer")
        val _columnIndexOfEpisodeRunTime: Int = getColumnIndexOrThrow(_stmt, "episode_run_time")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _result: MutableList<SeriesImportStageEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesImportStageEntity
          val _tmpSessionId: Long
          _tmpSessionId = _stmt.getLong(_columnIndexOfSessionId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpProviderSeriesKey: String
          _tmpProviderSeriesKey = _stmt.getText(_columnIndexOfProviderSeriesKey)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
          }
          val _tmpBackdropUrl: String?
          if (_stmt.isNull(_columnIndexOfBackdropUrl)) {
            _tmpBackdropUrl = null
          } else {
            _tmpBackdropUrl = _stmt.getText(_columnIndexOfBackdropUrl)
          }
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
          val _tmpPlot: String?
          if (_stmt.isNull(_columnIndexOfPlot)) {
            _tmpPlot = null
          } else {
            _tmpPlot = _stmt.getText(_columnIndexOfPlot)
          }
          val _tmpCast: String?
          if (_stmt.isNull(_columnIndexOfCast)) {
            _tmpCast = null
          } else {
            _tmpCast = _stmt.getText(_columnIndexOfCast)
          }
          val _tmpDirector: String?
          if (_stmt.isNull(_columnIndexOfDirector)) {
            _tmpDirector = null
          } else {
            _tmpDirector = _stmt.getText(_columnIndexOfDirector)
          }
          val _tmpGenre: String?
          if (_stmt.isNull(_columnIndexOfGenre)) {
            _tmpGenre = null
          } else {
            _tmpGenre = _stmt.getText(_columnIndexOfGenre)
          }
          val _tmpReleaseDate: String?
          if (_stmt.isNull(_columnIndexOfReleaseDate)) {
            _tmpReleaseDate = null
          } else {
            _tmpReleaseDate = _stmt.getText(_columnIndexOfReleaseDate)
          }
          val _tmpRating: Float
          _tmpRating = _stmt.getDouble(_columnIndexOfRating).toFloat()
          val _tmpTmdbId: Long?
          if (_stmt.isNull(_columnIndexOfTmdbId)) {
            _tmpTmdbId = null
          } else {
            _tmpTmdbId = _stmt.getLong(_columnIndexOfTmdbId)
          }
          val _tmpYoutubeTrailer: String?
          if (_stmt.isNull(_columnIndexOfYoutubeTrailer)) {
            _tmpYoutubeTrailer = null
          } else {
            _tmpYoutubeTrailer = _stmt.getText(_columnIndexOfYoutubeTrailer)
          }
          val _tmpEpisodeRunTime: String?
          if (_stmt.isNull(_columnIndexOfEpisodeRunTime)) {
            _tmpEpisodeRunTime = null
          } else {
            _tmpEpisodeRunTime = _stmt.getText(_columnIndexOfEpisodeRunTime)
          }
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          _item = SeriesImportStageEntity(_tmpSessionId,_tmpProviderId,_tmpSeriesId,_tmpProviderSeriesId,_tmpProviderSeriesKey,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpYoutubeTrailer,_tmpEpisodeRunTime,_tmpLastModified,_tmpIsAdult,_tmpSyncFingerprint)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun countChannelStages(providerId: Long, sessionId: Long): Int {
    val _sql: String = "SELECT COUNT(*) FROM channel_import_stage WHERE provider_id = ? AND session_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
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

  public override suspend fun countMovieStages(providerId: Long, sessionId: Long): Int {
    val _sql: String = "SELECT COUNT(*) FROM movie_import_stage WHERE provider_id = ? AND session_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
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

  public override suspend fun countSeriesStages(providerId: Long, sessionId: Long): Int {
    val _sql: String = "SELECT COUNT(*) FROM series_import_stage WHERE provider_id = ? AND session_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
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

  public override suspend fun getChannelStageCategorySummaries(providerId: Long, sessionId: Long): List<ChannelStageCategorySummary> {
    val _sql: String = """
        |
        |      SELECT
        |        category_id AS categoryId,
        |        COALESCE(
        |          MIN(CASE WHEN category_name IS NOT NULL AND TRIM(category_name) != '' THEN category_name END),
        |          'Category ' || category_id
        |        ) AS name,
        |        MAX(CASE WHEN is_adult THEN 1 ELSE 0 END) AS isAdult
        |      FROM channel_import_stage
        |      WHERE provider_id = ?
        |        AND session_id = ?
        |        AND category_id IS NOT NULL
        |      GROUP BY category_id
        |      
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
        val _columnIndexOfCategoryId: Int = 0
        val _columnIndexOfName: Int = 1
        val _columnIndexOfIsAdult: Int = 2
        val _result: MutableList<ChannelStageCategorySummary> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelStageCategorySummary
          val _tmpCategoryId: Long
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          _item = ChannelStageCategorySummary(_tmpCategoryId,_tmpName,_tmpIsAdult)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearChannelStages(providerId: Long, sessionId: Long) {
    val _sql: String = "DELETE FROM channel_import_stage WHERE provider_id = ? AND session_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearMovieStages(providerId: Long, sessionId: Long) {
    val _sql: String = "DELETE FROM movie_import_stage WHERE provider_id = ? AND session_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearSeriesStages(providerId: Long, sessionId: Long) {
    val _sql: String = "DELETE FROM series_import_stage WHERE provider_id = ? AND session_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearCategoryStages(providerId: Long, sessionId: Long) {
    val _sql: String = "DELETE FROM category_import_stage WHERE provider_id = ? AND session_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearProviderChannelStages(providerId: Long) {
    val _sql: String = "DELETE FROM channel_import_stage WHERE provider_id = ?"
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

  public override suspend fun clearProviderMovieStages(providerId: Long) {
    val _sql: String = "DELETE FROM movie_import_stage WHERE provider_id = ?"
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

  public override suspend fun clearProviderSeriesStages(providerId: Long) {
    val _sql: String = "DELETE FROM series_import_stage WHERE provider_id = ?"
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

  public override suspend fun clearProviderCategoryStages(providerId: Long) {
    val _sql: String = "DELETE FROM category_import_stage WHERE provider_id = ?"
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

  public override suspend fun updateChangedCategoriesFromStage(
    providerId: Long,
    sessionId: Long,
    type: String,
  ) {
    val _sql: String = """
        |
        |        UPDATE categories
        |        SET name = (
        |                SELECT stage.name
        |                FROM category_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.type = ?
        |                  AND stage.category_id = categories.category_id
        |            ),
        |            parent_id = (
        |                SELECT stage.parent_id
        |                FROM category_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.type = ?
        |                  AND stage.category_id = categories.category_id
        |            ),
        |            is_adult = (
        |                SELECT stage.is_adult
        |                FROM category_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.type = ?
        |                  AND stage.category_id = categories.category_id
        |            ),
        |            sync_fingerprint = (
        |                SELECT stage.sync_fingerprint
        |                FROM category_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.type = ?
        |                  AND stage.category_id = categories.category_id
        |            )
        |        WHERE provider_id = ?
        |          AND type = ?
        |          AND EXISTS (
        |              SELECT 1
        |              FROM category_import_stage AS stage
        |              WHERE stage.session_id = ?
        |                AND stage.provider_id = ?
        |                AND stage.type = ?
        |                AND stage.category_id = categories.category_id
        |                AND categories.sync_fingerprint != stage.sync_fingerprint
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 3
        _stmt.bindText(_argIndex, type)
        _argIndex = 4
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 5
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 6
        _stmt.bindText(_argIndex, type)
        _argIndex = 7
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 8
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 9
        _stmt.bindText(_argIndex, type)
        _argIndex = 10
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 11
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 12
        _stmt.bindText(_argIndex, type)
        _argIndex = 13
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 14
        _stmt.bindText(_argIndex, type)
        _argIndex = 15
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 16
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 17
        _stmt.bindText(_argIndex, type)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun insertMissingCategoriesFromStage(
    providerId: Long,
    sessionId: Long,
    type: String,
  ) {
    val _sql: String = """
        |
        |        INSERT INTO categories (
        |            category_id,
        |            name,
        |            parent_id,
        |            type,
        |            provider_id,
        |            is_adult,
        |            is_user_protected,
        |            sync_fingerprint
        |        )
        |        SELECT
        |            stage.category_id,
        |            stage.name,
        |            stage.parent_id,
        |            stage.type,
        |            stage.provider_id,
        |            stage.is_adult,
        |            0,
        |            stage.sync_fingerprint
        |        FROM category_import_stage AS stage
        |        WHERE stage.session_id = ?
        |          AND stage.provider_id = ?
        |          AND stage.type = ?
        |          AND NOT EXISTS (
        |              SELECT 1
        |              FROM categories AS existing
        |              WHERE existing.provider_id = stage.provider_id
        |                AND existing.type = stage.type
        |                AND existing.category_id = stage.category_id
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 3
        _stmt.bindText(_argIndex, type)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteStaleCategoriesForStage(
    providerId: Long,
    sessionId: Long,
    type: String,
  ) {
    val _sql: String = """
        |
        |        DELETE FROM categories
        |        WHERE provider_id = ?
        |          AND type = ?
        |          AND NOT EXISTS (
        |              SELECT 1
        |              FROM category_import_stage AS stage
        |              WHERE stage.session_id = ?
        |                AND stage.provider_id = ?
        |                AND stage.type = ?
        |                AND stage.category_id = categories.category_id
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, type)
        _argIndex = 3
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 4
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 5
        _stmt.bindText(_argIndex, type)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateChangedChannelsFromStage(providerId: Long, sessionId: Long) {
    val _sql: String = """
        |
        |        UPDATE channels
        |        SET name = (
        |                SELECT stage.name
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            ),
        |            logo_url = (
        |                SELECT stage.logo_url
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            ),
        |            group_title = (
        |                SELECT stage.group_title
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            ),
        |            category_id = (
        |                SELECT stage.category_id
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            ),
        |            category_name = (
        |                SELECT stage.category_name
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            ),
        |            stream_url = (
        |                SELECT stage.stream_url
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            ),
        |            epg_channel_id = (
        |                SELECT stage.epg_channel_id
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            ),
        |            number = (
        |                SELECT stage.number
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            ),
        |            catch_up_supported = (
        |                SELECT stage.catch_up_supported
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            ),
        |            catch_up_days = (
        |                SELECT stage.catch_up_days
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            ),
        |            catchUpSource = (
        |                SELECT stage.catchUpSource
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            ),
        |            logical_group_id = (
        |                SELECT stage.logical_group_id
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            ),
        |            error_count = (
        |                SELECT stage.error_count
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            ),
        |            is_adult = (
        |                SELECT stage.is_adult
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            ),
        |            sync_fingerprint = (
        |                SELECT stage.sync_fingerprint
        |                FROM channel_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = channels.stream_id
        |            )
        |        WHERE provider_id = ?
        |          AND EXISTS (
        |              SELECT 1
        |              FROM channel_import_stage AS stage
        |              WHERE stage.session_id = ?
        |                AND stage.provider_id = ?
        |                AND stage.stream_id = channels.stream_id
        |                AND channels.sync_fingerprint != stage.sync_fingerprint
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 4
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 5
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 6
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 7
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 8
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 9
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 10
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 11
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 12
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 13
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 14
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 15
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 16
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 17
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 18
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 19
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 20
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 21
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 22
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 23
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 24
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 25
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 26
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 27
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 28
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 29
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 30
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 31
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 32
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 33
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun insertMissingChannelsFromStage(providerId: Long, sessionId: Long) {
    val _sql: String = """
        |
        |        INSERT INTO channels (
        |            stream_id,
        |            name,
        |            logo_url,
        |            group_title,
        |            category_id,
        |            category_name,
        |            stream_url,
        |            epg_channel_id,
        |            number,
        |            catch_up_supported,
        |            catch_up_days,
        |            catchUpSource,
        |            logical_group_id,
        |            error_count,
        |            provider_id,
        |            is_adult,
        |            is_user_protected,
        |            sync_fingerprint
        |        )
        |        SELECT
        |            stage.stream_id,
        |            stage.name,
        |            stage.logo_url,
        |            stage.group_title,
        |            stage.category_id,
        |            stage.category_name,
        |            stage.stream_url,
        |            stage.epg_channel_id,
        |            stage.number,
        |            stage.catch_up_supported,
        |            stage.catch_up_days,
        |            stage.catchUpSource,
        |            stage.logical_group_id,
        |            stage.error_count,
        |            stage.provider_id,
        |            stage.is_adult,
        |            0,
        |            stage.sync_fingerprint
        |        FROM channel_import_stage AS stage
        |        WHERE stage.session_id = ?
        |          AND stage.provider_id = ?
        |          AND NOT EXISTS (
        |              SELECT 1
        |              FROM channels AS existing
        |              WHERE existing.provider_id = stage.provider_id
        |                AND existing.stream_id = stage.stream_id
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteStaleChannelsForStage(providerId: Long, sessionId: Long) {
    val _sql: String = """
        |
        |        DELETE FROM channels
        |        WHERE provider_id = ?
        |          AND NOT EXISTS (
        |              SELECT 1
        |              FROM channel_import_stage AS stage
        |              WHERE stage.session_id = ?
        |                AND stage.provider_id = ?
        |                AND stage.stream_id = channels.stream_id
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateChangedMoviesFromStage(providerId: Long, sessionId: Long) {
    val _sql: String = """
        |
        |        UPDATE movies
        |        SET name = (
        |                SELECT stage.name
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            poster_url = (
        |                SELECT stage.poster_url
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            backdrop_url = (
        |                SELECT stage.backdrop_url
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            category_id = (
        |                SELECT stage.category_id
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            category_name = (
        |                SELECT stage.category_name
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            stream_url = (
        |                SELECT stage.stream_url
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            container_extension = (
        |                SELECT stage.container_extension
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            plot = (
        |                SELECT stage.plot
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            cast = (
        |                SELECT stage.cast
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            director = (
        |                SELECT stage.director
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            genre = (
        |                SELECT stage.genre
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            release_date = (
        |                SELECT stage.release_date
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            duration = (
        |                SELECT stage.duration
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            duration_seconds = (
        |                SELECT stage.duration_seconds
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            rating = (
        |                SELECT stage.rating
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            year = (
        |                SELECT stage.year
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            tmdb_id = (
        |                SELECT stage.tmdb_id
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            youtube_trailer = (
        |                SELECT stage.youtube_trailer
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            is_adult = (
        |                SELECT stage.is_adult
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            ),
        |            sync_fingerprint = (
        |                SELECT stage.sync_fingerprint
        |                FROM movie_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.stream_id = movies.stream_id
        |            )
        |        WHERE provider_id = ?
        |          AND EXISTS (
        |              SELECT 1
        |              FROM movie_import_stage AS stage
        |              WHERE stage.session_id = ?
        |                AND stage.provider_id = ?
        |                AND stage.stream_id = movies.stream_id
        |                AND movies.sync_fingerprint != stage.sync_fingerprint
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 4
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 5
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 6
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 7
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 8
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 9
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 10
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 11
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 12
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 13
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 14
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 15
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 16
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 17
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 18
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 19
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 20
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 21
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 22
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 23
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 24
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 25
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 26
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 27
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 28
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 29
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 30
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 31
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 32
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 33
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 34
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 35
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 36
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 37
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 38
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 39
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 40
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 41
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 42
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 43
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun insertMissingMoviesFromStage(providerId: Long, sessionId: Long) {
    val _sql: String = """
        |
        |        INSERT INTO movies (
        |            stream_id,
        |            name,
        |            poster_url,
        |            backdrop_url,
        |            category_id,
        |            category_name,
        |            stream_url,
        |            container_extension,
        |            plot,
        |            cast,
        |            director,
        |            genre,
        |            release_date,
        |            duration,
        |            duration_seconds,
        |            rating,
        |            year,
        |            tmdb_id,
        |            youtube_trailer,
        |            provider_id,
        |            watch_progress,
        |            watch_count,
        |            last_watched_at,
        |            is_adult,
        |            is_user_protected,
        |            sync_fingerprint,
        |            added_at
        |        )
        |        SELECT
        |            stage.stream_id,
        |            stage.name,
        |            stage.poster_url,
        |            stage.backdrop_url,
        |            stage.category_id,
        |            stage.category_name,
        |            stage.stream_url,
        |            stage.container_extension,
        |            stage.plot,
        |            stage.cast,
        |            stage.director,
        |            stage.genre,
        |            stage.release_date,
        |            stage.duration,
        |            stage.duration_seconds,
        |            stage.rating,
        |            stage.year,
        |            stage.tmdb_id,
        |            stage.youtube_trailer,
        |            stage.provider_id,
        |            0,
        |            0,
        |            0,
        |            stage.is_adult,
        |            0,
        |            stage.sync_fingerprint,
        |            stage.added_at
        |        FROM movie_import_stage AS stage
        |        WHERE stage.session_id = ?
        |          AND stage.provider_id = ?
        |          AND NOT EXISTS (
        |              SELECT 1
        |              FROM movies AS existing
        |              WHERE existing.provider_id = stage.provider_id
        |                AND existing.stream_id = stage.stream_id
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteStaleMoviesForStage(providerId: Long, sessionId: Long) {
    val _sql: String = """
        |
        |        DELETE FROM movies
        |        WHERE provider_id = ?
        |          AND NOT EXISTS (
        |              SELECT 1
        |              FROM movie_import_stage AS stage
        |              WHERE stage.session_id = ?
        |                AND stage.provider_id = ?
        |                AND stage.stream_id = movies.stream_id
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateChangedSeriesFromStage(providerId: Long, sessionId: Long) {
    val _sql: String = """
        |
        |        UPDATE series
        |        SET name = (
        |                SELECT stage.name
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            poster_url = (
        |                SELECT stage.poster_url
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            backdrop_url = (
        |                SELECT stage.backdrop_url
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            category_id = (
        |                SELECT stage.category_id
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            category_name = (
        |                SELECT stage.category_name
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            plot = (
        |                SELECT stage.plot
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            cast = (
        |                SELECT stage.cast
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            director = (
        |                SELECT stage.director
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            genre = (
        |                SELECT stage.genre
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            release_date = (
        |                SELECT stage.release_date
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            rating = (
        |                SELECT stage.rating
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            tmdb_id = (
        |                SELECT stage.tmdb_id
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            youtube_trailer = (
        |                SELECT stage.youtube_trailer
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            episode_run_time = (
        |                SELECT stage.episode_run_time
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            last_modified = (
        |                SELECT stage.last_modified
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            provider_series_id = (
        |                SELECT stage.provider_series_id
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            is_adult = (
        |                SELECT stage.is_adult
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            ),
        |            sync_fingerprint = (
        |                SELECT stage.sync_fingerprint
        |                FROM series_import_stage AS stage
        |                WHERE stage.session_id = ?
        |                  AND stage.provider_id = ?
        |                  AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |            )
        |        WHERE provider_id = ?
        |          AND EXISTS (
        |              SELECT 1
        |              FROM series_import_stage AS stage
        |              WHERE stage.session_id = ?
        |                AND stage.provider_id = ?
        |                AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |                AND series.sync_fingerprint != stage.sync_fingerprint
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 4
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 5
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 6
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 7
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 8
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 9
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 10
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 11
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 12
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 13
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 14
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 15
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 16
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 17
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 18
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 19
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 20
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 21
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 22
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 23
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 24
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 25
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 26
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 27
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 28
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 29
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 30
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 31
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 32
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 33
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 34
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 35
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 36
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 37
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 38
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 39
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun insertMissingSeriesFromStage(providerId: Long, sessionId: Long) {
    val _sql: String = """
        |
        |        INSERT INTO series (
        |            series_id,
        |            provider_series_id,
        |            name,
        |            poster_url,
        |            backdrop_url,
        |            category_id,
        |            category_name,
        |            plot,
        |            cast,
        |            director,
        |            genre,
        |            release_date,
        |            rating,
        |            tmdb_id,
        |            youtube_trailer,
        |            episode_run_time,
        |            last_modified,
        |            provider_id,
        |            is_adult,
        |            is_user_protected,
        |            sync_fingerprint
        |        )
        |        SELECT
        |            stage.series_id,
        |            stage.provider_series_id,
        |            stage.name,
        |            stage.poster_url,
        |            stage.backdrop_url,
        |            stage.category_id,
        |            stage.category_name,
        |            stage.plot,
        |            stage.cast,
        |            stage.director,
        |            stage.genre,
        |            stage.release_date,
        |            stage.rating,
        |            stage.tmdb_id,
        |            stage.youtube_trailer,
        |            stage.episode_run_time,
        |            stage.last_modified,
        |            stage.provider_id,
        |            stage.is_adult,
        |            0,
        |            stage.sync_fingerprint
        |        FROM series_import_stage AS stage
        |        WHERE stage.session_id = ?
        |          AND stage.provider_id = ?
        |          AND NOT EXISTS (
        |              SELECT 1
        |              FROM series AS existing
        |              WHERE existing.provider_id = stage.provider_id
        |                AND COALESCE(NULLIF(existing.provider_series_id, ''), CAST(existing.series_id AS TEXT)) = stage.provider_series_key
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteStaleSeriesForStage(providerId: Long, sessionId: Long) {
    val _sql: String = """
        |
        |        DELETE FROM series
        |        WHERE provider_id = ?
        |          AND NOT EXISTS (
        |              SELECT 1
        |              FROM series_import_stage AS stage
        |              WHERE stage.session_id = ?
        |                AND stage.provider_id = ?
        |                AND stage.provider_series_key = COALESCE(NULLIF(series.provider_series_id, ''), CAST(series.series_id AS TEXT))
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sessionId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun rebuildChannelFts() {
    val _sql: String = "INSERT INTO channels_fts(channels_fts) VALUES('rebuild')"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun rebuildMovieFts() {
    val _sql: String = "INSERT INTO movies_fts(movies_fts) VALUES('rebuild')"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun rebuildSeriesFts() {
    val _sql: String = "INSERT INTO series_fts(series_fts) VALUES('rebuild')"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
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
