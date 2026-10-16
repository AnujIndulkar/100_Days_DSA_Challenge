package Tree;

public class Day97_LowestCommonAncestorOfBinaryTree {

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
    // 1. BRUTE FORCE
    // Topic: Binary Tree, Recursion
    // Time Complexity: O(n²)
    // Space Complexity: O(n)
    // --------------------------------------------------

    public TreeNode lowestCommonAncestorBrute(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) {
            return null;
        }

        // If current node is one of p or q
        if (root == p || root == q) {
            return root;
        }

        // Find path from root to p
        java.util.ArrayList<TreeNode> pathP = new java.util.ArrayList<>();

        // Find path from root to q
        java.util.ArrayList<TreeNode> pathQ = new java.util.ArrayList<>();

        findPath(root, p, pathP);
        findPath(root, q, pathQ);

        int i = 0;
        TreeNode answer = null;

        while (i < pathP.size() && i < pathQ.size() && pathP.get(i) == pathQ.get(i)) {

            answer = pathP.get(i);
            i++;
        }

        return answer;
    }

    private boolean findPath(TreeNode root, TreeNode target, java.util.ArrayList<TreeNode> path) {

        if (root == null) {
            return false;
        }

        path.add(root);

        if (root == target) {
            return true;
        }

        if (findPath(root.left, target, path) || findPath(root.right, target, path)) {

            return true;
        }

        // Backtrack
        path.remove(path.size() - 1);

        return false;
    }

    // --------------------------------------------------
    // 2. OPTIMAL
    // Topic: Binary Tree, Recursion, DFS
    // Time Complexity: O(n)
    // Space Complexity: O(h)
    // --------------------------------------------------

    public TreeNode lowestCommonAncestorOptimal(TreeNode root, TreeNode p, TreeNode q) {

        // Base case
        if (root == null || root == p || root == q) {

            return root;
        }

        // Search in left subtree
        TreeNode left = lowestCommonAncestorOptimal(root.left, p, q);

        // Search in right subtree
        TreeNode right = lowestCommonAncestorOptimal(root.right, p, q);

        // p and q found in different subtrees
        if (left != null && right != null) {
            return root;
        }

        // Return whichever subtree contains p or q
        return left != null ? left : right;
    }

    // --------------------------------------------------
    // MAIN METHOD
    // --------------------------------------------------

    public static void main(String[] args) {

        Day97_LowestCommonAncestorOfBinaryTree obj = new Day97_LowestCommonAncestorOfBinaryTree();

        /*
                 3
                / \
               5   1
              / \ / \
             6  2 0  8
               / \
              7   4

             p = 5
             q = 1

             LCA = 3
        */

        TreeNode root = new TreeNode(3, new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))), new TreeNode(1, new TreeNode(0), new TreeNode(8)));

        TreeNode p = root.left;
        TreeNode q = root.right;

        TreeNode brute = obj.lowestCommonAncestorBrute(root, p, q);

        TreeNode optimal = obj.lowestCommonAncestorOptimal(root, p, q);

        System.out.println("Brute Force LCA : " + brute.val);

        System.out.println("Optimal LCA : " + optimal.val);
    }
}
