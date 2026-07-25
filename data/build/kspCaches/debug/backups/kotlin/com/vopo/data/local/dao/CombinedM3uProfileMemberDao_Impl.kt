package com.vopo.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.CombinedM3uProfileMemberEntity
import com.vopo.`data`.local.entity.CombinedM3uProfileMemberWithProvider
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
public class CombinedM3uProfileMemberDao_Impl(
  __db: RoomDatabase,
) : CombinedM3uProfileMemberDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCombinedM3uProfileMemberEntity:
      EntityInsertAdapter<CombinedM3uProfileMemberEntity>

  private val __updateAdapterOfCombinedM3uProfileMemberEntity:
      EntityDeleteOrUpdateAdapter<CombinedM3uProfileMemberEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCombinedM3uProfileMemberEntity = object : EntityInsertAdapter<CombinedM3uProfileMemberEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `combined_m3u_profile_members` (`id`,`profile_id`,`provider_id`,`priority`,`enabled`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CombinedM3uProfileMemberEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.profileId)
        statement.bindLong(3, entity.providerId)
        statement.bindLong(4, entity.priority.toLong())
        val _tmp: Int = if (entity.enabled) 1 else 0
        statement.bindLong(5, _tmp.toLong())
      }
    }
    this.__updateAdapterOfCombinedM3uProfileMemberEntity = object : EntityDeleteOrUpdateAdapter<CombinedM3uProfileMemberEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `combined_m3u_profile_members` SET `id` = ?,`profile_id` = ?,`provider_id` = ?,`priority` = ?,`enabled` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CombinedM3uProfileMemberEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.profileId)
        statement.bindLong(3, entity.providerId)
        statement.bindLong(4, entity.priority.toLong())
        val _tmp: Int = if (entity.enabled) 1 else 0
        statement.bindLong(5, _tmp.toLong())
        statement.bindLong(6, entity.id)
      }
    }
  }

  public override suspend fun insert(member: CombinedM3uProfileMemberEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfCombinedM3uProfileMemberEntity.insertAndReturnId(_connection, member)
    _result
  }

  public override suspend fun update(member: CombinedM3uProfileMemberEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfCombinedM3uProfileMemberEntity.handle(_connection, member)
  }

  public override suspend fun replacePriorities(profileId: Long, members: List<CombinedM3uProfileMemberEntity>): Unit = performInTransactionSuspending(__db) {
    super@CombinedM3uProfileMemberDao_Impl.replacePriorities(profileId, members)
  }

  public override fun getForProfile(profileId: Long): Flow<List<CombinedM3uProfileMemberWithProvider>> {
    val _sql: String = """
        |
        |        SELECT m.id, m.profile_id, m.provider_id, m.priority, m.enabled, p.name AS provider_name
        |        FROM combined_m3u_profile_members m
        |        INNER JOIN providers p ON p.id = m.provider_id
        |        WHERE m.profile_id = ?
        |        ORDER BY m.priority ASC, m.id ASC
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("combined_m3u_profile_members", "providers")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, profileId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfProfileId: Int = 1
        val _columnIndexOfProviderId: Int = 2
        val _columnIndexOfPriority: Int = 3
        val _columnIndexOfEnabled: Int = 4
        val _columnIndexOfProviderName: Int = 5
        val _result: MutableList<CombinedM3uProfileMemberWithProvider> = mutableListOf()
        while (_stmt.step()) {
          val _item: CombinedM3uProfileMemberWithProvider
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProfileId: Long
          _tmpProfileId = _stmt.getLong(_columnIndexOfProfileId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpProviderName: String
          _tmpProviderName = _stmt.getText(_columnIndexOfProviderName)
          _item = CombinedM3uProfileMemberWithProvider(_tmpId,_tmpProfileId,_tmpProviderId,_tmpPriority,_tmpEnabled,_tmpProviderName)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getForProfileSync(profileId: Long): List<CombinedM3uProfileMemberWithProvider> {
    val _sql: String = """
        |
        |        SELECT m.id, m.profile_id, m.provider_id, m.priority, m.enabled, p.name AS provider_name
        |        FROM combined_m3u_profile_members m
        |        INNER JOIN providers p ON p.id = m.provider_id
        |        WHERE m.profile_id = ?
        |        ORDER BY m.priority ASC, m.id ASC
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, profileId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfProfileId: Int = 1
        val _columnIndexOfProviderId: Int = 2
        val _columnIndexOfPriority: Int = 3
        val _columnIndexOfEnabled: Int = 4
        val _columnIndexOfProviderName: Int = 5
        val _result: MutableList<CombinedM3uProfileMemberWithProvider> = mutableListOf()
        while (_stmt.step()) {
          val _item: CombinedM3uProfileMemberWithProvider
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProfileId: Long
          _tmpProfileId = _stmt.getLong(_columnIndexOfProfileId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpProviderName: String
          _tmpProviderName = _stmt.getText(_columnIndexOfProviderName)
          _item = CombinedM3uProfileMemberWithProvider(_tmpId,_tmpProfileId,_tmpProviderId,_tmpPriority,_tmpEnabled,_tmpProviderName)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getMember(profileId: Long, providerId: Long): CombinedM3uProfileMemberEntity? {
    val _sql: String = "SELECT * FROM combined_m3u_profile_members WHERE profile_id = ? AND provider_id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, profileId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProfileId: Int = getColumnIndexOrThrow(_stmt, "profile_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _result: CombinedM3uProfileMemberEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProfileId: Long
          _tmpProfileId = _stmt.getLong(_columnIndexOfProfileId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          _result = CombinedM3uProfileMemberEntity(_tmpId,_tmpProfileId,_tmpProviderId,_tmpPriority,_tmpEnabled)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun countForProfile(profileId: Long): Int {
    val _sql: String = "SELECT COUNT(*) FROM combined_m3u_profile_members WHERE profile_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, profileId)
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

  public override suspend fun delete(profileId: Long, providerId: Long) {
    val _sql: String = "DELETE FROM combined_m3u_profile_members WHERE profile_id = ? AND provider_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, profileId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteForProfile(profileId: Long) {
    val _sql: String = "DELETE FROM combined_m3u_profile_members WHERE profile_id = ?"
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
