package strings;

import java.util.*;

public class Day44_ImplementStrStr {

    // TOPIC: Implement strStr()

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n * m)
    // Space Complexity: O(1)
    public static int bruteForce(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (m == 0) return 0;
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == m) {
                return i; // match found
            }
        }
        return -1; // not found
    }

    // OPTIMAL APPROACH (KMP Algorithm)
    // Time Complexity: O(n + m)
    // Space Complexity: O(m)
    public static int optimal(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();
        if (m == 0) return 0;
        int[] lps = buildLPS(needle);
        int i = 0; // for haystack
        int j = 0; // for needle
        while (i < n) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                return i - j; // match found
            }
            else if (i < n && haystack.charAt(i) != needle.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return -1;
    }

    // helper to build LPS array (Longest Prefix Suffix)
    public static int[] buildLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // input
        System.out.print("Enter haystack: ");
        String haystack = sc.nextLine();
        System.out.print("Enter needle: ");
        String needle = sc.nextLine();
        // int res = bruteForce(haystack, needle);
        int res = optimal(haystack, needle);
        System.out.println("Index of first occurrence: " + res);
        sc.close();
    }
}
