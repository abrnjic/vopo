package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.XtreamContentIndexDao;
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
public final class DatabaseModule_ProvideXtreamContentIndexDaoFactory implements Factory<XtreamContentIndexDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideXtreamContentIndexDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public XtreamContentIndexDao get() {
    return provideXtreamContentIndexDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideXtreamContentIndexDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideXtreamContentIndexDaoFactory(dbProvider);
  }

  public static XtreamContentIndexDao provideXtreamContentIndexDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideXtreamContentIndexDao(db));
  }
}
