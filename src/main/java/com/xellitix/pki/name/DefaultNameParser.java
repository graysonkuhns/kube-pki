package com.xellitix.pki.name;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import java.util.Set;

/**
 * Default {@link NameParser} implementation.
 *
 * @author Grayson Kuhns
 */
public class DefaultNameParser implements NameParser {

  // JSON keys
  private static final Set<String> COUNTRY_KEYS = ImmutableSet.of("C", "country");
  private static final Set<String> STATE_KEYS = ImmutableSet.of("ST", "state");
  private static final Set<String> LOCALITY_KEYS = ImmutableSet.of("L", "locality");
  private static final Set<String> ORG_KEYS = ImmutableSet.of("O", "organization");
  private static final Set<String> ORG_UNIT_KEYS = ImmutableSet.of("OU", "organizationalUnit");

  // Messages
  private static final String NON_OBJECT_MSG = "Expected name to be a JSON object";
  private static final String UNDEFINED_PROPERTY_MSG_TEMPLATE = "Expected property \"%s\" to be defined";
  private static final String NON_STRING_PROPERTY_MSG_TEMPLATE = "Expected property \"%s\" to be a String";

  // Dependencies
  private final NameFactory nameFactory;

  /**
   * Constructor.
   *
   * @param nameFactory The {@link NameFactory}.
   */
  @Inject
  DefaultNameParser(final NameFactory nameFactory) {
    this.nameFactory = nameFactory;
  }

  /**
   * Parses a {@link Name}.
   *
   * @param name The {@link Name} data.
   * @return The {@link Name}.
   */
  @Override
  public Name parse(final JsonNode name) {

    // Verify the JSON data represents an object
    if (!name.isObject()) {
      throw new IllegalArgumentException(NON_OBJECT_MSG);
    }

    // Parse the properties
    final String country = getProperty(name, COUNTRY_KEYS, "country");
    final String state = getProperty(name, STATE_KEYS, "state");
    final String locality = getProperty(name, LOCALITY_KEYS, "locality");
    final String organization = getProperty(name, ORG_KEYS, "organization");
    final String organizationalUnit = getProperty(name, ORG_UNIT_KEYS, "organizationalUnit");

    // Create the name
    return nameFactory.create(country, state, locality, organization, organizationalUnit);
  }

  private String getProperty(final JsonNode object, final Set<String> keys, final String propertyName) {
    final String key = keys
        .stream()
        .filter(object::has)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(
            String.format(UNDEFINED_PROPERTY_MSG_TEMPLATE, propertyName)));

    // Get the property
    final JsonNode property = object.get(key);

    // Validate the property
    if (!property.isTextual()) {
      throw new IllegalArgumentException(
          String.format(NON_STRING_PROPERTY_MSG_TEMPLATE, propertyName));
    }

    return property.asText();
  }
}
