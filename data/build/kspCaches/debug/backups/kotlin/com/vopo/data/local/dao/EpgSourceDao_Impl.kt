package com.vopo.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.EpgSourceEntity
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
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class EpgSourceDao_Impl(
  __db: RoomDatabase,
) : EpgSourceDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfEpgSourceEntity: EntityInsertAdapter<EpgSourceEntity>

  private val __updateAdapterOfEpgSourceEntity: EntityDeleteOrUpdateAdapter<EpgSourceEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfEpgSourceEntity = object : EntityInsertAdapter<EpgSourceEntity>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `epg_sources` (`id`,`name`,`url`,`enabled`,`last_refresh_at`,`last_success_at`,`last_error`,`priority`,`created_at`,`updated_at`,`etag`,`last_modified_header`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: EpgSourceEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.url)
        val _tmp: Int = if (entity.enabled) 1 else 0
        statement.bindLong(4, _tmp.toLong())
        statement.bindLong(5, entity.lastRefreshAt)
        statement.bindLong(6, entity.lastSuccessAt)
        val _tmpLastError: String? = entity.lastError
        if (_tmpLastError == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpLastError)
        }
        statement.bindLong(8, entity.priority.toLong())
        statement.bindLong(9, entity.createdAt)
        statement.bindLong(10, entity.updatedAt)
        val _tmpEtag: String? = entity.etag
        if (_tmpEtag == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpEtag)
        }
        val _tmpLastModifiedHeader: String? = entity.lastModifiedHeader
        if (_tmpLastModifiedHeader == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpLastModifiedHeader)
        }
      }
    }
    this.__updateAdapterOfEpgSourceEntity = object : EntityDeleteOrUpdateAdapter<EpgSourceEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `epg_sources` SET `id` = ?,`name` = ?,`url` = ?,`enabled` = ?,`last_refresh_at` = ?,`last_success_at` = ?,`last_error` = ?,`priority` = ?,`created_at` = ?,`updated_at` = ?,`etag` = ?,`last_modified_header` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: EpgSourceEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.url)
        val _tmp: Int = if (entity.enabled) 1 else 0
        statement.bindLong(4, _tmp.toLong())
        statement.bindLong(5, entity.lastRefreshAt)
        statement.bindLong(6, entity.lastSuccessAt)
        val _tmpLastError: String? = entity.lastError
        if (_tmpLastError == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpLastError)
        }
        statement.bindLong(8, entity.priority.toLong())
        statement.bindLong(9, entity.createdAt)
        statement.bindLong(10, entity.updatedAt)
        val _tmpEtag: String? = entity.etag
        if (_tmpEtag == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpEtag)
        }
        val _tmpLastModifiedHeader: String? = entity.lastModifiedHeader
        if (_tmpLastModifiedHeader == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpLastModifiedHeader)
        }
        statement.bindLong(13, entity.id)
      }
    }
  }

  public override suspend fun insert(source: EpgSourceEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfEpgSourceEntity.insertAndReturnId(_connection, source)
    _result
  }

  public override suspend fun update(source: EpgSourceEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfEpgSourceEntity.handle(_connection, source)
  }

  public override fun getAll(): Flow<List<EpgSourceEntity>> {
    val _sql: String = "SELECT * FROM epg_sources WHERE id > 0 ORDER BY priority ASC, name ASC"
    return createFlow(__db, false, arrayOf("epg_sources")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfLastRefreshAt: Int = getColumnIndexOrThrow(_stmt, "last_refresh_at")
        val _columnIndexOfLastSuccessAt: Int = getColumnIndexOrThrow(_stmt, "last_success_at")
        val _columnIndexOfLastError: Int = getColumnIndexOrThrow(_stmt, "last_error")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _columnIndexOfEtag: Int = getColumnIndexOrThrow(_stmt, "etag")
        val _columnIndexOfLastModifiedHeader: Int = getColumnIndexOrThrow(_stmt, "last_modified_header")
        val _result: MutableList<EpgSourceEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: EpgSourceEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpLastRefreshAt: Long
          _tmpLastRefreshAt = _stmt.getLong(_columnIndexOfLastRefreshAt)
          val _tmpLastSuccessAt: Long
          _tmpLastSuccessAt = _stmt.getLong(_columnIndexOfLastSuccessAt)
          val _tmpLastError: String?
          if (_stmt.isNull(_columnIndexOfLastError)) {
            _tmpLastError = null
          } else {
            _tmpLastError = _stmt.getText(_columnIndexOfLastError)
          }
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          val _tmpEtag: String?
          if (_stmt.isNull(_columnIndexOfEtag)) {
            _tmpEtag = null
          } else {
            _tmpEtag = _stmt.getText(_columnIndexOfEtag)
          }
          val _tmpLastModifiedHeader: String?
          if (_stmt.isNull(_columnIndexOfLastModifiedHeader)) {
            _tmpLastModifiedHeader = null
          } else {
            _tmpLastModifiedHeader = _stmt.getText(_columnIndexOfLastModifiedHeader)
          }
          _item = EpgSourceEntity(_tmpId,_tmpName,_tmpUrl,_tmpEnabled,_tmpLastRefreshAt,_tmpLastSuccessAt,_tmpLastError,_tmpPriority,_tmpCreatedAt,_tmpUpdatedAt,_tmpEtag,_tmpLastModifiedHeader)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSync(): List<EpgSourceEntity> {
    val _sql: String = "SELECT * FROM epg_sources WHERE id > 0 ORDER BY priority ASC, name ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfLastRefreshAt: Int = getColumnIndexOrThrow(_stmt, "last_refresh_at")
        val _columnIndexOfLastSuccessAt: Int = getColumnIndexOrThrow(_stmt, "last_success_at")
        val _columnIndexOfLastError: Int = getColumnIndexOrThrow(_stmt, "last_error")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _columnIndexOfEtag: Int = getColumnIndexOrThrow(_stmt, "etag")
        val _columnIndexOfLastModifiedHeader: Int = getColumnIndexOrThrow(_stmt, "last_modified_header")
        val _result: MutableList<EpgSourceEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: EpgSourceEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpLastRefreshAt: Long
          _tmpLastRefreshAt = _stmt.getLong(_columnIndexOfLastRefreshAt)
          val _tmpLastSuccessAt: Long
          _tmpLastSuccessAt = _stmt.getLong(_columnIndexOfLastSuccessAt)
          val _tmpLastError: String?
          if (_stmt.isNull(_columnIndexOfLastError)) {
            _tmpLastError = null
          } else {
            _tmpLastError = _stmt.getText(_columnIndexOfLastError)
          }
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          val _tmpEtag: String?
          if (_stmt.isNull(_columnIndexOfEtag)) {
            _tmpEtag = null
          } else {
            _tmpEtag = _stmt.getText(_columnIndexOfEtag)
          }
          val _tmpLastModifiedHeader: String?
          if (_stmt.isNull(_columnIndexOfLastModifiedHeader)) {
            _tmpLastModifiedHeader = null
          } else {
            _tmpLastModifiedHeader = _stmt.getText(_columnIndexOfLastModifiedHeader)
          }
          _item = EpgSourceEntity(_tmpId,_tmpName,_tmpUrl,_tmpEnabled,_tmpLastRefreshAt,_tmpLastSuccessAt,_tmpLastError,_tmpPriority,_tmpCreatedAt,_tmpUpdatedAt,_tmpEtag,_tmpLastModifiedHeader)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long): EpgSourceEntity? {
    val _sql: String = "SELECT * FROM epg_sources WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfLastRefreshAt: Int = getColumnIndexOrThrow(_stmt, "last_refresh_at")
        val _columnIndexOfLastSuccessAt: Int = getColumnIndexOrThrow(_stmt, "last_success_at")
        val _columnIndexOfLastError: Int = getColumnIndexOrThrow(_stmt, "last_error")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _columnIndexOfEtag: Int = getColumnIndexOrThrow(_stmt, "etag")
        val _columnIndexOfLastModifiedHeader: Int = getColumnIndexOrThrow(_stmt, "last_modified_header")
        val _result: EpgSourceEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpLastRefreshAt: Long
          _tmpLastRefreshAt = _stmt.getLong(_columnIndexOfLastRefreshAt)
          val _tmpLastSuccessAt: Long
          _tmpLastSuccessAt = _stmt.getLong(_columnIndexOfLastSuccessAt)
          val _tmpLastError: String?
          if (_stmt.isNull(_columnIndexOfLastError)) {
            _tmpLastError = null
          } else {
            _tmpLastError = _stmt.getText(_columnIndexOfLastError)
          }
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          val _tmpEtag: String?
          if (_stmt.isNull(_columnIndexOfEtag)) {
            _tmpEtag = null
          } else {
            _tmpEtag = _stmt.getText(_columnIndexOfEtag)
          }
          val _tmpLastModifiedHeader: String?
          if (_stmt.isNull(_columnIndexOfLastModifiedHeader)) {
            _tmpLastModifiedHeader = null
          } else {
            _tmpLastModifiedHeader = _stmt.getText(_columnIndexOfLastModifiedHeader)
          }
          _result = EpgSourceEntity(_tmpId,_tmpName,_tmpUrl,_tmpEnabled,_tmpLastRefreshAt,_tmpLastSuccessAt,_tmpLastError,_tmpPriority,_tmpCreatedAt,_tmpUpdatedAt,_tmpEtag,_tmpLastModifiedHeader)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByUrl(url: String): EpgSourceEntity? {
    val _sql: String = "SELECT * FROM epg_sources WHERE url = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, url)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfLastRefreshAt: Int = getColumnIndexOrThrow(_stmt, "last_refresh_at")
        val _columnIndexOfLastSuccessAt: Int = getColumnIndexOrThrow(_stmt, "last_success_at")
        val _columnIndexOfLastError: Int = getColumnIndexOrThrow(_stmt, "last_error")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _columnIndexOfEtag: Int = getColumnIndexOrThrow(_stmt, "etag")
        val _columnIndexOfLastModifiedHeader: Int = getColumnIndexOrThrow(_stmt, "last_modified_header")
        val _result: EpgSourceEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpLastRefreshAt: Long
          _tmpLastRefreshAt = _stmt.getLong(_columnIndexOfLastRefreshAt)
          val _tmpLastSuccessAt: Long
          _tmpLastSuccessAt = _stmt.getLong(_columnIndexOfLastSuccessAt)
          val _tmpLastError: String?
          if (_stmt.isNull(_columnIndexOfLastError)) {
            _tmpLastError = null
          } else {
            _tmpLastError = _stmt.getText(_columnIndexOfLastError)
          }
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          val _tmpEtag: String?
          if (_stmt.isNull(_columnIndexOfEtag)) {
            _tmpEtag = null
          } else {
            _tmpEtag = _stmt.getText(_columnIndexOfEtag)
          }
          val _tmpLastModifiedHeader: String?
          if (_stmt.isNull(_columnIndexOfLastModifiedHeader)) {
            _tmpLastModifiedHeader = null
          } else {
            _tmpLastModifiedHeader = _stmt.getText(_columnIndexOfLastModifiedHeader)
          }
          _result = EpgSourceEntity(_tmpId,_tmpName,_tmpUrl,_tmpEnabled,_tmpLastRefreshAt,_tmpLastSuccessAt,_tmpLastError,_tmpPriority,_tmpCreatedAt,_tmpUpdatedAt,_tmpEtag,_tmpLastModifiedHeader)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getEnabled(): List<EpgSourceEntity> {
    val _sql: String = "SELECT * FROM epg_sources WHERE id > 0 AND enabled = 1 ORDER BY priority ASC, name ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfUrl: Int = getColumnIndexOrThrow(_stmt, "url")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfLastRefreshAt: Int = getColumnIndexOrThrow(_stmt, "last_refresh_at")
        val _columnIndexOfLastSuccessAt: Int = getColumnIndexOrThrow(_stmt, "last_success_at")
        val _columnIndexOfLastError: Int = getColumnIndexOrThrow(_stmt, "last_error")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _columnIndexOfEtag: Int = getColumnIndexOrThrow(_stmt, "etag")
        val _columnIndexOfLastModifiedHeader: Int = getColumnIndexOrThrow(_stmt, "last_modified_header")
        val _result: MutableList<EpgSourceEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: EpgSourceEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpUrl: String
          _tmpUrl = _stmt.getText(_columnIndexOfUrl)
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpLastRefreshAt: Long
          _tmpLastRefreshAt = _stmt.getLong(_columnIndexOfLastRefreshAt)
          val _tmpLastSuccessAt: Long
          _tmpLastSuccessAt = _stmt.getLong(_columnIndexOfLastSuccessAt)
          val _tmpLastError: String?
          if (_stmt.isNull(_columnIndexOfLastError)) {
            _tmpLastError = null
          } else {
            _tmpLastError = _stmt.getText(_columnIndexOfLastError)
          }
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          val _tmpEtag: String?
          if (_stmt.isNull(_columnIndexOfEtag)) {
            _tmpEtag = null
          } else {
            _tmpEtag = _stmt.getText(_columnIndexOfEtag)
          }
          val _tmpLastModifiedHeader: String?
          if (_stmt.isNull(_columnIndexOfLastModifiedHeader)) {
            _tmpLastModifiedHeader = null
          } else {
            _tmpLastModifiedHeader = _stmt.getText(_columnIndexOfLastModifiedHeader)
          }
          _item = EpgSourceEntity(_tmpId,_tmpName,_tmpUrl,_tmpEnabled,_tmpLastRefreshAt,_tmpLastSuccessAt,_tmpLastError,_tmpPriority,_tmpCreatedAt,_tmpUpdatedAt,_tmpEtag,_tmpLastModifiedHeader)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(id: Long) {
    val _sql: String = "DELETE FROM epg_sources WHERE id = ?"
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

  public override suspend fun setEnabled(
    id: Long,
    enabled: Boolean,
    now: Long,
  ) {
    val _sql: String = "UPDATE epg_sources SET enabled = ?, updated_at = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (enabled) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, now)
        _argIndex = 3
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateRefreshStatus(
    id: Long,
    at: Long,
    error: String?,
  ) {
    val _sql: String = "UPDATE epg_sources SET last_refresh_at = ?, last_error = ?, updated_at = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, at)
        _argIndex = 2
        if (error == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindText(_argIndex, error)
        }
        _argIndex = 3
        _stmt.bindLong(_argIndex, at)
        _argIndex = 4
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateRefreshError(
    id: Long,
    error: String?,
    at: Long,
  ) {
    val _sql: String = "UPDATE epg_sources SET last_error = ?, updated_at = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        if (error == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindText(_argIndex, error)
        }
        _argIndex = 2
        _stmt.bindLong(_argIndex, at)
        _argIndex = 3
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateRefreshSuccess(id: Long, at: Long) {
    val _sql: String = "UPDATE epg_sources SET last_refresh_at = ?, last_success_at = ?, last_error = NULL, updated_at = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, at)
        _argIndex = 2
        _stmt.bindLong(_argIndex, at)
        _argIndex = 3
        _stmt.bindLong(_argIndex, at)
        _argIndex = 4
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateConditionalHeaders(
    id: Long,
    etag: String?,
    lastModified: String?,
  ) {
    val _sql: String = "UPDATE epg_sources SET etag = ?, last_modified_header = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        if (etag == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindText(_argIndex, etag)
        }
        _argIndex = 2
        if (lastModified == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindText(_argIndex, lastModified)
        }
        _argIndex = 3
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
