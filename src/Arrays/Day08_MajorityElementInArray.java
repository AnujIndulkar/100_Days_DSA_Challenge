package Arrays;

import java.util.HashMap;
import java.util.Scanner;

public class Day08_MajorityElementInArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
//        System.out.println(solution(nums));
//        System.out.println(solution1(nums));
        System.out.println(solution2(nums));
    }

    public static int solution(int[] nums) {
        // Brute Force Approach
        // Time Complexity - O(n^2)
        // Space Complexity - O(1)
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
            if (count > n / 2) {
                return nums[i];
            }
        }
        return -1;
    }
    public static int solution1(int[] nums) {
        // Better Approach - HashMap
        // Time Complexity - O(n)
        // Space Complexity - O(n)
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num,0) + 1);
            if (map.get(num) > n / 2) {
                return num;
            }
        }
        return -1;
    }
    public static int solution2(int[] nums) {
        // Optimal Approach - Boyer–Moore Voting Algorithm
        // Time Complexity - O(n)
        // Space Complexity - O(1)
        int count = 0;
        int candidate = 0;
        for(int num : nums) {
            if(count == 0) {
                candidate = num;
            }
            if(num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }
}

