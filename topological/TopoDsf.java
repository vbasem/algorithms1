package topological;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class TopoDsf {
    Map<String, List<Edge>> graph;

    public TopoDsf(int size) {
        this.graph = new HashMap<>();
    }

    public static void main(String[] args) {

        TopoDsf graph = new TopoDsf(8);
        // graph.add("A", "D");
        // graph.add("J", "M");
        // graph.add("J", "L");
        // graph.add("K", "J");
        // graph.add("F", "K");
        // graph.add("F", "J");
        // graph.add("E", "F");
        // graph.add("E", "D");
        // graph.add("E", "A");

        // graph.add("D", "H");
        // graph.add("D", "G");
        // graph.add("C", "B");
        // graph.add("C", "A");
        // graph.add("B", "D");
        // graph.add("G", "I");
        // graph.add("I", "L");
        // graph.add("H", "I");

        graph.add("A", "B", 3);
        graph.add("A", "C", 6);
        graph.add("B", "C", 4);
        graph.add("B", "D", 4);
        graph.add("B", "E", 11);
        graph.add("C", "D", 8);
        graph.add("C", "G", 11);
        graph.add("D", "E", -4);
        graph.add("D", "F", 5);
        graph.add("D", "G", 2);
        graph.add("E", "H", 9);
        graph.add("F", "H", 1);
        graph.add("G", "H", 2);

        graph.printDfs();
        graph.printTopo();
        graph.printDijkstra("A","H");
        System.out.println("found path from 0 to 5 in : " + graph.solveBsf("A", "L"));

    }

    private void printDijkstra(String start, String end) {
        Set<String> visited = new HashSet<>();
        Map<String, Integer> distances = new HashMap<>();
        Queue<String> pq = new PriorityQueue<>((a, b) -> distances.get(a).compareTo(distances.get(b)));
        Map<String, String> path = new HashMap<>();
        path.put(start, null);
        distances.put(start, 0);
        pq.add(start);
        
        while (pq.size() > 0) {
            String next = pq.poll();
            if (next == null) { 
                System.out.println("Distance to " + end + " was :" + null);
                return;

            }
            else {
                visited.add(next);
                for (Edge e: graph.getOrDefault(next, new ArrayList<>()))  {
                    if (distances.get(e.u) == null)  {
                        path.put(e.u, next);
                        distances.put(e.u, e.w + distances.get(next));
                    } else {
                        if ((distances.get(next) + e.w) < distances.get(e.u)) {
                            path.put(e.u, next);
                            distances.put(e.u, distances.get(next) + e.w);
                        }
                    }
                    if (!visited.contains(e.u))  {
                        pq.add(e.u);
                    }
                }
            }
        }

            System.out.println("Distance to " + end + " was :" + distances.get(end) + "; path: " +  reconstructPath(path, start, end));
           

    }

    private String getNextCandidate(Map<String, Integer> distances, Set<String> visited) {
        String nextMin = null;
        int shortest = Integer.MAX_VALUE;

        for (String key: distances.keySet())  {
            if (!visited.contains(key)) {
                if (distances.get(key) < shortest) { 
                    shortest = distances.get(key);
                    nextMin = key;
                }
            }
        }

        return nextMin;
    }

    private void printTopo() {
        Deque<String> stack = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, Integer> costs = new HashMap<>();

        String result = "";
        String[] keys = Arrays.stream(graph.keySet().toArray()).toArray(String[]::new);



        for (String node : keys) {
            if (!visited.contains(node)) {
                result = topo(node, visited) +result;; 
            }
        }

        for (char key : result.toCharArray()) {
            costs.put(key + "", Integer.MAX_VALUE);
        }
        costs.put("A", 0);

        for (char key : result.toCharArray()) {
            for (Edge e: graph.getOrDefault(key+ "", new ArrayList<>()))  {
                if (costs.get(e.u + "") > (costs.get(key + "") + e.w)) {
                    costs.put(e.u, costs.get(key + "") + e.w);
                }
            }
        }



        costs.entrySet().forEach(e -> { 
            System.out.println(e.getKey() + " -> " + e.getValue());
        });
        
        System.out.println("Result " + result);

    }

    private String topo(String node, Set<String> visited) {
        String result = "";

        if (!visited.contains(node)) {
            
            for (Edge c : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(c.u)) {
                    result = topo(c.u, visited) + result;
                }
            }

            visited.add(node);
        }

  
        return  node + result;
    }

    private String solveBsf(String start, String goal) {
        Deque<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> path = new HashMap<>();
        queue.add(start);
        bsf(visited, queue, goal, path);
        return reconstructPath(path, start, goal);
    }

    private String reconstructPath(Map<String, String> path, String start, String goal) {
        String prev = path.get(goal);

        if (prev == null) {
            return "no path";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(goal);

        while (prev != null) {
            builder.append(" -> ");
            builder.append(prev);
            prev = path.get(prev);
        }

        return builder.toString();
    }

    private int bsf(Set<String> visited, Deque<String> queue, String goal, Map<String, String> path) {
        if (queue.size() == 0) {
            return Integer.MAX_VALUE;
        } else {
            var element = queue.removeFirst();
            visited.add(element);
            if (element.equals(goal)) {
                return 1;
            }
            graph.getOrDefault(element, new ArrayList<>()).stream().filter(e -> !visited.contains(e.u)).forEach(e -> {
                queue.add(e.u);
                path.put(e.u, element);
            });
            return 1 + bsf(visited, queue, goal, path);
        }
    }

    private void add(String i, String j) {
        if (!this.graph.containsKey(i)) {
            this.graph.put(i, new ArrayList<>());
        }
        this.graph.get(i).add(new Edge(j, 0));
    }

    private void add(String i, String j, int weight) {
        if (!this.graph.containsKey(i)) {
            this.graph.put(i, new ArrayList<>());
        }
        this.graph.get(i).add(new Edge(j, weight));
    }

    public void printDfs() {
        Set<String> visited = new HashSet<>();
        dfs("J", visited);

    }

    private void dfs(String at, Set<String> visited) {
        visited.add(at);
        System.out.println("At: " + at);
        for (Edge edge : this.graph.getOrDefault(at, new ArrayList<>())) {
            if (edge != null && !visited.contains(edge.u)) {
                dfs(edge.u, visited);
            }
        }
    }
}

class Edge {
    String u;
    int w;

    public Edge(String u, int w) {
        this.u = u;
        this.w = w;
    }
}
