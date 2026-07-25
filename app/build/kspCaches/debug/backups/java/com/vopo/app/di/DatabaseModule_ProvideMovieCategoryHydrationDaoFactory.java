package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.MovieCategoryHydrationDao;
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
public final class DatabaseModule_ProvideMovieCategoryHydrationDaoFactory implements Factory<MovieCategoryHydrationDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideMovieCategoryHydrationDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public MovieCategoryHydrationDao get() {
    return provideMovieCategoryHydrationDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideMovieCategoryHydrationDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideMovieCategoryHydrationDaoFactory(dbProvider);
  }

  public static MovieCategoryHydrationDao provideMovieCategoryHydrationDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideMovieCategoryHydrationDao(db));
  }
}
