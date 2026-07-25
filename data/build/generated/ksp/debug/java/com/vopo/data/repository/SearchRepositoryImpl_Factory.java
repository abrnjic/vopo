package com.vopo.data.repository;

import com.vopo.data.local.dao.SearchDao;
import com.vopo.domain.repository.ChannelRepository;
import com.vopo.domain.repository.MovieRepository;
import com.vopo.domain.repository.SeriesRepository;
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
public final class SearchRepositoryImpl_Factory implements Factory<SearchRepositoryImpl> {
  private final Provider<SearchDao> searchDaoProvider;

  private final Provider<ChannelRepository> channelRepositoryProvider;

  private final Provider<MovieRepository> movieRepositoryProvider;

  private final Provider<SeriesRepository> seriesRepositoryProvider;

  public SearchRepositoryImpl_Factory(Provider<SearchDao> searchDaoProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<SeriesRepository> seriesRepositoryProvider) {
    this.searchDaoProvider = searchDaoProvider;
    this.channelRepositoryProvider = channelRepositoryProvider;
    this.movieRepositoryProvider = movieRepositoryProvider;
    this.seriesRepositoryProvider = seriesRepositoryProvider;
  }

  @Override
  public SearchRepositoryImpl get() {
    return newInstance(searchDaoProvider.get(), channelRepositoryProvider.get(), movieRepositoryProvider.get(), seriesRepositoryProvider.get());
  }

  public static SearchRepositoryImpl_Factory create(Provider<SearchDao> searchDaoProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<MovieRepository> movieRepositoryProvider,
      Provider<SeriesRepository> seriesRepositoryProvider) {
    return new SearchRepositoryImpl_Factory(searchDaoProvider, channelRepositoryProvider, movieRepositoryProvider, seriesRepositoryProvider);
  }

  public static SearchRepositoryImpl newInstance(SearchDao searchDao,
      ChannelRepository channelRepository, MovieRepository movieRepository,
      SeriesRepository seriesRepository) {
    return new SearchRepositoryImpl(searchDao, channelRepository, movieRepository, seriesRepository);
  }
}
