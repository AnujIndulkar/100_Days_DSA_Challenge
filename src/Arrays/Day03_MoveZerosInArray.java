package Arrays;

import java.util.Scanner;

public class Day03_MoveZerosInArray {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] ans =solution(arr);
        int[] ans1 =solution1(arr);
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
    public static int[] solution(int[] arr) {
        //Brute Force Approach - making extra array.
        // Time Complexity-O(n)
        // Space Complexity-O(n)
        int[] temp = new int[arr.length];
        int j = 0;
        // store non-zero elements
        for (int k : arr) {
            if (k != 0) {
                temp[j] = k;
                j++;
            }
        }
        // copy back to orignal array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp[i];
        }
        return arr;
    }
    public static int[] solution1(int[] arr) {
        //Optimal Approach- Using Two Pointer
        // Time complexity-O(n)
        // Space complexity-O(1)
        int j = 0; // position for next non-zero
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
        return arr;
    }
}
