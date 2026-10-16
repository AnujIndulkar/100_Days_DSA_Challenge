package Tree;

import java.util.*;

public class Day100_BinaryTreeZigzagLevelOrderTraversal {

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
    // Time Complexity: O(n²) worst case
    // Space Complexity: O(n)
    // --------------------------------------------------

    public List<List<Integer>> zigzagLevelOrderBrute(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        int height = getHeight(root);

        for (int level = 0; level < height; level++) {

            List<Integer> currentLevel = new ArrayList<>();

            collectLevel(root, level, currentLevel);

            // Reverse alternate levels
            if (level % 2 == 1) {
                Collections.reverse(currentLevel);
            }

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

    public List<List<Integer>> zigzagLevelOrderOptimal(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        boolean leftToRight = true;

        while (!queue.isEmpty()) {

            int size = queue.size();

            List<Integer> currentLevel = new ArrayList<>();

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

            if (!leftToRight) {
                Collections.reverse(currentLevel);
            }

            result.add(currentLevel);

            leftToRight = !leftToRight;
        }

        return result;
    }

    // --------------------------------------------------
    // MAIN METHOD
    // --------------------------------------------------

    public static void main(String[] args) {

        Day100_BinaryTreeZigzagLevelOrderTraversal obj = new Day100_BinaryTreeZigzagLevelOrderTraversal();

        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        System.out.println("Brute Force: " + obj.zigzagLevelOrderBrute(root));

        System.out.println("Optimal: " + obj.zigzagLevelOrderOptimal(root));
    }
}
