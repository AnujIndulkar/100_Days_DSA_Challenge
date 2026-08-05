package Arrays;

import java.util.*;

public class Day61_LongestConsecutiveSequence {

    // 1. BRUTE FORCE
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)

    public int longestConsecutiveBrute(int[] nums) {

        int longest = 0;

        for (int num : nums) {

            int currentNum = num;
            int currentStreak = 1;

            while (contains(nums, currentNum + 1)) {

                currentNum++;
                currentStreak++;
            }

            longest = Math.max(longest, currentStreak);
        }

        return longest;
    }

    private boolean contains(int[] nums, int target) {

        for (int num : nums) {

            if (num == target) {
                return true;
            }
        }

        return false;
    }

    // 2. OPTIMAL (HASHSET)
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public int longestConsecutiveOptimal(int[] nums) {

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;

        for (int num : set) {

            // Start of sequence
            if (!set.contains(num - 1)) {

                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {

                    currentNum++;
                    currentStreak++;
                }

                longest = Math.max(longest, currentStreak);
            }
        }

        return longest;
    }

    public static void main(String[] args) {

        Day61_LongestConsecutiveSequence obj =
                new Day61_LongestConsecutiveSequence();

        int[] nums = {100, 4, 200, 1, 3, 2};

        System.out.println("Brute Force: " +
                obj.longestConsecutiveBrute(nums));

        System.out.println("Optimal: " +
                obj.longestConsecutiveOptimal(nums));
    }
}
