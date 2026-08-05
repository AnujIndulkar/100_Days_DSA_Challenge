package Arrays;

public class Day62_TrappingRainWater {

    // 1. BRUTE FORCE
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)

    public int trapBrute(int[] height) {

        int n = height.length;
        int water = 0;

        for (int i = 0; i < n; i++) {

            int leftMax = 0;
            int rightMax = 0;

            // Find left max
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }

            // Find right max
            for (int j = i; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            water += Math.min(leftMax, rightMax)
                    - height[i];
        }

        return water;
    }

    // 2. OPTIMAL (PREFIX MAX ARRAYS)
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public int trapOptimal(int[] height) {

        int n = height.length;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // Fill left max array
        leftMax[0] = height[0];

        for (int i = 1; i < n; i++) {

            leftMax[i] =
                    Math.max(leftMax[i - 1], height[i]);
        }

        // Fill right max array
        rightMax[n - 1] = height[n - 1];

        for (int i = n - 2; i >= 0; i--) {

            rightMax[i] =
                    Math.max(rightMax[i + 1], height[i]);
        }

        // Calculate trapped water
        int water = 0;

        for (int i = 0; i < n; i++) {

            water += Math.min(leftMax[i], rightMax[i])
                    - height[i];
        }

        return water;
    }

    public static void main(String[] args) {

        Day62_TrappingRainWater obj =
                new Day62_TrappingRainWater();

        int[] height = {
                0,1,0,2,1,0,1,3,2,1,2,1
        };

        System.out.println("Brute Force: " +
                obj.trapBrute(height));

        System.out.println("Optimal: " +
                obj.trapOptimal(height));
    }
}
