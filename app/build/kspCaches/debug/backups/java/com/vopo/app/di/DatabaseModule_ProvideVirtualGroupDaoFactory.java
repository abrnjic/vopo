package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.VirtualGroupDao;
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
public final class DatabaseModule_ProvideVirtualGroupDaoFactory implements Factory<VirtualGroupDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideVirtualGroupDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public VirtualGroupDao get() {
    return provideVirtualGroupDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideVirtualGroupDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideVirtualGroupDaoFactory(dbProvider);
  }

  public static VirtualGroupDao provideVirtualGroupDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideVirtualGroupDao(db));
  }
}
