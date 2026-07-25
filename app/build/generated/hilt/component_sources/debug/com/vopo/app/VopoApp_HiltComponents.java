package com.vopo.app;

import androidx.hilt.work.HiltWrapper_WorkerFactoryModule;
import com.vopo.app.cast.CastRouteChooserActivity_GeneratedInjector;
import com.vopo.app.di.DatabaseModule;
import com.vopo.app.di.FirebaseModule;
import com.vopo.app.di.NetworkModule;
import com.vopo.app.di.RepositoryModule;
import com.vopo.app.diagnostics.ExternalPlayerProbeActivity_GeneratedInjector;
import com.vopo.app.service.DownloadForegroundService;
import com.vopo.app.tvinput.TvInputSetupActivity_GeneratedInjector;
import com.vopo.app.tvinput.TvInputSetupViewModel_HiltModules;
import com.vopo.app.tvinput.VopoTvInputService_GeneratedInjector;
import com.vopo.app.ui.screens.dashboard.DashboardViewModel_HiltModules;
import com.vopo.app.ui.screens.downloads.DownloadsViewModel_HiltModules;
import com.vopo.app.ui.screens.epg.EpgViewModel_HiltModules;
import com.vopo.app.ui.screens.favorites.FavoritesViewModel_HiltModules;
import com.vopo.app.ui.screens.home.HomeViewModel_HiltModules;
import com.vopo.app.ui.screens.movies.MovieDetailViewModel_HiltModules;
import com.vopo.app.ui.screens.movies.MoviesViewModel_HiltModules;
import com.vopo.app.ui.screens.multiview.MultiViewViewModel_HiltModules;
import com.vopo.app.ui.screens.player.PlayerViewModel_HiltModules;
import com.vopo.app.ui.screens.plugins.PluginsViewModel_HiltModules;
import com.vopo.app.ui.screens.provider.ProviderSetupViewModel_HiltModules;
import com.vopo.app.ui.screens.search.SearchViewModel_HiltModules;
import com.vopo.app.ui.screens.series.SeriesDetailViewModel_HiltModules;
import com.vopo.app.ui.screens.series.SeriesViewModel_HiltModules;
import com.vopo.app.ui.screens.settings.SettingsViewModel_HiltModules;
import com.vopo.app.ui.screens.settings.parental.ParentalControlGroupViewModel_HiltModules;
import com.vopo.app.ui.screens.welcome.WelcomeViewModel_HiltModules;
import com.vopo.data.manager.recording.RecordingForegroundService;
import com.vopo.data.manager.recording.RecordingReconcileWorker;
import com.vopo.data.manager.reminder.ProgramReminderAlarmReceiver;
import com.vopo.data.manager.reminder.ProgramReminderRestoreReceiver;
import com.vopo.data.sync.BackgroundEpgSyncWorker;
import com.vopo.data.sync.ProviderSyncWorker;
import com.vopo.data.sync.StalkerIndexWorker;
import com.vopo.data.sync.SyncWorker;
import com.vopo.data.sync.XtreamIndexWorker;
import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.android.components.ViewComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.components.ViewWithFragmentComponent;
import dagger.hilt.android.flags.FragmentGetContextFix;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_DefaultViewModelFactories_ActivityModule;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ViewModelModule;
import dagger.hilt.android.internal.managers.ActivityComponentManager;
import dagger.hilt.android.internal.managers.FragmentComponentManager;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_LifecycleModule;
import dagger.hilt.android.internal.managers.HiltWrapper_SavedStateHandleModule;
import dagger.hilt.android.internal.managers.ServiceComponentManager;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.HiltWrapper_ActivityModule;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.android.scopes.FragmentScoped;
import dagger.hilt.android.scopes.ServiceScoped;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.android.scopes.ViewScoped;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedComponent;
import dagger.hilt.migration.DisableInstallInCheck;
import javax.annotation.processing.Generated;
import javax.inject.Singleton;

@Generated("dagger.hilt.processor.internal.root.RootProcessor")
public final class VopoApp_HiltComponents {
  private VopoApp_HiltComponents() {
  }

  @Module(
      subcomponents = ServiceC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ServiceCBuilderModule {
    @Binds
    ServiceComponentBuilder bind(ServiceC.Builder builder);
  }

  @Module(
      subcomponents = ActivityRetainedC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityRetainedCBuilderModule {
    @Binds
    ActivityRetainedComponentBuilder bind(ActivityRetainedC.Builder builder);
  }

  @Module(
      subcomponents = ActivityC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ActivityCBuilderModule {
    @Binds
    ActivityComponentBuilder bind(ActivityC.Builder builder);
  }

  @Module(
      subcomponents = ViewModelC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewModelCBuilderModule {
    @Binds
    ViewModelComponentBuilder bind(ViewModelC.Builder builder);
  }

  @Module(
      subcomponents = ViewC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewCBuilderModule {
    @Binds
    ViewComponentBuilder bind(ViewC.Builder builder);
  }

  @Module(
      subcomponents = FragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface FragmentCBuilderModule {
    @Binds
    FragmentComponentBuilder bind(FragmentC.Builder builder);
  }

  @Module(
      subcomponents = ViewWithFragmentC.class
  )
  @DisableInstallInCheck
  @Generated("dagger.hilt.processor.internal.root.RootProcessor")
  abstract interface ViewWithFragmentCBuilderModule {
    @Binds
    ViewWithFragmentComponentBuilder bind(ViewWithFragmentC.Builder builder);
  }

  @Component(
      modules = {
          ApplicationContextModule.class,
          DatabaseModule.class,
          FirebaseModule.class,
          HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule.class,
          HiltWrapper_WorkerFactoryModule.class,
          NetworkModule.class,
          RepositoryModule.class,
          ActivityRetainedCBuilderModule.class,
          ServiceCBuilderModule.class
      }
  )
  @Singleton
  public abstract static class SingletonC implements VopoApp_GeneratedInjector,
      DownloadForegroundService.DownloadServiceEntryPoint,
      RecordingForegroundService.RecordingServiceEntryPoint,
      RecordingReconcileWorker.RecordingWorkerEntryPoint,
      ProgramReminderAlarmReceiver.ProgramReminderAlarmReceiverEntryPoint,
      ProgramReminderRestoreReceiver.ProgramReminderRestoreReceiverEntryPoint,
      BackgroundEpgSyncWorker.BackgroundEpgSyncWorkerEntryPoint,
      ProviderSyncWorker.ProviderSyncWorkerEntryPoint,
      StalkerIndexWorker.StalkerIndexWorkerEntryPoint,
      SyncWorker.SyncWorkerEntryPoint,
      XtreamIndexWorker.XtreamIndexWorkerEntryPoint,
      FragmentGetContextFix.FragmentGetContextFixEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint,
      ServiceComponentManager.ServiceComponentBuilderEntryPoint,
      SingletonComponent,
      GeneratedComponent {
  }

  @Subcomponent
  @ServiceScoped
  public abstract static class ServiceC implements VopoTvInputService_GeneratedInjector,
      ServiceComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ServiceComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          DashboardViewModel_HiltModules.KeyModule.class,
          DownloadsViewModel_HiltModules.KeyModule.class,
          EpgViewModel_HiltModules.KeyModule.class,
          FavoritesViewModel_HiltModules.KeyModule.class,
          HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class,
          HiltWrapper_SavedStateHandleModule.class,
          HomeViewModel_HiltModules.KeyModule.class,
          MovieDetailViewModel_HiltModules.KeyModule.class,
          MoviesViewModel_HiltModules.KeyModule.class,
          MultiViewViewModel_HiltModules.KeyModule.class,
          ParentalControlGroupViewModel_HiltModules.KeyModule.class,
          PlayerViewModel_HiltModules.KeyModule.class,
          PluginsViewModel_HiltModules.KeyModule.class,
          ProviderSetupViewModel_HiltModules.KeyModule.class,
          SearchViewModel_HiltModules.KeyModule.class,
          SeriesDetailViewModel_HiltModules.KeyModule.class,
          SeriesViewModel_HiltModules.KeyModule.class,
          SettingsViewModel_HiltModules.KeyModule.class,
          TvInputSetupViewModel_HiltModules.KeyModule.class,
          ActivityCBuilderModule.class,
          ViewModelCBuilderModule.class,
          WelcomeViewModel_HiltModules.KeyModule.class
      }
  )
  @ActivityRetainedScoped
  public abstract static class ActivityRetainedC implements ActivityRetainedComponent,
      ActivityComponentManager.ActivityComponentBuilderEntryPoint,
      HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityRetainedComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          HiltWrapper_ActivityModule.class,
          HiltWrapper_DefaultViewModelFactories_ActivityModule.class,
          FragmentCBuilderModule.class,
          ViewCBuilderModule.class
      }
  )
  @ActivityScoped
  public abstract static class ActivityC implements MainActivity_GeneratedInjector,
      CastRouteChooserActivity_GeneratedInjector,
      ExternalPlayerProbeActivity_GeneratedInjector,
      TvInputSetupActivity_GeneratedInjector,
      ActivityComponent,
      DefaultViewModelFactories.ActivityEntryPoint,
      HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint,
      FragmentComponentManager.FragmentComponentBuilderEntryPoint,
      ViewComponentManager.ViewComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ActivityComponentBuilder {
    }
  }

  @Subcomponent(
      modules = {
          DashboardViewModel_HiltModules.BindsModule.class,
          DownloadsViewModel_HiltModules.BindsModule.class,
          EpgViewModel_HiltModules.BindsModule.class,
          FavoritesViewModel_HiltModules.BindsModule.class,
          HiltWrapper_HiltViewModelFactory_ViewModelModule.class,
          HomeViewModel_HiltModules.BindsModule.class,
          MovieDetailViewModel_HiltModules.BindsModule.class,
          MoviesViewModel_HiltModules.BindsModule.class,
          MultiViewViewModel_HiltModules.BindsModule.class,
          ParentalControlGroupViewModel_HiltModules.BindsModule.class,
          PlayerViewModel_HiltModules.BindsModule.class,
          PluginsViewModel_HiltModules.BindsModule.class,
          ProviderSetupViewModel_HiltModules.BindsModule.class,
          SearchViewModel_HiltModules.BindsModule.class,
          SeriesDetailViewModel_HiltModules.BindsModule.class,
          SeriesViewModel_HiltModules.BindsModule.class,
          SettingsViewModel_HiltModules.BindsModule.class,
          TvInputSetupViewModel_HiltModules.BindsModule.class,
          WelcomeViewModel_HiltModules.BindsModule.class
      }
  )
  @ViewModelScoped
  public abstract static class ViewModelC implements ViewModelComponent,
      HiltViewModelFactory.ViewModelFactoriesEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewModelComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewC implements ViewComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewComponentBuilder {
    }
  }

  @Subcomponent(
      modules = ViewWithFragmentCBuilderModule.class
  )
  @FragmentScoped
  public abstract static class FragmentC implements FragmentComponent,
      DefaultViewModelFactories.FragmentEntryPoint,
      ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends FragmentComponentBuilder {
    }
  }

  @Subcomponent
  @ViewScoped
  public abstract static class ViewWithFragmentC implements ViewWithFragmentComponent,
      GeneratedComponent {
    @Subcomponent.Builder
    abstract interface Builder extends ViewWithFragmentComponentBuilder {
    }
  }
}
