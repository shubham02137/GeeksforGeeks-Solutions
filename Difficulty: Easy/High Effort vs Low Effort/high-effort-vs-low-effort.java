class Solution {
    public int maxTask(int[] h, int[] l) {
        int n = h.length;
        if (n == 0) return 0;
        if (n == 1) return Math.max(h[0], l[0]);

        // dp[i-2] and dp[i-1] representation
        int prev2 = 0; // dp[-1] concept
        int prev1 = Math.max(h[0], l[0]); // dp[0]

        for (int i = 1; i < n; i++) {
            // Choice 1: Low-effort task on day i -> prev1 + l[i]
            // Choice 2: High-effort task on day i -> prev2 + h[i]
            int current = Math.max(prev1 + l[i], prev2 + h[i]);
            
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}