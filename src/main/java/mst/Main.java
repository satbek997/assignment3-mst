package mst;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Main {

    public static class GraphInput {
        public int id;
        public List<String> nodes;
        public List<Edge> edges;
    }

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("data/input.json"));
        JsonNode graphsArray = root.get("graphs");

        List<GraphInput> graphList = new ArrayList<>();
        for (JsonNode gNode : graphsArray) {
            GraphInput gi = new GraphInput();
            gi.id = gNode.get("id").asInt();
            gi.nodes = new ArrayList<>();
            for (JsonNode n : gNode.get("nodes")) gi.nodes.add(n.asText());
            gi.edges = new ArrayList<>();
            for (JsonNode e : gNode.get("edges"))
                gi.edges.add(new Edge(e.get("from").asText(),
                        e.get("to").asText(),
                        e.get("weight").asInt()));
            graphList.add(gi);
        }

        PrimMST primAlgo = new PrimMST();
        KruskalMST krusAlgo = new KruskalMST();

        ArrayNode resultsArray = mapper.createArrayNode();
        FileWriter csv = new FileWriter("data/mst_results.csv");
        csv.write("Graph_ID,Vertices,Edges,Algorithm,MST_Cost,Operations,Execution_Time_ms\n");

        for (GraphInput gi : graphList) {
            Graph g = new Graph(gi.nodes, gi.edges);
            var prim = primAlgo.runPrim(g);
            var krus = krusAlgo.runKruskal(g);

            boolean sameCost = prim.totalCost == krus.totalCost;
            System.out.println("Graph " + gi.id + " same cost: " + sameCost);

            ObjectNode graphNode = mapper.createObjectNode();
            graphNode.put("graph_id", gi.id);
            ObjectNode stats = mapper.createObjectNode();
            stats.put("vertices", g.vertexCount());
            stats.put("edges", g.edgeCount());
            graphNode.set("input_stats", stats);

            ObjectNode primNode = mapper.createObjectNode();
            primNode.put("total_cost", prim.totalCost);
            primNode.put("operations_count", prim.operationsCount);
            primNode.put("execution_time_ms", prim.execTimeMs);
            ArrayNode primEdges = mapper.createArrayNode();
            for (Edge e : prim.mstEdges) {
                ObjectNode en = mapper.createObjectNode();
                en.put("from", e.from);
                en.put("to", e.to);
                en.put("weight", e.weight);
                primEdges.add(en);
            }
            primNode.set("mst_edges", primEdges);
            graphNode.set("prim", primNode);

            ObjectNode krusNode = mapper.createObjectNode();
            krusNode.put("total_cost", krus.totalCost);
            krusNode.put("operations_count", krus.operationsCount);
            krusNode.put("execution_time_ms", krus.execTimeMs);
            ArrayNode krusEdges = mapper.createArrayNode();
            for (Edge e : krus.mstEdges) {
                ObjectNode en = mapper.createObjectNode();
                en.put("from", e.from);
                en.put("to", e.to);
                en.put("weight", e.weight);
                krusEdges.add(en);
            }
            krusNode.set("mst_edges", krusEdges);
            graphNode.set("kruskal", krusNode);

            resultsArray.add(graphNode);

            csv.write(String.format(Locale.US,
                    "%d,%d,%d,Prim,%d,%d,%.3f\n",
                    gi.id, g.vertexCount(), g.edgeCount(),
                    prim.totalCost, prim.operationsCount, prim.execTimeMs));
            csv.write(String.format(Locale.US,
                    "%d,%d,%d,Kruskal,%d,%d,%.3f\n",
                    gi.id, g.vertexCount(), g.edgeCount(),
                    krus.totalCost, krus.operationsCount, krus.execTimeMs));
        }

        ObjectNode rootOut = mapper.createObjectNode();
        rootOut.set("results", resultsArray);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data/output.json"), rootOut);
        csv.close();
        System.out.println("✅ Results saved to data/output.json and data/mst_results.csv");
    }
}
