package elevenessential;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class StringNonRepeatable {
    public static void main(String[] args) {
        String input = "aabbdbc";
        
        StringNonRepeatable solver = new StringNonRepeatable();
        System.out.println("solver.findNonRepeating(input) = " + solver.findNonRepeating(input));
    }

    public Character findNonRepeating(String input) {

        Map<Character, Integer> memory = new LinkedHashMap<>();

        for (int i = 0; i < input.length(); i++) {
            memory.put(input.charAt(i), memory.getOrDefault(input.charAt(i), 0) + 1);
        }

        for (Entry<Character, Integer> e : memory.entrySet()) {
            if (e.getValue() == 1) {

                return e.getKey();
            }
        }
        return null;
    }

}
