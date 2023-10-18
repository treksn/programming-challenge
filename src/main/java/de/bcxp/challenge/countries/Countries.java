package de.bcxp.challenge.countries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Represents a collection of countries.
 */
public class Countries {

    /** List of countries. */
    private List<Country> countries;

    /**
     * Initializes the countries using the provided data.
     *
     * @param countriesData List of country data maps.
     */
    public Countries(List<Map<String, String>> countriesData) {
        countries = new ArrayList<>();
        for (Map<String, String> countryData : countriesData) {
            countries.add(Country.fromMap(countryData));
        }
    }

    /**
     * Returns the country with the highest population density.
     *
     * @return Country with the max population density.
     */
    public Country getMaxPopulationDensityCountry() {
        return Collections.max(countries,
                Comparator.comparing(Country::getPopulationDensity));
    }
}
