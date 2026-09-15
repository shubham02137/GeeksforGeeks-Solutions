import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int shortestPath(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        // Step 1: Identify all unsafe cells (landmines and their 4-directional neighbors)
        boolean[][] isUnsafe = new boolean[n][m];
        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    isUnsafe[i][j] = true;
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dRow[d];
                        int nj = j + dCol[d];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                            isUnsafe[ni][nj] = true;
                        }
                    }
                }
            }
        }

        // Step 2: Multi-source BFS starting from all safe cells in column 0
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            if (!isUnsafe[i][0]) {
                queue.offer(new int[]{i, 0, 1}); // {row, col, distance}
                visited[i][0] = true;
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int dist = curr[2];

            // Reached the destination (rightmost column)
            if (c == m - 1) {
                return dist;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dRow[d];
                int nc = c + dCol[d];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && !isUnsafe[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, dist + 1});
                }
            }
        }

        return -1;
    }
}