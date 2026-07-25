package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.ProgramDao;
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
public final class DatabaseModule_ProvideProgramDaoFactory implements Factory<ProgramDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideProgramDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ProgramDao get() {
    return provideProgramDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideProgramDaoFactory create(Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideProgramDaoFactory(dbProvider);
  }

  public static ProgramDao provideProgramDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideProgramDao(db));
  }
}
