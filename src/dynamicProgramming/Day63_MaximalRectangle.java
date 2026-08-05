package dynamicProgramming;

import java.util.*;

public class Day63_MaximalRectangle {

    // 1. BRUTE FORCE
    // Time Complexity: O((m*n)^2)
    // Space Complexity: O(1)

    public int maximalRectangleBrute(char[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int maxArea = 0;

        for (int r1 = 0; r1 < rows; r1++) {

            for (int c1 = 0; c1 < cols; c1++) {

                if (matrix[r1][c1] == '0') {
                    continue;
                }

                for (int r2 = r1; r2 < rows; r2++) {

                    for (int c2 = c1; c2 < cols; c2++) {

                        boolean valid = true;

                        for (int i = r1; i <= r2; i++) {

                            for (int j = c1; j <= c2; j++) {

                                if (matrix[i][j] == '0') {
                                    valid = false;
                                    break;
                                }
                            }

                            if (!valid) {
                                break;
                            }
                        }

                        if (valid) {

                            int area =
                                    (r2 - r1 + 1) *
                                            (c2 - c1 + 1);

                            maxArea =
                                    Math.max(maxArea, area);
                        }
                    }
                }
            }
        }

        return maxArea;
    }

    // 2. OPTIMAL (HISTOGRAM + STACK)
    // Time Complexity: O(rows * cols)
    // Space Complexity: O(cols)

    public int maximalRectangleOptimal(char[][] matrix) {

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

            maxArea =
                    Math.max(maxArea,
                            largestRectangleArea(heights));
        }

        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {

        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {

            int currentHeight =
                    (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() &&
                    currentHeight <
                            heights[stack.peek()]) {

                int height =
                        heights[stack.pop()];

                int width;

                if (stack.isEmpty()) {
                    width = i;
                } else {
                    width =
                            i - stack.peek() - 1;
                }

                maxArea =
                        Math.max(maxArea,
                                height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {

        Day63_MaximalRectangle obj =
                new Day63_MaximalRectangle();

        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };

        System.out.println("Brute Force: " +
                obj.maximalRectangleBrute(matrix));

        System.out.println("Optimal: " +
                obj.maximalRectangleOptimal(matrix));
    }
}
