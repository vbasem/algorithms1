package problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptimalAccountBalancing {


    int max = Integer.MAX_VALUE;

    public static void main(String[] args) {
        OptimalAccountBalancing solver = new OptimalAccountBalancing();
        assert solver.findMinNumberOfTransactions(new int[][]{{0, 1, 10}, {2, 0, 5}}) == 2;
        assert solver.findMinNumberOfTransactions(new int[][]{{0, 1, 10}, {1, 0, 1}, {1, 2, 5}, {2, 0, 5}}) == 1;

    }

    public int findMinNumberOfTransactions(int[][] transactions) {
        Map<Integer, Integer> debts = new HashMap<>();
        for (int i = 0; i < transactions.length; i++) {
            int a = transactions[i][0];
            int b = transactions[i][1];
            int amount = transactions[i][2];

            debts.put(a, debts.getOrDefault(a, 0) - amount);
            debts.put(b, debts.getOrDefault(b, 0) + amount);
        }


        List<Integer> unsettled = new ArrayList<>();
        for (Integer debt : debts.values()) {
            if (debt != 0) {
                unsettled.add(debt);
            }
        }

        settle(unsettled, 0, 0);
        return max;

    }

    private boolean settle(List<Integer> unsettled, int index, int transactionsCount) {

        if (transactionsCount > max) {
            return false;
        }

        if (index >= unsettled.size()) {
            max = transactionsCount;
            return true;
        }

        if (unsettled.get(index) == 0) {
            return settle(unsettled, index + 1, transactionsCount);
        }


        for (int i = index + 1; i < unsettled.size(); i++) {
            int tempIndex = unsettled.get(index);
            int tempI = unsettled.get(i);
            if (unsettled.get(i) == 0) {
                continue;
            }
            if ((unsettled.get(index) ^ unsettled.get(i)) < 0) {

                unsettled.set(index, tempIndex + tempI);
                unsettled.set(i, tempIndex + tempI);
                if (!settle(unsettled, index + 1, transactionsCount + 1)) {
                    unsettled.set(index, tempIndex);
                    unsettled.set(i, tempI);
                }
            }
        }

        return true;
    }

}
