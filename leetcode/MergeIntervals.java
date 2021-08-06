package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public static void main(String[] args) {
        assert Arrays.deepEquals(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}), new int[][]{{1, 6}, {8, 10}, {15, 18}});
        assert Arrays.deepEquals(merge(new int[][]{{1, 4}, {4, 5}}), new int[][]{{1, 5}});
        assert Arrays.deepEquals(merge(new int[][]{{1, 4}, {4, 5}, {2, 10}, {11, 15}, {16, 18}, {18, 19}}), new int[][]{{1, 10}, {11, 15}, {16, 19}});
        assert Arrays.deepEquals(merge(new int[][]{}), new int[][]{});

        assert Arrays.deepEquals(mergeWithSort(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}), new int[][]{{1, 6}, {8, 10}, {15, 18}});
        assert Arrays.deepEquals(mergeWithSort(new int[][]{{1, 4}, {4, 5}}), new int[][]{{1, 5}});
        assert Arrays.deepEquals(mergeWithSort(new int[][]{{1, 4}, {4, 5}, {2, 10}, {11, 15}, {16, 18}, {18, 19}}), new int[][]{{1, 10}, {11, 15}, {16, 19}});
        assert Arrays.deepEquals(mergeWithSort(new int[][]{}), new int[][]{});
    }

    private static int[][] mergeWithSort(int[][] data) {
        Arrays.sort(data, (a, b) -> Integer.compare(a[0], b[0]));
        if (data.length < 2) {
            return data;
        }
        List<int[]> result = new ArrayList<>();
        result.add(data[0]);
        for (int i = 1; i < data.length; i++) {
            if (data[i][0] <= result.get(result.size() - 1)[1]) {
                // already have a larger interval that covers it
                if (result.get(result.size() - 1)[1] > data[i][1]) {
                    continue;
                }
                result.add(new int[]{result.remove(result.size() - 1)[0], data[i][1]});
            } else {
                result.add(data[i]);
            }
        }

        return result.toArray(new int[result.size()][2]);
    }

    private static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[][]{};
        }
        List<int[]> accumulator = new ArrayList<>();
        List<int[]> merge = merge(intervals, 0, accumulator);
        int[][] result = new int[merge.size()][2];
        for (int i = 0; i < merge.size(); i++) {
            result[i] = merge.get(i);
        }
        return result;
    }

    private static List<int[]> merge(int[][] intervals, int index, List<int[]> accumulator) {
        if (index == intervals.length) {
            return accumulator;
        }
        List<int[]> result = new ArrayList<>();


        boolean merged = false;

        for (int[] ints : accumulator) {
            if (ints[1] >= intervals[index][0]) {
                result.add(new int[]{ints[0], intervals[index][1]});
                merged = true;
            } else {
                result.add(ints);
            }
        }
        if (!merged) {
            result.add(intervals[index]);
        }

        return merge(intervals, index + 1, result);
    }
}
