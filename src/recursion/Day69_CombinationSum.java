package recursion;

import java.util.*;

public class Day69_CombinationSum {

    // 1. BRUTE FORCE (INCLUDE / EXCLUDE RECURSION)
    // Topic: Recursion, Backtracking
    // Time Complexity: O(2^target)
    // Space Complexity: O(target)

    public List<List<Integer>> combinationSumBrute(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();

        generate(0, candidates, target, new ArrayList<>(), result);

        return result;
    }

    private void generate(int index, int[] candidates, int target, List<Integer> current, List<List<Integer>> result) {

        if (target == 0) {

            result.add(new ArrayList<>(current));
            return;
        }

        if (index == candidates.length || target < 0) {
            return;
        }

        // Take current element
        current.add(candidates[index]);

        generate(index, candidates, target - candidates[index], current, result);

        current.remove(current.size() - 1);

        // Skip current element
        generate(index + 1, candidates, target, current, result);
    }

    // 2. OPTIMAL (BACKTRACKING)
    // Topic: Recursion, Backtracking
    // Time Complexity: O(N^(target / minElement))
    // Space Complexity: O(target)

    public List<List<Integer>> combinationSumOptimal(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();

        backtrack(0, candidates, target, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int start, int[] candidates, int target, List<Integer> current, List<List<Integer>> result) {

        if (target == 0) {

            result.add(new ArrayList<>(current));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            current.add(candidates[i]);

            // Reuse same element
            backtrack(i, candidates, target - candidates[i], current, result);

            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {

        Day69_CombinationSum obj = new Day69_CombinationSum();

        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        System.out.println("Brute Force: " + obj.combinationSumBrute(candidates, target));

        System.out.println("Optimal: " + obj.combinationSumOptimal(candidates, target));
    }
}
