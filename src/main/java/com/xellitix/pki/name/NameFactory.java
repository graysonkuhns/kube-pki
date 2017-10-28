package com.xellitix.pki.name;

import com.google.inject.assistedinject.Assisted;

/**
 * {@link Name} factory.
 *
 * @author Grayson Kuhns
 */
public interface NameFactory {

  /**
   * Creates a {@link Name}.
   *
   * @param country The country.
   * @param state The state.
   * @param locality The locality.
   * @param organization The organization.
   * @param organizationalUnit The organizational unit.
   * @return The {@link Name}.
   */
  Name createName(@Assisted("country") String country,
                  @Assisted("state") String state,
                  @Assisted("locality") String locality,
                  @Assisted("organization") String organization,
                  @Assisted("organizationalUnit") String organizationalUnit);
}
