package problem;

public class MaxCardPoints {

    public static void main(String[] args) {
        MaxCardPoints solver = new MaxCardPoints();

        assert solver.solve(new int[]{1, 79, 80, 1, 1, 1, 200, 1}, 3) == 202;
        assert solver.solve(new int[]{1, 1000, 1}, 1) == 1;
        assert solver.solve(new int[]{9, 7, 7, 9, 7, 7,9}, 7) == 55;
        assert solver.solve(new int[]{1, 2, 3, 4, 5, 6, 1}, 3) == 12;
    }

    private int solve(int[] nums, int pulls) {
        if (pulls == 0) {
            return 0;
        }

        return helper(nums, pulls , 0, nums.length - 1, 0);
    }

    private int helper(int[] nums, int pulls, int indexLeft, int indexRight, int sum) {
        if (pulls == 0) {
            return sum;
        }
        int left = helper(nums, pulls - 1, indexLeft + 1, indexRight, sum + nums[indexLeft] );
        int right = helper(nums, pulls - 1, indexLeft, indexRight - 1, sum + nums[indexRight]);

        return Math.max(left, right);

    }
}
