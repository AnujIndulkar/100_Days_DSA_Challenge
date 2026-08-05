package Arrays;

import java.util.Scanner;

public class Day13_SortColors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }
        // bruteForce(nums);
        // better(nums);
        optimal(nums);

        for(int num : nums){
            System.out.print(num + " ");
        }
    }

    // Brute Force Approach (Using Sorting)
    // Time Complexity: O(n log n)
    // Space Complexity: O(1)
    public static void bruteForce(int[] nums){
        for(int i = 0; i < nums.length - 1; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] > nums[j]){
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }

    }

    // Better Approach (Counting 0s, 1s, 2s)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static void better(int[] nums){
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        for(int num : nums){
            if(num == 0) count0++;
            else if(num == 1) count1++;
            else count2++;
        }
        int index = 0;
        for(int i = 0; i < count0; i++){
            nums[index++] = 0;
        }
        for(int i = 0; i < count1; i++){
            nums[index++] = 1;
        }
        for(int i = 0; i < count2; i++){
            nums[index++] = 2;
        }
    }

    // Optimal Approach (Dutch National Flag Algorithm)
    // Time Complexity: O(n)
    // Space Complexity : O(1)
    public static void optimal(int[] nums){
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;
        while(mid <= high){
            if(nums[mid] == 0){
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            }
            else if(nums[mid] == 1){
                mid++;
            }
            else{
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }
}
