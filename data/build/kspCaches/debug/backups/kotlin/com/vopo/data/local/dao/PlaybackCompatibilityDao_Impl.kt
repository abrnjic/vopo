package com.vopo.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.getTotalChangedRows
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.entity.PlaybackCompatibilityRecordEntity
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

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class PlaybackCompatibilityDao_Impl(
  __db: RoomDatabase,
) : PlaybackCompatibilityDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfPlaybackCompatibilityRecordEntity:
      EntityInsertAdapter<PlaybackCompatibilityRecordEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfPlaybackCompatibilityRecordEntity = object : EntityInsertAdapter<PlaybackCompatibilityRecordEntity>() {
      protected override fun createQuery(): String = "INSERT OR IGNORE INTO `playback_compatibility_records` (`id`,`device_fingerprint`,`device_model`,`android_sdk`,`stream_type`,`video_mime_type`,`resolution_bucket`,`decoder_name`,`surface_type`,`failure_type`,`last_failed_at`,`last_succeeded_at`,`failure_count`,`success_count`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: PlaybackCompatibilityRecordEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.deviceFingerprint)
        statement.bindText(3, entity.deviceModel)
        statement.bindLong(4, entity.androidSdk.toLong())
        statement.bindText(5, entity.streamType)
        statement.bindText(6, entity.videoMimeType)
        statement.bindText(7, entity.resolutionBucket)
        statement.bindText(8, entity.decoderName)
        statement.bindText(9, entity.surfaceType)
        statement.bindText(10, entity.failureType)
        statement.bindLong(11, entity.lastFailedAt)
        statement.bindLong(12, entity.lastSucceededAt)
        statement.bindLong(13, entity.failureCount.toLong())
        statement.bindLong(14, entity.successCount.toLong())
      }
    }
  }

  public override suspend fun insertCompatibilityRecordIgnore(record: PlaybackCompatibilityRecordEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfPlaybackCompatibilityRecordEntity.insertAndReturnId(_connection, record)
    _result
  }

  public override suspend fun recordFailure(
    deviceFingerprint: String,
    deviceModel: String,
    androidSdk: Int,
    streamType: String,
    videoMimeType: String,
    resolutionBucket: String,
    decoderName: String,
    surfaceType: String,
    failureType: String,
    failedAt: Long,
  ): Unit = performInTransactionSuspending(__db) {
    super@PlaybackCompatibilityDao_Impl.recordFailure(deviceFingerprint, deviceModel, androidSdk, streamType, videoMimeType, resolutionBucket, decoderName, surfaceType, failureType, failedAt)
  }

  public override suspend fun recordSuccess(
    deviceFingerprint: String,
    deviceModel: String,
    androidSdk: Int,
    streamType: String,
    videoMimeType: String,
    resolutionBucket: String,
    decoderName: String,
    surfaceType: String,
    succeededAt: Long,
  ): Unit = performInTransactionSuspending(__db) {
    super@PlaybackCompatibilityDao_Impl.recordSuccess(deviceFingerprint, deviceModel, androidSdk, streamType, videoMimeType, resolutionBucket, decoderName, surfaceType, succeededAt)
  }

  public override suspend fun getKnownBadCandidates(
    deviceFingerprint: String,
    streamType: String,
    videoMimeType: String,
    resolutionBucket: String,
  ): List<PlaybackCompatibilityRecordEntity> {
    val _sql: String = """
        |
        |        SELECT * FROM playback_compatibility_records
        |        WHERE device_fingerprint = ?
        |          AND stream_type = ?
        |          AND video_mime_type = ?
        |          AND resolution_bucket = ?
        |        ORDER BY failure_count DESC, last_failed_at DESC
        |        
        """.trimMargin()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, deviceFingerprint)
        _argIndex = 2
        _stmt.bindText(_argIndex, streamType)
        _argIndex = 3
        _stmt.bindText(_argIndex, videoMimeType)
        _argIndex = 4
        _stmt.bindText(_argIndex, resolutionBucket)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfDeviceFingerprint: Int = getColumnIndexOrThrow(_stmt, "device_fingerprint")
        val _columnIndexOfDeviceModel: Int = getColumnIndexOrThrow(_stmt, "device_model")
        val _columnIndexOfAndroidSdk: Int = getColumnIndexOrThrow(_stmt, "android_sdk")
        val _columnIndexOfStreamType: Int = getColumnIndexOrThrow(_stmt, "stream_type")
        val _columnIndexOfVideoMimeType: Int = getColumnIndexOrThrow(_stmt, "video_mime_type")
        val _columnIndexOfResolutionBucket: Int = getColumnIndexOrThrow(_stmt, "resolution_bucket")
        val _columnIndexOfDecoderName: Int = getColumnIndexOrThrow(_stmt, "decoder_name")
        val _columnIndexOfSurfaceType: Int = getColumnIndexOrThrow(_stmt, "surface_type")
        val _columnIndexOfFailureType: Int = getColumnIndexOrThrow(_stmt, "failure_type")
        val _columnIndexOfLastFailedAt: Int = getColumnIndexOrThrow(_stmt, "last_failed_at")
        val _columnIndexOfLastSucceededAt: Int = getColumnIndexOrThrow(_stmt, "last_succeeded_at")
        val _columnIndexOfFailureCount: Int = getColumnIndexOrThrow(_stmt, "failure_count")
        val _columnIndexOfSuccessCount: Int = getColumnIndexOrThrow(_stmt, "success_count")
        val _result: MutableList<PlaybackCompatibilityRecordEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: PlaybackCompatibilityRecordEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpDeviceFingerprint: String
          _tmpDeviceFingerprint = _stmt.getText(_columnIndexOfDeviceFingerprint)
          val _tmpDeviceModel: String
          _tmpDeviceModel = _stmt.getText(_columnIndexOfDeviceModel)
          val _tmpAndroidSdk: Int
          _tmpAndroidSdk = _stmt.getLong(_columnIndexOfAndroidSdk).toInt()
          val _tmpStreamType: String
          _tmpStreamType = _stmt.getText(_columnIndexOfStreamType)
          val _tmpVideoMimeType: String
          _tmpVideoMimeType = _stmt.getText(_columnIndexOfVideoMimeType)
          val _tmpResolutionBucket: String
          _tmpResolutionBucket = _stmt.getText(_columnIndexOfResolutionBucket)
          val _tmpDecoderName: String
          _tmpDecoderName = _stmt.getText(_columnIndexOfDecoderName)
          val _tmpSurfaceType: String
          _tmpSurfaceType = _stmt.getText(_columnIndexOfSurfaceType)
          val _tmpFailureType: String
          _tmpFailureType = _stmt.getText(_columnIndexOfFailureType)
          val _tmpLastFailedAt: Long
          _tmpLastFailedAt = _stmt.getLong(_columnIndexOfLastFailedAt)
          val _tmpLastSucceededAt: Long
          _tmpLastSucceededAt = _stmt.getLong(_columnIndexOfLastSucceededAt)
          val _tmpFailureCount: Int
          _tmpFailureCount = _stmt.getLong(_columnIndexOfFailureCount).toInt()
          val _tmpSuccessCount: Int
          _tmpSuccessCount = _stmt.getLong(_columnIndexOfSuccessCount).toInt()
          _item = PlaybackCompatibilityRecordEntity(_tmpId,_tmpDeviceFingerprint,_tmpDeviceModel,_tmpAndroidSdk,_tmpStreamType,_tmpVideoMimeType,_tmpResolutionBucket,_tmpDecoderName,_tmpSurfaceType,_tmpFailureType,_tmpLastFailedAt,_tmpLastSucceededAt,_tmpFailureCount,_tmpSuccessCount)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateFailureRecord(
    deviceFingerprint: String,
    deviceModel: String,
    androidSdk: Int,
    streamType: String,
    videoMimeType: String,
    resolutionBucket: String,
    decoderName: String,
    surfaceType: String,
    failureType: String,
    failedAt: Long,
  ): Int {
    val _sql: String = """
        |
        |        UPDATE playback_compatibility_records
        |        SET device_model = ?,
        |            android_sdk = ?,
        |            failure_type = ?,
        |            last_failed_at = ?,
        |            failure_count = failure_count + 1
        |        WHERE device_fingerprint = ?
        |          AND stream_type = ?
        |          AND video_mime_type = ?
        |          AND resolution_bucket = ?
        |          AND decoder_name = ?
        |          AND surface_type = ?
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, deviceModel)
        _argIndex = 2
        _stmt.bindLong(_argIndex, androidSdk.toLong())
        _argIndex = 3
        _stmt.bindText(_argIndex, failureType)
        _argIndex = 4
        _stmt.bindLong(_argIndex, failedAt)
        _argIndex = 5
        _stmt.bindText(_argIndex, deviceFingerprint)
        _argIndex = 6
        _stmt.bindText(_argIndex, streamType)
        _argIndex = 7
        _stmt.bindText(_argIndex, videoMimeType)
        _argIndex = 8
        _stmt.bindText(_argIndex, resolutionBucket)
        _argIndex = 9
        _stmt.bindText(_argIndex, decoderName)
        _argIndex = 10
        _stmt.bindText(_argIndex, surfaceType)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateSuccessRecord(
    deviceFingerprint: String,
    deviceModel: String,
    androidSdk: Int,
    streamType: String,
    videoMimeType: String,
    resolutionBucket: String,
    decoderName: String,
    surfaceType: String,
    succeededAt: Long,
  ): Int {
    val _sql: String = """
        |
        |        UPDATE playback_compatibility_records
        |        SET device_model = ?,
        |            android_sdk = ?,
        |            last_succeeded_at = ?,
        |            success_count = success_count + 1
        |        WHERE device_fingerprint = ?
        |          AND stream_type = ?
        |          AND video_mime_type = ?
        |          AND resolution_bucket = ?
        |          AND decoder_name = ?
        |          AND surface_type = ?
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, deviceModel)
        _argIndex = 2
        _stmt.bindLong(_argIndex, androidSdk.toLong())
        _argIndex = 3
        _stmt.bindLong(_argIndex, succeededAt)
        _argIndex = 4
        _stmt.bindText(_argIndex, deviceFingerprint)
        _argIndex = 5
        _stmt.bindText(_argIndex, streamType)
        _argIndex = 6
        _stmt.bindText(_argIndex, videoMimeType)
        _argIndex = 7
        _stmt.bindText(_argIndex, resolutionBucket)
        _argIndex = 8
        _stmt.bindText(_argIndex, decoderName)
        _argIndex = 9
        _stmt.bindText(_argIndex, surfaceType)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun deleteOlderThan(olderThanMs: Long): Int {
    val _sql: String = "DELETE FROM playback_compatibility_records WHERE last_failed_at < ? AND last_succeeded_at < ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, olderThanMs)
        _argIndex = 2
        _stmt.bindLong(_argIndex, olderThanMs)
        _stmt.step()
        getTotalChangedRows(_connection)
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun keepMostRecent(maxRecords: Int): Int {
    val _sql: String = """
        |
        |        DELETE FROM playback_compatibility_records
        |        WHERE id NOT IN (
        |            SELECT id FROM playback_compatibility_records
        |            ORDER BY MAX(last_failed_at, last_succeeded_at) DESC
        |            LIMIT ?
        |        )
        |        
        """.trimMargin()
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, maxRecords.toLong())
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
