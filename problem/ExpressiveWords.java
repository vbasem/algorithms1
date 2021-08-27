package problem;

public class ExpressiveWords {

    public static void main(String[] args) {
        ExpressiveWords solver = new ExpressiveWords();
        assert solver.expressiveWords(new String[]{"hello", "hi", "helo"}, "heeeellooo") == 1;
    }

    public int expressiveWords(String[] words, String target) {

        int match = 0;
        for (String word : words) {
            if (isMatch(target, word)) {
                match++;
            }
        }

        return match;
    }

    private boolean isMatch(String word, String target) {

        char[] targetC = target.toCharArray();
        char[] wordC = word.toCharArray();

        int wordIndex = 0;
        char prev = targetC[0];
        for (char c : targetC) {
            if (wordIndex >= wordC.length) {
                return false;
            }

            if (wordC[wordIndex] != c) {
                if (wordC[wordIndex] != prev) {
                    return false;
                }
                int repeats = 1;
                while (wordIndex < wordC.length && wordC[wordIndex] == prev) {
                    wordIndex++;
                    repeats++;
                }
                if (repeats == 1) {
                    return false;
                }
            } else {
                prev = c;
                wordIndex++;
            }

        }

        while (wordIndex < wordC.length) {
            if (wordC[wordIndex++] != targetC[targetC.length - 1]) {
                return false;
            }
        }

        return true;
    }
}
