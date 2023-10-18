package de.bcxp.challenge.weather;

import java.util.Map;

/**
 * Represents weather data for a specific day.
 * Provides a method to parse weather data from a map.
 */
public class WeatherDayData {

    /** The day of the month (1-31). */
    private int day;

    /** The minimum temperature recorded for the day. */
    private float minimumTemperature;

    /** The maximum temperature recorded for the day. */
    private float maximumTemperature;

    /**
     * Gets the day of the month.
     * 
     * @return the day of the month, a value between 1 and 31 inclusive.
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the day of the month.
     * 
     * @param day the day of the month, expected to be between 1 and 31 inclusive.
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * Gets the minimum temperature recorded for the day.
     * 
     * @return the minimum temperature.
     */
    public float getMinimumTemperature() {
        return minimumTemperature;
    }

    /**
     * Sets the minimum temperature recorded for the day.
     * 
     * @param minimumTemperature the minimum temperature to set.
     */
    public void setMinimumTemperature(float minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    /**
     * Gets the maximum temperature recorded for the day.
     * 
     * @return the maximum temperature.
     */
    public float getMaximumTemperature() {
        return maximumTemperature;
    }

    /**
     * Sets the maximum temperature recorded for the day.
     * 
     * @param maximumTemperature the maximum temperature to set.
     */
    public void setMaximumTemperature(float maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    /**
     * Creates a new {@code WeatherDayData} instance from the provided map.
     *
     * @param weatherDayData A map containing the weather data for the day.
     * @return A new {@code WeatherDayData} instance populated with the data from
     *         the provided map. If there's an error in parsing, the instance will
     *         have default values.
     */
    public static WeatherDayData fromMap(Map<String, String> weatherDayData) {
        WeatherDayData data = new WeatherDayData();
        try {
            data.setDay(Integer.parseInt(weatherDayData.get("Day")));
            data.setMinimumTemperature(Float.parseFloat(weatherDayData.get("MnT")));
            data.setMaximumTemperature(Float.parseFloat(weatherDayData.get("MxT")));
        } catch (Exception e) {
            throw new RuntimeException("Error while parsing weather day data.", e);
        }
        return data;
    }
}
