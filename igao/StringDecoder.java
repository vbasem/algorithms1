package igao;

public class StringDecoder {
    public static void main(String[] args) {
        StringDecoder solver = new StringDecoder();

        assert solver.solveRecursive("3[a]2[bc]").equals("aaabcbc");
        assert solver.solveRecursive("11[a]2[bc]").equals("aaaaaaaaaaabcbc");
        assert solver.solveRecursive("2[abc]3[cd]ef").equals("abcabccdcdcdef");
        assert solver.solveRecursive("abc3[cd]xyz").equals("abccdcdcdxyz");
    }

    private String solveRecursive(String input) {
        StringBuilder builder = new StringBuilder();
        helper(input, builder);
        return builder.toString();
    }

    private void helper(String input, StringBuilder builder) {
        int indexOfBracket = input.indexOf('[');
        int indexOfBracketEnd = input.indexOf(']');
        int digitIndex = indexOfBracket - 1;
        String repeat = "";
        while (digitIndex >= 0 && Character.isDigit(input.charAt(digitIndex))) {
            repeat = repeat + input.charAt(digitIndex);
            digitIndex--;
        }
        int subStringEndIndex = indexOfBracket != -1 ? digitIndex + 1 : input.length();
        builder.append(input, 0, subStringEndIndex);

        if (indexOfBracket != -1) {
            int repeats = Integer.parseInt(repeat);
            while (repeats-- > 0) {
                builder.append(input, indexOfBracket + 1, indexOfBracketEnd);
            }
            helper(input.substring(indexOfBracketEnd + 1), builder);
        }


    }

    private String solve(String input) {
        StringBuilder builder = new StringBuilder();
        String multiplier = "";
        String repeat = "";
        boolean start = false;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                multiplier += c;
                start = true;

                continue;
            }
            if (!start) {
                builder.append(c);
                continue;
            }
            if (start && Character.isLetter(c)) {
                repeat += c;
            }
            if (start && c == ']') {
                int m = Integer.parseInt(multiplier);
                while (m-- > 0) {
                    builder.append(repeat);
                }
                repeat = "";
                start = false;
                multiplier = "";
            }
        }
        return builder.toString();
    }
}
