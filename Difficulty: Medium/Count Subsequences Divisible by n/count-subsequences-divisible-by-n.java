class Solution {
    public int countSubsequences(String s, int n) {
        int MOD = 1_000_000_007;
        int[] dp = new int[n];

        for (int i = 0; i < s.length(); i++) {
            int digit = s.charAt(i) - '0';
            int[] nextDp = dp.clone();

            // Form a single-digit subsequence starting with the current digit
            nextDp[digit % n] = (nextDp[digit % n] + 1) % MOD;

            // Append current digit to all existing subsequences
            for (int r = 0; r < n; r++) {
                if (dp[r] > 0) {
                    int newRem = (r * 10 + digit) % n;
                    nextDp[newRem] = (nextDp[newRem] + dp[r]) % MOD;
                }
            }

            dp = nextDp;
        }

        return dp[0];
    }
}