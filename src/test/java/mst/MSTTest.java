package mst;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class MSTTest {

    private Graph createSampleGraph() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D", "E");
        List<Edge> edges = Arrays.asList(
                new Edge("A", "B", 4),
                new Edge("A", "C", 3),
                new Edge("B", "C", 2),
                new Edge("B", "D", 5),
                new Edge("C", "D", 7),
                new Edge("C", "E", 8),
                new Edge("D", "E", 6)
        );
        return new Graph(nodes, edges);
    }

    @Test
    public void testPrimAndKruskalEquality() {
        Graph g = createSampleGraph();
        PrimMST.PrimResult prim = new PrimMST().runPrim(g);
        KruskalMST.KruskalResult krus = new KruskalMST().runKruskal(g);

        assertEquals(prim.totalCost, krus.totalCost);
        assertEquals(g.vertexCount() - 1, prim.mstEdges.size());
        assertEquals(g.vertexCount() - 1, krus.mstEdges.size());
    }
}
