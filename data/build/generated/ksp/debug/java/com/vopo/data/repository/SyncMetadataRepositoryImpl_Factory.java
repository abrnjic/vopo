package com.vopo.data.repository;

import com.vopo.data.local.dao.SyncMetadataDao;
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
public final class SyncMetadataRepositoryImpl_Factory implements Factory<SyncMetadataRepositoryImpl> {
  private final Provider<SyncMetadataDao> daoProvider;

  public SyncMetadataRepositoryImpl_Factory(Provider<SyncMetadataDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public SyncMetadataRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static SyncMetadataRepositoryImpl_Factory create(Provider<SyncMetadataDao> daoProvider) {
    return new SyncMetadataRepositoryImpl_Factory(daoProvider);
  }

  public static SyncMetadataRepositoryImpl newInstance(SyncMetadataDao dao) {
    return new SyncMetadataRepositoryImpl(dao);
  }
}
