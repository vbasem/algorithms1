package leetcode;

public class CharacterRemover {
    public static void main(String[] args) {
        CharacterRemover solver = new CharacterRemover();
        assert solver.removeAllChars("hello oh little one", 'o').equals("hell h little ne");
    }

    public String removeAllChars(String input, char c) {

        int i = 0;

        while (i < input.length()) {
            if (input.charAt(i) == c) {
                input = input.substring(0, i) + input.substring(i + 1);
            }
            i++;
        }

        return input;

    }
}
