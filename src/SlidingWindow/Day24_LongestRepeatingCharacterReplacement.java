package SlidingWindow;

import java.util.*;

public class Day24_LongestRepeatingCharacterReplacement {

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n^3)
    // Space Complexity: O(26) or O(1)
    public static int bruteForce(String s, int k) {
        int n = s.length();
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int[] count = new int[26];
                int maxFreq = 0;
                for (int p = i; p <= j; p++) {
                    count[s.charAt(p) - 'A']++;
                    maxFreq = Math.max(maxFreq, count[s.charAt(p) - 'A']);
                }
                if (j - i + 1 - maxFreq <= k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    // OPTIMAL APPROACH (Sliding Window)
    // Time Complexity: O(n)
    // Space Complexity: O(26) or O(1)
    public static int optimal(String s, int k) {
        int[] count = new int[26]; // frequency of characters
        int left = 0, maxCount = 0, maxLen = 0;
        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right) - 'A']++;
            maxCount = Math.max(maxCount, count[s.charAt(right) - 'A']);
            // Shrink window if replacements needed > k
            while (right - left + 1 - maxCount > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter String: ");
        String s = sc.next();
        System.out.print("Enter K: ");
        int k = sc.nextInt();

        // int ans = bruteForce(s, k);
        int result = optimal(s, k);
        System.out.println("Longest Repeating Character Substring Length: " + result);
    }
}
