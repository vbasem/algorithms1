package apig;

import java.util.Arrays;

public class KnightsTour {

    private final Integer[][] board;
    private int nextMove;

    public KnightsTour(int size) {
        this.board = new Integer[size][size];
        this.nextMove = 0;
    }

    public static void main(String[] args) {
        KnightsTour solver = new KnightsTour(5);

        solver.tour(2, 2);
        solver.print();
        System.out.println();
        KnightsTour solver2 = new KnightsTour(5);
        solver2.closedTour(2, 2);
        solver2.print();


    }

    private void tour(int row, int col) {
        tour(row, col, (steps, pBoard) -> steps == (pBoard.length * pBoard.length));
    }

    private void closedTour(int row, int col) {
        tour(row, col, (steps, pBoard) -> {
            if (steps == (pBoard.length * pBoard.length)) {
                int[] start = new int[2];
                int[] end = new int[2];
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board.length; j++) {
                        if (board[i][j] == 0) {
                            start[0] = i;
                            start[1] = j;
                        }
                        if (board[i][j] == steps - 1) {
                            end[0] = i;
                            end[1] = j;
                        }
                    }
                }
                int startDifference = Math.abs(start[0] - end[0]);
                int endDifference = Math.abs(start[1] - end[1]);
                if (startDifference == 1 && endDifference == 2) {
                    return true;
                }
                if (startDifference == 2 && endDifference == 1) {
                    return true;
                }

            }

            return false;
        });

    }

    private void print() {
        if (nextMove <= 0) {
            System.out.println("No Solution");
            return;
        }
        for (Integer[] item : board) {
            System.out.println(Arrays.toString(item));
        }
    }

    private boolean tour(int row, int col, Checker checker) {
        board[row][col] = nextMove++;
        if (checker.isValid(nextMove, board)) {
            return true;
        }


        for (int i = -2; i <= 2; i++) {
            if (i == 0) {
                continue;
            }

            for (int j = -2; j <= 2; j++) {
                if (j == 0 || Math.abs(j) == Math.abs(i)) {
                    continue;
                }
                if (isAccessible(row + i, col + j)) {
                    if (tour(row + i, col + j, checker)) {
                        return true;
                    }
                }

            }
        }
        nextMove--;
        board[row][col] = null;
        return false;
    }

    private boolean isAccessible(int row, int col) {
        if (row < 0 || row >= board.length) {
            return false;
        }
        if (col < 0 || col >= board.length) {
            return false;
        }
        return board[row][col] == null;
    }


    @FunctionalInterface
    interface Checker {
        boolean isValid(int moves, Integer[][] board);
    }
}
