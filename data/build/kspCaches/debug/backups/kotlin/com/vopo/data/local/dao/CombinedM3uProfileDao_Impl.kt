package com.vopo.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.CombinedM3uProfileEntity
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
public class CombinedM3uProfileDao_Impl(
  __db: RoomDatabase,
) : CombinedM3uProfileDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCombinedM3uProfileEntity:
      EntityInsertAdapter<CombinedM3uProfileEntity>

  private val __updateAdapterOfCombinedM3uProfileEntity:
      EntityDeleteOrUpdateAdapter<CombinedM3uProfileEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCombinedM3uProfileEntity = object : EntityInsertAdapter<CombinedM3uProfileEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `combined_m3u_profiles` (`id`,`name`,`enabled`,`created_at`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CombinedM3uProfileEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmp: Int = if (entity.enabled) 1 else 0
        statement.bindLong(3, _tmp.toLong())
        statement.bindLong(4, entity.createdAt)
        statement.bindLong(5, entity.updatedAt)
      }
    }
    this.__updateAdapterOfCombinedM3uProfileEntity = object : EntityDeleteOrUpdateAdapter<CombinedM3uProfileEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `combined_m3u_profiles` SET `id` = ?,`name` = ?,`enabled` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CombinedM3uProfileEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmp: Int = if (entity.enabled) 1 else 0
        statement.bindLong(3, _tmp.toLong())
        statement.bindLong(4, entity.createdAt)
        statement.bindLong(5, entity.updatedAt)
        statement.bindLong(6, entity.id)
      }
    }
  }

  public override suspend fun insert(profile: CombinedM3uProfileEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfCombinedM3uProfileEntity.insertAndReturnId(_connection, profile)
    _result
  }

  public override suspend fun update(profile: CombinedM3uProfileEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfCombinedM3uProfileEntity.handle(_connection, profile)
  }

  public override fun getAll(): Flow<List<CombinedM3uProfileEntity>> {
    val _sql: String = "SELECT * FROM combined_m3u_profiles ORDER BY updated_at DESC, created_at DESC"
    return createFlow(__db, false, arrayOf("combined_m3u_profiles")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: MutableList<CombinedM3uProfileEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CombinedM3uProfileEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item = CombinedM3uProfileEntity(_tmpId,_tmpName,_tmpEnabled,_tmpCreatedAt,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(profileId: Long): CombinedM3uProfileEntity? {
    val _sql: String = "SELECT * FROM combined_m3u_profiles WHERE id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, profileId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: CombinedM3uProfileEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _result = CombinedM3uProfileEntity(_tmpId,_tmpName,_tmpEnabled,_tmpCreatedAt,_tmpUpdatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(profileId: Long) {
    val _sql: String = "DELETE FROM combined_m3u_profiles WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, profileId)
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
