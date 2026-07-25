package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.XtreamIndexJobDao;
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
public final class DatabaseModule_ProvideXtreamIndexJobDaoFactory implements Factory<XtreamIndexJobDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideXtreamIndexJobDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public XtreamIndexJobDao get() {
    return provideXtreamIndexJobDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideXtreamIndexJobDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideXtreamIndexJobDaoFactory(dbProvider);
  }

  public static XtreamIndexJobDao provideXtreamIndexJobDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideXtreamIndexJobDao(db));
  }
}
