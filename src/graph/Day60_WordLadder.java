package graph;

import java.util.*;

public class Day60_WordLadder {

    // 1. BRUTE FORCE (DFS)
    // Time Complexity: Exponential
    // Space Complexity: O(n)

    public int ladderLengthBrute(String beginWord,
                                 String endWord,
                                 List<String> wordList) {

        Set<String> visited = new HashSet<>();

        int result = dfs(beginWord, endWord,
                wordList, visited);

        return result == Integer.MAX_VALUE ? 0 : result;
    }

    private int dfs(String current,
                    String endWord,
                    List<String> wordList,
                    Set<String> visited) {

        if (current.equals(endWord)) {
            return 1;
        }

        visited.add(current);

        int minLength = Integer.MAX_VALUE;

        for (String word : wordList) {

            if (!visited.contains(word) &&
                    isOneLetterDifferent(current, word)) {

                int length = dfs(word, endWord,
                        wordList, visited);

                if (length != Integer.MAX_VALUE) {

                    minLength =
                            Math.min(minLength, length + 1);
                }
            }
        }

        visited.remove(current);

        return minLength;
    }

    // 2. OPTIMAL (BFS)
    // Time Complexity: O(n * wordLength * 26)
    // Space Complexity: O(n)

    public int ladderLengthOptimal(String beginWord,
                                   String endWord,
                                   List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String word = queue.poll();

                if (word.equals(endWord)) {
                    return level;
                }

                char[] chars = word.toCharArray();

                for (int j = 0; j < chars.length; j++) {

                    char original = chars[j];

                    for (char ch = 'a'; ch <= 'z'; ch++) {

                        chars[j] = ch;

                        String nextWord =
                                new String(chars);

                        if (wordSet.contains(nextWord)) {

                            queue.offer(nextWord);

                            wordSet.remove(nextWord);
                        }
                    }

                    chars[j] = original;
                }
            }

            level++;
        }

        return 0;
    }

    private boolean isOneLetterDifferent(String a,
                                         String b) {

        int diff = 0;

        for (int i = 0; i < a.length(); i++) {

            if (a.charAt(i) != b.charAt(i)) {
                diff++;
            }
        }

        return diff == 1;
    }

    public static void main(String[] args) {

        Day60_WordLadder obj =
                new Day60_WordLadder();

        String beginWord = "hit";
        String endWord = "cog";

        List<String> wordList = Arrays.asList(
                "hot", "dot", "dog",
                "lot", "log", "cog"
        );

        System.out.println("Brute Force: " +
                obj.ladderLengthBrute(
                        beginWord, endWord, wordList));

        System.out.println("Optimal: " +
                obj.ladderLengthOptimal(
                        beginWord, endWord, wordList));
    }
}
