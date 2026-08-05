package twopointer;

import java.util.*;

public class Day22_ThreeSumClosest {

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n^3)
    // Space Complexity: O(1)

    public static int bruteForce(int[] nums, int target) {
        int n = nums.length;
        int closestSum = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                        closestSum = sum;
                    }
                }
            }
        }
        return closestSum;
    }

    // OPTIMAL APPROACH (Two Pointer)
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)

    public static int optimal(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                // Update closest
                if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                    closestSum = sum;
                }
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closestSum;
    }
}
