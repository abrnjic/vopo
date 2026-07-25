package com.vopo.app.ui.screens.search;

import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.domain.manager.ParentalControlManager;
import com.vopo.domain.manager.RecordingManager;
import com.vopo.domain.repository.CategoryRepository;
import com.vopo.domain.repository.FavoriteRepository;
import com.vopo.domain.repository.ProviderRepository;
import com.vopo.domain.usecase.SearchContent;
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
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<SearchContent> searchContentProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<ParentalControlManager> parentalControlManagerProvider;

  private final Provider<FavoriteRepository> favoriteRepositoryProvider;

  private final Provider<CategoryRepository> categoryRepositoryProvider;

  private final Provider<RecordingManager> recordingManagerProvider;

  public SearchViewModel_Factory(Provider<ProviderRepository> providerRepositoryProvider,
      Provider<SearchContent> searchContentProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<RecordingManager> recordingManagerProvider) {
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.searchContentProvider = searchContentProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.parentalControlManagerProvider = parentalControlManagerProvider;
    this.favoriteRepositoryProvider = favoriteRepositoryProvider;
    this.categoryRepositoryProvider = categoryRepositoryProvider;
    this.recordingManagerProvider = recordingManagerProvider;
  }

  @Override
  public SearchViewModel get() {
    return newInstance(providerRepositoryProvider.get(), searchContentProvider.get(), preferencesRepositoryProvider.get(), parentalControlManagerProvider.get(), favoriteRepositoryProvider.get(), categoryRepositoryProvider.get(), recordingManagerProvider.get());
  }

  public static SearchViewModel_Factory create(
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<SearchContent> searchContentProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider,
      Provider<FavoriteRepository> favoriteRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<RecordingManager> recordingManagerProvider) {
    return new SearchViewModel_Factory(providerRepositoryProvider, searchContentProvider, preferencesRepositoryProvider, parentalControlManagerProvider, favoriteRepositoryProvider, categoryRepositoryProvider, recordingManagerProvider);
  }

  public static SearchViewModel newInstance(ProviderRepository providerRepository,
      SearchContent searchContent, PreferencesRepository preferencesRepository,
      ParentalControlManager parentalControlManager, FavoriteRepository favoriteRepository,
      CategoryRepository categoryRepository, RecordingManager recordingManager) {
    return new SearchViewModel(providerRepository, searchContent, preferencesRepository, parentalControlManager, favoriteRepository, categoryRepository, recordingManager);
  }
}
