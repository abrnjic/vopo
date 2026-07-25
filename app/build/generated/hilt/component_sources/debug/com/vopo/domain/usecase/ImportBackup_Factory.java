package com.vopo.domain.usecase;

import com.vopo.domain.manager.BackupManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class ImportBackup_Factory implements Factory<ImportBackup> {
  private final Provider<BackupManager> backupManagerProvider;

  public ImportBackup_Factory(Provider<BackupManager> backupManagerProvider) {
    this.backupManagerProvider = backupManagerProvider;
  }

  @Override
  public ImportBackup get() {
    return newInstance(backupManagerProvider.get());
  }

  public static ImportBackup_Factory create(Provider<BackupManager> backupManagerProvider) {
    return new ImportBackup_Factory(backupManagerProvider);
  }

  public static ImportBackup newInstance(BackupManager backupManager) {
    return new ImportBackup(backupManager);
  }
}
