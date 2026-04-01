/*
 * Name: Hirusha Maduranga
 * Student ID: 20241097
 * Module: Algorithms Coursework
 */

// Entry point that loads a graph and runs the acyclic checks.

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

// Main application class that wires the workflow together.
public class Main {
    // Read input, run sink elimination, and print a cycle if one exists.
    public static void main(String[] args) {
        String filePath = getInputPath(args);
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println("Error: No input file path provided.");
            return;
        }

        try {
            Graph graph = Parser.parseFile(filePath);
            printGraphInfo(graph, filePath);

            SinkElimination.Result result = SinkElimination.run(graph);
            if (!result.isAcyclic()) {
                System.out.println("Answer: No, the graph is not acyclic.");
                List<Integer> cycle = CycleDetector.findCycle(graph);
                CycleDetector.printCycle(cycle);
            } else {
                System.out.println("Answer: Yes, the graph is acyclic.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Get file path from args or prompt the user.
    private static String getInputPath(String[] args) {
        if (args.length > 0) {
            return args[0];
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input file path: ");
        String path = scanner.nextLine();
        return path;
    }

    // Print graph statistics and adjacency list in the required style.
    private static void printGraphInfo(Graph graph, String filePath) {
        int vertexCount = graph.getVertices().size();
        int edgeCount = countEdges(graph);

        System.out.println("Graph loaded from file: " + filePath);
        System.out.println("Vertices: " + vertexCount);
        System.out.println("Edges: " + edgeCount);
        System.out.println("Adjacency list:");

        Set<Integer> sortedVertices = new TreeSet<>(graph.getVertices());
        for (Integer vertex : sortedVertices) {
            List<Integer> neighbors = new ArrayList<>(graph.getNeighbors(vertex));
            neighbors.sort(Integer::compareTo);
            System.out.print(vertex + ":");
            if (!neighbors.isEmpty()) {
                System.out.print(" ");
            }
            for (int i = 0; i < neighbors.size(); i++) {
                System.out.print(neighbors.get(i));
                if (i < neighbors.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Count all directed edges in the graph.
    private static int countEdges(Graph graph) {
        int count = 0;
        for (Integer vertex : graph.getVertices()) {
            count += graph.getNeighbors(vertex).size();
        }
        return count;
    }
}
