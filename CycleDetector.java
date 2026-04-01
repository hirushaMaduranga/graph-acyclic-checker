/*
 * Name: Hirusha Maduranga
 * Student ID: 20241097
 * Module: Algorithms Coursework
 */

// Cycle detection using DFS with a recursion stack.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

// Utility class for DFS cycle detection.
public class CycleDetector {

    // Find and print one cycle if it exists.
    public static void printCycle(Graph graph) {
        // Track visited nodes to avoid repeated visits.
        Set<Integer> visited = new HashSet<>();
        // Track current recursion stack to detect back edges.
        Set<Integer> onStack = new HashSet<>();
        // Store the current DFS path for cycle reconstruction.
        List<Integer> path = new ArrayList<>();

        for (Integer vertex : new TreeSet<>(graph.getVertices())) {
            if (!visited.contains(vertex)) {
                // Start DFS from each unvisited vertex.
                List<Integer> cycle = dfs(vertex, graph, visited, onStack, path);
                if (cycle != null) {
                    System.out.print("Cycle found: ");
                    for (int i = 0; i < cycle.size(); i++) {
                        System.out.print(cycle.get(i));
                        if (i < cycle.size() - 1) {
                            System.out.print(" -> ");
                        }
                    }
                    System.out.println();
                    return;
                }
            }
        }

        System.out.println("Cycle found: none");
    }

    // DFS with recursion stack to detect a back-edge cycle.
    private static List<Integer> dfs(Integer current, Graph graph, Set<Integer> visited,
            Set<Integer> onStack, List<Integer> path) {
        // Mark current node as visited and on the active DFS path.
        visited.add(current);
        onStack.add(current);
        path.add(current);

        for (Integer neighbor : graph.getNeighbors(current)) {
            if (!visited.contains(neighbor)) {
                // Visit neighbor if it has not been seen yet.
                List<Integer> cycle = dfs(neighbor, graph, visited, onStack, path);
                if (cycle != null) {
                    return cycle;
                }
            } else if (onStack.contains(neighbor)) {
                // Reconstruct cycle from the current DFS path.
                int startIndex = path.indexOf(neighbor);
                List<Integer> cycle = new ArrayList<>();
                for (int i = startIndex; i < path.size(); i++) {
                    cycle.add(path.get(i));
                }
                // Close the cycle by returning to the start vertex.
                cycle.add(neighbor);
                return cycle;
            }
        }

        // Backtrack: remove current node from stack and path.
        onStack.remove(current);
        path.remove(path.size() - 1);
        return null;
    }
}
