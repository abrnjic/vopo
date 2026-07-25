package com.vopo.app.di;

import com.vopo.data.remote.stalker.StalkerApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.serialization.json.Json;
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
public final class NetworkModule_ProvideStalkerApiServiceFactory implements Factory<StalkerApiService> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<Json> xtreamJsonProvider;

  public NetworkModule_ProvideStalkerApiServiceFactory(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Json> xtreamJsonProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.xtreamJsonProvider = xtreamJsonProvider;
  }

  @Override
  public StalkerApiService get() {
    return provideStalkerApiService(okHttpClientProvider.get(), xtreamJsonProvider.get());
  }

  public static NetworkModule_ProvideStalkerApiServiceFactory create(
      Provider<OkHttpClient> okHttpClientProvider, Provider<Json> xtreamJsonProvider) {
    return new NetworkModule_ProvideStalkerApiServiceFactory(okHttpClientProvider, xtreamJsonProvider);
  }

  public static StalkerApiService provideStalkerApiService(OkHttpClient okHttpClient,
      Json xtreamJson) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideStalkerApiService(okHttpClient, xtreamJson));
  }
}
