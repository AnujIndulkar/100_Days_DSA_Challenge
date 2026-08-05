package dynamicProgramming;

public class Day54_MaximumProductSubarrayII {

    // 1. BRUTE FORCE
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)

    public int maxProductBrute(int[] nums) {

        int maxProduct = nums[0];

        for (int i = 0; i < nums.length; i++) {

            int product = 1;

            for (int j = i; j < nums.length; j++) {

                product *= nums[j];

                maxProduct = Math.max(maxProduct, product);
            }
        }

        return maxProduct;
    }

    // 2. DYNAMIC PROGRAMMING (OPTIMAL)
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    public int maxProductDP(int[] nums) {

        int maxProd = nums[0];
        int minProd = nums[0];
        int answer = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int current = nums[i];

            if (current < 0) {

                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }

            maxProd = Math.max(current, maxProd * current);
            minProd = Math.min(current, minProd * current);

            answer = Math.max(answer, maxProd);
        }

        return answer;
    }

    public static void main(String[] args) {

        Day54_MaximumProductSubarrayII obj =
                new Day54_MaximumProductSubarrayII();

        int[] nums = {2, 3, -2, 4};

        System.out.println("Brute Force: " + obj.maxProductBrute(nums));
        System.out.println("DP: " + obj.maxProductDP(nums));
    }
}
