package com.xellitix.pki.csr;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.xellitix.pki.name.Name;
import java.util.List;
import java.util.Set;

/**
 * Default {@link CertificateSigningRequest} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultCertificateSigningRequest implements CertificateSigningRequest {

  // Properties
  private final String commonName;
  private final List<Name> names;
  private final Set<String> hosts;

  /**
   * Constructor.
   *
   * @param commonName The common name.
   * @param names The {@link Name}s.
   * @param hosts The hosts.
   */
  @Inject
  DefaultCertificateSigningRequest(
      @Assisted final String commonName,
      @Assisted final List<Name> names,
      @Assisted final Set<String> hosts) {

    this.commonName = commonName;
    this.names = names;
    this.hosts = hosts;
  }

  /**
   * Gets the common name.
   *
   * @return The common name.
   */
  @Override
  public String getCommonName() {
    return commonName;
  }

  /**
   * Gets the {@link Name}s.
   *
   * @return The {@link Name}s.
   */
  @Override
  public List<Name> getNames() {
    return names;
  }

  /**
   * Gets the hosts the certificate will be valid for.
   *
   * @return The hosts.
   */
  @Override
  public Set<String> getHosts() {
    return hosts;
  }
}
