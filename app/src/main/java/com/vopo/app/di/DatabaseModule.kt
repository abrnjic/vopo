package com.vopo.app.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import com.vopo.app.BuildConfig
import com.vopo.data.local.VopoDatabase
import com.vopo.data.local.dao.*
import com.vopo.data.remote.jellyfin.JellyfinProvider
import com.google.gson.Gson
import okhttp3.OkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DEBUG_SLOW_QUERY_THRESHOLD_MS = 100L

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): VopoDatabase =
        Room.databaseBuilder(
            context,
            VopoDatabase::class.java,
            "vopo.db"
        )
            .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
            .openHelperFactory(
                if (BuildConfig.DEBUG) {
                    SlowQueryLoggingOpenHelperFactory(
                        delegate = FrameworkSQLiteOpenHelperFactory(),
                        slowQueryThresholdMs = DEBUG_SLOW_QUERY_THRESHOLD_MS
                    )
                } else {
                    FrameworkSQLiteOpenHelperFactory()
                }
            )
            .addMigrations(
                VopoDatabase.MIGRATION_1_2,
                VopoDatabase.MIGRATION_2_3,
                VopoDatabase.MIGRATION_3_4,
                VopoDatabase.MIGRATION_4_5,
                VopoDatabase.MIGRATION_5_6,
                VopoDatabase.MIGRATION_6_7,
                VopoDatabase.MIGRATION_7_8,
                VopoDatabase.MIGRATION_8_9,
                VopoDatabase.MIGRATION_9_10,
                VopoDatabase.MIGRATION_10_11,
                VopoDatabase.MIGRATION_11_12,
                VopoDatabase.MIGRATION_12_13,
                VopoDatabase.MIGRATION_13_14,
                VopoDatabase.MIGRATION_14_15,
                VopoDatabase.MIGRATION_15_16,
                VopoDatabase.MIGRATION_16_17,
                VopoDatabase.MIGRATION_17_18,
                VopoDatabase.MIGRATION_18_19,
                VopoDatabase.MIGRATION_19_20,
                VopoDatabase.MIGRATION_20_21,
                VopoDatabase.MIGRATION_21_22,
                VopoDatabase.MIGRATION_22_23,
                VopoDatabase.MIGRATION_23_24,
                VopoDatabase.MIGRATION_24_25,
                VopoDatabase.MIGRATION_25_26,
                VopoDatabase.MIGRATION_26_27,
                VopoDatabase.MIGRATION_27_28,
                VopoDatabase.MIGRATION_28_29,
                VopoDatabase.MIGRATION_29_30,
                VopoDatabase.MIGRATION_30_31,
                VopoDatabase.MIGRATION_31_32,
                VopoDatabase.MIGRATION_32_33,
                VopoDatabase.MIGRATION_33_34,
                VopoDatabase.MIGRATION_34_35,
                VopoDatabase.MIGRATION_35_36,
                VopoDatabase.MIGRATION_36_37,
                VopoDatabase.MIGRATION_37_38,
                VopoDatabase.MIGRATION_38_39,
                VopoDatabase.MIGRATION_39_40,
                VopoDatabase.MIGRATION_40_41,
                VopoDatabase.MIGRATION_41_42,
                VopoDatabase.MIGRATION_42_43,
                VopoDatabase.MIGRATION_43_44,
                VopoDatabase.MIGRATION_44_45,
                VopoDatabase.MIGRATION_45_46,
                VopoDatabase.MIGRATION_46_47,
                VopoDatabase.MIGRATION_47_48,
                VopoDatabase.MIGRATION_48_49,
                VopoDatabase.MIGRATION_49_50,
                VopoDatabase.MIGRATION_50_51,
                VopoDatabase.MIGRATION_51_52,
                VopoDatabase.MIGRATION_52_53,
                VopoDatabase.MIGRATION_53_54,
                VopoDatabase.MIGRATION_54_55,
                VopoDatabase.MIGRATION_55_56,
                VopoDatabase.MIGRATION_56_57,
                VopoDatabase.MIGRATION_57_58,
                VopoDatabase.MIGRATION_58_59,
                VopoDatabase.MIGRATION_59_60,
                VopoDatabase.MIGRATION_60_61
            )
            // NOTE: fallbackToDestructiveMigration() intentionally removed.
            // All future schema changes MUST add a corresponding Migration in VopoDatabase.
            .build()

    @Provides @Singleton
    fun provideJellyfinProvider(okHttpClient: OkHttpClient, gson: Gson): JellyfinProvider = JellyfinProvider(okHttpClient, gson)

    @Provides fun provideProviderDao(db: VopoDatabase): ProviderDao = db.providerDao()
    @Provides fun provideChannelDao(db: VopoDatabase): ChannelDao = db.channelDao()
    @Provides fun provideChannelPreferenceDao(db: VopoDatabase): ChannelPreferenceDao = db.channelPreferenceDao()
    @Provides fun provideMovieDao(db: VopoDatabase): MovieDao = db.movieDao()
    @Provides fun provideSeriesDao(db: VopoDatabase): SeriesDao = db.seriesDao()
    @Provides fun provideEpisodeDao(db: VopoDatabase): EpisodeDao = db.episodeDao()
    @Provides fun provideCategoryDao(db: VopoDatabase): CategoryDao = db.categoryDao()
    @Provides fun provideCatalogSyncDao(db: VopoDatabase): CatalogSyncDao = db.catalogSyncDao()
    @Provides fun provideProgramDao(db: VopoDatabase): ProgramDao = db.programDao()
    @Provides fun provideFavoriteDao(db: VopoDatabase): FavoriteDao = db.favoriteDao()
    @Provides fun provideVirtualGroupDao(db: VopoDatabase): VirtualGroupDao = db.virtualGroupDao()
    @Provides fun providePlaybackHistoryDao(db: VopoDatabase): PlaybackHistoryDao = db.playbackHistoryDao()
    @Provides fun provideTmdbIdentityDao(db: VopoDatabase): TmdbIdentityDao = db.tmdbIdentityDao()
    @Provides fun provideSearchHistoryDao(db: VopoDatabase): SearchHistoryDao = db.searchHistoryDao()
    @Provides fun provideSearchDao(db: VopoDatabase): SearchDao = db.searchDao()
    @Provides fun provideSyncMetadataDao(db: VopoDatabase): SyncMetadataDao = db.syncMetadataDao()
    @Provides fun provideMovieCategoryHydrationDao(db: VopoDatabase): MovieCategoryHydrationDao = db.movieCategoryHydrationDao()
    @Provides fun provideSeriesCategoryHydrationDao(db: VopoDatabase): SeriesCategoryHydrationDao = db.seriesCategoryHydrationDao()
    @Provides fun provideEpgSourceDao(db: VopoDatabase): EpgSourceDao = db.epgSourceDao()
    @Provides fun provideProviderEpgSourceDao(db: VopoDatabase): ProviderEpgSourceDao = db.providerEpgSourceDao()
    @Provides fun provideEpgChannelDao(db: VopoDatabase): EpgChannelDao = db.epgChannelDao()
    @Provides fun provideEpgProgrammeDao(db: VopoDatabase): EpgProgrammeDao = db.epgProgrammeDao()
    @Provides fun provideChannelEpgMappingDao(db: VopoDatabase): ChannelEpgMappingDao = db.channelEpgMappingDao()
    @Provides fun provideCombinedM3uProfileDao(db: VopoDatabase): CombinedM3uProfileDao = db.combinedM3uProfileDao()
    @Provides fun provideCombinedM3uProfileMemberDao(db: VopoDatabase): CombinedM3uProfileMemberDao = db.combinedM3uProfileMemberDao()
    @Provides fun provideRecordingScheduleDao(db: VopoDatabase): RecordingScheduleDao = db.recordingScheduleDao()
    @Provides fun provideRecordingRunDao(db: VopoDatabase): RecordingRunDao = db.recordingRunDao()
    @Provides fun provideProgramReminderDao(db: VopoDatabase): ProgramReminderDao = db.programReminderDao()
    @Provides fun provideRecordingStorageDao(db: VopoDatabase): RecordingStorageDao = db.recordingStorageDao()
    @Provides fun providePlaybackCompatibilityDao(db: VopoDatabase): PlaybackCompatibilityDao = db.playbackCompatibilityDao()
    @Provides fun provideXtreamContentIndexDao(db: VopoDatabase): XtreamContentIndexDao = db.xtreamContentIndexDao()
    @Provides fun provideXtreamIndexJobDao(db: VopoDatabase): XtreamIndexJobDao = db.xtreamIndexJobDao()
    @Provides fun provideXtreamLiveOnboardingDao(db: VopoDatabase): XtreamLiveOnboardingDao = db.xtreamLiveOnboardingDao()
    @Provides fun provideDownloadDao(db: VopoDatabase): DownloadDao = db.downloadDao()
}
