package com.vopo.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.RecordingRunEntity
import com.vopo.`data`.local.entity.RecordingRunWithSchedule
import com.vopo.domain.model.RecordingFailureCategory
import com.vopo.domain.model.RecordingRecurrence
import com.vopo.domain.model.RecordingSourceType
import com.vopo.domain.model.RecordingStatus
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.IllegalArgumentException
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
public class RecordingRunDao_Impl(
  __db: RoomDatabase,
) : RecordingRunDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRecordingRunEntity: EntityInsertAdapter<RecordingRunEntity>

  private val __updateAdapterOfRecordingRunEntity: EntityDeleteOrUpdateAdapter<RecordingRunEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfRecordingRunEntity = object : EntityInsertAdapter<RecordingRunEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `recording_runs` (`id`,`schedule_id`,`provider_id`,`channel_id`,`channel_name`,`stream_url`,`program_title`,`scheduled_start_ms`,`scheduled_end_ms`,`recurrence`,`recurring_rule_id`,`status`,`source_type`,`resolved_url`,`headers_json`,`user_agent`,`expiration_time`,`provider_label`,`output_uri`,`output_display_path`,`bytes_written`,`average_throughput_bps`,`retry_count`,`last_progress_at_ms`,`failure_category`,`failure_reason`,`terminal_at_ms`,`started_at_ms`,`ended_at_ms`,`schedule_enabled`,`priority`,`alarm_start_at_ms`,`alarm_stop_at_ms`,`created_at`,`updated_at`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RecordingRunEntity) {
        statement.bindText(1, entity.id)
        statement.bindLong(2, entity.scheduleId)
        statement.bindLong(3, entity.providerId)
        statement.bindLong(4, entity.channelId)
        statement.bindText(5, entity.channelName)
        statement.bindText(6, entity.streamUrl)
        val _tmpProgramTitle: String? = entity.programTitle
        if (_tmpProgramTitle == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpProgramTitle)
        }
        statement.bindLong(8, entity.scheduledStartMs)
        statement.bindLong(9, entity.scheduledEndMs)
        statement.bindText(10, __RecordingRecurrence_enumToString(entity.recurrence))
        val _tmpRecurringRuleId: String? = entity.recurringRuleId
        if (_tmpRecurringRuleId == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpRecurringRuleId)
        }
        statement.bindText(12, __RecordingStatus_enumToString(entity.status))
        statement.bindText(13, __RecordingSourceType_enumToString(entity.sourceType))
        val _tmpResolvedUrl: String? = entity.resolvedUrl
        if (_tmpResolvedUrl == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpResolvedUrl)
        }
        statement.bindText(15, entity.headersJson)
        val _tmpUserAgent: String? = entity.userAgent
        if (_tmpUserAgent == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpUserAgent)
        }
        val _tmpExpirationTime: Long? = entity.expirationTime
        if (_tmpExpirationTime == null) {
          statement.bindNull(17)
        } else {
          statement.bindLong(17, _tmpExpirationTime)
        }
        val _tmpProviderLabel: String? = entity.providerLabel
        if (_tmpProviderLabel == null) {
          statement.bindNull(18)
        } else {
          statement.bindText(18, _tmpProviderLabel)
        }
        val _tmpOutputUri: String? = entity.outputUri
        if (_tmpOutputUri == null) {
          statement.bindNull(19)
        } else {
          statement.bindText(19, _tmpOutputUri)
        }
        val _tmpOutputDisplayPath: String? = entity.outputDisplayPath
        if (_tmpOutputDisplayPath == null) {
          statement.bindNull(20)
        } else {
          statement.bindText(20, _tmpOutputDisplayPath)
        }
        statement.bindLong(21, entity.bytesWritten)
        statement.bindLong(22, entity.averageThroughputBytesPerSecond)
        statement.bindLong(23, entity.retryCount.toLong())
        val _tmpLastProgressAtMs: Long? = entity.lastProgressAtMs
        if (_tmpLastProgressAtMs == null) {
          statement.bindNull(24)
        } else {
          statement.bindLong(24, _tmpLastProgressAtMs)
        }
        statement.bindText(25, __RecordingFailureCategory_enumToString(entity.failureCategory))
        val _tmpFailureReason: String? = entity.failureReason
        if (_tmpFailureReason == null) {
          statement.bindNull(26)
        } else {
          statement.bindText(26, _tmpFailureReason)
        }
        val _tmpTerminalAtMs: Long? = entity.terminalAtMs
        if (_tmpTerminalAtMs == null) {
          statement.bindNull(27)
        } else {
          statement.bindLong(27, _tmpTerminalAtMs)
        }
        val _tmpStartedAtMs: Long? = entity.startedAtMs
        if (_tmpStartedAtMs == null) {
          statement.bindNull(28)
        } else {
          statement.bindLong(28, _tmpStartedAtMs)
        }
        val _tmpEndedAtMs: Long? = entity.endedAtMs
        if (_tmpEndedAtMs == null) {
          statement.bindNull(29)
        } else {
          statement.bindLong(29, _tmpEndedAtMs)
        }
        val _tmp: Int = if (entity.scheduleEnabled) 1 else 0
        statement.bindLong(30, _tmp.toLong())
        statement.bindLong(31, entity.priority.toLong())
        val _tmpAlarmStartAtMs: Long? = entity.alarmStartAtMs
        if (_tmpAlarmStartAtMs == null) {
          statement.bindNull(32)
        } else {
          statement.bindLong(32, _tmpAlarmStartAtMs)
        }
        val _tmpAlarmStopAtMs: Long? = entity.alarmStopAtMs
        if (_tmpAlarmStopAtMs == null) {
          statement.bindNull(33)
        } else {
          statement.bindLong(33, _tmpAlarmStopAtMs)
        }
        statement.bindLong(34, entity.createdAt)
        statement.bindLong(35, entity.updatedAt)
      }
    }
    this.__updateAdapterOfRecordingRunEntity = object : EntityDeleteOrUpdateAdapter<RecordingRunEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `recording_runs` SET `id` = ?,`schedule_id` = ?,`provider_id` = ?,`channel_id` = ?,`channel_name` = ?,`stream_url` = ?,`program_title` = ?,`scheduled_start_ms` = ?,`scheduled_end_ms` = ?,`recurrence` = ?,`recurring_rule_id` = ?,`status` = ?,`source_type` = ?,`resolved_url` = ?,`headers_json` = ?,`user_agent` = ?,`expiration_time` = ?,`provider_label` = ?,`output_uri` = ?,`output_display_path` = ?,`bytes_written` = ?,`average_throughput_bps` = ?,`retry_count` = ?,`last_progress_at_ms` = ?,`failure_category` = ?,`failure_reason` = ?,`terminal_at_ms` = ?,`started_at_ms` = ?,`ended_at_ms` = ?,`schedule_enabled` = ?,`priority` = ?,`alarm_start_at_ms` = ?,`alarm_stop_at_ms` = ?,`created_at` = ?,`updated_at` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: RecordingRunEntity) {
        statement.bindText(1, entity.id)
        statement.bindLong(2, entity.scheduleId)
        statement.bindLong(3, entity.providerId)
        statement.bindLong(4, entity.channelId)
        statement.bindText(5, entity.channelName)
        statement.bindText(6, entity.streamUrl)
        val _tmpProgramTitle: String? = entity.programTitle
        if (_tmpProgramTitle == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpProgramTitle)
        }
        statement.bindLong(8, entity.scheduledStartMs)
        statement.bindLong(9, entity.scheduledEndMs)
        statement.bindText(10, __RecordingRecurrence_enumToString(entity.recurrence))
        val _tmpRecurringRuleId: String? = entity.recurringRuleId
        if (_tmpRecurringRuleId == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpRecurringRuleId)
        }
        statement.bindText(12, __RecordingStatus_enumToString(entity.status))
        statement.bindText(13, __RecordingSourceType_enumToString(entity.sourceType))
        val _tmpResolvedUrl: String? = entity.resolvedUrl
        if (_tmpResolvedUrl == null) {
          statement.bindNull(14)
        } else {
          statement.bindText(14, _tmpResolvedUrl)
        }
        statement.bindText(15, entity.headersJson)
        val _tmpUserAgent: String? = entity.userAgent
        if (_tmpUserAgent == null) {
          statement.bindNull(16)
        } else {
          statement.bindText(16, _tmpUserAgent)
        }
        val _tmpExpirationTime: Long? = entity.expirationTime
        if (_tmpExpirationTime == null) {
          statement.bindNull(17)
        } else {
          statement.bindLong(17, _tmpExpirationTime)
        }
        val _tmpProviderLabel: String? = entity.providerLabel
        if (_tmpProviderLabel == null) {
          statement.bindNull(18)
        } else {
          statement.bindText(18, _tmpProviderLabel)
        }
        val _tmpOutputUri: String? = entity.outputUri
        if (_tmpOutputUri == null) {
          statement.bindNull(19)
        } else {
          statement.bindText(19, _tmpOutputUri)
        }
        val _tmpOutputDisplayPath: String? = entity.outputDisplayPath
        if (_tmpOutputDisplayPath == null) {
          statement.bindNull(20)
        } else {
          statement.bindText(20, _tmpOutputDisplayPath)
        }
        statement.bindLong(21, entity.bytesWritten)
        statement.bindLong(22, entity.averageThroughputBytesPerSecond)
        statement.bindLong(23, entity.retryCount.toLong())
        val _tmpLastProgressAtMs: Long? = entity.lastProgressAtMs
        if (_tmpLastProgressAtMs == null) {
          statement.bindNull(24)
        } else {
          statement.bindLong(24, _tmpLastProgressAtMs)
        }
        statement.bindText(25, __RecordingFailureCategory_enumToString(entity.failureCategory))
        val _tmpFailureReason: String? = entity.failureReason
        if (_tmpFailureReason == null) {
          statement.bindNull(26)
        } else {
          statement.bindText(26, _tmpFailureReason)
        }
        val _tmpTerminalAtMs: Long? = entity.terminalAtMs
        if (_tmpTerminalAtMs == null) {
          statement.bindNull(27)
        } else {
          statement.bindLong(27, _tmpTerminalAtMs)
        }
        val _tmpStartedAtMs: Long? = entity.startedAtMs
        if (_tmpStartedAtMs == null) {
          statement.bindNull(28)
        } else {
          statement.bindLong(28, _tmpStartedAtMs)
        }
        val _tmpEndedAtMs: Long? = entity.endedAtMs
        if (_tmpEndedAtMs == null) {
          statement.bindNull(29)
        } else {
          statement.bindLong(29, _tmpEndedAtMs)
        }
        val _tmp: Int = if (entity.scheduleEnabled) 1 else 0
        statement.bindLong(30, _tmp.toLong())
        statement.bindLong(31, entity.priority.toLong())
        val _tmpAlarmStartAtMs: Long? = entity.alarmStartAtMs
        if (_tmpAlarmStartAtMs == null) {
          statement.bindNull(32)
        } else {
          statement.bindLong(32, _tmpAlarmStartAtMs)
        }
        val _tmpAlarmStopAtMs: Long? = entity.alarmStopAtMs
        if (_tmpAlarmStopAtMs == null) {
          statement.bindNull(33)
        } else {
          statement.bindLong(33, _tmpAlarmStopAtMs)
        }
        statement.bindLong(34, entity.createdAt)
        statement.bindLong(35, entity.updatedAt)
        statement.bindText(36, entity.id)
      }
    }
  }

  public override suspend fun insert(run: RecordingRunEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfRecordingRunEntity.insert(_connection, run)
  }

  public override suspend fun insertAll(runs: List<RecordingRunEntity>): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfRecordingRunEntity.insert(_connection, runs)
  }

  public override suspend fun update(run: RecordingRunEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfRecordingRunEntity.handle(_connection, run)
  }

  public override suspend fun getIdsByProvider(providerId: Long): List<String> {
    val _sql: String = "SELECT id FROM recording_runs WHERE provider_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        val _result: MutableList<String> = mutableListOf()
        while (_stmt.step()) {
          val _item: String
          _item = _stmt.getText(0)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeAll(): Flow<List<RecordingRunWithSchedule>> {
    val _sql: String = """
        |
        |        SELECT
        |            rr.id,
        |            rr.schedule_id,
        |            rr.provider_id,
        |            rr.channel_id,
        |            rr.channel_name,
        |            rr.stream_url,
        |            rr.program_title,
        |            rr.scheduled_start_ms,
        |            rr.scheduled_end_ms,
        |            rr.recurrence,
        |            rr.recurring_rule_id,
        |            rr.status,
        |            rr.source_type,
        |            rr.output_uri,
        |            rr.output_display_path,
        |            rr.bytes_written,
        |            rr.average_throughput_bps,
        |            rr.retry_count,
        |            rr.last_progress_at_ms,
        |            rr.failure_category,
        |            rr.failure_reason,
        |            rr.terminal_at_ms,
        |            rr.schedule_enabled,
        |            rr.priority
        |        FROM recording_runs rr
        |        ORDER BY rr.scheduled_start_ms DESC, rr.created_at DESC
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("recording_runs")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = 0
        val _columnIndexOfScheduleId: Int = 1
        val _columnIndexOfProviderId: Int = 2
        val _columnIndexOfChannelId: Int = 3
        val _columnIndexOfChannelName: Int = 4
        val _columnIndexOfStreamUrl: Int = 5
        val _columnIndexOfProgramTitle: Int = 6
        val _columnIndexOfScheduledStartMs: Int = 7
        val _columnIndexOfScheduledEndMs: Int = 8
        val _columnIndexOfRecurrence: Int = 9
        val _columnIndexOfRecurringRuleId: Int = 10
        val _columnIndexOfStatus: Int = 11
        val _columnIndexOfSourceType: Int = 12
        val _columnIndexOfOutputUri: Int = 13
        val _columnIndexOfOutputDisplayPath: Int = 14
        val _columnIndexOfBytesWritten: Int = 15
        val _columnIndexOfAverageThroughputBytesPerSecond: Int = 16
        val _columnIndexOfRetryCount: Int = 17
        val _columnIndexOfLastProgressAtMs: Int = 18
        val _columnIndexOfFailureCategory: Int = 19
        val _columnIndexOfFailureReason: Int = 20
        val _columnIndexOfTerminalAtMs: Int = 21
        val _columnIndexOfScheduleEnabled: Int = 22
        val _columnIndexOfPriority: Int = 23
        val _result: MutableList<RecordingRunWithSchedule> = mutableListOf()
        while (_stmt.step()) {
          val _item: RecordingRunWithSchedule
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpScheduleId: Long
          _tmpScheduleId = _stmt.getLong(_columnIndexOfScheduleId)
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
          val _tmpScheduledStartMs: Long
          _tmpScheduledStartMs = _stmt.getLong(_columnIndexOfScheduledStartMs)
          val _tmpScheduledEndMs: Long
          _tmpScheduledEndMs = _stmt.getLong(_columnIndexOfScheduledEndMs)
          val _tmpRecurrence: RecordingRecurrence
          _tmpRecurrence = __RecordingRecurrence_stringToEnum(_stmt.getText(_columnIndexOfRecurrence))
          val _tmpRecurringRuleId: String?
          if (_stmt.isNull(_columnIndexOfRecurringRuleId)) {
            _tmpRecurringRuleId = null
          } else {
            _tmpRecurringRuleId = _stmt.getText(_columnIndexOfRecurringRuleId)
          }
          val _tmpStatus: RecordingStatus
          _tmpStatus = __RecordingStatus_stringToEnum(_stmt.getText(_columnIndexOfStatus))
          val _tmpSourceType: RecordingSourceType
          _tmpSourceType = __RecordingSourceType_stringToEnum(_stmt.getText(_columnIndexOfSourceType))
          val _tmpOutputUri: String?
          if (_stmt.isNull(_columnIndexOfOutputUri)) {
            _tmpOutputUri = null
          } else {
            _tmpOutputUri = _stmt.getText(_columnIndexOfOutputUri)
          }
          val _tmpOutputDisplayPath: String?
          if (_stmt.isNull(_columnIndexOfOutputDisplayPath)) {
            _tmpOutputDisplayPath = null
          } else {
            _tmpOutputDisplayPath = _stmt.getText(_columnIndexOfOutputDisplayPath)
          }
          val _tmpBytesWritten: Long
          _tmpBytesWritten = _stmt.getLong(_columnIndexOfBytesWritten)
          val _tmpAverageThroughputBytesPerSecond: Long
          _tmpAverageThroughputBytesPerSecond = _stmt.getLong(_columnIndexOfAverageThroughputBytesPerSecond)
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          val _tmpLastProgressAtMs: Long?
          if (_stmt.isNull(_columnIndexOfLastProgressAtMs)) {
            _tmpLastProgressAtMs = null
          } else {
            _tmpLastProgressAtMs = _stmt.getLong(_columnIndexOfLastProgressAtMs)
          }
          val _tmpFailureCategory: RecordingFailureCategory
          _tmpFailureCategory = __RecordingFailureCategory_stringToEnum(_stmt.getText(_columnIndexOfFailureCategory))
          val _tmpFailureReason: String?
          if (_stmt.isNull(_columnIndexOfFailureReason)) {
            _tmpFailureReason = null
          } else {
            _tmpFailureReason = _stmt.getText(_columnIndexOfFailureReason)
          }
          val _tmpTerminalAtMs: Long?
          if (_stmt.isNull(_columnIndexOfTerminalAtMs)) {
            _tmpTerminalAtMs = null
          } else {
            _tmpTerminalAtMs = _stmt.getLong(_columnIndexOfTerminalAtMs)
          }
          val _tmpScheduleEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfScheduleEnabled).toInt()
          _tmpScheduleEnabled = _tmp != 0
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          _item = RecordingRunWithSchedule(_tmpId,_tmpScheduleId,_tmpProviderId,_tmpChannelId,_tmpChannelName,_tmpStreamUrl,_tmpProgramTitle,_tmpScheduledStartMs,_tmpScheduledEndMs,_tmpRecurrence,_tmpRecurringRuleId,_tmpStatus,_tmpSourceType,_tmpOutputUri,_tmpOutputDisplayPath,_tmpBytesWritten,_tmpAverageThroughputBytesPerSecond,_tmpRetryCount,_tmpLastProgressAtMs,_tmpFailureCategory,_tmpFailureReason,_tmpTerminalAtMs,_tmpScheduleEnabled,_tmpPriority)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: String): RecordingRunEntity? {
    val _sql: String = "SELECT * FROM recording_runs WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfScheduleId: Int = getColumnIndexOrThrow(_stmt, "schedule_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfChannelId: Int = getColumnIndexOrThrow(_stmt, "channel_id")
        val _columnIndexOfChannelName: Int = getColumnIndexOrThrow(_stmt, "channel_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfProgramTitle: Int = getColumnIndexOrThrow(_stmt, "program_title")
        val _columnIndexOfScheduledStartMs: Int = getColumnIndexOrThrow(_stmt, "scheduled_start_ms")
        val _columnIndexOfScheduledEndMs: Int = getColumnIndexOrThrow(_stmt, "scheduled_end_ms")
        val _columnIndexOfRecurrence: Int = getColumnIndexOrThrow(_stmt, "recurrence")
        val _columnIndexOfRecurringRuleId: Int = getColumnIndexOrThrow(_stmt, "recurring_rule_id")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSourceType: Int = getColumnIndexOrThrow(_stmt, "source_type")
        val _columnIndexOfResolvedUrl: Int = getColumnIndexOrThrow(_stmt, "resolved_url")
        val _columnIndexOfHeadersJson: Int = getColumnIndexOrThrow(_stmt, "headers_json")
        val _columnIndexOfUserAgent: Int = getColumnIndexOrThrow(_stmt, "user_agent")
        val _columnIndexOfExpirationTime: Int = getColumnIndexOrThrow(_stmt, "expiration_time")
        val _columnIndexOfProviderLabel: Int = getColumnIndexOrThrow(_stmt, "provider_label")
        val _columnIndexOfOutputUri: Int = getColumnIndexOrThrow(_stmt, "output_uri")
        val _columnIndexOfOutputDisplayPath: Int = getColumnIndexOrThrow(_stmt, "output_display_path")
        val _columnIndexOfBytesWritten: Int = getColumnIndexOrThrow(_stmt, "bytes_written")
        val _columnIndexOfAverageThroughputBytesPerSecond: Int = getColumnIndexOrThrow(_stmt, "average_throughput_bps")
        val _columnIndexOfRetryCount: Int = getColumnIndexOrThrow(_stmt, "retry_count")
        val _columnIndexOfLastProgressAtMs: Int = getColumnIndexOrThrow(_stmt, "last_progress_at_ms")
        val _columnIndexOfFailureCategory: Int = getColumnIndexOrThrow(_stmt, "failure_category")
        val _columnIndexOfFailureReason: Int = getColumnIndexOrThrow(_stmt, "failure_reason")
        val _columnIndexOfTerminalAtMs: Int = getColumnIndexOrThrow(_stmt, "terminal_at_ms")
        val _columnIndexOfStartedAtMs: Int = getColumnIndexOrThrow(_stmt, "started_at_ms")
        val _columnIndexOfEndedAtMs: Int = getColumnIndexOrThrow(_stmt, "ended_at_ms")
        val _columnIndexOfScheduleEnabled: Int = getColumnIndexOrThrow(_stmt, "schedule_enabled")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfAlarmStartAtMs: Int = getColumnIndexOrThrow(_stmt, "alarm_start_at_ms")
        val _columnIndexOfAlarmStopAtMs: Int = getColumnIndexOrThrow(_stmt, "alarm_stop_at_ms")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: RecordingRunEntity?
        if (_stmt.step()) {
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpScheduleId: Long
          _tmpScheduleId = _stmt.getLong(_columnIndexOfScheduleId)
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
          val _tmpScheduledStartMs: Long
          _tmpScheduledStartMs = _stmt.getLong(_columnIndexOfScheduledStartMs)
          val _tmpScheduledEndMs: Long
          _tmpScheduledEndMs = _stmt.getLong(_columnIndexOfScheduledEndMs)
          val _tmpRecurrence: RecordingRecurrence
          _tmpRecurrence = __RecordingRecurrence_stringToEnum(_stmt.getText(_columnIndexOfRecurrence))
          val _tmpRecurringRuleId: String?
          if (_stmt.isNull(_columnIndexOfRecurringRuleId)) {
            _tmpRecurringRuleId = null
          } else {
            _tmpRecurringRuleId = _stmt.getText(_columnIndexOfRecurringRuleId)
          }
          val _tmpStatus: RecordingStatus
          _tmpStatus = __RecordingStatus_stringToEnum(_stmt.getText(_columnIndexOfStatus))
          val _tmpSourceType: RecordingSourceType
          _tmpSourceType = __RecordingSourceType_stringToEnum(_stmt.getText(_columnIndexOfSourceType))
          val _tmpResolvedUrl: String?
          if (_stmt.isNull(_columnIndexOfResolvedUrl)) {
            _tmpResolvedUrl = null
          } else {
            _tmpResolvedUrl = _stmt.getText(_columnIndexOfResolvedUrl)
          }
          val _tmpHeadersJson: String
          _tmpHeadersJson = _stmt.getText(_columnIndexOfHeadersJson)
          val _tmpUserAgent: String?
          if (_stmt.isNull(_columnIndexOfUserAgent)) {
            _tmpUserAgent = null
          } else {
            _tmpUserAgent = _stmt.getText(_columnIndexOfUserAgent)
          }
          val _tmpExpirationTime: Long?
          if (_stmt.isNull(_columnIndexOfExpirationTime)) {
            _tmpExpirationTime = null
          } else {
            _tmpExpirationTime = _stmt.getLong(_columnIndexOfExpirationTime)
          }
          val _tmpProviderLabel: String?
          if (_stmt.isNull(_columnIndexOfProviderLabel)) {
            _tmpProviderLabel = null
          } else {
            _tmpProviderLabel = _stmt.getText(_columnIndexOfProviderLabel)
          }
          val _tmpOutputUri: String?
          if (_stmt.isNull(_columnIndexOfOutputUri)) {
            _tmpOutputUri = null
          } else {
            _tmpOutputUri = _stmt.getText(_columnIndexOfOutputUri)
          }
          val _tmpOutputDisplayPath: String?
          if (_stmt.isNull(_columnIndexOfOutputDisplayPath)) {
            _tmpOutputDisplayPath = null
          } else {
            _tmpOutputDisplayPath = _stmt.getText(_columnIndexOfOutputDisplayPath)
          }
          val _tmpBytesWritten: Long
          _tmpBytesWritten = _stmt.getLong(_columnIndexOfBytesWritten)
          val _tmpAverageThroughputBytesPerSecond: Long
          _tmpAverageThroughputBytesPerSecond = _stmt.getLong(_columnIndexOfAverageThroughputBytesPerSecond)
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          val _tmpLastProgressAtMs: Long?
          if (_stmt.isNull(_columnIndexOfLastProgressAtMs)) {
            _tmpLastProgressAtMs = null
          } else {
            _tmpLastProgressAtMs = _stmt.getLong(_columnIndexOfLastProgressAtMs)
          }
          val _tmpFailureCategory: RecordingFailureCategory
          _tmpFailureCategory = __RecordingFailureCategory_stringToEnum(_stmt.getText(_columnIndexOfFailureCategory))
          val _tmpFailureReason: String?
          if (_stmt.isNull(_columnIndexOfFailureReason)) {
            _tmpFailureReason = null
          } else {
            _tmpFailureReason = _stmt.getText(_columnIndexOfFailureReason)
          }
          val _tmpTerminalAtMs: Long?
          if (_stmt.isNull(_columnIndexOfTerminalAtMs)) {
            _tmpTerminalAtMs = null
          } else {
            _tmpTerminalAtMs = _stmt.getLong(_columnIndexOfTerminalAtMs)
          }
          val _tmpStartedAtMs: Long?
          if (_stmt.isNull(_columnIndexOfStartedAtMs)) {
            _tmpStartedAtMs = null
          } else {
            _tmpStartedAtMs = _stmt.getLong(_columnIndexOfStartedAtMs)
          }
          val _tmpEndedAtMs: Long?
          if (_stmt.isNull(_columnIndexOfEndedAtMs)) {
            _tmpEndedAtMs = null
          } else {
            _tmpEndedAtMs = _stmt.getLong(_columnIndexOfEndedAtMs)
          }
          val _tmpScheduleEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfScheduleEnabled).toInt()
          _tmpScheduleEnabled = _tmp != 0
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpAlarmStartAtMs: Long?
          if (_stmt.isNull(_columnIndexOfAlarmStartAtMs)) {
            _tmpAlarmStartAtMs = null
          } else {
            _tmpAlarmStartAtMs = _stmt.getLong(_columnIndexOfAlarmStartAtMs)
          }
          val _tmpAlarmStopAtMs: Long?
          if (_stmt.isNull(_columnIndexOfAlarmStopAtMs)) {
            _tmpAlarmStopAtMs = null
          } else {
            _tmpAlarmStopAtMs = _stmt.getLong(_columnIndexOfAlarmStopAtMs)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _result = RecordingRunEntity(_tmpId,_tmpScheduleId,_tmpProviderId,_tmpChannelId,_tmpChannelName,_tmpStreamUrl,_tmpProgramTitle,_tmpScheduledStartMs,_tmpScheduledEndMs,_tmpRecurrence,_tmpRecurringRuleId,_tmpStatus,_tmpSourceType,_tmpResolvedUrl,_tmpHeadersJson,_tmpUserAgent,_tmpExpirationTime,_tmpProviderLabel,_tmpOutputUri,_tmpOutputDisplayPath,_tmpBytesWritten,_tmpAverageThroughputBytesPerSecond,_tmpRetryCount,_tmpLastProgressAtMs,_tmpFailureCategory,_tmpFailureReason,_tmpTerminalAtMs,_tmpStartedAtMs,_tmpEndedAtMs,_tmpScheduleEnabled,_tmpPriority,_tmpAlarmStartAtMs,_tmpAlarmStopAtMs,_tmpCreatedAt,_tmpUpdatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByStatus(status: RecordingStatus): List<RecordingRunEntity> {
    val _sql: String = "SELECT * FROM recording_runs WHERE status = ? ORDER BY scheduled_start_ms ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, __RecordingStatus_enumToString(status))
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfScheduleId: Int = getColumnIndexOrThrow(_stmt, "schedule_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfChannelId: Int = getColumnIndexOrThrow(_stmt, "channel_id")
        val _columnIndexOfChannelName: Int = getColumnIndexOrThrow(_stmt, "channel_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfProgramTitle: Int = getColumnIndexOrThrow(_stmt, "program_title")
        val _columnIndexOfScheduledStartMs: Int = getColumnIndexOrThrow(_stmt, "scheduled_start_ms")
        val _columnIndexOfScheduledEndMs: Int = getColumnIndexOrThrow(_stmt, "scheduled_end_ms")
        val _columnIndexOfRecurrence: Int = getColumnIndexOrThrow(_stmt, "recurrence")
        val _columnIndexOfRecurringRuleId: Int = getColumnIndexOrThrow(_stmt, "recurring_rule_id")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSourceType: Int = getColumnIndexOrThrow(_stmt, "source_type")
        val _columnIndexOfResolvedUrl: Int = getColumnIndexOrThrow(_stmt, "resolved_url")
        val _columnIndexOfHeadersJson: Int = getColumnIndexOrThrow(_stmt, "headers_json")
        val _columnIndexOfUserAgent: Int = getColumnIndexOrThrow(_stmt, "user_agent")
        val _columnIndexOfExpirationTime: Int = getColumnIndexOrThrow(_stmt, "expiration_time")
        val _columnIndexOfProviderLabel: Int = getColumnIndexOrThrow(_stmt, "provider_label")
        val _columnIndexOfOutputUri: Int = getColumnIndexOrThrow(_stmt, "output_uri")
        val _columnIndexOfOutputDisplayPath: Int = getColumnIndexOrThrow(_stmt, "output_display_path")
        val _columnIndexOfBytesWritten: Int = getColumnIndexOrThrow(_stmt, "bytes_written")
        val _columnIndexOfAverageThroughputBytesPerSecond: Int = getColumnIndexOrThrow(_stmt, "average_throughput_bps")
        val _columnIndexOfRetryCount: Int = getColumnIndexOrThrow(_stmt, "retry_count")
        val _columnIndexOfLastProgressAtMs: Int = getColumnIndexOrThrow(_stmt, "last_progress_at_ms")
        val _columnIndexOfFailureCategory: Int = getColumnIndexOrThrow(_stmt, "failure_category")
        val _columnIndexOfFailureReason: Int = getColumnIndexOrThrow(_stmt, "failure_reason")
        val _columnIndexOfTerminalAtMs: Int = getColumnIndexOrThrow(_stmt, "terminal_at_ms")
        val _columnIndexOfStartedAtMs: Int = getColumnIndexOrThrow(_stmt, "started_at_ms")
        val _columnIndexOfEndedAtMs: Int = getColumnIndexOrThrow(_stmt, "ended_at_ms")
        val _columnIndexOfScheduleEnabled: Int = getColumnIndexOrThrow(_stmt, "schedule_enabled")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfAlarmStartAtMs: Int = getColumnIndexOrThrow(_stmt, "alarm_start_at_ms")
        val _columnIndexOfAlarmStopAtMs: Int = getColumnIndexOrThrow(_stmt, "alarm_stop_at_ms")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: MutableList<RecordingRunEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RecordingRunEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpScheduleId: Long
          _tmpScheduleId = _stmt.getLong(_columnIndexOfScheduleId)
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
          val _tmpScheduledStartMs: Long
          _tmpScheduledStartMs = _stmt.getLong(_columnIndexOfScheduledStartMs)
          val _tmpScheduledEndMs: Long
          _tmpScheduledEndMs = _stmt.getLong(_columnIndexOfScheduledEndMs)
          val _tmpRecurrence: RecordingRecurrence
          _tmpRecurrence = __RecordingRecurrence_stringToEnum(_stmt.getText(_columnIndexOfRecurrence))
          val _tmpRecurringRuleId: String?
          if (_stmt.isNull(_columnIndexOfRecurringRuleId)) {
            _tmpRecurringRuleId = null
          } else {
            _tmpRecurringRuleId = _stmt.getText(_columnIndexOfRecurringRuleId)
          }
          val _tmpStatus: RecordingStatus
          _tmpStatus = __RecordingStatus_stringToEnum(_stmt.getText(_columnIndexOfStatus))
          val _tmpSourceType: RecordingSourceType
          _tmpSourceType = __RecordingSourceType_stringToEnum(_stmt.getText(_columnIndexOfSourceType))
          val _tmpResolvedUrl: String?
          if (_stmt.isNull(_columnIndexOfResolvedUrl)) {
            _tmpResolvedUrl = null
          } else {
            _tmpResolvedUrl = _stmt.getText(_columnIndexOfResolvedUrl)
          }
          val _tmpHeadersJson: String
          _tmpHeadersJson = _stmt.getText(_columnIndexOfHeadersJson)
          val _tmpUserAgent: String?
          if (_stmt.isNull(_columnIndexOfUserAgent)) {
            _tmpUserAgent = null
          } else {
            _tmpUserAgent = _stmt.getText(_columnIndexOfUserAgent)
          }
          val _tmpExpirationTime: Long?
          if (_stmt.isNull(_columnIndexOfExpirationTime)) {
            _tmpExpirationTime = null
          } else {
            _tmpExpirationTime = _stmt.getLong(_columnIndexOfExpirationTime)
          }
          val _tmpProviderLabel: String?
          if (_stmt.isNull(_columnIndexOfProviderLabel)) {
            _tmpProviderLabel = null
          } else {
            _tmpProviderLabel = _stmt.getText(_columnIndexOfProviderLabel)
          }
          val _tmpOutputUri: String?
          if (_stmt.isNull(_columnIndexOfOutputUri)) {
            _tmpOutputUri = null
          } else {
            _tmpOutputUri = _stmt.getText(_columnIndexOfOutputUri)
          }
          val _tmpOutputDisplayPath: String?
          if (_stmt.isNull(_columnIndexOfOutputDisplayPath)) {
            _tmpOutputDisplayPath = null
          } else {
            _tmpOutputDisplayPath = _stmt.getText(_columnIndexOfOutputDisplayPath)
          }
          val _tmpBytesWritten: Long
          _tmpBytesWritten = _stmt.getLong(_columnIndexOfBytesWritten)
          val _tmpAverageThroughputBytesPerSecond: Long
          _tmpAverageThroughputBytesPerSecond = _stmt.getLong(_columnIndexOfAverageThroughputBytesPerSecond)
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          val _tmpLastProgressAtMs: Long?
          if (_stmt.isNull(_columnIndexOfLastProgressAtMs)) {
            _tmpLastProgressAtMs = null
          } else {
            _tmpLastProgressAtMs = _stmt.getLong(_columnIndexOfLastProgressAtMs)
          }
          val _tmpFailureCategory: RecordingFailureCategory
          _tmpFailureCategory = __RecordingFailureCategory_stringToEnum(_stmt.getText(_columnIndexOfFailureCategory))
          val _tmpFailureReason: String?
          if (_stmt.isNull(_columnIndexOfFailureReason)) {
            _tmpFailureReason = null
          } else {
            _tmpFailureReason = _stmt.getText(_columnIndexOfFailureReason)
          }
          val _tmpTerminalAtMs: Long?
          if (_stmt.isNull(_columnIndexOfTerminalAtMs)) {
            _tmpTerminalAtMs = null
          } else {
            _tmpTerminalAtMs = _stmt.getLong(_columnIndexOfTerminalAtMs)
          }
          val _tmpStartedAtMs: Long?
          if (_stmt.isNull(_columnIndexOfStartedAtMs)) {
            _tmpStartedAtMs = null
          } else {
            _tmpStartedAtMs = _stmt.getLong(_columnIndexOfStartedAtMs)
          }
          val _tmpEndedAtMs: Long?
          if (_stmt.isNull(_columnIndexOfEndedAtMs)) {
            _tmpEndedAtMs = null
          } else {
            _tmpEndedAtMs = _stmt.getLong(_columnIndexOfEndedAtMs)
          }
          val _tmpScheduleEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfScheduleEnabled).toInt()
          _tmpScheduleEnabled = _tmp != 0
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpAlarmStartAtMs: Long?
          if (_stmt.isNull(_columnIndexOfAlarmStartAtMs)) {
            _tmpAlarmStartAtMs = null
          } else {
            _tmpAlarmStartAtMs = _stmt.getLong(_columnIndexOfAlarmStartAtMs)
          }
          val _tmpAlarmStopAtMs: Long?
          if (_stmt.isNull(_columnIndexOfAlarmStopAtMs)) {
            _tmpAlarmStopAtMs = null
          } else {
            _tmpAlarmStopAtMs = _stmt.getLong(_columnIndexOfAlarmStopAtMs)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item = RecordingRunEntity(_tmpId,_tmpScheduleId,_tmpProviderId,_tmpChannelId,_tmpChannelName,_tmpStreamUrl,_tmpProgramTitle,_tmpScheduledStartMs,_tmpScheduledEndMs,_tmpRecurrence,_tmpRecurringRuleId,_tmpStatus,_tmpSourceType,_tmpResolvedUrl,_tmpHeadersJson,_tmpUserAgent,_tmpExpirationTime,_tmpProviderLabel,_tmpOutputUri,_tmpOutputDisplayPath,_tmpBytesWritten,_tmpAverageThroughputBytesPerSecond,_tmpRetryCount,_tmpLastProgressAtMs,_tmpFailureCategory,_tmpFailureReason,_tmpTerminalAtMs,_tmpStartedAtMs,_tmpEndedAtMs,_tmpScheduleEnabled,_tmpPriority,_tmpAlarmStartAtMs,_tmpAlarmStopAtMs,_tmpCreatedAt,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getOverlapping(windowStartMs: Long, windowEndMs: Long): List<RecordingRunEntity> {
    val _sql: String = """
        |
        |        SELECT * FROM recording_runs
        |        WHERE (status = 'SCHEDULED' OR status = 'RECORDING')
        |          AND scheduled_start_ms < ?
        |          AND scheduled_end_ms > ?
        |        ORDER BY scheduled_start_ms ASC
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, windowEndMs)
        _argIndex = 2
        _stmt.bindLong(_argIndex, windowStartMs)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfScheduleId: Int = getColumnIndexOrThrow(_stmt, "schedule_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfChannelId: Int = getColumnIndexOrThrow(_stmt, "channel_id")
        val _columnIndexOfChannelName: Int = getColumnIndexOrThrow(_stmt, "channel_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfProgramTitle: Int = getColumnIndexOrThrow(_stmt, "program_title")
        val _columnIndexOfScheduledStartMs: Int = getColumnIndexOrThrow(_stmt, "scheduled_start_ms")
        val _columnIndexOfScheduledEndMs: Int = getColumnIndexOrThrow(_stmt, "scheduled_end_ms")
        val _columnIndexOfRecurrence: Int = getColumnIndexOrThrow(_stmt, "recurrence")
        val _columnIndexOfRecurringRuleId: Int = getColumnIndexOrThrow(_stmt, "recurring_rule_id")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSourceType: Int = getColumnIndexOrThrow(_stmt, "source_type")
        val _columnIndexOfResolvedUrl: Int = getColumnIndexOrThrow(_stmt, "resolved_url")
        val _columnIndexOfHeadersJson: Int = getColumnIndexOrThrow(_stmt, "headers_json")
        val _columnIndexOfUserAgent: Int = getColumnIndexOrThrow(_stmt, "user_agent")
        val _columnIndexOfExpirationTime: Int = getColumnIndexOrThrow(_stmt, "expiration_time")
        val _columnIndexOfProviderLabel: Int = getColumnIndexOrThrow(_stmt, "provider_label")
        val _columnIndexOfOutputUri: Int = getColumnIndexOrThrow(_stmt, "output_uri")
        val _columnIndexOfOutputDisplayPath: Int = getColumnIndexOrThrow(_stmt, "output_display_path")
        val _columnIndexOfBytesWritten: Int = getColumnIndexOrThrow(_stmt, "bytes_written")
        val _columnIndexOfAverageThroughputBytesPerSecond: Int = getColumnIndexOrThrow(_stmt, "average_throughput_bps")
        val _columnIndexOfRetryCount: Int = getColumnIndexOrThrow(_stmt, "retry_count")
        val _columnIndexOfLastProgressAtMs: Int = getColumnIndexOrThrow(_stmt, "last_progress_at_ms")
        val _columnIndexOfFailureCategory: Int = getColumnIndexOrThrow(_stmt, "failure_category")
        val _columnIndexOfFailureReason: Int = getColumnIndexOrThrow(_stmt, "failure_reason")
        val _columnIndexOfTerminalAtMs: Int = getColumnIndexOrThrow(_stmt, "terminal_at_ms")
        val _columnIndexOfStartedAtMs: Int = getColumnIndexOrThrow(_stmt, "started_at_ms")
        val _columnIndexOfEndedAtMs: Int = getColumnIndexOrThrow(_stmt, "ended_at_ms")
        val _columnIndexOfScheduleEnabled: Int = getColumnIndexOrThrow(_stmt, "schedule_enabled")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfAlarmStartAtMs: Int = getColumnIndexOrThrow(_stmt, "alarm_start_at_ms")
        val _columnIndexOfAlarmStopAtMs: Int = getColumnIndexOrThrow(_stmt, "alarm_stop_at_ms")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: MutableList<RecordingRunEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RecordingRunEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpScheduleId: Long
          _tmpScheduleId = _stmt.getLong(_columnIndexOfScheduleId)
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
          val _tmpScheduledStartMs: Long
          _tmpScheduledStartMs = _stmt.getLong(_columnIndexOfScheduledStartMs)
          val _tmpScheduledEndMs: Long
          _tmpScheduledEndMs = _stmt.getLong(_columnIndexOfScheduledEndMs)
          val _tmpRecurrence: RecordingRecurrence
          _tmpRecurrence = __RecordingRecurrence_stringToEnum(_stmt.getText(_columnIndexOfRecurrence))
          val _tmpRecurringRuleId: String?
          if (_stmt.isNull(_columnIndexOfRecurringRuleId)) {
            _tmpRecurringRuleId = null
          } else {
            _tmpRecurringRuleId = _stmt.getText(_columnIndexOfRecurringRuleId)
          }
          val _tmpStatus: RecordingStatus
          _tmpStatus = __RecordingStatus_stringToEnum(_stmt.getText(_columnIndexOfStatus))
          val _tmpSourceType: RecordingSourceType
          _tmpSourceType = __RecordingSourceType_stringToEnum(_stmt.getText(_columnIndexOfSourceType))
          val _tmpResolvedUrl: String?
          if (_stmt.isNull(_columnIndexOfResolvedUrl)) {
            _tmpResolvedUrl = null
          } else {
            _tmpResolvedUrl = _stmt.getText(_columnIndexOfResolvedUrl)
          }
          val _tmpHeadersJson: String
          _tmpHeadersJson = _stmt.getText(_columnIndexOfHeadersJson)
          val _tmpUserAgent: String?
          if (_stmt.isNull(_columnIndexOfUserAgent)) {
            _tmpUserAgent = null
          } else {
            _tmpUserAgent = _stmt.getText(_columnIndexOfUserAgent)
          }
          val _tmpExpirationTime: Long?
          if (_stmt.isNull(_columnIndexOfExpirationTime)) {
            _tmpExpirationTime = null
          } else {
            _tmpExpirationTime = _stmt.getLong(_columnIndexOfExpirationTime)
          }
          val _tmpProviderLabel: String?
          if (_stmt.isNull(_columnIndexOfProviderLabel)) {
            _tmpProviderLabel = null
          } else {
            _tmpProviderLabel = _stmt.getText(_columnIndexOfProviderLabel)
          }
          val _tmpOutputUri: String?
          if (_stmt.isNull(_columnIndexOfOutputUri)) {
            _tmpOutputUri = null
          } else {
            _tmpOutputUri = _stmt.getText(_columnIndexOfOutputUri)
          }
          val _tmpOutputDisplayPath: String?
          if (_stmt.isNull(_columnIndexOfOutputDisplayPath)) {
            _tmpOutputDisplayPath = null
          } else {
            _tmpOutputDisplayPath = _stmt.getText(_columnIndexOfOutputDisplayPath)
          }
          val _tmpBytesWritten: Long
          _tmpBytesWritten = _stmt.getLong(_columnIndexOfBytesWritten)
          val _tmpAverageThroughputBytesPerSecond: Long
          _tmpAverageThroughputBytesPerSecond = _stmt.getLong(_columnIndexOfAverageThroughputBytesPerSecond)
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          val _tmpLastProgressAtMs: Long?
          if (_stmt.isNull(_columnIndexOfLastProgressAtMs)) {
            _tmpLastProgressAtMs = null
          } else {
            _tmpLastProgressAtMs = _stmt.getLong(_columnIndexOfLastProgressAtMs)
          }
          val _tmpFailureCategory: RecordingFailureCategory
          _tmpFailureCategory = __RecordingFailureCategory_stringToEnum(_stmt.getText(_columnIndexOfFailureCategory))
          val _tmpFailureReason: String?
          if (_stmt.isNull(_columnIndexOfFailureReason)) {
            _tmpFailureReason = null
          } else {
            _tmpFailureReason = _stmt.getText(_columnIndexOfFailureReason)
          }
          val _tmpTerminalAtMs: Long?
          if (_stmt.isNull(_columnIndexOfTerminalAtMs)) {
            _tmpTerminalAtMs = null
          } else {
            _tmpTerminalAtMs = _stmt.getLong(_columnIndexOfTerminalAtMs)
          }
          val _tmpStartedAtMs: Long?
          if (_stmt.isNull(_columnIndexOfStartedAtMs)) {
            _tmpStartedAtMs = null
          } else {
            _tmpStartedAtMs = _stmt.getLong(_columnIndexOfStartedAtMs)
          }
          val _tmpEndedAtMs: Long?
          if (_stmt.isNull(_columnIndexOfEndedAtMs)) {
            _tmpEndedAtMs = null
          } else {
            _tmpEndedAtMs = _stmt.getLong(_columnIndexOfEndedAtMs)
          }
          val _tmpScheduleEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfScheduleEnabled).toInt()
          _tmpScheduleEnabled = _tmp != 0
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpAlarmStartAtMs: Long?
          if (_stmt.isNull(_columnIndexOfAlarmStartAtMs)) {
            _tmpAlarmStartAtMs = null
          } else {
            _tmpAlarmStartAtMs = _stmt.getLong(_columnIndexOfAlarmStartAtMs)
          }
          val _tmpAlarmStopAtMs: Long?
          if (_stmt.isNull(_columnIndexOfAlarmStopAtMs)) {
            _tmpAlarmStopAtMs = null
          } else {
            _tmpAlarmStopAtMs = _stmt.getLong(_columnIndexOfAlarmStopAtMs)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item = RecordingRunEntity(_tmpId,_tmpScheduleId,_tmpProviderId,_tmpChannelId,_tmpChannelName,_tmpStreamUrl,_tmpProgramTitle,_tmpScheduledStartMs,_tmpScheduledEndMs,_tmpRecurrence,_tmpRecurringRuleId,_tmpStatus,_tmpSourceType,_tmpResolvedUrl,_tmpHeadersJson,_tmpUserAgent,_tmpExpirationTime,_tmpProviderLabel,_tmpOutputUri,_tmpOutputDisplayPath,_tmpBytesWritten,_tmpAverageThroughputBytesPerSecond,_tmpRetryCount,_tmpLastProgressAtMs,_tmpFailureCategory,_tmpFailureReason,_tmpTerminalAtMs,_tmpStartedAtMs,_tmpEndedAtMs,_tmpScheduleEnabled,_tmpPriority,_tmpAlarmStartAtMs,_tmpAlarmStopAtMs,_tmpCreatedAt,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAlarmManagedScheduledRuns(): List<RecordingRunEntity> {
    val _sql: String = "SELECT * FROM recording_runs WHERE alarm_start_at_ms IS NOT NULL AND status = 'SCHEDULED'"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfScheduleId: Int = getColumnIndexOrThrow(_stmt, "schedule_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfChannelId: Int = getColumnIndexOrThrow(_stmt, "channel_id")
        val _columnIndexOfChannelName: Int = getColumnIndexOrThrow(_stmt, "channel_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfProgramTitle: Int = getColumnIndexOrThrow(_stmt, "program_title")
        val _columnIndexOfScheduledStartMs: Int = getColumnIndexOrThrow(_stmt, "scheduled_start_ms")
        val _columnIndexOfScheduledEndMs: Int = getColumnIndexOrThrow(_stmt, "scheduled_end_ms")
        val _columnIndexOfRecurrence: Int = getColumnIndexOrThrow(_stmt, "recurrence")
        val _columnIndexOfRecurringRuleId: Int = getColumnIndexOrThrow(_stmt, "recurring_rule_id")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSourceType: Int = getColumnIndexOrThrow(_stmt, "source_type")
        val _columnIndexOfResolvedUrl: Int = getColumnIndexOrThrow(_stmt, "resolved_url")
        val _columnIndexOfHeadersJson: Int = getColumnIndexOrThrow(_stmt, "headers_json")
        val _columnIndexOfUserAgent: Int = getColumnIndexOrThrow(_stmt, "user_agent")
        val _columnIndexOfExpirationTime: Int = getColumnIndexOrThrow(_stmt, "expiration_time")
        val _columnIndexOfProviderLabel: Int = getColumnIndexOrThrow(_stmt, "provider_label")
        val _columnIndexOfOutputUri: Int = getColumnIndexOrThrow(_stmt, "output_uri")
        val _columnIndexOfOutputDisplayPath: Int = getColumnIndexOrThrow(_stmt, "output_display_path")
        val _columnIndexOfBytesWritten: Int = getColumnIndexOrThrow(_stmt, "bytes_written")
        val _columnIndexOfAverageThroughputBytesPerSecond: Int = getColumnIndexOrThrow(_stmt, "average_throughput_bps")
        val _columnIndexOfRetryCount: Int = getColumnIndexOrThrow(_stmt, "retry_count")
        val _columnIndexOfLastProgressAtMs: Int = getColumnIndexOrThrow(_stmt, "last_progress_at_ms")
        val _columnIndexOfFailureCategory: Int = getColumnIndexOrThrow(_stmt, "failure_category")
        val _columnIndexOfFailureReason: Int = getColumnIndexOrThrow(_stmt, "failure_reason")
        val _columnIndexOfTerminalAtMs: Int = getColumnIndexOrThrow(_stmt, "terminal_at_ms")
        val _columnIndexOfStartedAtMs: Int = getColumnIndexOrThrow(_stmt, "started_at_ms")
        val _columnIndexOfEndedAtMs: Int = getColumnIndexOrThrow(_stmt, "ended_at_ms")
        val _columnIndexOfScheduleEnabled: Int = getColumnIndexOrThrow(_stmt, "schedule_enabled")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfAlarmStartAtMs: Int = getColumnIndexOrThrow(_stmt, "alarm_start_at_ms")
        val _columnIndexOfAlarmStopAtMs: Int = getColumnIndexOrThrow(_stmt, "alarm_stop_at_ms")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: MutableList<RecordingRunEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RecordingRunEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpScheduleId: Long
          _tmpScheduleId = _stmt.getLong(_columnIndexOfScheduleId)
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
          val _tmpScheduledStartMs: Long
          _tmpScheduledStartMs = _stmt.getLong(_columnIndexOfScheduledStartMs)
          val _tmpScheduledEndMs: Long
          _tmpScheduledEndMs = _stmt.getLong(_columnIndexOfScheduledEndMs)
          val _tmpRecurrence: RecordingRecurrence
          _tmpRecurrence = __RecordingRecurrence_stringToEnum(_stmt.getText(_columnIndexOfRecurrence))
          val _tmpRecurringRuleId: String?
          if (_stmt.isNull(_columnIndexOfRecurringRuleId)) {
            _tmpRecurringRuleId = null
          } else {
            _tmpRecurringRuleId = _stmt.getText(_columnIndexOfRecurringRuleId)
          }
          val _tmpStatus: RecordingStatus
          _tmpStatus = __RecordingStatus_stringToEnum(_stmt.getText(_columnIndexOfStatus))
          val _tmpSourceType: RecordingSourceType
          _tmpSourceType = __RecordingSourceType_stringToEnum(_stmt.getText(_columnIndexOfSourceType))
          val _tmpResolvedUrl: String?
          if (_stmt.isNull(_columnIndexOfResolvedUrl)) {
            _tmpResolvedUrl = null
          } else {
            _tmpResolvedUrl = _stmt.getText(_columnIndexOfResolvedUrl)
          }
          val _tmpHeadersJson: String
          _tmpHeadersJson = _stmt.getText(_columnIndexOfHeadersJson)
          val _tmpUserAgent: String?
          if (_stmt.isNull(_columnIndexOfUserAgent)) {
            _tmpUserAgent = null
          } else {
            _tmpUserAgent = _stmt.getText(_columnIndexOfUserAgent)
          }
          val _tmpExpirationTime: Long?
          if (_stmt.isNull(_columnIndexOfExpirationTime)) {
            _tmpExpirationTime = null
          } else {
            _tmpExpirationTime = _stmt.getLong(_columnIndexOfExpirationTime)
          }
          val _tmpProviderLabel: String?
          if (_stmt.isNull(_columnIndexOfProviderLabel)) {
            _tmpProviderLabel = null
          } else {
            _tmpProviderLabel = _stmt.getText(_columnIndexOfProviderLabel)
          }
          val _tmpOutputUri: String?
          if (_stmt.isNull(_columnIndexOfOutputUri)) {
            _tmpOutputUri = null
          } else {
            _tmpOutputUri = _stmt.getText(_columnIndexOfOutputUri)
          }
          val _tmpOutputDisplayPath: String?
          if (_stmt.isNull(_columnIndexOfOutputDisplayPath)) {
            _tmpOutputDisplayPath = null
          } else {
            _tmpOutputDisplayPath = _stmt.getText(_columnIndexOfOutputDisplayPath)
          }
          val _tmpBytesWritten: Long
          _tmpBytesWritten = _stmt.getLong(_columnIndexOfBytesWritten)
          val _tmpAverageThroughputBytesPerSecond: Long
          _tmpAverageThroughputBytesPerSecond = _stmt.getLong(_columnIndexOfAverageThroughputBytesPerSecond)
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          val _tmpLastProgressAtMs: Long?
          if (_stmt.isNull(_columnIndexOfLastProgressAtMs)) {
            _tmpLastProgressAtMs = null
          } else {
            _tmpLastProgressAtMs = _stmt.getLong(_columnIndexOfLastProgressAtMs)
          }
          val _tmpFailureCategory: RecordingFailureCategory
          _tmpFailureCategory = __RecordingFailureCategory_stringToEnum(_stmt.getText(_columnIndexOfFailureCategory))
          val _tmpFailureReason: String?
          if (_stmt.isNull(_columnIndexOfFailureReason)) {
            _tmpFailureReason = null
          } else {
            _tmpFailureReason = _stmt.getText(_columnIndexOfFailureReason)
          }
          val _tmpTerminalAtMs: Long?
          if (_stmt.isNull(_columnIndexOfTerminalAtMs)) {
            _tmpTerminalAtMs = null
          } else {
            _tmpTerminalAtMs = _stmt.getLong(_columnIndexOfTerminalAtMs)
          }
          val _tmpStartedAtMs: Long?
          if (_stmt.isNull(_columnIndexOfStartedAtMs)) {
            _tmpStartedAtMs = null
          } else {
            _tmpStartedAtMs = _stmt.getLong(_columnIndexOfStartedAtMs)
          }
          val _tmpEndedAtMs: Long?
          if (_stmt.isNull(_columnIndexOfEndedAtMs)) {
            _tmpEndedAtMs = null
          } else {
            _tmpEndedAtMs = _stmt.getLong(_columnIndexOfEndedAtMs)
          }
          val _tmpScheduleEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfScheduleEnabled).toInt()
          _tmpScheduleEnabled = _tmp != 0
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpAlarmStartAtMs: Long?
          if (_stmt.isNull(_columnIndexOfAlarmStartAtMs)) {
            _tmpAlarmStartAtMs = null
          } else {
            _tmpAlarmStartAtMs = _stmt.getLong(_columnIndexOfAlarmStartAtMs)
          }
          val _tmpAlarmStopAtMs: Long?
          if (_stmt.isNull(_columnIndexOfAlarmStopAtMs)) {
            _tmpAlarmStopAtMs = null
          } else {
            _tmpAlarmStopAtMs = _stmt.getLong(_columnIndexOfAlarmStopAtMs)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item = RecordingRunEntity(_tmpId,_tmpScheduleId,_tmpProviderId,_tmpChannelId,_tmpChannelName,_tmpStreamUrl,_tmpProgramTitle,_tmpScheduledStartMs,_tmpScheduledEndMs,_tmpRecurrence,_tmpRecurringRuleId,_tmpStatus,_tmpSourceType,_tmpResolvedUrl,_tmpHeadersJson,_tmpUserAgent,_tmpExpirationTime,_tmpProviderLabel,_tmpOutputUri,_tmpOutputDisplayPath,_tmpBytesWritten,_tmpAverageThroughputBytesPerSecond,_tmpRetryCount,_tmpLastProgressAtMs,_tmpFailureCategory,_tmpFailureReason,_tmpTerminalAtMs,_tmpStartedAtMs,_tmpEndedAtMs,_tmpScheduleEnabled,_tmpPriority,_tmpAlarmStartAtMs,_tmpAlarmStopAtMs,_tmpCreatedAt,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getRecordingRuns(): List<RecordingRunEntity> {
    val _sql: String = "SELECT * FROM recording_runs WHERE status = 'RECORDING'"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfScheduleId: Int = getColumnIndexOrThrow(_stmt, "schedule_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfChannelId: Int = getColumnIndexOrThrow(_stmt, "channel_id")
        val _columnIndexOfChannelName: Int = getColumnIndexOrThrow(_stmt, "channel_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfProgramTitle: Int = getColumnIndexOrThrow(_stmt, "program_title")
        val _columnIndexOfScheduledStartMs: Int = getColumnIndexOrThrow(_stmt, "scheduled_start_ms")
        val _columnIndexOfScheduledEndMs: Int = getColumnIndexOrThrow(_stmt, "scheduled_end_ms")
        val _columnIndexOfRecurrence: Int = getColumnIndexOrThrow(_stmt, "recurrence")
        val _columnIndexOfRecurringRuleId: Int = getColumnIndexOrThrow(_stmt, "recurring_rule_id")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSourceType: Int = getColumnIndexOrThrow(_stmt, "source_type")
        val _columnIndexOfResolvedUrl: Int = getColumnIndexOrThrow(_stmt, "resolved_url")
        val _columnIndexOfHeadersJson: Int = getColumnIndexOrThrow(_stmt, "headers_json")
        val _columnIndexOfUserAgent: Int = getColumnIndexOrThrow(_stmt, "user_agent")
        val _columnIndexOfExpirationTime: Int = getColumnIndexOrThrow(_stmt, "expiration_time")
        val _columnIndexOfProviderLabel: Int = getColumnIndexOrThrow(_stmt, "provider_label")
        val _columnIndexOfOutputUri: Int = getColumnIndexOrThrow(_stmt, "output_uri")
        val _columnIndexOfOutputDisplayPath: Int = getColumnIndexOrThrow(_stmt, "output_display_path")
        val _columnIndexOfBytesWritten: Int = getColumnIndexOrThrow(_stmt, "bytes_written")
        val _columnIndexOfAverageThroughputBytesPerSecond: Int = getColumnIndexOrThrow(_stmt, "average_throughput_bps")
        val _columnIndexOfRetryCount: Int = getColumnIndexOrThrow(_stmt, "retry_count")
        val _columnIndexOfLastProgressAtMs: Int = getColumnIndexOrThrow(_stmt, "last_progress_at_ms")
        val _columnIndexOfFailureCategory: Int = getColumnIndexOrThrow(_stmt, "failure_category")
        val _columnIndexOfFailureReason: Int = getColumnIndexOrThrow(_stmt, "failure_reason")
        val _columnIndexOfTerminalAtMs: Int = getColumnIndexOrThrow(_stmt, "terminal_at_ms")
        val _columnIndexOfStartedAtMs: Int = getColumnIndexOrThrow(_stmt, "started_at_ms")
        val _columnIndexOfEndedAtMs: Int = getColumnIndexOrThrow(_stmt, "ended_at_ms")
        val _columnIndexOfScheduleEnabled: Int = getColumnIndexOrThrow(_stmt, "schedule_enabled")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfAlarmStartAtMs: Int = getColumnIndexOrThrow(_stmt, "alarm_start_at_ms")
        val _columnIndexOfAlarmStopAtMs: Int = getColumnIndexOrThrow(_stmt, "alarm_stop_at_ms")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: MutableList<RecordingRunEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RecordingRunEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpScheduleId: Long
          _tmpScheduleId = _stmt.getLong(_columnIndexOfScheduleId)
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
          val _tmpScheduledStartMs: Long
          _tmpScheduledStartMs = _stmt.getLong(_columnIndexOfScheduledStartMs)
          val _tmpScheduledEndMs: Long
          _tmpScheduledEndMs = _stmt.getLong(_columnIndexOfScheduledEndMs)
          val _tmpRecurrence: RecordingRecurrence
          _tmpRecurrence = __RecordingRecurrence_stringToEnum(_stmt.getText(_columnIndexOfRecurrence))
          val _tmpRecurringRuleId: String?
          if (_stmt.isNull(_columnIndexOfRecurringRuleId)) {
            _tmpRecurringRuleId = null
          } else {
            _tmpRecurringRuleId = _stmt.getText(_columnIndexOfRecurringRuleId)
          }
          val _tmpStatus: RecordingStatus
          _tmpStatus = __RecordingStatus_stringToEnum(_stmt.getText(_columnIndexOfStatus))
          val _tmpSourceType: RecordingSourceType
          _tmpSourceType = __RecordingSourceType_stringToEnum(_stmt.getText(_columnIndexOfSourceType))
          val _tmpResolvedUrl: String?
          if (_stmt.isNull(_columnIndexOfResolvedUrl)) {
            _tmpResolvedUrl = null
          } else {
            _tmpResolvedUrl = _stmt.getText(_columnIndexOfResolvedUrl)
          }
          val _tmpHeadersJson: String
          _tmpHeadersJson = _stmt.getText(_columnIndexOfHeadersJson)
          val _tmpUserAgent: String?
          if (_stmt.isNull(_columnIndexOfUserAgent)) {
            _tmpUserAgent = null
          } else {
            _tmpUserAgent = _stmt.getText(_columnIndexOfUserAgent)
          }
          val _tmpExpirationTime: Long?
          if (_stmt.isNull(_columnIndexOfExpirationTime)) {
            _tmpExpirationTime = null
          } else {
            _tmpExpirationTime = _stmt.getLong(_columnIndexOfExpirationTime)
          }
          val _tmpProviderLabel: String?
          if (_stmt.isNull(_columnIndexOfProviderLabel)) {
            _tmpProviderLabel = null
          } else {
            _tmpProviderLabel = _stmt.getText(_columnIndexOfProviderLabel)
          }
          val _tmpOutputUri: String?
          if (_stmt.isNull(_columnIndexOfOutputUri)) {
            _tmpOutputUri = null
          } else {
            _tmpOutputUri = _stmt.getText(_columnIndexOfOutputUri)
          }
          val _tmpOutputDisplayPath: String?
          if (_stmt.isNull(_columnIndexOfOutputDisplayPath)) {
            _tmpOutputDisplayPath = null
          } else {
            _tmpOutputDisplayPath = _stmt.getText(_columnIndexOfOutputDisplayPath)
          }
          val _tmpBytesWritten: Long
          _tmpBytesWritten = _stmt.getLong(_columnIndexOfBytesWritten)
          val _tmpAverageThroughputBytesPerSecond: Long
          _tmpAverageThroughputBytesPerSecond = _stmt.getLong(_columnIndexOfAverageThroughputBytesPerSecond)
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          val _tmpLastProgressAtMs: Long?
          if (_stmt.isNull(_columnIndexOfLastProgressAtMs)) {
            _tmpLastProgressAtMs = null
          } else {
            _tmpLastProgressAtMs = _stmt.getLong(_columnIndexOfLastProgressAtMs)
          }
          val _tmpFailureCategory: RecordingFailureCategory
          _tmpFailureCategory = __RecordingFailureCategory_stringToEnum(_stmt.getText(_columnIndexOfFailureCategory))
          val _tmpFailureReason: String?
          if (_stmt.isNull(_columnIndexOfFailureReason)) {
            _tmpFailureReason = null
          } else {
            _tmpFailureReason = _stmt.getText(_columnIndexOfFailureReason)
          }
          val _tmpTerminalAtMs: Long?
          if (_stmt.isNull(_columnIndexOfTerminalAtMs)) {
            _tmpTerminalAtMs = null
          } else {
            _tmpTerminalAtMs = _stmt.getLong(_columnIndexOfTerminalAtMs)
          }
          val _tmpStartedAtMs: Long?
          if (_stmt.isNull(_columnIndexOfStartedAtMs)) {
            _tmpStartedAtMs = null
          } else {
            _tmpStartedAtMs = _stmt.getLong(_columnIndexOfStartedAtMs)
          }
          val _tmpEndedAtMs: Long?
          if (_stmt.isNull(_columnIndexOfEndedAtMs)) {
            _tmpEndedAtMs = null
          } else {
            _tmpEndedAtMs = _stmt.getLong(_columnIndexOfEndedAtMs)
          }
          val _tmpScheduleEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfScheduleEnabled).toInt()
          _tmpScheduleEnabled = _tmp != 0
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpAlarmStartAtMs: Long?
          if (_stmt.isNull(_columnIndexOfAlarmStartAtMs)) {
            _tmpAlarmStartAtMs = null
          } else {
            _tmpAlarmStartAtMs = _stmt.getLong(_columnIndexOfAlarmStartAtMs)
          }
          val _tmpAlarmStopAtMs: Long?
          if (_stmt.isNull(_columnIndexOfAlarmStopAtMs)) {
            _tmpAlarmStopAtMs = null
          } else {
            _tmpAlarmStopAtMs = _stmt.getLong(_columnIndexOfAlarmStopAtMs)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item = RecordingRunEntity(_tmpId,_tmpScheduleId,_tmpProviderId,_tmpChannelId,_tmpChannelName,_tmpStreamUrl,_tmpProgramTitle,_tmpScheduledStartMs,_tmpScheduledEndMs,_tmpRecurrence,_tmpRecurringRuleId,_tmpStatus,_tmpSourceType,_tmpResolvedUrl,_tmpHeadersJson,_tmpUserAgent,_tmpExpirationTime,_tmpProviderLabel,_tmpOutputUri,_tmpOutputDisplayPath,_tmpBytesWritten,_tmpAverageThroughputBytesPerSecond,_tmpRetryCount,_tmpLastProgressAtMs,_tmpFailureCategory,_tmpFailureReason,_tmpTerminalAtMs,_tmpStartedAtMs,_tmpEndedAtMs,_tmpScheduleEnabled,_tmpPriority,_tmpAlarmStartAtMs,_tmpAlarmStopAtMs,_tmpCreatedAt,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getExpiredRuns(thresholdMs: Long): List<RecordingRunEntity> {
    val _sql: String = """
        |
        |        SELECT * FROM recording_runs
        |        WHERE status IN ('COMPLETED', 'FAILED', 'CANCELLED')
        |          AND terminal_at_ms IS NOT NULL
        |          AND terminal_at_ms < ?
        |        ORDER BY terminal_at_ms ASC
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, thresholdMs)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfScheduleId: Int = getColumnIndexOrThrow(_stmt, "schedule_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfChannelId: Int = getColumnIndexOrThrow(_stmt, "channel_id")
        val _columnIndexOfChannelName: Int = getColumnIndexOrThrow(_stmt, "channel_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfProgramTitle: Int = getColumnIndexOrThrow(_stmt, "program_title")
        val _columnIndexOfScheduledStartMs: Int = getColumnIndexOrThrow(_stmt, "scheduled_start_ms")
        val _columnIndexOfScheduledEndMs: Int = getColumnIndexOrThrow(_stmt, "scheduled_end_ms")
        val _columnIndexOfRecurrence: Int = getColumnIndexOrThrow(_stmt, "recurrence")
        val _columnIndexOfRecurringRuleId: Int = getColumnIndexOrThrow(_stmt, "recurring_rule_id")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSourceType: Int = getColumnIndexOrThrow(_stmt, "source_type")
        val _columnIndexOfResolvedUrl: Int = getColumnIndexOrThrow(_stmt, "resolved_url")
        val _columnIndexOfHeadersJson: Int = getColumnIndexOrThrow(_stmt, "headers_json")
        val _columnIndexOfUserAgent: Int = getColumnIndexOrThrow(_stmt, "user_agent")
        val _columnIndexOfExpirationTime: Int = getColumnIndexOrThrow(_stmt, "expiration_time")
        val _columnIndexOfProviderLabel: Int = getColumnIndexOrThrow(_stmt, "provider_label")
        val _columnIndexOfOutputUri: Int = getColumnIndexOrThrow(_stmt, "output_uri")
        val _columnIndexOfOutputDisplayPath: Int = getColumnIndexOrThrow(_stmt, "output_display_path")
        val _columnIndexOfBytesWritten: Int = getColumnIndexOrThrow(_stmt, "bytes_written")
        val _columnIndexOfAverageThroughputBytesPerSecond: Int = getColumnIndexOrThrow(_stmt, "average_throughput_bps")
        val _columnIndexOfRetryCount: Int = getColumnIndexOrThrow(_stmt, "retry_count")
        val _columnIndexOfLastProgressAtMs: Int = getColumnIndexOrThrow(_stmt, "last_progress_at_ms")
        val _columnIndexOfFailureCategory: Int = getColumnIndexOrThrow(_stmt, "failure_category")
        val _columnIndexOfFailureReason: Int = getColumnIndexOrThrow(_stmt, "failure_reason")
        val _columnIndexOfTerminalAtMs: Int = getColumnIndexOrThrow(_stmt, "terminal_at_ms")
        val _columnIndexOfStartedAtMs: Int = getColumnIndexOrThrow(_stmt, "started_at_ms")
        val _columnIndexOfEndedAtMs: Int = getColumnIndexOrThrow(_stmt, "ended_at_ms")
        val _columnIndexOfScheduleEnabled: Int = getColumnIndexOrThrow(_stmt, "schedule_enabled")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfAlarmStartAtMs: Int = getColumnIndexOrThrow(_stmt, "alarm_start_at_ms")
        val _columnIndexOfAlarmStopAtMs: Int = getColumnIndexOrThrow(_stmt, "alarm_stop_at_ms")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: MutableList<RecordingRunEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RecordingRunEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpScheduleId: Long
          _tmpScheduleId = _stmt.getLong(_columnIndexOfScheduleId)
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
          val _tmpScheduledStartMs: Long
          _tmpScheduledStartMs = _stmt.getLong(_columnIndexOfScheduledStartMs)
          val _tmpScheduledEndMs: Long
          _tmpScheduledEndMs = _stmt.getLong(_columnIndexOfScheduledEndMs)
          val _tmpRecurrence: RecordingRecurrence
          _tmpRecurrence = __RecordingRecurrence_stringToEnum(_stmt.getText(_columnIndexOfRecurrence))
          val _tmpRecurringRuleId: String?
          if (_stmt.isNull(_columnIndexOfRecurringRuleId)) {
            _tmpRecurringRuleId = null
          } else {
            _tmpRecurringRuleId = _stmt.getText(_columnIndexOfRecurringRuleId)
          }
          val _tmpStatus: RecordingStatus
          _tmpStatus = __RecordingStatus_stringToEnum(_stmt.getText(_columnIndexOfStatus))
          val _tmpSourceType: RecordingSourceType
          _tmpSourceType = __RecordingSourceType_stringToEnum(_stmt.getText(_columnIndexOfSourceType))
          val _tmpResolvedUrl: String?
          if (_stmt.isNull(_columnIndexOfResolvedUrl)) {
            _tmpResolvedUrl = null
          } else {
            _tmpResolvedUrl = _stmt.getText(_columnIndexOfResolvedUrl)
          }
          val _tmpHeadersJson: String
          _tmpHeadersJson = _stmt.getText(_columnIndexOfHeadersJson)
          val _tmpUserAgent: String?
          if (_stmt.isNull(_columnIndexOfUserAgent)) {
            _tmpUserAgent = null
          } else {
            _tmpUserAgent = _stmt.getText(_columnIndexOfUserAgent)
          }
          val _tmpExpirationTime: Long?
          if (_stmt.isNull(_columnIndexOfExpirationTime)) {
            _tmpExpirationTime = null
          } else {
            _tmpExpirationTime = _stmt.getLong(_columnIndexOfExpirationTime)
          }
          val _tmpProviderLabel: String?
          if (_stmt.isNull(_columnIndexOfProviderLabel)) {
            _tmpProviderLabel = null
          } else {
            _tmpProviderLabel = _stmt.getText(_columnIndexOfProviderLabel)
          }
          val _tmpOutputUri: String?
          if (_stmt.isNull(_columnIndexOfOutputUri)) {
            _tmpOutputUri = null
          } else {
            _tmpOutputUri = _stmt.getText(_columnIndexOfOutputUri)
          }
          val _tmpOutputDisplayPath: String?
          if (_stmt.isNull(_columnIndexOfOutputDisplayPath)) {
            _tmpOutputDisplayPath = null
          } else {
            _tmpOutputDisplayPath = _stmt.getText(_columnIndexOfOutputDisplayPath)
          }
          val _tmpBytesWritten: Long
          _tmpBytesWritten = _stmt.getLong(_columnIndexOfBytesWritten)
          val _tmpAverageThroughputBytesPerSecond: Long
          _tmpAverageThroughputBytesPerSecond = _stmt.getLong(_columnIndexOfAverageThroughputBytesPerSecond)
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          val _tmpLastProgressAtMs: Long?
          if (_stmt.isNull(_columnIndexOfLastProgressAtMs)) {
            _tmpLastProgressAtMs = null
          } else {
            _tmpLastProgressAtMs = _stmt.getLong(_columnIndexOfLastProgressAtMs)
          }
          val _tmpFailureCategory: RecordingFailureCategory
          _tmpFailureCategory = __RecordingFailureCategory_stringToEnum(_stmt.getText(_columnIndexOfFailureCategory))
          val _tmpFailureReason: String?
          if (_stmt.isNull(_columnIndexOfFailureReason)) {
            _tmpFailureReason = null
          } else {
            _tmpFailureReason = _stmt.getText(_columnIndexOfFailureReason)
          }
          val _tmpTerminalAtMs: Long?
          if (_stmt.isNull(_columnIndexOfTerminalAtMs)) {
            _tmpTerminalAtMs = null
          } else {
            _tmpTerminalAtMs = _stmt.getLong(_columnIndexOfTerminalAtMs)
          }
          val _tmpStartedAtMs: Long?
          if (_stmt.isNull(_columnIndexOfStartedAtMs)) {
            _tmpStartedAtMs = null
          } else {
            _tmpStartedAtMs = _stmt.getLong(_columnIndexOfStartedAtMs)
          }
          val _tmpEndedAtMs: Long?
          if (_stmt.isNull(_columnIndexOfEndedAtMs)) {
            _tmpEndedAtMs = null
          } else {
            _tmpEndedAtMs = _stmt.getLong(_columnIndexOfEndedAtMs)
          }
          val _tmpScheduleEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfScheduleEnabled).toInt()
          _tmpScheduleEnabled = _tmp != 0
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpAlarmStartAtMs: Long?
          if (_stmt.isNull(_columnIndexOfAlarmStartAtMs)) {
            _tmpAlarmStartAtMs = null
          } else {
            _tmpAlarmStartAtMs = _stmt.getLong(_columnIndexOfAlarmStartAtMs)
          }
          val _tmpAlarmStopAtMs: Long?
          if (_stmt.isNull(_columnIndexOfAlarmStopAtMs)) {
            _tmpAlarmStopAtMs = null
          } else {
            _tmpAlarmStopAtMs = _stmt.getLong(_columnIndexOfAlarmStopAtMs)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item = RecordingRunEntity(_tmpId,_tmpScheduleId,_tmpProviderId,_tmpChannelId,_tmpChannelName,_tmpStreamUrl,_tmpProgramTitle,_tmpScheduledStartMs,_tmpScheduledEndMs,_tmpRecurrence,_tmpRecurringRuleId,_tmpStatus,_tmpSourceType,_tmpResolvedUrl,_tmpHeadersJson,_tmpUserAgent,_tmpExpirationTime,_tmpProviderLabel,_tmpOutputUri,_tmpOutputDisplayPath,_tmpBytesWritten,_tmpAverageThroughputBytesPerSecond,_tmpRetryCount,_tmpLastProgressAtMs,_tmpFailureCategory,_tmpFailureReason,_tmpTerminalAtMs,_tmpStartedAtMs,_tmpEndedAtMs,_tmpScheduleEnabled,_tmpPriority,_tmpAlarmStartAtMs,_tmpAlarmStopAtMs,_tmpCreatedAt,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getScheduledByRecurringRuleId(recurringRuleId: String): List<RecordingRunEntity> {
    val _sql: String = """
        |
        |        SELECT * FROM recording_runs
        |        WHERE recurring_rule_id = ?
        |          AND status = 'SCHEDULED'
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, recurringRuleId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfScheduleId: Int = getColumnIndexOrThrow(_stmt, "schedule_id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfChannelId: Int = getColumnIndexOrThrow(_stmt, "channel_id")
        val _columnIndexOfChannelName: Int = getColumnIndexOrThrow(_stmt, "channel_name")
        val _columnIndexOfStreamUrl: Int = getColumnIndexOrThrow(_stmt, "stream_url")
        val _columnIndexOfProgramTitle: Int = getColumnIndexOrThrow(_stmt, "program_title")
        val _columnIndexOfScheduledStartMs: Int = getColumnIndexOrThrow(_stmt, "scheduled_start_ms")
        val _columnIndexOfScheduledEndMs: Int = getColumnIndexOrThrow(_stmt, "scheduled_end_ms")
        val _columnIndexOfRecurrence: Int = getColumnIndexOrThrow(_stmt, "recurrence")
        val _columnIndexOfRecurringRuleId: Int = getColumnIndexOrThrow(_stmt, "recurring_rule_id")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfSourceType: Int = getColumnIndexOrThrow(_stmt, "source_type")
        val _columnIndexOfResolvedUrl: Int = getColumnIndexOrThrow(_stmt, "resolved_url")
        val _columnIndexOfHeadersJson: Int = getColumnIndexOrThrow(_stmt, "headers_json")
        val _columnIndexOfUserAgent: Int = getColumnIndexOrThrow(_stmt, "user_agent")
        val _columnIndexOfExpirationTime: Int = getColumnIndexOrThrow(_stmt, "expiration_time")
        val _columnIndexOfProviderLabel: Int = getColumnIndexOrThrow(_stmt, "provider_label")
        val _columnIndexOfOutputUri: Int = getColumnIndexOrThrow(_stmt, "output_uri")
        val _columnIndexOfOutputDisplayPath: Int = getColumnIndexOrThrow(_stmt, "output_display_path")
        val _columnIndexOfBytesWritten: Int = getColumnIndexOrThrow(_stmt, "bytes_written")
        val _columnIndexOfAverageThroughputBytesPerSecond: Int = getColumnIndexOrThrow(_stmt, "average_throughput_bps")
        val _columnIndexOfRetryCount: Int = getColumnIndexOrThrow(_stmt, "retry_count")
        val _columnIndexOfLastProgressAtMs: Int = getColumnIndexOrThrow(_stmt, "last_progress_at_ms")
        val _columnIndexOfFailureCategory: Int = getColumnIndexOrThrow(_stmt, "failure_category")
        val _columnIndexOfFailureReason: Int = getColumnIndexOrThrow(_stmt, "failure_reason")
        val _columnIndexOfTerminalAtMs: Int = getColumnIndexOrThrow(_stmt, "terminal_at_ms")
        val _columnIndexOfStartedAtMs: Int = getColumnIndexOrThrow(_stmt, "started_at_ms")
        val _columnIndexOfEndedAtMs: Int = getColumnIndexOrThrow(_stmt, "ended_at_ms")
        val _columnIndexOfScheduleEnabled: Int = getColumnIndexOrThrow(_stmt, "schedule_enabled")
        val _columnIndexOfPriority: Int = getColumnIndexOrThrow(_stmt, "priority")
        val _columnIndexOfAlarmStartAtMs: Int = getColumnIndexOrThrow(_stmt, "alarm_start_at_ms")
        val _columnIndexOfAlarmStopAtMs: Int = getColumnIndexOrThrow(_stmt, "alarm_stop_at_ms")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: MutableList<RecordingRunEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: RecordingRunEntity
          val _tmpId: String
          _tmpId = _stmt.getText(_columnIndexOfId)
          val _tmpScheduleId: Long
          _tmpScheduleId = _stmt.getLong(_columnIndexOfScheduleId)
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
          val _tmpScheduledStartMs: Long
          _tmpScheduledStartMs = _stmt.getLong(_columnIndexOfScheduledStartMs)
          val _tmpScheduledEndMs: Long
          _tmpScheduledEndMs = _stmt.getLong(_columnIndexOfScheduledEndMs)
          val _tmpRecurrence: RecordingRecurrence
          _tmpRecurrence = __RecordingRecurrence_stringToEnum(_stmt.getText(_columnIndexOfRecurrence))
          val _tmpRecurringRuleId: String?
          if (_stmt.isNull(_columnIndexOfRecurringRuleId)) {
            _tmpRecurringRuleId = null
          } else {
            _tmpRecurringRuleId = _stmt.getText(_columnIndexOfRecurringRuleId)
          }
          val _tmpStatus: RecordingStatus
          _tmpStatus = __RecordingStatus_stringToEnum(_stmt.getText(_columnIndexOfStatus))
          val _tmpSourceType: RecordingSourceType
          _tmpSourceType = __RecordingSourceType_stringToEnum(_stmt.getText(_columnIndexOfSourceType))
          val _tmpResolvedUrl: String?
          if (_stmt.isNull(_columnIndexOfResolvedUrl)) {
            _tmpResolvedUrl = null
          } else {
            _tmpResolvedUrl = _stmt.getText(_columnIndexOfResolvedUrl)
          }
          val _tmpHeadersJson: String
          _tmpHeadersJson = _stmt.getText(_columnIndexOfHeadersJson)
          val _tmpUserAgent: String?
          if (_stmt.isNull(_columnIndexOfUserAgent)) {
            _tmpUserAgent = null
          } else {
            _tmpUserAgent = _stmt.getText(_columnIndexOfUserAgent)
          }
          val _tmpExpirationTime: Long?
          if (_stmt.isNull(_columnIndexOfExpirationTime)) {
            _tmpExpirationTime = null
          } else {
            _tmpExpirationTime = _stmt.getLong(_columnIndexOfExpirationTime)
          }
          val _tmpProviderLabel: String?
          if (_stmt.isNull(_columnIndexOfProviderLabel)) {
            _tmpProviderLabel = null
          } else {
            _tmpProviderLabel = _stmt.getText(_columnIndexOfProviderLabel)
          }
          val _tmpOutputUri: String?
          if (_stmt.isNull(_columnIndexOfOutputUri)) {
            _tmpOutputUri = null
          } else {
            _tmpOutputUri = _stmt.getText(_columnIndexOfOutputUri)
          }
          val _tmpOutputDisplayPath: String?
          if (_stmt.isNull(_columnIndexOfOutputDisplayPath)) {
            _tmpOutputDisplayPath = null
          } else {
            _tmpOutputDisplayPath = _stmt.getText(_columnIndexOfOutputDisplayPath)
          }
          val _tmpBytesWritten: Long
          _tmpBytesWritten = _stmt.getLong(_columnIndexOfBytesWritten)
          val _tmpAverageThroughputBytesPerSecond: Long
          _tmpAverageThroughputBytesPerSecond = _stmt.getLong(_columnIndexOfAverageThroughputBytesPerSecond)
          val _tmpRetryCount: Int
          _tmpRetryCount = _stmt.getLong(_columnIndexOfRetryCount).toInt()
          val _tmpLastProgressAtMs: Long?
          if (_stmt.isNull(_columnIndexOfLastProgressAtMs)) {
            _tmpLastProgressAtMs = null
          } else {
            _tmpLastProgressAtMs = _stmt.getLong(_columnIndexOfLastProgressAtMs)
          }
          val _tmpFailureCategory: RecordingFailureCategory
          _tmpFailureCategory = __RecordingFailureCategory_stringToEnum(_stmt.getText(_columnIndexOfFailureCategory))
          val _tmpFailureReason: String?
          if (_stmt.isNull(_columnIndexOfFailureReason)) {
            _tmpFailureReason = null
          } else {
            _tmpFailureReason = _stmt.getText(_columnIndexOfFailureReason)
          }
          val _tmpTerminalAtMs: Long?
          if (_stmt.isNull(_columnIndexOfTerminalAtMs)) {
            _tmpTerminalAtMs = null
          } else {
            _tmpTerminalAtMs = _stmt.getLong(_columnIndexOfTerminalAtMs)
          }
          val _tmpStartedAtMs: Long?
          if (_stmt.isNull(_columnIndexOfStartedAtMs)) {
            _tmpStartedAtMs = null
          } else {
            _tmpStartedAtMs = _stmt.getLong(_columnIndexOfStartedAtMs)
          }
          val _tmpEndedAtMs: Long?
          if (_stmt.isNull(_columnIndexOfEndedAtMs)) {
            _tmpEndedAtMs = null
          } else {
            _tmpEndedAtMs = _stmt.getLong(_columnIndexOfEndedAtMs)
          }
          val _tmpScheduleEnabled: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfScheduleEnabled).toInt()
          _tmpScheduleEnabled = _tmp != 0
          val _tmpPriority: Int
          _tmpPriority = _stmt.getLong(_columnIndexOfPriority).toInt()
          val _tmpAlarmStartAtMs: Long?
          if (_stmt.isNull(_columnIndexOfAlarmStartAtMs)) {
            _tmpAlarmStartAtMs = null
          } else {
            _tmpAlarmStartAtMs = _stmt.getLong(_columnIndexOfAlarmStartAtMs)
          }
          val _tmpAlarmStopAtMs: Long?
          if (_stmt.isNull(_columnIndexOfAlarmStopAtMs)) {
            _tmpAlarmStopAtMs = null
          } else {
            _tmpAlarmStopAtMs = _stmt.getLong(_columnIndexOfAlarmStopAtMs)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _item = RecordingRunEntity(_tmpId,_tmpScheduleId,_tmpProviderId,_tmpChannelId,_tmpChannelName,_tmpStreamUrl,_tmpProgramTitle,_tmpScheduledStartMs,_tmpScheduledEndMs,_tmpRecurrence,_tmpRecurringRuleId,_tmpStatus,_tmpSourceType,_tmpResolvedUrl,_tmpHeadersJson,_tmpUserAgent,_tmpExpirationTime,_tmpProviderLabel,_tmpOutputUri,_tmpOutputDisplayPath,_tmpBytesWritten,_tmpAverageThroughputBytesPerSecond,_tmpRetryCount,_tmpLastProgressAtMs,_tmpFailureCategory,_tmpFailureReason,_tmpTerminalAtMs,_tmpStartedAtMs,_tmpEndedAtMs,_tmpScheduleEnabled,_tmpPriority,_tmpAlarmStartAtMs,_tmpAlarmStopAtMs,_tmpCreatedAt,_tmpUpdatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(id: String) {
    val _sql: String = "DELETE FROM recording_runs WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteAll() {
    val _sql: String = "DELETE FROM recording_runs"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateProgress(
    id: String,
    bytesWritten: Long,
    averageThroughputBytesPerSecond: Long,
    retryCount: Int,
    lastProgressAtMs: Long,
    updatedAt: Long,
  ) {
    val _sql: String = """
        |
        |        UPDATE recording_runs
        |        SET bytes_written = ?,
        |            average_throughput_bps = ?,
        |            retry_count = MAX(retry_count, ?),
        |            last_progress_at_ms = ?,
        |            updated_at = ?
        |        WHERE id = ?
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, bytesWritten)
        _argIndex = 2
        _stmt.bindLong(_argIndex, averageThroughputBytesPerSecond)
        _argIndex = 3
        _stmt.bindLong(_argIndex, retryCount.toLong())
        _argIndex = 4
        _stmt.bindLong(_argIndex, lastProgressAtMs)
        _argIndex = 5
        _stmt.bindLong(_argIndex, updatedAt)
        _argIndex = 6
        _stmt.bindText(_argIndex, id)
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

  private fun __RecordingStatus_enumToString(_value: RecordingStatus): String = when (_value) {
    RecordingStatus.SCHEDULED -> "SCHEDULED"
    RecordingStatus.RECORDING -> "RECORDING"
    RecordingStatus.COMPLETED -> "COMPLETED"
    RecordingStatus.FAILED -> "FAILED"
    RecordingStatus.CANCELLED -> "CANCELLED"
  }

  private fun __RecordingSourceType_enumToString(_value: RecordingSourceType): String = when (_value) {
    RecordingSourceType.TS -> "TS"
    RecordingSourceType.HLS -> "HLS"
    RecordingSourceType.DASH -> "DASH"
    RecordingSourceType.UNKNOWN -> "UNKNOWN"
  }

  private fun __RecordingFailureCategory_enumToString(_value: RecordingFailureCategory): String = when (_value) {
    RecordingFailureCategory.NONE -> "NONE"
    RecordingFailureCategory.NETWORK -> "NETWORK"
    RecordingFailureCategory.STORAGE -> "STORAGE"
    RecordingFailureCategory.AUTH -> "AUTH"
    RecordingFailureCategory.TOKEN_EXPIRED -> "TOKEN_EXPIRED"
    RecordingFailureCategory.DRM_UNSUPPORTED -> "DRM_UNSUPPORTED"
    RecordingFailureCategory.FORMAT_UNSUPPORTED -> "FORMAT_UNSUPPORTED"
    RecordingFailureCategory.SCHEDULE_CONFLICT -> "SCHEDULE_CONFLICT"
    RecordingFailureCategory.PROVIDER_LIMIT -> "PROVIDER_LIMIT"
    RecordingFailureCategory.UNKNOWN -> "UNKNOWN"
  }

  private fun __RecordingRecurrence_stringToEnum(_value: String): RecordingRecurrence = when (_value) {
    "NONE" -> RecordingRecurrence.NONE
    "DAILY" -> RecordingRecurrence.DAILY
    "WEEKLY" -> RecordingRecurrence.WEEKLY
    else -> throw IllegalArgumentException("Can't convert value to enum, unknown value: " + _value)
  }

  private fun __RecordingStatus_stringToEnum(_value: String): RecordingStatus = when (_value) {
    "SCHEDULED" -> RecordingStatus.SCHEDULED
    "RECORDING" -> RecordingStatus.RECORDING
    "COMPLETED" -> RecordingStatus.COMPLETED
    "FAILED" -> RecordingStatus.FAILED
    "CANCELLED" -> RecordingStatus.CANCELLED
    else -> throw IllegalArgumentException("Can't convert value to enum, unknown value: " + _value)
  }

  private fun __RecordingSourceType_stringToEnum(_value: String): RecordingSourceType = when (_value) {
    "TS" -> RecordingSourceType.TS
    "HLS" -> RecordingSourceType.HLS
    "DASH" -> RecordingSourceType.DASH
    "UNKNOWN" -> RecordingSourceType.UNKNOWN
    else -> throw IllegalArgumentException("Can't convert value to enum, unknown value: " + _value)
  }

  private fun __RecordingFailureCategory_stringToEnum(_value: String): RecordingFailureCategory = when (_value) {
    "NONE" -> RecordingFailureCategory.NONE
    "NETWORK" -> RecordingFailureCategory.NETWORK
    "STORAGE" -> RecordingFailureCategory.STORAGE
    "AUTH" -> RecordingFailureCategory.AUTH
    "TOKEN_EXPIRED" -> RecordingFailureCategory.TOKEN_EXPIRED
    "DRM_UNSUPPORTED" -> RecordingFailureCategory.DRM_UNSUPPORTED
    "FORMAT_UNSUPPORTED" -> RecordingFailureCategory.FORMAT_UNSUPPORTED
    "SCHEDULE_CONFLICT" -> RecordingFailureCategory.SCHEDULE_CONFLICT
    "PROVIDER_LIMIT" -> RecordingFailureCategory.PROVIDER_LIMIT
    "UNKNOWN" -> RecordingFailureCategory.UNKNOWN
    else -> throw IllegalArgumentException("Can't convert value to enum, unknown value: " + _value)
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
