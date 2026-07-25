package com.vopo.data.sync;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.ChannelDao;
import com.vopo.data.local.dao.EpgProgrammeDao;
import com.vopo.data.local.dao.EpisodeDao;
import com.vopo.data.local.dao.FavoriteDao;
import com.vopo.data.local.dao.ProgramDao;
import com.vopo.data.local.dao.ProgramReminderDao;
import com.vopo.data.local.dao.SearchHistoryDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class DatabaseMaintenanceManager_Factory implements Factory<DatabaseMaintenanceManager> {
  private final Provider<VopoDatabase> databaseProvider;

  private final Provider<ChannelDao> channelDaoProvider;

  private final Provider<ProgramDao> programDaoProvider;

  private final Provider<EpgProgrammeDao> epgProgrammeDaoProvider;

  private final Provider<EpisodeDao> episodeDaoProvider;

  private final Provider<FavoriteDao> favoriteDaoProvider;

  private final Provider<ProgramReminderDao> programReminderDaoProvider;

  private final Provider<SearchHistoryDao> searchHistoryDaoProvider;

  private final Provider<SyncManager> syncManagerProvider;

  public DatabaseMaintenanceManager_Factory(Provider<VopoDatabase> databaseProvider,
      Provider<ChannelDao> channelDaoProvider, Provider<ProgramDao> programDaoProvider,
      Provider<EpgProgrammeDao> epgProgrammeDaoProvider, Provider<EpisodeDao> episodeDaoProvider,
      Provider<FavoriteDao> favoriteDaoProvider,
      Provider<ProgramReminderDao> programReminderDaoProvider,
      Provider<SearchHistoryDao> searchHistoryDaoProvider,
      Provider<SyncManager> syncManagerProvider) {
    this.databaseProvider = databaseProvider;
    this.channelDaoProvider = channelDaoProvider;
    this.programDaoProvider = programDaoProvider;
    this.epgProgrammeDaoProvider = epgProgrammeDaoProvider;
    this.episodeDaoProvider = episodeDaoProvider;
    this.favoriteDaoProvider = favoriteDaoProvider;
    this.programReminderDaoProvider = programReminderDaoProvider;
    this.searchHistoryDaoProvider = searchHistoryDaoProvider;
    this.syncManagerProvider = syncManagerProvider;
  }

  @Override
  public DatabaseMaintenanceManager get() {
    return newInstance(databaseProvider.get(), channelDaoProvider.get(), programDaoProvider.get(), epgProgrammeDaoProvider.get(), episodeDaoProvider.get(), favoriteDaoProvider.get(), programReminderDaoProvider.get(), searchHistoryDaoProvider.get(), syncManagerProvider.get());
  }

  public static DatabaseMaintenanceManager_Factory create(Provider<VopoDatabase> databaseProvider,
      Provider<ChannelDao> channelDaoProvider, Provider<ProgramDao> programDaoProvider,
      Provider<EpgProgrammeDao> epgProgrammeDaoProvider, Provider<EpisodeDao> episodeDaoProvider,
      Provider<FavoriteDao> favoriteDaoProvider,
      Provider<ProgramReminderDao> programReminderDaoProvider,
      Provider<SearchHistoryDao> searchHistoryDaoProvider,
      Provider<SyncManager> syncManagerProvider) {
    return new DatabaseMaintenanceManager_Factory(databaseProvider, channelDaoProvider, programDaoProvider, epgProgrammeDaoProvider, episodeDaoProvider, favoriteDaoProvider, programReminderDaoProvider, searchHistoryDaoProvider, syncManagerProvider);
  }

  public static DatabaseMaintenanceManager newInstance(VopoDatabase database, ChannelDao channelDao,
      ProgramDao programDao, EpgProgrammeDao epgProgrammeDao, EpisodeDao episodeDao,
      FavoriteDao favoriteDao, ProgramReminderDao programReminderDao,
      SearchHistoryDao searchHistoryDao, SyncManager syncManager) {
    return new DatabaseMaintenanceManager(database, channelDao, programDao, epgProgrammeDao, episodeDao, favoriteDao, programReminderDao, searchHistoryDao, syncManager);
  }
}
