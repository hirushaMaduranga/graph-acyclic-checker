/*
 * Name: Hirusha Maduranga
 * Student ID: 20241097
 * Module: Algorithms Coursework
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    // Read graph from a text file.
    // Supports either:
    // 1) plain edge pairs: u v
    // 2) benchmark files where first line is a single integer vertex count
    public static Graph parseFile(String path) throws IOException {
        Graph graph = new Graph();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        boolean firstNonEmptyLine = true;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] parts = line.split("\\s+");

            // Benchmark files use the first line as the number of vertices.
            if (firstNonEmptyLine && parts.length == 1) {
                int vertexCount = Integer.parseInt(parts[0]);
                for (int i = 0; i < vertexCount; i++) {
                    graph.addVertex(i);
                }
                firstNonEmptyLine = false;
                continue;
            }

            firstNonEmptyLine = false;

            if (parts.length < 2) {
                // Ignore malformed lines safely.
                continue;
            }

            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            graph.addEdge(from, to);
        }

        reader.close();
        return graph;
    }
}
