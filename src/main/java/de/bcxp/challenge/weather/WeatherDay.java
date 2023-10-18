package de.bcxp.challenge.weather;

import java.util.Map;

/**
 * Represents weather data for a specific day.
 */
public class WeatherDay {

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
     * Returns the difference between maximum and minimum temperature.
     *
     * @return Temperature spread.
     */
    public float getTemperatureSpread() {
        return maximumTemperature - minimumTemperature;
    }

    /**
     * Creates a new {@code WeatherDay} instance from the provided map.
     *
     * @param weatherDay A map containing the weather data for the day.
     * @return A new {@code WeatherDay} instance populated with the data from
     *         the provided map. If there's an error in parsing, the instance will
     *         have default values.
     */
    public static WeatherDay fromMap(Map<String, String> weatherDay) {
        WeatherDay data = new WeatherDay();
        try {
            data.setDay(Integer.parseInt(weatherDay.get("Day")));
            data.setMinimumTemperature(Float.parseFloat(weatherDay.get("MnT")));
            data.setMaximumTemperature(Float.parseFloat(weatherDay.get("MxT")));
        } catch (Exception e) {
            throw new RuntimeException("Error while parsing weather day data.", e);
        }
        return data;
    }
}
