package patterns;

import java.util.*;

public class Day40_PascalsTriangleII {

    // TOPIC: Pascal's Triangle II
    // Return only a specific row (0-indexed)

    // BRUTE FORCE APPROACH (Using nCr)
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
    public static List<Integer> bruteForce(int rowIndex) {

        List<Integer> row = new ArrayList<>();

        for (int j = 0; j <= rowIndex; j++) {
            row.add(nCr(rowIndex, j));
        }

        return row;
    }

    // Helper function to calculate nCr
    public static int nCr(int n, int r) {
        int res = 1;

        for (int i = 0; i < r; i++) {
            res = res * (n - i);
            res = res / (i + 1);
        }

        return res;
    }

    // OPTIMAL APPROACH (Using previous value)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static List<Integer> optimal(int rowIndex) {

        List<Integer> row = new ArrayList<>();

        int val = 1;
        row.add(val);  // first element always 1

        for (int i = 1; i <= rowIndex; i++) {
            val = val * (rowIndex - i + 1) / i;
            row.add(val);
        }

        return row;
    }

    // PRINT FUNCTION
    public static void print(List<Integer> row) {
        System.out.println(row);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // input
        System.out.print("Enter row index: ");
        int rowIndex = sc.nextInt();

        // List<Integer> result = bruteForce(rowIndex);
        List<Integer> result = optimal(rowIndex);

        System.out.println("Pascal's Triangle Row:");
        print(result);

        sc.close();
    }
}
