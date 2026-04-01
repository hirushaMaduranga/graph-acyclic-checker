/*
 * Name: Hirusha Maduranga
 * Student ID: 20241097
 * Module: Algorithms Coursework
 */

// File parser that builds a directed graph from text input.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Utility class for parsing graph files.
public class Parser {

    // Read graph from a text file and build the directed graph safely.
    public static Graph parseFile(String path) throws IOException {
        Graph graph = new Graph();
        boolean firstNonEmptyLine = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                if (firstNonEmptyLine && parts.length == 1) {
                    Integer vertexCount = tryParseInt(parts[0]);
                    if (vertexCount != null && vertexCount >= 0) {
                        // Add all vertices so isolated nodes are included.
                        for (int i = 0; i < vertexCount; i++) {
                            graph.addVertex(i);
                        }
                        firstNonEmptyLine = false;
                        continue;
                    }
                }

                firstNonEmptyLine = false;

                if (parts.length < 2) {
                    // Skip malformed lines safely.
                    continue;
                }

                Integer from = tryParseInt(parts[0]);
                Integer to = tryParseInt(parts[1]);
                if (from == null || to == null) {
                    continue;
                }

                graph.addEdge(from, to);
            }
        }

        return graph;
    }

    // Safely parse an integer; return null if parsing fails.
    private static Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
