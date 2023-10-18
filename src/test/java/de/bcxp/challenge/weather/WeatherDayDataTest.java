package de.bcxp.challenge.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class WeatherDayDataTest {

    @Test
    void fromMap_ValidMap_CorrectValuesSet() {
        Map<String, String> validMap = new HashMap<>();
        validMap.put("Day", "5");
        validMap.put("MnT", "10.5");
        validMap.put("MxT", "20.5");

        WeatherDayData result = WeatherDayData.fromMap(validMap);

        assertEquals(5, result.getDay());
        assertEquals(10.5f, result.getMinimumTemperature());
        assertEquals(20.5f, result.getMaximumTemperature());
    }

    @Test
    void fromMap_InvalidData_DefaultValuesSet() {
        Map<String, String> invalidMap = new HashMap<>();
        invalidMap.put("Day", "InvalidDay");
        invalidMap.put("MnT", "InvalidMinTemp");
        invalidMap.put("MxT", "InvalidMaxTemp");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            WeatherDayData.fromMap(invalidMap);
        });
        assertTrue(exception.getMessage().contains("Error while parsing weather day data"));
    }
}
