package de.bcxp.challenge.countries;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Represents data for a specific country.
 */
public class Country {

    /** The name of the country. */
    private String name;

    /** The population of the country. */
    private int population;

    /** The area of the country in square kilometers. */
    private int area;

    /**
     * Gets the name of the country.
     * 
     * @return the name of the country.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the country.
     * 
     * @param name the name of the country.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the population of the country.
     * 
     * @return the population of the country.
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Sets the population of the country.
     * 
     * @param population the population to set.
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Gets the area of the country in square kilometers.
     * 
     * @return the area of the country.
     */
    public int getArea() {
        return area;
    }

    /**
     * Sets the area of the country in square kilometers.
     * 
     * @param area the area to set.
     */
    public void setArea(int area) {
        this.area = area;
    }

    /**
     * Gets the population density of the country.
     * 
     * @return the population density calculated as population/area.
     *         Returns 0 if area is 0 to avoid division by zero.
     */
    public float getPopulationDensity() {
        if (area == 0) {
            return 0;
        }
        return (float) population / area;
    }

    /**
     * Creates a new {@code Country} instance from the provided map.
     *
     * @param countryData A map containing the data for the country.
     * @return A new {@code Country} instance populated with the data from
     *         the provided map.
     */
    public static Country fromMap(Map<String, String> countryData) {
        Country data = new Country();
        NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
        try {
            data.setName(countryData.get("Name"));
            data.setPopulation(format.parse(countryData.get("Population")).intValue());
            data.setArea(format.parse(countryData.get("Area (km²)")).intValue());
        } catch (Exception e) {
            throw new RuntimeException("Error while parsing country data.", e);
        }
        return data;
    }
}
