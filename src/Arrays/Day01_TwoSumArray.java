package Arrays;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day01_TwoSumArray {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        int [] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }
        int[] a = solution(arr,k);
        int[] b = solution1(arr,k);
//        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

    }
    public static int[] solution(int[] arr, int k){
        //Brute Force Approach
        // Time Complexity - O(n^2)
        // Space Complexity - O(1)
        // This solution is better for small input.
       for(int i = 0; i < arr.length; i++){
           for(int j = i+1; j<arr.length; j++){
               int add =arr[i] + arr[j];
               if(add == k){
                   return new int []{i,j};
               }
           }
       }
       return new int[]{};
    }
    public static int[] solution1(int[] arr,int k){
        //Optimal Approach by using HashMap
        // Time Complexity - O(n)
        // Space Complexity - O(n)
        // this Solution is better for large input.
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0; i < arr.length; i++){
            int need = k - arr[i];
            if(map.containsKey(need)){
                return new int[]{map.get(need),i};
            }
            map.put(arr[i],i);
        }
        return new int[]{-1,-1};
    }
}
