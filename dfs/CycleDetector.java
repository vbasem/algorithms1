package dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CycleDetector {

    public static void main(String[] args) {
        CycleDetector solver = new CycleDetector();
        int[][] graph = {
            {2, 4},
            {},
            {1,3},
            {},
            {5},
            {0}
        };
        boolean result = solver.hasCycle(graph);
        System.out.println("result = " + result);

    }

    public boolean hasCycle(int[][] graph) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> beingVistied = new HashSet<>();
        Map<Integer, Integer> path = new HashMap<>();

        path.put(0, null);
        return detectCycle(0, graph, beingVistied, visited, path);
    }

    private boolean detectCycle(int current, int[][] graph, Set<Integer> beingVistied, Set<Integer> visited,
            Map<Integer, Integer> path) {
            System.out.println("visitng = " + current);
            beingVistied.add(current);
        for (int i =0 ; i <graph[current].length; i++)  {
            if (!visited.contains(graph[current][i]))  {
                if (beingVistied.contains(graph[current][i]))  {
                    System.out.println("found being visitede = " + graph[current][i]);
                    return true;
                }
                if (detectCycle(graph[current][i], graph, beingVistied, visited, path)) {
                    return true;
                }
            }
        }
        beingVistied.remove( current);
        visited.add(current);

        return false;
    }

}
