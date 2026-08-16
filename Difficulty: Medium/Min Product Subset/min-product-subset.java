class Solution {
    public int minProd(int[] arr) {
        int n = arr.length;
        if (n == 1) {
            return arr[0];
        }

        int negCount = 0;
        int zeroCount = 0;
        int posCount = 0;

        int minPos = Integer.MAX_VALUE;
        int maxNeg = Integer.MIN_VALUE;
        int prod = 1;

        for (int x : arr) {
            if (x == 0) {
                zeroCount++;
                continue;
            }
            if (x < 0) {
                negCount++;
                maxNeg = Math.max(maxNeg, x); // largest negative (closest to 0)
            } else {
                posCount++;
                minPos = Math.min(minPos, x);
            }
            prod *= x;
        }

        // Case 1: No negative numbers
        if (negCount == 0) {
            if (zeroCount > 0) {
                return 0;
            }
            return minPos;
        }

        // Case 2: Even number of negative numbers
        // Exclude the negative number with the smallest absolute value (largest negative)
        if (negCount % 2 == 0) {
            prod /= maxNeg;
        }

        return prod;
    }
}