package sandbox;

import java.util.ArrayList;
import java.util.List;

public class StringPermutations2 {
    List<String> permutations;

    public static void main(String[] args) {
        StringPermutations2 solver = new StringPermutations2();
        List<String> abc = solver.permutate("abc");
        abc.forEach(System.out::println);
    }

    public List<String> permutate(String input) {
        permutations = new ArrayList<>(fac(input.length()));
        int[] used = new int[input.length()];

        permutate(input, used, new char[input.length()], 0);


        return permutations;
    }

    private void permutate(String input, int[] used, char[] res, int index) {
        if (index == input.length()) {
            permutations.add(new String(res));
            return;
        }
        for (int i = 0; i < used.length; i++) {
            if (used[i] == 0) {
                used[i] = 1;
                res[index] = input.charAt(i);
                permutate(input, used, res, index + 1);
                used[i] = 0;
            }
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
