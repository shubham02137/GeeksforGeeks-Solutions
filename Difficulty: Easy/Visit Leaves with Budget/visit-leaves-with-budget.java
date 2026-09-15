import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int getCount(Node root, int k) {
        if (root == null || k <= 0) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        int level = 1;
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();

                // Check if curr is a leaf node
                if (curr.left == null && curr.right == null) {
                    if (k >= level) {
                        k -= level;
                        count++;
                    } else {
                        // Not enough budget left for this or deeper leaves
                        return count;
                    }
                }

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }

            level++;
        }

        return count;
    }
}