package com.vopo.data.repository;

import com.vopo.data.local.DatabaseTransactionRunner;
import com.vopo.data.local.dao.ProgramDao;
import com.vopo.data.local.dao.ProviderDao;
import com.vopo.data.parser.XmltvParser;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.domain.repository.EpgSourceRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.OkHttpClient;

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
public final class EpgRepositoryImpl_Factory implements Factory<EpgRepositoryImpl> {
  private final Provider<ProgramDao> programDaoProvider;

  private final Provider<ProviderDao> providerDaoProvider;

  private final Provider<XmltvParser> xmltvParserProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<DatabaseTransactionRunner> transactionRunnerProvider;

  private final Provider<EpgSourceRepository> epgSourceRepositoryProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<CoroutineScope> externalScopeProvider;

  public EpgRepositoryImpl_Factory(Provider<ProgramDao> programDaoProvider,
      Provider<ProviderDao> providerDaoProvider, Provider<XmltvParser> xmltvParserProvider,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider,
      Provider<EpgSourceRepository> epgSourceRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<CoroutineScope> externalScopeProvider) {
    this.programDaoProvider = programDaoProvider;
    this.providerDaoProvider = providerDaoProvider;
    this.xmltvParserProvider = xmltvParserProvider;
    this.okHttpClientProvider = okHttpClientProvider;
    this.transactionRunnerProvider = transactionRunnerProvider;
    this.epgSourceRepositoryProvider = epgSourceRepositoryProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.externalScopeProvider = externalScopeProvider;
  }

  @Override
  public EpgRepositoryImpl get() {
    return newInstance(programDaoProvider.get(), providerDaoProvider.get(), xmltvParserProvider.get(), okHttpClientProvider.get(), transactionRunnerProvider.get(), epgSourceRepositoryProvider.get(), preferencesRepositoryProvider.get(), externalScopeProvider.get());
  }

  public static EpgRepositoryImpl_Factory create(Provider<ProgramDao> programDaoProvider,
      Provider<ProviderDao> providerDaoProvider, Provider<XmltvParser> xmltvParserProvider,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider,
      Provider<EpgSourceRepository> epgSourceRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<CoroutineScope> externalScopeProvider) {
    return new EpgRepositoryImpl_Factory(programDaoProvider, providerDaoProvider, xmltvParserProvider, okHttpClientProvider, transactionRunnerProvider, epgSourceRepositoryProvider, preferencesRepositoryProvider, externalScopeProvider);
  }

  public static EpgRepositoryImpl newInstance(ProgramDao programDao, ProviderDao providerDao,
      XmltvParser xmltvParser, OkHttpClient okHttpClient,
      DatabaseTransactionRunner transactionRunner, EpgSourceRepository epgSourceRepository,
      PreferencesRepository preferencesRepository, CoroutineScope externalScope) {
    return new EpgRepositoryImpl(programDao, providerDao, xmltvParser, okHttpClient, transactionRunner, epgSourceRepository, preferencesRepository, externalScope);
  }
}
