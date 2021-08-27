package apig;

import java.util.Arrays;

public class RodCutting {

    public static void main(String[] args) {
        RodCutting solver = new RodCutting();
        assert Arrays.equals(solver.cut(new int[]{1,5,6,3}, 5), new int[]{1,2,0,0});
        assert Arrays.equals(solver.cut(new int[]{2,5,7,3,9}, 5), new int[]{1,2,0,0,0});
        assert Arrays.equals(solver.cut(new int[]{2,5,7,3,22}, 5), new int[]{0,0,0,0,1});
    }

    public int[] cut(int[] cutValues, int rodLength) {

        int[][] dp = new int[cutValues.length +1][rodLength + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= rodLength; j++) {
                int fits = j / i;
                int rest = j % i;
                dp[i][j] = Math.max(cutValues[i -1] * fits + dp[i - 1][rest], dp[i-1][j]);
            }

        }

        return buildCutResult2(dp, cutValues);
    }

    public int[] buildCutResult(int[][] dp, int[] cutValues) {
        int[] result = new int[cutValues.length ];
        int value = dp[dp.length - 1][dp[0].length -1];
        for (int i = dp.length - 1, v = dp[0].length - 1; value > 0; i--) {
            if (dp[i][v] == dp[i - 1][v]) {
                result[i -1] = 0;
                continue;
            } else {
                result[i -1] = v / i;
                value = dp[i][v] % cutValues[i-1];
                v = v % i;
            }

        }
        return result;

    }

    public int[] buildCutResult2(int[][] dp, int[] cutValues) {
        int[] result = new int[cutValues.length ];
        int row = dp.length - 1;
        int col =  dp[0].length - 1;
        while (row > 0 && col > 0)  {
            if (dp[row][col] == dp[row - 1][col]) {
                row--;
            } else {
                result[row -1]++;
                col = col - row;
            }
        }
        return result;

    }
}