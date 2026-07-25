package com.vopo.app.ui.screens.provider;

import com.vopo.app.pairing.ProviderQrPairingManager;
import com.vopo.domain.manager.DriveBackupSyncManager;
import com.vopo.domain.repository.CombinedM3uRepository;
import com.vopo.domain.repository.ProviderRepository;
import com.vopo.domain.usecase.ImportBackup;
import com.vopo.domain.usecase.ValidateAndAddProvider;
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
public final class ProviderSetupViewModel_Factory implements Factory<ProviderSetupViewModel> {
  private final Provider<ProviderRepository> providerRepositoryProvider;

  private final Provider<CombinedM3uRepository> combinedM3uRepositoryProvider;

  private final Provider<ValidateAndAddProvider> validateAndAddProvider;

  private final Provider<ImportBackup> importBackupProvider;

  private final Provider<DriveBackupSyncManager> driveBackupSyncManagerProvider;

  private final Provider<ProviderQrPairingManager> providerQrPairingManagerProvider;

  public ProviderSetupViewModel_Factory(Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<ValidateAndAddProvider> validateAndAddProvider,
      Provider<ImportBackup> importBackupProvider,
      Provider<DriveBackupSyncManager> driveBackupSyncManagerProvider,
      Provider<ProviderQrPairingManager> providerQrPairingManagerProvider) {
    this.providerRepositoryProvider = providerRepositoryProvider;
    this.combinedM3uRepositoryProvider = combinedM3uRepositoryProvider;
    this.validateAndAddProvider = validateAndAddProvider;
    this.importBackupProvider = importBackupProvider;
    this.driveBackupSyncManagerProvider = driveBackupSyncManagerProvider;
    this.providerQrPairingManagerProvider = providerQrPairingManagerProvider;
  }

  @Override
  public ProviderSetupViewModel get() {
    return newInstance(providerRepositoryProvider.get(), combinedM3uRepositoryProvider.get(), validateAndAddProvider.get(), importBackupProvider.get(), driveBackupSyncManagerProvider.get(), providerQrPairingManagerProvider.get());
  }

  public static ProviderSetupViewModel_Factory create(
      Provider<ProviderRepository> providerRepositoryProvider,
      Provider<CombinedM3uRepository> combinedM3uRepositoryProvider,
      Provider<ValidateAndAddProvider> validateAndAddProvider,
      Provider<ImportBackup> importBackupProvider,
      Provider<DriveBackupSyncManager> driveBackupSyncManagerProvider,
      Provider<ProviderQrPairingManager> providerQrPairingManagerProvider) {
    return new ProviderSetupViewModel_Factory(providerRepositoryProvider, combinedM3uRepositoryProvider, validateAndAddProvider, importBackupProvider, driveBackupSyncManagerProvider, providerQrPairingManagerProvider);
  }

  public static ProviderSetupViewModel newInstance(ProviderRepository providerRepository,
      CombinedM3uRepository combinedM3uRepository, ValidateAndAddProvider validateAndAddProvider,
      ImportBackup importBackup, DriveBackupSyncManager driveBackupSyncManager,
      ProviderQrPairingManager providerQrPairingManager) {
    return new ProviderSetupViewModel(providerRepository, combinedM3uRepository, validateAndAddProvider, importBackup, driveBackupSyncManager, providerQrPairingManager);
  }
}
