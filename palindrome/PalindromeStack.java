package palindrome;

import java.util.Deque;
import java.util.LinkedList;

public class PalindromeStack{

    public static void main(String[] args) {
        // System.out.println(check("abc d", "d cba"));
        // System.out.println(check("abc d", "d cbaa"));
        // System.out.println(check("abc d", ""));
        // System.out.println(check("abc d", "abcde"));

                // should return true
                System.out.println(checkForPalindrome("abccba"));
                // should return true
                System.out.println(checkForPalindrome("Was it a car or a cat I saw?"));
                // should return true
                System.out.println(checkForPalindrome("I did, did I?"));
                // should return false
                System.out.println(checkForPalindrome("hello"));
                // should return true
                System.out.println(checkForPalindrome("Don't nod"));
                System.out.println("-------");
                System.out.println(checkForPalindrome("abc1cba"));
                System.out.println(checkForPalindrome("abc12cba"));
                System.out.println(checkForPalindrome("?"));
                System.out.println(checkForPalindrome("?!"));
                System.out.println(checkForPalindrome(""));

    }

    public static boolean check(String a, String b) {

        Deque<Character> stack = new LinkedList<>();

        if (a.length() != b.length()) {
            return false;
        }

        for(char c: a.toCharArray()) {
            stack.push(c);
        }

        for (char c: b.toCharArray()) {
            if (c != stack.pop()) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkForPalindrome(String input) { 
        String lowerdInput = input.toLowerCase();
        Deque<Character> stack = new LinkedList<>();

        for (char c: lowerdInput.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                stack.push(c);
            }
        }

        
        for (char c: lowerdInput.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                if (stack.pop() != c) {
                    return false;
                }
            }
        }


        return true;
    }



}