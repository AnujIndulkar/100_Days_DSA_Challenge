package Arrays;

import java.util.*;

public class Day15_SlidingWindowMaximum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        // int[] ans = bruteForce(nums, k);
        int[] ans = optimal(nums, k);
        for(int num : ans){
            System.out.print(num + " ");
        }
    }

    // Brute Force
    // Time Complexity: O(n * k)
    // Space Complexity: O(n - k + 1) = O(n)
    public static int[] bruteForce(int[] nums, int k){
        int n = nums.length;
        int[] result = new int[n - k + 1];
        for(int i = 0; i <= n - k; i++){
            int max = nums[i];
            for(int j = i; j < i + k; j++){
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        return result;
    }

    // Optimal (Sliding Window + Deque)
    // Time Complexity: O(n)
    // Each element is added and removed from deque at most once
    // Space Complexity: O(k)
    public static int[] optimal(int[] nums, int k){
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new LinkedList<>();
        int index = 0;
        for(int i = 0; i < n; i++){
            // remove element outside window
            if(!dq.isEmpty() && dq.peekFirst() == i - k){
                dq.pollFirst();
            }
            // remove smaller elements
            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]){
                dq.pollLast();
            }
            dq.offerLast(i);
            if(i >= k - 1){
                result[index++] = nums[dq.peekFirst()];
            }
        }
        return result;
    }
}