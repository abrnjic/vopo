package com.vopo.app.cast;

import android.content.Context;
import com.vopo.app.plugins.VopoPluginManager;
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
public final class CastManager_Factory implements Factory<CastManager> {
  private final Provider<Context> contextProvider;

  private final Provider<VopoPluginManager> pluginManagerProvider;

  public CastManager_Factory(Provider<Context> contextProvider,
      Provider<VopoPluginManager> pluginManagerProvider) {
    this.contextProvider = contextProvider;
    this.pluginManagerProvider = pluginManagerProvider;
  }

  @Override
  public CastManager get() {
    return newInstance(contextProvider.get(), pluginManagerProvider.get());
  }

  public static CastManager_Factory create(Provider<Context> contextProvider,
      Provider<VopoPluginManager> pluginManagerProvider) {
    return new CastManager_Factory(contextProvider, pluginManagerProvider);
  }

  public static CastManager newInstance(Context context, VopoPluginManager pluginManager) {
    return new CastManager(context, pluginManager);
  }
}
