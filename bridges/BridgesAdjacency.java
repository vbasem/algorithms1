package bridges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BridgesAdjacency {
    public static void main(String[] args) {
        BridgesAdjacency graph = new BridgesAdjacency(9);
        // graph.add("A", "D");
        // graph.add("J", "M");
        // graph.add("J", "L");
        // graph.add("K", "J");
        // graph.add("F", "K");
        // graph.add("F", "J");
        // graph.add("E", "F");
        // graph.add("E", "D");
        // graph.add("E", "A");

        // graph.add("D", "H");
        // graph.add("D", "G");
        // graph.add("C", "B");
        // graph.add("C", "A");
        // graph.add("B", "D");
        // graph.add("G", "I");
        // graph.add("I", "L");
        // graph.add("H", "I");

        graph.add(0, 1, 0);
        graph.add(0, 2, 0);
        graph.add(1, 2, 0);
        graph.add(2, 3, 0);
        graph.add(3, 4, 0);
        graph.add(2, 5, 0);
        graph.add(5, 6, 0);
        graph.add(6, 7, 0);
        graph.add(7, 8, 0);
        graph.add(8, 5, 0);

        graph.findBridges();


        for (int i = 0; i < graph.bridges.size()/2; i++) {
            System.out.println("bridge from " + graph.bridges.get(2*i) + " to " + graph.bridges.get(2*i +1));
        }
    }
    List<Integer> bridges = new ArrayList<>();
    private void findBridges() {
        int[] ids = new int[graph.size()];
        int[] lows = new int[graph.size()];
        boolean[] visited = new boolean[graph.size()];
        int id = 0;


        for (int i = 0; i < graph.size(); i++) {
            id = i;
            if (!visited[i]) {
                findBridges(i,-1, 0, lows, visited, ids);
            }
        }

    }

    private void findBridges(int edge,int parent, int id,  int[] lows, boolean[] visited, int[] ids) {
        visited[edge] = true;
        id = id +1;
        ids[edge] = lows[edge] = id;

        for (int next : graph.get(edge)) {
            if (next == parent) {
                continue;
            }
            if (!visited[next]) {
                 findBridges(next, edge, id, lows, visited, ids);
                 lows[edge] = Math.min(lows[edge], lows[next]);
                 if (ids[edge] < lows[next]) {
                     bridges.add(edge);
                     bridges.add(next);
                 }
            } else {
                lows[edge] = Math.min(lows[edge], ids[next]);
            }
        }
    }

    List<List<Integer>> graph;

    public BridgesAdjacency(int size) {
        this.graph = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public void add(int from, int to, int weight) {
        this.graph.get(from).add(to);
        this.graph.get(to).add(from);
    }
}

class Edge {
    int u;
    int w;

    public Edge(int u, int w) {
        this.u = u;
        this.w = w;
    }
}
