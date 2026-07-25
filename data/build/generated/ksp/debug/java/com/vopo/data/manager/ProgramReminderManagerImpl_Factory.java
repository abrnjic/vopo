package com.vopo.data.manager;

import com.vopo.data.local.dao.ProgramReminderDao;
import com.vopo.data.manager.reminder.ProgramReminderAlarmScheduler;
import com.vopo.data.manager.reminder.ProgramReminderNotifier;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
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
public final class ProgramReminderManagerImpl_Factory implements Factory<ProgramReminderManagerImpl> {
  private final Provider<ProgramReminderDao> programReminderDaoProvider;

  private final Provider<ProgramReminderAlarmScheduler> alarmSchedulerProvider;

  private final Provider<ProgramReminderNotifier> notifierProvider;

  public ProgramReminderManagerImpl_Factory(Provider<ProgramReminderDao> programReminderDaoProvider,
      Provider<ProgramReminderAlarmScheduler> alarmSchedulerProvider,
      Provider<ProgramReminderNotifier> notifierProvider) {
    this.programReminderDaoProvider = programReminderDaoProvider;
    this.alarmSchedulerProvider = alarmSchedulerProvider;
    this.notifierProvider = notifierProvider;
  }

  @Override
  public ProgramReminderManagerImpl get() {
    return newInstance(programReminderDaoProvider.get(), alarmSchedulerProvider.get(), notifierProvider.get());
  }

  public static ProgramReminderManagerImpl_Factory create(
      Provider<ProgramReminderDao> programReminderDaoProvider,
      Provider<ProgramReminderAlarmScheduler> alarmSchedulerProvider,
      Provider<ProgramReminderNotifier> notifierProvider) {
    return new ProgramReminderManagerImpl_Factory(programReminderDaoProvider, alarmSchedulerProvider, notifierProvider);
  }

  public static ProgramReminderManagerImpl newInstance(ProgramReminderDao programReminderDao,
      ProgramReminderAlarmScheduler alarmScheduler, ProgramReminderNotifier notifier) {
    return new ProgramReminderManagerImpl(programReminderDao, alarmScheduler, notifier);
  }
}
