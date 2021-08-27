package problem;

public class NumberOfIslands {

    public static void main(String[] args) {
        NumberOfIslands solver = new NumberOfIslands();

        int[][] grid = new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0
                }, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0
        }, {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0
        }, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0
        }, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0
        }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0
        }, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0
        }, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0
        }};
         int[][] grid2 = new int[][] {{0,0,0,0,0,0,0,0}};
        assert solver.countIslands(grid) == 6;
        assert solver.countIslands(grid2) == 0;

    }

    public int countIslands(int[][] grid) {

        int islands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++;
                    discover(grid, i, j);
                }
            }
        }

        return islands;
    }


    private void discover(int[][] grid, int row, int col) {
        grid[row][col] = 2;

        for (int i = - 1; i < 2; i++) {
            for (int j = - 1; j < 2; j++) {
                if (Math.abs(j) == Math.abs(i)) continue;
                if (isViable(grid, row + i, col + j)) {
                    discover(grid, row + i, col +j);
                }
            }
        }
    }

    private boolean isViable(int[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length) return false;
        if (col < 0 || col >= grid[row].length) return false;
        return grid[row][col] == 1;
    }
}
