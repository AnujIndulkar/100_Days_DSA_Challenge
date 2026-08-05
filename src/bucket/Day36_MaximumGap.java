package bucket;

import java.util.*;

public class Day36_MaximumGap {

    // TOPIC: Maximum Gap

    // BRUTE FORCE APPROACH (Sorting)
    // Time Complexity: O(n log n)
    // Space Complexity: O(1) (ignoring sorting space)
    public static int bruteForce(int[] nums) {

        int n = nums.length;
        if (n < 2) return 0;
        Arrays.sort(nums);
        int maxGap = 0;
        for (int i = 1; i < n; i++) {
            maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
        }

        return maxGap;
    }

    // OPTIMAL APPROACH (Bucket Sort / Pigeonhole Principle)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int optimal(int[] nums) {
        int n = nums.length;
        if (n < 2) return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // find min and max
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min == max) return 0;
        int bucketSize = Math.max(1, (max - min) / (n - 1));
        int bucketCount = (max - min) / bucketSize + 1;
        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        boolean[] used = new boolean[bucketCount];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);
        // place elements into buckets
        for (int num : nums) {
            int idx = (num - min) / bucketSize;
            bucketMin[idx] = Math.min(bucketMin[idx], num);
            bucketMax[idx] = Math.max(bucketMax[idx], num);
            used[idx] = true;
        }
        int prevMax = min;
        int maxGap = 0;
        // calculate max gap
        for (int i = 0; i < bucketCount; i++) {
            if (!used[i]) continue;
            maxGap = Math.max(maxGap, bucketMin[i] - prevMax);
            prevMax = bucketMax[i];
        }

        return maxGap;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // input
        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.print("Enter array elements: ");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // int res = bruteForce(nums);
        int res = optimal(nums);

        System.out.println("Maximum Gap: " + res);
    }
}
