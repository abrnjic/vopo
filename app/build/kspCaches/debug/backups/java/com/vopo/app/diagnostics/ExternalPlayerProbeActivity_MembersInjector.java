package com.vopo.app.diagnostics;

import com.vopo.domain.repository.ChannelRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

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
public final class ExternalPlayerProbeActivity_MembersInjector implements MembersInjector<ExternalPlayerProbeActivity> {
  private final Provider<ChannelRepository> channelRepositoryProvider;

  public ExternalPlayerProbeActivity_MembersInjector(
      Provider<ChannelRepository> channelRepositoryProvider) {
    this.channelRepositoryProvider = channelRepositoryProvider;
  }

  public static MembersInjector<ExternalPlayerProbeActivity> create(
      Provider<ChannelRepository> channelRepositoryProvider) {
    return new ExternalPlayerProbeActivity_MembersInjector(channelRepositoryProvider);
  }

  @Override
  public void injectMembers(ExternalPlayerProbeActivity instance) {
    injectChannelRepository(instance, channelRepositoryProvider.get());
  }

  @InjectedFieldSignature("com.vopo.app.diagnostics.ExternalPlayerProbeActivity.channelRepository")
  public static void injectChannelRepository(ExternalPlayerProbeActivity instance,
      ChannelRepository channelRepository) {
    instance.channelRepository = channelRepository;
  }
}
