package com.vopo.domain.manager;

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
public final class ParentalControlManager_Factory implements Factory<ParentalControlManager> {
  private final Provider<ParentalControlSessionStore> sessionStoreProvider;

  public ParentalControlManager_Factory(
      Provider<ParentalControlSessionStore> sessionStoreProvider) {
    this.sessionStoreProvider = sessionStoreProvider;
  }

  @Override
  public ParentalControlManager get() {
    return newInstance(sessionStoreProvider.get());
  }

  public static ParentalControlManager_Factory create(
      Provider<ParentalControlSessionStore> sessionStoreProvider) {
    return new ParentalControlManager_Factory(sessionStoreProvider);
  }

  public static ParentalControlManager newInstance(ParentalControlSessionStore sessionStore) {
    return new ParentalControlManager(sessionStore);
  }
}
