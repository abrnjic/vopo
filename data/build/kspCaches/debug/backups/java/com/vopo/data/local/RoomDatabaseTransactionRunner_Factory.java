package com.vopo.data.local;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class RoomDatabaseTransactionRunner_Factory implements Factory<RoomDatabaseTransactionRunner> {
  private final Provider<VopoDatabase> databaseProvider;

  public RoomDatabaseTransactionRunner_Factory(Provider<VopoDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RoomDatabaseTransactionRunner get() {
    return newInstance(databaseProvider.get());
  }

  public static RoomDatabaseTransactionRunner_Factory create(
      Provider<VopoDatabase> databaseProvider) {
    return new RoomDatabaseTransactionRunner_Factory(databaseProvider);
  }

  public static RoomDatabaseTransactionRunner newInstance(VopoDatabase database) {
    return new RoomDatabaseTransactionRunner(database);
  }
}
