package com.vopo.app.pairing;

import android.content.Context;
import com.vopo.domain.repository.ProviderRepository;
import com.vopo.domain.usecase.ValidateAndAddProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ProviderQrPairingManager_Factory implements Factory<ProviderQrPairingManager> {
  private final Provider<Context> contextProvider;

  private final Provider<ValidateAndAddProvider> validateAndAddProvider;

  private final Provider<ProviderRepository> providerRepositoryProvider;

  public ProviderQrPairingManager_Factory(Provider<Context> contextProvider,
      Provider<ValidateAndAddProvider> validateAndAddProvider,
      Provider<ProviderRepository> providerRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.validateAndAddProvider = validateAndAddProvider;
    this.providerRepositoryProvider = providerRepositoryProvider;
  }

  @Override
  public ProviderQrPairingManager get() {
    return newInstance(contextProvider.get(), validateAndAddProvider.get(), providerRepositoryProvider.get());
  }

  public static ProviderQrPairingManager_Factory create(Provider<Context> contextProvider,
      Provider<ValidateAndAddProvider> validateAndAddProvider,
      Provider<ProviderRepository> providerRepositoryProvider) {
    return new ProviderQrPairingManager_Factory(contextProvider, validateAndAddProvider, providerRepositoryProvider);
  }

  public static ProviderQrPairingManager newInstance(Context context,
      ValidateAndAddProvider validateAndAddProvider, ProviderRepository providerRepository) {
    return new ProviderQrPairingManager(context, validateAndAddProvider, providerRepository);
  }
}
