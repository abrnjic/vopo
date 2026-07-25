package com.vopo.data.security;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class AndroidKeystoreCredentialCrypto_Factory implements Factory<AndroidKeystoreCredentialCrypto> {
  @Override
  public AndroidKeystoreCredentialCrypto get() {
    return newInstance();
  }

  public static AndroidKeystoreCredentialCrypto_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AndroidKeystoreCredentialCrypto newInstance() {
    return new AndroidKeystoreCredentialCrypto();
  }

  private static final class InstanceHolder {
    static final AndroidKeystoreCredentialCrypto_Factory INSTANCE = new AndroidKeystoreCredentialCrypto_Factory();
  }
}
