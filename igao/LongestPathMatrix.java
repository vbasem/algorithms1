package igao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestPathMatrix {

    public static void main(String[] args) {
        LongestPathMatrix solver = new LongestPathMatrix();
        int[][] matrix = {{1, 2, 3, 4},
                {2, 2, 3, 4},
                {3, 2, 3, 4},
                {4, 5, 6, 7}};

//        int[] result = solver.solve(matrix);
//        System.out.println(Arrays.toString(result));
//        assert Arrays.equals(result, new int[]{1, 2, 3, 4, 5, 6, 7});

        int[][] m2 = {{1, 2},
                {3, 4}};

        int[] r2 = solver.solveDp(m2);
        System.out.println(Arrays.toString(r2));
        assert Arrays.equals(r2, new int[]{1, 2, 4});
    }

    private int[] solveRecursive(int[][] matrix) {

        return helper(matrix, 0, 0).stream().mapToInt(Integer::intValue).toArray();
    }

    private List<Integer> helper(int[][] matrix, int x, int y) {
        if (x >= matrix.length || y >= matrix[x].length) {
            return new ArrayList<>();
        } else {
            List<Integer> bot = new ArrayList<>();
            List<Integer> right = new ArrayList<>();
            List<Integer> result;
            if (x + 1 < matrix.length && matrix[x][y] < matrix[x + 1][y]) {
                bot = helper(matrix, x + 1, y);
            }
            if (y + 1 < matrix[x].length && matrix[x][y] < matrix[x][y + 1]) {
                right = helper(matrix, x, y + 1);
            }

            if (bot.size() > right.size()) {
                result = bot;
            } else {
                result = right;
            }
            result.add(matrix[x][y]);
            return result;

        }
    }

    private int[] solveDp(int[][] matrix) {

        Path[][] dp = new Path[matrix.length][matrix[0].length];
        int[] largestIndex = new int[]{0, 0};
        dp[0][0] = new Path(1);
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                Path top = getTop(dp, i, j);
                Path left = getLeft(dp, i, j);
                dp[i][j] = new Path(top, left, i, j);
                if (dp[largestIndex[0]][largestIndex[1]].value <= dp[i][j].value) {
                    largestIndex = new int[]{i, j};
                }
            }
        }
        return reconstructDpPath(matrix, dp, largestIndex);
    }

    private int[] reconstructDpPath(int[][] matrix, Path[][] dp, int[] largestIndex) {
        List<Integer> path = new ArrayList<>();
        Path current = dp[largestIndex[0]][largestIndex[1]];
        path.add(matrix[largestIndex[0]][largestIndex[1]]);
        while (current.from[0] != 0 || current.from[1] != 0) {
            path.add(matrix[current.from[0]][current.from[1]]);
            current = dp[current.from[0]][current.from[1]];
        }
        path.add(matrix[current.from[0]][current.from[1]]);
        return path.stream().mapToInt(Integer::intValue).toArray();
    }

    private Path getTop(Path[][] dp, int i, int j) {
        if (i - 1 < 0) {
            return new Path(0);
        } else {
            return new Path(dp[i - 1][j].value, new int[]{i - 1, j});
        }
    }

    private Path getLeft(Path[][] dp, int i, int j) {
        if (j - 1 < 0) {
            return new Path(0);
        } else {
            return new Path(dp[i][j - 1].value, new int[]{i, j - 1});
        }
    }


    static class Path {
        int value;
        int[] from;

        public Path(int i) {
            this.value = i;
        }

        public Path(int value, int[] from) {
            this.value = value;
            this.from = from;
        }

        public Path(Path top, Path left, int i, int j) {
            if (top.value == 0 && left.value == 0) {
                this.value = 0;
            } else {
                if (top.value > left.value) {
                    this.value = top.value + 1;
                    this.from = new int[]{i - 1, j};
                } else {
                    this.value = left.value + 1;
                    this.from = new int[]{i, j - 1};
                }
            }
        }
    }
}
