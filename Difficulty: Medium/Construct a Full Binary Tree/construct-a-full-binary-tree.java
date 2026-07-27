class Solution {
    private int preIndex;

    public Node constructBinaryTree(int[] pre, int[] preMirror) {
        int n = pre.length;

        HashMap<Integer, Integer> mirrorIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            mirrorIndex.put(preMirror[i], i);
        }

        preIndex = 0;
        return build(pre, 0, n - 1, mirrorIndex);
    }

    private Node build(int[] pre, int left, int right,
                       HashMap<Integer, Integer> mirrorIndex) {

        if (preIndex >= pre.length || left > right) {
            return null;
        }

        Node root = new Node(pre[preIndex++]);

        if (left == right || preIndex >= pre.length) {
            return root;
        }

        int leftRootValue = pre[preIndex];
        int index = mirrorIndex.get(leftRootValue);

        root.left = build(pre, index, right, mirrorIndex);
        root.right = build(pre, left + 1, index - 1, mirrorIndex);

        return root;
    }
}