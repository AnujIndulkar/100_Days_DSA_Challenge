package Arrays;

import java.util.Scanner;

public class Day16_ContainerWithMostWater {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] height = new int[n];
        for(int i = 0; i < n; i++){
            height[i] = sc.nextInt();
        }
//        System.out.println(bruteForce(height));
        System.out.println(optimal(height));
    }

/*
Brute Force Approach
Time Complexity- O(n^2)
Space Complexity -O(1)
 */
    public static int bruteForce(int[] height){
        int maxWater = 0;
        for(int i = 0; i < height.length; i++){
            for(int j = i + 1; j < height.length; j++){
                int h = Math.min(height[i], height[j]);
                int w = j - i;
                int water = h * w;
                maxWater = Math.max(maxWater, water);
            }
        }
        return maxWater;
    }

    /*
    Optimal Approach - Two Pointer
    Time Complexity- O(n)
    Space Complexity -O(1)
     */
    public static int optimal(int[] height){
        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;
        while(left < right){
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            int water = h * w;
            maxWater = Math.max(maxWater, water);
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }
        return maxWater;
    }
}
