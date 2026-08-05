package Arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Day12_MergeIntervals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of intervals
        int[][] intervals = new int[n][2];
        for(int i = 0; i < n; i++){
            intervals[i][0] = sc.nextInt(); // start
            intervals[i][1] = sc.nextInt(); // end
        }
//      int[][] ans = bruteForce(intervals);
        int[][] ans = optimal(intervals);
        for(int i = 0; i < ans.length; i++){
            System.out.println(ans[i][0] + " " + ans[i][1]);
        }
    }

    public static int[][] bruteForce(int[][] intervals){
        // Brute Force Approach
        // Time Complexity - O(n^2)
        // Space Complexity - O(n)
        int n = intervals.length;
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        int[][] temp = new int[n][2];
        int index = 0;
        for(int i = 0; i < n; i++){
            int start = intervals[i][0];
            int end = intervals[i][1];
            if(index == 0){
                temp[index][0] = start;
                temp[index][1] = end;
                index++;
            }else{
                int prevEnd = temp[index-1][1];
                if(start <= prevEnd){
                    temp[index-1][1] = Math.max(prevEnd, end);
                }else{
                    temp[index][0] = start;
                    temp[index][1] = end;
                    index++;
                }
            }
        }
        return Arrays.copyOf(temp, index);
    }

    public static int[][] optimal(int[][] intervals){
        // Optimal Approach
        // Time Complexity - O(n log n)
        // Space Complexity - O(n)
        int n = intervals.length;
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        int[][] result = new int[n][2];
        int index = 0;
        result[0] = intervals[0];
        for(int i = 1; i < n; i++){
            int[] last = result[index];
            int[] current = intervals[i];
            if(current[0] <= last[1]){
                last[1] = Math.max(last[1], current[1]);
            }else{
                index++;
                result[index] = current;
            }
        }
        return Arrays.copyOf(result, index + 1);
    }
}