package com.vopo.player.timeshift;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class DefaultLiveTimeshiftManager_Factory implements Factory<DefaultLiveTimeshiftManager> {
  private final Provider<Context> contextProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  public DefaultLiveTimeshiftManager_Factory(Provider<Context> contextProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    this.contextProvider = contextProvider;
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public DefaultLiveTimeshiftManager get() {
    return newInstance(contextProvider.get(), okHttpClientProvider.get());
  }

  public static DefaultLiveTimeshiftManager_Factory create(Provider<Context> contextProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    return new DefaultLiveTimeshiftManager_Factory(contextProvider, okHttpClientProvider);
  }

  public static DefaultLiveTimeshiftManager newInstance(Context context,
      OkHttpClient okHttpClient) {
    return new DefaultLiveTimeshiftManager(context, okHttpClient);
  }
}
