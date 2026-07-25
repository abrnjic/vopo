package com.vopo.app.di;

import com.vopo.data.parser.XmltvParser;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class NetworkModule_ProvideXmltvParserFactory implements Factory<XmltvParser> {
  @Override
  public XmltvParser get() {
    return provideXmltvParser();
  }

  public static NetworkModule_ProvideXmltvParserFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static XmltvParser provideXmltvParser() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideXmltvParser());
  }

  private static final class InstanceHolder {
    static final NetworkModule_ProvideXmltvParserFactory INSTANCE = new NetworkModule_ProvideXmltvParserFactory();
  }
}
