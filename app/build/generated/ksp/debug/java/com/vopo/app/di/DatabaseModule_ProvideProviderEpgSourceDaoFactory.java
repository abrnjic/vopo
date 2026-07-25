package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.ProviderEpgSourceDao;
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
public final class DatabaseModule_ProvideProviderEpgSourceDaoFactory implements Factory<ProviderEpgSourceDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideProviderEpgSourceDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ProviderEpgSourceDao get() {
    return provideProviderEpgSourceDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideProviderEpgSourceDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideProviderEpgSourceDaoFactory(dbProvider);
  }

  public static ProviderEpgSourceDao provideProviderEpgSourceDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideProviderEpgSourceDao(db));
  }
}
