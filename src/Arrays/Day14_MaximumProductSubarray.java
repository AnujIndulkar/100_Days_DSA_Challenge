package Arrays;

import java.util.Scanner;

public class Day14_MaximumProductSubarray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }
        // System.out.println(bruteForce(nums));
        System.out.println(optimal(nums));
    }

    // Brute Force Approach (Check all subarrays)
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public static int bruteForce(int[] nums){
        int maxProduct = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            int product = 1;
            for(int j = i; j < nums.length; j++){
                product *= nums[j];
                maxProduct = Math.max(maxProduct, product);
            }
        }
        return maxProduct;
    }

    // Optimal Approach (Tracking Maximum and Minimum Product)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static int optimal(int[] nums){
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];
        for(int i = 1; i < nums.length; i++){
            if(nums[i] < 0){
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }
            maxProduct = Math.max(nums[i], maxProduct * nums[i]);
            minProduct = Math.min(nums[i], minProduct * nums[i]);
            result = Math.max(result, maxProduct);
        }
        return result;
    }
}
