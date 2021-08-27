package problem;

import java.util.Arrays;
import java.util.TreeMap;

public class MostSimilarPath {

    int[][] dp;
    int[][] trace;

    public static void main(String[] args) {

        String[] f1 = new MostSimilarPath().findPath(5, new int[][]{{0, 2}, {0, 3}, {1, 2}, {1, 3}, {1, 4}, {2, 4}}, new String[]{"ATL", "PEK", "LAX", "DXB", "HND"}, new String[]{"ATL", "DXB", "HND", "LAX"});
        System.out.println(Arrays.toString(f1));

        String[] f2 = new MostSimilarPath().findPath(6, new int[][]{{0,1},{1,2},{2,3},{3,4},{4,5}}, new String[]{"ATL","PEK","LAX","ATL","DXB","HND"}, new String[]{"ATL","DXB","HND","DXB","ATL","LAX","PEK"});
        System.out.println(Arrays.toString(f2));

        String[] f3 = new MostSimilarPath().findPath(4, new int[][]{{1,0},{2,0},{3,0},{2,1},{3,1},{3,2}}, new String[]{"ATL","PEK","LAX","DXB"}, new String[]{"ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX"});
        System.out.println(Arrays.toString(f3));
    }

    public String[] findPath(int cities, int[][] roads, String[] names, String[] targetPath) {
        dp = new int[cities][targetPath.length];
        trace = new int[targetPath.length][cities];
        for (int i = 0; i < cities; i++) {
            dp[i] = new int[targetPath.length];
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < cities; i++) {
            dsf(cities, roads, names, targetPath, i, 0);
        }

        return buildPath(names);
    }

    private String[] buildPath(String[] names) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        String[] result = new String[trace.length];
        for (int i = 0; i < dp.length; i++) {
            if (dp[i][0] < min) {
                min = dp[i][0];
                minIndex = i;
            }
        }

        for (int i = 0; i < trace.length; i++) {
            result[i] = names[minIndex];
            minIndex = trace[i][minIndex];
        }

        return result;
    }

    public int dsf(int cities, int[][] roads, String[] names, String[] targetPath, int current, int pathCounter) {
        if (pathCounter >= targetPath.length) {
            return 0;
        }
        int min = Integer.MAX_VALUE;

        if (dp[current][pathCounter] != -1) {
            return dp[current][pathCounter];
        }


        for (int[] road : roads) {
            if (road[0] == current) {
                int m = dsf(cities, roads, names, targetPath, road[1], pathCounter + 1);
                if (m < min) {
                    trace[pathCounter][current] = road[1];
                    min = m;
                }
            }
            if (road[1] == current) {
                int m = dsf(cities, roads, names, targetPath, road[0], pathCounter + 1);
                if (m < min) {
                    trace[pathCounter][current] = road[0];
                    min = m;
                }
            }
        }


        dp[current][pathCounter] = min + (targetPath[pathCounter].equals(names[current]) ? 0 : 1);
        return dp[current][pathCounter];

    }
}