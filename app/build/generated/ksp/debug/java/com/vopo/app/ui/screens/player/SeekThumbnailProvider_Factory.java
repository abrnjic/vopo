package com.vopo.app.ui.screens.player;

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
public final class SeekThumbnailProvider_Factory implements Factory<SeekThumbnailProvider> {
  private final Provider<Context> contextProvider;

  public SeekThumbnailProvider_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SeekThumbnailProvider get() {
    return newInstance(contextProvider.get());
  }

  public static SeekThumbnailProvider_Factory create(Provider<Context> contextProvider) {
    return new SeekThumbnailProvider_Factory(contextProvider);
  }

  public static SeekThumbnailProvider newInstance(Context context) {
    return new SeekThumbnailProvider(context);
  }
}
