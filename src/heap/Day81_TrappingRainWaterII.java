package heap;

import java.util.PriorityQueue;

public class Day81_TrappingRainWaterII {

    // Cell class for Min Heap
    static class Cell {
        int row;
        int col;
        int height;

        Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    // 1. BRUTE FORCE
    // Topic: Matrix
    // Time Complexity: O((m*n)^2)
    // Space Complexity: O(1)

    public int trapRainWaterBrute(int[][] heightMap) {
        int rows = heightMap.length;
        int cols = heightMap[0].length;
        if (rows < 3 || cols < 3) {
            return 0;
        }
        int water = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                int leftMax = heightMap[i][j];
                for (int k = j - 1; k >= 0; k--) {
                    leftMax = Math.max(leftMax, heightMap[i][k]);
                }
                int rightMax = heightMap[i][j];
                for (int k = j + 1; k < cols; k++) {
                    rightMax = Math.max(rightMax, heightMap[i][k]);
                }
                int topMax = heightMap[i][j];
                for (int k = i - 1; k >= 0; k--) {
                    topMax = Math.max(topMax, heightMap[k][j]);
                }
                int bottomMax = heightMap[i][j];
                for (int k = i + 1; k < rows; k++) {
                    bottomMax = Math.max(bottomMax, heightMap[k][j]);
                }
                int boundaryHeight = Math.min(Math.min(leftMax, rightMax), Math.min(topMax, bottomMax));

                if (boundaryHeight > heightMap[i][j]) {
                    water += boundaryHeight - heightMap[i][j];
                }
            }
        }
        return water;
    }

    // 2. OPTIMAL (MIN HEAP + BFS)
    // Topic: Heap, BFS
    // Time Complexity: O(m*n log(m*n))
    // Space Complexity: O(m*n)

    public int trapRainWaterOptimal(int[][] heightMap) {
        int rows = heightMap.length;
        int cols = heightMap[0].length;
        if (rows < 3 || cols < 3) {
            return 0;
        }
        PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> a.height - b.height);
        boolean[][] visited = new boolean[rows][cols];

        // Add first & last column
        for (int i = 0; i < rows; i++) {
            pq.offer(new Cell(i, 0, heightMap[i][0]));
            pq.offer(new Cell(i, cols - 1, heightMap[i][cols - 1]));
            visited[i][0] = true;
            visited[i][cols - 1] = true;
        }

        // Add first & last row
        for (int j = 1; j < cols - 1; j++) {
            pq.offer(new Cell(0, j, heightMap[0][j]));
            pq.offer(new Cell(rows - 1, j, heightMap[rows - 1][j]));
            visited[0][j] = true;
            visited[rows - 1][j] = true;
        }
        int[][] directions = {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };

        int water = 0;
        while (!pq.isEmpty()) {
            Cell current = pq.poll();
            for (int[] dir : directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];

                if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || visited[newRow][newCol]) {
                    continue;
                }

                visited[newRow][newCol] = true;
                water += Math.max(0, current.height - heightMap[newRow][newCol]);

                pq.offer(new Cell(newRow, newCol, Math.max(current.height, heightMap[newRow][newCol])));
            }
        }
        return water;
    }

    public static void main(String[] args) {
        Day81_TrappingRainWaterII obj = new Day81_TrappingRainWaterII();
        int[][] heightMap = {
                {1, 4, 3, 1, 3, 2},
                {3, 2, 1, 3, 2, 4},
                {2, 3, 3, 2, 3, 1}
        };

        System.out.println("Brute Force : " + obj.trapRainWaterBrute(heightMap));
        System.out.println("Optimal : " + obj.trapRainWaterOptimal(heightMap));
    }
}
