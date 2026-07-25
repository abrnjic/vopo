package com.vopo.data.remote.xtream;

import com.vopo.data.local.dao.ProviderDao;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.data.remote.stalker.StalkerApiService;
import com.vopo.data.security.CredentialCrypto;
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
public final class XtreamStreamUrlResolver_Factory implements Factory<XtreamStreamUrlResolver> {
  private final Provider<ProviderDao> providerDaoProvider;

  private final Provider<CredentialCrypto> credentialCryptoProvider;

  private final Provider<StalkerApiService> stalkerApiServiceProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  public XtreamStreamUrlResolver_Factory(Provider<ProviderDao> providerDaoProvider,
      Provider<CredentialCrypto> credentialCryptoProvider,
      Provider<StalkerApiService> stalkerApiServiceProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider) {
    this.providerDaoProvider = providerDaoProvider;
    this.credentialCryptoProvider = credentialCryptoProvider;
    this.stalkerApiServiceProvider = stalkerApiServiceProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
  }

  @Override
  public XtreamStreamUrlResolver get() {
    return newInstance(providerDaoProvider.get(), credentialCryptoProvider.get(), stalkerApiServiceProvider.get(), preferencesRepositoryProvider.get());
  }

  public static XtreamStreamUrlResolver_Factory create(Provider<ProviderDao> providerDaoProvider,
      Provider<CredentialCrypto> credentialCryptoProvider,
      Provider<StalkerApiService> stalkerApiServiceProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider) {
    return new XtreamStreamUrlResolver_Factory(providerDaoProvider, credentialCryptoProvider, stalkerApiServiceProvider, preferencesRepositoryProvider);
  }

  public static XtreamStreamUrlResolver newInstance(ProviderDao providerDao,
      CredentialCrypto credentialCrypto, StalkerApiService stalkerApiService,
      PreferencesRepository preferencesRepository) {
    return new XtreamStreamUrlResolver(providerDao, credentialCrypto, stalkerApiService, preferencesRepository);
  }
}
