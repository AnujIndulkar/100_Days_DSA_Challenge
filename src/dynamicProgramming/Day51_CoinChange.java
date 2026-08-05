package dynamicProgramming;

import java.util.*;

public class Day51_CoinChange {

    // 1. BRUTE FORCE (RECURSION)
    // Time Complexity: Exponential
    // Space Complexity: O(amount)

    public int coinChangeBrute(int[] coins, int amount) {
        int res = helper(coins, amount);
        return (res == Integer.MAX_VALUE) ? -1 : res;
    }

    private int helper(int[] coins, int amount) {

        if (amount == 0) return 0;
        if (amount < 0) return Integer.MAX_VALUE;

        int minCoins = Integer.MAX_VALUE;

        for (int coin : coins) {
            int res = helper(coins, amount - coin);

            if (res != Integer.MAX_VALUE) {
                minCoins = Math.min(minCoins, res + 1);
            }
        }

        return minCoins;
    }

    // 2. DYNAMIC PROGRAMMING (BOTTOM-UP)
    // Time Complexity: O(n * amount)
    // Space Complexity: O(amount)

    public int coinChangeDP(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return (dp[amount] > amount) ? -1 : dp[amount];
    }

    public static void main(String[] args) {

        Day51_CoinChange obj = new Day51_CoinChange();

        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println("Brute Force: " + obj.coinChangeBrute(coins, amount));
        System.out.println("DP: " + obj.coinChangeDP(coins, amount));
    }
}
