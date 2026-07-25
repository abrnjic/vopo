package com.vopo.data.validation;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class ProviderSetupInputValidatorImpl_Factory implements Factory<ProviderSetupInputValidatorImpl> {
  @Override
  public ProviderSetupInputValidatorImpl get() {
    return newInstance();
  }

  public static ProviderSetupInputValidatorImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ProviderSetupInputValidatorImpl newInstance() {
    return new ProviderSetupInputValidatorImpl();
  }

  private static final class InstanceHolder {
    static final ProviderSetupInputValidatorImpl_Factory INSTANCE = new ProviderSetupInputValidatorImpl_Factory();
  }
}
