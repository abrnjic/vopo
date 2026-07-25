package com.vopo.data.repository;

import com.vopo.data.local.dao.CategoryDao;
import com.vopo.data.local.dao.ChannelDao;
import com.vopo.data.local.dao.FavoriteDao;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.data.remote.xtream.XtreamStreamUrlResolver;
import com.vopo.domain.manager.ParentalControlManager;
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
public final class ChannelRepositoryImpl_Factory implements Factory<ChannelRepositoryImpl> {
  private final Provider<ChannelDao> channelDaoProvider;

  private final Provider<CategoryDao> categoryDaoProvider;

  private final Provider<FavoriteDao> favoriteDaoProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<ParentalControlManager> parentalControlManagerProvider;

  private final Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider;

  public ChannelRepositoryImpl_Factory(Provider<ChannelDao> channelDaoProvider,
      Provider<CategoryDao> categoryDaoProvider, Provider<FavoriteDao> favoriteDaoProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider,
      Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider) {
    this.channelDaoProvider = channelDaoProvider;
    this.categoryDaoProvider = categoryDaoProvider;
    this.favoriteDaoProvider = favoriteDaoProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.parentalControlManagerProvider = parentalControlManagerProvider;
    this.xtreamStreamUrlResolverProvider = xtreamStreamUrlResolverProvider;
  }

  @Override
  public ChannelRepositoryImpl get() {
    return newInstance(channelDaoProvider.get(), categoryDaoProvider.get(), favoriteDaoProvider.get(), preferencesRepositoryProvider.get(), parentalControlManagerProvider.get(), xtreamStreamUrlResolverProvider.get());
  }

  public static ChannelRepositoryImpl_Factory create(Provider<ChannelDao> channelDaoProvider,
      Provider<CategoryDao> categoryDaoProvider, Provider<FavoriteDao> favoriteDaoProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider,
      Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider) {
    return new ChannelRepositoryImpl_Factory(channelDaoProvider, categoryDaoProvider, favoriteDaoProvider, preferencesRepositoryProvider, parentalControlManagerProvider, xtreamStreamUrlResolverProvider);
  }

  public static ChannelRepositoryImpl newInstance(ChannelDao channelDao, CategoryDao categoryDao,
      FavoriteDao favoriteDao, PreferencesRepository preferencesRepository,
      ParentalControlManager parentalControlManager,
      XtreamStreamUrlResolver xtreamStreamUrlResolver) {
    return new ChannelRepositoryImpl(channelDao, categoryDao, favoriteDao, preferencesRepository, parentalControlManager, xtreamStreamUrlResolver);
  }
}
