package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.RoomEnumConverters
import com.vopo.`data`.local.entity.VirtualGroupEntity
import com.vopo.domain.model.ContentType
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlin.text.StringBuilder
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class VirtualGroupDao_Impl(
  __db: RoomDatabase,
) : VirtualGroupDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfVirtualGroupEntity: EntityInsertAdapter<VirtualGroupEntity>

  private val __roomEnumConverters: RoomEnumConverters = RoomEnumConverters()
  init {
    this.__db = __db
    this.__insertAdapterOfVirtualGroupEntity = object : EntityInsertAdapter<VirtualGroupEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `virtual_groups` (`id`,`provider_id`,`name`,`icon_emoji`,`position`,`created_at`,`content_type`) VALUES (nullif(?, 0),?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: VirtualGroupEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.providerId)
        statement.bindText(3, entity.name)
        val _tmpIconEmoji: String? = entity.iconEmoji
        if (_tmpIconEmoji == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpIconEmoji)
        }
        statement.bindLong(5, entity.position.toLong())
        statement.bindLong(6, entity.createdAt)
        val _tmp: String? = __roomEnumConverters.fromContentType(entity.contentType)
        if (_tmp == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmp)
        }
      }
    }
  }

  public override suspend fun insert(group: VirtualGroupEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfVirtualGroupEntity.insertAndReturnId(_connection, group)
    _result
  }

  public override fun getByType(providerId: Long, contentType: String): Flow<List<VirtualGroupEntity>> {
    val _sql: String = "SELECT * FROM virtual_groups WHERE provider_id = ? AND content_type = ? ORDER BY position ASC"
    return createFlow(__db, false, arrayOf("virtual_groups")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, contentType)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfIconEmoji: Int = getColumnIndexOrThrow(_stmt, "icon_emoji")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _result: MutableList<VirtualGroupEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: VirtualGroupEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpIconEmoji: String?
          if (_stmt.isNull(_columnIndexOfIconEmoji)) {
            _tmpIconEmoji = null
          } else {
            _tmpIconEmoji = _stmt.getText(_columnIndexOfIconEmoji)
          }
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpContentType: ContentType
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfContentType)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfContentType)
          }
          val _tmp_1: ContentType? = __roomEnumConverters.toContentType(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ContentType', but it was NULL.")
          } else {
            _tmpContentType = _tmp_1
          }
          _item = VirtualGroupEntity(_tmpId,_tmpProviderId,_tmpName,_tmpIconEmoji,_tmpPosition,_tmpCreatedAt,_tmpContentType)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByTypeForProviders(providerIds: List<Long>, contentType: String): Flow<List<VirtualGroupEntity>> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM virtual_groups WHERE provider_id IN (")
    val _inputSize: Int = providerIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") AND content_type = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" ORDER BY provider_id ASC, position ASC")
    val _sql: String = _stringBuilder.toString()
    return createFlow(__db, false, arrayOf("virtual_groups")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in providerIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        _argIndex = 1 + _inputSize
        _stmt.bindText(_argIndex, contentType)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfIconEmoji: Int = getColumnIndexOrThrow(_stmt, "icon_emoji")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _result: MutableList<VirtualGroupEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: VirtualGroupEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpIconEmoji: String?
          if (_stmt.isNull(_columnIndexOfIconEmoji)) {
            _tmpIconEmoji = null
          } else {
            _tmpIconEmoji = _stmt.getText(_columnIndexOfIconEmoji)
          }
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpContentType: ContentType
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfContentType)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfContentType)
          }
          val _tmp_1: ContentType? = __roomEnumConverters.toContentType(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ContentType', but it was NULL.")
          } else {
            _tmpContentType = _tmp_1
          }
          _item_1 = VirtualGroupEntity(_tmpId,_tmpProviderId,_tmpName,_tmpIconEmoji,_tmpPosition,_tmpCreatedAt,_tmpContentType)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long): VirtualGroupEntity? {
    val _sql: String = "SELECT * FROM virtual_groups WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfIconEmoji: Int = getColumnIndexOrThrow(_stmt, "icon_emoji")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _result: VirtualGroupEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpIconEmoji: String?
          if (_stmt.isNull(_columnIndexOfIconEmoji)) {
            _tmpIconEmoji = null
          } else {
            _tmpIconEmoji = _stmt.getText(_columnIndexOfIconEmoji)
          }
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpContentType: ContentType
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfContentType)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfContentType)
          }
          val _tmp_1: ContentType? = __roomEnumConverters.toContentType(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ContentType', but it was NULL.")
          } else {
            _tmpContentType = _tmp_1
          }
          _result = VirtualGroupEntity(_tmpId,_tmpProviderId,_tmpName,_tmpIconEmoji,_tmpPosition,_tmpCreatedAt,_tmpContentType)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getMaxPosition(providerId: Long, contentType: String): Int? {
    val _sql: String = "SELECT MAX(position) FROM virtual_groups WHERE provider_id = ? AND content_type = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, contentType)
        val _result: Int?
        if (_stmt.step()) {
          val _tmp: Int?
          if (_stmt.isNull(0)) {
            _tmp = null
          } else {
            _tmp = _stmt.getLong(0).toInt()
          }
          _result = _tmp
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun rename(id: Long, name: String) {
    val _sql: String = "UPDATE virtual_groups SET name = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, name)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(id: Long) {
    val _sql: String = "DELETE FROM virtual_groups WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
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
