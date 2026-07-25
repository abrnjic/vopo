package com.vopo.app.di;

import com.vopo.data.local.VopoDatabase;
import com.vopo.data.local.dao.RecordingRunDao;
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
public final class DatabaseModule_ProvideRecordingRunDaoFactory implements Factory<RecordingRunDao> {
  private final Provider<VopoDatabase> dbProvider;

  public DatabaseModule_ProvideRecordingRunDaoFactory(Provider<VopoDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public RecordingRunDao get() {
    return provideRecordingRunDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideRecordingRunDaoFactory create(
      Provider<VopoDatabase> dbProvider) {
    return new DatabaseModule_ProvideRecordingRunDaoFactory(dbProvider);
  }

  public static RecordingRunDao provideRecordingRunDao(VopoDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRecordingRunDao(db));
  }
}
