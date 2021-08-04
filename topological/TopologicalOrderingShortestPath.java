package topological;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class TopologicalOrderingShortestPath {

    public static void main(String[] args) {
        TopoEdge[][] graph = {
            {new TopoEdge(1,1), new TopoEdge(3,2)},
            {new TopoEdge(2,6)},
            {new TopoEdge(5,2), new TopoEdge(4,1)},
            {new TopoEdge(1,4),new TopoEdge(4,3)},
            {new TopoEdge(5, 1)},
            {},
        };

        new TopologicalOrderingShortestPath().printDependecnyGraph(graph);
    }

    public void printDependecnyGraph(TopoEdge[][] graph)  {
        Stack<Integer> topo = new Stack<>();
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < graph.length; i++)  {
            if (!visited.contains(i))  {
                discover(i, graph, visited, topo);
            }
        }

        Map<Integer, int[]> costs = new HashMap<>();

          for (int i = 0 ; i < graph.length; i++) {
            costs.put(i, new int[] {Integer.MAX_VALUE, i});     
        }
        costs.put(topo.peek(), new int[]{topo.peek(), 0});

        for (int i = 0 ; i < graph.length; i++)  {
            int nextVertex = topo.pop();
            for (int k = 0; k < graph[nextVertex].length; k++)  {
                if (costs.get(nextVertex)[0] + graph[nextVertex][k].weight  < costs.get(graph[nextVertex][k].to)[0])  {
                    costs.put(graph[nextVertex][k].to,  new int[]{ costs.get(nextVertex)[0] + graph[nextVertex][k].weight, nextVertex});
                }
            }
        }

        for (Integer item: costs.keySet())  { 
            System.out.println("costs to item = " + item + " : " + costs.get(item)[0] + " coming from: " + costs.get(item)[1]);
        }
        // for (int i = 0 ; i < graph.length; i++) {
            // System.out.print(topo.pop() + " -> ");
        // }
        
    }

    private void discover(int vertex, TopoEdge[][] graph, Set<Integer> visited, Stack<Integer> topo) {
        visited.add(vertex);
        for (int i = 0; i < graph[vertex].length; i++)  {
            if (!visited.contains(graph[vertex][i].to))  {
                discover(graph[vertex][i].to, graph, visited, topo);
            }
        }

        topo.push(vertex);
    }
    
}


class TopoEdge { 

    public TopoEdge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
    int to;
    int weight;
}