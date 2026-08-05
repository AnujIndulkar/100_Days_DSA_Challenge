package recursion;

import java.util.*;

public class Day67_Permutations {

    // 1. BRUTE FORCE (BACKTRACKING + VISITED ARRAY)
    // Topic: Recursion, Backtracking
    // Time Complexity: O(n! * n)
    // Space Complexity: O(n)

    public List<List<Integer>> permuteBrute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        boolean[] visited = new boolean[nums.length];

        generate(nums, visited, new ArrayList<>(), result);

        return result;
    }

    private void generate(int[] nums, boolean[] visited, List<Integer> current, List<List<Integer>> result) {

        if (current.size() == nums.length) {

            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            current.add(nums[i]);

            generate(nums, visited, current, result);

            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }

    // 2. OPTIMAL (IN-PLACE SWAPPING)
    // Topic: Recursion, Backtracking
    // Time Complexity: O(n! * n)
    // Space Complexity: O(n)

    public List<List<Integer>> permuteOptimal(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        backtrack(0, nums, result);

        return result;
    }

    private void backtrack(int index, int[] nums, List<List<Integer>> result) {

        if (index == nums.length) {

            List<Integer> permutation = new ArrayList<>();

            for (int num : nums) {
                permutation.add(num);
            }

            result.add(permutation);
            return;
        }

        for (int i = index; i < nums.length; i++) {

            swap(nums, index, i);

            backtrack(index + 1, nums, result);

            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {

        Day67_Permutations obj = new Day67_Permutations();

        int[] nums = {1, 2, 3};

        System.out.println("Brute Force: " + obj.permuteBrute(nums));

        System.out.println("Optimal: " + obj.permuteOptimal(nums));
    }
}
