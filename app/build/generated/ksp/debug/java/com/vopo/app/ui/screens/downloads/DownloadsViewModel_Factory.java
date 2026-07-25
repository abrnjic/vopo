package com.vopo.app.ui.screens.downloads;

import android.content.Context;
import com.vopo.domain.repository.DownloadManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class DownloadsViewModel_Factory implements Factory<DownloadsViewModel> {
  private final Provider<DownloadManager> downloadManagerProvider;

  private final Provider<Context> applicationProvider;

  public DownloadsViewModel_Factory(Provider<DownloadManager> downloadManagerProvider,
      Provider<Context> applicationProvider) {
    this.downloadManagerProvider = downloadManagerProvider;
    this.applicationProvider = applicationProvider;
  }

  @Override
  public DownloadsViewModel get() {
    return newInstance(downloadManagerProvider.get(), applicationProvider.get());
  }

  public static DownloadsViewModel_Factory create(Provider<DownloadManager> downloadManagerProvider,
      Provider<Context> applicationProvider) {
    return new DownloadsViewModel_Factory(downloadManagerProvider, applicationProvider);
  }

  public static DownloadsViewModel newInstance(DownloadManager downloadManager,
      Context application) {
    return new DownloadsViewModel(downloadManager, application);
  }
}
