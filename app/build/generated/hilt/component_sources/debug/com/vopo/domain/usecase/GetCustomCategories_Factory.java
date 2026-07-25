package com.vopo.domain.usecase;

import com.vopo.domain.repository.ChannelRepository;
import com.vopo.domain.repository.FavoriteRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class GetCustomCategories_Factory implements Factory<GetCustomCategories> {
  private final Provider<FavoriteRepository> favoriteRepositoryProvider;

  private final Provider<ChannelRepository> channelRepositoryProvider;

  public GetCustomCategories_Factory(Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider) {
    this.favoriteRepositoryProvider = favoriteRepositoryProvider;
    this.channelRepositoryProvider = channelRepositoryProvider;
  }

  @Override
  public GetCustomCategories get() {
    return newInstance(favoriteRepositoryProvider.get(), channelRepositoryProvider.get());
  }

  public static GetCustomCategories_Factory create(
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<ChannelRepository> channelRepositoryProvider) {
    return new GetCustomCategories_Factory(favoriteRepositoryProvider, channelRepositoryProvider);
  }

  public static GetCustomCategories newInstance(FavoriteRepository favoriteRepository,
      ChannelRepository channelRepository) {
    return new GetCustomCategories(favoriteRepository, channelRepository);
  }
}
