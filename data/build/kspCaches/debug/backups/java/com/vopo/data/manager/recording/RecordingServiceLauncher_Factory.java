package com.vopo.data.manager.recording;

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
public final class RecordingServiceLauncher_Factory implements Factory<RecordingServiceLauncher> {
  @Override
  public RecordingServiceLauncher get() {
    return newInstance();
  }

  public static RecordingServiceLauncher_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RecordingServiceLauncher newInstance() {
    return new RecordingServiceLauncher();
  }

  private static final class InstanceHolder {
    static final RecordingServiceLauncher_Factory INSTANCE = new RecordingServiceLauncher_Factory();
  }
}
