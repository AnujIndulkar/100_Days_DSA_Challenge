package recursion;

import java.util.*;

public class Day68_PermutationsII {

    // 1. BRUTE FORCE (BACKTRACKING + HASHSET)
    // Topic: Recursion, Backtracking, HashSet
    // Time Complexity: O(n! * n)
    // Space Complexity: O(n! * n)

    public List<List<Integer>> permuteUniqueBrute(int[] nums) {

        Set<List<Integer>> set = new HashSet<>();

        boolean[] visited = new boolean[nums.length];

        generate(nums, visited, new ArrayList<>(), set);

        return new ArrayList<>(set);
    }

    private void generate(int[] nums, boolean[] visited, List<Integer> current, Set<List<Integer>> set) {

        if (current.size() == nums.length) {

            set.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            current.add(nums[i]);

            generate(nums, visited, current, set);

            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }

    // 2. OPTIMAL (SORTING + DUPLICATE SKIPPING)
    // Topic: Recursion, Backtracking, Sorting
    // Time Complexity: O(n! * n)
    // Space Complexity: O(n)

    public List<List<Integer>> permuteUniqueOptimal(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        boolean[] visited = new boolean[nums.length];

        backtrack(nums, visited, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int[] nums, boolean[] visited, List<Integer> current, List<List<Integer>> result) {

        if (current.size() == nums.length) {

            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (visited[i]) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }

            visited[i] = true;
            current.add(nums[i]);

            backtrack(nums, visited, current, result);

            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {

        Day68_PermutationsII obj = new Day68_PermutationsII();

        int[] nums = {1, 1, 2};

        System.out.println("Brute Force: " + obj.permuteUniqueBrute(nums));

        System.out.println("Optimal: " + obj.permuteUniqueOptimal(nums));
    }
}
