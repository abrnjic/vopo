package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.XtreamLiveOnboardingDao;
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
public final class DatabaseModule_ProvideXtreamLiveOnboardingDaoFactory implements Factory<XtreamLiveOnboardingDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideXtreamLiveOnboardingDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public XtreamLiveOnboardingDao get() {
    return provideXtreamLiveOnboardingDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideXtreamLiveOnboardingDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideXtreamLiveOnboardingDaoFactory(dbProvider);
  }

  public static XtreamLiveOnboardingDao provideXtreamLiveOnboardingDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideXtreamLiveOnboardingDao(db));
  }
}
