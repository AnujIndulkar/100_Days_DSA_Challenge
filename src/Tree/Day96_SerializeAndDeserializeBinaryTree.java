package Tree;
import java.util.*;

public class Day96_SerializeAndDeserializeBinaryTree {

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
    // Topic: Binary Tree, Recursion, Serialization
    // Time Complexity: O(n²)
    // Space Complexity: O(n)
    // --------------------------------------------------

    public String serializeBrute(TreeNode root) {

        if (root == null) {
            return "null,";
        }

        // String concatenation creates new strings
        return root.val + "," + serializeBrute(root.left) + serializeBrute(root.right);
    }

    public TreeNode deserializeBrute(String data) {

        String[] values = data.split(",");

        Queue<String> queue = new LinkedList<>(Arrays.asList(values));

        return buildTreeBrute(queue);
    }

    private TreeNode buildTreeBrute(Queue<String> queue) {

        String value = queue.poll();

        if (value.equals("null")) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(value));

        node.left = buildTreeBrute(queue);

        node.right = buildTreeBrute(queue);

        return node;
    }

    // --------------------------------------------------
    // 2. OPTIMAL
    // Topic: Binary Tree, DFS, Recursion, StringBuilder
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // --------------------------------------------------

    public String serializeOptimal(TreeNode root) {

        StringBuilder result = new StringBuilder();

        serializeDFS(root, result);

        return result.toString();
    }

    private void serializeDFS(TreeNode node, StringBuilder result) {

        if (node == null) {

            result.append("null,");
            return;
        }

        result.append(node.val).append(",");

        serializeDFS(node.left, result);

        serializeDFS(node.right, result);
    }

    public TreeNode deserializeOptimal(String data) {

        String[] values = data.split(",");

        Queue<String> queue = new LinkedList<>(Arrays.asList(values));

        return deserializeDFS(queue);
    }

    private TreeNode deserializeDFS(Queue<String> queue) {

        String value = queue.poll();

        if (value.equals("null")) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(value));

        node.left = deserializeDFS(queue);

        node.right = deserializeDFS(queue);

        return node;
    }


    public static void main(String[] args) {

        Day96_SerializeAndDeserializeBinaryTree obj = new Day96_SerializeAndDeserializeBinaryTree();

        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), new TreeNode(5)));

        // Brute Force
        String bruteData = obj.serializeBrute(root);

        System.out.println("Brute Serialized : " + bruteData);

        TreeNode bruteTree = obj.deserializeBrute(bruteData);

        System.out.println("Brute Deserialized : " + obj.serializeBrute(bruteTree));

        // Optimal
        String optimalData = obj.serializeOptimal(root);

        System.out.println("Optimal Serialized : " + optimalData);

        TreeNode optimalTree = obj.deserializeOptimal(optimalData);

        System.out.println("Optimal Deserialized : " + obj.serializeOptimal(optimalTree));
    }
}