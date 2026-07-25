package com.vopo.app.ui.screens.settings;

import android.content.Context;
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
public final class InternetSpeedTestRunner_Factory implements Factory<InternetSpeedTestRunner> {
  private final Provider<Context> contextProvider;

  private final Provider<InternetDownloadSpeedProbe> downloadSpeedProbeProvider;

  public InternetSpeedTestRunner_Factory(Provider<Context> contextProvider,
      Provider<InternetDownloadSpeedProbe> downloadSpeedProbeProvider) {
    this.contextProvider = contextProvider;
    this.downloadSpeedProbeProvider = downloadSpeedProbeProvider;
  }

  @Override
  public InternetSpeedTestRunner get() {
    return newInstance(contextProvider.get(), downloadSpeedProbeProvider.get());
  }

  public static InternetSpeedTestRunner_Factory create(Provider<Context> contextProvider,
      Provider<InternetDownloadSpeedProbe> downloadSpeedProbeProvider) {
    return new InternetSpeedTestRunner_Factory(contextProvider, downloadSpeedProbeProvider);
  }

  public static InternetSpeedTestRunner newInstance(Context context,
      InternetDownloadSpeedProbe downloadSpeedProbe) {
    return new InternetSpeedTestRunner(context, downloadSpeedProbe);
  }
}
