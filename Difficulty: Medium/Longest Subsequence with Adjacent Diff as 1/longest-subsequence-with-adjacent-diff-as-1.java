class Solution {
    public int longestSubseq(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // Find maximum value to size the dp array, or use a map
        int maxVal = 0;
        for (int num : arr) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

        // dp[v] stores the length of the longest valid subsequence ending with value v
        int[] dp = new int[maxVal + 2];
        int maxLen = 0;

        for (int num : arr) {
            int prev = (num > 0) ? dp[num - 1] : 0;
            int next = dp[num + 1];

            dp[num] = Math.max(prev, next) + 1;
            maxLen = Math.max(maxLen, dp[num]);
        }

        return maxLen;
    }
}