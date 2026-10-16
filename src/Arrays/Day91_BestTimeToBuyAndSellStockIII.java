package Arrays;

public class Day91_BestTimeToBuyAndSellStockIII {

    // --------------------------------------------------
    // 1. BRUTE FORCE (RECURSION)
    // Topic: Array, Recursion, Dynamic Programming
    // Time Complexity: O(3^n)
    // Space Complexity: O(n)
    // --------------------------------------------------

    public int maxProfitBrute(int[] prices) {

        return solve(0, 0, 2, prices);
    }

    private int solve(int index, int buy, int transactions, int[] prices) {

        if (index == prices.length || transactions == 0) {

            return 0;
        }

        int profit;

        if (buy == 0) {

            // Buy
            int take = -prices[index] + solve(index + 1, 1, transactions, prices);

            // Skip
            int skip = solve(index + 1, 0, transactions, prices);

            profit = Math.max(take, skip);

        } else {

            // Sell
            int sell = prices[index] + solve(index + 1, 0, transactions - 1, prices);

            // Hold
            int hold = solve(index + 1, 1, transactions, prices);

            profit = Math.max(sell, hold);
        }

        return profit;
    }


    // --------------------------------------------------
    // 2. OPTIMAL (DP)
    // Topic: Array, Dynamic Programming
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // --------------------------------------------------

    public int maxProfitOptimal(int[] prices) {

        int buy1 = Integer.MIN_VALUE;
        int sell1 = 0;

        int buy2 = Integer.MIN_VALUE;
        int sell2 = 0;

        for (int price : prices) {

            // First transaction
            buy1 = Math.max(buy1, -price);

            sell1 = Math.max(sell1, buy1 + price);

            // Second transaction
            buy2 = Math.max(buy2, sell1 - price);

            sell2 = Math.max(sell2, buy2 + price);
        }

        return sell2;
    }


    public static void main(String[] args) {

        Day91_BestTimeToBuyAndSellStockIII obj = new Day91_BestTimeToBuyAndSellStockIII();

        int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };

        System.out.println("Brute Force : " + obj.maxProfitBrute(prices));

        System.out.println("Optimal : " + obj.maxProfitOptimal(prices));
    }
}