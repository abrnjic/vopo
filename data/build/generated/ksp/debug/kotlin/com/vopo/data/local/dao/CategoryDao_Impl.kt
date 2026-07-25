package com.vopo.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.RoomEnumConverters
import com.vopo.`data`.local.entity.CategoryEntity
import com.vopo.domain.model.ContentType
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
public class CategoryDao_Impl(
  __db: RoomDatabase,
) : CategoryDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfCategoryEntity: EntityInsertAdapter<CategoryEntity>

  private val __roomEnumConverters: RoomEnumConverters = RoomEnumConverters()

  private val __updateAdapterOfCategoryEntity: EntityDeleteOrUpdateAdapter<CategoryEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfCategoryEntity = object : EntityInsertAdapter<CategoryEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `categories` (`id`,`category_id`,`name`,`parent_id`,`type`,`provider_id`,`is_adult`,`is_user_protected`,`sync_fingerprint`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: CategoryEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.categoryId)
        statement.bindText(3, entity.name)
        val _tmpParentId: Long? = entity.parentId
        if (_tmpParentId == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpParentId)
        }
        val _tmp: String? = __roomEnumConverters.fromContentType(entity.type)
        if (_tmp == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmp)
        }
        statement.bindLong(6, entity.providerId)
        val _tmp_1: Int = if (entity.isAdult) 1 else 0
        statement.bindLong(7, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isUserProtected) 1 else 0
        statement.bindLong(8, _tmp_2.toLong())
        statement.bindText(9, entity.syncFingerprint)
      }
    }
    this.__updateAdapterOfCategoryEntity = object : EntityDeleteOrUpdateAdapter<CategoryEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `categories` SET `id` = ?,`category_id` = ?,`name` = ?,`parent_id` = ?,`type` = ?,`provider_id` = ?,`is_adult` = ?,`is_user_protected` = ?,`sync_fingerprint` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: CategoryEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.categoryId)
        statement.bindText(3, entity.name)
        val _tmpParentId: Long? = entity.parentId
        if (_tmpParentId == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpParentId)
        }
        val _tmp: String? = __roomEnumConverters.fromContentType(entity.type)
        if (_tmp == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmp)
        }
        statement.bindLong(6, entity.providerId)
        val _tmp_1: Int = if (entity.isAdult) 1 else 0
        statement.bindLong(7, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isUserProtected) 1 else 0
        statement.bindLong(8, _tmp_2.toLong())
        statement.bindText(9, entity.syncFingerprint)
        statement.bindLong(10, entity.id)
      }
    }
  }

  public override suspend fun insertAll(categories: List<CategoryEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfCategoryEntity.insert(_connection, categories)
  }

  public override suspend fun updateAll(categories: List<CategoryEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfCategoryEntity.handleMultiple(_connection, categories)
  }

  public override suspend fun replaceAll(
    providerId: Long,
    type: String,
    categories: List<CategoryEntity>,
  ): Unit = performInTransactionSuspending(__db) {
    super@CategoryDao_Impl.replaceAll(providerId, type, categories)
  }

  public override fun getByProviderAndType(providerId: Long, type: String): Flow<List<CategoryEntity>> {
    val _sql: String = "SELECT * FROM categories WHERE provider_id = ? AND type = ? ORDER BY id ASC"
    return createFlow(__db, false, arrayOf("categories")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, type)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfParentId: Int = getColumnIndexOrThrow(_stmt, "parent_id")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _result: MutableList<CategoryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CategoryEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpCategoryId: Long
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpParentId: Long?
          if (_stmt.isNull(_columnIndexOfParentId)) {
            _tmpParentId = null
          } else {
            _tmpParentId = _stmt.getLong(_columnIndexOfParentId)
          }
          val _tmpType: ContentType
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfType)
          }
          val _tmp_1: ContentType? = __roomEnumConverters.toContentType(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ContentType', but it was NULL.")
          } else {
            _tmpType = _tmp_1
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_2 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_3 != 0
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          _item = CategoryEntity(_tmpId,_tmpCategoryId,_tmpName,_tmpParentId,_tmpType,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderAndTypeSync(providerId: Long, type: String): List<CategoryEntity> {
    val _sql: String = "SELECT * FROM categories WHERE provider_id = ? AND type = ? ORDER BY id ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, type)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfParentId: Int = getColumnIndexOrThrow(_stmt, "parent_id")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _result: MutableList<CategoryEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: CategoryEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpCategoryId: Long
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpParentId: Long?
          if (_stmt.isNull(_columnIndexOfParentId)) {
            _tmpParentId = null
          } else {
            _tmpParentId = _stmt.getLong(_columnIndexOfParentId)
          }
          val _tmpType: ContentType
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfType)
          }
          val _tmp_1: ContentType? = __roomEnumConverters.toContentType(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ContentType', but it was NULL.")
          } else {
            _tmpType = _tmp_1
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_2 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_3: Int
          _tmp_3 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_3 != 0
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          _item = CategoryEntity(_tmpId,_tmpCategoryId,_tmpName,_tmpParentId,_tmpType,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpSyncFingerprint)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByProviderAndType(providerId: Long, type: String) {
    val _sql: String = "DELETE FROM categories WHERE provider_id = ? AND type = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, type)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByIds(ids: List<Long>) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("DELETE FROM categories WHERE id IN (")
    val _inputSize: Int = ids.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in ids) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateProtectionStatus(
    providerId: Long,
    categoryId: Long,
    type: String,
    isProtected: Boolean,
  ) {
    val _sql: String = "UPDATE categories SET is_user_protected = ? WHERE provider_id = ? AND category_id = ? AND type = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (isProtected) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 4
        _stmt.bindText(_argIndex, type)
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
