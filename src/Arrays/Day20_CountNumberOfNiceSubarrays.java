package Arrays;

import java.util.Scanner;

public class Day20_CountNumberOfNiceSubarrays {

    // BRUTE FORCE APPROACH
    // Idea: Generate all possible subarrays and count odd numbers
    // If the number of odd numbers equals k, increase the count
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)

    public static int bruteForce(int[] nums, int k) {
        int n = nums.length;
        int count = 0;
        for(int i = 0; i < n; i++) {
            int oddCount = 0;
            for(int j = i; j < n; j++) {
                if(nums[j] % 2 == 1) {
                    oddCount++;
                }
                if(oddCount == k) {
                    count++;
                }
            }
        }
        return count;
    }

    // OPTIMAL APPROACH
    // Idea: Use Sliding Window with the formula
    // exactly(k) = atMost(k) - atMost(k-1)
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    public static int optimal(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }
    public static int atMost(int[] nums, int k) {
        int left = 0;
        int count = 0;
        for(int right = 0; right < nums.length; right++) {
            if(nums[right] % 2 == 1) {
                k--;
            }
            while(k < 0) {
                if(nums[left] % 2 == 1) {
                    k++;
                }
                left++;
            }
            count += right - left + 1;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of array: ");
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.println("Enter array elements: ");
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.print("Enter value of k: ");
        int k = sc.nextInt();
//        System.out.println("Brute Force Answer: " + bruteForce(nums, k));
        System.out.println("Optimal Answer: " + optimal(nums, k));
    }
}
