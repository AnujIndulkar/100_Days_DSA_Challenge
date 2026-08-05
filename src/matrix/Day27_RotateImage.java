package matrix;

import java.util.*;

public class Day27_RotateImage {

    // TOPIC: Rotate Image (90 Degree Clockwise)
    // BRUTE FORCE APPROACH (Using extra matrix)
    // Time Complexity: O(n * n)
    // Space Complexity: O(n * n)
    public static int[][] bruteForce(int[][] arr) {

        int n = arr.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[j][n - 1 - i] = arr[i][j];
            }
        }
        return res;
    }

    // OPTIMAL APPROACH (Transpose + Reverse)
    // Time Complexity: O(n * n)
    // Space Complexity: O(1)
    public static void optimal(int[][] arr) {
        int n = arr.length;
        // Step 1: Transpose matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = arr[i][left];
                arr[i][left] = arr[i][right];
                arr[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size (n x n): ");
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        System.out.println("Enter matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        // int[][] res = bruteForce(arr);
        optimal(arr);
        System.out.println("Rotated Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
