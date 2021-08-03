package elevenessential;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class MineSweeper {

    public static void main(String[] args) {
        MineSweeper solver = new MineSweeper();
        int[][] mines = {{0,0}, {3,3}};
        int rows = 4;
        int cols = 4;
        int[][] game = solver.mineSweeper(mines, rows, cols);
        
        Arrays.stream(game).forEach(e -> System.out.println(Arrays.toString(e)));
        solver.clickBfs(game, 4, 4, 0, 2);
        Arrays.stream(game).forEach(e -> System.out.println(Arrays.toString(e)));
    }

    public int[][] clickBfs(int[][] game, int rows, int cols, int rc, int cc)  {
        Deque<int[]> queue = new LinkedList<>();
        queue.add(new int[]{rc, cc});

        while (!queue.isEmpty())  {
            int[] current = queue.remove();
            int r = current[0];
            int c = current[1];
            if (r >= rows || r < 0 || c >=cols || c < 0)  {
                continue;
            }
    

            if (game[r][c] == 0)  {
                game[r][c] = -2;
                for (int i = r -1; i < r + 2; i++) {
                    for (int j = c -1; j < c + 2; j++) {
                        queue.add(new int[]{i,j});
                    }
                }
            }
        }

        return game;
    }

    public int[][] click(int[][] game, int rows, int cols, int rc, int cc)  {

        if (rc >= rows || rc < 0 || cc >=cols || cc < 0)  {
            return game;
        }

        if (game[rc][cc] != 0) {
            return game;
        }
        game[rc][cc] = -2;
        int[][] all = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

        for (int i = 0; i < all.length; i++) {
            int r1 = rc + all[i][0];
            int c1 = cc + all[i][1];
            click(game, rows, cols, r1, c1);
        }

        return game;
    }



    public int[][] mineSweeper(int[][] mines, int rows, int cols) {
        if (rows == 0 ||  cols == 0)  {
            return new int[rows][cols];
        }

        int[][] game = new int[rows][cols];

        for (int i = 0; i < mines.length; i++) {

            int row = mines[i][0];
            int col = mines[i][1];
            addMine(game, row, col);

        }
        return game;

    }

    private void addMine(int[][] game, int row, int col) {
        int[][] all = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

        for (int i = 0; i < all.length; i++) {
            int r = row + all[i][0];
            int c = col + all[i][1];
            if (r >= game.length || r < 0 || c < 0 || c >= game[0].length) {
                continue;
            } else {
                game[row][col] = -1;
                if (game[r][c] != -1) {
                    game[r][c]++;
                }
            }
        }
    }

}
