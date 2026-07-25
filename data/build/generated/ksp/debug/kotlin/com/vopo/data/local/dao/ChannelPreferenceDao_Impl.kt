package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.ChannelPreferenceEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ChannelPreferenceDao_Impl(
  __db: RoomDatabase,
) : ChannelPreferenceDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfChannelPreferenceEntity: EntityInsertAdapter<ChannelPreferenceEntity>

  private val __insertAdapterOfChannelPreferenceEntity_1:
      EntityInsertAdapter<ChannelPreferenceEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfChannelPreferenceEntity = object : EntityInsertAdapter<ChannelPreferenceEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `channel_preferences` (`id`,`channel_id`,`aspect_ratio`,`audio_video_offset_ms`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ChannelPreferenceEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.channelId)
        val _tmpAspectRatio: String? = entity.aspectRatio
        if (_tmpAspectRatio == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpAspectRatio)
        }
        val _tmpAudioVideoOffsetMs: Int? = entity.audioVideoOffsetMs
        if (_tmpAudioVideoOffsetMs == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpAudioVideoOffsetMs.toLong())
        }
        statement.bindLong(5, entity.updatedAt)
      }
    }
    this.__insertAdapterOfChannelPreferenceEntity_1 = object : EntityInsertAdapter<ChannelPreferenceEntity>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `channel_preferences` (`id`,`channel_id`,`aspect_ratio`,`audio_video_offset_ms`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ChannelPreferenceEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.channelId)
        val _tmpAspectRatio: String? = entity.aspectRatio
        if (_tmpAspectRatio == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpAspectRatio)
        }
        val _tmpAudioVideoOffsetMs: Int? = entity.audioVideoOffsetMs
        if (_tmpAudioVideoOffsetMs == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpAudioVideoOffsetMs.toLong())
        }
        statement.bindLong(5, entity.updatedAt)
      }
    }
  }

  public override suspend fun upsert(preference: ChannelPreferenceEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfChannelPreferenceEntity.insert(_connection, preference)
  }

  public override suspend fun insertIgnore(preference: ChannelPreferenceEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfChannelPreferenceEntity_1.insertAndReturnId(_connection, preference)
    _result
  }

  public override suspend fun setAspectRatio(channelId: Long, aspectRatio: String): Unit = performInTransactionSuspending(__db) {
    super@ChannelPreferenceDao_Impl.setAspectRatio(channelId, aspectRatio)
  }

  public override suspend fun setAudioVideoOffset(channelId: Long, offsetMs: Int?): Unit = performInTransactionSuspending(__db) {
    super@ChannelPreferenceDao_Impl.setAudioVideoOffset(channelId, offsetMs)
  }

  public override fun observeAspectRatio(channelId: Long): Flow<String?> {
    val _sql: String = "SELECT aspect_ratio FROM channel_preferences WHERE channel_id = ? LIMIT 1"
    return createFlow(__db, false, arrayOf("channel_preferences")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, channelId)
        val _result: String?
        if (_stmt.step()) {
          if (_stmt.isNull(0)) {
            _result = null
          } else {
            _result = _stmt.getText(0)
          }
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeAudioVideoOffset(channelId: Long): Flow<Int?> {
    val _sql: String = "SELECT audio_video_offset_ms FROM channel_preferences WHERE channel_id = ? LIMIT 1"
    return createFlow(__db, false, arrayOf("channel_preferences")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, channelId)
        val _result: Int?
        if (_stmt.step()) {
          if (_stmt.isNull(0)) {
            _result = null
          } else {
            _result = _stmt.getLong(0).toInt()
          }
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateAspectRatio(
    channelId: Long,
    aspectRatio: String?,
    updatedAt: Long,
  ): Int {
    val _sql: String = "UPDATE channel_preferences SET aspect_ratio = ?, updated_at = ? WHERE channel_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        if (aspectRatio == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindText(_argIndex, aspectRatio)
        }
        _argIndex = 2
        _stmt.bindLong(_argIndex, updatedAt)
        _argIndex = 3
        _stmt.bindLong(_argIndex, channelId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateAudioVideoOffset(
    channelId: Long,
    offsetMs: Int?,
    updatedAt: Long,
  ): Int {
    val _sql: String = "UPDATE channel_preferences SET audio_video_offset_ms = ?, updated_at = ? WHERE channel_id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        if (offsetMs == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, offsetMs.toLong())
        }
        _argIndex = 2
        _stmt.bindLong(_argIndex, updatedAt)
        _argIndex = 3
        _stmt.bindLong(_argIndex, channelId)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAll() {
    val _sql: String = "DELETE FROM channel_preferences"
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
