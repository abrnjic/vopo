package com.vopo.domain.usecase;

import com.vopo.domain.manager.ProviderSyncStateReader;
import com.vopo.domain.repository.ProviderRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class SyncProvider_Factory implements Factory<SyncProvider> {
  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<ProviderSyncStateReader> providerSyncStateReaderProvider;

  public SyncProvider_Factory(Provider<ProviderRepository> providerRepositoryProvider,
      Provider<ProviderSyncStateReader> providerSyncStateReaderProvider) {
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.providerSyncStateReaderProvider = providerSyncStateReaderProvider;
  }

  @Override
  public SyncProvider get() {
    return newInstance(providerRepositoryProvider.get(), providerSyncStateReaderProvider.get());
  }

  public static SyncProvider_Factory create(Provider<ProviderRepository> providerRepositoryProvider,
      Provider<ProviderSyncStateReader> providerSyncStateReaderProvider) {
    return new SyncProvider_Factory(providerRepositoryProvider, providerSyncStateReaderProvider);
  }

  public static SyncProvider newInstance(ProviderRepository providerRepository,
      ProviderSyncStateReader providerSyncStateReader) {
    return new SyncProvider(providerRepository, providerSyncStateReader);
  }
}
