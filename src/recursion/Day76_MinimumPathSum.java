package recursion;

public class Day76_MinimumPathSum {

    // 1. BRUTE FORCE (RECURSION)
    // Topic: Recursion
    // Time Complexity: O(2^(m+n))
    // Space Complexity: O(m+n)

    public int minPathSumBrute(int[][] grid) {
        return findMinPath(0, 0, grid);
    }

    private int findMinPath(int row, int col, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // Reached destination
        if (row == m - 1 && col == n - 1) {
            return grid[row][col];
        }
        // Out of bounds
        if (row >= m || col >= n) {
            return Integer.MAX_VALUE;
        }
        // Move Down
        int down = findMinPath(row + 1, col, grid);
        // Move Right
        int right = findMinPath(row, col + 1, grid);
        return grid[row][col] + Math.min(down, right);
    }


    // 2. OPTIMAL (DP + MEMOIZATION)
    // Topic: Dynamic Programming, Recursion, Memoization
    // Time Complexity: O(m * n)
    // Space Complexity: O(m * n)

    public int minPathSumOptimal(int[][] grid) {
        Integer[][] dp = new Integer[grid.length][grid[0].length];
        return solve(0, 0, grid, dp);
    }

    private int solve(int row, int col, int[][] grid, Integer[][] dp) {
        int m = grid.length;
        int n = grid[0].length;
        // Reached destination
        if (row == m - 1 && col == n - 1) {
            return grid[row][col];
        }
        // Out of bounds
        if (row >= m || col >= n) {
            return Integer.MAX_VALUE;
        }
        // Already computed
        if (dp[row][col] != null) {
            return dp[row][col];
        }
        // Move Down
        int down = solve(row + 1, col, grid, dp);
        // Move Right
        int right = solve(row, col + 1, grid, dp);
        dp[row][col] = grid[row][col] + Math.min(down, right);
        return dp[row][col];
    }

    public static void main(String[] args) {
        Day76_MinimumPathSum obj = new Day76_MinimumPathSum();
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println("Brute Force: " + obj.minPathSumBrute(grid));
        System.out.println("Optimal: " + obj.minPathSumOptimal(grid));
    }
}
