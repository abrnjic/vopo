package com.vopo.domain.usecase;

import com.vopo.domain.manager.ProviderSetupInputValidator;
import com.vopo.domain.repository.ProviderRepository;
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
public final class ValidateAndAddProvider_Factory implements Factory<ValidateAndAddProvider> {
  private final Provider<ProviderSetupInputValidator> providerSetupInputValidatorProvider;

  private final Provider<ProviderRepository> providerRepositoryProvider;

  public ValidateAndAddProvider_Factory(
      Provider<ProviderSetupInputValidator> providerSetupInputValidatorProvider,
      Provider<ProviderRepository> providerRepositoryProvider) {
    this.providerSetupInputValidatorProvider = providerSetupInputValidatorProvider;
    this.providerRepositoryProvider = providerRepositoryProvider;
  }

  @Override
  public ValidateAndAddProvider get() {
    return newInstance(providerSetupInputValidatorProvider.get(), providerRepositoryProvider.get());
  }

  public static ValidateAndAddProvider_Factory create(
      Provider<ProviderSetupInputValidator> providerSetupInputValidatorProvider,
      Provider<ProviderRepository> providerRepositoryProvider) {
    return new ValidateAndAddProvider_Factory(providerSetupInputValidatorProvider, providerRepositoryProvider);
  }

  public static ValidateAndAddProvider newInstance(
      ProviderSetupInputValidator providerSetupInputValidator,
      ProviderRepository providerRepository) {
    return new ValidateAndAddProvider(providerSetupInputValidator, providerRepository);
  }
}
