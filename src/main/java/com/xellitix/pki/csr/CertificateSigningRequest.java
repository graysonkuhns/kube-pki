package com.xellitix.pki.csr;

import com.xellitix.pki.name.Name;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Certificate Signing Request model.
 *
 * @author Grayson Kuhns
 */
public interface CertificateSigningRequest extends Serializable {

  /**
   * Gets the common name.
   *
   * @return The common name.
   */
  String getCommonName();

  /**
   * Gets the {@link Name}s.
   *
   * @return The {@link Name}s.
   */
  List<Name> getNames();

  /**
   * Gets the hosts the certificate will be valid for.
   *
   * @return The hosts.
   */
  Set<String> getHosts();
}
