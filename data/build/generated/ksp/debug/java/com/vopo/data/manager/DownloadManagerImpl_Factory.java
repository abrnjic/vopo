package com.vopo.data.manager;

import android.content.Context;
import com.vopo.data.local.dao.DownloadDao;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.data.remote.xtream.XtreamStreamUrlResolver;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineScope;
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
public final class DownloadManagerImpl_Factory implements Factory<DownloadManagerImpl> {
  private final Provider<Context> contextProvider;

  private final Provider<DownloadDao> downloadDaoProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider;

  private final Provider<CoroutineScope> applicationScopeProvider;

  public DownloadManagerImpl_Factory(Provider<Context> contextProvider,
      Provider<DownloadDao> downloadDaoProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider,
      Provider<CoroutineScope> applicationScopeProvider) {
    this.contextProvider = contextProvider;
    this.downloadDaoProvider = downloadDaoProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.okHttpClientProvider = okHttpClientProvider;
    this.xtreamStreamUrlResolverProvider = xtreamStreamUrlResolverProvider;
    this.applicationScopeProvider = applicationScopeProvider;
  }

  @Override
  public DownloadManagerImpl get() {
    return newInstance(contextProvider.get(), downloadDaoProvider.get(), preferencesRepositoryProvider.get(), okHttpClientProvider.get(), xtreamStreamUrlResolverProvider.get(), applicationScopeProvider.get());
  }

  public static DownloadManagerImpl_Factory create(Provider<Context> contextProvider,
      Provider<DownloadDao> downloadDaoProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<OkHttpClient> okHttpClientProvider,
      Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider,
      Provider<CoroutineScope> applicationScopeProvider) {
    return new DownloadManagerImpl_Factory(contextProvider, downloadDaoProvider, preferencesRepositoryProvider, okHttpClientProvider, xtreamStreamUrlResolverProvider, applicationScopeProvider);
  }

  public static DownloadManagerImpl newInstance(Context context, DownloadDao downloadDao,
      PreferencesRepository preferencesRepository, OkHttpClient okHttpClient,
      XtreamStreamUrlResolver xtreamStreamUrlResolver, CoroutineScope applicationScope) {
    return new DownloadManagerImpl(context, downloadDao, preferencesRepository, okHttpClient, xtreamStreamUrlResolver, applicationScope);
  }
}
