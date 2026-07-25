package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.EpgProgrammeDao;
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
public final class DatabaseModule_ProvideEpgProgrammeDaoFactory implements Factory<EpgProgrammeDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideEpgProgrammeDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public EpgProgrammeDao get() {
    return provideEpgProgrammeDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideEpgProgrammeDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideEpgProgrammeDaoFactory(dbProvider);
  }

  public static EpgProgrammeDao provideEpgProgrammeDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideEpgProgrammeDao(db));
  }
}
