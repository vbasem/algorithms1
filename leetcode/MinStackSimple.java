package leetcode;

import java.util.ArrayList;
import java.util.List;

public class MinStackSimple {
    List<StackNode> stack;

    public MinStackSimple() {
        this.stack = new ArrayList<>();
    }

    public static void main(String[] args) {
        MinStackSimple stack = new MinStackSimple();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        assert stack.getMin() == -3; // return -3
        stack.pop();
        assert stack.top() == 0;    // return 0
        assert stack.getMin() == -2; // return -2
    }

    public void push(int val) {
        stack.add(makeStackNode(val));
    }

    private StackNode makeStackNode(int val) {
        int min = stack.size() == 0 ? val : Math.min(stack.get(stack.size() - 1).min, val);
        return new StackNode(val, min);
    }

    public void pop() {
        StackNode node = stack.remove(stack.size() - 1);
    }

    public int top() {
        // empty stack explosion
        return stack.get(stack.size() - 1).value;
    }

    public int getMin() {
        return stack.get(stack.size() - 1).min;
    }

    class StackNode {
        int value;
        int min;

        public StackNode(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }
}
