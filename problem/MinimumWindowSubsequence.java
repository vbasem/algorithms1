package problem;

public class MinimumWindowSubsequence {
    public static void main(String[] args) {
        MinimumWindowSubsequence solver = new MinimumWindowSubsequence();
        String result = solver.findSubsequence("ccadeebdecdedbdede", "bdee");
        assert result.equals("bdede");
        assert solver.findSubsequence("abc", "ac").equals("abc");
        assert solver.findSubsequence("aabc", "ac").equals("abc");

    }


    public String findSubsequence(String word, String sub) {
        if (word == null || sub == null) {
            return "";
        }

        if (word.length() == 0 || sub.length() == 0) {
            return "";
        }

        char[] wordArray = word.toCharArray();
        char[] subArray = sub.toCharArray();
        Node[][] dp = new Node[sub.length() + 1][word.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = new Node(0);
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = new Node(0);
        }

        for (int i = 1; i <= subArray.length; i++) {
            for (int j = 1; j <= wordArray.length; j++) {
                if (wordArray[j - 1] == subArray[i - 1]) {
                    dp[i][j] = dp[i - 1][j - 1].match(dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i][j - 1].passNoMatch(dp[i - 1][j]);
                }
            }
        }

        return buildResult(dp, wordArray, subArray);
    }

    private String buildResult(Node[][] dp, char[] wordArray, char[] subArray) {
        if (dp[dp.length - 1][dp[0].length - 1].value != subArray.length) {
            System.out.println("no result found");
            return "";
        }
        int rowIndex = dp.length - 1;
        int colIndex = dp[0].length - 1;
        Node start = dp[rowIndex][colIndex];
        for (int i = 0; i < dp[rowIndex].length; i++) {
            if (dp[rowIndex][i].value == start.value && dp[rowIndex][i].counter < start.counter) {
                start = dp[rowIndex][i];
                colIndex = i;
            }
        }
        StringBuilder result = new StringBuilder();
        while (rowIndex > 0 && colIndex > 0) {
            Node currentNode = dp[rowIndex][colIndex];
            while (currentNode.isSuccessor(dp[rowIndex][colIndex - 1])) {
                if (result.length() > 0) {
                    result.append(wordArray[colIndex - 1]);
                }
                colIndex--;
                currentNode = dp[rowIndex][colIndex];
            }
            result.append(wordArray[colIndex - 1]);
            rowIndex--;
            colIndex--;
        }

        String r =  result.reverse().toString();
        return r;
    }

    class Node {
        int value;
        int counter;

        public Node(int value) {
            this.value = value;
            counter = 0;
        }

        public Node(int value, int counter) {
            this.value = value;
            this.counter = counter;
        }

        public Node match(Node right) {
            if (right.value > this.value) {
                return new Node(right.value, right.counter);
            }
            return new Node(value + 1, counter + 1);
        }

        public Node passNoMatch(Node top) {
            if (top.value >= this.value) {
                return new Node(top.value, top.counter);
            }
            return new Node(this.value, this.counter == 0 ? 0 : counter + 1);
        }

        public boolean isSuccessor(Node prev) {
            return prev.value == this.value && prev.counter < this.counter;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", counter=" + counter +
                    '}';
        }
    }
}
