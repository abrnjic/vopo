package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.RoomEnumConverters
import com.vopo.`data`.local.entity.TmdbIdentityEntity
import javax.`annotation`.processing.Generated
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class TmdbIdentityDao_Impl(
  __db: RoomDatabase,
) : TmdbIdentityDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfTmdbIdentityEntity: EntityInsertAdapter<TmdbIdentityEntity>

  private val __roomEnumConverters: RoomEnumConverters = RoomEnumConverters()
  init {
    this.__db = __db
    this.__insertAdapterOfTmdbIdentityEntity = object : EntityInsertAdapter<TmdbIdentityEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `tmdb_identity` (`tmdb_id`,`content_type`,`canonical_provider_id`,`first_seen_at`) VALUES (?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: TmdbIdentityEntity) {
        statement.bindLong(1, entity.tmdbId)
        val _tmp: String? = __roomEnumConverters.fromContentType(entity.contentType)
        if (_tmp == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmp)
        }
        statement.bindLong(3, entity.canonicalProviderId)
        statement.bindLong(4, entity.firstSeenAt)
      }
    }
  }

  public override suspend fun upsertAll(identities: List<TmdbIdentityEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfTmdbIdentityEntity.insert(_connection, identities)
  }

  public override suspend fun pruneOrphanedMovieIdentities() {
    val _sql: String = """
        |
        |        DELETE FROM tmdb_identity
        |        WHERE content_type = 'MOVIE'
        |          AND NOT EXISTS (
        |              SELECT 1 FROM movies
        |              WHERE movies.tmdb_id = tmdb_identity.tmdb_id
        |          )
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

  public override suspend fun pruneOrphanedSeriesIdentities() {
    val _sql: String = """
        |
        |        DELETE FROM tmdb_identity
        |        WHERE content_type = 'SERIES'
        |          AND NOT EXISTS (
        |              SELECT 1 FROM series
        |              WHERE series.tmdb_id = tmdb_identity.tmdb_id
        |          )
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

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
