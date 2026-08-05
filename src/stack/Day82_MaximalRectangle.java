package stack;

import java.util.Stack;

public class Day82_MaximalRectangle {

    // 1. BRUTE FORCE
    // Topic: Matrix
    // Time Complexity: O((m*n)^2)
    // Space Complexity: O(n)

    public int maximalRectangleBrute(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] heights = new int[cols];
        int maxArea = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }

            // Brute Force Largest Rectangle
            for (int start = 0; start < cols; start++) {
                int minHeight = Integer.MAX_VALUE;
                for (int end = start; end < cols; end++) {
                    minHeight = Math.min(minHeight, heights[end]);
                    maxArea = Math.max(maxArea, minHeight * (end - start + 1));
                }
            }
        }

        return maxArea;
    }

    // 2. OPTIMAL (Histogram + Monotonic Stack)
    // Topic: Stack
    // Time Complexity: O(m*n)
    // Space Complexity: O(n)

    public int maximalRectangleOptimal(char[][] matrix) {

        if (matrix.length == 0) {
            return 0;
        }

        int cols = matrix[0].length;
        int[] heights = new int[cols];

        int maxArea = 0;

        for (char[] row : matrix) {
            for (int j = 0; j < cols; j++) {
                if (row[j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }

            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= heights.length; i++) {

            int currentHeight = (i == heights.length) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width;
                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }

                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {

        Day82_MaximalRectangle obj = new Day82_MaximalRectangle();

        char[][] matrix = {

                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };

        System.out.println("Brute Force : " + obj.maximalRectangleBrute(matrix));

        System.out.println("Optimal : " + obj.maximalRectangleOptimal(matrix));
    }
}
