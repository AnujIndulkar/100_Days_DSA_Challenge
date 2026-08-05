package Arrays;

import java.util.Scanner;

public class Day06_MaximumSubarray {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int ans = solution(arr);
//        System.out.println(ans);
        int ans1 = solution1(arr);
        System.out.println(ans1);
    }
    public static int solution(int [] arr){
        // Brute Force Approach
        // Time Complexity - O(n^2)
        // Space Complexity - O(1)
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            int sum = 0;
            for(int j = i; j < n; j++){
                sum += arr[j];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
    public static int solution1(int [] arr){
        // Optimal Approach - Kadane’s Algorithm
        // Time Complexity - O(n)
        // Space Complexity - O(1)
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            currentSum += arr[i];
            maxSum = Math.max(currentSum, maxSum);
            if(currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }
}
