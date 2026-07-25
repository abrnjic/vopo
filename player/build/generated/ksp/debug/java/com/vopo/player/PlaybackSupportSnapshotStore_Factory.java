package com.vopo.player;

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
public final class PlaybackSupportSnapshotStore_Factory implements Factory<PlaybackSupportSnapshotStore> {
  private final Provider<Context> contextProvider;

  public PlaybackSupportSnapshotStore_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public PlaybackSupportSnapshotStore get() {
    return newInstance(contextProvider.get());
  }

  public static PlaybackSupportSnapshotStore_Factory create(Provider<Context> contextProvider) {
    return new PlaybackSupportSnapshotStore_Factory(contextProvider);
  }

  public static PlaybackSupportSnapshotStore newInstance(Context context) {
    return new PlaybackSupportSnapshotStore(context);
  }
}
