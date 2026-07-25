package com.vopo.data.preferences;

import android.content.Context;
import com.vopo.data.local.dao.ChannelPreferenceDao;
import com.vopo.data.local.dao.SearchHistoryDao;
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
public final class PreferencesRepository_Factory implements Factory<PreferencesRepository> {
  private final Provider<Context> contextProvider;

  private final Provider<ChannelPreferenceDao> channelPreferenceDaoProvider;

  private final Provider<SearchHistoryDao> searchHistoryDaoProvider;

  public PreferencesRepository_Factory(Provider<Context> contextProvider,
      Provider<ChannelPreferenceDao> channelPreferenceDaoProvider,
      Provider<SearchHistoryDao> searchHistoryDaoProvider) {
    this.contextProvider = contextProvider;
    this.channelPreferenceDaoProvider = channelPreferenceDaoProvider;
    this.searchHistoryDaoProvider = searchHistoryDaoProvider;
  }

  @Override
  public PreferencesRepository get() {
    return newInstance(contextProvider.get(), channelPreferenceDaoProvider.get(), searchHistoryDaoProvider.get());
  }

  public static PreferencesRepository_Factory create(Provider<Context> contextProvider,
      Provider<ChannelPreferenceDao> channelPreferenceDaoProvider,
      Provider<SearchHistoryDao> searchHistoryDaoProvider) {
    return new PreferencesRepository_Factory(contextProvider, channelPreferenceDaoProvider, searchHistoryDaoProvider);
  }

  public static PreferencesRepository newInstance(Context context,
      ChannelPreferenceDao channelPreferenceDao, SearchHistoryDao searchHistoryDao) {
    return new PreferencesRepository(context, channelPreferenceDao, searchHistoryDao);
  }
}
