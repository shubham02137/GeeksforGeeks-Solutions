class Solution {
    public int minCost(int n, int i, int d, int c) {
        if (n == 0) return 0;
        if (n == 1) return i;

        int[] dp = new int[n + 1];
        dp[1] = i;

        for (int k = 2; k <= n; k++) {
            if (k % 2 == 0) {
                // Either insert 1 from (k - 1) or copy-paste from (k / 2)
                dp[k] = Math.min(dp[k - 1] + i, dp[k / 2] + c);
            } else {
                // Either insert 1 from (k - 1) or copy-paste from ((k + 1) / 2) and delete 1
                dp[k] = Math.min(dp[k - 1] + i, dp[(k + 1) / 2] + c + d);
            }
        }

        return dp[n];
    }
}