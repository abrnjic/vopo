package com.vopo.data.repository;

import android.content.Context;
import com.google.firebase.firestore.FirebaseFirestore;
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
public final class LicenseRepositoryImpl_Factory implements Factory<LicenseRepositoryImpl> {
  private final Provider<Context> contextProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  public LicenseRepositoryImpl_Factory(Provider<Context> contextProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    this.contextProvider = contextProvider;
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public LicenseRepositoryImpl get() {
    return newInstance(contextProvider.get(), firestoreProvider.get());
  }

  public static LicenseRepositoryImpl_Factory create(Provider<Context> contextProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    return new LicenseRepositoryImpl_Factory(contextProvider, firestoreProvider);
  }

  public static LicenseRepositoryImpl newInstance(Context context, FirebaseFirestore firestore) {
    return new LicenseRepositoryImpl(context, firestore);
  }
}
