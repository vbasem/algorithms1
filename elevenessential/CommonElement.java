package elevenessential;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonElement {

    public static void main(String[] args) {
        CommonElement solver = new CommonElement();
        int[] a = { 1, 3, 4, 6, 7, 9 };
        int[] b = { 1, 2, 4, 5, 9, 10 };
        List<Integer> result = solver.findCommonElements(a, b);
        List<Integer> result2 = solver.findCommonElementsUsingSort(a, b);
        Integer[] resultInArray = new Integer[result.size()];
        result.toArray(resultInArray);
                System.out.println("result = " + Arrays.toString(result.toArray()));
        System.out.println("result = " + Arrays.toString(result2.toArray()));
    }

    public List<Integer> findCommonElements(int[] first, int[] second) {
        Set<Integer> lookup = new HashSet<>(first.length);
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < first.length; i++) {
            lookup.add(first[i]);
        }

        for (int i = 0; i < second.length; i++) {
            if (lookup.contains(second[i])) {
                result.add(second[i]);
            }
        }

        return result;
    }

    public List<Integer> findCommonElementsUsingSort(int[] a, int[] b) {
        List<Integer> result = new ArrayList<>();

        int i = 0;
        int k = 0;

        while (i < a.length && k < b.length) {
            if (a[i] == b[k]) {
                result.add(a[i]);
                i++;
                k++;
            } else if (a[i] < b[k]) {
                i++;
            } else {
                k++;
            }

        }
        return result;

    }

}
