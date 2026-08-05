package Arrays;
import java.util.HashMap;
import java.util.Scanner;
public class Day10_SubarraySumEqualsToK {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }
        int k = sc.nextInt();
//        System.out.println(bruteForce(nums, k));
        System.out.println(optimal(nums, k));
    }

    // Brute Force Approach
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public static int bruteForce(int[] nums, int k){
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            for(int j = i; j < nums.length; j++){
                sum += nums[j];
                if(sum == k){
                    count++;
                }
            }
        }
        return count;
    }

    // Optimal Approach (Prefix Sum + HashMap)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int optimal(int[] nums, int k){
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int prefixSum = 0;
        int count = 0;
        for(int num : nums){
            prefixSum += num;
            if(map.containsKey(prefixSum - k)){
                count += map.get(prefixSum - k);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum,0) + 1);
        }
        return count;
    }
}
