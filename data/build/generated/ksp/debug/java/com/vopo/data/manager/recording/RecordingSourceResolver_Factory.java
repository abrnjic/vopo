package com.vopo.data.manager.recording;

import com.vopo.data.local.dao.ProviderDao;
import com.vopo.data.remote.xtream.XtreamStreamUrlResolver;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class RecordingSourceResolver_Factory implements Factory<RecordingSourceResolver> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<ProviderDao> providerDaoProvider;

  private final Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider;

  public RecordingSourceResolver_Factory(Provider<OkHttpClient> okHttpClientProvider,
      Provider<ProviderDao> providerDaoProvider,
      Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.providerDaoProvider = providerDaoProvider;
    this.xtreamStreamUrlResolverProvider = xtreamStreamUrlResolverProvider;
  }

  @Override
  public RecordingSourceResolver get() {
    return newInstance(okHttpClientProvider.get(), providerDaoProvider.get(), xtreamStreamUrlResolverProvider.get());
  }

  public static RecordingSourceResolver_Factory create(Provider<OkHttpClient> okHttpClientProvider,
      Provider<ProviderDao> providerDaoProvider,
      Provider<XtreamStreamUrlResolver> xtreamStreamUrlResolverProvider) {
    return new RecordingSourceResolver_Factory(okHttpClientProvider, providerDaoProvider, xtreamStreamUrlResolverProvider);
  }

  public static RecordingSourceResolver newInstance(OkHttpClient okHttpClient,
      ProviderDao providerDao, XtreamStreamUrlResolver xtreamStreamUrlResolver) {
    return new RecordingSourceResolver(okHttpClient, providerDao, xtreamStreamUrlResolver);
  }
}
