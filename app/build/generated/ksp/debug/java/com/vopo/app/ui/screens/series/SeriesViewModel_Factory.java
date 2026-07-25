package com.vopo.app.ui.screens.series;

import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.domain.manager.ParentalControlManager;
import com.vopo.domain.repository.FavoriteRepository;
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
public final class SeriesViewModel_Factory implements Factory<SeriesViewModel> {
  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<SeriesRepository> seriesRepositoryProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  private final Provider<FavoriteRepository> favoriteRepositoryProvider;

  private final Provider<GetContinueWatching> getContinueWatchingProvider;

  private final Provider<GetCustomCategories> getCustomCategoriesProvider;

  private final Provider<ParentalControlManager> parentalControlManagerProvider;

  public SeriesViewModel_Factory(Provider<ProviderRepository> providerRepositoryProvider,
      Provider<SeriesRepository> seriesRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<GetContinueWatching> getContinueWatchingProvider,
      Provider<GetCustomCategories> getCustomCategoriesProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider) {
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.seriesRepositoryProvider = seriesRepositoryProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
    this.favoriteRepositoryProvider = favoriteRepositoryProvider;
    this.getContinueWatchingProvider = getContinueWatchingProvider;
    this.getCustomCategoriesProvider = getCustomCategoriesProvider;
    this.parentalControlManagerProvider = parentalControlManagerProvider;
  }

  @Override
  public SeriesViewModel get() {
    return newInstance(providerRepositoryProvider.get(), seriesRepositoryProvider.get(), preferencesRepositoryProvider.get(), playbackHistoryRepositoryProvider.get(), favoriteRepositoryProvider.get(), getContinueWatchingProvider.get(), getCustomCategoriesProvider.get(), parentalControlManagerProvider.get());
  }

  public static SeriesViewModel_Factory create(
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<SeriesRepository> seriesRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<GetContinueWatching> getContinueWatchingProvider,
      Provider<GetCustomCategories> getCustomCategoriesProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider) {
    return new SeriesViewModel_Factory(providerRepositoryProvider, seriesRepositoryProvider, preferencesRepositoryProvider, playbackHistoryRepositoryProvider, favoriteRepositoryProvider, getContinueWatchingProvider, getCustomCategoriesProvider, parentalControlManagerProvider);
  }

  public static SeriesViewModel newInstance(ProviderRepository providerRepository,
      SeriesRepository seriesRepository, PreferencesRepository preferencesRepository,
      PlaybackHistoryRepository playbackHistoryRepository, FavoriteRepository favoriteRepository,
      GetContinueWatching getContinueWatching, GetCustomCategories getCustomCategories,
      ParentalControlManager parentalControlManager) {
    return new SeriesViewModel(providerRepository, seriesRepository, preferencesRepository, playbackHistoryRepository, favoriteRepository, getContinueWatching, getCustomCategories, parentalControlManager);
  }
}
