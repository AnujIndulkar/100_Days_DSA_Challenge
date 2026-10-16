package Arrays;

public class Day94_BestTimeToBuyAndSellStockWithTransactionFee {

    // --------------------------------------------------
    // 1. BRUTE FORCE (RECURSION)
    // Topic: Array, Recursion, Dynamic Programming
    // Time Complexity: O(2^n)
    // Space Complexity: O(n)
    // --------------------------------------------------

    public int maxProfitBrute(int[] prices, int fee) {

        return solve(0, 0, prices, fee);
    }

    private int solve(int index, int buy, int[] prices, int fee) {

        // No days left
        if (index == prices.length) {

            return 0;
        }

        if (buy == 0) {

            // Buy
            int take = -prices[index] + solve(index + 1, 1, prices, fee);

            // Skip
            int skip = solve(index + 1, 0, prices, fee);

            return Math.max(take, skip);

        } else {

            // Sell and pay transaction fee
            int sell = prices[index] - fee + solve(index + 1, 0, prices, fee);

            // Hold
            int hold = solve(index + 1, 1, prices, fee);

            return Math.max(sell, hold);
        }
    }


    // --------------------------------------------------
    // 2. OPTIMAL (DP - STATE OPTIMIZATION)
    // Topic: Array, Dynamic Programming
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // --------------------------------------------------

    public int maxProfitOptimal(int[] prices, int fee) {

        int cash = 0;

        int hold = -prices[0];

        for (int i = 1; i < prices.length; i++) {

            // Profit after selling
            cash = Math.max(cash, hold + prices[i] - fee);

            // Profit after buying
            hold = Math.max(hold, cash - prices[i]);
        }

        return cash;
    }


    public static void main(String[] args) {

        Day94_BestTimeToBuyAndSellStockWithTransactionFee obj = new Day94_BestTimeToBuyAndSellStockWithTransactionFee();

        int[] prices = {1, 3, 2, 8, 4, 9
        };

        int fee = 2;

        System.out.println("Brute Force : " + obj.maxProfitBrute(prices, fee));

        System.out.println("Optimal : " + obj.maxProfitOptimal(prices, fee));
    }
}