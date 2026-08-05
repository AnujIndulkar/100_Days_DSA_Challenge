package twopointer;

import java.util.*;

public class Day21_FourSum {

    // BRUTE FORCE APPROACH
    // Idea: Generate all possible quadruplets using 4 loops
    // Check if their sum equals target
    // Use Set to avoid duplicates
    // Time Complexity: O(n^4)
    // Space Complexity: O(no. of unique quadruplets)

    public static List<List<Integer>> bruteForce(int[] nums, int target) {
        int n = nums.length;
        Set<List<Integer>> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                for(int k = j + 1; k < n; k++) {
                    for(int l = k + 1; l < n; l++) {
                        if(nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            Collections.sort(temp); // avoid duplicates
                            set.add(temp);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }

    // OPTIMAL APPROACH
    // Idea:
    // 1. Sort the array
    // 2. Fix first two elements (i, j)
    // 3. Use two pointer (left, right) for remaining two
    // Time Complexity: O(n^3)
    // Space Complexity: O(1) (excluding result)

    public static List<List<Integer>> optimal(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for(int i = 0; i < n; i++) {
            // skip duplicates for i
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            for(int j = i + 1; j < n; j++) {
                // skip duplicates for j
                if(j > i + 1 && nums[j] == nums[j - 1]) continue;
                int left = j + 1;
                int right = n - 1;
                while(left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                        // skip duplicates
                        while(left < right && nums[left] == nums[left - 1]) left++;
                        while(left < right && nums[right] == nums[right + 1]) right--;
                    }
                    else if(sum < target) {
                        left++;
                    }
                    else {
                        right--;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of array: ");
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.println("Enter array elements: ");
        for(int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.print("Enter target: ");
        int target = sc.nextInt();
//        System.out.println("Brute Force Answer: " + bruteForce(nums, target));
        System.out.println("Optimal Answer: " + optimal(nums, target));
    }
}




