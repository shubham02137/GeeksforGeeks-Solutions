import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public static int numberOfCells(int r, int c, int u, int d, char[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        // If start cell is out of bounds or an obstacle
        if (r < 0 || r >= n || c < 0 || c >= m || mat[r][c] == '#') {
            return 0;
        }

        // minUp[i][j] stores the minimum up-moves required to reach (i, j)
        int[][] minUp = new int[n][m];
        for (int[] row : minUp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // 0-1 BFS using Deque
        Deque<int[]> deque = new ArrayDeque<>();
        minUp[r][c] = 0;
        deque.offer(new int[]{r, c});

        // Directions: Left, Right, Down, Up
        int[] dr = {0, 0, 1, -1};
        int[] dc = {-1, 1, 0, 0};

        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int cr = curr[0];
            int cc = curr[1];
            int upMoves = minUp[cr][cc];

            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && mat[nr][nc] != '#') {
                    int weight = (i == 3) ? 1 : 0; // Up move costs 1, others cost 0
                    int nextUp = upMoves + weight;
                    // downMoves = nextUp + (nr - r)
                    int nextDown = nextUp + (nr - r);

                    if (nextUp <= u && nextDown <= d && nextUp < minUp[nr][nc]) {
                        minUp[nr][nc] = nextUp;
                        if (weight == 0) {
                            deque.offerFirst(new int[]{nr, nc});
                        } else {
                            deque.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }

        // Count unique reachable cells
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (minUp[i][j] != Integer.MAX_VALUE) {
                    ans++;
                }
            }
        }

        return ans;
    }
}