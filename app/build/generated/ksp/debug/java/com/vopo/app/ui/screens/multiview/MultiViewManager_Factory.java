package com.vopo.app.ui.screens.multiview;

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
public final class MultiViewManager_Factory implements Factory<MultiViewManager> {
  @Override
  public MultiViewManager get() {
    return newInstance();
  }

  public static MultiViewManager_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MultiViewManager newInstance() {
    return new MultiViewManager();
  }

  private static final class InstanceHolder {
    static final MultiViewManager_Factory INSTANCE = new MultiViewManager_Factory();
  }
}
