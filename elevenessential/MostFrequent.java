package elevenessential;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MostFrequent {

    public static void main(String[] args) {
        MostFrequent solver = new MostFrequent();
        // int[] in = { 1, 3, 1, 3, 2, 1, 1, 3, 3, 3 };
        int[] in = { 0 };
                // mostFrequent(array1) should return 1.
                int[] array1 = {1, 3, 1, 3, 2, 1};
                // mostFrequent(array2) should return 3.
                int[] array2 = {3, 3, 1, 3, 2, 1};
                // mostFrequent(array3) should return null.
                int[] array3 = {};
                // mostFrequent(array4) should return 0.
                int[] array4 = {0};
                // mostFrequent(array5) should return -1.
                int[] array5 = {0, -1, 10, 10, -1, 10, -1, -1, -1, 1};
        System.out.println("solver.mostFrequent(in) = " + Arrays.toString(solver.mostFrequent(array3)));
        System.out.println("solver.mostFrequent(in) = " + Arrays.toString(solver.mostFrequentDictionary(in)));
    }

    public int[] mostFrequentDictionary(int[] input) {
        Map<Integer, Integer> freq = new HashMap<>();
        int max = 0;
        int maxKey = -1;

        for (int i = 0; i < input.length; i++) {
            int key = input[i];
            int count = freq.getOrDefault(key, 0) + 1;
            freq.put(key, count );
            if (count > max) {
                max = count;
                maxKey = key;
            }
        }

        return new int[] { maxKey, max };
    }

    public int[] mostFrequent(int[] input) {

        int mid = input.length / 2;
        return consolidate(input, solve(input, 0, mid), solve(input, mid, input.length), 0, mid, input.length);

    }

    private int[] consolidate(int[] input, int[] left, int[] right, int start, int mid, int end) {
        if (left[0] == right[0]) {
            return new int[] { left[0], left[1] + right[1] };
        } else {
            int rightCountInLeftSide = count(input, right[0], start, mid) + right[1];
            int leftCountInRightside = count(input, left[0], mid, end) + left[1];

            if (rightCountInLeftSide > leftCountInRightside) {
                return new int[] { right[0], rightCountInLeftSide };
            } else {
                return new int[] { left[0], leftCountInRightside };
            }
        }
    }

    private int count(int[] input, int target, int start, int end) {
        int count = 0;
        for (int i = start; i < end; i++) {
            if (input[i] == target) {
                count++;
            }
        }

        return count;
    }

    private int[] solve(int[] input, int start, int end) {

        if (start == end || end - start == 1) {
            return new int[] { input[start], 1 };
        }

        int mid = (end + start) / 2;

        return consolidate(input, solve(input, start, mid), solve(input, mid, end), start, mid, end);

    }

}
