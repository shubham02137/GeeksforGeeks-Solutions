class Solution {
    public int pairCount(int x, int y) {
        // LCM must be divisible by GCD
        if (y % x != 0) {
            return 0;
        }

        int k = y / x;
        int distinctPrimeFactors = 0;

        // Count distinct prime factors of k = y / x
        for (int d = 2; d * d <= k; d++) {
            if (k % d == 0) {
                distinctPrimeFactors++;
                while (k % d == 0) {
                    k /= d;
                }
            }
        }

        if (k > 1) {
            distinctPrimeFactors++;
        }

        // Each distinct prime factor power must go entirely to either a' or b'
        return 1 << distinctPrimeFactors;
    }
}