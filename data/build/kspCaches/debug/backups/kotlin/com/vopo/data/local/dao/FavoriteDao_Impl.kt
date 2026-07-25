package com.vopo.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.RoomEnumConverters
import com.vopo.`data`.local.entity.CategoryCount
import com.vopo.`data`.local.entity.FavoriteEntity
import com.vopo.domain.model.ContentType
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
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class FavoriteDao_Impl(
  __db: RoomDatabase,
) : FavoriteDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfFavoriteEntity: EntityInsertAdapter<FavoriteEntity>

  private val __roomEnumConverters: RoomEnumConverters = RoomEnumConverters()

  private val __updateAdapterOfFavoriteEntity: EntityDeleteOrUpdateAdapter<FavoriteEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfFavoriteEntity = object : EntityInsertAdapter<FavoriteEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `favorites` (`id`,`provider_id`,`content_id`,`content_type`,`position`,`group_id`,`group_key`,`added_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: FavoriteEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.providerId)
        statement.bindLong(3, entity.contentId)
        val _tmp: String? = __roomEnumConverters.fromContentType(entity.contentType)
        if (_tmp == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmp)
        }
        statement.bindLong(5, entity.position.toLong())
        val _tmpGroupId: Long? = entity.groupId
        if (_tmpGroupId == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpGroupId)
        }
        statement.bindLong(7, entity.groupKey)
        statement.bindLong(8, entity.addedAt)
      }
    }
    this.__updateAdapterOfFavoriteEntity = object : EntityDeleteOrUpdateAdapter<FavoriteEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `favorites` SET `id` = ?,`provider_id` = ?,`content_id` = ?,`content_type` = ?,`position` = ?,`group_id` = ?,`group_key` = ?,`added_at` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: FavoriteEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.providerId)
        statement.bindLong(3, entity.contentId)
        val _tmp: String? = __roomEnumConverters.fromContentType(entity.contentType)
        if (_tmp == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmp)
        }
        statement.bindLong(5, entity.position.toLong())
        val _tmpGroupId: Long? = entity.groupId
        if (_tmpGroupId == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpGroupId)
        }
        statement.bindLong(7, entity.groupKey)
        statement.bindLong(8, entity.addedAt)
        statement.bindLong(9, entity.id)
      }
    }
  }

  protected override suspend fun insertDirect(favorite: FavoriteEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfFavoriteEntity.insert(_connection, favorite)
  }

  public override suspend fun updateAll(favorites: List<FavoriteEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfFavoriteEntity.handleMultiple(_connection, favorites)
  }

  public override suspend fun insert(favorite: FavoriteEntity): Unit = performInTransactionSuspending(__db) {
    super@FavoriteDao_Impl.insert(favorite)
  }

  public override suspend fun updateGroup(favoriteId: Long, groupId: Long?): Unit = performInTransactionSuspending(__db) {
    super@FavoriteDao_Impl.updateGroup(favoriteId, groupId)
  }

  public override fun getAllGlobal(providerId: Long): Flow<List<FavoriteEntity>> {
    val _sql: String = "SELECT * FROM favorites WHERE provider_id = ? AND group_id IS NULL ORDER BY position ASC"
    return createFlow(__db, false, arrayOf("favorites")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _columnIndexOfGroupId: Int = getColumnIndexOrThrow(_stmt, "group_id")
        val _columnIndexOfGroupKey: Int = getColumnIndexOrThrow(_stmt, "group_key")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<FavoriteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: FavoriteEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpGroupId: Long?
          if (_stmt.isNull(_columnIndexOfGroupId)) {
            _tmpGroupId = null
          } else {
            _tmpGroupId = _stmt.getLong(_columnIndexOfGroupId)
          }
          val _tmpGroupKey: Long
          _tmpGroupKey = _stmt.getLong(_columnIndexOfGroupKey)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = FavoriteEntity(_tmpId,_tmpProviderId,_tmpContentId,_tmpContentType,_tmpPosition,_tmpGroupId,_tmpGroupKey,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllGlobalByProviders(providerIds: List<Long>): Flow<List<FavoriteEntity>> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM favorites WHERE provider_id IN (")
    val _inputSize: Int = providerIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") AND group_id IS NULL ORDER BY provider_id ASC, position ASC")
    val _sql: String = _stringBuilder.toString()
    return createFlow(__db, false, arrayOf("favorites")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in providerIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _columnIndexOfGroupId: Int = getColumnIndexOrThrow(_stmt, "group_id")
        val _columnIndexOfGroupKey: Int = getColumnIndexOrThrow(_stmt, "group_key")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<FavoriteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: FavoriteEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpGroupId: Long?
          if (_stmt.isNull(_columnIndexOfGroupId)) {
            _tmpGroupId = null
          } else {
            _tmpGroupId = _stmt.getLong(_columnIndexOfGroupId)
          }
          val _tmpGroupKey: Long
          _tmpGroupKey = _stmt.getLong(_columnIndexOfGroupKey)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item_1 = FavoriteEntity(_tmpId,_tmpProviderId,_tmpContentId,_tmpContentType,_tmpPosition,_tmpGroupId,_tmpGroupKey,_tmpAddedAt)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getGlobalByType(providerId: Long, contentType: String): Flow<List<FavoriteEntity>> {
    val _sql: String = "SELECT * FROM favorites WHERE provider_id = ? AND content_type = ? AND group_id IS NULL ORDER BY position ASC"
    return createFlow(__db, false, arrayOf("favorites")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, contentType)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _columnIndexOfGroupId: Int = getColumnIndexOrThrow(_stmt, "group_id")
        val _columnIndexOfGroupKey: Int = getColumnIndexOrThrow(_stmt, "group_key")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<FavoriteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: FavoriteEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpGroupId: Long?
          if (_stmt.isNull(_columnIndexOfGroupId)) {
            _tmpGroupId = null
          } else {
            _tmpGroupId = _stmt.getLong(_columnIndexOfGroupId)
          }
          val _tmpGroupKey: Long
          _tmpGroupKey = _stmt.getLong(_columnIndexOfGroupKey)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = FavoriteEntity(_tmpId,_tmpProviderId,_tmpContentId,_tmpContentType,_tmpPosition,_tmpGroupId,_tmpGroupKey,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getGlobalByTypeForProviders(providerIds: List<Long>, contentType: String): Flow<List<FavoriteEntity>> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM favorites WHERE provider_id IN (")
    val _inputSize: Int = providerIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") AND content_type = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" AND group_id IS NULL ORDER BY provider_id ASC, position ASC")
    val _sql: String = _stringBuilder.toString()
    return createFlow(__db, false, arrayOf("favorites")) { _connection ->
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
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _columnIndexOfGroupId: Int = getColumnIndexOrThrow(_stmt, "group_id")
        val _columnIndexOfGroupKey: Int = getColumnIndexOrThrow(_stmt, "group_key")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<FavoriteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: FavoriteEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpGroupId: Long?
          if (_stmt.isNull(_columnIndexOfGroupId)) {
            _tmpGroupId = null
          } else {
            _tmpGroupId = _stmt.getLong(_columnIndexOfGroupId)
          }
          val _tmpGroupKey: Long
          _tmpGroupKey = _stmt.getLong(_columnIndexOfGroupKey)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item_1 = FavoriteEntity(_tmpId,_tmpProviderId,_tmpContentId,_tmpContentType,_tmpPosition,_tmpGroupId,_tmpGroupKey,_tmpAddedAt)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllByType(providerId: Long, contentType: String): Flow<List<FavoriteEntity>> {
    val _sql: String = "SELECT * FROM favorites WHERE provider_id = ? AND content_type = ? ORDER BY position ASC"
    return createFlow(__db, false, arrayOf("favorites")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, contentType)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _columnIndexOfGroupId: Int = getColumnIndexOrThrow(_stmt, "group_id")
        val _columnIndexOfGroupKey: Int = getColumnIndexOrThrow(_stmt, "group_key")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<FavoriteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: FavoriteEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpGroupId: Long?
          if (_stmt.isNull(_columnIndexOfGroupId)) {
            _tmpGroupId = null
          } else {
            _tmpGroupId = _stmt.getLong(_columnIndexOfGroupId)
          }
          val _tmpGroupKey: Long
          _tmpGroupKey = _stmt.getLong(_columnIndexOfGroupKey)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = FavoriteEntity(_tmpId,_tmpProviderId,_tmpContentId,_tmpContentType,_tmpPosition,_tmpGroupId,_tmpGroupKey,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getAllByTypeForProviders(providerIds: List<Long>, contentType: String): Flow<List<FavoriteEntity>> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM favorites WHERE provider_id IN (")
    val _inputSize: Int = providerIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") AND content_type = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" ORDER BY provider_id ASC, position ASC")
    val _sql: String = _stringBuilder.toString()
    return createFlow(__db, false, arrayOf("favorites")) { _connection ->
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
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _columnIndexOfGroupId: Int = getColumnIndexOrThrow(_stmt, "group_id")
        val _columnIndexOfGroupKey: Int = getColumnIndexOrThrow(_stmt, "group_key")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<FavoriteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: FavoriteEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpGroupId: Long?
          if (_stmt.isNull(_columnIndexOfGroupId)) {
            _tmpGroupId = null
          } else {
            _tmpGroupId = _stmt.getLong(_columnIndexOfGroupId)
          }
          val _tmpGroupKey: Long
          _tmpGroupKey = _stmt.getLong(_columnIndexOfGroupKey)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item_1 = FavoriteEntity(_tmpId,_tmpProviderId,_tmpContentId,_tmpContentType,_tmpPosition,_tmpGroupId,_tmpGroupKey,_tmpAddedAt)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByGroup(groupId: Long): Flow<List<FavoriteEntity>> {
    val _sql: String = """
        |
        |        SELECT f.*
        |        FROM favorites AS f
        |        INNER JOIN virtual_groups AS g
        |            ON g.id = f.group_id
        |           AND g.provider_id = f.provider_id
        |           AND g.content_type = f.content_type
        |        WHERE g.id = ?
        |        ORDER BY f.position ASC
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("favorites", "virtual_groups")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, groupId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _columnIndexOfGroupId: Int = getColumnIndexOrThrow(_stmt, "group_id")
        val _columnIndexOfGroupKey: Int = getColumnIndexOrThrow(_stmt, "group_key")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: MutableList<FavoriteEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: FavoriteEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpGroupId: Long?
          if (_stmt.isNull(_columnIndexOfGroupId)) {
            _tmpGroupId = null
          } else {
            _tmpGroupId = _stmt.getLong(_columnIndexOfGroupId)
          }
          val _tmpGroupKey: Long
          _tmpGroupKey = _stmt.getLong(_columnIndexOfGroupKey)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _item = FavoriteEntity(_tmpId,_tmpProviderId,_tmpContentId,_tmpContentType,_tmpPosition,_tmpGroupId,_tmpGroupKey,_tmpAddedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun `get`(
    providerId: Long,
    contentId: Long,
    contentType: String,
    groupId: Long?,
  ): FavoriteEntity? {
    val _sql: String = "SELECT * FROM favorites WHERE provider_id = ? AND content_id = ? AND content_type = ? AND (? IS NULL AND group_id IS NULL OR group_id = ?) LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, contentId)
        _argIndex = 3
        _stmt.bindText(_argIndex, contentType)
        _argIndex = 4
        if (groupId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, groupId)
        }
        _argIndex = 5
        if (groupId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, groupId)
        }
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _columnIndexOfGroupId: Int = getColumnIndexOrThrow(_stmt, "group_id")
        val _columnIndexOfGroupKey: Int = getColumnIndexOrThrow(_stmt, "group_key")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: FavoriteEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpGroupId: Long?
          if (_stmt.isNull(_columnIndexOfGroupId)) {
            _tmpGroupId = null
          } else {
            _tmpGroupId = _stmt.getLong(_columnIndexOfGroupId)
          }
          val _tmpGroupKey: Long
          _tmpGroupKey = _stmt.getLong(_columnIndexOfGroupKey)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _result = FavoriteEntity(_tmpId,_tmpProviderId,_tmpContentId,_tmpContentType,_tmpPosition,_tmpGroupId,_tmpGroupKey,_tmpAddedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getGlobalFavoriteCount(providerId: Long, contentType: String): Flow<Int> {
    val _sql: String = "SELECT COUNT(*) FROM favorites WHERE provider_id = ? AND group_id IS NULL AND content_type = ?"
    return createFlow(__db, false, arrayOf("favorites")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, contentType)
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

  public override fun getGroupFavoriteCounts(providerId: Long, contentType: String): Flow<List<CategoryCount>> {
    val _sql: String = "SELECT group_id as category_id, COUNT(*) as item_count FROM favorites WHERE provider_id = ? AND group_id IS NOT NULL AND content_type = ? GROUP BY group_id"
    return createFlow(__db, false, arrayOf("favorites")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, contentType)
        val _columnIndexOfCategoryId: Int = 0
        val _columnIndexOfItemCount: Int = 1
        val _result: MutableList<CategoryCount> = mutableListOf()
        while (_stmt.step()) {
          val _item: CategoryCount
          val _tmpCategoryId: Long
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          val _tmpItem_count: Int
          _tmpItem_count = _stmt.getLong(_columnIndexOfItemCount).toInt()
          _item = CategoryCount(_tmpCategoryId,_tmpItem_count)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getGroupFavoriteCountsForProviders(providerIds: List<Long>, contentType: String): Flow<List<CategoryCount>> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT group_id as category_id, COUNT(*) as item_count FROM favorites WHERE provider_id IN (")
    val _inputSize: Int = providerIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(") AND group_id IS NOT NULL AND content_type = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" GROUP BY group_id")
    val _sql: String = _stringBuilder.toString()
    return createFlow(__db, false, arrayOf("favorites")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in providerIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        _argIndex = 1 + _inputSize
        _stmt.bindText(_argIndex, contentType)
        val _columnIndexOfCategoryId: Int = 0
        val _columnIndexOfItemCount: Int = 1
        val _result: MutableList<CategoryCount> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: CategoryCount
          val _tmpCategoryId: Long
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          val _tmpItem_count: Int
          _tmpItem_count = _stmt.getLong(_columnIndexOfItemCount).toInt()
          _item_1 = CategoryCount(_tmpCategoryId,_tmpItem_count)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getGroupMemberships(
    providerId: Long,
    contentId: Long,
    contentType: String,
  ): List<Long> {
    val _sql: String = "SELECT group_id FROM favorites WHERE provider_id = ? AND content_id = ? AND content_type = ? AND group_id IS NOT NULL"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, contentId)
        _argIndex = 3
        _stmt.bindText(_argIndex, contentType)
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

  public override suspend fun getMaxPosition(providerId: Long, groupId: Long?): Int? {
    val _sql: String = "SELECT MAX(position) FROM favorites WHERE provider_id = ? AND (? IS NULL AND group_id IS NULL OR group_id = ?)"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        if (groupId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, groupId)
        }
        _argIndex = 3
        if (groupId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, groupId)
        }
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

  protected override suspend fun getById(favoriteId: Long): FavoriteEntity? {
    val _sql: String = "SELECT * FROM favorites WHERE id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, favoriteId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfContentId: Int = getColumnIndexOrThrow(_stmt, "content_id")
        val _columnIndexOfContentType: Int = getColumnIndexOrThrow(_stmt, "content_type")
        val _columnIndexOfPosition: Int = getColumnIndexOrThrow(_stmt, "position")
        val _columnIndexOfGroupId: Int = getColumnIndexOrThrow(_stmt, "group_id")
        val _columnIndexOfGroupKey: Int = getColumnIndexOrThrow(_stmt, "group_key")
        val _columnIndexOfAddedAt: Int = getColumnIndexOrThrow(_stmt, "added_at")
        val _result: FavoriteEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentId: Long
          _tmpContentId = _stmt.getLong(_columnIndexOfContentId)
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
          val _tmpPosition: Int
          _tmpPosition = _stmt.getLong(_columnIndexOfPosition).toInt()
          val _tmpGroupId: Long?
          if (_stmt.isNull(_columnIndexOfGroupId)) {
            _tmpGroupId = null
          } else {
            _tmpGroupId = _stmt.getLong(_columnIndexOfGroupId)
          }
          val _tmpGroupKey: Long
          _tmpGroupKey = _stmt.getLong(_columnIndexOfGroupKey)
          val _tmpAddedAt: Long
          _tmpAddedAt = _stmt.getLong(_columnIndexOfAddedAt)
          _result = FavoriteEntity(_tmpId,_tmpProviderId,_tmpContentId,_tmpContentType,_tmpPosition,_tmpGroupId,_tmpGroupKey,_tmpAddedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  protected override suspend fun getGroupConstraint(groupId: Long): FavoriteGroupConstraint? {
    val _sql: String = "SELECT provider_id, content_type FROM virtual_groups WHERE id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, groupId)
        val _columnIndexOfProviderId: Int = 0
        val _columnIndexOfContentType: Int = 1
        val _result: FavoriteGroupConstraint?
        if (_stmt.step()) {
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpContentType: String
          _tmpContentType = _stmt.getText(_columnIndexOfContentType)
          _result = FavoriteGroupConstraint(_tmpProviderId,_tmpContentType)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(
    providerId: Long,
    contentId: Long,
    contentType: String,
    groupId: Long?,
  ) {
    val _sql: String = "DELETE FROM favorites WHERE provider_id = ? AND content_id = ? AND content_type = ? AND (? IS NULL AND group_id IS NULL OR group_id = ?)"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, contentId)
        _argIndex = 3
        _stmt.bindText(_argIndex, contentType)
        _argIndex = 4
        if (groupId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, groupId)
        }
        _argIndex = 5
        if (groupId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, groupId)
        }
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteMissingLiveFavorites(): Int {
    val _sql: String = "DELETE FROM favorites WHERE content_type = 'LIVE' AND content_id NOT IN (SELECT id FROM channels)"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteMissingMovieFavorites(): Int {
    val _sql: String = "DELETE FROM favorites WHERE content_type = 'MOVIE' AND content_id NOT IN (SELECT id FROM movies)"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteMissingSeriesFavorites(): Int {
    val _sql: String = "DELETE FROM favorites WHERE content_type = 'SERIES' AND content_id NOT IN (SELECT id FROM series)"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  protected override suspend fun updateGroupDirect(favoriteId: Long, groupId: Long?) {
    val _sql: String = "UPDATE favorites SET group_id = ?, group_key = COALESCE(?, 0) WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        if (groupId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, groupId)
        }
        _argIndex = 2
        if (groupId == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, groupId)
        }
        _argIndex = 3
        _stmt.bindLong(_argIndex, favoriteId)
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
