package graph;

import java.util.*;

public class Day89_WordLadderII {

    // --------------------------------------------------
    // 1. BRUTE FORCE
    // Topic: Graph, BFS, Backtracking
    // Time Complexity: O(N * L * 26 * P)
    // Space Complexity: O(N * L * P)
    // --------------------------------------------------

    public List<List<String>> findLaddersBrute(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> result = new ArrayList<>();

        if (!wordList.contains(endWord)) {
            return result;
        }

        Set<String> dictionary = new HashSet<>(wordList);

        Queue<List<String>> queue = new LinkedList<>();

        queue.offer(new ArrayList<>(List.of(beginWord)));

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            Set<String> usedThisLevel = new HashSet<>();

            for (int i = 0; i < size; i++) {
                List<String> path = queue.poll();
                String current = path.get(path.size() - 1);

                for (int j = 0; j < current.length(); j++) {
                    char[] chars = current.toCharArray();
                    char original = chars[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) {
                            continue;
                        }

                        chars[j] = c;

                        String next = new String(chars);

                        if (!dictionary.contains(next)) {
                            continue;
                        }

                        List<String> newPath = new ArrayList<>(path);

                        newPath.add(next);

                        if (next.equals(endWord)) {
                            result.add(newPath);
                            found = true;

                        } else {
                            queue.offer(newPath);
                        }

                        usedThisLevel.add(next);
                    }
                }
            }

            for (String word : usedThisLevel) {
                dictionary.remove(word);
            }
        }

        return result;
    }


    // --------------------------------------------------
    // 2. OPTIMAL (BFS + BACKTRACKING)
    // Topic: Graph, BFS, Backtracking
    // Time Complexity: O(N * L * 26 + P * L)
    // Space Complexity: O(N * L + P * L)
    // --------------------------------------------------

    public List<List<String>> findLaddersOptimal(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> result = new ArrayList<>();

        Set<String> dictionary = new HashSet<>(wordList);

        if (!dictionary.contains(endWord)) {
            return result;
        }

        Map<String, List<String>> parents = new HashMap<>();
        Set<String> currentLevel = new HashSet<>();
        currentLevel.add(beginWord);

        boolean found = false;

        while (!currentLevel.isEmpty() && !found) {

            Set<String> nextLevel = new HashSet<>();

            for (String word : currentLevel) {
                dictionary.remove(word);
            }

            for (String word : currentLevel) {
                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {

                    char original = chars[i];

                    for (char c = 'a'; c <= 'z'; c++) {

                        if (c == original) {
                            continue;
                        }

                        chars[i] = c;
                        String next = new String(chars);

                        if (!dictionary.contains(next)) {
                            continue;
                        }

                        parents.computeIfAbsent(next, k -> new ArrayList<>()).add(word);
                        nextLevel.add(next);

                        if (next.equals(endWord)) {
                            found = true;
                        }
                    }

                    chars[i] = original;
                }
            }

            currentLevel = nextLevel;
        }

        if (!found) {
            return result;
        }

        List<String> path = new ArrayList<>();

        path.add(endWord);

        buildPaths(endWord, beginWord, parents, path, result);

        return result;
    }

    private void buildPaths(String current, String beginWord, Map<String, List<String>> parents, List<String> path, List<List<String>> result) {

        if (current.equals(beginWord)) {

            List<String> completePath = new ArrayList<>(path);
            Collections.reverse(completePath);
            result.add(completePath);

            return;
        }

        if (!parents.containsKey(current)) {
            return;
        }

        for (String parent : parents.get(current)) {

            path.add(parent);

            buildPaths(parent, beginWord, parents, path, result);

            path.remove(path.size() - 1);
        }
    }


    public static void main(String[] args) {

        Day89_WordLadderII obj = new Day89_WordLadderII();

        String beginWord = "hit";
        String endWord = "cog";

        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

        System.out.println("Brute Force:");

        System.out.println(obj.findLaddersBrute(beginWord, endWord, wordList));


        System.out.println("Optimal:");

        System.out.println(obj.findLaddersOptimal(beginWord, endWord, wordList));
    }
}