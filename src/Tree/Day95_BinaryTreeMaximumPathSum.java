package Tree;

public class Day95_BinaryTreeMaximumPathSum {

    // Definition for a binary tree node
    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

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
    // Time Complexity: O(n²)
    // Space Complexity: O(n)
    // --------------------------------------------------

    public int maxPathSumBrute(TreeNode root) {

        if (root == null) {

            return 0;
        }

        int leftPath = maxGain(root.left);
        int rightPath = maxGain(root.right);

        int currentPath = root.val + Math.max(0, leftPath) + Math.max(0, rightPath);

        int leftMaximum = maxPathSumBrute(root.left);

        int rightMaximum = maxPathSumBrute(root.right);

        return Math.max(currentPath, Math.max(leftMaximum, rightMaximum));
    }

    private int maxGain(TreeNode node) {

        if (node == null) {

            return 0;
        }

        int left = Math.max(0, maxGain(node.left));

        int right = Math.max(0, maxGain(node.right));

        return node.val + Math.max(left, right);
    }


    // --------------------------------------------------
    // 2. OPTIMAL (DFS)
    // Topic: Binary Tree, Recursion, DFS
    // Time Complexity: O(n)
    // Space Complexity: O(h)
    // --------------------------------------------------

    private int maximumPathSum;

    public int maxPathSumOptimal(TreeNode root) {

        maximumPathSum = Integer.MIN_VALUE;

        dfs(root);

        return maximumPathSum;
    }

    private int dfs(TreeNode node) {

        if (node == null) {

            return 0;
        }

        // Maximum contribution from left subtree
        int leftGain = Math.max(0, dfs(node.left));

        // Maximum contribution from right subtree
        int rightGain = Math.max(0, dfs(node.right));

        // Path passing through current node
        int currentPath = node.val + leftGain + rightGain;

        // Update global maximum
        maximumPathSum = Math.max(maximumPathSum, currentPath);

        // Return maximum single-side path
        // to the parent
        return node.val + Math.max(leftGain, rightGain);
    }


    public static void main(String[] args) {

        Day95_BinaryTreeMaximumPathSum obj = new Day95_BinaryTreeMaximumPathSum();

        TreeNode root = new TreeNode(-10, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        System.out.println("Brute Force : "
                + obj.maxPathSumBrute(root));

        System.out.println("Optimal : " + obj.maxPathSumOptimal(root));
    }
}
