class Solution {
    public int zigzagSequence(int[][] mat) {
        int n = mat.length;
        if (n == 1) return mat[0][0];

        // dp[j] stores the max path sum ending at mat[i-1][j]
        int[] dp = new int[n];
        for (int j = 0; j < n; j++) {
            dp[j] = mat[0][j];
        }

        for (int i = 1; i < n; i++) {
            // Find max1, max2, and maxCol from previous row's dp
            int max1 = -1, max2 = -1, maxCol = -1;

            for (int j = 0; j < n; j++) {
                if (dp[j] > max1) {
                    max2 = max1;
                    max1 = dp[j];
                    maxCol = j;
                } else if (dp[j] > max2) {
                    max2 = dp[j];
                }
            }

            // Compute new DP values for row i
            int[] nextDp = new int[n];
            for (int j = 0; j < n; j++) {
                if (j != maxCol) {
                    nextDp[j] = mat[i][j] + max1;
                } else {
                    nextDp[j] = mat[i][j] + max2;
                }
            }

            dp = nextDp;
        }

        // Find max in bottom row
        int maxAnswer = 0;
        for (int val : dp) {
            maxAnswer = Math.max(maxAnswer, val);
        }

        return maxAnswer;
    }
}