package tsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TravellingSalesmanDp {

    public static void main(String[] args) {
        int[][] graph = new int[7][7];
        // for (int i = 0; i <graph.length; i++) {
        // graph[i] = new int[4];
        // }

        graph[0][0] = 0;
        graph[0][1] = 4;
        graph[0][2] = 1;
        graph[0][3] = 9;
        graph[0][4] = 1;
        graph[0][5] = 2;
        graph[0][6] = 3;

        graph[1][0] = 3;
        graph[1][1] = 0;
        graph[1][2] = 6;
        graph[1][3] = 11;
        graph[1][4] = 4;
        graph[1][5] = -2;
        graph[1][6] = 5;

        graph[2][0] = 4;
        graph[2][1] = 1;
        graph[2][2] = 0;
        graph[2][3] = 2;
        graph[2][4] = 2;
        graph[2][5] = 8;
        graph[2][6] = 11;

        graph[3][0] = 6;
        graph[3][1] = 5;
        graph[3][2] = -4;
        graph[3][3] = 0;
        graph[3][4] = 5;
        graph[3][5] = 15;
        graph[3][6] = 13;

        graph[4][0] = 2;
        graph[4][1] = 1;
        graph[4][2] = -3;
        graph[4][3] = 4;
        graph[4][4] = 0;
        graph[4][5] = 1;
        graph[4][6] = 5;

        graph[5][0] = 4;
        graph[5][1] = 3;
        graph[5][2] = -1;
        graph[5][3] = 4;
        graph[5][4] = 11;
        graph[5][5] = 0;
        graph[5][6] = 20;


        graph[6][0] = 1;
        graph[6][1] = 3;
        graph[6][2] = 2;
        graph[6][3] = -1;
        graph[6][4] = -3;
        graph[6][5] = 5;
        graph[6][6] = 0;


        
        
        TravellingSalesmanDp solver = new TravellingSalesmanDp();
        int[][] solution = solver.solve(graph);

        for (int i = 0; i < solution.length; i++) {
            System.out.println(Arrays.toString(solution[i]));
        }
        System.out.println("cache = " + solver.cacheHit);

        System.out.println("1 << 1 = " + (1 << 1));
    }

    public int[][] solve(int[][] graph) {
        int[][] result = new int[graph.length][graph.length + 1];
        Arrays.fill(result, new int[graph.length + 1]);
        Map<String, int[]> memo = new HashMap<>();

        for (int i = 0; i < graph.length; i++) {
            Set<Integer> visited = new LinkedHashSet<>();
            result[i] = solveForEach(graph, i, i, visited, memo);
        }

        return result;
    }

    int cacheHit = 0;

    public int[] solveForEach(int[][] graph, int start, int current, Set<Integer> visited, Map<String, int[]> memo) {
        visited.add(current);

        if (visited.size() == graph.length) {
            int[] ret = new int[graph.length + 1];
            ret[graph.length] = graph[current][start];
            return ret;
        }

        String key = visited.stream().map(n -> n + "").collect(Collectors.joining(":"));

        if (memo.containsKey(key)) {
            cacheHit++;
            return memo.get(key);
        }

        int[] best = null;
        int[] result;
        for (int i = 0; i < graph.length; i++) {
            if (visited.contains(i)) {
                continue;
            }
            Set<Integer> tempVisited = new LinkedHashSet<>(visited);
            result = solveForEach(graph, start, i, tempVisited, memo);
            result[i] = graph[current][i];
            int sum = sumOfTrip(result);
            if (best == null || sum < sumOfTrip(best)) {
                best = result;
            }
        }
        memo.put(key, best);
        return best;
    }

    private int sumOfTrip(int[] result) {
        return Arrays.stream(result).sum();
    }

}
