class Solution {

    int count(int n, int m) {

        int[][] dp = new int[n + 1][m + 1];

        // Base case
        for (int j = 1; j <= m; j++) {
            dp[1][j] = 1;
        }

        // DP
        for (int len = 2; len <= n; len++) {
            for (int curr = 1; curr <= m; curr++) {
                for (int prev = 1; prev <= m; prev++) {
                    if (curr % prev == 0 || prev % curr == 0) {
                        dp[len][curr] += dp[len - 1][prev];
                    }
                }
            }
        }

        int ans = 0;
        for (int j = 1; j <= m; j++) {
            ans += dp[n][j];
        }

        return ans;
    }
}