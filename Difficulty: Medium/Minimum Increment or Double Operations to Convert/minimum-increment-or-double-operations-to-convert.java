class Solution {

    public int countMinOperations(int[] arr) {
        int increments = 0;
        int maxDoublings = 0;

        for (int num : arr) {
            int doublings = 0;
            int temp = num;

            while (temp > 0) {
                if ((temp & 1) == 1) {
                    increments++;
                }

                temp >>= 1;

                if (temp > 0) {
                    doublings++;
                }
            }

            maxDoublings = Math.max(maxDoublings, doublings);
        }

        return increments + maxDoublings;
    }
}