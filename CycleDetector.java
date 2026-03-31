/*
 * Name: Hirusha Maduranga
 * Student ID: 20241097
 * Module: Algorithms Coursework
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CycleDetector {

    // Find and print one cycle if it exists.
    public static void printCycle(Graph graph) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> onStack = new HashSet<>();
        List<Integer> path = new ArrayList<>();

        for (Integer vertex : new TreeSet<>(graph.getVertices())) {
            if (!visited.contains(vertex)) {
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
        visited.add(current);
        onStack.add(current);
        path.add(current);

        for (Integer neighbor : graph.getNeighbors(current)) {
            if (!visited.contains(neighbor)) {
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
                cycle.add(neighbor);
                return cycle;
            }
        }

        onStack.remove(current);
        path.remove(path.size() - 1);
        return null;
    }
}
