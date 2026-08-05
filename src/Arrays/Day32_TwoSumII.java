package Arrays;

import java.util.*;

public class Day32_TwoSumII {

    // TOPIC: Two Sum II (Sorted Array)

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public static int[] bruteForce(int[] numbers, int target) {

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1}; // 1-based index
                }
            }
        }

        return new int[]{-1, -1};
    }

    // OPTIMAL APPROACH (Two Pointers)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static int[] optimal(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1}; // 1-based index
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // input array
        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] numbers = new int[n];

        System.out.print("Enter sorted array elements: ");
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        System.out.print("Enter target: ");
        int target = sc.nextInt();

        // int[] res = bruteForce(numbers, target); // brute force
        int[] res = optimal(numbers, target); // optimal

        System.out.println("Indices (1-based): " + res[0] + " " + res[1]);
    }
}
