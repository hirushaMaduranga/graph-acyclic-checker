/*
 * Name: Hirusha Maduranga
 * Student ID: 20241097
 * Module: Algorithms Coursework
 */

// Cycle detection using DFS with a recursion stack.

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// Utility class for DFS cycle detection.
public class CycleDetector {

    // Detect one cycle using DFS and return it as a list.
    public static List<Integer> findCycle(Graph graph) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> onStack = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();

        for (Integer vertex : new TreeSet<>(graph.getVertices())) {
            if (!visited.contains(vertex)) {
                List<Integer> cycle = dfs(vertex, graph, visited, onStack, parent);
                if (cycle != null) {
                    return cycle;
                }
            }
        }

        return null;
    }

    // Print the cycle in the required format.
    public static void printCycle(List<Integer> cycle) {
        if (cycle == null || cycle.isEmpty()) {
            System.out.println("Cycle found: none");
            return;
        }

        System.out.print("Cycle found: ");
        for (int i = 0; i < cycle.size(); i++) {
            System.out.print(cycle.get(i));
            if (i < cycle.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    // DFS with recursion stack to detect a back-edge cycle.
    private static List<Integer> dfs(Integer current, Graph graph, Set<Integer> visited,
            Set<Integer> onStack, Map<Integer, Integer> parent) {
        visited.add(current);
        onStack.add(current);

        for (Integer neighbor : graph.getNeighbors(current)) {
            if (!visited.contains(neighbor)) {
                parent.put(neighbor, current);
                List<Integer> cycle = dfs(neighbor, graph, visited, onStack, parent);
                if (cycle != null) {
                    return cycle;
                }
            } else if (onStack.contains(neighbor)) {
                // Reconstruct cycle by walking back through parents.
                List<Integer> cycle = new ArrayList<>();
                cycle.add(neighbor);
                int node = current;
                while (node != neighbor) {
                    cycle.add(node);
                    Integer next = parent.get(node);
                    if (next == null) {
                        break;
                    }
                    node = next;
                }
                cycle.add(neighbor);
                Collections.reverse(cycle);
                return cycle;
            }
        }

        onStack.remove(current);
        return null;
    }
}
