package strings;

import java.util.*;

public class Day41_ValidPalindrome {

    // TOPIC: Valid Palindrome

    // BRUTE FORCE APPROACH (Clean + Reverse)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static boolean bruteForce(String s) {
        // remove non-alphanumeric and convert to lowercase
        String cleaned = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                cleaned += Character.toLowerCase(ch);
            }
        }
        // reverse string
        String reversed = "";
        for (int i = cleaned.length() - 1; i >= 0; i--) {
            reversed += cleaned.charAt(i);
        }
        return cleaned.equals(reversed);
    }

    // OPTIMAL APPROACH (Two Pointers)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static boolean optimal(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            // skip non-alphanumeric
            if (!Character.isLetterOrDigit(l)) {
                left++;
            } else if (!Character.isLetterOrDigit(r)) {
                right--;
            } else {
                if (Character.toLowerCase(l) != Character.toLowerCase(r)) {
                    return false;
                }
                left++;
                right--;
            }
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
        System.out.println("Is Palindrome: " + res);
        sc.close();
    }
}
