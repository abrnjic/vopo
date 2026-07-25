package com.vopo.`data`.local.dao

import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class SearchDao_Impl(
  __db: RoomDatabase,
) : SearchDao() {
  private val __db: RoomDatabase
  init {
    this.__db = __db
  }

  public override fun searchAll(
    providerId: Long,
    ftsQuery: String,
    rawQuery: String,
    prefixLike: String,
    limitPerSection: Int,
  ): Flow<List<SearchHitEntity>> {
    val _sql: String = """
        |
        |        SELECT * FROM (
        |            SELECT 'LIVE' AS content_type,
        |                   c.id AS content_id,
        |                   c.name AS title,
        |                   0 AS section_rank,
        |                   CASE
        |                       WHEN LOWER(c.name) = LOWER(?) THEN 0
        |                       WHEN LOWER(c.name) LIKE LOWER(?) ESCAPE '\' THEN 1
        |                       ELSE 2
        |                   END AS match_rank
        |            FROM channels c
        |            JOIN channels_fts ON c.id = channels_fts.rowid
        |            WHERE c.provider_id = ?
        |              AND channels_fts MATCH ?
        |            ORDER BY match_rank ASC, c.name ASC
        |            LIMIT ?
        |        )
        |        UNION ALL
        |        SELECT * FROM (
        |            SELECT 'MOVIE' AS content_type,
        |                   m.id AS content_id,
        |                   m.name AS title,
        |                   1 AS section_rank,
        |                   CASE
        |                       WHEN LOWER(m.name) = LOWER(?) THEN 0
        |                       WHEN LOWER(m.name) LIKE LOWER(?) ESCAPE '\' THEN 1
        |                       ELSE 2
        |                   END AS match_rank
        |            FROM movies m
        |            JOIN movies_fts ON m.id = movies_fts.rowid
        |            WHERE m.provider_id = ?
        |              AND movies_fts MATCH ?
        |            ORDER BY match_rank ASC, m.name ASC
        |            LIMIT ?
        |        )
        |        UNION ALL
        |        SELECT * FROM (
        |            SELECT 'SERIES' AS content_type,
        |                   s.id AS content_id,
        |                   s.name AS title,
        |                   2 AS section_rank,
        |                   CASE
        |                       WHEN LOWER(s.name) = LOWER(?) THEN 0
        |                       WHEN LOWER(s.name) LIKE LOWER(?) ESCAPE '\' THEN 1
        |                       ELSE 2
        |                   END AS match_rank
        |            FROM series s
        |            JOIN series_fts ON s.id = series_fts.rowid
        |            WHERE s.provider_id = ?
        |              AND series_fts MATCH ?
        |            ORDER BY match_rank ASC, s.name ASC
        |            LIMIT ?
        |        )
        |        ORDER BY section_rank ASC, match_rank ASC, title ASC
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels", "channels_fts", "movies", "movies_fts", "series", "series_fts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, rawQuery)
        _argIndex = 2
        _stmt.bindText(_argIndex, prefixLike)
        _argIndex = 3
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 4
        _stmt.bindText(_argIndex, ftsQuery)
        _argIndex = 5
        _stmt.bindLong(_argIndex, limitPerSection.toLong())
        _argIndex = 6
        _stmt.bindText(_argIndex, rawQuery)
        _argIndex = 7
        _stmt.bindText(_argIndex, prefixLike)
        _argIndex = 8
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 9
        _stmt.bindText(_argIndex, ftsQuery)
        _argIndex = 10
        _stmt.bindLong(_argIndex, limitPerSection.toLong())
        _argIndex = 11
        _stmt.bindText(_argIndex, rawQuery)
        _argIndex = 12
        _stmt.bindText(_argIndex, prefixLike)
        _argIndex = 13
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 14
        _stmt.bindText(_argIndex, ftsQuery)
        _argIndex = 15
        _stmt.bindLong(_argIndex, limitPerSection.toLong())
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfSectionRank: Int = getColumnIndexOrThrow(_stmt, "section_rank")
        val _columnIndexOfMatchRank: Int = getColumnIndexOrThrow(_stmt, "match_rank")
        val _result: MutableList<SearchHitEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SearchHitEntity
          val _tmpContentType: String
          _tmpContentType = _stmt.getText(_columnIndexOfContentType)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpSectionRank: Int
          _tmpSectionRank = _stmt.getLong(_columnIndexOfSectionRank).toInt()
          val _tmpMatchRank: Int
          _tmpMatchRank = _stmt.getLong(_columnIndexOfMatchRank).toInt()
          _item = SearchHitEntity(_tmpContentType,_tmpContentId,_tmpTitle,_tmpSectionRank,_tmpMatchRank)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun searchLive(
    providerId: Long,
    ftsQuery: String,
    rawQuery: String,
    prefixLike: String,
    limitPerSection: Int,
  ): Flow<List<SearchHitEntity>> {
    val _sql: String = """
        |
        |        SELECT 'LIVE' AS content_type,
        |               c.id AS content_id,
        |               c.name AS title,
        |               0 AS section_rank,
        |               CASE
        |                   WHEN LOWER(c.name) = LOWER(?) THEN 0
        |                   WHEN LOWER(c.name) LIKE LOWER(?) ESCAPE '\' THEN 1
        |                   ELSE 2
        |               END AS match_rank
        |        FROM channels c
        |        JOIN channels_fts ON c.id = channels_fts.rowid
        |        WHERE c.provider_id = ?
        |          AND channels_fts MATCH ?
        |        ORDER BY match_rank ASC, c.name ASC
        |        LIMIT ?
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels", "channels_fts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, rawQuery)
        _argIndex = 2
        _stmt.bindText(_argIndex, prefixLike)
        _argIndex = 3
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 4
        _stmt.bindText(_argIndex, ftsQuery)
        _argIndex = 5
        _stmt.bindLong(_argIndex, limitPerSection.toLong())
        val _columnIndexOfContentType: Int = 0
        val _columnIndexOfContentId: Int = 1
        val _columnIndexOfTitle: Int = 2
        val _columnIndexOfSectionRank: Int = 3
        val _columnIndexOfMatchRank: Int = 4
        val _result: MutableList<SearchHitEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SearchHitEntity
          val _tmpContentType: String
          _tmpContentType = _stmt.getText(_columnIndexOfContentType)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpSectionRank: Int
          _tmpSectionRank = _stmt.getLong(_columnIndexOfSectionRank).toInt()
          val _tmpMatchRank: Int
          _tmpMatchRank = _stmt.getLong(_columnIndexOfMatchRank).toInt()
          _item = SearchHitEntity(_tmpContentType,_tmpContentId,_tmpTitle,_tmpSectionRank,_tmpMatchRank)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun searchMovies(
    providerId: Long,
    ftsQuery: String,
    rawQuery: String,
    prefixLike: String,
    limitPerSection: Int,
  ): Flow<List<SearchHitEntity>> {
    val _sql: String = """
        |
        |        SELECT 'MOVIE' AS content_type,
        |               m.id AS content_id,
        |               m.name AS title,
        |               1 AS section_rank,
        |               CASE
        |                   WHEN LOWER(m.name) = LOWER(?) THEN 0
        |                   WHEN LOWER(m.name) LIKE LOWER(?) ESCAPE '\' THEN 1
        |                   ELSE 2
        |               END AS match_rank
        |        FROM movies m
        |        JOIN movies_fts ON m.id = movies_fts.rowid
        |        WHERE m.provider_id = ?
        |          AND movies_fts MATCH ?
        |        ORDER BY match_rank ASC, m.name ASC
        |        LIMIT ?
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("movies", "movies_fts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, rawQuery)
        _argIndex = 2
        _stmt.bindText(_argIndex, prefixLike)
        _argIndex = 3
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 4
        _stmt.bindText(_argIndex, ftsQuery)
        _argIndex = 5
        _stmt.bindLong(_argIndex, limitPerSection.toLong())
        val _columnIndexOfContentType: Int = 0
        val _columnIndexOfContentId: Int = 1
        val _columnIndexOfTitle: Int = 2
        val _columnIndexOfSectionRank: Int = 3
        val _columnIndexOfMatchRank: Int = 4
        val _result: MutableList<SearchHitEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SearchHitEntity
          val _tmpContentType: String
          _tmpContentType = _stmt.getText(_columnIndexOfContentType)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpSectionRank: Int
          _tmpSectionRank = _stmt.getLong(_columnIndexOfSectionRank).toInt()
          val _tmpMatchRank: Int
          _tmpMatchRank = _stmt.getLong(_columnIndexOfMatchRank).toInt()
          _item = SearchHitEntity(_tmpContentType,_tmpContentId,_tmpTitle,_tmpSectionRank,_tmpMatchRank)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun searchSeries(
    providerId: Long,
    ftsQuery: String,
    rawQuery: String,
    prefixLike: String,
    limitPerSection: Int,
  ): Flow<List<SearchHitEntity>> {
    val _sql: String = """
        |
        |        SELECT 'SERIES' AS content_type,
        |               s.id AS content_id,
        |               s.name AS title,
        |               2 AS section_rank,
        |               CASE
        |                   WHEN LOWER(s.name) = LOWER(?) THEN 0
        |                   WHEN LOWER(s.name) LIKE LOWER(?) ESCAPE '\' THEN 1
        |                   ELSE 2
        |               END AS match_rank
        |        FROM series s
        |        JOIN series_fts ON s.id = series_fts.rowid
        |        WHERE s.provider_id = ?
        |          AND series_fts MATCH ?
        |        ORDER BY match_rank ASC, s.name ASC
        |        LIMIT ?
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("series", "series_fts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, rawQuery)
        _argIndex = 2
        _stmt.bindText(_argIndex, prefixLike)
        _argIndex = 3
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 4
        _stmt.bindText(_argIndex, ftsQuery)
        _argIndex = 5
        _stmt.bindLong(_argIndex, limitPerSection.toLong())
        val _columnIndexOfContentType: Int = 0
        val _columnIndexOfContentId: Int = 1
        val _columnIndexOfTitle: Int = 2
        val _columnIndexOfSectionRank: Int = 3
        val _columnIndexOfMatchRank: Int = 4
        val _result: MutableList<SearchHitEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SearchHitEntity
          val _tmpContentType: String
          _tmpContentType = _stmt.getText(_columnIndexOfContentType)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpSectionRank: Int
          _tmpSectionRank = _stmt.getLong(_columnIndexOfSectionRank).toInt()
          val _tmpMatchRank: Int
          _tmpMatchRank = _stmt.getLong(_columnIndexOfMatchRank).toInt()
          _item = SearchHitEntity(_tmpContentType,_tmpContentId,_tmpTitle,_tmpSectionRank,_tmpMatchRank)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
