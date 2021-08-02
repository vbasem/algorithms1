package leetcode;

import java.util.Arrays;

public class NumberOfIslands {

    public static void main(String[] args) {
        NumberOfIslands solver = new NumberOfIslands();

        int[][] grid = new int[4][5];

        grid[0][0] = 1;
        grid[0][1] = 1;
        grid[0][2] = 0;
        grid[0][3] = 0;
        grid[0][4] = 0;

        grid[1][0] = 1;
        grid[1][1] = 1;
        grid[1][2] = 0;
        grid[1][3] = 0;
        grid[1][4] = 0;

        grid[2][0] = 0;
        grid[2][1] = 0;
        grid[2][2] = 1;
        grid[2][3] = 0;
        grid[2][4] = 0;

        grid[3][0] = 0;
        grid[3][1] = 0;
        grid[3][2] = 0;
        grid[3][3] = 1;
        grid[3][4] = 1;
        int result = solver.hasIsland(grid);
        System.out.println("result = " + result);
    }

    public int hasIsland(int[][] grid) {
        int[][] memo = new int[grid.length][grid[0].length];
        int id = 1;
        int islands =0 ;
        for (int i = 0 ; i < grid.length; i++) {
            for (int k= 0; k < grid[i].length; k++)  {
                if (memo[i][k] == 0) { 
                    hasIsland(grid, i, k, memo, islands+1);
                    islands = Math.max(islands, memo[i][k]);
                }
            }
        }

        for (int i = 0; i < memo.length; i++) { 
            System.out.println(Arrays.toString(memo[i]));
        }
        System.out.println("cache = " + cache);
        return islands;
    }

    private boolean hasIsland(int[][] grid, int row, int column, int[][] memo, int id) {
        if (row < 0 || column < 0 || row >= grid.length || column >= grid[row].length ||grid[row][column] == 0) {
            if (row >=0 && column >= 0 && row < grid.length && column < grid[row].length) {
                memo[row][column] = -1;
            }
            return false;
        }
        if (memo[row][column] == 0) {
            memo[row][column] = id;
            hasIsland(grid, row - 1, column, memo, id);
            hasIsland(grid, row + 1, column, memo, id);
            hasIsland(grid, row, column - 1, memo, id);
            hasIsland(grid, row, column + 1, memo, id);
            return true;
        } else {
            cache++;
            return true;
        }
    }
    int cache = 0;
}
