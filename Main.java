/*
 * Name: Hirusha Maduranga
 * Student ID: 20241097
 * Module: Algorithms Coursework
 */

// Entry point that loads a graph and runs the acyclic checks.

// Main application class that wires the workflow together.
public class Main {
    // Read input, run sink elimination, and print a cycle if one exists.
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        String filePath = args[0];
        System.out.println("Reading graph from: " + filePath);

        try {
            // Parse the graph from file.
            Graph graph = Parser.parseFile(filePath);
            // Use sink elimination to test for acyclicity.
            boolean acyclic = SinkElimination.isAcyclic(graph);

            if (!acyclic) {
                // If cyclic, print one detected cycle using DFS.
                CycleDetector.printCycle(graph);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
