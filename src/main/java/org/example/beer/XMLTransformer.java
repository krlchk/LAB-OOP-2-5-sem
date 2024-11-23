package org.example.beer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XMLTransformer {
    private static final Logger logger = LoggerFactory.getLogger(XMLTransformer.class);

    public static void main(String[] args) {
        transformXML("src/main/resources/beer.xml", "src/main/resources/beer.xsl", "src/main/resources/transformed_beer.xml");
    }

    public static void transformXML(String xmlFilePath, String xslFilePath, String outputFilePath) {
        try {
            logger.info("Starting XML transformation: {} -> {} using {}", xmlFilePath, outputFilePath, xslFilePath);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(new File(xslFilePath)));

            transformer.transform(new StreamSource(new File(xmlFilePath)),
                    new StreamResult(new File(outputFilePath)));

            logger.info("Transformation completed successfully. Output saved to: {}", outputFilePath);
        } catch (Exception e) {
            logger.error("Error during XML transformation", e);
        }
    }
}
