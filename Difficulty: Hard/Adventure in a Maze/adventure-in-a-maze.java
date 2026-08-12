import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> findWays(int[][] grid) {
        int n = grid.length;
        int MOD = 1000000007;

        // count[i][j] stores total number of valid paths to reach (i, j)
        int[][] count = new int[n][n];
        // maxScore[i][j] stores the maximum adventure value to reach (i, j)
        int[][] maxScore = new int[n][n];

        // Base case: starting cell (0, 0)
        count[0][0] = 1;
        maxScore[0][0] = grid[0][0];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // If starting cell, skip since it's already initialized
                if (i == 0 && j == 0) continue;

                int waysFromLeft = 0, scoreFromLeft = 0;
                int waysFromTop = 0, scoreFromTop = 0;

                // Check transition from Left (i, j - 1)
                if (j > 0 && count[i][j - 1] > 0) {
                    int prevVal = grid[i][j - 1];
                    // Direction allowed to move right: 1 or 3
                    if (prevVal == 1 || prevVal == 3) {
                        waysFromLeft = count[i][j - 1];
                        scoreFromLeft = maxScore[i][j - 1] + grid[i][j];
                    }
                }

                // Check transition from Top (i - 1, j)
                if (i > 0 && count[i - 1][j] > 0) {
                    int prevVal = grid[i - 1][j];
                    // Direction allowed to move down: 2 or 3
                    if (prevVal == 2 || prevVal == 3) {
                        waysFromTop = count[i - 1][j];
                        scoreFromTop = maxScore[i - 1][j] + grid[i][j];
                    }
                }

                // Combine results from Left and Top transitions
                if (waysFromLeft > 0 || waysFromTop > 0) {
                    count[i][j] = (waysFromLeft + waysFromTop) % MOD;
                    maxScore[i][j] = Math.max(scoreFromLeft, scoreFromTop);
                }
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.add(count[n - 1][n - 1]);
        result.add(maxScore[n - 1][n - 1]);
        return result;
    }
}