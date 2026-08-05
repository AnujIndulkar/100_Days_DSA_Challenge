package dynamicProgramming;

public class Day52_ClimbingStairs {

    // 1. BRUTE FORCE (RECURSION)
    // Time Complexity: O(2^n)
    // Space Complexity: O(n)

    public int climbStairsBrute(int n) {
        return helper(n);
    }

    private int helper(int n) {

        if (n == 0 || n == 1) return 1;

        return helper(n - 1) + helper(n - 2);
    }

    // 2. DYNAMIC PROGRAMMING (BOTTOM-UP)
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public int climbStairsDP(int n) {

        if (n == 0 || n == 1) return 1;

        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {

        Day52_ClimbingStairs obj = new Day52_ClimbingStairs();

        int n = 5;

        System.out.println("Brute Force: " + obj.climbStairsBrute(n));
        System.out.println("DP: " + obj.climbStairsDP(n));
    }
}
