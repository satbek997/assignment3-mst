package mst;

import java.util.*;

public class KruskalMST {

    public static class KruskalResult {
        public List<Edge> mstEdges = new ArrayList<>();
        public int totalCost = 0;
        public long operationsCount = 0;
        public double execTimeMs = 0.0;
    }

    public KruskalResult runKruskal(Graph g) {
        long start = System.nanoTime();
        KruskalResult result = new KruskalResult();

        List<Edge> sorted = new ArrayList<>(g.edges);
        Collections.sort(sorted);
        result.operationsCount += sorted.size();

        DisjointSet ds = new DisjointSet();
        ds.makeSet(g.nodes);

        for (Edge e : sorted) {
            result.operationsCount++;
            if (ds.union(e.from, e.to)) {
                result.mstEdges.add(e);
                result.totalCost += e.weight;
            }
            if (result.mstEdges.size() == g.vertexCount() - 1) break;
        }

        result.execTimeMs = (System.nanoTime() - start) / 1_000_000.0;
        return result;
    }
}
