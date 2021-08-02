package tabulation;

public class CanConstructTab {
    public static void main(String[] args) {
        CanConstructTab solver = new CanConstructTab();
        String target = "abcabc";
        // String target ="abcd";
        // String target = "skateboard";
        // String target = "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef";
        // String target = "purple";
        String[] bank = { "ab", "abc", "c" };
        // String[] bank = { "purp", "p", "ur", "le", "purpl", "e" };
        // String[] bank = {"a", "bcd", "ab","cd", "abcd"};
        // String[] bank = { "bo", "rd", "ate", "t", "ska", "sk", "boar" };
        // String[] bank = { "e", "ee", "eee", "eeee", "eeeee", "eeeeee" , "f"};
        boolean result = solver.canConstruct(target, bank);
        System.out.println("result = " + result);
    }

    public boolean canConstruct(String target, String[] bank) {
        boolean[] tab = new boolean[target.length() + 1];
        tab[0] = true;

        for (int i = 0; i < tab.length ; i++) {
            if (tab[i] == true) {
                for (String word : bank) {
                    if ((word.length() + i ) < tab.length) {
                        String w = target.substring(i , word.length()+ i);
                        System.out.println("w = " + w + " word: "+ word + " equals : " + w.equals(word));
                        if (w.equals(word)) {
                            tab[i+word.length()] = true;
                        }
                    }

                }

            }
        }
        return tab[tab.length - 1];
    }
}
