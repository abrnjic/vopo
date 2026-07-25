package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.SyncMetadataEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class SyncMetadataDao_Impl(
  __db: RoomDatabase,
) : SyncMetadataDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfSyncMetadataEntity: EntityInsertAdapter<SyncMetadataEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfSyncMetadataEntity = object : EntityInsertAdapter<SyncMetadataEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `sync_metadata` (`provider_id`,`last_live_sync`,`last_live_success`,`last_movie_sync`,`last_series_sync`,`last_series_success`,`last_epg_sync`,`last_epg_success`,`last_movie_attempt`,`last_movie_success`,`last_movie_partial`,`live_count`,`movie_count`,`series_count`,`epg_count`,`last_sync_status`,`movie_sync_mode`,`movie_warnings_count`,`movie_catalog_stale`,`live_avoid_full_until`,`movie_avoid_full_until`,`series_avoid_full_until`,`live_sequential_failures_remembered`,`live_healthy_sync_streak`,`movie_parallel_failures_remembered`,`movie_healthy_sync_streak`,`series_sequential_failures_remembered`,`series_healthy_sync_streak`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SyncMetadataEntity) {
        statement.bindLong(1, entity.providerId)
        statement.bindLong(2, entity.lastLiveSync)
        statement.bindLong(3, entity.lastLiveSuccess)
        statement.bindLong(4, entity.lastMovieSync)
        statement.bindLong(5, entity.lastSeriesSync)
        statement.bindLong(6, entity.lastSeriesSuccess)
        statement.bindLong(7, entity.lastEpgSync)
        statement.bindLong(8, entity.lastEpgSuccess)
        statement.bindLong(9, entity.lastMovieAttempt)
        statement.bindLong(10, entity.lastMovieSuccess)
        statement.bindLong(11, entity.lastMoviePartial)
        statement.bindLong(12, entity.liveCount.toLong())
        statement.bindLong(13, entity.movieCount.toLong())
        statement.bindLong(14, entity.seriesCount.toLong())
        statement.bindLong(15, entity.epgCount.toLong())
        statement.bindText(16, entity.lastSyncStatus)
        statement.bindText(17, entity.movieSyncMode)
        statement.bindLong(18, entity.movieWarningsCount.toLong())
        val _tmp: Int = if (entity.movieCatalogStale) 1 else 0
        statement.bindLong(19, _tmp.toLong())
        statement.bindLong(20, entity.liveAvoidFullUntil)
        statement.bindLong(21, entity.movieAvoidFullUntil)
        statement.bindLong(22, entity.seriesAvoidFullUntil)
        val _tmp_1: Int = if (entity.liveSequentialFailuresRemembered) 1 else 0
        statement.bindLong(23, _tmp_1.toLong())
        statement.bindLong(24, entity.liveHealthySyncStreak.toLong())
        val _tmp_2: Int = if (entity.movieParallelFailuresRemembered) 1 else 0
        statement.bindLong(25, _tmp_2.toLong())
        statement.bindLong(26, entity.movieHealthySyncStreak.toLong())
        val _tmp_3: Int = if (entity.seriesSequentialFailuresRemembered) 1 else 0
        statement.bindLong(27, _tmp_3.toLong())
        statement.bindLong(28, entity.seriesHealthySyncStreak.toLong())
      }
    }
  }

  public override suspend fun insertOrUpdate(metadata: SyncMetadataEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfSyncMetadataEntity.insert(_connection, metadata)
  }

  public override fun `get`(providerId: Long): Flow<SyncMetadataEntity?> {
    val _sql: String = "SELECT * FROM sync_metadata WHERE provider_id = ?"
    return createFlow(__db, false, arrayOf("sync_metadata")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfLastLiveSync: Int = getColumnIndexOrThrow(_stmt, "last_live_sync")
        val _columnIndexOfLastLiveSuccess: Int = getColumnIndexOrThrow(_stmt, "last_live_success")
        val _columnIndexOfLastMovieSync: Int = getColumnIndexOrThrow(_stmt, "last_movie_sync")
        val _columnIndexOfLastSeriesSync: Int = getColumnIndexOrThrow(_stmt, "last_series_sync")
        val _columnIndexOfLastSeriesSuccess: Int = getColumnIndexOrThrow(_stmt, "last_series_success")
        val _columnIndexOfLastEpgSync: Int = getColumnIndexOrThrow(_stmt, "last_epg_sync")
        val _columnIndexOfLastEpgSuccess: Int = getColumnIndexOrThrow(_stmt, "last_epg_success")
        val _columnIndexOfLastMovieAttempt: Int = getColumnIndexOrThrow(_stmt, "last_movie_attempt")
        val _columnIndexOfLastMovieSuccess: Int = getColumnIndexOrThrow(_stmt, "last_movie_success")
        val _columnIndexOfLastMoviePartial: Int = getColumnIndexOrThrow(_stmt, "last_movie_partial")
        val _columnIndexOfLiveCount: Int = getColumnIndexOrThrow(_stmt, "live_count")
        val _columnIndexOfMovieCount: Int = getColumnIndexOrThrow(_stmt, "movie_count")
        val _columnIndexOfSeriesCount: Int = getColumnIndexOrThrow(_stmt, "series_count")
        val _columnIndexOfEpgCount: Int = getColumnIndexOrThrow(_stmt, "epg_count")
        val _columnIndexOfLastSyncStatus: Int = getColumnIndexOrThrow(_stmt, "last_sync_status")
        val _columnIndexOfMovieSyncMode: Int = getColumnIndexOrThrow(_stmt, "movie_sync_mode")
        val _columnIndexOfMovieWarningsCount: Int = getColumnIndexOrThrow(_stmt, "movie_warnings_count")
        val _columnIndexOfMovieCatalogStale: Int = getColumnIndexOrThrow(_stmt, "movie_catalog_stale")
        val _columnIndexOfLiveAvoidFullUntil: Int = getColumnIndexOrThrow(_stmt, "live_avoid_full_until")
        val _columnIndexOfMovieAvoidFullUntil: Int = getColumnIndexOrThrow(_stmt, "movie_avoid_full_until")
        val _columnIndexOfSeriesAvoidFullUntil: Int = getColumnIndexOrThrow(_stmt, "series_avoid_full_until")
        val _columnIndexOfLiveSequentialFailuresRemembered: Int = getColumnIndexOrThrow(_stmt, "live_sequential_failures_remembered")
        val _columnIndexOfLiveHealthySyncStreak: Int = getColumnIndexOrThrow(_stmt, "live_healthy_sync_streak")
        val _columnIndexOfMovieParallelFailuresRemembered: Int = getColumnIndexOrThrow(_stmt, "movie_parallel_failures_remembered")
        val _columnIndexOfMovieHealthySyncStreak: Int = getColumnIndexOrThrow(_stmt, "movie_healthy_sync_streak")
        val _columnIndexOfSeriesSequentialFailuresRemembered: Int = getColumnIndexOrThrow(_stmt, "series_sequential_failures_remembered")
        val _columnIndexOfSeriesHealthySyncStreak: Int = getColumnIndexOrThrow(_stmt, "series_healthy_sync_streak")
        val _result: SyncMetadataEntity?
        if (_stmt.step()) {
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpLastLiveSync: Long
          _tmpLastLiveSync = _stmt.getLong(_columnIndexOfLastLiveSync)
          val _tmpLastLiveSuccess: Long
          _tmpLastLiveSuccess = _stmt.getLong(_columnIndexOfLastLiveSuccess)
          val _tmpLastMovieSync: Long
          _tmpLastMovieSync = _stmt.getLong(_columnIndexOfLastMovieSync)
          val _tmpLastSeriesSync: Long
          _tmpLastSeriesSync = _stmt.getLong(_columnIndexOfLastSeriesSync)
          val _tmpLastSeriesSuccess: Long
          _tmpLastSeriesSuccess = _stmt.getLong(_columnIndexOfLastSeriesSuccess)
          val _tmpLastEpgSync: Long
          _tmpLastEpgSync = _stmt.getLong(_columnIndexOfLastEpgSync)
          val _tmpLastEpgSuccess: Long
          _tmpLastEpgSuccess = _stmt.getLong(_columnIndexOfLastEpgSuccess)
          val _tmpLastMovieAttempt: Long
          _tmpLastMovieAttempt = _stmt.getLong(_columnIndexOfLastMovieAttempt)
          val _tmpLastMovieSuccess: Long
          _tmpLastMovieSuccess = _stmt.getLong(_columnIndexOfLastMovieSuccess)
          val _tmpLastMoviePartial: Long
          _tmpLastMoviePartial = _stmt.getLong(_columnIndexOfLastMoviePartial)
          val _tmpLiveCount: Int
          _tmpLiveCount = _stmt.getLong(_columnIndexOfLiveCount).toInt()
          val _tmpMovieCount: Int
          _tmpMovieCount = _stmt.getLong(_columnIndexOfMovieCount).toInt()
          val _tmpSeriesCount: Int
          _tmpSeriesCount = _stmt.getLong(_columnIndexOfSeriesCount).toInt()
          val _tmpEpgCount: Int
          _tmpEpgCount = _stmt.getLong(_columnIndexOfEpgCount).toInt()
          val _tmpLastSyncStatus: String
          _tmpLastSyncStatus = _stmt.getText(_columnIndexOfLastSyncStatus)
          val _tmpMovieSyncMode: String
          _tmpMovieSyncMode = _stmt.getText(_columnIndexOfMovieSyncMode)
          val _tmpMovieWarningsCount: Int
          _tmpMovieWarningsCount = _stmt.getLong(_columnIndexOfMovieWarningsCount).toInt()
          val _tmpMovieCatalogStale: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfMovieCatalogStale).toInt()
          _tmpMovieCatalogStale = _tmp != 0
          val _tmpLiveAvoidFullUntil: Long
          _tmpLiveAvoidFullUntil = _stmt.getLong(_columnIndexOfLiveAvoidFullUntil)
          val _tmpMovieAvoidFullUntil: Long
          _tmpMovieAvoidFullUntil = _stmt.getLong(_columnIndexOfMovieAvoidFullUntil)
          val _tmpSeriesAvoidFullUntil: Long
          _tmpSeriesAvoidFullUntil = _stmt.getLong(_columnIndexOfSeriesAvoidFullUntil)
          val _tmpLiveSequentialFailuresRemembered: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfLiveSequentialFailuresRemembered).toInt()
          _tmpLiveSequentialFailuresRemembered = _tmp_1 != 0
          val _tmpLiveHealthySyncStreak: Int
          _tmpLiveHealthySyncStreak = _stmt.getLong(_columnIndexOfLiveHealthySyncStreak).toInt()
          val _tmpMovieParallelFailuresRemembered: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfMovieParallelFailuresRemembered).toInt()
          _tmpMovieParallelFailuresRemembered = _tmp_2 != 0
          val _tmpMovieHealthySyncStreak: Int
          _tmpMovieHealthySyncStreak = _stmt.getLong(_columnIndexOfMovieHealthySyncStreak).toInt()
          val _tmpSeriesSequentialFailuresRemembered: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfSeriesSequentialFailuresRemembered).toInt()
          _tmpSeriesSequentialFailuresRemembered = _tmp_3 != 0
          val _tmpSeriesHealthySyncStreak: Int
          _tmpSeriesHealthySyncStreak = _stmt.getLong(_columnIndexOfSeriesHealthySyncStreak).toInt()
          _result = SyncMetadataEntity(_tmpProviderId,_tmpLastLiveSync,_tmpLastLiveSuccess,_tmpLastMovieSync,_tmpLastSeriesSync,_tmpLastSeriesSuccess,_tmpLastEpgSync,_tmpLastEpgSuccess,_tmpLastMovieAttempt,_tmpLastMovieSuccess,_tmpLastMoviePartial,_tmpLiveCount,_tmpMovieCount,_tmpSeriesCount,_tmpEpgCount,_tmpLastSyncStatus,_tmpMovieSyncMode,_tmpMovieWarningsCount,_tmpMovieCatalogStale,_tmpLiveAvoidFullUntil,_tmpMovieAvoidFullUntil,_tmpSeriesAvoidFullUntil,_tmpLiveSequentialFailuresRemembered,_tmpLiveHealthySyncStreak,_tmpMovieParallelFailuresRemembered,_tmpMovieHealthySyncStreak,_tmpSeriesSequentialFailuresRemembered,_tmpSeriesHealthySyncStreak)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getSync(providerId: Long): SyncMetadataEntity? {
    val _sql: String = "SELECT * FROM sync_metadata WHERE provider_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfLastLiveSync: Int = getColumnIndexOrThrow(_stmt, "last_live_sync")
        val _columnIndexOfLastLiveSuccess: Int = getColumnIndexOrThrow(_stmt, "last_live_success")
        val _columnIndexOfLastMovieSync: Int = getColumnIndexOrThrow(_stmt, "last_movie_sync")
        val _columnIndexOfLastSeriesSync: Int = getColumnIndexOrThrow(_stmt, "last_series_sync")
        val _columnIndexOfLastSeriesSuccess: Int = getColumnIndexOrThrow(_stmt, "last_series_success")
        val _columnIndexOfLastEpgSync: Int = getColumnIndexOrThrow(_stmt, "last_epg_sync")
        val _columnIndexOfLastEpgSuccess: Int = getColumnIndexOrThrow(_stmt, "last_epg_success")
        val _columnIndexOfLastMovieAttempt: Int = getColumnIndexOrThrow(_stmt, "last_movie_attempt")
        val _columnIndexOfLastMovieSuccess: Int = getColumnIndexOrThrow(_stmt, "last_movie_success")
        val _columnIndexOfLastMoviePartial: Int = getColumnIndexOrThrow(_stmt, "last_movie_partial")
        val _columnIndexOfLiveCount: Int = getColumnIndexOrThrow(_stmt, "live_count")
        val _columnIndexOfMovieCount: Int = getColumnIndexOrThrow(_stmt, "movie_count")
        val _columnIndexOfSeriesCount: Int = getColumnIndexOrThrow(_stmt, "series_count")
        val _columnIndexOfEpgCount: Int = getColumnIndexOrThrow(_stmt, "epg_count")
        val _columnIndexOfLastSyncStatus: Int = getColumnIndexOrThrow(_stmt, "last_sync_status")
        val _columnIndexOfMovieSyncMode: Int = getColumnIndexOrThrow(_stmt, "movie_sync_mode")
        val _columnIndexOfMovieWarningsCount: Int = getColumnIndexOrThrow(_stmt, "movie_warnings_count")
        val _columnIndexOfMovieCatalogStale: Int = getColumnIndexOrThrow(_stmt, "movie_catalog_stale")
        val _columnIndexOfLiveAvoidFullUntil: Int = getColumnIndexOrThrow(_stmt, "live_avoid_full_until")
        val _columnIndexOfMovieAvoidFullUntil: Int = getColumnIndexOrThrow(_stmt, "movie_avoid_full_until")
        val _columnIndexOfSeriesAvoidFullUntil: Int = getColumnIndexOrThrow(_stmt, "series_avoid_full_until")
        val _columnIndexOfLiveSequentialFailuresRemembered: Int = getColumnIndexOrThrow(_stmt, "live_sequential_failures_remembered")
        val _columnIndexOfLiveHealthySyncStreak: Int = getColumnIndexOrThrow(_stmt, "live_healthy_sync_streak")
        val _columnIndexOfMovieParallelFailuresRemembered: Int = getColumnIndexOrThrow(_stmt, "movie_parallel_failures_remembered")
        val _columnIndexOfMovieHealthySyncStreak: Int = getColumnIndexOrThrow(_stmt, "movie_healthy_sync_streak")
        val _columnIndexOfSeriesSequentialFailuresRemembered: Int = getColumnIndexOrThrow(_stmt, "series_sequential_failures_remembered")
        val _columnIndexOfSeriesHealthySyncStreak: Int = getColumnIndexOrThrow(_stmt, "series_healthy_sync_streak")
        val _result: SyncMetadataEntity?
        if (_stmt.step()) {
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpLastLiveSync: Long
          _tmpLastLiveSync = _stmt.getLong(_columnIndexOfLastLiveSync)
          val _tmpLastLiveSuccess: Long
          _tmpLastLiveSuccess = _stmt.getLong(_columnIndexOfLastLiveSuccess)
          val _tmpLastMovieSync: Long
          _tmpLastMovieSync = _stmt.getLong(_columnIndexOfLastMovieSync)
          val _tmpLastSeriesSync: Long
          _tmpLastSeriesSync = _stmt.getLong(_columnIndexOfLastSeriesSync)
          val _tmpLastSeriesSuccess: Long
          _tmpLastSeriesSuccess = _stmt.getLong(_columnIndexOfLastSeriesSuccess)
          val _tmpLastEpgSync: Long
          _tmpLastEpgSync = _stmt.getLong(_columnIndexOfLastEpgSync)
          val _tmpLastEpgSuccess: Long
          _tmpLastEpgSuccess = _stmt.getLong(_columnIndexOfLastEpgSuccess)
          val _tmpLastMovieAttempt: Long
          _tmpLastMovieAttempt = _stmt.getLong(_columnIndexOfLastMovieAttempt)
          val _tmpLastMovieSuccess: Long
          _tmpLastMovieSuccess = _stmt.getLong(_columnIndexOfLastMovieSuccess)
          val _tmpLastMoviePartial: Long
          _tmpLastMoviePartial = _stmt.getLong(_columnIndexOfLastMoviePartial)
          val _tmpLiveCount: Int
          _tmpLiveCount = _stmt.getLong(_columnIndexOfLiveCount).toInt()
          val _tmpMovieCount: Int
          _tmpMovieCount = _stmt.getLong(_columnIndexOfMovieCount).toInt()
          val _tmpSeriesCount: Int
          _tmpSeriesCount = _stmt.getLong(_columnIndexOfSeriesCount).toInt()
          val _tmpEpgCount: Int
          _tmpEpgCount = _stmt.getLong(_columnIndexOfEpgCount).toInt()
          val _tmpLastSyncStatus: String
          _tmpLastSyncStatus = _stmt.getText(_columnIndexOfLastSyncStatus)
          val _tmpMovieSyncMode: String
          _tmpMovieSyncMode = _stmt.getText(_columnIndexOfMovieSyncMode)
          val _tmpMovieWarningsCount: Int
          _tmpMovieWarningsCount = _stmt.getLong(_columnIndexOfMovieWarningsCount).toInt()
          val _tmpMovieCatalogStale: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfMovieCatalogStale).toInt()
          _tmpMovieCatalogStale = _tmp != 0
          val _tmpLiveAvoidFullUntil: Long
          _tmpLiveAvoidFullUntil = _stmt.getLong(_columnIndexOfLiveAvoidFullUntil)
          val _tmpMovieAvoidFullUntil: Long
          _tmpMovieAvoidFullUntil = _stmt.getLong(_columnIndexOfMovieAvoidFullUntil)
          val _tmpSeriesAvoidFullUntil: Long
          _tmpSeriesAvoidFullUntil = _stmt.getLong(_columnIndexOfSeriesAvoidFullUntil)
          val _tmpLiveSequentialFailuresRemembered: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfLiveSequentialFailuresRemembered).toInt()
          _tmpLiveSequentialFailuresRemembered = _tmp_1 != 0
          val _tmpLiveHealthySyncStreak: Int
          _tmpLiveHealthySyncStreak = _stmt.getLong(_columnIndexOfLiveHealthySyncStreak).toInt()
          val _tmpMovieParallelFailuresRemembered: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfMovieParallelFailuresRemembered).toInt()
          _tmpMovieParallelFailuresRemembered = _tmp_2 != 0
          val _tmpMovieHealthySyncStreak: Int
          _tmpMovieHealthySyncStreak = _stmt.getLong(_columnIndexOfMovieHealthySyncStreak).toInt()
          val _tmpSeriesSequentialFailuresRemembered: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfSeriesSequentialFailuresRemembered).toInt()
          _tmpSeriesSequentialFailuresRemembered = _tmp_3 != 0
          val _tmpSeriesHealthySyncStreak: Int
          _tmpSeriesHealthySyncStreak = _stmt.getLong(_columnIndexOfSeriesHealthySyncStreak).toInt()
          _result = SyncMetadataEntity(_tmpProviderId,_tmpLastLiveSync,_tmpLastLiveSuccess,_tmpLastMovieSync,_tmpLastSeriesSync,_tmpLastSeriesSuccess,_tmpLastEpgSync,_tmpLastEpgSuccess,_tmpLastMovieAttempt,_tmpLastMovieSuccess,_tmpLastMoviePartial,_tmpLiveCount,_tmpMovieCount,_tmpSeriesCount,_tmpEpgCount,_tmpLastSyncStatus,_tmpMovieSyncMode,_tmpMovieWarningsCount,_tmpMovieCatalogStale,_tmpLiveAvoidFullUntil,_tmpMovieAvoidFullUntil,_tmpSeriesAvoidFullUntil,_tmpLiveSequentialFailuresRemembered,_tmpLiveHealthySyncStreak,_tmpMovieParallelFailuresRemembered,_tmpMovieHealthySyncStreak,_tmpSeriesSequentialFailuresRemembered,_tmpSeriesHealthySyncStreak)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(providerId: Long) {
    val _sql: String = "DELETE FROM sync_metadata WHERE provider_id = ?"
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
