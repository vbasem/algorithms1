package sandbox;

public class Anagram {
    public static void main(String[] args) {
        Anagram solver = new Anagram();
        assert solver.isAnagram("abc", "cba");
        assert !solver.isAnagram("abcb", "cbaa");


    }
    public boolean isAnagram(String first, String second)  {
        if (first == null || second == null) {
            return false;
        }

        if (first.length() != second.length()) {
            return false;
        }

        int result  =0;

        for (int i = 0; i < first.length(); i++){
            result = first.charAt(i) ^ second.charAt(i) ^ result;
        }


        return result == 0;
    }
}
