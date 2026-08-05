package strings;

import java.util.*;

public class Day46_GroupAnagrams {

    // TOPIC: Group Anagrams

    // BRUTE FORCE APPROACH
    // Time Complexity: O(n^2 * k log k)
    // Space Complexity: O(n)
    public static List<List<String>> bruteForce(String[] strs) {

        List<List<String>> result = new ArrayList<>();
        boolean[] visited = new boolean[strs.length];

        for (int i = 0; i < strs.length; i++) {

            if (visited[i]) continue;

            List<String> group = new ArrayList<>();
            group.add(strs[i]);
            visited[i] = true;

            for (int j = i + 1; j < strs.length; j++) {

                if (!visited[j] && isAnagram(strs[i], strs[j])) {
                    group.add(strs[j]);
                    visited[j] = true;
                }
            }

            result.add(group);
        }

        return result;
    }

    // helper for brute force
    public static boolean isAnagram(String a, String b) {

        if (a.length() != b.length()) return false;

        char[] arr1 = a.toCharArray();
        char[] arr2 = b.toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2);
    }

    // OPTIMAL APPROACH (HashMap + Sorting)
    // Time Complexity: O(n * k log k)
    // Space Complexity: O(n)
    public static List<List<String>> optimal(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            char[] arr = str.toCharArray();
            Arrays.sort(arr);

            String key = new String(arr);

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of strings: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] strs = new String[n];

        System.out.println("Enter strings:");
        for (int i = 0; i < n; i++) {
            strs[i] = sc.nextLine();
        }

        // List<List<String>> res = bruteForce(strs);
        List<List<String>> res = optimal(strs);

        System.out.println("Grouped Anagrams: " + res);

        sc.close();
    }
}
