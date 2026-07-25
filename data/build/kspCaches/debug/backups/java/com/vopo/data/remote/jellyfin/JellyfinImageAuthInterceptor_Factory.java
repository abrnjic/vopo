package com.vopo.data.remote.jellyfin;

import com.vopo.data.local.dao.ProviderDao;
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
public final class JellyfinImageAuthInterceptor_Factory implements Factory<JellyfinImageAuthInterceptor> {
  private final Provider<ProviderDao> providerDaoProvider;

  private final Provider<CredentialCrypto> credentialCryptoProvider;

  public JellyfinImageAuthInterceptor_Factory(Provider<ProviderDao> providerDaoProvider,
      Provider<CredentialCrypto> credentialCryptoProvider) {
    this.providerDaoProvider = providerDaoProvider;
    this.credentialCryptoProvider = credentialCryptoProvider;
  }

  @Override
  public JellyfinImageAuthInterceptor get() {
    return newInstance(providerDaoProvider.get(), credentialCryptoProvider.get());
  }

  public static JellyfinImageAuthInterceptor_Factory create(
      Provider<ProviderDao> providerDaoProvider,
      Provider<CredentialCrypto> credentialCryptoProvider) {
    return new JellyfinImageAuthInterceptor_Factory(providerDaoProvider, credentialCryptoProvider);
  }

  public static JellyfinImageAuthInterceptor newInstance(ProviderDao providerDao,
      CredentialCrypto credentialCrypto) {
    return new JellyfinImageAuthInterceptor(providerDao, credentialCrypto);
  }
}
