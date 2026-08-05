package SlidingWindow;

import java.util.*;

public class Day23_LongestSubstringKDistinct {

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
    public static int bruteForce(String s, int k) {
        int n = s.length();
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            HashSet<Character> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(s.charAt(j));
                if (set.size() <= k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                } else {
                    break;
                }
            }
        }
        return maxLen;
    }

    // OPTIMAL APPROACH (Sliding Window)
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public static int optimal(String s, int k) {
        int n = s.length();
        int left = 0, maxLen = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            // shrink window
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
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
//        int ans = bruteForce(s,k);
        int result = optimal(s, k);
        System.out.println("Longest Length: " + result);
    }
}
