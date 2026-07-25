package com.vopo.app.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineScope;

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
public final class RepositoryModule_Companion_ProvideRepositoryCoroutineScopeFactory implements Factory<CoroutineScope> {
  @Override
  public CoroutineScope get() {
    return provideRepositoryCoroutineScope();
  }

  public static RepositoryModule_Companion_ProvideRepositoryCoroutineScopeFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CoroutineScope provideRepositoryCoroutineScope() {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.Companion.provideRepositoryCoroutineScope());
  }

  private static final class InstanceHolder {
    static final RepositoryModule_Companion_ProvideRepositoryCoroutineScopeFactory INSTANCE = new RepositoryModule_Companion_ProvideRepositoryCoroutineScopeFactory();
  }
}
