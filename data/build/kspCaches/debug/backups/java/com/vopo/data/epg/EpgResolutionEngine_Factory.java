package com.vopo.data.epg;

import com.vopo.data.local.dao.ChannelDao;
import com.vopo.data.local.dao.ChannelEpgMappingDao;
import com.vopo.data.local.dao.EpgChannelDao;
import com.vopo.data.local.dao.EpgProgrammeDao;
import com.vopo.data.local.dao.ProgramDao;
import com.vopo.data.local.dao.ProviderEpgSourceDao;
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
public final class EpgResolutionEngine_Factory implements Factory<EpgResolutionEngine> {
  private final Provider<ChannelDao> channelDaoProvider;

  private final Provider<ChannelEpgMappingDao> channelEpgMappingDaoProvider;

  private final Provider<ProviderEpgSourceDao> providerEpgSourceDaoProvider;

  private final Provider<EpgChannelDao> epgChannelDaoProvider;

  private final Provider<EpgProgrammeDao> epgProgrammeDaoProvider;

  private final Provider<ProgramDao> programDaoProvider;

  public EpgResolutionEngine_Factory(Provider<ChannelDao> channelDaoProvider,
      Provider<ChannelEpgMappingDao> channelEpgMappingDaoProvider,
      Provider<ProviderEpgSourceDao> providerEpgSourceDaoProvider,
      Provider<EpgChannelDao> epgChannelDaoProvider,
      Provider<EpgProgrammeDao> epgProgrammeDaoProvider, Provider<ProgramDao> programDaoProvider) {
    this.channelDaoProvider = channelDaoProvider;
    this.channelEpgMappingDaoProvider = channelEpgMappingDaoProvider;
    this.providerEpgSourceDaoProvider = providerEpgSourceDaoProvider;
    this.epgChannelDaoProvider = epgChannelDaoProvider;
    this.epgProgrammeDaoProvider = epgProgrammeDaoProvider;
    this.programDaoProvider = programDaoProvider;
  }

  @Override
  public EpgResolutionEngine get() {
    return newInstance(channelDaoProvider.get(), channelEpgMappingDaoProvider.get(), providerEpgSourceDaoProvider.get(), epgChannelDaoProvider.get(), epgProgrammeDaoProvider.get(), programDaoProvider.get());
  }

  public static EpgResolutionEngine_Factory create(Provider<ChannelDao> channelDaoProvider,
      Provider<ChannelEpgMappingDao> channelEpgMappingDaoProvider,
      Provider<ProviderEpgSourceDao> providerEpgSourceDaoProvider,
      Provider<EpgChannelDao> epgChannelDaoProvider,
      Provider<EpgProgrammeDao> epgProgrammeDaoProvider, Provider<ProgramDao> programDaoProvider) {
    return new EpgResolutionEngine_Factory(channelDaoProvider, channelEpgMappingDaoProvider, providerEpgSourceDaoProvider, epgChannelDaoProvider, epgProgrammeDaoProvider, programDaoProvider);
  }

  public static EpgResolutionEngine newInstance(ChannelDao channelDao,
      ChannelEpgMappingDao channelEpgMappingDao, ProviderEpgSourceDao providerEpgSourceDao,
      EpgChannelDao epgChannelDao, EpgProgrammeDao epgProgrammeDao, ProgramDao programDao) {
    return new EpgResolutionEngine(channelDao, channelEpgMappingDao, providerEpgSourceDao, epgChannelDao, epgProgrammeDao, programDao);
  }
}
