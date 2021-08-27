package leo;

import java.util.Arrays;

public class LongestSubstringWithoutRepeating {

    // start from left and keep taking items to the right with 2 pointers
// keep an array from 0 - 26 with counter which can be fill with char - 'a'
// if counter for any letter > 1
// keep shrinking the window from the left until counter is back to 1,

    public static void main(String[] args) {
        LongestSubstringWithoutRepeating solver = new LongestSubstringWithoutRepeating();

        assert solver.find("abcabcbb").equals("abc");
        assert solver.find("bbbbb").equals("b");
        assert solver.find("").equals("");
        assert solver.find(null).equals("");
        assert solver.find("abcdeffabcdefghiiabcdeccabcdefghij").equals("abcdefghij");
    }

    public String find(String input) {

        if (input == null || input.length() ==0) {
            return "";
        }

        int[] counter = new int[26];
        int leftIndex = 0 ;
        int rightIndex = 0;
        int longestStart = 0;
        int longestEnd = 0;

//abcabcbb
        while (rightIndex < input.length() ) {
            counter[input.charAt(rightIndex) - 'a']++;
            while (counter[input.charAt(rightIndex) - 'a'] == 2) {
                counter[input.charAt(leftIndex++) - 'a']--;
            }
            if (longestEnd - longestStart < rightIndex - leftIndex) {
                longestStart = leftIndex;
                longestEnd = rightIndex;
            }
            rightIndex++;
        }

        return input.substring(longestStart, longestEnd + 1);

    }
}
