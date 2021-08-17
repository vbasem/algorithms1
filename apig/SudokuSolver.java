package apig;

public class SudokuSolver {

    public static void main(String[] args) {
        SudokuSolver solver = new SudokuSolver();
        int[][] puzzle = {
                {9, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 7},
                {5, 0, 0, 0, 0, 3, 0, 0, 4},
                {0, 0, 7, 0, 0, 0, 2, 0, 0},
                {0, 0, 3, 6, 0, 8, 0, 0, 0},
                {0, 0, 0, 4, 0, 0, 6, 1, 0},
                {0, 8, 5, 0, 4, 0, 0, 0, 0},
                {0, 0, 0, 3, 2, 0, 0, 6, 0},
                {0, 4, 0, 0, 1, 0, 0, 9, 0},
        };
        solver.solve(puzzle);
    }

    private void solve(int[][] puzzle) {

        if (solve(puzzle, 0, 0)) {
            print(puzzle);

        } else {
            System.out.println("no solution");
        }
    }

    private boolean solve(int[][] puzzle, int row, int col) {
        if (row >= puzzle.length) {
            return true;
        }

        if (puzzle[row][col] != 0) {
            return solve(puzzle, getNextRow(row, col), getNextCol(col));
        }

        for (int i = 1; i < 10; i++) {
            if (isValidChoice(puzzle, row, col, i)) {
                puzzle[row][col] = i;
                if (solve(puzzle, getNextRow(row, col), getNextCol(col))) {
                    return true;
                } else {
                    puzzle[row][col] = 0;
                }
            }
        }

        return false;
    }

    private boolean isValidChoice(int[][] puzzle, int row, int col, int num) {
        return !(conflictsInRowCol(puzzle, row, col, num)) && !conflictsInBox(puzzle, row, col, num);
    }

    private boolean conflictsInRowCol(int[][] puzzle, int row, int col, int num) {
        for (int i = 0; i < puzzle.length; i++) {
            if (puzzle[row][i] == num || puzzle[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean conflictsInBox(int[][] puzzle, int row, int col, int num) {
        int rOff = (row / 3) * 3;
        int cOff = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i + rOff][j + cOff] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getNextCol(int col) {
        return col == 8 ? 0 : col + 1;
    }

    private int getNextRow(int row, int col) {
        return col == 8 ? row + 1 : row;
    }

    private void print(int[][] puzzle) {
        int r = 0;
        for (int[] row : puzzle) {
            int c = 0;
            if (r++ % 3 == 0) {
                System.out.println();
            }
            for (int n : row) {
                if (c++ % 3 == 0) {
                    System.out.print("  ");
                }
                System.out.print(n+ " ");

            }
            System.out.println();
        }
    }

}
