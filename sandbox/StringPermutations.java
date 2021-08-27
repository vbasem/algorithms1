package sandbox;

import java.util.*;
import java.util.stream.Collectors;

public class StringPermutations {
    public static void main(String[] args) {
        StringPermutations solver = new StringPermutations();
        List<String> abc = solver.permutate("abc");
        abc.forEach(System.out::println);
    }

    public List<String> permutate(String input) {
        List<String> result = new ArrayList<>(fac(input.length()));

        Map<Character, Integer> letters = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            letters.merge(input.charAt(i), 1, (current, newValue) -> current + 1);
        }


        result.addAll(permutate(letters));
        return result;
    }

    private List<String> permutate(Map<Character, Integer> letters) {
        List<String> result = new ArrayList<>();
        if (letters.size() == 1)  {
            result.add(letters.keySet().stream().findAny().get() + "");
        }
        for (char c: letters.keySet())  {
            HashMap<Character, Integer> sub = new HashMap<>(letters);
            sub.computeIfPresent(c, (key, val) -> val > 1 ? val -1  : null);
            List<String> permutations = permutate(sub);

            result.addAll(permutations.stream().map(s -> c + s).collect(Collectors.toList()));
        }
        return result;
    }

    public int fac(int in) {
        int r = 1;
        for (int i = 2; i <= in; i++) {
            r *= i;
        }

        return r;
    }
}
