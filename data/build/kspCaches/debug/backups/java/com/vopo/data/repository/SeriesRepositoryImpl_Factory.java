package com.vopo.data.repository;

import com.vopo.data.local.dao.CategoryDao;
import com.vopo.data.local.dao.EpisodeDao;
import com.vopo.data.local.dao.FavoriteDao;
import com.vopo.data.local.dao.PlaybackHistoryDao;
import com.vopo.data.local.dao.ProviderDao;
import com.vopo.data.local.dao.SeriesCategoryHydrationDao;
import com.vopo.data.local.dao.SeriesDao;
import com.vopo.data.local.dao.XtreamContentIndexDao;
import com.vopo.data.local.dao.XtreamIndexJobDao;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.data.remote.jellyfin.JellyfinProvider;
import com.vopo.data.remote.stalker.StalkerApiService;
import com.vopo.data.remote.xtream.XtreamApiService;
import com.vopo.data.remote.xtream.XtreamStreamUrlResolver;
import com.vopo.data.security.CredentialCrypto;
import com.vopo.data.sync.SyncManager;
import com.vopo.domain.repository.PlaybackHistoryRepository;
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
public final class SeriesRepositoryImpl_Factory implements Factory<SeriesRepositoryImpl> {
  private final Provider<SeriesDao> seriesDaoProvider;

  private final Provider<EpisodeDao> episodeDaoProvider;

  private final Provider<CategoryDao> categoryDaoProvider;

  private final Provider<FavoriteDao> favoriteDaoProvider;

  private final Provider<PlaybackHistoryDao> playbackHistoryDaoProvider;

  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  private final Provider<ProviderDao> providerDaoProvider;

  private final Provider<StalkerApiService> stalkerApiServiceProvider;

  private final Provider<XtreamApiService> xtreamApiServiceProvider;

  private final Provider<CredentialCrypto> credentialCryptoProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider;

  private final Provider<XtreamContentIndexDao> xtreamContentIndexDaoProvider;

  private final Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider;

  private final Provider<SyncManager> syncManagerProvider;

  private final Provider<SeriesCategoryHydrationDao> seriesCategoryHydrationDaoProvider;

  private final Provider<JellyfinProvider> jellyfinProvider;

  public SeriesRepositoryImpl_Factory(Provider<SeriesDao> seriesDaoProvider,
      Provider<EpisodeDao> episodeDaoProvider, Provider<CategoryDao> categoryDaoProvider,
      Provider<FavoriteDao> favoriteDaoProvider,
      Provider<PlaybackHistoryDao> playbackHistoryDaoProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<ProviderDao> providerDaoProvider,
      Provider<StalkerApiService> stalkerApiServiceProvider,
      Provider<XtreamApiService> xtreamApiServiceProvider,
      Provider<CredentialCrypto> credentialCryptoProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider,
      Provider<XtreamContentIndexDao> xtreamContentIndexDaoProvider,
      Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider,
      Provider<SyncManager> syncManagerProvider,
      Provider<SeriesCategoryHydrationDao> seriesCategoryHydrationDaoProvider,
      Provider<JellyfinProvider> jellyfinProvider) {
    this.seriesDaoProvider = seriesDaoProvider;
    this.episodeDaoProvider = episodeDaoProvider;
    this.categoryDaoProvider = categoryDaoProvider;
    this.favoriteDaoProvider = favoriteDaoProvider;
    this.playbackHistoryDaoProvider = playbackHistoryDaoProvider;
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
    this.providerDaoProvider = providerDaoProvider;
    this.stalkerApiServiceProvider = stalkerApiServiceProvider;
    this.xtreamApiServiceProvider = xtreamApiServiceProvider;
    this.credentialCryptoProvider = credentialCryptoProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.xtreamStreamUrlResolverProvider = xtreamStreamUrlResolverProvider;
    this.xtreamContentIndexDaoProvider = xtreamContentIndexDaoProvider;
    this.xtreamIndexJobDaoProvider = xtreamIndexJobDaoProvider;
    this.syncManagerProvider = syncManagerProvider;
    this.seriesCategoryHydrationDaoProvider = seriesCategoryHydrationDaoProvider;
    this.jellyfinProvider = jellyfinProvider;
  }

  @Override
  public SeriesRepositoryImpl get() {
    return newInstance(seriesDaoProvider.get(), episodeDaoProvider.get(), categoryDaoProvider.get(), favoriteDaoProvider.get(), playbackHistoryDaoProvider.get(), playbackHistoryRepositoryProvider.get(), providerDaoProvider.get(), stalkerApiServiceProvider.get(), xtreamApiServiceProvider.get(), credentialCryptoProvider.get(), preferencesRepositoryProvider.get(), xtreamStreamUrlResolverProvider.get(), xtreamContentIndexDaoProvider.get(), xtreamIndexJobDaoProvider.get(), syncManagerProvider.get(), seriesCategoryHydrationDaoProvider.get(), jellyfinProvider.get());
  }

  public static SeriesRepositoryImpl_Factory create(Provider<SeriesDao> seriesDaoProvider,
      Provider<EpisodeDao> episodeDaoProvider, Provider<CategoryDao> categoryDaoProvider,
      Provider<FavoriteDao> favoriteDaoProvider,
      Provider<PlaybackHistoryDao> playbackHistoryDaoProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<ProviderDao> providerDaoProvider,
      Provider<StalkerApiService> stalkerApiServiceProvider,
      Provider<XtreamApiService> xtreamApiServiceProvider,
      Provider<CredentialCrypto> credentialCryptoProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider,
      Provider<XtreamContentIndexDao> xtreamContentIndexDaoProvider,
      Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider,
      Provider<SyncManager> syncManagerProvider,
      Provider<SeriesCategoryHydrationDao> seriesCategoryHydrationDaoProvider,
      Provider<JellyfinProvider> jellyfinProvider) {
    return new SeriesRepositoryImpl_Factory(seriesDaoProvider, episodeDaoProvider, categoryDaoProvider, favoriteDaoProvider, playbackHistoryDaoProvider, playbackHistoryRepositoryProvider, providerDaoProvider, stalkerApiServiceProvider, xtreamApiServiceProvider, credentialCryptoProvider, preferencesRepositoryProvider, xtreamStreamUrlResolverProvider, xtreamContentIndexDaoProvider, xtreamIndexJobDaoProvider, syncManagerProvider, seriesCategoryHydrationDaoProvider, jellyfinProvider);
  }

  public static SeriesRepositoryImpl newInstance(SeriesDao seriesDao, EpisodeDao episodeDao,
      CategoryDao categoryDao, FavoriteDao favoriteDao, PlaybackHistoryDao playbackHistoryDao,
      PlaybackHistoryRepository playbackHistoryRepository, ProviderDao providerDao,
      StalkerApiService stalkerApiService, XtreamApiService xtreamApiService,
      CredentialCrypto credentialCrypto, PreferencesRepository preferencesRepository,
      XtreamStreamUrlResolver xtreamStreamUrlResolver, XtreamContentIndexDao xtreamContentIndexDao,
      XtreamIndexJobDao xtreamIndexJobDao, SyncManager syncManager,
      SeriesCategoryHydrationDao seriesCategoryHydrationDao, JellyfinProvider jellyfinProvider) {
    return new SeriesRepositoryImpl(seriesDao, episodeDao, categoryDao, favoriteDao, playbackHistoryDao, playbackHistoryRepository, providerDao, stalkerApiService, xtreamApiService, credentialCrypto, preferencesRepository, xtreamStreamUrlResolver, xtreamContentIndexDao, xtreamIndexJobDao, syncManager, seriesCategoryHydrationDao, jellyfinProvider);
  }
}
