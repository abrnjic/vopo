package com.vopo.app.ui.screens.home;

import android.app.Application;
import com.vopo.app.player.LivePreviewHandoffManager;
import com.vopo.app.plugins.VopoPluginManager;
import com.vopo.app.tvinput.TvInputChannelSyncManager;
import com.vopo.app.ui.screens.multiview.MultiViewManager;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.data.sync.SyncManager;
import com.vopo.domain.manager.ParentalControlManager;
import com.vopo.domain.repository.CategoryRepository;
import com.vopo.domain.repository.ChannelRepository;
import com.vopo.domain.repository.CombinedM3uRepository;
import com.vopo.domain.repository.EpgRepository;
import com.vopo.domain.repository.FavoriteRepository;
import com.vopo.domain.repository.PlaybackHistoryRepository;
import com.vopo.domain.repository.ProviderRepository;
import com.vopo.domain.usecase.GetCustomCategories;
import com.vopo.domain.usecase.UnlockParentalCategory;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<CombinedM3uRepository> combinedM3uRepositoryProvider;

  private final Provider<ChannelRepository> channelRepositoryProvider;

  private final Provider<CategoryRepository> categoryRepositoryProvider;

  private final Provider<FavoriteRepository> favoriteRepositoryProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<EpgRepository> epgRepositoryProvider;

  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  private final Provider<GetCustomCategories> getCustomCategoriesProvider;

  private final Provider<UnlockParentalCategory> unlockParentalCategoryProvider;

  private final Provider<ParentalControlManager> parentalControlManagerProvider;

  private final Provider<SyncManager> syncManagerProvider;

  private final Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider;

  private final Provider<MultiViewManager> multiViewManagerProvider;

  private final Provider<LivePreviewHandoffManager> livePreviewHandoffManagerProvider;

  private final Provider<VopoPluginManager> pluginManagerProvider;

  private final Provider<PlayerEngine> playerEngineProvider;

  public HomeViewModel_Factory(Provider<Application> applicationProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<EpgRepository> epgRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<GetCustomCategories> getCustomCategoriesProvider,
      Provider<UnlockParentalCategory> unlockParentalCategoryProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider,
      Provider<SyncManager> syncManagerProvider,
      Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider,
      Provider<MultiViewManager> multiViewManagerProvider,
      Provider<LivePreviewHandoffManager> livePreviewHandoffManagerProvider,
      Provider<VopoPluginManager> pluginManagerProvider,
      Provider<PlayerEngine> playerEngineProvider) {
    this.applicationProvider = applicationProvider;
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.combinedM3uRepositoryProvider = combinedM3uRepositoryProvider;
    this.channelRepositoryProvider = channelRepositoryProvider;
    this.categoryRepositoryProvider = categoryRepositoryProvider;
    this.favoriteRepositoryProvider = favoriteRepositoryProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.epgRepositoryProvider = epgRepositoryProvider;
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
    this.getCustomCategoriesProvider = getCustomCategoriesProvider;
    this.unlockParentalCategoryProvider = unlockParentalCategoryProvider;
    this.parentalControlManagerProvider = parentalControlManagerProvider;
    this.syncManagerProvider = syncManagerProvider;
    this.tvInputChannelSyncManagerProvider = tvInputChannelSyncManagerProvider;
    this.multiViewManagerProvider = multiViewManagerProvider;
    this.livePreviewHandoffManagerProvider = livePreviewHandoffManagerProvider;
    this.pluginManagerProvider = pluginManagerProvider;
    this.playerEngineProvider = playerEngineProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(applicationProvider.get(), providerRepositoryProvider.get(), combinedM3uRepositoryProvider.get(), channelRepositoryProvider.get(), categoryRepositoryProvider.get(), favoriteRepositoryProvider.get(), preferencesRepositoryProvider.get(), epgRepositoryProvider.get(), playbackHistoryRepositoryProvider.get(), getCustomCategoriesProvider.get(), unlockParentalCategoryProvider.get(), parentalControlManagerProvider.get(), syncManagerProvider.get(), tvInputChannelSyncManagerProvider.get(), multiViewManagerProvider.get(), livePreviewHandoffManagerProvider.get(), pluginManagerProvider.get(), playerEngineProvider);
  }

  public static HomeViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<EpgRepository> epgRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<GetCustomCategories> getCustomCategoriesProvider,
      Provider<UnlockParentalCategory> unlockParentalCategoryProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider,
      Provider<SyncManager> syncManagerProvider,
      Provider<TvInputChannelSyncManager> tvInputChannelSyncManagerProvider,
      Provider<MultiViewManager> multiViewManagerProvider,
      Provider<LivePreviewHandoffManager> livePreviewHandoffManagerProvider,
      Provider<VopoPluginManager> pluginManagerProvider,
      Provider<PlayerEngine> playerEngineProvider) {
    return new HomeViewModel_Factory(applicationProvider, providerRepositoryProvider, combinedM3uRepositoryProvider, channelRepositoryProvider, categoryRepositoryProvider, favoriteRepositoryProvider, preferencesRepositoryProvider, epgRepositoryProvider, playbackHistoryRepositoryProvider, getCustomCategoriesProvider, unlockParentalCategoryProvider, parentalControlManagerProvider, syncManagerProvider, tvInputChannelSyncManagerProvider, multiViewManagerProvider, livePreviewHandoffManagerProvider, pluginManagerProvider, playerEngineProvider);
  }

  public static HomeViewModel newInstance(Application application,
      ProviderRepository providerRepository, CombinedM3uRepository combinedM3uRepository,
      ChannelRepository channelRepository, CategoryRepository categoryRepository,
      FavoriteRepository favoriteRepository, PreferencesRepository preferencesRepository,
      EpgRepository epgRepository, PlaybackHistoryRepository playbackHistoryRepository,
      GetCustomCategories getCustomCategories, UnlockParentalCategory unlockParentalCategory,
      ParentalControlManager parentalControlManager, SyncManager syncManager,
      TvInputChannelSyncManager tvInputChannelSyncManager, MultiViewManager multiViewManager,
      LivePreviewHandoffManager livePreviewHandoffManager, VopoPluginManager pluginManager,
      javax.inject.Provider<PlayerEngine> playerEngineProvider) {
    return new HomeViewModel(application, providerRepository, combinedM3uRepository, channelRepository, categoryRepository, favoriteRepository, preferencesRepository, epgRepository, playbackHistoryRepository, getCustomCategories, unlockParentalCategory, parentalControlManager, syncManager, tvInputChannelSyncManager, multiViewManager, livePreviewHandoffManager, pluginManager, playerEngineProvider);
  }
}
