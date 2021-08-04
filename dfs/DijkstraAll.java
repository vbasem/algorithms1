package dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstraAll {

    public static void main(String[] args) {
        DijkstraAll solver = new DijkstraAll();
        YetAnotherEdge[][] graph = { { new YetAnotherEdge(1, 1), new YetAnotherEdge(3, 2) },
                { new YetAnotherEdge(2, 6) }, { new YetAnotherEdge(5, 2), new YetAnotherEdge(4, 1) },
                { new YetAnotherEdge(1, 4), new YetAnotherEdge(4, 3) }, { new YetAnotherEdge(5, 1) }, {}, };

        Map<Integer, YetAnotherEdge> solution = solver.shortestPath(graph, 0);

        for (Integer item : solution.keySet()) {
            System.out.println("Shortest path to " + item + " : " + solution.get(item).cost + " coming from: "
                    + solution.get(item).to);
        }
    }

    public Map<Integer, YetAnotherEdge> shortestPath(YetAnotherEdge[][] graph, int start) {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, YetAnotherEdge> costs = new HashMap<>();
        ;
        PriorityQueue<YetAnotherEdge> queue = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.cost, e2.cost));

        for (int i = 0; i < graph.length; i++) {
            costs.put(i, new YetAnotherEdge(i, Integer.MAX_VALUE));
        }

        costs.put(start, new YetAnotherEdge(start, 0));
        queue.add(costs.get(start));
        YetAnotherEdge e = null;

        while ((e = queue.poll()) != null) {
            if (!visited.contains(e.to)) {
                calculateShortestPath(e.to, graph, visited, costs, queue);
            }
        }

        return costs;
    }

    private void calculateShortestPath(int current, YetAnotherEdge[][] graph, Set<Integer> visited,
            Map<Integer, YetAnotherEdge> costs, PriorityQueue<YetAnotherEdge> queue) {
        visited.add(current);

        for (int i = 0; i < graph[current].length; i++) {
            int nextCost = costs.get(current).cost + graph[current][i].cost;
            int nextElement = graph[current][i].to;
            if (costs.get(nextElement).cost > nextCost) {
                costs.put(nextElement, new YetAnotherEdge(current, nextCost));
            }
            queue.add(new YetAnotherEdge(nextElement, costs.get(nextElement).cost));
        }

    }

}

class YetAnotherEdge {
    int cost;
    int to;

    public YetAnotherEdge(int to, int cost) {
        this.to = to;
        this.cost = cost;

    }
}
