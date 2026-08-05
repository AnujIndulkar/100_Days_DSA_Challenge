package queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class Day83_SlidingWindowMaximum {

    // 1. BRUTE FORCE
    // Topic: Array
    // Time Complexity: O(n * k)
    // Space Complexity: O(1)

    public int[] maxSlidingWindowBrute(int[] nums, int k) {

        int n = nums.length;
        int[] result = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            int max = nums[i];
            // Find maximum in current window
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }

        return result;
    }

    // 2. OPTIMAL (MONOTONIC DEQUE)
    // Topic: Queue, Sliding Window
    // Time Complexity: O(n)
    // Space Complexity: O(k)

    public int[] maxSlidingWindowOptimal(int[] nums, int k) {

        int n = nums.length;
        int[] result = new int[n - k + 1];

        Deque<Integer> deque = new ArrayDeque<>();

        int index = 0;

        for (int i = 0; i < n; i++) {
            // Remove indices outside window
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // Remove smaller elements
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }

            // Add current index
            deque.offerLast(i);
            // Store maximum
            if (i >= k - 1) {
                result[index++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }


    public static void main(String[] args) {

        Day83_SlidingWindowMaximum obj = new Day83_SlidingWindowMaximum();

        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.print("Brute Force : ");
        int[] brute = obj.maxSlidingWindowBrute(nums, k);

        for (int value : brute) {
            System.out.print(value + " ");
        }

        System.out.println();
        System.out.print("Optimal : ");

        int[] optimal = obj.maxSlidingWindowOptimal(nums, k);

        for (int value : optimal) {
            System.out.print(value + " ");
        }
    }
}
