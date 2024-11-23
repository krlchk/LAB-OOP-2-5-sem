package org.example.beer.parser;

import org.example.beer.model.Beer;
import org.example.beer.model.BottlingMethod;
import org.example.beer.model.Chars;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class BeerHandler extends DefaultHandler {

    private String valueOfElement;
    private List<Beer> beerList = new ArrayList<>();
    private Beer currentBeer;
    private Chars currentChars;
    private BottlingMethod currentBottlingMethod;
    private List<String> currentIngredients;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "beer" -> {
                currentBeer = new Beer();
                currentBeer.setId(attributes.getValue("id"));
                beerList.add(currentBeer);
            }
            case "chars" -> currentChars = new Chars();
            case "bottlingMethod" -> currentBottlingMethod = new BottlingMethod();
            case "ingredients" -> currentIngredients = new ArrayList<>();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "name" -> currentBeer.setName(valueOfElement);
            case "type" -> currentBeer.setType(valueOfElement);
            case "al" -> currentBeer.setAl(valueOfElement);
            case "manufacturer" -> currentBeer.setManufacturer(valueOfElement);
            case "ingredient" -> currentIngredients.add(valueOfElement);
            case "ingredients" -> currentBeer.setIngredients(currentIngredients);
            case "revolutions" -> currentChars.setRevolutions(Float.parseFloat(valueOfElement));
            case "transparency" -> currentChars.setTransparency(Byte.parseByte(valueOfElement));
            case "filtered" -> currentChars.setFiltered(valueOfElement);
            case "nutritionalValue" -> currentChars.setNutritionalValue(Byte.parseByte(valueOfElement));
            case "bottlingMethod" -> currentChars.setBottlingMethod(currentBottlingMethod);
            case "chars" -> currentBeer.setChars(currentChars);
            case "volume" -> currentBottlingMethod.setVolume(Float.parseFloat(valueOfElement));
            case "material" -> currentBottlingMethod.setMaterial(valueOfElement);
        }
        valueOfElement = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        valueOfElement = new String(ch, start, length).trim();
    }

    public List<Beer> getBeerList() {
        return beerList;
    }

    @Override
    public void startDocument() throws SAXException {
        beerList = new ArrayList<>();
    }
}
