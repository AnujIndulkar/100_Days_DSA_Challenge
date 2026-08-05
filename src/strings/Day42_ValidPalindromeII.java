package strings;

import java.util.*;

public class Day42_ValidPalindromeII {

    // TOPIC: Valid Palindrome II

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
    public static boolean bruteForce(String s) {

        // try removing one character at every position
        for (int i = 0; i < s.length(); i++) {

            String newStr = s.substring(0, i) + s.substring(i + 1);

            if (isPalindrome(newStr)) {
                return true;
            }
        }

        // also check original string
        return isPalindrome(s);
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

    // OPTIMAL APPROACH (Two Pointers)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static boolean optimal(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {
                // try skipping either left or right character
                return check(s, left + 1, right) || check(s, left, right - 1);
            }

            left++;
            right--;
        }

        return true;
    }

    // helper function for optimal
    public static boolean check(String s, int left, int right) {

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // input
        System.out.print("Enter string: ");
        String s = sc.nextLine();

        // boolean res = bruteForce(s);
        boolean res = optimal(s);

        System.out.println("Valid Palindrome II: " + res);

        sc.close();
    }
}
