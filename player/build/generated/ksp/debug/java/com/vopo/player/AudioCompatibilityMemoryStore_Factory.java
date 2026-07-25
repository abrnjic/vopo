package com.vopo.player;

import android.content.Context;
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
public final class AudioCompatibilityMemoryStore_Factory implements Factory<AudioCompatibilityMemoryStore> {
  private final Provider<Context> contextProvider;

  public AudioCompatibilityMemoryStore_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AudioCompatibilityMemoryStore get() {
    return newInstance(contextProvider.get());
  }

  public static AudioCompatibilityMemoryStore_Factory create(Provider<Context> contextProvider) {
    return new AudioCompatibilityMemoryStore_Factory(contextProvider);
  }

  public static AudioCompatibilityMemoryStore newInstance(Context context) {
    return new AudioCompatibilityMemoryStore(context);
  }
}
