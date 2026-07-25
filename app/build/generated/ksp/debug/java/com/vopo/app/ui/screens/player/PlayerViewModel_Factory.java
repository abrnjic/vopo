package com.vopo.app.ui.screens.player;

import android.content.Context;
import com.vopo.app.cast.CastManager;
import com.vopo.app.player.LivePreviewHandoffManager;
import com.vopo.app.plugins.VopoPluginManager;
import com.vopo.app.tv.LauncherRecommendationsManager;
import com.vopo.app.tv.WatchNextManager;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.data.remote.xtream.XtreamStreamUrlResolver;
import com.vopo.data.sync.SyncManager;
import com.vopo.domain.manager.RecordingManager;
import com.vopo.domain.repository.ChannelRepository;
import com.vopo.domain.repository.CombinedM3uRepository;
import com.vopo.domain.repository.DownloadManager;
import com.vopo.domain.repository.EpgRepository;
import com.vopo.domain.repository.FavoriteRepository;
import com.vopo.domain.repository.MovieRepository;
import com.vopo.domain.repository.PlaybackHistoryRepository;
import com.vopo.domain.repository.ProviderRepository;
import com.vopo.domain.repository.SeriesRepository;
import com.vopo.domain.usecase.GetCustomCategories;
import com.vopo.domain.usecase.MarkAsWatched;
import com.vopo.domain.usecase.ScheduleRecording;
import com.vopo.player.PlayerEngine;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

@ScopeMetadata
@QualifierMetadata({
    "dagger.hilt.android.qualifiers.ApplicationContext",
    "com.vopo.app.di.MainPlayerEngine"
})
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
public final class PlayerViewModel_Factory implements Factory<PlayerViewModel> {
  private final Provider<Context> appContextProvider;

  private final Provider<PlayerEngine> mainPlayerEngineProvider;

  private final Provider<EpgRepository> epgRepositoryProvider;

  private final Provider<ChannelRepository> channelRepositoryProvider;

  private final Provider<MovieRepository> movieRepositoryProvider;

  private final Provider<SeriesRepository> seriesRepositoryProvider;

  private final Provider<FavoriteRepository> favoriteRepositoryProvider;

  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<CombinedM3uRepository> combinedM3uRepositoryProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<GetCustomCategories> getCustomCategoriesProvider;

  private final Provider<MarkAsWatched> markAsWatchedProvider;

  private final Provider<ScheduleRecording> scheduleRecordingUseCaseProvider;

  private final Provider<RecordingManager> recordingManagerProvider;

  private final Provider<WatchNextManager> watchNextManagerProvider;

  private final Provider<LauncherRecommendationsManager> launcherRecommendationsManagerProvider;

  private final Provider<CastManager> castManagerProvider;

  private final Provider<VopoPluginManager> pluginManagerProvider;

  private final Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider;

  private final Provider<SeekThumbnailProvider> seekThumbnailProvider;

  private final Provider<LivePreviewHandoffManager> livePreviewHandoffManagerProvider;

  private final Provider<SyncManager> syncManagerProvider;

  private final Provider<DownloadManager> downloadManagerProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  public PlayerViewModel_Factory(Provider<Context> appContextProvider,
      Provider<PlayerEngine> mainPlayerEngineProvider,
      Provider<EpgRepository> epgRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<SeriesRepository> seriesRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<GetCustomCategories> getCustomCategoriesProvider,
      Provider<MarkAsWatched> markAsWatchedProvider,
      Provider<ScheduleRecording> scheduleRecordingUseCaseProvider,
      Provider<RecordingManager> recordingManagerProvider,
      Provider<WatchNextManager> watchNextManagerProvider,
      Provider<LauncherRecommendationsManager> launcherRecommendationsManagerProvider,
      Provider<CastManager> castManagerProvider, Provider<VopoPluginManager> pluginManagerProvider,
      Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider,
      Provider<SeekThumbnailProvider> seekThumbnailProvider,
      Provider<LivePreviewHandoffManager> livePreviewHandoffManagerProvider,
      Provider<SyncManager> syncManagerProvider, Provider<DownloadManager> downloadManagerProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    this.appContextProvider = appContextProvider;
    this.mainPlayerEngineProvider = mainPlayerEngineProvider;
    this.epgRepositoryProvider = epgRepositoryProvider;
    this.channelRepositoryProvider = channelRepositoryProvider;
    this.movieRepositoryProvider = movieRepositoryProvider;
    this.seriesRepositoryProvider = seriesRepositoryProvider;
    this.favoriteRepositoryProvider = favoriteRepositoryProvider;
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.combinedM3uRepositoryProvider = combinedM3uRepositoryProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.getCustomCategoriesProvider = getCustomCategoriesProvider;
    this.markAsWatchedProvider = markAsWatchedProvider;
    this.scheduleRecordingUseCaseProvider = scheduleRecordingUseCaseProvider;
    this.recordingManagerProvider = recordingManagerProvider;
    this.watchNextManagerProvider = watchNextManagerProvider;
    this.launcherRecommendationsManagerProvider = launcherRecommendationsManagerProvider;
    this.castManagerProvider = castManagerProvider;
    this.pluginManagerProvider = pluginManagerProvider;
    this.xtreamStreamUrlResolverProvider = xtreamStreamUrlResolverProvider;
    this.seekThumbnailProvider = seekThumbnailProvider;
    this.livePreviewHandoffManagerProvider = livePreviewHandoffManagerProvider;
    this.syncManagerProvider = syncManagerProvider;
    this.downloadManagerProvider = downloadManagerProvider;
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public PlayerViewModel get() {
    return newInstance(appContextProvider.get(), mainPlayerEngineProvider.get(), epgRepositoryProvider.get(), channelRepositoryProvider.get(), movieRepositoryProvider.get(), seriesRepositoryProvider.get(), favoriteRepositoryProvider.get(), playbackHistoryRepositoryProvider.get(), providerRepositoryProvider.get(), combinedM3uRepositoryProvider.get(), preferencesRepositoryProvider.get(), getCustomCategoriesProvider.get(), markAsWatchedProvider.get(), scheduleRecordingUseCaseProvider.get(), recordingManagerProvider.get(), watchNextManagerProvider.get(), launcherRecommendationsManagerProvider.get(), castManagerProvider.get(), pluginManagerProvider.get(), xtreamStreamUrlResolverProvider.get(), seekThumbnailProvider.get(), livePreviewHandoffManagerProvider.get(), syncManagerProvider.get(), downloadManagerProvider.get(), okHttpClientProvider.get());
  }

  public static PlayerViewModel_Factory create(Provider<Context> appContextProvider,
      Provider<PlayerEngine> mainPlayerEngineProvider,
      Provider<EpgRepository> epgRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<SeriesRepository> seriesRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<GetCustomCategories> getCustomCategoriesProvider,
      Provider<MarkAsWatched> markAsWatchedProvider,
      Provider<ScheduleRecording> scheduleRecordingUseCaseProvider,
      Provider<RecordingManager> recordingManagerProvider,
      Provider<WatchNextManager> watchNextManagerProvider,
      Provider<LauncherRecommendationsManager> launcherRecommendationsManagerProvider,
      Provider<CastManager> castManagerProvider, Provider<VopoPluginManager> pluginManagerProvider,
      Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider,
      Provider<SeekThumbnailProvider> seekThumbnailProvider,
      Provider<LivePreviewHandoffManager> livePreviewHandoffManagerProvider,
      Provider<SyncManager> syncManagerProvider, Provider<DownloadManager> downloadManagerProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    return new PlayerViewModel_Factory(appContextProvider, mainPlayerEngineProvider, epgRepositoryProvider, channelRepositoryProvider, movieRepositoryProvider, seriesRepositoryProvider, favoriteRepositoryProvider, playbackHistoryRepositoryProvider, providerRepositoryProvider, combinedM3uRepositoryProvider, preferencesRepositoryProvider, getCustomCategoriesProvider, markAsWatchedProvider, scheduleRecordingUseCaseProvider, recordingManagerProvider, watchNextManagerProvider, launcherRecommendationsManagerProvider, castManagerProvider, pluginManagerProvider, xtreamStreamUrlResolverProvider, seekThumbnailProvider, livePreviewHandoffManagerProvider, syncManagerProvider, downloadManagerProvider, okHttpClientProvider);
  }

  public static PlayerViewModel newInstance(Context appContext, PlayerEngine mainPlayerEngine,
      EpgRepository epgRepository, ChannelRepository channelRepository,
      MovieRepository movieRepository, SeriesRepository seriesRepository,
      FavoriteRepository favoriteRepository, PlaybackHistoryRepository playbackHistoryRepository,
      ProviderRepository providerRepository, CombinedM3uRepository combinedM3uRepository,
      PreferencesRepository preferencesRepository, GetCustomCategories getCustomCategories,
      MarkAsWatched markAsWatched, ScheduleRecording scheduleRecordingUseCase,
      RecordingManager recordingManager, WatchNextManager watchNextManager,
      LauncherRecommendationsManager launcherRecommendationsManager, CastManager castManager,
      VopoPluginManager pluginManager, XtreamStreamUrlResolver xtreamStreamUrlResolver,
      SeekThumbnailProvider seekThumbnailProvider,
      LivePreviewHandoffManager livePreviewHandoffManager, SyncManager syncManager,
      DownloadManager downloadManager, OkHttpClient okHttpClient) {
    return new PlayerViewModel(appContext, mainPlayerEngine, epgRepository, channelRepository, movieRepository, seriesRepository, favoriteRepository, playbackHistoryRepository, providerRepository, combinedM3uRepository, preferencesRepository, getCustomCategories, markAsWatched, scheduleRecordingUseCase, recordingManager, watchNextManager, launcherRecommendationsManager, castManager, pluginManager, xtreamStreamUrlResolver, seekThumbnailProvider, livePreviewHandoffManager, syncManager, downloadManager, okHttpClient);
  }
}
