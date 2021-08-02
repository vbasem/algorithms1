
package palindrome;

import java.util.Deque;
import java.util.LinkedList;

public class PalindromeQueue {

    public static void main(String[] args) {
        // System.out.println(check("abc d", "d cba"));
        // System.out.println(check("abc d", "d cbaa"));
        // System.out.println(check("abc d", ""));
        // System.out.println(check("abc d", "abcde"));

        // should return true
        System.out.println(checkForPalindrome2("abccba"));
        // should return true
        System.out.println(checkForPalindrome2("Was it a car or a cat I saw?"));
      //  should return true
        System.out.println(checkForPalindrome2("I did, did I?"));
      //  should return false
        System.out.println(checkForPalindrome2("hello"));
      // should return true
        System.out.println(checkForPalindrome2("Don't nod"));
        System.out.println("-------");
        System.out.println(checkForPalindrome2("abc1cba"));
        System.out.println(checkForPalindrome2("abc12cba"));
        System.out.println(checkForPalindrome2("?"));
        System.out.println(checkForPalindrome2("?!"));
        System.out.println(checkForPalindrome2(""));

    }

    public static boolean checkForPalindrome2(String input) {
        Deque<Character> queue = new LinkedList<>();
        Deque<Character> stack = new LinkedList<>();

        for (char c : input.toLowerCase().toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                queue.add(c);
                stack.push(c);
            }
        }

        for (int i = 0; i < queue.size(); i++) {
            char a = queue.poll();
            char b = stack.pop();
            // System.out.print(a);
            // System.out.print(" - ");
            // System.out.print(b);
            // System.out.println();
            if (a != b) {
                return false;
            }
        }

        return true;
    }
}