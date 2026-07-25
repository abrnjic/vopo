package com.vopo.app.di;

import com.vopo.data.parser.M3uParser;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class RepositoryModule_Companion_ProvideM3uParserFactory implements Factory<M3uParser> {
  @Override
  public M3uParser get() {
    return provideM3uParser();
  }

  public static RepositoryModule_Companion_ProvideM3uParserFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static M3uParser provideM3uParser() {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.Companion.provideM3uParser());
  }

  private static final class InstanceHolder {
    static final RepositoryModule_Companion_ProvideM3uParserFactory INSTANCE = new RepositoryModule_Companion_ProvideM3uParserFactory();
  }
}
