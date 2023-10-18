package de.bcxp.challenge.weather;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WeatherDataTest {

    private WeatherData weatherData;
    private String testFilePath = "src/test/java/de/bcxp/challenge/weather/test_weather.csv";

    @BeforeEach
    public void setup() {
        weatherData = new WeatherData();
        weatherData.load(testFilePath);
    }

    @Test
    public void testLoad() {
        // Assuming day 1 exists
        assertEquals(1, weatherData.getByDay(1).getDay());
    }

    @Test()
    public void testGetByDayNotFound() {
        // Assuming day not found
        assertThrows(NoSuchElementException.class, () -> weatherData.getByDay(99));
    }

    @Test
    public void testGetMinimumDayTemperature() {
        // Assuming day 1 min temperature is 59
        float minTemp = weatherData.getMinimumDayTemperature(1);
        assertEquals(59, minTemp);
    }

    @Test
    public void testGetMaximumDayTemperature() {
        // Assuming day 1 max temperature is 88
        float maxTemp = weatherData.getMaximumDayTemperature(1);
        assertEquals(88, maxTemp);
    }

    @Test
    public void testGetTemperatureSpreads() {
        Map<Integer, Float> spreads = weatherData.getTemperatureSpreads();
        assertTrue(spreads.containsKey(1));

        // Assuming day 1 temperature spread is 29 (88 - 59)
        assertEquals(29, spreads.get(1));
    }

    @Test
    public void testGetSmallestTemperatureSpreadDay() {
        // Assuming day 2 has the smallest spread 16 (79 - 63)
        int day = weatherData.getSmallestTemperatureSpreadDay();
        assertEquals(2, day);
    }
}
