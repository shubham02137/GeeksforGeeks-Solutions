class Solution {
    public int maxSumWithK(int[] arr, int k) {
        int n = arr.length;

        // Kadane: max subarray sum ending at each index
        int[] maxEndHere = new int[n];
        maxEndHere[0] = arr[0];

        for (int i = 1; i < n; i++) {
            maxEndHere[i] = Math.max(arr[i], maxEndHere[i - 1] + arr[i]);
        }

        // Sum of first k elements
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        int ans = windowSum;

        // Slide the window
        for (int i = k; i < n; i++) {
            windowSum += arr[i] - arr[i - k];

            // Only current window
            ans = Math.max(ans, windowSum);

            // Extend with previous best subarray
            ans = Math.max(ans, windowSum + maxEndHere[i - k]);
        }

        return ans;
    }
}