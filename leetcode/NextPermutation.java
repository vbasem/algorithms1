package leetcode;

import java.util.Arrays;

public class NextPermutation {

    // [] -> []
    // [x] -> [x]
    // [1,2] -> [2,1]
    // [2,1] -> [1,2]
    // [1,2,3] -> [1] + [2,3] -> 1+ [3,2] swapped -> stop
    // [1,2,2] -> [1] + [2,2] -> no swap  [1,2] + [2], [2,1] + [2] -> swap
    // [4,3,2,1] -> [4,3] -> [2,1] -> np swap 4 + 3,2+  [,1] -> no swap 4,3 [2,1 -> no swap

    // for i = n -1
    // compare data[n] with data[n-1]
    // if swapped return result , else i = n-2
    // if n == 0 -> and no swap -> do a merge sort

    public static void main(String[] args) {
        NextPermutation solver = new NextPermutation();
        System.out.println(Arrays.toString(solver.solve(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(solver.solve(new int[]{1})));
        System.out.println(Arrays.toString(solver.solve(new int[]{1, 1, 5})));
        System.out.println(Arrays.toString(solver.solve(new int[]{1, 5, 1})));
        System.out.println(Arrays.toString(solver.solve(new int[]{3, 2, 1})));
        System.out.println(Arrays.toString(solver.solve(new int[]{8, 7, 6, 5, 4, 3, 2, 1})));
        System.out.println(Arrays.toString(solver.solve(new int[]{1, 5, 8, 4, 7, 6, 5, 3, 1})));
    }

    public int[] solve(int[] data) {
        int indexOfSwap = -1;
        for (int i = data.length - 2; i >= 0; i--) {
            if (data[i +1] > data[i]) {
                int difference = data[i+ 1] - data[i];
                indexOfSwap = i;
                int swappedPos = i+1;
                for (int j = data.length - 1; j > i; j--)  {
                    if (data[j] - data[i] > 0 && data[j] - data[i]  < difference ) {
                        difference = data[j] - data[i];
                        swappedPos = j;
                    }
                }
                swap(data, i, swappedPos);
                break;
            }
        }
        if (indexOfSwap != -1)  {
            int i = indexOfSwap +1;
            int j  = data.length -1;
            while (i < j) {
                swap(data, i++, j--);
            }
        } else {
            reverse(data);
        }
        return data;
    }

    private void reverse(int[] data) {
        int i = 0;
        int j  = data.length -1;
        while (i < j) {
            swap(data, i++, j--);
        }
    }

    private void swap(int[] data, int first, int second) {
        int tmp = data[first];
        data[first] = data[second];
        data[second] = tmp;
    }
}
