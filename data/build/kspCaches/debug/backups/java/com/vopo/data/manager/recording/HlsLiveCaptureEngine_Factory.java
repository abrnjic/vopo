package com.vopo.data.manager.recording;

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
public final class HlsLiveCaptureEngine_Factory implements Factory<HlsLiveCaptureEngine> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  public HlsLiveCaptureEngine_Factory(Provider<OkHttpClient> okHttpClientProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public HlsLiveCaptureEngine get() {
    return newInstance(okHttpClientProvider.get());
  }

  public static HlsLiveCaptureEngine_Factory create(Provider<OkHttpClient> okHttpClientProvider) {
    return new HlsLiveCaptureEngine_Factory(okHttpClientProvider);
  }

  public static HlsLiveCaptureEngine newInstance(OkHttpClient okHttpClient) {
    return new HlsLiveCaptureEngine(okHttpClient);
  }
}
