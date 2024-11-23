package org.example.beer.parser;
import org.example.beer.model.Beer;
import org.example.beer.model.BottlingMethod;
import org.example.beer.parser.BeerHandler;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParserSAX {
    private static final Logger logger = LoggerFactory.getLogger(ParserSAX.class);

    public List<Beer> parseSAX(File xmlFile) throws SAXException, IOException, ParserConfigurationException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        BeerHandler beerHandler = new BeerHandler();
        logger.info("Parsing XML file using SAX parser: {}", xmlFile.getName());
        saxParser.parse(xmlFile, beerHandler);
        logger.info("Finished parsing XML file: {}", xmlFile.getName());
        return beerHandler.getBeerList();
    }
}

