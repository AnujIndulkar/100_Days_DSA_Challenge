package patterns;

import java.util.*;

public class Day50_JumpGameII {

    // 1. BRUTE FORCE (RECURSION)
    // Time Complexity: Exponential
    // Space Complexity: O(n)

    public int jumpBrute(int[] nums) {
        return helper(nums, 0);
    }

    private int helper(int[] nums, int index) {

        if (index >= nums.length - 1) return 0;

        int minJumps = Integer.MAX_VALUE;

        for (int step = 1; step <= nums[index]; step++) {
            int next = index + step;

            if (next < nums.length) {
                int jumps = helper(nums, next);

                if (jumps != Integer.MAX_VALUE) {
                    minJumps = Math.min(minJumps, jumps + 1);
                }
            }
        }

        return minJumps;
    }

    // 2. DYNAMIC PROGRAMMING
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)

    public int jumpDP(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {

                if (j + nums[j] >= i && dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n - 1];
    }

    // 3. GREEDY (OPTIMAL)
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    public int jumpGreedy(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {

            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }

        return jumps;
    }

    public static void main(String[] args) {

        Day50_JumpGameII obj = new Day50_JumpGameII();

        int[] nums = {2, 3, 1, 1, 4};

        System.out.println("Brute Force: " + obj.jumpBrute(nums));
        System.out.println("DP: " + obj.jumpDP(nums));
        System.out.println("Greedy: " + obj.jumpGreedy(nums));
    }
}
