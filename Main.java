/*
 * Name: Hirusha Maduranga
 * Student ID: 20241097
 * Module: Algorithms Coursework
 */

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        String filePath = args[0];
        System.out.println("Reading graph from: " + filePath);

        try {
            Graph graph = Parser.parseFile(filePath);
            boolean acyclic = SinkElimination.isAcyclic(graph);

            if (!acyclic) {
                CycleDetector.printCycle(graph);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
