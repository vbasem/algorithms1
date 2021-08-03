package dfs;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.util.Stack;


public class Maze {

    public static void main(String[] args) {
        Maze solver = new Maze();
        int[][] maze = new int[5][5];
        maze[1][0] = -1;
        maze[1][1] = -1;
        maze[1][3] = -1;
        maze[2][3] = -1;
        maze[3][0] = -1;
        maze[3][1] = -1;
        maze[3][2] = -1;
        maze[3][3] = -1;
        maze[4][3] = -1;
        int[] start = { 0, 0 };
        int[] end = { 4, 4 };
        List<int[]> solution = solver.solve(maze, start, end);

        solution.forEach(e -> System.out.print(Arrays.toString(e) + " -> "));

    }

    public List<int[]> solve(int[][] matrix, int[] start, int[] end) {
        Stack<int[]> stack = new Stack<>();
        Map<String, int[]> path = new HashMap<>();
        stack.add(start);
        path.put(makeKey(start), null);
        while (!stack.isEmpty()) {
            int[] current = stack.pop();

            if (current[0] == end[0] && current[1] == end[1]) {
                return reconstructPath(current, path);
            } else {
                addNeighboursToStack(matrix, current, stack, path);
            }
        }

        return new ArrayList<>();
    }

    private void addNeighboursToStack(int[][] matrix, int[] current, Stack<int[]> stack, Map<String, int[]> path) {
        for (int i = current[0] - 1; i < current[0] + 2; i++) {
            for (int k = current[1] - 1; k < current[1] + 2; k++) {
                // skip diagonal
                if (i != current[0] && k != current[1]) {
                    continue;
                }
                // boundary check
                if (i >= matrix.length || i < 0 || k >= matrix[0].length || k < 0) {
                    continue;
                }
                // blocked path
                if (matrix[i][k] == -1) {
                    continue;
                }

                String key = makeKey(new int[] { i, k });
                if (!path.containsKey(key)) {
                    path.put(key, current);
                    stack.add(new int[] { i, k });
                }

            }
        }

    }

    private List<int[]> reconstructPath(int[] current, Map<String, int[]> path) {
        LinkedList<int[]> travelPath = new LinkedList<>();
        int[] node = current;

        while (node != null) {
            travelPath.addLast(node);
            node = path.get(makeKey(node));
        }

        return travelPath;
    }

    private String makeKey(int[] node) {
        return node[0] + ":" + node[1];
    }

}
