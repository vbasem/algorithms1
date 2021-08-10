package igao;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    Map<Character, Integer> dic = new HashMap<>();

    public static void main(String[] args) {
        MinimumWindowSubstring solver = new MinimumWindowSubstring();
        String input1 = "ADOBECODEBANC";
        String wanted1 = "ABC";
//        assert solver.solve(input1, wanted1).equals("BANC");

        String input2 = "a";
        String wanted2 = "a";
        assert solver.solve(input2, wanted2).equals("a");


        String input3 = "a";
        String wanted3 = "aa";
        assert solver.solve(input3, wanted3).equals("");

    }

    private String solve(String input, String wanted) {

        buildLetterCount(wanted, dic);
        Map<Character, Integer> newCount = new HashMap<>(dic);
        String r = helper(input, newCount, 0, new StringBuilder());
        return r;
    }
    private String helper(String input, Map<Character, Integer> letterCount, int index, StringBuilder result) {
        if (index >= input.length()) {
            if (letterCount.isEmpty()) {
                return result.toString();
            } else {
                result.setLength(0);
                return "";
            }
        }
        char current = input.charAt(index);
        result.append(current);

        if (letterCount.containsKey(current)) {
            if (letterCount.get(current) == 1) {
                letterCount.remove(current);
            } else {
                letterCount.put(current, letterCount.get(current) - 1);
            }
        }
        if (!letterCount.isEmpty()) {
            helper(input, letterCount, index + 1, result);
        }
        Map<Character, Integer> newCount = new HashMap<>(dic);
        String resultRest = helper(input, newCount, index + 1, new StringBuilder());

        if (result.length() > 0 && resultRest.length() > 0) {
            if (result.length() < resultRest.length()) {
                return result.toString();

            } else {
                return resultRest;
            }
        } else if (resultRest.length() > result.length()) {
            return resultRest;
        } else {
            return result.toString();
        }
    }

    private void buildLetterCount(String wanted, Map<Character, Integer> letterCount) {
        for (char c : wanted.toCharArray()) {
            letterCount.put(c, letterCount.getOrDefault(c, 0) + 1);
        }
    }
}
