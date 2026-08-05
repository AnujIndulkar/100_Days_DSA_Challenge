package strings;

import java.util.*;

public class Day45_MinimumWindowSubstring {

    // TOPIC: Minimum Window Substring

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n^3)
    // Space Complexity: O(1)
    public static String bruteForce(String s, String t) {

        int n = s.length();
        String result = "";
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                String sub = s.substring(i, j + 1);

                if (containsAll(sub, t) && sub.length() < minLen) {
                    minLen = sub.length();
                    result = sub;
                }
            }
        }

        return result;
    }

    // helper for brute force
    public static boolean containsAll(String sub, String t) {

        int[] count = new int[256];

        for (char c : sub.toCharArray()) {
            count[c]++;
        }

        for (char c : t.toCharArray()) {
            if (count[c] == 0) {
                return false;
            }
            count[c]--;
        }

        return true;
    }

    // OPTIMAL APPROACH (Sliding Window)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static String optimal(String s, String t) {

        if (s.length() == 0 || t.length() == 0) return "";

        int[] map = new int[256];

        for (char c : t.toCharArray()) {
            map[c]++;
        }

        int left = 0, right = 0;
        int count = t.length();

        int minLen = Integer.MAX_VALUE;
        int start = 0;

        while (right < s.length()) {

            if (map[s.charAt(right)] > 0) {
                count--;
            }
            map[s.charAt(right)]--;
            right++;

            while (count == 0) {

                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }

                map[s.charAt(left)]++;

                if (map[s.charAt(left)] > 0) {
                    count++;
                }

                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // input
        System.out.print("Enter string s: ");
        String s = sc.nextLine();

        System.out.print("Enter string t: ");
        String t = sc.nextLine();

        // String res = bruteForce(s, t);
        String res = optimal(s, t);

        System.out.println("Minimum Window Substring: " + res);

        sc.close();
    }
}
