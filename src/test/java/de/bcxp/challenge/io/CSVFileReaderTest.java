package de.bcxp.challenge.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVFileReaderTest {

    private CSVFileReader reader;

    @TempDir
    File tempDir;

    @BeforeEach
    public void setup() {
        reader = new CSVFileReader();
    }

    @Test
    public void testReadDefaultDelimiter() throws Exception {
        File csvFile = new File(tempDir, "test.csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            bw.write("Day,Temperature,Humidity\n");
            bw.write("Monday,25.4C,87%\n");
            bw.write("Wednesday,28.1C,44%\n");
        }

        List<Map<String, String>> result = reader.read(csvFile.getAbsolutePath());
        assertEquals(2, result.size());

        Map<String, String> firstRow = result.get(0);
        assertEquals("Monday", firstRow.get("Day"));
        assertEquals("25.4C", firstRow.get("Temperature"));
        assertEquals("87%", firstRow.get("Humidity"));

        Map<String, String> secondRow = result.get(1);
        assertEquals("Wednesday", secondRow.get("Day"));
        assertEquals("28.1C", secondRow.get("Temperature"));
        assertEquals("44%", secondRow.get("Humidity"));
    }

    @Test
    public void testReadCustomDelimiter() throws Exception {
        File csvFile = new File(tempDir, "test.tsv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            bw.write("Day\tTemperature\tHumidity\n");
            bw.write("Thursday\t22.4C\t66%\n");
        }

        List<Map<String, String>> result = reader.read(csvFile.getAbsolutePath(), "\t");
        assertEquals(1, result.size());

        Map<String, String> firstRow = result.get(0);
        assertEquals("Thursday", firstRow.get("Day"));
        assertEquals("22.4C", firstRow.get("Temperature"));
        assertEquals("66%", firstRow.get("Humidity"));
    }

    @Test
    public void testEmptyFile() throws Exception {
        File csvFile = new File(tempDir, "empty.csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            // No content
        }
        List<Map<String, String>> result = reader.read(csvFile.getAbsolutePath());
        assertTrue(result.isEmpty());
    }

    @Test
    public void testMismatchedRowLength() throws Exception {
        File csvFile = new File(tempDir, "mismatched.csv");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            bw.write("Day,Temperature,Humidity\n");
            bw.write("Saturday,25.3c\n"); // Missing humidity
        }

        Exception exception = assertThrows(RuntimeException.class, () -> {
            reader.read(csvFile.getAbsolutePath());
        });
        assertTrue(exception.getMessage().contains("Error in provided data"));
    }
}