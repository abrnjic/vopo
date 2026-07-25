package com.vopo.app.ui.screens.multiview;

import android.content.Context;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.domain.manager.ParentalControlManager;
import com.vopo.domain.repository.ChannelRepository;
import com.vopo.domain.repository.FavoriteRepository;
import com.vopo.domain.repository.PlaybackHistoryRepository;
import com.vopo.domain.repository.ProviderRepository;
import com.vopo.domain.usecase.UnlockParentalCategory;
import com.vopo.player.PlayerEngine;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata({
    "dagger.hilt.android.qualifiers.ApplicationContext",
    "com.vopo.app.di.AuxiliaryPlayerEngine"
})
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
public final class MultiViewViewModel_Factory implements Factory<MultiViewViewModel> {
  private final Provider<Context> contextProvider;

  private final Provider<MultiViewManager> multiViewManagerProvider;

  private final Provider<PlayerEngine> playerEngineProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<ChannelRepository> channelRepositoryProvider;

  private final Provider<FavoriteRepository> favoriteRepositoryProvider;

  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<ParentalControlManager> parentalControlManagerProvider;

  private final Provider<UnlockParentalCategory> unlockParentalCategoryProvider;

  public MultiViewViewModel_Factory(Provider<Context> contextProvider,
      Provider<MultiViewManager> multiViewManagerProvider,
      Provider<PlayerEngine> playerEngineProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider,
      Provider<UnlockParentalCategory> unlockParentalCategoryProvider) {
    this.contextProvider = contextProvider;
    this.multiViewManagerProvider = multiViewManagerProvider;
    this.playerEngineProvider = playerEngineProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.channelRepositoryProvider = channelRepositoryProvider;
    this.favoriteRepositoryProvider = favoriteRepositoryProvider;
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.parentalControlManagerProvider = parentalControlManagerProvider;
    this.unlockParentalCategoryProvider = unlockParentalCategoryProvider;
  }

  @Override
  public MultiViewViewModel get() {
    return newInstance(contextProvider.get(), multiViewManagerProvider.get(), playerEngineProvider, preferencesRepositoryProvider.get(), channelRepositoryProvider.get(), favoriteRepositoryProvider.get(), playbackHistoryRepositoryProvider.get(), providerRepositoryProvider.get(), parentalControlManagerProvider.get(), unlockParentalCategoryProvider.get());
  }

  public static MultiViewViewModel_Factory create(Provider<Context> contextProvider,
      Provider<MultiViewManager> multiViewManagerProvider,
      Provider<PlayerEngine> playerEngineProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider,
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider,
      Provider<UnlockParentalCategory> unlockParentalCategoryProvider) {
    return new MultiViewViewModel_Factory(contextProvider, multiViewManagerProvider, playerEngineProvider, preferencesRepositoryProvider, channelRepositoryProvider, favoriteRepositoryProvider, playbackHistoryRepositoryProvider, providerRepositoryProvider, parentalControlManagerProvider, unlockParentalCategoryProvider);
  }

  public static MultiViewViewModel newInstance(Context context, MultiViewManager multiViewManager,
      javax.inject.Provider<PlayerEngine> playerEngineProvider,
      PreferencesRepository preferencesRepository, ChannelRepository channelRepository,
      FavoriteRepository favoriteRepository, PlaybackHistoryRepository playbackHistoryRepository,
      ProviderRepository providerRepository, ParentalControlManager parentalControlManager,
      UnlockParentalCategory unlockParentalCategory) {
    return new MultiViewViewModel(context, multiViewManager, playerEngineProvider, preferencesRepository, channelRepository, favoriteRepository, playbackHistoryRepository, providerRepository, parentalControlManager, unlockParentalCategory);
  }
}
