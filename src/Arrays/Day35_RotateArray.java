package Arrays;

import java.util.*;

public class Day35_RotateArray {

    // TOPIC: Rotate Array

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n * k)
    // Space Complexity: O(1)
    public static int[] bruteForce(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        for (int i = 0; i < k; i++) {
            int last = nums[n - 1];
            // shift right
            for (int j = n - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = last;
        }

        return nums;
    }


    // BETTER APPROACH (Using Extra Array)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int[] better(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            temp[(i + k) % n] = nums[i];
        }

        return temp;
    }

    // OPTIMAL APPROACH (Reversal Algorithm)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static int[] optimal(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);

        return nums;
    }

    // helper function
    private static void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
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

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        // int[] res = bruteForce(nums, k);
        // int[] res = better(nums, k);
        int[] res = optimal(nums, k);

        System.out.println("Array after rotation:");
        for (int num : res) {
            System.out.print(num + " ");
        }
    }
}
