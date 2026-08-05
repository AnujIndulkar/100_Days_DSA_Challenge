package Arrays;

import java.util.*;

public class Day18_MinimumSizeSubarraySum {

    /*
    Brute Force Approach
    Time Complexity - O(n^2)
    Space Complexity - O(1)
     */
    public static void bruteForce(int target, int[] nums) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = i; j < n; j++) {
                sum += nums[j];
                if(sum >= target) {
                    minLength = Math.min(minLength, j - i + 1);
                    break;
                }
            }
        }

        if(minLength == Integer.MAX_VALUE)
            System.out.println(0);
        else
            System.out.println(minLength);
    }

    /*
    Optimal Approach – Sliding Window
    Time Complexity - O(n)
    Space Complexity - O(1)
     */
    public static void optimal(int target, int[] nums) {
        int n = nums.length;
        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        for(int right = 0; right < n; right++) {
            sum += nums[right];
            while(sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        if(minLength == Integer.MAX_VALUE)
            System.out.println(0);
        else
            System.out.println(minLength);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int target = sc.nextInt();
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        bruteForce(target, nums);
        optimal(target, nums);
    }
}
