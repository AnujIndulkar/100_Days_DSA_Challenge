package twopointer;

import java.util.*;

public class Day33_ThreeSumSmaller {

    // TOPIC: 3Sum Smaller

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n^3)
    // Space Complexity: O(1)
    public static int bruteForce(int[] nums, int target) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] < target) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    // OPTIMAL APPROACH (Sorting + Two Pointers)
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public static int optimal(int[] nums, int target) {

        Arrays.sort(nums);
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum < target) {
                    count += (right - left); // all pairs from left to right-1
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // input array
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] nums = new int[n];

        System.out.print("Enter array elements: ");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.print("Enter target: ");
        int target = sc.nextInt();

        // int res = bruteForce(nums, target); // brute force
        int res = optimal(nums, target); // optimal

        System.out.println("Number of triplets with sum less than target: " + res);
    }
}