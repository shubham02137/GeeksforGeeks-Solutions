class Solution {
    public long pairAndSum(int[] arr) {
        long totalSum = 0;

        // Iterate through all 32 possible bit positions
        for (int bit = 0; bit < 32; bit++) {
            long countSetBits = 0;

            for (int num : arr) {
                if ((num & (1 << bit)) != 0) {
                    countSetBits++;
                }
            }

            // Number of pairs where both elements have the k-th bit set: C(count, 2)
            long pairs = (countSetBits * (countSetBits - 1)) / 2;

            // Add the contribution of the current bit position to the total sum
            totalSum += pairs * (1L << bit);
        }

        return totalSum;
    }
}