package de.bcxp.challenge.weather;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Represents a collection of weather data, typically over a month.
 */
public class Weather {

    /** A list holding weather data for each day in a typical month. */
    private List<WeatherDay> weatherMonth;

    /**
     * Initializes the Weather object from a list of daily weather data maps.
     * 
     * @param weatherData List of maps with daily weather data.
     */
    public Weather(List<Map<String, String>> weatherData) {
        weatherMonth = new ArrayList<>();
        for (Map<String, String> weaterDayData : weatherData) {
            weatherMonth.add(WeatherDay.fromMap(weaterDayData));
        }
    }

    /**
     * Retrieves the weather data for a specific day.
     *
     * @param day The day for which the weather data is to be retrieved.
     * @return The WeatherDay instance for the specified day.
     * @throws NoSuchElementException if data for the specified day cannot be found.
     * 
     */
    public WeatherDay getByDay(int day) {
        for (WeatherDay weatherDay : weatherMonth) {
            if (weatherDay.getDay() == day) {
                return weatherDay;
            }
        }
        throw new NoSuchElementException("WeatherDay with day " + day + " could not be found.");
    }

    /**
     * Retrieves the minimum temperature for a specific day.
     *
     * @param day The day for which the minimum temperature is to be retrieved.
     * @return The minimum temperature for the specified day.
     */
    public float getMinimumDayTemperature(int day) {
        return getByDay(day).getMinimumTemperature();
    }

    /**
     * Retrieves the maximum temperature for a specific day.
     *
     * @param day The day for which the maximum temperature is to be retrieved.
     * @return The maximum temperature for the specified day.
     */
    public float getMaximumDayTemperature(int day) {
        return getByDay(day).getMaximumTemperature();
    }

    /**
     * Determines and returns the day with the smallest temperature spread.
     *
     * @return The day with the smallest temperature spread.
     */
    public WeatherDay getMinTemperatureSpreadDay() {
        return Collections.min(weatherMonth,
                Comparator.comparing(WeatherDay::getTemperatureSpread));
    }
}
