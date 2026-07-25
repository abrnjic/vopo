package com.vopo.domain.usecase;

import com.vopo.domain.manager.RecordingManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class ScheduleRecording_Factory implements Factory<ScheduleRecording> {
  private final Provider<RecordingManager> recordingManagerProvider;

  public ScheduleRecording_Factory(Provider<RecordingManager> recordingManagerProvider) {
    this.recordingManagerProvider = recordingManagerProvider;
  }

  @Override
  public ScheduleRecording get() {
    return newInstance(recordingManagerProvider.get());
  }

  public static ScheduleRecording_Factory create(
      Provider<RecordingManager> recordingManagerProvider) {
    return new ScheduleRecording_Factory(recordingManagerProvider);
  }

  public static ScheduleRecording newInstance(RecordingManager recordingManager) {
    return new ScheduleRecording(recordingManager);
  }
}
