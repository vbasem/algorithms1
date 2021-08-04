package topological;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TopologicalOrdering {

    public static void main(String[] args) {
        int[][] graph = {
            {},
            {},
            {3},
            {1},
            {0,1},
            {0,2},

        };

        new TopologicalOrdering().printDependecnyGraph(graph);
    }

    public void printDependecnyGraph(int[][] graph)  {
        Stack<Integer> topo = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < graph.length; i++)  {
            if (!visited.contains(i))  {
                discover(i, graph, visited, topo);
            }
        }

        for (int i = 0 ; i < graph.length; i++) {
            System.out.print(topo.pop() + " -> ");
        }
        
    }

    private void discover(int vertex, int[][] graph, Set<Integer> visited, Stack<Integer> topo) {
        visited.add(vertex);
        for (int i = 0; i < graph[vertex].length; i++)  {
            if (!visited.contains(graph[vertex][i]))  {
                discover(graph[vertex][i], graph, visited, topo);
            }
        }

        topo.push(vertex);
    }
    
}
