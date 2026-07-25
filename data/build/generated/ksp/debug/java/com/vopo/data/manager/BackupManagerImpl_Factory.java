package com.vopo.data.manager;

import android.content.Context;
import com.google.gson.Gson;
import com.vopo.data.local.DatabaseTransactionRunner;
import com.vopo.data.local.dao.EpisodeDao;
import com.vopo.data.local.dao.FavoriteDao;
import com.vopo.data.local.dao.MovieDao;
import com.vopo.data.local.dao.PlaybackHistoryDao;
import com.vopo.data.local.dao.ProviderDao;
import com.vopo.data.local.dao.RecordingScheduleDao;
import com.vopo.data.local.dao.VirtualGroupDao;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.data.security.CredentialCrypto;
import com.vopo.domain.manager.RecordingManager;
import com.vopo.domain.repository.CategoryRepository;
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
public final class BackupManagerImpl_Factory implements Factory<BackupManagerImpl> {
  private final Provider<Context> contextProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  private final Provider<CredentialCrypto> credentialCryptoProvider;

  private final Provider<ProviderDao> providerDaoProvider;

  private final Provider<FavoriteDao> favoriteDaoProvider;

  private final Provider<VirtualGroupDao> virtualGroupDaoProvider;

  private final Provider<PlaybackHistoryDao> playbackHistoryDaoProvider;

  private final Provider<MovieDao> movieDaoProvider;

  private final Provider<EpisodeDao> episodeDaoProvider;

  private final Provider<CategoryRepository> categoryRepositoryProvider;

  private final Provider<RecordingScheduleDao> recordingScheduleDaoProvider;

  private final Provider<RecordingManager> recordingManagerProvider;

  private final Provider<DatabaseTransactionRunner> transactionRunnerProvider;

  private final Provider<Gson> gsonProvider;

  public BackupManagerImpl_Factory(Provider<Context> contextProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<CredentialCrypto> credentialCryptoProvider,
      Provider<ProviderDao> providerDaoProvider, Provider<FavoriteDao> favoriteDaoProvider,
      Provider<VirtualGroupDao> virtualGroupDaoProvider,
      Provider<PlaybackHistoryDao> playbackHistoryDaoProvider, Provider<MovieDao> movieDaoProvider,
      Provider<EpisodeDao> episodeDaoProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<RecordingScheduleDao> recordingScheduleDaoProvider,
      Provider<RecordingManager> recordingManagerProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider, Provider<Gson> gsonProvider) {
    this.contextProvider = contextProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
    this.credentialCryptoProvider = credentialCryptoProvider;
    this.providerDaoProvider = providerDaoProvider;
    this.favoriteDaoProvider = favoriteDaoProvider;
    this.virtualGroupDaoProvider = virtualGroupDaoProvider;
    this.playbackHistoryDaoProvider = playbackHistoryDaoProvider;
    this.movieDaoProvider = movieDaoProvider;
    this.episodeDaoProvider = episodeDaoProvider;
    this.categoryRepositoryProvider = categoryRepositoryProvider;
    this.recordingScheduleDaoProvider = recordingScheduleDaoProvider;
    this.recordingManagerProvider = recordingManagerProvider;
    this.transactionRunnerProvider = transactionRunnerProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public BackupManagerImpl get() {
    return newInstance(contextProvider.get(), preferencesRepositoryProvider.get(), credentialCryptoProvider.get(), providerDaoProvider.get(), favoriteDaoProvider.get(), virtualGroupDaoProvider.get(), playbackHistoryDaoProvider.get(), movieDaoProvider.get(), episodeDaoProvider.get(), categoryRepositoryProvider.get(), recordingScheduleDaoProvider.get(), recordingManagerProvider.get(), transactionRunnerProvider.get(), gsonProvider.get());
  }

  public static BackupManagerImpl_Factory create(Provider<Context> contextProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider,
      Provider<CredentialCrypto> credentialCryptoProvider,
      Provider<ProviderDao> providerDaoProvider, Provider<FavoriteDao> favoriteDaoProvider,
      Provider<VirtualGroupDao> virtualGroupDaoProvider,
      Provider<PlaybackHistoryDao> playbackHistoryDaoProvider, Provider<MovieDao> movieDaoProvider,
      Provider<EpisodeDao> episodeDaoProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<RecordingScheduleDao> recordingScheduleDaoProvider,
      Provider<RecordingManager> recordingManagerProvider,
      Provider<DatabaseTransactionRunner> transactionRunnerProvider, Provider<Gson> gsonProvider) {
    return new BackupManagerImpl_Factory(contextProvider, preferencesRepositoryProvider, credentialCryptoProvider, providerDaoProvider, favoriteDaoProvider, virtualGroupDaoProvider, playbackHistoryDaoProvider, movieDaoProvider, episodeDaoProvider, categoryRepositoryProvider, recordingScheduleDaoProvider, recordingManagerProvider, transactionRunnerProvider, gsonProvider);
  }

  public static BackupManagerImpl newInstance(Context context,
      PreferencesRepository preferencesRepository, CredentialCrypto credentialCrypto,
      ProviderDao providerDao, FavoriteDao favoriteDao, VirtualGroupDao virtualGroupDao,
      PlaybackHistoryDao playbackHistoryDao, MovieDao movieDao, EpisodeDao episodeDao,
      CategoryRepository categoryRepository, RecordingScheduleDao recordingScheduleDao,
      RecordingManager recordingManager, DatabaseTransactionRunner transactionRunner, Gson gson) {
    return new BackupManagerImpl(context, preferencesRepository, credentialCrypto, providerDao, favoriteDao, virtualGroupDao, playbackHistoryDao, movieDao, episodeDao, categoryRepository, recordingScheduleDao, recordingManager, transactionRunner, gson);
  }
}
