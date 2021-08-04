package dfs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;

public class LongestPathTask {

    public static void main(String[] args) {
        LongestPathTask solver = new LongestPathTask();

        // var a = new Task("a", 10);
        // var b = new Task("b", 20);
        // var c = new Task("c", 5);
        // var d = new Task("d", 10);
        // var e = new Task("e", 20);
        // var f = new Task("f", 15);
        // var g = new Task("g", 5);
        // var h = new Task("h", 15);
        // a.addFollowupTask(b);
        // a.addFollowupTask(f);
        // a.addFollowupTask(h);
        // b.addFollowupTask(c);
        // c.addFollowupTask(g);
        // c.addFollowupTask(d);
        // d.addFollowupTask(e);
        // f.addFollowupTask(g);
        // g.addFollowupTask(e);
        // h.addFollowupTask(e);
        // List<Task> tasks = new ArrayList<>() {
        // {
        // add(a);
        // add(b);
        // add(c);
        // add(d);
        // add(e);
        // add(f);
        // add(g);
        // add(h);
        // }
        // };

        var s = new Task("s", 5);
        var a = new Task("a", 15);
        var b = new Task("b", 23);
        var c = new Task("c", 5);
        var d = new Task("d", 6);
        var e = new Task("e", 13);
        s.addFollowupTask(a, 1);
        s.addFollowupTask(c, 2);
        a.addFollowupTask(b, 6);
        b.addFollowupTask(e, 2);
        b.addFollowupTask(d, 1);
        c.addFollowupTask(a, 4);
        c.addFollowupTask(d, 3);
        d.addFollowupTask(e, 1);

        List<Task> tasks = new ArrayList<>() {
            {
                add(s);
                add(a);
                add(b);
                add(c);
                add(d);
                add(e);
            }
        };

        Deque<Task> topology = solver.getTopology(tasks);

        Task t = null;
        while ((t = topology.pollFirst()) != null) {
            System.out.println("task = " + t);
        }

        Map<Task, Task> longestPath = solver.findLongestPath(tasks);

        for (Task item : longestPath.keySet()) {
            System.out.println("Task = " + item + " from " + longestPath.get(item) + " duration:" + item.totalDuration);
        }
    }

    public Map<Task, Task> findLongestPath(List<Task> tasks) {
        Deque<Task> topology = getTopology(tasks);
        Map<Task, Task> path = new HashMap<>();
        Task first = topology.peek();
        first.totalDuration = 0;
        Task t = null;
        while ((t = topology.pollFirst()) != null) {
            for (Entry<Task, Integer> item : t.getFollowingTasks().entrySet()) {
                Task follow = item.getKey();
                if (!path.containsKey(follow) || (-t.totalDuration - item.getValue() < follow.totalDuration)) {
                    follow.totalDuration = t.totalDuration + item.getValue();
                    path.put(follow, t);
                }
            }
        }

        return path;
    }

    private Deque<Task> getTopology(List<Task> tasks) {
        Set<Task> visited = new HashSet<>();
        Deque<Task> order = new LinkedList<>();

        for (Task t : tasks) {
            if (!visited.contains(t)) {
                discoverTopology(t, visited, order);
            }
        }

        return order;
    }

    private void discoverTopology(Task current, Set<Task> visited, Deque<Task> order) {

        visited.add(current);
        for (Task t : current.getFollowingTasks().keySet()) {
            if (!visited.contains(t)) {
                discoverTopology(t, visited, order);
            }
        }
        order.addFirst(current);
    }
}

class Task {
    String name;
    int duration;
    Map<Task, Integer> followingTasks;
    int totalDuration = Integer.MAX_VALUE;
    Task predecessor;

    public Task(String name, int duration) {
        this.followingTasks = new HashMap<>();
        this.name = name;
        this.duration = duration;
    }

    public void addFollowupTask(Task t, int duration) {
        this.followingTasks.put(t, duration);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getName() {
        return this.name;
    }

    public Map<Task, Integer> getFollowingTasks() {
        return this.followingTasks;
    }

    @Override
    public final boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Task))
            return false;
        Task other = (Task) o;

        return this.name.equals(other.name);
    }

    @Override
    public final int hashCode() {
        return name.hashCode();
    }

}
