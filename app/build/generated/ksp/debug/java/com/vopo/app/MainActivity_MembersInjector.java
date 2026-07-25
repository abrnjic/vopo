package com.vopo.app;

import com.vopo.app.cast.CastManager;
import com.vopo.app.tv.LauncherRecommendationsManager;
import com.vopo.app.tv.WatchNextManager;
import com.vopo.app.tvinput.TvInputChannelSyncManager;
import com.vopo.data.preferences.PreferencesRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<WatchNextManager> watchNextManagerProvider;

  private final Provider<LauncherRecommendationsManager> launcherRecommendationsManagerProvider;

  private final Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider;

  private final Provider<CastManager> castManagerProvider;

  public MainActivity_MembersInjector(Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<WatchNextManager> watchNextManagerProvider,
      Provider<LauncherRecommendationsManager> launcherRecommendationsManagerProvider,
      Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider,
      Provider<CastManager> castManagerProvider) {
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.watchNextManagerProvider = watchNextManagerProvider;
    this.launcherRecommendationsManagerProvider = launcherRecommendationsManagerProvider;
    this.tvInputChannelSyncManagerProvider = tvInputChannelSyncManagerProvider;
    this.castManagerProvider = castManagerProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<WatchNextManager> watchNextManagerProvider,
      Provider<LauncherRecommendationsManager> launcherRecommendationsManagerProvider,
      Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider,
      Provider<CastManager> castManagerProvider) {
    return new MainActivity_MembersInjector(preferencesRepositoryProvider, watchNextManagerProvider, launcherRecommendationsManagerProvider, tvInputChannelSyncManagerProvider, castManagerProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectPreferencesRepository(instance, preferencesRepositoryProvider.get());
    injectWatchNextManager(instance, watchNextManagerProvider.get());
    injectLauncherRecommendationsManager(instance, launcherRecommendationsManagerProvider.get());
    injectTvInputChannelSyncManager(instance, tvInputChannelSyncManagerProvider.get());
    injectCastManager(instance, castManagerProvider.get());
  }

  @InjectedFieldSignature("com.vopo.app.MainActivity.preferencesRepository")
  public static void injectPreferencesRepository(MainActivity instance,
      PreferencesRepository preferencesRepository) {
    instance.preferencesRepository = preferencesRepository;
  }

  @InjectedFieldSignature("com.vopo.app.MainActivity.watchNextManager")
  public static void injectWatchNextManager(MainActivity instance,
      WatchNextManager watchNextManager) {
    instance.watchNextManager = watchNextManager;
  }

  @InjectedFieldSignature("com.vopo.app.MainActivity.launcherRecommendationsManager")
  public static void injectLauncherRecommendationsManager(MainActivity instance,
      LauncherRecommendationsManager launcherRecommendationsManager) {
    instance.launcherRecommendationsManager = launcherRecommendationsManager;
  }

  @InjectedFieldSignature("com.vopo.app.MainActivity.tvInputChannelSyncManager")
  public static void injectTvInputChannelSyncManager(MainActivity instance,
      TvInputChannelSyncManager tvInputChannelSyncManager) {
    instance.tvInputChannelSyncManager = tvInputChannelSyncManager;
  }

  @InjectedFieldSignature("com.vopo.app.MainActivity.castManager")
  public static void injectCastManager(MainActivity instance, CastManager castManager) {
    instance.castManager = castManager;
  }
}
