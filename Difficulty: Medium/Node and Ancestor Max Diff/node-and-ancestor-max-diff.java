/*
class Node {
    int data;
    Node left, right;
    Node(int item) {
        data = item;
        left = right = null;
    }
}
*/

class Solution {
    private int maxDifference;

    int maxDiff(Node root) {
        maxDifference = Integer.MIN_VALUE;
        findMin(root);
        return maxDifference;
    }

    private int findMin(Node node) {
        if (node == null) {
            return Integer.MAX_VALUE;
        }

        // Leaf nodes do not have descendants
        if (node.left == null && node.right == null) {
            return node.data;
        }

        // Recursively find the minimum value in the left and right subtrees
        int leftMin = findMin(node.left);
        int rightMin = findMin(node.right);

        int minChild = Math.min(leftMin, rightMin);

        // Update the maximum difference: Ancestor (current) - Descendant (min in subtree)
        maxDifference = Math.max(maxDifference, node.data - minChild);

        // Return the minimum node value in the current subtree
        return Math.min(node.data, minChild);
    }
}