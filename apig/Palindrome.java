package apig;

public class Palindrome {

    public static void main(String[] args) {
        Palindrome solver = new Palindrome();
        assert solver.isPalindrome("madam");
        assert solver.isPalindrome("abccba");
        assert !solver.isPalindrome("abcd");
    }

    public boolean isPalindrome(String in)  {
        if (in == null ) {
            return false;
        }
        int s = 0;
        int e = in.length() - 1;

        while (s < e)  {
            if (in.charAt(s++) != in.charAt(e--))  {
                return false;
            }
        }

        return true;

    }
}
