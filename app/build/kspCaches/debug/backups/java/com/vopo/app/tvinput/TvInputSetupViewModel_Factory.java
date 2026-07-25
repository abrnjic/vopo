package com.vopo.app.tvinput;

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
public final class TvInputSetupViewModel_Factory implements Factory<TvInputSetupViewModel> {
  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider;

  public TvInputSetupViewModel_Factory(Provider<ProviderRepository> providerRepositoryProvider,
      Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider) {
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.tvInputChannelSyncManagerProvider = tvInputChannelSyncManagerProvider;
  }

  @Override
  public TvInputSetupViewModel get() {
    return newInstance(providerRepositoryProvider.get(), tvInputChannelSyncManagerProvider.get());
  }

  public static TvInputSetupViewModel_Factory create(
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider) {
    return new TvInputSetupViewModel_Factory(providerRepositoryProvider, tvInputChannelSyncManagerProvider);
  }

  public static TvInputSetupViewModel newInstance(ProviderRepository providerRepository,
      TvInputChannelSyncManager tvInputChannelSyncManager) {
    return new TvInputSetupViewModel(providerRepository, tvInputChannelSyncManager);
  }
}
