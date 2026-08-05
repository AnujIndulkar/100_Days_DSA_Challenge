package matrix;
import java.util.*;

public class Day64_RotateMatrix {

    // 1. BRUTE FORCE
    // Time Complexity: O(n²)
    // Space Complexity: O(n²)

    static void rotateMatrixBrute(int[][] matrix) {

        int n = matrix.length;

        int[][] rotated = new int[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                rotated[j][n - 1 - i] =
                        matrix[i][j];
            }
        }

        printMatrix(rotated);
    }

    // 2. OPTIMAL (Transpose and Reverse)
    // Time Complexity: O(n²)
    // Space Complexity: O(1)

    static void rotateMatrixOptimal(int[][] matrix) {

        int n = matrix.length;

        // Transpose

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                int temp = matrix[i][j];

                matrix[i][j] =
                        matrix[j][i];

                matrix[j][i] =
                        temp;
            }
        }

        // Reverse each row

        for (int i = 0; i < n; i++) {

            int left = 0;
            int right = n - 1;

            while (left < right) {

                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;

                left++;
                right--;
            }
        }

        printMatrix(matrix);
    }

    static void printMatrix(int[][] matrix) {

        int n = matrix.length;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                System.out.print(
                        matrix[i][j] + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                matrix[i][j] =
                        sc.nextInt();
            }
        }

        // Copy matrix for brute force

        int[][] copy = new int[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                copy[i][j] = matrix[i][j];
            }
        }

        System.out.println("Brute Force:");
        rotateMatrixBrute(copy);
        System.out.println();

        System.out.println("Optimal:");

        rotateMatrixOptimal(matrix);
    }
}
