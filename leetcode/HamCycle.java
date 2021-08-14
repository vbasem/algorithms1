package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class HamCycle {

    Deque<Integer> stack = new ArrayDeque<>();

    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
        };

        HamCycle solver = new HamCycle();
        solver.detectHamCycle(matrix);
//        solver.topSort(matrix);
    }

    private void topSort(int[][] matrix) {
        int[] path = new int[matrix.length];
        Arrays.fill(path, -1);

        for (int i = 0; i < matrix.length; i++) {
            if (path[i] == -1) {
                topSortHelper(matrix, path, i);
            }
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    private void topSortHelper(int[][] matrix, int[] path, int vertex) {
        path[vertex] = 1;
        for (int v : matrix[vertex]) {
            if (path[v] == -1) {
                path[v] = vertex;
                topSortHelper(matrix, path, v);
            }
        }

        stack.push(vertex);
    }

    public void detectHamCycle(int[][] matrix) {
        int[] path = new int[matrix.length];
        path[0] = 0;
        if (dsf(matrix, path, 1)) {
            System.out.println("found a path");
            for (int i = 0; i < path.length; i++) {
                System.out.println(i + " -> " + path[i]);
            }
            System.out.println(path[path.length - 1] + " -> " + path[0]);
        } else {
            System.out.println("no path found");
        }
    }

    private boolean dsf(int[][] matrix, int[] path, int pos) {

        if (pos == (matrix.length) && matrix[path[pos-1]][path[0]] == 1) {
            return true;
        }

        for (int v = 1; v < matrix.length; v++) {

            boolean bad = false;
            for (int j = 0; j < pos; j++) {
                if (path[j] == v) {
                    bad = true;
                    break;
                }
            }
            if (!bad && matrix[path[pos - 1]][v] != 0) {
                path[pos] = v;
                if (dsf(matrix, path, pos + 1)) {
                    return true;
                } else {
                    path[pos] = 0;
                }
            }
        }

        return false;
    }
}
