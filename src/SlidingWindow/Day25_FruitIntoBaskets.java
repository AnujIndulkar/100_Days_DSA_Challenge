package SlidingWindow;

import java.util.*;

public class Day25_FruitIntoBaskets {

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
    public static int bruteForce(int[] fruits) {
        int n = fruits.length;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            HashSet<Integer> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(fruits[j]);
                if (set.size() <= 2) {
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
    public static int optimal(int[] fruits) {
        int left = 0, maxLen = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int right = 0; right < fruits.length; right++) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            // shrink window if more than 2 types
            while (map.size() > 2) {
                map.put(fruits[left], map.get(fruits[left]) - 1);
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of fruits: ");
        int n = sc.nextInt();

        int[] fruits = new int[n];
        System.out.print("Enter fruits array: ");
        for (int i = 0; i < n; i++) {
            fruits[i] = sc.nextInt();
        }
        // int ans = bruteForce(fruits); // for testing brute force
        int result = optimal(fruits); // optimal approach
        System.out.println("Maximum fruits in baskets: " + result);
    }
}
