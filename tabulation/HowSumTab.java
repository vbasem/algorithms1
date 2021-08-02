package tabulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HowSumTab {

    public static void main(String[] args) {

        HowSumTab solver = new HowSumTab();
        System.out.println("solver.howSum = " + Arrays.toString(solver.howSum(15, new int[]{1, 16,3,5,2})));
        System.out.println("solver.howSum = " + Arrays.toString(solver.bestSum(15, new int[]{1, 10, 5, 16,3,5,2})));

    }

    public Integer[] bestSum(int target, int[] nums) {
        int[] tab = new int[target + 1];
        Arrays.fill(tab, -1);
        tab[0] = 0;

        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != -1) {

                for (int k = 0; k < nums.length; k++) {
                    if (i + nums[k] < tab.length) {
                        if (tab[i + nums[k]] < nums[k] )
                        tab[i + nums[k]] = nums[k];
                    }
                }
            }
        }

        return reconstructSum(tab);

    }

    public Integer[] howSum(int target, int[] nums) {
        int[] tab = new int[target + 1];
        Arrays.fill(tab, -1);
        tab[0] = 0;

        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != -1) {

                for (int k = 0; k < nums.length; k++) {
                    if (i + nums[k] < tab.length) {
                        tab[i + nums[k]] = nums[k];
                    }
                }
            }
        }

        return reconstructSum(tab);

    }

    private Integer[] reconstructSum(int[] tab) {
        List<Integer> result = new ArrayList<>(tab.length);
        if (tab[tab.length - 1] == -1) {
            return new Integer[] {};
        } else {
            int prev = tab.length - 1;
            while (prev != 0) {
                result.add(tab[prev]);
                prev = prev - tab[prev];
            }
        }
        Integer[] ret = new Integer[result.size()];
        return result.toArray(ret);

    }

}
