package com.vopo.data.remote.stalker;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class OkHttpStalkerApiService_Factory implements Factory<OkHttpStalkerApiService> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<Json> jsonProvider;

  public OkHttpStalkerApiService_Factory(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Json> jsonProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.jsonProvider = jsonProvider;
  }

  @Override
  public OkHttpStalkerApiService get() {
    return newInstance(okHttpClientProvider.get(), jsonProvider.get());
  }

  public static OkHttpStalkerApiService_Factory create(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Json> jsonProvider) {
    return new OkHttpStalkerApiService_Factory(okHttpClientProvider, jsonProvider);
  }

  public static OkHttpStalkerApiService newInstance(OkHttpClient okHttpClient, Json json) {
    return new OkHttpStalkerApiService(okHttpClient, json);
  }
}
