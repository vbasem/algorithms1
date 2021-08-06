package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    static Map<Character, Character> parentheses = new HashMap<>() {{
        put(')', '(');
        put('}', '{');
        put(']', '[');
    }};

    public static void main(String[] args) {
        assert isValid("()");
        assert isValid("()[]{}");
        assert !isValid("(]");
        assert !isValid("([)]");
        assert isValid("{[]}");
    }

    private static boolean isValid(String input) {
        Stack<Character> stack = new Stack<>();

        for (char c : input.toCharArray()) {
            if (isClosing(c)) {
                if (!hasValidStackEntry(c, stack)) {
                    return false;
                }
            } else {
                stack.push(c);
            }

        }
        // for items in input
        // if is closing
        // pop stack and check if match opening
        // if opening add to stack

        // stack should be empty at the end

        return stack.isEmpty();
    }

    private static boolean hasValidStackEntry(char c, Stack<Character> stack) {
        if (stack.isEmpty()) {
            return false;
        }

        return parentheses.get(c) == stack.pop();
    }

    private static boolean isClosing(char c) {
        return parentheses.containsKey(c);
    }
}
