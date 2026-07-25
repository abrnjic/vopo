package com.vopo.data.manager.reminder;

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
public final class ProgramReminderAlarmScheduler_Factory implements Factory<ProgramReminderAlarmScheduler> {
  private final Provider<Context> contextProvider;

  public ProgramReminderAlarmScheduler_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ProgramReminderAlarmScheduler get() {
    return newInstance(contextProvider.get());
  }

  public static ProgramReminderAlarmScheduler_Factory create(Provider<Context> contextProvider) {
    return new ProgramReminderAlarmScheduler_Factory(contextProvider);
  }

  public static ProgramReminderAlarmScheduler newInstance(Context context) {
    return new ProgramReminderAlarmScheduler(context);
  }
}
