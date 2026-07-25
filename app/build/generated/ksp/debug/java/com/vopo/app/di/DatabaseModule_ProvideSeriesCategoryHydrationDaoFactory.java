package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.SeriesCategoryHydrationDao;
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
public final class DatabaseModule_ProvideSeriesCategoryHydrationDaoFactory implements Factory<SeriesCategoryHydrationDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideSeriesCategoryHydrationDaoFactory(
      Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public SeriesCategoryHydrationDao get() {
    return provideSeriesCategoryHydrationDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideSeriesCategoryHydrationDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideSeriesCategoryHydrationDaoFactory(dbProvider);
  }

  public static SeriesCategoryHydrationDao provideSeriesCategoryHydrationDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideSeriesCategoryHydrationDao(db));
  }
}
