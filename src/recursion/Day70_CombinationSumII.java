package recursion;

import java.util.*;

public class Day70_CombinationSumII {

    // 1. BRUTE FORCE (INCLUDE / EXCLUDE + HASHSET)
    // Topic: Recursion, Backtracking, HashSet
    // Time Complexity: O(2^n * k)
    // Space Complexity: O(2^n * k)

    public List<List<Integer>> combinationSum2Brute(int[] candidates, int target) {

        Set<List<Integer>> set = new HashSet<>();

        Arrays.sort(candidates);

        generate(0, candidates, target, new ArrayList<>(), set);

        return new ArrayList<>(set);
    }

    private void generate(int index, int[] candidates, int target, List<Integer> current, Set<List<Integer>> set) {

        if (target == 0) {

            set.add(new ArrayList<>(current));
            return;
        }

        if (index == candidates.length || target < 0) {
            return;
        }

        // Take current element
        current.add(candidates[index]);

        generate(index + 1, candidates, target - candidates[index], current, set);

        current.remove(current.size() - 1);

        // Skip current element
        generate(index + 1, candidates, target, current, set);
    }

    // 2. OPTIMAL (BACKTRACKING + SORTING)
    // Topic: Recursion, Backtracking, Sorting
    // Time Complexity: O(2^n * k)
    // Space Complexity: O(k)

    public List<List<Integer>> combinationSum2Optimal(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(candidates);

        backtrack(0, candidates, target, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int start, int[] candidates, int target, List<Integer> current, List<List<Integer>> result) {

        if (target == 0) {

            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            // Skip duplicates
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (candidates[i] > target) {
                break;
            }

            current.add(candidates[i]);

            // Use each element only once
            backtrack(i + 1, candidates, target - candidates[i], current, result);

            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {

        Day70_CombinationSumII obj = new Day70_CombinationSumII();

        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        System.out.println("Brute Force: " + obj.combinationSum2Brute(candidates, target));

        System.out.println("Optimal: " + obj.combinationSum2Optimal(candidates, target));
    }
}
