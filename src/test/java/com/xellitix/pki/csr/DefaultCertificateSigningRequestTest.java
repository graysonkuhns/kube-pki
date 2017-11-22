package com.xellitix.pki.csr;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.xellitix.pki.name.Name;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@link DefaultCertificateSigningRequest} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultCertificateSigningRequestTest {

  // Constants
  private static final String COMMON_NAME = "ACME Corporation";

  // Fixtures
  private List<Name> names;
  private Set<String> hosts;
  private DefaultCertificateSigningRequest csr;

  @Test
  public void getCommonNameTest() {
    assertThat(csr
        .getCommonName())
        .isNotNull()
        .isEqualTo(COMMON_NAME);
  }

  @Test
  public void getNamesTest() {
    assertThat(csr
        .getNames())
        .isNotNull()
        .isEqualTo(names);
  }

  @Test
  public void getHostsTest() {
    assertThat(csr
        .getHosts())
        .isNotNull()
        .isEqualTo(hosts);
  }

  @Before
  public void setUp() {
    // Create the list of names
    names = ImmutableList.of();

    // Create the set of hosts
    hosts = ImmutableSet.of();

    // Create the CSR
    csr = new DefaultCertificateSigningRequest(COMMON_NAME, names, hosts);
  }
}
