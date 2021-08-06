package leetcode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinStackNaive {

    public static void main(String[] args) {
        MinStackNaive stack = new MinStackNaive();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        assert stack.getMin() == -3; // return -3
        stack.pop();
        assert stack.top() == 0;    // return 0
        assert stack.getMin() == -2; // return -2
    }
    List<Integer> stack;
    List<Integer> minStack;
    Map<Integer, Integer> memory;

    public MinStackNaive() {
        stack = new ArrayList<>();
        minStack = new ArrayList<>();
        memory = new HashMap<>();

    }

    public void push(int val) {
        stack.add(val);
        pushMemory(val);
        pushMin(val);
    }

    private void pushMin(int val) {
        int i = minStack.size() - 1;
        minStack.add(val);
        while (i >= 0 && minStack.get(i) < val) {
            minStack.set(i + 1, minStack.get(i));
            minStack.set(i, val);
            i--;
        }
    }

    private void pushMemory(int val) {
        memory.put(val, memory.getOrDefault(val, 0) + 1);
    }

    public void pop() {
        Integer remove = stack.remove(stack.size() - 1);
        removeMemory(remove);
        removeMin(remove);
    }

    private void removeMin(Integer remove) {
        if (minStack.get(minStack.size() - 1) == remove) {
            minStack.remove(minStack.size() - 1);
        }
    }

    private void removeMemory(Integer remove) {
        memory.put(remove, memory.getOrDefault(remove, 1) - 1);
    }

    public int top() {
        return stack.get(stack.size() - 1);
    }

    public int getMin() {
        int min = minStack.get(minStack.size() - 1);
        while (memory.get(min) < 1) {
            minStack.remove(minStack.size() - 1);
            min = minStack.get(minStack.size() - 1);
        }

        return min;
    }
}
