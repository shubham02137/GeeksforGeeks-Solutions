import java.util.*;

class Solution {
    public int minThrows(int n, int lad[], int sn[]) {
        int target = n * n;

        // Map moves: moves[i] stores the destination if there is a snake/ladder at i
        int[] moves = new int[target + 1];
        Arrays.fill(moves, -1);

        for (int i = 0; i < lad.length; i += 2) {
            moves[lad[i]] = lad[i + 1];
        }
        for (int i = 0; i < sn.length; i += 2) {
            moves[sn[i]] = sn[i + 1];
        }

        // BFS to find the shortest path (minimum throws)
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[target + 1];

        // {current_cell, throws_count}
        queue.offer(new int[]{1, 0});
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cell = curr[0];
            int dist = curr[1];

            if (cell == target) {
                return dist;
            }

            // Try all possible dice rolls (1 to 6)
            for (int dice = 1; dice <= 6; dice++) {
                int nextCell = cell + dice;

                if (nextCell <= target) {
                    // Check if there is a snake or ladder at nextCell
                    int dest = (moves[nextCell] != -1) ? moves[nextCell] : nextCell;

                    if (!visited[dest]) {
                        visited[dest] = true;
                        queue.offer(new int[]{dest, dist + 1});
                    }
                }
            }
        }

        return -1;
    }
}