package de.bcxp.challenge.countries;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class CountryTest {
    @Test
    void fromMap_ValidMap_CorrectValuesSet() {
        Map<String, String> validMap = new HashMap<>();
        validMap.put("Name", "TestCountry");
        validMap.put("Population", "1000000");
        validMap.put("Area (km²)", "5000");

        Country result = Country.fromMap(validMap);

        assertEquals("TestCountry", result.getName());
        assertEquals(1000000, result.getPopulation());
        assertEquals(5000, result.getArea());
    }

    @Test
    void fromMap_InvalidData_ThrowsException() {
        Map<String, String> invalidMap = new HashMap<>();
        invalidMap.put("Name", "TestCountry");
        invalidMap.put("Population", "InvalidPopulation");
        invalidMap.put("Area (km²)", "InvalidArea");

        Exception exception = assertThrows(RuntimeException.class, () -> {
            Country.fromMap(invalidMap);
        });
        assertTrue(exception.getMessage().contains("Error while parsing country data"));
    }

    @Test
    void getPopulationDensity_ValidData_CorrectValueReturned() {
        Country country = new Country();
        country.setPopulation(1000000);
        country.setArea(5000);

        float expectedDensity = 200.0f; // 1000000 / 5000
        assertEquals(expectedDensity, country.getPopulationDensity());
    }
}
