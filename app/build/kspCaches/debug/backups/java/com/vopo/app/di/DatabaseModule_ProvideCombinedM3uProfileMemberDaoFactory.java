package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.CombinedM3uProfileMemberDao;
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
public final class DatabaseModule_ProvideCombinedM3uProfileMemberDaoFactory implements Factory<CombinedM3uProfileMemberDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideCombinedM3uProfileMemberDaoFactory(
      Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public CombinedM3uProfileMemberDao get() {
    return provideCombinedM3uProfileMemberDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideCombinedM3uProfileMemberDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideCombinedM3uProfileMemberDaoFactory(dbProvider);
  }

  public static CombinedM3uProfileMemberDao provideCombinedM3uProfileMemberDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideCombinedM3uProfileMemberDao(db));
  }
}
