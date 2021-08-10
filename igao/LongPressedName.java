package igao;

public class LongPressedName {

    public static void main(String[] args) {
        LongPressedName solver = new LongPressedName();


//        assert !solver.solve("saeed", "ssaaed"): "got" + solver.solve("saeed", "ssaaed");
        assert solver.solve("leelee", "lleeeeleee") : "got" + solver.solve("leelee", "lleeeeleee");


    }

    private boolean solve(String name, String typed) {
        char[] dp = new char[name.length()];

        int dpIndex = 0;
        for (char c : typed.toCharArray()) {
            if (dpIndex < name.length() && name.charAt(dpIndex) == c) {
                dp[dpIndex++] = c;
            } else if (dpIndex == 0) {
                return false;
            } else if (dp[dpIndex - 1] != c) {
                return false;
            }
        }

        return true;
    }
}
