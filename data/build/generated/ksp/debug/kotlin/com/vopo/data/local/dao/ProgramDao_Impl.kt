package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.ProgramBrowseEntity
import com.vopo.`data`.local.entity.ProgramEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
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
public class ProgramDao_Impl(
  __db: RoomDatabase,
) : ProgramDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfProgramEntity: EntityInsertAdapter<ProgramEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfProgramEntity = object : EntityInsertAdapter<ProgramEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `programs` (`id`,`provider_id`,`channel_id`,`title`,`description`,`start_time`,`end_time`,`lang`,`rating`,`image_url`,`genre`,`category`,`has_archive`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ProgramEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.providerId)
        statement.bindText(3, entity.channelId)
        statement.bindText(4, entity.title)
        statement.bindText(5, entity.description)
        statement.bindLong(6, entity.startTime)
        statement.bindLong(7, entity.endTime)
        statement.bindText(8, entity.lang)
        val _tmpRating: String? = entity.rating
        if (_tmpRating == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpRating)
        }
        val _tmpImageUrl: String? = entity.imageUrl
        if (_tmpImageUrl == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpImageUrl)
        }
        val _tmpGenre: String? = entity.genre
        if (_tmpGenre == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpGenre)
        }
        val _tmpCategory: String? = entity.category
        if (_tmpCategory == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpCategory)
        }
        val _tmp: Int = if (entity.hasArchive) 1 else 0
        statement.bindLong(13, _tmp.toLong())
      }
    }
  }

  public override suspend fun insertAll(programs: List<ProgramEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfProgramEntity.insert(_connection, programs)
  }

  public override fun getForChannel(
    providerId: Long,
    channelId: String,
    startTime: Long,
    endTime: Long,
  ): Flow<List<ProgramBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT
        |            id,
        |            provider_id,
        |            channel_id,
        |            title,
        |            CASE
        |                WHEN LENGTH(description) > 600 THEN SUBSTR(description, 1, 600) || '...'
        |                ELSE description
        |            END AS description,
        |            start_time,
        |            end_time,
        |            lang,
        |                        rating,
        |                        image_url,
        |                        genre,
        |                        category,
        |            has_archive
        |        FROM programs
        |        WHERE provider_id = ?
        |          AND channel_id = ?
        |          AND end_time > ?
        |          AND start_time < ?
        |        ORDER BY start_time ASC
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("programs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, channelId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, startTime)
        _argIndex = 4
        _stmt.bindLong(_argIndex, endTime)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfProviderId: Int = 1
        val _columnIndexOfChannelId: Int = 2
        val _columnIndexOfTitle: Int = 3
        val _columnIndexOfDescription: Int = 4
        val _columnIndexOfStartTime: Int = 5
        val _columnIndexOfEndTime: Int = 6
        val _columnIndexOfLang: Int = 7
        val _columnIndexOfRating: Int = 8
        val _columnIndexOfImageUrl: Int = 9
        val _columnIndexOfGenre: Int = 10
        val _columnIndexOfCategory: Int = 11
        val _columnIndexOfHasArchive: Int = 12
        val _result: MutableList<ProgramBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProgramBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpChannelId: String
          _tmpChannelId = _stmt.getText(_columnIndexOfChannelId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpStartTime: Long
          _tmpStartTime = _stmt.getLong(_columnIndexOfStartTime)
          val _tmpEndTime: Long
          _tmpEndTime = _stmt.getLong(_columnIndexOfEndTime)
          val _tmpLang: String
          _tmpLang = _stmt.getText(_columnIndexOfLang)
          val _tmpRating: String?
          if (_stmt.isNull(_columnIndexOfRating)) {
            _tmpRating = null
          } else {
            _tmpRating = _stmt.getText(_columnIndexOfRating)
          }
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpGenre: String?
          if (_stmt.isNull(_columnIndexOfGenre)) {
            _tmpGenre = null
          } else {
            _tmpGenre = _stmt.getText(_columnIndexOfGenre)
          }
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
          val _tmpHasArchive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfHasArchive).toInt()
          _tmpHasArchive = _tmp != 0
          _item = ProgramBrowseEntity(_tmpId,_tmpProviderId,_tmpChannelId,_tmpTitle,_tmpDescription,_tmpStartTime,_tmpEndTime,_tmpLang,_tmpRating,_tmpImageUrl,_tmpGenre,_tmpCategory,_tmpHasArchive)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getForChannels(
    providerId: Long,
    channelIds: List<String>,
    startTime: Long,
    endTime: Long,
  ): Flow<List<ProgramBrowseEntity>> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        SELECT")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            id,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            provider_id,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            channel_id,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            title,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            CASE")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                WHEN LENGTH(description) > 600 THEN SUBSTR(description, 1, 600) || '...'")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                ELSE description")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            END AS description,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            start_time,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            end_time,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            lang,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        rating,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        image_url,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        genre,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        category,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            has_archive")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        FROM programs")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        WHERE provider_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND channel_id IN (")
    val _inputSize: Int = channelIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND end_time > ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND start_time < ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        ORDER BY channel_id ASC, start_time ASC")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        ")
    val _sql: String = _stringBuilder.toString()
    return createFlow(__db, false, arrayOf("programs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        for (_item: String in channelIds) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        _argIndex = 2 + _inputSize
        _stmt.bindLong(_argIndex, startTime)
        _argIndex = 3 + _inputSize
        _stmt.bindLong(_argIndex, endTime)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfProviderId: Int = 1
        val _columnIndexOfChannelId: Int = 2
        val _columnIndexOfTitle: Int = 3
        val _columnIndexOfDescription: Int = 4
        val _columnIndexOfStartTime: Int = 5
        val _columnIndexOfEndTime: Int = 6
        val _columnIndexOfLang: Int = 7
        val _columnIndexOfRating: Int = 8
        val _columnIndexOfImageUrl: Int = 9
        val _columnIndexOfGenre: Int = 10
        val _columnIndexOfCategory: Int = 11
        val _columnIndexOfHasArchive: Int = 12
        val _result: MutableList<ProgramBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: ProgramBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpChannelId: String
          _tmpChannelId = _stmt.getText(_columnIndexOfChannelId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpStartTime: Long
          _tmpStartTime = _stmt.getLong(_columnIndexOfStartTime)
          val _tmpEndTime: Long
          _tmpEndTime = _stmt.getLong(_columnIndexOfEndTime)
          val _tmpLang: String
          _tmpLang = _stmt.getText(_columnIndexOfLang)
          val _tmpRating: String?
          if (_stmt.isNull(_columnIndexOfRating)) {
            _tmpRating = null
          } else {
            _tmpRating = _stmt.getText(_columnIndexOfRating)
          }
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpGenre: String?
          if (_stmt.isNull(_columnIndexOfGenre)) {
            _tmpGenre = null
          } else {
            _tmpGenre = _stmt.getText(_columnIndexOfGenre)
          }
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
          val _tmpHasArchive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfHasArchive).toInt()
          _tmpHasArchive = _tmp != 0
          _item_1 = ProgramBrowseEntity(_tmpId,_tmpProviderId,_tmpChannelId,_tmpTitle,_tmpDescription,_tmpStartTime,_tmpEndTime,_tmpLang,_tmpRating,_tmpImageUrl,_tmpGenre,_tmpCategory,_tmpHasArchive)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getForCategory(
    providerId: Long,
    categoryId: Long,
    startTime: Long,
    endTime: Long,
  ): Flow<List<ProgramBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT
        |            programs.id,
        |            programs.provider_id,
        |            programs.channel_id,
        |            programs.title,
        |            CASE
        |                WHEN LENGTH(programs.description) > 600 THEN SUBSTR(programs.description, 1, 600) || '...'
        |                ELSE programs.description
        |            END AS description,
        |            programs.start_time,
        |            programs.end_time,
        |            programs.lang,
        |            programs.rating,
        |            programs.image_url,
        |            programs.genre,
        |            programs.category,
        |            programs.has_archive
        |        FROM programs
        |        INNER JOIN channels
        |            ON channels.provider_id = programs.provider_id
        |           AND (
        |               channels.epg_channel_id = programs.channel_id
        |               OR CAST(channels.stream_id AS TEXT) = programs.channel_id
        |           )
        |        WHERE programs.provider_id = ?
        |          AND channels.category_id = ?
        |          AND programs.end_time > ?
        |          AND programs.start_time < ?
        |        ORDER BY channels.number ASC, programs.channel_id ASC, programs.start_time ASC
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("programs", "channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, startTime)
        _argIndex = 4
        _stmt.bindLong(_argIndex, endTime)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfProviderId: Int = 1
        val _columnIndexOfChannelId: Int = 2
        val _columnIndexOfTitle: Int = 3
        val _columnIndexOfDescription: Int = 4
        val _columnIndexOfStartTime: Int = 5
        val _columnIndexOfEndTime: Int = 6
        val _columnIndexOfLang: Int = 7
        val _columnIndexOfRating: Int = 8
        val _columnIndexOfImageUrl: Int = 9
        val _columnIndexOfGenre: Int = 10
        val _columnIndexOfCategory: Int = 11
        val _columnIndexOfHasArchive: Int = 12
        val _result: MutableList<ProgramBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProgramBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpChannelId: String
          _tmpChannelId = _stmt.getText(_columnIndexOfChannelId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpStartTime: Long
          _tmpStartTime = _stmt.getLong(_columnIndexOfStartTime)
          val _tmpEndTime: Long
          _tmpEndTime = _stmt.getLong(_columnIndexOfEndTime)
          val _tmpLang: String
          _tmpLang = _stmt.getText(_columnIndexOfLang)
          val _tmpRating: String?
          if (_stmt.isNull(_columnIndexOfRating)) {
            _tmpRating = null
          } else {
            _tmpRating = _stmt.getText(_columnIndexOfRating)
          }
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpGenre: String?
          if (_stmt.isNull(_columnIndexOfGenre)) {
            _tmpGenre = null
          } else {
            _tmpGenre = _stmt.getText(_columnIndexOfGenre)
          }
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
          val _tmpHasArchive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfHasArchive).toInt()
          _tmpHasArchive = _tmp != 0
          _item = ProgramBrowseEntity(_tmpId,_tmpProviderId,_tmpChannelId,_tmpTitle,_tmpDescription,_tmpStartTime,_tmpEndTime,_tmpLang,_tmpRating,_tmpImageUrl,_tmpGenre,_tmpCategory,_tmpHasArchive)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun searchPrograms(
    providerId: Long,
    queryPattern: String,
    startTime: Long,
    endTime: Long,
    categoryId: Long?,
    limit: Int,
  ): Flow<List<ProgramBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT
        |            programs.id,
        |            programs.provider_id,
        |            programs.channel_id,
        |            programs.title,
        |            CASE
        |                WHEN LENGTH(programs.description) > 600 THEN SUBSTR(programs.description, 1, 600) || '...'
        |                ELSE programs.description
        |            END AS description,
        |            programs.start_time,
        |            programs.end_time,
        |            programs.lang,
        |                        programs.rating,
        |                        programs.image_url,
        |                        programs.genre,
        |                        programs.category,
        |            programs.has_archive
        |        FROM programs
        |        WHERE programs.provider_id = ?
        |          AND programs.end_time > ?
        |          AND programs.start_time < ?
        |          AND (
        |              LOWER(programs.title) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(programs.description) LIKE LOWER(?) ESCAPE '\'
        |          )
        |          AND (
        |              ? IS NULL
        |              OR EXISTS (
        |                  SELECT 1 FROM channels
        |                  WHERE channels.provider_id = programs.provider_id
        |                    AND (
        |                        channels.epg_channel_id = programs.channel_id
        |                        OR CAST(channels.stream_id AS TEXT) = programs.channel_id
        |                    )
        |                    AND channels.category_id = ?
        |              )
        |          )
        |        ORDER BY programs.start_time ASC, programs.channel_id ASC
        |        LIMIT ?
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("programs", "channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, startTime)
        _argIndex = 3
        _stmt.bindLong(_argIndex, endTime)
        _argIndex = 4
        _stmt.bindText(_argIndex, queryPattern)
        _argIndex = 5
        _stmt.bindText(_argIndex, queryPattern)
        _argIndex = 6
        if (categoryId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, categoryId)
        }
        _argIndex = 7
        if (categoryId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, categoryId)
        }
        _argIndex = 8
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfProviderId: Int = 1
        val _columnIndexOfChannelId: Int = 2
        val _columnIndexOfTitle: Int = 3
        val _columnIndexOfDescription: Int = 4
        val _columnIndexOfStartTime: Int = 5
        val _columnIndexOfEndTime: Int = 6
        val _columnIndexOfLang: Int = 7
        val _columnIndexOfRating: Int = 8
        val _columnIndexOfImageUrl: Int = 9
        val _columnIndexOfGenre: Int = 10
        val _columnIndexOfCategory: Int = 11
        val _columnIndexOfHasArchive: Int = 12
        val _result: MutableList<ProgramBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProgramBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpChannelId: String
          _tmpChannelId = _stmt.getText(_columnIndexOfChannelId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpStartTime: Long
          _tmpStartTime = _stmt.getLong(_columnIndexOfStartTime)
          val _tmpEndTime: Long
          _tmpEndTime = _stmt.getLong(_columnIndexOfEndTime)
          val _tmpLang: String
          _tmpLang = _stmt.getText(_columnIndexOfLang)
          val _tmpRating: String?
          if (_stmt.isNull(_columnIndexOfRating)) {
            _tmpRating = null
          } else {
            _tmpRating = _stmt.getText(_columnIndexOfRating)
          }
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpGenre: String?
          if (_stmt.isNull(_columnIndexOfGenre)) {
            _tmpGenre = null
          } else {
            _tmpGenre = _stmt.getText(_columnIndexOfGenre)
          }
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
          val _tmpHasArchive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfHasArchive).toInt()
          _tmpHasArchive = _tmp != 0
          _item = ProgramBrowseEntity(_tmpId,_tmpProviderId,_tmpChannelId,_tmpTitle,_tmpDescription,_tmpStartTime,_tmpEndTime,_tmpLang,_tmpRating,_tmpImageUrl,_tmpGenre,_tmpCategory,_tmpHasArchive)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getNowPlaying(
    providerId: Long,
    channelId: String,
    now: Long,
  ): Flow<ProgramBrowseEntity?> {
    val _sql: String = """
        |
        |        SELECT
        |            id,
        |            provider_id,
        |            channel_id,
        |            title,
        |            CASE
        |                WHEN LENGTH(description) > 600 THEN SUBSTR(description, 1, 600) || '...'
        |                ELSE description
        |            END AS description,
        |            start_time,
        |            end_time,
        |            lang,
        |                        rating,
        |                        image_url,
        |                        genre,
        |                        category,
        |            has_archive
        |        FROM programs
        |        WHERE provider_id = ?
        |          AND channel_id = ?
        |          AND start_time <= ?
        |          AND end_time > ?
        |        LIMIT 1
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("programs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, channelId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, now)
        _argIndex = 4
        _stmt.bindLong(_argIndex, now)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfProviderId: Int = 1
        val _columnIndexOfChannelId: Int = 2
        val _columnIndexOfTitle: Int = 3
        val _columnIndexOfDescription: Int = 4
        val _columnIndexOfStartTime: Int = 5
        val _columnIndexOfEndTime: Int = 6
        val _columnIndexOfLang: Int = 7
        val _columnIndexOfRating: Int = 8
        val _columnIndexOfImageUrl: Int = 9
        val _columnIndexOfGenre: Int = 10
        val _columnIndexOfCategory: Int = 11
        val _columnIndexOfHasArchive: Int = 12
        val _result: ProgramBrowseEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpChannelId: String
          _tmpChannelId = _stmt.getText(_columnIndexOfChannelId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpStartTime: Long
          _tmpStartTime = _stmt.getLong(_columnIndexOfStartTime)
          val _tmpEndTime: Long
          _tmpEndTime = _stmt.getLong(_columnIndexOfEndTime)
          val _tmpLang: String
          _tmpLang = _stmt.getText(_columnIndexOfLang)
          val _tmpRating: String?
          if (_stmt.isNull(_columnIndexOfRating)) {
            _tmpRating = null
          } else {
            _tmpRating = _stmt.getText(_columnIndexOfRating)
          }
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpGenre: String?
          if (_stmt.isNull(_columnIndexOfGenre)) {
            _tmpGenre = null
          } else {
            _tmpGenre = _stmt.getText(_columnIndexOfGenre)
          }
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
          val _tmpHasArchive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfHasArchive).toInt()
          _tmpHasArchive = _tmp != 0
          _result = ProgramBrowseEntity(_tmpId,_tmpProviderId,_tmpChannelId,_tmpTitle,_tmpDescription,_tmpStartTime,_tmpEndTime,_tmpLang,_tmpRating,_tmpImageUrl,_tmpGenre,_tmpCategory,_tmpHasArchive)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getNowPlayingForChannels(
    providerId: Long,
    channelIds: List<String>,
    now: Long,
  ): Flow<List<ProgramBrowseEntity>> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        SELECT")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            id,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            provider_id,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            channel_id,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            title,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            CASE")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                WHEN LENGTH(description) > 600 THEN SUBSTR(description, 1, 600) || '...'")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                ELSE description")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            END AS description,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            start_time,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            end_time,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            lang,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        rating,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        image_url,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        genre,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        category,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            has_archive")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        FROM programs")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        WHERE provider_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND channel_id IN (")
    val _inputSize: Int = channelIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND start_time <= ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND end_time > ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        ")
    val _sql: String = _stringBuilder.toString()
    return createFlow(__db, false, arrayOf("programs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        for (_item: String in channelIds) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        _argIndex = 2 + _inputSize
        _stmt.bindLong(_argIndex, now)
        _argIndex = 3 + _inputSize
        _stmt.bindLong(_argIndex, now)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfProviderId: Int = 1
        val _columnIndexOfChannelId: Int = 2
        val _columnIndexOfTitle: Int = 3
        val _columnIndexOfDescription: Int = 4
        val _columnIndexOfStartTime: Int = 5
        val _columnIndexOfEndTime: Int = 6
        val _columnIndexOfLang: Int = 7
        val _columnIndexOfRating: Int = 8
        val _columnIndexOfImageUrl: Int = 9
        val _columnIndexOfGenre: Int = 10
        val _columnIndexOfCategory: Int = 11
        val _columnIndexOfHasArchive: Int = 12
        val _result: MutableList<ProgramBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: ProgramBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpChannelId: String
          _tmpChannelId = _stmt.getText(_columnIndexOfChannelId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpStartTime: Long
          _tmpStartTime = _stmt.getLong(_columnIndexOfStartTime)
          val _tmpEndTime: Long
          _tmpEndTime = _stmt.getLong(_columnIndexOfEndTime)
          val _tmpLang: String
          _tmpLang = _stmt.getText(_columnIndexOfLang)
          val _tmpRating: String?
          if (_stmt.isNull(_columnIndexOfRating)) {
            _tmpRating = null
          } else {
            _tmpRating = _stmt.getText(_columnIndexOfRating)
          }
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpGenre: String?
          if (_stmt.isNull(_columnIndexOfGenre)) {
            _tmpGenre = null
          } else {
            _tmpGenre = _stmt.getText(_columnIndexOfGenre)
          }
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
          val _tmpHasArchive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfHasArchive).toInt()
          _tmpHasArchive = _tmp != 0
          _item_1 = ProgramBrowseEntity(_tmpId,_tmpProviderId,_tmpChannelId,_tmpTitle,_tmpDescription,_tmpStartTime,_tmpEndTime,_tmpLang,_tmpRating,_tmpImageUrl,_tmpGenre,_tmpCategory,_tmpHasArchive)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getNowPlayingForChannelsSync(
    providerId: Long,
    channelIds: List<String>,
    now: Long,
  ): List<ProgramBrowseEntity> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        SELECT")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            id,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            provider_id,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            channel_id,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            title,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            CASE")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                WHEN LENGTH(description) > 600 THEN SUBSTR(description, 1, 600) || '...'")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                ELSE description")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            END AS description,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            start_time,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            end_time,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            lang,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        rating,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        image_url,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        genre,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        category,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            has_archive")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        FROM programs")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        WHERE provider_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND channel_id IN (")
    val _inputSize: Int = channelIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND start_time <= ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND end_time > ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        ")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        for (_item: String in channelIds) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        _argIndex = 2 + _inputSize
        _stmt.bindLong(_argIndex, now)
        _argIndex = 3 + _inputSize
        _stmt.bindLong(_argIndex, now)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfProviderId: Int = 1
        val _columnIndexOfChannelId: Int = 2
        val _columnIndexOfTitle: Int = 3
        val _columnIndexOfDescription: Int = 4
        val _columnIndexOfStartTime: Int = 5
        val _columnIndexOfEndTime: Int = 6
        val _columnIndexOfLang: Int = 7
        val _columnIndexOfRating: Int = 8
        val _columnIndexOfImageUrl: Int = 9
        val _columnIndexOfGenre: Int = 10
        val _columnIndexOfCategory: Int = 11
        val _columnIndexOfHasArchive: Int = 12
        val _result: MutableList<ProgramBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: ProgramBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpChannelId: String
          _tmpChannelId = _stmt.getText(_columnIndexOfChannelId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpStartTime: Long
          _tmpStartTime = _stmt.getLong(_columnIndexOfStartTime)
          val _tmpEndTime: Long
          _tmpEndTime = _stmt.getLong(_columnIndexOfEndTime)
          val _tmpLang: String
          _tmpLang = _stmt.getText(_columnIndexOfLang)
          val _tmpRating: String?
          if (_stmt.isNull(_columnIndexOfRating)) {
            _tmpRating = null
          } else {
            _tmpRating = _stmt.getText(_columnIndexOfRating)
          }
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpGenre: String?
          if (_stmt.isNull(_columnIndexOfGenre)) {
            _tmpGenre = null
          } else {
            _tmpGenre = _stmt.getText(_columnIndexOfGenre)
          }
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
          val _tmpHasArchive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfHasArchive).toInt()
          _tmpHasArchive = _tmp != 0
          _item_1 = ProgramBrowseEntity(_tmpId,_tmpProviderId,_tmpChannelId,_tmpTitle,_tmpDescription,_tmpStartTime,_tmpEndTime,_tmpLang,_tmpRating,_tmpImageUrl,_tmpGenre,_tmpCategory,_tmpHasArchive)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getForChannelsSync(
    providerId: Long,
    channelIds: List<String>,
    startTime: Long,
    endTime: Long,
  ): List<ProgramBrowseEntity> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        SELECT")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            id,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            provider_id,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            channel_id,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            title,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            CASE")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                WHEN LENGTH(description) > 600 THEN SUBSTR(description, 1, 600) || '...'")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                ELSE description")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            END AS description,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            start_time,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            end_time,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            lang,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        rating,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        image_url,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        genre,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                        category,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("            has_archive")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        FROM programs")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        WHERE provider_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND channel_id IN (")
    val _inputSize: Int = channelIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND end_time > ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND start_time < ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        ORDER BY channel_id ASC, start_time ASC")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        ")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        for (_item: String in channelIds) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        _argIndex = 2 + _inputSize
        _stmt.bindLong(_argIndex, startTime)
        _argIndex = 3 + _inputSize
        _stmt.bindLong(_argIndex, endTime)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfProviderId: Int = 1
        val _columnIndexOfChannelId: Int = 2
        val _columnIndexOfTitle: Int = 3
        val _columnIndexOfDescription: Int = 4
        val _columnIndexOfStartTime: Int = 5
        val _columnIndexOfEndTime: Int = 6
        val _columnIndexOfLang: Int = 7
        val _columnIndexOfRating: Int = 8
        val _columnIndexOfImageUrl: Int = 9
        val _columnIndexOfGenre: Int = 10
        val _columnIndexOfCategory: Int = 11
        val _columnIndexOfHasArchive: Int = 12
        val _result: MutableList<ProgramBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: ProgramBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpChannelId: String
          _tmpChannelId = _stmt.getText(_columnIndexOfChannelId)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpStartTime: Long
          _tmpStartTime = _stmt.getLong(_columnIndexOfStartTime)
          val _tmpEndTime: Long
          _tmpEndTime = _stmt.getLong(_columnIndexOfEndTime)
          val _tmpLang: String
          _tmpLang = _stmt.getText(_columnIndexOfLang)
          val _tmpRating: String?
          if (_stmt.isNull(_columnIndexOfRating)) {
            _tmpRating = null
          } else {
            _tmpRating = _stmt.getText(_columnIndexOfRating)
          }
          val _tmpImageUrl: String?
          if (_stmt.isNull(_columnIndexOfImageUrl)) {
            _tmpImageUrl = null
          } else {
            _tmpImageUrl = _stmt.getText(_columnIndexOfImageUrl)
          }
          val _tmpGenre: String?
          if (_stmt.isNull(_columnIndexOfGenre)) {
            _tmpGenre = null
          } else {
            _tmpGenre = _stmt.getText(_columnIndexOfGenre)
          }
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
          val _tmpHasArchive: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfHasArchive).toInt()
          _tmpHasArchive = _tmp != 0
          _item_1 = ProgramBrowseEntity(_tmpId,_tmpProviderId,_tmpChannelId,_tmpTitle,_tmpDescription,_tmpStartTime,_tmpEndTime,_tmpLang,_tmpRating,_tmpImageUrl,_tmpGenre,_tmpCategory,_tmpHasArchive)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getChannelIdsWithPrograms(providerId: Long, channelIds: List<String>): List<String> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                SELECT DISTINCT channel_id")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                FROM programs")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                WHERE provider_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                    AND channel_id IN (")
    val _inputSize: Int = channelIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("                ")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        for (_item: String in channelIds) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        val _result: MutableList<String> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: String
          _item_1 = _stmt.getText(0)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun countByProvider(providerId: Long): Int {
    val _sql: String = "SELECT COUNT(*) FROM programs WHERE provider_id = ?"
    return performSuspending(__db, true, false) { _connection ->
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

  public override fun observeCountByProvider(providerId: Long): Flow<Int> {
    val _sql: String = "SELECT COUNT(*) FROM programs WHERE provider_id = ?"
    return createFlow(__db, false, arrayOf("programs")) { _connection ->
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

  public override suspend fun deleteOld(beforeTime: Long): Int {
    val _sql: String = "DELETE FROM programs WHERE end_time < ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, beforeTime)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByProvider(providerId: Long) {
    val _sql: String = "DELETE FROM programs WHERE provider_id = ?"
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

  public override suspend fun moveToProvider(sourceProviderId: Long, targetProviderId: Long) {
    val _sql: String = "UPDATE programs SET provider_id = ? WHERE provider_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, targetProviderId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, sourceProviderId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteForChannel(providerId: Long, channelId: String) {
    val _sql: String = "DELETE FROM programs WHERE provider_id = ? AND channel_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, channelId)
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
