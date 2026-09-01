class Solution {
    public int palindromicStrings(int n, int k) {
        long MOD = 1_000_000_007L;
        long totalCount = 0;

        for (int len = 1; len <= n; len++) {
            int distinctNeeded = (len + 1) / 2;

            if (distinctNeeded > k) {
                continue;
            }

            // Permutation P(k, distinctNeeded) = k * (k - 1) * ... * (k - distinctNeeded + 1)
            long ways = 1;
            for (int i = 0; i < distinctNeeded; i++) {
                ways = (ways * (k - i)) % MOD;
            }

            totalCount = (totalCount + ways) % MOD;
        }

        return (int) totalCount;
    }
}