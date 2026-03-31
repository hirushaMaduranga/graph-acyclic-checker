/*
 * Name: Hirusha Maduranga
 * Student ID: 20241097
 * Module: Algorithms Coursework
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
    private final Map<Integer, List<Integer>> adjacency;
    private final Set<Integer> vertices;

    public Graph() {
        adjacency = new HashMap<>();
        vertices = new HashSet<>();
    }

    // Add a vertex even if it has no edges.
    public void addVertex(int v) {
        vertices.add(v);
        adjacency.computeIfAbsent(v, k -> new ArrayList<>());
    }

    // Add a directed edge from -> to.
    public void addEdge(int from, int to) {
        addVertex(from);
        addVertex(to);
        adjacency.get(from).add(to);
    }

    // Return a copy of the vertices set.
    public Set<Integer> getVertices() {
        return new HashSet<>(vertices);
    }

    // Return a copy of the outgoing neighbours for a vertex.
    public List<Integer> getNeighbors(int vertex) {
        return new ArrayList<>(adjacency.getOrDefault(vertex, new ArrayList<>()));
    }

    // Return the outgoing degree of a vertex.
    public int getOutDegree(int vertex) {
        return adjacency.getOrDefault(vertex, new ArrayList<>()).size();
    }

    // Remove a vertex and all edges going to it.
    public void removeVertex(int vertex) {
        vertices.remove(vertex);
        adjacency.remove(vertex);

        for (List<Integer> neighbors : adjacency.values()) {
            neighbors.removeIf(v -> v == vertex);
        }
    }

    // Create a deep copy of the graph.
    public Graph copy() {
        Graph newGraph = new Graph();

        for (Integer vertex : vertices) {
            newGraph.addVertex(vertex);
        }

        for (Integer vertex : adjacency.keySet()) {
            for (Integer neighbor : adjacency.get(vertex)) {
                newGraph.addEdge(vertex, neighbor);
            }
        }

        return newGraph;
    }

    public boolean isEmpty() {
        return vertices.isEmpty();
    }
}
