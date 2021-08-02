package dfs;

import java.util.Arrays;

public class Dfs {
    Edge[][] graph;

    public Dfs(int size) {
        this.graph = new Edge[size][];
    }

    public static void main(String[] args) {

        Dfs graph = new Dfs(13);
        graph.graph[0] = new Edge[1];
        graph.graph[1] = new Edge[1];
        graph.graph[2] = new Edge[0];
        graph.graph[3] = new Edge[3];
        graph.graph[4] = new Edge[0];
        graph.graph[5] = new Edge[1];
        graph.graph[6] = new Edge[1];
        graph.graph[7] = new Edge[2];
        graph.graph[8] = new Edge[2];
        graph.graph[9] = new Edge[1];
        graph.graph[10] = new Edge[1];
        graph.graph[11] = new Edge[1];
        graph.graph[12] = new Edge[0];
        graph.graph[0][0] = new Edge(9, 0);
        graph.graph[1][0] = new Edge(0, 0);
        graph.graph[3][0] = new Edge(2, 0);
        graph.graph[3][1] = new Edge(4, 0);
        graph.graph[3][2] = new Edge(5, 0);
        graph.graph[5][0] = new Edge(6, 0);
        graph.graph[6][0] = new Edge(7, 0);
        graph.graph[7][1] = new Edge(3, 0);
        graph.graph[7][0] = new Edge(10, 0);
        graph.graph[8][0] = new Edge(1, 0);
        graph.graph[8][1] = new Edge(7, 0);
        graph.graph[9][0] = new Edge(8, 0);
        graph.graph[10][0] = new Edge(11, 0);
        graph.graph[11][0] = new Edge(7, 0);

        graph.printDfs();

    }

    public void printDfs() {
        boolean visited[] = new boolean[graph.length];
        Arrays.fill(visited, false);
        dfs(0, visited);

    }

    private void dfs(int at, boolean[] visited) {
        visited[at] = true;
        System.out.println("At: " + at);
        for (Edge edge : graph[at]) {
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
