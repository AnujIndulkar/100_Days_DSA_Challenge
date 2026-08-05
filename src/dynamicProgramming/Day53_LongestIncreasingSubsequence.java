package dynamicProgramming;

import java.util.*;

public class Day53_LongestIncreasingSubsequence {

    // 1. BRUTE FORCE (RECURSION)
    // Time Complexity: O(2^n)
    // Space Complexity: O(n)

    public int lisBrute(int[] nums) {
        return helper(nums, 0, Integer.MIN_VALUE);
    }

    private int helper(int[] nums, int index, int prev) {

        if (index == nums.length) return 0;

        // Skip current element
        int notTake = helper(nums, index + 1, prev);

        // Take current element
        int take = 0;

        if (nums[index] > prev) {
            take = 1 + helper(nums, index + 1, nums[index]);
        }

        return Math.max(take, notTake);
    }

    // 2. DYNAMIC PROGRAMMING (BOTTOM-UP)
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)

    public int lisDP(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxLength = 1;

        for (int i = 1; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    public static void main(String[] args) {

        Day53_LongestIncreasingSubsequence obj =
                new Day53_LongestIncreasingSubsequence();

        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println("Brute Force: " + obj.lisBrute(nums));
        System.out.println("DP: " + obj.lisDP(nums));
    }
}