package de.bcxp.challenge.weather;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.bcxp.challenge.io.CSVFileReader;

public class WeatherTest {

    private Weather weather;
    private String testFilePath = "src/test/java/de/bcxp/challenge/weather/test_weather.csv";
    private CSVFileReader fileReader = new CSVFileReader();

    @BeforeEach
    public void setup() {
        List<Map<String, String>> testWeatherData = fileReader.read(testFilePath);
        weather = new Weather(testWeatherData);
    }

    @Test
    public void testGetByDay() {
        // Assuming day 1 exists
        assertEquals(1, weather.getByDay(1).getDay());
    }

    @Test()
    public void testGetByDayNotFound() {
        // Assuming day not found
        assertThrows(NoSuchElementException.class, () -> weather.getByDay(99));
    }

    @Test
    public void testGetMinimumDayTemperature() {
        // Assuming day 1 min temperature is 59
        float minTemp = weather.getMinimumDayTemperature(1);
        assertEquals(59, minTemp);
    }

    @Test
    public void testGetMaximumDayTemperature() {
        // Assuming day 1 max temperature is 88
        float maxTemp = weather.getMaximumDayTemperature(1);
        assertEquals(88, maxTemp);
    }

    @Test
    public void testGetSmallestTemperatureSpreadDay() {
        // Assuming day 2 has the smallest spread 
        WeatherDay dayWithSmallestSpread = weather.getMinTemperatureSpreadDay();
        assertEquals(2, dayWithSmallestSpread.getDay());
    }
}
