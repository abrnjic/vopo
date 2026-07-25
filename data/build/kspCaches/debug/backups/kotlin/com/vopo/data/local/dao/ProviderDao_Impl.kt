package com.vopo.`data`.local.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.appendPlaceholders
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performBlocking
import androidx.room.util.performInTransactionSuspending
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.vopo.`data`.local.RoomEnumConverters
import com.vopo.`data`.local.entity.ProviderEntity
import com.vopo.domain.model.ProviderEpgSyncMode
import com.vopo.domain.model.ProviderStatus
import com.vopo.domain.model.ProviderType
import com.vopo.domain.model.ProviderXtreamLiveSyncMode
import com.vopo.domain.model.StalkerAuthMode
import com.vopo.domain.model.StalkerBootstrapRecipe
import com.vopo.domain.model.StalkerCookieMode
import com.vopo.domain.model.StalkerEndpointPreference
import com.vopo.domain.model.StalkerMagPreset
import com.vopo.domain.model.StalkerPlaybackBackendHint
import com.vopo.domain.model.StalkerPortalFingerprint
import com.vopo.domain.model.StalkerPortalProfile
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
public class ProviderDao_Impl(
  __db: RoomDatabase,
) : ProviderDao() {
  private val __db: RoomDatabase

  private val __insertAdapterOfProviderEntity: EntityInsertAdapter<ProviderEntity>

  private val __roomEnumConverters: RoomEnumConverters = RoomEnumConverters()

  private val __updateAdapterOfProviderEntity: EntityDeleteOrUpdateAdapter<ProviderEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfProviderEntity = object : EntityInsertAdapter<ProviderEntity>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `providers` (`id`,`name`,`type`,`server_url`,`username`,`password`,`m3u_url`,`epg_url`,`http_user_agent`,`http_headers`,`stalker_mac_address`,`stalker_device_profile`,`stalker_device_timezone`,`stalker_device_locale`,`stalker_serial_number`,`stalker_device_id`,`stalker_device_id2`,`stalker_signature`,`stalker_advanced_options_json`,`stalker_auth_mode`,`stalker_portal_profile`,`stalker_portal_fingerprint`,`stalker_mag_preset`,`stalker_last_bootstrap_recipe`,`stalker_endpoint_preference`,`stalker_cookie_mode`,`stalker_playback_backend_hint`,`stalker_last_playback_mode`,`stalker_credentials_required`,`stalker_mac_required`,`stalker_uses_temp_links`,`stalker_module_restricted`,`stalker_strict_fingerprint_required`,`stalker_recipe_fallback_used`,`stalker_recipe_rediscovery_attempts`,`is_active`,`max_connections`,`expiration_date`,`api_version`,`allowed_output_formats_json`,`epg_sync_mode`,`xtream_fast_sync_enabled`,`xtream_live_sync_mode`,`m3u_vod_classification_enabled`,`status`,`last_synced_at`,`created_at`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ProviderEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmp: String? = __roomEnumConverters.fromProviderType(entity.type)
        if (_tmp == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmp)
        }
        statement.bindText(4, entity.serverUrl)
        statement.bindText(5, entity.username)
        statement.bindText(6, entity.password)
        statement.bindText(7, entity.m3uUrl)
        statement.bindText(8, entity.epgUrl)
        statement.bindText(9, entity.httpUserAgent)
        statement.bindText(10, entity.httpHeaders)
        statement.bindText(11, entity.stalkerMacAddress)
        statement.bindText(12, entity.stalkerDeviceProfile)
        statement.bindText(13, entity.stalkerDeviceTimezone)
        statement.bindText(14, entity.stalkerDeviceLocale)
        statement.bindText(15, entity.stalkerSerialNumber)
        statement.bindText(16, entity.stalkerDeviceId)
        statement.bindText(17, entity.stalkerDeviceId2)
        statement.bindText(18, entity.stalkerSignature)
        statement.bindText(19, entity.stalkerAdvancedOptionsJson)
        val _tmp_1: String? = __roomEnumConverters.fromStalkerAuthMode(entity.stalkerAuthMode)
        if (_tmp_1 == null) {
          statement.bindNull(20)
        } else {
          statement.bindText(20, _tmp_1)
        }
        val _tmp_2: String? = __roomEnumConverters.fromStalkerPortalProfile(entity.stalkerPortalProfile)
        if (_tmp_2 == null) {
          statement.bindNull(21)
        } else {
          statement.bindText(21, _tmp_2)
        }
        val _tmp_3: String? = __roomEnumConverters.fromStalkerPortalFingerprint(entity.stalkerPortalFingerprint)
        if (_tmp_3 == null) {
          statement.bindNull(22)
        } else {
          statement.bindText(22, _tmp_3)
        }
        val _tmp_4: String? = __roomEnumConverters.fromStalkerMagPreset(entity.stalkerMagPreset)
        if (_tmp_4 == null) {
          statement.bindNull(23)
        } else {
          statement.bindText(23, _tmp_4)
        }
        val _tmp_5: String? = __roomEnumConverters.fromStalkerBootstrapRecipe(entity.stalkerLastBootstrapRecipe)
        if (_tmp_5 == null) {
          statement.bindNull(24)
        } else {
          statement.bindText(24, _tmp_5)
        }
        val _tmp_6: String? = __roomEnumConverters.fromStalkerEndpointPreference(entity.stalkerEndpointPreference)
        if (_tmp_6 == null) {
          statement.bindNull(25)
        } else {
          statement.bindText(25, _tmp_6)
        }
        val _tmp_7: String? = __roomEnumConverters.fromStalkerCookieMode(entity.stalkerCookieMode)
        if (_tmp_7 == null) {
          statement.bindNull(26)
        } else {
          statement.bindText(26, _tmp_7)
        }
        val _tmp_8: String? = __roomEnumConverters.fromStalkerPlaybackBackendHint(entity.stalkerPlaybackBackendHint)
        if (_tmp_8 == null) {
          statement.bindNull(27)
        } else {
          statement.bindText(27, _tmp_8)
        }
        val _tmpStalkerLastPlaybackMode: String? = entity.stalkerLastPlaybackMode
        if (_tmpStalkerLastPlaybackMode == null) {
          statement.bindNull(28)
        } else {
          statement.bindText(28, _tmpStalkerLastPlaybackMode)
        }
        val _tmp_9: Int = if (entity.stalkerCredentialsRequired) 1 else 0
        statement.bindLong(29, _tmp_9.toLong())
        val _tmp_10: Int = if (entity.stalkerMacRequired) 1 else 0
        statement.bindLong(30, _tmp_10.toLong())
        val _tmp_11: Int = if (entity.stalkerUsesTemporaryLinks) 1 else 0
        statement.bindLong(31, _tmp_11.toLong())
        val _tmp_12: Int = if (entity.stalkerModuleRestricted) 1 else 0
        statement.bindLong(32, _tmp_12.toLong())
        val _tmp_13: Int = if (entity.stalkerStrictFingerprintRequired) 1 else 0
        statement.bindLong(33, _tmp_13.toLong())
        val _tmp_14: Int = if (entity.stalkerRecipeFallbackUsed) 1 else 0
        statement.bindLong(34, _tmp_14.toLong())
        statement.bindLong(35, entity.stalkerRecipeRediscoveryAttempts.toLong())
        val _tmp_15: Int = if (entity.isActive) 1 else 0
        statement.bindLong(36, _tmp_15.toLong())
        statement.bindLong(37, entity.maxConnections.toLong())
        val _tmpExpirationDate: Long? = entity.expirationDate
        if (_tmpExpirationDate == null) {
          statement.bindNull(38)
        } else {
          statement.bindLong(38, _tmpExpirationDate)
        }
        val _tmpApiVersion: String? = entity.apiVersion
        if (_tmpApiVersion == null) {
          statement.bindNull(39)
        } else {
          statement.bindText(39, _tmpApiVersion)
        }
        statement.bindText(40, entity.allowedOutputFormatsJson)
        val _tmp_16: String? = __roomEnumConverters.fromProviderEpgSyncMode(entity.epgSyncMode)
        if (_tmp_16 == null) {
          statement.bindNull(41)
        } else {
          statement.bindText(41, _tmp_16)
        }
        val _tmp_17: Int = if (entity.xtreamFastSyncEnabled) 1 else 0
        statement.bindLong(42, _tmp_17.toLong())
        val _tmp_18: String? = __roomEnumConverters.fromProviderXtreamLiveSyncMode(entity.xtreamLiveSyncMode)
        if (_tmp_18 == null) {
          statement.bindNull(43)
        } else {
          statement.bindText(43, _tmp_18)
        }
        val _tmp_19: Int = if (entity.m3uVodClassificationEnabled) 1 else 0
        statement.bindLong(44, _tmp_19.toLong())
        val _tmp_20: String? = __roomEnumConverters.fromProviderStatus(entity.status)
        if (_tmp_20 == null) {
          statement.bindNull(45)
        } else {
          statement.bindText(45, _tmp_20)
        }
        statement.bindLong(46, entity.lastSyncedAt)
        statement.bindLong(47, entity.createdAt)
      }
    }
    this.__updateAdapterOfProviderEntity = object : EntityDeleteOrUpdateAdapter<ProviderEntity>() {
      protected override fun createQuery(): String = "UPDATE OR ABORT `providers` SET `id` = ?,`name` = ?,`type` = ?,`server_url` = ?,`username` = ?,`password` = ?,`m3u_url` = ?,`epg_url` = ?,`http_user_agent` = ?,`http_headers` = ?,`stalker_mac_address` = ?,`stalker_device_profile` = ?,`stalker_device_timezone` = ?,`stalker_device_locale` = ?,`stalker_serial_number` = ?,`stalker_device_id` = ?,`stalker_device_id2` = ?,`stalker_signature` = ?,`stalker_advanced_options_json` = ?,`stalker_auth_mode` = ?,`stalker_portal_profile` = ?,`stalker_portal_fingerprint` = ?,`stalker_mag_preset` = ?,`stalker_last_bootstrap_recipe` = ?,`stalker_endpoint_preference` = ?,`stalker_cookie_mode` = ?,`stalker_playback_backend_hint` = ?,`stalker_last_playback_mode` = ?,`stalker_credentials_required` = ?,`stalker_mac_required` = ?,`stalker_uses_temp_links` = ?,`stalker_module_restricted` = ?,`stalker_strict_fingerprint_required` = ?,`stalker_recipe_fallback_used` = ?,`stalker_recipe_rediscovery_attempts` = ?,`is_active` = ?,`max_connections` = ?,`expiration_date` = ?,`api_version` = ?,`allowed_output_formats_json` = ?,`epg_sync_mode` = ?,`xtream_fast_sync_enabled` = ?,`xtream_live_sync_mode` = ?,`m3u_vod_classification_enabled` = ?,`status` = ?,`last_synced_at` = ?,`created_at` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ProviderEntity) {
        statement.bindLong(1, entity.id)
        statement.bindText(2, entity.name)
        val _tmp: String? = __roomEnumConverters.fromProviderType(entity.type)
        if (_tmp == null) {
          statement.bindNull(3)
        } else {
          statement.bindText(3, _tmp)
        }
        statement.bindText(4, entity.serverUrl)
        statement.bindText(5, entity.username)
        statement.bindText(6, entity.password)
        statement.bindText(7, entity.m3uUrl)
        statement.bindText(8, entity.epgUrl)
        statement.bindText(9, entity.httpUserAgent)
        statement.bindText(10, entity.httpHeaders)
        statement.bindText(11, entity.stalkerMacAddress)
        statement.bindText(12, entity.stalkerDeviceProfile)
        statement.bindText(13, entity.stalkerDeviceTimezone)
        statement.bindText(14, entity.stalkerDeviceLocale)
        statement.bindText(15, entity.stalkerSerialNumber)
        statement.bindText(16, entity.stalkerDeviceId)
        statement.bindText(17, entity.stalkerDeviceId2)
        statement.bindText(18, entity.stalkerSignature)
        statement.bindText(19, entity.stalkerAdvancedOptionsJson)
        val _tmp_1: String? = __roomEnumConverters.fromStalkerAuthMode(entity.stalkerAuthMode)
        if (_tmp_1 == null) {
          statement.bindNull(20)
        } else {
          statement.bindText(20, _tmp_1)
        }
        val _tmp_2: String? = __roomEnumConverters.fromStalkerPortalProfile(entity.stalkerPortalProfile)
        if (_tmp_2 == null) {
          statement.bindNull(21)
        } else {
          statement.bindText(21, _tmp_2)
        }
        val _tmp_3: String? = __roomEnumConverters.fromStalkerPortalFingerprint(entity.stalkerPortalFingerprint)
        if (_tmp_3 == null) {
          statement.bindNull(22)
        } else {
          statement.bindText(22, _tmp_3)
        }
        val _tmp_4: String? = __roomEnumConverters.fromStalkerMagPreset(entity.stalkerMagPreset)
        if (_tmp_4 == null) {
          statement.bindNull(23)
        } else {
          statement.bindText(23, _tmp_4)
        }
        val _tmp_5: String? = __roomEnumConverters.fromStalkerBootstrapRecipe(entity.stalkerLastBootstrapRecipe)
        if (_tmp_5 == null) {
          statement.bindNull(24)
        } else {
          statement.bindText(24, _tmp_5)
        }
        val _tmp_6: String? = __roomEnumConverters.fromStalkerEndpointPreference(entity.stalkerEndpointPreference)
        if (_tmp_6 == null) {
          statement.bindNull(25)
        } else {
          statement.bindText(25, _tmp_6)
        }
        val _tmp_7: String? = __roomEnumConverters.fromStalkerCookieMode(entity.stalkerCookieMode)
        if (_tmp_7 == null) {
          statement.bindNull(26)
        } else {
          statement.bindText(26, _tmp_7)
        }
        val _tmp_8: String? = __roomEnumConverters.fromStalkerPlaybackBackendHint(entity.stalkerPlaybackBackendHint)
        if (_tmp_8 == null) {
          statement.bindNull(27)
        } else {
          statement.bindText(27, _tmp_8)
        }
        val _tmpStalkerLastPlaybackMode: String? = entity.stalkerLastPlaybackMode
        if (_tmpStalkerLastPlaybackMode == null) {
          statement.bindNull(28)
        } else {
          statement.bindText(28, _tmpStalkerLastPlaybackMode)
        }
        val _tmp_9: Int = if (entity.stalkerCredentialsRequired) 1 else 0
        statement.bindLong(29, _tmp_9.toLong())
        val _tmp_10: Int = if (entity.stalkerMacRequired) 1 else 0
        statement.bindLong(30, _tmp_10.toLong())
        val _tmp_11: Int = if (entity.stalkerUsesTemporaryLinks) 1 else 0
        statement.bindLong(31, _tmp_11.toLong())
        val _tmp_12: Int = if (entity.stalkerModuleRestricted) 1 else 0
        statement.bindLong(32, _tmp_12.toLong())
        val _tmp_13: Int = if (entity.stalkerStrictFingerprintRequired) 1 else 0
        statement.bindLong(33, _tmp_13.toLong())
        val _tmp_14: Int = if (entity.stalkerRecipeFallbackUsed) 1 else 0
        statement.bindLong(34, _tmp_14.toLong())
        statement.bindLong(35, entity.stalkerRecipeRediscoveryAttempts.toLong())
        val _tmp_15: Int = if (entity.isActive) 1 else 0
        statement.bindLong(36, _tmp_15.toLong())
        statement.bindLong(37, entity.maxConnections.toLong())
        val _tmpExpirationDate: Long? = entity.expirationDate
        if (_tmpExpirationDate == null) {
          statement.bindNull(38)
        } else {
          statement.bindLong(38, _tmpExpirationDate)
        }
        val _tmpApiVersion: String? = entity.apiVersion
        if (_tmpApiVersion == null) {
          statement.bindNull(39)
        } else {
          statement.bindText(39, _tmpApiVersion)
        }
        statement.bindText(40, entity.allowedOutputFormatsJson)
        val _tmp_16: String? = __roomEnumConverters.fromProviderEpgSyncMode(entity.epgSyncMode)
        if (_tmp_16 == null) {
          statement.bindNull(41)
        } else {
          statement.bindText(41, _tmp_16)
        }
        val _tmp_17: Int = if (entity.xtreamFastSyncEnabled) 1 else 0
        statement.bindLong(42, _tmp_17.toLong())
        val _tmp_18: String? = __roomEnumConverters.fromProviderXtreamLiveSyncMode(entity.xtreamLiveSyncMode)
        if (_tmp_18 == null) {
          statement.bindNull(43)
        } else {
          statement.bindText(43, _tmp_18)
        }
        val _tmp_19: Int = if (entity.m3uVodClassificationEnabled) 1 else 0
        statement.bindLong(44, _tmp_19.toLong())
        val _tmp_20: String? = __roomEnumConverters.fromProviderStatus(entity.status)
        if (_tmp_20 == null) {
          statement.bindNull(45)
        } else {
          statement.bindText(45, _tmp_20)
        }
        statement.bindLong(46, entity.lastSyncedAt)
        statement.bindLong(47, entity.createdAt)
        statement.bindLong(48, entity.id)
      }
    }
  }

  protected override suspend fun insertDirect(provider: ProviderEntity): Long = performSuspending(__db, false, true) { _connection ->
    val _result: Long = __insertAdapterOfProviderEntity.insertAndReturnId(_connection, provider)
    _result
  }

  protected override suspend fun updateDirect(provider: ProviderEntity): Unit = performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfProviderEntity.handle(_connection, provider)
  }

  public override suspend fun insert(provider: ProviderEntity): Long = performInTransactionSuspending(__db) {
    super@ProviderDao_Impl.insert(provider)
  }

  public override suspend fun update(provider: ProviderEntity): Unit = performInTransactionSuspending(__db) {
    super@ProviderDao_Impl.update(provider)
  }

  public override suspend fun setActive(id: Long): Unit = performInTransactionSuspending(__db) {
    super@ProviderDao_Impl.setActive(id)
  }

  public override fun getAll(): Flow<List<ProviderEntity>> {
    val _sql: String = "SELECT * FROM providers ORDER BY created_at DESC"
    return createFlow(__db, false, arrayOf("providers")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfServerUrl: Int = getColumnIndexOrThrow(_stmt, "server_url")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfM3uUrl: Int = getColumnIndexOrThrow(_stmt, "m3u_url")
        val _columnIndexOfEpgUrl: Int = getColumnIndexOrThrow(_stmt, "epg_url")
        val _columnIndexOfHttpUserAgent: Int = getColumnIndexOrThrow(_stmt, "http_user_agent")
        val _columnIndexOfHttpHeaders: Int = getColumnIndexOrThrow(_stmt, "http_headers")
        val _columnIndexOfStalkerMacAddress: Int = getColumnIndexOrThrow(_stmt, "stalker_mac_address")
        val _columnIndexOfStalkerDeviceProfile: Int = getColumnIndexOrThrow(_stmt, "stalker_device_profile")
        val _columnIndexOfStalkerDeviceTimezone: Int = getColumnIndexOrThrow(_stmt, "stalker_device_timezone")
        val _columnIndexOfStalkerDeviceLocale: Int = getColumnIndexOrThrow(_stmt, "stalker_device_locale")
        val _columnIndexOfStalkerSerialNumber: Int = getColumnIndexOrThrow(_stmt, "stalker_serial_number")
        val _columnIndexOfStalkerDeviceId: Int = getColumnIndexOrThrow(_stmt, "stalker_device_id")
        val _columnIndexOfStalkerDeviceId2: Int = getColumnIndexOrThrow(_stmt, "stalker_device_id2")
        val _columnIndexOfStalkerSignature: Int = getColumnIndexOrThrow(_stmt, "stalker_signature")
        val _columnIndexOfStalkerAdvancedOptionsJson: Int = getColumnIndexOrThrow(_stmt, "stalker_advanced_options_json")
        val _columnIndexOfStalkerAuthMode: Int = getColumnIndexOrThrow(_stmt, "stalker_auth_mode")
        val _columnIndexOfStalkerPortalProfile: Int = getColumnIndexOrThrow(_stmt, "stalker_portal_profile")
        val _columnIndexOfStalkerPortalFingerprint: Int = getColumnIndexOrThrow(_stmt, "stalker_portal_fingerprint")
        val _columnIndexOfStalkerMagPreset: Int = getColumnIndexOrThrow(_stmt, "stalker_mag_preset")
        val _columnIndexOfStalkerLastBootstrapRecipe: Int = getColumnIndexOrThrow(_stmt, "stalker_last_bootstrap_recipe")
        val _columnIndexOfStalkerEndpointPreference: Int = getColumnIndexOrThrow(_stmt, "stalker_endpoint_preference")
        val _columnIndexOfStalkerCookieMode: Int = getColumnIndexOrThrow(_stmt, "stalker_cookie_mode")
        val _columnIndexOfStalkerPlaybackBackendHint: Int = getColumnIndexOrThrow(_stmt, "stalker_playback_backend_hint")
        val _columnIndexOfStalkerLastPlaybackMode: Int = getColumnIndexOrThrow(_stmt, "stalker_last_playback_mode")
        val _columnIndexOfStalkerCredentialsRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_credentials_required")
        val _columnIndexOfStalkerMacRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_mac_required")
        val _columnIndexOfStalkerUsesTemporaryLinks: Int = getColumnIndexOrThrow(_stmt, "stalker_uses_temp_links")
        val _columnIndexOfStalkerModuleRestricted: Int = getColumnIndexOrThrow(_stmt, "stalker_module_restricted")
        val _columnIndexOfStalkerStrictFingerprintRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_strict_fingerprint_required")
        val _columnIndexOfStalkerRecipeFallbackUsed: Int = getColumnIndexOrThrow(_stmt, "stalker_recipe_fallback_used")
        val _columnIndexOfStalkerRecipeRediscoveryAttempts: Int = getColumnIndexOrThrow(_stmt, "stalker_recipe_rediscovery_attempts")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "is_active")
        val _columnIndexOfMaxConnections: Int = getColumnIndexOrThrow(_stmt, "max_connections")
        val _columnIndexOfExpirationDate: Int = getColumnIndexOrThrow(_stmt, "expiration_date")
        val _columnIndexOfApiVersion: Int = getColumnIndexOrThrow(_stmt, "api_version")
        val _columnIndexOfAllowedOutputFormatsJson: Int = getColumnIndexOrThrow(_stmt, "allowed_output_formats_json")
        val _columnIndexOfEpgSyncMode: Int = getColumnIndexOrThrow(_stmt, "epg_sync_mode")
        val _columnIndexOfXtreamFastSyncEnabled: Int = getColumnIndexOrThrow(_stmt, "xtream_fast_sync_enabled")
        val _columnIndexOfXtreamLiveSyncMode: Int = getColumnIndexOrThrow(_stmt, "xtream_live_sync_mode")
        val _columnIndexOfM3uVodClassificationEnabled: Int = getColumnIndexOrThrow(_stmt, "m3u_vod_classification_enabled")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfLastSyncedAt: Int = getColumnIndexOrThrow(_stmt, "last_synced_at")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _result: MutableList<ProviderEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProviderEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpType: ProviderType
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfType)
          }
          val _tmp_1: ProviderType? = __roomEnumConverters.toProviderType(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderType', but it was NULL.")
          } else {
            _tmpType = _tmp_1
          }
          val _tmpServerUrl: String
          _tmpServerUrl = _stmt.getText(_columnIndexOfServerUrl)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpM3uUrl: String
          _tmpM3uUrl = _stmt.getText(_columnIndexOfM3uUrl)
          val _tmpEpgUrl: String
          _tmpEpgUrl = _stmt.getText(_columnIndexOfEpgUrl)
          val _tmpHttpUserAgent: String
          _tmpHttpUserAgent = _stmt.getText(_columnIndexOfHttpUserAgent)
          val _tmpHttpHeaders: String
          _tmpHttpHeaders = _stmt.getText(_columnIndexOfHttpHeaders)
          val _tmpStalkerMacAddress: String
          _tmpStalkerMacAddress = _stmt.getText(_columnIndexOfStalkerMacAddress)
          val _tmpStalkerDeviceProfile: String
          _tmpStalkerDeviceProfile = _stmt.getText(_columnIndexOfStalkerDeviceProfile)
          val _tmpStalkerDeviceTimezone: String
          _tmpStalkerDeviceTimezone = _stmt.getText(_columnIndexOfStalkerDeviceTimezone)
          val _tmpStalkerDeviceLocale: String
          _tmpStalkerDeviceLocale = _stmt.getText(_columnIndexOfStalkerDeviceLocale)
          val _tmpStalkerSerialNumber: String
          _tmpStalkerSerialNumber = _stmt.getText(_columnIndexOfStalkerSerialNumber)
          val _tmpStalkerDeviceId: String
          _tmpStalkerDeviceId = _stmt.getText(_columnIndexOfStalkerDeviceId)
          val _tmpStalkerDeviceId2: String
          _tmpStalkerDeviceId2 = _stmt.getText(_columnIndexOfStalkerDeviceId2)
          val _tmpStalkerSignature: String
          _tmpStalkerSignature = _stmt.getText(_columnIndexOfStalkerSignature)
          val _tmpStalkerAdvancedOptionsJson: String
          _tmpStalkerAdvancedOptionsJson = _stmt.getText(_columnIndexOfStalkerAdvancedOptionsJson)
          val _tmpStalkerAuthMode: StalkerAuthMode
          val _tmp_2: String?
          if (_stmt.isNull(_columnIndexOfStalkerAuthMode)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfStalkerAuthMode)
          }
          val _tmp_3: StalkerAuthMode? = __roomEnumConverters.toStalkerAuthMode(_tmp_2)
          if (_tmp_3 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerAuthMode', but it was NULL.")
          } else {
            _tmpStalkerAuthMode = _tmp_3
          }
          val _tmpStalkerPortalProfile: StalkerPortalProfile
          val _tmp_4: String?
          if (_stmt.isNull(_columnIndexOfStalkerPortalProfile)) {
            _tmp_4 = null
          } else {
            _tmp_4 = _stmt.getText(_columnIndexOfStalkerPortalProfile)
          }
          val _tmp_5: StalkerPortalProfile? = __roomEnumConverters.toStalkerPortalProfile(_tmp_4)
          if (_tmp_5 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPortalProfile', but it was NULL.")
          } else {
            _tmpStalkerPortalProfile = _tmp_5
          }
          val _tmpStalkerPortalFingerprint: StalkerPortalFingerprint
          val _tmp_6: String?
          if (_stmt.isNull(_columnIndexOfStalkerPortalFingerprint)) {
            _tmp_6 = null
          } else {
            _tmp_6 = _stmt.getText(_columnIndexOfStalkerPortalFingerprint)
          }
          val _tmp_7: StalkerPortalFingerprint? = __roomEnumConverters.toStalkerPortalFingerprint(_tmp_6)
          if (_tmp_7 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPortalFingerprint', but it was NULL.")
          } else {
            _tmpStalkerPortalFingerprint = _tmp_7
          }
          val _tmpStalkerMagPreset: StalkerMagPreset
          val _tmp_8: String?
          if (_stmt.isNull(_columnIndexOfStalkerMagPreset)) {
            _tmp_8 = null
          } else {
            _tmp_8 = _stmt.getText(_columnIndexOfStalkerMagPreset)
          }
          val _tmp_9: StalkerMagPreset? = __roomEnumConverters.toStalkerMagPreset(_tmp_8)
          if (_tmp_9 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerMagPreset', but it was NULL.")
          } else {
            _tmpStalkerMagPreset = _tmp_9
          }
          val _tmpStalkerLastBootstrapRecipe: StalkerBootstrapRecipe
          val _tmp_10: String?
          if (_stmt.isNull(_columnIndexOfStalkerLastBootstrapRecipe)) {
            _tmp_10 = null
          } else {
            _tmp_10 = _stmt.getText(_columnIndexOfStalkerLastBootstrapRecipe)
          }
          val _tmp_11: StalkerBootstrapRecipe? = __roomEnumConverters.toStalkerBootstrapRecipe(_tmp_10)
          if (_tmp_11 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerBootstrapRecipe', but it was NULL.")
          } else {
            _tmpStalkerLastBootstrapRecipe = _tmp_11
          }
          val _tmpStalkerEndpointPreference: StalkerEndpointPreference
          val _tmp_12: String?
          if (_stmt.isNull(_columnIndexOfStalkerEndpointPreference)) {
            _tmp_12 = null
          } else {
            _tmp_12 = _stmt.getText(_columnIndexOfStalkerEndpointPreference)
          }
          val _tmp_13: StalkerEndpointPreference? = __roomEnumConverters.toStalkerEndpointPreference(_tmp_12)
          if (_tmp_13 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerEndpointPreference', but it was NULL.")
          } else {
            _tmpStalkerEndpointPreference = _tmp_13
          }
          val _tmpStalkerCookieMode: StalkerCookieMode
          val _tmp_14: String?
          if (_stmt.isNull(_columnIndexOfStalkerCookieMode)) {
            _tmp_14 = null
          } else {
            _tmp_14 = _stmt.getText(_columnIndexOfStalkerCookieMode)
          }
          val _tmp_15: StalkerCookieMode? = __roomEnumConverters.toStalkerCookieMode(_tmp_14)
          if (_tmp_15 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerCookieMode', but it was NULL.")
          } else {
            _tmpStalkerCookieMode = _tmp_15
          }
          val _tmpStalkerPlaybackBackendHint: StalkerPlaybackBackendHint
          val _tmp_16: String?
          if (_stmt.isNull(_columnIndexOfStalkerPlaybackBackendHint)) {
            _tmp_16 = null
          } else {
            _tmp_16 = _stmt.getText(_columnIndexOfStalkerPlaybackBackendHint)
          }
          val _tmp_17: StalkerPlaybackBackendHint? = __roomEnumConverters.toStalkerPlaybackBackendHint(_tmp_16)
          if (_tmp_17 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPlaybackBackendHint', but it was NULL.")
          } else {
            _tmpStalkerPlaybackBackendHint = _tmp_17
          }
          val _tmpStalkerLastPlaybackMode: String?
          if (_stmt.isNull(_columnIndexOfStalkerLastPlaybackMode)) {
            _tmpStalkerLastPlaybackMode = null
          } else {
            _tmpStalkerLastPlaybackMode = _stmt.getText(_columnIndexOfStalkerLastPlaybackMode)
          }
          val _tmpStalkerCredentialsRequired: Boolean
          val _tmp_18: Int
          _tmp_18 = _stmt.getLong(_columnIndexOfStalkerCredentialsRequired).toInt()
          _tmpStalkerCredentialsRequired = _tmp_18 != 0
          val _tmpStalkerMacRequired: Boolean
          val _tmp_19: Int
          _tmp_19 = _stmt.getLong(_columnIndexOfStalkerMacRequired).toInt()
          _tmpStalkerMacRequired = _tmp_19 != 0
          val _tmpStalkerUsesTemporaryLinks: Boolean
          val _tmp_20: Int
          _tmp_20 = _stmt.getLong(_columnIndexOfStalkerUsesTemporaryLinks).toInt()
          _tmpStalkerUsesTemporaryLinks = _tmp_20 != 0
          val _tmpStalkerModuleRestricted: Boolean
          val _tmp_21: Int
          _tmp_21 = _stmt.getLong(_columnIndexOfStalkerModuleRestricted).toInt()
          _tmpStalkerModuleRestricted = _tmp_21 != 0
          val _tmpStalkerStrictFingerprintRequired: Boolean
          val _tmp_22: Int
          _tmp_22 = _stmt.getLong(_columnIndexOfStalkerStrictFingerprintRequired).toInt()
          _tmpStalkerStrictFingerprintRequired = _tmp_22 != 0
          val _tmpStalkerRecipeFallbackUsed: Boolean
          val _tmp_23: Int
          _tmp_23 = _stmt.getLong(_columnIndexOfStalkerRecipeFallbackUsed).toInt()
          _tmpStalkerRecipeFallbackUsed = _tmp_23 != 0
          val _tmpStalkerRecipeRediscoveryAttempts: Int
          _tmpStalkerRecipeRediscoveryAttempts = _stmt.getLong(_columnIndexOfStalkerRecipeRediscoveryAttempts).toInt()
          val _tmpIsActive: Boolean
          val _tmp_24: Int
          _tmp_24 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_24 != 0
          val _tmpMaxConnections: Int
          _tmpMaxConnections = _stmt.getLong(_columnIndexOfMaxConnections).toInt()
          val _tmpExpirationDate: Long?
          if (_stmt.isNull(_columnIndexOfExpirationDate)) {
            _tmpExpirationDate = null
          } else {
            _tmpExpirationDate = _stmt.getLong(_columnIndexOfExpirationDate)
          }
          val _tmpApiVersion: String?
          if (_stmt.isNull(_columnIndexOfApiVersion)) {
            _tmpApiVersion = null
          } else {
            _tmpApiVersion = _stmt.getText(_columnIndexOfApiVersion)
          }
          val _tmpAllowedOutputFormatsJson: String
          _tmpAllowedOutputFormatsJson = _stmt.getText(_columnIndexOfAllowedOutputFormatsJson)
          val _tmpEpgSyncMode: ProviderEpgSyncMode
          val _tmp_25: String?
          if (_stmt.isNull(_columnIndexOfEpgSyncMode)) {
            _tmp_25 = null
          } else {
            _tmp_25 = _stmt.getText(_columnIndexOfEpgSyncMode)
          }
          val _tmp_26: ProviderEpgSyncMode? = __roomEnumConverters.toProviderEpgSyncMode(_tmp_25)
          if (_tmp_26 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderEpgSyncMode', but it was NULL.")
          } else {
            _tmpEpgSyncMode = _tmp_26
          }
          val _tmpXtreamFastSyncEnabled: Boolean
          val _tmp_27: Int
          _tmp_27 = _stmt.getLong(_columnIndexOfXtreamFastSyncEnabled).toInt()
          _tmpXtreamFastSyncEnabled = _tmp_27 != 0
          val _tmpXtreamLiveSyncMode: ProviderXtreamLiveSyncMode
          val _tmp_28: String?
          if (_stmt.isNull(_columnIndexOfXtreamLiveSyncMode)) {
            _tmp_28 = null
          } else {
            _tmp_28 = _stmt.getText(_columnIndexOfXtreamLiveSyncMode)
          }
          val _tmp_29: ProviderXtreamLiveSyncMode? = __roomEnumConverters.toProviderXtreamLiveSyncMode(_tmp_28)
          if (_tmp_29 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderXtreamLiveSyncMode', but it was NULL.")
          } else {
            _tmpXtreamLiveSyncMode = _tmp_29
          }
          val _tmpM3uVodClassificationEnabled: Boolean
          val _tmp_30: Int
          _tmp_30 = _stmt.getLong(_columnIndexOfM3uVodClassificationEnabled).toInt()
          _tmpM3uVodClassificationEnabled = _tmp_30 != 0
          val _tmpStatus: ProviderStatus
          val _tmp_31: String?
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmp_31 = null
          } else {
            _tmp_31 = _stmt.getText(_columnIndexOfStatus)
          }
          val _tmp_32: ProviderStatus? = __roomEnumConverters.toProviderStatus(_tmp_31)
          if (_tmp_32 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderStatus', but it was NULL.")
          } else {
            _tmpStatus = _tmp_32
          }
          val _tmpLastSyncedAt: Long
          _tmpLastSyncedAt = _stmt.getLong(_columnIndexOfLastSyncedAt)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item = ProviderEntity(_tmpId,_tmpName,_tmpType,_tmpServerUrl,_tmpUsername,_tmpPassword,_tmpM3uUrl,_tmpEpgUrl,_tmpHttpUserAgent,_tmpHttpHeaders,_tmpStalkerMacAddress,_tmpStalkerDeviceProfile,_tmpStalkerDeviceTimezone,_tmpStalkerDeviceLocale,_tmpStalkerSerialNumber,_tmpStalkerDeviceId,_tmpStalkerDeviceId2,_tmpStalkerSignature,_tmpStalkerAdvancedOptionsJson,_tmpStalkerAuthMode,_tmpStalkerPortalProfile,_tmpStalkerPortalFingerprint,_tmpStalkerMagPreset,_tmpStalkerLastBootstrapRecipe,_tmpStalkerEndpointPreference,_tmpStalkerCookieMode,_tmpStalkerPlaybackBackendHint,_tmpStalkerLastPlaybackMode,_tmpStalkerCredentialsRequired,_tmpStalkerMacRequired,_tmpStalkerUsesTemporaryLinks,_tmpStalkerModuleRestricted,_tmpStalkerStrictFingerprintRequired,_tmpStalkerRecipeFallbackUsed,_tmpStalkerRecipeRediscoveryAttempts,_tmpIsActive,_tmpMaxConnections,_tmpExpirationDate,_tmpApiVersion,_tmpAllowedOutputFormatsJson,_tmpEpgSyncMode,_tmpXtreamFastSyncEnabled,_tmpXtreamLiveSyncMode,_tmpM3uVodClassificationEnabled,_tmpStatus,_tmpLastSyncedAt,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAllSync(): List<ProviderEntity> {
    val _sql: String = "SELECT * FROM providers ORDER BY created_at DESC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfServerUrl: Int = getColumnIndexOrThrow(_stmt, "server_url")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfM3uUrl: Int = getColumnIndexOrThrow(_stmt, "m3u_url")
        val _columnIndexOfEpgUrl: Int = getColumnIndexOrThrow(_stmt, "epg_url")
        val _columnIndexOfHttpUserAgent: Int = getColumnIndexOrThrow(_stmt, "http_user_agent")
        val _columnIndexOfHttpHeaders: Int = getColumnIndexOrThrow(_stmt, "http_headers")
        val _columnIndexOfStalkerMacAddress: Int = getColumnIndexOrThrow(_stmt, "stalker_mac_address")
        val _columnIndexOfStalkerDeviceProfile: Int = getColumnIndexOrThrow(_stmt, "stalker_device_profile")
        val _columnIndexOfStalkerDeviceTimezone: Int = getColumnIndexOrThrow(_stmt, "stalker_device_timezone")
        val _columnIndexOfStalkerDeviceLocale: Int = getColumnIndexOrThrow(_stmt, "stalker_device_locale")
        val _columnIndexOfStalkerSerialNumber: Int = getColumnIndexOrThrow(_stmt, "stalker_serial_number")
        val _columnIndexOfStalkerDeviceId: Int = getColumnIndexOrThrow(_stmt, "stalker_device_id")
        val _columnIndexOfStalkerDeviceId2: Int = getColumnIndexOrThrow(_stmt, "stalker_device_id2")
        val _columnIndexOfStalkerSignature: Int = getColumnIndexOrThrow(_stmt, "stalker_signature")
        val _columnIndexOfStalkerAdvancedOptionsJson: Int = getColumnIndexOrThrow(_stmt, "stalker_advanced_options_json")
        val _columnIndexOfStalkerAuthMode: Int = getColumnIndexOrThrow(_stmt, "stalker_auth_mode")
        val _columnIndexOfStalkerPortalProfile: Int = getColumnIndexOrThrow(_stmt, "stalker_portal_profile")
        val _columnIndexOfStalkerPortalFingerprint: Int = getColumnIndexOrThrow(_stmt, "stalker_portal_fingerprint")
        val _columnIndexOfStalkerMagPreset: Int = getColumnIndexOrThrow(_stmt, "stalker_mag_preset")
        val _columnIndexOfStalkerLastBootstrapRecipe: Int = getColumnIndexOrThrow(_stmt, "stalker_last_bootstrap_recipe")
        val _columnIndexOfStalkerEndpointPreference: Int = getColumnIndexOrThrow(_stmt, "stalker_endpoint_preference")
        val _columnIndexOfStalkerCookieMode: Int = getColumnIndexOrThrow(_stmt, "stalker_cookie_mode")
        val _columnIndexOfStalkerPlaybackBackendHint: Int = getColumnIndexOrThrow(_stmt, "stalker_playback_backend_hint")
        val _columnIndexOfStalkerLastPlaybackMode: Int = getColumnIndexOrThrow(_stmt, "stalker_last_playback_mode")
        val _columnIndexOfStalkerCredentialsRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_credentials_required")
        val _columnIndexOfStalkerMacRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_mac_required")
        val _columnIndexOfStalkerUsesTemporaryLinks: Int = getColumnIndexOrThrow(_stmt, "stalker_uses_temp_links")
        val _columnIndexOfStalkerModuleRestricted: Int = getColumnIndexOrThrow(_stmt, "stalker_module_restricted")
        val _columnIndexOfStalkerStrictFingerprintRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_strict_fingerprint_required")
        val _columnIndexOfStalkerRecipeFallbackUsed: Int = getColumnIndexOrThrow(_stmt, "stalker_recipe_fallback_used")
        val _columnIndexOfStalkerRecipeRediscoveryAttempts: Int = getColumnIndexOrThrow(_stmt, "stalker_recipe_rediscovery_attempts")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "is_active")
        val _columnIndexOfMaxConnections: Int = getColumnIndexOrThrow(_stmt, "max_connections")
        val _columnIndexOfExpirationDate: Int = getColumnIndexOrThrow(_stmt, "expiration_date")
        val _columnIndexOfApiVersion: Int = getColumnIndexOrThrow(_stmt, "api_version")
        val _columnIndexOfAllowedOutputFormatsJson: Int = getColumnIndexOrThrow(_stmt, "allowed_output_formats_json")
        val _columnIndexOfEpgSyncMode: Int = getColumnIndexOrThrow(_stmt, "epg_sync_mode")
        val _columnIndexOfXtreamFastSyncEnabled: Int = getColumnIndexOrThrow(_stmt, "xtream_fast_sync_enabled")
        val _columnIndexOfXtreamLiveSyncMode: Int = getColumnIndexOrThrow(_stmt, "xtream_live_sync_mode")
        val _columnIndexOfM3uVodClassificationEnabled: Int = getColumnIndexOrThrow(_stmt, "m3u_vod_classification_enabled")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfLastSyncedAt: Int = getColumnIndexOrThrow(_stmt, "last_synced_at")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _result: MutableList<ProviderEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProviderEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpType: ProviderType
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfType)
          }
          val _tmp_1: ProviderType? = __roomEnumConverters.toProviderType(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderType', but it was NULL.")
          } else {
            _tmpType = _tmp_1
          }
          val _tmpServerUrl: String
          _tmpServerUrl = _stmt.getText(_columnIndexOfServerUrl)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpM3uUrl: String
          _tmpM3uUrl = _stmt.getText(_columnIndexOfM3uUrl)
          val _tmpEpgUrl: String
          _tmpEpgUrl = _stmt.getText(_columnIndexOfEpgUrl)
          val _tmpHttpUserAgent: String
          _tmpHttpUserAgent = _stmt.getText(_columnIndexOfHttpUserAgent)
          val _tmpHttpHeaders: String
          _tmpHttpHeaders = _stmt.getText(_columnIndexOfHttpHeaders)
          val _tmpStalkerMacAddress: String
          _tmpStalkerMacAddress = _stmt.getText(_columnIndexOfStalkerMacAddress)
          val _tmpStalkerDeviceProfile: String
          _tmpStalkerDeviceProfile = _stmt.getText(_columnIndexOfStalkerDeviceProfile)
          val _tmpStalkerDeviceTimezone: String
          _tmpStalkerDeviceTimezone = _stmt.getText(_columnIndexOfStalkerDeviceTimezone)
          val _tmpStalkerDeviceLocale: String
          _tmpStalkerDeviceLocale = _stmt.getText(_columnIndexOfStalkerDeviceLocale)
          val _tmpStalkerSerialNumber: String
          _tmpStalkerSerialNumber = _stmt.getText(_columnIndexOfStalkerSerialNumber)
          val _tmpStalkerDeviceId: String
          _tmpStalkerDeviceId = _stmt.getText(_columnIndexOfStalkerDeviceId)
          val _tmpStalkerDeviceId2: String
          _tmpStalkerDeviceId2 = _stmt.getText(_columnIndexOfStalkerDeviceId2)
          val _tmpStalkerSignature: String
          _tmpStalkerSignature = _stmt.getText(_columnIndexOfStalkerSignature)
          val _tmpStalkerAdvancedOptionsJson: String
          _tmpStalkerAdvancedOptionsJson = _stmt.getText(_columnIndexOfStalkerAdvancedOptionsJson)
          val _tmpStalkerAuthMode: StalkerAuthMode
          val _tmp_2: String?
          if (_stmt.isNull(_columnIndexOfStalkerAuthMode)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfStalkerAuthMode)
          }
          val _tmp_3: StalkerAuthMode? = __roomEnumConverters.toStalkerAuthMode(_tmp_2)
          if (_tmp_3 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerAuthMode', but it was NULL.")
          } else {
            _tmpStalkerAuthMode = _tmp_3
          }
          val _tmpStalkerPortalProfile: StalkerPortalProfile
          val _tmp_4: String?
          if (_stmt.isNull(_columnIndexOfStalkerPortalProfile)) {
            _tmp_4 = null
          } else {
            _tmp_4 = _stmt.getText(_columnIndexOfStalkerPortalProfile)
          }
          val _tmp_5: StalkerPortalProfile? = __roomEnumConverters.toStalkerPortalProfile(_tmp_4)
          if (_tmp_5 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPortalProfile', but it was NULL.")
          } else {
            _tmpStalkerPortalProfile = _tmp_5
          }
          val _tmpStalkerPortalFingerprint: StalkerPortalFingerprint
          val _tmp_6: String?
          if (_stmt.isNull(_columnIndexOfStalkerPortalFingerprint)) {
            _tmp_6 = null
          } else {
            _tmp_6 = _stmt.getText(_columnIndexOfStalkerPortalFingerprint)
          }
          val _tmp_7: StalkerPortalFingerprint? = __roomEnumConverters.toStalkerPortalFingerprint(_tmp_6)
          if (_tmp_7 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPortalFingerprint', but it was NULL.")
          } else {
            _tmpStalkerPortalFingerprint = _tmp_7
          }
          val _tmpStalkerMagPreset: StalkerMagPreset
          val _tmp_8: String?
          if (_stmt.isNull(_columnIndexOfStalkerMagPreset)) {
            _tmp_8 = null
          } else {
            _tmp_8 = _stmt.getText(_columnIndexOfStalkerMagPreset)
          }
          val _tmp_9: StalkerMagPreset? = __roomEnumConverters.toStalkerMagPreset(_tmp_8)
          if (_tmp_9 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerMagPreset', but it was NULL.")
          } else {
            _tmpStalkerMagPreset = _tmp_9
          }
          val _tmpStalkerLastBootstrapRecipe: StalkerBootstrapRecipe
          val _tmp_10: String?
          if (_stmt.isNull(_columnIndexOfStalkerLastBootstrapRecipe)) {
            _tmp_10 = null
          } else {
            _tmp_10 = _stmt.getText(_columnIndexOfStalkerLastBootstrapRecipe)
          }
          val _tmp_11: StalkerBootstrapRecipe? = __roomEnumConverters.toStalkerBootstrapRecipe(_tmp_10)
          if (_tmp_11 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerBootstrapRecipe', but it was NULL.")
          } else {
            _tmpStalkerLastBootstrapRecipe = _tmp_11
          }
          val _tmpStalkerEndpointPreference: StalkerEndpointPreference
          val _tmp_12: String?
          if (_stmt.isNull(_columnIndexOfStalkerEndpointPreference)) {
            _tmp_12 = null
          } else {
            _tmp_12 = _stmt.getText(_columnIndexOfStalkerEndpointPreference)
          }
          val _tmp_13: StalkerEndpointPreference? = __roomEnumConverters.toStalkerEndpointPreference(_tmp_12)
          if (_tmp_13 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerEndpointPreference', but it was NULL.")
          } else {
            _tmpStalkerEndpointPreference = _tmp_13
          }
          val _tmpStalkerCookieMode: StalkerCookieMode
          val _tmp_14: String?
          if (_stmt.isNull(_columnIndexOfStalkerCookieMode)) {
            _tmp_14 = null
          } else {
            _tmp_14 = _stmt.getText(_columnIndexOfStalkerCookieMode)
          }
          val _tmp_15: StalkerCookieMode? = __roomEnumConverters.toStalkerCookieMode(_tmp_14)
          if (_tmp_15 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerCookieMode', but it was NULL.")
          } else {
            _tmpStalkerCookieMode = _tmp_15
          }
          val _tmpStalkerPlaybackBackendHint: StalkerPlaybackBackendHint
          val _tmp_16: String?
          if (_stmt.isNull(_columnIndexOfStalkerPlaybackBackendHint)) {
            _tmp_16 = null
          } else {
            _tmp_16 = _stmt.getText(_columnIndexOfStalkerPlaybackBackendHint)
          }
          val _tmp_17: StalkerPlaybackBackendHint? = __roomEnumConverters.toStalkerPlaybackBackendHint(_tmp_16)
          if (_tmp_17 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPlaybackBackendHint', but it was NULL.")
          } else {
            _tmpStalkerPlaybackBackendHint = _tmp_17
          }
          val _tmpStalkerLastPlaybackMode: String?
          if (_stmt.isNull(_columnIndexOfStalkerLastPlaybackMode)) {
            _tmpStalkerLastPlaybackMode = null
          } else {
            _tmpStalkerLastPlaybackMode = _stmt.getText(_columnIndexOfStalkerLastPlaybackMode)
          }
          val _tmpStalkerCredentialsRequired: Boolean
          val _tmp_18: Int
          _tmp_18 = _stmt.getLong(_columnIndexOfStalkerCredentialsRequired).toInt()
          _tmpStalkerCredentialsRequired = _tmp_18 != 0
          val _tmpStalkerMacRequired: Boolean
          val _tmp_19: Int
          _tmp_19 = _stmt.getLong(_columnIndexOfStalkerMacRequired).toInt()
          _tmpStalkerMacRequired = _tmp_19 != 0
          val _tmpStalkerUsesTemporaryLinks: Boolean
          val _tmp_20: Int
          _tmp_20 = _stmt.getLong(_columnIndexOfStalkerUsesTemporaryLinks).toInt()
          _tmpStalkerUsesTemporaryLinks = _tmp_20 != 0
          val _tmpStalkerModuleRestricted: Boolean
          val _tmp_21: Int
          _tmp_21 = _stmt.getLong(_columnIndexOfStalkerModuleRestricted).toInt()
          _tmpStalkerModuleRestricted = _tmp_21 != 0
          val _tmpStalkerStrictFingerprintRequired: Boolean
          val _tmp_22: Int
          _tmp_22 = _stmt.getLong(_columnIndexOfStalkerStrictFingerprintRequired).toInt()
          _tmpStalkerStrictFingerprintRequired = _tmp_22 != 0
          val _tmpStalkerRecipeFallbackUsed: Boolean
          val _tmp_23: Int
          _tmp_23 = _stmt.getLong(_columnIndexOfStalkerRecipeFallbackUsed).toInt()
          _tmpStalkerRecipeFallbackUsed = _tmp_23 != 0
          val _tmpStalkerRecipeRediscoveryAttempts: Int
          _tmpStalkerRecipeRediscoveryAttempts = _stmt.getLong(_columnIndexOfStalkerRecipeRediscoveryAttempts).toInt()
          val _tmpIsActive: Boolean
          val _tmp_24: Int
          _tmp_24 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_24 != 0
          val _tmpMaxConnections: Int
          _tmpMaxConnections = _stmt.getLong(_columnIndexOfMaxConnections).toInt()
          val _tmpExpirationDate: Long?
          if (_stmt.isNull(_columnIndexOfExpirationDate)) {
            _tmpExpirationDate = null
          } else {
            _tmpExpirationDate = _stmt.getLong(_columnIndexOfExpirationDate)
          }
          val _tmpApiVersion: String?
          if (_stmt.isNull(_columnIndexOfApiVersion)) {
            _tmpApiVersion = null
          } else {
            _tmpApiVersion = _stmt.getText(_columnIndexOfApiVersion)
          }
          val _tmpAllowedOutputFormatsJson: String
          _tmpAllowedOutputFormatsJson = _stmt.getText(_columnIndexOfAllowedOutputFormatsJson)
          val _tmpEpgSyncMode: ProviderEpgSyncMode
          val _tmp_25: String?
          if (_stmt.isNull(_columnIndexOfEpgSyncMode)) {
            _tmp_25 = null
          } else {
            _tmp_25 = _stmt.getText(_columnIndexOfEpgSyncMode)
          }
          val _tmp_26: ProviderEpgSyncMode? = __roomEnumConverters.toProviderEpgSyncMode(_tmp_25)
          if (_tmp_26 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderEpgSyncMode', but it was NULL.")
          } else {
            _tmpEpgSyncMode = _tmp_26
          }
          val _tmpXtreamFastSyncEnabled: Boolean
          val _tmp_27: Int
          _tmp_27 = _stmt.getLong(_columnIndexOfXtreamFastSyncEnabled).toInt()
          _tmpXtreamFastSyncEnabled = _tmp_27 != 0
          val _tmpXtreamLiveSyncMode: ProviderXtreamLiveSyncMode
          val _tmp_28: String?
          if (_stmt.isNull(_columnIndexOfXtreamLiveSyncMode)) {
            _tmp_28 = null
          } else {
            _tmp_28 = _stmt.getText(_columnIndexOfXtreamLiveSyncMode)
          }
          val _tmp_29: ProviderXtreamLiveSyncMode? = __roomEnumConverters.toProviderXtreamLiveSyncMode(_tmp_28)
          if (_tmp_29 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderXtreamLiveSyncMode', but it was NULL.")
          } else {
            _tmpXtreamLiveSyncMode = _tmp_29
          }
          val _tmpM3uVodClassificationEnabled: Boolean
          val _tmp_30: Int
          _tmp_30 = _stmt.getLong(_columnIndexOfM3uVodClassificationEnabled).toInt()
          _tmpM3uVodClassificationEnabled = _tmp_30 != 0
          val _tmpStatus: ProviderStatus
          val _tmp_31: String?
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmp_31 = null
          } else {
            _tmp_31 = _stmt.getText(_columnIndexOfStatus)
          }
          val _tmp_32: ProviderStatus? = __roomEnumConverters.toProviderStatus(_tmp_31)
          if (_tmp_32 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderStatus', but it was NULL.")
          } else {
            _tmpStatus = _tmp_32
          }
          val _tmpLastSyncedAt: Long
          _tmpLastSyncedAt = _stmt.getLong(_columnIndexOfLastSyncedAt)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item = ProviderEntity(_tmpId,_tmpName,_tmpType,_tmpServerUrl,_tmpUsername,_tmpPassword,_tmpM3uUrl,_tmpEpgUrl,_tmpHttpUserAgent,_tmpHttpHeaders,_tmpStalkerMacAddress,_tmpStalkerDeviceProfile,_tmpStalkerDeviceTimezone,_tmpStalkerDeviceLocale,_tmpStalkerSerialNumber,_tmpStalkerDeviceId,_tmpStalkerDeviceId2,_tmpStalkerSignature,_tmpStalkerAdvancedOptionsJson,_tmpStalkerAuthMode,_tmpStalkerPortalProfile,_tmpStalkerPortalFingerprint,_tmpStalkerMagPreset,_tmpStalkerLastBootstrapRecipe,_tmpStalkerEndpointPreference,_tmpStalkerCookieMode,_tmpStalkerPlaybackBackendHint,_tmpStalkerLastPlaybackMode,_tmpStalkerCredentialsRequired,_tmpStalkerMacRequired,_tmpStalkerUsesTemporaryLinks,_tmpStalkerModuleRestricted,_tmpStalkerStrictFingerprintRequired,_tmpStalkerRecipeFallbackUsed,_tmpStalkerRecipeRediscoveryAttempts,_tmpIsActive,_tmpMaxConnections,_tmpExpirationDate,_tmpApiVersion,_tmpAllowedOutputFormatsJson,_tmpEpgSyncMode,_tmpXtreamFastSyncEnabled,_tmpXtreamLiveSyncMode,_tmpM3uVodClassificationEnabled,_tmpStatus,_tmpLastSyncedAt,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getActive(): Flow<ProviderEntity?> {
    val _sql: String = "SELECT * FROM providers WHERE is_active = 1 LIMIT 1"
    return createFlow(__db, false, arrayOf("providers")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfServerUrl: Int = getColumnIndexOrThrow(_stmt, "server_url")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfM3uUrl: Int = getColumnIndexOrThrow(_stmt, "m3u_url")
        val _columnIndexOfEpgUrl: Int = getColumnIndexOrThrow(_stmt, "epg_url")
        val _columnIndexOfHttpUserAgent: Int = getColumnIndexOrThrow(_stmt, "http_user_agent")
        val _columnIndexOfHttpHeaders: Int = getColumnIndexOrThrow(_stmt, "http_headers")
        val _columnIndexOfStalkerMacAddress: Int = getColumnIndexOrThrow(_stmt, "stalker_mac_address")
        val _columnIndexOfStalkerDeviceProfile: Int = getColumnIndexOrThrow(_stmt, "stalker_device_profile")
        val _columnIndexOfStalkerDeviceTimezone: Int = getColumnIndexOrThrow(_stmt, "stalker_device_timezone")
        val _columnIndexOfStalkerDeviceLocale: Int = getColumnIndexOrThrow(_stmt, "stalker_device_locale")
        val _columnIndexOfStalkerSerialNumber: Int = getColumnIndexOrThrow(_stmt, "stalker_serial_number")
        val _columnIndexOfStalkerDeviceId: Int = getColumnIndexOrThrow(_stmt, "stalker_device_id")
        val _columnIndexOfStalkerDeviceId2: Int = getColumnIndexOrThrow(_stmt, "stalker_device_id2")
        val _columnIndexOfStalkerSignature: Int = getColumnIndexOrThrow(_stmt, "stalker_signature")
        val _columnIndexOfStalkerAdvancedOptionsJson: Int = getColumnIndexOrThrow(_stmt, "stalker_advanced_options_json")
        val _columnIndexOfStalkerAuthMode: Int = getColumnIndexOrThrow(_stmt, "stalker_auth_mode")
        val _columnIndexOfStalkerPortalProfile: Int = getColumnIndexOrThrow(_stmt, "stalker_portal_profile")
        val _columnIndexOfStalkerPortalFingerprint: Int = getColumnIndexOrThrow(_stmt, "stalker_portal_fingerprint")
        val _columnIndexOfStalkerMagPreset: Int = getColumnIndexOrThrow(_stmt, "stalker_mag_preset")
        val _columnIndexOfStalkerLastBootstrapRecipe: Int = getColumnIndexOrThrow(_stmt, "stalker_last_bootstrap_recipe")
        val _columnIndexOfStalkerEndpointPreference: Int = getColumnIndexOrThrow(_stmt, "stalker_endpoint_preference")
        val _columnIndexOfStalkerCookieMode: Int = getColumnIndexOrThrow(_stmt, "stalker_cookie_mode")
        val _columnIndexOfStalkerPlaybackBackendHint: Int = getColumnIndexOrThrow(_stmt, "stalker_playback_backend_hint")
        val _columnIndexOfStalkerLastPlaybackMode: Int = getColumnIndexOrThrow(_stmt, "stalker_last_playback_mode")
        val _columnIndexOfStalkerCredentialsRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_credentials_required")
        val _columnIndexOfStalkerMacRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_mac_required")
        val _columnIndexOfStalkerUsesTemporaryLinks: Int = getColumnIndexOrThrow(_stmt, "stalker_uses_temp_links")
        val _columnIndexOfStalkerModuleRestricted: Int = getColumnIndexOrThrow(_stmt, "stalker_module_restricted")
        val _columnIndexOfStalkerStrictFingerprintRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_strict_fingerprint_required")
        val _columnIndexOfStalkerRecipeFallbackUsed: Int = getColumnIndexOrThrow(_stmt, "stalker_recipe_fallback_used")
        val _columnIndexOfStalkerRecipeRediscoveryAttempts: Int = getColumnIndexOrThrow(_stmt, "stalker_recipe_rediscovery_attempts")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "is_active")
        val _columnIndexOfMaxConnections: Int = getColumnIndexOrThrow(_stmt, "max_connections")
        val _columnIndexOfExpirationDate: Int = getColumnIndexOrThrow(_stmt, "expiration_date")
        val _columnIndexOfApiVersion: Int = getColumnIndexOrThrow(_stmt, "api_version")
        val _columnIndexOfAllowedOutputFormatsJson: Int = getColumnIndexOrThrow(_stmt, "allowed_output_formats_json")
        val _columnIndexOfEpgSyncMode: Int = getColumnIndexOrThrow(_stmt, "epg_sync_mode")
        val _columnIndexOfXtreamFastSyncEnabled: Int = getColumnIndexOrThrow(_stmt, "xtream_fast_sync_enabled")
        val _columnIndexOfXtreamLiveSyncMode: Int = getColumnIndexOrThrow(_stmt, "xtream_live_sync_mode")
        val _columnIndexOfM3uVodClassificationEnabled: Int = getColumnIndexOrThrow(_stmt, "m3u_vod_classification_enabled")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfLastSyncedAt: Int = getColumnIndexOrThrow(_stmt, "last_synced_at")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _result: ProviderEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpType: ProviderType
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfType)
          }
          val _tmp_1: ProviderType? = __roomEnumConverters.toProviderType(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderType', but it was NULL.")
          } else {
            _tmpType = _tmp_1
          }
          val _tmpServerUrl: String
          _tmpServerUrl = _stmt.getText(_columnIndexOfServerUrl)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpM3uUrl: String
          _tmpM3uUrl = _stmt.getText(_columnIndexOfM3uUrl)
          val _tmpEpgUrl: String
          _tmpEpgUrl = _stmt.getText(_columnIndexOfEpgUrl)
          val _tmpHttpUserAgent: String
          _tmpHttpUserAgent = _stmt.getText(_columnIndexOfHttpUserAgent)
          val _tmpHttpHeaders: String
          _tmpHttpHeaders = _stmt.getText(_columnIndexOfHttpHeaders)
          val _tmpStalkerMacAddress: String
          _tmpStalkerMacAddress = _stmt.getText(_columnIndexOfStalkerMacAddress)
          val _tmpStalkerDeviceProfile: String
          _tmpStalkerDeviceProfile = _stmt.getText(_columnIndexOfStalkerDeviceProfile)
          val _tmpStalkerDeviceTimezone: String
          _tmpStalkerDeviceTimezone = _stmt.getText(_columnIndexOfStalkerDeviceTimezone)
          val _tmpStalkerDeviceLocale: String
          _tmpStalkerDeviceLocale = _stmt.getText(_columnIndexOfStalkerDeviceLocale)
          val _tmpStalkerSerialNumber: String
          _tmpStalkerSerialNumber = _stmt.getText(_columnIndexOfStalkerSerialNumber)
          val _tmpStalkerDeviceId: String
          _tmpStalkerDeviceId = _stmt.getText(_columnIndexOfStalkerDeviceId)
          val _tmpStalkerDeviceId2: String
          _tmpStalkerDeviceId2 = _stmt.getText(_columnIndexOfStalkerDeviceId2)
          val _tmpStalkerSignature: String
          _tmpStalkerSignature = _stmt.getText(_columnIndexOfStalkerSignature)
          val _tmpStalkerAdvancedOptionsJson: String
          _tmpStalkerAdvancedOptionsJson = _stmt.getText(_columnIndexOfStalkerAdvancedOptionsJson)
          val _tmpStalkerAuthMode: StalkerAuthMode
          val _tmp_2: String?
          if (_stmt.isNull(_columnIndexOfStalkerAuthMode)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfStalkerAuthMode)
          }
          val _tmp_3: StalkerAuthMode? = __roomEnumConverters.toStalkerAuthMode(_tmp_2)
          if (_tmp_3 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerAuthMode', but it was NULL.")
          } else {
            _tmpStalkerAuthMode = _tmp_3
          }
          val _tmpStalkerPortalProfile: StalkerPortalProfile
          val _tmp_4: String?
          if (_stmt.isNull(_columnIndexOfStalkerPortalProfile)) {
            _tmp_4 = null
          } else {
            _tmp_4 = _stmt.getText(_columnIndexOfStalkerPortalProfile)
          }
          val _tmp_5: StalkerPortalProfile? = __roomEnumConverters.toStalkerPortalProfile(_tmp_4)
          if (_tmp_5 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPortalProfile', but it was NULL.")
          } else {
            _tmpStalkerPortalProfile = _tmp_5
          }
          val _tmpStalkerPortalFingerprint: StalkerPortalFingerprint
          val _tmp_6: String?
          if (_stmt.isNull(_columnIndexOfStalkerPortalFingerprint)) {
            _tmp_6 = null
          } else {
            _tmp_6 = _stmt.getText(_columnIndexOfStalkerPortalFingerprint)
          }
          val _tmp_7: StalkerPortalFingerprint? = __roomEnumConverters.toStalkerPortalFingerprint(_tmp_6)
          if (_tmp_7 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPortalFingerprint', but it was NULL.")
          } else {
            _tmpStalkerPortalFingerprint = _tmp_7
          }
          val _tmpStalkerMagPreset: StalkerMagPreset
          val _tmp_8: String?
          if (_stmt.isNull(_columnIndexOfStalkerMagPreset)) {
            _tmp_8 = null
          } else {
            _tmp_8 = _stmt.getText(_columnIndexOfStalkerMagPreset)
          }
          val _tmp_9: StalkerMagPreset? = __roomEnumConverters.toStalkerMagPreset(_tmp_8)
          if (_tmp_9 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerMagPreset', but it was NULL.")
          } else {
            _tmpStalkerMagPreset = _tmp_9
          }
          val _tmpStalkerLastBootstrapRecipe: StalkerBootstrapRecipe
          val _tmp_10: String?
          if (_stmt.isNull(_columnIndexOfStalkerLastBootstrapRecipe)) {
            _tmp_10 = null
          } else {
            _tmp_10 = _stmt.getText(_columnIndexOfStalkerLastBootstrapRecipe)
          }
          val _tmp_11: StalkerBootstrapRecipe? = __roomEnumConverters.toStalkerBootstrapRecipe(_tmp_10)
          if (_tmp_11 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerBootstrapRecipe', but it was NULL.")
          } else {
            _tmpStalkerLastBootstrapRecipe = _tmp_11
          }
          val _tmpStalkerEndpointPreference: StalkerEndpointPreference
          val _tmp_12: String?
          if (_stmt.isNull(_columnIndexOfStalkerEndpointPreference)) {
            _tmp_12 = null
          } else {
            _tmp_12 = _stmt.getText(_columnIndexOfStalkerEndpointPreference)
          }
          val _tmp_13: StalkerEndpointPreference? = __roomEnumConverters.toStalkerEndpointPreference(_tmp_12)
          if (_tmp_13 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerEndpointPreference', but it was NULL.")
          } else {
            _tmpStalkerEndpointPreference = _tmp_13
          }
          val _tmpStalkerCookieMode: StalkerCookieMode
          val _tmp_14: String?
          if (_stmt.isNull(_columnIndexOfStalkerCookieMode)) {
            _tmp_14 = null
          } else {
            _tmp_14 = _stmt.getText(_columnIndexOfStalkerCookieMode)
          }
          val _tmp_15: StalkerCookieMode? = __roomEnumConverters.toStalkerCookieMode(_tmp_14)
          if (_tmp_15 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerCookieMode', but it was NULL.")
          } else {
            _tmpStalkerCookieMode = _tmp_15
          }
          val _tmpStalkerPlaybackBackendHint: StalkerPlaybackBackendHint
          val _tmp_16: String?
          if (_stmt.isNull(_columnIndexOfStalkerPlaybackBackendHint)) {
            _tmp_16 = null
          } else {
            _tmp_16 = _stmt.getText(_columnIndexOfStalkerPlaybackBackendHint)
          }
          val _tmp_17: StalkerPlaybackBackendHint? = __roomEnumConverters.toStalkerPlaybackBackendHint(_tmp_16)
          if (_tmp_17 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPlaybackBackendHint', but it was NULL.")
          } else {
            _tmpStalkerPlaybackBackendHint = _tmp_17
          }
          val _tmpStalkerLastPlaybackMode: String?
          if (_stmt.isNull(_columnIndexOfStalkerLastPlaybackMode)) {
            _tmpStalkerLastPlaybackMode = null
          } else {
            _tmpStalkerLastPlaybackMode = _stmt.getText(_columnIndexOfStalkerLastPlaybackMode)
          }
          val _tmpStalkerCredentialsRequired: Boolean
          val _tmp_18: Int
          _tmp_18 = _stmt.getLong(_columnIndexOfStalkerCredentialsRequired).toInt()
          _tmpStalkerCredentialsRequired = _tmp_18 != 0
          val _tmpStalkerMacRequired: Boolean
          val _tmp_19: Int
          _tmp_19 = _stmt.getLong(_columnIndexOfStalkerMacRequired).toInt()
          _tmpStalkerMacRequired = _tmp_19 != 0
          val _tmpStalkerUsesTemporaryLinks: Boolean
          val _tmp_20: Int
          _tmp_20 = _stmt.getLong(_columnIndexOfStalkerUsesTemporaryLinks).toInt()
          _tmpStalkerUsesTemporaryLinks = _tmp_20 != 0
          val _tmpStalkerModuleRestricted: Boolean
          val _tmp_21: Int
          _tmp_21 = _stmt.getLong(_columnIndexOfStalkerModuleRestricted).toInt()
          _tmpStalkerModuleRestricted = _tmp_21 != 0
          val _tmpStalkerStrictFingerprintRequired: Boolean
          val _tmp_22: Int
          _tmp_22 = _stmt.getLong(_columnIndexOfStalkerStrictFingerprintRequired).toInt()
          _tmpStalkerStrictFingerprintRequired = _tmp_22 != 0
          val _tmpStalkerRecipeFallbackUsed: Boolean
          val _tmp_23: Int
          _tmp_23 = _stmt.getLong(_columnIndexOfStalkerRecipeFallbackUsed).toInt()
          _tmpStalkerRecipeFallbackUsed = _tmp_23 != 0
          val _tmpStalkerRecipeRediscoveryAttempts: Int
          _tmpStalkerRecipeRediscoveryAttempts = _stmt.getLong(_columnIndexOfStalkerRecipeRediscoveryAttempts).toInt()
          val _tmpIsActive: Boolean
          val _tmp_24: Int
          _tmp_24 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_24 != 0
          val _tmpMaxConnections: Int
          _tmpMaxConnections = _stmt.getLong(_columnIndexOfMaxConnections).toInt()
          val _tmpExpirationDate: Long?
          if (_stmt.isNull(_columnIndexOfExpirationDate)) {
            _tmpExpirationDate = null
          } else {
            _tmpExpirationDate = _stmt.getLong(_columnIndexOfExpirationDate)
          }
          val _tmpApiVersion: String?
          if (_stmt.isNull(_columnIndexOfApiVersion)) {
            _tmpApiVersion = null
          } else {
            _tmpApiVersion = _stmt.getText(_columnIndexOfApiVersion)
          }
          val _tmpAllowedOutputFormatsJson: String
          _tmpAllowedOutputFormatsJson = _stmt.getText(_columnIndexOfAllowedOutputFormatsJson)
          val _tmpEpgSyncMode: ProviderEpgSyncMode
          val _tmp_25: String?
          if (_stmt.isNull(_columnIndexOfEpgSyncMode)) {
            _tmp_25 = null
          } else {
            _tmp_25 = _stmt.getText(_columnIndexOfEpgSyncMode)
          }
          val _tmp_26: ProviderEpgSyncMode? = __roomEnumConverters.toProviderEpgSyncMode(_tmp_25)
          if (_tmp_26 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderEpgSyncMode', but it was NULL.")
          } else {
            _tmpEpgSyncMode = _tmp_26
          }
          val _tmpXtreamFastSyncEnabled: Boolean
          val _tmp_27: Int
          _tmp_27 = _stmt.getLong(_columnIndexOfXtreamFastSyncEnabled).toInt()
          _tmpXtreamFastSyncEnabled = _tmp_27 != 0
          val _tmpXtreamLiveSyncMode: ProviderXtreamLiveSyncMode
          val _tmp_28: String?
          if (_stmt.isNull(_columnIndexOfXtreamLiveSyncMode)) {
            _tmp_28 = null
          } else {
            _tmp_28 = _stmt.getText(_columnIndexOfXtreamLiveSyncMode)
          }
          val _tmp_29: ProviderXtreamLiveSyncMode? = __roomEnumConverters.toProviderXtreamLiveSyncMode(_tmp_28)
          if (_tmp_29 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderXtreamLiveSyncMode', but it was NULL.")
          } else {
            _tmpXtreamLiveSyncMode = _tmp_29
          }
          val _tmpM3uVodClassificationEnabled: Boolean
          val _tmp_30: Int
          _tmp_30 = _stmt.getLong(_columnIndexOfM3uVodClassificationEnabled).toInt()
          _tmpM3uVodClassificationEnabled = _tmp_30 != 0
          val _tmpStatus: ProviderStatus
          val _tmp_31: String?
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmp_31 = null
          } else {
            _tmp_31 = _stmt.getText(_columnIndexOfStatus)
          }
          val _tmp_32: ProviderStatus? = __roomEnumConverters.toProviderStatus(_tmp_31)
          if (_tmp_32 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderStatus', but it was NULL.")
          } else {
            _tmpStatus = _tmp_32
          }
          val _tmpLastSyncedAt: Long
          _tmpLastSyncedAt = _stmt.getLong(_columnIndexOfLastSyncedAt)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _result = ProviderEntity(_tmpId,_tmpName,_tmpType,_tmpServerUrl,_tmpUsername,_tmpPassword,_tmpM3uUrl,_tmpEpgUrl,_tmpHttpUserAgent,_tmpHttpHeaders,_tmpStalkerMacAddress,_tmpStalkerDeviceProfile,_tmpStalkerDeviceTimezone,_tmpStalkerDeviceLocale,_tmpStalkerSerialNumber,_tmpStalkerDeviceId,_tmpStalkerDeviceId2,_tmpStalkerSignature,_tmpStalkerAdvancedOptionsJson,_tmpStalkerAuthMode,_tmpStalkerPortalProfile,_tmpStalkerPortalFingerprint,_tmpStalkerMagPreset,_tmpStalkerLastBootstrapRecipe,_tmpStalkerEndpointPreference,_tmpStalkerCookieMode,_tmpStalkerPlaybackBackendHint,_tmpStalkerLastPlaybackMode,_tmpStalkerCredentialsRequired,_tmpStalkerMacRequired,_tmpStalkerUsesTemporaryLinks,_tmpStalkerModuleRestricted,_tmpStalkerStrictFingerprintRequired,_tmpStalkerRecipeFallbackUsed,_tmpStalkerRecipeRediscoveryAttempts,_tmpIsActive,_tmpMaxConnections,_tmpExpirationDate,_tmpApiVersion,_tmpAllowedOutputFormatsJson,_tmpEpgSyncMode,_tmpXtreamFastSyncEnabled,_tmpXtreamLiveSyncMode,_tmpM3uVodClassificationEnabled,_tmpStatus,_tmpLastSyncedAt,_tmpCreatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByUrlAndUser(
    serverUrl: String,
    username: String,
    stalkerMacAddress: String,
  ): ProviderEntity? {
    val _sql: String = "SELECT * FROM providers WHERE server_url = ? AND username = ? AND stalker_mac_address = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, serverUrl)
        _argIndex = 2
        _stmt.bindText(_argIndex, username)
        _argIndex = 3
        _stmt.bindText(_argIndex, stalkerMacAddress)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfServerUrl: Int = getColumnIndexOrThrow(_stmt, "server_url")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfM3uUrl: Int = getColumnIndexOrThrow(_stmt, "m3u_url")
        val _columnIndexOfEpgUrl: Int = getColumnIndexOrThrow(_stmt, "epg_url")
        val _columnIndexOfHttpUserAgent: Int = getColumnIndexOrThrow(_stmt, "http_user_agent")
        val _columnIndexOfHttpHeaders: Int = getColumnIndexOrThrow(_stmt, "http_headers")
        val _columnIndexOfStalkerMacAddress: Int = getColumnIndexOrThrow(_stmt, "stalker_mac_address")
        val _columnIndexOfStalkerDeviceProfile: Int = getColumnIndexOrThrow(_stmt, "stalker_device_profile")
        val _columnIndexOfStalkerDeviceTimezone: Int = getColumnIndexOrThrow(_stmt, "stalker_device_timezone")
        val _columnIndexOfStalkerDeviceLocale: Int = getColumnIndexOrThrow(_stmt, "stalker_device_locale")
        val _columnIndexOfStalkerSerialNumber: Int = getColumnIndexOrThrow(_stmt, "stalker_serial_number")
        val _columnIndexOfStalkerDeviceId: Int = getColumnIndexOrThrow(_stmt, "stalker_device_id")
        val _columnIndexOfStalkerDeviceId2: Int = getColumnIndexOrThrow(_stmt, "stalker_device_id2")
        val _columnIndexOfStalkerSignature: Int = getColumnIndexOrThrow(_stmt, "stalker_signature")
        val _columnIndexOfStalkerAdvancedOptionsJson: Int = getColumnIndexOrThrow(_stmt, "stalker_advanced_options_json")
        val _columnIndexOfStalkerAuthMode: Int = getColumnIndexOrThrow(_stmt, "stalker_auth_mode")
        val _columnIndexOfStalkerPortalProfile: Int = getColumnIndexOrThrow(_stmt, "stalker_portal_profile")
        val _columnIndexOfStalkerPortalFingerprint: Int = getColumnIndexOrThrow(_stmt, "stalker_portal_fingerprint")
        val _columnIndexOfStalkerMagPreset: Int = getColumnIndexOrThrow(_stmt, "stalker_mag_preset")
        val _columnIndexOfStalkerLastBootstrapRecipe: Int = getColumnIndexOrThrow(_stmt, "stalker_last_bootstrap_recipe")
        val _columnIndexOfStalkerEndpointPreference: Int = getColumnIndexOrThrow(_stmt, "stalker_endpoint_preference")
        val _columnIndexOfStalkerCookieMode: Int = getColumnIndexOrThrow(_stmt, "stalker_cookie_mode")
        val _columnIndexOfStalkerPlaybackBackendHint: Int = getColumnIndexOrThrow(_stmt, "stalker_playback_backend_hint")
        val _columnIndexOfStalkerLastPlaybackMode: Int = getColumnIndexOrThrow(_stmt, "stalker_last_playback_mode")
        val _columnIndexOfStalkerCredentialsRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_credentials_required")
        val _columnIndexOfStalkerMacRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_mac_required")
        val _columnIndexOfStalkerUsesTemporaryLinks: Int = getColumnIndexOrThrow(_stmt, "stalker_uses_temp_links")
        val _columnIndexOfStalkerModuleRestricted: Int = getColumnIndexOrThrow(_stmt, "stalker_module_restricted")
        val _columnIndexOfStalkerStrictFingerprintRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_strict_fingerprint_required")
        val _columnIndexOfStalkerRecipeFallbackUsed: Int = getColumnIndexOrThrow(_stmt, "stalker_recipe_fallback_used")
        val _columnIndexOfStalkerRecipeRediscoveryAttempts: Int = getColumnIndexOrThrow(_stmt, "stalker_recipe_rediscovery_attempts")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "is_active")
        val _columnIndexOfMaxConnections: Int = getColumnIndexOrThrow(_stmt, "max_connections")
        val _columnIndexOfExpirationDate: Int = getColumnIndexOrThrow(_stmt, "expiration_date")
        val _columnIndexOfApiVersion: Int = getColumnIndexOrThrow(_stmt, "api_version")
        val _columnIndexOfAllowedOutputFormatsJson: Int = getColumnIndexOrThrow(_stmt, "allowed_output_formats_json")
        val _columnIndexOfEpgSyncMode: Int = getColumnIndexOrThrow(_stmt, "epg_sync_mode")
        val _columnIndexOfXtreamFastSyncEnabled: Int = getColumnIndexOrThrow(_stmt, "xtream_fast_sync_enabled")
        val _columnIndexOfXtreamLiveSyncMode: Int = getColumnIndexOrThrow(_stmt, "xtream_live_sync_mode")
        val _columnIndexOfM3uVodClassificationEnabled: Int = getColumnIndexOrThrow(_stmt, "m3u_vod_classification_enabled")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfLastSyncedAt: Int = getColumnIndexOrThrow(_stmt, "last_synced_at")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _result: ProviderEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpType: ProviderType
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfType)
          }
          val _tmp_1: ProviderType? = __roomEnumConverters.toProviderType(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderType', but it was NULL.")
          } else {
            _tmpType = _tmp_1
          }
          val _tmpServerUrl: String
          _tmpServerUrl = _stmt.getText(_columnIndexOfServerUrl)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpM3uUrl: String
          _tmpM3uUrl = _stmt.getText(_columnIndexOfM3uUrl)
          val _tmpEpgUrl: String
          _tmpEpgUrl = _stmt.getText(_columnIndexOfEpgUrl)
          val _tmpHttpUserAgent: String
          _tmpHttpUserAgent = _stmt.getText(_columnIndexOfHttpUserAgent)
          val _tmpHttpHeaders: String
          _tmpHttpHeaders = _stmt.getText(_columnIndexOfHttpHeaders)
          val _tmpStalkerMacAddress: String
          _tmpStalkerMacAddress = _stmt.getText(_columnIndexOfStalkerMacAddress)
          val _tmpStalkerDeviceProfile: String
          _tmpStalkerDeviceProfile = _stmt.getText(_columnIndexOfStalkerDeviceProfile)
          val _tmpStalkerDeviceTimezone: String
          _tmpStalkerDeviceTimezone = _stmt.getText(_columnIndexOfStalkerDeviceTimezone)
          val _tmpStalkerDeviceLocale: String
          _tmpStalkerDeviceLocale = _stmt.getText(_columnIndexOfStalkerDeviceLocale)
          val _tmpStalkerSerialNumber: String
          _tmpStalkerSerialNumber = _stmt.getText(_columnIndexOfStalkerSerialNumber)
          val _tmpStalkerDeviceId: String
          _tmpStalkerDeviceId = _stmt.getText(_columnIndexOfStalkerDeviceId)
          val _tmpStalkerDeviceId2: String
          _tmpStalkerDeviceId2 = _stmt.getText(_columnIndexOfStalkerDeviceId2)
          val _tmpStalkerSignature: String
          _tmpStalkerSignature = _stmt.getText(_columnIndexOfStalkerSignature)
          val _tmpStalkerAdvancedOptionsJson: String
          _tmpStalkerAdvancedOptionsJson = _stmt.getText(_columnIndexOfStalkerAdvancedOptionsJson)
          val _tmpStalkerAuthMode: StalkerAuthMode
          val _tmp_2: String?
          if (_stmt.isNull(_columnIndexOfStalkerAuthMode)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfStalkerAuthMode)
          }
          val _tmp_3: StalkerAuthMode? = __roomEnumConverters.toStalkerAuthMode(_tmp_2)
          if (_tmp_3 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerAuthMode', but it was NULL.")
          } else {
            _tmpStalkerAuthMode = _tmp_3
          }
          val _tmpStalkerPortalProfile: StalkerPortalProfile
          val _tmp_4: String?
          if (_stmt.isNull(_columnIndexOfStalkerPortalProfile)) {
            _tmp_4 = null
          } else {
            _tmp_4 = _stmt.getText(_columnIndexOfStalkerPortalProfile)
          }
          val _tmp_5: StalkerPortalProfile? = __roomEnumConverters.toStalkerPortalProfile(_tmp_4)
          if (_tmp_5 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPortalProfile', but it was NULL.")
          } else {
            _tmpStalkerPortalProfile = _tmp_5
          }
          val _tmpStalkerPortalFingerprint: StalkerPortalFingerprint
          val _tmp_6: String?
          if (_stmt.isNull(_columnIndexOfStalkerPortalFingerprint)) {
            _tmp_6 = null
          } else {
            _tmp_6 = _stmt.getText(_columnIndexOfStalkerPortalFingerprint)
          }
          val _tmp_7: StalkerPortalFingerprint? = __roomEnumConverters.toStalkerPortalFingerprint(_tmp_6)
          if (_tmp_7 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPortalFingerprint', but it was NULL.")
          } else {
            _tmpStalkerPortalFingerprint = _tmp_7
          }
          val _tmpStalkerMagPreset: StalkerMagPreset
          val _tmp_8: String?
          if (_stmt.isNull(_columnIndexOfStalkerMagPreset)) {
            _tmp_8 = null
          } else {
            _tmp_8 = _stmt.getText(_columnIndexOfStalkerMagPreset)
          }
          val _tmp_9: StalkerMagPreset? = __roomEnumConverters.toStalkerMagPreset(_tmp_8)
          if (_tmp_9 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerMagPreset', but it was NULL.")
          } else {
            _tmpStalkerMagPreset = _tmp_9
          }
          val _tmpStalkerLastBootstrapRecipe: StalkerBootstrapRecipe
          val _tmp_10: String?
          if (_stmt.isNull(_columnIndexOfStalkerLastBootstrapRecipe)) {
            _tmp_10 = null
          } else {
            _tmp_10 = _stmt.getText(_columnIndexOfStalkerLastBootstrapRecipe)
          }
          val _tmp_11: StalkerBootstrapRecipe? = __roomEnumConverters.toStalkerBootstrapRecipe(_tmp_10)
          if (_tmp_11 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerBootstrapRecipe', but it was NULL.")
          } else {
            _tmpStalkerLastBootstrapRecipe = _tmp_11
          }
          val _tmpStalkerEndpointPreference: StalkerEndpointPreference
          val _tmp_12: String?
          if (_stmt.isNull(_columnIndexOfStalkerEndpointPreference)) {
            _tmp_12 = null
          } else {
            _tmp_12 = _stmt.getText(_columnIndexOfStalkerEndpointPreference)
          }
          val _tmp_13: StalkerEndpointPreference? = __roomEnumConverters.toStalkerEndpointPreference(_tmp_12)
          if (_tmp_13 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerEndpointPreference', but it was NULL.")
          } else {
            _tmpStalkerEndpointPreference = _tmp_13
          }
          val _tmpStalkerCookieMode: StalkerCookieMode
          val _tmp_14: String?
          if (_stmt.isNull(_columnIndexOfStalkerCookieMode)) {
            _tmp_14 = null
          } else {
            _tmp_14 = _stmt.getText(_columnIndexOfStalkerCookieMode)
          }
          val _tmp_15: StalkerCookieMode? = __roomEnumConverters.toStalkerCookieMode(_tmp_14)
          if (_tmp_15 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerCookieMode', but it was NULL.")
          } else {
            _tmpStalkerCookieMode = _tmp_15
          }
          val _tmpStalkerPlaybackBackendHint: StalkerPlaybackBackendHint
          val _tmp_16: String?
          if (_stmt.isNull(_columnIndexOfStalkerPlaybackBackendHint)) {
            _tmp_16 = null
          } else {
            _tmp_16 = _stmt.getText(_columnIndexOfStalkerPlaybackBackendHint)
          }
          val _tmp_17: StalkerPlaybackBackendHint? = __roomEnumConverters.toStalkerPlaybackBackendHint(_tmp_16)
          if (_tmp_17 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPlaybackBackendHint', but it was NULL.")
          } else {
            _tmpStalkerPlaybackBackendHint = _tmp_17
          }
          val _tmpStalkerLastPlaybackMode: String?
          if (_stmt.isNull(_columnIndexOfStalkerLastPlaybackMode)) {
            _tmpStalkerLastPlaybackMode = null
          } else {
            _tmpStalkerLastPlaybackMode = _stmt.getText(_columnIndexOfStalkerLastPlaybackMode)
          }
          val _tmpStalkerCredentialsRequired: Boolean
          val _tmp_18: Int
          _tmp_18 = _stmt.getLong(_columnIndexOfStalkerCredentialsRequired).toInt()
          _tmpStalkerCredentialsRequired = _tmp_18 != 0
          val _tmpStalkerMacRequired: Boolean
          val _tmp_19: Int
          _tmp_19 = _stmt.getLong(_columnIndexOfStalkerMacRequired).toInt()
          _tmpStalkerMacRequired = _tmp_19 != 0
          val _tmpStalkerUsesTemporaryLinks: Boolean
          val _tmp_20: Int
          _tmp_20 = _stmt.getLong(_columnIndexOfStalkerUsesTemporaryLinks).toInt()
          _tmpStalkerUsesTemporaryLinks = _tmp_20 != 0
          val _tmpStalkerModuleRestricted: Boolean
          val _tmp_21: Int
          _tmp_21 = _stmt.getLong(_columnIndexOfStalkerModuleRestricted).toInt()
          _tmpStalkerModuleRestricted = _tmp_21 != 0
          val _tmpStalkerStrictFingerprintRequired: Boolean
          val _tmp_22: Int
          _tmp_22 = _stmt.getLong(_columnIndexOfStalkerStrictFingerprintRequired).toInt()
          _tmpStalkerStrictFingerprintRequired = _tmp_22 != 0
          val _tmpStalkerRecipeFallbackUsed: Boolean
          val _tmp_23: Int
          _tmp_23 = _stmt.getLong(_columnIndexOfStalkerRecipeFallbackUsed).toInt()
          _tmpStalkerRecipeFallbackUsed = _tmp_23 != 0
          val _tmpStalkerRecipeRediscoveryAttempts: Int
          _tmpStalkerRecipeRediscoveryAttempts = _stmt.getLong(_columnIndexOfStalkerRecipeRediscoveryAttempts).toInt()
          val _tmpIsActive: Boolean
          val _tmp_24: Int
          _tmp_24 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_24 != 0
          val _tmpMaxConnections: Int
          _tmpMaxConnections = _stmt.getLong(_columnIndexOfMaxConnections).toInt()
          val _tmpExpirationDate: Long?
          if (_stmt.isNull(_columnIndexOfExpirationDate)) {
            _tmpExpirationDate = null
          } else {
            _tmpExpirationDate = _stmt.getLong(_columnIndexOfExpirationDate)
          }
          val _tmpApiVersion: String?
          if (_stmt.isNull(_columnIndexOfApiVersion)) {
            _tmpApiVersion = null
          } else {
            _tmpApiVersion = _stmt.getText(_columnIndexOfApiVersion)
          }
          val _tmpAllowedOutputFormatsJson: String
          _tmpAllowedOutputFormatsJson = _stmt.getText(_columnIndexOfAllowedOutputFormatsJson)
          val _tmpEpgSyncMode: ProviderEpgSyncMode
          val _tmp_25: String?
          if (_stmt.isNull(_columnIndexOfEpgSyncMode)) {
            _tmp_25 = null
          } else {
            _tmp_25 = _stmt.getText(_columnIndexOfEpgSyncMode)
          }
          val _tmp_26: ProviderEpgSyncMode? = __roomEnumConverters.toProviderEpgSyncMode(_tmp_25)
          if (_tmp_26 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderEpgSyncMode', but it was NULL.")
          } else {
            _tmpEpgSyncMode = _tmp_26
          }
          val _tmpXtreamFastSyncEnabled: Boolean
          val _tmp_27: Int
          _tmp_27 = _stmt.getLong(_columnIndexOfXtreamFastSyncEnabled).toInt()
          _tmpXtreamFastSyncEnabled = _tmp_27 != 0
          val _tmpXtreamLiveSyncMode: ProviderXtreamLiveSyncMode
          val _tmp_28: String?
          if (_stmt.isNull(_columnIndexOfXtreamLiveSyncMode)) {
            _tmp_28 = null
          } else {
            _tmp_28 = _stmt.getText(_columnIndexOfXtreamLiveSyncMode)
          }
          val _tmp_29: ProviderXtreamLiveSyncMode? = __roomEnumConverters.toProviderXtreamLiveSyncMode(_tmp_28)
          if (_tmp_29 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderXtreamLiveSyncMode', but it was NULL.")
          } else {
            _tmpXtreamLiveSyncMode = _tmp_29
          }
          val _tmpM3uVodClassificationEnabled: Boolean
          val _tmp_30: Int
          _tmp_30 = _stmt.getLong(_columnIndexOfM3uVodClassificationEnabled).toInt()
          _tmpM3uVodClassificationEnabled = _tmp_30 != 0
          val _tmpStatus: ProviderStatus
          val _tmp_31: String?
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmp_31 = null
          } else {
            _tmp_31 = _stmt.getText(_columnIndexOfStatus)
          }
          val _tmp_32: ProviderStatus? = __roomEnumConverters.toProviderStatus(_tmp_31)
          if (_tmp_32 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderStatus', but it was NULL.")
          } else {
            _tmpStatus = _tmp_32
          }
          val _tmpLastSyncedAt: Long
          _tmpLastSyncedAt = _stmt.getLong(_columnIndexOfLastSyncedAt)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _result = ProviderEntity(_tmpId,_tmpName,_tmpType,_tmpServerUrl,_tmpUsername,_tmpPassword,_tmpM3uUrl,_tmpEpgUrl,_tmpHttpUserAgent,_tmpHttpHeaders,_tmpStalkerMacAddress,_tmpStalkerDeviceProfile,_tmpStalkerDeviceTimezone,_tmpStalkerDeviceLocale,_tmpStalkerSerialNumber,_tmpStalkerDeviceId,_tmpStalkerDeviceId2,_tmpStalkerSignature,_tmpStalkerAdvancedOptionsJson,_tmpStalkerAuthMode,_tmpStalkerPortalProfile,_tmpStalkerPortalFingerprint,_tmpStalkerMagPreset,_tmpStalkerLastBootstrapRecipe,_tmpStalkerEndpointPreference,_tmpStalkerCookieMode,_tmpStalkerPlaybackBackendHint,_tmpStalkerLastPlaybackMode,_tmpStalkerCredentialsRequired,_tmpStalkerMacRequired,_tmpStalkerUsesTemporaryLinks,_tmpStalkerModuleRestricted,_tmpStalkerStrictFingerprintRequired,_tmpStalkerRecipeFallbackUsed,_tmpStalkerRecipeRediscoveryAttempts,_tmpIsActive,_tmpMaxConnections,_tmpExpirationDate,_tmpApiVersion,_tmpAllowedOutputFormatsJson,_tmpEpgSyncMode,_tmpXtreamFastSyncEnabled,_tmpXtreamLiveSyncMode,_tmpM3uVodClassificationEnabled,_tmpStatus,_tmpLastSyncedAt,_tmpCreatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long): ProviderEntity? {
    val _sql: String = "SELECT * FROM providers WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfServerUrl: Int = getColumnIndexOrThrow(_stmt, "server_url")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfM3uUrl: Int = getColumnIndexOrThrow(_stmt, "m3u_url")
        val _columnIndexOfEpgUrl: Int = getColumnIndexOrThrow(_stmt, "epg_url")
        val _columnIndexOfHttpUserAgent: Int = getColumnIndexOrThrow(_stmt, "http_user_agent")
        val _columnIndexOfHttpHeaders: Int = getColumnIndexOrThrow(_stmt, "http_headers")
        val _columnIndexOfStalkerMacAddress: Int = getColumnIndexOrThrow(_stmt, "stalker_mac_address")
        val _columnIndexOfStalkerDeviceProfile: Int = getColumnIndexOrThrow(_stmt, "stalker_device_profile")
        val _columnIndexOfStalkerDeviceTimezone: Int = getColumnIndexOrThrow(_stmt, "stalker_device_timezone")
        val _columnIndexOfStalkerDeviceLocale: Int = getColumnIndexOrThrow(_stmt, "stalker_device_locale")
        val _columnIndexOfStalkerSerialNumber: Int = getColumnIndexOrThrow(_stmt, "stalker_serial_number")
        val _columnIndexOfStalkerDeviceId: Int = getColumnIndexOrThrow(_stmt, "stalker_device_id")
        val _columnIndexOfStalkerDeviceId2: Int = getColumnIndexOrThrow(_stmt, "stalker_device_id2")
        val _columnIndexOfStalkerSignature: Int = getColumnIndexOrThrow(_stmt, "stalker_signature")
        val _columnIndexOfStalkerAdvancedOptionsJson: Int = getColumnIndexOrThrow(_stmt, "stalker_advanced_options_json")
        val _columnIndexOfStalkerAuthMode: Int = getColumnIndexOrThrow(_stmt, "stalker_auth_mode")
        val _columnIndexOfStalkerPortalProfile: Int = getColumnIndexOrThrow(_stmt, "stalker_portal_profile")
        val _columnIndexOfStalkerPortalFingerprint: Int = getColumnIndexOrThrow(_stmt, "stalker_portal_fingerprint")
        val _columnIndexOfStalkerMagPreset: Int = getColumnIndexOrThrow(_stmt, "stalker_mag_preset")
        val _columnIndexOfStalkerLastBootstrapRecipe: Int = getColumnIndexOrThrow(_stmt, "stalker_last_bootstrap_recipe")
        val _columnIndexOfStalkerEndpointPreference: Int = getColumnIndexOrThrow(_stmt, "stalker_endpoint_preference")
        val _columnIndexOfStalkerCookieMode: Int = getColumnIndexOrThrow(_stmt, "stalker_cookie_mode")
        val _columnIndexOfStalkerPlaybackBackendHint: Int = getColumnIndexOrThrow(_stmt, "stalker_playback_backend_hint")
        val _columnIndexOfStalkerLastPlaybackMode: Int = getColumnIndexOrThrow(_stmt, "stalker_last_playback_mode")
        val _columnIndexOfStalkerCredentialsRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_credentials_required")
        val _columnIndexOfStalkerMacRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_mac_required")
        val _columnIndexOfStalkerUsesTemporaryLinks: Int = getColumnIndexOrThrow(_stmt, "stalker_uses_temp_links")
        val _columnIndexOfStalkerModuleRestricted: Int = getColumnIndexOrThrow(_stmt, "stalker_module_restricted")
        val _columnIndexOfStalkerStrictFingerprintRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_strict_fingerprint_required")
        val _columnIndexOfStalkerRecipeFallbackUsed: Int = getColumnIndexOrThrow(_stmt, "stalker_recipe_fallback_used")
        val _columnIndexOfStalkerRecipeRediscoveryAttempts: Int = getColumnIndexOrThrow(_stmt, "stalker_recipe_rediscovery_attempts")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "is_active")
        val _columnIndexOfMaxConnections: Int = getColumnIndexOrThrow(_stmt, "max_connections")
        val _columnIndexOfExpirationDate: Int = getColumnIndexOrThrow(_stmt, "expiration_date")
        val _columnIndexOfApiVersion: Int = getColumnIndexOrThrow(_stmt, "api_version")
        val _columnIndexOfAllowedOutputFormatsJson: Int = getColumnIndexOrThrow(_stmt, "allowed_output_formats_json")
        val _columnIndexOfEpgSyncMode: Int = getColumnIndexOrThrow(_stmt, "epg_sync_mode")
        val _columnIndexOfXtreamFastSyncEnabled: Int = getColumnIndexOrThrow(_stmt, "xtream_fast_sync_enabled")
        val _columnIndexOfXtreamLiveSyncMode: Int = getColumnIndexOrThrow(_stmt, "xtream_live_sync_mode")
        val _columnIndexOfM3uVodClassificationEnabled: Int = getColumnIndexOrThrow(_stmt, "m3u_vod_classification_enabled")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfLastSyncedAt: Int = getColumnIndexOrThrow(_stmt, "last_synced_at")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _result: ProviderEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpType: ProviderType
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfType)
          }
          val _tmp_1: ProviderType? = __roomEnumConverters.toProviderType(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderType', but it was NULL.")
          } else {
            _tmpType = _tmp_1
          }
          val _tmpServerUrl: String
          _tmpServerUrl = _stmt.getText(_columnIndexOfServerUrl)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpM3uUrl: String
          _tmpM3uUrl = _stmt.getText(_columnIndexOfM3uUrl)
          val _tmpEpgUrl: String
          _tmpEpgUrl = _stmt.getText(_columnIndexOfEpgUrl)
          val _tmpHttpUserAgent: String
          _tmpHttpUserAgent = _stmt.getText(_columnIndexOfHttpUserAgent)
          val _tmpHttpHeaders: String
          _tmpHttpHeaders = _stmt.getText(_columnIndexOfHttpHeaders)
          val _tmpStalkerMacAddress: String
          _tmpStalkerMacAddress = _stmt.getText(_columnIndexOfStalkerMacAddress)
          val _tmpStalkerDeviceProfile: String
          _tmpStalkerDeviceProfile = _stmt.getText(_columnIndexOfStalkerDeviceProfile)
          val _tmpStalkerDeviceTimezone: String
          _tmpStalkerDeviceTimezone = _stmt.getText(_columnIndexOfStalkerDeviceTimezone)
          val _tmpStalkerDeviceLocale: String
          _tmpStalkerDeviceLocale = _stmt.getText(_columnIndexOfStalkerDeviceLocale)
          val _tmpStalkerSerialNumber: String
          _tmpStalkerSerialNumber = _stmt.getText(_columnIndexOfStalkerSerialNumber)
          val _tmpStalkerDeviceId: String
          _tmpStalkerDeviceId = _stmt.getText(_columnIndexOfStalkerDeviceId)
          val _tmpStalkerDeviceId2: String
          _tmpStalkerDeviceId2 = _stmt.getText(_columnIndexOfStalkerDeviceId2)
          val _tmpStalkerSignature: String
          _tmpStalkerSignature = _stmt.getText(_columnIndexOfStalkerSignature)
          val _tmpStalkerAdvancedOptionsJson: String
          _tmpStalkerAdvancedOptionsJson = _stmt.getText(_columnIndexOfStalkerAdvancedOptionsJson)
          val _tmpStalkerAuthMode: StalkerAuthMode
          val _tmp_2: String?
          if (_stmt.isNull(_columnIndexOfStalkerAuthMode)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfStalkerAuthMode)
          }
          val _tmp_3: StalkerAuthMode? = __roomEnumConverters.toStalkerAuthMode(_tmp_2)
          if (_tmp_3 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerAuthMode', but it was NULL.")
          } else {
            _tmpStalkerAuthMode = _tmp_3
          }
          val _tmpStalkerPortalProfile: StalkerPortalProfile
          val _tmp_4: String?
          if (_stmt.isNull(_columnIndexOfStalkerPortalProfile)) {
            _tmp_4 = null
          } else {
            _tmp_4 = _stmt.getText(_columnIndexOfStalkerPortalProfile)
          }
          val _tmp_5: StalkerPortalProfile? = __roomEnumConverters.toStalkerPortalProfile(_tmp_4)
          if (_tmp_5 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPortalProfile', but it was NULL.")
          } else {
            _tmpStalkerPortalProfile = _tmp_5
          }
          val _tmpStalkerPortalFingerprint: StalkerPortalFingerprint
          val _tmp_6: String?
          if (_stmt.isNull(_columnIndexOfStalkerPortalFingerprint)) {
            _tmp_6 = null
          } else {
            _tmp_6 = _stmt.getText(_columnIndexOfStalkerPortalFingerprint)
          }
          val _tmp_7: StalkerPortalFingerprint? = __roomEnumConverters.toStalkerPortalFingerprint(_tmp_6)
          if (_tmp_7 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPortalFingerprint', but it was NULL.")
          } else {
            _tmpStalkerPortalFingerprint = _tmp_7
          }
          val _tmpStalkerMagPreset: StalkerMagPreset
          val _tmp_8: String?
          if (_stmt.isNull(_columnIndexOfStalkerMagPreset)) {
            _tmp_8 = null
          } else {
            _tmp_8 = _stmt.getText(_columnIndexOfStalkerMagPreset)
          }
          val _tmp_9: StalkerMagPreset? = __roomEnumConverters.toStalkerMagPreset(_tmp_8)
          if (_tmp_9 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerMagPreset', but it was NULL.")
          } else {
            _tmpStalkerMagPreset = _tmp_9
          }
          val _tmpStalkerLastBootstrapRecipe: StalkerBootstrapRecipe
          val _tmp_10: String?
          if (_stmt.isNull(_columnIndexOfStalkerLastBootstrapRecipe)) {
            _tmp_10 = null
          } else {
            _tmp_10 = _stmt.getText(_columnIndexOfStalkerLastBootstrapRecipe)
          }
          val _tmp_11: StalkerBootstrapRecipe? = __roomEnumConverters.toStalkerBootstrapRecipe(_tmp_10)
          if (_tmp_11 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerBootstrapRecipe', but it was NULL.")
          } else {
            _tmpStalkerLastBootstrapRecipe = _tmp_11
          }
          val _tmpStalkerEndpointPreference: StalkerEndpointPreference
          val _tmp_12: String?
          if (_stmt.isNull(_columnIndexOfStalkerEndpointPreference)) {
            _tmp_12 = null
          } else {
            _tmp_12 = _stmt.getText(_columnIndexOfStalkerEndpointPreference)
          }
          val _tmp_13: StalkerEndpointPreference? = __roomEnumConverters.toStalkerEndpointPreference(_tmp_12)
          if (_tmp_13 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerEndpointPreference', but it was NULL.")
          } else {
            _tmpStalkerEndpointPreference = _tmp_13
          }
          val _tmpStalkerCookieMode: StalkerCookieMode
          val _tmp_14: String?
          if (_stmt.isNull(_columnIndexOfStalkerCookieMode)) {
            _tmp_14 = null
          } else {
            _tmp_14 = _stmt.getText(_columnIndexOfStalkerCookieMode)
          }
          val _tmp_15: StalkerCookieMode? = __roomEnumConverters.toStalkerCookieMode(_tmp_14)
          if (_tmp_15 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerCookieMode', but it was NULL.")
          } else {
            _tmpStalkerCookieMode = _tmp_15
          }
          val _tmpStalkerPlaybackBackendHint: StalkerPlaybackBackendHint
          val _tmp_16: String?
          if (_stmt.isNull(_columnIndexOfStalkerPlaybackBackendHint)) {
            _tmp_16 = null
          } else {
            _tmp_16 = _stmt.getText(_columnIndexOfStalkerPlaybackBackendHint)
          }
          val _tmp_17: StalkerPlaybackBackendHint? = __roomEnumConverters.toStalkerPlaybackBackendHint(_tmp_16)
          if (_tmp_17 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPlaybackBackendHint', but it was NULL.")
          } else {
            _tmpStalkerPlaybackBackendHint = _tmp_17
          }
          val _tmpStalkerLastPlaybackMode: String?
          if (_stmt.isNull(_columnIndexOfStalkerLastPlaybackMode)) {
            _tmpStalkerLastPlaybackMode = null
          } else {
            _tmpStalkerLastPlaybackMode = _stmt.getText(_columnIndexOfStalkerLastPlaybackMode)
          }
          val _tmpStalkerCredentialsRequired: Boolean
          val _tmp_18: Int
          _tmp_18 = _stmt.getLong(_columnIndexOfStalkerCredentialsRequired).toInt()
          _tmpStalkerCredentialsRequired = _tmp_18 != 0
          val _tmpStalkerMacRequired: Boolean
          val _tmp_19: Int
          _tmp_19 = _stmt.getLong(_columnIndexOfStalkerMacRequired).toInt()
          _tmpStalkerMacRequired = _tmp_19 != 0
          val _tmpStalkerUsesTemporaryLinks: Boolean
          val _tmp_20: Int
          _tmp_20 = _stmt.getLong(_columnIndexOfStalkerUsesTemporaryLinks).toInt()
          _tmpStalkerUsesTemporaryLinks = _tmp_20 != 0
          val _tmpStalkerModuleRestricted: Boolean
          val _tmp_21: Int
          _tmp_21 = _stmt.getLong(_columnIndexOfStalkerModuleRestricted).toInt()
          _tmpStalkerModuleRestricted = _tmp_21 != 0
          val _tmpStalkerStrictFingerprintRequired: Boolean
          val _tmp_22: Int
          _tmp_22 = _stmt.getLong(_columnIndexOfStalkerStrictFingerprintRequired).toInt()
          _tmpStalkerStrictFingerprintRequired = _tmp_22 != 0
          val _tmpStalkerRecipeFallbackUsed: Boolean
          val _tmp_23: Int
          _tmp_23 = _stmt.getLong(_columnIndexOfStalkerRecipeFallbackUsed).toInt()
          _tmpStalkerRecipeFallbackUsed = _tmp_23 != 0
          val _tmpStalkerRecipeRediscoveryAttempts: Int
          _tmpStalkerRecipeRediscoveryAttempts = _stmt.getLong(_columnIndexOfStalkerRecipeRediscoveryAttempts).toInt()
          val _tmpIsActive: Boolean
          val _tmp_24: Int
          _tmp_24 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_24 != 0
          val _tmpMaxConnections: Int
          _tmpMaxConnections = _stmt.getLong(_columnIndexOfMaxConnections).toInt()
          val _tmpExpirationDate: Long?
          if (_stmt.isNull(_columnIndexOfExpirationDate)) {
            _tmpExpirationDate = null
          } else {
            _tmpExpirationDate = _stmt.getLong(_columnIndexOfExpirationDate)
          }
          val _tmpApiVersion: String?
          if (_stmt.isNull(_columnIndexOfApiVersion)) {
            _tmpApiVersion = null
          } else {
            _tmpApiVersion = _stmt.getText(_columnIndexOfApiVersion)
          }
          val _tmpAllowedOutputFormatsJson: String
          _tmpAllowedOutputFormatsJson = _stmt.getText(_columnIndexOfAllowedOutputFormatsJson)
          val _tmpEpgSyncMode: ProviderEpgSyncMode
          val _tmp_25: String?
          if (_stmt.isNull(_columnIndexOfEpgSyncMode)) {
            _tmp_25 = null
          } else {
            _tmp_25 = _stmt.getText(_columnIndexOfEpgSyncMode)
          }
          val _tmp_26: ProviderEpgSyncMode? = __roomEnumConverters.toProviderEpgSyncMode(_tmp_25)
          if (_tmp_26 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderEpgSyncMode', but it was NULL.")
          } else {
            _tmpEpgSyncMode = _tmp_26
          }
          val _tmpXtreamFastSyncEnabled: Boolean
          val _tmp_27: Int
          _tmp_27 = _stmt.getLong(_columnIndexOfXtreamFastSyncEnabled).toInt()
          _tmpXtreamFastSyncEnabled = _tmp_27 != 0
          val _tmpXtreamLiveSyncMode: ProviderXtreamLiveSyncMode
          val _tmp_28: String?
          if (_stmt.isNull(_columnIndexOfXtreamLiveSyncMode)) {
            _tmp_28 = null
          } else {
            _tmp_28 = _stmt.getText(_columnIndexOfXtreamLiveSyncMode)
          }
          val _tmp_29: ProviderXtreamLiveSyncMode? = __roomEnumConverters.toProviderXtreamLiveSyncMode(_tmp_28)
          if (_tmp_29 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderXtreamLiveSyncMode', but it was NULL.")
          } else {
            _tmpXtreamLiveSyncMode = _tmp_29
          }
          val _tmpM3uVodClassificationEnabled: Boolean
          val _tmp_30: Int
          _tmp_30 = _stmt.getLong(_columnIndexOfM3uVodClassificationEnabled).toInt()
          _tmpM3uVodClassificationEnabled = _tmp_30 != 0
          val _tmpStatus: ProviderStatus
          val _tmp_31: String?
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmp_31 = null
          } else {
            _tmp_31 = _stmt.getText(_columnIndexOfStatus)
          }
          val _tmp_32: ProviderStatus? = __roomEnumConverters.toProviderStatus(_tmp_31)
          if (_tmp_32 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderStatus', but it was NULL.")
          } else {
            _tmpStatus = _tmp_32
          }
          val _tmpLastSyncedAt: Long
          _tmpLastSyncedAt = _stmt.getLong(_columnIndexOfLastSyncedAt)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _result = ProviderEntity(_tmpId,_tmpName,_tmpType,_tmpServerUrl,_tmpUsername,_tmpPassword,_tmpM3uUrl,_tmpEpgUrl,_tmpHttpUserAgent,_tmpHttpHeaders,_tmpStalkerMacAddress,_tmpStalkerDeviceProfile,_tmpStalkerDeviceTimezone,_tmpStalkerDeviceLocale,_tmpStalkerSerialNumber,_tmpStalkerDeviceId,_tmpStalkerDeviceId2,_tmpStalkerSignature,_tmpStalkerAdvancedOptionsJson,_tmpStalkerAuthMode,_tmpStalkerPortalProfile,_tmpStalkerPortalFingerprint,_tmpStalkerMagPreset,_tmpStalkerLastBootstrapRecipe,_tmpStalkerEndpointPreference,_tmpStalkerCookieMode,_tmpStalkerPlaybackBackendHint,_tmpStalkerLastPlaybackMode,_tmpStalkerCredentialsRequired,_tmpStalkerMacRequired,_tmpStalkerUsesTemporaryLinks,_tmpStalkerModuleRestricted,_tmpStalkerStrictFingerprintRequired,_tmpStalkerRecipeFallbackUsed,_tmpStalkerRecipeRediscoveryAttempts,_tmpIsActive,_tmpMaxConnections,_tmpExpirationDate,_tmpApiVersion,_tmpAllowedOutputFormatsJson,_tmpEpgSyncMode,_tmpXtreamFastSyncEnabled,_tmpXtreamLiveSyncMode,_tmpM3uVodClassificationEnabled,_tmpStatus,_tmpLastSyncedAt,_tmpCreatedAt)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByIds(ids: List<Long>): List<ProviderEntity> {
    val _stringBuilder: StringBuilder = StringBuilder()
    _stringBuilder.append("SELECT * FROM providers WHERE id IN (")
    val _inputSize: Int = ids.size
    appendPlaceholders(_stringBuilder, _inputSize)
    _stringBuilder.append(")")
    val _sql: String = _stringBuilder.toString()
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        for (_item: Long in ids) {
          _stmt.bindLong(_argIndex, _item)
          _argIndex++
        }
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfServerUrl: Int = getColumnIndexOrThrow(_stmt, "server_url")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfM3uUrl: Int = getColumnIndexOrThrow(_stmt, "m3u_url")
        val _columnIndexOfEpgUrl: Int = getColumnIndexOrThrow(_stmt, "epg_url")
        val _columnIndexOfHttpUserAgent: Int = getColumnIndexOrThrow(_stmt, "http_user_agent")
        val _columnIndexOfHttpHeaders: Int = getColumnIndexOrThrow(_stmt, "http_headers")
        val _columnIndexOfStalkerMacAddress: Int = getColumnIndexOrThrow(_stmt, "stalker_mac_address")
        val _columnIndexOfStalkerDeviceProfile: Int = getColumnIndexOrThrow(_stmt, "stalker_device_profile")
        val _columnIndexOfStalkerDeviceTimezone: Int = getColumnIndexOrThrow(_stmt, "stalker_device_timezone")
        val _columnIndexOfStalkerDeviceLocale: Int = getColumnIndexOrThrow(_stmt, "stalker_device_locale")
        val _columnIndexOfStalkerSerialNumber: Int = getColumnIndexOrThrow(_stmt, "stalker_serial_number")
        val _columnIndexOfStalkerDeviceId: Int = getColumnIndexOrThrow(_stmt, "stalker_device_id")
        val _columnIndexOfStalkerDeviceId2: Int = getColumnIndexOrThrow(_stmt, "stalker_device_id2")
        val _columnIndexOfStalkerSignature: Int = getColumnIndexOrThrow(_stmt, "stalker_signature")
        val _columnIndexOfStalkerAdvancedOptionsJson: Int = getColumnIndexOrThrow(_stmt, "stalker_advanced_options_json")
        val _columnIndexOfStalkerAuthMode: Int = getColumnIndexOrThrow(_stmt, "stalker_auth_mode")
        val _columnIndexOfStalkerPortalProfile: Int = getColumnIndexOrThrow(_stmt, "stalker_portal_profile")
        val _columnIndexOfStalkerPortalFingerprint: Int = getColumnIndexOrThrow(_stmt, "stalker_portal_fingerprint")
        val _columnIndexOfStalkerMagPreset: Int = getColumnIndexOrThrow(_stmt, "stalker_mag_preset")
        val _columnIndexOfStalkerLastBootstrapRecipe: Int = getColumnIndexOrThrow(_stmt, "stalker_last_bootstrap_recipe")
        val _columnIndexOfStalkerEndpointPreference: Int = getColumnIndexOrThrow(_stmt, "stalker_endpoint_preference")
        val _columnIndexOfStalkerCookieMode: Int = getColumnIndexOrThrow(_stmt, "stalker_cookie_mode")
        val _columnIndexOfStalkerPlaybackBackendHint: Int = getColumnIndexOrThrow(_stmt, "stalker_playback_backend_hint")
        val _columnIndexOfStalkerLastPlaybackMode: Int = getColumnIndexOrThrow(_stmt, "stalker_last_playback_mode")
        val _columnIndexOfStalkerCredentialsRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_credentials_required")
        val _columnIndexOfStalkerMacRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_mac_required")
        val _columnIndexOfStalkerUsesTemporaryLinks: Int = getColumnIndexOrThrow(_stmt, "stalker_uses_temp_links")
        val _columnIndexOfStalkerModuleRestricted: Int = getColumnIndexOrThrow(_stmt, "stalker_module_restricted")
        val _columnIndexOfStalkerStrictFingerprintRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_strict_fingerprint_required")
        val _columnIndexOfStalkerRecipeFallbackUsed: Int = getColumnIndexOrThrow(_stmt, "stalker_recipe_fallback_used")
        val _columnIndexOfStalkerRecipeRediscoveryAttempts: Int = getColumnIndexOrThrow(_stmt, "stalker_recipe_rediscovery_attempts")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "is_active")
        val _columnIndexOfMaxConnections: Int = getColumnIndexOrThrow(_stmt, "max_connections")
        val _columnIndexOfExpirationDate: Int = getColumnIndexOrThrow(_stmt, "expiration_date")
        val _columnIndexOfApiVersion: Int = getColumnIndexOrThrow(_stmt, "api_version")
        val _columnIndexOfAllowedOutputFormatsJson: Int = getColumnIndexOrThrow(_stmt, "allowed_output_formats_json")
        val _columnIndexOfEpgSyncMode: Int = getColumnIndexOrThrow(_stmt, "epg_sync_mode")
        val _columnIndexOfXtreamFastSyncEnabled: Int = getColumnIndexOrThrow(_stmt, "xtream_fast_sync_enabled")
        val _columnIndexOfXtreamLiveSyncMode: Int = getColumnIndexOrThrow(_stmt, "xtream_live_sync_mode")
        val _columnIndexOfM3uVodClassificationEnabled: Int = getColumnIndexOrThrow(_stmt, "m3u_vod_classification_enabled")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfLastSyncedAt: Int = getColumnIndexOrThrow(_stmt, "last_synced_at")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _result: MutableList<ProviderEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item_1: ProviderEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpType: ProviderType
          val _tmp: String?
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmp = null
          } else {
            _tmp = _stmt.getText(_columnIndexOfType)
          }
          val _tmp_1: ProviderType? = __roomEnumConverters.toProviderType(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderType', but it was NULL.")
          } else {
            _tmpType = _tmp_1
          }
          val _tmpServerUrl: String
          _tmpServerUrl = _stmt.getText(_columnIndexOfServerUrl)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpM3uUrl: String
          _tmpM3uUrl = _stmt.getText(_columnIndexOfM3uUrl)
          val _tmpEpgUrl: String
          _tmpEpgUrl = _stmt.getText(_columnIndexOfEpgUrl)
          val _tmpHttpUserAgent: String
          _tmpHttpUserAgent = _stmt.getText(_columnIndexOfHttpUserAgent)
          val _tmpHttpHeaders: String
          _tmpHttpHeaders = _stmt.getText(_columnIndexOfHttpHeaders)
          val _tmpStalkerMacAddress: String
          _tmpStalkerMacAddress = _stmt.getText(_columnIndexOfStalkerMacAddress)
          val _tmpStalkerDeviceProfile: String
          _tmpStalkerDeviceProfile = _stmt.getText(_columnIndexOfStalkerDeviceProfile)
          val _tmpStalkerDeviceTimezone: String
          _tmpStalkerDeviceTimezone = _stmt.getText(_columnIndexOfStalkerDeviceTimezone)
          val _tmpStalkerDeviceLocale: String
          _tmpStalkerDeviceLocale = _stmt.getText(_columnIndexOfStalkerDeviceLocale)
          val _tmpStalkerSerialNumber: String
          _tmpStalkerSerialNumber = _stmt.getText(_columnIndexOfStalkerSerialNumber)
          val _tmpStalkerDeviceId: String
          _tmpStalkerDeviceId = _stmt.getText(_columnIndexOfStalkerDeviceId)
          val _tmpStalkerDeviceId2: String
          _tmpStalkerDeviceId2 = _stmt.getText(_columnIndexOfStalkerDeviceId2)
          val _tmpStalkerSignature: String
          _tmpStalkerSignature = _stmt.getText(_columnIndexOfStalkerSignature)
          val _tmpStalkerAdvancedOptionsJson: String
          _tmpStalkerAdvancedOptionsJson = _stmt.getText(_columnIndexOfStalkerAdvancedOptionsJson)
          val _tmpStalkerAuthMode: StalkerAuthMode
          val _tmp_2: String?
          if (_stmt.isNull(_columnIndexOfStalkerAuthMode)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getText(_columnIndexOfStalkerAuthMode)
          }
          val _tmp_3: StalkerAuthMode? = __roomEnumConverters.toStalkerAuthMode(_tmp_2)
          if (_tmp_3 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerAuthMode', but it was NULL.")
          } else {
            _tmpStalkerAuthMode = _tmp_3
          }
          val _tmpStalkerPortalProfile: StalkerPortalProfile
          val _tmp_4: String?
          if (_stmt.isNull(_columnIndexOfStalkerPortalProfile)) {
            _tmp_4 = null
          } else {
            _tmp_4 = _stmt.getText(_columnIndexOfStalkerPortalProfile)
          }
          val _tmp_5: StalkerPortalProfile? = __roomEnumConverters.toStalkerPortalProfile(_tmp_4)
          if (_tmp_5 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPortalProfile', but it was NULL.")
          } else {
            _tmpStalkerPortalProfile = _tmp_5
          }
          val _tmpStalkerPortalFingerprint: StalkerPortalFingerprint
          val _tmp_6: String?
          if (_stmt.isNull(_columnIndexOfStalkerPortalFingerprint)) {
            _tmp_6 = null
          } else {
            _tmp_6 = _stmt.getText(_columnIndexOfStalkerPortalFingerprint)
          }
          val _tmp_7: StalkerPortalFingerprint? = __roomEnumConverters.toStalkerPortalFingerprint(_tmp_6)
          if (_tmp_7 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPortalFingerprint', but it was NULL.")
          } else {
            _tmpStalkerPortalFingerprint = _tmp_7
          }
          val _tmpStalkerMagPreset: StalkerMagPreset
          val _tmp_8: String?
          if (_stmt.isNull(_columnIndexOfStalkerMagPreset)) {
            _tmp_8 = null
          } else {
            _tmp_8 = _stmt.getText(_columnIndexOfStalkerMagPreset)
          }
          val _tmp_9: StalkerMagPreset? = __roomEnumConverters.toStalkerMagPreset(_tmp_8)
          if (_tmp_9 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerMagPreset', but it was NULL.")
          } else {
            _tmpStalkerMagPreset = _tmp_9
          }
          val _tmpStalkerLastBootstrapRecipe: StalkerBootstrapRecipe
          val _tmp_10: String?
          if (_stmt.isNull(_columnIndexOfStalkerLastBootstrapRecipe)) {
            _tmp_10 = null
          } else {
            _tmp_10 = _stmt.getText(_columnIndexOfStalkerLastBootstrapRecipe)
          }
          val _tmp_11: StalkerBootstrapRecipe? = __roomEnumConverters.toStalkerBootstrapRecipe(_tmp_10)
          if (_tmp_11 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerBootstrapRecipe', but it was NULL.")
          } else {
            _tmpStalkerLastBootstrapRecipe = _tmp_11
          }
          val _tmpStalkerEndpointPreference: StalkerEndpointPreference
          val _tmp_12: String?
          if (_stmt.isNull(_columnIndexOfStalkerEndpointPreference)) {
            _tmp_12 = null
          } else {
            _tmp_12 = _stmt.getText(_columnIndexOfStalkerEndpointPreference)
          }
          val _tmp_13: StalkerEndpointPreference? = __roomEnumConverters.toStalkerEndpointPreference(_tmp_12)
          if (_tmp_13 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerEndpointPreference', but it was NULL.")
          } else {
            _tmpStalkerEndpointPreference = _tmp_13
          }
          val _tmpStalkerCookieMode: StalkerCookieMode
          val _tmp_14: String?
          if (_stmt.isNull(_columnIndexOfStalkerCookieMode)) {
            _tmp_14 = null
          } else {
            _tmp_14 = _stmt.getText(_columnIndexOfStalkerCookieMode)
          }
          val _tmp_15: StalkerCookieMode? = __roomEnumConverters.toStalkerCookieMode(_tmp_14)
          if (_tmp_15 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerCookieMode', but it was NULL.")
          } else {
            _tmpStalkerCookieMode = _tmp_15
          }
          val _tmpStalkerPlaybackBackendHint: StalkerPlaybackBackendHint
          val _tmp_16: String?
          if (_stmt.isNull(_columnIndexOfStalkerPlaybackBackendHint)) {
            _tmp_16 = null
          } else {
            _tmp_16 = _stmt.getText(_columnIndexOfStalkerPlaybackBackendHint)
          }
          val _tmp_17: StalkerPlaybackBackendHint? = __roomEnumConverters.toStalkerPlaybackBackendHint(_tmp_16)
          if (_tmp_17 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPlaybackBackendHint', but it was NULL.")
          } else {
            _tmpStalkerPlaybackBackendHint = _tmp_17
          }
          val _tmpStalkerLastPlaybackMode: String?
          if (_stmt.isNull(_columnIndexOfStalkerLastPlaybackMode)) {
            _tmpStalkerLastPlaybackMode = null
          } else {
            _tmpStalkerLastPlaybackMode = _stmt.getText(_columnIndexOfStalkerLastPlaybackMode)
          }
          val _tmpStalkerCredentialsRequired: Boolean
          val _tmp_18: Int
          _tmp_18 = _stmt.getLong(_columnIndexOfStalkerCredentialsRequired).toInt()
          _tmpStalkerCredentialsRequired = _tmp_18 != 0
          val _tmpStalkerMacRequired: Boolean
          val _tmp_19: Int
          _tmp_19 = _stmt.getLong(_columnIndexOfStalkerMacRequired).toInt()
          _tmpStalkerMacRequired = _tmp_19 != 0
          val _tmpStalkerUsesTemporaryLinks: Boolean
          val _tmp_20: Int
          _tmp_20 = _stmt.getLong(_columnIndexOfStalkerUsesTemporaryLinks).toInt()
          _tmpStalkerUsesTemporaryLinks = _tmp_20 != 0
          val _tmpStalkerModuleRestricted: Boolean
          val _tmp_21: Int
          _tmp_21 = _stmt.getLong(_columnIndexOfStalkerModuleRestricted).toInt()
          _tmpStalkerModuleRestricted = _tmp_21 != 0
          val _tmpStalkerStrictFingerprintRequired: Boolean
          val _tmp_22: Int
          _tmp_22 = _stmt.getLong(_columnIndexOfStalkerStrictFingerprintRequired).toInt()
          _tmpStalkerStrictFingerprintRequired = _tmp_22 != 0
          val _tmpStalkerRecipeFallbackUsed: Boolean
          val _tmp_23: Int
          _tmp_23 = _stmt.getLong(_columnIndexOfStalkerRecipeFallbackUsed).toInt()
          _tmpStalkerRecipeFallbackUsed = _tmp_23 != 0
          val _tmpStalkerRecipeRediscoveryAttempts: Int
          _tmpStalkerRecipeRediscoveryAttempts = _stmt.getLong(_columnIndexOfStalkerRecipeRediscoveryAttempts).toInt()
          val _tmpIsActive: Boolean
          val _tmp_24: Int
          _tmp_24 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_24 != 0
          val _tmpMaxConnections: Int
          _tmpMaxConnections = _stmt.getLong(_columnIndexOfMaxConnections).toInt()
          val _tmpExpirationDate: Long?
          if (_stmt.isNull(_columnIndexOfExpirationDate)) {
            _tmpExpirationDate = null
          } else {
            _tmpExpirationDate = _stmt.getLong(_columnIndexOfExpirationDate)
          }
          val _tmpApiVersion: String?
          if (_stmt.isNull(_columnIndexOfApiVersion)) {
            _tmpApiVersion = null
          } else {
            _tmpApiVersion = _stmt.getText(_columnIndexOfApiVersion)
          }
          val _tmpAllowedOutputFormatsJson: String
          _tmpAllowedOutputFormatsJson = _stmt.getText(_columnIndexOfAllowedOutputFormatsJson)
          val _tmpEpgSyncMode: ProviderEpgSyncMode
          val _tmp_25: String?
          if (_stmt.isNull(_columnIndexOfEpgSyncMode)) {
            _tmp_25 = null
          } else {
            _tmp_25 = _stmt.getText(_columnIndexOfEpgSyncMode)
          }
          val _tmp_26: ProviderEpgSyncMode? = __roomEnumConverters.toProviderEpgSyncMode(_tmp_25)
          if (_tmp_26 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderEpgSyncMode', but it was NULL.")
          } else {
            _tmpEpgSyncMode = _tmp_26
          }
          val _tmpXtreamFastSyncEnabled: Boolean
          val _tmp_27: Int
          _tmp_27 = _stmt.getLong(_columnIndexOfXtreamFastSyncEnabled).toInt()
          _tmpXtreamFastSyncEnabled = _tmp_27 != 0
          val _tmpXtreamLiveSyncMode: ProviderXtreamLiveSyncMode
          val _tmp_28: String?
          if (_stmt.isNull(_columnIndexOfXtreamLiveSyncMode)) {
            _tmp_28 = null
          } else {
            _tmp_28 = _stmt.getText(_columnIndexOfXtreamLiveSyncMode)
          }
          val _tmp_29: ProviderXtreamLiveSyncMode? = __roomEnumConverters.toProviderXtreamLiveSyncMode(_tmp_28)
          if (_tmp_29 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderXtreamLiveSyncMode', but it was NULL.")
          } else {
            _tmpXtreamLiveSyncMode = _tmp_29
          }
          val _tmpM3uVodClassificationEnabled: Boolean
          val _tmp_30: Int
          _tmp_30 = _stmt.getLong(_columnIndexOfM3uVodClassificationEnabled).toInt()
          _tmpM3uVodClassificationEnabled = _tmp_30 != 0
          val _tmpStatus: ProviderStatus
          val _tmp_31: String?
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmp_31 = null
          } else {
            _tmp_31 = _stmt.getText(_columnIndexOfStatus)
          }
          val _tmp_32: ProviderStatus? = __roomEnumConverters.toProviderStatus(_tmp_31)
          if (_tmp_32 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderStatus', but it was NULL.")
          } else {
            _tmpStatus = _tmp_32
          }
          val _tmpLastSyncedAt: Long
          _tmpLastSyncedAt = _stmt.getLong(_columnIndexOfLastSyncedAt)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item_1 = ProviderEntity(_tmpId,_tmpName,_tmpType,_tmpServerUrl,_tmpUsername,_tmpPassword,_tmpM3uUrl,_tmpEpgUrl,_tmpHttpUserAgent,_tmpHttpHeaders,_tmpStalkerMacAddress,_tmpStalkerDeviceProfile,_tmpStalkerDeviceTimezone,_tmpStalkerDeviceLocale,_tmpStalkerSerialNumber,_tmpStalkerDeviceId,_tmpStalkerDeviceId2,_tmpStalkerSignature,_tmpStalkerAdvancedOptionsJson,_tmpStalkerAuthMode,_tmpStalkerPortalProfile,_tmpStalkerPortalFingerprint,_tmpStalkerMagPreset,_tmpStalkerLastBootstrapRecipe,_tmpStalkerEndpointPreference,_tmpStalkerCookieMode,_tmpStalkerPlaybackBackendHint,_tmpStalkerLastPlaybackMode,_tmpStalkerCredentialsRequired,_tmpStalkerMacRequired,_tmpStalkerUsesTemporaryLinks,_tmpStalkerModuleRestricted,_tmpStalkerStrictFingerprintRequired,_tmpStalkerRecipeFallbackUsed,_tmpStalkerRecipeRediscoveryAttempts,_tmpIsActive,_tmpMaxConnections,_tmpExpirationDate,_tmpApiVersion,_tmpAllowedOutputFormatsJson,_tmpEpgSyncMode,_tmpXtreamFastSyncEnabled,_tmpXtreamLiveSyncMode,_tmpM3uVodClassificationEnabled,_tmpStatus,_tmpLastSyncedAt,_tmpCreatedAt)
          _result.add(_item_1)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByTypeSync(type: ProviderType): List<ProviderEntity> {
    val _sql: String = "SELECT * FROM providers WHERE type = ?"
    return performBlocking(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: String? = __roomEnumConverters.fromProviderType(type)
        if (_tmp == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindText(_argIndex, _tmp)
        }
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfType: Int = getColumnIndexOrThrow(_stmt, "type")
        val _columnIndexOfServerUrl: Int = getColumnIndexOrThrow(_stmt, "server_url")
        val _columnIndexOfUsername: Int = getColumnIndexOrThrow(_stmt, "username")
        val _columnIndexOfPassword: Int = getColumnIndexOrThrow(_stmt, "password")
        val _columnIndexOfM3uUrl: Int = getColumnIndexOrThrow(_stmt, "m3u_url")
        val _columnIndexOfEpgUrl: Int = getColumnIndexOrThrow(_stmt, "epg_url")
        val _columnIndexOfHttpUserAgent: Int = getColumnIndexOrThrow(_stmt, "http_user_agent")
        val _columnIndexOfHttpHeaders: Int = getColumnIndexOrThrow(_stmt, "http_headers")
        val _columnIndexOfStalkerMacAddress: Int = getColumnIndexOrThrow(_stmt, "stalker_mac_address")
        val _columnIndexOfStalkerDeviceProfile: Int = getColumnIndexOrThrow(_stmt, "stalker_device_profile")
        val _columnIndexOfStalkerDeviceTimezone: Int = getColumnIndexOrThrow(_stmt, "stalker_device_timezone")
        val _columnIndexOfStalkerDeviceLocale: Int = getColumnIndexOrThrow(_stmt, "stalker_device_locale")
        val _columnIndexOfStalkerSerialNumber: Int = getColumnIndexOrThrow(_stmt, "stalker_serial_number")
        val _columnIndexOfStalkerDeviceId: Int = getColumnIndexOrThrow(_stmt, "stalker_device_id")
        val _columnIndexOfStalkerDeviceId2: Int = getColumnIndexOrThrow(_stmt, "stalker_device_id2")
        val _columnIndexOfStalkerSignature: Int = getColumnIndexOrThrow(_stmt, "stalker_signature")
        val _columnIndexOfStalkerAdvancedOptionsJson: Int = getColumnIndexOrThrow(_stmt, "stalker_advanced_options_json")
        val _columnIndexOfStalkerAuthMode: Int = getColumnIndexOrThrow(_stmt, "stalker_auth_mode")
        val _columnIndexOfStalkerPortalProfile: Int = getColumnIndexOrThrow(_stmt, "stalker_portal_profile")
        val _columnIndexOfStalkerPortalFingerprint: Int = getColumnIndexOrThrow(_stmt, "stalker_portal_fingerprint")
        val _columnIndexOfStalkerMagPreset: Int = getColumnIndexOrThrow(_stmt, "stalker_mag_preset")
        val _columnIndexOfStalkerLastBootstrapRecipe: Int = getColumnIndexOrThrow(_stmt, "stalker_last_bootstrap_recipe")
        val _columnIndexOfStalkerEndpointPreference: Int = getColumnIndexOrThrow(_stmt, "stalker_endpoint_preference")
        val _columnIndexOfStalkerCookieMode: Int = getColumnIndexOrThrow(_stmt, "stalker_cookie_mode")
        val _columnIndexOfStalkerPlaybackBackendHint: Int = getColumnIndexOrThrow(_stmt, "stalker_playback_backend_hint")
        val _columnIndexOfStalkerLastPlaybackMode: Int = getColumnIndexOrThrow(_stmt, "stalker_last_playback_mode")
        val _columnIndexOfStalkerCredentialsRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_credentials_required")
        val _columnIndexOfStalkerMacRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_mac_required")
        val _columnIndexOfStalkerUsesTemporaryLinks: Int = getColumnIndexOrThrow(_stmt, "stalker_uses_temp_links")
        val _columnIndexOfStalkerModuleRestricted: Int = getColumnIndexOrThrow(_stmt, "stalker_module_restricted")
        val _columnIndexOfStalkerStrictFingerprintRequired: Int = getColumnIndexOrThrow(_stmt, "stalker_strict_fingerprint_required")
        val _columnIndexOfStalkerRecipeFallbackUsed: Int = getColumnIndexOrThrow(_stmt, "stalker_recipe_fallback_used")
        val _columnIndexOfStalkerRecipeRediscoveryAttempts: Int = getColumnIndexOrThrow(_stmt, "stalker_recipe_rediscovery_attempts")
        val _columnIndexOfIsActive: Int = getColumnIndexOrThrow(_stmt, "is_active")
        val _columnIndexOfMaxConnections: Int = getColumnIndexOrThrow(_stmt, "max_connections")
        val _columnIndexOfExpirationDate: Int = getColumnIndexOrThrow(_stmt, "expiration_date")
        val _columnIndexOfApiVersion: Int = getColumnIndexOrThrow(_stmt, "api_version")
        val _columnIndexOfAllowedOutputFormatsJson: Int = getColumnIndexOrThrow(_stmt, "allowed_output_formats_json")
        val _columnIndexOfEpgSyncMode: Int = getColumnIndexOrThrow(_stmt, "epg_sync_mode")
        val _columnIndexOfXtreamFastSyncEnabled: Int = getColumnIndexOrThrow(_stmt, "xtream_fast_sync_enabled")
        val _columnIndexOfXtreamLiveSyncMode: Int = getColumnIndexOrThrow(_stmt, "xtream_live_sync_mode")
        val _columnIndexOfM3uVodClassificationEnabled: Int = getColumnIndexOrThrow(_stmt, "m3u_vod_classification_enabled")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfLastSyncedAt: Int = getColumnIndexOrThrow(_stmt, "last_synced_at")
        val _columnIndexOfCreatedAt: Int = getColumnIndexOrThrow(_stmt, "created_at")
        val _result: MutableList<ProviderEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: ProviderEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpType: ProviderType
          val _tmp_1: String?
          if (_stmt.isNull(_columnIndexOfType)) {
            _tmp_1 = null
          } else {
            _tmp_1 = _stmt.getText(_columnIndexOfType)
          }
          val _tmp_2: ProviderType? = __roomEnumConverters.toProviderType(_tmp_1)
          if (_tmp_2 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderType', but it was NULL.")
          } else {
            _tmpType = _tmp_2
          }
          val _tmpServerUrl: String
          _tmpServerUrl = _stmt.getText(_columnIndexOfServerUrl)
          val _tmpUsername: String
          _tmpUsername = _stmt.getText(_columnIndexOfUsername)
          val _tmpPassword: String
          _tmpPassword = _stmt.getText(_columnIndexOfPassword)
          val _tmpM3uUrl: String
          _tmpM3uUrl = _stmt.getText(_columnIndexOfM3uUrl)
          val _tmpEpgUrl: String
          _tmpEpgUrl = _stmt.getText(_columnIndexOfEpgUrl)
          val _tmpHttpUserAgent: String
          _tmpHttpUserAgent = _stmt.getText(_columnIndexOfHttpUserAgent)
          val _tmpHttpHeaders: String
          _tmpHttpHeaders = _stmt.getText(_columnIndexOfHttpHeaders)
          val _tmpStalkerMacAddress: String
          _tmpStalkerMacAddress = _stmt.getText(_columnIndexOfStalkerMacAddress)
          val _tmpStalkerDeviceProfile: String
          _tmpStalkerDeviceProfile = _stmt.getText(_columnIndexOfStalkerDeviceProfile)
          val _tmpStalkerDeviceTimezone: String
          _tmpStalkerDeviceTimezone = _stmt.getText(_columnIndexOfStalkerDeviceTimezone)
          val _tmpStalkerDeviceLocale: String
          _tmpStalkerDeviceLocale = _stmt.getText(_columnIndexOfStalkerDeviceLocale)
          val _tmpStalkerSerialNumber: String
          _tmpStalkerSerialNumber = _stmt.getText(_columnIndexOfStalkerSerialNumber)
          val _tmpStalkerDeviceId: String
          _tmpStalkerDeviceId = _stmt.getText(_columnIndexOfStalkerDeviceId)
          val _tmpStalkerDeviceId2: String
          _tmpStalkerDeviceId2 = _stmt.getText(_columnIndexOfStalkerDeviceId2)
          val _tmpStalkerSignature: String
          _tmpStalkerSignature = _stmt.getText(_columnIndexOfStalkerSignature)
          val _tmpStalkerAdvancedOptionsJson: String
          _tmpStalkerAdvancedOptionsJson = _stmt.getText(_columnIndexOfStalkerAdvancedOptionsJson)
          val _tmpStalkerAuthMode: StalkerAuthMode
          val _tmp_3: String?
          if (_stmt.isNull(_columnIndexOfStalkerAuthMode)) {
            _tmp_3 = null
          } else {
            _tmp_3 = _stmt.getText(_columnIndexOfStalkerAuthMode)
          }
          val _tmp_4: StalkerAuthMode? = __roomEnumConverters.toStalkerAuthMode(_tmp_3)
          if (_tmp_4 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerAuthMode', but it was NULL.")
          } else {
            _tmpStalkerAuthMode = _tmp_4
          }
          val _tmpStalkerPortalProfile: StalkerPortalProfile
          val _tmp_5: String?
          if (_stmt.isNull(_columnIndexOfStalkerPortalProfile)) {
            _tmp_5 = null
          } else {
            _tmp_5 = _stmt.getText(_columnIndexOfStalkerPortalProfile)
          }
          val _tmp_6: StalkerPortalProfile? = __roomEnumConverters.toStalkerPortalProfile(_tmp_5)
          if (_tmp_6 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPortalProfile', but it was NULL.")
          } else {
            _tmpStalkerPortalProfile = _tmp_6
          }
          val _tmpStalkerPortalFingerprint: StalkerPortalFingerprint
          val _tmp_7: String?
          if (_stmt.isNull(_columnIndexOfStalkerPortalFingerprint)) {
            _tmp_7 = null
          } else {
            _tmp_7 = _stmt.getText(_columnIndexOfStalkerPortalFingerprint)
          }
          val _tmp_8: StalkerPortalFingerprint? = __roomEnumConverters.toStalkerPortalFingerprint(_tmp_7)
          if (_tmp_8 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPortalFingerprint', but it was NULL.")
          } else {
            _tmpStalkerPortalFingerprint = _tmp_8
          }
          val _tmpStalkerMagPreset: StalkerMagPreset
          val _tmp_9: String?
          if (_stmt.isNull(_columnIndexOfStalkerMagPreset)) {
            _tmp_9 = null
          } else {
            _tmp_9 = _stmt.getText(_columnIndexOfStalkerMagPreset)
          }
          val _tmp_10: StalkerMagPreset? = __roomEnumConverters.toStalkerMagPreset(_tmp_9)
          if (_tmp_10 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerMagPreset', but it was NULL.")
          } else {
            _tmpStalkerMagPreset = _tmp_10
          }
          val _tmpStalkerLastBootstrapRecipe: StalkerBootstrapRecipe
          val _tmp_11: String?
          if (_stmt.isNull(_columnIndexOfStalkerLastBootstrapRecipe)) {
            _tmp_11 = null
          } else {
            _tmp_11 = _stmt.getText(_columnIndexOfStalkerLastBootstrapRecipe)
          }
          val _tmp_12: StalkerBootstrapRecipe? = __roomEnumConverters.toStalkerBootstrapRecipe(_tmp_11)
          if (_tmp_12 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerBootstrapRecipe', but it was NULL.")
          } else {
            _tmpStalkerLastBootstrapRecipe = _tmp_12
          }
          val _tmpStalkerEndpointPreference: StalkerEndpointPreference
          val _tmp_13: String?
          if (_stmt.isNull(_columnIndexOfStalkerEndpointPreference)) {
            _tmp_13 = null
          } else {
            _tmp_13 = _stmt.getText(_columnIndexOfStalkerEndpointPreference)
          }
          val _tmp_14: StalkerEndpointPreference? = __roomEnumConverters.toStalkerEndpointPreference(_tmp_13)
          if (_tmp_14 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerEndpointPreference', but it was NULL.")
          } else {
            _tmpStalkerEndpointPreference = _tmp_14
          }
          val _tmpStalkerCookieMode: StalkerCookieMode
          val _tmp_15: String?
          if (_stmt.isNull(_columnIndexOfStalkerCookieMode)) {
            _tmp_15 = null
          } else {
            _tmp_15 = _stmt.getText(_columnIndexOfStalkerCookieMode)
          }
          val _tmp_16: StalkerCookieMode? = __roomEnumConverters.toStalkerCookieMode(_tmp_15)
          if (_tmp_16 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerCookieMode', but it was NULL.")
          } else {
            _tmpStalkerCookieMode = _tmp_16
          }
          val _tmpStalkerPlaybackBackendHint: StalkerPlaybackBackendHint
          val _tmp_17: String?
          if (_stmt.isNull(_columnIndexOfStalkerPlaybackBackendHint)) {
            _tmp_17 = null
          } else {
            _tmp_17 = _stmt.getText(_columnIndexOfStalkerPlaybackBackendHint)
          }
          val _tmp_18: StalkerPlaybackBackendHint? = __roomEnumConverters.toStalkerPlaybackBackendHint(_tmp_17)
          if (_tmp_18 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.StalkerPlaybackBackendHint', but it was NULL.")
          } else {
            _tmpStalkerPlaybackBackendHint = _tmp_18
          }
          val _tmpStalkerLastPlaybackMode: String?
          if (_stmt.isNull(_columnIndexOfStalkerLastPlaybackMode)) {
            _tmpStalkerLastPlaybackMode = null
          } else {
            _tmpStalkerLastPlaybackMode = _stmt.getText(_columnIndexOfStalkerLastPlaybackMode)
          }
          val _tmpStalkerCredentialsRequired: Boolean
          val _tmp_19: Int
          _tmp_19 = _stmt.getLong(_columnIndexOfStalkerCredentialsRequired).toInt()
          _tmpStalkerCredentialsRequired = _tmp_19 != 0
          val _tmpStalkerMacRequired: Boolean
          val _tmp_20: Int
          _tmp_20 = _stmt.getLong(_columnIndexOfStalkerMacRequired).toInt()
          _tmpStalkerMacRequired = _tmp_20 != 0
          val _tmpStalkerUsesTemporaryLinks: Boolean
          val _tmp_21: Int
          _tmp_21 = _stmt.getLong(_columnIndexOfStalkerUsesTemporaryLinks).toInt()
          _tmpStalkerUsesTemporaryLinks = _tmp_21 != 0
          val _tmpStalkerModuleRestricted: Boolean
          val _tmp_22: Int
          _tmp_22 = _stmt.getLong(_columnIndexOfStalkerModuleRestricted).toInt()
          _tmpStalkerModuleRestricted = _tmp_22 != 0
          val _tmpStalkerStrictFingerprintRequired: Boolean
          val _tmp_23: Int
          _tmp_23 = _stmt.getLong(_columnIndexOfStalkerStrictFingerprintRequired).toInt()
          _tmpStalkerStrictFingerprintRequired = _tmp_23 != 0
          val _tmpStalkerRecipeFallbackUsed: Boolean
          val _tmp_24: Int
          _tmp_24 = _stmt.getLong(_columnIndexOfStalkerRecipeFallbackUsed).toInt()
          _tmpStalkerRecipeFallbackUsed = _tmp_24 != 0
          val _tmpStalkerRecipeRediscoveryAttempts: Int
          _tmpStalkerRecipeRediscoveryAttempts = _stmt.getLong(_columnIndexOfStalkerRecipeRediscoveryAttempts).toInt()
          val _tmpIsActive: Boolean
          val _tmp_25: Int
          _tmp_25 = _stmt.getLong(_columnIndexOfIsActive).toInt()
          _tmpIsActive = _tmp_25 != 0
          val _tmpMaxConnections: Int
          _tmpMaxConnections = _stmt.getLong(_columnIndexOfMaxConnections).toInt()
          val _tmpExpirationDate: Long?
          if (_stmt.isNull(_columnIndexOfExpirationDate)) {
            _tmpExpirationDate = null
          } else {
            _tmpExpirationDate = _stmt.getLong(_columnIndexOfExpirationDate)
          }
          val _tmpApiVersion: String?
          if (_stmt.isNull(_columnIndexOfApiVersion)) {
            _tmpApiVersion = null
          } else {
            _tmpApiVersion = _stmt.getText(_columnIndexOfApiVersion)
          }
          val _tmpAllowedOutputFormatsJson: String
          _tmpAllowedOutputFormatsJson = _stmt.getText(_columnIndexOfAllowedOutputFormatsJson)
          val _tmpEpgSyncMode: ProviderEpgSyncMode
          val _tmp_26: String?
          if (_stmt.isNull(_columnIndexOfEpgSyncMode)) {
            _tmp_26 = null
          } else {
            _tmp_26 = _stmt.getText(_columnIndexOfEpgSyncMode)
          }
          val _tmp_27: ProviderEpgSyncMode? = __roomEnumConverters.toProviderEpgSyncMode(_tmp_26)
          if (_tmp_27 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderEpgSyncMode', but it was NULL.")
          } else {
            _tmpEpgSyncMode = _tmp_27
          }
          val _tmpXtreamFastSyncEnabled: Boolean
          val _tmp_28: Int
          _tmp_28 = _stmt.getLong(_columnIndexOfXtreamFastSyncEnabled).toInt()
          _tmpXtreamFastSyncEnabled = _tmp_28 != 0
          val _tmpXtreamLiveSyncMode: ProviderXtreamLiveSyncMode
          val _tmp_29: String?
          if (_stmt.isNull(_columnIndexOfXtreamLiveSyncMode)) {
            _tmp_29 = null
          } else {
            _tmp_29 = _stmt.getText(_columnIndexOfXtreamLiveSyncMode)
          }
          val _tmp_30: ProviderXtreamLiveSyncMode? = __roomEnumConverters.toProviderXtreamLiveSyncMode(_tmp_29)
          if (_tmp_30 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderXtreamLiveSyncMode', but it was NULL.")
          } else {
            _tmpXtreamLiveSyncMode = _tmp_30
          }
          val _tmpM3uVodClassificationEnabled: Boolean
          val _tmp_31: Int
          _tmp_31 = _stmt.getLong(_columnIndexOfM3uVodClassificationEnabled).toInt()
          _tmpM3uVodClassificationEnabled = _tmp_31 != 0
          val _tmpStatus: ProviderStatus
          val _tmp_32: String?
          if (_stmt.isNull(_columnIndexOfStatus)) {
            _tmp_32 = null
          } else {
            _tmp_32 = _stmt.getText(_columnIndexOfStatus)
          }
          val _tmp_33: ProviderStatus? = __roomEnumConverters.toProviderStatus(_tmp_32)
          if (_tmp_33 == null) {
            error("Expected NON-NULL 'com.vopo.domain.model.ProviderStatus', but it was NULL.")
          } else {
            _tmpStatus = _tmp_33
          }
          val _tmpLastSyncedAt: Long
          _tmpLastSyncedAt = _stmt.getLong(_columnIndexOfLastSyncedAt)
          val _tmpCreatedAt: Long
          _tmpCreatedAt = _stmt.getLong(_columnIndexOfCreatedAt)
          _item = ProviderEntity(_tmpId,_tmpName,_tmpType,_tmpServerUrl,_tmpUsername,_tmpPassword,_tmpM3uUrl,_tmpEpgUrl,_tmpHttpUserAgent,_tmpHttpHeaders,_tmpStalkerMacAddress,_tmpStalkerDeviceProfile,_tmpStalkerDeviceTimezone,_tmpStalkerDeviceLocale,_tmpStalkerSerialNumber,_tmpStalkerDeviceId,_tmpStalkerDeviceId2,_tmpStalkerSignature,_tmpStalkerAdvancedOptionsJson,_tmpStalkerAuthMode,_tmpStalkerPortalProfile,_tmpStalkerPortalFingerprint,_tmpStalkerMagPreset,_tmpStalkerLastBootstrapRecipe,_tmpStalkerEndpointPreference,_tmpStalkerCookieMode,_tmpStalkerPlaybackBackendHint,_tmpStalkerLastPlaybackMode,_tmpStalkerCredentialsRequired,_tmpStalkerMacRequired,_tmpStalkerUsesTemporaryLinks,_tmpStalkerModuleRestricted,_tmpStalkerStrictFingerprintRequired,_tmpStalkerRecipeFallbackUsed,_tmpStalkerRecipeRediscoveryAttempts,_tmpIsActive,_tmpMaxConnections,_tmpExpirationDate,_tmpApiVersion,_tmpAllowedOutputFormatsJson,_tmpEpgSyncMode,_tmpXtreamFastSyncEnabled,_tmpXtreamLiveSyncMode,_tmpM3uVodClassificationEnabled,_tmpStatus,_tmpLastSyncedAt,_tmpCreatedAt)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun delete(id: Long) {
    val _sql: String = "DELETE FROM providers WHERE id = ?"
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

  public override suspend fun deactivateAll() {
    val _sql: String = "UPDATE providers SET is_active = 0"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun activate(id: Long) {
    val _sql: String = "UPDATE providers SET is_active = 1 WHERE id = ?"
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

  public override suspend fun updateSyncTime(id: Long, timestamp: Long) {
    val _sql: String = "UPDATE providers SET last_synced_at = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, timestamp)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateEpgUrl(id: Long, epgUrl: String) {
    val _sql: String = "UPDATE providers SET epg_url = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, epgUrl)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
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
