package dynamicProgramming;

public class Day74_UniquePaths {

    // 1. BRUTE FORCE (RECURSION)
    // Topic: Recursion
    // Time Complexity: O(2^(m+n))
    // Space Complexity: O(m+n)

    public int uniquePathsBrute(int m, int n) {
        return countPaths(0, 0, m, n);
    }

    private int countPaths(int row, int col, int m, int n) {
        // Reached destination
        if (row == m - 1 && col == n - 1) {
            return 1;
        }
        // Out of bounds
        if (row >= m || col >= n) {
            return 0;
        }
        // Move Down
        int down = countPaths(row + 1, col, m, n);
        // Move Right
        int right = countPaths(row, col + 1, m, n);
        return down + right;
    }

    // 2. OPTIMAL (DP + MEMOIZATION)
    // Topic: Dynamic Programming, Recursion, Memoization
    // Time Complexity: O(m * n)
    // Space Complexity: O(m * n)

    public int uniquePathsOptimal(int m, int n) {
        Integer[][] dp = new Integer[m][n];
        return solve(0, 0, m, n, dp);
    }

    private int solve(int row, int col, int m, int n, Integer[][] dp) {
        // Reached destination
        if (row == m - 1 && col == n - 1) {
            return 1;
        }
        // Out of bounds
        if (row >= m || col >= n) {
            return 0;
        }
        // Already computed
        if (dp[row][col] != null) {
            return dp[row][col];
        }
        // Move Down
        int down = solve(row + 1, col, m, n, dp);
        // Move Right
        int right = solve(row, col + 1, m, n, dp);
        dp[row][col] = down + right;
        return dp[row][col];
    }

    public static void main(String[] args) {
        Day74_UniquePaths obj = new Day74_UniquePaths();
        int m = 3;
        int n = 7;
        System.out.println("Brute Force: " + obj.uniquePathsBrute(m, n));
        System.out.println("Optimal: " + obj.uniquePathsOptimal(m, n));
    }
}
