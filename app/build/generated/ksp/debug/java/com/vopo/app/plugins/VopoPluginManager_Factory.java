package com.vopo.app.plugins;

import android.content.Context;
import com.vopo.app.tvinput.TvInputChannelSyncManager;
import com.vopo.domain.repository.CombinedM3uRepository;
import com.vopo.domain.repository.ProviderRepository;
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
public final class VopoPluginManager_Factory implements Factory<VopoPluginManager> {
  private final Provider<Context> contextProvider;

  private final Provider<PluginMessengerClient> messengerClientProvider;

  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<CombinedM3uRepository> combinedM3uRepositoryProvider;

  private final Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<Json> jsonProvider;

  public VopoPluginManager_Factory(Provider<Context> contextProvider,
      Provider<PluginMessengerClient> messengerClientProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider,
      Provider<OkHttpClient> okHttpClientProvider, Provider<Json> jsonProvider) {
    this.contextProvider = contextProvider;
    this.messengerClientProvider = messengerClientProvider;
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.combinedM3uRepositoryProvider = combinedM3uRepositoryProvider;
    this.tvInputChannelSyncManagerProvider = tvInputChannelSyncManagerProvider;
    this.okHttpClientProvider = okHttpClientProvider;
    this.jsonProvider = jsonProvider;
  }

  @Override
  public VopoPluginManager get() {
    return newInstance(contextProvider.get(), messengerClientProvider.get(), providerRepositoryProvider.get(), combinedM3uRepositoryProvider.get(), tvInputChannelSyncManagerProvider.get(), okHttpClientProvider.get(), jsonProvider.get());
  }

  public static VopoPluginManager_Factory create(Provider<Context> contextProvider,
      Provider<PluginMessengerClient> messengerClientProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider,
      Provider<OkHttpClient> okHttpClientProvider, Provider<Json> jsonProvider) {
    return new VopoPluginManager_Factory(contextProvider, messengerClientProvider, providerRepositoryProvider, combinedM3uRepositoryProvider, tvInputChannelSyncManagerProvider, okHttpClientProvider, jsonProvider);
  }

  public static VopoPluginManager newInstance(Context context,
      PluginMessengerClient messengerClient, ProviderRepository providerRepository,
      CombinedM3uRepository combinedM3uRepository,
      TvInputChannelSyncManager tvInputChannelSyncManager, OkHttpClient okHttpClient, Json json) {
    return new VopoPluginManager(context, messengerClient, providerRepository, combinedM3uRepository, tvInputChannelSyncManager, okHttpClient, json);
  }
}
