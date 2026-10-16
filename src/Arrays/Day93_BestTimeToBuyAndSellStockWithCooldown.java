package Arrays;

public class Day93_BestTimeToBuyAndSellStockWithCooldown {

    // --------------------------------------------------
    // 1. BRUTE FORCE (RECURSION)
    // Topic: Array, Recursion, Dynamic Programming
    // Time Complexity: O(2^n)
    // Space Complexity: O(n)
    // --------------------------------------------------

    public int maxProfitBrute(int[] prices) {
        return solve(0, 0, prices);
    }

    private int solve(int index, int buy, int[] prices) {
        if (index >= prices.length) {
            return 0;
        }

        if (buy == 0) {
            // Buy
            int take = -prices[index] + solve(index + 1, 1, prices);
            // Skip
            int skip = solve(index + 1, 0, prices);
            return Math.max(take, skip);

        } else {
            // Sell and take cooldown
            int sell = prices[index] + solve(index + 2, 0, prices);
            // Hold
            int hold = solve(index + 1, 1, prices);
            return Math.max(sell, hold);
        }
    }

    // --------------------------------------------------
    // 2. OPTIMAL (DP)
    // Topic: Array, Dynamic Programming
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // --------------------------------------------------

    public int maxProfitOptimal(int[] prices) {
        int n = prices.length;

        if (n == 0) {
            return 0;
        }

        int[] buy = new int[n];
        int[] sell = new int[n];

        buy[0] = -prices[0];
        sell[0] = 0;

        for (int i = 1; i < n; i++) {
            // Buy or keep holding
            if (i == 1) {
                buy[i] = Math.max(buy[i - 1], -prices[i]);
            } else {
                buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            }

            // Sell or keep previous profit
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }

        return sell[n - 1];
    }


    public static void main(String[] args) {

        Day93_BestTimeToBuyAndSellStockWithCooldown obj = new Day93_BestTimeToBuyAndSellStockWithCooldown();

        int[] prices = {1, 2, 3, 0, 2};

        System.out.println("Brute Force : " + obj.maxProfitBrute(prices));
        System.out.println("Optimal : " + obj.maxProfitOptimal(prices));
    }
}
