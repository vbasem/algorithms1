package leetcode;

import java.util.ArrayList;
import java.util.List;

public class EnQueens {
    List<String[]> results;

    public static void main(String[] args) {

        EnQueens solver = new EnQueens();

        solver.solve(15);

//        solver.print(result);
    }

    private void print(List<List<String>> result) {
        for (List<String> solution : result) {
            solution.forEach(col -> System.out.print(col + ","));
            System.out.println();
        }
    }

    public void solve(int dimension) {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            result.addAll(dsfSolve(i, 0, dimension, new boolean[dimension][dimension]));
//            results[i] = new String[dimension];
        }
        print(result);
//        boolean[][] blocked = new boolean[dimension][dimension];
//        doSolve(dimension, 0, 0, blocked, new String[dimension]);
    }

    private List<List<String>> dsfSolve(int row, int col, int dimension, boolean[][] blocked) {
        List<List<String>> result = new ArrayList<>();
        if (col + 1 >= dimension) {
            result.add(new ArrayList<>());
            result.get(0).add(makeStringResult(dimension, row));
            return result;
        }
        boolean[][] branchBlocked = createCopy(blocked);
        branchBlocked[row][col] = true;
        for (int i = 0; i < dimension; i++) {
            if (!isBlocked(i, col + 1, branchBlocked)) {
                final int tempIndex = row;
                List<List<String>> subResult = dsfSolve(i, col + 1, dimension, branchBlocked);
                if (subResult.size() > 0) {
                    subResult.forEach(item -> item.add(0, makeStringResult(dimension, tempIndex)));
                    result.addAll(subResult);
                }
            }
        }


        return result;
    }

    public boolean doSolve(int dimension, int r, int c, boolean[][] blocked, String[] temp) {
        //same row placement
        // check if i can place at r and c by checking against blocked
        // if blocked contains r -> cant use r , same for c
        // diagonals are comibnation of  r+x, c+x, r-x, c-x, r+x, c-x, r-x c-x  as l ong as within the boundaries
        // if I can place the item, place it, add blocked coordinate, and pass r-1, c to next call
        // if i cant place it, skip blocked addition step and go to r-1 c

        // next column placement
        // if row placement was sucessfull, check for r=0, c+1 solutions with the blocked r and c

        // no solution found for current row placement
        if (c >= dimension - 1) {
            if (possibleSolution(temp)) {
                results.add(temp);
            }
            return false;
        }

        // we searched all rows for solutions
        if (r >= dimension) {
            return false;
        }

        // check for other solutions

        String[] copy = new String[temp.length];
        System.arraycopy(temp, 0, copy, 0, temp.length);
        String[] copy2 = new String[temp.length];
        System.arraycopy(temp, 0, copy2, 0, temp.length);

        doSolve(dimension, r + 1, c, createCopy(blocked), copy);

        if (!isBlocked(r, c, blocked)) {
            blocked[r][c] = true;

            String spot = makeStringResult(dimension, r);
            copy2[c] = spot;
//            results[r][c] = spot;
            return doSolve(dimension, 0, c + 1, blocked, copy2);

        } else {
            return doSolve(dimension, r + 1, c, blocked, copy2);
        }

    }

    private boolean possibleSolution(String[] temp) {
//        for (String s : temp) {
//            if (s == null) {
//                return false;
//            }
//        }

        return true;
    }

    private boolean[][] createCopy(boolean[][] blocked) {
        boolean[][] copy = new boolean[blocked.length][blocked.length];
        for (int i = 0; i < blocked.length; i++) {
            System.arraycopy(blocked[i], 0, copy[i], 0, blocked.length);
        }

        return copy;
    }


    private String makeStringResult(int dimension, int r) {
        String result = "";
        for (int i = 0; i < dimension; i++) {
            result += i == r ? "Q" : ".";
        }

        return result;
    }

    private boolean isBlocked(int r, int c, boolean[][] blocked) {
        for (int i = 0; i < blocked.length; i++) {
            for (int j = 0; j < blocked.length; j++) {
                if ((i == r || c == j) && blocked[i][j]) {
                    return true;
                }
            }
        }

        for (int i = 1; i < blocked.length; i++) {
            if (isInBoundary(r + i, c + i, blocked.length) && blocked[r + i][c + i]) {
                return true;
            }
            if (isInBoundary(r + i, c - i, blocked.length) && blocked[r + i][c - i]) {
                return true;
            }
            if (isInBoundary(r - i, c + i, blocked.length) && blocked[r - i][c + i]) {
                return true;
            }
            if (isInBoundary(r - i, c - i, blocked.length) && blocked[r - i][c - i]) {
                return true;
            }
        }

        return false;
    }

    private boolean isInBoundary(int r, int c, int dimension) {
        return r >= 0 && c >= 0 && r < dimension && c < dimension;
    }
}
