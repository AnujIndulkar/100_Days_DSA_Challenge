package Arrays;

import java.util.HashSet;
import java.util.Scanner;

public class Day05_MissingNumberInArray {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int ans = solution(arr);
//        System.out.println(ans);
        int ans1 = solution1(arr);
//        System.out.println(ans1);
        int ans2 = solution2(arr);
        System.out.println(ans2);
    }
        public static int solution(int [] arr){
            // Brute Force Approach
            // Time Complexity - O(n^2)
            // Space Complexity - O(1)
            for(int num = 0; num <= arr.length; num++){
                boolean found = false;
                for (int j : arr) {
                    if (j == num) {
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    return num;
                }
            }
            return -1;
        }
    public static int solution1(int[] arr) {
        // Better Approach - Using HashSet
        // Time Complexity - O(n)
        // Space Complexity - O(n)
        int n = arr.length;
        HashSet<Integer> set = new HashSet<>();
        // Step 1: Store all elements in set
        for (int j : arr) {
            set.add(j);
        }
        // Step 2: Check from 0 to n
        for (int num = 0; num <= n; num++) {
            if (!set.contains(num)) {
                return num;
            }
        }
        return -1;
    }
    public static int solution2(int[] arr) {
          // Optimal Approach - Using Math Formula
          // Time Complexity - O(n)
          // Space Complexity - O(1)
        int n = arr.length;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int j : arr) {
            actualSum += j;
        }
        return expectedSum - actualSum;
    }
}
