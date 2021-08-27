package leo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        TwoSum solver = new TwoSum();

        assert Arrays.equals(solver.findTwoSums(new int[]{2,7,11,15}, 9), new int[] {0,1});
        assert Arrays.equals(solver.findTwoSums(new int[]{0,4,3,0}, 0), new int[] {0,3});
    }

    Map<Integer, Integer> lookup = new HashMap<>();

    public int[] findTwoSums(int[] input, int target) {

        if (isInvalidInput(input)) {
            return new int[]{};
        }

        for (int i = 0; i < input.length; i++) {
            if (lookup.containsKey(input[i])) {
                return new int[]{lookup.get(input[i]), i};
            } else {
                lookup.put(target - input[i], i);
            }
        }

        return new int[]{};
    }


    public boolean isInvalidInput(int[] input) {

        return input == null || input.length == 0;
    }
}
