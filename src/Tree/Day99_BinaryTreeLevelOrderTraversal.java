package Tree;

import java.util.*;

public class Day99_BinaryTreeLevelOrderTraversal {

    // Definition for a binary tree node
    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // --------------------------------------------------
    // 1. BRUTE FORCE (RECURSION)
    // Topic: Binary Tree, Recursion
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // --------------------------------------------------

    public List<List<Integer>> levelOrderBrute(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        int height = getHeight(root);

        // Process each level separately
        for (int level = 0; level < height; level++) {

            List<Integer> currentLevel = new ArrayList<>();

            collectLevel(root, level, currentLevel);

            result.add(currentLevel);
        }

        return result;
    }

    private int getHeight(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    private void collectLevel(TreeNode root, int level, List<Integer> currentLevel) {

        if (root == null) {
            return;
        }

        if (level == 0) {
            currentLevel.add(root.val);
            return;
        }

        collectLevel(root.left, level - 1, currentLevel);
        collectLevel(root.right, level - 1, currentLevel);
    }

    // --------------------------------------------------
    // 2. OPTIMAL (BFS + QUEUE)
    // Topic: Binary Tree, BFS, Queue
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // --------------------------------------------------

    public List<List<Integer>> levelOrderOptimal(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();

            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes at this level
            for (int i = 0; i < size; i++) {

                TreeNode node = queue.poll();

                currentLevel.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    // --------------------------------------------------
    // MAIN METHOD
    // --------------------------------------------------

    public static void main(String[] args) {

        Day99_BinaryTreeLevelOrderTraversal obj = new Day99_BinaryTreeLevelOrderTraversal();

        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        System.out.println("Brute Force: " + obj.levelOrderBrute(root));

        System.out.println("Optimal: " + obj.levelOrderOptimal(root));
    }
}
