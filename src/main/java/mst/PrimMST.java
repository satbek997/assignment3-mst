package mst;

import java.util.*;

public class PrimMST {

    public static class PrimResult {
        public List<Edge> mstEdges = new ArrayList<>();
        public int totalCost = 0;
        public long operationsCount = 0;
        public double execTimeMs = 0.0;
    }

    public PrimResult runPrim(Graph g) {
        long start = System.nanoTime();
        PrimResult result = new PrimResult();

        if (g.nodes.isEmpty()) return result;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Set<String> inMST = new HashSet<>();

        String startNode = g.nodes.get(0);
        inMST.add(startNode);
        for (Edge e : g.getNeighbors(startNode)) {
            pq.add(e);
            result.operationsCount++;
        }

        while (inMST.size() < g.vertexCount() && !pq.isEmpty()) {
            Edge smallest = pq.poll();
            result.operationsCount++;
            if (inMST.contains(smallest.to)) continue;

            inMST.add(smallest.to);
            result.mstEdges.add(smallest);
            result.totalCost += smallest.weight;

            for (Edge next : g.getNeighbors(smallest.to)) {
                if (!inMST.contains(next.to)) {
                    pq.add(next);
                    result.operationsCount++;
                }
            }
        }

        result.execTimeMs = (System.nanoTime() - start) / 1_000_000.0;
        return result;
    }
}
