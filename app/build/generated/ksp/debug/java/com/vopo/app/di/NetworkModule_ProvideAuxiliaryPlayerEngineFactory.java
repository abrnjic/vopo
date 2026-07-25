package com.vopo.app.di;

import android.content.Context;
import com.vopo.domain.repository.PlaybackCompatibilityRepository;
import com.vopo.player.AudioCompatibilityMemoryStore;
import com.vopo.player.PlaybackSupportSnapshotStore;
import com.vopo.player.PlayerEngine;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

@ScopeMetadata
@QualifierMetadata({
    "com.vopo.app.di.AuxiliaryPlayerEngine",
    "dagger.hilt.android.qualifiers.ApplicationContext"
})
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
public final class NetworkModule_ProvideAuxiliaryPlayerEngineFactory implements Factory<PlayerEngine> {
  private final Provider<Context> contextProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<PlaybackCompatibilityRepository> playbackCompatibilityRepositoryProvider;

  private final Provider<AudioCompatibilityMemoryStore> audioCompatibilityMemoryStoreProvider;

  private final Provider<PlaybackSupportSnapshotStore> playbackSupportSnapshotStoreProvider;

  public NetworkModule_ProvideAuxiliaryPlayerEngineFactory(Provider<Context> contextProvider,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<PlaybackCompatibilityRepository> playbackCompatibilityRepositoryProvider,
      Provider<AudioCompatibilityMemoryStore> audioCompatibilityMemoryStoreProvider,
      Provider<PlaybackSupportSnapshotStore> playbackSupportSnapshotStoreProvider) {
    this.contextProvider = contextProvider;
    this.okHttpClientProvider = okHttpClientProvider;
    this.playbackCompatibilityRepositoryProvider = playbackCompatibilityRepositoryProvider;
    this.audioCompatibilityMemoryStoreProvider = audioCompatibilityMemoryStoreProvider;
    this.playbackSupportSnapshotStoreProvider = playbackSupportSnapshotStoreProvider;
  }

  @Override
  public PlayerEngine get() {
    return provideAuxiliaryPlayerEngine(contextProvider.get(), okHttpClientProvider.get(), playbackCompatibilityRepositoryProvider.get(), audioCompatibilityMemoryStoreProvider.get(), playbackSupportSnapshotStoreProvider.get());
  }

  public static NetworkModule_ProvideAuxiliaryPlayerEngineFactory create(
      Provider<Context> contextProvider, Provider<OkHttpClient> okHttpClientProvider,
      Provider<PlaybackCompatibilityRepository> playbackCompatibilityRepositoryProvider,
      Provider<AudioCompatibilityMemoryStore> audioCompatibilityMemoryStoreProvider,
      Provider<PlaybackSupportSnapshotStore> playbackSupportSnapshotStoreProvider) {
    return new NetworkModule_ProvideAuxiliaryPlayerEngineFactory(contextProvider, okHttpClientProvider, playbackCompatibilityRepositoryProvider, audioCompatibilityMemoryStoreProvider, playbackSupportSnapshotStoreProvider);
  }

  public static PlayerEngine provideAuxiliaryPlayerEngine(Context context,
      OkHttpClient okHttpClient, PlaybackCompatibilityRepository playbackCompatibilityRepository,
      AudioCompatibilityMemoryStore audioCompatibilityMemoryStore,
      PlaybackSupportSnapshotStore playbackSupportSnapshotStore) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideAuxiliaryPlayerEngine(context, okHttpClient, playbackCompatibilityRepository, audioCompatibilityMemoryStore, playbackSupportSnapshotStore));
  }
}
