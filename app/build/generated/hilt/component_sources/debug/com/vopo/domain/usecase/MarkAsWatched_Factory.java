package com.vopo.domain.usecase;

import com.vopo.domain.repository.PlaybackHistoryRepository;
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
public final class MarkAsWatched_Factory implements Factory<MarkAsWatched> {
  private final Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider;

  public MarkAsWatched_Factory(
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider) {
    this.playbackHistoryRepositoryProvider = playbackHistoryRepositoryProvider;
  }

  @Override
  public MarkAsWatched get() {
    return newInstance(playbackHistoryRepositoryProvider.get());
  }

  public static MarkAsWatched_Factory create(
      Provider<PlaybackHistoryRepository> playbackHistoryRepositoryProvider) {
    return new MarkAsWatched_Factory(playbackHistoryRepositoryProvider);
  }

  public static MarkAsWatched newInstance(PlaybackHistoryRepository playbackHistoryRepository) {
    return new MarkAsWatched(playbackHistoryRepository);
  }
}
