package recursion;

import java.util.*;

public class Day65_Subsets {

    // 1. BRUTE FORCE (BIT MANIPULATION)
    // Time Complexity: O(n * 2^n)
    // Space Complexity: O(1) (excluding output)

    public List<List<Integer>> subsetsBrute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        int n = nums.length;
        int totalSubsets = 1 << n;

        for (int mask = 0; mask < totalSubsets; mask++) {

            List<Integer> subset = new ArrayList<>();

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }

            result.add(subset);
        }

        return result;
    }

    // 2. OPTIMAL (BACKTRACKING)
    // Time Complexity: O(n * 2^n)
    // Space Complexity: O(n)

    public List<List<Integer>> subsetsOptimal(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        backtrack(0, nums, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int index, int[] nums, List<Integer> current, List<List<Integer>> result) {

        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {

            current.add(nums[i]);

            backtrack(i + 1, nums, current, result);

            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {

        Day65_Subsets obj = new Day65_Subsets();

        int[] nums = {1, 2, 3};

        System.out.println("Brute Force: " + obj.subsetsBrute(nums));

        System.out.println("Optimal: " + obj.subsetsOptimal(nums));
    }
}
