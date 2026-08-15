import java.util.Arrays;

class Solution {
    private int[][][] memo;
    private String numStr;
    private int targetD;

    public int countWithout(int n, int d) {
        if (n <= 0) return 0;

        this.numStr = String.valueOf(n);
        this.targetD = d;
        this.memo = new int[numStr.length()][2][2];

        for (int[][] row : memo) {
            for (int[] subRow : row) {
                Arrays.fill(subRow, -1);
            }
        }

        return solve(0, 1, 1);
    }

    private int solve(int idx, int tight, int leadingZero) {
        if (idx == numStr.length()) {
            return leadingZero == 1 ? 0 : 1;
        }

        if (memo[idx][tight][leadingZero] != -1) {
            return memo[idx][tight][leadingZero];
        }

        int limit = (tight == 1) ? (numStr.charAt(idx) - '0') : 9;
        int count = 0;

        for (int digit = 0; digit <= limit; digit++) {
            if (!(leadingZero == 1 && digit == 0) && digit == targetD) {
                continue;
            }

            int nextTight = (tight == 1 && digit == limit) ? 1 : 0;
            int nextLeadingZero = (leadingZero == 1 && digit == 0) ? 1 : 0;

            count += solve(idx + 1, nextTight, nextLeadingZero);
        }

        return memo[idx][tight][leadingZero] = count;
    }
}