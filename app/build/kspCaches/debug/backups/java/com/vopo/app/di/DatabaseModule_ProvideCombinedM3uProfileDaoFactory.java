package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.CombinedM3uProfileDao;
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
public final class DatabaseModule_ProvideCombinedM3uProfileDaoFactory implements Factory<CombinedM3uProfileDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideCombinedM3uProfileDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public CombinedM3uProfileDao get() {
    return provideCombinedM3uProfileDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideCombinedM3uProfileDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideCombinedM3uProfileDaoFactory(dbProvider);
  }

  public static CombinedM3uProfileDao provideCombinedM3uProfileDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideCombinedM3uProfileDao(db));
  }
}
