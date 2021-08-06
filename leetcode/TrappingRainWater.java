package leetcode;

import java.util.HashMap;
import java.util.Map;

public class TrappingRainWater {
    public static void main(String[] args) {
        TrappingRainWater solver = new TrappingRainWater();
        assert solver.solve(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}) == 6;
        System.out.println(solver.cache);
        assert solver.solve(new int[]{2, 0, 2}) == 2;
        assert solver.solve(new int[]{3, 0, 2, 0, 4}) == 7;
    }
    Map<Integer, Integer> memo;
    int cache = 0;

    private int solve(int[] elevations) {
        memo = new HashMap<>();
        cache = 0;
        boolean[] visited = new boolean[elevations.length];
        int total = 0;
        for (int i = 0; i < elevations.length; i++) {
            if (!visited[i]) {
                total += solveFor(elevations, i, visited);
            }
        }
        return total;
    }

    // good luck understanding this overcomplicated logic
    private int solveFor(int[] elevations, int index, boolean[] visited) {
        visited[index] = true;
        if (memo.containsKey(index))  {
            return memo.get(index);
        }
        cache++;
        int next = index + 1;
        int water = 0;
        int totalWater = 0;
        while (next < elevations.length) {
            if (elevations[next] >= elevations[index]) {
                totalWater += water;
                break;
            }
            water += (elevations[index] - elevations[next]);
            int nextWater = solveFor(elevations, next, visited);
            totalWater += nextWater;
            water -= nextWater;
            next++;
        }
        memo.put(index, totalWater);
        return totalWater;
    }
}
