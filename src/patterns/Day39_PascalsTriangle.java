package patterns;

import java.util.*;

public class Day39_PascalsTriangle {

    // TOPIC: Pascal's Triangle

    // BRUTE FORCE APPROACH (Using nCr)
    // Time Complexity: O(n^3 approx)
    // Space Complexity: O(n^2)
    public static List<List<Integer>> bruteForce(int numRows) {

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j <= i; j++) {
                row.add(nCr(i, j));
            }

            ans.add(row);
        }

        return ans;
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

    // OPTIMAL APPROACH (Using previous row)
    // Time Complexity: O(n^2)
    // Space Complexity: O(n^2)
    public static List<List<Integer>> optimal(int numRows) {

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j <= i; j++) {

                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    int val = ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j);
                    row.add(val);
                }
            }

            ans.add(row);
        }

        return ans;
    }

    //  PRINT FUNCTION
    public static void print(List<List<Integer>> result) {
        for (List<Integer> row : result) {
            System.out.println(row);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // input
        System.out.print("Enter number of rows: ");
        int numRows = sc.nextInt();

        // List<List<Integer>> result = bruteForce(numRows);
        List<List<Integer>> result = optimal(numRows);

        System.out.println("Pascal's Triangle:");
        print(result);

        sc.close();
    }
}
