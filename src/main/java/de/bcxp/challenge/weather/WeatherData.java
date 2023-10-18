package de.bcxp.challenge.weather;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import de.bcxp.challenge.io.CSVFileReader;

/**
 * Represents a collection of weather data, typically over a month. This class
 * provides methods to load the data from a file, retrieve specific day's data,
 * and perform various operations related to temperature.
 */
public class WeatherData {

    /** Responsible for reading the CSV file. */
    private CSVFileReader fileReader = new CSVFileReader();

    /** A list holding data for each day in a typical month. */
    private List<WeatherDayData> weatherMonthData;

    /**
     * Loads weather data from the provided file path.
     *
     * @param filePath The path to the file containing the weather data.
     * @return The WeatherData instance (this) with populated data.
     */
    public WeatherData load(String filePath) {
        List<Map<String, String>> fileData = this.fileReader.read(filePath);
        weatherMonthData = new ArrayList<>();
        for (Map<String, String> fileDataRow : fileData) {
            weatherMonthData.add(WeatherDayData.fromMap(fileDataRow));
        }
        return this;
    }

    /**
     * Retrieves the weather data for a specific day.
     *
     * @param day The day for which the weather data is to be retrieved.
     * @return The WeatherDayData instance for the specified day.
     * @throws NoSuchElementException if data for the specified day cannot be found.
     *                                         
     */
    public WeatherDayData getByDay(int day) {
        for (WeatherDayData weatherDayData : weatherMonthData) {
            if (weatherDayData.getDay() == day) {
                return weatherDayData;
            }
        }
        throw new NoSuchElementException("WeatherDayData with day " + day + " could not be found.");
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
     * Computes and returns the temperature spreads (difference between maximum and
     * minimum temperature) for all days.
     *
     * @return A map with days as keys and their respective temperature spreads as
     *         values.
     */
    public Map<Integer, Float> getTemperatureSpreads() {
        Map<Integer, Float> dayTemperatureSpreads = new HashMap<Integer, Float>();
        for (WeatherDayData weatherDayData : weatherMonthData) {
            dayTemperatureSpreads.put(weatherDayData.getDay(),
                    weatherDayData.getMaximumTemperature() - weatherDayData.getMinimumTemperature());
        }
        return dayTemperatureSpreads;
    }

    /**
     * Determines and returns the day with the smallest temperature spread.
     *
     * @return The day with the smallest temperature spread.
     */
    public int getSmallestTemperatureSpreadDay() {
        Map.Entry<Integer, Float> min = Collections.min(getTemperatureSpreads().entrySet(),
                Map.Entry.comparingByValue());
        return min.getKey();
    }
}
