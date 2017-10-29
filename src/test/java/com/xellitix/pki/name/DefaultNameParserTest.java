package com.xellitix.pki.name;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * {@link DefaultNameParser} test case.
 *
 * @author Grayson Kuhns
 */
public class DefaultNameParserTest {

  // JSON schemas
  private static final String SCHEMA_ONE_COUNTRY_KEY = "country";
  private static final String SCHEMA_ONE_STATE_KEY = "state";
  private static final String SCHEMA_ONE_LOCALITY_KEY = "locality";
  private static final String SCHEMA_ONE_ORG_KEY = "organization";
  private static final String SCHEMA_ONE_ORG_UNIT_KEY = "organizationalUnit";

  private static final String SCHEMA_TWO_COUNTRY_KEY = "C";
  private static final String SCHEMA_TWO_STATE_KEY = "ST";
  private static final String SCHEMA_TWO_LOCALITY_KEY = "L";
  private static final String SCHEMA_TWO_ORG_KEY = "O";
  private static final String SCHEMA_TWO_ORG_UNIT_KEY = "OU";

  private static final String COUNTRY = "United States";
  private static final String STATE = "Missouri";
  private static final String LOCALITY = "Kansas City";
  private static final String ORG = "Xellitix";
  private static final String ORG_UNIT = "Xellitix DevOps";

  private static final String NON_OBJECT_MSG = "Expected name to be a JSON object";
  private static final String UNDEFINED_COUNTRY_MSG = "Expected property \"country\" to be defined";
  private static final String NON_STRING_COUNTRY_MSG = "Expected property \"country\" to be a String";

  // Rules
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // Fixtures
  private ObjectNode schemaOne;
  private ObjectNode schemaTwo;

  private Name name;
  private NameFactory factory;
  private DefaultNameParser parser;

  @Test
  public void parseSchemaOneTest() {
    assertThat(parser
        .parse(schemaOne))
        .isNotNull()
        .isEqualTo(name);

    // Verify the parsed values
    verify(factory).create(
        eq(COUNTRY),
        eq(STATE),
        eq(LOCALITY),
        eq(ORG),
        eq(ORG_UNIT)
    );
  }

  @Test
  public void parseSchemaTwoTest() {
    assertThat(parser
        .parse(schemaTwo))
        .isNotNull()
        .isEqualTo(name);

    // Verify the parsed values
    verify(factory).create(
        eq(COUNTRY),
        eq(STATE),
        eq(LOCALITY),
        eq(ORG),
        eq(ORG_UNIT)
    );
  }

  @Test
  public void parseNonObjectCausesExceptionTest() {
    // Describe the exception to expect
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(NON_OBJECT_MSG);

    // Prepare the environment
    ArrayNode data = new ObjectMapper().createArrayNode();

    // Attempt to parse data
    parser.parse(data);
  }

  @Test
  public void undefinedPropertyCausesExceptionTest() {
    // Describe the exception to expect
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(UNDEFINED_COUNTRY_MSG);

    // Prepare the environment
    schemaOne.remove(SCHEMA_ONE_COUNTRY_KEY);

    // Attempt to parse data
    parser.parse(schemaOne);
  }

  @Test
  public void nonStringPropertyCausesExceptionTest() {
    // Describe the exception to expect
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage(NON_STRING_COUNTRY_MSG);

    // Prepare the environment
    schemaOne.put(SCHEMA_ONE_COUNTRY_KEY, 5);

    // Attempt to parse data
    parser.parse(schemaOne);
  }

  @Before
  public void setUp() {
    ObjectMapper mapper = new ObjectMapper();

    // Create name data using schema one
    schemaOne = mapper.createObjectNode();
    schemaOne.put(SCHEMA_ONE_COUNTRY_KEY, COUNTRY);
    schemaOne.put(SCHEMA_ONE_STATE_KEY, STATE);
    schemaOne.put(SCHEMA_ONE_LOCALITY_KEY, LOCALITY);
    schemaOne.put(SCHEMA_ONE_ORG_KEY, ORG);
    schemaOne.put(SCHEMA_ONE_ORG_UNIT_KEY, ORG_UNIT);

    // Create name data using schema two
    schemaTwo = mapper.createObjectNode();
    schemaTwo.put(SCHEMA_TWO_COUNTRY_KEY, COUNTRY);
    schemaTwo.put(SCHEMA_TWO_STATE_KEY, STATE);
    schemaTwo.put(SCHEMA_TWO_LOCALITY_KEY, LOCALITY);
    schemaTwo.put(SCHEMA_TWO_ORG_KEY, ORG);
    schemaTwo.put(SCHEMA_TWO_ORG_UNIT_KEY, ORG_UNIT);

    // Factory mocking
    name = mock(Name.class);
    factory = mock(NameFactory.class);
    doReturn(name)
        .when(factory).create(anyString(), anyString(), anyString(), anyString(), anyString());

    // Create the parser
    parser = new DefaultNameParser(factory);
  }
}
