package com.vopo.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.vopo.app.cast.CastManager;
import com.vopo.app.cast.CastRouteChooserActivity;
import com.vopo.app.cast.CastRouteChooserActivity_MembersInjector;
import com.vopo.app.di.DatabaseModule_ProvideCatalogSyncDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideCategoryDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideChannelDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideChannelEpgMappingDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideChannelPreferenceDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideCombinedM3uProfileDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideCombinedM3uProfileMemberDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideDatabaseFactory;
import com.vopo.app.di.DatabaseModule_ProvideDownloadDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideEpgChannelDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideEpgProgrammeDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideEpgSourceDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideEpisodeDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideFavoriteDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideJellyfinProviderFactory;
import com.vopo.app.di.DatabaseModule_ProvideMovieCategoryHydrationDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideMovieDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvidePlaybackCompatibilityDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvidePlaybackHistoryDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideProgramDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideProgramReminderDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideProviderDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideProviderEpgSourceDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideRecordingRunDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideRecordingScheduleDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideRecordingStorageDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideSearchDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideSearchHistoryDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideSeriesCategoryHydrationDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideSeriesDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideSyncMetadataDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideTmdbIdentityDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideVirtualGroupDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideXtreamContentIndexDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideXtreamIndexJobDaoFactory;
import com.vopo.app.di.DatabaseModule_ProvideXtreamLiveOnboardingDaoFactory;
import com.vopo.app.di.FirebaseModule_ProvideFirebaseFirestoreFactory;
import com.vopo.app.di.NetworkModule_ProvideAuxiliaryPlayerEngineFactory;
import com.vopo.app.di.NetworkModule_ProvideGsonFactory;
import com.vopo.app.di.NetworkModule_ProvideMainPlayerEngineFactory;
import com.vopo.app.di.NetworkModule_ProvideOkHttpClientFactory;
import com.vopo.app.di.NetworkModule_ProvideStalkerApiServiceFactory;
import com.vopo.app.di.NetworkModule_ProvideXmltvParserFactory;
import com.vopo.app.di.NetworkModule_ProvideXtreamApiServiceFactory;
import com.vopo.app.di.NetworkModule_ProvideXtreamJsonFactory;
import com.vopo.app.di.RepositoryModule_Companion_ProvideM3uParserFactory;
import com.vopo.app.di.RepositoryModule_Companion_ProvideRepositoryCoroutineScopeFactory;
import com.vopo.app.diagnostics.ExternalPlayerProbeActivity;
import com.vopo.app.diagnostics.ExternalPlayerProbeActivity_MembersInjector;
import com.vopo.app.pairing.ProviderQrPairingManager;
import com.vopo.app.player.LivePreviewHandoffManager;
import com.vopo.app.plugins.PluginMessengerClient;
import com.vopo.app.plugins.VopoPluginManager;
import com.vopo.app.tv.LauncherRecommendationsManager;
import com.vopo.app.tv.WatchNextManager;
import com.vopo.app.tvinput.TvInputChannelSyncManager;
import com.vopo.app.tvinput.TvInputSetupActivity;
import com.vopo.app.tvinput.TvInputSetupViewModel;
import com.vopo.app.tvinput.TvInputSetupViewModel_HiltModules;
import com.vopo.app.tvinput.TvInputSetupViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.tvinput.TvInputSetupViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.tvinput.VopoTvInputService;
import com.vopo.app.tvinput.VopoTvInputService_MembersInjector;
import com.vopo.app.ui.screens.dashboard.DashboardViewModel;
import com.vopo.app.ui.screens.dashboard.DashboardViewModel_HiltModules;
import com.vopo.app.ui.screens.dashboard.DashboardViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.dashboard.DashboardViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.downloads.DownloadsViewModel;
import com.vopo.app.ui.screens.downloads.DownloadsViewModel_HiltModules;
import com.vopo.app.ui.screens.downloads.DownloadsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.downloads.DownloadsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.epg.EpgViewModel;
import com.vopo.app.ui.screens.epg.EpgViewModel_HiltModules;
import com.vopo.app.ui.screens.epg.EpgViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.epg.EpgViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.favorites.FavoritesViewModel;
import com.vopo.app.ui.screens.favorites.FavoritesViewModel_HiltModules;
import com.vopo.app.ui.screens.favorites.FavoritesViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.favorites.FavoritesViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.home.HomeViewModel;
import com.vopo.app.ui.screens.home.HomeViewModel_HiltModules;
import com.vopo.app.ui.screens.home.HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.home.HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.movies.MovieDetailViewModel;
import com.vopo.app.ui.screens.movies.MovieDetailViewModel_HiltModules;
import com.vopo.app.ui.screens.movies.MovieDetailViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.movies.MovieDetailViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.movies.MoviesViewModel;
import com.vopo.app.ui.screens.movies.MoviesViewModel_HiltModules;
import com.vopo.app.ui.screens.movies.MoviesViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.movies.MoviesViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.multiview.MultiViewManager;
import com.vopo.app.ui.screens.multiview.MultiViewViewModel;
import com.vopo.app.ui.screens.multiview.MultiViewViewModel_HiltModules;
import com.vopo.app.ui.screens.multiview.MultiViewViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.multiview.MultiViewViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.player.PlayerViewModel;
import com.vopo.app.ui.screens.player.PlayerViewModel_HiltModules;
import com.vopo.app.ui.screens.player.PlayerViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.player.PlayerViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.player.SeekThumbnailProvider;
import com.vopo.app.ui.screens.plugins.PluginsViewModel;
import com.vopo.app.ui.screens.plugins.PluginsViewModel_HiltModules;
import com.vopo.app.ui.screens.plugins.PluginsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.plugins.PluginsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.provider.ProviderSetupViewModel;
import com.vopo.app.ui.screens.provider.ProviderSetupViewModel_HiltModules;
import com.vopo.app.ui.screens.provider.ProviderSetupViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.provider.ProviderSetupViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.search.SearchViewModel;
import com.vopo.app.ui.screens.search.SearchViewModel_HiltModules;
import com.vopo.app.ui.screens.search.SearchViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.search.SearchViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.series.SeriesDetailViewModel;
import com.vopo.app.ui.screens.series.SeriesDetailViewModel_HiltModules;
import com.vopo.app.ui.screens.series.SeriesDetailViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.series.SeriesDetailViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.series.SeriesViewModel;
import com.vopo.app.ui.screens.series.SeriesViewModel_HiltModules;
import com.vopo.app.ui.screens.series.SeriesViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.series.SeriesViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.settings.InternetDownloadSpeedProbe;
import com.vopo.app.ui.screens.settings.InternetSpeedTestRunner;
import com.vopo.app.ui.screens.settings.SettingsViewModel;
import com.vopo.app.ui.screens.settings.SettingsViewModel_HiltModules;
import com.vopo.app.ui.screens.settings.SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.settings.SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.settings.parental.ParentalControlGroupViewModel;
import com.vopo.app.ui.screens.settings.parental.ParentalControlGroupViewModel_HiltModules;
import com.vopo.app.ui.screens.settings.parental.ParentalControlGroupViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.settings.parental.ParentalControlGroupViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.ui.screens.welcome.WelcomeViewModel;
import com.vopo.app.ui.screens.welcome.WelcomeViewModel_HiltModules;
import com.vopo.app.ui.screens.welcome.WelcomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.vopo.app.ui.screens.welcome.WelcomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.vopo.app.update.AppUpdateInstaller;
import com.vopo.app.update.GitHubReleaseChecker;
import com.vopo.data.epg.EpgResolutionEngine;
import com.vopo.data.local.RoomDatabaseTransactionRunner;
import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.CatalogSyncDao;
import com.vopo.data.local.dao.CategoryDao;
import com.vopo.data.local.dao.ChannelDao;
import com.vopo.data.local.dao.ChannelEpgMappingDao;
import com.vopo.data.local.dao.ChannelPreferenceDao;
import com.vopo.data.local.dao.CombinedM3uProfileDao;
import com.vopo.data.local.dao.CombinedM3uProfileMemberDao;
import com.vopo.data.local.dao.DownloadDao;
import com.vopo.data.local.dao.EpgChannelDao;
import com.vopo.data.local.dao.EpgProgrammeDao;
import com.vopo.data.local.dao.EpgSourceDao;
import com.vopo.data.local.dao.EpisodeDao;
import com.vopo.data.local.dao.FavoriteDao;
import com.vopo.data.local.dao.MovieCategoryHydrationDao;
import com.vopo.data.local.dao.MovieDao;
import com.vopo.data.local.dao.PlaybackCompatibilityDao;
import com.vopo.data.local.dao.PlaybackHistoryDao;
import com.vopo.data.local.dao.ProgramDao;
import com.vopo.data.local.dao.ProgramReminderDao;
import com.vopo.data.local.dao.ProviderDao;
import com.vopo.data.local.dao.ProviderEpgSourceDao;
import com.vopo.data.local.dao.RecordingRunDao;
import com.vopo.data.local.dao.RecordingScheduleDao;
import com.vopo.data.local.dao.RecordingStorageDao;
import com.vopo.data.local.dao.SearchDao;
import com.vopo.data.local.dao.SearchHistoryDao;
import com.vopo.data.local.dao.SeriesCategoryHydrationDao;
import com.vopo.data.local.dao.SeriesDao;
import com.vopo.data.local.dao.SyncMetadataDao;
import com.vopo.data.local.dao.TmdbIdentityDao;
import com.vopo.data.local.dao.VirtualGroupDao;
import com.vopo.data.local.dao.XtreamContentIndexDao;
import com.vopo.data.local.dao.XtreamIndexJobDao;
import com.vopo.data.local.dao.XtreamLiveOnboardingDao;
import com.vopo.data.manager.BackupManagerImpl;
import com.vopo.data.manager.DownloadManagerImpl;
import com.vopo.data.manager.GoogleDriveBackupSyncManager;
import com.vopo.data.manager.ProgramReminderManagerImpl;
import com.vopo.data.manager.RecordingManagerImpl;
import com.vopo.data.manager.recording.HlsLiveCaptureEngine;
import com.vopo.data.manager.recording.RecordingAlarmScheduler;
import com.vopo.data.manager.recording.RecordingServiceLauncher;
import com.vopo.data.manager.recording.RecordingSourceResolver;
import com.vopo.data.manager.recording.TsPassThroughCaptureEngine;
import com.vopo.data.manager.reminder.ProgramReminderAlarmScheduler;
import com.vopo.data.manager.reminder.ProgramReminderNotifier;
import com.vopo.data.parser.M3uParser;
import com.vopo.data.parser.XmltvParser;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.data.remote.jellyfin.JellyfinImageAuthInterceptor;
import com.vopo.data.remote.jellyfin.JellyfinProvider;
import com.vopo.data.remote.stalker.StalkerApiService;
import com.vopo.data.remote.xtream.XtreamApiService;
import com.vopo.data.remote.xtream.XtreamStreamUrlResolver;
import com.vopo.data.repository.CategoryRepositoryImpl;
import com.vopo.data.repository.ChannelRepositoryImpl;
import com.vopo.data.repository.CombinedM3uRepositoryImpl;
import com.vopo.data.repository.EpgRepositoryImpl;
import com.vopo.data.repository.EpgSourceRepositoryImpl;
import com.vopo.data.repository.ExternalRatingsRepositoryImpl;
import com.vopo.data.repository.FavoriteRepositoryImpl;
import com.vopo.data.repository.LicenseRepositoryImpl;
import com.vopo.data.repository.MovieRepositoryImpl;
import com.vopo.data.repository.PlaybackCompatibilityRepositoryImpl;
import com.vopo.data.repository.PlaybackHistoryRepositoryImpl;
import com.vopo.data.repository.ProviderRepositoryImpl;
import com.vopo.data.repository.SearchRepositoryImpl;
import com.vopo.data.repository.SeriesRepositoryImpl;
import com.vopo.data.repository.SyncMetadataRepositoryImpl;
import com.vopo.data.security.AndroidKeystoreCredentialCrypto;
import com.vopo.data.sync.DatabaseMaintenanceManager;
import com.vopo.data.sync.ProviderSyncStateReaderImpl;
import com.vopo.data.sync.SyncManager;
import com.vopo.data.sync.SyncProgressBus;
import com.vopo.data.validation.ProviderSetupInputValidatorImpl;
import com.vopo.domain.manager.ParentalControlManager;
import com.vopo.domain.manager.RecordingManager;
import com.vopo.domain.repository.DownloadManager;
import com.vopo.domain.repository.SyncMetadataRepository;
import com.vopo.domain.usecase.GetContinueWatching;
import com.vopo.domain.usecase.GetCustomCategories;
import com.vopo.domain.usecase.ImportBackup;
import com.vopo.domain.usecase.MarkAsWatched;
import com.vopo.domain.usecase.ScheduleRecording;
import com.vopo.domain.usecase.SearchContent;
import com.vopo.domain.usecase.SyncProvider;
import com.vopo.domain.usecase.UnlockParentalCategory;
import com.vopo.domain.usecase.ValidateAndAddProvider;
import com.vopo.player.AudioCompatibilityMemoryStore;
import com.vopo.player.PlaybackSupportSnapshotStore;
import com.vopo.player.PlayerEngine;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideApplicationFactory;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.serialization.json.Json;
import okhttp3.OkHttpClient;

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
public final class DaggerVopoApp_HiltComponents_SingletonC {
  private DaggerVopoApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public VopoApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements VopoApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public VopoApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements VopoApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public VopoApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements VopoApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public VopoApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements VopoApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public VopoApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements VopoApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public VopoApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements VopoApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public VopoApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements VopoApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public VopoApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends VopoApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends VopoApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    FragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends VopoApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends VopoApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    ActivityCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity arg0) {
      injectMainActivity2(arg0);
    }

    @Override
    public void injectCastRouteChooserActivity(CastRouteChooserActivity arg0) {
      injectCastRouteChooserActivity2(arg0);
    }

    @Override
    public void injectExternalPlayerProbeActivity(ExternalPlayerProbeActivity arg0) {
      injectExternalPlayerProbeActivity2(arg0);
    }

    @Override
    public void injectTvInputSetupActivity(TvInputSetupActivity arg0) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>builderWithExpectedSize(18).put(DashboardViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, DashboardViewModel_HiltModules.KeyModule.provide()).put(DownloadsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, DownloadsViewModel_HiltModules.KeyModule.provide()).put(EpgViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, EpgViewModel_HiltModules.KeyModule.provide()).put(FavoritesViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, FavoritesViewModel_HiltModules.KeyModule.provide()).put(HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, HomeViewModel_HiltModules.KeyModule.provide()).put(MovieDetailViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, MovieDetailViewModel_HiltModules.KeyModule.provide()).put(MoviesViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, MoviesViewModel_HiltModules.KeyModule.provide()).put(MultiViewViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, MultiViewViewModel_HiltModules.KeyModule.provide()).put(ParentalControlGroupViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ParentalControlGroupViewModel_HiltModules.KeyModule.provide()).put(PlayerViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, PlayerViewModel_HiltModules.KeyModule.provide()).put(PluginsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, PluginsViewModel_HiltModules.KeyModule.provide()).put(ProviderSetupViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ProviderSetupViewModel_HiltModules.KeyModule.provide()).put(SearchViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SearchViewModel_HiltModules.KeyModule.provide()).put(SeriesDetailViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SeriesDetailViewModel_HiltModules.KeyModule.provide()).put(SeriesViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SeriesViewModel_HiltModules.KeyModule.provide()).put(SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, SettingsViewModel_HiltModules.KeyModule.provide()).put(TvInputSetupViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, TvInputSetupViewModel_HiltModules.KeyModule.provide()).put(WelcomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, WelcomeViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @CanIgnoreReturnValue
    private MainActivity injectMainActivity2(MainActivity instance) {
      MainActivity_MembersInjector.injectPreferencesRepository(instance, singletonCImpl.preferencesRepositoryProvider.get());
      MainActivity_MembersInjector.injectWatchNextManager(instance, singletonCImpl.watchNextManagerProvider.get());
      MainActivity_MembersInjector.injectLauncherRecommendationsManager(instance, singletonCImpl.launcherRecommendationsManagerProvider.get());
      MainActivity_MembersInjector.injectTvInputChannelSyncManager(instance, singletonCImpl.tvInputChannelSyncManagerProvider.get());
      MainActivity_MembersInjector.injectCastManager(instance, singletonCImpl.castManagerProvider.get());
      return instance;
    }

    @CanIgnoreReturnValue
    private CastRouteChooserActivity injectCastRouteChooserActivity2(
        CastRouteChooserActivity instance2) {
      CastRouteChooserActivity_MembersInjector.injectCastManager(instance2, singletonCImpl.castManagerProvider.get());
      return instance2;
    }

    @CanIgnoreReturnValue
    private ExternalPlayerProbeActivity injectExternalPlayerProbeActivity2(
        ExternalPlayerProbeActivity instance3) {
      ExternalPlayerProbeActivity_MembersInjector.injectChannelRepository(instance3, singletonCImpl.channelRepositoryImplProvider.get());
      return instance3;
    }
  }

  private static final class ViewModelCImpl extends VopoApp_HiltComponents.ViewModelC {
    private final SavedStateHandle savedStateHandle;

    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    Provider<DashboardViewModel> dashboardViewModelProvider;

    Provider<DownloadsViewModel> downloadsViewModelProvider;

    Provider<EpgViewModel> epgViewModelProvider;

    Provider<FavoritesViewModel> favoritesViewModelProvider;

    Provider<HomeViewModel> homeViewModelProvider;

    Provider<MovieDetailViewModel> movieDetailViewModelProvider;

    Provider<MoviesViewModel> moviesViewModelProvider;

    Provider<MultiViewViewModel> multiViewViewModelProvider;

    Provider<ParentalControlGroupViewModel> parentalControlGroupViewModelProvider;

    Provider<PlayerViewModel> playerViewModelProvider;

    Provider<PluginsViewModel> pluginsViewModelProvider;

    Provider<ProviderSetupViewModel> providerSetupViewModelProvider;

    Provider<SearchViewModel> searchViewModelProvider;

    Provider<SeriesDetailViewModel> seriesDetailViewModelProvider;

    Provider<SeriesViewModel> seriesViewModelProvider;

    Provider<SettingsViewModel> settingsViewModelProvider;

    Provider<TvInputSetupViewModel> tvInputSetupViewModelProvider;

    Provider<WelcomeViewModel> welcomeViewModelProvider;

    ViewModelCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        SavedStateHandle savedStateHandleParam, ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.savedStateHandle = savedStateHandleParam;
      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    GetContinueWatching getContinueWatching() {
      return new GetContinueWatching(singletonCImpl.playbackHistoryRepositoryImplProvider.get());
    }

    GetCustomCategories getCustomCategories() {
      return new GetCustomCategories(singletonCImpl.favoriteRepositoryImplProvider.get(), singletonCImpl.channelRepositoryImplProvider.get());
    }

    ScheduleRecording scheduleRecording() {
      return new ScheduleRecording(singletonCImpl.recordingManagerImplProvider.get());
    }

    UnlockParentalCategory unlockParentalCategory() {
      return new UnlockParentalCategory(singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.parentalControlManagerProvider.get());
    }

    MarkAsWatched markAsWatched() {
      return new MarkAsWatched(singletonCImpl.playbackHistoryRepositoryImplProvider.get());
    }

    ValidateAndAddProvider validateAndAddProvider() {
      return new ValidateAndAddProvider(singletonCImpl.providerSetupInputValidatorImplProvider.get(), singletonCImpl.providerRepositoryImplProvider.get());
    }

    ImportBackup importBackup() {
      return new ImportBackup(singletonCImpl.backupManagerImplProvider.get());
    }

    SearchContent searchContent() {
      return new SearchContent(singletonCImpl.searchRepositoryImplProvider.get(), singletonCImpl.providerSyncStateReaderImplProvider.get());
    }

    SyncProvider syncProvider() {
      return new SyncProvider(singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.providerSyncStateReaderImplProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.dashboardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.downloadsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.epgViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.favoritesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.movieDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.moviesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.multiViewViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.parentalControlGroupViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
      this.playerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 9);
      this.pluginsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 10);
      this.providerSetupViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 11);
      this.searchViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 12);
      this.seriesDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 13);
      this.seriesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 14);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 15);
      this.tvInputSetupViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 16);
      this.welcomeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 17);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(18).put(DashboardViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (dashboardViewModelProvider))).put(DownloadsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (downloadsViewModelProvider))).put(EpgViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (epgViewModelProvider))).put(FavoritesViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (favoritesViewModelProvider))).put(HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (homeViewModelProvider))).put(MovieDetailViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (movieDetailViewModelProvider))).put(MoviesViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (moviesViewModelProvider))).put(MultiViewViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (multiViewViewModelProvider))).put(ParentalControlGroupViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (parentalControlGroupViewModelProvider))).put(PlayerViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (playerViewModelProvider))).put(PluginsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (pluginsViewModelProvider))).put(ProviderSetupViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (providerSetupViewModelProvider))).put(SearchViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (searchViewModelProvider))).put(SeriesDetailViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (seriesDetailViewModelProvider))).put(SeriesViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (seriesViewModelProvider))).put(SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (settingsViewModelProvider))).put(TvInputSetupViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (tvInputSetupViewModelProvider))).put(WelcomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (welcomeViewModelProvider))).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.vopo.app.ui.screens.dashboard.DashboardViewModel
          return (T) new DashboardViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.combinedM3uRepositoryImplProvider.get(), singletonCImpl.favoriteRepositoryImplProvider.get(), singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.playbackHistoryRepositoryImplProvider.get(), singletonCImpl.movieRepositoryImplProvider.get(), singletonCImpl.seriesRepositoryImplProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), viewModelCImpl.getContinueWatching(), viewModelCImpl.getCustomCategories(), singletonCImpl.syncManagerProvider.get(), singletonCImpl.appUpdateInstallerProvider.get(), singletonCImpl.recordingManagerImplProvider.get());

          case 1: // com.vopo.app.ui.screens.downloads.DownloadsViewModel
          return (T) new DownloadsViewModel(singletonCImpl.downloadManagerImplProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.vopo.app.ui.screens.epg.EpgViewModel
          return (T) new EpgViewModel(singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.combinedM3uRepositoryImplProvider.get(), singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.epgRepositoryImplProvider.get(), singletonCImpl.epgSourceRepositoryImplProvider.get(), singletonCImpl.favoriteRepositoryImplProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.parentalControlManagerProvider.get(), singletonCImpl.programReminderManagerImplProvider.get(), viewModelCImpl.getCustomCategories(), viewModelCImpl.scheduleRecording(), singletonCImpl.recordingManagerImplProvider.get(), singletonCImpl.provideAuxiliaryPlayerEngineProvider, singletonCImpl.vopoPluginManagerProvider.get(), singletonCImpl.livePreviewHandoffManagerProvider.get(), ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule));

          case 3: // com.vopo.app.ui.screens.favorites.FavoritesViewModel
          return (T) new FavoritesViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.favoriteRepositoryImplProvider.get(), singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.movieRepositoryImplProvider.get(), singletonCImpl.seriesRepositoryImplProvider.get(), singletonCImpl.playbackHistoryRepositoryImplProvider.get(), singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), viewModelCImpl.getContinueWatching());

          case 4: // com.vopo.app.ui.screens.home.HomeViewModel
          return (T) new HomeViewModel(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule), singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.combinedM3uRepositoryImplProvider.get(), singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.categoryRepositoryImplProvider.get(), singletonCImpl.favoriteRepositoryImplProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.epgRepositoryImplProvider.get(), singletonCImpl.playbackHistoryRepositoryImplProvider.get(), viewModelCImpl.getCustomCategories(), viewModelCImpl.unlockParentalCategory(), singletonCImpl.parentalControlManagerProvider.get(), singletonCImpl.syncManagerProvider.get(), singletonCImpl.tvInputChannelSyncManagerProvider.get(), singletonCImpl.multiViewManagerProvider.get(), singletonCImpl.livePreviewHandoffManagerProvider.get(), singletonCImpl.vopoPluginManagerProvider.get(), singletonCImpl.provideAuxiliaryPlayerEngineProvider);

          case 5: // com.vopo.app.ui.screens.movies.MovieDetailViewModel
          return (T) new MovieDetailViewModel(viewModelCImpl.savedStateHandle, singletonCImpl.movieRepositoryImplProvider.get(), singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.playbackHistoryRepositoryImplProvider.get(), singletonCImpl.externalRatingsRepositoryImplProvider.get(), singletonCImpl.favoriteRepositoryImplProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.vopoPluginManagerProvider.get(), singletonCImpl.downloadManagerImplProvider.get());

          case 6: // com.vopo.app.ui.screens.movies.MoviesViewModel
          return (T) new MoviesViewModel(singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.movieRepositoryImplProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.playbackHistoryRepositoryImplProvider.get(), singletonCImpl.favoriteRepositoryImplProvider.get(), viewModelCImpl.getContinueWatching(), viewModelCImpl.getCustomCategories(), singletonCImpl.parentalControlManagerProvider.get());

          case 7: // com.vopo.app.ui.screens.multiview.MultiViewViewModel
          return (T) new MultiViewViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.multiViewManagerProvider.get(), singletonCImpl.provideAuxiliaryPlayerEngineProvider, singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.favoriteRepositoryImplProvider.get(), singletonCImpl.playbackHistoryRepositoryImplProvider.get(), singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.parentalControlManagerProvider.get(), viewModelCImpl.unlockParentalCategory());

          case 8: // com.vopo.app.ui.screens.settings.parental.ParentalControlGroupViewModel
          return (T) new ParentalControlGroupViewModel(singletonCImpl.categoryRepositoryImplProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), viewModelCImpl.savedStateHandle);

          case 9: // com.vopo.app.ui.screens.player.PlayerViewModel
          return (T) new PlayerViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideMainPlayerEngineProvider.get(), singletonCImpl.epgRepositoryImplProvider.get(), singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.movieRepositoryImplProvider.get(), singletonCImpl.seriesRepositoryImplProvider.get(), singletonCImpl.favoriteRepositoryImplProvider.get(), singletonCImpl.playbackHistoryRepositoryImplProvider.get(), singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.combinedM3uRepositoryImplProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), viewModelCImpl.getCustomCategories(), viewModelCImpl.markAsWatched(), viewModelCImpl.scheduleRecording(), singletonCImpl.recordingManagerImplProvider.get(), singletonCImpl.watchNextManagerProvider.get(), singletonCImpl.launcherRecommendationsManagerProvider.get(), singletonCImpl.castManagerProvider.get(), singletonCImpl.vopoPluginManagerProvider.get(), singletonCImpl.xtreamStreamUrlResolverProvider.get(), singletonCImpl.seekThumbnailProvider.get(), singletonCImpl.livePreviewHandoffManagerProvider.get(), singletonCImpl.syncManagerProvider.get(), singletonCImpl.downloadManagerImplProvider.get(), singletonCImpl.provideOkHttpClientProvider.get());

          case 10: // com.vopo.app.ui.screens.plugins.PluginsViewModel
          return (T) new PluginsViewModel(singletonCImpl.vopoPluginManagerProvider.get());

          case 11: // com.vopo.app.ui.screens.provider.ProviderSetupViewModel
          return (T) new ProviderSetupViewModel(singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.combinedM3uRepositoryImplProvider.get(), viewModelCImpl.validateAndAddProvider(), viewModelCImpl.importBackup(), singletonCImpl.googleDriveBackupSyncManagerProvider.get(), singletonCImpl.providerQrPairingManagerProvider.get());

          case 12: // com.vopo.app.ui.screens.search.SearchViewModel
          return (T) new SearchViewModel(singletonCImpl.providerRepositoryImplProvider.get(), viewModelCImpl.searchContent(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.parentalControlManagerProvider.get(), singletonCImpl.favoriteRepositoryImplProvider.get(), singletonCImpl.categoryRepositoryImplProvider.get(), singletonCImpl.recordingManagerImplProvider.get());

          case 13: // com.vopo.app.ui.screens.series.SeriesDetailViewModel
          return (T) new SeriesDetailViewModel(viewModelCImpl.savedStateHandle, singletonCImpl.seriesRepositoryImplProvider.get(), singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.playbackHistoryRepositoryImplProvider.get(), singletonCImpl.externalRatingsRepositoryImplProvider.get(), singletonCImpl.favoriteRepositoryImplProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.vopoPluginManagerProvider.get(), singletonCImpl.downloadManagerImplProvider.get());

          case 14: // com.vopo.app.ui.screens.series.SeriesViewModel
          return (T) new SeriesViewModel(singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.seriesRepositoryImplProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.playbackHistoryRepositoryImplProvider.get(), singletonCImpl.favoriteRepositoryImplProvider.get(), viewModelCImpl.getContinueWatching(), viewModelCImpl.getCustomCategories(), singletonCImpl.parentalControlManagerProvider.get());

          case 15: // com.vopo.app.ui.screens.settings.SettingsViewModel
          return (T) new SettingsViewModel(ApplicationContextModule_ProvideApplicationFactory.provideApplication(singletonCImpl.applicationContextModule), singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.combinedM3uRepositoryImplProvider.get(), singletonCImpl.categoryRepositoryImplProvider.get(), singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.movieRepositoryImplProvider.get(), singletonCImpl.seriesRepositoryImplProvider.get(), singletonCImpl.programDao(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.internetSpeedTestRunnerProvider.get(), singletonCImpl.backupManagerImplProvider.get(), singletonCImpl.googleDriveBackupSyncManagerProvider.get(), singletonCImpl.recordingManagerImplProvider.get(), singletonCImpl.parentalControlManagerProvider.get(), singletonCImpl.syncManagerProvider.get(), singletonCImpl.xtreamIndexJobDao(), singletonCImpl.xtreamLiveOnboardingDao(), singletonCImpl.syncMetadataRepositoryImplProvider.get(), singletonCImpl.playbackHistoryRepositoryImplProvider.get(), singletonCImpl.watchNextManagerProvider.get(), singletonCImpl.launcherRecommendationsManagerProvider.get(), singletonCImpl.tvInputChannelSyncManagerProvider.get(), viewModelCImpl.syncProvider(), singletonCImpl.epgSourceRepositoryImplProvider.get(), singletonCImpl.gitHubReleaseCheckerProvider.get(), singletonCImpl.appUpdateInstallerProvider.get(), viewModelCImpl.getCustomCategories(), singletonCImpl.audioCompatibilityMemoryStoreProvider.get());

          case 16: // com.vopo.app.tvinput.TvInputSetupViewModel
          return (T) new TvInputSetupViewModel(singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.tvInputChannelSyncManagerProvider.get());

          case 17: // com.vopo.app.ui.screens.welcome.WelcomeViewModel
          return (T) new WelcomeViewModel(singletonCImpl.providerRepositoryImplProvider.get(), viewModelCImpl.validateAndAddProvider(), singletonCImpl.licenseRepositoryImplProvider.get(), singletonCImpl.syncProgressBusProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends VopoApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends VopoApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }

    @Override
    public void injectVopoTvInputService(VopoTvInputService arg0) {
      injectVopoTvInputService2(arg0);
    }

    @CanIgnoreReturnValue
    private VopoTvInputService injectVopoTvInputService2(VopoTvInputService instance) {
      VopoTvInputService_MembersInjector.injectChannelRepository(instance, singletonCImpl.channelRepositoryImplProvider.get());
      VopoTvInputService_MembersInjector.injectOkHttpClient(instance, singletonCImpl.provideOkHttpClientProvider.get());
      return instance;
    }
  }

  private static final class SingletonCImpl extends VopoApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    Provider<VopoDatabase> provideDatabaseProvider;

    Provider<PreferencesRepository> preferencesRepositoryProvider;

    Provider<OkHttpClient> provideOkHttpClientProvider;

    Provider<GitHubReleaseChecker> gitHubReleaseCheckerProvider;

    Provider<AndroidKeystoreCredentialCrypto> androidKeystoreCredentialCryptoProvider;

    Provider<JellyfinImageAuthInterceptor> jellyfinImageAuthInterceptorProvider;

    Provider<Json> provideXtreamJsonProvider;

    Provider<StalkerApiService> provideStalkerApiServiceProvider;

    Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider;

    Provider<CoroutineScope> provideRepositoryCoroutineScopeProvider;

    Provider<DownloadManagerImpl> downloadManagerImplProvider;

    Provider<Gson> provideGsonProvider;

    Provider<RoomDatabaseTransactionRunner> roomDatabaseTransactionRunnerProvider;

    Provider<RecordingSourceResolver> recordingSourceResolverProvider;

    Provider<TsPassThroughCaptureEngine> tsPassThroughCaptureEngineProvider;

    Provider<HlsLiveCaptureEngine> hlsLiveCaptureEngineProvider;

    Provider<RecordingAlarmScheduler> recordingAlarmSchedulerProvider;

    Provider<RecordingServiceLauncher> recordingServiceLauncherProvider;

    Provider<RecordingManagerImpl> recordingManagerImplProvider;

    Provider<ProgramReminderAlarmScheduler> programReminderAlarmSchedulerProvider;

    Provider<ProgramReminderNotifier> programReminderNotifierProvider;

    Provider<ProgramReminderManagerImpl> programReminderManagerImplProvider;

    Provider<JellyfinProvider> provideJellyfinProvider;

    Provider<M3uParser> provideM3uParserProvider;

    Provider<XmltvParser> provideXmltvParserProvider;

    Provider<EpgResolutionEngine> epgResolutionEngineProvider;

    Provider<EpgSourceRepositoryImpl> epgSourceRepositoryImplProvider;

    Provider<EpgRepositoryImpl> epgRepositoryImplProvider;

    Provider<SyncMetadataRepositoryImpl> syncMetadataRepositoryImplProvider;

    Provider<SyncProgressBus> syncProgressBusProvider;

    Provider<SyncManager> syncManagerProvider;

    Provider<DatabaseMaintenanceManager> databaseMaintenanceManagerProvider;

    Provider<PlaybackHistoryRepositoryImpl> playbackHistoryRepositoryImplProvider;

    Provider<XtreamApiService> provideXtreamApiServiceProvider;

    Provider<ProviderRepositoryImpl> providerRepositoryImplProvider;

    Provider<WatchNextManager> watchNextManagerProvider;

    Provider<ParentalControlManager> parentalControlManagerProvider;

    Provider<ChannelRepositoryImpl> channelRepositoryImplProvider;

    Provider<CombinedM3uRepositoryImpl> combinedM3uRepositoryImplProvider;

    Provider<MovieRepositoryImpl> movieRepositoryImplProvider;

    Provider<SeriesRepositoryImpl> seriesRepositoryImplProvider;

    Provider<LauncherRecommendationsManager> launcherRecommendationsManagerProvider;

    Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider;

    Provider<PluginMessengerClient> pluginMessengerClientProvider;

    Provider<VopoPluginManager> vopoPluginManagerProvider;

    Provider<CastManager> castManagerProvider;

    Provider<FavoriteRepositoryImpl> favoriteRepositoryImplProvider;

    Provider<AppUpdateInstaller> appUpdateInstallerProvider;

    Provider<PlaybackCompatibilityRepositoryImpl> playbackCompatibilityRepositoryImplProvider;

    Provider<AudioCompatibilityMemoryStore> audioCompatibilityMemoryStoreProvider;

    Provider<PlaybackSupportSnapshotStore> playbackSupportSnapshotStoreProvider;

    Provider<PlayerEngine> provideAuxiliaryPlayerEngineProvider;

    Provider<LivePreviewHandoffManager> livePreviewHandoffManagerProvider;

    Provider<CategoryRepositoryImpl> categoryRepositoryImplProvider;

    Provider<MultiViewManager> multiViewManagerProvider;

    Provider<ExternalRatingsRepositoryImpl> externalRatingsRepositoryImplProvider;

    Provider<PlayerEngine> provideMainPlayerEngineProvider;

    Provider<SeekThumbnailProvider> seekThumbnailProvider;

    Provider<ProviderSetupInputValidatorImpl> providerSetupInputValidatorImplProvider;

    Provider<BackupManagerImpl> backupManagerImplProvider;

    Provider<GoogleDriveBackupSyncManager> googleDriveBackupSyncManagerProvider;

    Provider<ProviderQrPairingManager> providerQrPairingManagerProvider;

    Provider<SearchRepositoryImpl> searchRepositoryImplProvider;

    Provider<ProviderSyncStateReaderImpl> providerSyncStateReaderImplProvider;

    Provider<InternetSpeedTestRunner> internetSpeedTestRunnerProvider;

    Provider<FirebaseFirestore> provideFirebaseFirestoreProvider;

    Provider<LicenseRepositoryImpl> licenseRepositoryImplProvider;

    SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);
      initialize2(applicationContextModuleParam);
      initialize3(applicationContextModuleParam);

    }

    ChannelPreferenceDao channelPreferenceDao() {
      return DatabaseModule_ProvideChannelPreferenceDaoFactory.provideChannelPreferenceDao(provideDatabaseProvider.get());
    }

    SearchHistoryDao searchHistoryDao() {
      return DatabaseModule_ProvideSearchHistoryDaoFactory.provideSearchHistoryDao(provideDatabaseProvider.get());
    }

    DownloadDao downloadDao() {
      return DatabaseModule_ProvideDownloadDaoFactory.provideDownloadDao(provideDatabaseProvider.get());
    }

    RecordingScheduleDao recordingScheduleDao() {
      return DatabaseModule_ProvideRecordingScheduleDaoFactory.provideRecordingScheduleDao(provideDatabaseProvider.get());
    }

    RecordingRunDao recordingRunDao() {
      return DatabaseModule_ProvideRecordingRunDaoFactory.provideRecordingRunDao(provideDatabaseProvider.get());
    }

    RecordingStorageDao recordingStorageDao() {
      return DatabaseModule_ProvideRecordingStorageDaoFactory.provideRecordingStorageDao(provideDatabaseProvider.get());
    }

    ProgramReminderDao programReminderDao() {
      return DatabaseModule_ProvideProgramReminderDaoFactory.provideProgramReminderDao(provideDatabaseProvider.get());
    }

    MovieDao movieDao() {
      return DatabaseModule_ProvideMovieDaoFactory.provideMovieDao(provideDatabaseProvider.get());
    }

    SeriesDao seriesDao() {
      return DatabaseModule_ProvideSeriesDaoFactory.provideSeriesDao(provideDatabaseProvider.get());
    }

    ProgramDao programDao() {
      return DatabaseModule_ProvideProgramDaoFactory.provideProgramDao(provideDatabaseProvider.get());
    }

    MovieCategoryHydrationDao movieCategoryHydrationDao() {
      return DatabaseModule_ProvideMovieCategoryHydrationDaoFactory.provideMovieCategoryHydrationDao(provideDatabaseProvider.get());
    }

    SeriesCategoryHydrationDao seriesCategoryHydrationDao() {
      return DatabaseModule_ProvideSeriesCategoryHydrationDaoFactory.provideSeriesCategoryHydrationDao(provideDatabaseProvider.get());
    }

    CatalogSyncDao catalogSyncDao() {
      return DatabaseModule_ProvideCatalogSyncDaoFactory.provideCatalogSyncDao(provideDatabaseProvider.get());
    }

    TmdbIdentityDao tmdbIdentityDao() {
      return DatabaseModule_ProvideTmdbIdentityDaoFactory.provideTmdbIdentityDao(provideDatabaseProvider.get());
    }

    XtreamContentIndexDao xtreamContentIndexDao() {
      return DatabaseModule_ProvideXtreamContentIndexDaoFactory.provideXtreamContentIndexDao(provideDatabaseProvider.get());
    }

    EpisodeDao episodeDao() {
      return DatabaseModule_ProvideEpisodeDaoFactory.provideEpisodeDao(provideDatabaseProvider.get());
    }

    EpgSourceDao epgSourceDao() {
      return DatabaseModule_ProvideEpgSourceDaoFactory.provideEpgSourceDao(provideDatabaseProvider.get());
    }

    ProviderEpgSourceDao providerEpgSourceDao() {
      return DatabaseModule_ProvideProviderEpgSourceDaoFactory.provideProviderEpgSourceDao(provideDatabaseProvider.get());
    }

    ChannelEpgMappingDao channelEpgMappingDao() {
      return DatabaseModule_ProvideChannelEpgMappingDaoFactory.provideChannelEpgMappingDao(provideDatabaseProvider.get());
    }

    EpgChannelDao epgChannelDao() {
      return DatabaseModule_ProvideEpgChannelDaoFactory.provideEpgChannelDao(provideDatabaseProvider.get());
    }

    EpgProgrammeDao epgProgrammeDao() {
      return DatabaseModule_ProvideEpgProgrammeDaoFactory.provideEpgProgrammeDao(provideDatabaseProvider.get());
    }

    SyncMetadataDao syncMetadataDao() {
      return DatabaseModule_ProvideSyncMetadataDaoFactory.provideSyncMetadataDao(provideDatabaseProvider.get());
    }

    FavoriteDao favoriteDao() {
      return DatabaseModule_ProvideFavoriteDaoFactory.provideFavoriteDao(provideDatabaseProvider.get());
    }

    PlaybackHistoryDao playbackHistoryDao() {
      return DatabaseModule_ProvidePlaybackHistoryDaoFactory.providePlaybackHistoryDao(provideDatabaseProvider.get());
    }

    CombinedM3uProfileDao combinedM3uProfileDao() {
      return DatabaseModule_ProvideCombinedM3uProfileDaoFactory.provideCombinedM3uProfileDao(provideDatabaseProvider.get());
    }

    CombinedM3uProfileMemberDao combinedM3uProfileMemberDao() {
      return DatabaseModule_ProvideCombinedM3uProfileMemberDaoFactory.provideCombinedM3uProfileMemberDao(provideDatabaseProvider.get());
    }

    VirtualGroupDao virtualGroupDao() {
      return DatabaseModule_ProvideVirtualGroupDaoFactory.provideVirtualGroupDao(provideDatabaseProvider.get());
    }

    PlaybackCompatibilityDao playbackCompatibilityDao() {
      return DatabaseModule_ProvidePlaybackCompatibilityDaoFactory.providePlaybackCompatibilityDao(provideDatabaseProvider.get());
    }

    ValidateAndAddProvider validateAndAddProvider() {
      return new ValidateAndAddProvider(providerSetupInputValidatorImplProvider.get(), providerRepositoryImplProvider.get());
    }

    SearchDao searchDao() {
      return DatabaseModule_ProvideSearchDaoFactory.provideSearchDao(provideDatabaseProvider.get());
    }

    InternetDownloadSpeedProbe internetDownloadSpeedProbe() {
      return new InternetDownloadSpeedProbe(provideOkHttpClientProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<VopoDatabase>(singletonCImpl, 1));
      this.preferencesRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<PreferencesRepository>(singletonCImpl, 0));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 3));
      this.gitHubReleaseCheckerProvider = DoubleCheck.provider(new SwitchingProvider<GitHubReleaseChecker>(singletonCImpl, 2));
      this.androidKeystoreCredentialCryptoProvider = DoubleCheck.provider(new SwitchingProvider<AndroidKeystoreCredentialCrypto>(singletonCImpl, 5));
      this.jellyfinImageAuthInterceptorProvider = DoubleCheck.provider(new SwitchingProvider<JellyfinImageAuthInterceptor>(singletonCImpl, 4));
      this.provideXtreamJsonProvider = DoubleCheck.provider(new SwitchingProvider<Json>(singletonCImpl, 9));
      this.provideStalkerApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<StalkerApiService>(singletonCImpl, 8));
      this.xtreamStreamUrlResolverProvider = DoubleCheck.provider(new SwitchingProvider<XtreamStreamUrlResolver>(singletonCImpl, 7));
      this.provideRepositoryCoroutineScopeProvider = DoubleCheck.provider(new SwitchingProvider<CoroutineScope>(singletonCImpl, 10));
      this.downloadManagerImplProvider = DoubleCheck.provider(new SwitchingProvider<DownloadManagerImpl>(singletonCImpl, 6));
      this.provideGsonProvider = DoubleCheck.provider(new SwitchingProvider<Gson>(singletonCImpl, 12));
      this.roomDatabaseTransactionRunnerProvider = DoubleCheck.provider(new SwitchingProvider<RoomDatabaseTransactionRunner>(singletonCImpl, 13));
      this.recordingSourceResolverProvider = DoubleCheck.provider(new SwitchingProvider<RecordingSourceResolver>(singletonCImpl, 14));
      this.tsPassThroughCaptureEngineProvider = DoubleCheck.provider(new SwitchingProvider<TsPassThroughCaptureEngine>(singletonCImpl, 15));
      this.hlsLiveCaptureEngineProvider = DoubleCheck.provider(new SwitchingProvider<HlsLiveCaptureEngine>(singletonCImpl, 16));
      this.recordingAlarmSchedulerProvider = DoubleCheck.provider(new SwitchingProvider<RecordingAlarmScheduler>(singletonCImpl, 17));
      this.recordingServiceLauncherProvider = DoubleCheck.provider(new SwitchingProvider<RecordingServiceLauncher>(singletonCImpl, 18));
      this.recordingManagerImplProvider = DoubleCheck.provider(new SwitchingProvider<RecordingManagerImpl>(singletonCImpl, 11));
      this.programReminderAlarmSchedulerProvider = DoubleCheck.provider(new SwitchingProvider<ProgramReminderAlarmScheduler>(singletonCImpl, 20));
      this.programReminderNotifierProvider = DoubleCheck.provider(new SwitchingProvider<ProgramReminderNotifier>(singletonCImpl, 21));
      this.programReminderManagerImplProvider = DoubleCheck.provider(new SwitchingProvider<ProgramReminderManagerImpl>(singletonCImpl, 19));
      this.provideJellyfinProvider = DoubleCheck.provider(new SwitchingProvider<JellyfinProvider>(singletonCImpl, 23));
      this.provideM3uParserProvider = DoubleCheck.provider(new SwitchingProvider<M3uParser>(singletonCImpl, 24));
      this.provideXmltvParserProvider = DoubleCheck.provider(new SwitchingProvider<XmltvParser>(singletonCImpl, 26));
    }

    @SuppressWarnings("unchecked")
    private void initialize2(final ApplicationContextModule applicationContextModuleParam) {
      this.epgResolutionEngineProvider = DoubleCheck.provider(new SwitchingProvider<EpgResolutionEngine>(singletonCImpl, 28));
      this.epgSourceRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<EpgSourceRepositoryImpl>(singletonCImpl, 27));
      this.epgRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<EpgRepositoryImpl>(singletonCImpl, 25));
      this.syncMetadataRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<SyncMetadataRepositoryImpl>(singletonCImpl, 29));
      this.syncProgressBusProvider = DoubleCheck.provider(new SwitchingProvider<SyncProgressBus>(singletonCImpl, 30));
      this.syncManagerProvider = DoubleCheck.provider(new SwitchingProvider<SyncManager>(singletonCImpl, 22));
      this.databaseMaintenanceManagerProvider = DoubleCheck.provider(new SwitchingProvider<DatabaseMaintenanceManager>(singletonCImpl, 31));
      this.playbackHistoryRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<PlaybackHistoryRepositoryImpl>(singletonCImpl, 33));
      this.provideXtreamApiServiceProvider = DoubleCheck.provider(new SwitchingProvider<XtreamApiService>(singletonCImpl, 35));
      this.providerRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<ProviderRepositoryImpl>(singletonCImpl, 34));
      this.watchNextManagerProvider = DoubleCheck.provider(new SwitchingProvider<WatchNextManager>(singletonCImpl, 32));
      this.parentalControlManagerProvider = DoubleCheck.provider(new SwitchingProvider<ParentalControlManager>(singletonCImpl, 39));
      this.channelRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<ChannelRepositoryImpl>(singletonCImpl, 38));
      this.combinedM3uRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<CombinedM3uRepositoryImpl>(singletonCImpl, 37));
      this.movieRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<MovieRepositoryImpl>(singletonCImpl, 40));
      this.seriesRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<SeriesRepositoryImpl>(singletonCImpl, 41));
      this.launcherRecommendationsManagerProvider = DoubleCheck.provider(new SwitchingProvider<LauncherRecommendationsManager>(singletonCImpl, 36));
      this.tvInputChannelSyncManagerProvider = DoubleCheck.provider(new SwitchingProvider<TvInputChannelSyncManager>(singletonCImpl, 42));
      this.pluginMessengerClientProvider = DoubleCheck.provider(new SwitchingProvider<PluginMessengerClient>(singletonCImpl, 45));
      this.vopoPluginManagerProvider = DoubleCheck.provider(new SwitchingProvider<VopoPluginManager>(singletonCImpl, 44));
      this.castManagerProvider = DoubleCheck.provider(new SwitchingProvider<CastManager>(singletonCImpl, 43));
      this.favoriteRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<FavoriteRepositoryImpl>(singletonCImpl, 46));
      this.appUpdateInstallerProvider = DoubleCheck.provider(new SwitchingProvider<AppUpdateInstaller>(singletonCImpl, 47));
      this.playbackCompatibilityRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<PlaybackCompatibilityRepositoryImpl>(singletonCImpl, 49));
      this.audioCompatibilityMemoryStoreProvider = DoubleCheck.provider(new SwitchingProvider<AudioCompatibilityMemoryStore>(singletonCImpl, 50));
    }

    @SuppressWarnings("unchecked")
    private void initialize3(final ApplicationContextModule applicationContextModuleParam) {
      this.playbackSupportSnapshotStoreProvider = DoubleCheck.provider(new SwitchingProvider<PlaybackSupportSnapshotStore>(singletonCImpl, 51));
      this.provideAuxiliaryPlayerEngineProvider = new SwitchingProvider<>(singletonCImpl, 48);
      this.livePreviewHandoffManagerProvider = DoubleCheck.provider(new SwitchingProvider<LivePreviewHandoffManager>(singletonCImpl, 52));
      this.categoryRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<CategoryRepositoryImpl>(singletonCImpl, 53));
      this.multiViewManagerProvider = DoubleCheck.provider(new SwitchingProvider<MultiViewManager>(singletonCImpl, 54));
      this.externalRatingsRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<ExternalRatingsRepositoryImpl>(singletonCImpl, 55));
      this.provideMainPlayerEngineProvider = DoubleCheck.provider(new SwitchingProvider<PlayerEngine>(singletonCImpl, 56));
      this.seekThumbnailProvider = DoubleCheck.provider(new SwitchingProvider<SeekThumbnailProvider>(singletonCImpl, 57));
      this.providerSetupInputValidatorImplProvider = DoubleCheck.provider(new SwitchingProvider<ProviderSetupInputValidatorImpl>(singletonCImpl, 58));
      this.backupManagerImplProvider = DoubleCheck.provider(new SwitchingProvider<BackupManagerImpl>(singletonCImpl, 59));
      this.googleDriveBackupSyncManagerProvider = DoubleCheck.provider(new SwitchingProvider<GoogleDriveBackupSyncManager>(singletonCImpl, 60));
      this.providerQrPairingManagerProvider = DoubleCheck.provider(new SwitchingProvider<ProviderQrPairingManager>(singletonCImpl, 61));
      this.searchRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<SearchRepositoryImpl>(singletonCImpl, 62));
      this.providerSyncStateReaderImplProvider = DoubleCheck.provider(new SwitchingProvider<ProviderSyncStateReaderImpl>(singletonCImpl, 63));
      this.internetSpeedTestRunnerProvider = DoubleCheck.provider(new SwitchingProvider<InternetSpeedTestRunner>(singletonCImpl, 64));
      this.provideFirebaseFirestoreProvider = DoubleCheck.provider(new SwitchingProvider<FirebaseFirestore>(singletonCImpl, 66));
      this.licenseRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<LicenseRepositoryImpl>(singletonCImpl, 65));
    }

    @Override
    public void injectVopoApp(VopoApp arg0) {
      injectVopoApp2(arg0);
    }

    @Override
    public DownloadManager downloadManager() {
      return downloadManagerImplProvider.get();
    }

    @Override
    public RecordingManager recordingManager() {
      return recordingManagerImplProvider.get();
    }

    @Override
    public ProgramReminderManagerImpl reminderManager() {
      return programReminderManagerImplProvider.get();
    }

    @Override
    public SyncManager syncManager() {
      return syncManagerProvider.get();
    }

    @Override
    public ProviderDao providerDao() {
      return DatabaseModule_ProvideProviderDaoFactory.provideProviderDao(provideDatabaseProvider.get());
    }

    @Override
    public ChannelDao channelDao() {
      return DatabaseModule_ProvideChannelDaoFactory.provideChannelDao(provideDatabaseProvider.get());
    }

    @Override
    public CategoryDao categoryDao() {
      return DatabaseModule_ProvideCategoryDaoFactory.provideCategoryDao(provideDatabaseProvider.get());
    }

    @Override
    public SyncMetadataRepository syncMetadataRepository() {
      return syncMetadataRepositoryImplProvider.get();
    }

    @Override
    public XtreamIndexJobDao xtreamIndexJobDao() {
      return DatabaseModule_ProvideXtreamIndexJobDaoFactory.provideXtreamIndexJobDao(provideDatabaseProvider.get());
    }

    @Override
    public XtreamLiveOnboardingDao xtreamLiveOnboardingDao() {
      return DatabaseModule_ProvideXtreamLiveOnboardingDaoFactory.provideXtreamLiveOnboardingDao(provideDatabaseProvider.get());
    }

    @Override
    public DatabaseMaintenanceManager databaseMaintenanceManager() {
      return databaseMaintenanceManagerProvider.get();
    }

    @Override
    public PreferencesRepository preferencesRepository() {
      return preferencesRepositoryProvider.get();
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @CanIgnoreReturnValue
    private VopoApp injectVopoApp2(VopoApp instance) {
      VopoApp_MembersInjector.injectPreferencesRepository(instance, preferencesRepositoryProvider.get());
      VopoApp_MembersInjector.injectGitHubReleaseChecker(instance, gitHubReleaseCheckerProvider.get());
      VopoApp_MembersInjector.injectOkHttpClient(instance, provideOkHttpClientProvider.get());
      VopoApp_MembersInjector.injectJellyfinImageAuthInterceptor(instance, jellyfinImageAuthInterceptorProvider.get());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.vopo.data.preferences.PreferencesRepository
          return (T) new PreferencesRepository(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.channelPreferenceDao(), singletonCImpl.searchHistoryDao());

          case 1: // com.vopo.data.local.VopoDatabase
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 2: // com.vopo.app.update.GitHubReleaseChecker
          return (T) new GitHubReleaseChecker(singletonCImpl.provideOkHttpClientProvider.get());

          case 3: // okhttp3.OkHttpClient
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // com.vopo.data.remote.jellyfin.JellyfinImageAuthInterceptor
          return (T) new JellyfinImageAuthInterceptor(singletonCImpl.providerDao(), singletonCImpl.androidKeystoreCredentialCryptoProvider.get());

          case 5: // com.vopo.data.security.AndroidKeystoreCredentialCrypto
          return (T) new AndroidKeystoreCredentialCrypto();

          case 6: // com.vopo.data.manager.DownloadManagerImpl
          return (T) new DownloadManagerImpl(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.downloadDao(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.xtreamStreamUrlResolverProvider.get(), singletonCImpl.provideRepositoryCoroutineScopeProvider.get());

          case 7: // com.vopo.data.remote.xtream.XtreamStreamUrlResolver
          return (T) new XtreamStreamUrlResolver(singletonCImpl.providerDao(), singletonCImpl.androidKeystoreCredentialCryptoProvider.get(), singletonCImpl.provideStalkerApiServiceProvider.get(), singletonCImpl.preferencesRepositoryProvider.get());

          case 8: // com.vopo.data.remote.stalker.StalkerApiService
          return (T) NetworkModule_ProvideStalkerApiServiceFactory.provideStalkerApiService(singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.provideXtreamJsonProvider.get());

          case 9: // kotlinx.serialization.json.Json
          return (T) NetworkModule_ProvideXtreamJsonFactory.provideXtreamJson();

          case 10: // kotlinx.coroutines.CoroutineScope
          return (T) RepositoryModule_Companion_ProvideRepositoryCoroutineScopeFactory.provideRepositoryCoroutineScope();

          case 11: // com.vopo.data.manager.RecordingManagerImpl
          return (T) new RecordingManagerImpl(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideGsonProvider.get(), singletonCImpl.roomDatabaseTransactionRunnerProvider.get(), singletonCImpl.providerDao(), singletonCImpl.recordingScheduleDao(), singletonCImpl.recordingRunDao(), singletonCImpl.recordingStorageDao(), singletonCImpl.recordingSourceResolverProvider.get(), singletonCImpl.tsPassThroughCaptureEngineProvider.get(), singletonCImpl.hlsLiveCaptureEngineProvider.get(), singletonCImpl.recordingAlarmSchedulerProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.recordingServiceLauncherProvider.get());

          case 12: // com.google.gson.Gson
          return (T) NetworkModule_ProvideGsonFactory.provideGson();

          case 13: // com.vopo.data.local.RoomDatabaseTransactionRunner
          return (T) new RoomDatabaseTransactionRunner(singletonCImpl.provideDatabaseProvider.get());

          case 14: // com.vopo.data.manager.recording.RecordingSourceResolver
          return (T) new RecordingSourceResolver(singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.providerDao(), singletonCImpl.xtreamStreamUrlResolverProvider.get());

          case 15: // com.vopo.data.manager.recording.TsPassThroughCaptureEngine
          return (T) new TsPassThroughCaptureEngine(singletonCImpl.provideOkHttpClientProvider.get());

          case 16: // com.vopo.data.manager.recording.HlsLiveCaptureEngine
          return (T) new HlsLiveCaptureEngine(singletonCImpl.provideOkHttpClientProvider.get());

          case 17: // com.vopo.data.manager.recording.RecordingAlarmScheduler
          return (T) new RecordingAlarmScheduler(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 18: // com.vopo.data.manager.recording.RecordingServiceLauncher
          return (T) new RecordingServiceLauncher();

          case 19: // com.vopo.data.manager.ProgramReminderManagerImpl
          return (T) new ProgramReminderManagerImpl(singletonCImpl.programReminderDao(), singletonCImpl.programReminderAlarmSchedulerProvider.get(), singletonCImpl.programReminderNotifierProvider.get());

          case 20: // com.vopo.data.manager.reminder.ProgramReminderAlarmScheduler
          return (T) new ProgramReminderAlarmScheduler(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 21: // com.vopo.data.manager.reminder.ProgramReminderNotifier
          return (T) new ProgramReminderNotifier(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 22: // com.vopo.data.sync.SyncManager
          return (T) new SyncManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.providerDao(), singletonCImpl.channelDao(), singletonCImpl.movieDao(), singletonCImpl.seriesDao(), singletonCImpl.programDao(), singletonCImpl.categoryDao(), singletonCImpl.movieCategoryHydrationDao(), singletonCImpl.seriesCategoryHydrationDao(), singletonCImpl.catalogSyncDao(), singletonCImpl.tmdbIdentityDao(), singletonCImpl.xtreamContentIndexDao(), singletonCImpl.xtreamIndexJobDao(), singletonCImpl.xtreamLiveOnboardingDao(), singletonCImpl.provideStalkerApiServiceProvider.get(), singletonCImpl.episodeDao(), singletonCImpl.provideJellyfinProvider.get(), singletonCImpl.provideXtreamJsonProvider.get(), singletonCImpl.provideM3uParserProvider.get(), singletonCImpl.epgRepositoryImplProvider.get(), singletonCImpl.epgSourceRepositoryImplProvider.get(), singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.androidKeystoreCredentialCryptoProvider.get(), singletonCImpl.syncMetadataRepositoryImplProvider.get(), singletonCImpl.roomDatabaseTransactionRunnerProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.syncProgressBusProvider.get());

          case 23: // com.vopo.data.remote.jellyfin.JellyfinProvider
          return (T) DatabaseModule_ProvideJellyfinProviderFactory.provideJellyfinProvider(singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.provideGsonProvider.get());

          case 24: // com.vopo.data.parser.M3uParser
          return (T) RepositoryModule_Companion_ProvideM3uParserFactory.provideM3uParser();

          case 25: // com.vopo.data.repository.EpgRepositoryImpl
          return (T) new EpgRepositoryImpl(singletonCImpl.programDao(), singletonCImpl.providerDao(), singletonCImpl.provideXmltvParserProvider.get(), singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.roomDatabaseTransactionRunnerProvider.get(), singletonCImpl.epgSourceRepositoryImplProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.provideRepositoryCoroutineScopeProvider.get());

          case 26: // com.vopo.data.parser.XmltvParser
          return (T) NetworkModule_ProvideXmltvParserFactory.provideXmltvParser();

          case 27: // com.vopo.data.repository.EpgSourceRepositoryImpl
          return (T) new EpgSourceRepositoryImpl(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.epgSourceDao(), singletonCImpl.providerEpgSourceDao(), singletonCImpl.providerDao(), singletonCImpl.channelEpgMappingDao(), singletonCImpl.epgChannelDao(), singletonCImpl.epgProgrammeDao(), singletonCImpl.provideXmltvParserProvider.get(), singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.epgResolutionEngineProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.roomDatabaseTransactionRunnerProvider.get());

          case 28: // com.vopo.data.epg.EpgResolutionEngine
          return (T) new EpgResolutionEngine(singletonCImpl.channelDao(), singletonCImpl.channelEpgMappingDao(), singletonCImpl.providerEpgSourceDao(), singletonCImpl.epgChannelDao(), singletonCImpl.epgProgrammeDao(), singletonCImpl.programDao());

          case 29: // com.vopo.data.repository.SyncMetadataRepositoryImpl
          return (T) new SyncMetadataRepositoryImpl(singletonCImpl.syncMetadataDao());

          case 30: // com.vopo.data.sync.SyncProgressBus
          return (T) new SyncProgressBus();

          case 31: // com.vopo.data.sync.DatabaseMaintenanceManager
          return (T) new DatabaseMaintenanceManager(singletonCImpl.provideDatabaseProvider.get(), singletonCImpl.channelDao(), singletonCImpl.programDao(), singletonCImpl.epgProgrammeDao(), singletonCImpl.episodeDao(), singletonCImpl.favoriteDao(), singletonCImpl.programReminderDao(), singletonCImpl.searchHistoryDao(), singletonCImpl.syncManagerProvider.get());

          case 32: // com.vopo.app.tv.WatchNextManager
          return (T) new WatchNextManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.playbackHistoryRepositoryImplProvider.get(), singletonCImpl.providerRepositoryImplProvider.get());

          case 33: // com.vopo.data.repository.PlaybackHistoryRepositoryImpl
          return (T) new PlaybackHistoryRepositoryImpl(singletonCImpl.playbackHistoryDao(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.movieDao(), singletonCImpl.episodeDao(), singletonCImpl.roomDatabaseTransactionRunnerProvider.get());

          case 34: // com.vopo.data.repository.ProviderRepositoryImpl
          return (T) new ProviderRepositoryImpl(singletonCImpl.providerDao(), singletonCImpl.categoryDao(), singletonCImpl.channelDao(), singletonCImpl.programDao(), singletonCImpl.recordingRunDao(), singletonCImpl.programReminderDao(), singletonCImpl.provideStalkerApiServiceProvider.get(), singletonCImpl.provideXtreamApiServiceProvider.get(), singletonCImpl.androidKeystoreCredentialCryptoProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.syncManagerProvider.get(), singletonCImpl.syncMetadataRepositoryImplProvider.get(), singletonCImpl.roomDatabaseTransactionRunnerProvider.get(), singletonCImpl.recordingAlarmSchedulerProvider.get(), singletonCImpl.programReminderAlarmSchedulerProvider.get(), singletonCImpl.provideJellyfinProvider.get());

          case 35: // com.vopo.data.remote.xtream.XtreamApiService
          return (T) NetworkModule_ProvideXtreamApiServiceFactory.provideXtreamApiService(singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.provideXtreamJsonProvider.get());

          case 36: // com.vopo.app.tv.LauncherRecommendationsManager
          return (T) new LauncherRecommendationsManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.combinedM3uRepositoryImplProvider.get(), singletonCImpl.playbackHistoryRepositoryImplProvider.get(), singletonCImpl.movieRepositoryImplProvider.get(), singletonCImpl.seriesRepositoryImplProvider.get());

          case 37: // com.vopo.data.repository.CombinedM3uRepositoryImpl
          return (T) new CombinedM3uRepositoryImpl(singletonCImpl.combinedM3uProfileDao(), singletonCImpl.combinedM3uProfileMemberDao(), singletonCImpl.providerDao(), singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.preferencesRepositoryProvider.get());

          case 38: // com.vopo.data.repository.ChannelRepositoryImpl
          return (T) new ChannelRepositoryImpl(singletonCImpl.channelDao(), singletonCImpl.categoryDao(), singletonCImpl.favoriteDao(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.parentalControlManagerProvider.get(), singletonCImpl.xtreamStreamUrlResolverProvider.get());

          case 39: // com.vopo.domain.manager.ParentalControlManager
          return (T) new ParentalControlManager(singletonCImpl.preferencesRepositoryProvider.get());

          case 40: // com.vopo.data.repository.MovieRepositoryImpl
          return (T) new MovieRepositoryImpl(singletonCImpl.movieDao(), singletonCImpl.categoryDao(), singletonCImpl.providerDao(), singletonCImpl.provideStalkerApiServiceProvider.get(), singletonCImpl.provideXtreamApiServiceProvider.get(), singletonCImpl.androidKeystoreCredentialCryptoProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.favoriteDao(), singletonCImpl.playbackHistoryDao(), singletonCImpl.playbackHistoryRepositoryImplProvider.get(), singletonCImpl.xtreamStreamUrlResolverProvider.get(), singletonCImpl.movieCategoryHydrationDao(), singletonCImpl.syncMetadataRepositoryImplProvider.get(), singletonCImpl.xtreamContentIndexDao(), singletonCImpl.xtreamIndexJobDao(), singletonCImpl.syncManagerProvider.get(), singletonCImpl.roomDatabaseTransactionRunnerProvider.get());

          case 41: // com.vopo.data.repository.SeriesRepositoryImpl
          return (T) new SeriesRepositoryImpl(singletonCImpl.seriesDao(), singletonCImpl.episodeDao(), singletonCImpl.categoryDao(), singletonCImpl.favoriteDao(), singletonCImpl.playbackHistoryDao(), singletonCImpl.playbackHistoryRepositoryImplProvider.get(), singletonCImpl.providerDao(), singletonCImpl.provideStalkerApiServiceProvider.get(), singletonCImpl.provideXtreamApiServiceProvider.get(), singletonCImpl.androidKeystoreCredentialCryptoProvider.get(), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.xtreamStreamUrlResolverProvider.get(), singletonCImpl.xtreamContentIndexDao(), singletonCImpl.xtreamIndexJobDao(), singletonCImpl.syncManagerProvider.get(), singletonCImpl.seriesCategoryHydrationDao(), singletonCImpl.provideJellyfinProvider.get());

          case 42: // com.vopo.app.tvinput.TvInputChannelSyncManager
          return (T) new TvInputChannelSyncManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.epgRepositoryImplProvider.get());

          case 43: // com.vopo.app.cast.CastManager
          return (T) new CastManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.vopoPluginManagerProvider.get());

          case 44: // com.vopo.app.plugins.VopoPluginManager
          return (T) new VopoPluginManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.pluginMessengerClientProvider.get(), singletonCImpl.providerRepositoryImplProvider.get(), singletonCImpl.combinedM3uRepositoryImplProvider.get(), singletonCImpl.tvInputChannelSyncManagerProvider.get(), singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.provideXtreamJsonProvider.get());

          case 45: // com.vopo.app.plugins.PluginMessengerClient
          return (T) new PluginMessengerClient(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 46: // com.vopo.data.repository.FavoriteRepositoryImpl
          return (T) new FavoriteRepositoryImpl(singletonCImpl.favoriteDao(), singletonCImpl.virtualGroupDao(), singletonCImpl.roomDatabaseTransactionRunnerProvider.get());

          case 47: // com.vopo.app.update.AppUpdateInstaller
          return (T) new AppUpdateInstaller(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.preferencesRepositoryProvider.get());

          case 48: // @com.vopo.app.di.AuxiliaryPlayerEngine com.vopo.player.PlayerEngine
          return (T) NetworkModule_ProvideAuxiliaryPlayerEngineFactory.provideAuxiliaryPlayerEngine(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.playbackCompatibilityRepositoryImplProvider.get(), singletonCImpl.audioCompatibilityMemoryStoreProvider.get(), singletonCImpl.playbackSupportSnapshotStoreProvider.get());

          case 49: // com.vopo.data.repository.PlaybackCompatibilityRepositoryImpl
          return (T) new PlaybackCompatibilityRepositoryImpl(singletonCImpl.playbackCompatibilityDao());

          case 50: // com.vopo.player.AudioCompatibilityMemoryStore
          return (T) new AudioCompatibilityMemoryStore(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 51: // com.vopo.player.PlaybackSupportSnapshotStore
          return (T) new PlaybackSupportSnapshotStore(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 52: // com.vopo.app.player.LivePreviewHandoffManager
          return (T) new LivePreviewHandoffManager();

          case 53: // com.vopo.data.repository.CategoryRepositoryImpl
          return (T) new CategoryRepositoryImpl(singletonCImpl.categoryDao(), singletonCImpl.channelDao(), singletonCImpl.movieDao(), singletonCImpl.seriesDao(), singletonCImpl.roomDatabaseTransactionRunnerProvider.get());

          case 54: // com.vopo.app.ui.screens.multiview.MultiViewManager
          return (T) new MultiViewManager();

          case 55: // com.vopo.data.repository.ExternalRatingsRepositoryImpl
          return (T) new ExternalRatingsRepositoryImpl();

          case 56: // @com.vopo.app.di.MainPlayerEngine com.vopo.player.PlayerEngine
          return (T) NetworkModule_ProvideMainPlayerEngineFactory.provideMainPlayerEngine(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.playbackCompatibilityRepositoryImplProvider.get(), singletonCImpl.audioCompatibilityMemoryStoreProvider.get(), singletonCImpl.playbackSupportSnapshotStoreProvider.get());

          case 57: // com.vopo.app.ui.screens.player.SeekThumbnailProvider
          return (T) new SeekThumbnailProvider(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 58: // com.vopo.data.validation.ProviderSetupInputValidatorImpl
          return (T) new ProviderSetupInputValidatorImpl();

          case 59: // com.vopo.data.manager.BackupManagerImpl
          return (T) new BackupManagerImpl(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.preferencesRepositoryProvider.get(), singletonCImpl.androidKeystoreCredentialCryptoProvider.get(), singletonCImpl.providerDao(), singletonCImpl.favoriteDao(), singletonCImpl.virtualGroupDao(), singletonCImpl.playbackHistoryDao(), singletonCImpl.movieDao(), singletonCImpl.episodeDao(), singletonCImpl.categoryRepositoryImplProvider.get(), singletonCImpl.recordingScheduleDao(), singletonCImpl.recordingManagerImplProvider.get(), singletonCImpl.roomDatabaseTransactionRunnerProvider.get(), singletonCImpl.provideGsonProvider.get());

          case 60: // com.vopo.data.manager.GoogleDriveBackupSyncManager
          return (T) new GoogleDriveBackupSyncManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.backupManagerImplProvider.get());

          case 61: // com.vopo.app.pairing.ProviderQrPairingManager
          return (T) new ProviderQrPairingManager(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.validateAndAddProvider(), singletonCImpl.providerRepositoryImplProvider.get());

          case 62: // com.vopo.data.repository.SearchRepositoryImpl
          return (T) new SearchRepositoryImpl(singletonCImpl.searchDao(), singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.movieRepositoryImplProvider.get(), singletonCImpl.seriesRepositoryImplProvider.get());

          case 63: // com.vopo.data.sync.ProviderSyncStateReaderImpl
          return (T) new ProviderSyncStateReaderImpl(singletonCImpl.syncManagerProvider.get(), singletonCImpl.xtreamIndexJobDao());

          case 64: // com.vopo.app.ui.screens.settings.InternetSpeedTestRunner
          return (T) new InternetSpeedTestRunner(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.internetDownloadSpeedProbe());

          case 65: // com.vopo.data.repository.LicenseRepositoryImpl
          return (T) new LicenseRepositoryImpl(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideFirebaseFirestoreProvider.get());

          case 66: // com.google.firebase.firestore.FirebaseFirestore
          return (T) FirebaseModule_ProvideFirebaseFirestoreFactory.provideFirebaseFirestore();

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
