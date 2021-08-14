package apig;

import java.util.ArrayList;
import java.util.List;

public class ColoringProblem {
    public static void main(String[] args) {
        Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(1, 3);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);
//        g1.color();

        Graph g2 = new Graph(5);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(1, 2);
        g2.addEdge(1, 4);
        g2.addEdge(2, 4);
        g2.addEdge(4, 3);
        g2.color();
    }

    static class Graph {
        List<Integer>[] adjacencyList;

        public Graph(int vertices) {
            this.adjacencyList = new List[vertices];
            for (int i = 0; i < vertices; i++) {
                this.adjacencyList[i] = new ArrayList<>();
            }
        }

        public void addEdge(int from, int to) {
            this.adjacencyList[from].add(to);
        }

        public void color() {
            int[] colors = new int[this.adjacencyList.length];

            for (int i = 0; i < colors.length; i++) {
                if (colors[i] == 0) {
                    colors[i] = 1;
                    color(i, colors);
                }
            }

            printColors(colors);
        }

        private void printColors(int[] colors) {
            for (int i = 0; i < colors.length; i++) {
                System.out.println("""
                        %s ->  %s""".formatted(i, colors[i]));

            }
        }

        private boolean color(int vertex, int[] colors) {
            if (hasColorConflict(vertex, colors)) {
                return false;
            }

            for (int i = 0; i < adjacencyList[vertex].size(); i++) {
                Integer child = adjacencyList[vertex].get(i);
                if (colors[child] == 0) {
                    int color = 1;
                    colors[child] = color;
                    while (!color(child, colors)) {
                        colors[child] = ++color;
                    }
                }
            }

            return true;
        }

        private boolean hasColorConflict(int vertex, int[] colors) {
            for (int i = 0; i < colors.length; i++) {
                if (colors[i] == 0 || i == vertex) {
                    continue;
                }
                for (Integer child : adjacencyList[i]) {
                    if (child == vertex && colors[child] == colors[i]) {
                        return true;
                    }
                }
            }

            for (Integer child : adjacencyList[vertex]) {
                if (colors[child] == colors[vertex]) {
                    return true;
                }
            }

            return false;
        }
    }
}
