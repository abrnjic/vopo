package com.vopo.data.sync;

import android.content.Context;
import com.vopo.data.local.DatabaseTransactionRunner;
import com.vopo.data.local.dao.CatalogSyncDao;
import com.vopo.data.local.dao.CategoryDao;
import com.vopo.data.local.dao.ChannelDao;
import com.vopo.data.local.dao.EpisodeDao;
import com.vopo.data.local.dao.MovieCategoryHydrationDao;
import com.vopo.data.local.dao.MovieDao;
import com.vopo.data.local.dao.ProgramDao;
import com.vopo.data.local.dao.ProviderDao;
import com.vopo.data.local.dao.SeriesCategoryHydrationDao;
import com.vopo.data.local.dao.SeriesDao;
import com.vopo.data.local.dao.TmdbIdentityDao;
import com.vopo.data.local.dao.XtreamContentIndexDao;
import com.vopo.data.local.dao.XtreamIndexJobDao;
import com.vopo.data.local.dao.XtreamLiveOnboardingDao;
import com.vopo.data.parser.M3uParser;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.data.remote.jellyfin.JellyfinProvider;
import com.vopo.data.remote.stalker.StalkerApiService;
import com.vopo.data.security.CredentialCrypto;
import com.vopo.domain.repository.EpgRepository;
import com.vopo.domain.repository.EpgSourceRepository;
import com.vopo.domain.repository.SyncMetadataRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.serialization.json.Json;
import okhttp3.OkHttpClient;

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
public final class SyncManager_Factory implements Factory<SyncManager> {
  private final Provider<Context> applicationContextProvider;

  private final Provider<ProviderDao> providerDaoProvider;

  private final Provider<ChannelDao> channelDaoProvider;

  private final Provider<MovieDao> movieDaoProvider;

  private final Provider<SeriesDao> seriesDaoProvider;

  private final Provider<ProgramDao> programDaoProvider;

  private final Provider<CategoryDao> categoryDaoProvider;

  private final Provider<MovieCategoryHydrationDao> movieCategoryHydrationDaoProvider;

  private final Provider<SeriesCategoryHydrationDao> seriesCategoryHydrationDaoProvider;

  private final Provider<CatalogSyncDao> catalogSyncDaoProvider;

  private final Provider<TmdbIdentityDao> tmdbIdentityDaoProvider;

  private final Provider<XtreamContentIndexDao> xtreamContentIndexDaoProvider;

  private final Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider;

  private final Provider<XtreamLiveOnboardingDao> xtreamLiveOnboardingDaoProvider;

  private final Provider<StalkerApiService> stalkerApiServiceProvider;

  private final Provider<EpisodeDao> episodeDaoProvider;

  private final Provider<JellyfinProvider> jellyfinProvider;

  private final Provider<Json> xtreamJsonProvider;

  private final Provider<M3uParser> m3uParserProvider;

  private final Provider<EpgRepository> epgRepositoryProvider;

  private final Provider<EpgSourceRepository> epgSourceRepositoryProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<CredentialCrypto> credentialCryptoProvider;

  private final Provider<SyncMetadataRepository> syncMetadataRepositoryProvider;

  private final Provider<DatabaseTransactionRunner> transactionRunnerProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<SyncProgressBus> syncProgressBusProvider;

  public SyncManager_Factory(Provider<Context> applicationContextProvider,
      Provider<ProviderDao> providerDaoProvider, Provider<ChannelDao> channelDaoProvider,
      Provider<MovieDao> movieDaoProvider, Provider<SeriesDao> seriesDaoProvider,
      Provider<ProgramDao> programDaoProvider, Provider<CategoryDao> categoryDaoProvider,
      Provider<MovieCategoryHydrationDao> movieCategoryHydrationDaoProvider,
      Provider<SeriesCategoryHydrationDao> seriesCategoryHydrationDaoProvider,
      Provider<CatalogSyncDao> catalogSyncDaoProvider,
      Provider<TmdbIdentityDao> tmdbIdentityDaoProvider,
      Provider<XtreamContentIndexDao> xtreamContentIndexDaoProvider,
      Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider,
      Provider<XtreamLiveOnboardingDao> xtreamLiveOnboardingDaoProvider,
      Provider<StalkerApiService> stalkerApiServiceProvider,
      Provider<EpisodeDao> episodeDaoProvider, Provider<JellyfinProvider> jellyfinProvider,
      Provider<Json> xtreamJsonProvider, Provider<M3uParser> m3uParserProvider,
      Provider<EpgRepository> epgRepositoryProvider,
      Provider<EpgSourceRepository> epgSourceRepositoryProvider,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<CredentialCrypto> credentialCryptoProvider,
      Provider<SyncMetadataRepository> syncMetadataRepositoryProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<SyncProgressBus> syncProgressBusProvider) {
    this.applicationContextProvider = applicationContextProvider;
    this.providerDaoProvider = providerDaoProvider;
    this.channelDaoProvider = channelDaoProvider;
    this.movieDaoProvider = movieDaoProvider;
    this.seriesDaoProvider = seriesDaoProvider;
    this.programDaoProvider = programDaoProvider;
    this.categoryDaoProvider = categoryDaoProvider;
    this.movieCategoryHydrationDaoProvider = movieCategoryHydrationDaoProvider;
    this.seriesCategoryHydrationDaoProvider = seriesCategoryHydrationDaoProvider;
    this.catalogSyncDaoProvider = catalogSyncDaoProvider;
    this.tmdbIdentityDaoProvider = tmdbIdentityDaoProvider;
    this.xtreamContentIndexDaoProvider = xtreamContentIndexDaoProvider;
    this.xtreamIndexJobDaoProvider = xtreamIndexJobDaoProvider;
    this.xtreamLiveOnboardingDaoProvider = xtreamLiveOnboardingDaoProvider;
    this.stalkerApiServiceProvider = stalkerApiServiceProvider;
    this.episodeDaoProvider = episodeDaoProvider;
    this.jellyfinProvider = jellyfinProvider;
    this.xtreamJsonProvider = xtreamJsonProvider;
    this.m3uParserProvider = m3uParserProvider;
    this.epgRepositoryProvider = epgRepositoryProvider;
    this.epgSourceRepositoryProvider = epgSourceRepositoryProvider;
    this.okHttpClientProvider = okHttpClientProvider;
    this.credentialCryptoProvider = credentialCryptoProvider;
    this.syncMetadataRepositoryProvider = syncMetadataRepositoryProvider;
    this.transactionRunnerProvider = transactionRunnerProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.syncProgressBusProvider = syncProgressBusProvider;
  }

  @Override
  public SyncManager get() {
    return newInstance(applicationContextProvider.get(), providerDaoProvider.get(), channelDaoProvider.get(), movieDaoProvider.get(), seriesDaoProvider.get(), programDaoProvider.get(), categoryDaoProvider.get(), movieCategoryHydrationDaoProvider.get(), seriesCategoryHydrationDaoProvider.get(), catalogSyncDaoProvider.get(), tmdbIdentityDaoProvider.get(), xtreamContentIndexDaoProvider.get(), xtreamIndexJobDaoProvider.get(), xtreamLiveOnboardingDaoProvider.get(), stalkerApiServiceProvider.get(), episodeDaoProvider.get(), jellyfinProvider.get(), xtreamJsonProvider.get(), m3uParserProvider.get(), epgRepositoryProvider.get(), epgSourceRepositoryProvider.get(), okHttpClientProvider.get(), credentialCryptoProvider.get(), syncMetadataRepositoryProvider.get(), transactionRunnerProvider.get(), preferencesRepositoryProvider.get(), syncProgressBusProvider.get());
  }

  public static SyncManager_Factory create(Provider<Context> applicationContextProvider,
      Provider<ProviderDao> providerDaoProvider, Provider<ChannelDao> channelDaoProvider,
      Provider<MovieDao> movieDaoProvider, Provider<SeriesDao> seriesDaoProvider,
      Provider<ProgramDao> programDaoProvider, Provider<CategoryDao> categoryDaoProvider,
      Provider<MovieCategoryHydrationDao> movieCategoryHydrationDaoProvider,
      Provider<SeriesCategoryHydrationDao> seriesCategoryHydrationDaoProvider,
      Provider<CatalogSyncDao> catalogSyncDaoProvider,
      Provider<TmdbIdentityDao> tmdbIdentityDaoProvider,
      Provider<XtreamContentIndexDao> xtreamContentIndexDaoProvider,
      Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider,
      Provider<XtreamLiveOnboardingDao> xtreamLiveOnboardingDaoProvider,
      Provider<StalkerApiService> stalkerApiServiceProvider,
      Provider<EpisodeDao> episodeDaoProvider, Provider<JellyfinProvider> jellyfinProvider,
      Provider<Json> xtreamJsonProvider, Provider<M3uParser> m3uParserProvider,
      Provider<EpgRepository> epgRepositoryProvider,
      Provider<EpgSourceRepository> epgSourceRepositoryProvider,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<CredentialCrypto> credentialCryptoProvider,
      Provider<SyncMetadataRepository> syncMetadataRepositoryProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<SyncProgressBus> syncProgressBusProvider) {
    return new SyncManager_Factory(applicationContextProvider, providerDaoProvider, channelDaoProvider, movieDaoProvider, seriesDaoProvider, programDaoProvider, categoryDaoProvider, movieCategoryHydrationDaoProvider, seriesCategoryHydrationDaoProvider, catalogSyncDaoProvider, tmdbIdentityDaoProvider, xtreamContentIndexDaoProvider, xtreamIndexJobDaoProvider, xtreamLiveOnboardingDaoProvider, stalkerApiServiceProvider, episodeDaoProvider, jellyfinProvider, xtreamJsonProvider, m3uParserProvider, epgRepositoryProvider, epgSourceRepositoryProvider, okHttpClientProvider, credentialCryptoProvider, syncMetadataRepositoryProvider, transactionRunnerProvider, preferencesRepositoryProvider, syncProgressBusProvider);
  }

  public static SyncManager newInstance(Context applicationContext, ProviderDao providerDao,
      ChannelDao channelDao, MovieDao movieDao, SeriesDao seriesDao, ProgramDao programDao,
      CategoryDao categoryDao, MovieCategoryHydrationDao movieCategoryHydrationDao,
      SeriesCategoryHydrationDao seriesCategoryHydrationDao, CatalogSyncDao catalogSyncDao,
      TmdbIdentityDao tmdbIdentityDao, XtreamContentIndexDao xtreamContentIndexDao,
      XtreamIndexJobDao xtreamIndexJobDao, XtreamLiveOnboardingDao xtreamLiveOnboardingDao,
      StalkerApiService stalkerApiService, EpisodeDao episodeDao, JellyfinProvider jellyfinProvider,
      Json xtreamJson, M3uParser m3uParser, EpgRepository epgRepository,
      EpgSourceRepository epgSourceRepository, OkHttpClient okHttpClient,
      CredentialCrypto credentialCrypto, SyncMetadataRepository syncMetadataRepository,
      DatabaseTransactionRunner transactionRunner, PreferencesRepository preferencesRepository,
      SyncProgressBus syncProgressBus) {
    return new SyncManager(applicationContext, providerDao, channelDao, movieDao, seriesDao, programDao, categoryDao, movieCategoryHydrationDao, seriesCategoryHydrationDao, catalogSyncDao, tmdbIdentityDao, xtreamContentIndexDao, xtreamIndexJobDao, xtreamLiveOnboardingDao, stalkerApiService, episodeDao, jellyfinProvider, xtreamJson, m3uParser, epgRepository, epgSourceRepository, okHttpClient, credentialCrypto, syncMetadataRepository, transactionRunner, preferencesRepository, syncProgressBus);
  }
}
