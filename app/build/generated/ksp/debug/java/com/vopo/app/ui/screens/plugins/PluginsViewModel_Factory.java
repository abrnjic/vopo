package com.vopo.app.ui.screens.plugins;

import com.vopo.app.plugins.VopoPluginManager;
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
public final class PluginsViewModel_Factory implements Factory<PluginsViewModel> {
  private final Provider<VopoPluginManager> pluginManagerProvider;

  public PluginsViewModel_Factory(Provider<VopoPluginManager> pluginManagerProvider) {
    this.pluginManagerProvider = pluginManagerProvider;
  }

  @Override
  public PluginsViewModel get() {
    return newInstance(pluginManagerProvider.get());
  }

  public static PluginsViewModel_Factory create(Provider<VopoPluginManager> pluginManagerProvider) {
    return new PluginsViewModel_Factory(pluginManagerProvider);
  }

  public static PluginsViewModel newInstance(VopoPluginManager pluginManager) {
    return new PluginsViewModel(pluginManager);
  }
}
