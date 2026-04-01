/*
 * Name: Hirusha Maduranga
 * Student ID: 20241097
 * Module: Algorithms Coursework
 */

// Acyclic check using the sink elimination algorithm.

import java.util.Set;
import java.util.TreeSet;

// Utility class for sink elimination logic.
public class SinkElimination {

    // Check whether the graph is acyclic using sink elimination.
    public static boolean isAcyclic(Graph graph) {
        // Work on a copy so the original graph is unchanged.
        Graph workingGraph = graph.copy();

        while (!workingGraph.isEmpty()) {
            // Find a sink (out-degree 0) in the current graph.
            Integer sink = findSink(workingGraph);

            if (sink == null) {
                // No sink means there is a cycle.
                System.out.println("Graph is acyclic: NO");
                return false;
            }

            System.out.println("Sink found and removed: " + sink);
            // Remove the sink and repeat on the smaller graph.
            workingGraph.removeVertex(sink);
        }

        System.out.println("Graph is acyclic: YES");
        return true;
    }

    // Find one sink (vertex with no outgoing edges).
    private static Integer findSink(Graph graph) {
        // Use sorted order to make the output deterministic.
        Set<Integer> sortedVertices = new TreeSet<>(graph.getVertices());

        for (Integer vertex : sortedVertices) {
            // A sink has out-degree 0.
            if (graph.getOutDegree(vertex) == 0) {
                return vertex;
            }
        }

        return null;
    }
}
