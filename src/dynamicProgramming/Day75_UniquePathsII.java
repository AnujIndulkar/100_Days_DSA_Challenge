package dynamicProgramming;

public class Day75_UniquePathsII {

    // 1. BRUTE FORCE (RECURSION)
    // Topic: Recursion
    // Time Complexity: O(2^(m*n))
    // Space Complexity: O(m+n)

    public int uniquePathsBrute(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        return countPaths(0, 0, m, n, obstacleGrid);
    }

    private int countPaths(int row, int col, int m, int n, int[][] grid) {
        // Out of bounds
        if (row >= m || col >= n) {
            return 0;
        }

        // Obstacle found
        if (grid[row][col] == 1) {
            return 0;
        }

        // Reached destination
        if (row == m - 1 && col == n - 1) {
            return 1;
        }

        // Move Down
        int down = countPaths(row + 1, col, m, n, grid);

        // Move Right
        int right = countPaths(row, col + 1, m, n, grid);

        return down + right;
    }

    // 2. OPTIMAL (DP + MEMOIZATION)
    // Topic: Dynamic Programming, Recursion, Memoization
    // Time Complexity: O(m * n)
    // Space Complexity: O(m * n)

    public int uniquePathsOptimal(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        Integer[][] dp = new Integer[m][n];

        return solve(0, 0, m, n, obstacleGrid, dp);
    }

    private int solve(int row, int col, int m, int n, int[][] grid, Integer[][] dp) {
        // Out of bounds
        if (row >= m || col >= n) {
            return 0;
        }

        // Obstacle found
        if (grid[row][col] == 1) {
            return 0;
        }

        // Reached destination
        if (row == m - 1 && col == n - 1) {
            return 1;
        }

        // Already computed
        if (dp[row][col] != null) {
            return dp[row][col];
        }

        int down = solve(row + 1, col, m, n, grid, dp);
        int right = solve(row, col + 1, m, n, grid, dp);

        dp[row][col] = down + right;
        return dp[row][col];
    }

    public static void main(String[] args) {
        Day75_UniquePathsII obj = new Day75_UniquePathsII();

        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };

        System.out.println("Brute Force: " + obj.uniquePathsBrute(obstacleGrid));
        System.out.println("Optimal: " + obj.uniquePathsOptimal(obstacleGrid));
    }
}
