/*
 * Name: Hirusha Maduranga
 * Student ID: 20241097
 * Module: Algorithms Coursework
 */

// Acyclic check using the sink elimination algorithm.

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// Utility class for sink elimination logic.
public class SinkElimination {

    // Result object to return elimination details.
    public static class Result {
        private final boolean acyclic;
        private final List<Integer> eliminationOrder;
        private final int totalVertices;

        public Result(boolean acyclic, List<Integer> eliminationOrder, int totalVertices) {
            this.acyclic = acyclic;
            this.eliminationOrder = eliminationOrder;
            this.totalVertices = totalVertices;
        }

        public boolean isAcyclic() {
            return acyclic;
        }

        public List<Integer> getEliminationOrder() {
            return eliminationOrder;
        }

        public int getTotalVertices() {
            return totalVertices;
        }
    }

    // Run sink elimination and print steps in the required style.
    public static Result run(Graph graph) {
        Graph workingGraph = graph.copy();
        List<Integer> order = new ArrayList<>();
        int totalVertices = workingGraph.getVertices().size();

        System.out.println("Starting sink elimination...");

        while (!workingGraph.isEmpty()) {
            Integer sink = findSink(workingGraph);
            if (sink == null) {
                System.out.println("No sink found in remaining graph");
                System.out.println("Graph is cyclic");
                System.out.println("Elimination order: " + order);
                System.out.println(
                        "Summary: Removed " + order.size() + " of " + totalVertices + " vertices.");
                return new Result(false, order, totalVertices);
            }

            System.out.println("Sink found: " + sink);
            System.out.println("Removing sink: " + sink);
            order.add(sink);
            workingGraph.removeVertex(sink);
        }

        System.out.println("Graph is acyclic");
        System.out.println("Elimination order: " + order);
        System.out.println("Summary: Removed all vertices.");
        return new Result(true, order, totalVertices);
    }

    // Find one sink (vertex with no outgoing edges).
    private static Integer findSink(Graph graph) {
        Set<Integer> sortedVertices = new TreeSet<>(graph.getVertices());
        for (Integer vertex : sortedVertices) {
            if (graph.getOutDegree(vertex) == 0) {
                return vertex;
            }
        }
        return null;
    }
}
