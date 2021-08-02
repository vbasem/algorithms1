package connected;

import java.util.Arrays;

public class Coloring {
    Edge[][] graph;

    public Coloring(int size) {
        this.graph = new Edge[size][];
    }

    public static void main(String[] args) {

        Coloring graph = new Coloring(18);
        graph.graph[0] = new Edge[1];
        graph.graph[0][0] = new Edge(8, 0);

        graph.graph[1] = new Edge[1];
        graph.graph[1][0] = new Edge(5, 0);
        
        graph.graph[2] = new Edge[1];
        graph.graph[2][0] = new Edge(9, 0);

        graph.graph[3] = new Edge[1];
        graph.graph[3][0] = new Edge(9, 0);

        graph.graph[4] = new Edge[1];
        graph.graph[4][0] = new Edge(0, 0);

        graph.graph[5] = new Edge[2];
        graph.graph[5][0] = new Edge(16, 0);
        graph.graph[5][1] = new Edge(17, 0);

        graph.graph[6] = new Edge[1];
        graph.graph[6][0] = new Edge(11, 0);

        graph.graph[7] = new Edge[1];
        graph.graph[7][0] = new Edge(6, 0);


        graph.graph[8] = new Edge[2];
        graph.graph[8][0] = new Edge(4, 0);
        graph.graph[8][1] = new Edge(14, 0);

        graph.graph[9] = new Edge[1];
        graph.graph[9][0] = new Edge(15, 0);

        graph.graph[10] = new Edge[0];
        

        graph.graph[11] = new Edge[1];
        graph.graph[11][0] = new Edge(7, 0);

        graph.graph[12] = new Edge[0];
        

        graph.graph[13] = new Edge[1];
        graph.graph[13][0] = new Edge(0, 0);

        graph.graph[14] = new Edge[2];
        graph.graph[14][0] = new Edge(0, 0);
        graph.graph[14][1] = new Edge(13, 0);

        graph.graph[15] = new Edge[2];
        graph.graph[15][0] = new Edge(10, 0);
        graph.graph[15][1] = new Edge(2, 0);
        

        graph.graph[16] = new Edge[0];

        graph.graph[17] = new Edge[0];

        graph.printDfs();
        graph.color();

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

    public void color()  {
        int visited[] = new int[graph.length];
        Arrays.fill(visited, -1);
        color(0, visited);
        System.out.println(Arrays.toString(visited));
       
    }

    private void color(int index, int[] visited) { 
        if (index == visited.length)  {
            return;
        }
        if (visited[index] == -1) {
            colorForIndex(index, visited, index);
        }
         
        color(index + 1, visited);
    }

    private void colorForIndex(int index, int[] visited, int color) {
        visited[index] = color;
        System.out.println("Color " + color + " for " + index);

        for (Edge e: graph[index])  {
            if (visited[e.u] == -1) {
                colorForIndex(e.u, visited, color);
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
