package com.vopo.`data`.local

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.FtsTableInfo
import androidx.room.util.TableInfo
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.vopo.`data`.local.dao.CatalogSyncDao
import com.vopo.`data`.local.dao.CatalogSyncDao_Impl
import com.vopo.`data`.local.dao.CategoryDao
import com.vopo.`data`.local.dao.CategoryDao_Impl
import com.vopo.`data`.local.dao.ChannelDao
import com.vopo.`data`.local.dao.ChannelDao_Impl
import com.vopo.`data`.local.dao.ChannelEpgMappingDao
import com.vopo.`data`.local.dao.ChannelEpgMappingDao_Impl
import com.vopo.`data`.local.dao.ChannelPreferenceDao
import com.vopo.`data`.local.dao.ChannelPreferenceDao_Impl
import com.vopo.`data`.local.dao.CombinedM3uProfileDao
import com.vopo.`data`.local.dao.CombinedM3uProfileDao_Impl
import com.vopo.`data`.local.dao.CombinedM3uProfileMemberDao
import com.vopo.`data`.local.dao.CombinedM3uProfileMemberDao_Impl
import com.vopo.`data`.local.dao.DownloadDao
import com.vopo.`data`.local.dao.DownloadDao_Impl
import com.vopo.`data`.local.dao.EpgChannelDao
import com.vopo.`data`.local.dao.EpgChannelDao_Impl
import com.vopo.`data`.local.dao.EpgProgrammeDao
import com.vopo.`data`.local.dao.EpgProgrammeDao_Impl
import com.vopo.`data`.local.dao.EpgSourceDao
import com.vopo.`data`.local.dao.EpgSourceDao_Impl
import com.vopo.`data`.local.dao.EpisodeDao
import com.vopo.`data`.local.dao.EpisodeDao_Impl
import com.vopo.`data`.local.dao.FavoriteDao
import com.vopo.`data`.local.dao.FavoriteDao_Impl
import com.vopo.`data`.local.dao.MovieCategoryHydrationDao
import com.vopo.`data`.local.dao.MovieCategoryHydrationDao_Impl
import com.vopo.`data`.local.dao.MovieDao
import com.vopo.`data`.local.dao.MovieDao_Impl
import com.vopo.`data`.local.dao.PlaybackCompatibilityDao
import com.vopo.`data`.local.dao.PlaybackCompatibilityDao_Impl
import com.vopo.`data`.local.dao.PlaybackHistoryDao
import com.vopo.`data`.local.dao.PlaybackHistoryDao_Impl
import com.vopo.`data`.local.dao.ProgramDao
import com.vopo.`data`.local.dao.ProgramDao_Impl
import com.vopo.`data`.local.dao.ProgramReminderDao
import com.vopo.`data`.local.dao.ProgramReminderDao_Impl
import com.vopo.`data`.local.dao.ProviderDao
import com.vopo.`data`.local.dao.ProviderDao_Impl
import com.vopo.`data`.local.dao.ProviderEpgSourceDao
import com.vopo.`data`.local.dao.ProviderEpgSourceDao_Impl
import com.vopo.`data`.local.dao.RecordingRunDao
import com.vopo.`data`.local.dao.RecordingRunDao_Impl
import com.vopo.`data`.local.dao.RecordingScheduleDao
import com.vopo.`data`.local.dao.RecordingScheduleDao_Impl
import com.vopo.`data`.local.dao.RecordingStorageDao
import com.vopo.`data`.local.dao.RecordingStorageDao_Impl
import com.vopo.`data`.local.dao.SearchDao
import com.vopo.`data`.local.dao.SearchDao_Impl
import com.vopo.`data`.local.dao.SearchHistoryDao
import com.vopo.`data`.local.dao.SearchHistoryDao_Impl
import com.vopo.`data`.local.dao.SeriesCategoryHydrationDao
import com.vopo.`data`.local.dao.SeriesCategoryHydrationDao_Impl
import com.vopo.`data`.local.dao.SeriesDao
import com.vopo.`data`.local.dao.SeriesDao_Impl
import com.vopo.`data`.local.dao.SyncMetadataDao
import com.vopo.`data`.local.dao.SyncMetadataDao_Impl
import com.vopo.`data`.local.dao.TmdbIdentityDao
import com.vopo.`data`.local.dao.TmdbIdentityDao_Impl
import com.vopo.`data`.local.dao.VirtualGroupDao
import com.vopo.`data`.local.dao.VirtualGroupDao_Impl
import com.vopo.`data`.local.dao.XtreamContentIndexDao
import com.vopo.`data`.local.dao.XtreamContentIndexDao_Impl
import com.vopo.`data`.local.dao.XtreamIndexJobDao
import com.vopo.`data`.local.dao.XtreamIndexJobDao_Impl
import com.vopo.`data`.local.dao.XtreamLiveOnboardingDao
import com.vopo.`data`.local.dao.XtreamLiveOnboardingDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass
import androidx.room.util.FtsTableInfo.Companion.read as ftsTableInfoRead
import androidx.room.util.TableInfo.Companion.read as tableInfoRead

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class VopoDatabase_Impl : VopoDatabase() {
  private val _providerDao: Lazy<ProviderDao> = lazy {
    ProviderDao_Impl(this)
  }

  private val _channelDao: Lazy<ChannelDao> = lazy {
    ChannelDao_Impl(this)
  }

  private val _channelPreferenceDao: Lazy<ChannelPreferenceDao> = lazy {
    ChannelPreferenceDao_Impl(this)
  }

  private val _movieDao: Lazy<MovieDao> = lazy {
    MovieDao_Impl(this)
  }

  private val _seriesDao: Lazy<SeriesDao> = lazy {
    SeriesDao_Impl(this)
  }

  private val _episodeDao: Lazy<EpisodeDao> = lazy {
    EpisodeDao_Impl(this)
  }

  private val _categoryDao: Lazy<CategoryDao> = lazy {
    CategoryDao_Impl(this)
  }

  private val _catalogSyncDao: Lazy<CatalogSyncDao> = lazy {
    CatalogSyncDao_Impl(this)
  }

  private val _programDao: Lazy<ProgramDao> = lazy {
    ProgramDao_Impl(this)
  }

  private val _favoriteDao: Lazy<FavoriteDao> = lazy {
    FavoriteDao_Impl(this)
  }

  private val _virtualGroupDao: Lazy<VirtualGroupDao> = lazy {
    VirtualGroupDao_Impl(this)
  }

  private val _playbackHistoryDao: Lazy<PlaybackHistoryDao> = lazy {
    PlaybackHistoryDao_Impl(this)
  }

  private val _tmdbIdentityDao: Lazy<TmdbIdentityDao> = lazy {
    TmdbIdentityDao_Impl(this)
  }

  private val _searchHistoryDao: Lazy<SearchHistoryDao> = lazy {
    SearchHistoryDao_Impl(this)
  }

  private val _searchDao: Lazy<SearchDao> = lazy {
    SearchDao_Impl(this)
  }

  private val _syncMetadataDao: Lazy<SyncMetadataDao> = lazy {
    SyncMetadataDao_Impl(this)
  }

  private val _movieCategoryHydrationDao: Lazy<MovieCategoryHydrationDao> = lazy {
    MovieCategoryHydrationDao_Impl(this)
  }

  private val _seriesCategoryHydrationDao: Lazy<SeriesCategoryHydrationDao> = lazy {
    SeriesCategoryHydrationDao_Impl(this)
  }

  private val _epgSourceDao: Lazy<EpgSourceDao> = lazy {
    EpgSourceDao_Impl(this)
  }

  private val _providerEpgSourceDao: Lazy<ProviderEpgSourceDao> = lazy {
    ProviderEpgSourceDao_Impl(this)
  }

  private val _epgChannelDao: Lazy<EpgChannelDao> = lazy {
    EpgChannelDao_Impl(this)
  }

  private val _epgProgrammeDao: Lazy<EpgProgrammeDao> = lazy {
    EpgProgrammeDao_Impl(this)
  }

  private val _channelEpgMappingDao: Lazy<ChannelEpgMappingDao> = lazy {
    ChannelEpgMappingDao_Impl(this)
  }

  private val _combinedM3uProfileDao: Lazy<CombinedM3uProfileDao> = lazy {
    CombinedM3uProfileDao_Impl(this)
  }

  private val _combinedM3uProfileMemberDao: Lazy<CombinedM3uProfileMemberDao> = lazy {
    CombinedM3uProfileMemberDao_Impl(this)
  }

  private val _recordingScheduleDao: Lazy<RecordingScheduleDao> = lazy {
    RecordingScheduleDao_Impl(this)
  }

  private val _recordingRunDao: Lazy<RecordingRunDao> = lazy {
    RecordingRunDao_Impl(this)
  }

  private val _programReminderDao: Lazy<ProgramReminderDao> = lazy {
    ProgramReminderDao_Impl(this)
  }

  private val _recordingStorageDao: Lazy<RecordingStorageDao> = lazy {
    RecordingStorageDao_Impl(this)
  }

  private val _playbackCompatibilityDao: Lazy<PlaybackCompatibilityDao> = lazy {
    PlaybackCompatibilityDao_Impl(this)
  }

  private val _xtreamContentIndexDao: Lazy<XtreamContentIndexDao> = lazy {
    XtreamContentIndexDao_Impl(this)
  }

  private val _xtreamIndexJobDao: Lazy<XtreamIndexJobDao> = lazy {
    XtreamIndexJobDao_Impl(this)
  }

  private val _xtreamLiveOnboardingDao: Lazy<XtreamLiveOnboardingDao> = lazy {
    XtreamLiveOnboardingDao_Impl(this)
  }

  private val _downloadDao: Lazy<DownloadDao> = lazy {
    DownloadDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(61, "edf501986892b75630f4c6c292fe89e5", "2fc4d216fcf53b4c7d58f095ad14ad65") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `providers` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `type` TEXT NOT NULL, `server_url` TEXT NOT NULL, `username` TEXT NOT NULL, `password` TEXT NOT NULL, `m3u_url` TEXT NOT NULL, `epg_url` TEXT NOT NULL, `http_user_agent` TEXT NOT NULL, `http_headers` TEXT NOT NULL, `stalker_mac_address` TEXT NOT NULL, `stalker_device_profile` TEXT NOT NULL, `stalker_device_timezone` TEXT NOT NULL, `stalker_device_locale` TEXT NOT NULL, `stalker_serial_number` TEXT NOT NULL, `stalker_device_id` TEXT NOT NULL, `stalker_device_id2` TEXT NOT NULL, `stalker_signature` TEXT NOT NULL, `stalker_advanced_options_json` TEXT NOT NULL, `stalker_auth_mode` TEXT NOT NULL, `stalker_portal_profile` TEXT NOT NULL, `stalker_portal_fingerprint` TEXT NOT NULL, `stalker_mag_preset` TEXT NOT NULL, `stalker_last_bootstrap_recipe` TEXT NOT NULL, `stalker_endpoint_preference` TEXT NOT NULL, `stalker_cookie_mode` TEXT NOT NULL, `stalker_playback_backend_hint` TEXT NOT NULL, `stalker_last_playback_mode` TEXT, `stalker_credentials_required` INTEGER NOT NULL, `stalker_mac_required` INTEGER NOT NULL, `stalker_uses_temp_links` INTEGER NOT NULL, `stalker_module_restricted` INTEGER NOT NULL, `stalker_strict_fingerprint_required` INTEGER NOT NULL, `stalker_recipe_fallback_used` INTEGER NOT NULL, `stalker_recipe_rediscovery_attempts` INTEGER NOT NULL, `is_active` INTEGER NOT NULL, `max_connections` INTEGER NOT NULL, `expiration_date` INTEGER, `api_version` TEXT, `allowed_output_formats_json` TEXT NOT NULL, `epg_sync_mode` TEXT NOT NULL, `xtream_fast_sync_enabled` INTEGER NOT NULL, `xtream_live_sync_mode` TEXT NOT NULL, `m3u_vod_classification_enabled` INTEGER NOT NULL, `status` TEXT NOT NULL, `last_synced_at` INTEGER NOT NULL, `created_at` INTEGER NOT NULL)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_providers_server_url_username_stalker_mac_address` ON `providers` (`server_url`, `username`, `stalker_mac_address`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `channels` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `stream_id` INTEGER NOT NULL, `name` TEXT NOT NULL, `logo_url` TEXT, `group_title` TEXT, `category_id` INTEGER, `category_name` TEXT, `stream_url` TEXT NOT NULL, `epg_channel_id` TEXT, `number` INTEGER NOT NULL, `catch_up_supported` INTEGER NOT NULL, `catch_up_days` INTEGER NOT NULL, `catchUpSource` TEXT, `provider_id` INTEGER NOT NULL, `is_adult` INTEGER NOT NULL, `is_user_protected` INTEGER NOT NULL, `logical_group_id` TEXT NOT NULL, `error_count` INTEGER NOT NULL, `quality_options_json` TEXT, `sync_fingerprint` TEXT NOT NULL, FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_channels_provider_id` ON `channels` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_channels_provider_id_category_id` ON `channels` (`provider_id`, `category_id`)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_channels_provider_id_stream_id` ON `channels` (`provider_id`, `stream_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_channels_logical_group_id` ON `channels` (`logical_group_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_channels_provider_id_category_id_logical_group_id` ON `channels` (`provider_id`, `category_id`, `logical_group_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `channel_preferences` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `channel_id` INTEGER NOT NULL, `aspect_ratio` TEXT, `audio_video_offset_ms` INTEGER, `updated_at` INTEGER NOT NULL, FOREIGN KEY(`channel_id`) REFERENCES `channels`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_channel_preferences_channel_id` ON `channel_preferences` (`channel_id`)")
        connection.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `channels_fts` USING FTS4(`name` TEXT NOT NULL, content=`channels`)")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_channels_fts_BEFORE_UPDATE BEFORE UPDATE ON `channels` BEGIN DELETE FROM `channels_fts` WHERE `docid`=OLD.`rowid`; END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_channels_fts_BEFORE_DELETE BEFORE DELETE ON `channels` BEGIN DELETE FROM `channels_fts` WHERE `docid`=OLD.`rowid`; END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_channels_fts_AFTER_UPDATE AFTER UPDATE ON `channels` BEGIN INSERT INTO `channels_fts`(`docid`, `name`) VALUES (NEW.`rowid`, NEW.`name`); END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_channels_fts_AFTER_INSERT AFTER INSERT ON `channels` BEGIN INSERT INTO `channels_fts`(`docid`, `name`) VALUES (NEW.`rowid`, NEW.`name`); END")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `movies` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `stream_id` INTEGER NOT NULL, `name` TEXT NOT NULL, `poster_url` TEXT, `backdrop_url` TEXT, `category_id` INTEGER, `category_name` TEXT, `stream_url` TEXT NOT NULL, `container_extension` TEXT, `plot` TEXT, `cast` TEXT, `director` TEXT, `genre` TEXT, `release_date` TEXT, `duration` TEXT, `duration_seconds` INTEGER NOT NULL, `rating` REAL NOT NULL, `year` TEXT, `tmdb_id` INTEGER, `youtube_trailer` TEXT, `provider_id` INTEGER NOT NULL, `watch_progress` INTEGER NOT NULL, `watch_count` INTEGER NOT NULL, `last_watched_at` INTEGER NOT NULL, `is_adult` INTEGER NOT NULL, `is_user_protected` INTEGER NOT NULL, `sync_fingerprint` TEXT NOT NULL, `added_at` INTEGER NOT NULL, `cache_state` TEXT NOT NULL, `detail_hydrated_at` INTEGER NOT NULL, `remote_stale_at` INTEGER NOT NULL, FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_movies_provider_id` ON `movies` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_movies_provider_id_category_id` ON `movies` (`provider_id`, `category_id`)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_movies_provider_id_stream_id` ON `movies` (`provider_id`, `stream_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_movies_provider_id_name_id` ON `movies` (`provider_id`, `name`, `id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_movies_provider_id_category_id_name_id` ON `movies` (`provider_id`, `category_id`, `name`, `id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_movies_provider_id_rating_name_id` ON `movies` (`provider_id`, `rating`, `name`, `id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_movies_provider_id_added_at_release_date_name_id` ON `movies` (`provider_id`, `added_at`, `release_date`, `name`, `id`)")
        connection.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `movies_fts` USING FTS4(`name` TEXT NOT NULL, content=`movies`)")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_movies_fts_BEFORE_UPDATE BEFORE UPDATE ON `movies` BEGIN DELETE FROM `movies_fts` WHERE `docid`=OLD.`rowid`; END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_movies_fts_BEFORE_DELETE BEFORE DELETE ON `movies` BEGIN DELETE FROM `movies_fts` WHERE `docid`=OLD.`rowid`; END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_movies_fts_AFTER_UPDATE AFTER UPDATE ON `movies` BEGIN INSERT INTO `movies_fts`(`docid`, `name`) VALUES (NEW.`rowid`, NEW.`name`); END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_movies_fts_AFTER_INSERT AFTER INSERT ON `movies` BEGIN INSERT INTO `movies_fts`(`docid`, `name`) VALUES (NEW.`rowid`, NEW.`name`); END")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `series` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `series_id` INTEGER NOT NULL, `provider_series_id` TEXT, `name` TEXT NOT NULL, `poster_url` TEXT, `backdrop_url` TEXT, `category_id` INTEGER, `category_name` TEXT, `plot` TEXT, `cast` TEXT, `director` TEXT, `genre` TEXT, `release_date` TEXT, `rating` REAL NOT NULL, `tmdb_id` INTEGER, `youtube_trailer` TEXT, `episode_run_time` TEXT, `last_modified` INTEGER NOT NULL, `provider_id` INTEGER NOT NULL, `is_adult` INTEGER NOT NULL, `is_user_protected` INTEGER NOT NULL, `sync_fingerprint` TEXT NOT NULL, `cache_state` TEXT NOT NULL, `detail_hydrated_at` INTEGER NOT NULL, `remote_stale_at` INTEGER NOT NULL, FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_series_provider_id` ON `series` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_series_provider_id_category_id` ON `series` (`provider_id`, `category_id`)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_series_provider_id_series_id` ON `series` (`provider_id`, `series_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_series_provider_id_name_id` ON `series` (`provider_id`, `name`, `id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_series_provider_id_category_id_name_id` ON `series` (`provider_id`, `category_id`, `name`, `id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_series_provider_id_rating_name_id` ON `series` (`provider_id`, `rating`, `name`, `id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_series_provider_id_last_modified_name_id` ON `series` (`provider_id`, `last_modified`, `name`, `id`)")
        connection.execSQL("CREATE VIRTUAL TABLE IF NOT EXISTS `series_fts` USING FTS4(`name` TEXT NOT NULL, content=`series`)")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_series_fts_BEFORE_UPDATE BEFORE UPDATE ON `series` BEGIN DELETE FROM `series_fts` WHERE `docid`=OLD.`rowid`; END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_series_fts_BEFORE_DELETE BEFORE DELETE ON `series` BEGIN DELETE FROM `series_fts` WHERE `docid`=OLD.`rowid`; END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_series_fts_AFTER_UPDATE AFTER UPDATE ON `series` BEGIN INSERT INTO `series_fts`(`docid`, `name`) VALUES (NEW.`rowid`, NEW.`name`); END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_series_fts_AFTER_INSERT AFTER INSERT ON `series` BEGIN INSERT INTO `series_fts`(`docid`, `name`) VALUES (NEW.`rowid`, NEW.`name`); END")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `episodes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `episode_id` INTEGER NOT NULL, `title` TEXT NOT NULL, `episode_number` INTEGER NOT NULL, `season_number` INTEGER NOT NULL, `stream_url` TEXT NOT NULL, `container_extension` TEXT, `cover_url` TEXT, `plot` TEXT, `duration` TEXT, `duration_seconds` INTEGER NOT NULL, `rating` REAL NOT NULL, `release_date` TEXT, `series_id` INTEGER NOT NULL, `provider_id` INTEGER NOT NULL, `watch_progress` INTEGER NOT NULL, `last_watched_at` INTEGER NOT NULL, `is_adult` INTEGER NOT NULL, `is_user_protected` INTEGER NOT NULL, FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`series_id`) REFERENCES `series`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_episodes_series_id` ON `episodes` (`series_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_episodes_provider_id` ON `episodes` (`provider_id`)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_episodes_provider_id_episode_id` ON `episodes` (`provider_id`, `episode_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `categories` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `category_id` INTEGER NOT NULL, `name` TEXT NOT NULL, `parent_id` INTEGER, `type` TEXT NOT NULL, `provider_id` INTEGER NOT NULL, `is_adult` INTEGER NOT NULL, `is_user_protected` INTEGER NOT NULL, `sync_fingerprint` TEXT NOT NULL, FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_categories_provider_id` ON `categories` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_categories_provider_id_type` ON `categories` (`provider_id`, `type`)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_categories_provider_id_category_id_type` ON `categories` (`provider_id`, `category_id`, `type`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `channel_import_stage` (`session_id` INTEGER NOT NULL, `provider_id` INTEGER NOT NULL, `stream_id` INTEGER NOT NULL, `name` TEXT NOT NULL, `logo_url` TEXT, `group_title` TEXT, `category_id` INTEGER, `category_name` TEXT, `stream_url` TEXT NOT NULL, `epg_channel_id` TEXT, `number` INTEGER NOT NULL, `catch_up_supported` INTEGER NOT NULL, `catch_up_days` INTEGER NOT NULL, `catchUpSource` TEXT, `is_adult` INTEGER NOT NULL, `logical_group_id` TEXT NOT NULL, `error_count` INTEGER NOT NULL, `sync_fingerprint` TEXT NOT NULL, PRIMARY KEY(`session_id`, `provider_id`, `stream_id`), FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_channel_import_stage_provider_id` ON `channel_import_stage` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_channel_import_stage_session_id_provider_id` ON `channel_import_stage` (`session_id`, `provider_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `movie_import_stage` (`session_id` INTEGER NOT NULL, `provider_id` INTEGER NOT NULL, `stream_id` INTEGER NOT NULL, `name` TEXT NOT NULL, `poster_url` TEXT, `backdrop_url` TEXT, `category_id` INTEGER, `category_name` TEXT, `stream_url` TEXT NOT NULL, `container_extension` TEXT, `plot` TEXT, `cast` TEXT, `director` TEXT, `genre` TEXT, `release_date` TEXT, `duration` TEXT, `duration_seconds` INTEGER NOT NULL, `rating` REAL NOT NULL, `year` TEXT, `tmdb_id` INTEGER, `youtube_trailer` TEXT, `is_adult` INTEGER NOT NULL, `sync_fingerprint` TEXT NOT NULL, `added_at` INTEGER NOT NULL, PRIMARY KEY(`session_id`, `provider_id`, `stream_id`), FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_movie_import_stage_provider_id` ON `movie_import_stage` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_movie_import_stage_session_id_provider_id` ON `movie_import_stage` (`session_id`, `provider_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `series_import_stage` (`session_id` INTEGER NOT NULL, `provider_id` INTEGER NOT NULL, `series_id` INTEGER NOT NULL, `provider_series_id` TEXT, `provider_series_key` TEXT NOT NULL, `name` TEXT NOT NULL, `poster_url` TEXT, `backdrop_url` TEXT, `category_id` INTEGER, `category_name` TEXT, `plot` TEXT, `cast` TEXT, `director` TEXT, `genre` TEXT, `release_date` TEXT, `rating` REAL NOT NULL, `tmdb_id` INTEGER, `youtube_trailer` TEXT, `episode_run_time` TEXT, `last_modified` INTEGER NOT NULL, `is_adult` INTEGER NOT NULL, `sync_fingerprint` TEXT NOT NULL, PRIMARY KEY(`session_id`, `provider_id`, `provider_series_key`), FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_series_import_stage_provider_id` ON `series_import_stage` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_series_import_stage_session_id_provider_id` ON `series_import_stage` (`session_id`, `provider_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `category_import_stage` (`session_id` INTEGER NOT NULL, `provider_id` INTEGER NOT NULL, `category_id` INTEGER NOT NULL, `name` TEXT NOT NULL, `parent_id` INTEGER, `type` TEXT NOT NULL, `is_adult` INTEGER NOT NULL, `sync_fingerprint` TEXT NOT NULL, PRIMARY KEY(`session_id`, `provider_id`, `category_id`, `type`), FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_category_import_stage_provider_id` ON `category_import_stage` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_category_import_stage_session_id_provider_id` ON `category_import_stage` (`session_id`, `provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_category_import_stage_provider_id_type` ON `category_import_stage` (`provider_id`, `type`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `programs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `provider_id` INTEGER NOT NULL, `channel_id` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `start_time` INTEGER NOT NULL, `end_time` INTEGER NOT NULL, `lang` TEXT NOT NULL, `rating` TEXT, `image_url` TEXT, `genre` TEXT, `category` TEXT, `has_archive` INTEGER NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_programs_provider_id` ON `programs` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_programs_provider_id_channel_id` ON `programs` (`provider_id`, `channel_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_programs_provider_id_end_time_channel_id` ON `programs` (`provider_id`, `end_time`, `channel_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_programs_provider_id_start_time_end_time` ON `programs` (`provider_id`, `start_time`, `end_time`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_programs_start_time` ON `programs` (`start_time`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_programs_provider_id_channel_id_start_time` ON `programs` (`provider_id`, `channel_id`, `start_time`)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_programs_provider_id_channel_id_start_time_end_time` ON `programs` (`provider_id`, `channel_id`, `start_time`, `end_time`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `favorites` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `provider_id` INTEGER NOT NULL, `content_id` INTEGER NOT NULL, `content_type` TEXT NOT NULL, `position` INTEGER NOT NULL, `group_id` INTEGER, `group_key` INTEGER NOT NULL, `added_at` INTEGER NOT NULL, FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`group_id`) REFERENCES `virtual_groups`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_favorites_provider_id_content_id_content_type_group_key` ON `favorites` (`provider_id`, `content_id`, `content_type`, `group_key`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_favorites_provider_id_content_type_group_id` ON `favorites` (`provider_id`, `content_type`, `group_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_favorites_group_id_position` ON `favorites` (`group_id`, `position`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `virtual_groups` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `provider_id` INTEGER NOT NULL, `name` TEXT NOT NULL, `icon_emoji` TEXT, `position` INTEGER NOT NULL, `created_at` INTEGER NOT NULL, `content_type` TEXT NOT NULL, FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_virtual_groups_provider_id_content_type` ON `virtual_groups` (`provider_id`, `content_type`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_virtual_groups_position` ON `virtual_groups` (`position`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_virtual_groups_content_type` ON `virtual_groups` (`content_type`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `playback_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `content_id` INTEGER NOT NULL, `content_type` TEXT NOT NULL, `provider_id` INTEGER NOT NULL, `title` TEXT NOT NULL, `poster_url` TEXT, `stream_url` TEXT NOT NULL, `resume_position_ms` INTEGER NOT NULL, `total_duration_ms` INTEGER NOT NULL, `last_watched_at` INTEGER NOT NULL, `watch_count` INTEGER NOT NULL, `watched_status` TEXT NOT NULL, `series_id` INTEGER, `season_number` INTEGER, `episode_number` INTEGER, FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_playback_history_content_id_content_type_provider_id` ON `playback_history` (`content_id`, `content_type`, `provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_playback_history_last_watched_at` ON `playback_history` (`last_watched_at`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_playback_history_provider_id` ON `playback_history` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_playback_history_provider_id_content_type_content_id` ON `playback_history` (`provider_id`, `content_type`, `content_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_playback_history_provider_id_content_type_last_watched_at` ON `playback_history` (`provider_id`, `content_type`, `last_watched_at`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `tmdb_identity` (`tmdb_id` INTEGER NOT NULL, `content_type` TEXT NOT NULL, `canonical_provider_id` INTEGER NOT NULL, `first_seen_at` INTEGER NOT NULL, PRIMARY KEY(`tmdb_id`, `content_type`), FOREIGN KEY(`canonical_provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_tmdb_identity_content_type` ON `tmdb_identity` (`content_type`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_tmdb_identity_canonical_provider_id` ON `tmdb_identity` (`canonical_provider_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `search_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `query` TEXT NOT NULL, `content_scope` TEXT NOT NULL, `provider_id` INTEGER NOT NULL, `used_at` INTEGER NOT NULL, `use_count` INTEGER NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_search_history_content_scope_provider_id_used_at` ON `search_history` (`content_scope`, `provider_id`, `used_at`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_search_history_used_at` ON `search_history` (`used_at`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_search_history_provider_id` ON `search_history` (`provider_id`)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_search_history_query_content_scope_provider_id` ON `search_history` (`query`, `content_scope`, `provider_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `sync_metadata` (`provider_id` INTEGER NOT NULL, `last_live_sync` INTEGER NOT NULL, `last_live_success` INTEGER NOT NULL, `last_movie_sync` INTEGER NOT NULL, `last_series_sync` INTEGER NOT NULL, `last_series_success` INTEGER NOT NULL, `last_epg_sync` INTEGER NOT NULL, `last_epg_success` INTEGER NOT NULL, `last_movie_attempt` INTEGER NOT NULL, `last_movie_success` INTEGER NOT NULL, `last_movie_partial` INTEGER NOT NULL, `live_count` INTEGER NOT NULL, `movie_count` INTEGER NOT NULL, `series_count` INTEGER NOT NULL, `epg_count` INTEGER NOT NULL, `last_sync_status` TEXT NOT NULL, `movie_sync_mode` TEXT NOT NULL, `movie_warnings_count` INTEGER NOT NULL, `movie_catalog_stale` INTEGER NOT NULL, `live_avoid_full_until` INTEGER NOT NULL, `movie_avoid_full_until` INTEGER NOT NULL, `series_avoid_full_until` INTEGER NOT NULL, `live_sequential_failures_remembered` INTEGER NOT NULL, `live_healthy_sync_streak` INTEGER NOT NULL, `movie_parallel_failures_remembered` INTEGER NOT NULL, `movie_healthy_sync_streak` INTEGER NOT NULL, `series_sequential_failures_remembered` INTEGER NOT NULL, `series_healthy_sync_streak` INTEGER NOT NULL, PRIMARY KEY(`provider_id`), FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `movie_category_hydration` (`provider_id` INTEGER NOT NULL, `category_id` INTEGER NOT NULL, `last_hydrated_at` INTEGER NOT NULL, `item_count` INTEGER NOT NULL, `last_status` TEXT NOT NULL, `last_error` TEXT, `last_loaded_page` INTEGER NOT NULL, `last_attempted_page` INTEGER NOT NULL, `last_successful_page` INTEGER NOT NULL, `total_pages` INTEGER NOT NULL, `is_complete` INTEGER NOT NULL, `page_size` INTEGER NOT NULL, `retry_after_ms` INTEGER NOT NULL, `failure_count` INTEGER NOT NULL, `retry_budget_remaining` INTEGER NOT NULL, `last_page_fingerprint` TEXT, PRIMARY KEY(`provider_id`, `category_id`), FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_movie_category_hydration_provider_id` ON `movie_category_hydration` (`provider_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `series_category_hydration` (`provider_id` INTEGER NOT NULL, `category_id` INTEGER NOT NULL, `last_hydrated_at` INTEGER NOT NULL, `item_count` INTEGER NOT NULL, `last_status` TEXT NOT NULL, `last_error` TEXT, `last_loaded_page` INTEGER NOT NULL, `last_attempted_page` INTEGER NOT NULL, `last_successful_page` INTEGER NOT NULL, `total_pages` INTEGER NOT NULL, `is_complete` INTEGER NOT NULL, `page_size` INTEGER NOT NULL, `retry_after_ms` INTEGER NOT NULL, `failure_count` INTEGER NOT NULL, `retry_budget_remaining` INTEGER NOT NULL, `last_page_fingerprint` TEXT, PRIMARY KEY(`provider_id`, `category_id`), FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_series_category_hydration_provider_id` ON `series_category_hydration` (`provider_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `epg_sources` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `url` TEXT NOT NULL, `enabled` INTEGER NOT NULL, `last_refresh_at` INTEGER NOT NULL, `last_success_at` INTEGER NOT NULL, `last_error` TEXT, `priority` INTEGER NOT NULL, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, `etag` TEXT, `last_modified_header` TEXT)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_epg_sources_url` ON `epg_sources` (`url`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `provider_epg_sources` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `provider_id` INTEGER NOT NULL, `epg_source_id` INTEGER NOT NULL, `priority` INTEGER NOT NULL, `enabled` INTEGER NOT NULL, FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`epg_source_id`) REFERENCES `epg_sources`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_provider_epg_sources_provider_id_epg_source_id` ON `provider_epg_sources` (`provider_id`, `epg_source_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_provider_epg_sources_epg_source_id` ON `provider_epg_sources` (`epg_source_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `epg_channels` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `epg_source_id` INTEGER NOT NULL, `xmltv_channel_id` TEXT NOT NULL, `display_name` TEXT NOT NULL, `normalized_name` TEXT NOT NULL, `icon_url` TEXT, FOREIGN KEY(`epg_source_id`) REFERENCES `epg_sources`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_epg_channels_epg_source_id_xmltv_channel_id` ON `epg_channels` (`epg_source_id`, `xmltv_channel_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_epg_channels_epg_source_id` ON `epg_channels` (`epg_source_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_epg_channels_normalized_name` ON `epg_channels` (`normalized_name`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `epg_programmes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `epg_source_id` INTEGER NOT NULL, `xmltv_channel_id` TEXT NOT NULL, `start_time` INTEGER NOT NULL, `end_time` INTEGER NOT NULL, `title` TEXT NOT NULL, `subtitle` TEXT, `description` TEXT NOT NULL, `category` TEXT, `lang` TEXT NOT NULL, `rating` TEXT, `image_url` TEXT, `episode_info` TEXT, FOREIGN KEY(`epg_source_id`) REFERENCES `epg_sources`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_epg_programmes_epg_source_id_xmltv_channel_id_start_time` ON `epg_programmes` (`epg_source_id`, `xmltv_channel_id`, `start_time`)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_epg_programmes_epg_source_id_xmltv_channel_id_start_time_end_time` ON `epg_programmes` (`epg_source_id`, `xmltv_channel_id`, `start_time`, `end_time`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_epg_programmes_epg_source_id` ON `epg_programmes` (`epg_source_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_epg_programmes_start_time` ON `epg_programmes` (`start_time`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `channel_epg_mappings` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `provider_channel_id` INTEGER NOT NULL, `provider_id` INTEGER NOT NULL, `source_type` TEXT NOT NULL, `epg_source_id` INTEGER, `xmltv_channel_id` TEXT, `match_type` TEXT, `confidence` REAL NOT NULL, `matched_at` INTEGER NOT NULL, `failed_attempts` INTEGER NOT NULL, `source` TEXT, `is_manual_override` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_channel_epg_mappings_provider_id_provider_channel_id` ON `channel_epg_mappings` (`provider_id`, `provider_channel_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_channel_epg_mappings_provider_id` ON `channel_epg_mappings` (`provider_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `combined_m3u_profiles` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `enabled` INTEGER NOT NULL, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `combined_m3u_profile_members` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `profile_id` INTEGER NOT NULL, `provider_id` INTEGER NOT NULL, `priority` INTEGER NOT NULL, `enabled` INTEGER NOT NULL, FOREIGN KEY(`profile_id`) REFERENCES `combined_m3u_profiles`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_combined_m3u_profile_members_profile_id` ON `combined_m3u_profile_members` (`profile_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_combined_m3u_profile_members_provider_id` ON `combined_m3u_profile_members` (`provider_id`)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_combined_m3u_profile_members_profile_id_provider_id` ON `combined_m3u_profile_members` (`profile_id`, `provider_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `recording_schedules` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `provider_id` INTEGER NOT NULL, `channel_id` INTEGER NOT NULL, `channel_name` TEXT NOT NULL, `stream_url` TEXT NOT NULL, `program_title` TEXT, `requested_start_ms` INTEGER NOT NULL, `requested_end_ms` INTEGER NOT NULL, `recurrence` TEXT NOT NULL, `recurring_rule_id` TEXT, `enabled` INTEGER NOT NULL, `is_manual` INTEGER NOT NULL, `priority` INTEGER NOT NULL, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_recording_schedules_provider_id` ON `recording_schedules` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_recording_schedules_enabled_requested_start_ms` ON `recording_schedules` (`enabled`, `requested_start_ms`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_recording_schedules_recurring_rule_id` ON `recording_schedules` (`recurring_rule_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `recording_runs` (`id` TEXT NOT NULL, `schedule_id` INTEGER NOT NULL, `provider_id` INTEGER NOT NULL, `channel_id` INTEGER NOT NULL, `channel_name` TEXT NOT NULL, `stream_url` TEXT NOT NULL, `program_title` TEXT, `scheduled_start_ms` INTEGER NOT NULL, `scheduled_end_ms` INTEGER NOT NULL, `recurrence` TEXT NOT NULL, `recurring_rule_id` TEXT, `status` TEXT NOT NULL, `source_type` TEXT NOT NULL, `resolved_url` TEXT, `headers_json` TEXT NOT NULL, `user_agent` TEXT, `expiration_time` INTEGER, `provider_label` TEXT, `output_uri` TEXT, `output_display_path` TEXT, `bytes_written` INTEGER NOT NULL, `average_throughput_bps` INTEGER NOT NULL, `retry_count` INTEGER NOT NULL, `last_progress_at_ms` INTEGER, `failure_category` TEXT NOT NULL, `failure_reason` TEXT, `terminal_at_ms` INTEGER, `started_at_ms` INTEGER, `ended_at_ms` INTEGER, `schedule_enabled` INTEGER NOT NULL, `priority` INTEGER NOT NULL, `alarm_start_at_ms` INTEGER, `alarm_stop_at_ms` INTEGER, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`schedule_id`) REFERENCES `recording_schedules`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_recording_runs_schedule_id` ON `recording_runs` (`schedule_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_recording_runs_provider_id` ON `recording_runs` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_recording_runs_status_scheduled_start_ms` ON `recording_runs` (`status`, `scheduled_start_ms`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_recording_runs_alarm_start_at_ms` ON `recording_runs` (`alarm_start_at_ms`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_recording_runs_alarm_stop_at_ms` ON `recording_runs` (`alarm_stop_at_ms`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `program_reminders` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `provider_id` INTEGER NOT NULL, `channel_id` TEXT NOT NULL, `channel_name` TEXT NOT NULL, `program_title` TEXT NOT NULL, `program_start_time` INTEGER NOT NULL, `remind_at` INTEGER NOT NULL, `lead_time_minutes` INTEGER NOT NULL, `is_dismissed` INTEGER NOT NULL, `notified_at` INTEGER, `created_at` INTEGER NOT NULL, FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_program_reminders_provider_id_remind_at` ON `program_reminders` (`provider_id`, `remind_at`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_program_reminders_is_dismissed_notified_at_remind_at` ON `program_reminders` (`is_dismissed`, `notified_at`, `remind_at`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_program_reminders_provider_id_channel_id_program_start_time` ON `program_reminders` (`provider_id`, `channel_id`, `program_start_time`)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_program_reminders_provider_id_channel_id_program_title_program_start_time` ON `program_reminders` (`provider_id`, `channel_id`, `program_title`, `program_start_time`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `recording_storage` (`id` INTEGER NOT NULL, `tree_uri` TEXT, `display_name` TEXT, `output_directory` TEXT, `available_bytes` INTEGER, `is_writable` INTEGER NOT NULL, `file_name_pattern` TEXT NOT NULL, `retention_days` INTEGER, `max_simultaneous_recordings` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `playback_compatibility_records` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `device_fingerprint` TEXT NOT NULL, `device_model` TEXT NOT NULL, `android_sdk` INTEGER NOT NULL, `stream_type` TEXT NOT NULL, `video_mime_type` TEXT NOT NULL, `resolution_bucket` TEXT NOT NULL, `decoder_name` TEXT NOT NULL, `surface_type` TEXT NOT NULL, `failure_type` TEXT NOT NULL, `last_failed_at` INTEGER NOT NULL, `last_succeeded_at` INTEGER NOT NULL, `failure_count` INTEGER NOT NULL, `success_count` INTEGER NOT NULL)")
        connection.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_playback_compatibility_records_device_fingerprint_stream_type_video_mime_type_resolution_bucket_decoder_name_surface_type` ON `playback_compatibility_records` (`device_fingerprint`, `stream_type`, `video_mime_type`, `resolution_bucket`, `decoder_name`, `surface_type`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_playback_compatibility_records_device_fingerprint_stream_type_video_mime_type_resolution_bucket` ON `playback_compatibility_records` (`device_fingerprint`, `stream_type`, `video_mime_type`, `resolution_bucket`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_playback_compatibility_records_last_failed_at` ON `playback_compatibility_records` (`last_failed_at`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_playback_compatibility_records_last_succeeded_at` ON `playback_compatibility_records` (`last_succeeded_at`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `xtream_content_index` (`provider_id` INTEGER NOT NULL, `content_type` TEXT NOT NULL, `remote_id` TEXT NOT NULL, `local_content_id` INTEGER, `name` TEXT NOT NULL, `category_id` INTEGER, `category_name` TEXT, `image_url` TEXT, `container_extension` TEXT, `rating` REAL NOT NULL, `added_at` INTEGER NOT NULL, `remote_updated_at` INTEGER NOT NULL, `is_adult` INTEGER NOT NULL, `indexed_at` INTEGER NOT NULL, `detail_hydrated_at` INTEGER NOT NULL, `stale_state` TEXT NOT NULL, `error_state` TEXT, `sync_fingerprint` TEXT NOT NULL, PRIMARY KEY(`provider_id`, `content_type`, `remote_id`), FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_xtream_content_index_provider_id_content_type` ON `xtream_content_index` (`provider_id`, `content_type`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_xtream_content_index_provider_id_content_type_category_id` ON `xtream_content_index` (`provider_id`, `content_type`, `category_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_xtream_content_index_provider_id_content_type_name` ON `xtream_content_index` (`provider_id`, `content_type`, `name`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_xtream_content_index_provider_id_content_type_local_content_id` ON `xtream_content_index` (`provider_id`, `content_type`, `local_content_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_xtream_content_index_provider_id_indexed_at` ON `xtream_content_index` (`provider_id`, `indexed_at`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_xtream_content_index_stale_state` ON `xtream_content_index` (`stale_state`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `xtream_index_jobs` (`provider_id` INTEGER NOT NULL, `section` TEXT NOT NULL, `state` TEXT NOT NULL, `total_categories` INTEGER NOT NULL, `completed_categories` INTEGER NOT NULL, `next_category_index` INTEGER NOT NULL, `failed_categories` INTEGER NOT NULL, `indexed_rows` INTEGER NOT NULL, `skipped_malformed_rows` INTEGER NOT NULL, `deleted_pruned_rows` INTEGER NOT NULL, `priority_category_id` INTEGER, `priority_requested_at` INTEGER NOT NULL, `last_error` TEXT, `last_attempt_at` INTEGER NOT NULL, `last_success_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, PRIMARY KEY(`provider_id`, `section`), FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_xtream_index_jobs_provider_id` ON `xtream_index_jobs` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_xtream_index_jobs_section` ON `xtream_index_jobs` (`section`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_xtream_index_jobs_state` ON `xtream_index_jobs` (`state`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_xtream_index_jobs_updated_at` ON `xtream_index_jobs` (`updated_at`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `xtream_live_onboarding_state` (`provider_id` INTEGER NOT NULL, `provider_type` TEXT NOT NULL, `content_type` TEXT NOT NULL, `phase` TEXT NOT NULL, `staged_session_id` INTEGER, `import_strategy` TEXT, `next_category_index` INTEGER NOT NULL, `accepted_row_count` INTEGER NOT NULL, `staged_flush_count` INTEGER NOT NULL, `sync_profile_tier` TEXT, `sync_profile_batch_size` INTEGER NOT NULL, `sync_profile_strategy` TEXT, `sync_profile_low_memory` INTEGER NOT NULL, `sync_profile_memory_class_mb` INTEGER NOT NULL, `sync_profile_available_mem_mb` INTEGER NOT NULL, `last_error` TEXT, `created_at` INTEGER NOT NULL, `updated_at` INTEGER NOT NULL, `completed_at` INTEGER, PRIMARY KEY(`provider_id`), FOREIGN KEY(`provider_id`) REFERENCES `providers`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_xtream_live_onboarding_state_provider_id` ON `xtream_live_onboarding_state` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_xtream_live_onboarding_state_phase` ON `xtream_live_onboarding_state` (`phase`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_xtream_live_onboarding_state_updated_at` ON `xtream_live_onboarding_state` (`updated_at`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_xtream_live_onboarding_state_staged_session_id` ON `xtream_live_onboarding_state` (`staged_session_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `downloads` (`id` TEXT NOT NULL, `provider_id` INTEGER NOT NULL, `content_type` TEXT NOT NULL, `content_id` INTEGER NOT NULL, `content_name` TEXT NOT NULL, `stream_url` TEXT NOT NULL, `source_stream_url` TEXT, `source_stream_id` INTEGER, `container_extension` TEXT, `poster_url` TEXT, `output_uri` TEXT, `output_display_path` TEXT, `status` TEXT NOT NULL, `bytes_written` INTEGER NOT NULL, `total_bytes` INTEGER, `supports_resume` INTEGER NOT NULL, `retry_count` INTEGER NOT NULL, `created_at` INTEGER NOT NULL, `completed_at` INTEGER, `failure_reason` TEXT, `series_id` INTEGER, `season_number` INTEGER, `episode_number` INTEGER, PRIMARY KEY(`id`))")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_downloads_status` ON `downloads` (`status`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_downloads_provider_id` ON `downloads` (`provider_id`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_downloads_content_type_content_id` ON `downloads` (`content_type`, `content_id`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'edf501986892b75630f4c6c292fe89e5')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `providers`")
        connection.execSQL("DROP TABLE IF EXISTS `channels`")
        connection.execSQL("DROP TABLE IF EXISTS `channel_preferences`")
        connection.execSQL("DROP TABLE IF EXISTS `channels_fts`")
        connection.execSQL("DROP TABLE IF EXISTS `movies`")
        connection.execSQL("DROP TABLE IF EXISTS `movies_fts`")
        connection.execSQL("DROP TABLE IF EXISTS `series`")
        connection.execSQL("DROP TABLE IF EXISTS `series_fts`")
        connection.execSQL("DROP TABLE IF EXISTS `episodes`")
        connection.execSQL("DROP TABLE IF EXISTS `categories`")
        connection.execSQL("DROP TABLE IF EXISTS `channel_import_stage`")
        connection.execSQL("DROP TABLE IF EXISTS `movie_import_stage`")
        connection.execSQL("DROP TABLE IF EXISTS `series_import_stage`")
        connection.execSQL("DROP TABLE IF EXISTS `category_import_stage`")
        connection.execSQL("DROP TABLE IF EXISTS `programs`")
        connection.execSQL("DROP TABLE IF EXISTS `favorites`")
        connection.execSQL("DROP TABLE IF EXISTS `virtual_groups`")
        connection.execSQL("DROP TABLE IF EXISTS `playback_history`")
        connection.execSQL("DROP TABLE IF EXISTS `tmdb_identity`")
        connection.execSQL("DROP TABLE IF EXISTS `search_history`")
        connection.execSQL("DROP TABLE IF EXISTS `sync_metadata`")
        connection.execSQL("DROP TABLE IF EXISTS `movie_category_hydration`")
        connection.execSQL("DROP TABLE IF EXISTS `series_category_hydration`")
        connection.execSQL("DROP TABLE IF EXISTS `epg_sources`")
        connection.execSQL("DROP TABLE IF EXISTS `provider_epg_sources`")
        connection.execSQL("DROP TABLE IF EXISTS `epg_channels`")
        connection.execSQL("DROP TABLE IF EXISTS `epg_programmes`")
        connection.execSQL("DROP TABLE IF EXISTS `channel_epg_mappings`")
        connection.execSQL("DROP TABLE IF EXISTS `combined_m3u_profiles`")
        connection.execSQL("DROP TABLE IF EXISTS `combined_m3u_profile_members`")
        connection.execSQL("DROP TABLE IF EXISTS `recording_schedules`")
        connection.execSQL("DROP TABLE IF EXISTS `recording_runs`")
        connection.execSQL("DROP TABLE IF EXISTS `program_reminders`")
        connection.execSQL("DROP TABLE IF EXISTS `recording_storage`")
        connection.execSQL("DROP TABLE IF EXISTS `playback_compatibility_records`")
        connection.execSQL("DROP TABLE IF EXISTS `xtream_content_index`")
        connection.execSQL("DROP TABLE IF EXISTS `xtream_index_jobs`")
        connection.execSQL("DROP TABLE IF EXISTS `xtream_live_onboarding_state`")
        connection.execSQL("DROP TABLE IF EXISTS `downloads`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        connection.execSQL("PRAGMA foreign_keys = ON")
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_channels_fts_BEFORE_UPDATE BEFORE UPDATE ON `channels` BEGIN DELETE FROM `channels_fts` WHERE `docid`=OLD.`rowid`; END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_channels_fts_BEFORE_DELETE BEFORE DELETE ON `channels` BEGIN DELETE FROM `channels_fts` WHERE `docid`=OLD.`rowid`; END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_channels_fts_AFTER_UPDATE AFTER UPDATE ON `channels` BEGIN INSERT INTO `channels_fts`(`docid`, `name`) VALUES (NEW.`rowid`, NEW.`name`); END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_channels_fts_AFTER_INSERT AFTER INSERT ON `channels` BEGIN INSERT INTO `channels_fts`(`docid`, `name`) VALUES (NEW.`rowid`, NEW.`name`); END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_movies_fts_BEFORE_UPDATE BEFORE UPDATE ON `movies` BEGIN DELETE FROM `movies_fts` WHERE `docid`=OLD.`rowid`; END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_movies_fts_BEFORE_DELETE BEFORE DELETE ON `movies` BEGIN DELETE FROM `movies_fts` WHERE `docid`=OLD.`rowid`; END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_movies_fts_AFTER_UPDATE AFTER UPDATE ON `movies` BEGIN INSERT INTO `movies_fts`(`docid`, `name`) VALUES (NEW.`rowid`, NEW.`name`); END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_movies_fts_AFTER_INSERT AFTER INSERT ON `movies` BEGIN INSERT INTO `movies_fts`(`docid`, `name`) VALUES (NEW.`rowid`, NEW.`name`); END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_series_fts_BEFORE_UPDATE BEFORE UPDATE ON `series` BEGIN DELETE FROM `series_fts` WHERE `docid`=OLD.`rowid`; END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_series_fts_BEFORE_DELETE BEFORE DELETE ON `series` BEGIN DELETE FROM `series_fts` WHERE `docid`=OLD.`rowid`; END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_series_fts_AFTER_UPDATE AFTER UPDATE ON `series` BEGIN INSERT INTO `series_fts`(`docid`, `name`) VALUES (NEW.`rowid`, NEW.`name`); END")
        connection.execSQL("CREATE TRIGGER IF NOT EXISTS room_fts_content_sync_series_fts_AFTER_INSERT AFTER INSERT ON `series` BEGIN INSERT INTO `series_fts`(`docid`, `name`) VALUES (NEW.`rowid`, NEW.`name`); END")
      }

      public override fun onValidateSchema(connection: SQLiteConnection): RoomOpenDelegate.ValidationResult {
        val _columnsProviders: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsProviders.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("type", TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("server_url", TableInfo.Column("server_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("username", TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("password", TableInfo.Column("password", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("m3u_url", TableInfo.Column("m3u_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("epg_url", TableInfo.Column("epg_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("http_user_agent", TableInfo.Column("http_user_agent", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("http_headers", TableInfo.Column("http_headers", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_mac_address", TableInfo.Column("stalker_mac_address", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_device_profile", TableInfo.Column("stalker_device_profile", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_device_timezone", TableInfo.Column("stalker_device_timezone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_device_locale", TableInfo.Column("stalker_device_locale", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_serial_number", TableInfo.Column("stalker_serial_number", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_device_id", TableInfo.Column("stalker_device_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_device_id2", TableInfo.Column("stalker_device_id2", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_signature", TableInfo.Column("stalker_signature", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_advanced_options_json", TableInfo.Column("stalker_advanced_options_json", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_auth_mode", TableInfo.Column("stalker_auth_mode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_portal_profile", TableInfo.Column("stalker_portal_profile", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_portal_fingerprint", TableInfo.Column("stalker_portal_fingerprint", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_mag_preset", TableInfo.Column("stalker_mag_preset", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_last_bootstrap_recipe", TableInfo.Column("stalker_last_bootstrap_recipe", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_endpoint_preference", TableInfo.Column("stalker_endpoint_preference", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_cookie_mode", TableInfo.Column("stalker_cookie_mode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_playback_backend_hint", TableInfo.Column("stalker_playback_backend_hint", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_last_playback_mode", TableInfo.Column("stalker_last_playback_mode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_credentials_required", TableInfo.Column("stalker_credentials_required", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_mac_required", TableInfo.Column("stalker_mac_required", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_uses_temp_links", TableInfo.Column("stalker_uses_temp_links", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_module_restricted", TableInfo.Column("stalker_module_restricted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_strict_fingerprint_required", TableInfo.Column("stalker_strict_fingerprint_required", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_recipe_fallback_used", TableInfo.Column("stalker_recipe_fallback_used", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("stalker_recipe_rediscovery_attempts", TableInfo.Column("stalker_recipe_rediscovery_attempts", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("is_active", TableInfo.Column("is_active", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("max_connections", TableInfo.Column("max_connections", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("expiration_date", TableInfo.Column("expiration_date", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("api_version", TableInfo.Column("api_version", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("allowed_output_formats_json", TableInfo.Column("allowed_output_formats_json", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("epg_sync_mode", TableInfo.Column("epg_sync_mode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("xtream_fast_sync_enabled", TableInfo.Column("xtream_fast_sync_enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("xtream_live_sync_mode", TableInfo.Column("xtream_live_sync_mode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("m3u_vod_classification_enabled", TableInfo.Column("m3u_vod_classification_enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("status", TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("last_synced_at", TableInfo.Column("last_synced_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviders.put("created_at", TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysProviders: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesProviders: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesProviders.add(TableInfo.Index("index_providers_server_url_username_stalker_mac_address", true, listOf("server_url", "username", "stalker_mac_address"), listOf("ASC", "ASC", "ASC")))
        val _infoProviders: TableInfo = TableInfo("providers", _columnsProviders, _foreignKeysProviders, _indicesProviders)
        val _existingProviders: TableInfo = tableInfoRead(connection, "providers")
        if (!_infoProviders.equals(_existingProviders)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |providers(com.vopo.data.local.entity.ProviderEntity).
              | Expected:
              |""".trimMargin() + _infoProviders + """
              |
              | Found:
              |""".trimMargin() + _existingProviders)
        }
        val _columnsChannels: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsChannels.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("stream_id", TableInfo.Column("stream_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("logo_url", TableInfo.Column("logo_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("group_title", TableInfo.Column("group_title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("category_id", TableInfo.Column("category_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("category_name", TableInfo.Column("category_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("stream_url", TableInfo.Column("stream_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("epg_channel_id", TableInfo.Column("epg_channel_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("number", TableInfo.Column("number", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("catch_up_supported", TableInfo.Column("catch_up_supported", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("catch_up_days", TableInfo.Column("catch_up_days", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("catchUpSource", TableInfo.Column("catchUpSource", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("is_adult", TableInfo.Column("is_adult", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("is_user_protected", TableInfo.Column("is_user_protected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("logical_group_id", TableInfo.Column("logical_group_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("error_count", TableInfo.Column("error_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("quality_options_json", TableInfo.Column("quality_options_json", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannels.put("sync_fingerprint", TableInfo.Column("sync_fingerprint", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysChannels: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysChannels.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesChannels: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesChannels.add(TableInfo.Index("index_channels_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesChannels.add(TableInfo.Index("index_channels_provider_id_category_id", false, listOf("provider_id", "category_id"), listOf("ASC", "ASC")))
        _indicesChannels.add(TableInfo.Index("index_channels_provider_id_stream_id", true, listOf("provider_id", "stream_id"), listOf("ASC", "ASC")))
        _indicesChannels.add(TableInfo.Index("index_channels_logical_group_id", false, listOf("logical_group_id"), listOf("ASC")))
        _indicesChannels.add(TableInfo.Index("index_channels_provider_id_category_id_logical_group_id", false, listOf("provider_id", "category_id", "logical_group_id"), listOf("ASC", "ASC", "ASC")))
        val _infoChannels: TableInfo = TableInfo("channels", _columnsChannels, _foreignKeysChannels, _indicesChannels)
        val _existingChannels: TableInfo = tableInfoRead(connection, "channels")
        if (!_infoChannels.equals(_existingChannels)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |channels(com.vopo.data.local.entity.ChannelEntity).
              | Expected:
              |""".trimMargin() + _infoChannels + """
              |
              | Found:
              |""".trimMargin() + _existingChannels)
        }
        val _columnsChannelPreferences: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsChannelPreferences.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelPreferences.put("channel_id", TableInfo.Column("channel_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelPreferences.put("aspect_ratio", TableInfo.Column("aspect_ratio", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelPreferences.put("audio_video_offset_ms", TableInfo.Column("audio_video_offset_ms", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelPreferences.put("updated_at", TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysChannelPreferences: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysChannelPreferences.add(TableInfo.ForeignKey("channels", "CASCADE", "NO ACTION", listOf("channel_id"), listOf("id")))
        val _indicesChannelPreferences: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesChannelPreferences.add(TableInfo.Index("index_channel_preferences_channel_id", true, listOf("channel_id"), listOf("ASC")))
        val _infoChannelPreferences: TableInfo = TableInfo("channel_preferences", _columnsChannelPreferences, _foreignKeysChannelPreferences, _indicesChannelPreferences)
        val _existingChannelPreferences: TableInfo = tableInfoRead(connection, "channel_preferences")
        if (!_infoChannelPreferences.equals(_existingChannelPreferences)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |channel_preferences(com.vopo.data.local.entity.ChannelPreferenceEntity).
              | Expected:
              |""".trimMargin() + _infoChannelPreferences + """
              |
              | Found:
              |""".trimMargin() + _existingChannelPreferences)
        }
        val _columnsChannelsFts: MutableSet<String> = mutableSetOf()
        _columnsChannelsFts.add("name")
        val _infoChannelsFts: FtsTableInfo = FtsTableInfo("channels_fts", _columnsChannelsFts, "CREATE VIRTUAL TABLE IF NOT EXISTS `channels_fts` USING FTS4(`name` TEXT NOT NULL, content=`channels`)")
        val _existingChannelsFts: FtsTableInfo = ftsTableInfoRead(connection, "channels_fts")
        if (!_infoChannelsFts.equals(_existingChannelsFts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |channels_fts(com.vopo.data.local.entity.ChannelFtsEntity).
              | Expected:
              |""".trimMargin() + _infoChannelsFts + """
              |
              | Found:
              |""".trimMargin() + _existingChannelsFts)
        }
        val _columnsMovies: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsMovies.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("stream_id", TableInfo.Column("stream_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("poster_url", TableInfo.Column("poster_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("backdrop_url", TableInfo.Column("backdrop_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("category_id", TableInfo.Column("category_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("category_name", TableInfo.Column("category_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("stream_url", TableInfo.Column("stream_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("container_extension", TableInfo.Column("container_extension", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("plot", TableInfo.Column("plot", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("cast", TableInfo.Column("cast", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("director", TableInfo.Column("director", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("genre", TableInfo.Column("genre", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("release_date", TableInfo.Column("release_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("duration", TableInfo.Column("duration", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("duration_seconds", TableInfo.Column("duration_seconds", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("rating", TableInfo.Column("rating", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("year", TableInfo.Column("year", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("tmdb_id", TableInfo.Column("tmdb_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("youtube_trailer", TableInfo.Column("youtube_trailer", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("watch_progress", TableInfo.Column("watch_progress", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("watch_count", TableInfo.Column("watch_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("last_watched_at", TableInfo.Column("last_watched_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("is_adult", TableInfo.Column("is_adult", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("is_user_protected", TableInfo.Column("is_user_protected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("sync_fingerprint", TableInfo.Column("sync_fingerprint", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("added_at", TableInfo.Column("added_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("cache_state", TableInfo.Column("cache_state", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("detail_hydrated_at", TableInfo.Column("detail_hydrated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovies.put("remote_stale_at", TableInfo.Column("remote_stale_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysMovies: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysMovies.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesMovies: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesMovies.add(TableInfo.Index("index_movies_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesMovies.add(TableInfo.Index("index_movies_provider_id_category_id", false, listOf("provider_id", "category_id"), listOf("ASC", "ASC")))
        _indicesMovies.add(TableInfo.Index("index_movies_provider_id_stream_id", true, listOf("provider_id", "stream_id"), listOf("ASC", "ASC")))
        _indicesMovies.add(TableInfo.Index("index_movies_provider_id_name_id", false, listOf("provider_id", "name", "id"), listOf("ASC", "ASC", "ASC")))
        _indicesMovies.add(TableInfo.Index("index_movies_provider_id_category_id_name_id", false, listOf("provider_id", "category_id", "name", "id"), listOf("ASC", "ASC", "ASC", "ASC")))
        _indicesMovies.add(TableInfo.Index("index_movies_provider_id_rating_name_id", false, listOf("provider_id", "rating", "name", "id"), listOf("ASC", "ASC", "ASC", "ASC")))
        _indicesMovies.add(TableInfo.Index("index_movies_provider_id_added_at_release_date_name_id", false, listOf("provider_id", "added_at", "release_date", "name", "id"), listOf("ASC", "ASC", "ASC", "ASC", "ASC")))
        val _infoMovies: TableInfo = TableInfo("movies", _columnsMovies, _foreignKeysMovies, _indicesMovies)
        val _existingMovies: TableInfo = tableInfoRead(connection, "movies")
        if (!_infoMovies.equals(_existingMovies)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |movies(com.vopo.data.local.entity.MovieEntity).
              | Expected:
              |""".trimMargin() + _infoMovies + """
              |
              | Found:
              |""".trimMargin() + _existingMovies)
        }
        val _columnsMoviesFts: MutableSet<String> = mutableSetOf()
        _columnsMoviesFts.add("name")
        val _infoMoviesFts: FtsTableInfo = FtsTableInfo("movies_fts", _columnsMoviesFts, "CREATE VIRTUAL TABLE IF NOT EXISTS `movies_fts` USING FTS4(`name` TEXT NOT NULL, content=`movies`)")
        val _existingMoviesFts: FtsTableInfo = ftsTableInfoRead(connection, "movies_fts")
        if (!_infoMoviesFts.equals(_existingMoviesFts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |movies_fts(com.vopo.data.local.entity.MovieFtsEntity).
              | Expected:
              |""".trimMargin() + _infoMoviesFts + """
              |
              | Found:
              |""".trimMargin() + _existingMoviesFts)
        }
        val _columnsSeries: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSeries.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("series_id", TableInfo.Column("series_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("provider_series_id", TableInfo.Column("provider_series_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("poster_url", TableInfo.Column("poster_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("backdrop_url", TableInfo.Column("backdrop_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("category_id", TableInfo.Column("category_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("category_name", TableInfo.Column("category_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("plot", TableInfo.Column("plot", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("cast", TableInfo.Column("cast", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("director", TableInfo.Column("director", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("genre", TableInfo.Column("genre", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("release_date", TableInfo.Column("release_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("rating", TableInfo.Column("rating", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("tmdb_id", TableInfo.Column("tmdb_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("youtube_trailer", TableInfo.Column("youtube_trailer", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("episode_run_time", TableInfo.Column("episode_run_time", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("last_modified", TableInfo.Column("last_modified", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("is_adult", TableInfo.Column("is_adult", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("is_user_protected", TableInfo.Column("is_user_protected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("sync_fingerprint", TableInfo.Column("sync_fingerprint", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("cache_state", TableInfo.Column("cache_state", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("detail_hydrated_at", TableInfo.Column("detail_hydrated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeries.put("remote_stale_at", TableInfo.Column("remote_stale_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSeries: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysSeries.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesSeries: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesSeries.add(TableInfo.Index("index_series_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesSeries.add(TableInfo.Index("index_series_provider_id_category_id", false, listOf("provider_id", "category_id"), listOf("ASC", "ASC")))
        _indicesSeries.add(TableInfo.Index("index_series_provider_id_series_id", true, listOf("provider_id", "series_id"), listOf("ASC", "ASC")))
        _indicesSeries.add(TableInfo.Index("index_series_provider_id_name_id", false, listOf("provider_id", "name", "id"), listOf("ASC", "ASC", "ASC")))
        _indicesSeries.add(TableInfo.Index("index_series_provider_id_category_id_name_id", false, listOf("provider_id", "category_id", "name", "id"), listOf("ASC", "ASC", "ASC", "ASC")))
        _indicesSeries.add(TableInfo.Index("index_series_provider_id_rating_name_id", false, listOf("provider_id", "rating", "name", "id"), listOf("ASC", "ASC", "ASC", "ASC")))
        _indicesSeries.add(TableInfo.Index("index_series_provider_id_last_modified_name_id", false, listOf("provider_id", "last_modified", "name", "id"), listOf("ASC", "ASC", "ASC", "ASC")))
        val _infoSeries: TableInfo = TableInfo("series", _columnsSeries, _foreignKeysSeries, _indicesSeries)
        val _existingSeries: TableInfo = tableInfoRead(connection, "series")
        if (!_infoSeries.equals(_existingSeries)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |series(com.vopo.data.local.entity.SeriesEntity).
              | Expected:
              |""".trimMargin() + _infoSeries + """
              |
              | Found:
              |""".trimMargin() + _existingSeries)
        }
        val _columnsSeriesFts: MutableSet<String> = mutableSetOf()
        _columnsSeriesFts.add("name")
        val _infoSeriesFts: FtsTableInfo = FtsTableInfo("series_fts", _columnsSeriesFts, "CREATE VIRTUAL TABLE IF NOT EXISTS `series_fts` USING FTS4(`name` TEXT NOT NULL, content=`series`)")
        val _existingSeriesFts: FtsTableInfo = ftsTableInfoRead(connection, "series_fts")
        if (!_infoSeriesFts.equals(_existingSeriesFts)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |series_fts(com.vopo.data.local.entity.SeriesFtsEntity).
              | Expected:
              |""".trimMargin() + _infoSeriesFts + """
              |
              | Found:
              |""".trimMargin() + _existingSeriesFts)
        }
        val _columnsEpisodes: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsEpisodes.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("episode_id", TableInfo.Column("episode_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("title", TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("episode_number", TableInfo.Column("episode_number", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("season_number", TableInfo.Column("season_number", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("stream_url", TableInfo.Column("stream_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("container_extension", TableInfo.Column("container_extension", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("cover_url", TableInfo.Column("cover_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("plot", TableInfo.Column("plot", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("duration", TableInfo.Column("duration", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("duration_seconds", TableInfo.Column("duration_seconds", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("rating", TableInfo.Column("rating", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("release_date", TableInfo.Column("release_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("series_id", TableInfo.Column("series_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("watch_progress", TableInfo.Column("watch_progress", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("last_watched_at", TableInfo.Column("last_watched_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("is_adult", TableInfo.Column("is_adult", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpisodes.put("is_user_protected", TableInfo.Column("is_user_protected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysEpisodes: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysEpisodes.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        _foreignKeysEpisodes.add(TableInfo.ForeignKey("series", "CASCADE", "NO ACTION", listOf("series_id"), listOf("id")))
        val _indicesEpisodes: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesEpisodes.add(TableInfo.Index("index_episodes_series_id", false, listOf("series_id"), listOf("ASC")))
        _indicesEpisodes.add(TableInfo.Index("index_episodes_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesEpisodes.add(TableInfo.Index("index_episodes_provider_id_episode_id", true, listOf("provider_id", "episode_id"), listOf("ASC", "ASC")))
        val _infoEpisodes: TableInfo = TableInfo("episodes", _columnsEpisodes, _foreignKeysEpisodes, _indicesEpisodes)
        val _existingEpisodes: TableInfo = tableInfoRead(connection, "episodes")
        if (!_infoEpisodes.equals(_existingEpisodes)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |episodes(com.vopo.data.local.entity.EpisodeEntity).
              | Expected:
              |""".trimMargin() + _infoEpisodes + """
              |
              | Found:
              |""".trimMargin() + _existingEpisodes)
        }
        val _columnsCategories: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCategories.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategories.put("category_id", TableInfo.Column("category_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategories.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategories.put("parent_id", TableInfo.Column("parent_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategories.put("type", TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategories.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategories.put("is_adult", TableInfo.Column("is_adult", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategories.put("is_user_protected", TableInfo.Column("is_user_protected", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategories.put("sync_fingerprint", TableInfo.Column("sync_fingerprint", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCategories: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysCategories.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesCategories: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesCategories.add(TableInfo.Index("index_categories_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesCategories.add(TableInfo.Index("index_categories_provider_id_type", false, listOf("provider_id", "type"), listOf("ASC", "ASC")))
        _indicesCategories.add(TableInfo.Index("index_categories_provider_id_category_id_type", true, listOf("provider_id", "category_id", "type"), listOf("ASC", "ASC", "ASC")))
        val _infoCategories: TableInfo = TableInfo("categories", _columnsCategories, _foreignKeysCategories, _indicesCategories)
        val _existingCategories: TableInfo = tableInfoRead(connection, "categories")
        if (!_infoCategories.equals(_existingCategories)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |categories(com.vopo.data.local.entity.CategoryEntity).
              | Expected:
              |""".trimMargin() + _infoCategories + """
              |
              | Found:
              |""".trimMargin() + _existingCategories)
        }
        val _columnsChannelImportStage: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsChannelImportStage.put("session_id", TableInfo.Column("session_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("stream_id", TableInfo.Column("stream_id", "INTEGER", true, 3, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("logo_url", TableInfo.Column("logo_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("group_title", TableInfo.Column("group_title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("category_id", TableInfo.Column("category_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("category_name", TableInfo.Column("category_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("stream_url", TableInfo.Column("stream_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("epg_channel_id", TableInfo.Column("epg_channel_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("number", TableInfo.Column("number", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("catch_up_supported", TableInfo.Column("catch_up_supported", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("catch_up_days", TableInfo.Column("catch_up_days", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("catchUpSource", TableInfo.Column("catchUpSource", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("is_adult", TableInfo.Column("is_adult", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("logical_group_id", TableInfo.Column("logical_group_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("error_count", TableInfo.Column("error_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelImportStage.put("sync_fingerprint", TableInfo.Column("sync_fingerprint", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysChannelImportStage: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysChannelImportStage.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesChannelImportStage: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesChannelImportStage.add(TableInfo.Index("index_channel_import_stage_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesChannelImportStage.add(TableInfo.Index("index_channel_import_stage_session_id_provider_id", false, listOf("session_id", "provider_id"), listOf("ASC", "ASC")))
        val _infoChannelImportStage: TableInfo = TableInfo("channel_import_stage", _columnsChannelImportStage, _foreignKeysChannelImportStage, _indicesChannelImportStage)
        val _existingChannelImportStage: TableInfo = tableInfoRead(connection, "channel_import_stage")
        if (!_infoChannelImportStage.equals(_existingChannelImportStage)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |channel_import_stage(com.vopo.data.local.entity.ChannelImportStageEntity).
              | Expected:
              |""".trimMargin() + _infoChannelImportStage + """
              |
              | Found:
              |""".trimMargin() + _existingChannelImportStage)
        }
        val _columnsMovieImportStage: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsMovieImportStage.put("session_id", TableInfo.Column("session_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("stream_id", TableInfo.Column("stream_id", "INTEGER", true, 3, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("poster_url", TableInfo.Column("poster_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("backdrop_url", TableInfo.Column("backdrop_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("category_id", TableInfo.Column("category_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("category_name", TableInfo.Column("category_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("stream_url", TableInfo.Column("stream_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("container_extension", TableInfo.Column("container_extension", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("plot", TableInfo.Column("plot", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("cast", TableInfo.Column("cast", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("director", TableInfo.Column("director", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("genre", TableInfo.Column("genre", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("release_date", TableInfo.Column("release_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("duration", TableInfo.Column("duration", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("duration_seconds", TableInfo.Column("duration_seconds", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("rating", TableInfo.Column("rating", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("year", TableInfo.Column("year", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("tmdb_id", TableInfo.Column("tmdb_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("youtube_trailer", TableInfo.Column("youtube_trailer", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("is_adult", TableInfo.Column("is_adult", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("sync_fingerprint", TableInfo.Column("sync_fingerprint", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieImportStage.put("added_at", TableInfo.Column("added_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysMovieImportStage: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysMovieImportStage.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesMovieImportStage: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesMovieImportStage.add(TableInfo.Index("index_movie_import_stage_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesMovieImportStage.add(TableInfo.Index("index_movie_import_stage_session_id_provider_id", false, listOf("session_id", "provider_id"), listOf("ASC", "ASC")))
        val _infoMovieImportStage: TableInfo = TableInfo("movie_import_stage", _columnsMovieImportStage, _foreignKeysMovieImportStage, _indicesMovieImportStage)
        val _existingMovieImportStage: TableInfo = tableInfoRead(connection, "movie_import_stage")
        if (!_infoMovieImportStage.equals(_existingMovieImportStage)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |movie_import_stage(com.vopo.data.local.entity.MovieImportStageEntity).
              | Expected:
              |""".trimMargin() + _infoMovieImportStage + """
              |
              | Found:
              |""".trimMargin() + _existingMovieImportStage)
        }
        val _columnsSeriesImportStage: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSeriesImportStage.put("session_id", TableInfo.Column("session_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("series_id", TableInfo.Column("series_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("provider_series_id", TableInfo.Column("provider_series_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("provider_series_key", TableInfo.Column("provider_series_key", "TEXT", true, 3, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("poster_url", TableInfo.Column("poster_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("backdrop_url", TableInfo.Column("backdrop_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("category_id", TableInfo.Column("category_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("category_name", TableInfo.Column("category_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("plot", TableInfo.Column("plot", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("cast", TableInfo.Column("cast", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("director", TableInfo.Column("director", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("genre", TableInfo.Column("genre", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("release_date", TableInfo.Column("release_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("rating", TableInfo.Column("rating", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("tmdb_id", TableInfo.Column("tmdb_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("youtube_trailer", TableInfo.Column("youtube_trailer", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("episode_run_time", TableInfo.Column("episode_run_time", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("last_modified", TableInfo.Column("last_modified", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("is_adult", TableInfo.Column("is_adult", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesImportStage.put("sync_fingerprint", TableInfo.Column("sync_fingerprint", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSeriesImportStage: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysSeriesImportStage.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesSeriesImportStage: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesSeriesImportStage.add(TableInfo.Index("index_series_import_stage_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesSeriesImportStage.add(TableInfo.Index("index_series_import_stage_session_id_provider_id", false, listOf("session_id", "provider_id"), listOf("ASC", "ASC")))
        val _infoSeriesImportStage: TableInfo = TableInfo("series_import_stage", _columnsSeriesImportStage, _foreignKeysSeriesImportStage, _indicesSeriesImportStage)
        val _existingSeriesImportStage: TableInfo = tableInfoRead(connection, "series_import_stage")
        if (!_infoSeriesImportStage.equals(_existingSeriesImportStage)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |series_import_stage(com.vopo.data.local.entity.SeriesImportStageEntity).
              | Expected:
              |""".trimMargin() + _infoSeriesImportStage + """
              |
              | Found:
              |""".trimMargin() + _existingSeriesImportStage)
        }
        val _columnsCategoryImportStage: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCategoryImportStage.put("session_id", TableInfo.Column("session_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategoryImportStage.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategoryImportStage.put("category_id", TableInfo.Column("category_id", "INTEGER", true, 3, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategoryImportStage.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategoryImportStage.put("parent_id", TableInfo.Column("parent_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategoryImportStage.put("type", TableInfo.Column("type", "TEXT", true, 4, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategoryImportStage.put("is_adult", TableInfo.Column("is_adult", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCategoryImportStage.put("sync_fingerprint", TableInfo.Column("sync_fingerprint", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCategoryImportStage: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysCategoryImportStage.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesCategoryImportStage: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesCategoryImportStage.add(TableInfo.Index("index_category_import_stage_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesCategoryImportStage.add(TableInfo.Index("index_category_import_stage_session_id_provider_id", false, listOf("session_id", "provider_id"), listOf("ASC", "ASC")))
        _indicesCategoryImportStage.add(TableInfo.Index("index_category_import_stage_provider_id_type", false, listOf("provider_id", "type"), listOf("ASC", "ASC")))
        val _infoCategoryImportStage: TableInfo = TableInfo("category_import_stage", _columnsCategoryImportStage, _foreignKeysCategoryImportStage, _indicesCategoryImportStage)
        val _existingCategoryImportStage: TableInfo = tableInfoRead(connection, "category_import_stage")
        if (!_infoCategoryImportStage.equals(_existingCategoryImportStage)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |category_import_stage(com.vopo.data.local.entity.CategoryImportStageEntity).
              | Expected:
              |""".trimMargin() + _infoCategoryImportStage + """
              |
              | Found:
              |""".trimMargin() + _existingCategoryImportStage)
        }
        val _columnsPrograms: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPrograms.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("channel_id", TableInfo.Column("channel_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("title", TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("description", TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("start_time", TableInfo.Column("start_time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("end_time", TableInfo.Column("end_time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("lang", TableInfo.Column("lang", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("rating", TableInfo.Column("rating", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("image_url", TableInfo.Column("image_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("genre", TableInfo.Column("genre", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("category", TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPrograms.put("has_archive", TableInfo.Column("has_archive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPrograms: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPrograms: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesPrograms.add(TableInfo.Index("index_programs_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesPrograms.add(TableInfo.Index("index_programs_provider_id_channel_id", false, listOf("provider_id", "channel_id"), listOf("ASC", "ASC")))
        _indicesPrograms.add(TableInfo.Index("index_programs_provider_id_end_time_channel_id", false, listOf("provider_id", "end_time", "channel_id"), listOf("ASC", "ASC", "ASC")))
        _indicesPrograms.add(TableInfo.Index("index_programs_provider_id_start_time_end_time", false, listOf("provider_id", "start_time", "end_time"), listOf("ASC", "ASC", "ASC")))
        _indicesPrograms.add(TableInfo.Index("index_programs_start_time", false, listOf("start_time"), listOf("ASC")))
        _indicesPrograms.add(TableInfo.Index("index_programs_provider_id_channel_id_start_time", false, listOf("provider_id", "channel_id", "start_time"), listOf("ASC", "ASC", "ASC")))
        _indicesPrograms.add(TableInfo.Index("index_programs_provider_id_channel_id_start_time_end_time", true, listOf("provider_id", "channel_id", "start_time", "end_time"), listOf("ASC", "ASC", "ASC", "ASC")))
        val _infoPrograms: TableInfo = TableInfo("programs", _columnsPrograms, _foreignKeysPrograms, _indicesPrograms)
        val _existingPrograms: TableInfo = tableInfoRead(connection, "programs")
        if (!_infoPrograms.equals(_existingPrograms)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |programs(com.vopo.data.local.entity.ProgramEntity).
              | Expected:
              |""".trimMargin() + _infoPrograms + """
              |
              | Found:
              |""".trimMargin() + _existingPrograms)
        }
        val _columnsFavorites: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsFavorites.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavorites.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavorites.put("content_id", TableInfo.Column("content_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavorites.put("content_type", TableInfo.Column("content_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavorites.put("position", TableInfo.Column("position", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavorites.put("group_id", TableInfo.Column("group_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavorites.put("group_key", TableInfo.Column("group_key", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsFavorites.put("added_at", TableInfo.Column("added_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysFavorites: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysFavorites.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        _foreignKeysFavorites.add(TableInfo.ForeignKey("virtual_groups", "SET NULL", "NO ACTION", listOf("group_id"), listOf("id")))
        val _indicesFavorites: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesFavorites.add(TableInfo.Index("index_favorites_provider_id_content_id_content_type_group_key", true, listOf("provider_id", "content_id", "content_type", "group_key"), listOf("ASC", "ASC", "ASC", "ASC")))
        _indicesFavorites.add(TableInfo.Index("index_favorites_provider_id_content_type_group_id", false, listOf("provider_id", "content_type", "group_id"), listOf("ASC", "ASC", "ASC")))
        _indicesFavorites.add(TableInfo.Index("index_favorites_group_id_position", false, listOf("group_id", "position"), listOf("ASC", "ASC")))
        val _infoFavorites: TableInfo = TableInfo("favorites", _columnsFavorites, _foreignKeysFavorites, _indicesFavorites)
        val _existingFavorites: TableInfo = tableInfoRead(connection, "favorites")
        if (!_infoFavorites.equals(_existingFavorites)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |favorites(com.vopo.data.local.entity.FavoriteEntity).
              | Expected:
              |""".trimMargin() + _infoFavorites + """
              |
              | Found:
              |""".trimMargin() + _existingFavorites)
        }
        val _columnsVirtualGroups: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsVirtualGroups.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVirtualGroups.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVirtualGroups.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVirtualGroups.put("icon_emoji", TableInfo.Column("icon_emoji", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVirtualGroups.put("position", TableInfo.Column("position", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVirtualGroups.put("created_at", TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsVirtualGroups.put("content_type", TableInfo.Column("content_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysVirtualGroups: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysVirtualGroups.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesVirtualGroups: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesVirtualGroups.add(TableInfo.Index("index_virtual_groups_provider_id_content_type", false, listOf("provider_id", "content_type"), listOf("ASC", "ASC")))
        _indicesVirtualGroups.add(TableInfo.Index("index_virtual_groups_position", false, listOf("position"), listOf("ASC")))
        _indicesVirtualGroups.add(TableInfo.Index("index_virtual_groups_content_type", false, listOf("content_type"), listOf("ASC")))
        val _infoVirtualGroups: TableInfo = TableInfo("virtual_groups", _columnsVirtualGroups, _foreignKeysVirtualGroups, _indicesVirtualGroups)
        val _existingVirtualGroups: TableInfo = tableInfoRead(connection, "virtual_groups")
        if (!_infoVirtualGroups.equals(_existingVirtualGroups)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |virtual_groups(com.vopo.data.local.entity.VirtualGroupEntity).
              | Expected:
              |""".trimMargin() + _infoVirtualGroups + """
              |
              | Found:
              |""".trimMargin() + _existingVirtualGroups)
        }
        val _columnsPlaybackHistory: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPlaybackHistory.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackHistory.put("content_id", TableInfo.Column("content_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackHistory.put("content_type", TableInfo.Column("content_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackHistory.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackHistory.put("title", TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackHistory.put("poster_url", TableInfo.Column("poster_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackHistory.put("stream_url", TableInfo.Column("stream_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackHistory.put("resume_position_ms", TableInfo.Column("resume_position_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackHistory.put("total_duration_ms", TableInfo.Column("total_duration_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackHistory.put("last_watched_at", TableInfo.Column("last_watched_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackHistory.put("watch_count", TableInfo.Column("watch_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackHistory.put("watched_status", TableInfo.Column("watched_status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackHistory.put("series_id", TableInfo.Column("series_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackHistory.put("season_number", TableInfo.Column("season_number", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackHistory.put("episode_number", TableInfo.Column("episode_number", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPlaybackHistory: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysPlaybackHistory.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesPlaybackHistory: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesPlaybackHistory.add(TableInfo.Index("index_playback_history_content_id_content_type_provider_id", true, listOf("content_id", "content_type", "provider_id"), listOf("ASC", "ASC", "ASC")))
        _indicesPlaybackHistory.add(TableInfo.Index("index_playback_history_last_watched_at", false, listOf("last_watched_at"), listOf("ASC")))
        _indicesPlaybackHistory.add(TableInfo.Index("index_playback_history_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesPlaybackHistory.add(TableInfo.Index("index_playback_history_provider_id_content_type_content_id", false, listOf("provider_id", "content_type", "content_id"), listOf("ASC", "ASC", "ASC")))
        _indicesPlaybackHistory.add(TableInfo.Index("index_playback_history_provider_id_content_type_last_watched_at", false, listOf("provider_id", "content_type", "last_watched_at"), listOf("ASC", "ASC", "ASC")))
        val _infoPlaybackHistory: TableInfo = TableInfo("playback_history", _columnsPlaybackHistory, _foreignKeysPlaybackHistory, _indicesPlaybackHistory)
        val _existingPlaybackHistory: TableInfo = tableInfoRead(connection, "playback_history")
        if (!_infoPlaybackHistory.equals(_existingPlaybackHistory)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |playback_history(com.vopo.data.local.entity.PlaybackHistoryEntity).
              | Expected:
              |""".trimMargin() + _infoPlaybackHistory + """
              |
              | Found:
              |""".trimMargin() + _existingPlaybackHistory)
        }
        val _columnsTmdbIdentity: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsTmdbIdentity.put("tmdb_id", TableInfo.Column("tmdb_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTmdbIdentity.put("content_type", TableInfo.Column("content_type", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTmdbIdentity.put("canonical_provider_id", TableInfo.Column("canonical_provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsTmdbIdentity.put("first_seen_at", TableInfo.Column("first_seen_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysTmdbIdentity: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysTmdbIdentity.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("canonical_provider_id"), listOf("id")))
        val _indicesTmdbIdentity: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesTmdbIdentity.add(TableInfo.Index("index_tmdb_identity_content_type", false, listOf("content_type"), listOf("ASC")))
        _indicesTmdbIdentity.add(TableInfo.Index("index_tmdb_identity_canonical_provider_id", false, listOf("canonical_provider_id"), listOf("ASC")))
        val _infoTmdbIdentity: TableInfo = TableInfo("tmdb_identity", _columnsTmdbIdentity, _foreignKeysTmdbIdentity, _indicesTmdbIdentity)
        val _existingTmdbIdentity: TableInfo = tableInfoRead(connection, "tmdb_identity")
        if (!_infoTmdbIdentity.equals(_existingTmdbIdentity)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |tmdb_identity(com.vopo.data.local.entity.TmdbIdentityEntity).
              | Expected:
              |""".trimMargin() + _infoTmdbIdentity + """
              |
              | Found:
              |""".trimMargin() + _existingTmdbIdentity)
        }
        val _columnsSearchHistory: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSearchHistory.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSearchHistory.put("query", TableInfo.Column("query", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSearchHistory.put("content_scope", TableInfo.Column("content_scope", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSearchHistory.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSearchHistory.put("used_at", TableInfo.Column("used_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSearchHistory.put("use_count", TableInfo.Column("use_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSearchHistory: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesSearchHistory: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesSearchHistory.add(TableInfo.Index("index_search_history_content_scope_provider_id_used_at", false, listOf("content_scope", "provider_id", "used_at"), listOf("ASC", "ASC", "ASC")))
        _indicesSearchHistory.add(TableInfo.Index("index_search_history_used_at", false, listOf("used_at"), listOf("ASC")))
        _indicesSearchHistory.add(TableInfo.Index("index_search_history_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesSearchHistory.add(TableInfo.Index("index_search_history_query_content_scope_provider_id", true, listOf("query", "content_scope", "provider_id"), listOf("ASC", "ASC", "ASC")))
        val _infoSearchHistory: TableInfo = TableInfo("search_history", _columnsSearchHistory, _foreignKeysSearchHistory, _indicesSearchHistory)
        val _existingSearchHistory: TableInfo = tableInfoRead(connection, "search_history")
        if (!_infoSearchHistory.equals(_existingSearchHistory)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |search_history(com.vopo.data.local.entity.SearchHistoryEntity).
              | Expected:
              |""".trimMargin() + _infoSearchHistory + """
              |
              | Found:
              |""".trimMargin() + _existingSearchHistory)
        }
        val _columnsSyncMetadata: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSyncMetadata.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("last_live_sync", TableInfo.Column("last_live_sync", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("last_live_success", TableInfo.Column("last_live_success", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("last_movie_sync", TableInfo.Column("last_movie_sync", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("last_series_sync", TableInfo.Column("last_series_sync", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("last_series_success", TableInfo.Column("last_series_success", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("last_epg_sync", TableInfo.Column("last_epg_sync", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("last_epg_success", TableInfo.Column("last_epg_success", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("last_movie_attempt", TableInfo.Column("last_movie_attempt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("last_movie_success", TableInfo.Column("last_movie_success", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("last_movie_partial", TableInfo.Column("last_movie_partial", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("live_count", TableInfo.Column("live_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("movie_count", TableInfo.Column("movie_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("series_count", TableInfo.Column("series_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("epg_count", TableInfo.Column("epg_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("last_sync_status", TableInfo.Column("last_sync_status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("movie_sync_mode", TableInfo.Column("movie_sync_mode", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("movie_warnings_count", TableInfo.Column("movie_warnings_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("movie_catalog_stale", TableInfo.Column("movie_catalog_stale", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("live_avoid_full_until", TableInfo.Column("live_avoid_full_until", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("movie_avoid_full_until", TableInfo.Column("movie_avoid_full_until", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("series_avoid_full_until", TableInfo.Column("series_avoid_full_until", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("live_sequential_failures_remembered", TableInfo.Column("live_sequential_failures_remembered", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("live_healthy_sync_streak", TableInfo.Column("live_healthy_sync_streak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("movie_parallel_failures_remembered", TableInfo.Column("movie_parallel_failures_remembered", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("movie_healthy_sync_streak", TableInfo.Column("movie_healthy_sync_streak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("series_sequential_failures_remembered", TableInfo.Column("series_sequential_failures_remembered", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSyncMetadata.put("series_healthy_sync_streak", TableInfo.Column("series_healthy_sync_streak", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSyncMetadata: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysSyncMetadata.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesSyncMetadata: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoSyncMetadata: TableInfo = TableInfo("sync_metadata", _columnsSyncMetadata, _foreignKeysSyncMetadata, _indicesSyncMetadata)
        val _existingSyncMetadata: TableInfo = tableInfoRead(connection, "sync_metadata")
        if (!_infoSyncMetadata.equals(_existingSyncMetadata)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |sync_metadata(com.vopo.data.local.entity.SyncMetadataEntity).
              | Expected:
              |""".trimMargin() + _infoSyncMetadata + """
              |
              | Found:
              |""".trimMargin() + _existingSyncMetadata)
        }
        val _columnsMovieCategoryHydration: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsMovieCategoryHydration.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("category_id", TableInfo.Column("category_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("last_hydrated_at", TableInfo.Column("last_hydrated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("item_count", TableInfo.Column("item_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("last_status", TableInfo.Column("last_status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("last_error", TableInfo.Column("last_error", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("last_loaded_page", TableInfo.Column("last_loaded_page", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("last_attempted_page", TableInfo.Column("last_attempted_page", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("last_successful_page", TableInfo.Column("last_successful_page", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("total_pages", TableInfo.Column("total_pages", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("is_complete", TableInfo.Column("is_complete", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("page_size", TableInfo.Column("page_size", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("retry_after_ms", TableInfo.Column("retry_after_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("failure_count", TableInfo.Column("failure_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("retry_budget_remaining", TableInfo.Column("retry_budget_remaining", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsMovieCategoryHydration.put("last_page_fingerprint", TableInfo.Column("last_page_fingerprint", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysMovieCategoryHydration: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysMovieCategoryHydration.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesMovieCategoryHydration: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesMovieCategoryHydration.add(TableInfo.Index("index_movie_category_hydration_provider_id", false, listOf("provider_id"), listOf("ASC")))
        val _infoMovieCategoryHydration: TableInfo = TableInfo("movie_category_hydration", _columnsMovieCategoryHydration, _foreignKeysMovieCategoryHydration, _indicesMovieCategoryHydration)
        val _existingMovieCategoryHydration: TableInfo = tableInfoRead(connection, "movie_category_hydration")
        if (!_infoMovieCategoryHydration.equals(_existingMovieCategoryHydration)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |movie_category_hydration(com.vopo.data.local.entity.MovieCategoryHydrationEntity).
              | Expected:
              |""".trimMargin() + _infoMovieCategoryHydration + """
              |
              | Found:
              |""".trimMargin() + _existingMovieCategoryHydration)
        }
        val _columnsSeriesCategoryHydration: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsSeriesCategoryHydration.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("category_id", TableInfo.Column("category_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("last_hydrated_at", TableInfo.Column("last_hydrated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("item_count", TableInfo.Column("item_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("last_status", TableInfo.Column("last_status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("last_error", TableInfo.Column("last_error", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("last_loaded_page", TableInfo.Column("last_loaded_page", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("last_attempted_page", TableInfo.Column("last_attempted_page", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("last_successful_page", TableInfo.Column("last_successful_page", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("total_pages", TableInfo.Column("total_pages", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("is_complete", TableInfo.Column("is_complete", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("page_size", TableInfo.Column("page_size", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("retry_after_ms", TableInfo.Column("retry_after_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("failure_count", TableInfo.Column("failure_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("retry_budget_remaining", TableInfo.Column("retry_budget_remaining", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsSeriesCategoryHydration.put("last_page_fingerprint", TableInfo.Column("last_page_fingerprint", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysSeriesCategoryHydration: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysSeriesCategoryHydration.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesSeriesCategoryHydration: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesSeriesCategoryHydration.add(TableInfo.Index("index_series_category_hydration_provider_id", false, listOf("provider_id"), listOf("ASC")))
        val _infoSeriesCategoryHydration: TableInfo = TableInfo("series_category_hydration", _columnsSeriesCategoryHydration, _foreignKeysSeriesCategoryHydration, _indicesSeriesCategoryHydration)
        val _existingSeriesCategoryHydration: TableInfo = tableInfoRead(connection, "series_category_hydration")
        if (!_infoSeriesCategoryHydration.equals(_existingSeriesCategoryHydration)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |series_category_hydration(com.vopo.data.local.entity.SeriesCategoryHydrationEntity).
              | Expected:
              |""".trimMargin() + _infoSeriesCategoryHydration + """
              |
              | Found:
              |""".trimMargin() + _existingSeriesCategoryHydration)
        }
        val _columnsEpgSources: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsEpgSources.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgSources.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgSources.put("url", TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgSources.put("enabled", TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgSources.put("last_refresh_at", TableInfo.Column("last_refresh_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgSources.put("last_success_at", TableInfo.Column("last_success_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgSources.put("last_error", TableInfo.Column("last_error", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgSources.put("priority", TableInfo.Column("priority", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgSources.put("created_at", TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgSources.put("updated_at", TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgSources.put("etag", TableInfo.Column("etag", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgSources.put("last_modified_header", TableInfo.Column("last_modified_header", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysEpgSources: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesEpgSources: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesEpgSources.add(TableInfo.Index("index_epg_sources_url", true, listOf("url"), listOf("ASC")))
        val _infoEpgSources: TableInfo = TableInfo("epg_sources", _columnsEpgSources, _foreignKeysEpgSources, _indicesEpgSources)
        val _existingEpgSources: TableInfo = tableInfoRead(connection, "epg_sources")
        if (!_infoEpgSources.equals(_existingEpgSources)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |epg_sources(com.vopo.data.local.entity.EpgSourceEntity).
              | Expected:
              |""".trimMargin() + _infoEpgSources + """
              |
              | Found:
              |""".trimMargin() + _existingEpgSources)
        }
        val _columnsProviderEpgSources: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsProviderEpgSources.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviderEpgSources.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviderEpgSources.put("epg_source_id", TableInfo.Column("epg_source_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviderEpgSources.put("priority", TableInfo.Column("priority", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProviderEpgSources.put("enabled", TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysProviderEpgSources: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysProviderEpgSources.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        _foreignKeysProviderEpgSources.add(TableInfo.ForeignKey("epg_sources", "CASCADE", "NO ACTION", listOf("epg_source_id"), listOf("id")))
        val _indicesProviderEpgSources: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesProviderEpgSources.add(TableInfo.Index("index_provider_epg_sources_provider_id_epg_source_id", true, listOf("provider_id", "epg_source_id"), listOf("ASC", "ASC")))
        _indicesProviderEpgSources.add(TableInfo.Index("index_provider_epg_sources_epg_source_id", false, listOf("epg_source_id"), listOf("ASC")))
        val _infoProviderEpgSources: TableInfo = TableInfo("provider_epg_sources", _columnsProviderEpgSources, _foreignKeysProviderEpgSources, _indicesProviderEpgSources)
        val _existingProviderEpgSources: TableInfo = tableInfoRead(connection, "provider_epg_sources")
        if (!_infoProviderEpgSources.equals(_existingProviderEpgSources)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |provider_epg_sources(com.vopo.data.local.entity.ProviderEpgSourceEntity).
              | Expected:
              |""".trimMargin() + _infoProviderEpgSources + """
              |
              | Found:
              |""".trimMargin() + _existingProviderEpgSources)
        }
        val _columnsEpgChannels: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsEpgChannels.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgChannels.put("epg_source_id", TableInfo.Column("epg_source_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgChannels.put("xmltv_channel_id", TableInfo.Column("xmltv_channel_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgChannels.put("display_name", TableInfo.Column("display_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgChannels.put("normalized_name", TableInfo.Column("normalized_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgChannels.put("icon_url", TableInfo.Column("icon_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysEpgChannels: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysEpgChannels.add(TableInfo.ForeignKey("epg_sources", "CASCADE", "NO ACTION", listOf("epg_source_id"), listOf("id")))
        val _indicesEpgChannels: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesEpgChannels.add(TableInfo.Index("index_epg_channels_epg_source_id_xmltv_channel_id", true, listOf("epg_source_id", "xmltv_channel_id"), listOf("ASC", "ASC")))
        _indicesEpgChannels.add(TableInfo.Index("index_epg_channels_epg_source_id", false, listOf("epg_source_id"), listOf("ASC")))
        _indicesEpgChannels.add(TableInfo.Index("index_epg_channels_normalized_name", false, listOf("normalized_name"), listOf("ASC")))
        val _infoEpgChannels: TableInfo = TableInfo("epg_channels", _columnsEpgChannels, _foreignKeysEpgChannels, _indicesEpgChannels)
        val _existingEpgChannels: TableInfo = tableInfoRead(connection, "epg_channels")
        if (!_infoEpgChannels.equals(_existingEpgChannels)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |epg_channels(com.vopo.data.local.entity.EpgChannelEntity).
              | Expected:
              |""".trimMargin() + _infoEpgChannels + """
              |
              | Found:
              |""".trimMargin() + _existingEpgChannels)
        }
        val _columnsEpgProgrammes: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsEpgProgrammes.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgProgrammes.put("epg_source_id", TableInfo.Column("epg_source_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgProgrammes.put("xmltv_channel_id", TableInfo.Column("xmltv_channel_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgProgrammes.put("start_time", TableInfo.Column("start_time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgProgrammes.put("end_time", TableInfo.Column("end_time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgProgrammes.put("title", TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgProgrammes.put("subtitle", TableInfo.Column("subtitle", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgProgrammes.put("description", TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgProgrammes.put("category", TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgProgrammes.put("lang", TableInfo.Column("lang", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgProgrammes.put("rating", TableInfo.Column("rating", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgProgrammes.put("image_url", TableInfo.Column("image_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsEpgProgrammes.put("episode_info", TableInfo.Column("episode_info", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysEpgProgrammes: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysEpgProgrammes.add(TableInfo.ForeignKey("epg_sources", "CASCADE", "NO ACTION", listOf("epg_source_id"), listOf("id")))
        val _indicesEpgProgrammes: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesEpgProgrammes.add(TableInfo.Index("index_epg_programmes_epg_source_id_xmltv_channel_id_start_time", false, listOf("epg_source_id", "xmltv_channel_id", "start_time"), listOf("ASC", "ASC", "ASC")))
        _indicesEpgProgrammes.add(TableInfo.Index("index_epg_programmes_epg_source_id_xmltv_channel_id_start_time_end_time", true, listOf("epg_source_id", "xmltv_channel_id", "start_time", "end_time"), listOf("ASC", "ASC", "ASC", "ASC")))
        _indicesEpgProgrammes.add(TableInfo.Index("index_epg_programmes_epg_source_id", false, listOf("epg_source_id"), listOf("ASC")))
        _indicesEpgProgrammes.add(TableInfo.Index("index_epg_programmes_start_time", false, listOf("start_time"), listOf("ASC")))
        val _infoEpgProgrammes: TableInfo = TableInfo("epg_programmes", _columnsEpgProgrammes, _foreignKeysEpgProgrammes, _indicesEpgProgrammes)
        val _existingEpgProgrammes: TableInfo = tableInfoRead(connection, "epg_programmes")
        if (!_infoEpgProgrammes.equals(_existingEpgProgrammes)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |epg_programmes(com.vopo.data.local.entity.EpgProgrammeEntity).
              | Expected:
              |""".trimMargin() + _infoEpgProgrammes + """
              |
              | Found:
              |""".trimMargin() + _existingEpgProgrammes)
        }
        val _columnsChannelEpgMappings: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsChannelEpgMappings.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelEpgMappings.put("provider_channel_id", TableInfo.Column("provider_channel_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelEpgMappings.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelEpgMappings.put("source_type", TableInfo.Column("source_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelEpgMappings.put("epg_source_id", TableInfo.Column("epg_source_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelEpgMappings.put("xmltv_channel_id", TableInfo.Column("xmltv_channel_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelEpgMappings.put("match_type", TableInfo.Column("match_type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelEpgMappings.put("confidence", TableInfo.Column("confidence", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelEpgMappings.put("matched_at", TableInfo.Column("matched_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelEpgMappings.put("failed_attempts", TableInfo.Column("failed_attempts", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelEpgMappings.put("source", TableInfo.Column("source", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelEpgMappings.put("is_manual_override", TableInfo.Column("is_manual_override", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsChannelEpgMappings.put("updated_at", TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysChannelEpgMappings: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysChannelEpgMappings.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesChannelEpgMappings: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesChannelEpgMappings.add(TableInfo.Index("index_channel_epg_mappings_provider_id_provider_channel_id", true, listOf("provider_id", "provider_channel_id"), listOf("ASC", "ASC")))
        _indicesChannelEpgMappings.add(TableInfo.Index("index_channel_epg_mappings_provider_id", false, listOf("provider_id"), listOf("ASC")))
        val _infoChannelEpgMappings: TableInfo = TableInfo("channel_epg_mappings", _columnsChannelEpgMappings, _foreignKeysChannelEpgMappings, _indicesChannelEpgMappings)
        val _existingChannelEpgMappings: TableInfo = tableInfoRead(connection, "channel_epg_mappings")
        if (!_infoChannelEpgMappings.equals(_existingChannelEpgMappings)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |channel_epg_mappings(com.vopo.data.local.entity.ChannelEpgMappingEntity).
              | Expected:
              |""".trimMargin() + _infoChannelEpgMappings + """
              |
              | Found:
              |""".trimMargin() + _existingChannelEpgMappings)
        }
        val _columnsCombinedM3uProfiles: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCombinedM3uProfiles.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCombinedM3uProfiles.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCombinedM3uProfiles.put("enabled", TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCombinedM3uProfiles.put("created_at", TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCombinedM3uProfiles.put("updated_at", TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCombinedM3uProfiles: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesCombinedM3uProfiles: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoCombinedM3uProfiles: TableInfo = TableInfo("combined_m3u_profiles", _columnsCombinedM3uProfiles, _foreignKeysCombinedM3uProfiles, _indicesCombinedM3uProfiles)
        val _existingCombinedM3uProfiles: TableInfo = tableInfoRead(connection, "combined_m3u_profiles")
        if (!_infoCombinedM3uProfiles.equals(_existingCombinedM3uProfiles)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |combined_m3u_profiles(com.vopo.data.local.entity.CombinedM3uProfileEntity).
              | Expected:
              |""".trimMargin() + _infoCombinedM3uProfiles + """
              |
              | Found:
              |""".trimMargin() + _existingCombinedM3uProfiles)
        }
        val _columnsCombinedM3uProfileMembers: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsCombinedM3uProfileMembers.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCombinedM3uProfileMembers.put("profile_id", TableInfo.Column("profile_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCombinedM3uProfileMembers.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCombinedM3uProfileMembers.put("priority", TableInfo.Column("priority", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsCombinedM3uProfileMembers.put("enabled", TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysCombinedM3uProfileMembers: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysCombinedM3uProfileMembers.add(TableInfo.ForeignKey("combined_m3u_profiles", "CASCADE", "NO ACTION", listOf("profile_id"), listOf("id")))
        _foreignKeysCombinedM3uProfileMembers.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesCombinedM3uProfileMembers: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesCombinedM3uProfileMembers.add(TableInfo.Index("index_combined_m3u_profile_members_profile_id", false, listOf("profile_id"), listOf("ASC")))
        _indicesCombinedM3uProfileMembers.add(TableInfo.Index("index_combined_m3u_profile_members_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesCombinedM3uProfileMembers.add(TableInfo.Index("index_combined_m3u_profile_members_profile_id_provider_id", true, listOf("profile_id", "provider_id"), listOf("ASC", "ASC")))
        val _infoCombinedM3uProfileMembers: TableInfo = TableInfo("combined_m3u_profile_members", _columnsCombinedM3uProfileMembers, _foreignKeysCombinedM3uProfileMembers, _indicesCombinedM3uProfileMembers)
        val _existingCombinedM3uProfileMembers: TableInfo = tableInfoRead(connection, "combined_m3u_profile_members")
        if (!_infoCombinedM3uProfileMembers.equals(_existingCombinedM3uProfileMembers)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |combined_m3u_profile_members(com.vopo.data.local.entity.CombinedM3uProfileMemberEntity).
              | Expected:
              |""".trimMargin() + _infoCombinedM3uProfileMembers + """
              |
              | Found:
              |""".trimMargin() + _existingCombinedM3uProfileMembers)
        }
        val _columnsRecordingSchedules: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRecordingSchedules.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingSchedules.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingSchedules.put("channel_id", TableInfo.Column("channel_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingSchedules.put("channel_name", TableInfo.Column("channel_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingSchedules.put("stream_url", TableInfo.Column("stream_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingSchedules.put("program_title", TableInfo.Column("program_title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingSchedules.put("requested_start_ms", TableInfo.Column("requested_start_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingSchedules.put("requested_end_ms", TableInfo.Column("requested_end_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingSchedules.put("recurrence", TableInfo.Column("recurrence", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingSchedules.put("recurring_rule_id", TableInfo.Column("recurring_rule_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingSchedules.put("enabled", TableInfo.Column("enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingSchedules.put("is_manual", TableInfo.Column("is_manual", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingSchedules.put("priority", TableInfo.Column("priority", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingSchedules.put("created_at", TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingSchedules.put("updated_at", TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRecordingSchedules: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysRecordingSchedules.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesRecordingSchedules: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesRecordingSchedules.add(TableInfo.Index("index_recording_schedules_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesRecordingSchedules.add(TableInfo.Index("index_recording_schedules_enabled_requested_start_ms", false, listOf("enabled", "requested_start_ms"), listOf("ASC", "ASC")))
        _indicesRecordingSchedules.add(TableInfo.Index("index_recording_schedules_recurring_rule_id", false, listOf("recurring_rule_id"), listOf("ASC")))
        val _infoRecordingSchedules: TableInfo = TableInfo("recording_schedules", _columnsRecordingSchedules, _foreignKeysRecordingSchedules, _indicesRecordingSchedules)
        val _existingRecordingSchedules: TableInfo = tableInfoRead(connection, "recording_schedules")
        if (!_infoRecordingSchedules.equals(_existingRecordingSchedules)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |recording_schedules(com.vopo.data.local.entity.RecordingScheduleEntity).
              | Expected:
              |""".trimMargin() + _infoRecordingSchedules + """
              |
              | Found:
              |""".trimMargin() + _existingRecordingSchedules)
        }
        val _columnsRecordingRuns: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRecordingRuns.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("schedule_id", TableInfo.Column("schedule_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("channel_id", TableInfo.Column("channel_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("channel_name", TableInfo.Column("channel_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("stream_url", TableInfo.Column("stream_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("program_title", TableInfo.Column("program_title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("scheduled_start_ms", TableInfo.Column("scheduled_start_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("scheduled_end_ms", TableInfo.Column("scheduled_end_ms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("recurrence", TableInfo.Column("recurrence", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("recurring_rule_id", TableInfo.Column("recurring_rule_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("status", TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("source_type", TableInfo.Column("source_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("resolved_url", TableInfo.Column("resolved_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("headers_json", TableInfo.Column("headers_json", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("user_agent", TableInfo.Column("user_agent", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("expiration_time", TableInfo.Column("expiration_time", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("provider_label", TableInfo.Column("provider_label", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("output_uri", TableInfo.Column("output_uri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("output_display_path", TableInfo.Column("output_display_path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("bytes_written", TableInfo.Column("bytes_written", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("average_throughput_bps", TableInfo.Column("average_throughput_bps", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("retry_count", TableInfo.Column("retry_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("last_progress_at_ms", TableInfo.Column("last_progress_at_ms", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("failure_category", TableInfo.Column("failure_category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("failure_reason", TableInfo.Column("failure_reason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("terminal_at_ms", TableInfo.Column("terminal_at_ms", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("started_at_ms", TableInfo.Column("started_at_ms", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("ended_at_ms", TableInfo.Column("ended_at_ms", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("schedule_enabled", TableInfo.Column("schedule_enabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("priority", TableInfo.Column("priority", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("alarm_start_at_ms", TableInfo.Column("alarm_start_at_ms", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("alarm_stop_at_ms", TableInfo.Column("alarm_stop_at_ms", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("created_at", TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingRuns.put("updated_at", TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRecordingRuns: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysRecordingRuns.add(TableInfo.ForeignKey("recording_schedules", "CASCADE", "NO ACTION", listOf("schedule_id"), listOf("id")))
        _foreignKeysRecordingRuns.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesRecordingRuns: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesRecordingRuns.add(TableInfo.Index("index_recording_runs_schedule_id", false, listOf("schedule_id"), listOf("ASC")))
        _indicesRecordingRuns.add(TableInfo.Index("index_recording_runs_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesRecordingRuns.add(TableInfo.Index("index_recording_runs_status_scheduled_start_ms", false, listOf("status", "scheduled_start_ms"), listOf("ASC", "ASC")))
        _indicesRecordingRuns.add(TableInfo.Index("index_recording_runs_alarm_start_at_ms", false, listOf("alarm_start_at_ms"), listOf("ASC")))
        _indicesRecordingRuns.add(TableInfo.Index("index_recording_runs_alarm_stop_at_ms", false, listOf("alarm_stop_at_ms"), listOf("ASC")))
        val _infoRecordingRuns: TableInfo = TableInfo("recording_runs", _columnsRecordingRuns, _foreignKeysRecordingRuns, _indicesRecordingRuns)
        val _existingRecordingRuns: TableInfo = tableInfoRead(connection, "recording_runs")
        if (!_infoRecordingRuns.equals(_existingRecordingRuns)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |recording_runs(com.vopo.data.local.entity.RecordingRunEntity).
              | Expected:
              |""".trimMargin() + _infoRecordingRuns + """
              |
              | Found:
              |""".trimMargin() + _existingRecordingRuns)
        }
        val _columnsProgramReminders: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsProgramReminders.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramReminders.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramReminders.put("channel_id", TableInfo.Column("channel_id", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramReminders.put("channel_name", TableInfo.Column("channel_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramReminders.put("program_title", TableInfo.Column("program_title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramReminders.put("program_start_time", TableInfo.Column("program_start_time", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramReminders.put("remind_at", TableInfo.Column("remind_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramReminders.put("lead_time_minutes", TableInfo.Column("lead_time_minutes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramReminders.put("is_dismissed", TableInfo.Column("is_dismissed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramReminders.put("notified_at", TableInfo.Column("notified_at", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsProgramReminders.put("created_at", TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysProgramReminders: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysProgramReminders.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesProgramReminders: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesProgramReminders.add(TableInfo.Index("index_program_reminders_provider_id_remind_at", false, listOf("provider_id", "remind_at"), listOf("ASC", "ASC")))
        _indicesProgramReminders.add(TableInfo.Index("index_program_reminders_is_dismissed_notified_at_remind_at", false, listOf("is_dismissed", "notified_at", "remind_at"), listOf("ASC", "ASC", "ASC")))
        _indicesProgramReminders.add(TableInfo.Index("index_program_reminders_provider_id_channel_id_program_start_time", false, listOf("provider_id", "channel_id", "program_start_time"), listOf("ASC", "ASC", "ASC")))
        _indicesProgramReminders.add(TableInfo.Index("index_program_reminders_provider_id_channel_id_program_title_program_start_time", true, listOf("provider_id", "channel_id", "program_title", "program_start_time"), listOf("ASC", "ASC", "ASC", "ASC")))
        val _infoProgramReminders: TableInfo = TableInfo("program_reminders", _columnsProgramReminders, _foreignKeysProgramReminders, _indicesProgramReminders)
        val _existingProgramReminders: TableInfo = tableInfoRead(connection, "program_reminders")
        if (!_infoProgramReminders.equals(_existingProgramReminders)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |program_reminders(com.vopo.data.local.entity.ProgramReminderEntity).
              | Expected:
              |""".trimMargin() + _infoProgramReminders + """
              |
              | Found:
              |""".trimMargin() + _existingProgramReminders)
        }
        val _columnsRecordingStorage: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsRecordingStorage.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingStorage.put("tree_uri", TableInfo.Column("tree_uri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingStorage.put("display_name", TableInfo.Column("display_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingStorage.put("output_directory", TableInfo.Column("output_directory", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingStorage.put("available_bytes", TableInfo.Column("available_bytes", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingStorage.put("is_writable", TableInfo.Column("is_writable", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingStorage.put("file_name_pattern", TableInfo.Column("file_name_pattern", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingStorage.put("retention_days", TableInfo.Column("retention_days", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingStorage.put("max_simultaneous_recordings", TableInfo.Column("max_simultaneous_recordings", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsRecordingStorage.put("updated_at", TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysRecordingStorage: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesRecordingStorage: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoRecordingStorage: TableInfo = TableInfo("recording_storage", _columnsRecordingStorage, _foreignKeysRecordingStorage, _indicesRecordingStorage)
        val _existingRecordingStorage: TableInfo = tableInfoRead(connection, "recording_storage")
        if (!_infoRecordingStorage.equals(_existingRecordingStorage)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |recording_storage(com.vopo.data.local.entity.RecordingStorageEntity).
              | Expected:
              |""".trimMargin() + _infoRecordingStorage + """
              |
              | Found:
              |""".trimMargin() + _existingRecordingStorage)
        }
        val _columnsPlaybackCompatibilityRecords: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsPlaybackCompatibilityRecords.put("id", TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackCompatibilityRecords.put("device_fingerprint", TableInfo.Column("device_fingerprint", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackCompatibilityRecords.put("device_model", TableInfo.Column("device_model", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackCompatibilityRecords.put("android_sdk", TableInfo.Column("android_sdk", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackCompatibilityRecords.put("stream_type", TableInfo.Column("stream_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackCompatibilityRecords.put("video_mime_type", TableInfo.Column("video_mime_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackCompatibilityRecords.put("resolution_bucket", TableInfo.Column("resolution_bucket", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackCompatibilityRecords.put("decoder_name", TableInfo.Column("decoder_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackCompatibilityRecords.put("surface_type", TableInfo.Column("surface_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackCompatibilityRecords.put("failure_type", TableInfo.Column("failure_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackCompatibilityRecords.put("last_failed_at", TableInfo.Column("last_failed_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackCompatibilityRecords.put("last_succeeded_at", TableInfo.Column("last_succeeded_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackCompatibilityRecords.put("failure_count", TableInfo.Column("failure_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsPlaybackCompatibilityRecords.put("success_count", TableInfo.Column("success_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysPlaybackCompatibilityRecords: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesPlaybackCompatibilityRecords: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesPlaybackCompatibilityRecords.add(TableInfo.Index("index_playback_compatibility_records_device_fingerprint_stream_type_video_mime_type_resolution_bucket_decoder_name_surface_type", true, listOf("device_fingerprint", "stream_type", "video_mime_type", "resolution_bucket", "decoder_name", "surface_type"), listOf("ASC", "ASC", "ASC", "ASC", "ASC", "ASC")))
        _indicesPlaybackCompatibilityRecords.add(TableInfo.Index("index_playback_compatibility_records_device_fingerprint_stream_type_video_mime_type_resolution_bucket", false, listOf("device_fingerprint", "stream_type", "video_mime_type", "resolution_bucket"), listOf("ASC", "ASC", "ASC", "ASC")))
        _indicesPlaybackCompatibilityRecords.add(TableInfo.Index("index_playback_compatibility_records_last_failed_at", false, listOf("last_failed_at"), listOf("ASC")))
        _indicesPlaybackCompatibilityRecords.add(TableInfo.Index("index_playback_compatibility_records_last_succeeded_at", false, listOf("last_succeeded_at"), listOf("ASC")))
        val _infoPlaybackCompatibilityRecords: TableInfo = TableInfo("playback_compatibility_records", _columnsPlaybackCompatibilityRecords, _foreignKeysPlaybackCompatibilityRecords, _indicesPlaybackCompatibilityRecords)
        val _existingPlaybackCompatibilityRecords: TableInfo = tableInfoRead(connection, "playback_compatibility_records")
        if (!_infoPlaybackCompatibilityRecords.equals(_existingPlaybackCompatibilityRecords)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |playback_compatibility_records(com.vopo.data.local.entity.PlaybackCompatibilityRecordEntity).
              | Expected:
              |""".trimMargin() + _infoPlaybackCompatibilityRecords + """
              |
              | Found:
              |""".trimMargin() + _existingPlaybackCompatibilityRecords)
        }
        val _columnsXtreamContentIndex: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsXtreamContentIndex.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("content_type", TableInfo.Column("content_type", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("remote_id", TableInfo.Column("remote_id", "TEXT", true, 3, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("local_content_id", TableInfo.Column("local_content_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("name", TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("category_id", TableInfo.Column("category_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("category_name", TableInfo.Column("category_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("image_url", TableInfo.Column("image_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("container_extension", TableInfo.Column("container_extension", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("rating", TableInfo.Column("rating", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("added_at", TableInfo.Column("added_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("remote_updated_at", TableInfo.Column("remote_updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("is_adult", TableInfo.Column("is_adult", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("indexed_at", TableInfo.Column("indexed_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("detail_hydrated_at", TableInfo.Column("detail_hydrated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("stale_state", TableInfo.Column("stale_state", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("error_state", TableInfo.Column("error_state", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamContentIndex.put("sync_fingerprint", TableInfo.Column("sync_fingerprint", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysXtreamContentIndex: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysXtreamContentIndex.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesXtreamContentIndex: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesXtreamContentIndex.add(TableInfo.Index("index_xtream_content_index_provider_id_content_type", false, listOf("provider_id", "content_type"), listOf("ASC", "ASC")))
        _indicesXtreamContentIndex.add(TableInfo.Index("index_xtream_content_index_provider_id_content_type_category_id", false, listOf("provider_id", "content_type", "category_id"), listOf("ASC", "ASC", "ASC")))
        _indicesXtreamContentIndex.add(TableInfo.Index("index_xtream_content_index_provider_id_content_type_name", false, listOf("provider_id", "content_type", "name"), listOf("ASC", "ASC", "ASC")))
        _indicesXtreamContentIndex.add(TableInfo.Index("index_xtream_content_index_provider_id_content_type_local_content_id", false, listOf("provider_id", "content_type", "local_content_id"), listOf("ASC", "ASC", "ASC")))
        _indicesXtreamContentIndex.add(TableInfo.Index("index_xtream_content_index_provider_id_indexed_at", false, listOf("provider_id", "indexed_at"), listOf("ASC", "ASC")))
        _indicesXtreamContentIndex.add(TableInfo.Index("index_xtream_content_index_stale_state", false, listOf("stale_state"), listOf("ASC")))
        val _infoXtreamContentIndex: TableInfo = TableInfo("xtream_content_index", _columnsXtreamContentIndex, _foreignKeysXtreamContentIndex, _indicesXtreamContentIndex)
        val _existingXtreamContentIndex: TableInfo = tableInfoRead(connection, "xtream_content_index")
        if (!_infoXtreamContentIndex.equals(_existingXtreamContentIndex)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |xtream_content_index(com.vopo.data.local.entity.XtreamContentIndexEntity).
              | Expected:
              |""".trimMargin() + _infoXtreamContentIndex + """
              |
              | Found:
              |""".trimMargin() + _existingXtreamContentIndex)
        }
        val _columnsXtreamIndexJobs: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsXtreamIndexJobs.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("section", TableInfo.Column("section", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("state", TableInfo.Column("state", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("total_categories", TableInfo.Column("total_categories", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("completed_categories", TableInfo.Column("completed_categories", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("next_category_index", TableInfo.Column("next_category_index", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("failed_categories", TableInfo.Column("failed_categories", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("indexed_rows", TableInfo.Column("indexed_rows", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("skipped_malformed_rows", TableInfo.Column("skipped_malformed_rows", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("deleted_pruned_rows", TableInfo.Column("deleted_pruned_rows", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("priority_category_id", TableInfo.Column("priority_category_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("priority_requested_at", TableInfo.Column("priority_requested_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("last_error", TableInfo.Column("last_error", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("last_attempt_at", TableInfo.Column("last_attempt_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("last_success_at", TableInfo.Column("last_success_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamIndexJobs.put("updated_at", TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysXtreamIndexJobs: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysXtreamIndexJobs.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesXtreamIndexJobs: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesXtreamIndexJobs.add(TableInfo.Index("index_xtream_index_jobs_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesXtreamIndexJobs.add(TableInfo.Index("index_xtream_index_jobs_section", false, listOf("section"), listOf("ASC")))
        _indicesXtreamIndexJobs.add(TableInfo.Index("index_xtream_index_jobs_state", false, listOf("state"), listOf("ASC")))
        _indicesXtreamIndexJobs.add(TableInfo.Index("index_xtream_index_jobs_updated_at", false, listOf("updated_at"), listOf("ASC")))
        val _infoXtreamIndexJobs: TableInfo = TableInfo("xtream_index_jobs", _columnsXtreamIndexJobs, _foreignKeysXtreamIndexJobs, _indicesXtreamIndexJobs)
        val _existingXtreamIndexJobs: TableInfo = tableInfoRead(connection, "xtream_index_jobs")
        if (!_infoXtreamIndexJobs.equals(_existingXtreamIndexJobs)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |xtream_index_jobs(com.vopo.data.local.entity.XtreamIndexJobEntity).
              | Expected:
              |""".trimMargin() + _infoXtreamIndexJobs + """
              |
              | Found:
              |""".trimMargin() + _existingXtreamIndexJobs)
        }
        val _columnsXtreamLiveOnboardingState: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsXtreamLiveOnboardingState.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("provider_type", TableInfo.Column("provider_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("content_type", TableInfo.Column("content_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("phase", TableInfo.Column("phase", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("staged_session_id", TableInfo.Column("staged_session_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("import_strategy", TableInfo.Column("import_strategy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("next_category_index", TableInfo.Column("next_category_index", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("accepted_row_count", TableInfo.Column("accepted_row_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("staged_flush_count", TableInfo.Column("staged_flush_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("sync_profile_tier", TableInfo.Column("sync_profile_tier", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("sync_profile_batch_size", TableInfo.Column("sync_profile_batch_size", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("sync_profile_strategy", TableInfo.Column("sync_profile_strategy", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("sync_profile_low_memory", TableInfo.Column("sync_profile_low_memory", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("sync_profile_memory_class_mb", TableInfo.Column("sync_profile_memory_class_mb", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("sync_profile_available_mem_mb", TableInfo.Column("sync_profile_available_mem_mb", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("last_error", TableInfo.Column("last_error", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("created_at", TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("updated_at", TableInfo.Column("updated_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsXtreamLiveOnboardingState.put("completed_at", TableInfo.Column("completed_at", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysXtreamLiveOnboardingState: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        _foreignKeysXtreamLiveOnboardingState.add(TableInfo.ForeignKey("providers", "CASCADE", "NO ACTION", listOf("provider_id"), listOf("id")))
        val _indicesXtreamLiveOnboardingState: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesXtreamLiveOnboardingState.add(TableInfo.Index("index_xtream_live_onboarding_state_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesXtreamLiveOnboardingState.add(TableInfo.Index("index_xtream_live_onboarding_state_phase", false, listOf("phase"), listOf("ASC")))
        _indicesXtreamLiveOnboardingState.add(TableInfo.Index("index_xtream_live_onboarding_state_updated_at", false, listOf("updated_at"), listOf("ASC")))
        _indicesXtreamLiveOnboardingState.add(TableInfo.Index("index_xtream_live_onboarding_state_staged_session_id", false, listOf("staged_session_id"), listOf("ASC")))
        val _infoXtreamLiveOnboardingState: TableInfo = TableInfo("xtream_live_onboarding_state", _columnsXtreamLiveOnboardingState, _foreignKeysXtreamLiveOnboardingState, _indicesXtreamLiveOnboardingState)
        val _existingXtreamLiveOnboardingState: TableInfo = tableInfoRead(connection, "xtream_live_onboarding_state")
        if (!_infoXtreamLiveOnboardingState.equals(_existingXtreamLiveOnboardingState)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |xtream_live_onboarding_state(com.vopo.data.local.entity.XtreamLiveOnboardingStateEntity).
              | Expected:
              |""".trimMargin() + _infoXtreamLiveOnboardingState + """
              |
              | Found:
              |""".trimMargin() + _existingXtreamLiveOnboardingState)
        }
        val _columnsDownloads: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDownloads.put("id", TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("provider_id", TableInfo.Column("provider_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("content_type", TableInfo.Column("content_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("content_id", TableInfo.Column("content_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("content_name", TableInfo.Column("content_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("stream_url", TableInfo.Column("stream_url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("source_stream_url", TableInfo.Column("source_stream_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("source_stream_id", TableInfo.Column("source_stream_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("container_extension", TableInfo.Column("container_extension", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("poster_url", TableInfo.Column("poster_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("output_uri", TableInfo.Column("output_uri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("output_display_path", TableInfo.Column("output_display_path", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("status", TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("bytes_written", TableInfo.Column("bytes_written", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("total_bytes", TableInfo.Column("total_bytes", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("supports_resume", TableInfo.Column("supports_resume", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("retry_count", TableInfo.Column("retry_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("created_at", TableInfo.Column("created_at", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("completed_at", TableInfo.Column("completed_at", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("failure_reason", TableInfo.Column("failure_reason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("series_id", TableInfo.Column("series_id", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("season_number", TableInfo.Column("season_number", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloads.put("episode_number", TableInfo.Column("episode_number", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDownloads: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDownloads: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesDownloads.add(TableInfo.Index("index_downloads_status", false, listOf("status"), listOf("ASC")))
        _indicesDownloads.add(TableInfo.Index("index_downloads_provider_id", false, listOf("provider_id"), listOf("ASC")))
        _indicesDownloads.add(TableInfo.Index("index_downloads_content_type_content_id", false, listOf("content_type", "content_id"), listOf("ASC", "ASC")))
        val _infoDownloads: TableInfo = TableInfo("downloads", _columnsDownloads, _foreignKeysDownloads, _indicesDownloads)
        val _existingDownloads: TableInfo = tableInfoRead(connection, "downloads")
        if (!_infoDownloads.equals(_existingDownloads)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |downloads(com.vopo.data.local.entity.DownloadEntity).
              | Expected:
              |""".trimMargin() + _infoDownloads + """
              |
              | Found:
              |""".trimMargin() + _existingDownloads)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    _shadowTablesMap.put("channels_fts", "channels")
    _shadowTablesMap.put("movies_fts", "movies")
    _shadowTablesMap.put("series_fts", "series")
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "providers", "channels", "channel_preferences", "channels_fts", "movies", "movies_fts", "series", "series_fts", "episodes", "categories", "channel_import_stage", "movie_import_stage", "series_import_stage", "category_import_stage", "programs", "favorites", "virtual_groups", "playback_history", "tmdb_identity", "search_history", "sync_metadata", "movie_category_hydration", "series_category_hydration", "epg_sources", "provider_epg_sources", "epg_channels", "epg_programmes", "channel_epg_mappings", "combined_m3u_profiles", "combined_m3u_profile_members", "recording_schedules", "recording_runs", "program_reminders", "recording_storage", "playback_compatibility_records", "xtream_content_index", "xtream_index_jobs", "xtream_live_onboarding_state", "downloads")
  }

  public override fun clearAllTables() {
    super.performClear(true, "providers", "channels", "channel_preferences", "channels_fts", "movies", "movies_fts", "series", "series_fts", "episodes", "categories", "channel_import_stage", "movie_import_stage", "series_import_stage", "category_import_stage", "programs", "favorites", "virtual_groups", "playback_history", "tmdb_identity", "search_history", "sync_metadata", "movie_category_hydration", "series_category_hydration", "epg_sources", "provider_epg_sources", "epg_channels", "epg_programmes", "channel_epg_mappings", "combined_m3u_profiles", "combined_m3u_profile_members", "recording_schedules", "recording_runs", "program_reminders", "recording_storage", "playback_compatibility_records", "xtream_content_index", "xtream_index_jobs", "xtream_live_onboarding_state", "downloads")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(ProviderDao::class, ProviderDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ChannelDao::class, ChannelDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ChannelPreferenceDao::class, ChannelPreferenceDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(MovieDao::class, MovieDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SeriesDao::class, SeriesDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(EpisodeDao::class, EpisodeDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CategoryDao::class, CategoryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CatalogSyncDao::class, CatalogSyncDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ProgramDao::class, ProgramDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(FavoriteDao::class, FavoriteDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(VirtualGroupDao::class, VirtualGroupDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PlaybackHistoryDao::class, PlaybackHistoryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(TmdbIdentityDao::class, TmdbIdentityDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SearchHistoryDao::class, SearchHistoryDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SearchDao::class, SearchDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SyncMetadataDao::class, SyncMetadataDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(MovieCategoryHydrationDao::class, MovieCategoryHydrationDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(SeriesCategoryHydrationDao::class, SeriesCategoryHydrationDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(EpgSourceDao::class, EpgSourceDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ProviderEpgSourceDao::class, ProviderEpgSourceDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(EpgChannelDao::class, EpgChannelDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(EpgProgrammeDao::class, EpgProgrammeDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ChannelEpgMappingDao::class, ChannelEpgMappingDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CombinedM3uProfileDao::class, CombinedM3uProfileDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(CombinedM3uProfileMemberDao::class, CombinedM3uProfileMemberDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RecordingScheduleDao::class, RecordingScheduleDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RecordingRunDao::class, RecordingRunDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ProgramReminderDao::class, ProgramReminderDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(RecordingStorageDao::class, RecordingStorageDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(PlaybackCompatibilityDao::class, PlaybackCompatibilityDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(XtreamContentIndexDao::class, XtreamContentIndexDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(XtreamIndexJobDao::class, XtreamIndexJobDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(XtreamLiveOnboardingDao::class, XtreamLiveOnboardingDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(DownloadDao::class, DownloadDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>): List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun providerDao(): ProviderDao = _providerDao.value

  public override fun channelDao(): ChannelDao = _channelDao.value

  public override fun channelPreferenceDao(): ChannelPreferenceDao = _channelPreferenceDao.value

  public override fun movieDao(): MovieDao = _movieDao.value

  public override fun seriesDao(): SeriesDao = _seriesDao.value

  public override fun episodeDao(): EpisodeDao = _episodeDao.value

  public override fun categoryDao(): CategoryDao = _categoryDao.value

  public override fun catalogSyncDao(): CatalogSyncDao = _catalogSyncDao.value

  public override fun programDao(): ProgramDao = _programDao.value

  public override fun favoriteDao(): FavoriteDao = _favoriteDao.value

  public override fun virtualGroupDao(): VirtualGroupDao = _virtualGroupDao.value

  public override fun playbackHistoryDao(): PlaybackHistoryDao = _playbackHistoryDao.value

  public override fun tmdbIdentityDao(): TmdbIdentityDao = _tmdbIdentityDao.value

  public override fun searchHistoryDao(): SearchHistoryDao = _searchHistoryDao.value

  public override fun searchDao(): SearchDao = _searchDao.value

  public override fun syncMetadataDao(): SyncMetadataDao = _syncMetadataDao.value

  public override fun movieCategoryHydrationDao(): MovieCategoryHydrationDao = _movieCategoryHydrationDao.value

  public override fun seriesCategoryHydrationDao(): SeriesCategoryHydrationDao = _seriesCategoryHydrationDao.value

  public override fun epgSourceDao(): EpgSourceDao = _epgSourceDao.value

  public override fun providerEpgSourceDao(): ProviderEpgSourceDao = _providerEpgSourceDao.value

  public override fun epgChannelDao(): EpgChannelDao = _epgChannelDao.value

  public override fun epgProgrammeDao(): EpgProgrammeDao = _epgProgrammeDao.value

  public override fun channelEpgMappingDao(): ChannelEpgMappingDao = _channelEpgMappingDao.value

  public override fun combinedM3uProfileDao(): CombinedM3uProfileDao = _combinedM3uProfileDao.value

  public override fun combinedM3uProfileMemberDao(): CombinedM3uProfileMemberDao = _combinedM3uProfileMemberDao.value

  public override fun recordingScheduleDao(): RecordingScheduleDao = _recordingScheduleDao.value

  public override fun recordingRunDao(): RecordingRunDao = _recordingRunDao.value

  public override fun programReminderDao(): ProgramReminderDao = _programReminderDao.value

  public override fun recordingStorageDao(): RecordingStorageDao = _recordingStorageDao.value

  public override fun playbackCompatibilityDao(): PlaybackCompatibilityDao = _playbackCompatibilityDao.value

  public override fun xtreamContentIndexDao(): XtreamContentIndexDao = _xtreamContentIndexDao.value

  public override fun xtreamIndexJobDao(): XtreamIndexJobDao = _xtreamIndexJobDao.value

  public override fun xtreamLiveOnboardingDao(): XtreamLiveOnboardingDao = _xtreamLiveOnboardingDao.value

  public override fun downloadDao(): DownloadDao = _downloadDao.value
}
