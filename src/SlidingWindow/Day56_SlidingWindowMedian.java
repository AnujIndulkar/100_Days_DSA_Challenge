package SlidingWindow;

import java.util.*;

public class Day56_SlidingWindowMedian {

    // 1. BRUTE FORCE
    // Time Complexity: O(n * k log k)
    // Space Complexity: O(k)

    public double[] medianSlidingWindowBrute(int[] nums, int k) {

        int n = nums.length;
        double[] result = new double[n - k + 1];

        for (int i = 0; i <= n - k; i++) {

            int[] window = new int[k];

            for (int j = 0; j < k; j++) {
                window[j] = nums[i + j];
            }

            Arrays.sort(window);

            if (k % 2 == 1) {
                result[i] = window[k / 2];
            } else {
                result[i] = ((double) window[k / 2]
                        + window[(k / 2) - 1]) / 2.0;
            }
        }

        return result;
    }

    // 2. OPTIMAL (TWO HEAPS)
    // Time Complexity: O(n log k)
    // Space Complexity: O(k)

    public double[] medianSlidingWindowOptimal(int[] nums, int k) {

        int n = nums.length;
        double[] result = new double[n - k + 1];

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        PriorityQueue<Integer> minHeap =
                new PriorityQueue<>();

        for (int i = 0; i < n; i++) {

            // Add element
            if (maxHeap.isEmpty() || nums[i] <= maxHeap.peek()) {
                maxHeap.offer(nums[i]);
            } else {
                minHeap.offer(nums[i]);
            }

            balanceHeaps(maxHeap, minHeap);

            // Remove element outside window
            if (i >= k) {

                int remove = nums[i - k];

                if (remove <= maxHeap.peek()) {
                    maxHeap.remove(remove);
                } else {
                    minHeap.remove(remove);
                }

                balanceHeaps(maxHeap, minHeap);
            }

            // Store median
            if (i >= k - 1) {

                if (k % 2 == 1) {
                    result[i - k + 1] = maxHeap.peek();
                } else {
                    result[i - k + 1] =
                            ((double) maxHeap.peek() + minHeap.peek()) / 2.0;
                }
            }
        }

        return result;
    }

    private void balanceHeaps(PriorityQueue<Integer> maxHeap,
                              PriorityQueue<Integer> minHeap) {

        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }

        else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public static void main(String[] args) {

        Day56_SlidingWindowMedian obj =
                new Day56_SlidingWindowMedian();

        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        System.out.println("Brute Force: " +
                Arrays.toString(obj.medianSlidingWindowBrute(nums, k)));

        System.out.println("Optimal: " +
                Arrays.toString(obj.medianSlidingWindowOptimal(nums, k)));
    }
}
