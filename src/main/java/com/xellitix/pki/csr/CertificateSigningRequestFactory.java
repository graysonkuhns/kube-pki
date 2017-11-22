package com.xellitix.pki.csr;

import com.xellitix.pki.name.Name;
import java.util.List;
import java.util.Set;

public interface CertificateSigningRequestFactory {

  /**
   * Creates a {@link CertificateSigningRequest}.
   *
   * @param commonName The common name.
   * @param names The {@link Name}s.
   * @param hosts The hosts.
   * @return The {@link CertificateSigningRequest}.
   */
  CertificateSigningRequest create(String commonName, List<Name> names, Set<String> hosts);
}
