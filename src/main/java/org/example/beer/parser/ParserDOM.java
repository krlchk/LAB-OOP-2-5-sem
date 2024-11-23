package org.example.beer.parser;

import org.example.beer.model.Beer;
import org.example.beer.model.Chars;
import org.example.beer.model.BottlingMethod;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParserDOM {
    private static final Logger logger = LoggerFactory.getLogger(ParserDOM.class);

    public List<Beer> parseDOM(File xmlFile) {
        List<Beer> beers = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList beerNodes = document.getElementsByTagName("beer");
            for (int i = 0; i < beerNodes.getLength(); i++) {
                Node beerNode = beerNodes.item(i);
                if (beerNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beerElement = (Element) beerNode;
                    Beer beer = parseBeerElement(beerElement);
                    beers.add(beer);
                }
            }
        } catch (Exception e) {
            logger.error("Помилка при парсингу DOM", e);
        }
        return beers;
    }

    private Beer parseBeerElement(Element beerElement) {
        Beer beer = new Beer();
        beer.setId(getTagValue(beerElement, "id"));
        beer.setName(getTagValue(beerElement, "name"));
        beer.setType(getTagValue(beerElement, "type"));
        beer.setAl(getTagValue(beerElement, "al"));
        beer.setManufacturer(getTagValue(beerElement, "manufacturer"));

        List<String> ingredients = new ArrayList<>();
        NodeList ingredientNodes = beerElement.getElementsByTagName("ingredient");
        for (int i = 0; i < ingredientNodes.getLength(); i++) {
            ingredients.add(ingredientNodes.item(i).getTextContent());
        }
        beer.setIngredients(ingredients);

        Element charsElement = (Element) beerElement.getElementsByTagName("chars").item(0);
        Chars chars = parseCharsElement(charsElement);
        beer.setChars(chars);

        return beer;
    }

    private Chars parseCharsElement(Element charsElement) {
        Chars chars = new Chars();
        chars.setRevolutions(charsElement.getElementsByTagName("revolutions").getLength() > 0
                ? Float.parseFloat(getTagValue(charsElement, "revolutions"))
                : 0.0f);
        chars.setTransparency(Byte.parseByte(getTagValue(charsElement, "transparency")));
        chars.setFiltered(getTagValue(charsElement, "filtered"));
        chars.setNutritionalValue(charsElement.getElementsByTagName("nutritionalValue").getLength() > 0
                ? Byte.parseByte(getTagValue(charsElement, "nutritionalValue"))
                : 0);

        Element bottlingElement = (Element) charsElement.getElementsByTagName("bottlingMethod").item(0);
        BottlingMethod bottlingMethod = parseBottlingMethod(bottlingElement);
        chars.setBottlingMethod(bottlingMethod);

        return chars;
    }

    private BottlingMethod parseBottlingMethod(Element bottlingElement) {
        BottlingMethod bottlingMethod = new BottlingMethod();
        bottlingMethod.setVolume(Float.parseFloat(getTagValue(bottlingElement, "volume")));
        bottlingMethod.setMaterial(getTagValue(bottlingElement, "material"));
        return bottlingMethod;
    }

    private String getTagValue(Element element, String tagName) {
        NodeList nodes = element.getElementsByTagName(tagName);
        return nodes.getLength() > 0 ? nodes.item(0).getTextContent() : "";
    }
}
