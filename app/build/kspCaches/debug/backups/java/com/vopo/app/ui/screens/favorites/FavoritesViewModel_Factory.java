package com.vopo.app.ui.screens.favorites;

import android.content.Context;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.domain.repository.ChannelRepository;
import com.vopo.domain.repository.FavoriteRepository;
import com.vopo.domain.repository.MovieRepository;
import com.vopo.domain.repository.PlaybackHistoryRepository;
import com.vopo.domain.repository.ProviderRepository;
import com.vopo.domain.repository.SeriesRepository;
import com.vopo.domain.usecase.GetContinueWatching;
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
public final class FavoritesViewModel_Factory implements Factory<FavoritesViewModel> {
  private final Provider<Context> appContextProvider;

  private final Provider<FavoriteRepository> favoriteRepositoryProvider;

  private final Provider<ChannelRepository> channelRepositoryProvider;

  private final Provider<MovieRepository> movieRepositoryProvider;

  private final Provider<SeriesRepository> seriesRepositoryProvider;

  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<GetContinueWatching> getContinueWatchingProvider;

  public FavoritesViewModel_Factory(Provider<Context> appContextProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<SeriesRepository> seriesRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<GetContinueWatching> getContinueWatchingProvider) {
    this.appContextProvider = appContextProvider;
    this.favoriteRepositoryProvider = favoriteRepositoryProvider;
    this.channelRepositoryProvider = channelRepositoryProvider;
    this.movieRepositoryProvider = movieRepositoryProvider;
    this.seriesRepositoryProvider = seriesRepositoryProvider;
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.getContinueWatchingProvider = getContinueWatchingProvider;
  }

  @Override
  public FavoritesViewModel get() {
    return newInstance(appContextProvider.get(), favoriteRepositoryProvider.get(), channelRepositoryProvider.get(), movieRepositoryProvider.get(), seriesRepositoryProvider.get(), playbackHistoryRepositoryProvider.get(), providerRepositoryProvider.get(), preferencesRepositoryProvider.get(), getContinueWatchingProvider.get());
  }

  public static FavoritesViewModel_Factory create(Provider<Context> appContextProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<SeriesRepository> seriesRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<GetContinueWatching> getContinueWatchingProvider) {
    return new FavoritesViewModel_Factory(appContextProvider, favoriteRepositoryProvider, channelRepositoryProvider, movieRepositoryProvider, seriesRepositoryProvider, playbackHistoryRepositoryProvider, providerRepositoryProvider, preferencesRepositoryProvider, getContinueWatchingProvider);
  }

  public static FavoritesViewModel newInstance(Context appContext,
      FavoriteRepository favoriteRepository, ChannelRepository channelRepository,
      MovieRepository movieRepository, SeriesRepository seriesRepository,
      PlaybackHistoryRepository playbackHistoryRepository, ProviderRepository providerRepository,
      PreferencesRepository preferencesRepository, GetContinueWatching getContinueWatching) {
    return new FavoritesViewModel(appContext, favoriteRepository, channelRepository, movieRepository, seriesRepository, playbackHistoryRepository, providerRepository, preferencesRepository, getContinueWatching);
  }
}
