package Tree;

import java.util.HashMap;
import java.util.Map;

public class Day98_ConstructBinaryTreeFromPreorderAndInorder {

    // Definition for a binary tree node
    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // --------------------------------------------------
    // 1. BRUTE FORCE
    // Topic: Binary Tree, Recursion, Array
    // Time Complexity: O(n²)
    // Space Complexity: O(n)
    // --------------------------------------------------

    public TreeNode buildTreeBrute(int[] preorder, int[] inorder) {

        return buildBrute(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildBrute(
            int[] preorder,
            int preStart,
            int preEnd,
            int[] inorder,
            int inStart,
            int inEnd) {

        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // First preorder element is the root
        int rootValue = preorder[preStart];
        TreeNode root = new TreeNode(rootValue);

        // Find root in inorder traversal
        int rootIndex = inStart;

        while (rootIndex <= inEnd && inorder[rootIndex] != rootValue) {
            rootIndex++;
        }

        int leftSize = rootIndex - inStart;

        // Build left subtree
        root.left = buildBrute(preorder, preStart + 1, preStart + leftSize, inorder, inStart, rootIndex - 1);

        // Build right subtree
        root.right = buildBrute(preorder, preStart + leftSize + 1, preEnd, inorder, rootIndex + 1, inEnd);

        return root;
    }

    // --------------------------------------------------
    // 2. OPTIMAL (HASHMAP + RECURSION)
    // Topic: Binary Tree, Recursion, HashMap
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // --------------------------------------------------

    private Map<Integer, Integer> inorderMap;
    private int preorderIndex;

    public TreeNode buildTreeOptimal(int[] preorder, int[] inorder) {

        inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        preorderIndex = 0;

        return buildOptimal(preorder, 0, inorder.length - 1);
    }

    private TreeNode buildOptimal(int[] preorder, int inStart, int inEnd) {

        if (inStart > inEnd) {
            return null;
        }

        // Get root from preorder traversal
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // Find root index in O(1) average time
        int rootIndex = inorderMap.get(rootValue);

        // Build left subtree before right subtree
        root.left = buildOptimal(preorder, inStart, rootIndex - 1);

        root.right = buildOptimal(preorder, rootIndex + 1, inEnd);

        return root;
    }

    // Print tree using inorder traversal
    private void printInorder(TreeNode root) {
        if (root == null) {
            return;
        }

        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    // --------------------------------------------------
    // MAIN METHOD
    // --------------------------------------------------

    public static void main(String[] args) {

        Day98_ConstructBinaryTreeFromPreorderAndInorder obj = new Day98_ConstructBinaryTreeFromPreorderAndInorder();

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode bruteRoot = obj.buildTreeBrute(preorder, inorder);

        System.out.print("Brute Force Inorder: ");
        obj.printInorder(bruteRoot);

        System.out.println();

        TreeNode optimalRoot = obj.buildTreeOptimal(preorder, inorder);

        System.out.print("Optimal Inorder: ");
        obj.printInorder(optimalRoot);

        System.out.println();
    }
}
