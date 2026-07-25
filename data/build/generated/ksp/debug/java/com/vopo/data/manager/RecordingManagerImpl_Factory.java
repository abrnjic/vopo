package com.vopo.data.manager;

import android.content.Context;
import com.google.gson.Gson;
import com.vopo.data.local.DatabaseTransactionRunner;
import com.vopo.data.local.dao.ProviderDao;
import com.vopo.data.local.dao.RecordingRunDao;
import com.vopo.data.local.dao.RecordingScheduleDao;
import com.vopo.data.local.dao.RecordingStorageDao;
import com.vopo.data.manager.recording.HlsLiveCaptureEngine;
import com.vopo.data.manager.recording.RecordingAlarmScheduler;
import com.vopo.data.manager.recording.RecordingServiceLauncher;
import com.vopo.data.manager.recording.RecordingSourceResolver;
import com.vopo.data.manager.recording.TsPassThroughCaptureEngine;
import com.vopo.data.preferences.PreferencesRepository;
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
public final class RecordingManagerImpl_Factory implements Factory<RecordingManagerImpl> {
  private final Provider<Context> contextProvider;

  private final Provider<Gson> gsonProvider;

  private final Provider<DatabaseTransactionRunner> transactionRunnerProvider;

  private final Provider<ProviderDao> providerDaoProvider;

  private final Provider<RecordingScheduleDao> recordingScheduleDaoProvider;

  private final Provider<RecordingRunDao> recordingRunDaoProvider;

  private final Provider<RecordingStorageDao> recordingStorageDaoProvider;

  private final Provider<RecordingSourceResolver> recordingSourceResolverProvider;

  private final Provider<TsPassThroughCaptureEngine> tsPassThroughCaptureEngineProvider;

  private final Provider<HlsLiveCaptureEngine> hlsLiveCaptureEngineProvider;

  private final Provider<RecordingAlarmScheduler> alarmSchedulerProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<RecordingServiceLauncher> recordingServiceLauncherProvider;

  public RecordingManagerImpl_Factory(Provider<Context> contextProvider,
      Provider<Gson> gsonProvider, Provider<DatabaseTransactionRunner> transactionRunnerProvider,
      Provider<ProviderDao> providerDaoProvider,
      Provider<RecordingScheduleDao> recordingScheduleDaoProvider,
      Provider<RecordingRunDao> recordingRunDaoProvider,
      Provider<RecordingStorageDao> recordingStorageDaoProvider,
      Provider<RecordingSourceResolver> recordingSourceResolverProvider,
      Provider<TsPassThroughCaptureEngine> tsPassThroughCaptureEngineProvider,
      Provider<HlsLiveCaptureEngine> hlsLiveCaptureEngineProvider,
      Provider<RecordingAlarmScheduler> alarmSchedulerProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<RecordingServiceLauncher> recordingServiceLauncherProvider) {
    this.contextProvider = contextProvider;
    this.gsonProvider = gsonProvider;
    this.transactionRunnerProvider = transactionRunnerProvider;
    this.providerDaoProvider = providerDaoProvider;
    this.recordingScheduleDaoProvider = recordingScheduleDaoProvider;
    this.recordingRunDaoProvider = recordingRunDaoProvider;
    this.recordingStorageDaoProvider = recordingStorageDaoProvider;
    this.recordingSourceResolverProvider = recordingSourceResolverProvider;
    this.tsPassThroughCaptureEngineProvider = tsPassThroughCaptureEngineProvider;
    this.hlsLiveCaptureEngineProvider = hlsLiveCaptureEngineProvider;
    this.alarmSchedulerProvider = alarmSchedulerProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.recordingServiceLauncherProvider = recordingServiceLauncherProvider;
  }

  @Override
  public RecordingManagerImpl get() {
    return newInstance(contextProvider.get(), gsonProvider.get(), transactionRunnerProvider.get(), providerDaoProvider.get(), recordingScheduleDaoProvider.get(), recordingRunDaoProvider.get(), recordingStorageDaoProvider.get(), recordingSourceResolverProvider.get(), tsPassThroughCaptureEngineProvider.get(), hlsLiveCaptureEngineProvider.get(), alarmSchedulerProvider.get(), preferencesRepositoryProvider.get(), recordingServiceLauncherProvider.get());
  }

  public static RecordingManagerImpl_Factory create(Provider<Context> contextProvider,
      Provider<Gson> gsonProvider, Provider<DatabaseTransactionRunner> transactionRunnerProvider,
      Provider<ProviderDao> providerDaoProvider,
      Provider<RecordingScheduleDao> recordingScheduleDaoProvider,
      Provider<RecordingRunDao> recordingRunDaoProvider,
      Provider<RecordingStorageDao> recordingStorageDaoProvider,
      Provider<RecordingSourceResolver> recordingSourceResolverProvider,
      Provider<TsPassThroughCaptureEngine> tsPassThroughCaptureEngineProvider,
      Provider<HlsLiveCaptureEngine> hlsLiveCaptureEngineProvider,
      Provider<RecordingAlarmScheduler> alarmSchedulerProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<RecordingServiceLauncher> recordingServiceLauncherProvider) {
    return new RecordingManagerImpl_Factory(contextProvider, gsonProvider, transactionRunnerProvider, providerDaoProvider, recordingScheduleDaoProvider, recordingRunDaoProvider, recordingStorageDaoProvider, recordingSourceResolverProvider, tsPassThroughCaptureEngineProvider, hlsLiveCaptureEngineProvider, alarmSchedulerProvider, preferencesRepositoryProvider, recordingServiceLauncherProvider);
  }

  public static RecordingManagerImpl newInstance(Context context, Gson gson,
      DatabaseTransactionRunner transactionRunner, ProviderDao providerDao,
      RecordingScheduleDao recordingScheduleDao, RecordingRunDao recordingRunDao,
      RecordingStorageDao recordingStorageDao, RecordingSourceResolver recordingSourceResolver,
      TsPassThroughCaptureEngine tsPassThroughCaptureEngine,
      HlsLiveCaptureEngine hlsLiveCaptureEngine, RecordingAlarmScheduler alarmScheduler,
      PreferencesRepository preferencesRepository,
      RecordingServiceLauncher recordingServiceLauncher) {
    return new RecordingManagerImpl(context, gson, transactionRunner, providerDao, recordingScheduleDao, recordingRunDao, recordingStorageDao, recordingSourceResolver, tsPassThroughCaptureEngine, hlsLiveCaptureEngine, alarmScheduler, preferencesRepository, recordingServiceLauncher);
  }
}
