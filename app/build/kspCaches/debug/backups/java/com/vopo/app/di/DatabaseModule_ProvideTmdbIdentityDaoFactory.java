package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.TmdbIdentityDao;
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
public final class DatabaseModule_ProvideTmdbIdentityDaoFactory implements Factory<TmdbIdentityDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideTmdbIdentityDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public TmdbIdentityDao get() {
    return provideTmdbIdentityDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideTmdbIdentityDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideTmdbIdentityDaoFactory(dbProvider);
  }

  public static TmdbIdentityDao provideTmdbIdentityDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideTmdbIdentityDao(db));
  }
}
