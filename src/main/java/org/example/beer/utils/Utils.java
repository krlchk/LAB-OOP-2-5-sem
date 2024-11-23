package org.example.beer.utils;

import org.example.beer.model.Beer;
import org.example.beer.model.BeerComparator;
import java.util.Collections;
import java.util.List;

public class Utils {
    public static void sortBeers(List<Beer> beers) {
        Collections.sort(beers, new BeerComparator());
    }
}
