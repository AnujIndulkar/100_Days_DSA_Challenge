package twopointer;

import java.util.*;

public class Day34_MoveZeroesII {

    // TOPIC: Move Zeroes II (Variation)

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int[] bruteForce(int[] nums) {
        List<Integer> list = new ArrayList<>();

        // store non-zero elements
        for (int num : nums) {
            if (num != 0) {
                list.add(num);
            }
        }

        // add zeros at the end
        int zeroCount = nums.length - list.size();
        while (zeroCount-- > 0) {
            list.add(0);
        }

        // convert list to array
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    // OPTIMAL APPROACH (Two Pointers)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static int[] optimal(int[] nums) {

        int j = 0; // position for non-zero

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // swap nums[i] and nums[j]
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
        return nums;
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

        // int[] res = bruteForce(nums); // brute force
        int[] res = optimal(nums); // optimal

        System.out.println("Array after moving zeroes:");
        for (int num : res) {
            System.out.print(num + " ");
        }
    }
}
