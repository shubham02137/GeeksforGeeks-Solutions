class Solution {
    public int maxFruits(ArrayList<Integer> arr, int m) {
        int n = arr.size();
        if (m >= n) {
            int total = 0;
            for (int val : arr) {
                total += val;
            }
            return total;
        }

        // Sum of the first window of size m
        int currentSum = 0;
        for (int i = 0; i < m; i++) {
            currentSum += arr.get(i);
        }

        int maxSum = currentSum;

        // Slide the window of size m across the circular array
        for (int i = 0; i < n - 1; i++) {
            currentSum = currentSum - arr.get(i) + arr.get((i + m) % n);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}