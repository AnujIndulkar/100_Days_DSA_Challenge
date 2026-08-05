package strings;

import java.util.*;

public class Day29_ValidAnagram {

    // TOPIC: Valid Anagram

    // BRUTE FORCE APPROACH (Sorting)
    // Time Complexity: O(n log n)
    // Space Complexity: O(n)
    public static boolean bruteForce(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    // OPTIMAL APPROACH (Frequency Count)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static boolean optimal(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26]; // for lowercase letters
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int val : count) {
            if (val != 0) return false;
        }
        return true;
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String s = sc.next();

        System.out.print("Enter second string: ");
        String t = sc.next();

        // boolean res = bruteForce(s, t); // brute
        boolean res = optimal(s, t); // optimal

        System.out.println("Is Anagram: " + res);
    }
}
