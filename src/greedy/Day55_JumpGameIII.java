package greedy;

public class Day55_JumpGameIII {

    // 1. BRUTE FORCE
    // Time Complexity: Exponential
    // Space Complexity: O(n)

    public boolean canJumpBrute(int[] nums) {
        return helper(nums, 0);
    }

    private boolean helper(int[] nums, int index) {

        if (index >= nums.length - 1) return true;

        for (int step = 1; step <= nums[index]; step++) {

            if (helper(nums, index + step)) {
                return true;
            }
        }

        return false;
    }

    // 2. GREEDY (OPTIMAL)
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    public boolean canJumpGreedy(int[] nums) {

        int goal = nums.length - 1;

        for (int i = nums.length - 2; i >= 0; i--) {

            if (i + nums[i] >= goal) {
                goal = i;
            }
        }

        return goal == 0;
    }

    public static void main(String[] args) {

        Day55_JumpGameIII obj = new Day55_JumpGameIII();

        int[] nums = {2, 3, 1, 1, 4};

        System.out.println("Brute Force: " + obj.canJumpBrute(nums));
        System.out.println("Greedy: " + obj.canJumpGreedy(nums));
    }
}