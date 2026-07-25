package com.vopo.data.manager.recording;

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
public final class RecordingAlarmScheduler_Factory implements Factory<RecordingAlarmScheduler> {
  private final Provider<Context> contextProvider;

  public RecordingAlarmScheduler_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public RecordingAlarmScheduler get() {
    return newInstance(contextProvider.get());
  }

  public static RecordingAlarmScheduler_Factory create(Provider<Context> contextProvider) {
    return new RecordingAlarmScheduler_Factory(contextProvider);
  }

  public static RecordingAlarmScheduler newInstance(Context context) {
    return new RecordingAlarmScheduler(context);
  }
}
