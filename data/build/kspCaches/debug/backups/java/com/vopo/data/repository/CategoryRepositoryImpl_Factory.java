package com.vopo.data.repository;

import com.vopo.data.local.DatabaseTransactionRunner;
import com.vopo.data.local.dao.CategoryDao;
import com.vopo.data.local.dao.ChannelDao;
import com.vopo.data.local.dao.MovieDao;
import com.vopo.data.local.dao.SeriesDao;
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
public final class CategoryRepositoryImpl_Factory implements Factory<CategoryRepositoryImpl> {
  private final Provider<CategoryDao> categoryDaoProvider;

  private final Provider<ChannelDao> channelDaoProvider;

  private final Provider<MovieDao> movieDaoProvider;

  private final Provider<SeriesDao> seriesDaoProvider;

  private final Provider<DatabaseTransactionRunner> transactionRunnerProvider;

  public CategoryRepositoryImpl_Factory(Provider<CategoryDao> categoryDaoProvider,
      Provider<ChannelDao> channelDaoProvider, Provider<MovieDao> movieDaoProvider,
      Provider<SeriesDao> seriesDaoProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider) {
    this.categoryDaoProvider = categoryDaoProvider;
    this.channelDaoProvider = channelDaoProvider;
    this.movieDaoProvider = movieDaoProvider;
    this.seriesDaoProvider = seriesDaoProvider;
    this.transactionRunnerProvider = transactionRunnerProvider;
  }

  @Override
  public CategoryRepositoryImpl get() {
    return newInstance(categoryDaoProvider.get(), channelDaoProvider.get(), movieDaoProvider.get(), seriesDaoProvider.get(), transactionRunnerProvider.get());
  }

  public static CategoryRepositoryImpl_Factory create(Provider<CategoryDao> categoryDaoProvider,
      Provider<ChannelDao> channelDaoProvider, Provider<MovieDao> movieDaoProvider,
      Provider<SeriesDao> seriesDaoProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider) {
    return new CategoryRepositoryImpl_Factory(categoryDaoProvider, channelDaoProvider, movieDaoProvider, seriesDaoProvider, transactionRunnerProvider);
  }

  public static CategoryRepositoryImpl newInstance(CategoryDao categoryDao, ChannelDao channelDao,
      MovieDao movieDao, SeriesDao seriesDao, DatabaseTransactionRunner transactionRunner) {
    return new CategoryRepositoryImpl(categoryDao, channelDao, movieDao, seriesDao, transactionRunner);
  }
}
