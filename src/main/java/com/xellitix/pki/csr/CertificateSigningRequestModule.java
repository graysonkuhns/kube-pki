package com.xellitix.pki.csr;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

/**
 * {@link CertificateSigningRequest} Google Guice module.
 *
 * @author Grayson Kuhns
 */
public class CertificateSigningRequestModule extends AbstractModule {

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    install(new FactoryModuleBuilder()
      .implement(CertificateSigningRequest.class, DefaultCertificateSigningRequest.class)
      .build(CertificateSigningRequestFactory.class));
  }
}
