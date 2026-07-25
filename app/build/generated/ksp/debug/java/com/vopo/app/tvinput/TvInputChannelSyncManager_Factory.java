package com.vopo.app.tvinput;

import android.content.Context;
import com.vopo.domain.repository.ChannelRepository;
import com.vopo.domain.repository.EpgRepository;
import com.vopo.domain.repository.ProviderRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class TvInputChannelSyncManager_Factory implements Factory<TvInputChannelSyncManager> {
  private final Provider<Context> contextProvider;

  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<ChannelRepository> channelRepositoryProvider;

  private final Provider<EpgRepository> epgRepositoryProvider;

  public TvInputChannelSyncManager_Factory(Provider<Context> contextProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<EpgRepository> epgRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.channelRepositoryProvider = channelRepositoryProvider;
    this.epgRepositoryProvider = epgRepositoryProvider;
  }

  @Override
  public TvInputChannelSyncManager get() {
    return newInstance(contextProvider.get(), providerRepositoryProvider.get(), channelRepositoryProvider.get(), epgRepositoryProvider.get());
  }

  public static TvInputChannelSyncManager_Factory create(Provider<Context> contextProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<EpgRepository> epgRepositoryProvider) {
    return new TvInputChannelSyncManager_Factory(contextProvider, providerRepositoryProvider, channelRepositoryProvider, epgRepositoryProvider);
  }

  public static TvInputChannelSyncManager newInstance(Context context,
      ProviderRepository providerRepository, ChannelRepository channelRepository,
      EpgRepository epgRepository) {
    return new TvInputChannelSyncManager(context, providerRepository, channelRepository, epgRepository);
  }
}
