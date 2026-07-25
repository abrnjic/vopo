package com.vopo.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.RecordingScheduleEntity
import com.vopo.domain.model.RecordingRecurrence
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.IllegalArgumentException
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class RecordingScheduleDao_Impl(
  __db: RoomDatabase,
) : RecordingScheduleDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRecordingScheduleEntity: EntityInsertAdapter<RecordingScheduleEntity>

  private val __updateAdapterOfRecordingScheduleEntity:
      EntityDeleteOrUpdateAdapter<RecordingScheduleEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfRecordingScheduleEntity = object : EntityInsertAdapter<RecordingScheduleEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `recording_schedules` (`id`,`provider_id`,`channel_id`,`channel_name`,`stream_url`,`program_title`,`requested_start_ms`,`requested_end_ms`,`recurrence`,`recurring_rule_id`,`enabled`,`is_manual`,`priority`,`created_at`,`updated_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RecordingScheduleEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.providerId)
        statement.bindLong(3, entity.channelId)
        statement.bindText(4, entity.channelName)
        statement.bindText(5, entity.streamUrl)
        val _tmpProgramTitle: String? = entity.programTitle
        if (_tmpProgramTitle == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpProgramTitle)
        }
        statement.bindLong(7, entity.requestedStartMs)
        statement.bindLong(8, entity.requestedEndMs)
        statement.bindText(9, __RecordingRecurrence_enumToString(entity.recurrence))
        val _tmpRecurringRuleId: String? = entity.recurringRuleId
        if (_tmpRecurringRuleId == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpRecurringRuleId)
        }
        val _tmp: Int = if (entity.enabled) 1 else 0
        statement.bindLong(11, _tmp.toLong())
        val _tmp_1: Int = if (entity.isManual) 1 else 0
        statement.bindLong(12, _tmp_1.toLong())
        statement.bindLong(13, entity.priority.toLong())
        statement.bindLong(14, entity.createdAt)
        statement.bindLong(15, entity.updatedAt)
      }
    }
    this.__updateAdapterOfRecordingScheduleEntity = object : EntityDeleteOrUpdateAdapter<RecordingScheduleEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `recording_schedules` SET `id` = ?,`provider_id` = ?,`channel_id` = ?,`channel_name` = ?,`stream_url` = ?,`program_title` = ?,`requested_start_ms` = ?,`requested_end_ms` = ?,`recurrence` = ?,`recurring_rule_id` = ?,`enabled` = ?,`is_manual` = ?,`priority` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RecordingScheduleEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.providerId)
        statement.bindLong(3, entity.channelId)
        statement.bindText(4, entity.channelName)
        statement.bindText(5, entity.streamUrl)
        val _tmpProgramTitle: String? = entity.programTitle
        if (_tmpProgramTitle == null) {
          statement.bindNull(6)
        } else {
          statement.bindText(6, _tmpProgramTitle)
        }
        statement.bindLong(7, entity.requestedStartMs)
        statement.bindLong(8, entity.requestedEndMs)
        statement.bindText(9, __RecordingRecurrence_enumToString(entity.recurrence))
        val _tmpRecurringRuleId: String? = entity.recurringRuleId
        if (_tmpRecurringRuleId == null) {
          statement.bindNull(10)
        } else {
          statement.bindText(10, _tmpRecurringRuleId)
        }
        val _tmp: Int = if (entity.enabled) 1 else 0
        statement.bindLong(11, _tmp.toLong())
        val _tmp_1: Int = if (entity.isManual) 1 else 0
        statement.bindLong(12, _tmp_1.toLong())
        statement.bindLong(13, entity.priority.toLong())
        statement.bindLong(14, entity.createdAt)
        statement.bindLong(15, entity.updatedAt)
        statement.bindLong(16, entity.id)
      }
    }
  }

  public override suspend fun insert(schedule: RecordingScheduleEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfRecordingScheduleEntity.insertAndReturnId(_connection, schedule)
    _result
  }

  public override suspend fun update(schedule: RecordingScheduleEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfRecordingScheduleEntity.handle(_connection, schedule)
  }

  public override suspend fun getById(id: Long): RecordingScheduleEntity? {
    val _sql: String = "SELECT * FROM recording_schedules WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfChannelId: Int = getColumnIndexOrThrow(_stmt, "channel_id")
        val _columnIndexOfChannelName: Int = getColumnIndexOrThrow(_stmt, "channel_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfProgramTitle: Int = getColumnIndexOrThrow(_stmt, "program_title")
        val _columnIndexOfRequestedStartMs: Int = getColumnIndexOrThrow(_stmt, "requested_start_ms")
        val _columnIndexOfRequestedEndMs: Int = getColumnIndexOrThrow(_stmt, "requested_end_ms")
        val _columnIndexOfRecurrence: Int = getColumnIndexOrThrow(_stmt, "recurrence")
        val _columnIndexOfRecurringRuleId: Int = getColumnIndexOrThrow(_stmt, "recurring_rule_id")
        val _columnIndexOfEnabled: Int = getColumnIndexOrThrow(_stmt, "enabled")
        val _columnIndexOfIsManual: Int = getColumnIndexOrThrow(_stmt, "is_manual")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: RecordingScheduleEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpChannelId: Long
          _tmpChannelId = _stmt.getLong(_columnIndexOfChannelId)
          val _tmpChannelName: String
          _tmpChannelName = _stmt.getText(_columnIndexOfChannelName)
          val _tmpStreamUrl: String
          _tmpStreamUrl = _stmt.getText(_columnIndexOfStreamUrl)
          val _tmpProgramTitle: String?
          if (_stmt.isNull(_columnIndexOfProgramTitle)) {
            _tmpProgramTitle = null
          } else {
            _tmpProgramTitle = _stmt.getText(_columnIndexOfProgramTitle)
          }
          val _tmpRequestedStartMs: Long
          _tmpRequestedStartMs = _stmt.getLong(_columnIndexOfRequestedStartMs)
          val _tmpRequestedEndMs: Long
          _tmpRequestedEndMs = _stmt.getLong(_columnIndexOfRequestedEndMs)
          val _tmpRecurrence: RecordingRecurrence
          _tmpRecurrence = __RecordingRecurrence_stringToEnum(_stmt.getText(_columnIndexOfRecurrence))
          val _tmpRecurringRuleId: String?
          if (_stmt.isNull(_columnIndexOfRecurringRuleId)) {
            _tmpRecurringRuleId = null
          } else {
            _tmpRecurringRuleId = _stmt.getText(_columnIndexOfRecurringRuleId)
          }
          val _tmpEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfEnabled).toInt()
          _tmpEnabled = _tmp != 0
          val _tmpIsManual: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsManual).toInt()
          _tmpIsManual = _tmp_1 != 0
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _result = RecordingScheduleEntity(_tmpId,_tmpProviderId,_tmpChannelId,_tmpChannelName,_tmpStreamUrl,_tmpProgramTitle,_tmpRequestedStartMs,_tmpRequestedEndMs,_tmpRecurrence,_tmpRecurringRuleId,_tmpEnabled,_tmpIsManual,_tmpPriority,_tmpCreatedAt,_tmpUpdatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(id: Long) {
    val _sql: String = "DELETE FROM recording_schedules WHERE id = ?"
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

  private fun __RecordingRecurrence_enumToString(_value: RecordingRecurrence): String = when (_value) {
    RecordingRecurrence.NONE -> "NONE"
    RecordingRecurrence.DAILY -> "DAILY"
    RecordingRecurrence.WEEKLY -> "WEEKLY"
  }

  private fun __RecordingRecurrence_stringToEnum(_value: String): RecordingRecurrence = when (_value) {
    "NONE" -> RecordingRecurrence.NONE
    "DAILY" -> RecordingRecurrence.DAILY
    "WEEKLY" -> RecordingRecurrence.WEEKLY
    else -> throw IllegalArgumentException("Can't convert value to enum, unknown value: " + _value)
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
