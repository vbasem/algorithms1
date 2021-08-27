package apig;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {

    public static void main(String[] args) {
        SubsetSum solver = new SubsetSum();
        List<Integer> subset = solver.findSubset(new int[]{23, 1,4,4, 8}, 8);
        subset.forEach(System.out::println);
    }

    public List<Integer> findSubset(int[] nums, int sum) {
        List<Integer> result = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return result;
        }

        boolean[][] dp = new boolean[nums.length + 1][Math.abs(sum) + 1];

        if (sum == 0) {
            int index = findZeroIndex(nums);
            if (index >= 0) {
                result.add(0);
            }

            return result;
        }

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= sum; j++) {
                int col = j - nums[i - 1];
                if (dp[i - 1][j]) {
                    dp[i][j] = dp[i - 1][j];
                } else if (col >= 0 && col <= sum) {
                    dp[i][j] = dp[i - 1][col];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return buildResult(dp, result, nums);
    }

    private int findZeroIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return i;
            }
        }

        return -1;
    }

    private List<Integer> buildResult(boolean[][] dp, List<Integer> result, int[] nums) {

        if (!dp[dp.length - 1][dp[0].length - 1]) {
            return result;
        }

        int row = dp.length - 1;
        int col = dp[0].length - 1;

        while (row > 0 || col > 0) {
            if (dp[row - 1][col]) {
                row--;
            } else {
                result.add(nums[row - 1]);
                col = col - nums[row - 1];
            }
        }
        return result;
    }
}
