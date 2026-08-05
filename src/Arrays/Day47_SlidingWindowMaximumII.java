package Arrays;

import java.util.*;

public class Day47_SlidingWindowMaximumII {

    // TOPIC: Sliding Window Maximum

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n * k)
    // Space Complexity: O(1)
    public static int[] bruteForce(int[] nums, int k) {

        int n = nums.length;
        int[] result = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {

            int max = nums[i];

            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }

            result[i] = max;
        }

        return result;
    }

    // OPTIMAL APPROACH (Deque)
    // Time Complexity: O(n)
    // Space Complexity: O(k)
    public static int[] optimal(int[] nums, int k) {

        int n = nums.length;
        int[] result = new int[n - k + 1];

        Deque<Integer> dq = new ArrayDeque<>();

        int idx = 0;

        for (int i = 0; i < n; i++) {

            // remove out of window indices
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // remove smaller elements
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }

            dq.offerLast(i);

            // store result
            if (i >= k - 1) {
                result[idx++] = nums[dq.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.print("Enter window size k: ");
        int k = sc.nextInt();

        // int[] res = bruteForce(nums, k);
        int[] res = optimal(nums, k);

        System.out.println("Sliding Window Maximum: " + Arrays.toString(res));

        sc.close();
    }
}
