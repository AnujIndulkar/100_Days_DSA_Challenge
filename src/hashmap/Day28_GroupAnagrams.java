package hashmap;

import java.util.*;

public class Day28_GroupAnagrams {

    // TOPIC: Group Anagrams

    // BRUTE FORCE APPROACH
    // Compare each string with others after sorting
    // Time Complexity: O(N^2 * K log K)
    // Space Complexity: O(N)
    public static List<List<String>> bruteForce(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        boolean[] visited = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            if (visited[i]) continue;
            List<String> group = new ArrayList<>();
            group.add(strs[i]);
            visited[i] = true;
            for (int j = i + 1; j < strs.length; j++) {
                if (isAnagram(strs[i], strs[j])) {
                    group.add(strs[j]);
                    visited[j] = true;
                }
            }
            result.add(group);
        }
        return result;
    }

    // Helper function to check anagram
    public static boolean isAnagram(String s1, String s2) {
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    // OPTIMAL APPROACH (HashMap + Sorting)
    // Time Complexity: O(N * K log K)
    // Space Complexity: O(N * K)
    public static List<List<String>> optimal(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of strings: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline
        String[] strs = new String[n];
        System.out.println("Enter strings:");
        for (int i = 0; i < n; i++) {
            strs[i] = sc.nextLine();
        }
        // List<List<String>> res = bruteForce(strs);
        List<List<String>> res = optimal(strs);
        System.out.println("Grouped Anagrams:");
        for (List<String> group : res) {
            System.out.println(group);
        }
    }
}
