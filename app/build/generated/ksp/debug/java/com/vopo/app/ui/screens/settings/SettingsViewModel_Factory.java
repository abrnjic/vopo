package com.vopo.app.ui.screens.settings;

import android.app.Application;
import com.vopo.app.tv.LauncherRecommendationsManager;
import com.vopo.app.tv.WatchNextManager;
import com.vopo.app.tvinput.TvInputChannelSyncManager;
import com.vopo.app.update.AppUpdateInstaller;
import com.vopo.app.update.GitHubReleaseChecker;
import com.vopo.data.local.dao.ProgramDao;
import com.vopo.data.local.dao.XtreamIndexJobDao;
import com.vopo.data.local.dao.XtreamLiveOnboardingDao;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.data.sync.SyncManager;
import com.vopo.domain.manager.BackupManager;
import com.vopo.domain.manager.DriveBackupSyncManager;
import com.vopo.domain.manager.ParentalControlManager;
import com.vopo.domain.manager.RecordingManager;
import com.vopo.domain.repository.CategoryRepository;
import com.vopo.domain.repository.ChannelRepository;
import com.vopo.domain.repository.CombinedM3uRepository;
import com.vopo.domain.repository.EpgSourceRepository;
import com.vopo.domain.repository.MovieRepository;
import com.vopo.domain.repository.PlaybackHistoryRepository;
import com.vopo.domain.repository.ProviderRepository;
import com.vopo.domain.repository.SeriesRepository;
import com.vopo.domain.repository.SyncMetadataRepository;
import com.vopo.domain.usecase.GetCustomCategories;
import com.vopo.domain.usecase.SyncProvider;
import com.vopo.player.AudioCompatibilityMemoryStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<CombinedM3uRepository> combinedM3uRepositoryProvider;

  private final Provider<CategoryRepository> categoryRepositoryProvider;

  private final Provider<ChannelRepository> channelRepositoryProvider;

  private final Provider<MovieRepository> movieRepositoryProvider;

  private final Provider<SeriesRepository> seriesRepositoryProvider;

  private final Provider<ProgramDao> programDaoProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<InternetSpeedTestRunner> internetSpeedTestRunnerProvider;

  private final Provider<BackupManager> backupManagerProvider;

  private final Provider<DriveBackupSyncManager> driveBackupSyncManagerProvider;

  private final Provider<RecordingManager> recordingManagerProvider;

  private final Provider<ParentalControlManager> parentalControlManagerProvider;

  private final Provider<SyncManager> syncManagerProvider;

  private final Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider;

  private final Provider<XtreamLiveOnboardingDao> xtreamLiveOnboardingDaoProvider;

  private final Provider<SyncMetadataRepository> syncMetadataRepositoryProvider;

  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  private final Provider<WatchNextManager> watchNextManagerProvider;

  private final Provider<LauncherRecommendationsManager> launcherRecommendationsManagerProvider;

  private final Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider;

  private final Provider<SyncProvider> syncProvider;

  private final Provider<EpgSourceRepository> epgSourceRepositoryProvider;

  private final Provider<GitHubReleaseChecker> gitHubReleaseCheckerProvider;

  private final Provider<AppUpdateInstaller> appUpdateInstallerProvider;

  private final Provider<GetCustomCategories> getCustomCategoriesProvider;

  private final Provider<AudioCompatibilityMemoryStore> audioCompatibilityMemoryStoreProvider;

  public SettingsViewModel_Factory(Provider<Application> applicationProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<SeriesRepository> seriesRepositoryProvider, Provider<ProgramDao> programDaoProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<InternetSpeedTestRunner> internetSpeedTestRunnerProvider,
      Provider<BackupManager> backupManagerProvider,
      Provider<DriveBackupSyncManager> driveBackupSyncManagerProvider,
      Provider<RecordingManager> recordingManagerProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider,
      Provider<SyncManager> syncManagerProvider,
      Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider,
      Provider<XtreamLiveOnboardingDao> xtreamLiveOnboardingDaoProvider,
      Provider<SyncMetadataRepository> syncMetadataRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<WatchNextManager> watchNextManagerProvider,
      Provider<LauncherRecommendationsManager> launcherRecommendationsManagerProvider,
      Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider,
      Provider<SyncProvider> syncProvider,
      Provider<EpgSourceRepository> epgSourceRepositoryProvider,
      Provider<GitHubReleaseChecker> gitHubReleaseCheckerProvider,
      Provider<AppUpdateInstaller> appUpdateInstallerProvider,
      Provider<GetCustomCategories> getCustomCategoriesProvider,
      Provider<AudioCompatibilityMemoryStore> audioCompatibilityMemoryStoreProvider) {
    this.applicationProvider = applicationProvider;
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.combinedM3uRepositoryProvider = combinedM3uRepositoryProvider;
    this.categoryRepositoryProvider = categoryRepositoryProvider;
    this.channelRepositoryProvider = channelRepositoryProvider;
    this.movieRepositoryProvider = movieRepositoryProvider;
    this.seriesRepositoryProvider = seriesRepositoryProvider;
    this.programDaoProvider = programDaoProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.internetSpeedTestRunnerProvider = internetSpeedTestRunnerProvider;
    this.backupManagerProvider = backupManagerProvider;
    this.driveBackupSyncManagerProvider = driveBackupSyncManagerProvider;
    this.recordingManagerProvider = recordingManagerProvider;
    this.parentalControlManagerProvider = parentalControlManagerProvider;
    this.syncManagerProvider = syncManagerProvider;
    this.xtreamIndexJobDaoProvider = xtreamIndexJobDaoProvider;
    this.xtreamLiveOnboardingDaoProvider = xtreamLiveOnboardingDaoProvider;
    this.syncMetadataRepositoryProvider = syncMetadataRepositoryProvider;
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
    this.watchNextManagerProvider = watchNextManagerProvider;
    this.launcherRecommendationsManagerProvider = launcherRecommendationsManagerProvider;
    this.tvInputChannelSyncManagerProvider = tvInputChannelSyncManagerProvider;
    this.syncProvider = syncProvider;
    this.epgSourceRepositoryProvider = epgSourceRepositoryProvider;
    this.gitHubReleaseCheckerProvider = gitHubReleaseCheckerProvider;
    this.appUpdateInstallerProvider = appUpdateInstallerProvider;
    this.getCustomCategoriesProvider = getCustomCategoriesProvider;
    this.audioCompatibilityMemoryStoreProvider = audioCompatibilityMemoryStoreProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(applicationProvider.get(), providerRepositoryProvider.get(), combinedM3uRepositoryProvider.get(), categoryRepositoryProvider.get(), channelRepositoryProvider.get(), movieRepositoryProvider.get(), seriesRepositoryProvider.get(), programDaoProvider.get(), preferencesRepositoryProvider.get(), internetSpeedTestRunnerProvider.get(), backupManagerProvider.get(), driveBackupSyncManagerProvider.get(), recordingManagerProvider.get(), parentalControlManagerProvider.get(), syncManagerProvider.get(), xtreamIndexJobDaoProvider.get(), xtreamLiveOnboardingDaoProvider.get(), syncMetadataRepositoryProvider.get(), playbackHistoryRepositoryProvider.get(), watchNextManagerProvider.get(), launcherRecommendationsManagerProvider.get(), tvInputChannelSyncManagerProvider.get(), syncProvider.get(), epgSourceRepositoryProvider.get(), gitHubReleaseCheckerProvider.get(), appUpdateInstallerProvider.get(), getCustomCategoriesProvider.get(), audioCompatibilityMemoryStoreProvider.get());
  }

  public static SettingsViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<SeriesRepository> seriesRepositoryProvider, Provider<ProgramDao> programDaoProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<InternetSpeedTestRunner> internetSpeedTestRunnerProvider,
      Provider<BackupManager> backupManagerProvider,
      Provider<DriveBackupSyncManager> driveBackupSyncManagerProvider,
      Provider<RecordingManager> recordingManagerProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider,
      Provider<SyncManager> syncManagerProvider,
      Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider,
      Provider<XtreamLiveOnboardingDao> xtreamLiveOnboardingDaoProvider,
      Provider<SyncMetadataRepository> syncMetadataRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<WatchNextManager> watchNextManagerProvider,
      Provider<LauncherRecommendationsManager> launcherRecommendationsManagerProvider,
      Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider,
      Provider<SyncProvider> syncProvider,
      Provider<EpgSourceRepository> epgSourceRepositoryProvider,
      Provider<GitHubReleaseChecker> gitHubReleaseCheckerProvider,
      Provider<AppUpdateInstaller> appUpdateInstallerProvider,
      Provider<GetCustomCategories> getCustomCategoriesProvider,
      Provider<AudioCompatibilityMemoryStore> audioCompatibilityMemoryStoreProvider) {
    return new SettingsViewModel_Factory(applicationProvider, providerRepositoryProvider, combinedM3uRepositoryProvider, categoryRepositoryProvider, channelRepositoryProvider, movieRepositoryProvider, seriesRepositoryProvider, programDaoProvider, preferencesRepositoryProvider, internetSpeedTestRunnerProvider, backupManagerProvider, driveBackupSyncManagerProvider, recordingManagerProvider, parentalControlManagerProvider, syncManagerProvider, xtreamIndexJobDaoProvider, xtreamLiveOnboardingDaoProvider, syncMetadataRepositoryProvider, playbackHistoryRepositoryProvider, watchNextManagerProvider, launcherRecommendationsManagerProvider, tvInputChannelSyncManagerProvider, syncProvider, epgSourceRepositoryProvider, gitHubReleaseCheckerProvider, appUpdateInstallerProvider, getCustomCategoriesProvider, audioCompatibilityMemoryStoreProvider);
  }

  public static SettingsViewModel newInstance(Application application,
      ProviderRepository providerRepository, CombinedM3uRepository combinedM3uRepository,
      CategoryRepository categoryRepository, ChannelRepository channelRepository,
      MovieRepository movieRepository, SeriesRepository seriesRepository, ProgramDao programDao,
      PreferencesRepository preferencesRepository, InternetSpeedTestRunner internetSpeedTestRunner,
      BackupManager backupManager, DriveBackupSyncManager driveBackupSyncManager,
      RecordingManager recordingManager, ParentalControlManager parentalControlManager,
      SyncManager syncManager, XtreamIndexJobDao xtreamIndexJobDao,
      XtreamLiveOnboardingDao xtreamLiveOnboardingDao,
      SyncMetadataRepository syncMetadataRepository,
      PlaybackHistoryRepository playbackHistoryRepository, WatchNextManager watchNextManager,
      LauncherRecommendationsManager launcherRecommendationsManager,
      TvInputChannelSyncManager tvInputChannelSyncManager, SyncProvider syncProvider,
      EpgSourceRepository epgSourceRepository, GitHubReleaseChecker gitHubReleaseChecker,
      AppUpdateInstaller appUpdateInstaller, GetCustomCategories getCustomCategories,
      AudioCompatibilityMemoryStore audioCompatibilityMemoryStore) {
    return new SettingsViewModel(application, providerRepository, combinedM3uRepository, categoryRepository, channelRepository, movieRepository, seriesRepository, programDao, preferencesRepository, internetSpeedTestRunner, backupManager, driveBackupSyncManager, recordingManager, parentalControlManager, syncManager, xtreamIndexJobDao, xtreamLiveOnboardingDao, syncMetadataRepository, playbackHistoryRepository, watchNextManager, launcherRecommendationsManager, tvInputChannelSyncManager, syncProvider, epgSourceRepository, gitHubReleaseChecker, appUpdateInstaller, getCustomCategories, audioCompatibilityMemoryStore);
  }
}
