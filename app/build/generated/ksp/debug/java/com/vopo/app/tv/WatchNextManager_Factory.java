package com.vopo.app.tv;

import android.content.Context;
import com.vopo.domain.repository.PlaybackHistoryRepository;
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
public final class WatchNextManager_Factory implements Factory<WatchNextManager> {
  private final Provider<Context> contextProvider;

  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  private final Provider<ProviderRepository> providerRepositoryProvider;

  public WatchNextManager_Factory(Provider<Context> contextProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<ProviderRepository> providerRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
    this.providerRepositoryProvider = providerRepositoryProvider;
  }

  @Override
  public WatchNextManager get() {
    return newInstance(contextProvider.get(), playbackHistoryRepositoryProvider.get(), providerRepositoryProvider.get());
  }

  public static WatchNextManager_Factory create(Provider<Context> contextProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<ProviderRepository> providerRepositoryProvider) {
    return new WatchNextManager_Factory(contextProvider, playbackHistoryRepositoryProvider, providerRepositoryProvider);
  }

  public static WatchNextManager newInstance(Context context,
      PlaybackHistoryRepository playbackHistoryRepository, ProviderRepository providerRepository) {
    return new WatchNextManager(context, playbackHistoryRepository, providerRepository);
  }
}
