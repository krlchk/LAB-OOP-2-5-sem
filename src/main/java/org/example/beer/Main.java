package org.example.beer;
import org.example.beer.parser.ParserDOM;
import org.example.beer.parser.ParserSAX;
import org.example.beer.parser.ParserStAX;
import org.example.beer.model.Beer;
import org.example.beer.model.BeerComparator;
import org.example.beer.utils.Utils;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        String xmlFile = "src/main/resources/beer.xml";
        String xsdFile = "src/main/resources/beer.xsd";

        boolean isValid = XMLValidator.validate(xmlFile, xsdFile);
        if (isValid) {
            logger.info("XML файл є валідним!");
        } else {
            logger.error("XML файл не пройшов валідацію.");
            return;
        }

        try {
            ParserSAX parserSAX = new ParserSAX();
            File file = new File(xmlFile);
            List<Beer> beersSAX = parserSAX.parseSAX(file);
            Utils.sortBeers(beersSAX); // Сортування
            logger.debug("Результат парсингу SAX:");
            beersSAX.forEach(Main::printBeerDetails);
        } catch (Exception e) {
            logger.error("Помилка при парсингу SAX", e);
        }

        try {
            ParserDOM parserDOM = new ParserDOM();
            File file = new File(xmlFile);
            List<Beer> beersDOM = parserDOM.parseDOM(file);
            Utils.sortBeers(beersDOM); // Сортування
            logger.debug("Результат парсингу DOM:");
            beersDOM.forEach(Main::printBeerDetails);
        } catch (Exception e) {
            logger.error("Помилка при парсингу DOM", e);
        }

        try {
            ParserStAX parserStAX = new ParserStAX();
            File file = new File(xmlFile);
            List<Beer> beersStAX = parserStAX.parseStAX(file);
            Utils.sortBeers(beersStAX); // Сортування
            logger.debug("Результат парсингу StAX:");
            beersStAX.forEach(Main::printBeerDetails);
        } catch (Exception e) {
            logger.error("Помилка при парсингу StAX", e);
        }
    }

    private static void printBeerDetails(Beer beer) {
        logger.info("Name: {}", beer.getName());
        logger.info("Type: {}", beer.getType());
        logger.info("Alcohol: {}", beer.getAl());
        logger.info("Manufacturer: {}", beer.getManufacturer());
        logger.info("Ingredients: {}", (beer.getIngredients() != null ? String.join(", ", beer.getIngredients()) : "N/A"));
        if (false) {
            logger.info("Revolutions: {}", beer.getChars().getRevolutions());
            logger.info("Transparency: {}", beer.getChars().getTransparency());
            logger.info("Filtered: {}", beer.getChars().getFiltered());
            logger.info("Nutritional Value: {}", beer.getChars().getNutritionalValue());
            if (beer.getChars().getBottlingMethod() != null) {
                logger.info("Bottling Volume: {}", beer.getChars().getBottlingMethod().getVolume());
                logger.info("Bottling Material: {}", beer.getChars().getBottlingMethod().getMaterial());
            }
        }
        logger.info("------");
    }
}




//команда для зміни структури
//xsltproc src/main/resources/beer.xsl src/main/resources/beer.xml > src/main/resources/transformed_beer.xml