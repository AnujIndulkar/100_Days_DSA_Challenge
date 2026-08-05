package hashmap;

import java.util.Arrays;
import java.util.HashSet;

public class Day86_LongestConsecutiveSequence {

    // 1. BRUTE FORCE
    // Topic: Array, Sorting
    // Time Complexity: O(n log n)
    // Space Complexity: O(1)

    public int longestConsecutiveBrute(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int longest = 1;
        int current = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            }

            if (nums[i] == nums[i - 1] + 1) {
                current++;
            } else {
                longest = Math.max(longest, current);
                current = 1;
            }
        }
        return Math.max(longest, current);
    }

    // 2. OPTIMAL (HASHSET)
    // Topic: HashSet
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public int longestConsecutiveOptimal(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;

        for (int num : set) {
            // Start of sequence
            if (!set.contains(num - 1)) {
                int current = num;
                int length = 1;
                while (set.contains(current + 1)) {
                    current++;
                    length++;
                }

                longest = Math.max(longest, length);
            }
        }

        return longest;
    }


    public static void main(String[] args) {

        Day86_LongestConsecutiveSequence obj = new Day86_LongestConsecutiveSequence();

        int[] nums = {100, 4, 200, 1, 3, 2};

        System.out.println("Brute Force : " + obj.longestConsecutiveBrute(nums));
        System.out.println("Optimal : " + obj.longestConsecutiveOptimal(nums));
    }
}
