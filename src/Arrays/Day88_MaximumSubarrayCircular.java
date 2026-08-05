package Arrays;

public class Day88_MaximumSubarrayCircular {

    // --------------------------------------------------
    // 1. BRUTE FORCE
    // Topic: Array
    // Time Complexity: O(n²)
    // Space Complexity: O(1)
    // --------------------------------------------------

    public int maxSubarraySumCircularBrute(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = 0; j < n; j++) {
                currentSum += nums[(i + j) % n];
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }


    // --------------------------------------------------
    // 2. OPTIMAL (KADANE'S ALGORITHM)
    // Topic: Array, Dynamic Programming
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    // --------------------------------------------------

    public int maxSubarraySumCircularOptimal(int[] nums) {
        int totalSum = 0;
        int currentMax = 0;
        int maxSum = nums[0];
        int currentMin = 0;
        int minSum = nums[0];

        for (int num : nums) {
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);
            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);
            totalSum += num;
        }

        if (maxSum < 0) {
            return maxSum;
        }
        return Math.max(maxSum, totalSum - minSum);
    }


    public static void main(String[] args) {

        Day88_MaximumSubarrayCircular obj = new Day88_MaximumSubarrayCircular();

        int[] nums = {1, -2, 3, -2};
        System.out.println("Brute Force : " + obj.maxSubarraySumCircularBrute(nums));
        System.out.println("Optimal : " + obj.maxSubarraySumCircularOptimal(nums));
    }
}
