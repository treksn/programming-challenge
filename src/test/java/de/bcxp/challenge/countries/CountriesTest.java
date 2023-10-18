package de.bcxp.challenge.countries;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.bcxp.challenge.io.CSVFileReader;

public class CountriesTest {

    private Countries countries;
    private String testFilePath = "src/test/java/de/bcxp/challenge/countries/test_countries.csv";
    private CSVFileReader fileReader = new CSVFileReader();

    @BeforeEach
    public void setup() {
        List<Map<String, String>> testCountryData = fileReader.read(testFilePath, ";");
        countries = new Countries(testCountryData);
    }

    @Test
    public void testMaxPopulationDensityCountry() {
        // Assuming "Belgium" has the highest population density in test data
        Country countryWithMaxDensity = countries.getMaxPopulationDensityCountry();
        assertEquals("Belgium", countryWithMaxDensity.getName());
    }
}
