package com.vopo.data.repository;

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
public final class ExternalRatingsRepositoryImpl_Factory implements Factory<ExternalRatingsRepositoryImpl> {
  @Override
  public ExternalRatingsRepositoryImpl get() {
    return newInstance();
  }

  public static ExternalRatingsRepositoryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ExternalRatingsRepositoryImpl newInstance() {
    return new ExternalRatingsRepositoryImpl();
  }

  private static final class InstanceHolder {
    static final ExternalRatingsRepositoryImpl_Factory INSTANCE = new ExternalRatingsRepositoryImpl_Factory();
  }
}
