package com.vopo.data.repository;

import com.vopo.data.local.DatabaseTransactionRunner;
import com.vopo.data.local.dao.CategoryDao;
import com.vopo.data.local.dao.ChannelDao;
import com.vopo.data.local.dao.ProgramDao;
import com.vopo.data.local.dao.ProgramReminderDao;
import com.vopo.data.local.dao.ProviderDao;
import com.vopo.data.local.dao.RecordingRunDao;
import com.vopo.data.manager.recording.RecordingAlarmScheduler;
import com.vopo.data.manager.reminder.ProgramReminderAlarmScheduler;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.data.remote.jellyfin.JellyfinProvider;
import com.vopo.data.remote.stalker.StalkerApiService;
import com.vopo.data.remote.xtream.XtreamApiService;
import com.vopo.data.security.CredentialCrypto;
import com.vopo.data.sync.SyncManager;
import com.vopo.domain.repository.SyncMetadataRepository;
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
public final class ProviderRepositoryImpl_Factory implements Factory<ProviderRepositoryImpl> {
  private final Provider<ProviderDao> providerDaoProvider;

  private final Provider<CategoryDao> categoryDaoProvider;

  private final Provider<ChannelDao> channelDaoProvider;

  private final Provider<ProgramDao> programDaoProvider;

  private final Provider<RecordingRunDao> recordingRunDaoProvider;

  private final Provider<ProgramReminderDao> programReminderDaoProvider;

  private final Provider<StalkerApiService> stalkerApiServiceProvider;

  private final Provider<XtreamApiService> xtreamApiServiceProvider;

  private final Provider<CredentialCrypto> credentialCryptoProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<SyncManager> syncManagerProvider;

  private final Provider<SyncMetadataRepository> syncMetadataRepositoryProvider;

  private final Provider<DatabaseTransactionRunner> transactionRunnerProvider;

  private final Provider<RecordingAlarmScheduler> recordingAlarmSchedulerProvider;

  private final Provider<ProgramReminderAlarmScheduler> programReminderAlarmSchedulerProvider;

  private final Provider<JellyfinProvider> jellyfinProvider;

  public ProviderRepositoryImpl_Factory(Provider<ProviderDao> providerDaoProvider,
      Provider<CategoryDao> categoryDaoProvider, Provider<ChannelDao> channelDaoProvider,
      Provider<ProgramDao> programDaoProvider, Provider<RecordingRunDao> recordingRunDaoProvider,
      Provider<ProgramReminderDao> programReminderDaoProvider,
      Provider<StalkerApiService> stalkerApiServiceProvider,
      Provider<XtreamApiService> xtreamApiServiceProvider,
      Provider<CredentialCrypto> credentialCryptoProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<SyncManager> syncManagerProvider,
      Provider<SyncMetadataRepository> syncMetadataRepositoryProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider,
      Provider<RecordingAlarmScheduler> recordingAlarmSchedulerProvider,
      Provider<ProgramReminderAlarmScheduler> programReminderAlarmSchedulerProvider,
      Provider<JellyfinProvider> jellyfinProvider) {
    this.providerDaoProvider = providerDaoProvider;
    this.categoryDaoProvider = categoryDaoProvider;
    this.channelDaoProvider = channelDaoProvider;
    this.programDaoProvider = programDaoProvider;
    this.recordingRunDaoProvider = recordingRunDaoProvider;
    this.programReminderDaoProvider = programReminderDaoProvider;
    this.stalkerApiServiceProvider = stalkerApiServiceProvider;
    this.xtreamApiServiceProvider = xtreamApiServiceProvider;
    this.credentialCryptoProvider = credentialCryptoProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.syncManagerProvider = syncManagerProvider;
    this.syncMetadataRepositoryProvider = syncMetadataRepositoryProvider;
    this.transactionRunnerProvider = transactionRunnerProvider;
    this.recordingAlarmSchedulerProvider = recordingAlarmSchedulerProvider;
    this.programReminderAlarmSchedulerProvider = programReminderAlarmSchedulerProvider;
    this.jellyfinProvider = jellyfinProvider;
  }

  @Override
  public ProviderRepositoryImpl get() {
    return newInstance(providerDaoProvider.get(), categoryDaoProvider.get(), channelDaoProvider.get(), programDaoProvider.get(), recordingRunDaoProvider.get(), programReminderDaoProvider.get(), stalkerApiServiceProvider.get(), xtreamApiServiceProvider.get(), credentialCryptoProvider.get(), preferencesRepositoryProvider.get(), syncManagerProvider.get(), syncMetadataRepositoryProvider.get(), transactionRunnerProvider.get(), recordingAlarmSchedulerProvider.get(), programReminderAlarmSchedulerProvider.get(), jellyfinProvider.get());
  }

  public static ProviderRepositoryImpl_Factory create(Provider<ProviderDao> providerDaoProvider,
      Provider<CategoryDao> categoryDaoProvider, Provider<ChannelDao> channelDaoProvider,
      Provider<ProgramDao> programDaoProvider, Provider<RecordingRunDao> recordingRunDaoProvider,
      Provider<ProgramReminderDao> programReminderDaoProvider,
      Provider<StalkerApiService> stalkerApiServiceProvider,
      Provider<XtreamApiService> xtreamApiServiceProvider,
      Provider<CredentialCrypto> credentialCryptoProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<SyncManager> syncManagerProvider,
      Provider<SyncMetadataRepository> syncMetadataRepositoryProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider,
      Provider<RecordingAlarmScheduler> recordingAlarmSchedulerProvider,
      Provider<ProgramReminderAlarmScheduler> programReminderAlarmSchedulerProvider,
      Provider<JellyfinProvider> jellyfinProvider) {
    return new ProviderRepositoryImpl_Factory(providerDaoProvider, categoryDaoProvider, channelDaoProvider, programDaoProvider, recordingRunDaoProvider, programReminderDaoProvider, stalkerApiServiceProvider, xtreamApiServiceProvider, credentialCryptoProvider, preferencesRepositoryProvider, syncManagerProvider, syncMetadataRepositoryProvider, transactionRunnerProvider, recordingAlarmSchedulerProvider, programReminderAlarmSchedulerProvider, jellyfinProvider);
  }

  public static ProviderRepositoryImpl newInstance(ProviderDao providerDao, CategoryDao categoryDao,
      ChannelDao channelDao, ProgramDao programDao, RecordingRunDao recordingRunDao,
      ProgramReminderDao programReminderDao, StalkerApiService stalkerApiService,
      XtreamApiService xtreamApiService, CredentialCrypto credentialCrypto,
      PreferencesRepository preferencesRepository, SyncManager syncManager,
      SyncMetadataRepository syncMetadataRepository, DatabaseTransactionRunner transactionRunner,
      RecordingAlarmScheduler recordingAlarmScheduler,
      ProgramReminderAlarmScheduler programReminderAlarmScheduler,
      JellyfinProvider jellyfinProvider) {
    return new ProviderRepositoryImpl(providerDao, categoryDao, channelDao, programDao, recordingRunDao, programReminderDao, stalkerApiService, xtreamApiService, credentialCrypto, preferencesRepository, syncManager, syncMetadataRepository, transactionRunner, recordingAlarmScheduler, programReminderAlarmScheduler, jellyfinProvider);
  }
}
