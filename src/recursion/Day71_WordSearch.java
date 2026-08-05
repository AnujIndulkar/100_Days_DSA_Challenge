package recursion;

public class Day71_WordSearch {

    // 1. BRUTE FORCE (DFS + BACKTRACKING)
    // Topic: Recursion, Backtracking, DFS
    // Time Complexity: O(m * n * 4^L)
    // Space Complexity: O(L)

    public boolean existBrute(char[][] board, String word) {

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

        // Word found
        if (index == word.length()) {
            return true;
        }

        // Out of bounds or character mismatch
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != word.charAt(index)) {

            return false;
        }

        // Mark as visited
        char temp = board[row][col];
        board[row][col] = '#';

        boolean found =
                dfs(row + 1, col, index + 1, board, word) ||
                        dfs(row - 1, col, index + 1, board, word) ||
                        dfs(row, col + 1, index + 1, board, word) ||
                        dfs(row, col - 1, index + 1, board, word);

        // Backtrack
        board[row][col] = temp;

        return found;
    }


    // 2. OPTIMAL (DFS + BACKTRACKING + EARLY RETURN)
    // Topic: Recursion, Backtracking, DFS
    // Time Complexity: O(m * n * 4^L)
    // Space Complexity: O(L)

    public boolean existOptimal(char[][] board, String word) {

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (board[i][j] == word.charAt(0) && backtrack(i, j, 0, board, word)) {

                    return true;
                }
            }
        }

        return false;
    }

    private boolean backtrack(int row, int col, int index, char[][] board, String word) {

        if (index == word.length()) {
            return true;
        }

        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != word.charAt(index)) {

            return false;
        }

        char temp = board[row][col];
        board[row][col] = '#';

        boolean found =
                backtrack(row + 1, col, index + 1, board, word) ||
                        backtrack(row - 1, col, index + 1, board, word) ||
                        backtrack(row, col + 1, index + 1, board, word) ||
                        backtrack(row, col - 1, index + 1, board, word);

        board[row][col] = temp;

        return found;
    }

    public static void main(String[] args) {

        Day71_WordSearch obj = new Day71_WordSearch();

        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        String word = "ABCCED";

        System.out.println("Brute Force: " + obj.existBrute(board, word));

        System.out.println("Optimal: " + obj.existOptimal(board, word));
    }
}
