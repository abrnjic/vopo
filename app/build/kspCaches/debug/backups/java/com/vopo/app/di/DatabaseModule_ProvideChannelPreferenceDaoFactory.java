package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.ChannelPreferenceDao;
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
public final class DatabaseModule_ProvideChannelPreferenceDaoFactory implements Factory<ChannelPreferenceDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideChannelPreferenceDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ChannelPreferenceDao get() {
    return provideChannelPreferenceDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideChannelPreferenceDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideChannelPreferenceDaoFactory(dbProvider);
  }

  public static ChannelPreferenceDao provideChannelPreferenceDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideChannelPreferenceDao(db));
  }
}
