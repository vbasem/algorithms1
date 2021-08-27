package apig;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        MergeSort solver = new MergeSort();
        assert Arrays.equals(solver.sort(new int[]{4, 22, 1, 3, 5, 7, 26}), new int[]{1, 3, 4, 5, 7, 22, 26});
        assert Arrays.equals(solver.sort(new int[]{5, 4, 3, 2, 1}), new int[]{1, 2, 3, 4, 5});
        assert Arrays.equals(solver.sort(new int[]{}), new int[]{});
        assert Arrays.equals(solver.sort(null), new int[]{});
        assert Arrays.equals(solver.sort(new int[]{1}), new int[]{1});
        assert Arrays.equals(solver.sort(new int[]{-1, -2, -3, -4}), new int[]{-4, -3, -2, -1});
        assert Arrays.equals(solver.sort(new int[]{3, 2, 2, 2, 1}), new int[]{1, 2, 2, 2, 3});
        assert Arrays.equals(solver.sortDescending(new int[]{1, 2, 2, 2, 3}), new int[]{3, 2, 2, 2, 1});
    }

    public int[] sort(int[] data) {
        if (IsNotSortable(data)) return new int[]{};
        return helper(data, 0, data.length - 1, (x, y) -> x < y);
    }

    public int[] sortDescending(int[] data) {
        if (IsNotSortable(data)) return new int[]{};
        return helper(data, 0, data.length - 1, (x, y) -> x > y);
    }

    private boolean IsNotSortable(int[] data) {
        if (data == null || data.length == 0) {
            return true;
        }
        return false;
    }

    private int[] helper(int[] data, int start, int end, SortComparator comparator) {
        if (start == end) {
            return new int[]{data[start]};
        }

        int mid = (end + start) / 2;
        int[] left = helper(data, start, mid, comparator);
        int[] right = helper(data, mid + 1, end, comparator);

        return combine(left, right, comparator);

    }

    private int[] combine(int[] left, int[] right, SortComparator comparator) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        int r = 0;
        while (i < left.length & j < right.length) {
            if (comparator.isLess(left[i], right[j])) {
                result[r++] = left[i++];
            } else {
                result[r++] = right[j++];
            }
        }

        while (i < left.length) {
            result[r++] = left[i++];
        }


        while (j < right.length) {
            result[r++] = right[j++];
        }
        return result;
    }

    @FunctionalInterface
    interface SortComparator {
        boolean isLess(int x, int y);
    }
}
