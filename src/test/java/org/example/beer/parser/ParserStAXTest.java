package org.example.beer.parser;

import org.example.beer.model.Beer;
import org.example.beer.model.Chars;
import org.example.beer.model.BottlingMethod;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParserStAXTest {

    @Test
    public void testParseStAX() throws FileNotFoundException, XMLStreamException {
        File xmlFile = Paths.get("src/main/resources/beer.xml").toFile();
        ParserStAX parserStAX = new ParserStAX();
        List<Beer> beers = parserStAX.parseStAX(xmlFile);

        assertNotNull(beers);
        assertEquals(3, beers.size());

        Beer beer1 = beers.get(0);
        assertEquals("Golden Ale", beer1.getName());
        assertEquals("light", beer1.getType());
        assertEquals("alcoholic", beer1.getAl());
        assertEquals("Gold Brewery", beer1.getManufacturer());

        List<String> ingredients1 = beer1.getIngredients();
        assertNotNull(ingredients1);
        assertEquals(3, ingredients1.size());
        assertEquals("water", ingredients1.get(0));
        assertEquals("barley malt", ingredients1.get(1));
        assertEquals("hops", ingredients1.get(2));

        Chars chars1 = beer1.getChars();
        assertNotNull(chars1);
        assertEquals(5.2f, chars1.getRevolutions());
        assertEquals((byte) 85, chars1.getTransparency());
        assertEquals("yes", chars1.getFiltered());
        assertEquals((byte) 43, chars1.getNutritionalValue());

        BottlingMethod bottlingMethod1 = chars1.getBottlingMethod();
        assertNotNull(bottlingMethod1);
        assertEquals(0.5f, bottlingMethod1.getVolume());
        assertEquals("glass", bottlingMethod1.getMaterial());

        Beer beer2 = beers.get(1);
        assertEquals("Dark Stout", beer2.getName());
        assertEquals("dark", beer2.getType());
        assertEquals("alcoholic", beer2.getAl());
        assertEquals("Black Rock Brewery", beer2.getManufacturer());

        List<String> ingredients2 = beer2.getIngredients();
        assertNotNull(ingredients2);
        assertEquals(3, ingredients2.size());
        assertEquals("water", ingredients2.get(0));
        assertEquals("roasted barley", ingredients2.get(1));
        assertEquals("hops", ingredients2.get(2));

        Chars chars2 = beer2.getChars();
        assertNotNull(chars2);
        assertEquals(6.0f, chars2.getRevolutions());
        assertEquals((byte) 30, chars2.getTransparency());
        assertEquals("no", chars2.getFiltered());
        assertEquals((byte) 60, chars2.getNutritionalValue());

        BottlingMethod bottlingMethod2 = chars2.getBottlingMethod();
        assertNotNull(bottlingMethod2);
        assertEquals(0.33f, bottlingMethod2.getVolume());
        assertEquals("can", bottlingMethod2.getMaterial());

        Beer beer3 = beers.get(2);
        assertEquals("Live Lager", beer3.getName());
        assertEquals("light", beer3.getType());
        assertEquals("non-alcoholic", beer3.getAl());
        assertEquals("Fresh Hops Brewery", beer3.getManufacturer());

        List<String> ingredients3 = beer3.getIngredients();
        assertNotNull(ingredients3);
        assertEquals(4, ingredients3.size());
        assertEquals("water", ingredients3.get(0));
        assertEquals("wheat malt", ingredients3.get(1));
        assertEquals("hops", ingredients3.get(2));
        assertEquals("yeast", ingredients3.get(3));

        Chars chars3 = beer3.getChars();
        assertNotNull(chars3);
        assertEquals(0.0f, chars3.getRevolutions());
        assertEquals((byte) 90, chars3.getTransparency());
        assertEquals("yes", chars3.getFiltered());
        assertEquals((byte) 0, chars3.getNutritionalValue());

        BottlingMethod bottlingMethod3 = chars3.getBottlingMethod();
        assertNotNull(bottlingMethod3);
        assertEquals(1.0f, bottlingMethod3.getVolume());
        assertEquals("plastic", bottlingMethod3.getMaterial());
    }
}
