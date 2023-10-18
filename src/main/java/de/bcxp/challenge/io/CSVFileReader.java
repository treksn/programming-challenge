package de.bcxp.challenge.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads CSV files and returns the content as a list of key-value pair mappings.
 * Assumes the first row of the CSV is the header. Supports custom delimiters.
 */
public class CSVFileReader implements StructuredFileReader {

    /** The default delimiter used for parsing CSV entries. */
    final String DELIMITER = ",";

    @Override
    public List<Map<String, String>> read(String filePath) {
        return read(filePath, DELIMITER);
    }

    /**
     * Reads structured content from a csv file.
     *
     * @param filePath      The path to the file
     * @param delimiter The delimiter used to separate entries in the CSV file.
     * @return Content of the file as a list of key/value pairs
     */
    public List<Map<String, String>> read(String filePath, String delimiter)  {
        List<Map<String, String>> fileData = new ArrayList<>();
        List<String> header = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            for (int i = 0; (line = br.readLine()) != null; ++i) {
                List<String> values = Arrays.asList(line.split(delimiter));
                if (i == 0) {
                    header = values;
                    continue;
                }
                if (header.size() != values.size()) {
                    throw new IllegalArgumentException(
                            "Value count not matching the header definition at line " + (i + 1));
                }
                Map<String, String> rowData = new HashMap<String, String>();
                for (int j = 0; j < values.size(); ++j) {
                    rowData.put(header.get(j), values.get(j));
                }
                fileData.add(rowData);
            }
        } catch (IllegalArgumentException iae) {
            throw new RuntimeException("Error in provided data: " + iae.getMessage(), iae);
        } catch (FileNotFoundException fnfe) {
            throw new RuntimeException("The specified file was not found: " + filePath, fnfe);
        } catch (IOException ioe) {
            throw new RuntimeException("Error reading the file: " + filePath, ioe);
        }
        return fileData;
    }
}
