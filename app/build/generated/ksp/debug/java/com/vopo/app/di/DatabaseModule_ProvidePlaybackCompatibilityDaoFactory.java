package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.PlaybackCompatibilityDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvidePlaybackCompatibilityDaoFactory implements Factory<PlaybackCompatibilityDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvidePlaybackCompatibilityDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public PlaybackCompatibilityDao get() {
    return providePlaybackCompatibilityDao(dbProvider.get());
  }

  public static DatabaseModule_ProvidePlaybackCompatibilityDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvidePlaybackCompatibilityDaoFactory(dbProvider);
  }

  public static PlaybackCompatibilityDao providePlaybackCompatibilityDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePlaybackCompatibilityDao(db));
  }
}
