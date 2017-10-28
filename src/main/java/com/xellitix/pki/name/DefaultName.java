package com.xellitix.pki.name;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Default {@link Name} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultName implements Name {

  // Properties
  private final String country;
  private final String state;
  private final String locality;
  private final String organization;
  private final String organizationalUnit;

  /**
   * Constructor.
   *
   * @param country The country.
   * @param state The state.
   * @param locality The locality.
   * @param organization The organization.
   * @param organizationalUnit The organizational unit.
   */
  @Inject
  DefaultName(
      @Assisted("country") final String country,
      @Assisted("state") final String state,
      @Assisted("locality") final String locality,
      @Assisted("organization") final String organization,
      @Assisted("organizationalUnit") final String organizationalUnit) {
    this.country = country;
    this.state = state;
    this.locality = locality;
    this.organization = organization;
    this.organizationalUnit = organizationalUnit;
  }

  /**
   * Gets the country.
   *
   * @return The country.
   */
  @Override
  public String getCountry() {
    return country;
  }

  /**
   * Gets the state.
   *
   * @return The state.
   */
  @Override
  public String getState() {
    return state;
  }

  /**
   * Gets the locality.
   *
   * @return The locality.
   */
  @Override
  public String getLocality() {
    return locality;
  }

  /**
   * Gets the organization.
   *
   * @return The organization.
   */
  @Override
  public String getOrganization() {
    return organization;
  }

  /**
   * Gets the organizational unit.
   *
   * @return The organizational unit.
   */
  @Override
  public String getOrganizationalUnit() {
    return organizationalUnit;
  }
}
