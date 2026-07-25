package com.vopo.app.ui.screens.series;

import androidx.lifecycle.SavedStateHandle;
import com.vopo.app.plugins.VopoPluginManager;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.domain.repository.DownloadManager;
import com.vopo.domain.repository.ExternalRatingsRepository;
import com.vopo.domain.repository.FavoriteRepository;
import com.vopo.domain.repository.PlaybackHistoryRepository;
import com.vopo.domain.repository.ProviderRepository;
import com.vopo.domain.repository.SeriesRepository;
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
public final class SeriesDetailViewModel_Factory implements Factory<SeriesDetailViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<SeriesRepository> seriesRepositoryProvider;

  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  private final Provider<ExternalRatingsRepository> externalRatingsRepositoryProvider;

  private final Provider<FavoriteRepository> favoriteRepositoryProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<VopoPluginManager> pluginManagerProvider;

  private final Provider<DownloadManager> downloadManagerProvider;

  public SeriesDetailViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<SeriesRepository> seriesRepositoryProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<ExternalRatingsRepository> externalRatingsRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<VopoPluginManager> pluginManagerProvider,
      Provider<DownloadManager> downloadManagerProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.seriesRepositoryProvider = seriesRepositoryProvider;
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
    this.externalRatingsRepositoryProvider = externalRatingsRepositoryProvider;
    this.favoriteRepositoryProvider = favoriteRepositoryProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.pluginManagerProvider = pluginManagerProvider;
    this.downloadManagerProvider = downloadManagerProvider;
  }

  @Override
  public SeriesDetailViewModel get() {
    return newInstance(savedStateHandleProvider.get(), seriesRepositoryProvider.get(), providerRepositoryProvider.get(), playbackHistoryRepositoryProvider.get(), externalRatingsRepositoryProvider.get(), favoriteRepositoryProvider.get(), preferencesRepositoryProvider.get(), pluginManagerProvider.get(), downloadManagerProvider.get());
  }

  public static SeriesDetailViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<SeriesRepository> seriesRepositoryProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<ExternalRatingsRepository> externalRatingsRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<VopoPluginManager> pluginManagerProvider,
      Provider<DownloadManager> downloadManagerProvider) {
    return new SeriesDetailViewModel_Factory(savedStateHandleProvider, seriesRepositoryProvider, providerRepositoryProvider, playbackHistoryRepositoryProvider, externalRatingsRepositoryProvider, favoriteRepositoryProvider, preferencesRepositoryProvider, pluginManagerProvider, downloadManagerProvider);
  }

  public static SeriesDetailViewModel newInstance(SavedStateHandle savedStateHandle,
      SeriesRepository seriesRepository, ProviderRepository providerRepository,
      PlaybackHistoryRepository playbackHistoryRepository,
      ExternalRatingsRepository externalRatingsRepository, FavoriteRepository favoriteRepository,
      PreferencesRepository preferencesRepository, VopoPluginManager pluginManager,
      DownloadManager downloadManager) {
    return new SeriesDetailViewModel(savedStateHandle, seriesRepository, providerRepository, playbackHistoryRepository, externalRatingsRepository, favoriteRepository, preferencesRepository, pluginManager, downloadManager);
  }
}
