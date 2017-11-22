package com.xellitix.pki.csr;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link CertificateSigningRequestModule} test case.
 *
 * @author Grayson Kuhns
 */
public class CertificateSigningRequestModuleTest {

  // Fixtures
  private Injector injector;

  @Test
  public void getCertificateSigningRequestFactoryTest() {
    injector.getInstance(CertificateSigningRequestFactory.class);
  }

  @Before
  public void setUp() {
    injector = Guice.createInjector(new CertificateSigningRequestModule());
  }
}
