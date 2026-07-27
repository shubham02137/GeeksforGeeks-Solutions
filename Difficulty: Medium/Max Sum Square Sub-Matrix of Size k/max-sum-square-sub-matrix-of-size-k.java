class Solution {
    public int maximumSum(int[][] mat, int k) {

        int n = mat.length;

        long[][] prefix = new long[n + 1][n + 1];

        // Build 2D prefix sum
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                prefix[i][j] =
                        mat[i - 1][j - 1]
                        + prefix[i - 1][j]
                        + prefix[i][j - 1]
                        - prefix[i - 1][j - 1];
            }
        }

        long maxSum = Long.MIN_VALUE;

        // Calculate sum of every k x k sub-matrix
        for (int i = k; i <= n; i++) {
            for (int j = k; j <= n; j++) {

                long sum =
                        prefix[i][j]
                        - prefix[i - k][j]
                        - prefix[i][j - k]
                        + prefix[i - k][j - k];

                maxSum = Math.max(maxSum, sum);
            }
        }

        return (int) maxSum;
    }
}