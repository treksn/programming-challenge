package de.bcxp.challenge.io;

import java.util.List;
import java.util.Map;

/**
 * Interface for reading structured file formats and returning content
 * as a list of key-value pair mappings.
 */
public interface StructuredFileReader {

    /**
     * Reads structured content from a file.
     *
     * @param path the path to the file
     * @return content of the file as a list of key/value pairs
     */
    List<Map<String, String>> read(String path);
}
