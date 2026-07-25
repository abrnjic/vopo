package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.SearchDao;
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
public final class DatabaseModule_ProvideSearchDaoFactory implements Factory<SearchDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideSearchDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public SearchDao get() {
    return provideSearchDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideSearchDaoFactory create(Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideSearchDaoFactory(dbProvider);
  }

  public static SearchDao provideSearchDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideSearchDao(db));
  }
}
