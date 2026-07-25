package com.vopo.app.di;

import com.vopo.data.remote.xtream.XtreamApiService;
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
public final class NetworkModule_ProvideXtreamApiServiceFactory implements Factory<XtreamApiService> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<Json> xtreamJsonProvider;

  public NetworkModule_ProvideXtreamApiServiceFactory(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Json> xtreamJsonProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.xtreamJsonProvider = xtreamJsonProvider;
  }

  @Override
  public XtreamApiService get() {
    return provideXtreamApiService(okHttpClientProvider.get(), xtreamJsonProvider.get());
  }

  public static NetworkModule_ProvideXtreamApiServiceFactory create(
      Provider<OkHttpClient> okHttpClientProvider, Provider<Json> xtreamJsonProvider) {
    return new NetworkModule_ProvideXtreamApiServiceFactory(okHttpClientProvider, xtreamJsonProvider);
  }

  public static XtreamApiService provideXtreamApiService(OkHttpClient okHttpClient,
      Json xtreamJson) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideXtreamApiService(okHttpClient, xtreamJson));
  }
}
