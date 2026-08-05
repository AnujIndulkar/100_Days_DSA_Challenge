package strings;

import java.util.*;

public class Day43_LongestPalindromicSubstring {

    // TOPIC: Longest Palindromic Substring

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n^3)
    // Space Complexity: O(1)
    public static String bruteForce(String s) {
        int n = s.length();
        String longest = "";
        // check all substrings
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String sub = s.substring(i, j + 1);
                if (isPalindrome(sub) && sub.length() > longest.length()) {
                    longest = sub;
                }
            }
        }
        return longest;
    }

    // helper to check palindrome
    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // OPTIMAL APPROACH (Expand Around Center)
    // Time Complexity: O(n^2)
    // Space Complexity: O(1)
    public static String optimal(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);       // odd length
            int len2 = expand(s, i, i + 1);   // even length
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    // helper function for optimal
    public static int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() &&
                s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // input
        System.out.print("Enter string: ");
        String s = sc.nextLine();

        // String res = bruteForce(s);
        String res = optimal(s);

        System.out.println("Longest Palindromic Substring: " + res);

        sc.close();
    }
}