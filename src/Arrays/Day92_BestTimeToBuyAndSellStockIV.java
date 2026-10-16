package Arrays;

public class Day92_BestTimeToBuyAndSellStockIV {

    // --------------------------------------------------
    // 1. BRUTE FORCE (RECURSION)
    // Topic: Array, Recursion, Dynamic Programming
    // Time Complexity: O(2^n)
    // Space Complexity: O(n)
    // --------------------------------------------------

    public int maxProfitBrute(int k, int[] prices) {
        return solve(0, 0, k, prices);
    }

    private int solve(int index, int buy, int transactions, int[] prices) {

        if (index == prices.length || transactions == 0) {
            return 0;
        }

        if (buy == 0) {
            // Buy
            int take = -prices[index] + solve(index + 1, 1, transactions, prices);
            // Skip
            int skip = solve(index + 1, 0, transactions, prices);

            return Math.max(take, skip);

        } else {
            // Sell
            int sell = prices[index] + solve(index + 1, 0, transactions - 1, prices);
            // Hold
            int hold = solve(index + 1, 1, transactions, prices);

            return Math.max(sell, hold);
        }
    }


    // --------------------------------------------------
    // 2. OPTIMAL (DP)
    // Topic: Array, Dynamic Programming
    // Time Complexity: O(n * k)
    // Space Complexity: O(k)
    // --------------------------------------------------

    public int maxProfitOptimal(int k, int[] prices) {

        if (prices.length == 0 || k == 0) {
            return 0;
        }

        // If k is large, this becomes
        // unlimited transactions.
        if (k >= prices.length / 2) {
            int profit = 0;

            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }

            return profit;
        }

        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];

        for (int transaction = 1; transaction <= k; transaction++) {

            buy[transaction] = Integer.MIN_VALUE;
        }

        for (int price : prices) {

            for (int transaction = 1; transaction <= k; transaction++) {

                buy[transaction] = Math.max(buy[transaction], sell[transaction - 1] - price);

                sell[transaction] = Math.max(sell[transaction], buy[transaction] + price);
            }
        }

        return sell[k];
    }


    public static void main(String[] args) {

        Day92_BestTimeToBuyAndSellStockIV obj = new Day92_BestTimeToBuyAndSellStockIV();

        int k = 2;

        int[] prices = {3, 2, 6, 5, 0, 3};

        System.out.println("Brute Force : " + obj.maxProfitBrute(k, prices));

        System.out.println("Optimal : " + obj.maxProfitOptimal(k, prices));
    }
}