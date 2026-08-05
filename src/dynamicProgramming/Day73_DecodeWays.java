package dynamicProgramming;

public class Day73_DecodeWays {

    // 1. BRUTE FORCE (RECURSION)
    // Topic: Recursion
    // Time Complexity: O(2^n)
    // Space Complexity: O(n)

    public int numDecodingsBrute(String s) {
        return decode(0, s);
    }

    private int decode(int index, String s) {
        // Reached end of string
        if (index == s.length()) {
            return 1;
        }

        // Invalid if starts with 0
        if (s.charAt(index) == '0') {
            return 0;
        }

        // Take one digit
        int ways = decode(index + 1, s);

        // Take two digits
        if (index + 1 < s.length()) {
            int number = (s.charAt(index) - '0') * 10 + (s.charAt(index + 1) - '0');
            if (number >= 10 && number <= 26) {
                ways += decode(index + 2, s);
            }
        }
        return ways;
    }


    // 2. OPTIMAL (DP + MEMOIZATION)
    // Topic: Dynamic Programming, Recursion
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public int numDecodingsOptimal(String s) {
        Integer[] dp = new Integer[s.length()];
        return solve(0, s, dp);
    }

    private int solve(int index, String s, Integer[] dp) {
        if (index == s.length()) {
            return 1;
        }
        if (s.charAt(index) == '0') {
            return 0;
        }
        if (dp[index] != null) {
            return dp[index];
        }

        int ways = solve(index + 1, s, dp);
        if (index + 1 < s.length()) {
            int number = (s.charAt(index) - '0') * 10 + (s.charAt(index + 1) - '0');
            if (number >= 10 && number <= 26) {
                ways += solve(index + 2, s, dp);
            }
        }
        dp[index] = ways;
        return ways;
    }

    public static void main(String[] args) {
        Day73_DecodeWays obj = new Day73_DecodeWays();
        String s = "226";
        System.out.println("Brute Force: " + obj.numDecodingsBrute(s));
        System.out.println("Optimal: " + obj.numDecodingsOptimal(s));
    }
}