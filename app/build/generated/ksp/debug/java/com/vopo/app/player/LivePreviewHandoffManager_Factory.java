package com.vopo.app.player;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class LivePreviewHandoffManager_Factory implements Factory<LivePreviewHandoffManager> {
  @Override
  public LivePreviewHandoffManager get() {
    return newInstance();
  }

  public static LivePreviewHandoffManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LivePreviewHandoffManager newInstance() {
    return new LivePreviewHandoffManager();
  }

  private static final class InstanceHolder {
    static final LivePreviewHandoffManager_Factory INSTANCE = new LivePreviewHandoffManager_Factory();
  }
}
