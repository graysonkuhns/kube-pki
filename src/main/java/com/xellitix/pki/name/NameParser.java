package com.xellitix.pki.name;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * {@link Name} parser.
 *
 * @author Grayson Kuhns
 */
public interface NameParser {

  /**
   * Parses a {@link Name}.
   *
   * @param name The {@link Name} data.
   * @return The {@link Name}.
   */
  Name parse(JsonNode name);
}
