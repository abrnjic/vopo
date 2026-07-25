package com.vopo.app.ui.screens.settings;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

@ScopeMetadata
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
public final class InternetDownloadSpeedProbe_Factory implements Factory<InternetDownloadSpeedProbe> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  public InternetDownloadSpeedProbe_Factory(Provider<OkHttpClient> okHttpClientProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public InternetDownloadSpeedProbe get() {
    return newInstance(okHttpClientProvider.get());
  }

  public static InternetDownloadSpeedProbe_Factory create(
      Provider<OkHttpClient> okHttpClientProvider) {
    return new InternetDownloadSpeedProbe_Factory(okHttpClientProvider);
  }

  public static InternetDownloadSpeedProbe newInstance(OkHttpClient okHttpClient) {
    return new InternetDownloadSpeedProbe(okHttpClient);
  }
}
