package Arrays;

import java.util.Scanner;

public class Day17_JumpGame {

/*
Brute Force Approach (Recursion)
Time Complexity - O(2^n)
Space Complexity - O(n)  (recursion stack)
 */
    public static boolean canJump(int[] nums, int index) {
        if(index >= nums.length - 1) {
            return true;
        }
        int maxJump = nums[index];
        for(int step = 1; step <= maxJump; step++) {
            if(canJump(nums, index + step)) {
                return true;
            }
        }
        return false;
    }

/*
Optimal Approach (Greedy)
Time Complexity - O(n)
Space Complexity - O(1)
 */
    public static boolean canJump1(int[] nums) {
        int maxReach = 0;
        for(int i = 0; i < nums.length; i++) {
            if(i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(canJump(nums,0));
        System.out.println(canJump1(nums));
    }
}
