package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.EpgChannelDao;
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
public final class DatabaseModule_ProvideEpgChannelDaoFactory implements Factory<EpgChannelDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideEpgChannelDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public EpgChannelDao get() {
    return provideEpgChannelDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideEpgChannelDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideEpgChannelDaoFactory(dbProvider);
  }

  public static EpgChannelDao provideEpgChannelDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideEpgChannelDao(db));
  }
}
