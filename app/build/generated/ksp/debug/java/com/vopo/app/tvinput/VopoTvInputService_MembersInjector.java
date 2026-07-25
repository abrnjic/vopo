package com.vopo.app.tvinput;

import com.vopo.domain.repository.ChannelRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class VopoTvInputService_MembersInjector implements MembersInjector<VopoTvInputService> {
  private final Provider<ChannelRepository> channelRepositoryProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  public VopoTvInputService_MembersInjector(Provider<ChannelRepository> channelRepositoryProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    this.channelRepositoryProvider = channelRepositoryProvider;
    this.okHttpClientProvider = okHttpClientProvider;
  }

  public static MembersInjector<VopoTvInputService> create(
      Provider<ChannelRepository> channelRepositoryProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    return new VopoTvInputService_MembersInjector(channelRepositoryProvider, okHttpClientProvider);
  }

  @Override
  public void injectMembers(VopoTvInputService instance) {
    injectChannelRepository(instance, channelRepositoryProvider.get());
    injectOkHttpClient(instance, okHttpClientProvider.get());
  }

  @InjectedFieldSignature("com.vopo.app.tvinput.VopoTvInputService.channelRepository")
  public static void injectChannelRepository(VopoTvInputService instance,
      ChannelRepository channelRepository) {
    instance.channelRepository = channelRepository;
  }

  @InjectedFieldSignature("com.vopo.app.tvinput.VopoTvInputService.okHttpClient")
  public static void injectOkHttpClient(VopoTvInputService instance, OkHttpClient okHttpClient) {
    instance.okHttpClient = okHttpClient;
  }
}
