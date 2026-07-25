package com.vopo.data.repository;

import com.vopo.data.local.dao.CombinedM3uProfileDao;
import com.vopo.data.local.dao.CombinedM3uProfileMemberDao;
import com.vopo.data.local.dao.ProviderDao;
import com.vopo.data.preferences.PreferencesRepository;
import com.vopo.domain.repository.ChannelRepository;
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
public final class CombinedM3uRepositoryImpl_Factory implements Factory<CombinedM3uRepositoryImpl> {
  private final Provider<CombinedM3uProfileDao> profileDaoProvider;

  private final Provider<CombinedM3uProfileMemberDao> memberDaoProvider;

  private final Provider<ProviderDao> providerDaoProvider;

  private final Provider<ChannelRepository> channelRepositoryProvider;

  private final Provider<PreferencesRepository> preferencesRepositoryProvider;

  public CombinedM3uRepositoryImpl_Factory(Provider<CombinedM3uProfileDao> profileDaoProvider,
      Provider<CombinedM3uProfileMemberDao> memberDaoProvider,
      Provider<ProviderDao> providerDaoProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider) {
    this.profileDaoProvider = profileDaoProvider;
    this.memberDaoProvider = memberDaoProvider;
    this.providerDaoProvider = providerDaoProvider;
    this.channelRepositoryProvider = channelRepositoryProvider;
    this.preferencesRepositoryProvider = preferencesRepositoryProvider;
  }

  @Override
  public CombinedM3uRepositoryImpl get() {
    return newInstance(profileDaoProvider.get(), memberDaoProvider.get(), providerDaoProvider.get(), channelRepositoryProvider.get(), preferencesRepositoryProvider.get());
  }

  public static CombinedM3uRepositoryImpl_Factory create(
      Provider<CombinedM3uProfileDao> profileDaoProvider,
      Provider<CombinedM3uProfileMemberDao> memberDaoProvider,
      Provider<ProviderDao> providerDaoProvider,
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<PreferencesRepository> preferencesRepositoryProvider) {
    return new CombinedM3uRepositoryImpl_Factory(profileDaoProvider, memberDaoProvider, providerDaoProvider, channelRepositoryProvider, preferencesRepositoryProvider);
  }

  public static CombinedM3uRepositoryImpl newInstance(CombinedM3uProfileDao profileDao,
      CombinedM3uProfileMemberDao memberDao, ProviderDao providerDao,
      ChannelRepository channelRepository, PreferencesRepository preferencesRepository) {
    return new CombinedM3uRepositoryImpl(profileDao, memberDao, providerDao, channelRepository, preferencesRepository);
  }
}
