class Solution {
    public int minCost(int[][] mat) {
        int n = mat.length;
        if (n == 0) return 0;

        // Tracks the minimum cost ending with choice 0, 1, and 2
        int prev0 = mat[0][0];
        int prev1 = mat[0][1];
        int prev2 = mat[0][2];

        for (int i = 1; i < n; i++) {
            int curr0 = mat[i][0] + Math.min(prev1, prev2);
            int curr1 = mat[i][1] + Math.min(prev0, prev2);
            int curr2 = mat[i][2] + Math.min(prev0, prev1);

            prev0 = curr0;
            prev1 = curr1;
            prev2 = curr2;
        }

        return Math.min(prev0, Math.min(prev1, prev2));
    }
}