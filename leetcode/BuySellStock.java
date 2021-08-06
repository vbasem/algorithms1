package leetcode;

import java.util.HashMap;
import java.util.Map;

public class BuySellStock {
    static int cacheHit = 0;

    public static void main(String[] args) {

        assert findBestProfit(new int[]{7, 1, 5, 3, 6, 4}) == 5;
        assert findBestProfit(new int[]{7, 1, 5, 3, 6, 48, 15, 203, 40, 1, 5, 12, 33, 44, 12, 2, 3, 4, 5, 2000, 0, 2001}) == 2001;
        assert findBestProfit(new int[]{7, 6, 4, 3, 1}) == 0;
        assert findBestProfit(new int[]{}) == 0;
        assert findBestProfit(null) == 0;
    }

    private static int findBestProfit(int[] stock) {
        cacheHit = 0;
        Map<String, Integer> memo = new HashMap<>();
        if (stock == null || stock.length < 2) {
            return 0;
        }
        int result = find(stock, 0, 1, memo);
        System.out.println("cacheHit = " + cacheHit);
        return result;
    }

    private static int find(int[] stock, int start, int end, Map<String, Integer> memo) {
        String key = start + ":" + end;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (end == stock.length - 1) {
            return Math.max(stock[end] - stock[start], 0);
        }
        cacheHit++;
        int result = Math.max(stock[end] - stock[start], Math.max(find(stock, start, end + 1, memo), find(stock, start + 1, end + 1, memo)));
        memo.put(key, result);
        return result;
    }


}
