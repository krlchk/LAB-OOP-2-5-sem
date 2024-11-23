package org.example.beer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class XMLValidatorTest {

    @Test
    public void testValidXML() {
        String xmlFile = "src/main/resources/beer.xml";
        String xsdFile = "src/main/resources/beer.xsd";
        boolean isValid = XMLValidator.validate(xmlFile, xsdFile);
        assertTrue(isValid, "Expected the XML file to be valid according to the XSD schema.");
    }

    @Test
    public void testInvalidXML() {
        String xmlFile = "src/main/resources/invalid_beer.xml";
        String xsdFile = "src/main/resources/beer.xsd";
        boolean isValid = XMLValidator.validate(xmlFile, xsdFile);
        assertFalse(isValid, "Expected the XML file to be invalid according to the XSD schema.");
    }
}
