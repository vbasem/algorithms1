package apig;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class KnapsackRecursive {

    Map<String, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
        KnapsackRecursive solver = new KnapsackRecursive();
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int W = 50;

        int[] val2 = {40, 100, 50, 60, 24, 11, 33, 44, 55, 11};
        int[] wt2 = {20, 10, 40, 30, 5, 23, 11, 23, 44, 12, 31};
        int W2 = 60;

        int[] val3 = {20, 5, 10, 40, 15, 25};
        int[] wt3 = {1, 2, 3, 8, 7, 4};
        int W3 = 10;

        assert solver.maxValue(val, wt, W) == 220;
        assert solver.maxValue(val2, wt2, W2) == 217 : """
                got %s""".formatted(solver.maxValue(val2, wt2, W2));
        assert solver.maxValue(val3, wt3, W3) == 60;

    }

    public int maxValue(int[] values, int[] weights, int sackSize) {
//        return helper(values, weights, sackSize, 0, 0);
        return solveButtomUp(values, weights, sackSize);

    }

    public int helper(int[] values, int[] weights, int sackSize, int item, int totalWeight) {


        if (totalWeight > sackSize) {
            return Integer.MIN_VALUE;
        }
        if (item >= values.length || totalWeight == sackSize) {
            return 0;
        }

        String key = item + ":" + totalWeight;
//        if (memo.containsKey(key)) {
//            return memo.get(key);
//        }

        int max = Math.max(values[item] + helper(values, weights, sackSize, item + 1, totalWeight + weights[item]),
                helper(values, weights, sackSize, item + 1, totalWeight));

        memo.put(key, max);
        return max;
    }

    public int solveButtomUp(int[] values, int[] weights, int capacity) {
        int[] dp = new int[capacity + 1];
        int[] lastDp;
        for (int itemIndex = 0; itemIndex < values.length; itemIndex++) {
            lastDp = dp;
            dp = new int[capacity + 1];
            for (int currentCapacity = 1; currentCapacity <= capacity; currentCapacity++) {
                if (weights[itemIndex] <= currentCapacity) {
                    dp[currentCapacity] = Math.max(lastDp[currentCapacity], values[itemIndex] + lastDp[currentCapacity - weights[itemIndex]]);
                } else {
                    dp[currentCapacity] = lastDp[currentCapacity];
                }
            }
        }
        return dp[dp.length - 1];
    }
}
