package com.vopo.app.update;

import android.content.Context;
import com.vopo.data.preferences.PreferencesRepository;
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
public final class AppUpdateInstaller_Factory implements Factory<AppUpdateInstaller> {
  private final Provider<Context> contextProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  public AppUpdateInstaller_Factory(Provider<Context> contextProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider) {
    this.contextProvider = contextProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
  }

  @Override
  public AppUpdateInstaller get() {
    return newInstance(contextProvider.get(), preferencesRepositoryProvider.get());
  }

  public static AppUpdateInstaller_Factory create(Provider<Context> contextProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider) {
    return new AppUpdateInstaller_Factory(contextProvider, preferencesRepositoryProvider);
  }

  public static AppUpdateInstaller newInstance(Context context,
      PreferencesRepository preferencesRepository) {
    return new AppUpdateInstaller(context, preferencesRepository);
  }
}
