package wordbank;

import java.security.DrbgParameters.Reseed;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WordBankSolver {

    public static void main(String[] args) {
        WordBankSolver solver = new WordBankSolver();
        // String target = "abababcabc";
        // String target ="abcd";
        // String target = "skateboard";
        String target =        "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef";
        // String target = "purple";
        // String[] bank = { "ab", "abc", "cd", "def", "yo", "abcd", "c" };
        // String[] bank = { "purp", "p", "ur", "le", "purpl", "e" };
        // String[] bank = {"a", "bcd", "ab","cd", "abcd"};
        // String[] bank = { "bo", "rd", "ate", "t", "ska", "sk", "boar" };
        String[] bank = { "e", "ee", "eee", "eeee", "eeeee", "eeeeee" };
        // boolean canSolver = solver.canConstrucut(target, bank, new HashMap<String,
        // Boolean>());
        // System.out.println("can construct: " + canSolver);
        // System.out.println("Count: " + solver.counter);
        // int count = solver.countConstruct(target, bank, new HashMap<String, Integer>());
        // System.out.println("possible constructs: " + count);
        List<List<String>> all = solver.allConstructs(target, bank, new HashMap<String, List<List<String>>>());
        for (var x: all)  {
            x.forEach(s -> System.out.print(s + " -> "));
            System.out.println();
        }
        
    }

    private List<List<String>> allConstructs(String target, String[] bank, HashMap<String, List<List<String>>> memo) {
        if (target.equals("")) {
            List<List<String>> r = new ArrayList<>();
            r.add(new ArrayList<>());
            return r;
            
        } else if (memo.containsKey(target)) { 
            return memo.get(target);
        }else { 
            List<List<String>> result = null;
            List<List<String>> tmp = new ArrayList<>();

            for (int i = 0; i< bank.length; i++)  {
                if (target.indexOf(bank[i])  != 0) {
                    continue;
                }
                String newTarget = target.substring(bank[i].length(), target.length());
                result = allConstructs(newTarget, bank, memo);

                if (result != null) {
                    for (List<String> r : result) {
                        var x = new ArrayList<String>();
                        x.addAll(r);
                        x.add(bank[i]);
                        tmp.add(x);
                    }
                }

            }
            memo.put(target, tmp);
            return tmp;
        }
    }

    int possbileSolutions = 0;

    private int countConstruct(String target, String[] bank, HashMap<String, Integer> memo) {
        if (target.equals("")) {
            return 1;
        } else if (memo.containsKey(target)) {
            return memo.get(target);
        } else {

            int result = 0;
            Integer temp = 0;
            counter++;
            for (String word : bank) {
                int indexOfWord = target.indexOf(word);
                if (indexOfWord != 0) {
                    continue;
                }

                String newTarget = target.substring(word.length(), target.length());

                result = countConstruct(newTarget, bank, memo);
                temp += result;
            }
            memo.put(target, temp);
            return temp;
        }
    }

    public int counter = 0;

    // abc /abc ""
    // a -> b no index -> no lookup
    // abcd abc d
    public boolean canConstrucut(String target, String[] bank, Map<String, Boolean> memo) {
        if (target.equals("")) {
            return true;
        } else if (memo.containsKey(target)) {
            return memo.get(target);
        } else {

            boolean result = false;
            counter++;
            for (String word : bank) {
                int indexOfWord = target.indexOf(word);
                if (indexOfWord == -1) {
                    continue;
                }

                if (indexOfWord != 0 && (indexOfWord + word.length()) != target.length()) {
                    continue;
                }

                String newTarget = "";

                if (indexOfWord == 0) {
                    newTarget = target.substring(word.length(), target.length());
                } else {
                    newTarget = target.substring(0, indexOfWord);
                }

                // System.out.println(target + " - " + word + " = " + newTarget);

                result = canConstrucut(newTarget, bank, memo);
                // memo.put(newTarget, result);

                if (result == true) {
                    break;
                }
            }
            memo.put(target, result);
            return result;
        }

    }

}
