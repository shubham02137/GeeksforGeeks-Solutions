import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> largestSquare(int[][] mat, int[][] queries, int k) {
        int n = mat.length;
        int m = mat[0].length;
        
        // Build 2D Prefix Sum Array (1-indexed for convenience)
        int[][] pref = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                pref[i + 1][j + 1] = mat[i][j] + pref[i][j + 1] + pref[i + 1][j] - pref[i][j];
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for (int[] q : queries) {
            int r = q[0];
            int c = q[1];
            
            // Check if center cell itself exceeds k
            if (mat[r][c] > k) {
                ans.add(-1);
                continue;
            }
            
            // Maximum expansion radius possible based on matrix boundaries
            int maxL = Math.min(Math.min(r, n - 1 - r), Math.min(c, m - 1 - c));
            
            int low = 0, high = maxL, bestL = 0;
            
            // Binary search for max valid radius L
            while (low <= high) {
                int mid = low + (high - low) / 2;
                
                // Calculate square boundaries for radius mid
                int r1 = r - mid;
                int c1 = c - mid;
                int r2 = r + mid;
                int c2 = c + mid;
                
                // Query total 1s in submatrix [r1..r2][c1..c2]
                int ones = pref[r2 + 1][c2 + 1] - pref[r1][c2 + 1] - pref[r2 + 1][c1] + pref[r1][c1];
                
                if (ones <= k) {
                    bestL = mid;
                    low = mid + 1; // Try expanding further
                } else {
                    high = mid - 1; // Square contains too many 1s
                }
            }
            
            // Side length of odd square with radius bestL is 2 * bestL + 1
            ans.add(2 * bestL + 1);
        }
        
        return ans;
    }
}