package com.vopo.domain.usecase;

import com.vopo.domain.manager.ParentalControlManager;
import com.vopo.domain.manager.ParentalPinVerifier;
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
public final class UnlockParentalCategory_Factory implements Factory<UnlockParentalCategory> {
  private final Provider<ParentalPinVerifier> parentalPinVerifierProvider;

  private final Provider<ParentalControlManager> parentalControlManagerProvider;

  public UnlockParentalCategory_Factory(Provider<ParentalPinVerifier> parentalPinVerifierProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider) {
    this.parentalPinVerifierProvider = parentalPinVerifierProvider;
    this.parentalControlManagerProvider = parentalControlManagerProvider;
  }

  @Override
  public UnlockParentalCategory get() {
    return newInstance(parentalPinVerifierProvider.get(), parentalControlManagerProvider.get());
  }

  public static UnlockParentalCategory_Factory create(
      Provider<ParentalPinVerifier> parentalPinVerifierProvider,
      Provider<ParentalControlManager> parentalControlManagerProvider) {
    return new UnlockParentalCategory_Factory(parentalPinVerifierProvider, parentalControlManagerProvider);
  }

  public static UnlockParentalCategory newInstance(ParentalPinVerifier parentalPinVerifier,
      ParentalControlManager parentalControlManager) {
    return new UnlockParentalCategory(parentalPinVerifier, parentalControlManager);
  }
}
