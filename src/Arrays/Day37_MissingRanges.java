package Arrays;

import java.util.*;

public class Day37_MissingRanges {

    // TOPIC: Missing Ranges

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n + range)
    // Space Complexity: O(1) (ignoring output list)
    public static List<String> bruteForce(int[] nums, int lower, int upper) {

        List<String> result = new ArrayList<>();

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int i = lower; i <= upper; i++) {
            if (!set.contains(i)) {

                int start = i;

                while (i <= upper && !set.contains(i)) {
                    i++;
                }

                int end = i - 1;

                if (start == end) {
                    result.add(String.valueOf(start));
                } else {
                    result.add(start + "->" + end);
                }
            }
        }

        return result;
    }

    // OPTIMAL APPROACH
    // Time Complexity: O(n)
    // Space Complexity: O(1) (ignoring output list)
    public static List<String> optimal(int[] nums, int lower, int upper) {

        List<String> result = new ArrayList<>();
        long prev = (long) lower - 1;
        for (int i = 0; i <= nums.length; i++) {
            long curr = (i == nums.length) ? (long) upper + 1 : nums[i];
            if (curr - prev >= 2) {
                result.add(format(prev + 1, curr - 1));
            }
            prev = curr;
        }
        return result;
    }

    // Helper to format range
    private static String format(long start, long end) {
        if (start == end) {
            return String.valueOf(start);
        } else {
            return start + "->" + end;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // input
        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.print("Enter sorted array elements: ");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.print("Enter lower bound: ");
        int lower = sc.nextInt();

        System.out.print("Enter upper bound: ");
        int upper = sc.nextInt();

        // List<String> res = bruteForce(nums, lower, upper);
        List<String> res = optimal(nums, lower, upper);

        System.out.println("Missing Ranges: " + res);
    }
}
