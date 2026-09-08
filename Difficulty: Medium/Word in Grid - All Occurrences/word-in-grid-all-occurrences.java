class Solution {
    public ArrayList<ArrayList<Integer>> searchWord(char[][] mat, String word) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int n = mat.length;
        int m = mat[0].length;
        int len = word.length();

        // 8 possible directions: (row offset, col offset)
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        // Traverse each cell in row-major order to naturally maintain lexicographical order
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] != word.charAt(0)) {
                    continue;
                }

                // Check all 8 directions from cell (i, j)
                for (int dir = 0; dir < 8; dir++) {
                    int r = i;
                    int c = j;
                    int k = 0;

                    while (k < len) {
                        if (r < 0 || r >= n || c < 0 || c >= m || mat[r][c] != word.charAt(k)) {
                            break;
                        }
                        r += dx[dir];
                        c += dy[dir];
                        k++;
                    }

                    // Found an occurrence starting at (i, j)
                    if (k == len) {
                        ArrayList<Integer> pos = new ArrayList<>();
                        pos.add(i);
                        pos.add(j);
                        ans.add(pos);
                        // Once found in any direction from (i, j), break to avoid duplicate coordinate entries
                        break;
                    }
                }
            }
        }

        return ans;
    }
}