package com.vopo.app.di;

import com.google.gson.Gson;
import com.vopo.data.remote.jellyfin.JellyfinProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideJellyfinProviderFactory implements Factory<JellyfinProvider> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<Gson> gsonProvider;

  public DatabaseModule_ProvideJellyfinProviderFactory(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Gson> gsonProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public JellyfinProvider get() {
    return provideJellyfinProvider(okHttpClientProvider.get(), gsonProvider.get());
  }

  public static DatabaseModule_ProvideJellyfinProviderFactory create(
      Provider<OkHttpClient> okHttpClientProvider, Provider<Gson> gsonProvider) {
    return new DatabaseModule_ProvideJellyfinProviderFactory(okHttpClientProvider, gsonProvider);
  }

  public static JellyfinProvider provideJellyfinProvider(OkHttpClient okHttpClient, Gson gson) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideJellyfinProvider(okHttpClient, gson));
  }
}
