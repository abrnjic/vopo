package com.vopo.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.CategoryCount
import com.vopo.`data`.local.entity.SeriesBrowseEntity
import com.vopo.`data`.local.entity.SeriesEntity
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
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class SeriesDao_Impl(
  __db: RoomDatabase,
) : SeriesDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfSeriesEntity: EntityInsertAdapter<SeriesEntity>

  private val __updateAdapterOfSeriesEntity: EntityDeleteOrUpdateAdapter<SeriesEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfSeriesEntity = object : EntityInsertAdapter<SeriesEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `series` (`id`,`series_id`,`provider_series_id`,`name`,`poster_url`,`backdrop_url`,`category_id`,`category_name`,`plot`,`cast`,`director`,`genre`,`release_date`,`rating`,`tmdb_id`,`youtube_trailer`,`episode_run_time`,`last_modified`,`provider_id`,`is_adult`,`is_user_protected`,`sync_fingerprint`,`cache_state`,`detail_hydrated_at`,`remote_stale_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SeriesEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.seriesId)
        val _tmpProviderSeriesId: String? = entity.providerSeriesId
        if (_tmpProviderSeriesId == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpProviderSeriesId)
        }
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
        val _tmpPlot: String? = entity.plot
        if (_tmpPlot == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpPlot)
        }
        val _tmpCast: String? = entity.cast
        if (_tmpCast == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpCast)
        }
        val _tmpDirector: String? = entity.director
        if (_tmpDirector == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpDirector)
        }
        val _tmpGenre: String? = entity.genre
        if (_tmpGenre == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpGenre)
        }
        val _tmpReleaseDate: String? = entity.releaseDate
        if (_tmpReleaseDate == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpReleaseDate)
        }
        statement.bindDouble(14, entity.rating.toDouble())
        val _tmpTmdbId: Long? = entity.tmdbId
        if (_tmpTmdbId == null) {
          statement.bindNull(15)
        } else {
          statement.bindLong(15, _tmpTmdbId)
        }
        val _tmpYoutubeTrailer: String? = entity.youtubeTrailer
        if (_tmpYoutubeTrailer == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpYoutubeTrailer)
        }
        val _tmpEpisodeRunTime: String? = entity.episodeRunTime
        if (_tmpEpisodeRunTime == null) {
          statement.bindNull(17)
        } else {
          statement.bindText(17, _tmpEpisodeRunTime)
        }
        statement.bindLong(18, entity.lastModified)
        statement.bindLong(19, entity.providerId)
        val _tmp: Int = if (entity.isAdult) 1 else 0
        statement.bindLong(20, _tmp.toLong())
        val _tmp_1: Int = if (entity.isUserProtected) 1 else 0
        statement.bindLong(21, _tmp_1.toLong())
        statement.bindText(22, entity.syncFingerprint)
        statement.bindText(23, entity.cacheState)
        statement.bindLong(24, entity.detailHydratedAt)
        statement.bindLong(25, entity.remoteStaleAt)
      }
    }
    this.__updateAdapterOfSeriesEntity = object : EntityDeleteOrUpdateAdapter<SeriesEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `series` SET `id` = ?,`series_id` = ?,`provider_series_id` = ?,`name` = ?,`poster_url` = ?,`backdrop_url` = ?,`category_id` = ?,`category_name` = ?,`plot` = ?,`cast` = ?,`director` = ?,`genre` = ?,`release_date` = ?,`rating` = ?,`tmdb_id` = ?,`youtube_trailer` = ?,`episode_run_time` = ?,`last_modified` = ?,`provider_id` = ?,`is_adult` = ?,`is_user_protected` = ?,`sync_fingerprint` = ?,`cache_state` = ?,`detail_hydrated_at` = ?,`remote_stale_at` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: SeriesEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.seriesId)
        val _tmpProviderSeriesId: String? = entity.providerSeriesId
        if (_tmpProviderSeriesId == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpProviderSeriesId)
        }
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
        val _tmpPlot: String? = entity.plot
        if (_tmpPlot == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpPlot)
        }
        val _tmpCast: String? = entity.cast
        if (_tmpCast == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpCast)
        }
        val _tmpDirector: String? = entity.director
        if (_tmpDirector == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpDirector)
        }
        val _tmpGenre: String? = entity.genre
        if (_tmpGenre == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpGenre)
        }
        val _tmpReleaseDate: String? = entity.releaseDate
        if (_tmpReleaseDate == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpReleaseDate)
        }
        statement.bindDouble(14, entity.rating.toDouble())
        val _tmpTmdbId: Long? = entity.tmdbId
        if (_tmpTmdbId == null) {
          statement.bindNull(15)
        } else {
          statement.bindLong(15, _tmpTmdbId)
        }
        val _tmpYoutubeTrailer: String? = entity.youtubeTrailer
        if (_tmpYoutubeTrailer == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpYoutubeTrailer)
        }
        val _tmpEpisodeRunTime: String? = entity.episodeRunTime
        if (_tmpEpisodeRunTime == null) {
          statement.bindNull(17)
        } else {
          statement.bindText(17, _tmpEpisodeRunTime)
        }
        statement.bindLong(18, entity.lastModified)
        statement.bindLong(19, entity.providerId)
        val _tmp: Int = if (entity.isAdult) 1 else 0
        statement.bindLong(20, _tmp.toLong())
        val _tmp_1: Int = if (entity.isUserProtected) 1 else 0
        statement.bindLong(21, _tmp_1.toLong())
        statement.bindText(22, entity.syncFingerprint)
        statement.bindText(23, entity.cacheState)
        statement.bindLong(24, entity.detailHydratedAt)
        statement.bindLong(25, entity.remoteStaleAt)
        statement.bindLong(26, entity.id)
      }
    }
  }

  public override suspend fun insertAll(series: List<SeriesEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfSeriesEntity.insert(_connection, series)
  }

  public override suspend fun update(series: SeriesEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfSeriesEntity.handle(_connection, series)
  }

  public override suspend fun updateAll(series: List<SeriesEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfSeriesEntity.handleMultiple(_connection, series)
  }

  public override suspend fun replaceAll(providerId: Long, series: List<SeriesEntity>): Unit = performInTransactionSuspending(__db) {
    super@SeriesDao_Impl.replaceAll(providerId, series)
  }

  public override suspend fun replaceCategory(
    providerId: Long,
    categoryId: Long,
    series: List<SeriesEntity>,
  ): Unit = performInTransactionSuspending(__db) {
    super@SeriesDao_Impl.replaceCategory(providerId, categoryId, series)
  }

  public override suspend fun upsertCategoryPage(providerId: Long, series: List<SeriesEntity>): Unit = performInTransactionSuspending(__db) {
    super@SeriesDao_Impl.upsertCategoryPage(providerId, series)
  }

  public override fun getByProvider(providerId: Long): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? ORDER BY last_modified DESC, name ASC, id ASC)"
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByProviderPage(
    providerId: Long,
    limit: Int,
    offset: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? ORDER BY last_modified DESC, name ASC, id ASC LIMIT ? OFFSET ?)"
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderCursorPage(providerId: Long, limit: Int): List<SeriesBrowseEntity> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? ORDER BY name ASC, id ASC LIMIT ?)"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderCursorPageAfter(
    providerId: Long,
    lastName: String,
    lastId: Long,
    limit: Int,
  ): List<SeriesBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT * FROM series
        |        WHERE provider_id = ?
        |          AND (name > ? OR (name = ? AND id > ?))
        |        ORDER BY name ASC, id ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 3
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 4
        _stmt.bindLong(_argIndex, lastId)
        _argIndex = 5
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getFavoritesByProviderPage(
    providerId: Long,
    limit: Int,
    offset: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT series.* FROM series
        |        WHERE series.provider_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM favorites
        |              WHERE favorites.content_type = 'SERIES'
        |                                AND favorites.provider_id = series.provider_id
        |                AND favorites.group_id IS NULL
        |                AND favorites.content_id = series.id
        |          )
        |        ORDER BY series.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "favorites")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getFavoriteCountByProvider(providerId: Long): Flow<Int> {
    val _sql: String = """
        |
        |        SELECT COUNT(*)
        |        FROM series
        |        WHERE provider_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM favorites
        |              WHERE favorites.content_type = 'SERIES'
        |                                AND favorites.provider_id = series.provider_id
        |                AND favorites.group_id IS NULL
        |                AND favorites.content_id = series.id
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "favorites")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
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

  public override fun getInProgressByProviderPage(
    providerId: Long,
    limit: Int,
    offset: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT series.* FROM series
        |        WHERE series.provider_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM playback_history
        |              WHERE playback_history.provider_id = series.provider_id
        |                AND playback_history.resume_position_ms > 0
        |                AND (
        |                    playback_history.total_duration_ms <= 0
        |                    OR playback_history.resume_position_ms < CAST(playback_history.total_duration_ms * 0.95 AS INTEGER)
        |                )
        |                AND (
        |                    (playback_history.content_type = 'SERIES' AND playback_history.content_id = series.id)
        |                    OR (
        |                        playback_history.content_type = 'SERIES_EPISODE'
        |                        AND EXISTS (
        |                            SELECT 1 FROM episodes
        |                            WHERE episodes.id = playback_history.content_id
        |                              AND episodes.series_id = series.id
        |                        )
        |                    )
        |                )
        |          )
        |        ORDER BY series.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "playback_history", "episodes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getInProgressCountByProvider(providerId: Long): Flow<Int> {
    val _sql: String = """
        |
        |        SELECT COUNT(*)
        |        FROM series
        |        WHERE provider_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM playback_history
        |              WHERE playback_history.provider_id = series.provider_id
        |                AND playback_history.resume_position_ms > 0
        |                AND (
        |                    playback_history.total_duration_ms <= 0
        |                    OR playback_history.resume_position_ms < CAST(playback_history.total_duration_ms * 0.95 AS INTEGER)
        |                )
        |                AND (
        |                    (playback_history.content_type = 'SERIES' AND playback_history.content_id = series.id)
        |                    OR (
        |                        playback_history.content_type = 'SERIES_EPISODE'
        |                        AND EXISTS (
        |                            SELECT 1 FROM episodes
        |                            WHERE episodes.id = playback_history.content_id
        |                              AND episodes.series_id = series.id
        |                        )
        |                    )
        |                )
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "playback_history", "episodes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
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

  public override fun getUnwatchedByProviderPage(
    providerId: Long,
    limit: Int,
    offset: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT series.* FROM series
        |        WHERE series.provider_id = ?
        |          AND (
        |              NOT EXISTS (
        |                  SELECT 1 FROM episodes
        |                  WHERE episodes.series_id = series.id
        |                    AND episodes.provider_id = series.provider_id
        |              )
        |              OR EXISTS (
        |                  SELECT 1 FROM episodes e
        |                  WHERE e.series_id = series.id
        |                    AND e.provider_id = series.provider_id
        |                    AND NOT EXISTS (
        |                        SELECT 1 FROM playback_history ph
        |                        WHERE ph.content_id = e.id
        |                          AND ph.content_type = 'SERIES_EPISODE'
        |                          AND ph.provider_id = series.provider_id
        |                          AND ph.total_duration_ms > 0
        |                          AND ph.resume_position_ms >= CAST(ph.total_duration_ms * 0.95 AS INTEGER)
        |                    )
        |              )
        |          )
        |        ORDER BY series.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "episodes", "playback_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getUnwatchedCountByProvider(providerId: Long): Flow<Int> {
    val _sql: String = """
        |
        |        SELECT COUNT(*)
        |        FROM series
        |        WHERE provider_id = ?
        |          AND (
        |              NOT EXISTS (
        |                  SELECT 1 FROM episodes
        |                  WHERE episodes.series_id = series.id
        |                    AND episodes.provider_id = ?
        |              )
        |              OR EXISTS (
        |                  SELECT 1 FROM episodes e
        |                  WHERE e.series_id = series.id
        |                    AND e.provider_id = ?
        |                    AND NOT EXISTS (
        |                        SELECT 1 FROM playback_history ph
        |                        WHERE ph.content_id = e.id
        |                          AND ph.content_type = 'SERIES_EPISODE'
        |                          AND ph.provider_id = ?
        |                          AND ph.total_duration_ms > 0
        |                          AND ph.resume_position_ms >= CAST(ph.total_duration_ms * 0.95 AS INTEGER)
        |                    )
        |              )
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "episodes", "playback_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 4
        _stmt.bindLong(_argIndex, providerId)
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

  public override fun getByWatchCountProviderPage(
    providerId: Long,
    limit: Int,
    offset: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT series.* FROM series
        |        WHERE series.provider_id = ?
        |        ORDER BY COALESCE((
        |            SELECT MAX(playback_history.watch_count)
        |            FROM playback_history
        |            WHERE playback_history.provider_id = series.provider_id
        |              AND (
        |                  (playback_history.content_type = 'SERIES' AND playback_history.content_id = series.id)
        |                  OR (
        |                      playback_history.content_type = 'SERIES_EPISODE'
        |                      AND EXISTS (
        |                          SELECT 1 FROM episodes
        |                          WHERE episodes.id = playback_history.content_id
        |                            AND episodes.series_id = series.id
        |                      )
        |                  )
        |              )
        |        ), 0) DESC, series.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "playback_history", "episodes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByWatchCountProviderCursorPage(providerId: Long, limit: Int): List<SeriesBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT series.* FROM series
        |        WHERE series.provider_id = ?
        |        ORDER BY COALESCE((
        |            SELECT MAX(playback_history.watch_count)
        |            FROM playback_history
        |            WHERE playback_history.provider_id = series.provider_id
        |              AND (
        |                  (playback_history.content_type = 'SERIES' AND playback_history.content_id = series.id)
        |                  OR (
        |                      playback_history.content_type = 'SERIES_EPISODE'
        |                      AND EXISTS (
        |                          SELECT 1 FROM episodes
        |                          WHERE episodes.id = playback_history.content_id
        |                            AND episodes.series_id = series.id
        |                      )
        |                  )
        |              )
        |        ), 0) DESC, series.name ASC, series.id ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByWatchCountProviderCursorPageAfter(
    providerId: Long,
    lastWatchCount: Int,
    lastName: String,
    lastId: Long,
    limit: Int,
  ): List<SeriesBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT series.* FROM series
        |        WHERE series.provider_id = ?
        |          AND (
        |              COALESCE((
        |                  SELECT MAX(playback_history.watch_count)
        |                  FROM playback_history
        |                  WHERE playback_history.provider_id = series.provider_id
        |                    AND (
        |                        (playback_history.content_type = 'SERIES' AND playback_history.content_id = series.id)
        |                        OR (
        |                            playback_history.content_type = 'SERIES_EPISODE'
        |                            AND EXISTS (
        |                                SELECT 1 FROM episodes
        |                                WHERE episodes.id = playback_history.content_id
        |                                  AND episodes.series_id = series.id
        |                            )
        |                        )
        |                    )
        |              ), 0) < ?
        |              OR (
        |                  COALESCE((
        |                      SELECT MAX(playback_history.watch_count)
        |                      FROM playback_history
        |                      WHERE playback_history.provider_id = series.provider_id
        |                        AND (
        |                            (playback_history.content_type = 'SERIES' AND playback_history.content_id = series.id)
        |                            OR (
        |                                playback_history.content_type = 'SERIES_EPISODE'
        |                                AND EXISTS (
        |                                    SELECT 1 FROM episodes
        |                                    WHERE episodes.id = playback_history.content_id
        |                                      AND episodes.series_id = series.id
        |                                )
        |                            )
        |                        )
        |                  ), 0) = ?
        |                  AND (series.name > ? OR (series.name = ? AND series.id > ?))
        |              )
        |          )
        |        ORDER BY COALESCE((
        |            SELECT MAX(playback_history.watch_count)
        |            FROM playback_history
        |            WHERE playback_history.provider_id = series.provider_id
        |              AND (
        |                  (playback_history.content_type = 'SERIES' AND playback_history.content_id = series.id)
        |                  OR (
        |                      playback_history.content_type = 'SERIES_EPISODE'
        |                      AND EXISTS (
        |                          SELECT 1 FROM episodes
        |                          WHERE episodes.id = playback_history.content_id
        |                            AND episodes.series_id = series.id
        |                      )
        |                  )
        |              )
        |        ), 0) DESC, series.name ASC, series.id ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, lastWatchCount.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, lastWatchCount.toLong())
        _argIndex = 4
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 5
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 6
        _stmt.bindLong(_argIndex, lastId)
        _argIndex = 7
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByCategory(providerId: Long, categoryId: Long): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? AND category_id = ? ORDER BY last_modified DESC, name ASC, id ASC)"
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getFavoritesByCategoryPage(
    providerId: Long,
    categoryId: Long,
    limit: Int,
    offset: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT series.* FROM series
        |        WHERE series.provider_id = ?
        |          AND series.category_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM favorites
        |              WHERE favorites.content_type = 'SERIES'
        |                                AND favorites.provider_id = series.provider_id
        |                AND favorites.group_id IS NULL
        |                AND favorites.content_id = series.id
        |          )
        |        ORDER BY series.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "favorites")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getFavoriteCountByCategory(providerId: Long, categoryId: Long): Flow<Int> {
    val _sql: String = """
        |
        |        SELECT COUNT(*)
        |        FROM series
        |        WHERE provider_id = ?
        |          AND category_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM favorites
        |              WHERE favorites.content_type = 'SERIES'
        |                                AND favorites.provider_id = series.provider_id
        |                AND favorites.group_id IS NULL
        |                AND favorites.content_id = series.id
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "favorites")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
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

  public override fun getInProgressByCategoryPage(
    providerId: Long,
    categoryId: Long,
    limit: Int,
    offset: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT series.* FROM series
        |        WHERE series.provider_id = ?
        |          AND series.category_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM playback_history
        |              WHERE playback_history.provider_id = series.provider_id
        |                AND playback_history.resume_position_ms > 0
        |                AND (
        |                    playback_history.total_duration_ms <= 0
        |                    OR playback_history.resume_position_ms < CAST(playback_history.total_duration_ms * 0.95 AS INTEGER)
        |                )
        |                AND (
        |                    (playback_history.content_type = 'SERIES' AND playback_history.content_id = series.id)
        |                    OR (
        |                        playback_history.content_type = 'SERIES_EPISODE'
        |                        AND EXISTS (
        |                            SELECT 1 FROM episodes
        |                            WHERE episodes.id = playback_history.content_id
        |                              AND episodes.series_id = series.id
        |                        )
        |                    )
        |                )
        |          )
        |        ORDER BY series.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "playback_history", "episodes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getInProgressCountByCategory(providerId: Long, categoryId: Long): Flow<Int> {
    val _sql: String = """
        |
        |        SELECT COUNT(*)
        |        FROM series
        |        WHERE provider_id = ?
        |          AND category_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM playback_history
        |              WHERE playback_history.provider_id = series.provider_id
        |                AND playback_history.resume_position_ms > 0
        |                AND (
        |                    playback_history.total_duration_ms <= 0
        |                    OR playback_history.resume_position_ms < CAST(playback_history.total_duration_ms * 0.95 AS INTEGER)
        |                )
        |                AND (
        |                    (playback_history.content_type = 'SERIES' AND playback_history.content_id = series.id)
        |                    OR (
        |                        playback_history.content_type = 'SERIES_EPISODE'
        |                        AND EXISTS (
        |                            SELECT 1 FROM episodes
        |                            WHERE episodes.id = playback_history.content_id
        |                              AND episodes.series_id = series.id
        |                        )
        |                    )
        |                )
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "playback_history", "episodes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
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

  public override fun getUnwatchedByCategoryPage(
    providerId: Long,
    categoryId: Long,
    limit: Int,
    offset: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT series.* FROM series
        |        WHERE series.provider_id = ?
        |          AND series.category_id = ?
        |          AND (
        |              NOT EXISTS (
        |                  SELECT 1 FROM episodes
        |                  WHERE episodes.series_id = series.id
        |                    AND episodes.provider_id = series.provider_id
        |              )
        |              OR EXISTS (
        |                  SELECT 1 FROM episodes e
        |                  WHERE e.series_id = series.id
        |                    AND e.provider_id = series.provider_id
        |                    AND NOT EXISTS (
        |                        SELECT 1 FROM playback_history ph
        |                        WHERE ph.content_id = e.id
        |                          AND ph.content_type = 'SERIES_EPISODE'
        |                          AND ph.provider_id = series.provider_id
        |                          AND ph.total_duration_ms > 0
        |                          AND ph.resume_position_ms >= CAST(ph.total_duration_ms * 0.95 AS INTEGER)
        |                    )
        |              )
        |          )
        |        ORDER BY series.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "episodes", "playback_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getUnwatchedCountByCategory(providerId: Long, categoryId: Long): Flow<Int> {
    val _sql: String = """
        |
        |        SELECT COUNT(*)
        |        FROM series
        |        WHERE provider_id = ?
        |          AND category_id = ?
        |          AND (
        |              NOT EXISTS (
        |                  SELECT 1 FROM episodes
        |                  WHERE episodes.series_id = series.id
        |                    AND episodes.provider_id = ?
        |              )
        |              OR EXISTS (
        |                  SELECT 1 FROM episodes e
        |                  WHERE e.series_id = series.id
        |                    AND e.provider_id = ?
        |                    AND NOT EXISTS (
        |                        SELECT 1 FROM playback_history ph
        |                        WHERE ph.content_id = e.id
        |                          AND ph.content_type = 'SERIES_EPISODE'
        |                          AND ph.provider_id = ?
        |                          AND ph.total_duration_ms > 0
        |                          AND ph.resume_position_ms >= CAST(ph.total_duration_ms * 0.95 AS INTEGER)
        |                    )
        |              )
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "episodes", "playback_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 4
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 5
        _stmt.bindLong(_argIndex, providerId)
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

  public override fun getByWatchCountCategoryPage(
    providerId: Long,
    categoryId: Long,
    limit: Int,
    offset: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT series.* FROM series
        |        WHERE series.provider_id = ?
        |          AND series.category_id = ?
        |        ORDER BY COALESCE((
        |            SELECT MAX(playback_history.watch_count)
        |            FROM playback_history
        |            WHERE playback_history.provider_id = series.provider_id
        |              AND (
        |                  (playback_history.content_type = 'SERIES' AND playback_history.content_id = series.id)
        |                  OR (
        |                      playback_history.content_type = 'SERIES_EPISODE'
        |                      AND EXISTS (
        |                          SELECT 1 FROM episodes
        |                          WHERE episodes.id = playback_history.content_id
        |                            AND episodes.series_id = series.id
        |                      )
        |                  )
        |              )
        |        ), 0) DESC, series.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "playback_history", "episodes")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByCategoryPage(
    providerId: Long,
    categoryId: Long,
    limit: Int,
    offset: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? AND category_id = ? ORDER BY last_modified DESC, name ASC, id ASC LIMIT ? OFFSET ?)"
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByCategoryCursorPage(
    providerId: Long,
    categoryId: Long,
    limit: Int,
  ): List<SeriesBrowseEntity> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? AND category_id = ? ORDER BY name ASC, id ASC LIMIT ?)"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByCategoryCursorPageAfter(
    providerId: Long,
    categoryId: Long,
    lastName: String,
    lastId: Long,
    limit: Int,
  ): List<SeriesBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT * FROM series
        |        WHERE provider_id = ?
        |          AND category_id = ?
        |          AND (name > ? OR (name = ? AND id > ?))
        |        ORDER BY name ASC, id ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 4
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 5
        _stmt.bindLong(_argIndex, lastId)
        _argIndex = 6
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByCategoryPreview(
    providerId: Long,
    categoryId: Long,
    limit: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? AND category_id = ? ORDER BY last_modified DESC, name ASC, id ASC LIMIT ?)"
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTopRatedPreview(providerId: Long, limit: Int): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? ORDER BY rating DESC, name ASC LIMIT ?)"
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTopRatedCountByProvider(providerId: Long): Flow<Int> {
    val _sql: String = "SELECT COUNT(*) FROM series WHERE provider_id = ? AND rating > 0"
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
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

  public override suspend fun getTopRatedCursorPage(providerId: Long, limit: Int): List<SeriesBrowseEntity> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? ORDER BY rating DESC, name ASC, id ASC LIMIT ?)"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getTopRatedCursorPageAfter(
    providerId: Long,
    lastRating: Float,
    lastName: String,
    lastId: Long,
    limit: Int,
  ): List<SeriesBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT * FROM series
        |        WHERE provider_id = ?
        |          AND (
        |              rating < ?
        |              OR (rating = ? AND (name > ? OR (name = ? AND id > ?)))
        |          )
        |        ORDER BY rating DESC, name ASC, id ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindDouble(_argIndex, lastRating.toDouble())
        _argIndex = 3
        _stmt.bindDouble(_argIndex, lastRating.toDouble())
        _argIndex = 4
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 5
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 6
        _stmt.bindLong(_argIndex, lastId)
        _argIndex = 7
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTopRatedByCategoryPreview(
    providerId: Long,
    categoryId: Long,
    limit: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? AND category_id = ? ORDER BY rating DESC, name ASC LIMIT ?)"
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTopRatedCountByCategory(providerId: Long, categoryId: Long): Flow<Int> {
    val _sql: String = "SELECT COUNT(*) FROM series WHERE provider_id = ? AND category_id = ? AND rating > 0"
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
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

  public override suspend fun getTopRatedByCategoryCursorPage(
    providerId: Long,
    categoryId: Long,
    limit: Int,
  ): List<SeriesBrowseEntity> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? AND category_id = ? ORDER BY rating DESC, name ASC, id ASC LIMIT ?)"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getTopRatedByCategoryCursorPageAfter(
    providerId: Long,
    categoryId: Long,
    lastRating: Float,
    lastName: String,
    lastId: Long,
    limit: Int,
  ): List<SeriesBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT * FROM series
        |        WHERE provider_id = ?
        |          AND category_id = ?
        |          AND (
        |              rating < ?
        |              OR (rating = ? AND (name > ? OR (name = ? AND id > ?)))
        |          )
        |        ORDER BY rating DESC, name ASC, id ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindDouble(_argIndex, lastRating.toDouble())
        _argIndex = 4
        _stmt.bindDouble(_argIndex, lastRating.toDouble())
        _argIndex = 5
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 6
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 7
        _stmt.bindLong(_argIndex, lastId)
        _argIndex = 8
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getFreshPreview(providerId: Long, limit: Int): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? AND last_modified > 0 ORDER BY last_modified DESC, name ASC LIMIT ?)"
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getReleasedPreview(providerId: Long, limit: Int): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT * FROM series
        |        WHERE provider_id = ?
        |        ORDER BY
        |            CASE WHEN COALESCE(release_date, '') != '' THEN 1 ELSE 0 END DESC,
        |            release_date DESC,
        |            last_modified DESC,
        |            name ASC,
        |            id ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getFreshCountByProvider(providerId: Long): Flow<Int> {
    val _sql: String = """
        |
        |        SELECT COUNT(*) FROM series
        |                WHERE provider_id = ?
        |                    AND last_modified > 0
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
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

  public override suspend fun getFreshCursorPage(providerId: Long, limit: Int): List<SeriesBrowseEntity> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? AND last_modified > 0 ORDER BY last_modified DESC, name ASC, id ASC LIMIT ?)"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getFreshCursorPageAfter(
    providerId: Long,
    lastModified: Long,
    lastName: String,
    lastId: Long,
    limit: Int,
  ): List<SeriesBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT * FROM series
        |        WHERE provider_id = ?
        |                    AND last_modified > 0
        |          AND (
        |              last_modified < ?
        |              OR (last_modified = ? AND (name > ? OR (name = ? AND id > ?)))
        |          )
        |        ORDER BY last_modified DESC, name ASC, id ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, lastModified)
        _argIndex = 3
        _stmt.bindLong(_argIndex, lastModified)
        _argIndex = 4
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 5
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 6
        _stmt.bindLong(_argIndex, lastId)
        _argIndex = 7
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getFreshByCategoryPreview(
    providerId: Long,
    categoryId: Long,
    limit: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? AND category_id = ? AND last_modified > 0 ORDER BY last_modified DESC, name ASC LIMIT ?)"
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getReleasedByCategoryPreview(
    providerId: Long,
    categoryId: Long,
    limit: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT * FROM series
        |        WHERE provider_id = ?
        |          AND category_id = ?
        |        ORDER BY
        |            CASE WHEN COALESCE(release_date, '') != '' THEN 1 ELSE 0 END DESC,
        |            release_date DESC,
        |            last_modified DESC,
        |            name ASC,
        |            id ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getFreshCountByCategory(providerId: Long, categoryId: Long): Flow<Int> {
    val _sql: String = """
        |
        |        SELECT COUNT(*) FROM series
        |                WHERE provider_id = ?
        |                    AND category_id = ?
        |                    AND last_modified > 0
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
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

  public override suspend fun getFreshByCategoryCursorPage(
    providerId: Long,
    categoryId: Long,
    limit: Int,
  ): List<SeriesBrowseEntity> {
    val _sql: String = "SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE provider_id = ? AND category_id = ? AND last_modified > 0 ORDER BY last_modified DESC, name ASC, id ASC LIMIT ?)"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getFreshByCategoryCursorPageAfter(
    providerId: Long,
    categoryId: Long,
    lastModified: Long,
    lastName: String,
    lastId: Long,
    limit: Int,
  ): List<SeriesBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT * FROM series
        |        WHERE provider_id = ?
        |          AND category_id = ?
        |                    AND last_modified > 0
        |          AND (
        |              last_modified < ?
        |              OR (last_modified = ? AND (name > ? OR (name = ? AND id > ?)))
        |          )
        |        ORDER BY last_modified DESC, name ASC, id ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, lastModified)
        _argIndex = 4
        _stmt.bindLong(_argIndex, lastModified)
        _argIndex = 5
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 6
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 7
        _stmt.bindLong(_argIndex, lastId)
        _argIndex = 8
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun searchPage(
    providerId: Long,
    query: String,
    rawQuery: String,
    prefixLike: String,
    includeProtected: Int,
    limit: Int,
    offset: Int,
  ): List<SeriesBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT s.* FROM series s
        |        JOIN series_fts ON s.id = series_fts.rowid
        |        WHERE s.provider_id = ?
        |          AND series_fts MATCH ?
        |          AND (? != 0 OR s.is_user_protected = 0)
        |        ORDER BY
        |          CASE
        |            WHEN LOWER(s.name) = LOWER(?) THEN 0
        |            WHEN LOWER(s.name) LIKE LOWER(?) ESCAPE '\' THEN 1
        |            ELSE 2
        |          END ASC,
        |          s.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, query)
        _argIndex = 3
        _stmt.bindLong(_argIndex, includeProtected.toLong())
        _argIndex = 4
        _stmt.bindText(_argIndex, rawQuery)
        _argIndex = 5
        _stmt.bindText(_argIndex, prefixLike)
        _argIndex = 6
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 7
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun searchByCategoryPage(
    providerId: Long,
    categoryId: Long,
    query: String,
    rawQuery: String,
    prefixLike: String,
    includeProtected: Int,
    limit: Int,
    offset: Int,
  ): List<SeriesBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT s.* FROM series s
        |        JOIN series_fts ON s.id = series_fts.rowid
        |        WHERE s.provider_id = ?
        |          AND s.category_id = ?
        |          AND series_fts MATCH ?
        |          AND (? != 0 OR s.is_user_protected = 0)
        |        ORDER BY
        |          CASE
        |            WHEN LOWER(s.name) = LOWER(?) THEN 0
        |            WHEN LOWER(s.name) LIKE LOWER(?) ESCAPE '\' THEN 1
        |            ELSE 2
        |          END ASC,
        |          s.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindText(_argIndex, query)
        _argIndex = 4
        _stmt.bindLong(_argIndex, includeProtected.toLong())
        _argIndex = 5
        _stmt.bindText(_argIndex, rawQuery)
        _argIndex = 6
        _stmt.bindText(_argIndex, prefixLike)
        _argIndex = 7
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 8
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun search(
    providerId: Long,
    query: String,
    limit: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT s.* FROM series s
        |        JOIN series_fts ON s.id = series_fts.rowid
        |        WHERE s.provider_id = ?
        |          AND series_fts MATCH ?
        |        ORDER BY s.name ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "series_fts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, query)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun searchFallback(
    providerId: Long,
    queryLike: String,
    limit: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT s.* FROM series s
        |        WHERE s.provider_id = ?
        |          AND (
        |              LOWER(s.name) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(COALESCE(s.genre, '')) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(COALESCE(s.category_name, '')) LIKE LOWER(?) ESCAPE '\'
        |          )
        |        ORDER BY s.name ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, queryLike)
        _argIndex = 3
        _stmt.bindText(_argIndex, queryLike)
        _argIndex = 4
        _stmt.bindText(_argIndex, queryLike)
        _argIndex = 5
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun searchByCategory(
    providerId: Long,
    categoryId: Long,
    query: String,
    limit: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT s.* FROM series s
        |        JOIN series_fts ON s.id = series_fts.rowid
        |        WHERE s.provider_id = ?
        |          AND s.category_id = ?
        |          AND series_fts MATCH ?
        |        ORDER BY s.name ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "series_fts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindText(_argIndex, query)
        _argIndex = 4
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun searchByCategoryFallback(
    providerId: Long,
    categoryId: Long,
    queryLike: String,
    limit: Int,
  ): Flow<List<SeriesBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (
        |        SELECT s.* FROM series s
        |        WHERE s.provider_id = ?
        |          AND s.category_id = ?
        |          AND (
        |              LOWER(s.name) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(COALESCE(s.genre, '')) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(COALESCE(s.category_name, '')) LIKE LOWER(?) ESCAPE '\'
        |          )
        |        ORDER BY s.name ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindText(_argIndex, queryLike)
        _argIndex = 4
        _stmt.bindText(_argIndex, queryLike)
        _argIndex = 5
        _stmt.bindText(_argIndex, queryLike)
        _argIndex = 6
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfTmdbId: Int = getColumnIndexOrThrow(_stmt, "tmdb_id")
        val _columnIndexOfLastModified: Int = getColumnIndexOrThrow(_stmt, "last_modified")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long): SeriesEntity? {
    val _sql: String = "SELECT * FROM series WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
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
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfCacheState: Int = getColumnIndexOrThrow(_stmt, "cache_state")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfRemoteStaleAt: Int = getColumnIndexOrThrow(_stmt, "remote_stale_at")
        val _result: SeriesEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpCacheState: String
          _tmpCacheState = _stmt.getText(_columnIndexOfCacheState)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpRemoteStaleAt: Long
          _tmpRemoteStaleAt = _stmt.getLong(_columnIndexOfRemoteStaleAt)
          _result = SeriesEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpYoutubeTrailer,_tmpEpisodeRunTime,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint,_tmpCacheState,_tmpDetailHydratedAt,_tmpRemoteStaleAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderSync(providerId: Long): List<SeriesEntity> {
    val _sql: String = "SELECT * FROM series WHERE provider_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
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
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfCacheState: Int = getColumnIndexOrThrow(_stmt, "cache_state")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfRemoteStaleAt: Int = getColumnIndexOrThrow(_stmt, "remote_stale_at")
        val _result: MutableList<SeriesEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpCacheState: String
          _tmpCacheState = _stmt.getText(_columnIndexOfCacheState)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpRemoteStaleAt: Long
          _tmpRemoteStaleAt = _stmt.getLong(_columnIndexOfRemoteStaleAt)
          _item = SeriesEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpYoutubeTrailer,_tmpEpisodeRunTime,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint,_tmpCacheState,_tmpDetailHydratedAt,_tmpRemoteStaleAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderAndTmdbIdSync(providerId: Long, tmdbId: Long): List<SeriesEntity> {
    val _sql: String = "SELECT * FROM series WHERE provider_id = ? AND tmdb_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, tmdbId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
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
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfCacheState: Int = getColumnIndexOrThrow(_stmt, "cache_state")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfRemoteStaleAt: Int = getColumnIndexOrThrow(_stmt, "remote_stale_at")
        val _result: MutableList<SeriesEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpCacheState: String
          _tmpCacheState = _stmt.getText(_columnIndexOfCacheState)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpRemoteStaleAt: Long
          _tmpRemoteStaleAt = _stmt.getLong(_columnIndexOfRemoteStaleAt)
          _item = SeriesEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpYoutubeTrailer,_tmpEpisodeRunTime,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint,_tmpCacheState,_tmpDetailHydratedAt,_tmpRemoteStaleAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderAndReleaseYearPrefixSync(providerId: Long, yearPrefix: String): List<SeriesEntity> {
    val _sql: String = "SELECT * FROM series WHERE provider_id = ? AND release_date LIKE ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, yearPrefix)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
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
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfCacheState: Int = getColumnIndexOrThrow(_stmt, "cache_state")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfRemoteStaleAt: Int = getColumnIndexOrThrow(_stmt, "remote_stale_at")
        val _result: MutableList<SeriesEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpCacheState: String
          _tmpCacheState = _stmt.getText(_columnIndexOfCacheState)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpRemoteStaleAt: Long
          _tmpRemoteStaleAt = _stmt.getLong(_columnIndexOfRemoteStaleAt)
          _item = SeriesEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpYoutubeTrailer,_tmpEpisodeRunTime,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint,_tmpCacheState,_tmpDetailHydratedAt,_tmpRemoteStaleAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getTmdbIdsByProvider(providerId: Long): List<TmdbIdMapping> {
    val _sql: String = "SELECT tmdb_id FROM series WHERE provider_id = ? AND tmdb_id IS NOT NULL"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfTmdbId: Int = 0
        val _result: MutableList<TmdbIdMapping> = mutableListOf()
        while (_stmt.step()) {
          val _item: TmdbIdMapping
          val _tmpTmdbId: Long
          _tmpTmdbId = _stmt.getLong(_columnIndexOfTmdbId)
          _item = TmdbIdMapping(_tmpTmdbId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByIds(ids: List<Long>): Flow<List<SeriesBrowseEntity>> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT `id`, `series_id`, `provider_series_id`, `name`, `poster_url`, `category_id`, `category_name`, `genre`, `release_date`, `rating`, `tmdb_id`, `last_modified`, `provider_id`, `is_adult`, `is_user_protected` FROM (SELECT * FROM series WHERE id IN (")
    val _inputSize: Int = ids.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append("))")
    val _sql: String = _stringBuilder.toString()
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in ids) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfId: Int = 0
        val _columnIndexOfSeriesId: Int = 1
        val _columnIndexOfProviderSeriesId: Int = 2
        val _columnIndexOfName: Int = 3
        val _columnIndexOfPosterUrl: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfGenre: Int = 7
        val _columnIndexOfReleaseDate: Int = 8
        val _columnIndexOfRating: Int = 9
        val _columnIndexOfTmdbId: Int = 10
        val _columnIndexOfLastModified: Int = 11
        val _columnIndexOfProviderId: Int = 12
        val _columnIndexOfIsAdult: Int = 13
        val _columnIndexOfIsUserProtected: Int = 14
        val _result: MutableList<SeriesBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: SeriesBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpPosterUrl: String?
          if (_stmt.isNull(_columnIndexOfPosterUrl)) {
            _tmpPosterUrl = null
          } else {
            _tmpPosterUrl = _stmt.getText(_columnIndexOfPosterUrl)
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
          val _tmpLastModified: Long
          _tmpLastModified = _stmt.getLong(_columnIndexOfLastModified)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          _item_1 = SeriesBrowseEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getBySeriesId(providerId: Long, seriesId: Long): SeriesEntity? {
    val _sql: String = "SELECT * FROM series WHERE provider_id = ? AND series_id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, seriesId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
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
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfCacheState: Int = getColumnIndexOrThrow(_stmt, "cache_state")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfRemoteStaleAt: Int = getColumnIndexOrThrow(_stmt, "remote_stale_at")
        val _result: SeriesEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpCacheState: String
          _tmpCacheState = _stmt.getText(_columnIndexOfCacheState)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpRemoteStaleAt: Long
          _tmpRemoteStaleAt = _stmt.getLong(_columnIndexOfRemoteStaleAt)
          _result = SeriesEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpYoutubeTrailer,_tmpEpisodeRunTime,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint,_tmpCacheState,_tmpDetailHydratedAt,_tmpRemoteStaleAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getBySeriesIds(providerId: Long, seriesIds: List<Long>): List<SeriesEntity> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM series WHERE provider_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" AND series_id IN (")
    val _inputSize: Int = seriesIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        for (_item: Long in seriesIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
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
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfCacheState: Int = getColumnIndexOrThrow(_stmt, "cache_state")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfRemoteStaleAt: Int = getColumnIndexOrThrow(_stmt, "remote_stale_at")
        val _result: MutableList<SeriesEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: SeriesEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpCacheState: String
          _tmpCacheState = _stmt.getText(_columnIndexOfCacheState)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpRemoteStaleAt: Long
          _tmpRemoteStaleAt = _stmt.getLong(_columnIndexOfRemoteStaleAt)
          _item_1 = SeriesEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpYoutubeTrailer,_tmpEpisodeRunTime,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint,_tmpCacheState,_tmpDetailHydratedAt,_tmpRemoteStaleAt)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderSeriesId(providerId: Long, providerSeriesId: String): SeriesEntity? {
    val _sql: String = "SELECT * FROM series WHERE provider_id = ? AND provider_series_id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, providerSeriesId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "series_id")
        val _columnIndexOfProviderSeriesId: Int = getColumnIndexOrThrow(_stmt, "provider_series_id")
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
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfCacheState: Int = getColumnIndexOrThrow(_stmt, "cache_state")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfRemoteStaleAt: Int = getColumnIndexOrThrow(_stmt, "remote_stale_at")
        val _result: SeriesEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpSeriesId: Long
          _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId)
          val _tmpProviderSeriesId: String?
          if (_stmt.isNull(_columnIndexOfProviderSeriesId)) {
            _tmpProviderSeriesId = null
          } else {
            _tmpProviderSeriesId = _stmt.getText(_columnIndexOfProviderSeriesId)
          }
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_1 != 0
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpCacheState: String
          _tmpCacheState = _stmt.getText(_columnIndexOfCacheState)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpRemoteStaleAt: Long
          _tmpRemoteStaleAt = _stmt.getLong(_columnIndexOfRemoteStaleAt)
          _result = SeriesEntity(_tmpId,_tmpSeriesId,_tmpProviderSeriesId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpRating,_tmpTmdbId,_tmpYoutubeTrailer,_tmpEpisodeRunTime,_tmpLastModified,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint,_tmpCacheState,_tmpDetailHydratedAt,_tmpRemoteStaleAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getIdMappings(providerId: Long): List<SeriesRemoteIdMapping> {
    val _sql: String = """
        |
        |        SELECT id, COALESCE(NULLIF(provider_series_id, ''), CAST(series_id AS TEXT)) AS remote_id
        |        FROM series
        |        WHERE provider_id = ?
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfRemoteId: Int = 1
        val _result: MutableList<SeriesRemoteIdMapping> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesRemoteIdMapping
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpRemoteId: String
          _tmpRemoteId = _stmt.getText(_columnIndexOfRemoteId)
          _item = SeriesRemoteIdMapping(_tmpId,_tmpRemoteId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getIdMappingsByCategory(providerId: Long, categoryId: Long): List<SeriesRemoteIdMapping> {
    val _sql: String = """
        |
        |        SELECT id, COALESCE(NULLIF(provider_series_id, ''), CAST(series_id AS TEXT)) AS remote_id
        |        FROM series
        |        WHERE provider_id = ? AND category_id = ?
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfRemoteId: Int = 1
        val _result: MutableList<SeriesRemoteIdMapping> = mutableListOf()
        while (_stmt.step()) {
          val _item: SeriesRemoteIdMapping
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpRemoteId: String
          _tmpRemoteId = _stmt.getText(_columnIndexOfRemoteId)
          _item = SeriesRemoteIdMapping(_tmpId,_tmpRemoteId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getCategoryCounts(providerId: Long): Flow<List<CategoryCount>> {
    val _sql: String = "SELECT category_id, COUNT(*) as item_count FROM series WHERE provider_id = ? AND category_id IS NOT NULL GROUP BY category_id"
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfCategoryId: Int = 0
        val _columnIndexOfItemCount: Int = 1
        val _result: MutableList<CategoryCount> = mutableListOf()
        while (_stmt.step()) {
          val _item: CategoryCount
          val _tmpCategoryId: Long
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          val _tmpItem_count: Int
          _tmpItem_count = _stmt.getLong(_columnIndexOfItemCount).toInt()
          _item = CategoryCount(_tmpCategoryId,_tmpItem_count)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getCount(providerId: Long): Flow<Int> {
    val _sql: String = "SELECT COUNT(*) FROM series WHERE provider_id = ?"
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
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

  public override fun getCountByCategory(providerId: Long, categoryId: Long): Flow<Int> {
    val _sql: String = "SELECT COUNT(*) FROM series WHERE provider_id = ? AND category_id = ?"
    return createFlow(__db, false, arrayOf("series")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
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

  public override suspend fun deleteByProvider(providerId: Long) {
    val _sql: String = "DELETE FROM series WHERE provider_id = ?"
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

  public override suspend fun deleteByProviderAndCategory(providerId: Long, categoryId: Long) {
    val _sql: String = "DELETE FROM series WHERE provider_id = ? AND category_id = ?"
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

  public override suspend fun deleteByIds(ids: List<Long>) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("DELETE FROM series WHERE id IN (")
    val _inputSize: Int = ids.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in ids) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteMissingByCategory(
    providerId: Long,
    categoryId: Long,
    remoteIds: List<String>,
  ) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        DELETE FROM series")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        WHERE provider_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND category_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND COALESCE(NULLIF(provider_series_id, ''), CAST(series_id AS TEXT)) NOT IN (")
    val _inputSize: Int = remoteIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        ")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        for (_item: String in remoteIds) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateProtectionStatus(
    providerId: Long,
    categoryId: Long,
    isProtected: Boolean,
  ) {
    val _sql: String = "UPDATE series SET is_user_protected = ? WHERE provider_id = ? AND category_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (isProtected) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, categoryId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearProtectionForCategories(providerId: Long, categoryIds: List<Long>) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("UPDATE series SET is_user_protected = 0 WHERE provider_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" AND category_id IN (")
    val _inputSize: Int = categoryIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        for (_item: Long in categoryIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
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
