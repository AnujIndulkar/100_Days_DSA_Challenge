package greedy;

import java.util.*;

public class Day49_Candy {

    // TOPIC: Candy (Greedy)

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
    public static int bruteForce(int[] ratings) {

        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        boolean changed = true;

        while (changed) {
            changed = false;

            for (int i = 0; i < n; i++) {

                // left neighbor
                if (i > 0 && ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                    candies[i] = candies[i - 1] + 1;
                    changed = true;
                }

                // right neighbor
                if (i < n - 1 && ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                    candies[i] = candies[i + 1] + 1;
                    changed = true;
                }
            }
        }

        int sum = 0;
        for (int c : candies) sum += c;

        return sum;
    }

    // OPTIMAL APPROACH (Greedy - Two Pass)
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public static int optimal(int[] ratings) {

        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        // left to right
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // right to left
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int sum = 0;
        for (int c : candies) sum += c;

        return sum;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of children: ");
        int n = sc.nextInt();

        int[] ratings = new int[n];

        System.out.println("Enter ratings:");
        for (int i = 0; i < n; i++) {
            ratings[i] = sc.nextInt();
        }

        // int res = bruteForce(ratings);
        int res = optimal(ratings);

        System.out.println("Minimum candies required: " + res);

        sc.close();
    }
}
