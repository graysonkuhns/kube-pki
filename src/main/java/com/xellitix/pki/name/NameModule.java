package com.xellitix.pki.name;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * {@link Name} Google Guice module.
 *
 * @author Grayson Kuhns
 */
public class NameModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    install(new FactoryModuleBuilder()
      .implement(Name.class, DefaultName.class)
      .build(NameFactory.class));
  }
}
