package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.ChannelEpgMappingDao;
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
public final class DatabaseModule_ProvideChannelEpgMappingDaoFactory implements Factory<ChannelEpgMappingDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideChannelEpgMappingDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ChannelEpgMappingDao get() {
    return provideChannelEpgMappingDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideChannelEpgMappingDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideChannelEpgMappingDaoFactory(dbProvider);
  }

  public static ChannelEpgMappingDao provideChannelEpgMappingDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideChannelEpgMappingDao(db));
  }
}
