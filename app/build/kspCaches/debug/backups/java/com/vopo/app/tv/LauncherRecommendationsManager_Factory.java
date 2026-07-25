package com.vopo.app.tv;

import android.content.Context;
import com.vopo.domain.repository.CombinedM3uRepository;
import com.vopo.domain.repository.MovieRepository;
import com.vopo.domain.repository.PlaybackHistoryRepository;
import com.vopo.domain.repository.ProviderRepository;
import com.vopo.domain.repository.SeriesRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class LauncherRecommendationsManager_Factory implements Factory<LauncherRecommendationsManager> {
  private final Provider<Context> contextProvider;

  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<CombinedM3uRepository> combinedM3uRepositoryProvider;

  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  private final Provider<MovieRepository> movieRepositoryProvider;

  private final Provider<SeriesRepository> seriesRepositoryProvider;

  public LauncherRecommendationsManager_Factory(Provider<Context> contextProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<SeriesRepository> seriesRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.combinedM3uRepositoryProvider = combinedM3uRepositoryProvider;
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
    this.movieRepositoryProvider = movieRepositoryProvider;
    this.seriesRepositoryProvider = seriesRepositoryProvider;
  }

  @Override
  public LauncherRecommendationsManager get() {
    return newInstance(contextProvider.get(), providerRepositoryProvider.get(), combinedM3uRepositoryProvider.get(), playbackHistoryRepositoryProvider.get(), movieRepositoryProvider.get(), seriesRepositoryProvider.get());
  }

  public static LauncherRecommendationsManager_Factory create(Provider<Context> contextProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<SeriesRepository> seriesRepositoryProvider) {
    return new LauncherRecommendationsManager_Factory(contextProvider, providerRepositoryProvider, combinedM3uRepositoryProvider, playbackHistoryRepositoryProvider, movieRepositoryProvider, seriesRepositoryProvider);
  }

  public static LauncherRecommendationsManager newInstance(Context context,
      ProviderRepository providerRepository, CombinedM3uRepository combinedM3uRepository,
      PlaybackHistoryRepository playbackHistoryRepository, MovieRepository movieRepository,
      SeriesRepository seriesRepository) {
    return new LauncherRecommendationsManager(context, providerRepository, combinedM3uRepository, playbackHistoryRepository, movieRepository, seriesRepository);
  }
}
