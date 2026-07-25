package com.vopo.data.remote.jellyfin;

import com.google.gson.Gson;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
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
public final class JellyfinProvider_Factory implements Factory<JellyfinProvider> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<Gson> gsonProvider;

  public JellyfinProvider_Factory(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Gson> gsonProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public JellyfinProvider get() {
    return newInstance(okHttpClientProvider.get(), gsonProvider.get());
  }

  public static JellyfinProvider_Factory create(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Gson> gsonProvider) {
    return new JellyfinProvider_Factory(okHttpClientProvider, gsonProvider);
  }

  public static JellyfinProvider newInstance(OkHttpClient okHttpClient, Gson gson) {
    return new JellyfinProvider(okHttpClient, gson);
  }
}
