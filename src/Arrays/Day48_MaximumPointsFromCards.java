package Arrays;

import java.util.*;

public class Day48_MaximumPointsFromCards {

    // TOPIC: Maximum Points You Can Obtain from Cards

    // BRUTE FORCE APPROACH
    // Time Complexity: O(2^k)
    // Space Complexity: O(k)
    public static int bruteForce(int[] cards, int k) {
        return helper(cards, k, 0, cards.length - 1);
    }

    // helper for brute force
    public static int helper(int[] cards, int k, int left, int right) {

        if (k == 0) return 0;

        int takeLeft = cards[left] + helper(cards, k - 1, left + 1, right);
        int takeRight = cards[right] + helper(cards, k - 1, left, right - 1);

        return Math.max(takeLeft, takeRight);
    }

    // BETTER APPROACH (Sliding Window - Total Sum Trick)
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public static int optimal(int[] cards, int k) {

        int n = cards.length;

        int totalSum = 0;
        for (int num : cards) totalSum += num;

        // window size = n - k (minimum subarray to remove)
        int windowSize = n - k;

        if (windowSize == 0) return totalSum;

        int windowSum = 0;

        // initial window
        for (int i = 0; i < windowSize; i++) {
            windowSum += cards[i];
        }

        int minSum = windowSum;

        for (int i = windowSize; i < n; i++) {

            windowSum += cards[i];
            windowSum -= cards[i - windowSize];

            minSum = Math.min(minSum, windowSum);
        }

        return totalSum - minSum;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cards: ");
        int n = sc.nextInt();

        int[] cards = new int[n];

        System.out.println("Enter card points:");
        for (int i = 0; i < n; i++) {
            cards[i] = sc.nextInt();
        }

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        // int res = bruteForce(cards, k);
        int res = optimal(cards, k);

        System.out.println("Maximum Points: " + res);

        sc.close();
    }
}
