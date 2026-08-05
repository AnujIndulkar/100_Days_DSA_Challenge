package Arrays;
import java.util.Arrays;
import java.util.Scanner;

public class Day09_ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
//        System.out.println(Arrays.toString(solution(nums)));
        System.out.println(Arrays.toString(solution1(nums)));
    }
    public static int[] solution(int[] nums) {
        // Brute Force Approach
        // Time Complexity - O(n^2)
        // Space Complexity - O(1)
        int n = nums.length;
        int[] result = new int[n];
        for(int i = 0; i < n; i++){
            int product = 1;
            for(int j = 0; j < n; j++){
                if(i != j){
                    product = product * nums[j];
                }
            }
            result[i] = product;
        }
        return result;
    }
    public static int[] solution1(int[] nums) {
        // Optimal Approach - (prefix - suffix)
        // Time Complexity - O(n)
        // Space Complexity - O(1)
        int n = nums.length;
        int[] result = new int[n];
        result[0] = 1;
        // prefix product
        for(int i = 1; i < n; i++){
            result[i] = result[i-1] * nums[i-1];
        }
        int suffix = 1;
        //suffix product
        for(int i = n-1; i >= 0; i--){
            result[i] = result[i] * suffix;
            suffix = suffix * nums[i];
        }
        return result;
    }
}
