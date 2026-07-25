package com.vopo.app;

import com.vopo.app.update.GitHubReleaseChecker;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.data.remote.jellyfin.JellyfinImageAuthInterceptor;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class VopoApp_MembersInjector implements MembersInjector<VopoApp> {
  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<GitHubReleaseChecker> gitHubReleaseCheckerProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<JellyfinImageAuthInterceptor> jellyfinImageAuthInterceptorProvider;

  public VopoApp_MembersInjector(Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<GitHubReleaseChecker> gitHubReleaseCheckerProvider,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<JellyfinImageAuthInterceptor> jellyfinImageAuthInterceptorProvider) {
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.gitHubReleaseCheckerProvider = gitHubReleaseCheckerProvider;
    this.okHttpClientProvider = okHttpClientProvider;
    this.jellyfinImageAuthInterceptorProvider = jellyfinImageAuthInterceptorProvider;
  }

  public static MembersInjector<VopoApp> create(
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<GitHubReleaseChecker> gitHubReleaseCheckerProvider,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<JellyfinImageAuthInterceptor> jellyfinImageAuthInterceptorProvider) {
    return new VopoApp_MembersInjector(preferencesRepositoryProvider, gitHubReleaseCheckerProvider, okHttpClientProvider, jellyfinImageAuthInterceptorProvider);
  }

  @Override
  public void injectMembers(VopoApp instance) {
    injectPreferencesRepository(instance, preferencesRepositoryProvider.get());
    injectGitHubReleaseChecker(instance, gitHubReleaseCheckerProvider.get());
    injectOkHttpClient(instance, okHttpClientProvider.get());
    injectJellyfinImageAuthInterceptor(instance, jellyfinImageAuthInterceptorProvider.get());
  }

  @InjectedFieldSignature("com.vopo.app.VopoApp.preferencesRepository")
  public static void injectPreferencesRepository(VopoApp instance,
      PreferencesRepository preferencesRepository) {
    instance.preferencesRepository = preferencesRepository;
  }

  @InjectedFieldSignature("com.vopo.app.VopoApp.gitHubReleaseChecker")
  public static void injectGitHubReleaseChecker(VopoApp instance,
      GitHubReleaseChecker gitHubReleaseChecker) {
    instance.gitHubReleaseChecker = gitHubReleaseChecker;
  }

  @InjectedFieldSignature("com.vopo.app.VopoApp.okHttpClient")
  public static void injectOkHttpClient(VopoApp instance, OkHttpClient okHttpClient) {
    instance.okHttpClient = okHttpClient;
  }

  @InjectedFieldSignature("com.vopo.app.VopoApp.jellyfinImageAuthInterceptor")
  public static void injectJellyfinImageAuthInterceptor(VopoApp instance,
      JellyfinImageAuthInterceptor jellyfinImageAuthInterceptor) {
    instance.jellyfinImageAuthInterceptor = jellyfinImageAuthInterceptor;
  }
}
