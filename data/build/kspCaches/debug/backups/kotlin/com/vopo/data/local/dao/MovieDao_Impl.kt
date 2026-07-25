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
import com.vopo.`data`.local.entity.MovieBrowseEntity
import com.vopo.`data`.local.entity.MovieEntity
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
public class MovieDao_Impl(
  __db: RoomDatabase,
) : MovieDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfMovieEntity: EntityInsertAdapter<MovieEntity>

  private val __updateAdapterOfMovieEntity: EntityDeleteOrUpdateAdapter<MovieEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfMovieEntity = object : EntityInsertAdapter<MovieEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `movies` (`id`,`stream_id`,`name`,`poster_url`,`backdrop_url`,`category_id`,`category_name`,`stream_url`,`container_extension`,`plot`,`cast`,`director`,`genre`,`release_date`,`duration`,`duration_seconds`,`rating`,`year`,`tmdb_id`,`youtube_trailer`,`provider_id`,`watch_progress`,`watch_count`,`last_watched_at`,`is_adult`,`is_user_protected`,`sync_fingerprint`,`added_at`,`cache_state`,`detail_hydrated_at`,`remote_stale_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: MovieEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.streamId)
        statement.bindText(3, entity.name)
        val _tmpPosterUrl: String? = entity.posterUrl
        if (_tmpPosterUrl == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpPosterUrl)
        }
        val _tmpBackdropUrl: String? = entity.backdropUrl
        if (_tmpBackdropUrl == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpBackdropUrl)
        }
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
        statement.bindText(8, entity.streamUrl)
        val _tmpContainerExtension: String? = entity.containerExtension
        if (_tmpContainerExtension == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpContainerExtension)
        }
        val _tmpPlot: String? = entity.plot
        if (_tmpPlot == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpPlot)
        }
        val _tmpCast: String? = entity.cast
        if (_tmpCast == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpCast)
        }
        val _tmpDirector: String? = entity.director
        if (_tmpDirector == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpDirector)
        }
        val _tmpGenre: String? = entity.genre
        if (_tmpGenre == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpGenre)
        }
        val _tmpReleaseDate: String? = entity.releaseDate
        if (_tmpReleaseDate == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpReleaseDate)
        }
        val _tmpDuration: String? = entity.duration
        if (_tmpDuration == null) {
          statement.bindNull(15)
        } else {
          statement.bindText(15, _tmpDuration)
        }
        statement.bindLong(16, entity.durationSeconds.toLong())
        statement.bindDouble(17, entity.rating.toDouble())
        val _tmpYear: String? = entity.year
        if (_tmpYear == null) {
          statement.bindNull(18)
        } else {
          statement.bindText(18, _tmpYear)
        }
        val _tmpTmdbId: Long? = entity.tmdbId
        if (_tmpTmdbId == null) {
          statement.bindNull(19)
        } else {
          statement.bindLong(19, _tmpTmdbId)
        }
        val _tmpYoutubeTrailer: String? = entity.youtubeTrailer
        if (_tmpYoutubeTrailer == null) {
          statement.bindNull(20)
        } else {
          statement.bindText(20, _tmpYoutubeTrailer)
        }
        statement.bindLong(21, entity.providerId)
        statement.bindLong(22, entity.watchProgress)
        statement.bindLong(23, entity.watchCount.toLong())
        statement.bindLong(24, entity.lastWatchedAt)
        val _tmp: Int = if (entity.isAdult) 1 else 0
        statement.bindLong(25, _tmp.toLong())
        val _tmp_1: Int = if (entity.isUserProtected) 1 else 0
        statement.bindLong(26, _tmp_1.toLong())
        statement.bindText(27, entity.syncFingerprint)
        statement.bindLong(28, entity.addedAt)
        statement.bindText(29, entity.cacheState)
        statement.bindLong(30, entity.detailHydratedAt)
        statement.bindLong(31, entity.remoteStaleAt)
      }
    }
    this.__updateAdapterOfMovieEntity = object : EntityDeleteOrUpdateAdapter<MovieEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `movies` SET `id` = ?,`stream_id` = ?,`name` = ?,`poster_url` = ?,`backdrop_url` = ?,`category_id` = ?,`category_name` = ?,`stream_url` = ?,`container_extension` = ?,`plot` = ?,`cast` = ?,`director` = ?,`genre` = ?,`release_date` = ?,`duration` = ?,`duration_seconds` = ?,`rating` = ?,`year` = ?,`tmdb_id` = ?,`youtube_trailer` = ?,`provider_id` = ?,`watch_progress` = ?,`watch_count` = ?,`last_watched_at` = ?,`is_adult` = ?,`is_user_protected` = ?,`sync_fingerprint` = ?,`added_at` = ?,`cache_state` = ?,`detail_hydrated_at` = ?,`remote_stale_at` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: MovieEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.streamId)
        statement.bindText(3, entity.name)
        val _tmpPosterUrl: String? = entity.posterUrl
        if (_tmpPosterUrl == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpPosterUrl)
        }
        val _tmpBackdropUrl: String? = entity.backdropUrl
        if (_tmpBackdropUrl == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpBackdropUrl)
        }
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
        statement.bindText(8, entity.streamUrl)
        val _tmpContainerExtension: String? = entity.containerExtension
        if (_tmpContainerExtension == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpContainerExtension)
        }
        val _tmpPlot: String? = entity.plot
        if (_tmpPlot == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpPlot)
        }
        val _tmpCast: String? = entity.cast
        if (_tmpCast == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpCast)
        }
        val _tmpDirector: String? = entity.director
        if (_tmpDirector == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpDirector)
        }
        val _tmpGenre: String? = entity.genre
        if (_tmpGenre == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpGenre)
        }
        val _tmpReleaseDate: String? = entity.releaseDate
        if (_tmpReleaseDate == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpReleaseDate)
        }
        val _tmpDuration: String? = entity.duration
        if (_tmpDuration == null) {
          statement.bindNull(15)
        } else {
          statement.bindText(15, _tmpDuration)
        }
        statement.bindLong(16, entity.durationSeconds.toLong())
        statement.bindDouble(17, entity.rating.toDouble())
        val _tmpYear: String? = entity.year
        if (_tmpYear == null) {
          statement.bindNull(18)
        } else {
          statement.bindText(18, _tmpYear)
        }
        val _tmpTmdbId: Long? = entity.tmdbId
        if (_tmpTmdbId == null) {
          statement.bindNull(19)
        } else {
          statement.bindLong(19, _tmpTmdbId)
        }
        val _tmpYoutubeTrailer: String? = entity.youtubeTrailer
        if (_tmpYoutubeTrailer == null) {
          statement.bindNull(20)
        } else {
          statement.bindText(20, _tmpYoutubeTrailer)
        }
        statement.bindLong(21, entity.providerId)
        statement.bindLong(22, entity.watchProgress)
        statement.bindLong(23, entity.watchCount.toLong())
        statement.bindLong(24, entity.lastWatchedAt)
        val _tmp: Int = if (entity.isAdult) 1 else 0
        statement.bindLong(25, _tmp.toLong())
        val _tmp_1: Int = if (entity.isUserProtected) 1 else 0
        statement.bindLong(26, _tmp_1.toLong())
        statement.bindText(27, entity.syncFingerprint)
        statement.bindLong(28, entity.addedAt)
        statement.bindText(29, entity.cacheState)
        statement.bindLong(30, entity.detailHydratedAt)
        statement.bindLong(31, entity.remoteStaleAt)
        statement.bindLong(32, entity.id)
      }
    }
  }

  public override suspend fun insertAll(movies: List<MovieEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfMovieEntity.insert(_connection, movies)
  }

  public override suspend fun update(movie: MovieEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfMovieEntity.handle(_connection, movie)
  }

  public override suspend fun updateAll(movies: List<MovieEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfMovieEntity.handleMultiple(_connection, movies)
  }

  public override suspend fun replaceAll(providerId: Long, movies: List<MovieEntity>): Unit = performInTransactionSuspending(__db) {
    super@MovieDao_Impl.replaceAll(providerId, movies)
  }

  public override suspend fun replaceCategory(
    providerId: Long,
    categoryId: Long,
    movies: List<MovieEntity>,
  ): Unit = performInTransactionSuspending(__db) {
    super@MovieDao_Impl.replaceCategory(providerId, categoryId, movies)
  }

  public override suspend fun upsertCategoryPage(providerId: Long, movies: List<MovieEntity>): Unit = performInTransactionSuspending(__db) {
    super@MovieDao_Impl.upsertCategoryPage(providerId, movies)
  }

  public override fun getByProvider(providerId: Long): Flow<List<MovieBrowseEntity>> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? ORDER BY added_at DESC, name ASC, id ASC)"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByProviderUnprotected(providerId: Long): Flow<List<MovieBrowseEntity>> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? AND is_user_protected = 0 ORDER BY added_at DESC, name ASC, id ASC)"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? ORDER BY added_at DESC, name ASC, id ASC LIMIT ? OFFSET ?)"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderCursorPage(providerId: Long, limit: Int): List<MovieBrowseEntity> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? ORDER BY name ASC, id ASC LIMIT ?)"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): List<MovieBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT * FROM movies
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
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT movies.* FROM movies
        |        WHERE movies.provider_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM favorites
        |              WHERE favorites.content_type = 'MOVIE'
        |                                AND favorites.provider_id = movies.provider_id
        |                AND favorites.group_id IS NULL
        |                AND favorites.content_id = movies.id
        |          )
        |        ORDER BY movies.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "favorites")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
        |        FROM movies
        |        WHERE provider_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM favorites
        |              WHERE favorites.content_type = 'MOVIE'
        |                                AND favorites.provider_id = movies.provider_id
        |                AND favorites.group_id IS NULL
        |                AND favorites.content_id = movies.id
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "favorites")) { _connection ->
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT movies.* FROM movies
        |        WHERE movies.provider_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM playback_history
        |              WHERE playback_history.provider_id = movies.provider_id
        |                AND playback_history.content_type = 'MOVIE'
        |                AND playback_history.content_id = movies.id
        |                AND playback_history.resume_position_ms > 0
        |                AND (
        |                    playback_history.total_duration_ms <= 0
        |                    OR playback_history.resume_position_ms < CAST(playback_history.total_duration_ms * 0.95 AS INTEGER)
        |                )
        |          )
        |        ORDER BY movies.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "playback_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
        |        FROM movies
        |        WHERE provider_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM playback_history
        |              WHERE playback_history.provider_id = movies.provider_id
        |                AND playback_history.content_type = 'MOVIE'
        |                AND playback_history.content_id = movies.id
        |                AND playback_history.resume_position_ms > 0
        |                AND (
        |                    playback_history.total_duration_ms <= 0
        |                    OR playback_history.resume_position_ms < CAST(playback_history.total_duration_ms * 0.95 AS INTEGER)
        |                )
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "playback_history")) { _connection ->
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT movies.* FROM movies
        |        WHERE movies.provider_id = ?
        |          AND NOT EXISTS (
        |              SELECT 1 FROM playback_history
        |              WHERE playback_history.provider_id = movies.provider_id
        |                AND playback_history.content_type = 'MOVIE'
        |                AND playback_history.content_id = movies.id
        |                AND playback_history.resume_position_ms > 0
        |          )
        |        ORDER BY movies.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "playback_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
        |        FROM movies
        |        WHERE movies.provider_id = ?
        |          AND NOT EXISTS (
        |              SELECT 1 FROM playback_history
        |              WHERE playback_history.provider_id = movies.provider_id
        |                AND playback_history.content_type = 'MOVIE'
        |                AND playback_history.content_id = movies.id
        |                AND playback_history.resume_position_ms > 0
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "playback_history")) { _connection ->
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

  public override fun getByWatchCountProviderPage(
    providerId: Long,
    limit: Int,
    offset: Int,
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT movies.* FROM movies
        |        WHERE movies.provider_id = ?
        |        ORDER BY COALESCE(movies.watch_count, 0) DESC, movies.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByWatchCountProviderCursorPage(providerId: Long, limit: Int): List<MovieBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT movies.* FROM movies
        |        WHERE movies.provider_id = ?
        |        ORDER BY COALESCE(movies.watch_count, 0) DESC, movies.name ASC, movies.id ASC
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
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): List<MovieBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT movies.* FROM movies
        |        WHERE movies.provider_id = ?
        |          AND (
        |              COALESCE(movies.watch_count, 0) < ?
        |              OR (
        |                  COALESCE(movies.watch_count, 0) = ?
        |                  AND (movies.name > ? OR (movies.name = ? AND movies.id > ?))
        |              )
        |          )
        |        ORDER BY COALESCE(movies.watch_count, 0) DESC, movies.name ASC, movies.id ASC
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
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByCategory(providerId: Long, categoryId: Long): Flow<List<MovieBrowseEntity>> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? AND category_id = ? ORDER BY added_at DESC, name ASC, id ASC)"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): List<MovieBrowseEntity> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? AND category_id = ? ORDER BY name ASC, id ASC LIMIT ?)"
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
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): List<MovieBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT * FROM movies
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
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT movies.* FROM movies
        |        WHERE movies.provider_id = ?
        |          AND movies.category_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM favorites
        |              WHERE favorites.content_type = 'MOVIE'
        |                                AND favorites.provider_id = movies.provider_id
        |                AND favorites.group_id IS NULL
        |                AND favorites.content_id = movies.id
        |          )
        |        ORDER BY movies.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "favorites")) { _connection ->
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
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
        |        FROM movies
        |        WHERE provider_id = ?
        |          AND category_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM favorites
        |              WHERE favorites.content_type = 'MOVIE'
        |                                AND favorites.provider_id = movies.provider_id
        |                AND favorites.group_id IS NULL
        |                AND favorites.content_id = movies.id
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "favorites")) { _connection ->
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT movies.* FROM movies
        |        WHERE movies.provider_id = ?
        |          AND movies.category_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM playback_history
        |              WHERE playback_history.provider_id = movies.provider_id
        |                AND playback_history.content_type = 'MOVIE'
        |                AND playback_history.content_id = movies.id
        |                AND playback_history.resume_position_ms > 0
        |                AND (
        |                    playback_history.total_duration_ms <= 0
        |                    OR playback_history.resume_position_ms < CAST(playback_history.total_duration_ms * 0.95 AS INTEGER)
        |                )
        |          )
        |        ORDER BY movies.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "playback_history")) { _connection ->
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
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
        |        FROM movies
        |        WHERE provider_id = ?
        |          AND category_id = ?
        |          AND EXISTS (
        |              SELECT 1 FROM playback_history
        |              WHERE playback_history.provider_id = movies.provider_id
        |                AND playback_history.content_type = 'MOVIE'
        |                AND playback_history.content_id = movies.id
        |                AND playback_history.resume_position_ms > 0
        |                AND (
        |                    playback_history.total_duration_ms <= 0
        |                    OR playback_history.resume_position_ms < CAST(playback_history.total_duration_ms * 0.95 AS INTEGER)
        |                )
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "playback_history")) { _connection ->
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT movies.* FROM movies
        |        WHERE movies.provider_id = ?
        |          AND movies.category_id = ?
        |          AND NOT EXISTS (
        |              SELECT 1 FROM playback_history
        |              WHERE playback_history.provider_id = movies.provider_id
        |                AND playback_history.content_type = 'MOVIE'
        |                AND playback_history.content_id = movies.id
        |                AND playback_history.resume_position_ms > 0
        |          )
        |        ORDER BY movies.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "playback_history")) { _connection ->
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
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
        |        FROM movies
        |        WHERE movies.provider_id = ?
        |          AND movies.category_id = ?
        |          AND NOT EXISTS (
        |              SELECT 1 FROM playback_history
        |              WHERE playback_history.provider_id = movies.provider_id
        |                AND playback_history.content_type = 'MOVIE'
        |                AND playback_history.content_id = movies.id
        |                AND playback_history.resume_position_ms > 0
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "playback_history")) { _connection ->
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

  public override fun getByWatchCountCategoryPage(
    providerId: Long,
    categoryId: Long,
    limit: Int,
    offset: Int,
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT movies.* FROM movies
        |        WHERE movies.provider_id = ?
        |          AND movies.category_id = ?
        |        ORDER BY COALESCE(movies.watch_count, 0) DESC, movies.name ASC
        |        LIMIT ? OFFSET ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
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
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByWatchCountCategoryCursorPage(
    providerId: Long,
    categoryId: Long,
    limit: Int,
  ): List<MovieBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT movies.* FROM movies
        |        WHERE movies.provider_id = ?
        |          AND movies.category_id = ?
        |        ORDER BY COALESCE(movies.watch_count, 0) DESC, movies.name ASC, movies.id ASC
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
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByWatchCountCategoryCursorPageAfter(
    providerId: Long,
    categoryId: Long,
    lastWatchCount: Int,
    lastName: String,
    lastId: Long,
    limit: Int,
  ): List<MovieBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT movies.* FROM movies
        |        WHERE movies.provider_id = ?
        |          AND movies.category_id = ?
        |          AND (
        |              COALESCE(movies.watch_count, 0) < ?
        |              OR (
        |                  COALESCE(movies.watch_count, 0) = ?
        |                  AND (movies.name > ? OR (movies.name = ? AND movies.id > ?))
        |              )
        |          )
        |        ORDER BY COALESCE(movies.watch_count, 0) DESC, movies.name ASC, movies.id ASC
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
        _stmt.bindLong(_argIndex, lastWatchCount.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, lastWatchCount.toLong())
        _argIndex = 5
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 6
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 7
        _stmt.bindLong(_argIndex, lastId)
        _argIndex = 8
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByCategoryUnprotected(providerId: Long, categoryId: Long): Flow<List<MovieBrowseEntity>> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? AND category_id = ? AND is_user_protected = 0 ORDER BY added_at DESC, name ASC, id ASC)"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? AND category_id = ? ORDER BY added_at DESC, name ASC, id ASC LIMIT ? OFFSET ?)"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
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
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? AND category_id = ? ORDER BY added_at DESC, name ASC, id ASC LIMIT ?)"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTopRatedPreview(providerId: Long, limit: Int): Flow<List<MovieBrowseEntity>> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? AND rating > 0 ORDER BY rating DESC, name ASC LIMIT ?)"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTopRatedCountByProvider(providerId: Long): Flow<Int> {
    val _sql: String = "SELECT COUNT(*) FROM movies WHERE provider_id = ? AND rating > 0"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
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

  public override suspend fun getTopRatedCursorPage(providerId: Long, limit: Int): List<MovieBrowseEntity> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? AND rating > 0 ORDER BY rating DESC, name ASC, id ASC LIMIT ?)"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): List<MovieBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT * FROM movies
        |        WHERE provider_id = ?
        |          AND rating > 0
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
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? AND category_id = ? AND rating > 0 ORDER BY rating DESC, name ASC LIMIT ?)"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getTopRatedCountByCategory(providerId: Long, categoryId: Long): Flow<Int> {
    val _sql: String = "SELECT COUNT(*) FROM movies WHERE provider_id = ? AND category_id = ? AND rating > 0"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
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
  ): List<MovieBrowseEntity> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? AND category_id = ? AND rating > 0 ORDER BY rating DESC, name ASC, id ASC LIMIT ?)"
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
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): List<MovieBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT * FROM movies
        |        WHERE provider_id = ?
        |          AND category_id = ?
        |          AND rating > 0
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
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getFreshPreview(providerId: Long, limit: Int): Flow<List<MovieBrowseEntity>> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? AND added_at > 0 ORDER BY added_at DESC, name ASC, id ASC LIMIT ?)"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getReleasedPreview(providerId: Long, limit: Int): Flow<List<MovieBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |            SELECT * FROM movies
        |            WHERE provider_id = ?
        |            ORDER BY
        |                CASE WHEN COALESCE(release_date, '') != '' THEN 1 ELSE 0 END DESC,
        |                release_date DESC,
        |                CASE WHEN COALESCE(year, '') != '' THEN 1 ELSE 0 END DESC,
        |                year DESC,
        |                added_at DESC,
        |                name ASC,
        |                id ASC
        |            LIMIT ?
        |            )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
        |        SELECT COUNT(*) FROM movies
        |        WHERE provider_id = ?
        |          AND (
        |                            added_at > 0
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
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

  public override suspend fun getFreshCursorPage(providerId: Long, limit: Int): List<MovieBrowseEntity> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? AND added_at > 0 ORDER BY added_at DESC, name ASC, id ASC LIMIT ?)"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
    lastAddedAt: Long,
    lastName: String,
    lastId: Long,
    limit: Int,
  ): List<MovieBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT * FROM movies
        |        WHERE provider_id = ?
        |          AND added_at > 0
        |          AND (
        |              added_at < ?
        |              OR (
        |                  added_at = ?
        |                  AND (name > ? OR (name = ? AND id > ?))
        |              )
        |          )
        |        ORDER BY added_at DESC, name ASC, id ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, lastAddedAt)
        _argIndex = 3
        _stmt.bindLong(_argIndex, lastAddedAt)
        _argIndex = 4
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 5
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 6
        _stmt.bindLong(_argIndex, lastId)
        _argIndex = 7
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? AND category_id = ? AND added_at > 0 ORDER BY added_at DESC, name ASC, id ASC LIMIT ?)"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |            SELECT * FROM movies
        |            WHERE provider_id = ?
        |              AND category_id = ?
        |            ORDER BY
        |                CASE WHEN COALESCE(release_date, '') != '' THEN 1 ELSE 0 END DESC,
        |                release_date DESC,
        |                CASE WHEN COALESCE(year, '') != '' THEN 1 ELSE 0 END DESC,
        |                year DESC,
        |                added_at DESC,
        |                name ASC,
        |                id ASC
        |            LIMIT ?
        |            )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
        |        SELECT COUNT(*) FROM movies
        |        WHERE provider_id = ?
        |          AND category_id = ?
        |          AND (
        |                            added_at > 0
        |          )
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
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
  ): List<MovieBrowseEntity> {
    val _sql: String = "SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE provider_id = ? AND category_id = ? AND added_at > 0 ORDER BY added_at DESC, name ASC, id ASC LIMIT ?)"
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
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
    lastAddedAt: Long,
    lastName: String,
    lastId: Long,
    limit: Int,
  ): List<MovieBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT * FROM movies
        |        WHERE provider_id = ?
        |          AND category_id = ?
        |          AND added_at > 0
        |          AND (
        |              added_at < ?
        |              OR (
        |                  added_at = ?
        |                  AND (name > ? OR (name = ? AND id > ?))
        |              )
        |          )
        |        ORDER BY added_at DESC, name ASC, id ASC
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
        _stmt.bindLong(_argIndex, lastAddedAt)
        _argIndex = 4
        _stmt.bindLong(_argIndex, lastAddedAt)
        _argIndex = 5
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 6
        _stmt.bindText(_argIndex, lastName)
        _argIndex = 7
        _stmt.bindLong(_argIndex, lastId)
        _argIndex = 8
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): List<MovieBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT m.* FROM movies m
        |        JOIN movies_fts ON m.id = movies_fts.rowid
        |        WHERE m.provider_id = ?
        |          AND movies_fts MATCH ?
        |          AND (? != 0 OR m.is_user_protected = 0)
        |        ORDER BY
        |          CASE
        |            WHEN LOWER(m.name) = LOWER(?) THEN 0
        |            WHEN LOWER(m.name) LIKE LOWER(?) ESCAPE '\' THEN 1
        |            ELSE 2
        |          END ASC,
        |          m.name ASC
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
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): List<MovieBrowseEntity> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT m.* FROM movies m
        |        JOIN movies_fts ON m.id = movies_fts.rowid
        |        WHERE m.provider_id = ?
        |          AND m.category_id = ?
        |          AND movies_fts MATCH ?
        |          AND (? != 0 OR m.is_user_protected = 0)
        |        ORDER BY
        |          CASE
        |            WHEN LOWER(m.name) = LOWER(?) THEN 0
        |            WHEN LOWER(m.name) LIKE LOWER(?) ESCAPE '\' THEN 1
        |            ELSE 2
        |          END ASC,
        |          m.name ASC
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
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT m.* FROM movies m
        |        JOIN movies_fts ON m.id = movies_fts.rowid
        |        WHERE m.provider_id = ?
        |          AND movies_fts MATCH ?
        |        ORDER BY m.name ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "movies_fts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, query)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT m.* FROM movies m
        |        WHERE m.provider_id = ?
        |          AND (
        |              LOWER(m.name) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(COALESCE(m.genre, '')) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(COALESCE(m.category_name, '')) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(COALESCE(m.year, '')) LIKE LOWER(?) ESCAPE '\'
        |          )
        |        ORDER BY m.name ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
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
        _stmt.bindText(_argIndex, queryLike)
        _argIndex = 6
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT m.* FROM movies m
        |        JOIN movies_fts ON m.id = movies_fts.rowid
        |        WHERE m.provider_id = ?
        |          AND m.category_id = ?
        |          AND movies_fts MATCH ?
        |        ORDER BY m.name ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "movies_fts")) { _connection ->
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
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
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
  ): Flow<List<MovieBrowseEntity>> {
    val _sql: String = """
        |SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (
        |        SELECT m.* FROM movies m
        |        WHERE m.provider_id = ?
        |          AND m.category_id = ?
        |          AND (
        |              LOWER(m.name) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(COALESCE(m.genre, '')) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(COALESCE(m.category_name, '')) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(COALESCE(m.year, '')) LIKE LOWER(?) ESCAPE '\'
        |          )
        |        ORDER BY m.name ASC
        |        LIMIT ?
        |        )
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
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
        _stmt.bindText(_argIndex, queryLike)
        _argIndex = 7
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfPosterUrl: Int = getColumnIndexOrThrow(_stmt, "poster_url")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfContainerExtension: Int = getColumnIndexOrThrow(_stmt, "container_extension")
        val _columnIndexOfGenre: Int = getColumnIndexOrThrow(_stmt, "genre")
        val _columnIndexOfReleaseDate: Int = getColumnIndexOrThrow(_stmt, "release_date")
        val _columnIndexOfDurationSeconds: Int = getColumnIndexOrThrow(_stmt, "duration_seconds")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfYear: Int = getColumnIndexOrThrow(_stmt, "year")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long): MovieEntity? {
    val _sql: String = "SELECT * FROM movies WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
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
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _columnIndexOfCacheState: Int = getColumnIndexOrThrow(_stmt, "cache_state")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfRemoteStaleAt: Int = getColumnIndexOrThrow(_stmt, "remote_stale_at")
        val _result: MovieEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpWatchProgress: Long
          _tmpWatchProgress = _stmt.getLong(_columnIndexOfWatchProgress)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
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
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          val _tmpCacheState: String
          _tmpCacheState = _stmt.getText(_columnIndexOfCacheState)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpRemoteStaleAt: Long
          _tmpRemoteStaleAt = _stmt.getLong(_columnIndexOfRemoteStaleAt)
          _result = MovieEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpDuration,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpTmdbId,_tmpYoutubeTrailer,_tmpProviderId,_tmpWatchProgress,_tmpWatchCount,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint,_tmpAddedAt,_tmpCacheState,_tmpDetailHydratedAt,_tmpRemoteStaleAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderSync(providerId: Long): List<MovieEntity> {
    val _sql: String = "SELECT * FROM movies WHERE provider_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
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
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _columnIndexOfCacheState: Int = getColumnIndexOrThrow(_stmt, "cache_state")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfRemoteStaleAt: Int = getColumnIndexOrThrow(_stmt, "remote_stale_at")
        val _result: MutableList<MovieEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpWatchProgress: Long
          _tmpWatchProgress = _stmt.getLong(_columnIndexOfWatchProgress)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
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
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          val _tmpCacheState: String
          _tmpCacheState = _stmt.getText(_columnIndexOfCacheState)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpRemoteStaleAt: Long
          _tmpRemoteStaleAt = _stmt.getLong(_columnIndexOfRemoteStaleAt)
          _item = MovieEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpDuration,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpTmdbId,_tmpYoutubeTrailer,_tmpProviderId,_tmpWatchProgress,_tmpWatchCount,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint,_tmpAddedAt,_tmpCacheState,_tmpDetailHydratedAt,_tmpRemoteStaleAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderAndTmdbIdSync(providerId: Long, tmdbId: Long): List<MovieEntity> {
    val _sql: String = "SELECT * FROM movies WHERE provider_id = ? AND tmdb_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, tmdbId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
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
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _columnIndexOfCacheState: Int = getColumnIndexOrThrow(_stmt, "cache_state")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfRemoteStaleAt: Int = getColumnIndexOrThrow(_stmt, "remote_stale_at")
        val _result: MutableList<MovieEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpWatchProgress: Long
          _tmpWatchProgress = _stmt.getLong(_columnIndexOfWatchProgress)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
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
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          val _tmpCacheState: String
          _tmpCacheState = _stmt.getText(_columnIndexOfCacheState)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpRemoteStaleAt: Long
          _tmpRemoteStaleAt = _stmt.getLong(_columnIndexOfRemoteStaleAt)
          _item = MovieEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpDuration,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpTmdbId,_tmpYoutubeTrailer,_tmpProviderId,_tmpWatchProgress,_tmpWatchCount,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint,_tmpAddedAt,_tmpCacheState,_tmpDetailHydratedAt,_tmpRemoteStaleAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderAndYearSync(providerId: Long, year: String): List<MovieEntity> {
    val _sql: String = "SELECT * FROM movies WHERE provider_id = ? AND year = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, year)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
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
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _columnIndexOfCacheState: Int = getColumnIndexOrThrow(_stmt, "cache_state")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfRemoteStaleAt: Int = getColumnIndexOrThrow(_stmt, "remote_stale_at")
        val _result: MutableList<MovieEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpWatchProgress: Long
          _tmpWatchProgress = _stmt.getLong(_columnIndexOfWatchProgress)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
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
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          val _tmpCacheState: String
          _tmpCacheState = _stmt.getText(_columnIndexOfCacheState)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpRemoteStaleAt: Long
          _tmpRemoteStaleAt = _stmt.getLong(_columnIndexOfRemoteStaleAt)
          _item = MovieEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpDuration,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpTmdbId,_tmpYoutubeTrailer,_tmpProviderId,_tmpWatchProgress,_tmpWatchCount,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint,_tmpAddedAt,_tmpCacheState,_tmpDetailHydratedAt,_tmpRemoteStaleAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderAndReleaseYearPrefixSync(providerId: Long, yearPrefix: String): List<MovieEntity> {
    val _sql: String = "SELECT * FROM movies WHERE provider_id = ? AND release_date LIKE ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, yearPrefix)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
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
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _columnIndexOfCacheState: Int = getColumnIndexOrThrow(_stmt, "cache_state")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfRemoteStaleAt: Int = getColumnIndexOrThrow(_stmt, "remote_stale_at")
        val _result: MutableList<MovieEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: MovieEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpWatchProgress: Long
          _tmpWatchProgress = _stmt.getLong(_columnIndexOfWatchProgress)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
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
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          val _tmpCacheState: String
          _tmpCacheState = _stmt.getText(_columnIndexOfCacheState)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpRemoteStaleAt: Long
          _tmpRemoteStaleAt = _stmt.getLong(_columnIndexOfRemoteStaleAt)
          _item = MovieEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpDuration,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpTmdbId,_tmpYoutubeTrailer,_tmpProviderId,_tmpWatchProgress,_tmpWatchCount,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint,_tmpAddedAt,_tmpCacheState,_tmpDetailHydratedAt,_tmpRemoteStaleAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getTmdbIdsByProvider(providerId: Long): List<TmdbIdMapping> {
    val _sql: String = "SELECT tmdb_id FROM movies WHERE provider_id = ? AND tmdb_id IS NOT NULL"
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

  public override fun getByIds(ids: List<Long>): Flow<List<MovieBrowseEntity>> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT `id`, `stream_id`, `name`, `poster_url`, `category_id`, `category_name`, `stream_url`, `container_extension`, `genre`, `release_date`, `duration_seconds`, `rating`, `year`, `provider_id`, `watch_progress`, `last_watched_at`, `is_adult`, `is_user_protected`, `added_at` FROM (SELECT * FROM movies WHERE id IN (")
    val _inputSize: Int = ids.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append("))")
    val _sql: String = _stringBuilder.toString()
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in ids) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfPosterUrl: Int = 3
        val _columnIndexOfCategoryId: Int = 4
        val _columnIndexOfCategoryName: Int = 5
        val _columnIndexOfStreamUrl: Int = 6
        val _columnIndexOfContainerExtension: Int = 7
        val _columnIndexOfGenre: Int = 8
        val _columnIndexOfReleaseDate: Int = 9
        val _columnIndexOfDurationSeconds: Int = 10
        val _columnIndexOfRating: Int = 11
        val _columnIndexOfYear: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfWatchProgress: Int = 14
        val _columnIndexOfLastWatchedAt: Int = 15
        val _columnIndexOfIsAdult: Int = 16
        val _columnIndexOfIsUserProtected: Int = 17
        val _columnIndexOfAddedAt: Int = 18
        val _result: MutableList<MovieBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: MovieBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item_1 = MovieBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpGenre,_tmpReleaseDate,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpProviderId,_tmpWatchProgress,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpAddedAt)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByStreamId(providerId: Long, streamId: Long): MovieEntity? {
    val _sql: String = "SELECT * FROM movies WHERE provider_id = ? AND stream_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, streamId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
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
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _columnIndexOfCacheState: Int = getColumnIndexOrThrow(_stmt, "cache_state")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfRemoteStaleAt: Int = getColumnIndexOrThrow(_stmt, "remote_stale_at")
        val _result: MovieEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpWatchProgress: Long
          _tmpWatchProgress = _stmt.getLong(_columnIndexOfWatchProgress)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
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
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          val _tmpCacheState: String
          _tmpCacheState = _stmt.getText(_columnIndexOfCacheState)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpRemoteStaleAt: Long
          _tmpRemoteStaleAt = _stmt.getLong(_columnIndexOfRemoteStaleAt)
          _result = MovieEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpDuration,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpTmdbId,_tmpYoutubeTrailer,_tmpProviderId,_tmpWatchProgress,_tmpWatchCount,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint,_tmpAddedAt,_tmpCacheState,_tmpDetailHydratedAt,_tmpRemoteStaleAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByStreamIds(providerId: Long, streamIds: List<Long>): List<MovieEntity> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM movies WHERE provider_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" AND stream_id IN (")
    val _inputSize: Int = streamIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        for (_item: Long in streamIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
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
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfWatchProgress: Int = getColumnIndexOrThrow(_stmt, "watch_progress")
        val _columnIndexOfWatchCount: Int = getColumnIndexOrThrow(_stmt, "watch_count")
        val _columnIndexOfLastWatchedAt: Int = getColumnIndexOrThrow(_stmt, "last_watched_at")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _columnIndexOfCacheState: Int = getColumnIndexOrThrow(_stmt, "cache_state")
        val _columnIndexOfDetailHydratedAt: Int = getColumnIndexOrThrow(_stmt, "detail_hydrated_at")
        val _columnIndexOfRemoteStaleAt: Int = getColumnIndexOrThrow(_stmt, "remote_stale_at")
        val _result: MutableList<MovieEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: MovieEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
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
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpWatchProgress: Long
          _tmpWatchProgress = _stmt.getLong(_columnIndexOfWatchProgress)
          val _tmpWatchCount: Int
          _tmpWatchCount = _stmt.getLong(_columnIndexOfWatchCount).toInt()
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
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          val _tmpCacheState: String
          _tmpCacheState = _stmt.getText(_columnIndexOfCacheState)
          val _tmpDetailHydratedAt: Long
          _tmpDetailHydratedAt = _stmt.getLong(_columnIndexOfDetailHydratedAt)
          val _tmpRemoteStaleAt: Long
          _tmpRemoteStaleAt = _stmt.getLong(_columnIndexOfRemoteStaleAt)
          _item_1 = MovieEntity(_tmpId,_tmpStreamId,_tmpName,_tmpPosterUrl,_tmpBackdropUrl,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpContainerExtension,_tmpPlot,_tmpCast,_tmpDirector,_tmpGenre,_tmpReleaseDate,_tmpDuration,_tmpDurationSeconds,_tmpRating,_tmpYear,_tmpTmdbId,_tmpYoutubeTrailer,_tmpProviderId,_tmpWatchProgress,_tmpWatchCount,_tmpLastWatchedAt,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint,_tmpAddedAt,_tmpCacheState,_tmpDetailHydratedAt,_tmpRemoteStaleAt)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getIdMappings(providerId: Long): List<RemoteIdMapping> {
    val _sql: String = "SELECT id, stream_id AS remote_id FROM movies WHERE provider_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
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

  public override suspend fun getIdMappingsByCategory(providerId: Long, categoryId: Long): List<RemoteIdMapping> {
    val _sql: String = "SELECT id, stream_id AS remote_id FROM movies WHERE provider_id = ? AND category_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
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

  public override fun getCategoryCounts(providerId: Long): Flow<List<CategoryCount>> {
    val _sql: String = "SELECT category_id, COUNT(*) as item_count FROM movies WHERE provider_id = ? AND category_id IS NOT NULL GROUP BY category_id"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
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
    val _sql: String = "SELECT COUNT(*) FROM movies WHERE provider_id = ?"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
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
    val _sql: String = "SELECT COUNT(*) FROM movies WHERE provider_id = ? AND category_id = ?"
    return createFlow(__db, false, arrayOf("movies")) { _connection ->
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

  public override suspend fun syncWatchProgressFromHistory(id: Long, providerId: Long) {
    val _sql: String = """
        |
        |        UPDATE movies
        |        SET watch_progress = COALESCE((
        |            SELECT resume_position_ms FROM playback_history
        |            WHERE playback_history.content_id = movies.id
        |              AND playback_history.content_type = 'MOVIE'
        |              AND playback_history.provider_id = movies.provider_id
        |        ), 0),
        |            watch_count = COALESCE((
        |                SELECT watch_count FROM playback_history
        |                WHERE playback_history.content_id = movies.id
        |                  AND playback_history.content_type = 'MOVIE'
        |                  AND playback_history.provider_id = movies.provider_id
        |            ), 0),
        |            last_watched_at = COALESCE((
        |                SELECT last_watched_at FROM playback_history
        |                WHERE playback_history.content_id = movies.id
        |                  AND playback_history.content_type = 'MOVIE'
        |                  AND playback_history.provider_id = movies.provider_id
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
        |        UPDATE movies
        |        SET watch_progress = COALESCE((
        |            SELECT resume_position_ms FROM playback_history
        |            WHERE playback_history.content_id = movies.id
        |              AND playback_history.content_type = 'MOVIE'
        |              AND playback_history.provider_id = movies.provider_id
        |        ), 0),
        |            watch_count = COALESCE((
        |                SELECT watch_count FROM playback_history
        |                WHERE playback_history.content_id = movies.id
        |                  AND playback_history.content_type = 'MOVIE'
        |                  AND playback_history.provider_id = movies.provider_id
        |            ), 0),
        |            last_watched_at = COALESCE((
        |                SELECT last_watched_at FROM playback_history
        |                WHERE playback_history.content_id = movies.id
        |                  AND playback_history.content_type = 'MOVIE'
        |                  AND playback_history.provider_id = movies.provider_id
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
        |        UPDATE movies
        |        SET watch_progress = COALESCE((
        |            SELECT resume_position_ms FROM playback_history
        |            WHERE playback_history.content_id = movies.id
        |              AND playback_history.content_type = 'MOVIE'
        |              AND playback_history.provider_id = movies.provider_id
        |        ), 0),
        |            watch_count = COALESCE((
        |                SELECT watch_count FROM playback_history
        |                WHERE playback_history.content_id = movies.id
        |                  AND playback_history.content_type = 'MOVIE'
        |                  AND playback_history.provider_id = movies.provider_id
        |            ), 0),
        |            last_watched_at = COALESCE((
        |                SELECT last_watched_at FROM playback_history
        |                WHERE playback_history.content_id = movies.id
        |                  AND playback_history.content_type = 'MOVIE'
        |                  AND playback_history.provider_id = movies.provider_id
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
    val _sql: String = "UPDATE movies SET watch_progress = 0, watch_count = 0, last_watched_at = 0"
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
    val _sql: String = "DELETE FROM movies WHERE provider_id = ?"
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
    val _sql: String = "DELETE FROM movies WHERE provider_id = ? AND category_id = ?"
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
    _stringBuilder.append("DELETE FROM movies WHERE id IN (")
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
    remoteIds: List<Long>,
  ) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("DELETE FROM movies WHERE provider_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" AND category_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" AND stream_id NOT IN (")
    val _inputSize: Int = remoteIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        for (_item: Long in remoteIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun restoreWatchProgress(providerId: Long) {
    val _sql: String = """
        |
        |        UPDATE movies 
        |        SET watch_progress = (
        |            SELECT resume_position_ms FROM playback_history 
        |            WHERE playback_history.content_id = movies.id 
        |            AND playback_history.content_type = 'MOVIE'
        |            AND playback_history.provider_id = ?
        |        ),
        |            watch_count = COALESCE((
        |                SELECT watch_count FROM playback_history
        |                WHERE playback_history.content_id = movies.id
        |                AND playback_history.content_type = 'MOVIE'
        |                AND playback_history.provider_id = ?
        |            ), 0),
        |            last_watched_at = COALESCE((
        |                SELECT last_watched_at FROM playback_history
        |                WHERE playback_history.content_id = movies.id
        |                AND playback_history.content_type = 'MOVIE'
        |                AND playback_history.provider_id = ?
        |            ), 0)
        |        WHERE provider_id = ? AND EXISTS (
        |            SELECT 1 FROM playback_history 
        |            WHERE playback_history.content_id = movies.id
        |            AND playback_history.content_type = 'MOVIE' 
        |            AND playback_history.provider_id = ?
        |        )
        |    
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
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
        _argIndex = 5
        _stmt.bindLong(_argIndex, providerId)
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
    val _sql: String = "UPDATE movies SET is_user_protected = ? WHERE provider_id = ? AND category_id = ?"
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
    _stringBuilder.append("UPDATE movies SET is_user_protected = 0 WHERE provider_id = ")
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
