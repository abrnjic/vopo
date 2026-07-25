package com.vopo.data.manager;

import android.content.Context;
import com.vopo.domain.manager.BackupManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class GoogleDriveBackupSyncManager_Factory implements Factory<GoogleDriveBackupSyncManager> {
  private final Provider<Context> contextProvider;

  private final Provider<BackupManager> backupManagerProvider;

  public GoogleDriveBackupSyncManager_Factory(Provider<Context> contextProvider,
      Provider<BackupManager> backupManagerProvider) {
    this.contextProvider = contextProvider;
    this.backupManagerProvider = backupManagerProvider;
  }

  @Override
  public GoogleDriveBackupSyncManager get() {
    return newInstance(contextProvider.get(), backupManagerProvider.get());
  }

  public static GoogleDriveBackupSyncManager_Factory create(Provider<Context> contextProvider,
      Provider<BackupManager> backupManagerProvider) {
    return new GoogleDriveBackupSyncManager_Factory(contextProvider, backupManagerProvider);
  }

  public static GoogleDriveBackupSyncManager newInstance(Context context,
      BackupManager backupManager) {
    return new GoogleDriveBackupSyncManager(context, backupManager);
  }
}
