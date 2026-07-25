package com.vopo.player;

import android.content.Context;
import com.vopo.domain.repository.PlaybackCompatibilityRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class Media3PlayerEngine_Factory implements Factory<Media3PlayerEngine> {
  private final Provider<Context> contextProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<PlaybackCompatibilityRepository> playbackCompatibilityRepositoryProvider;

  private final Provider<AudioCompatibilityMemoryStore> audioCompatibilityMemoryStoreProvider;

  private final Provider<PlaybackSupportSnapshotStore> playbackSupportSnapshotStoreProvider;

  public Media3PlayerEngine_Factory(Provider<Context> contextProvider,
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
  public Media3PlayerEngine get() {
    return newInstance(contextProvider.get(), okHttpClientProvider.get(), playbackCompatibilityRepositoryProvider.get(), audioCompatibilityMemoryStoreProvider.get(), playbackSupportSnapshotStoreProvider.get());
  }

  public static Media3PlayerEngine_Factory create(Provider<Context> contextProvider,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<PlaybackCompatibilityRepository> playbackCompatibilityRepositoryProvider,
      Provider<AudioCompatibilityMemoryStore> audioCompatibilityMemoryStoreProvider,
      Provider<PlaybackSupportSnapshotStore> playbackSupportSnapshotStoreProvider) {
    return new Media3PlayerEngine_Factory(contextProvider, okHttpClientProvider, playbackCompatibilityRepositoryProvider, audioCompatibilityMemoryStoreProvider, playbackSupportSnapshotStoreProvider);
  }

  public static Media3PlayerEngine newInstance(Context context, OkHttpClient okHttpClient,
      PlaybackCompatibilityRepository playbackCompatibilityRepository,
      AudioCompatibilityMemoryStore audioCompatibilityMemoryStore,
      PlaybackSupportSnapshotStore playbackSupportSnapshotStore) {
    return new Media3PlayerEngine(context, okHttpClient, playbackCompatibilityRepository, audioCompatibilityMemoryStore, playbackSupportSnapshotStore);
  }
}
