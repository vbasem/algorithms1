package problem;

public class EncodeStringShortestLength {
    String[][] dp;

    public static void main(String[] args) {
        EncodeStringShortestLength solver = new EncodeStringShortestLength();
        System.out.println(solver.encode("abbbabbbcabbbabbbc"));
//        System.out.println(solver.encode("ababababababab"));
    }

    public String encode(String input) {
        dp = new String[input.length()][input.length()];

        for (int length = 1; length <= input.length(); length++) {
            for (int j = 0; j + length <= input.length(); j++) {
                int end = length + j;
                String sub = input.substring(j, end);
                dp[j][end- 1 ] = sub;

                if (length < 4) {
                    continue;
                }
                String shortest = sub;

                int repeatPosition = (sub + sub).indexOf(sub, 1);
                int repeats = sub.length() / repeatPosition;
                if (repeatPosition < sub.length()) {
                    shortest = repeats + "[" + dp[j][j + repeatPosition - 1 ] + "]";
                }


                for (int k = j; k  + 1 < end; k++) {
                    if (dp[j][k].length() + dp[k + 1][end - 1].length() < shortest.length()) {
                        shortest = dp[j][k] + dp[k + 1][end - 1];
                    }
                }
                dp[j][end - 1] = shortest;
            }
        }

        return dp[0][dp[0].length  - 1];
    }

}
