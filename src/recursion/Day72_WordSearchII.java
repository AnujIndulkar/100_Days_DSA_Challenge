package recursion;

import java.util.*;

public class Day72_WordSearchII {

    // Trie Node
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    // 1. BRUTE FORCE (DFS + BACKTRACKING FOR EACH WORD)
    // Topic: Recursion, Backtracking, DFS
    // Time Complexity: O(W * M * N * 4^L)
    // Space Complexity: O(L)

    public List<String> findWordsBrute(char[][] board, String[] words) {

        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (exist(board, word)) {
                result.add(word);
            }
        }
        return result;
    }

    private boolean exist(char[][] board, String word) {

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(i, j, 0, board, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int row, int col, int index, char[][] board, String word) {

        if (index == word.length()) {
            return true;
        }

        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != word.charAt(index)) {
            return false;
        }

        char temp = board[row][col];
        board[row][col] = '#';

        boolean found = dfs(row + 1, col, index + 1, board, word) || dfs(row - 1, col, index + 1, board, word) || dfs(row, col + 1, index + 1, board, word) || dfs(row, col - 1, index + 1, board, word);
        board[row][col] = temp;
        return found;
    }


    // 2. OPTIMAL (TRIE + DFS + BACKTRACKING)
    // Topic: Trie, Recursion, Backtracking, DFS
    // Time Complexity: O(M * N * 4^L)
    // Space Complexity: O(Total characters in words)

    public List<String> findWordsOptimal(char[][] board, String[] words) {

        TrieNode root = buildTrie(words);
        List<String> result = new ArrayList<>();

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                search(i, j, board, root, result);
            }
        }
        return result;
    }

    private TrieNode buildTrie(String[] words) {

        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.word = word;
        }
        return root;
    }

    private void search(int row, int col, char[][] board, TrieNode node, List<String> result) {

        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] == '#') {
            return;
        }

        char ch = board[row][col];

        TrieNode next = node.children[ch - 'a'];

        if (next == null) {
            return;
        }

        // Word found
        if (next.word != null) {
            result.add(next.word);
            next.word = null; // Avoid duplicates
        }

        board[row][col] = '#';
        search(row + 1, col, board, next, result);
        search(row - 1, col, board, next, result);
        search(row, col + 1, board, next, result);
        search(row, col - 1, board, next, result);

        // Backtrack
        board[row][col] = ch;
    }

    public static void main(String[] args) {

        Day72_WordSearchII obj = new Day72_WordSearchII();

        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };

        String[] words = {"oath", "pea", "eat", "rain"};

        System.out.println("Brute Force: " + obj.findWordsBrute(board, words));

        System.out.println("Optimal: " + obj.findWordsOptimal(board, words));
    }
}
