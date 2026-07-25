package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.ChannelDao;
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
public final class DatabaseModule_ProvideChannelDaoFactory implements Factory<ChannelDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideChannelDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ChannelDao get() {
    return provideChannelDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideChannelDaoFactory create(Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideChannelDaoFactory(dbProvider);
  }

  public static ChannelDao provideChannelDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideChannelDao(db));
  }
}
