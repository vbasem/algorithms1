package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordBreak {

    Map<String, String> memo = new HashMap<>();
    int cacheMiss = 0;

    public static void main(String[] args) {
        WordBreak solver = new WordBreak();
        String input = "wordbreakproblem1";
        Set<String> dict = new HashSet<>() {{
            add("this");
            add("th");
            add("is");
            add("famous");
            add("word");
            add("break");
            add("b");
            add("r");
            add("e");
            add("a");
            add("k");
            add("br");
            add("bre");
            add("brea");
            add("ak");
            add("problem");
        }};

        String result = solver.solve(input, dict, "");
        boolean dSolution = solver.solve(input, dict);
        System.out.println(dSolution);

        System.out.println(solver.cacheMiss);

    }

    private boolean solve(String input, Set<String> dict) {
        boolean[] dynamic = new boolean[input.length() + 1];
        dynamic[0] = true;
        int count = 0;

        for (int i = 1; i <= input.length(); i++) {
            for (int dI = 0; dI < i; dI++) {
                count++;
                if (dynamic[dI] && dict.contains(input.substring(dI, i))) {
                    dynamic[i] = true;
                    break;
                }
            }
        }

        System.out.println("count = " + count);
        return dynamic[dynamic.length - 1];
    }

    private String solve(String input, Set<String> dict, String result) {
        if (input.isEmpty()) {
            System.out.println(result);
            return result;
        }


        cacheMiss++;
        String sub = "";
        for (int i = 0; i <= input.length(); i++) {
            String word = input.substring(0, i);
            if (dict.contains(word)) {
                sub = solve(input.substring(i), dict, result + " " + word);
                memo.put(input, sub);
            }
        }

        return sub;
    }
}
