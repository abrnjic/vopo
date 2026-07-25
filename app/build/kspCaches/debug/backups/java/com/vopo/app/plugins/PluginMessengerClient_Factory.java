package com.vopo.app.plugins;

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
public final class PluginMessengerClient_Factory implements Factory<PluginMessengerClient> {
  private final Provider<Context> contextProvider;

  public PluginMessengerClient_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public PluginMessengerClient get() {
    return newInstance(contextProvider.get());
  }

  public static PluginMessengerClient_Factory create(Provider<Context> contextProvider) {
    return new PluginMessengerClient_Factory(contextProvider);
  }

  public static PluginMessengerClient newInstance(Context context) {
    return new PluginMessengerClient(context);
  }
}
