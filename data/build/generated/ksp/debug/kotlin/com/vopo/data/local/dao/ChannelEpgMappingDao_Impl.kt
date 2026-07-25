package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.ChannelEpgMappingEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Float
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
public class ChannelEpgMappingDao_Impl(
  __db: RoomDatabase,
) : ChannelEpgMappingDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfChannelEpgMappingEntity: EntityInsertAdapter<ChannelEpgMappingEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfChannelEpgMappingEntity = object : EntityInsertAdapter<ChannelEpgMappingEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `channel_epg_mappings` (`id`,`provider_channel_id`,`provider_id`,`source_type`,`epg_source_id`,`xmltv_channel_id`,`match_type`,`confidence`,`matched_at`,`failed_attempts`,`source`,`is_manual_override`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ChannelEpgMappingEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.providerChannelId)
        statement.bindLong(3, entity.providerId)
        statement.bindText(4, entity.sourceType)
        val _tmpEpgSourceId: Long? = entity.epgSourceId
        if (_tmpEpgSourceId == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpEpgSourceId)
        }
        val _tmpXmltvChannelId: String? = entity.xmltvChannelId
        if (_tmpXmltvChannelId == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpXmltvChannelId)
        }
        val _tmpMatchType: String? = entity.matchType
        if (_tmpMatchType == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpMatchType)
        }
        statement.bindDouble(8, entity.confidence.toDouble())
        statement.bindLong(9, entity.matchedAt)
        statement.bindLong(10, entity.failedAttempts.toLong())
        val _tmpSource: String? = entity.source
        if (_tmpSource == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpSource)
        }
        val _tmp: Int = if (entity.isManualOverride) 1 else 0
        statement.bindLong(12, _tmp.toLong())
        statement.bindLong(13, entity.updatedAt)
      }
    }
  }

  public override suspend fun insertAll(mappings: List<ChannelEpgMappingEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfChannelEpgMappingEntity.insert(_connection, mappings)
  }

  public override suspend fun upsert(mapping: ChannelEpgMappingEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfChannelEpgMappingEntity.insert(_connection, mapping)
  }

  public override suspend fun replaceForProvider(providerId: Long, mappings: List<ChannelEpgMappingEntity>): Unit = performInTransactionSuspending(__db) {
    super@ChannelEpgMappingDao_Impl.replaceForProvider(providerId, mappings)
  }

  public override suspend fun getForProvider(providerId: Long): List<ChannelEpgMappingEntity> {
    val _sql: String = "SELECT * FROM channel_epg_mappings WHERE provider_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderChannelId: Int = getColumnIndexOrThrow(_stmt, "provider_channel_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfSourceType: Int = getColumnIndexOrThrow(_stmt, "source_type")
        val _columnIndexOfEpgSourceId: Int = getColumnIndexOrThrow(_stmt, "epg_source_id")
        val _columnIndexOfXmltvChannelId: Int = getColumnIndexOrThrow(_stmt, "xmltv_channel_id")
        val _columnIndexOfMatchType: Int = getColumnIndexOrThrow(_stmt, "match_type")
        val _columnIndexOfConfidence: Int = getColumnIndexOrThrow(_stmt, "confidence")
        val _columnIndexOfMatchedAt: Int = getColumnIndexOrThrow(_stmt, "matched_at")
        val _columnIndexOfFailedAttempts: Int = getColumnIndexOrThrow(_stmt, "failed_attempts")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _columnIndexOfIsManualOverride: Int = getColumnIndexOrThrow(_stmt, "is_manual_override")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: MutableList<ChannelEpgMappingEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelEpgMappingEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderChannelId: Long
          _tmpProviderChannelId = _stmt.getLong(_columnIndexOfProviderChannelId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpSourceType: String
          _tmpSourceType = _stmt.getText(_columnIndexOfSourceType)
          val _tmpEpgSourceId: Long?
          if (_stmt.isNull(_columnIndexOfEpgSourceId)) {
            _tmpEpgSourceId = null
          } else {
            _tmpEpgSourceId = _stmt.getLong(_columnIndexOfEpgSourceId)
          }
          val _tmpXmltvChannelId: String?
          if (_stmt.isNull(_columnIndexOfXmltvChannelId)) {
            _tmpXmltvChannelId = null
          } else {
            _tmpXmltvChannelId = _stmt.getText(_columnIndexOfXmltvChannelId)
          }
          val _tmpMatchType: String?
          if (_stmt.isNull(_columnIndexOfMatchType)) {
            _tmpMatchType = null
          } else {
            _tmpMatchType = _stmt.getText(_columnIndexOfMatchType)
          }
          val _tmpConfidence: Float
          _tmpConfidence = _stmt.getDouble(_columnIndexOfConfidence).toFloat()
          val _tmpMatchedAt: Long
          _tmpMatchedAt = _stmt.getLong(_columnIndexOfMatchedAt)
          val _tmpFailedAttempts: Int
          _tmpFailedAttempts = _stmt.getLong(_columnIndexOfFailedAttempts).toInt()
          val _tmpSource: String?
          if (_stmt.isNull(_columnIndexOfSource)) {
            _tmpSource = null
          } else {
            _tmpSource = _stmt.getText(_columnIndexOfSource)
          }
          val _tmpIsManualOverride: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsManualOverride).toInt()
          _tmpIsManualOverride = _tmp != 0
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item = ChannelEpgMappingEntity(_tmpId,_tmpProviderChannelId,_tmpProviderId,_tmpSourceType,_tmpEpgSourceId,_tmpXmltvChannelId,_tmpMatchType,_tmpConfidence,_tmpMatchedAt,_tmpFailedAttempts,_tmpSource,_tmpIsManualOverride,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getForChannel(providerId: Long, channelId: Long): ChannelEpgMappingEntity? {
    val _sql: String = "SELECT * FROM channel_epg_mappings WHERE provider_id = ? AND provider_channel_id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, channelId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderChannelId: Int = getColumnIndexOrThrow(_stmt, "provider_channel_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfSourceType: Int = getColumnIndexOrThrow(_stmt, "source_type")
        val _columnIndexOfEpgSourceId: Int = getColumnIndexOrThrow(_stmt, "epg_source_id")
        val _columnIndexOfXmltvChannelId: Int = getColumnIndexOrThrow(_stmt, "xmltv_channel_id")
        val _columnIndexOfMatchType: Int = getColumnIndexOrThrow(_stmt, "match_type")
        val _columnIndexOfConfidence: Int = getColumnIndexOrThrow(_stmt, "confidence")
        val _columnIndexOfMatchedAt: Int = getColumnIndexOrThrow(_stmt, "matched_at")
        val _columnIndexOfFailedAttempts: Int = getColumnIndexOrThrow(_stmt, "failed_attempts")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _columnIndexOfIsManualOverride: Int = getColumnIndexOrThrow(_stmt, "is_manual_override")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: ChannelEpgMappingEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderChannelId: Long
          _tmpProviderChannelId = _stmt.getLong(_columnIndexOfProviderChannelId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpSourceType: String
          _tmpSourceType = _stmt.getText(_columnIndexOfSourceType)
          val _tmpEpgSourceId: Long?
          if (_stmt.isNull(_columnIndexOfEpgSourceId)) {
            _tmpEpgSourceId = null
          } else {
            _tmpEpgSourceId = _stmt.getLong(_columnIndexOfEpgSourceId)
          }
          val _tmpXmltvChannelId: String?
          if (_stmt.isNull(_columnIndexOfXmltvChannelId)) {
            _tmpXmltvChannelId = null
          } else {
            _tmpXmltvChannelId = _stmt.getText(_columnIndexOfXmltvChannelId)
          }
          val _tmpMatchType: String?
          if (_stmt.isNull(_columnIndexOfMatchType)) {
            _tmpMatchType = null
          } else {
            _tmpMatchType = _stmt.getText(_columnIndexOfMatchType)
          }
          val _tmpConfidence: Float
          _tmpConfidence = _stmt.getDouble(_columnIndexOfConfidence).toFloat()
          val _tmpMatchedAt: Long
          _tmpMatchedAt = _stmt.getLong(_columnIndexOfMatchedAt)
          val _tmpFailedAttempts: Int
          _tmpFailedAttempts = _stmt.getLong(_columnIndexOfFailedAttempts).toInt()
          val _tmpSource: String?
          if (_stmt.isNull(_columnIndexOfSource)) {
            _tmpSource = null
          } else {
            _tmpSource = _stmt.getText(_columnIndexOfSource)
          }
          val _tmpIsManualOverride: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsManualOverride).toInt()
          _tmpIsManualOverride = _tmp != 0
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _result = ChannelEpgMappingEntity(_tmpId,_tmpProviderChannelId,_tmpProviderId,_tmpSourceType,_tmpEpgSourceId,_tmpXmltvChannelId,_tmpMatchType,_tmpConfidence,_tmpMatchedAt,_tmpFailedAttempts,_tmpSource,_tmpIsManualOverride,_tmpUpdatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getForChannels(providerId: Long, channelIds: List<Long>): List<ChannelEpgMappingEntity> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        SELECT * FROM channel_epg_mappings")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("        WHERE provider_id = ")
    _stringBuilder.append("?")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("          AND provider_channel_id IN (")
    val _inputSize: Int = channelIds.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    _stringBuilder.append("""
        |
        |""".trimMargin())
    _stringBuilder.append("    ")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        for (_item: Long in channelIds) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderChannelId: Int = getColumnIndexOrThrow(_stmt, "provider_channel_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfSourceType: Int = getColumnIndexOrThrow(_stmt, "source_type")
        val _columnIndexOfEpgSourceId: Int = getColumnIndexOrThrow(_stmt, "epg_source_id")
        val _columnIndexOfXmltvChannelId: Int = getColumnIndexOrThrow(_stmt, "xmltv_channel_id")
        val _columnIndexOfMatchType: Int = getColumnIndexOrThrow(_stmt, "match_type")
        val _columnIndexOfConfidence: Int = getColumnIndexOrThrow(_stmt, "confidence")
        val _columnIndexOfMatchedAt: Int = getColumnIndexOrThrow(_stmt, "matched_at")
        val _columnIndexOfFailedAttempts: Int = getColumnIndexOrThrow(_stmt, "failed_attempts")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _columnIndexOfIsManualOverride: Int = getColumnIndexOrThrow(_stmt, "is_manual_override")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: MutableList<ChannelEpgMappingEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: ChannelEpgMappingEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderChannelId: Long
          _tmpProviderChannelId = _stmt.getLong(_columnIndexOfProviderChannelId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpSourceType: String
          _tmpSourceType = _stmt.getText(_columnIndexOfSourceType)
          val _tmpEpgSourceId: Long?
          if (_stmt.isNull(_columnIndexOfEpgSourceId)) {
            _tmpEpgSourceId = null
          } else {
            _tmpEpgSourceId = _stmt.getLong(_columnIndexOfEpgSourceId)
          }
          val _tmpXmltvChannelId: String?
          if (_stmt.isNull(_columnIndexOfXmltvChannelId)) {
            _tmpXmltvChannelId = null
          } else {
            _tmpXmltvChannelId = _stmt.getText(_columnIndexOfXmltvChannelId)
          }
          val _tmpMatchType: String?
          if (_stmt.isNull(_columnIndexOfMatchType)) {
            _tmpMatchType = null
          } else {
            _tmpMatchType = _stmt.getText(_columnIndexOfMatchType)
          }
          val _tmpConfidence: Float
          _tmpConfidence = _stmt.getDouble(_columnIndexOfConfidence).toFloat()
          val _tmpMatchedAt: Long
          _tmpMatchedAt = _stmt.getLong(_columnIndexOfMatchedAt)
          val _tmpFailedAttempts: Int
          _tmpFailedAttempts = _stmt.getLong(_columnIndexOfFailedAttempts).toInt()
          val _tmpSource: String?
          if (_stmt.isNull(_columnIndexOfSource)) {
            _tmpSource = null
          } else {
            _tmpSource = _stmt.getText(_columnIndexOfSource)
          }
          val _tmpIsManualOverride: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsManualOverride).toInt()
          _tmpIsManualOverride = _tmp != 0
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item_1 = ChannelEpgMappingEntity(_tmpId,_tmpProviderChannelId,_tmpProviderId,_tmpSourceType,_tmpEpgSourceId,_tmpXmltvChannelId,_tmpMatchType,_tmpConfidence,_tmpMatchedAt,_tmpFailedAttempts,_tmpSource,_tmpIsManualOverride,_tmpUpdatedAt)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getResolutionStats(providerId: Long): List<EpgResolutionStatRow> {
    val _sql: String = """
        |
        |        SELECT source_type, match_type, COUNT(*) as cnt
        |        FROM channel_epg_mappings
        |        WHERE provider_id = ?
        |        GROUP BY source_type, match_type
        |    
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _columnIndexOfSourceType: Int = 0
        val _columnIndexOfMatchType: Int = 1
        val _columnIndexOfCnt: Int = 2
        val _result: MutableList<EpgResolutionStatRow> = mutableListOf()
        while (_stmt.step()) {
          val _item: EpgResolutionStatRow
          val _tmpSourceType: String
          _tmpSourceType = _stmt.getText(_columnIndexOfSourceType)
          val _tmpMatchType: String?
          if (_stmt.isNull(_columnIndexOfMatchType)) {
            _tmpMatchType = null
          } else {
            _tmpMatchType = _stmt.getText(_columnIndexOfMatchType)
          }
          val _tmpCnt: Int
          _tmpCnt = _stmt.getLong(_columnIndexOfCnt).toInt()
          _item = EpgResolutionStatRow(_tmpSourceType,_tmpMatchType,_tmpCnt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun countLowConfidence(providerId: Long, minConfidence: Float): Int {
    val _sql: String = """
        |
        |        SELECT COUNT(*) FROM channel_epg_mappings
        |        WHERE provider_id = ?
        |          AND confidence > 0
        |          AND confidence < ?
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindDouble(_argIndex, minConfidence.toDouble())
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

  public override suspend fun countRematchCandidates(
    providerId: Long,
    minConfidence: Float,
    maxAttempts: Int,
  ): Int {
    val _sql: String = """
        |
        |        SELECT COUNT(*) FROM channel_epg_mappings
        |        WHERE provider_id = ?
        |          AND failed_attempts < ?
        |          AND (
        |              source_type = 'NONE'
        |              OR (confidence > 0 AND confidence < ?)
        |          )
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, maxAttempts.toLong())
        _argIndex = 3
        _stmt.bindDouble(_argIndex, minConfidence.toDouble())
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

  public override suspend fun getChannelsNeedingRematch(
    providerId: Long,
    minConfidence: Float,
    maxAttempts: Int,
    limit: Int,
  ): List<ChannelEpgMappingEntity> {
    val _sql: String = """
        |
        |        SELECT * FROM channel_epg_mappings
        |        WHERE provider_id = ?
        |          AND failed_attempts < ?
        |          AND (
        |              source_type = 'NONE'
        |              OR (confidence > 0 AND confidence < ?)
        |          )
        |        ORDER BY confidence ASC, failed_attempts ASC, provider_channel_id ASC
        |        LIMIT ?
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, maxAttempts.toLong())
        _argIndex = 3
        _stmt.bindDouble(_argIndex, minConfidence.toDouble())
        _argIndex = 4
        _stmt.bindLong(_argIndex, limit.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderChannelId: Int = getColumnIndexOrThrow(_stmt, "provider_channel_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfSourceType: Int = getColumnIndexOrThrow(_stmt, "source_type")
        val _columnIndexOfEpgSourceId: Int = getColumnIndexOrThrow(_stmt, "epg_source_id")
        val _columnIndexOfXmltvChannelId: Int = getColumnIndexOrThrow(_stmt, "xmltv_channel_id")
        val _columnIndexOfMatchType: Int = getColumnIndexOrThrow(_stmt, "match_type")
        val _columnIndexOfConfidence: Int = getColumnIndexOrThrow(_stmt, "confidence")
        val _columnIndexOfMatchedAt: Int = getColumnIndexOrThrow(_stmt, "matched_at")
        val _columnIndexOfFailedAttempts: Int = getColumnIndexOrThrow(_stmt, "failed_attempts")
        val _columnIndexOfSource: Int = getColumnIndexOrThrow(_stmt, "source")
        val _columnIndexOfIsManualOverride: Int = getColumnIndexOrThrow(_stmt, "is_manual_override")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: MutableList<ChannelEpgMappingEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ChannelEpgMappingEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderChannelId: Long
          _tmpProviderChannelId = _stmt.getLong(_columnIndexOfProviderChannelId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpSourceType: String
          _tmpSourceType = _stmt.getText(_columnIndexOfSourceType)
          val _tmpEpgSourceId: Long?
          if (_stmt.isNull(_columnIndexOfEpgSourceId)) {
            _tmpEpgSourceId = null
          } else {
            _tmpEpgSourceId = _stmt.getLong(_columnIndexOfEpgSourceId)
          }
          val _tmpXmltvChannelId: String?
          if (_stmt.isNull(_columnIndexOfXmltvChannelId)) {
            _tmpXmltvChannelId = null
          } else {
            _tmpXmltvChannelId = _stmt.getText(_columnIndexOfXmltvChannelId)
          }
          val _tmpMatchType: String?
          if (_stmt.isNull(_columnIndexOfMatchType)) {
            _tmpMatchType = null
          } else {
            _tmpMatchType = _stmt.getText(_columnIndexOfMatchType)
          }
          val _tmpConfidence: Float
          _tmpConfidence = _stmt.getDouble(_columnIndexOfConfidence).toFloat()
          val _tmpMatchedAt: Long
          _tmpMatchedAt = _stmt.getLong(_columnIndexOfMatchedAt)
          val _tmpFailedAttempts: Int
          _tmpFailedAttempts = _stmt.getLong(_columnIndexOfFailedAttempts).toInt()
          val _tmpSource: String?
          if (_stmt.isNull(_columnIndexOfSource)) {
            _tmpSource = null
          } else {
            _tmpSource = _stmt.getText(_columnIndexOfSource)
          }
          val _tmpIsManualOverride: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsManualOverride).toInt()
          _tmpIsManualOverride = _tmp != 0
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item = ChannelEpgMappingEntity(_tmpId,_tmpProviderChannelId,_tmpProviderId,_tmpSourceType,_tmpEpgSourceId,_tmpXmltvChannelId,_tmpMatchType,_tmpConfidence,_tmpMatchedAt,_tmpFailedAttempts,_tmpSource,_tmpIsManualOverride,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteForChannel(providerId: Long, channelId: Long) {
    val _sql: String = "DELETE FROM channel_epg_mappings WHERE provider_id = ? AND provider_channel_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindLong(_argIndex, channelId)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByProvider(providerId: Long) {
    val _sql: String = "DELETE FROM channel_epg_mappings WHERE provider_id = ?"
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
