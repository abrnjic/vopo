package com.vopo.data.sync;

import com.vopo.data.local.dao.XtreamIndexJobDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
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
public final class ProviderSyncStateReaderImpl_Factory implements Factory<ProviderSyncStateReaderImpl> {
  private final Provider<SyncManager> syncManagerProvider;

  private final Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider;

  public ProviderSyncStateReaderImpl_Factory(Provider<SyncManager> syncManagerProvider,
      Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider) {
    this.syncManagerProvider = syncManagerProvider;
    this.xtreamIndexJobDaoProvider = xtreamIndexJobDaoProvider;
  }

  @Override
  public ProviderSyncStateReaderImpl get() {
    return newInstance(syncManagerProvider.get(), xtreamIndexJobDaoProvider.get());
  }

  public static ProviderSyncStateReaderImpl_Factory create(
      Provider<SyncManager> syncManagerProvider,
      Provider<XtreamIndexJobDao> xtreamIndexJobDaoProvider) {
    return new ProviderSyncStateReaderImpl_Factory(syncManagerProvider, xtreamIndexJobDaoProvider);
  }

  public static ProviderSyncStateReaderImpl newInstance(SyncManager syncManager,
      XtreamIndexJobDao xtreamIndexJobDao) {
    return new ProviderSyncStateReaderImpl(syncManager, xtreamIndexJobDao);
  }
}
