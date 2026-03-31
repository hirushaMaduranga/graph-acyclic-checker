/*
 * Name: Hirusha Maduranga
 * Student ID: 20241097
 * Module: Algorithms Coursework
 */

import java.util.Set;
import java.util.TreeSet;

public class SinkElimination {

    // Check whether the graph is acyclic using sink elimination.
    public static boolean isAcyclic(Graph graph) {
        Graph workingGraph = graph.copy();

        while (!workingGraph.isEmpty()) {
            Integer sink = findSink(workingGraph);

            if (sink == null) {
                System.out.println("Graph is acyclic: NO");
                return false;
            }

            System.out.println("Sink found and removed: " + sink);
            workingGraph.removeVertex(sink);
        }

        System.out.println("Graph is acyclic: YES");
        return true;
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
