package com.vopo.data.sync;

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
public final class SyncProgressBus_Factory implements Factory<SyncProgressBus> {
  @Override
  public SyncProgressBus get() {
    return newInstance();
  }

  public static SyncProgressBus_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SyncProgressBus newInstance() {
    return new SyncProgressBus();
  }

  private static final class InstanceHolder {
    static final SyncProgressBus_Factory INSTANCE = new SyncProgressBus_Factory();
  }
}
