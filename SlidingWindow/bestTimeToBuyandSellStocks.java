package SlidingWindow;

public class bestTimeToBuyandSellStocks {
    /*
        * Approach: Brute Force
        *
     */
    public int maxProfit_1(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            int buy = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                int sell = prices[j];
                res = Math.max(res, sell - buy);
            }
        }
        return res;
    }
    /*
        Time Complexity: O(n^2)
        Space Complexity: O(1)
     */

    /*
        * Approach: Two Pointers
        *
     */
    public int maxProfit_2(int[] prices) {
        int l = 0, r = 1;
        int maxP = 0;

        while (r < prices.length) {
            if (prices[l] < prices[r]) {
                int profit = prices[r] - prices[l];
                maxP = Math.max(maxP, profit);
            } else {
                l = r;
            }
            r++;
        }
        return maxP;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
    /*
        * Approach: Dynamic Programming
        *
     */
    public int maxProfit_3(int[] prices) {
        int maxP = 0;
        int minBuy = prices[0];

        for (int sell : prices) {
            maxP = Math.max(maxP, sell - minBuy);
            minBuy = Math.min(minBuy, sell);
        }
        return maxP;
    }
    /*
        Time Complexity: O(n)
        Space Complexity: O(1)
     */
}
