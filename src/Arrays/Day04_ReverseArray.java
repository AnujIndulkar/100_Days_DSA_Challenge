package Arrays;

import java.util.Scanner;

public class Day04_ReverseArray {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int [] ans= solution(arr);
//        for (int an : ans) {
//            System.out.print(an + " ");
//        }
        int [] ans1= solution1(arr);
        for (int an1 : ans1) {
            System.out.print(an1 +" ");
        }

    }
    public static int [] solution(int [] arr){
        // Brute Force Approach - Extra Array
        // Time Complexity - O(n)
        // Space Complexity - O(n)
        int n = arr.length;
        int [] temp = new int[n];
        for(int i = 0; i < n; i++) {
            temp[i] = arr[n - 1 - i];
        }
        return temp;
    }
    public static int [] solution1(int [] arr){
        // Optimal Approach - Using Two Pointer
        // Time Complexity - O(n)
        // Space Complexity - O(1)
        int start = 0;
        int end = arr.length-1;
        while(start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
        return arr;
    }
}
