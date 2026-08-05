package Arrays;

import java.util.*;

public class Day30_SubarraysWithKDifferentIntegers {

    // TOPIC: Subarrays with K Different Integers

    // BRUTE FORCE APPROACH (Generate all subarrays and count distinct)
    // Time Complexity: O(n^3)
    // Space Complexity: O(n)
    public static int bruteForce(int[] nums, int k) {
        int n = nums.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                Set<Integer> set = new HashSet<>();
                for (int x = i; x <= j; x++) {
                    set.add(nums[x]);
                }
                if (set.size() == k) count++;
            }
        }

        return count;
    }

    // OPTIMAL APPROACH (Sliding Window + HashMap)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int optimal(int[] nums, int k) {
        return atMostK(nums, k) - atMostK(nums, k - 1);
    }

    private static int atMostK(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);

            while (freq.size() > k) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                if (freq.get(nums[left]) == 0) {
                    freq.remove(nums[left]);
                }
                left++;
            }

            count += right - left + 1; // all subarrays ending at right
        }

        return count;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] nums = new int[n];

        System.out.print("Enter array elements: ");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        // int res = bruteForce(nums, k); // brute force
        int res = optimal(nums, k); // optimal

        System.out.println("Number of subarrays with " + k + " distinct integers: " + res);
    }
}
