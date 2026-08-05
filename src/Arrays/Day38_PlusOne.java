package Arrays;

import java.util.*;

public class Day38_PlusOne {

    // TOPIC: Plus One

    // BRUTE FORCE APPROACH (Convert to number - Not safe for large input)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int[] bruteForce(int[] digits) {

        int n = digits.length;

        // convert array to number
        long num = 0;
        for (int i = 0; i < n; i++) {
            num = num * 10 + digits[i];
        }

        // add one
        num = num + 1;

        // convert back to array
        String str = String.valueOf(num);
        int[] result = new int[str.length()];

        for (int i = 0; i < str.length(); i++) {
            result[i] = str.charAt(i) - '0';
        }

        return result;
    }

    // OPTIMAL APPROACH (Carry handling)
    // Time Complexity: O(n)
    // Space Complexity: O(1) (or O(n) if new array created)
    public static int[] optimal(int[] digits) {

        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {

            if (digits[i] < 9) {
                digits[i]++;     // simple increment
                return digits;
            }

            digits[i] = 0;       // carry forward
        }

        // if all digits were 9 (e.g., 999 → 1000)
        int[] result = new int[n + 1];
        result[0] = 1;

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // input
        System.out.print("Enter number of digits: ");
        int n = sc.nextInt();

        int[] digits = new int[n];

        System.out.print("Enter digits: ");
        for (int i = 0; i < n; i++) {
            digits[i] = sc.nextInt();
        }

        // int[] res = bruteForce(digits);
        int[] res = optimal(digits);

        System.out.print("Result: ");
        for (int num : res) {
            System.out.print(num + " ");
        }
    }
}
