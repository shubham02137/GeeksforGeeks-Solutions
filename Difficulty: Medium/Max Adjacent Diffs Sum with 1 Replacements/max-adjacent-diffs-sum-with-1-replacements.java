class Solution {
    public int maxDiffSum(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return 0;
        }

        // dp0: max sum if arr[i-1] was replaced by 1
        // dp1: max sum if arr[i-1] retained its original value
        int dp0 = 0;
        int dp1 = 0;

        for (int i = 1; i < n; i++) {
            // Option 1: arr[i] is replaced by 1
            // from previous being 1: dp0 + |1 - 1|
            // from previous being arr[i-1]: dp1 + |1 - arr[i-1]|
            int nextDp0 = Math.max(dp0 + 0, dp1 + Math.abs(1 - arr[i - 1]));

            // Option 2: arr[i] keeps its original value arr[i]
            // from previous being 1: dp0 + |arr[i] - 1|
            // from previous being arr[i-1]: dp1 + |arr[i] - arr[i-1]|
            int nextDp1 = Math.max(dp0 + Math.abs(arr[i] - 1), dp1 + Math.abs(arr[i] - arr[i - 1]));

            dp0 = nextDp0;
            dp1 = nextDp1;
        }

        return Math.max(dp0, dp1);
    }
}