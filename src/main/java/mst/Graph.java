package mst;

import java.util.*;

public class Graph {
    public List<String> nodes;
    public List<Edge> edges;
    private Map<String, List<Edge>> adj;

    public Graph(List<String> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
        buildAdj();
    }

    private void buildAdj() {
        adj = new HashMap<>();
        for (String n : nodes) adj.put(n, new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.from).add(e);
            adj.get(e.to).add(new Edge(e.to, e.from, e.weight));
        }
    }

    public List<Edge> getNeighbors(String node) {
        return adj.get(node);
    }

    public int vertexCount() {
        return nodes.size();
    }

    public int edgeCount() {
        return edges.size();
    }
}
