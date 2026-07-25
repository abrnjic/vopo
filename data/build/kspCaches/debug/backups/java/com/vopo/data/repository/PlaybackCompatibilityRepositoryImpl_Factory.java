package com.vopo.data.repository;

import com.vopo.data.local.dao.PlaybackCompatibilityDao;
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
public final class PlaybackCompatibilityRepositoryImpl_Factory implements Factory<PlaybackCompatibilityRepositoryImpl> {
  private final Provider<PlaybackCompatibilityDao> daoProvider;

  public PlaybackCompatibilityRepositoryImpl_Factory(
      Provider<PlaybackCompatibilityDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public PlaybackCompatibilityRepositoryImpl get() {
    return newInstance(daoProvider.get());
  }

  public static PlaybackCompatibilityRepositoryImpl_Factory create(
      Provider<PlaybackCompatibilityDao> daoProvider) {
    return new PlaybackCompatibilityRepositoryImpl_Factory(daoProvider);
  }

  public static PlaybackCompatibilityRepositoryImpl newInstance(PlaybackCompatibilityDao dao) {
    return new PlaybackCompatibilityRepositoryImpl(dao);
  }
}
