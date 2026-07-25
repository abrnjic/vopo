package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.SearchHistoryEntity
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
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class SearchHistoryDao_Impl(
  __db: RoomDatabase,
) : SearchHistoryDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfSearchHistoryEntity: EntityInsertAdapter<SearchHistoryEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfSearchHistoryEntity = object : EntityInsertAdapter<SearchHistoryEntity>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `search_history` (`id`,`query`,`content_scope`,`provider_id`,`used_at`,`use_count`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: SearchHistoryEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.query)
        statement.bindText(3, entity.contentScope)
        statement.bindLong(4, entity.providerId)
        statement.bindLong(5, entity.usedAt)
        statement.bindLong(6, entity.useCount.toLong())
      }
    }
  }

  public override suspend fun insertIgnore(entity: SearchHistoryEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfSearchHistoryEntity.insertAndReturnId(_connection, entity)
    _result
  }

  public override suspend fun record(
    query: String,
    contentScope: String,
    providerId: Long,
    usedAt: Long,
  ): Unit = performInTransactionSuspending(__db) {
    super@SearchHistoryDao_Impl.record(query, contentScope, providerId, usedAt)
  }

  public override fun observeRecent(
    contentScope: String,
    providerId: Long,
    limit: Int,
  ): Flow<List<SearchHistoryEntity>> {
    val _sql: String = """
        |
        |        SELECT * FROM search_history
        |        WHERE content_scope = ?
        |          AND provider_id = ?
        |        ORDER BY used_at DESC
        |        LIMIT ?
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("search_history")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, contentScope)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfQuery: Int = getColumnIndexOrThrow(_stmt, "query")
        val _columnIndexOfContentScope: Int = getColumnIndexOrThrow(_stmt, "content_scope")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfUsedAt: Int = getColumnIndexOrThrow(_stmt, "used_at")
        val _columnIndexOfUseCount: Int = getColumnIndexOrThrow(_stmt, "use_count")
        val _result: MutableList<SearchHistoryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: SearchHistoryEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpQuery: String
          _tmpQuery = _stmt.getText(_columnIndexOfQuery)
          val _tmpContentScope: String
          _tmpContentScope = _stmt.getText(_columnIndexOfContentScope)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpUsedAt: Long
          _tmpUsedAt = _stmt.getLong(_columnIndexOfUsedAt)
          val _tmpUseCount: Int
          _tmpUseCount = _stmt.getLong(_columnIndexOfUseCount).toInt()
          _item = SearchHistoryEntity(_tmpId,_tmpQuery,_tmpContentScope,_tmpProviderId,_tmpUsedAt,_tmpUseCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun incrementUseCount(
    query: String,
    contentScope: String,
    providerId: Long,
    usedAt: Long,
  ): Int {
    val _sql: String = """
        |
        |        UPDATE search_history
        |        SET used_at = ?,
        |            use_count = use_count + 1
        |        WHERE query = ?
        |          AND content_scope = ?
        |          AND provider_id = ?
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, usedAt)
        _argIndex = 2
        _stmt.bindText(_argIndex, query)
        _argIndex = 3
        _stmt.bindText(_argIndex, contentScope)
        _argIndex = 4
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByScope(contentScope: String, providerId: Long) {
    val _sql: String = "DELETE FROM search_history WHERE content_scope = ? AND provider_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, contentScope)
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun pruneOlderThan(minUsedAt: Long) {
    val _sql: String = "DELETE FROM search_history WHERE used_at < ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, minUsedAt)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAll() {
    val _sql: String = "DELETE FROM search_history"
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
