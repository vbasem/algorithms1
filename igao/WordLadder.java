package igao;

import java.util.*;

public class WordLadder {
    Map<String, List<String>> transformations = new HashMap<>();

    public static void main(String[] args) {
        WordLadder solver = new WordLadder();
        String[] result = solver.solve("hit", "cog",new String[]{"hot", "dog", "dot", "lot", "log", "cog"});
        assert Arrays.deepEquals(result, new String[]{"cog", "dog", "dot", "hot"});
    }

    private String[] solve(String start, String end, String[] words) {
        int length = start.length();

        Arrays.stream(words).forEach(word -> {
            for (int i = 0; i < length; i++) {
                String key = word.substring(0, i) + "?" + word.substring(i + 1);
                transformations.computeIfAbsent(key, s -> new ArrayList<>()).add(word);
            }
        });

        Deque<String> queue = new ArrayDeque<>();
        Map<String, String> visited = new HashMap<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            for (int i = 0; i < length; i++) {
                String key = node.substring(0, i) + "?" + node.substring(i + 1);
                List<String> children = transformations.getOrDefault(key, new ArrayList<>());
                for (String child : children) {
                    if (visited.containsKey(child)) {
                        continue;
                    }
                    visited.put(child, node);
                    if (child.equals(end)) {
                        return constructPath(visited, start, end);
                    } else {
                        queue.add(child);
                    }
                }

            }
        }

        return new String[]{};
    }

    private String[] constructPath(Map<String, String> visited, String start, String end) {
        String current = end;
        List<String>  path = new ArrayList<>();
        while (!current.equals(start)) {
            path.add(current);
            current = visited.get(current);
        }

        return path.toArray(new String[0]);
    }
}
