package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsTree {
    List<List<Edge>> graph;

    public BfsTree(int size) {
        this.graph = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            this.graph.add(new ArrayList<>());
        }

    }

    public static void main(String[] args) {

        BfsTree graph = new BfsTree(13);
        graph.add(0, 1);
        graph.add(0, 2);
        graph.add(1, 0);
        // graph.add(1, 2);
        graph.add(1, 3);
        graph.add(2, 0);
        graph.add(2, 1);
        graph.add(2, 4);
        graph.add(3, 1);
        graph.add(3, 4);
        graph.add(4, 2);
        graph.add(4, 3);
        graph.add(4, 5);
        graph.add(5, 4);
        graph.add(5, 6);
        graph.add(6, 5);

        // graph.printDfs();
        System.out.println("found path from 0 to 5 in : " + graph.solveBsf(0, 5));

    }

    private String solveBsf(int start, int goal) {
        Deque<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.size()];
        int[] path = new int[graph.size()];
        Arrays.fill(path, -1);
        Arrays.fill(visited, false);
        queue.add(start);
        bsf(visited, queue, goal, path);
        System.out.println("found path from 0 to 5 in : " + Arrays.toString(path));
        return reconstructPath(path, start, goal);
    }

    private String reconstructPath(int[] path, int start, int goal) {
        int prev = path[goal];

        if (prev == -1) {
            return "no path";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(goal);

        while (prev != -1) {
            builder.append(" -> ");
            builder.append(prev);
            prev = path[prev];
        }

        return builder.toString();
    }

    private int bsf(boolean[] visited, Deque<Integer> queue, int goal, int[] path) {
        if (queue.size() == 0) {
            return Integer.MAX_VALUE;
        } else {
            var element = queue.removeFirst();
            visited[element] = true;
            if (element == goal) {
                return 1;
            }
            graph.get(element).stream().filter(e -> !visited[e.u]).forEach(e -> {
                queue.add(e.u);
                path[e.u] = element;
            });
            return 1 + bsf(visited, queue, goal, path);
        }
    }

    private void add(int i, int j) {
        this.graph.get(i).add(new Edge(j, 0));
    }

    public void printDfs() {
        boolean visited[] = new boolean[graph.size()];
        Arrays.fill(visited, false);
        dfs(0, visited);

    }

    private void dfs(int at, boolean[] visited) {
        visited[at] = true;
        System.out.println("At: " + at);
        for (Edge edge : graph.get(at)) {
            if (!visited[edge.u]) {
                dfs(edge.u, visited);
            }
        }
    }
}

class Edge {
    int u;
    int w;

    public Edge(int u, int w) {
        this.u = u;
        this.w = w;
    }
}
