package com.vopo.app.ui.screens.movies;

import androidx.lifecycle.SavedStateHandle;
import com.vopo.app.plugins.VopoPluginManager;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.domain.repository.DownloadManager;
import com.vopo.domain.repository.ExternalRatingsRepository;
import com.vopo.domain.repository.FavoriteRepository;
import com.vopo.domain.repository.MovieRepository;
import com.vopo.domain.repository.PlaybackHistoryRepository;
import com.vopo.domain.repository.ProviderRepository;
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
public final class MovieDetailViewModel_Factory implements Factory<MovieDetailViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<MovieRepository> movieRepositoryProvider;

  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  private final Provider<ExternalRatingsRepository> externalRatingsRepositoryProvider;

  private final Provider<FavoriteRepository> favoriteRepositoryProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<VopoPluginManager> pluginManagerProvider;

  private final Provider<DownloadManager> downloadManagerProvider;

  public MovieDetailViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<ExternalRatingsRepository> externalRatingsRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<VopoPluginManager> pluginManagerProvider,
      Provider<DownloadManager> downloadManagerProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.movieRepositoryProvider = movieRepositoryProvider;
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
    this.externalRatingsRepositoryProvider = externalRatingsRepositoryProvider;
    this.favoriteRepositoryProvider = favoriteRepositoryProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.pluginManagerProvider = pluginManagerProvider;
    this.downloadManagerProvider = downloadManagerProvider;
  }

  @Override
  public MovieDetailViewModel get() {
    return newInstance(savedStateHandleProvider.get(), movieRepositoryProvider.get(), providerRepositoryProvider.get(), playbackHistoryRepositoryProvider.get(), externalRatingsRepositoryProvider.get(), favoriteRepositoryProvider.get(), preferencesRepositoryProvider.get(), pluginManagerProvider.get(), downloadManagerProvider.get());
  }

  public static MovieDetailViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<ExternalRatingsRepository> externalRatingsRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<VopoPluginManager> pluginManagerProvider,
      Provider<DownloadManager> downloadManagerProvider) {
    return new MovieDetailViewModel_Factory(savedStateHandleProvider, movieRepositoryProvider, providerRepositoryProvider, playbackHistoryRepositoryProvider, externalRatingsRepositoryProvider, favoriteRepositoryProvider, preferencesRepositoryProvider, pluginManagerProvider, downloadManagerProvider);
  }

  public static MovieDetailViewModel newInstance(SavedStateHandle savedStateHandle,
      MovieRepository movieRepository, ProviderRepository providerRepository,
      PlaybackHistoryRepository playbackHistoryRepository,
      ExternalRatingsRepository externalRatingsRepository, FavoriteRepository favoriteRepository,
      PreferencesRepository preferencesRepository, VopoPluginManager pluginManager,
      DownloadManager downloadManager) {
    return new MovieDetailViewModel(savedStateHandle, movieRepository, providerRepository, playbackHistoryRepository, externalRatingsRepository, favoriteRepository, preferencesRepository, pluginManager, downloadManager);
  }
}
