package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.PlaybackHistoryDao;
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
public final class DatabaseModule_ProvidePlaybackHistoryDaoFactory implements Factory<PlaybackHistoryDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvidePlaybackHistoryDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public PlaybackHistoryDao get() {
    return providePlaybackHistoryDao(dbProvider.get());
  }

  public static DatabaseModule_ProvidePlaybackHistoryDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvidePlaybackHistoryDaoFactory(dbProvider);
  }

  public static PlaybackHistoryDao providePlaybackHistoryDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePlaybackHistoryDao(db));
  }
}
