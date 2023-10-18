package de.bcxp.challenge;

import java.util.List;
import java.util.Map;

import de.bcxp.challenge.countries.Countries;
import de.bcxp.challenge.countries.Country;
import de.bcxp.challenge.io.CSVFileReader;
import de.bcxp.challenge.weather.Weather;
import de.bcxp.challenge.weather.WeatherDay;

/**
 * The entry class for your solution. This class is only aimed as starting point
 * and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * 
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        CSVFileReader fileReader = new CSVFileReader();
        String resourcesFolder = "src/main/resources/de/bcxp/challenge/";
        List<Map<String, String>> weatherData = fileReader.read(resourcesFolder + "weather.csv");
        List<Map<String, String>> countriesData = fileReader.read(resourcesFolder + "countries.csv", ";");

        Weather weather = new Weather(weatherData);
        Countries countries = new Countries(countriesData);

        WeatherDay dayWithSmallestTempSpread = weather.getMinTemperatureSpreadDay();
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread.getDay());

        Country countryWithHighestPopulationDensity = countries.getMaxPopulationDensityCountry();
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity.getName());
    }
}
