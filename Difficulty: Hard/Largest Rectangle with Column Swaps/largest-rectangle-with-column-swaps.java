class Solution {
    public int maxArea(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        // Step 1: Precompute consecutive 1s ending at each row
        int[][] hist = new int[n][m];
        for (int j = 0; j < m; j++) {
            hist[0][j] = mat[0][j];
            for (int i = 1; i < n; i++) {
                hist[i][j] = (mat[i][j] == 0) ? 0 : hist[i - 1][j] + 1;
            }
        }

        int maxArea = 0;

        // Step 2 & 3: For each row, sort heights and find max area
        // Using counting sort since height values range from 0 to n
        for (int i = 0; i < n; i++) {
            int[] count = new int[n + 1];
            for (int j = 0; j < m; j++) {
                count[hist[i][j]]++;
            }

            int colCount = 0;
            // Traverse from maximum possible height down to 1
            for (int h = n; h >= 1; h--) {
                if (count[h] > 0) {
                    colCount += count[h];
                    maxArea = Math.max(maxArea, h * colCount);
                }
            }
        }

        return maxArea;
    }
}