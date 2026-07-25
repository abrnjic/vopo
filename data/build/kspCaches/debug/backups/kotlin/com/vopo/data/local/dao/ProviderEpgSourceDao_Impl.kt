package com.vopo.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.ProviderEpgSourceEntity
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
public class ProviderEpgSourceDao_Impl(
  __db: RoomDatabase,
) : ProviderEpgSourceDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfProviderEpgSourceEntity: EntityInsertAdapter<ProviderEpgSourceEntity>

  private val __updateAdapterOfProviderEpgSourceEntity:
      EntityDeleteOrUpdateAdapter<ProviderEpgSourceEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfProviderEpgSourceEntity = object : EntityInsertAdapter<ProviderEpgSourceEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `provider_epg_sources` (`id`,`provider_id`,`epg_source_id`,`priority`,`enabled`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ProviderEpgSourceEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.providerId)
        statement.bindLong(3, entity.epgSourceId)
        statement.bindLong(4, entity.priority.toLong())
        val _tmp: Int = if (entity.enabled) 1 else 0
        statement.bindLong(5, _tmp.toLong())
      }
    }
    this.__updateAdapterOfProviderEpgSourceEntity = object : EntityDeleteOrUpdateAdapter<ProviderEpgSourceEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `provider_epg_sources` SET `id` = ?,`provider_id` = ?,`epg_source_id` = ?,`priority` = ?,`enabled` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ProviderEpgSourceEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.providerId)
        statement.bindLong(3, entity.epgSourceId)
        statement.bindLong(4, entity.priority.toLong())
        val _tmp: Int = if (entity.enabled) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        statement.bindLong(6, entity.id)
      }
    }
  }

  public override suspend fun insert(assignment: ProviderEpgSourceEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfProviderEpgSourceEntity.insertAndReturnId(_connection, assignment)
    _result
  }

  public override suspend fun update(assignment: ProviderEpgSourceEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfProviderEpgSourceEntity.handle(_connection, assignment)
  }

  public override suspend fun swapPriorities(entity1: ProviderEpgSourceEntity, entity2: ProviderEpgSourceEntity): Unit = performInTransactionSuspending(__db) {
    super@ProviderEpgSourceDao_Impl.swapPriorities(entity1, entity2)
  }

  public override fun getForProvider(providerId: Long): Flow<List<ProviderEpgSourceWithDetails>> {
    val _sql: String = """
        |
        |        SELECT pes.*, es.name AS epg_source_name, es.url AS epg_source_url
        |        FROM provider_epg_sources pes
        |        JOIN epg_sources es ON es.id = pes.epg_source_id
        |        WHERE pes.provider_id = ?
        |        ORDER BY pes.priority ASC
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("provider_epg_sources", "epg_sources")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfEpgSourceId: Int = getColumnIndexOrThrow(_stmt, "epg_source_id")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfEpgSourceName: Int = getColumnIndexOrThrow(_stmt, "epg_source_name")
        val _columnIndexOfEpgSourceUrl: Int = getColumnIndexOrThrow(_stmt, "epg_source_url")
        val _result: MutableList<ProviderEpgSourceWithDetails> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProviderEpgSourceWithDetails
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpEpgSourceId: Long
          _tmpEpgSourceId = _stmt.getLong(_columnIndexOfEpgSourceId)
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpEpgSourceName: String
          _tmpEpgSourceName = _stmt.getText(_columnIndexOfEpgSourceName)
          val _tmpEpgSourceUrl: String
          _tmpEpgSourceUrl = _stmt.getText(_columnIndexOfEpgSourceUrl)
          _item = ProviderEpgSourceWithDetails(_tmpId,_tmpProviderId,_tmpEpgSourceId,_tmpPriority,_tmpEnabled,_tmpEpgSourceName,_tmpEpgSourceUrl)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getEnabledForProviderSync(providerId: Long): List<ProviderEpgSourceEntity> {
    val _sql: String = """
        |
        |        SELECT pes.*
        |        FROM provider_epg_sources pes
        |        JOIN epg_sources es ON es.id = pes.epg_source_id
        |        WHERE pes.provider_id = ? AND pes.enabled = 1 AND es.enabled = 1
        |        ORDER BY pes.priority ASC
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfEpgSourceId: Int = getColumnIndexOrThrow(_stmt, "epg_source_id")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _result: MutableList<ProviderEpgSourceEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProviderEpgSourceEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpEpgSourceId: Long
          _tmpEpgSourceId = _stmt.getLong(_columnIndexOfEpgSourceId)
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          _item = ProviderEpgSourceEntity(_tmpId,_tmpProviderId,_tmpEpgSourceId,_tmpPriority,_tmpEnabled)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getForProviderSync(providerId: Long): List<ProviderEpgSourceEntity> {
    val _sql: String = """
        |
        |        SELECT pes.*
        |        FROM provider_epg_sources pes
        |        WHERE pes.provider_id = ?
        |        ORDER BY pes.priority ASC
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfEpgSourceId: Int = getColumnIndexOrThrow(_stmt, "epg_source_id")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _result: MutableList<ProviderEpgSourceEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProviderEpgSourceEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpEpgSourceId: Long
          _tmpEpgSourceId = _stmt.getLong(_columnIndexOfEpgSourceId)
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          _item = ProviderEpgSourceEntity(_tmpId,_tmpProviderId,_tmpEpgSourceId,_tmpPriority,_tmpEnabled)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getProviderIdsForSourceSync(epgSourceId: Long): List<Long> {
    val _sql: String = "SELECT DISTINCT provider_id FROM provider_epg_sources WHERE epg_source_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, epgSourceId)
        val _result: MutableList<Long> = mutableListOf()
        while (_stmt.step()) {
          val _item: Long
          _item = _stmt.getLong(0)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(providerId: Long, epgSourceId: Long) {
    val _sql: String = "DELETE FROM provider_epg_sources WHERE provider_id = ? AND epg_source_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, epgSourceId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByProvider(providerId: Long) {
    val _sql: String = "DELETE FROM provider_epg_sources WHERE provider_id = ?"
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
