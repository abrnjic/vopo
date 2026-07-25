package com.vopo.data.repository;

import com.vopo.data.local.DatabaseTransactionRunner;
import com.vopo.data.local.dao.EpisodeDao;
import com.vopo.data.local.dao.MovieDao;
import com.vopo.data.local.dao.PlaybackHistoryDao;
import com.vopo.data.preferences.PreferencesRepository;
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
public final class PlaybackHistoryRepositoryImpl_Factory implements Factory<PlaybackHistoryRepositoryImpl> {
  private final Provider<PlaybackHistoryDao> daoProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<MovieDao> movieDaoProvider;

  private final Provider<EpisodeDao> episodeDaoProvider;

  private final Provider<DatabaseTransactionRunner> transactionRunnerProvider;

  public PlaybackHistoryRepositoryImpl_Factory(Provider<PlaybackHistoryDao> daoProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<MovieDao> movieDaoProvider, Provider<EpisodeDao> episodeDaoProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider) {
    this.daoProvider = daoProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.movieDaoProvider = movieDaoProvider;
    this.episodeDaoProvider = episodeDaoProvider;
    this.transactionRunnerProvider = transactionRunnerProvider;
  }

  @Override
  public PlaybackHistoryRepositoryImpl get() {
    return newInstance(daoProvider.get(), preferencesRepositoryProvider.get(), movieDaoProvider.get(), episodeDaoProvider.get(), transactionRunnerProvider.get());
  }

  public static PlaybackHistoryRepositoryImpl_Factory create(
      Provider<PlaybackHistoryDao> daoProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<MovieDao> movieDaoProvider, Provider<EpisodeDao> episodeDaoProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider) {
    return new PlaybackHistoryRepositoryImpl_Factory(daoProvider, preferencesRepositoryProvider, movieDaoProvider, episodeDaoProvider, transactionRunnerProvider);
  }

  public static PlaybackHistoryRepositoryImpl newInstance(PlaybackHistoryDao dao,
      PreferencesRepository preferencesRepository, MovieDao movieDao, EpisodeDao episodeDao,
      DatabaseTransactionRunner transactionRunner) {
    return new PlaybackHistoryRepositoryImpl(dao, preferencesRepository, movieDao, episodeDao, transactionRunner);
  }
}
