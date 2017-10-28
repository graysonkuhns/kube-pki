package com.xellitix.pki.name;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link NameModule} test case.
 *
 * @author Grayson Kuhns
 */
public class NameModuleTest {

  // Fixtures
  private Injector injector;

  @Test
  public void getNameFactoryTest() {
    injector.getInstance(NameFactory.class);
  }

  @Before
  public void setUp() {
    injector = Guice.createInjector(new NameModule());
  }
}
