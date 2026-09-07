import java.util.Arrays;

class Solution {
    private int[][][] memo;

    public int minCount(int[] arr) {
        int n = arr.length;
        // memo[index][last_inc_val][last_dec_val]
        // Values range from 1 to 100, so last_inc is 0..100 and last_dec is 0..101
        memo = new int[n][102][102];
        for (int[][] row : memo) {
            for (int[] subRow : row) {
                Arrays.fill(subRow, -1);
            }
        }

        int maxIncluded = getMaxIncluded(0, 0, 101, arr);
        return n - maxIncluded;
    }

    private int getMaxIncluded(int idx, int lastInc, int lastDec, int[] arr) {
        if (idx == arr.length) {
            return 0;
        }

        if (memo[idx][lastInc][lastDec] != -1) {
            return memo[idx][lastInc][lastDec];
        }

        // Option 1: Skip arr[idx]
        int ans = getMaxIncluded(idx + 1, lastInc, lastDec, arr);

        // Option 2: Add arr[idx] to increasing subsequence
        if (arr[idx] > lastInc) {
            ans = Math.max(ans, 1 + getMaxIncluded(idx + 1, arr[idx], lastDec, arr));
        }

        // Option 3: Add arr[idx] to decreasing subsequence
        if (arr[idx] < lastDec) {
            ans = Math.max(ans, 1 + getMaxIncluded(idx + 1, lastInc, arr[idx], arr));
        }

        return memo[idx][lastInc][lastDec] = ans;
    }
}