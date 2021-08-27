package apig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BinPacking {

    public static void main(String[] args) {
        BinPacking solver = new BinPacking();
        int[] result = solver.pack(new int[]{8, 8, 8, 8, 8}, new int[]{4, 2, 7, 5, 6, 3});
        System.out.println(Arrays.toString(result));
    }


    public int[] pack(int[] bins, int[] items) {

        Arrays.sort(items);
        new ArrayList<Integer>().sort(Collections.reverseOrder());

        for (int i = items.length - 1; i >= 0; i--) {

            for (int j = 0; j < bins.length; j++) {
                if (items[i] <= bins[j]) {
                    bins[j] = bins[j] - items[i];
                    break;
                }
            }
        }

        return bins;
    }
}
