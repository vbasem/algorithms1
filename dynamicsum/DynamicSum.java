package dynamicsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DynamicSum {

    public static void main(String[] args) {
        DynamicSum solver = new DynamicSum();
        int target = 8;
        System.out.println(solver.hasSum(target, new int[] { 7, 1, 3, 4 }, new Boolean[target + 1]));
        System.out.println(Arrays.toString(solver.whichSum(target, new int[] { 2,3,5,1 })));
        // System.out.println(Arrays.toString(solver.bestSum(target, new int[] {3, 1, 5,
        // 7})));
    }

    public Object[] whichSum(int target, int[] nums) {
        List<Integer>[] memo = new ArrayList[target + 1];
        Object[] data = solveWhichSum(target, nums, memo).toArray();
        return data;

    }

    private List<Integer> solveWhichSum(int target, int[] nums, List<Integer>[] memo) {
        if (target == 0) {
            return new ArrayList<>();
        } else if (target < 0) {
            return null;
        } else if (memo[target] != null) {
            return memo[target];

        } else {

            List<Integer> result = null;
            List<Integer> tmp = null;
            for (int i = 0; i < nums.length; i++) {
                result = solveWhichSum(target - nums[i], nums, memo);
                if (result != null) {

                    if (tmp == null || (tmp.size() - 1 > result.size())) {
                        tmp = new ArrayList<>(result.size());
                        for (int k = 0; k < result.size(); k++) {
                            tmp.add(result.get(k));
                        }
                        tmp.add(nums[i]);
                    }
                }
            }
            memo[target] = tmp;
            return tmp;
        }
    }

    public boolean hasSum(int n, int[] nums, Boolean[] memo) {

        if (n == 0) {
            return true;
        }

        if (n < 0) {
            return false;
        }

        if (memo[n] != null) {
            return memo[n];
        }

        boolean result = false;
        for (int i = 0; i < nums.length; i++) {
            result = hasSum(n - nums[i], nums, memo);
            if (result) {
                break;
            }
        }
        memo[n] = result;
        return result;
    }

    // public boolean solveHasSum(int n, int index, int[] num) {

    // }

}
