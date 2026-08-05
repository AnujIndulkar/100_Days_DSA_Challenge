package recursion;

import java.util.*;

public class Day66_SubsetsII {

    // 1. BRUTE FORCE (GENERATE ALL + HASHSET)
    // Topic: Recursion, HashSet
    // Time Complexity: O(n * 2^n)
    // Space Complexity: O(n * 2^n)

    public List<List<Integer>> subsetsWithDupBrute(int[] nums) {

        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet<>();

        generate(0, nums, new ArrayList<>(), set);

        return new ArrayList<>(set);
    }

    private void generate(int index, int[] nums, List<Integer> current, Set<List<Integer>> set) {

        if (index == nums.length) {

            set.add(new ArrayList<>(current));
            return;
        }

        // Include
        current.add(nums[index]);

        generate(index + 1, nums, current, set);

        current.remove(current.size() - 1);

        // Exclude
        generate(index + 1, nums, current, set);
    }

    // 2. OPTIMAL (BACKTRACKING + SKIP DUPLICATES)
    // Topic: Recursion, Backtracking
    // Time Complexity: O(n * 2^n)
    // Space Complexity: O(n)

    public List<List<Integer>> subsetsWithDupOptimal(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();

        backtrack(0, nums, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int index, int[] nums, List<Integer> current, List<List<Integer>> result) {

        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {

            // Skip duplicates
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }

            current.add(nums[i]);

            backtrack(i + 1, nums, current, result);

            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {

        Day66_SubsetsII obj = new Day66_SubsetsII();

        int[] nums = {1, 2, 2};

        System.out.println("Brute Force: " + obj.subsetsWithDupBrute(nums));

        System.out.println("Optimal: " + obj.subsetsWithDupOptimal(nums));
    }
}
