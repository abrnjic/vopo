package com.vopo.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.ProgramReminderEntity
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
public class ProgramReminderDao_Impl(
  __db: RoomDatabase,
) : ProgramReminderDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfProgramReminderEntity: EntityInsertAdapter<ProgramReminderEntity>

  private val __updateAdapterOfProgramReminderEntity:
      EntityDeleteOrUpdateAdapter<ProgramReminderEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfProgramReminderEntity = object : EntityInsertAdapter<ProgramReminderEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `program_reminders` (`id`,`provider_id`,`channel_id`,`channel_name`,`program_title`,`program_start_time`,`remind_at`,`lead_time_minutes`,`is_dismissed`,`notified_at`,`created_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ProgramReminderEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.providerId)
        statement.bindText(3, entity.channelId)
        statement.bindText(4, entity.channelName)
        statement.bindText(5, entity.programTitle)
        statement.bindLong(6, entity.programStartTime)
        statement.bindLong(7, entity.remindAt)
        statement.bindLong(8, entity.leadTimeMinutes.toLong())
        val _tmp: Int = if (entity.isDismissed) 1 else 0
        statement.bindLong(9, _tmp.toLong())
        val _tmpNotifiedAt: Long? = entity.notifiedAt
        if (_tmpNotifiedAt == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpNotifiedAt)
        }
        statement.bindLong(11, entity.createdAt)
      }
    }
    this.__updateAdapterOfProgramReminderEntity = object : EntityDeleteOrUpdateAdapter<ProgramReminderEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `program_reminders` SET `id` = ?,`provider_id` = ?,`channel_id` = ?,`channel_name` = ?,`program_title` = ?,`program_start_time` = ?,`remind_at` = ?,`lead_time_minutes` = ?,`is_dismissed` = ?,`notified_at` = ?,`created_at` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ProgramReminderEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.providerId)
        statement.bindText(3, entity.channelId)
        statement.bindText(4, entity.channelName)
        statement.bindText(5, entity.programTitle)
        statement.bindLong(6, entity.programStartTime)
        statement.bindLong(7, entity.remindAt)
        statement.bindLong(8, entity.leadTimeMinutes.toLong())
        val _tmp: Int = if (entity.isDismissed) 1 else 0
        statement.bindLong(9, _tmp.toLong())
        val _tmpNotifiedAt: Long? = entity.notifiedAt
        if (_tmpNotifiedAt == null) {
          statement.bindNull(10)
        } else {
          statement.bindLong(10, _tmpNotifiedAt)
        }
        statement.bindLong(11, entity.createdAt)
        statement.bindLong(12, entity.id)
      }
    }
  }

  public override suspend fun insert(reminder: ProgramReminderEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfProgramReminderEntity.insertAndReturnId(_connection, reminder)
    _result
  }

  public override suspend fun update(reminder: ProgramReminderEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfProgramReminderEntity.handle(_connection, reminder)
  }

  public override suspend fun getIdsByProvider(providerId: Long): List<Long> {
    val _sql: String = "SELECT id FROM program_reminders WHERE provider_id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
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

  public override fun observeUpcoming(): Flow<List<ProgramReminderEntity>> {
    val _sql: String = """
        |
        |        SELECT * FROM program_reminders
        |        WHERE is_dismissed = 0
        |        ORDER BY remind_at ASC, program_start_time ASC
        |        
        """.trimMargin()
    return createFlow(__db, false, arrayOf("program_reminders")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfChannelId: Int = getColumnIndexOrThrow(_stmt, "channel_id")
        val _columnIndexOfChannelName: Int = getColumnIndexOrThrow(_stmt, "channel_name")
        val _columnIndexOfProgramTitle: Int = getColumnIndexOrThrow(_stmt, "program_title")
        val _columnIndexOfProgramStartTime: Int = getColumnIndexOrThrow(_stmt, "program_start_time")
        val _columnIndexOfRemindAt: Int = getColumnIndexOrThrow(_stmt, "remind_at")
        val _columnIndexOfLeadTimeMinutes: Int = getColumnIndexOrThrow(_stmt, "lead_time_minutes")
        val _columnIndexOfIsDismissed: Int = getColumnIndexOrThrow(_stmt, "is_dismissed")
        val _columnIndexOfNotifiedAt: Int = getColumnIndexOrThrow(_stmt, "notified_at")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _result: MutableList<ProgramReminderEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProgramReminderEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpChannelId: String
          _tmpChannelId = _stmt.getText(_columnIndexOfChannelId)
          val _tmpChannelName: String
          _tmpChannelName = _stmt.getText(_columnIndexOfChannelName)
          val _tmpProgramTitle: String
          _tmpProgramTitle = _stmt.getText(_columnIndexOfProgramTitle)
          val _tmpProgramStartTime: Long
          _tmpProgramStartTime = _stmt.getLong(_columnIndexOfProgramStartTime)
          val _tmpRemindAt: Long
          _tmpRemindAt = _stmt.getLong(_columnIndexOfRemindAt)
          val _tmpLeadTimeMinutes: Int
          _tmpLeadTimeMinutes = _stmt.getLong(_columnIndexOfLeadTimeMinutes).toInt()
          val _tmpIsDismissed: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDismissed).toInt()
          _tmpIsDismissed = _tmp != 0
          val _tmpNotifiedAt: Long?
          if (_stmt.isNull(_columnIndexOfNotifiedAt)) {
            _tmpNotifiedAt = null
          } else {
            _tmpNotifiedAt = _stmt.getLong(_columnIndexOfNotifiedAt)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item = ProgramReminderEntity(_tmpId,_tmpProviderId,_tmpChannelId,_tmpChannelName,_tmpProgramTitle,_tmpProgramStartTime,_tmpRemindAt,_tmpLeadTimeMinutes,_tmpIsDismissed,_tmpNotifiedAt,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByProgram(
    providerId: Long,
    channelId: String,
    programTitle: String,
    programStartTime: Long,
  ): ProgramReminderEntity? {
    val _sql: String = """
        |
        |        SELECT * FROM program_reminders
        |        WHERE provider_id = ?
        |          AND channel_id = ?
        |          AND program_title = ?
        |          AND program_start_time = ?
        |        LIMIT 1
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, channelId)
        _argIndex = 3
        _stmt.bindText(_argIndex, programTitle)
        _argIndex = 4
        _stmt.bindLong(_argIndex, programStartTime)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfChannelId: Int = getColumnIndexOrThrow(_stmt, "channel_id")
        val _columnIndexOfChannelName: Int = getColumnIndexOrThrow(_stmt, "channel_name")
        val _columnIndexOfProgramTitle: Int = getColumnIndexOrThrow(_stmt, "program_title")
        val _columnIndexOfProgramStartTime: Int = getColumnIndexOrThrow(_stmt, "program_start_time")
        val _columnIndexOfRemindAt: Int = getColumnIndexOrThrow(_stmt, "remind_at")
        val _columnIndexOfLeadTimeMinutes: Int = getColumnIndexOrThrow(_stmt, "lead_time_minutes")
        val _columnIndexOfIsDismissed: Int = getColumnIndexOrThrow(_stmt, "is_dismissed")
        val _columnIndexOfNotifiedAt: Int = getColumnIndexOrThrow(_stmt, "notified_at")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _result: ProgramReminderEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpChannelId: String
          _tmpChannelId = _stmt.getText(_columnIndexOfChannelId)
          val _tmpChannelName: String
          _tmpChannelName = _stmt.getText(_columnIndexOfChannelName)
          val _tmpProgramTitle: String
          _tmpProgramTitle = _stmt.getText(_columnIndexOfProgramTitle)
          val _tmpProgramStartTime: Long
          _tmpProgramStartTime = _stmt.getLong(_columnIndexOfProgramStartTime)
          val _tmpRemindAt: Long
          _tmpRemindAt = _stmt.getLong(_columnIndexOfRemindAt)
          val _tmpLeadTimeMinutes: Int
          _tmpLeadTimeMinutes = _stmt.getLong(_columnIndexOfLeadTimeMinutes).toInt()
          val _tmpIsDismissed: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDismissed).toInt()
          _tmpIsDismissed = _tmp != 0
          val _tmpNotifiedAt: Long?
          if (_stmt.isNull(_columnIndexOfNotifiedAt)) {
            _tmpNotifiedAt = null
          } else {
            _tmpNotifiedAt = _stmt.getLong(_columnIndexOfNotifiedAt)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _result = ProgramReminderEntity(_tmpId,_tmpProviderId,_tmpChannelId,_tmpChannelName,_tmpProgramTitle,_tmpProgramStartTime,_tmpRemindAt,_tmpLeadTimeMinutes,_tmpIsDismissed,_tmpNotifiedAt,_tmpCreatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long): ProgramReminderEntity? {
    val _sql: String = "SELECT * FROM program_reminders WHERE id = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfChannelId: Int = getColumnIndexOrThrow(_stmt, "channel_id")
        val _columnIndexOfChannelName: Int = getColumnIndexOrThrow(_stmt, "channel_name")
        val _columnIndexOfProgramTitle: Int = getColumnIndexOrThrow(_stmt, "program_title")
        val _columnIndexOfProgramStartTime: Int = getColumnIndexOrThrow(_stmt, "program_start_time")
        val _columnIndexOfRemindAt: Int = getColumnIndexOrThrow(_stmt, "remind_at")
        val _columnIndexOfLeadTimeMinutes: Int = getColumnIndexOrThrow(_stmt, "lead_time_minutes")
        val _columnIndexOfIsDismissed: Int = getColumnIndexOrThrow(_stmt, "is_dismissed")
        val _columnIndexOfNotifiedAt: Int = getColumnIndexOrThrow(_stmt, "notified_at")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _result: ProgramReminderEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpChannelId: String
          _tmpChannelId = _stmt.getText(_columnIndexOfChannelId)
          val _tmpChannelName: String
          _tmpChannelName = _stmt.getText(_columnIndexOfChannelName)
          val _tmpProgramTitle: String
          _tmpProgramTitle = _stmt.getText(_columnIndexOfProgramTitle)
          val _tmpProgramStartTime: Long
          _tmpProgramStartTime = _stmt.getLong(_columnIndexOfProgramStartTime)
          val _tmpRemindAt: Long
          _tmpRemindAt = _stmt.getLong(_columnIndexOfRemindAt)
          val _tmpLeadTimeMinutes: Int
          _tmpLeadTimeMinutes = _stmt.getLong(_columnIndexOfLeadTimeMinutes).toInt()
          val _tmpIsDismissed: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDismissed).toInt()
          _tmpIsDismissed = _tmp != 0
          val _tmpNotifiedAt: Long?
          if (_stmt.isNull(_columnIndexOfNotifiedAt)) {
            _tmpNotifiedAt = null
          } else {
            _tmpNotifiedAt = _stmt.getLong(_columnIndexOfNotifiedAt)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _result = ProgramReminderEntity(_tmpId,_tmpProviderId,_tmpChannelId,_tmpChannelName,_tmpProgramTitle,_tmpProgramStartTime,_tmpRemindAt,_tmpLeadTimeMinutes,_tmpIsDismissed,_tmpNotifiedAt,_tmpCreatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getPendingActive(now: Long): List<ProgramReminderEntity> {
    val _sql: String = """
        |
        |        SELECT * FROM program_reminders
        |        WHERE is_dismissed = 0
        |          AND notified_at IS NULL
        |          AND program_start_time > ?
        |        ORDER BY remind_at ASC
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, now)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfProviderId: Int = getColumnIndexOrThrow(_stmt, "provider_id")
        val _columnIndexOfChannelId: Int = getColumnIndexOrThrow(_stmt, "channel_id")
        val _columnIndexOfChannelName: Int = getColumnIndexOrThrow(_stmt, "channel_name")
        val _columnIndexOfProgramTitle: Int = getColumnIndexOrThrow(_stmt, "program_title")
        val _columnIndexOfProgramStartTime: Int = getColumnIndexOrThrow(_stmt, "program_start_time")
        val _columnIndexOfRemindAt: Int = getColumnIndexOrThrow(_stmt, "remind_at")
        val _columnIndexOfLeadTimeMinutes: Int = getColumnIndexOrThrow(_stmt, "lead_time_minutes")
        val _columnIndexOfIsDismissed: Int = getColumnIndexOrThrow(_stmt, "is_dismissed")
        val _columnIndexOfNotifiedAt: Int = getColumnIndexOrThrow(_stmt, "notified_at")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _result: MutableList<ProgramReminderEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProgramReminderEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpProviderId: Long
          _tmpProviderId = _stmt.getLong(_columnIndexOfProviderId)
          val _tmpChannelId: String
          _tmpChannelId = _stmt.getText(_columnIndexOfChannelId)
          val _tmpChannelName: String
          _tmpChannelName = _stmt.getText(_columnIndexOfChannelName)
          val _tmpProgramTitle: String
          _tmpProgramTitle = _stmt.getText(_columnIndexOfProgramTitle)
          val _tmpProgramStartTime: Long
          _tmpProgramStartTime = _stmt.getLong(_columnIndexOfProgramStartTime)
          val _tmpRemindAt: Long
          _tmpRemindAt = _stmt.getLong(_columnIndexOfRemindAt)
          val _tmpLeadTimeMinutes: Int
          _tmpLeadTimeMinutes = _stmt.getLong(_columnIndexOfLeadTimeMinutes).toInt()
          val _tmpIsDismissed: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsDismissed).toInt()
          _tmpIsDismissed = _tmp != 0
          val _tmpNotifiedAt: Long?
          if (_stmt.isNull(_columnIndexOfNotifiedAt)) {
            _tmpNotifiedAt = null
          } else {
            _tmpNotifiedAt = _stmt.getLong(_columnIndexOfNotifiedAt)
          }
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item = ProgramReminderEntity(_tmpId,_tmpProviderId,_tmpChannelId,_tmpChannelName,_tmpProgramTitle,_tmpProgramStartTime,_tmpRemindAt,_tmpLeadTimeMinutes,_tmpIsDismissed,_tmpNotifiedAt,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteByProgram(
    providerId: Long,
    channelId: String,
    programTitle: String,
    programStartTime: Long,
  ) {
    val _sql: String = """
        |
        |        DELETE FROM program_reminders
        |        WHERE provider_id = ?
        |          AND channel_id = ?
        |          AND program_title = ?
        |          AND program_start_time = ?
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, providerId)
        _argIndex = 2
        _stmt.bindText(_argIndex, channelId)
        _argIndex = 3
        _stmt.bindText(_argIndex, programTitle)
        _argIndex = 4
        _stmt.bindLong(_argIndex, programStartTime)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteById(id: Long) {
    val _sql: String = "DELETE FROM program_reminders WHERE id = ?"
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

  public override suspend fun deleteExpired(beforeTime: Long): Int {
    val _sql: String = "DELETE FROM program_reminders WHERE program_start_time < ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, beforeTime)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
