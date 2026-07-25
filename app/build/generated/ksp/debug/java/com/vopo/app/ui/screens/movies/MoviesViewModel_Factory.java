package com.vopo.app.ui.screens.movies;

import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.domain.manager.ParentalControlManager;
import com.vopo.domain.repository.FavoriteRepository;
import com.vopo.domain.repository.MovieRepository;
import com.vopo.domain.repository.PlaybackHistoryRepository;
import com.vopo.domain.repository.ProviderRepository;
import com.vopo.domain.usecase.GetContinueWatching;
import com.vopo.domain.usecase.GetCustomCategories;
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
public final class MoviesViewModel_Factory implements Factory<MoviesViewModel> {
  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<MovieRepository> movieRepositoryProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  private final Provider<FavoriteRepository> favoriteRepositoryProvider;

  private final Provider<GetContinueWatching> getContinueWatchingProvider;

  private final Provider<GetCustomCategories> getCustomCategoriesProvider;

  private final Provider<ParentalControlManager> parentalControlManagerProvider;

  public MoviesViewModel_Factory(Provider<ProviderRepository> providerRepositoryProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<GetContinueWatching> getContinueWatchingProvider,
      Provider<GetCustomCategories> getCustomCategoriesProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider) {
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.movieRepositoryProvider = movieRepositoryProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
    this.favoriteRepositoryProvider = favoriteRepositoryProvider;
    this.getContinueWatchingProvider = getContinueWatchingProvider;
    this.getCustomCategoriesProvider = getCustomCategoriesProvider;
    this.parentalControlManagerProvider = parentalControlManagerProvider;
  }

  @Override
  public MoviesViewModel get() {
    return newInstance(providerRepositoryProvider.get(), movieRepositoryProvider.get(), preferencesRepositoryProvider.get(), playbackHistoryRepositoryProvider.get(), favoriteRepositoryProvider.get(), getContinueWatchingProvider.get(), getCustomCategoriesProvider.get(), parentalControlManagerProvider.get());
  }

  public static MoviesViewModel_Factory create(
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<GetContinueWatching> getContinueWatchingProvider,
      Provider<GetCustomCategories> getCustomCategoriesProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider) {
    return new MoviesViewModel_Factory(providerRepositoryProvider, movieRepositoryProvider, preferencesRepositoryProvider, playbackHistoryRepositoryProvider, favoriteRepositoryProvider, getContinueWatchingProvider, getCustomCategoriesProvider, parentalControlManagerProvider);
  }

  public static MoviesViewModel newInstance(ProviderRepository providerRepository,
      MovieRepository movieRepository, PreferencesRepository preferencesRepository,
      PlaybackHistoryRepository playbackHistoryRepository, FavoriteRepository favoriteRepository,
      GetContinueWatching getContinueWatching, GetCustomCategories getCustomCategories,
      ParentalControlManager parentalControlManager) {
    return new MoviesViewModel(providerRepository, movieRepository, preferencesRepository, playbackHistoryRepository, favoriteRepository, getContinueWatching, getCustomCategories, parentalControlManager);
  }
}
