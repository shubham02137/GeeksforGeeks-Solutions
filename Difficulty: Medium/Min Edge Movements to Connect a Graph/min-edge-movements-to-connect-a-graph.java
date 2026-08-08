class Solution {
    public int minEdgesReq(int n, int[][] edges) {
        int m = edges.length;
        
        // Impossible if total edges are less than n - 1
        if (m < n - 1) {
            return -1;
        }

        DSU dsu = new DSU(n);
        int components = n;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // If u and v were in different components, union them
            if (dsu.union(u, v)) {
                components--;
            }
        }

        // To connect 'components' separate components, we need (components - 1) operations
        return components - 1;
    }

    private static class DSU {
        private final int[] parent;

        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]); // Path compression
        }

        public boolean union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);

            if (rootI != rootJ) {
                parent[rootI] = rootJ;
                return true;
            }
            return false;
        }
    }
}