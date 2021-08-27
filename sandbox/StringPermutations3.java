package sandbox;

import java.util.ArrayList;
import java.util.List;

public class StringPermutations3 {
    List<String> permutations;

    public static void main(String[] args) {
        StringPermutations3 solver = new StringPermutations3();
        List<String> abc = solver.permutate("abc");
        abc.forEach(System.out::println);
    }

    public List<String> permutate(String input) {
        permutations = new ArrayList<>(fac(input.length()));

        permutate(input.toCharArray(), 0, input.length() - 1);


        return permutations;
    }

    private void permutate(char[] input , int low, int high) {
        if (low == high) {
            permutations.add(new String(input));
            return;
        }
        for (int i = low; i <= high; i++) {
            char temp = input[i];
            input[i] = input[low];
            input[low] = temp;
            permutate(input, low + 1, high);
            input[low] = input[i];
            input[i] = temp;
        }
    }

    public int fac(int in) {
        int r = 1;
        for (int i = 2; i <= in; i++) {
            r *= i;
        }

        return r;
    }
}
