package recursion;

import java.util.Stack;

public class Day77_LongestValidParentheses {

    // 1. BRUTE FORCE (CHECK ALL SUBSTRINGS)
    // Topic: Recursion / String / Brute Force
    // Time Complexity: O(n^3)
    // Space Complexity: O(1)

    public int longestValidParenthesesBrute(String s) {
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (isValid(s.substring(i, j + 1))) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    // Helper function to check valid parentheses
    private boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }


    // 2. OPTIMAL (DP APPROACH)
    // Topic: Dynamic Programming
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public int longestValidParenthesesOptimal(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int maxLen = 0;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                // Case 1: "...()"
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }
                // Case 2: "...))"
                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0);
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }
    
    public static void main(String[] args) {
        Day77_LongestValidParentheses obj = new Day77_LongestValidParentheses();
        String s = "(()())";
        System.out.println("Brute Force: " + obj.longestValidParenthesesBrute(s));
        System.out.println("Optimal: " + obj.longestValidParenthesesOptimal(s));
    }
}