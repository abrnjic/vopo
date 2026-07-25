package com.vopo.app.ui.screens.welcome;

import com.vopo.data.sync.SyncProgressBus;
import com.vopo.domain.repository.LicenseRepository;
import com.vopo.domain.repository.ProviderRepository;
import com.vopo.domain.usecase.ValidateAndAddProvider;
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
public final class WelcomeViewModel_Factory implements Factory<WelcomeViewModel> {
  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<ValidateAndAddProvider> validateAndAddProvider;

  private final Provider<LicenseRepository> licenseRepositoryProvider;

  private final Provider<SyncProgressBus> syncProgressBusProvider;

  public WelcomeViewModel_Factory(Provider<ProviderRepository> providerRepositoryProvider,
      Provider<ValidateAndAddProvider> validateAndAddProvider,
      Provider<LicenseRepository> licenseRepositoryProvider,
      Provider<SyncProgressBus> syncProgressBusProvider) {
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.validateAndAddProvider = validateAndAddProvider;
    this.licenseRepositoryProvider = licenseRepositoryProvider;
    this.syncProgressBusProvider = syncProgressBusProvider;
  }

  @Override
  public WelcomeViewModel get() {
    return newInstance(providerRepositoryProvider.get(), validateAndAddProvider.get(), licenseRepositoryProvider.get(), syncProgressBusProvider.get());
  }

  public static WelcomeViewModel_Factory create(
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<ValidateAndAddProvider> validateAndAddProvider,
      Provider<LicenseRepository> licenseRepositoryProvider,
      Provider<SyncProgressBus> syncProgressBusProvider) {
    return new WelcomeViewModel_Factory(providerRepositoryProvider, validateAndAddProvider, licenseRepositoryProvider, syncProgressBusProvider);
  }

  public static WelcomeViewModel newInstance(ProviderRepository providerRepository,
      ValidateAndAddProvider validateAndAddProvider, LicenseRepository licenseRepository,
      SyncProgressBus syncProgressBus) {
    return new WelcomeViewModel(providerRepository, validateAndAddProvider, licenseRepository, syncProgressBus);
  }
}
