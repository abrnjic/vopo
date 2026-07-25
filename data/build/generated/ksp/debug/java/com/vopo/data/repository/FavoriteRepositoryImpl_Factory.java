package com.vopo.data.repository;

import com.vopo.data.local.DatabaseTransactionRunner;
import com.vopo.data.local.dao.FavoriteDao;
import com.vopo.data.local.dao.VirtualGroupDao;
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
public final class FavoriteRepositoryImpl_Factory implements Factory<FavoriteRepositoryImpl> {
  private final Provider<FavoriteDao> favoriteDaoProvider;

  private final Provider<VirtualGroupDao> virtualGroupDaoProvider;

  private final Provider<DatabaseTransactionRunner> transactionRunnerProvider;

  public FavoriteRepositoryImpl_Factory(Provider<FavoriteDao> favoriteDaoProvider,
      Provider<VirtualGroupDao> virtualGroupDaoProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider) {
    this.favoriteDaoProvider = favoriteDaoProvider;
    this.virtualGroupDaoProvider = virtualGroupDaoProvider;
    this.transactionRunnerProvider = transactionRunnerProvider;
  }

  @Override
  public FavoriteRepositoryImpl get() {
    return newInstance(favoriteDaoProvider.get(), virtualGroupDaoProvider.get(), transactionRunnerProvider.get());
  }

  public static FavoriteRepositoryImpl_Factory create(Provider<FavoriteDao> favoriteDaoProvider,
      Provider<VirtualGroupDao> virtualGroupDaoProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider) {
    return new FavoriteRepositoryImpl_Factory(favoriteDaoProvider, virtualGroupDaoProvider, transactionRunnerProvider);
  }

  public static FavoriteRepositoryImpl newInstance(FavoriteDao favoriteDao,
      VirtualGroupDao virtualGroupDao, DatabaseTransactionRunner transactionRunner) {
    return new FavoriteRepositoryImpl(favoriteDao, virtualGroupDao, transactionRunner);
  }
}
