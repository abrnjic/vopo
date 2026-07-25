package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.RecordingStorageEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
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
public class RecordingStorageDao_Impl(
  __db: RoomDatabase,
) : RecordingStorageDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfRecordingStorageEntity: EntityInsertAdapter<RecordingStorageEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfRecordingStorageEntity = object : EntityInsertAdapter<RecordingStorageEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `recording_storage` (`id`,`tree_uri`,`display_name`,`output_directory`,`available_bytes`,`is_writable`,`file_name_pattern`,`retention_days`,`max_simultaneous_recordings`,`updated_at`) VALUES (?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: RecordingStorageEntity) {
        statement.bindLong(1, entity.id)
        val _tmpTreeUri: String? = entity.treeUri
        if (_tmpTreeUri == null) {
          statement.bindNull(2)
        } else {
          statement.bindText(2, _tmpTreeUri)
        }
        val _tmpDisplayName: String? = entity.displayName
        if (_tmpDisplayName == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmpDisplayName)
        }
        val _tmpOutputDirectory: String? = entity.outputDirectory
        if (_tmpOutputDirectory == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpOutputDirectory)
        }
        val _tmpAvailableBytes: Long? = entity.availableBytes
        if (_tmpAvailableBytes == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpAvailableBytes)
        }
        val _tmp: Int = if (entity.isWritable) 1 else 0
        statement.bindLong(6, _tmp.toLong())
        statement.bindText(7, entity.fileNamePattern)
        val _tmpRetentionDays: Int? = entity.retentionDays
        if (_tmpRetentionDays == null) {
          statement.bindNull(8)
        } else {
          statement.bindLong(8, _tmpRetentionDays.toLong())
        }
        statement.bindLong(9, entity.maxSimultaneousRecordings.toLong())
        statement.bindLong(10, entity.updatedAt)
      }
    }
  }

  public override suspend fun upsert(storage: RecordingStorageEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfRecordingStorageEntity.insert(_connection, storage)
  }

  public override fun observe(): Flow<RecordingStorageEntity?> {
    val _sql: String = "SELECT * FROM recording_storage WHERE id = 1"
    return createFlow(__db, false, arrayOf("recording_storage")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTreeUri: Int = getColumnIndexOrThrow(_stmt, "tree_uri")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "display_name")
        val _columnIndexOfOutputDirectory: Int = getColumnIndexOrThrow(_stmt, "output_directory")
        val _columnIndexOfAvailableBytes: Int = getColumnIndexOrThrow(_stmt, "available_bytes")
        val _columnIndexOfIsWritable: Int = getColumnIndexOrThrow(_stmt, "is_writable")
        val _columnIndexOfFileNamePattern: Int = getColumnIndexOrThrow(_stmt, "file_name_pattern")
        val _columnIndexOfRetentionDays: Int = getColumnIndexOrThrow(_stmt, "retention_days")
        val _columnIndexOfMaxSimultaneousRecordings: Int = getColumnIndexOrThrow(_stmt, "max_simultaneous_recordings")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: RecordingStorageEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpTreeUri: String?
          if (_stmt.isNull(_columnIndexOfTreeUri)) {
            _tmpTreeUri = null
          } else {
            _tmpTreeUri = _stmt.getText(_columnIndexOfTreeUri)
          }
          val _tmpDisplayName: String?
          if (_stmt.isNull(_columnIndexOfDisplayName)) {
            _tmpDisplayName = null
          } else {
            _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          }
          val _tmpOutputDirectory: String?
          if (_stmt.isNull(_columnIndexOfOutputDirectory)) {
            _tmpOutputDirectory = null
          } else {
            _tmpOutputDirectory = _stmt.getText(_columnIndexOfOutputDirectory)
          }
          val _tmpAvailableBytes: Long?
          if (_stmt.isNull(_columnIndexOfAvailableBytes)) {
            _tmpAvailableBytes = null
          } else {
            _tmpAvailableBytes = _stmt.getLong(_columnIndexOfAvailableBytes)
          }
          val _tmpIsWritable: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsWritable).toInt()
          _tmpIsWritable = _tmp != 0
          val _tmpFileNamePattern: String
          _tmpFileNamePattern = _stmt.getText(_columnIndexOfFileNamePattern)
          val _tmpRetentionDays: Int?
          if (_stmt.isNull(_columnIndexOfRetentionDays)) {
            _tmpRetentionDays = null
          } else {
            _tmpRetentionDays = _stmt.getLong(_columnIndexOfRetentionDays).toInt()
          }
          val _tmpMaxSimultaneousRecordings: Int
          _tmpMaxSimultaneousRecordings = _stmt.getLong(_columnIndexOfMaxSimultaneousRecordings).toInt()
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _result = RecordingStorageEntity(_tmpId,_tmpTreeUri,_tmpDisplayName,_tmpOutputDirectory,_tmpAvailableBytes,_tmpIsWritable,_tmpFileNamePattern,_tmpRetentionDays,_tmpMaxSimultaneousRecordings,_tmpUpdatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun `get`(): RecordingStorageEntity? {
    val _sql: String = "SELECT * FROM recording_storage WHERE id = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfTreeUri: Int = getColumnIndexOrThrow(_stmt, "tree_uri")
        val _columnIndexOfDisplayName: Int = getColumnIndexOrThrow(_stmt, "display_name")
        val _columnIndexOfOutputDirectory: Int = getColumnIndexOrThrow(_stmt, "output_directory")
        val _columnIndexOfAvailableBytes: Int = getColumnIndexOrThrow(_stmt, "available_bytes")
        val _columnIndexOfIsWritable: Int = getColumnIndexOrThrow(_stmt, "is_writable")
        val _columnIndexOfFileNamePattern: Int = getColumnIndexOrThrow(_stmt, "file_name_pattern")
        val _columnIndexOfRetentionDays: Int = getColumnIndexOrThrow(_stmt, "retention_days")
        val _columnIndexOfMaxSimultaneousRecordings: Int = getColumnIndexOrThrow(_stmt, "max_simultaneous_recordings")
        val _columnIndexOfUpdatedAt: Int = getColumnIndexOrThrow(_stmt, "updated_at")
        val _result: RecordingStorageEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpTreeUri: String?
          if (_stmt.isNull(_columnIndexOfTreeUri)) {
            _tmpTreeUri = null
          } else {
            _tmpTreeUri = _stmt.getText(_columnIndexOfTreeUri)
          }
          val _tmpDisplayName: String?
          if (_stmt.isNull(_columnIndexOfDisplayName)) {
            _tmpDisplayName = null
          } else {
            _tmpDisplayName = _stmt.getText(_columnIndexOfDisplayName)
          }
          val _tmpOutputDirectory: String?
          if (_stmt.isNull(_columnIndexOfOutputDirectory)) {
            _tmpOutputDirectory = null
          } else {
            _tmpOutputDirectory = _stmt.getText(_columnIndexOfOutputDirectory)
          }
          val _tmpAvailableBytes: Long?
          if (_stmt.isNull(_columnIndexOfAvailableBytes)) {
            _tmpAvailableBytes = null
          } else {
            _tmpAvailableBytes = _stmt.getLong(_columnIndexOfAvailableBytes)
          }
          val _tmpIsWritable: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfIsWritable).toInt()
          _tmpIsWritable = _tmp != 0
          val _tmpFileNamePattern: String
          _tmpFileNamePattern = _stmt.getText(_columnIndexOfFileNamePattern)
          val _tmpRetentionDays: Int?
          if (_stmt.isNull(_columnIndexOfRetentionDays)) {
            _tmpRetentionDays = null
          } else {
            _tmpRetentionDays = _stmt.getLong(_columnIndexOfRetentionDays).toInt()
          }
          val _tmpMaxSimultaneousRecordings: Int
          _tmpMaxSimultaneousRecordings = _stmt.getLong(_columnIndexOfMaxSimultaneousRecordings).toInt()
          val _tmpUpdatedAt: Long
          _tmpUpdatedAt = _stmt.getLong(_columnIndexOfUpdatedAt)
          _result = RecordingStorageEntity(_tmpId,_tmpTreeUri,_tmpDisplayName,_tmpOutputDirectory,_tmpAvailableBytes,_tmpIsWritable,_tmpFileNamePattern,_tmpRetentionDays,_tmpMaxSimultaneousRecordings,_tmpUpdatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
