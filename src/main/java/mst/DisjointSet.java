package mst;

import java.util.*;

public class DisjointSet {
    private Map<String, String> parent = new HashMap<>();
    private Map<String, Integer> rank = new HashMap<>();

    public void makeSet(Iterable<String> nodes) {
        for (String v : nodes) {
            parent.put(v, v);
            rank.put(v, 0);
        }
    }

    public String find(String v) {
        if (!parent.get(v).equals(v))
            parent.put(v, find(parent.get(v)));
        return parent.get(v);
    }

    public boolean union(String a, String b) {
        String rootA = find(a);
        String rootB = find(b);
        if (rootA.equals(rootB)) return false;

        int rankA = rank.get(rootA);
        int rankB = rank.get(rootB);
        if (rankA < rankB) parent.put(rootA, rootB);
        else if (rankA > rankB) parent.put(rootB, rootA);
        else {
            parent.put(rootB, rootA);
            rank.put(rootA, rankA + 1);
        }
        return true;
    }
}
