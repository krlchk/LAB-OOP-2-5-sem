package org.example.beer.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeerComparatorTest {

    @Test
    public void testComparator() {
        Beer beer1 = new Beer();
        beer1.setType("light");

        Beer beer2 = new Beer();
        beer2.setType("dark");

        Beer beer3 = new Beer();
        beer3.setType("light");

        List<Beer> beers = Arrays.asList(beer1, beer2, beer3);
        beers.sort(new BeerComparator());

        Assertions.assertEquals("dark", beers.get(0).getType());
        Assertions.assertEquals("light", beers.get(1).getType());
        Assertions.assertEquals("light", beers.get(2).getType());
    }
}
