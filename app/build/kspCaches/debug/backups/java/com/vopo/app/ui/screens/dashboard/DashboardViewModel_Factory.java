package com.vopo.app.ui.screens.dashboard;

import android.content.Context;
import com.vopo.app.update.AppUpdateInstaller;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.data.sync.SyncManager;
import com.vopo.domain.manager.RecordingManager;
import com.vopo.domain.repository.ChannelRepository;
import com.vopo.domain.repository.CombinedM3uRepository;
import com.vopo.domain.repository.FavoriteRepository;
import com.vopo.domain.repository.MovieRepository;
import com.vopo.domain.repository.PlaybackHistoryRepository;
import com.vopo.domain.repository.ProviderRepository;
import com.vopo.domain.repository.SeriesRepository;
import com.vopo.domain.usecase.GetContinueWatching;
import com.vopo.domain.usecase.GetCustomCategories;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<Context> appContextProvider;

  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<CombinedM3uRepository> combinedM3uRepositoryProvider;

  private final Provider<FavoriteRepository> favoriteRepositoryProvider;

  private final Provider<ChannelRepository> channelRepositoryProvider;

  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  private final Provider<MovieRepository> movieRepositoryProvider;

  private final Provider<SeriesRepository> seriesRepositoryProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<GetContinueWatching> getContinueWatchingProvider;

  private final Provider<GetCustomCategories> getCustomCategoriesProvider;

  private final Provider<SyncManager> syncManagerProvider;

  private final Provider<AppUpdateInstaller> appUpdateInstallerProvider;

  private final Provider<RecordingManager> recordingManagerProvider;

  public DashboardViewModel_Factory(Provider<Context> appContextProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<SeriesRepository> seriesRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<GetContinueWatching> getContinueWatchingProvider,
      Provider<GetCustomCategories> getCustomCategoriesProvider,
      Provider<SyncManager> syncManagerProvider,
      Provider<AppUpdateInstaller> appUpdateInstallerProvider,
      Provider<RecordingManager> recordingManagerProvider) {
    this.appContextProvider = appContextProvider;
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.combinedM3uRepositoryProvider = combinedM3uRepositoryProvider;
    this.favoriteRepositoryProvider = favoriteRepositoryProvider;
    this.channelRepositoryProvider = channelRepositoryProvider;
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
    this.movieRepositoryProvider = movieRepositoryProvider;
    this.seriesRepositoryProvider = seriesRepositoryProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.getContinueWatchingProvider = getContinueWatchingProvider;
    this.getCustomCategoriesProvider = getCustomCategoriesProvider;
    this.syncManagerProvider = syncManagerProvider;
    this.appUpdateInstallerProvider = appUpdateInstallerProvider;
    this.recordingManagerProvider = recordingManagerProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(appContextProvider.get(), providerRepositoryProvider.get(), combinedM3uRepositoryProvider.get(), favoriteRepositoryProvider.get(), channelRepositoryProvider.get(), playbackHistoryRepositoryProvider.get(), movieRepositoryProvider.get(), seriesRepositoryProvider.get(), preferencesRepositoryProvider.get(), getContinueWatchingProvider.get(), getCustomCategoriesProvider.get(), syncManagerProvider.get(), appUpdateInstallerProvider.get(), recordingManagerProvider.get());
  }

  public static DashboardViewModel_Factory create(Provider<Context> appContextProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<SeriesRepository> seriesRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<GetContinueWatching> getContinueWatchingProvider,
      Provider<GetCustomCategories> getCustomCategoriesProvider,
      Provider<SyncManager> syncManagerProvider,
      Provider<AppUpdateInstaller> appUpdateInstallerProvider,
      Provider<RecordingManager> recordingManagerProvider) {
    return new DashboardViewModel_Factory(appContextProvider, providerRepositoryProvider, combinedM3uRepositoryProvider, favoriteRepositoryProvider, channelRepositoryProvider, playbackHistoryRepositoryProvider, movieRepositoryProvider, seriesRepositoryProvider, preferencesRepositoryProvider, getContinueWatchingProvider, getCustomCategoriesProvider, syncManagerProvider, appUpdateInstallerProvider, recordingManagerProvider);
  }

  public static DashboardViewModel newInstance(Context appContext,
      ProviderRepository providerRepository, CombinedM3uRepository combinedM3uRepository,
      FavoriteRepository favoriteRepository, ChannelRepository channelRepository,
      PlaybackHistoryRepository playbackHistoryRepository, MovieRepository movieRepository,
      SeriesRepository seriesRepository, PreferencesRepository preferencesRepository,
      GetContinueWatching getContinueWatching, GetCustomCategories getCustomCategories,
      SyncManager syncManager, AppUpdateInstaller appUpdateInstaller,
      RecordingManager recordingManager) {
    return new DashboardViewModel(appContext, providerRepository, combinedM3uRepository, favoriteRepository, channelRepository, playbackHistoryRepository, movieRepository, seriesRepository, preferencesRepository, getContinueWatching, getCustomCategories, syncManager, appUpdateInstaller, recordingManager);
  }
}
