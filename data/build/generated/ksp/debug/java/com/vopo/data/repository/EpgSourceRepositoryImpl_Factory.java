package com.vopo.data.repository;

import android.content.Context;
import com.vopo.data.epg.EpgResolutionEngine;
import com.vopo.data.local.DatabaseTransactionRunner;
import com.vopo.data.local.dao.ChannelEpgMappingDao;
import com.vopo.data.local.dao.EpgChannelDao;
import com.vopo.data.local.dao.EpgProgrammeDao;
import com.vopo.data.local.dao.EpgSourceDao;
import com.vopo.data.local.dao.ProviderDao;
import com.vopo.data.local.dao.ProviderEpgSourceDao;
import com.vopo.data.parser.XmltvParser;
import com.vopo.data.preferences.PreferencesRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
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
public final class EpgSourceRepositoryImpl_Factory implements Factory<EpgSourceRepositoryImpl> {
  private final Provider<Context> contextProvider;

  private final Provider<EpgSourceDao> epgSourceDaoProvider;

  private final Provider<ProviderEpgSourceDao> providerEpgSourceDaoProvider;

  private final Provider<ProviderDao> providerDaoProvider;

  private final Provider<ChannelEpgMappingDao> channelEpgMappingDaoProvider;

  private final Provider<EpgChannelDao> epgChannelDaoProvider;

  private final Provider<EpgProgrammeDao> epgProgrammeDaoProvider;

  private final Provider<XmltvParser> xmltvParserProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<EpgResolutionEngine> resolutionEngineProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<DatabaseTransactionRunner> transactionRunnerProvider;

  public EpgSourceRepositoryImpl_Factory(Provider<Context> contextProvider,
      Provider<EpgSourceDao> epgSourceDaoProvider,
      Provider<ProviderEpgSourceDao> providerEpgSourceDaoProvider,
      Provider<ProviderDao> providerDaoProvider,
      Provider<ChannelEpgMappingDao> channelEpgMappingDaoProvider,
      Provider<EpgChannelDao> epgChannelDaoProvider,
      Provider<EpgProgrammeDao> epgProgrammeDaoProvider, Provider<XmltvParser> xmltvParserProvider,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<EpgResolutionEngine> resolutionEngineProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider) {
    this.contextProvider = contextProvider;
    this.epgSourceDaoProvider = epgSourceDaoProvider;
    this.providerEpgSourceDaoProvider = providerEpgSourceDaoProvider;
    this.providerDaoProvider = providerDaoProvider;
    this.channelEpgMappingDaoProvider = channelEpgMappingDaoProvider;
    this.epgChannelDaoProvider = epgChannelDaoProvider;
    this.epgProgrammeDaoProvider = epgProgrammeDaoProvider;
    this.xmltvParserProvider = xmltvParserProvider;
    this.okHttpClientProvider = okHttpClientProvider;
    this.resolutionEngineProvider = resolutionEngineProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.transactionRunnerProvider = transactionRunnerProvider;
  }

  @Override
  public EpgSourceRepositoryImpl get() {
    return newInstance(contextProvider.get(), epgSourceDaoProvider.get(), providerEpgSourceDaoProvider.get(), providerDaoProvider.get(), channelEpgMappingDaoProvider.get(), epgChannelDaoProvider.get(), epgProgrammeDaoProvider.get(), xmltvParserProvider.get(), okHttpClientProvider.get(), resolutionEngineProvider.get(), preferencesRepositoryProvider.get(), transactionRunnerProvider.get());
  }

  public static EpgSourceRepositoryImpl_Factory create(Provider<Context> contextProvider,
      Provider<EpgSourceDao> epgSourceDaoProvider,
      Provider<ProviderEpgSourceDao> providerEpgSourceDaoProvider,
      Provider<ProviderDao> providerDaoProvider,
      Provider<ChannelEpgMappingDao> channelEpgMappingDaoProvider,
      Provider<EpgChannelDao> epgChannelDaoProvider,
      Provider<EpgProgrammeDao> epgProgrammeDaoProvider, Provider<XmltvParser> xmltvParserProvider,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<EpgResolutionEngine> resolutionEngineProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider) {
    return new EpgSourceRepositoryImpl_Factory(contextProvider, epgSourceDaoProvider, providerEpgSourceDaoProvider, providerDaoProvider, channelEpgMappingDaoProvider, epgChannelDaoProvider, epgProgrammeDaoProvider, xmltvParserProvider, okHttpClientProvider, resolutionEngineProvider, preferencesRepositoryProvider, transactionRunnerProvider);
  }

  public static EpgSourceRepositoryImpl newInstance(Context context, EpgSourceDao epgSourceDao,
      ProviderEpgSourceDao providerEpgSourceDao, ProviderDao providerDao,
      ChannelEpgMappingDao channelEpgMappingDao, EpgChannelDao epgChannelDao,
      EpgProgrammeDao epgProgrammeDao, XmltvParser xmltvParser, OkHttpClient okHttpClient,
      EpgResolutionEngine resolutionEngine, PreferencesRepository preferencesRepository,
      DatabaseTransactionRunner transactionRunner) {
    return new EpgSourceRepositoryImpl(context, epgSourceDao, providerEpgSourceDao, providerDao, channelEpgMappingDao, epgChannelDao, epgProgrammeDao, xmltvParser, okHttpClient, resolutionEngine, preferencesRepository, transactionRunner);
  }
}
