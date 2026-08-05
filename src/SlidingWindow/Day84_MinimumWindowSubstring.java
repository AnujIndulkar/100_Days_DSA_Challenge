package SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class Day84_MinimumWindowSubstring {

    // 1. BRUTE FORCE
    // Topic: String
    // Time Complexity: O(n²)
    // Space Complexity: O(1)

    public String minWindowBrute(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        String answer = "";
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < s.length(); i++) {
            int[] frequency = new int[128];
            for (char ch : t.toCharArray()) {
                frequency[ch]++;
            }

            for (int j = i; j < s.length(); j++) {
                frequency[s.charAt(j)]--;
                if (isValid(frequency)) {
                    if (j - i + 1 < minLength) {
                        minLength = j - i + 1;
                        answer = s.substring(i, j + 1);
                    }
                    break;
                }
            }
        }
        return answer;
    }

    private boolean isValid(int[] frequency) {

        for (int value : frequency) {
            if (value > 0) {
                return false;
            }
        }
        return true;
    }

    // 2. OPTIMAL (SLIDING WINDOW)
    // Topic: Sliding Window, HashMap
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    public String minWindowOptimal(String s, String t) {

        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();

        for (char ch : t.toCharArray()) {

            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int left = 0;
        int count = t.length();
        int minLength = Integer.MAX_VALUE;
        int start = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            if (map.containsKey(current)) {
                if (map.get(current) > 0) {
                    count--;
                }

                map.put(current, map.get(current) - 1);
            }

            while (count == 0) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    start = left;
                }

                char leftChar = s.charAt(left);
                if (map.containsKey(leftChar)) {
                    map.put(leftChar, map.get(leftChar) + 1);
                    if (map.get(leftChar) > 0) {
                        count++;
                    }
                }
                left++;
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(start, start + minLength);
    }


    public static void main(String[] args) {

        Day84_MinimumWindowSubstring obj = new Day84_MinimumWindowSubstring();

        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.println("Brute Force : " + obj.minWindowBrute(s, t));
        System.out.println("Optimal : " + obj.minWindowOptimal(s, t));
    }
}
