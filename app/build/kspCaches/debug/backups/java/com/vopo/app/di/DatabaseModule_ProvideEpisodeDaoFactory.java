package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.EpisodeDao;
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
public final class DatabaseModule_ProvideEpisodeDaoFactory implements Factory<EpisodeDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideEpisodeDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public EpisodeDao get() {
    return provideEpisodeDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideEpisodeDaoFactory create(Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideEpisodeDaoFactory(dbProvider);
  }

  public static EpisodeDao provideEpisodeDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideEpisodeDao(db));
  }
}
