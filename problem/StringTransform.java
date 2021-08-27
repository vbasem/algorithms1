package problem;

import java.util.HashMap;
import java.util.Map;

public class StringTransform {
    public static void main(String[] args) {
        StringTransform solver = new StringTransform();
        assert solver.canTransform("aabcc".toCharArray(), "ccdee".toCharArray());
        assert !solver.canTransform("boongogo".toCharArray(), "gaasjoja".toCharArray());
        assert solver.canTransform("".toCharArray(), "".toCharArray());
    }

    public boolean canTransform(char[] original, char[] transformed) {
        if (original == null && transformed == null) {
            return true;
        }
        if (original.length == 0 && transformed.length == 0 ) {
            return true;
        }

        if (original.length != transformed.length)  {
            return false;
        }

        int i = 0;
        Map<Character, Character> transformations = new HashMap<>();
        while ( i < original.length) {
            if (transformations.containsKey(original[i]) && transformations.get(original[i]) != transformed[i])  {
                return false;
            }
            transformations.putIfAbsent(original[i], transformed[i]);
            i++;
        }

        return true;
    }
}
