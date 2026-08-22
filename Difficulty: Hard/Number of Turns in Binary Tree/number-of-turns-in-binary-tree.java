/* Structure of Binary Tree Node
class Node {
    int data;
    Node left;
    Node right;

    Node(int val) {
        data = val;
        left = right = null;
    }
}
*/

class Solution {
    private int count = 0;

    public int numberOfTurns(Node root, int p, int q) {
        if (root == null || p == q) return -1;

        Node lca = findLCA(root, p, q);
        if (lca == null) return -1;

        count = 0;

        // Case 1: LCA is one of the nodes (p or q)
        if (lca.data == p) {
            // Find turns from p to q (passing false for both left/right child searches)
            countTurns(lca.left, q, true, 0);
            countTurns(lca.right, q, false, 0);
            return count == 0 ? -1 : count;
        } 
        else if (lca.data == q) {
            // Find turns from q to p
            countTurns(lca.left, p, true, 0);
            countTurns(lca.right, p, false, 0);
            return count == 0 ? -1 : count;
        }

        // Case 2: LCA is an ancestor of both p and q
        // Search left subtree for p and right subtree for q (or vice versa)
        countTurns(lca.left, p, true, 0);
        countTurns(lca.right, p, false, 0);
        countTurns(lca.left, q, true, 0);
        countTurns(lca.right, q, false, 0);

        // Traversing up to LCA from one branch and down into the other branch adds 1 turn at the LCA
        return (count + 1 == 0) ? -1 : count + 1;
    }

    private Node findLCA(Node root, int p, int q) {
        if (root == null || root.data == p || root.data == q) {
            return root;
        }

        Node left = findLCA(root.left, p, q);
        Node right = findLCA(root.right, p, q);

        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    private boolean countTurns(Node node, int target, boolean isLeft, int turns) {
        if (node == null) return false;

        if (node.data == target) {
            this.count += turns;
            return true;
        }

        // Turning from right to left or continuing left
        if (isLeft) {
            if (countTurns(node.left, target, true, turns)) return true;
            if (countTurns(node.right, target, false, turns + 1)) return true;
        } else {
            // Turning from left to right or continuing right
            if (countTurns(node.left, target, true, turns + 1)) return true;
            if (countTurns(node.right, target, false, turns)) return true;
        }

        return false;
    }
}