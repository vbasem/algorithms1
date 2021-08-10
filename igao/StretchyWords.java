package igao;

public class StretchyWords {

    public static void main(String[] args) {
        StretchyWords solver = new StretchyWords();

        assert solver.solve("heeellooo", new String[]{"hello", "hi", "helo"}) == 1;
        assert solver.solve("zzzzzyyyyy", new String[]{"zzyy", "zy", "zyy"}) == 3;
    }

    private int solve(String target, String[] words) {

        int result = 0;

        for (String s : words) {
            result += doSolve(target, s);
        }
        return result;
    }

    private int doSolve(String target, String word) {
        int letterRepeats;
        int i = 0;
        int j = 0;
        while (j < word.length()) {
            letterRepeats = 1;
            if (target.charAt(i) != word.charAt(j)) {
                return 0;
            }
            char current = target.charAt(i);
            while (i + 1 < target.length() && target.charAt(i + 1) == current) {
                i++;
                letterRepeats++;
            }

            while (j + 1 < word.length() && word.charAt(j + 1) == current) {
                j++;
                letterRepeats--;
            }
            if (letterRepeats == 1 || letterRepeats > 2) {
                i++;
                j++;
            } else {
                return 0;
            }
        }

        if (i < target.length()) {
            return 0;
        }

        return 1;
    }


}
