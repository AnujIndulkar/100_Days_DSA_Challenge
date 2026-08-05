package Arrays;

public class Day87_BestTimeToBuyAndSellStockII {

    // --------------------------------------------------
    // 1. BRUTE FORCE (RECURSION)
    // Topic: Array, Recursion
    // Time Complexity: O(2^n)
    // Space Complexity: O(n)
    // --------------------------------------------------

    public int maxProfitBrute(int[] prices) {
        return solve(0, true, prices);
    }

    private int solve(int index, boolean canBuy, int[] prices) {
        if (index == prices.length) {
            return 0;
        }

        if (canBuy) {
            int buy = -prices[index] + solve(index + 1, false, prices);
            int skip = solve(index + 1, true, prices);
            return Math.max(buy, skip);

        } else {
            int sell = prices[index] + solve(index + 1, true, prices);
            int hold = solve(index + 1, false, prices);
            return Math.max(sell, hold);
        }
    }

    // --------------------------------------------------
    // 2. OPTIMAL (GREEDY)
    // Topic: Array, Greedy
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // --------------------------------------------------

    public int maxProfitOptimal(int[] prices) {
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        Day87_BestTimeToBuyAndSellStockII obj = new Day87_BestTimeToBuyAndSellStockII();
        int[] prices = {7,1,5,3,6,4};
        System.out.println("Brute Force : " + obj.maxProfitBrute(prices));
        System.out.println("Optimal : " + obj.maxProfitOptimal(prices));
    }
}
