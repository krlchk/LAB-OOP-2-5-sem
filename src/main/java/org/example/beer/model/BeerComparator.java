package org.example.beer.model;

import org.example.beer.model.Beer;
import java.util.Comparator;

public class BeerComparator implements Comparator<Beer> {
    @Override
    public int compare(Beer beer1, Beer beer2) {
        String al1 = beer1.getType();
        String al2 = beer2.getType();
        if (al1 == null && al2 == null) {
            return 0;
        } else if (al1 == null) {
            return -1;
        } else if (al2 == null) {
            return 1;
        } else {
            return al1.compareTo(al2);
        }
    }
}
