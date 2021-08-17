package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GuessTheWord {

    public static void main(String[] args) {
        Master master = new Master("acckzz");

        GuessTheWord guessTheWord = new GuessTheWord();
        String[] words = generateRandomWords(100);
        words[0] = "acckzz";
        words[1] = "ccbazz";
        words[2] = "eiowzz";
        words[3] = "abcczz";


        guessTheWord.guess(words, master);
    }

    private void guess(String[] words, Master master) {
        int triesLeft = 10;
        Random random = new Random();
        List<String> nextCandidates = Arrays.stream(words).collect(Collectors.toList());
        for (int i = 0; i < triesLeft; i++)  {
            String nextCandidate = nextCandidates.get(random.nextInt(nextCandidates.size()));
            int matches = master.guess(nextCandidate);
            if (matches == 6) {
                System.out.printf("we solved the problem within %s tries \n", i);
                return;
            }

            List<String> temp = new ArrayList<>();
            for (String word: words) {
                if (!word.equals(nextCandidate) && isSimilarMatch(word, nextCandidate, matches)) {
                    temp.add(word);
                }
            }
            nextCandidates = temp;
        }

        System.out.println("failed to find word");
    }

    private boolean isSimilarMatch(String word, String candidate, int matches) {
        return  IntStream.range(0, word.length()).filter(i -> word.charAt(i) == candidate.charAt(i)).count() == matches;

    }

    public static String[] generateRandomWords(int numberOfWords)
    {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[6];
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }


    static class Master {
        String key;

        public Master(String key) {
            this.key = key;
        }

        public int guess(String in) {
            if (in.length() != key.length()) {
                throw new RuntimeException("input is not of the same length as the key");
            }
            int matches = 0;
            for (int i = 0; i < in.length(); i++) {
                if (in.charAt(i) == key.charAt(i)) {
                    matches++;
                }
            }

            return matches;
        }

    }
}
