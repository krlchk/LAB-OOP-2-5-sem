package org.example.beer.parser;

import org.example.beer.model.Beer;
import org.example.beer.model.Chars;
import org.example.beer.model.BottlingMethod;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParserStAX {
    private static final Logger logger = LoggerFactory.getLogger(ParserStAX.class);

    public List<Beer> parseStAX(File xml) throws XMLStreamException, FileNotFoundException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(xml));

        List<Beer> beerList = new ArrayList<>();
        Beer currentBeer = null;
        Chars currentChars = null;
        BottlingMethod currentBottlingMethod = null;
        List<String> currentIngredients = null;

        String currentElementName = null;

        logger.info("Parsing XML file using StAX parser: {}", xml.getName());

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElementName = startElement.getName().getLocalPart();

                switch (currentElementName) {
                    case "beer" -> {
                        currentBeer = new Beer();
                        Attribute idAttr = startElement.getAttributeByName(new javax.xml.namespace.QName("id"));
                        if (idAttr != null) {
                            currentBeer.setId(idAttr.getValue());
                        }
                    }
                    case "chars" -> currentChars = new Chars();
                    case "bottlingMethod" -> currentBottlingMethod = new BottlingMethod();
                    case "ingredients" -> currentIngredients = new ArrayList<>();
                }
            } else if (event.isCharacters() && currentBeer != null) {
                String data = event.asCharacters().getData().trim();
                if (!data.isEmpty()) {
                    switch (currentElementName) {
                        case "name" -> currentBeer.setName(data);
                        case "type" -> currentBeer.setType(data);
                        case "al" -> currentBeer.setAl(data);
                        case "manufacturer" -> currentBeer.setManufacturer(data);
                        case "ingredient" -> currentIngredients.add(data);
                        case "revolutions" -> currentChars.setRevolutions(Float.parseFloat(data));
                        case "transparency" -> currentChars.setTransparency(Byte.parseByte(data));
                        case "filtered" -> currentChars.setFiltered(data);
                        case "nutritionalValue" -> currentChars.setNutritionalValue(Byte.parseByte(data));
                        case "volume" -> currentBottlingMethod.setVolume(Float.parseFloat(data));
                        case "material" -> currentBottlingMethod.setMaterial(data);
                    }
                }
            } else if (event.isEndElement()) {
                String name = event.asEndElement().getName().getLocalPart();

                switch (name) {
                    case "beer" -> beerList.add(currentBeer);
                    case "chars" -> {
                        if (currentBeer != null) currentBeer.setChars(currentChars);
                        currentChars = null;
                    }
                    case "bottlingMethod" -> {
                        if (currentChars != null) currentChars.setBottlingMethod(currentBottlingMethod);
                        currentBottlingMethod = null;
                    }
                    case "ingredients" -> {
                        if (currentBeer != null) currentBeer.setIngredients(currentIngredients);
                        currentIngredients = null;
                    }
                }
            }
        }

        logger.info("Finished parsing XML file: {}", xml.getName());
        return beerList;
    }
}

