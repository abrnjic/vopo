package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.EpgProgrammeEntity
import javax.`annotation`.processing.Generated
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

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class EpgProgrammeDao_Impl(
  __db: RoomDatabase,
) : EpgProgrammeDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfEpgProgrammeEntity: EntityInsertAdapter<EpgProgrammeEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfEpgProgrammeEntity = object : EntityInsertAdapter<EpgProgrammeEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `epg_programmes` (`id`,`epg_source_id`,`xmltv_channel_id`,`start_time`,`end_time`,`title`,`subtitle`,`description`,`category`,`lang`,`rating`,`image_url`,`episode_info`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: EpgProgrammeEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.epgSourceId)
        statement.bindText(3, entity.xmltvChannelId)
        statement.bindLong(4, entity.startTime)
        statement.bindLong(5, entity.endTime)
        statement.bindText(6, entity.title)
        val _tmpSubtitle: String? = entity.subtitle
        if (_tmpSubtitle == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpSubtitle)
        }
        statement.bindText(8, entity.description)
        val _tmpCategory: String? = entity.category
        if (_tmpCategory == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpCategory)
        }
        statement.bindText(10, entity.lang)
        val _tmpRating: String? = entity.rating
        if (_tmpRating == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpRating)
        }
        val _tmpImageUrl: String? = entity.imageUrl
        if (_tmpImageUrl == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpImageUrl)
        }
        val _tmpEpisodeInfo: String? = entity.episodeInfo
        if (_tmpEpisodeInfo == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpEpisodeInfo)
        }
      }
    }
  }

  public override suspend fun insertAll(programmes: List<EpgProgrammeEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfEpgProgrammeEntity.insert(_connection, programmes)
  }

  public override suspend fun getForChannel(
    sourceId: Long,
    channelId: String,
    startTime: Long,
    endTime: Long,
  ): List<EpgProgrammeEntity> {
    val _sql: String = """
        |
        |        SELECT * FROM epg_programmes
        |        WHERE epg_source_id = ?
        |          AND xmltv_channel_id = ?
        |          AND end_time > ?
        |          AND start_time < ?
        |        ORDER BY start_time ASC
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sourceId)
        _argIndex = 2
        _stmt.bindText(_argIndex, channelId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, startTime)
        _argIndex = 4
        _stmt.bindLong(_argIndex, endTime)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEpgSourceId: Int = getColumnIndexOrThrow(_stmt, "epg_source_id")
        val _columnIndexOfXmltvChannelId: Int = getColumnIndexOrThrow(_stmt, "xmltv_channel_id")
        val _columnIndexOfStartTime: Int = getColumnIndexOrThrow(_stmt, "start_time")
        val _columnIndexOfEndTime: Int = getColumnIndexOrThrow(_stmt, "end_time")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfSubtitle: Int = getColumnIndexOrThrow(_stmt, "subtitle")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _columnIndexOfLang: Int = getColumnIndexOrThrow(_stmt, "lang")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "image_url")
        val _columnIndexOfEpisodeInfo: Int = getColumnIndexOrThrow(_stmt, "episode_info")
        val _result: MutableList<EpgProgrammeEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: EpgProgrammeEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpEpgSourceId: Long
          _tmpEpgSourceId = _stmt.getLong(_columnIndexOfEpgSourceId)
          val _tmpXmltvChannelId: String
          _tmpXmltvChannelId = _stmt.getText(_columnIndexOfXmltvChannelId)
          val _tmpStartTime: Long
          _tmpStartTime = _stmt.getLong(_columnIndexOfStartTime)
          val _tmpEndTime: Long
          _tmpEndTime = _stmt.getLong(_columnIndexOfEndTime)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpSubtitle: String?
          if (_stmt.isNull(_columnIndexOfSubtitle)) {
            _tmpSubtitle = null
          } else {
            _tmpSubtitle = _stmt.getText(_columnIndexOfSubtitle)
          }
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
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
          val _tmpEpisodeInfo: String?
          if (_stmt.isNull(_columnIndexOfEpisodeInfo)) {
            _tmpEpisodeInfo = null
          } else {
            _tmpEpisodeInfo = _stmt.getText(_columnIndexOfEpisodeInfo)
          }
          _item = EpgProgrammeEntity(_tmpId,_tmpEpgSourceId,_tmpXmltvChannelId,_tmpStartTime,_tmpEndTime,_tmpTitle,_tmpSubtitle,_tmpDescription,_tmpCategory,_tmpLang,_tmpRating,_tmpImageUrl,_tmpEpisodeInfo)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getForChannels(
    sourceId: Long,
    channelIds: List<String>,
    startTime: Long,
    endTime: Long,
  ): List<EpgProgrammeEntity> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        SELECT * FROM epg_programmes")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        WHERE epg_source_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND xmltv_channel_id IN (")
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
    _stringBuilder.append("        ORDER BY xmltv_channel_id ASC, start_time ASC")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("    ")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sourceId)
        _argIndex = 2
        for (_item: String in channelIds) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        _argIndex = 2 + _inputSize
        _stmt.bindLong(_argIndex, startTime)
        _argIndex = 3 + _inputSize
        _stmt.bindLong(_argIndex, endTime)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEpgSourceId: Int = getColumnIndexOrThrow(_stmt, "epg_source_id")
        val _columnIndexOfXmltvChannelId: Int = getColumnIndexOrThrow(_stmt, "xmltv_channel_id")
        val _columnIndexOfStartTime: Int = getColumnIndexOrThrow(_stmt, "start_time")
        val _columnIndexOfEndTime: Int = getColumnIndexOrThrow(_stmt, "end_time")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfSubtitle: Int = getColumnIndexOrThrow(_stmt, "subtitle")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _columnIndexOfLang: Int = getColumnIndexOrThrow(_stmt, "lang")
        val _columnIndexOfRating: Int = getColumnIndexOrThrow(_stmt, "rating")
        val _columnIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "image_url")
        val _columnIndexOfEpisodeInfo: Int = getColumnIndexOrThrow(_stmt, "episode_info")
        val _result: MutableList<EpgProgrammeEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: EpgProgrammeEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpEpgSourceId: Long
          _tmpEpgSourceId = _stmt.getLong(_columnIndexOfEpgSourceId)
          val _tmpXmltvChannelId: String
          _tmpXmltvChannelId = _stmt.getText(_columnIndexOfXmltvChannelId)
          val _tmpStartTime: Long
          _tmpStartTime = _stmt.getLong(_columnIndexOfStartTime)
          val _tmpEndTime: Long
          _tmpEndTime = _stmt.getLong(_columnIndexOfEndTime)
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpSubtitle: String?
          if (_stmt.isNull(_columnIndexOfSubtitle)) {
            _tmpSubtitle = null
          } else {
            _tmpSubtitle = _stmt.getText(_columnIndexOfSubtitle)
          }
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpCategory: String?
          if (_stmt.isNull(_columnIndexOfCategory)) {
            _tmpCategory = null
          } else {
            _tmpCategory = _stmt.getText(_columnIndexOfCategory)
          }
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
          val _tmpEpisodeInfo: String?
          if (_stmt.isNull(_columnIndexOfEpisodeInfo)) {
            _tmpEpisodeInfo = null
          } else {
            _tmpEpisodeInfo = _stmt.getText(_columnIndexOfEpisodeInfo)
          }
          _item_1 = EpgProgrammeEntity(_tmpId,_tmpEpgSourceId,_tmpXmltvChannelId,_tmpStartTime,_tmpEndTime,_tmpTitle,_tmpSubtitle,_tmpDescription,_tmpCategory,_tmpLang,_tmpRating,_tmpImageUrl,_tmpEpisodeInfo)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun countBySource(sourceId: Long): Int {
    val _sql: String = "SELECT COUNT(*) FROM epg_programmes WHERE epg_source_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sourceId)
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

  public override suspend fun countUpcomingForChannel(
    sourceId: Long,
    channelId: String,
    now: Long,
  ): Int {
    val _sql: String = """
        |
        |        SELECT COUNT(*) FROM epg_programmes
        |        WHERE epg_source_id = ?
        |          AND xmltv_channel_id = ?
        |          AND end_time > ?
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sourceId)
        _argIndex = 2
        _stmt.bindText(_argIndex, channelId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, now)
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

  public override suspend fun deleteBySource(sourceId: Long) {
    val _sql: String = "DELETE FROM epg_programmes WHERE epg_source_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sourceId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun moveToSource(oldSourceId: Long, newSourceId: Long) {
    val _sql: String = "UPDATE epg_programmes SET epg_source_id = ? WHERE epg_source_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, newSourceId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, oldSourceId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteOld(beforeTime: Long): Int {
    val _sql: String = "DELETE FROM epg_programmes WHERE end_time < ?"
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

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
