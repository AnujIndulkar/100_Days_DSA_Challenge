package Arrays;

import java.util.Scanner;
import java.util.HashSet;

public class Day02_ContainsDuplicateInArray {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        boolean ans = solution(arr);
//        System.out.println(ans);
        System.out.println(solution1(arr));
    }
    public static boolean solution(int [] arr){
        // Brute Force Approach - Sorting array by Insertion sort and then checking duplicate in Array.
        // Time Complexity - O(n^2)
        // Space Complexity - O(1)
        for(int i = 1; i < arr.length; i++){
            int current = arr[i];
            int j = i-1;
            while(j >= 0 && current < arr[j]){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = current;
        }
        // checking Duplicate
        for(int i =1; i < arr.length; i++){
            if(arr[i] == arr[i-1]) return true;
        }
        return false;
    }
    public static boolean solution1(int [] arr){
        // Optimal Approach - Using HashSet
        // Time Complexity - O(n)
        // Space Complexity - o(n)
        HashSet<Integer> set = new HashSet<>();
        for(int num : arr){
            if(set.contains(num)){
                return true;
            }
            set.add(num);
        }
        return false;
    }
}
