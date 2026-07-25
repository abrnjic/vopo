package com.vopo.app.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.serialization.json.Json;

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
public final class NetworkModule_ProvideXtreamJsonFactory implements Factory<Json> {
  @Override
  public Json get() {
    return provideXtreamJson();
  }

  public static NetworkModule_ProvideXtreamJsonFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Json provideXtreamJson() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideXtreamJson());
  }

  private static final class InstanceHolder {
    static final NetworkModule_ProvideXtreamJsonFactory INSTANCE = new NetworkModule_ProvideXtreamJsonFactory();
  }
}
