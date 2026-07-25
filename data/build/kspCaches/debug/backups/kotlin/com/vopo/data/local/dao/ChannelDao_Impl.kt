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
import com.vopo.`data`.local.entity.CategoryCount
import com.vopo.`data`.local.entity.ChannelBrowseEntity
import com.vopo.`data`.local.entity.ChannelEntity
import com.vopo.`data`.local.entity.ChannelGuideLookupEntity
import com.vopo.`data`.local.entity.ChannelGuideSyncEntity
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
public class ChannelDao_Impl(
  __db: RoomDatabase,
) : ChannelDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfChannelEntity: EntityInsertAdapter<ChannelEntity>

  private val __updateAdapterOfChannelEntity: EntityDeleteOrUpdateAdapter<ChannelEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfChannelEntity = object : EntityInsertAdapter<ChannelEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `channels` (`id`,`stream_id`,`name`,`logo_url`,`group_title`,`category_id`,`category_name`,`stream_url`,`epg_channel_id`,`number`,`catch_up_supported`,`catch_up_days`,`catchUpSource`,`provider_id`,`is_adult`,`is_user_protected`,`logical_group_id`,`error_count`,`quality_options_json`,`sync_fingerprint`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ChannelEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.streamId)
        statement.bindText(3, entity.name)
        val _tmpLogoUrl: String? = entity.logoUrl
        if (_tmpLogoUrl == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpLogoUrl)
        }
        val _tmpGroupTitle: String? = entity.groupTitle
        if (_tmpGroupTitle == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpGroupTitle)
        }
        val _tmpCategoryId: Long? = entity.categoryId
        if (_tmpCategoryId == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpCategoryId)
        }
        val _tmpCategoryName: String? = entity.categoryName
        if (_tmpCategoryName == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpCategoryName)
        }
        statement.bindText(8, entity.streamUrl)
        val _tmpEpgChannelId: String? = entity.epgChannelId
        if (_tmpEpgChannelId == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpEpgChannelId)
        }
        statement.bindLong(10, entity.number.toLong())
        val _tmp: Int = if (entity.catchUpSupported) 1 else 0
        statement.bindLong(11, _tmp.toLong())
        statement.bindLong(12, entity.catchUpDays.toLong())
        val _tmpCatchUpSource: String? = entity.catchUpSource
        if (_tmpCatchUpSource == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpCatchUpSource)
        }
        statement.bindLong(14, entity.providerId)
        val _tmp_1: Int = if (entity.isAdult) 1 else 0
        statement.bindLong(15, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isUserProtected) 1 else 0
        statement.bindLong(16, _tmp_2.toLong())
        statement.bindText(17, entity.logicalGroupId)
        statement.bindLong(18, entity.errorCount.toLong())
        val _tmpQualityOptionsJson: String? = entity.qualityOptionsJson
        if (_tmpQualityOptionsJson == null) {
          statement.bindNull(19)
        } else {
          statement.bindText(19, _tmpQualityOptionsJson)
        }
        statement.bindText(20, entity.syncFingerprint)
      }
    }
    this.__updateAdapterOfChannelEntity = object : EntityDeleteOrUpdateAdapter<ChannelEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `channels` SET `id` = ?,`stream_id` = ?,`name` = ?,`logo_url` = ?,`group_title` = ?,`category_id` = ?,`category_name` = ?,`stream_url` = ?,`epg_channel_id` = ?,`number` = ?,`catch_up_supported` = ?,`catch_up_days` = ?,`catchUpSource` = ?,`provider_id` = ?,`is_adult` = ?,`is_user_protected` = ?,`logical_group_id` = ?,`error_count` = ?,`quality_options_json` = ?,`sync_fingerprint` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ChannelEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.streamId)
        statement.bindText(3, entity.name)
        val _tmpLogoUrl: String? = entity.logoUrl
        if (_tmpLogoUrl == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpLogoUrl)
        }
        val _tmpGroupTitle: String? = entity.groupTitle
        if (_tmpGroupTitle == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpGroupTitle)
        }
        val _tmpCategoryId: Long? = entity.categoryId
        if (_tmpCategoryId == null) {
          statement.bindNull(6)
        } else {
          statement.bindLong(6, _tmpCategoryId)
        }
        val _tmpCategoryName: String? = entity.categoryName
        if (_tmpCategoryName == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpCategoryName)
        }
        statement.bindText(8, entity.streamUrl)
        val _tmpEpgChannelId: String? = entity.epgChannelId
        if (_tmpEpgChannelId == null) {
          statement.bindNull(9)
        } else {
          statement.bindText(9, _tmpEpgChannelId)
        }
        statement.bindLong(10, entity.number.toLong())
        val _tmp: Int = if (entity.catchUpSupported) 1 else 0
        statement.bindLong(11, _tmp.toLong())
        statement.bindLong(12, entity.catchUpDays.toLong())
        val _tmpCatchUpSource: String? = entity.catchUpSource
        if (_tmpCatchUpSource == null) {
          statement.bindNull(13)
        } else {
          statement.bindText(13, _tmpCatchUpSource)
        }
        statement.bindLong(14, entity.providerId)
        val _tmp_1: Int = if (entity.isAdult) 1 else 0
        statement.bindLong(15, _tmp_1.toLong())
        val _tmp_2: Int = if (entity.isUserProtected) 1 else 0
        statement.bindLong(16, _tmp_2.toLong())
        statement.bindText(17, entity.logicalGroupId)
        statement.bindLong(18, entity.errorCount.toLong())
        val _tmpQualityOptionsJson: String? = entity.qualityOptionsJson
        if (_tmpQualityOptionsJson == null) {
          statement.bindNull(19)
        } else {
          statement.bindText(19, _tmpQualityOptionsJson)
        }
        statement.bindText(20, entity.syncFingerprint)
        statement.bindLong(21, entity.id)
      }
    }
  }

  public override suspend fun insertAll(channels: List<ChannelEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfChannelEntity.insert(_connection, channels)
  }

  public override suspend fun updateAll(channels: List<ChannelEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfChannelEntity.handleMultiple(_connection, channels)
  }

  public override suspend fun replaceAll(providerId: Long, channels: List<ChannelEntity>): Unit = performInTransactionSuspending(__db) {
    super@ChannelDao_Impl.replaceAll(providerId, channels)
  }

  public override fun getByProvider(providerId: Long): Flow<List<ChannelBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,
        |               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,
        |               provider_id, is_adult, is_user_protected, logical_group_id, error_count
        |        FROM channels
        |        WHERE provider_id = ?
        |        ORDER BY number ASC
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByProviderWithoutErrors(providerId: Long): Flow<List<ChannelBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,
        |               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,
        |               provider_id, is_adult, is_user_protected, logical_group_id, error_count
        |        FROM channels
        |        WHERE provider_id = ? AND error_count = 0
        |        ORDER BY number ASC
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByProviderWithoutErrorsBrowsePage(providerId: Long, limit: Int): Flow<List<ChannelBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,
        |               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,
        |               provider_id, is_adult, is_user_protected, logical_group_id, error_count
        |        FROM channels
        |        WHERE provider_id = ? AND error_count = 0
        |        ORDER BY number ASC
        |        LIMIT ?
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByProviderBrowsePage(providerId: Long, limit: Int): Flow<List<ChannelBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,
        |               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,
        |               provider_id, is_adult, is_user_protected, logical_group_id, error_count
        |        FROM channels
        |        WHERE provider_id = ?
        |        ORDER BY number ASC
        |        LIMIT ?
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByCategoryBrowsePage(
    providerId: Long,
    categoryId: Long,
    limit: Int,
  ): Flow<List<ChannelBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,
        |               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,
        |               provider_id, is_adult, is_user_protected, logical_group_id, error_count
        |        FROM channels
        |        WHERE provider_id = ? AND category_id = ?
        |        ORDER BY number ASC
        |        LIMIT ?
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByProviderPage(
    providerId: Long,
    limit: Int,
    offset: Int,
  ): Flow<List<ChannelEntity>> {
    val _sql: String = "SELECT * FROM channels WHERE provider_id = ? ORDER BY number ASC LIMIT ? OFFSET ?"
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfLogoUrl: Int = getColumnIndexOrThrow(_stmt, "logo_url")
        val _columnIndexOfGroupTitle: Int = getColumnIndexOrThrow(_stmt, "group_title")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfEpgChannelId: Int = getColumnIndexOrThrow(_stmt, "epg_channel_id")
        val _columnIndexOfNumber: Int = getColumnIndexOrThrow(_stmt, "number")
        val _columnIndexOfCatchUpSupported: Int = getColumnIndexOrThrow(_stmt, "catch_up_supported")
        val _columnIndexOfCatchUpDays: Int = getColumnIndexOrThrow(_stmt, "catch_up_days")
        val _columnIndexOfCatchUpSource: Int = getColumnIndexOrThrow(_stmt, "catchUpSource")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfLogicalGroupId: Int = getColumnIndexOrThrow(_stmt, "logical_group_id")
        val _columnIndexOfErrorCount: Int = getColumnIndexOrThrow(_stmt, "error_count")
        val _columnIndexOfQualityOptionsJson: Int = getColumnIndexOrThrow(_stmt, "quality_options_json")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _result: MutableList<ChannelEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          val _tmpQualityOptionsJson: String?
          if (_stmt.isNull(_columnIndexOfQualityOptionsJson)) {
            _tmpQualityOptionsJson = null
          } else {
            _tmpQualityOptionsJson = _stmt.getText(_columnIndexOfQualityOptionsJson)
          }
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          _item = ChannelEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount,_tmpQualityOptionsJson,_tmpSyncFingerprint)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByCategory(providerId: Long, categoryId: Long): Flow<List<ChannelBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,
        |               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,
        |               provider_id, is_adult, is_user_protected, logical_group_id, error_count
        |        FROM channels
        |        WHERE provider_id = ? AND category_id = ?
        |        ORDER BY number ASC
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByCategoryWithoutErrors(providerId: Long, categoryId: Long): Flow<List<ChannelBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,
        |               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,
        |               provider_id, is_adult, is_user_protected, logical_group_id, error_count
        |        FROM channels
        |        WHERE provider_id = ? AND category_id = ? AND error_count = 0
        |        ORDER BY number ASC
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByCategoryWithoutErrorsBrowsePage(
    providerId: Long,
    categoryId: Long,
    limit: Int,
  ): Flow<List<ChannelBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,
        |               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,
        |               provider_id, is_adult, is_user_protected, logical_group_id, error_count
        |        FROM channels
        |        WHERE provider_id = ? AND category_id = ? AND error_count = 0
        |        ORDER BY number ASC
        |        LIMIT ?
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderBrowsePageOffset(
    providerId: Long,
    limit: Int,
    offset: Int,
  ): List<ChannelBrowseEntity> {
    val _sql: String = """
        |
        |        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,
        |               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,
        |               provider_id, is_adult, is_user_protected, logical_group_id, error_count
        |        FROM channels
        |        WHERE provider_id = ?
        |        ORDER BY number ASC
        |        LIMIT ? OFFSET ?
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderWithoutErrorsBrowsePageOffset(
    providerId: Long,
    limit: Int,
    offset: Int,
  ): List<ChannelBrowseEntity> {
    val _sql: String = """
        |
        |        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,
        |               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,
        |               provider_id, is_adult, is_user_protected, logical_group_id, error_count
        |        FROM channels
        |        WHERE provider_id = ? AND error_count = 0
        |        ORDER BY number ASC
        |        LIMIT ? OFFSET ?
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByCategoryBrowsePageOffset(
    providerId: Long,
    categoryId: Long,
    limit: Int,
    offset: Int,
  ): List<ChannelBrowseEntity> {
    val _sql: String = """
        |
        |        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,
        |               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,
        |               provider_id, is_adult, is_user_protected, logical_group_id, error_count
        |        FROM channels
        |        WHERE provider_id = ? AND category_id = ?
        |        ORDER BY number ASC
        |        LIMIT ? OFFSET ?
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByCategoryWithoutErrorsBrowsePageOffset(
    providerId: Long,
    categoryId: Long,
    limit: Int,
    offset: Int,
  ): List<ChannelBrowseEntity> {
    val _sql: String = """
        |
        |        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,
        |               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,
        |               provider_id, is_adult, is_user_protected, logical_group_id, error_count
        |        FROM channels
        |        WHERE provider_id = ? AND category_id = ? AND error_count = 0
        |        ORDER BY number ASC
        |        LIMIT ? OFFSET ?
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByCategoryPage(
    providerId: Long,
    categoryId: Long,
    limit: Int,
    offset: Int,
  ): Flow<List<ChannelEntity>> {
    val _sql: String = "SELECT * FROM channels WHERE provider_id = ? AND category_id = ? ORDER BY number ASC LIMIT ? OFFSET ?"
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, offset.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfLogoUrl: Int = getColumnIndexOrThrow(_stmt, "logo_url")
        val _columnIndexOfGroupTitle: Int = getColumnIndexOrThrow(_stmt, "group_title")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfEpgChannelId: Int = getColumnIndexOrThrow(_stmt, "epg_channel_id")
        val _columnIndexOfNumber: Int = getColumnIndexOrThrow(_stmt, "number")
        val _columnIndexOfCatchUpSupported: Int = getColumnIndexOrThrow(_stmt, "catch_up_supported")
        val _columnIndexOfCatchUpDays: Int = getColumnIndexOrThrow(_stmt, "catch_up_days")
        val _columnIndexOfCatchUpSource: Int = getColumnIndexOrThrow(_stmt, "catchUpSource")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfLogicalGroupId: Int = getColumnIndexOrThrow(_stmt, "logical_group_id")
        val _columnIndexOfErrorCount: Int = getColumnIndexOrThrow(_stmt, "error_count")
        val _columnIndexOfQualityOptionsJson: Int = getColumnIndexOrThrow(_stmt, "quality_options_json")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _result: MutableList<ChannelEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          val _tmpQualityOptionsJson: String?
          if (_stmt.isNull(_columnIndexOfQualityOptionsJson)) {
            _tmpQualityOptionsJson = null
          } else {
            _tmpQualityOptionsJson = _stmt.getText(_columnIndexOfQualityOptionsJson)
          }
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          _item = ChannelEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount,_tmpQualityOptionsJson,_tmpSyncFingerprint)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun search(
    providerId: Long,
    query: String,
    limit: Int,
  ): Flow<List<ChannelBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT c.id, c.stream_id, c.name, c.logo_url, c.group_title, c.category_id, c.category_name, c.stream_url,
        |               c.epg_channel_id, c.number, c.catch_up_supported, c.catch_up_days, c.catchUpSource,
        |               c.provider_id, c.is_adult, c.is_user_protected, c.logical_group_id, c.error_count
        |        FROM channels c
        |        JOIN channels_fts ON c.id = channels_fts.rowid
        |        WHERE c.provider_id = ?
        |          AND channels_fts MATCH ?
        |        ORDER BY c.name ASC
        |        LIMIT ?
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels", "channels_fts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, query)
        _argIndex = 3
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun searchFallback(
    providerId: Long,
    queryLike: String,
    limit: Int,
  ): Flow<List<ChannelBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT c.id, c.stream_id, c.name, c.logo_url, c.group_title, c.category_id, c.category_name, c.stream_url,
        |               c.epg_channel_id, c.number, c.catch_up_supported, c.catch_up_days, c.catchUpSource,
        |               c.provider_id, c.is_adult, c.is_user_protected, c.logical_group_id, c.error_count
        |        FROM channels c
        |        WHERE c.provider_id = ?
        |          AND (
        |              LOWER(c.name) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(COALESCE(c.group_title, '')) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(COALESCE(c.category_name, '')) LIKE LOWER(?) ESCAPE '\'
        |          )
        |        ORDER BY c.name ASC
        |        LIMIT ?
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, queryLike)
        _argIndex = 3
        _stmt.bindText(_argIndex, queryLike)
        _argIndex = 4
        _stmt.bindText(_argIndex, queryLike)
        _argIndex = 5
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun searchByCategory(
    providerId: Long,
    categoryId: Long,
    query: String,
    limit: Int,
  ): Flow<List<ChannelBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT c.id, c.stream_id, c.name, c.logo_url, c.group_title, c.category_id, c.category_name, c.stream_url,
        |               c.epg_channel_id, c.number, c.catch_up_supported, c.catch_up_days, c.catchUpSource,
        |               c.provider_id, c.is_adult, c.is_user_protected, c.logical_group_id, c.error_count
        |        FROM channels c
        |        JOIN channels_fts ON c.id = channels_fts.rowid
        |        WHERE c.provider_id = ?
        |          AND c.category_id = ?
        |          AND channels_fts MATCH ?
        |        ORDER BY c.name ASC
        |        LIMIT ?
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels", "channels_fts")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindText(_argIndex, query)
        _argIndex = 4
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun searchByCategoryFallback(
    providerId: Long,
    categoryId: Long,
    queryLike: String,
    limit: Int,
  ): Flow<List<ChannelBrowseEntity>> {
    val _sql: String = """
        |
        |        SELECT c.id, c.stream_id, c.name, c.logo_url, c.group_title, c.category_id, c.category_name, c.stream_url,
        |               c.epg_channel_id, c.number, c.catch_up_supported, c.catch_up_days, c.catchUpSource,
        |               c.provider_id, c.is_adult, c.is_user_protected, c.logical_group_id, c.error_count
        |        FROM channels c
        |        WHERE c.provider_id = ?
        |          AND c.category_id = ?
        |          AND (
        |              LOWER(c.name) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(COALESCE(c.group_title, '')) LIKE LOWER(?) ESCAPE '\'
        |              OR LOWER(COALESCE(c.category_name, '')) LIKE LOWER(?) ESCAPE '\'
        |          )
        |        ORDER BY c.name ASC
        |        LIMIT ?
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, categoryId)
        _argIndex = 3
        _stmt.bindText(_argIndex, queryLike)
        _argIndex = 4
        _stmt.bindText(_argIndex, queryLike)
        _argIndex = 5
        _stmt.bindText(_argIndex, queryLike)
        _argIndex = 6
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long): ChannelEntity? {
    val _sql: String = "SELECT * FROM channels WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfLogoUrl: Int = getColumnIndexOrThrow(_stmt, "logo_url")
        val _columnIndexOfGroupTitle: Int = getColumnIndexOrThrow(_stmt, "group_title")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfEpgChannelId: Int = getColumnIndexOrThrow(_stmt, "epg_channel_id")
        val _columnIndexOfNumber: Int = getColumnIndexOrThrow(_stmt, "number")
        val _columnIndexOfCatchUpSupported: Int = getColumnIndexOrThrow(_stmt, "catch_up_supported")
        val _columnIndexOfCatchUpDays: Int = getColumnIndexOrThrow(_stmt, "catch_up_days")
        val _columnIndexOfCatchUpSource: Int = getColumnIndexOrThrow(_stmt, "catchUpSource")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfLogicalGroupId: Int = getColumnIndexOrThrow(_stmt, "logical_group_id")
        val _columnIndexOfErrorCount: Int = getColumnIndexOrThrow(_stmt, "error_count")
        val _columnIndexOfQualityOptionsJson: Int = getColumnIndexOrThrow(_stmt, "quality_options_json")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _result: ChannelEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          val _tmpQualityOptionsJson: String?
          if (_stmt.isNull(_columnIndexOfQualityOptionsJson)) {
            _tmpQualityOptionsJson = null
          } else {
            _tmpQualityOptionsJson = _stmt.getText(_columnIndexOfQualityOptionsJson)
          }
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          _result = ChannelEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount,_tmpQualityOptionsJson,_tmpSyncFingerprint)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getGuideLookupsByIds(ids: List<Long>): List<ChannelGuideLookupEntity> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        SELECT id, stream_id, epg_channel_id")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        FROM channels")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        WHERE id IN (")
    val _inputSize: Int = ids.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        ")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in ids) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfEpgChannelId: Int = 2
        val _result: MutableList<ChannelGuideLookupEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: ChannelGuideLookupEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          _item_1 = ChannelGuideLookupEntity(_tmpId,_tmpStreamId,_tmpEpgChannelId)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getGuideSyncEntriesByProvider(providerId: Long): List<ChannelGuideSyncEntity> {
    val _sql: String = """
        |
        |        SELECT name, stream_id, epg_channel_id
        |        FROM channels
        |        WHERE provider_id = ?
        |        ORDER BY number ASC, id ASC
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfName: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfEpgChannelId: Int = 2
        val _result: MutableList<ChannelGuideSyncEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelGuideSyncEntity
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          _item = ChannelGuideSyncEntity(_tmpName,_tmpStreamId,_tmpEpgChannelId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProviderSync(providerId: Long): List<ChannelEntity> {
    val _sql: String = "SELECT * FROM channels WHERE provider_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfStreamId: Int = getColumnIndexOrThrow(_stmt, "stream_id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfLogoUrl: Int = getColumnIndexOrThrow(_stmt, "logo_url")
        val _columnIndexOfGroupTitle: Int = getColumnIndexOrThrow(_stmt, "group_title")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "category_id")
        val _columnIndexOfCategoryName: Int = getColumnIndexOrThrow(_stmt, "category_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfEpgChannelId: Int = getColumnIndexOrThrow(_stmt, "epg_channel_id")
        val _columnIndexOfNumber: Int = getColumnIndexOrThrow(_stmt, "number")
        val _columnIndexOfCatchUpSupported: Int = getColumnIndexOrThrow(_stmt, "catch_up_supported")
        val _columnIndexOfCatchUpDays: Int = getColumnIndexOrThrow(_stmt, "catch_up_days")
        val _columnIndexOfCatchUpSource: Int = getColumnIndexOrThrow(_stmt, "catchUpSource")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfIsAdult: Int = getColumnIndexOrThrow(_stmt, "is_adult")
        val _columnIndexOfIsUserProtected: Int = getColumnIndexOrThrow(_stmt, "is_user_protected")
        val _columnIndexOfLogicalGroupId: Int = getColumnIndexOrThrow(_stmt, "logical_group_id")
        val _columnIndexOfErrorCount: Int = getColumnIndexOrThrow(_stmt, "error_count")
        val _columnIndexOfQualityOptionsJson: Int = getColumnIndexOrThrow(_stmt, "quality_options_json")
        val _columnIndexOfSyncFingerprint: Int = getColumnIndexOrThrow(_stmt, "sync_fingerprint")
        val _result: MutableList<ChannelEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          val _tmpQualityOptionsJson: String?
          if (_stmt.isNull(_columnIndexOfQualityOptionsJson)) {
            _tmpQualityOptionsJson = null
          } else {
            _tmpQualityOptionsJson = _stmt.getText(_columnIndexOfQualityOptionsJson)
          }
          val _tmpSyncFingerprint: String
          _tmpSyncFingerprint = _stmt.getText(_columnIndexOfSyncFingerprint)
          _item = ChannelEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount,_tmpQualityOptionsJson,_tmpSyncFingerprint)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getIdMappings(providerId: Long): List<RemoteIdMapping> {
    val _sql: String = "SELECT id, stream_id AS remote_id FROM channels WHERE provider_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfRemoteId: Int = 1
        val _result: MutableList<RemoteIdMapping> = mutableListOf()
        while (_stmt.step()) {
          val _item: RemoteIdMapping
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpRemoteId: Long
          _tmpRemoteId = _stmt.getLong(_columnIndexOfRemoteId)
          _item = RemoteIdMapping(_tmpId,_tmpRemoteId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByIds(ids: List<Long>): Flow<List<ChannelBrowseEntity>> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("               provider_id, is_adult, is_user_protected, logical_group_id, error_count")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        FROM channels")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        WHERE id IN (")
    val _inputSize: Int = ids.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        ")
    val _sql: String = _stringBuilder.toString()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in ids) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item_1 = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByLogicalGroupIds(logicalGroupIds: List<String>): Flow<List<ChannelBrowseEntity>> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("               provider_id, is_adult, is_user_protected, logical_group_id, error_count")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        FROM channels")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        WHERE logical_group_id IN (")
    val _inputSize: Int = logicalGroupIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        ORDER BY provider_id ASC, number ASC, name ASC")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        ")
    val _sql: String = _stringBuilder.toString()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: String in logicalGroupIds) {
          _stmt.bindText(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item_1 = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByLogicalGroupId(providerId: Long, logicalGroupId: String): List<ChannelBrowseEntity> {
    val _sql: String = """
        |
        |        SELECT id, stream_id, name, logo_url, group_title, category_id, category_name, stream_url,
        |               epg_channel_id, number, catch_up_supported, catch_up_days, catchUpSource,
        |               provider_id, is_adult, is_user_protected, logical_group_id, error_count
        |        FROM channels
        |        WHERE provider_id = ? AND logical_group_id = ?
        |        ORDER BY number ASC, name ASC
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, logicalGroupId)
        val _columnIndexOfId: Int = 0
        val _columnIndexOfStreamId: Int = 1
        val _columnIndexOfName: Int = 2
        val _columnIndexOfLogoUrl: Int = 3
        val _columnIndexOfGroupTitle: Int = 4
        val _columnIndexOfCategoryId: Int = 5
        val _columnIndexOfCategoryName: Int = 6
        val _columnIndexOfStreamUrl: Int = 7
        val _columnIndexOfEpgChannelId: Int = 8
        val _columnIndexOfNumber: Int = 9
        val _columnIndexOfCatchUpSupported: Int = 10
        val _columnIndexOfCatchUpDays: Int = 11
        val _columnIndexOfCatchUpSource: Int = 12
        val _columnIndexOfProviderId: Int = 13
        val _columnIndexOfIsAdult: Int = 14
        val _columnIndexOfIsUserProtected: Int = 15
        val _columnIndexOfLogicalGroupId: Int = 16
        val _columnIndexOfErrorCount: Int = 17
        val _result: MutableList<ChannelBrowseEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelBrowseEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpStreamId: Long
          _tmpStreamId = _stmt.getLong(_columnIndexOfStreamId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpLogoUrl: String?
          if (_stmt.isNull(_columnIndexOfLogoUrl)) {
            _tmpLogoUrl = null
          } else {
            _tmpLogoUrl = _stmt.getText(_columnIndexOfLogoUrl)
          }
          val _tmpGroupTitle: String?
          if (_stmt.isNull(_columnIndexOfGroupTitle)) {
            _tmpGroupTitle = null
          } else {
            _tmpGroupTitle = _stmt.getText(_columnIndexOfGroupTitle)
          }
          val _tmpCategoryId: Long?
          if (_stmt.isNull(_columnIndexOfCategoryId)) {
            _tmpCategoryId = null
          } else {
            _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId)
          }
          val _tmpCategoryName: String?
          if (_stmt.isNull(_columnIndexOfCategoryName)) {
            _tmpCategoryName = null
          } else {
            _tmpCategoryName = _stmt.getText(_columnIndexOfCategoryName)
          }
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpEpgChannelId: String?
          if (_stmt.isNull(_columnIndexOfEpgChannelId)) {
            _tmpEpgChannelId = null
          } else {
            _tmpEpgChannelId = _stmt.getText(_columnIndexOfEpgChannelId)
          }
          val _tmpNumber: Int
          _tmpNumber = _stmt.getLong(_columnIndexOfNumber).toInt()
          val _tmpCatchUpSupported: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfCatchUpSupported).toInt()
          _tmpCatchUpSupported = _tmp != 0
          val _tmpCatchUpDays: Int
          _tmpCatchUpDays = _stmt.getLong(_columnIndexOfCatchUpDays).toInt()
          val _tmpCatchUpSource: String?
          if (_stmt.isNull(_columnIndexOfCatchUpSource)) {
            _tmpCatchUpSource = null
          } else {
            _tmpCatchUpSource = _stmt.getText(_columnIndexOfCatchUpSource)
          }
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpIsAdult: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsAdult).toInt()
          _tmpIsAdult = _tmp_1 != 0
          val _tmpIsUserProtected: Boolean
          val _tmp_2: Int
          _tmp_2 = _stmt.getLong(_columnIndexOfIsUserProtected).toInt()
          _tmpIsUserProtected = _tmp_2 != 0
          val _tmpLogicalGroupId: String
          _tmpLogicalGroupId = _stmt.getText(_columnIndexOfLogicalGroupId)
          val _tmpErrorCount: Int
          _tmpErrorCount = _stmt.getLong(_columnIndexOfErrorCount).toInt()
          _item = ChannelBrowseEntity(_tmpId,_tmpStreamId,_tmpName,_tmpLogoUrl,_tmpGroupTitle,_tmpCategoryId,_tmpCategoryName,_tmpStreamUrl,_tmpEpgChannelId,_tmpNumber,_tmpCatchUpSupported,_tmpCatchUpDays,_tmpCatchUpSource,_tmpProviderId,_tmpIsAdult,_tmpIsUserProtected,_tmpLogicalGroupId,_tmpErrorCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getRawCategoryCounts(providerId: Long): Flow<List<CategoryCount>> {
    val _sql: String = "SELECT category_id, COUNT(*) as item_count FROM channels WHERE provider_id = ? AND category_id IS NOT NULL GROUP BY category_id"
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
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

  public override fun getCategoryCounts(providerId: Long): Flow<List<CategoryCount>> {
    val _sql: String = """
        |
        |        SELECT category_id, COUNT(*) as item_count
        |        FROM channels
        |        WHERE provider_id = ?
        |          AND category_id IS NOT NULL
        |          AND NOT (TRIM(name) LIKE '##%' AND TRIM(name) LIKE '%##')
        |        GROUP BY category_id
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
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

  public override fun getRawGroupedCategoryCounts(providerId: Long): Flow<List<CategoryCount>> {
    val _sql: String = """
        |
        |        SELECT
        |            category_id,
        |            COUNT(
        |                DISTINCT CASE
        |                    WHEN logical_group_id IS NOT NULL AND logical_group_id != '' THEN logical_group_id
        |                    ELSE CAST(id AS TEXT)
        |                END
        |            ) AS item_count
        |        FROM channels
        |        WHERE provider_id = ?
        |          AND category_id IS NOT NULL
        |        GROUP BY category_id
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
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

  public override fun getGroupedCategoryCounts(providerId: Long): Flow<List<CategoryCount>> {
    val _sql: String = """
        |
        |        SELECT
        |            category_id,
        |            COUNT(
        |                DISTINCT CASE
        |                    WHEN logical_group_id IS NOT NULL AND logical_group_id != '' THEN logical_group_id
        |                    ELSE CAST(id AS TEXT)
        |                END
        |            ) AS item_count
        |        FROM channels
        |        WHERE provider_id = ?
        |          AND category_id IS NOT NULL
        |          AND NOT (TRIM(name) LIKE '##%' AND TRIM(name) LIKE '%##')
        |        GROUP BY category_id
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
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

  public override fun getRawCount(providerId: Long): Flow<Int> {
    val _sql: String = "SELECT COUNT(*) FROM channels WHERE provider_id = ?"
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
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

  public override fun getCount(providerId: Long): Flow<Int> {
    val _sql: String = """
        |
        |        SELECT COUNT(*)
        |        FROM channels
        |        WHERE provider_id = ?
        |          AND NOT (TRIM(name) LIKE '##%' AND TRIM(name) LIKE '%##')
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("channels")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
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

  public override suspend fun getMaxCatchUpDaysAcrossAllProviders(): Int {
    val _sql: String = "SELECT COALESCE(MAX(catch_up_days), 0) FROM channels WHERE catch_up_supported = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
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

  public override suspend fun deleteByProvider(providerId: Long) {
    val _sql: String = "DELETE FROM channels WHERE provider_id = ?"
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

  public override suspend fun deleteByIds(ids: List<Long>) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("DELETE FROM channels WHERE id IN (")
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
    isProtected: Boolean,
  ) {
    val _sql: String = "UPDATE channels SET is_user_protected = ? WHERE provider_id = ? AND category_id = ?"
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
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun clearProtectionForCategories(providerId: Long, categoryIds: List<Long>) {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("UPDATE channels SET is_user_protected = 0 WHERE provider_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append(" AND category_id IN (")
    val _inputSize: Int = categoryIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        for (_item: Long in categoryIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun incrementErrorCount(id: Long) {
    val _sql: String = "UPDATE channels SET error_count = error_count + 1 WHERE id = ?"
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

  public override suspend fun resetErrorCount(id: Long) {
    val _sql: String = "UPDATE channels SET error_count = 0 WHERE id = ?"
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

  public override suspend fun backfillEpgIcons(providerId: Long) {
    val _sql: String = """
        |
        |        UPDATE channels SET logo_url = (
        |            SELECT ec.icon_url
        |            FROM channel_epg_mappings cem
        |            JOIN epg_channels ec ON ec.epg_source_id = cem.epg_source_id
        |                AND ec.xmltv_channel_id = cem.xmltv_channel_id
        |            WHERE cem.provider_channel_id = channels.id
        |                AND cem.provider_id = ?
        |                AND ec.icon_url IS NOT NULL AND ec.icon_url != ''
        |            LIMIT 1
        |        )
        |        WHERE provider_id = ? AND (logo_url IS NULL OR logo_url = '')
        |    
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
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
