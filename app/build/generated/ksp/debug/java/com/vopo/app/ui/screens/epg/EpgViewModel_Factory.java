package com.vopo.app.ui.screens.epg;

import android.app.Application;
import com.vopo.app.player.LivePreviewHandoffManager;
import com.vopo.app.plugins.VopoPluginManager;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.domain.manager.ParentalControlManager;
import com.vopo.domain.manager.ProgramReminderManager;
import com.vopo.domain.manager.RecordingManager;
import com.vopo.domain.repository.ChannelRepository;
import com.vopo.domain.repository.CombinedM3uRepository;
import com.vopo.domain.repository.EpgRepository;
import com.vopo.domain.repository.EpgSourceRepository;
import com.vopo.domain.repository.FavoriteRepository;
import com.vopo.domain.repository.ProviderRepository;
import com.vopo.domain.usecase.GetCustomCategories;
import com.vopo.domain.usecase.ScheduleRecording;
import com.vopo.player.PlayerEngine;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("com.vopo.app.di.AuxiliaryPlayerEngine")
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
public final class EpgViewModel_Factory implements Factory<EpgViewModel> {
  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<CombinedM3uRepository> combinedM3uRepositoryProvider;

  private final Provider<ChannelRepository> channelRepositoryProvider;

  private final Provider<EpgRepository> epgRepositoryProvider;

  private final Provider<EpgSourceRepository> epgSourceRepositoryProvider;

  private final Provider<FavoriteRepository> favoriteRepositoryProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<ParentalControlManager> parentalControlManagerProvider;

  private final Provider<ProgramReminderManager> programReminderManagerProvider;

  private final Provider<GetCustomCategories> getCustomCategoriesProvider;

  private final Provider<ScheduleRecording> scheduleRecordingProvider;

  private final Provider<RecordingManager> recordingManagerProvider;

  private final Provider<PlayerEngine> playerEngineProvider;

  private final Provider<VopoPluginManager> pluginManagerProvider;

  private final Provider<LivePreviewHandoffManager> livePreviewHandoffManagerProvider;

  private final Provider<Application> applicationProvider;

  public EpgViewModel_Factory(Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<EpgRepository> epgRepositoryProvider,
      Provider<EpgSourceRepository> epgSourceRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider,
      Provider<ProgramReminderManager> programReminderManagerProvider,
      Provider<GetCustomCategories> getCustomCategoriesProvider,
      Provider<ScheduleRecording> scheduleRecordingProvider,
      Provider<RecordingManager> recordingManagerProvider,
      Provider<PlayerEngine> playerEngineProvider,
      Provider<VopoPluginManager> pluginManagerProvider,
      Provider<LivePreviewHandoffManager> livePreviewHandoffManagerProvider,
      Provider<Application> applicationProvider) {
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.combinedM3uRepositoryProvider = combinedM3uRepositoryProvider;
    this.channelRepositoryProvider = channelRepositoryProvider;
    this.epgRepositoryProvider = epgRepositoryProvider;
    this.epgSourceRepositoryProvider = epgSourceRepositoryProvider;
    this.favoriteRepositoryProvider = favoriteRepositoryProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.parentalControlManagerProvider = parentalControlManagerProvider;
    this.programReminderManagerProvider = programReminderManagerProvider;
    this.getCustomCategoriesProvider = getCustomCategoriesProvider;
    this.scheduleRecordingProvider = scheduleRecordingProvider;
    this.recordingManagerProvider = recordingManagerProvider;
    this.playerEngineProvider = playerEngineProvider;
    this.pluginManagerProvider = pluginManagerProvider;
    this.livePreviewHandoffManagerProvider = livePreviewHandoffManagerProvider;
    this.applicationProvider = applicationProvider;
  }

  @Override
  public EpgViewModel get() {
    return newInstance(providerRepositoryProvider.get(), combinedM3uRepositoryProvider.get(), channelRepositoryProvider.get(), epgRepositoryProvider.get(), epgSourceRepositoryProvider.get(), favoriteRepositoryProvider.get(), preferencesRepositoryProvider.get(), parentalControlManagerProvider.get(), programReminderManagerProvider.get(), getCustomCategoriesProvider.get(), scheduleRecordingProvider.get(), recordingManagerProvider.get(), playerEngineProvider, pluginManagerProvider.get(), livePreviewHandoffManagerProvider.get(), applicationProvider.get());
  }

  public static EpgViewModel_Factory create(Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<EpgRepository> epgRepositoryProvider,
      Provider<EpgSourceRepository> epgSourceRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider,
      Provider<ProgramReminderManager> programReminderManagerProvider,
      Provider<GetCustomCategories> getCustomCategoriesProvider,
      Provider<ScheduleRecording> scheduleRecordingProvider,
      Provider<RecordingManager> recordingManagerProvider,
      Provider<PlayerEngine> playerEngineProvider,
      Provider<VopoPluginManager> pluginManagerProvider,
      Provider<LivePreviewHandoffManager> livePreviewHandoffManagerProvider,
      Provider<Application> applicationProvider) {
    return new EpgViewModel_Factory(providerRepositoryProvider, combinedM3uRepositoryProvider, channelRepositoryProvider, epgRepositoryProvider, epgSourceRepositoryProvider, favoriteRepositoryProvider, preferencesRepositoryProvider, parentalControlManagerProvider, programReminderManagerProvider, getCustomCategoriesProvider, scheduleRecordingProvider, recordingManagerProvider, playerEngineProvider, pluginManagerProvider, livePreviewHandoffManagerProvider, applicationProvider);
  }

  public static EpgViewModel newInstance(ProviderRepository providerRepository,
      CombinedM3uRepository combinedM3uRepository, ChannelRepository channelRepository,
      EpgRepository epgRepository, EpgSourceRepository epgSourceRepository,
      FavoriteRepository favoriteRepository, PreferencesRepository preferencesRepository,
      ParentalControlManager parentalControlManager, ProgramReminderManager programReminderManager,
      GetCustomCategories getCustomCategories, ScheduleRecording scheduleRecording,
      RecordingManager recordingManager, javax.inject.Provider<PlayerEngine> playerEngineProvider,
      VopoPluginManager pluginManager, LivePreviewHandoffManager livePreviewHandoffManager,
      Application application) {
    return new EpgViewModel(providerRepository, combinedM3uRepository, channelRepository, epgRepository, epgSourceRepository, favoriteRepository, preferencesRepository, parentalControlManager, programReminderManager, getCustomCategories, scheduleRecording, recordingManager, playerEngineProvider, pluginManager, livePreviewHandoffManager, application);
  }
}
