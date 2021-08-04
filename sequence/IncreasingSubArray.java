package sequence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncreasingSubArray {
    // [3,4,1,2,5,7]
    // [] -> []
    // [1] -> [1]
    // [1,2] -> [1] 0 > 0 [2]
    // [2, 1] -> [2] > [1]
    // [3,1,2] [3] [1,2]
    // [4,5,1,7,9] [4,5] 2 [1,7,9] 3 0 > 0
    public static void main(String[] args) {
        IncreasingSubArray solver = new IncreasingSubArray();
        List<Integer> result = solver.longestIncreasingSubsequence(new int[] {50, 3, 10, 7, 40, 80});
        result.forEach(e -> System.out.print(e + ","));
        System.out.println("\nsolver.counter = " + solver.counter);
    }

    public List<Integer> longestIncreasingSubsequence(int[] input) {
        Map<Integer, List<Integer>> memo = new HashMap<>();
        return getLongest(input, input.length - 1, memo);

    }
    int counter = 0;
    private List<Integer> getLongest(int[] input, int index, Map<Integer, List<Integer>> memo) {
        List<Integer> result = new ArrayList<Integer>();
  
        if (index < 0) {
            return result;
        }


        if (memo.containsKey(index))  {
            return memo.get(index);
        }

        List<Integer> temp = null;
        for (int i = index - 1; i >= 0; i--) {
            counter++;
            temp = getLongest(input, i, memo);
            if (input[index] > input[i]) {
                // temp.add(input[index]);
                if (temp.size() > result.size()) {
                    result.addAll(temp);
                }
            }
          
        }
        result.add(input[index]);
        memo.put(index, result);

        return result;
    }

}
