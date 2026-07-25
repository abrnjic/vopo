package com.vopo.domain.usecase;

import com.vopo.domain.manager.ProviderSyncStateReader;
import com.vopo.domain.repository.SearchRepository;
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
public final class SearchContent_Factory implements Factory<SearchContent> {
  private final Provider<SearchRepository> searchRepositoryProvider;

  private final Provider<ProviderSyncStateReader> providerSyncStateReaderProvider;

  public SearchContent_Factory(Provider<SearchRepository> searchRepositoryProvider,
      Provider<ProviderSyncStateReader> providerSyncStateReaderProvider) {
    this.searchRepositoryProvider = searchRepositoryProvider;
    this.providerSyncStateReaderProvider = providerSyncStateReaderProvider;
  }

  @Override
  public SearchContent get() {
    return newInstance(searchRepositoryProvider.get(), providerSyncStateReaderProvider.get());
  }

  public static SearchContent_Factory create(Provider<SearchRepository> searchRepositoryProvider,
      Provider<ProviderSyncStateReader> providerSyncStateReaderProvider) {
    return new SearchContent_Factory(searchRepositoryProvider, providerSyncStateReaderProvider);
  }

  public static SearchContent newInstance(SearchRepository searchRepository,
      ProviderSyncStateReader providerSyncStateReader) {
    return new SearchContent(searchRepository, providerSyncStateReader);
  }
}
