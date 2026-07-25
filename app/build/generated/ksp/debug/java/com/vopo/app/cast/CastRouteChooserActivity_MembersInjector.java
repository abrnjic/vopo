package com.vopo.app.cast;

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
public final class CastRouteChooserActivity_MembersInjector implements MembersInjector<CastRouteChooserActivity> {
  private final Provider<CastManager> castManagerProvider;

  public CastRouteChooserActivity_MembersInjector(Provider<CastManager> castManagerProvider) {
    this.castManagerProvider = castManagerProvider;
  }

  public static MembersInjector<CastRouteChooserActivity> create(
      Provider<CastManager> castManagerProvider) {
    return new CastRouteChooserActivity_MembersInjector(castManagerProvider);
  }

  @Override
  public void injectMembers(CastRouteChooserActivity instance) {
    injectCastManager(instance, castManagerProvider.get());
  }

  @InjectedFieldSignature("com.vopo.app.cast.CastRouteChooserActivity.castManager")
  public static void injectCastManager(CastRouteChooserActivity instance, CastManager castManager) {
    instance.castManager = castManager;
  }
}
