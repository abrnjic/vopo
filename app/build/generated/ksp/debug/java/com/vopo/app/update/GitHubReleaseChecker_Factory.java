package com.vopo.app.update;

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
public final class GitHubReleaseChecker_Factory implements Factory<GitHubReleaseChecker> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  public GitHubReleaseChecker_Factory(Provider<OkHttpClient> okHttpClientProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public GitHubReleaseChecker get() {
    return newInstance(okHttpClientProvider.get());
  }

  public static GitHubReleaseChecker_Factory create(Provider<OkHttpClient> okHttpClientProvider) {
    return new GitHubReleaseChecker_Factory(okHttpClientProvider);
  }

  public static GitHubReleaseChecker newInstance(OkHttpClient okHttpClient) {
    return new GitHubReleaseChecker(okHttpClient);
  }
}
