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
public final class TsPassThroughCaptureEngine_Factory implements Factory<TsPassThroughCaptureEngine> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  public TsPassThroughCaptureEngine_Factory(Provider<OkHttpClient> okHttpClientProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public TsPassThroughCaptureEngine get() {
    return newInstance(okHttpClientProvider.get());
  }

  public static TsPassThroughCaptureEngine_Factory create(
      Provider<OkHttpClient> okHttpClientProvider) {
    return new TsPassThroughCaptureEngine_Factory(okHttpClientProvider);
  }

  public static TsPassThroughCaptureEngine newInstance(OkHttpClient okHttpClient) {
    return new TsPassThroughCaptureEngine(okHttpClient);
  }
}
