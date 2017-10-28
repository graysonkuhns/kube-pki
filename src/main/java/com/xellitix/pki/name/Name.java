package com.xellitix.pki.name;

import java.io.Serializable;

/**
 * Certificate name model.
 *
 * @author Grayson Kuhns
 */
public interface Name extends Serializable {

  /**
   * Gets the country.
   *
   * @return The country.
   */
  String getCountry();

  /**
   * Gets the state.
   *
   * @return The state.
   */
  String getState();

  /**
   * Gets the locality.
   *
   * @return The locality.
   */
  String getLocality();

  /**
   * Gets the organization.
   *
   * @return The organization.
   */
  String getOrganization();

  /**
   * Gets the organizational unit.
   *
   * @return The organizational unit.
   */
  String getOrganizationalUnit();
}
