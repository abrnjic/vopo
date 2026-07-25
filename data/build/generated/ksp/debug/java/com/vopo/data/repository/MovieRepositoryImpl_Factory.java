package com.vopo.data.repository;

import com.vopo.data.local.DatabaseTransactionRunner;
import com.vopo.data.local.dao.CategoryDao;
import com.vopo.data.local.dao.FavoriteDao;
import com.vopo.data.local.dao.MovieCategoryHydrationDao;
import com.vopo.data.local.dao.MovieDao;
import com.vopo.data.local.dao.PlaybackHistoryDao;
import com.vopo.data.local.dao.ProviderDao;
import com.vopo.data.local.dao.XtreamContentIndexDao;
import com.vopo.data.local.dao.XtreamIndexJobDao;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.data.remote.stalker.StalkerApiService;
import com.vopo.data.remote.xtream.XtreamApiService;
import com.vopo.data.remote.xtream.XtreamStreamUrlResolver;
import com.vopo.data.security.CredentialCrypto;
import com.vopo.data.sync.SyncManager;
import com.vopo.domain.repository.PlaybackHistoryRepository;
import com.vopo.domain.repository.SyncMetadataRepository;
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
public final class MovieRepositoryImpl_Factory implements Factory<MovieRepositoryImpl> {
  private final Provider<MovieDao> movieDaoProvider;

  private final Provider<CategoryDao> categoryDaoProvider;

  private final Provider<ProviderDao> providerDaoProvider;

  private final Provider<StalkerApiService> stalkerApiServiceProvider;

  private final Provider<XtreamApiService> xtreamApiServiceProvider;

  private final Provider<CredentialCrypto> credentialCryptoProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<FavoriteDao> favoriteDaoProvider;

  private final Provider<PlaybackHistoryDao> playbackHistoryDaoProvider;

  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  private final Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider;

  private final Provider<MovieCategoryHydrationDao> movieCategoryHydrationDaoProvider;

  private final Provider<SyncMetadataRepository> syncMetadataRepositoryProvider;

  private final Provider<XtreamContentIndexDao> xtreamContentIndexDaoProvider;

  private final Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider;

  private final Provider<SyncManager> syncManagerProvider;

  private final Provider<DatabaseTransactionRunner> transactionRunnerProvider;

  public MovieRepositoryImpl_Factory(Provider<MovieDao> movieDaoProvider,
      Provider<CategoryDao> categoryDaoProvider, Provider<ProviderDao> providerDaoProvider,
      Provider<StalkerApiService> stalkerApiServiceProvider,
      Provider<XtreamApiService> xtreamApiServiceProvider,
      Provider<CredentialCrypto> credentialCryptoProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<FavoriteDao> favoriteDaoProvider,
      Provider<PlaybackHistoryDao> playbackHistoryDaoProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider,
      Provider<MovieCategoryHydrationDao> movieCategoryHydrationDaoProvider,
      Provider<SyncMetadataRepository> syncMetadataRepositoryProvider,
      Provider<XtreamContentIndexDao> xtreamContentIndexDaoProvider,
      Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider,
      Provider<SyncManager> syncManagerProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider) {
    this.movieDaoProvider = movieDaoProvider;
    this.categoryDaoProvider = categoryDaoProvider;
    this.providerDaoProvider = providerDaoProvider;
    this.stalkerApiServiceProvider = stalkerApiServiceProvider;
    this.xtreamApiServiceProvider = xtreamApiServiceProvider;
    this.credentialCryptoProvider = credentialCryptoProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.favoriteDaoProvider = favoriteDaoProvider;
    this.playbackHistoryDaoProvider = playbackHistoryDaoProvider;
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
    this.xtreamStreamUrlResolverProvider = xtreamStreamUrlResolverProvider;
    this.movieCategoryHydrationDaoProvider = movieCategoryHydrationDaoProvider;
    this.syncMetadataRepositoryProvider = syncMetadataRepositoryProvider;
    this.xtreamContentIndexDaoProvider = xtreamContentIndexDaoProvider;
    this.xtreamIndexJobDaoProvider = xtreamIndexJobDaoProvider;
    this.syncManagerProvider = syncManagerProvider;
    this.transactionRunnerProvider = transactionRunnerProvider;
  }

  @Override
  public MovieRepositoryImpl get() {
    return newInstance(movieDaoProvider.get(), categoryDaoProvider.get(), providerDaoProvider.get(), stalkerApiServiceProvider.get(), xtreamApiServiceProvider.get(), credentialCryptoProvider.get(), preferencesRepositoryProvider.get(), favoriteDaoProvider.get(), playbackHistoryDaoProvider.get(), playbackHistoryRepositoryProvider.get(), xtreamStreamUrlResolverProvider.get(), movieCategoryHydrationDaoProvider.get(), syncMetadataRepositoryProvider.get(), xtreamContentIndexDaoProvider.get(), xtreamIndexJobDaoProvider.get(), syncManagerProvider.get(), transactionRunnerProvider.get());
  }

  public static MovieRepositoryImpl_Factory create(Provider<MovieDao> movieDaoProvider,
      Provider<CategoryDao> categoryDaoProvider, Provider<ProviderDao> providerDaoProvider,
      Provider<StalkerApiService> stalkerApiServiceProvider,
      Provider<XtreamApiService> xtreamApiServiceProvider,
      Provider<CredentialCrypto> credentialCryptoProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<FavoriteDao> favoriteDaoProvider,
      Provider<PlaybackHistoryDao> playbackHistoryDaoProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider,
      Provider<MovieCategoryHydrationDao> movieCategoryHydrationDaoProvider,
      Provider<SyncMetadataRepository> syncMetadataRepositoryProvider,
      Provider<XtreamContentIndexDao> xtreamContentIndexDaoProvider,
      Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider,
      Provider<SyncManager> syncManagerProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider) {
    return new MovieRepositoryImpl_Factory(movieDaoProvider, categoryDaoProvider, providerDaoProvider, stalkerApiServiceProvider, xtreamApiServiceProvider, credentialCryptoProvider, preferencesRepositoryProvider, favoriteDaoProvider, playbackHistoryDaoProvider, playbackHistoryRepositoryProvider, xtreamStreamUrlResolverProvider, movieCategoryHydrationDaoProvider, syncMetadataRepositoryProvider, xtreamContentIndexDaoProvider, xtreamIndexJobDaoProvider, syncManagerProvider, transactionRunnerProvider);
  }

  public static MovieRepositoryImpl newInstance(MovieDao movieDao, CategoryDao categoryDao,
      ProviderDao providerDao, StalkerApiService stalkerApiService,
      XtreamApiService xtreamApiService, CredentialCrypto credentialCrypto,
      PreferencesRepository preferencesRepository, FavoriteDao favoriteDao,
      PlaybackHistoryDao playbackHistoryDao, PlaybackHistoryRepository playbackHistoryRepository,
      XtreamStreamUrlResolver xtreamStreamUrlResolver,
      MovieCategoryHydrationDao movieCategoryHydrationDao,
      SyncMetadataRepository syncMetadataRepository, XtreamContentIndexDao xtreamContentIndexDao,
      XtreamIndexJobDao xtreamIndexJobDao, SyncManager syncManager,
      DatabaseTransactionRunner transactionRunner) {
    return new MovieRepositoryImpl(movieDao, categoryDao, providerDao, stalkerApiService, xtreamApiService, credentialCrypto, preferencesRepository, favoriteDao, playbackHistoryDao, playbackHistoryRepository, xtreamStreamUrlResolver, movieCategoryHydrationDao, syncMetadataRepository, xtreamContentIndexDao, xtreamIndexJobDao, syncManager, transactionRunner);
  }
}
