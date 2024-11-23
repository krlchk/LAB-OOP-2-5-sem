package org.example.beer;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XMLValidator {
    private static final Logger logger = LoggerFactory.getLogger(XMLValidator.class);

    public static boolean validate(String xmlFile, String xsdFile) {
        try {
            logger.info("Validating XML file: {}", xmlFile);
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsdFile));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
            logger.info("XML validation successful");
            return true;
        } catch (Exception ex) {
            logger.error("XML validation failed", ex);
            return false;
        }
    }
}
