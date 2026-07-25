package com.vopo.app.ui.screens.settings.parental;

import androidx.lifecycle.SavedStateHandle;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.domain.repository.CategoryRepository;
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
public final class ParentalControlGroupViewModel_Factory implements Factory<ParentalControlGroupViewModel> {
  private final Provider<CategoryRepository> categoryRepositoryProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public ParentalControlGroupViewModel_Factory(
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.categoryRepositoryProvider = categoryRepositoryProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public ParentalControlGroupViewModel get() {
    return newInstance(categoryRepositoryProvider.get(), preferencesRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static ParentalControlGroupViewModel_Factory create(
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new ParentalControlGroupViewModel_Factory(categoryRepositoryProvider, preferencesRepositoryProvider, savedStateHandleProvider);
  }

  public static ParentalControlGroupViewModel newInstance(CategoryRepository categoryRepository,
      PreferencesRepository preferencesRepository, SavedStateHandle savedStateHandle) {
    return new ParentalControlGroupViewModel(categoryRepository, preferencesRepository, savedStateHandle);
  }
}
