package com.xellitix.pki.name;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link DefaultName} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultNameTest {

  // Constants
  private static final String COUNTRY = "United States";
  private static final String STATE = "Missouri";
  private static final String LOCALITY = "Kansas City";
  private static final String ORGANIZATION = "Xellitix";
  private static final String ORGANIZATIONAL_UNIT = "Xellitix DevOps";

  // Fixtures
  private DefaultName name;

  @Test
  public void getCountryTest() {
    assertThat(name
        .getCountry())
        .isNotNull()
        .isEqualTo(COUNTRY);
  }

  @Test
  public void getStateTest() {
    assertThat(name
        .getState())
        .isNotNull()
        .isEqualTo(STATE);
  }

  @Test
  public void getLocalityTest() {
    assertThat(name
        .getLocality())
        .isNotNull()
        .isEqualTo(LOCALITY);
  }

  @Test
  public void getOrganizationTest() {
    assertThat(name
        .getOrganization())
        .isNotNull()
        .isEqualTo(ORGANIZATION);
  }

  @Test
  public void getOrganizationalUnitTest() {
    assertThat(name
        .getOrganizationalUnit())
        .isNotNull()
        .isEqualTo(ORGANIZATIONAL_UNIT);
  }

  @Before
  public void setUp() {
    name = new DefaultName(COUNTRY, STATE, LOCALITY, ORGANIZATION, ORGANIZATIONAL_UNIT);
  }
}
