package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.EpgChannelEntity
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
public class EpgChannelDao_Impl(
  __db: RoomDatabase,
) : EpgChannelDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfEpgChannelEntity: EntityInsertAdapter<EpgChannelEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfEpgChannelEntity = object : EntityInsertAdapter<EpgChannelEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `epg_channels` (`id`,`epg_source_id`,`xmltv_channel_id`,`display_name`,`normalized_name`,`icon_url`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: EpgChannelEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.epgSourceId)
        statement.bindText(3, entity.xmltvChannelId)
        statement.bindText(4, entity.displayName)
        statement.bindText(5, entity.normalizedName)
        val _tmpIconUrl: String? = entity.iconUrl
        if (_tmpIconUrl == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpIconUrl)
        }
      }
    }
  }

  public override suspend fun insertAll(channels: List<EpgChannelEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfEpgChannelEntity.insert(_connection, channels)
  }

  public override suspend fun getBySource(sourceId: Long): List<EpgChannelEntity> {
    val _sql: String = "SELECT * FROM epg_channels WHERE epg_source_id = ? ORDER BY display_name ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sourceId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEpgSourceId: Int = getColumnIndexOrThrow(_stmt, "epg_source_id")
        val _columnIndexOfXmltvChannelId: Int = getColumnIndexOrThrow(_stmt, "xmltv_channel_id")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "display_name")
        val _columnIndexOfNormalizedName: Int = getColumnIndexOrThrow(_stmt, "normalized_name")
        val _columnIndexOfIconUrl: Int = getColumnIndexOrThrow(_stmt, "icon_url")
        val _result: MutableList<EpgChannelEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: EpgChannelEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpEpgSourceId: Long
          _tmpEpgSourceId = _stmt.getLong(_columnIndexOfEpgSourceId)
          val _tmpXmltvChannelId: String
          _tmpXmltvChannelId = _stmt.getText(_columnIndexOfXmltvChannelId)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          val _tmpNormalizedName: String
          _tmpNormalizedName = _stmt.getText(_columnIndexOfNormalizedName)
          val _tmpIconUrl: String?
          if (_stmt.isNull(_columnIndexOfIconUrl)) {
            _tmpIconUrl = null
          } else {
            _tmpIconUrl = _stmt.getText(_columnIndexOfIconUrl)
          }
          _item = EpgChannelEntity(_tmpId,_tmpEpgSourceId,_tmpXmltvChannelId,_tmpDisplayName,_tmpNormalizedName,_tmpIconUrl)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun searchBySource(
    sourceId: Long,
    pattern: String,
    limit: Int,
  ): List<EpgChannelEntity> {
    val _sql: String = """
        |
        |        SELECT * FROM epg_channels
        |        WHERE epg_source_id = ?
        |          AND (LOWER(xmltv_channel_id) LIKE LOWER(?) ESCAPE '\'
        |               OR LOWER(display_name) LIKE LOWER(?) ESCAPE '\'
        |               OR LOWER(normalized_name) LIKE LOWER(?) ESCAPE '\')
        |        ORDER BY display_name ASC
        |        LIMIT ?
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sourceId)
        _argIndex = 2
        _stmt.bindText(_argIndex, pattern)
        _argIndex = 3
        _stmt.bindText(_argIndex, pattern)
        _argIndex = 4
        _stmt.bindText(_argIndex, pattern)
        _argIndex = 5
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEpgSourceId: Int = getColumnIndexOrThrow(_stmt, "epg_source_id")
        val _columnIndexOfXmltvChannelId: Int = getColumnIndexOrThrow(_stmt, "xmltv_channel_id")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "display_name")
        val _columnIndexOfNormalizedName: Int = getColumnIndexOrThrow(_stmt, "normalized_name")
        val _columnIndexOfIconUrl: Int = getColumnIndexOrThrow(_stmt, "icon_url")
        val _result: MutableList<EpgChannelEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: EpgChannelEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpEpgSourceId: Long
          _tmpEpgSourceId = _stmt.getLong(_columnIndexOfEpgSourceId)
          val _tmpXmltvChannelId: String
          _tmpXmltvChannelId = _stmt.getText(_columnIndexOfXmltvChannelId)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          val _tmpNormalizedName: String
          _tmpNormalizedName = _stmt.getText(_columnIndexOfNormalizedName)
          val _tmpIconUrl: String?
          if (_stmt.isNull(_columnIndexOfIconUrl)) {
            _tmpIconUrl = null
          } else {
            _tmpIconUrl = _stmt.getText(_columnIndexOfIconUrl)
          }
          _item = EpgChannelEntity(_tmpId,_tmpEpgSourceId,_tmpXmltvChannelId,_tmpDisplayName,_tmpNormalizedName,_tmpIconUrl)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getBySourceAndChannelId(sourceId: Long, channelId: String): EpgChannelEntity? {
    val _sql: String = "SELECT * FROM epg_channels WHERE epg_source_id = ? AND xmltv_channel_id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, sourceId)
        _argIndex = 2
        _stmt.bindText(_argIndex, channelId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEpgSourceId: Int = getColumnIndexOrThrow(_stmt, "epg_source_id")
        val _columnIndexOfXmltvChannelId: Int = getColumnIndexOrThrow(_stmt, "xmltv_channel_id")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "display_name")
        val _columnIndexOfNormalizedName: Int = getColumnIndexOrThrow(_stmt, "normalized_name")
        val _columnIndexOfIconUrl: Int = getColumnIndexOrThrow(_stmt, "icon_url")
        val _result: EpgChannelEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpEpgSourceId: Long
          _tmpEpgSourceId = _stmt.getLong(_columnIndexOfEpgSourceId)
          val _tmpXmltvChannelId: String
          _tmpXmltvChannelId = _stmt.getText(_columnIndexOfXmltvChannelId)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          val _tmpNormalizedName: String
          _tmpNormalizedName = _stmt.getText(_columnIndexOfNormalizedName)
          val _tmpIconUrl: String?
          if (_stmt.isNull(_columnIndexOfIconUrl)) {
            _tmpIconUrl = null
          } else {
            _tmpIconUrl = _stmt.getText(_columnIndexOfIconUrl)
          }
          _result = EpgChannelEntity(_tmpId,_tmpEpgSourceId,_tmpXmltvChannelId,_tmpDisplayName,_tmpNormalizedName,_tmpIconUrl)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getBySources(sourceIds: List<Long>): List<EpgChannelEntity> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM epg_channels WHERE epg_source_id IN (")
    val _inputSize: Int = sourceIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in sourceIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfEpgSourceId: Int = getColumnIndexOrThrow(_stmt, "epg_source_id")
        val _columnIndexOfXmltvChannelId: Int = getColumnIndexOrThrow(_stmt, "xmltv_channel_id")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "display_name")
        val _columnIndexOfNormalizedName: Int = getColumnIndexOrThrow(_stmt, "normalized_name")
        val _columnIndexOfIconUrl: Int = getColumnIndexOrThrow(_stmt, "icon_url")
        val _result: MutableList<EpgChannelEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: EpgChannelEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpEpgSourceId: Long
          _tmpEpgSourceId = _stmt.getLong(_columnIndexOfEpgSourceId)
          val _tmpXmltvChannelId: String
          _tmpXmltvChannelId = _stmt.getText(_columnIndexOfXmltvChannelId)
          val _tmpDisplayName: String
          _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          val _tmpNormalizedName: String
          _tmpNormalizedName = _stmt.getText(_columnIndexOfNormalizedName)
          val _tmpIconUrl: String?
          if (_stmt.isNull(_columnIndexOfIconUrl)) {
            _tmpIconUrl = null
          } else {
            _tmpIconUrl = _stmt.getText(_columnIndexOfIconUrl)
          }
          _item_1 = EpgChannelEntity(_tmpId,_tmpEpgSourceId,_tmpXmltvChannelId,_tmpDisplayName,_tmpNormalizedName,_tmpIconUrl)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteBySource(sourceId: Long) {
    val _sql: String = "DELETE FROM epg_channels WHERE epg_source_id = ?"
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
    val _sql: String = "UPDATE epg_channels SET epg_source_id = ? WHERE epg_source_id = ?"
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

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
